package com.capitaworld.service.loans.service.sanctionimpl;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.notification.client.NotificationClient;
import com.capitaworld.service.notification.exceptions.NotificationException;
import com.capitaworld.service.notification.model.Notification;
import com.capitaworld.service.notification.model.NotificationRequest;
import com.capitaworld.service.notification.model.NotificationResponse;
import com.capitaworld.service.notification.utils.ContentType;
import com.capitaworld.service.notification.utils.NotificationAlias;
import com.capitaworld.service.notification.utils.NotificationType;
import com.capitaworld.service.users.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.config.AuditComponentBankToCW;
import com.capitaworld.service.loans.config.FPAsyncComponent;
import com.capitaworld.service.loans.domain.BankCWAuditTrailDomain;
import com.capitaworld.service.loans.domain.fundseeker.IneligibleProposalDetails;
import com.capitaworld.service.loans.domain.sanction.LoanSanctionDomain;
import com.capitaworld.service.loans.model.LoanSanctionRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.sanction.LoanSanctionAndDisbursedRequest;
import com.capitaworld.service.loans.repository.OfflineProcessedAppRepository;
import com.capitaworld.service.loans.repository.banktocw.BankToCWAuditTrailRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.sanction.LoanSanctionRepository;
import com.capitaworld.service.loans.service.sanction.LoanDisbursementService;
import com.capitaworld.service.loans.service.sanction.LoanSanctionService;
import com.capitaworld.service.loans.utils.CommonUtility;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.utils.OrganisationConfiguration;
import com.capitaworld.sidbi.integration.client.SidbiIntegrationClient;
import com.capitaworld.sidbi.integration.model.GenerateTokenRequest;
import com.capitaworld.sidbi.integration.util.AESEncryptionUtility;
import com.capitaworld.sidbi.integration.util.AESEncryptionUtilitySBI;
/**
 * @author Ankit
 *
 */

@Service
@Transactional
public class LoanSanctionServiceImpl implements LoanSanctionService {
	private static final Logger logger = LoggerFactory.getLogger(LoanSanctionServiceImpl.class);

	private static final String SUCCESS_LITERAL = "SUCCESS";
	private static final String ERROR_LITERAL = "error";
	private static final String USER_NAME = "userName";
	private static final String HO_NAME = "ho";
	private static final String HO="HO";
	private static final String CHECKER_NAME = "checkerName";
	private static final String BO_CHECKER = "bo";
	private static final String BO="BO";
	private static final String MAKER="Maker";
	private static final String MAKER_NAME= "makerName";
	private static final String CHECKER="Checker";
	private static final String EMAIL_ADDRESS_FROM="no-replay@onlinpsbloans.com";
	private static final String ISDYNAMIC="isDynamic";
	private static final String NAME_OF_ENTITY = "nameOfEntity";
	private static final String CLICK_HERE_TO_SEE_THE_PROPOSAL_DETAILS = "clickHereToSeeTheProposalDetails";
	private static final String APPLICATION_ID = "applicationId";

	@Autowired
	private LoanSanctionRepository loanSanctionRepository;
	
	@Autowired 
	private ProposalDetailsRepository proposalDetailsRepository;
	
	@Autowired
	private UsersClient userClient;
	
	@Value("${capitaworld.sidbi.integration.is_production}")
	private String isProduction; 
	
	@Value("${capitaworld.sidbi.integration.reverse_api_timeout}")
	private String reverseAPITimeOut; 
	
	@Autowired
	private SidbiIntegrationClient sidbiIntegrationClient;
	
	@Autowired
	private AuditComponentBankToCW auditComponentBankToCW;
	
	@Autowired
	private LoanDisbursementService loanDisbursementService;

	@Autowired
	private FPAsyncComponent fpAsyncComponent;
	
	@Autowired
	private BankToCWAuditTrailRepository bankToCWAuditTrailRepository;
	
	@Autowired
	private OfflineProcessedAppRepository offlineProcessedAppRepository;

	@Autowired
	private NotificationClient notiClient;

	@Autowired
	private LoanApplicationService loanApplicationService;

	@Autowired
	private ApplicationProposalMappingRepository applicationProposalMappingRepository;

	@Override
	public Boolean saveLoanSanctionDetail(LoanSanctionRequest loanSanctionRequest) throws Exception {
		try {
		logger.info("Enter in saveLoanSanctionDetail() ----------------------->  LoanSanctionRequest==> "+ loanSanctionRequest);
		
		LoanSanctionDomain loanSanctionDomainOld =loanSanctionRepository.findByAppliationId(loanSanctionRequest.getApplicationId());
		if(CommonUtils.isObjectNullOrEmpty(loanSanctionDomainOld) ) {
			loanSanctionDomainOld = new LoanSanctionDomain();
			loanSanctionDomainOld.setOrgId(!CommonUtils.isObjectNullOrEmpty(loanSanctionRequest.getOrgId()) ? loanSanctionRequest.getOrgId() : null);
			if(loanSanctionRequest.getIsIneligibleProposal() != null && loanSanctionRequest.getIsIneligibleProposal() == true) {
				loanSanctionDomainOld.setIsSanctionedFrom(loanSanctionRequest.getIsSanctionedFrom());
				IneligibleProposalDetails ineligibleProposalDetails = (IneligibleProposalDetails) offlineProcessedAppRepository.findByAppliationId(loanSanctionRequest.getApplicationId());
				ineligibleProposalDetails.setIsSanctioned(true);
			}else if(CommonUtils.isObjectNullOrEmpty(loanSanctionRequest.getIsIneligibleProposal()) || loanSanctionRequest.getIsIneligibleProposal() == false) {
				loanSanctionDomainOld.setIsSanctionedFrom(CommonUtils.sanctionedFrom.ELIGIBLE_USERS);
			}
			BeanUtils.copyProperties(loanSanctionRequest, loanSanctionDomainOld,"id");
			loanSanctionDomainOld.setCreatedBy(loanSanctionRequest.getActionBy());
			loanSanctionDomainOld.setCreatedDate(new Date());
			loanSanctionDomainOld.setIsActive(true);
		}else{
			//BeanUtils.copyProperties(loanSanctionRequest, loanSanctionDomainOld,"id");
			if(loanSanctionRequest.getIsIneligibleProposal() != null && loanSanctionRequest.getIsIneligibleProposal()) {
				loanSanctionDomainOld.setIsSanctionedFrom(loanSanctionRequest.getIsSanctionedFrom());
				IneligibleProposalDetails ineligibleProposalDetails = (IneligibleProposalDetails) offlineProcessedAppRepository.findByAppliationId(loanSanctionRequest.getApplicationId());
				ineligibleProposalDetails.setIsSanctioned(true);
			}else {
				loanSanctionDomainOld.setIsSanctionedFrom(CommonUtils.sanctionedFrom.ELIGIBLE_USERS);
			}
			
			loanSanctionDomainOld.setSanctionAmount(loanSanctionRequest.getSanctionAmount());
			loanSanctionDomainOld.setSanctionDate(new Date());
			loanSanctionDomainOld.setTenure(loanSanctionRequest.getTenure());
			loanSanctionDomainOld.setRoi(loanSanctionRequest.getRoi());
			loanSanctionDomainOld.setProcessingFee(loanSanctionRequest.getProcessingFee());
			loanSanctionDomainOld.setRemark(loanSanctionRequest.getRemark());
			loanSanctionDomainOld.setModifiedBy(loanSanctionRequest.getActionBy());
			loanSanctionDomainOld.setModifiedDate(new Date());
			loanSanctionDomainOld.setIsSanctionedFrom(1l);
		}
		//==================Sending Mail notification to Maker=============================
		
		fpAsyncComponent.sendEmailToFSWhenCheckerSanctionLoan(loanSanctionDomainOld);
		fpAsyncComponent.sendEmailToMakerHOBOWhenCheckerSanctionLoan(loanSanctionDomainOld);
		
		//=================================================================================
		//logger.info("Exit saveLoanSanctionDetail() -----------------------> LoanSanctionDomain "+ loanSanctionDomainOld);
		return loanSanctionRepository.save(loanSanctionDomainOld) != null;
		}catch (Exception e) {
			logger.error("Error/Exception in saveLoanSanctionDetail() -----------------------> Message : ",e);
			throw e;
		}

	}


	public Boolean sendMailToHOBOCheckerMakerForMultipleBanks(Long applicationId,LoanSanctionDomain loanSanctionDomainOld ){

		List<Object[]> proposalDetailByApplicationId = proposalDetailsRepository.findProposalDetailByApplicationId(applicationId);

		if(loanSanctionDomainOld!=null) {
			fpAsyncComponent.sendEmailToFSWhenCheckerSanctionLoan(loanSanctionDomainOld);
		}
	try{
		if (proposalDetailByApplicationId.get(1) != null){
			//multiple bank emails
			for(Object[] arr : proposalDetailByApplicationId ){

				Integer proposal_status = CommonUtils.convertInteger(arr[1]);
				logger.info("Proposal_Status ====>"+proposal_status);
				String proposal_code = CommonUtils.convertString(arr[2]);
				Long branch_id = CommonUtils.convertLong(arr[3]);
				logger.info("Branch_id ====>"+branch_id);


				if (proposal_status == 5 && branch_id != null) {

					UserResponse userResponse=  userClient.getBranchUsersListByBranchId(branch_id);

					for (int i=0;i<userResponse.getListData().size();i++) {
						try {
							String to[] = null;
							String smsTo = null;
							Map<String, Object> parameters = new HashMap<>();
							String mailTo = "";
							String fpName = "";
							String subject = "";
							Boolean result = false;
							String checkerName = "Checker";

							try {
								BranchUserResponse request = MultipleJSONObjectHelper.getObjectFromMap((Map) userResponse.getListData().get(i), BranchUserResponse.class);
								logger.info("BranchUser=>"+request );
								String user_id = request.getUserId();
								fpName = request.getUserName();
								String organizationName = loanApplicationService.getFsApplicantName(applicationId);

								ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.getByApplicationId(applicationId);
								String applicationCode = applicationProposalMapping.getApplicationCode();

								parameters.put(USER_NAME, fpName);
								if (request.getUserRole().equals(CHECKER)){
									if(request.getUserName() != "" && request.getUserName() != null) {
										checkerName = request.getUserName();
									}
									logger.info("Checker Name =====>"+checkerName);
								}
								to = new String[]{request.getEmail()};
								smsTo = request.getMobile();

								if (request.getUserRole().equals(HO)) {
									fpName = request.getUserName();
									subject = "Proposal of " + organizationName + " is sanctioned/disbursed and partially disbursed by other bank";
									parameters.put(APPLICATION_ID, applicationCode);
									parameters.put(NAME_OF_ENTITY, organizationName);
									parameters.put(CHECKER_NAME, checkerName);
									if (fpName != "" && fpName != null) {
										parameters.put(HO_NAME, fpName);
									}
									else{
										parameters.put(HO_NAME, "Sir/Madam");
									}

									logger.info("Subject ====> " + subject);
									logger.info("parameter fpName HO=====>"+parameters.get(HO_NAME));
									logger.info("Email id ====>"+to[0]);
									logger.info("Mobile No ====>"+smsTo);

									result = sendEmail(to,user_id,parameters, NotificationAlias.EMAIL_HO_MULTIPLE_BANK,subject);
									if(result) {
										logger.info("------Email send to "+to[0]+" and " + request.getUserRole()+" when Branch transfer-----");
									}else{
										logger.error("-----Error in sending email to "+to[0]+" and "+request.getUserRole()+" when Branch transfer------");
									}

									Boolean smsStatus = createNotificationForSMS(smsTo,user_id,parameters,NotificationAlias.SMS_HO_MULTIPLE_BANK);
									logger.info("SMS sending process complete STATUS is :" + smsStatus);

								}
								else if (request.getUserRole().equals(BO)) {
									fpName = request.getUserName();
									subject = "Proposal of " + organizationName + " is sanctioned/disbursed and partially disbursed by other bank";
									parameters.put(APPLICATION_ID, applicationCode);
									parameters.put(NAME_OF_ENTITY, organizationName);
									if (fpName != "" && fpName != null) {
										parameters.put(BO_CHECKER, fpName);
									}
									else{
										parameters.put(BO_CHECKER, "Sir/Madam");
									}

									logger.info("Subject ====> " + subject);
									logger.info("parameter fpName BO=====>"+parameters.get(BO_CHECKER));
									logger.info("Email id ====>"+to[0]);
									logger.info("Mobile No ====>"+smsTo);

									result = sendEmail(to,user_id,parameters, NotificationAlias.EMAIL_BO_MULTIPLE_BANK,subject);
									if(result) {
										logger.info("------Email send to "+to[0]+" and " + request.getUserRole()+" when Branch transfer-----");
									}else{
										logger.error("-----Error in sending email to "+to[0]+" and "+request.getUserRole()+" when Branch transfer------");
									}

									Boolean smsStatus = createNotificationForSMS(smsTo,user_id,parameters,NotificationAlias.SMS_BO_MULTIPLE_BANK);
									logger.info("SMS sending process complete STATUS is :" + smsStatus);
								}
								else if(request.getUserRole().equals(MAKER)) {
									fpName = request.getUserName();
									subject = "Proposal of "+organizationName+" is sanctioned/disbursed and partially disbursed by other bank";
									parameters.put(APPLICATION_ID, applicationCode);
									parameters.put(NAME_OF_ENTITY, organizationName);
									if (fpName != "" && fpName != null) {
										parameters.put(MAKER_NAME, fpName);
									}
									else{
										parameters.put(MAKER_NAME, "Sir/Madam");
									}
									logger.info("Subject ====> "+subject);
									logger.info("parameter fpName Maker=====>"+parameters.get(MAKER_NAME));
									logger.info("Email id ====>"+to[0]);
									logger.info("Mobile No ====>"+smsTo);

									result = sendEmail(to,user_id,parameters, NotificationAlias.EMAIL_MAKER_MULTIPLE_BANK,subject);
									if(result) {
										logger.info("------Email send to "+to[0]+" and " + request.getUserRole()+" when Branch transfer-----");
									}else{
										logger.error("-----Error in sending email to "+to[0]+" and "+request.getUserRole()+" when Branch transfer------");
									}

									Boolean smsStatus = createNotificationForSMS(smsTo,user_id,parameters,NotificationAlias.SMS_MAKER_MULTIPLE_BANK);
									logger.info("SMS sending process complete STATUS is :" + smsStatus);

								}
								else if(request.getUserRole().equals(CHECKER)) {
									fpName = request.getUserName();
									subject = "Proposal of " + organizationName + " is sanctioned/disbursed and partially disbursed by other bank";
									parameters.put(APPLICATION_ID, applicationCode);
									parameters.put(NAME_OF_ENTITY, organizationName);
									if (fpName != "" && fpName != null) {
										parameters.put(CHECKER_NAME, fpName);
									} else {
										parameters.put(CHECKER_NAME, "Sir/Madam");
									}

									logger.info("Subject ====> " + subject);
									logger.info("parameter fpName Checker=====>"+parameters.get(CHECKER_NAME));
									logger.info("Email id ====>"+to[0]);
									logger.info("Mobile No ====>"+smsTo);

									result = sendEmail(to, user_id, parameters, NotificationAlias.EMAIL_CHECKER_MULTIPLE_BANK, subject);
									if (result) {
										logger.info("------Email send to " + to[0] + " and " + request.getUserRole() + " when Branch transfer-----");
									} else {
										logger.error("-----Error in sending email to " + to[0] + " and " + request.getUserRole() + " when Branch transfer------");
									}

									Boolean smsStatus = createNotificationForSMS(smsTo,user_id,parameters,NotificationAlias.SMS_CHECKER_MULTIPLE_BANK);
									logger.info("SMS sending process complete STATUS is :" + smsStatus);

								}

								logger.info(user_id+" ====>"+request.getUserRole() + " ===> " + request.getEmail() + " ====> " + fpName+" ====> "+organizationName+ " ====> "+request.getMobile());


							} catch (IOException e) {
								logger.error("Exception",e);
							}
						} catch (Exception e) {
							logger.error("Exception",e);
						}
					}
				}

			}
		}else{
			if(loanSanctionDomainOld!=null) {
				fpAsyncComponent.sendEmailToMakerHOBOWhenCheckerSanctionLoan(loanSanctionDomainOld);
			}
		}
	}catch (Exception e){
		logger.info("Single proposal found: - "+e);
		if(loanSanctionDomainOld!=null) {
			fpAsyncComponent.sendEmailToMakerHOBOWhenCheckerSanctionLoan(loanSanctionDomainOld);
		}
	}
		return  true;
	}


	private Boolean sendEmail(String[] toNo,String userId,Map<String, Object> parameters,Long templateId,String subject) {
		logger.info("inside email for "+toNo[0]);
		Boolean isSent = false;
		NotificationRequest notificationRequest=new NotificationRequest();
		try {
			notificationRequest.setClientRefId(userId);
			try{
				notificationRequest.setIsDynamic(((Boolean) parameters.get(ISDYNAMIC)).booleanValue());
			}catch (Exception e) {
				notificationRequest.setIsDynamic(false);
			}

			Notification notification = new Notification();
			notification.setContentType(ContentType.TEMPLATE);
			notification.setTemplateId(templateId);
			notification.setSubject(subject);
			notification.setTo(toNo);
			notification.setType(NotificationType.EMAIL);
			notification.setFrom(EMAIL_ADDRESS_FROM);
			notification.setParameters(parameters);
			notification.setIsDynamic(notificationRequest.getIsDynamic());
			notificationRequest.addNotification(notification);

			Boolean status = sendNotification(notificationRequest);
			logger.info(" email status :  "+status);


			isSent=status;
		} catch (NotificationException e) {
			logger.debug("Error in sending mail To "+notificationRequest.getNotifications().get(0).getTo()+" for "+notificationRequest.getAlias());
			logger.debug("Error :"+e.getMessage());
			logger.error("Exception",e);
			isSent = false;
		}
		logger.info("outside email for "+toNo[0]);
		return isSent;
	}

	private Boolean createNotificationForSMS(String toNo,String toUserId,Map<String, Object> parameters,Long templateId) throws NotificationException {
		logger.info("inside sms for "+toNo);
		String to[] = { 91 + toNo };
		Notification notification = new Notification();
		NotificationRequest notificationRequest = new NotificationRequest();
		Boolean isSent = false;
		try {
			notification.setContentType(ContentType.TEMPLATE);
			notification.setTemplateId(templateId);
			notification.setTo(to);
			notification.setType(NotificationType.SMS);
			notification.setParameters(parameters);


			notificationRequest.addNotification(notification);
			notificationRequest.setClientRefId(toUserId);
			Boolean status = sendNotification(notificationRequest);

			isSent = status;
		}
		catch (NotificationException e){
			logger.info("Error in sending SMS To "+notificationRequest.getNotifications().get(0).getTo()+" for "+notificationRequest.getAlias());
			logger.info("Error :"+e.getMessage());
			logger.error("Exception",e);
			isSent = false;
		}
		logger.info("outside sms for "+toNo);
		return isSent;
	}

	private Boolean sendNotification(NotificationRequest notificationRequest) throws NotificationException {
		Boolean status=false;
		for (Notification noti : notificationRequest.getNotifications()) {
			if (noti.getTemplateId() != null && noti.getTemplateId() != 0) {


				NotificationResponse send = notiClient.send(notificationRequest);
				System.out.println("data = "+send.getMessage()+ "status :"+send.getStatus() );
				if(send.getStatus()==200L) {
					status = true;
				}else {
					status = false;
				}
			}
		}
		return status;
	}


	@Override
	public String sanctionRequestValidation( Long applicationId,Long orgId) throws Exception {
		logger.info("Enter in requestValidation() ----------------------->  applicationId==> "+ applicationId);
	        try {        	
		 
	        	Long recCount = proposalDetailsRepository.getApplicationIdCountByOrgId(applicationId ,orgId);
	        	if(recCount != null && recCount  > 0) {
	        		return  SUCCESS_LITERAL;
	        	}else {
	        		return "Invalid ApplicationId ";
	        	}		 
	        }catch (Exception e) {
	        	logger.error("Error/Exception in requestValidation() ----------------------->  Message : ", e);
	        	throw e;
			}
	}

	@Override
	public Boolean saveSanctionDetailFromPopup(LoanSanctionRequest loanSanctionRequest) throws Exception {

		logger.info("Enter in saveSanctionDetailFromPopup() ----------------------------- sanctionRequest Data : "+ loanSanctionRequest.toString());
		try {


			logger.info("going to fetch username/password");
			UserOrganisationRequest userOrganisationRequest = userClient.getByOrgId(loanSanctionRequest.getOrgId());
			if(CommonUtils.isObjectListNull( userOrganisationRequest, userOrganisationRequest.getUsername(),  userOrganisationRequest.getPassword() )){
				logger.warn("username/password found null ");
				return false;
			}

			loanSanctionRequest.setUserName(userOrganisationRequest.getUsername());
			loanSanctionRequest.setPassword(userOrganisationRequest.getPassword());
			loanSanctionRequest.setSanctionDate(new Date());

			return saveLoanSanctionDetail(loanSanctionRequest);

		}catch (Exception e) {
			logger.error("Error/Exception in saveSanctionDetailFromPopup() ----------------------->  Message : ",e);
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean saveSanctionAndDisbursementDetailsFromBank() throws Exception {

		logger.info("================= Enter in saveSanctionAndDisbursementDetailsFromBank() ============================== ");
		try {
			String failureReason = null ; 
			Boolean isReverseAPI = false ; 
			//Getting userOrgDetail List
			UserResponse userResponse = userClient.getOrgList();
			if(!CommonUtils.isObjectNullOrEmpty(userResponse) && !CommonUtils.isObjectNullOrEmpty(userResponse.getData())) {
				List<Map<String ,  Object>> userOrganisationRequestList = (List<Map<String, Object>>) userResponse.getData();
				if(!CommonUtils.isListNullOrEmpty(userOrganisationRequestList)){
					UserOrganisationRequest	userOrganisationRequest =null;
					for(Map<String , Object> map : userOrganisationRequestList) {
						userOrganisationRequest = (UserOrganisationRequest)	MultipleJSONObjectHelper.getObjectFromMap(map, UserOrganisationRequest.class);
						if(CommonUtils.isObjectNullOrEmpty(userOrganisationRequest.getIsReverseApiActivated()) || !userOrganisationRequest.getIsReverseApiActivated().booleanValue()) {
							logger.info("Organization ID is Not Activated==========>{}=====Name============>{}",userOrganisationRequest.getUserOrgId(),userOrganisationRequest.getOrganisationName());
							continue;
						}
						
						if(!CommonUtils.isObjectNullOrEmpty(isProduction)){
							if(CommonUtils.isObjectNullOrEmpty(userOrganisationRequest.getProductionUrl())) {
								logger.info("Production URL is NULL for Bank==========>{}",userOrganisationRequest.getOrganisationName());
								continue;
							}
							sidbiIntegrationClient.setIntegrationBaseUrl(userOrganisationRequest.getProductionUrl());
						}else {
							if(CommonUtils.isObjectNullOrEmpty(userOrganisationRequest.getUatUrl())) {
								logger.info("UAT URL is NULL for Bank==========>{}",userOrganisationRequest.getOrganisationName());
								continue;
							}
							sidbiIntegrationClient.setIntegrationBaseUrl(userOrganisationRequest.getUatUrl());//
						}
						logger.warn("---------------------- Getting token from SidbiIntegrationClient ----------------" );
						GenerateTokenRequest generateTokenRequest = new GenerateTokenRequest();
						generateTokenRequest.setApplicationId(1l);
						generateTokenRequest.setPassword(userOrganisationRequest.getPassword());
						
						if(userOrganisationRequest.getUserOrgId() == 17l || userOrganisationRequest.getUserOrgId() == 16l) {
							String reqTok = "bobc:bob12345";
							String requestDataEnc = Base64.getEncoder().encodeToString(reqTok.getBytes());
							generateTokenRequest.setBankToken(requestDataEnc);
						}
						String token=null;
						
						try {
							
							// for syndicate Bank only check orgid
							OrganisationConfiguration organisationConfiguration = MultipleJSONObjectHelper.getObjectFromString(userOrganisationRequest.getConfig(), OrganisationConfiguration.class);
							if(!CommonUtils.isObjectNullOrEmpty(organisationConfiguration) && organisationConfiguration.getIsSSL()){
								System.setProperty("javax.net.ssl.keyStore",  organisationConfiguration.getKeyStore());                                    
								System.setProperty("javax.net.ssl.keyStorePassword", organisationConfiguration.getKeyStorePassword());              
								System.setProperty("javax.net.ssl.keyStoreType",  organisationConfiguration.getKeyStoreType());            
							}
							token = sidbiIntegrationClient.getToken(generateTokenRequest,generateTokenRequest.getBankToken() , userOrganisationRequest.getCodeLanguage());
							generateTokenRequest.setToken(token);
							//Getting sanction and disbursement Details from Bank 
							String encryptedString = sidbiIntegrationClient.getSanctionAndDisbursmentDetailList(token, generateTokenRequest.getBankToken() , userOrganisationRequest.getCodeLanguage());
							String resJosn = null ;
							if(!CommonUtils.isObjectNullOrEmpty(encryptedString)) {
								 resJosn = sanctionAndDisbursementValidation(encryptedString , userOrganisationRequest.getCodeLanguage() , userOrganisationRequest.getUserOrgId());
								try {
									
									sidbiIntegrationClient.setTokenAsExpired(generateTokenRequest, generateTokenRequest.getBankToken(), userOrganisationRequest.getCodeLanguage());

								} catch (Exception e) {
									logger.error("---------- Error/Exception while expiring token ------------ " +e.getMessage());
								}
								if(resJosn != null || ! ERROR_LITERAL.equalsIgnoreCase(resJosn) ){
									//List<LoanSanctionAndDisbursedRequest> list = (List<LoanSanctionAndDisbursedRequest> )res;
									List<com.capitaworld.sidbi.integration.model.sanction.LoanSanctionAndDisbursedRequest> list1   = null ;
									try {
										/*if(!CommonUtils.isObjectListNull(list)&& !CommonUtils.isObjectNullOrEmpty(list.get(0))) {*/
										try {
										list1 = MultipleJSONObjectHelper.getListOfObjects(resJosn, null, com.capitaworld.sidbi.integration.model.sanction.LoanSanctionAndDisbursedRequest.class);
										}catch (Exception e) {
											failureReason = "Error/Exception while converting resJos to List response MSG ==> " +e.getMessage() ;
										}
										if(! CommonUtils.isListNullOrEmpty(list1) && list1 != null) {
											generateTokenRequest.setApplicationId(list1.get(0).getApplicationId());
											token = sidbiIntegrationClient.getToken(generateTokenRequest,generateTokenRequest.getBankToken() , userOrganisationRequest.getCodeLanguage());
											logger.info("********************************* " + list1.size() +" ***********************************");
										}else {
											failureReason ="response list is null " +list1;  
										}
										/*String json = MultipleJSONObjectHelper.getStringfromObject(list);
										list1 = MultipleJSONObjectHelper.getListOfObjects(resJosn, null, com.capitaworld.sidbi.integration.model.sanction.LoanSanctionAndDisbursedRequest.class); 
										}*/
									}catch (Exception e) {
										failureReason = "Error/Exception while getting appication from getSanctionAndDisbursmentDetailList ------------ orgId "+userOrganisationRequest.getUserOrgId()+" MSG =>" + e.getMessage();
										//logger.info("------------------ Error/Exception while getting appication from getSanctionAndDisbursmentDetailList ------------ MSG =>" + e.getMessage());	
									}
									if(sidbiIntegrationClient.updateSavedSanctionAndDisbursmentDetailList(list1 , token, generateTokenRequest.getBankToken() , userOrganisationRequest.getCodeLanguage())) {
										try {
											//wait for 20 Second
											isReverseAPI = true ;
											logger.info("*******Sucessgfully updated sanction and disbursement details in sidbi integration********** ");
											TimeUnit.SECONDS.sleep(20);
											logger.info("*******Going to Call another Bank Reverse API.********** ");
										}catch (Exception e) {
											//logger.error("Error/Exception in for 20 second wait() ----------------------->  Message ",e);
											failureReason = "Error/Exception while getting appication from getSanctionAndDisbursmentDetailList ------------ orgId "+userOrganisationRequest.getUserOrgId()+" MSG =>" + e.getMessage();
										}
									}
									//auditComponentBankToCW.saveBankToCWReqRes(resJosn , null , null , null , failureReason, userOrganisationRequest.getUserOrgId(), null);
								}else {
									//logger.info("*******Unable to store sanction or disbursement detail   **********  reasion is =={}", (res != null ? res.toString() : res));
									 failureReason = "---------------Unable to update  sanction or disbursement detail to bank Side Response ----------------" + resJosn ;
									//auditComponentBankToCW.saveBankToCWReqRes(null , null, null, null, failureReason, userOrganisationRequest.getUserOrgId(), null);
								}
								
							}else {
								//logger.info("*******Null in getting sanction or disbursement detail  from  bank side  **********  reasion is ==> ", encryptedString +" org id ==> " + userOrganisationRequest.getUserOrgId() );
								 failureReason ="----------------Null in getting sanction or disbursement detail  from  bank side  ------------------ org id ==> " + userOrganisationRequest.getUserOrgId();
								 //auditComponentBankToCW.saveBankToCWReqRes(encryptedString , null, null, null, failureReason, userOrganisationRequest.getUserOrgId(), null);
							}
							
							auditComponentBankToCW.saveBankToCWReqRes(resJosn == null ? encryptedString : resJosn  , null , null , null , failureReason, userOrganisationRequest.getUserOrgId(), null);
							
						}catch(Exception e) {
							logger.error("Error while Calling get token API and getting sanction & disbursemet detail MSG ==> ",e);
						}
					}
				}
				
			}
			logger.info("going to fetch username/password");
			return isReverseAPI;
		}catch (Exception e) {
			logger.error("Error/Exception in saveSanctionAndDisbursementDetailsFromBank() ----------------------->  Message : ", e);
			return false;
		}

	}
	
	@SuppressWarnings({ "unchecked"})
	public String sanctionAndDisbursementValidation(String encryptedString , Integer codeLonguage , Long mainOrgId) {
		
		LoansResponse loansResponse = null;
		Long orgId = null;
		String decrypt = null;
		String reason =null ; 
		List<LoanSanctionAndDisbursedRequest> loanSanctionAndDisbursedRequestList = null;
		/*Long applicationId =null;
		Boolean isSanctionSuccess = false; 
		Integer apiType = null;  */
		try {
			logger.info("=============================Entry saveLoanSanctionDisbursementDetailFromBank(){} ============================= ");
			
			if (encryptedString != null) {
				// Decryption of request
				try {
					if(codeLonguage == com.capitaworld.sidbi.integration.util.CommonUtils.CodeLangType.JAVA) {
						decrypt = AESEncryptionUtilitySBI.decrypt(encryptedString);
					}else if(codeLonguage == com.capitaworld.sidbi.integration.util.CommonUtils.CodeLangType.DOT_NET ) {
						decrypt = AESEncryptionUtility.decrypt(encryptedString);
					}
					loanSanctionAndDisbursedRequestList = MultipleJSONObjectHelper.getListOfObjects(decrypt,  null ,LoanSanctionAndDisbursedRequest.class);
					
				} catch (Exception e) {
					logger.error("Error while Converting Encrypted Object to LoanSanctionAndDisbursedRequest  saveLoanSanctionDisbursementDetailFromBank(){} -------------------------> ",e);
					loansResponse = new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value(),HttpStatus.OK);
					loansResponse.setData(false);
					if (CommonUtils.isObjectNullOrEmpty(decrypt)) {
						reason = "ERROR WHILE DECRYPT ENCRYPTED OBJECT   ====> Msg ===> ";
					} else {
						reason = "error while converting decrypt string to profileReqRes ====> Msg ===> ";
					}
					return reason+e.getMessage();
				}
				if(!CommonUtils.isListNullOrEmpty(loanSanctionAndDisbursedRequestList) ) {
					reason =  saveSanctionDetailWithDisbursementDetail(loanSanctionAndDisbursedRequestList);
				}
				
				try {
					return MultipleJSONObjectHelper.getStringfromObject(loanSanctionAndDisbursedRequestList) ;
				} catch (Exception e) {
					reason = "Error/Exception while converting object to string "+e.getMessage() ;
					return ERROR_LITERAL ;
				}
				
			} else {
				logger.info("Null in encryptedString while saveLoanSanctionDisbursementDetailFromBank() ----------------> encryptedString "+ encryptedString);
				loansResponse = new LoansResponse("Mandatory Fields Must Not be Null", HttpStatus.BAD_REQUEST.value(),HttpStatus.OK);
				loansResponse.setData(false);
				reason = "Null in encryptedString while saveLoanSanctionDisbursementDetailFromBank() encryptedString ====>"+ encryptedString;
				return null ;  
			}
			
		} catch (Exception e) {
			logger.error("Error while saveLoanSanctionDisbursementDetailFromBank()----------------------> ", e);
			/*loansResponse = new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK);
			loansResponse.setData(false);*/
			reason = "Error/Exception while save LoanSanctionAndDisbursedRequest in  saveLoanSanctionDisbursementDetailFromBank() ===> Msg "+ e.getMessage();
			return  ERROR_LITERAL;
		} finally {
			logger.info("Saving Request to DB ===> ");
			auditComponentBankToCW.saveBankToCWReqRes(decrypt != null ? decrypt : encryptedString , null ,CommonUtility.ApiType.REVERSE_SANCTION_AND_DISBURSEMENT, null , " ** Whole Request with reason ** => "+reason +" getting by username and password orgId => "+orgId , mainOrgId , null);
		}
	}

	@Override
	public Boolean saveLoanSanctionDetailById(Long orgId ,LoanSanctionRequest loanSanctionRequest) throws Exception {
		try {
		logger.info("========================Enter in saveLoanSanctionDetail() ====================  applicationId ==> "+ loanSanctionRequest.getApplicationId());
		                                                                       
		LoanSanctionDomain loanSanctionDomainOld =loanSanctionRepository.findByBankSanctionPrimaryKeyAndIsActiveAndApplicationId(loanSanctionRequest.getId(), true,loanSanctionRequest.getApplicationId());
		if(CommonUtils.isObjectNullOrEmpty(loanSanctionDomainOld) ) {
			loanSanctionDomainOld = new LoanSanctionDomain();
			loanSanctionDomainOld.setCreatedBy(loanSanctionRequest.getActionBy());
			loanSanctionDomainOld.setCreatedDate(new Date());
			loanSanctionDomainOld.setIsActive(true);
			BeanUtils.copyProperties(loanSanctionRequest, loanSanctionDomainOld, "id" , "createdBy" , "createdDate" , "isActive" , "modifiedBy" , "modifiedDate");
			loanSanctionDomainOld.setOrgId(orgId);
			loanSanctionDomainOld.setBankSanctionPrimaryKey(loanSanctionRequest.getId());
			loanSanctionDomainOld.setIsSanctionedFrom(CommonUtils.sanctionedFrom.FROM_API);
			logger.info("=============Exit saveLoanSanctionDetail() ================= ");
			return loanSanctionRepository.save(loanSanctionDomainOld) != null;
		}
		
		logger.info("--------------------already saved---------------- applicationId ==>" + loanSanctionRequest.getApplicationId()); 
		/*else{
			loanSanctionDomainOld.setModifiedBy(loanSanctionRequest.getActionBy());
			loanSanctionDomainOld.setModifiedDate(new Date());
		}
		BeanUtils.copyProperties(loanSanctionRequest, loanSanctionDomainOld, "id" , "createdBy" , "createdDate" , "isActive" , "modifiedBy" , "modifiedDate");
		loanSanctionDomainOld.setBankSanctionPrimaryKey(loanSanctionRequest.getId());
		logger.info("Exit saveLoanSanctionDetail() -----------------------> LoanSanctionDomain "+ loanSanctionDomainOld);
		return loanSanctionRepository.save(loanSanctionDomainOld) != null;*/
		return true;
		}catch (Exception e) {
			logger.error("Error/Exception in saveLoanSanctionDetail() -----------------------> Message : ",e);
			throw e;
		}
	}

	@Override
	public String getToken(String url , GenerateTokenRequest generateTokenRequest , Integer langCode  ) throws Exception {
		sidbiIntegrationClient.setIntegrationBaseUrl(url);
		String reqTok = "bobc:bob12345";
		String requestDataEnc = Base64.getEncoder().encodeToString(reqTok.getBytes());
		generateTokenRequest.setBankToken(requestDataEnc);
		
		return sidbiIntegrationClient.getToken(generateTokenRequest, generateTokenRequest.getBankToken(), langCode); 
	}
	
	private String saveSanctionDetailWithDisbursementDetail(List<LoanSanctionAndDisbursedRequest> loanSanctionAndDisbursedRequestList) {
		int rowUpdated = 0 ;                                             
		String jsonString = null ;
		Long applicationId = null; 
		Long orgId = null ;
		Boolean isSanctionSuccess = false;
		String sanctionReason = null ; 
		String disbursementReason  = null ;
		String status = null ;
		//checking validation 
		for(LoanSanctionAndDisbursedRequest loanSanctionAndDisbursedRequest : loanSanctionAndDisbursedRequestList) {
			BankCWAuditTrailDomain bankCWAuditTrailDomain = null;
			applicationId = loanSanctionAndDisbursedRequest.getApplicationId();
			//checking validation for right organization
			orgId = auditComponentBankToCW.getOrgIdByCredential(loanSanctionAndDisbursedRequest.getUserName(),loanSanctionAndDisbursedRequest.getPassword());
			if (!CommonUtils.isObjectNullOrEmpty(orgId)) {
				if(loanSanctionAndDisbursedRequest !=null && loanSanctionAndDisbursedRequest.getLoanSanctionRequest() !=null) {
					if (!CommonUtils.isObjectListNull(loanSanctionAndDisbursedRequest.getApplicationId(),
							loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getApplicationId(),
							loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getBranch(),
							loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getRoi(),
							loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getSanctionAmount(),
							loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getSanctionDate(),
							loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getTenure(),
							loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getUserName(),
							loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getPassword() )) { 
							applicationId = loanSanctionAndDisbursedRequest.getApplicationId();
							//saving sanction with validation
							try {
								sanctionReason = sanctionRequestValidation(loanSanctionAndDisbursedRequest.getApplicationId(), orgId);
								  
							} catch (Exception e) {
								sanctionReason = "Error/Exception wheile checking the validation MSG ==> "+e.getMessage();
								logger.error("Error/Exception wheile checking the validation MSG ==> ",e);
							}
							
							if(SUCCESS_LITERAL.equalsIgnoreCase(sanctionReason) ) {
								bankCWAuditTrailDomain = bankToCWAuditTrailRepository.findFirstByApplicationIdAndOrgIdAndApiTypeAndBankPrimaryKeyAndIsActiveOrderByIdDesc(loanSanctionAndDisbursedRequest.getApplicationId(), orgId, CommonUtility.ApiType.REVERSE_SANCTION , loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getId() , true);
								if(CommonUtils.isObjectNullOrEmpty(bankCWAuditTrailDomain)) {
									try {
										isSanctionSuccess = saveLoanSanctionDetailById(orgId ,  loanSanctionAndDisbursedRequest.getLoanSanctionRequest());
										if(isSanctionSuccess ) {
											status = sanctionReason ;
											//cw-response
											/*loansResponse = setLoanResponse("Successfully Sanction Detail Stored ", true, HttpStatus.OK.value()) ;*/
											loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.SUCCESS);
										}
									} catch (Exception e) {
										sanctionReason = "Error/Exception while saving the sanction Detail MSG ==> "+e.getMessage();
										logger.error("Error/Exception while saving the sanction Detail MSG ==> ",e);
									}
									
								try {
										rowUpdated = proposalDetailsRepository.updateSanctionStatus(13l, loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getApplicationId());
										status +=" proposal detail rowUpdated ==> "+rowUpdated ;
									}catch (Exception e) {
										auditComponentBankToCW.saveBankToCWReqRes(jsonString, loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getApplicationId() , CommonUtility.ApiType.PROPOSAL_UPDATE_STATUS_FAILED_REVERSE_SANCTION  , null, "Exception while updating the proposal status as partial disbursement = > MSG ==> "+e.getMessage() , orgId, null);
									}
										
									logger.info("------------------------- saving sanction detail of reverse api--------------- isSuccess ==> " + isSanctionSuccess +" ----------- updating the proposal detail table row " + rowUpdated);
								}else {
									status += " Already done sanction again appicationId ==> "+applicationId ;
									loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.ALREADY_DONE_SANCTION);
									logger.info("------------------------- already save sanction detail of reverse api---------------");
								}
								loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setIsSaved(isSanctionSuccess);
								loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setReason(sanctionReason);
							
								//	saving disbursement with validation
								if(! CommonUtils.isListNullOrEmpty(loanSanctionAndDisbursedRequest.getLoanDisbursementRequestsList())) {
									//already store reason in bank audit table 
									disbursementReason = saveDisbursementDetail(orgId, loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getId() ,  loanSanctionAndDisbursedRequest);
								}
							}else {
								//ALREADY SANCTION REASON IS FROM sanctionRequestValidation SO NOT SET AGAIN  
								loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setIsSaved(false);
								loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.INVALID_APPLICATION_ID);
								loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setReason(sanctionReason);
								
								logger.info("------------------------- unable to save reverse sanction details reason ==> "+ sanctionReason);
							}
					}else {
						logger.info("SOME of Values from  LoanSanctionAndDisbursedRequest while saveLoanSanctionDisbursementDetailFromBank() ");
						/*loansResponse = setLoanResponse("Mandatory Fields Must Not be Null" , false  , HttpStatus.BAD_REQUEST.value()) ;*/
						sanctionReason = "Mandatory Fields Must Not be Null while saveLoanSanctionDisbursementDetailFromBank() ";
						loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.MANDAROTY_FIELD_MUST_NOT_BE_NULL);
						loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setReason("Mandatory Fields Must Not be Null while save loan sanction details");
						loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setIsSaved(false);
					}
				}
				
			} else {
				sanctionReason = "Invalid Credentials";
				logger.info("Invalid Credentials while saveLoanSanctionDisbursementDetailFromBank() ----------------> orgId "+ orgId + " reason  " + sanctionReason);
				/*loansResponse = setLoanResponse(sanctionReason , false  , HttpStatus.UNAUTHORIZED.value()) ;*/
				loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setIsSaved(false);
				loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setStatusCode(CommonUtility.SanctionDisbursementAPIStatusCode.INVALID_CREDENTIAL);
				loanSanctionAndDisbursedRequest.getLoanSanctionRequest().setReason(sanctionReason);
				logger.info("================== Exit saveLoanDisbursementDetail() =================");
			}
			//saving req in bank to  cw-audit table
			try {
				jsonString = MultipleJSONObjectHelper.getStringfromObject(loanSanctionAndDisbursedRequest.getLoanSanctionRequest());
				auditComponentBankToCW.saveBankToCWReqRes(jsonString , 	applicationId,CommonUtility.ApiType.REVERSE_SANCTION, status ,  "sanctionReason==>" + sanctionReason + "disbursementReason ==> "+disbursementReason, orgId  , loanSanctionAndDisbursedRequest.getLoanSanctionRequest().getId());
			}catch (Exception e) {
				auditComponentBankToCW.saveBankToCWReqRes(jsonString, applicationId , null , status, "Exception while converting the object in String = > MSG "+e.getMessage() , orgId, null);
			}
			if (loanSanctionAndDisbursedRequest.getLoanDisbursementRequestsList() !=null ) {
				// without sanctionPrimaryId 
				disbursementReason =  saveDisbursementDetail(orgId , null ,  loanSanctionAndDisbursedRequest);
			}
		}	
		
		if( SUCCESS_LITERAL.equalsIgnoreCase(sanctionReason) && SUCCESS_LITERAL.equalsIgnoreCase(disbursementReason)){
			
			/*loansResponse = setLoanResponse("Both Sanction and disbursement Information Successfully Stored" , true  , HttpStatus.OK.value()) ;*/
			logger.info("------------------- reverse sanction and disbursement  is saved--------------");

		}else if( SUCCESS_LITERAL.equalsIgnoreCase(sanctionReason) ){
			/*loansResponse = setLoanResponse("Only Sanction Information Successfully Stored ", true  , HttpStatus.OK.value()) ;*/
			logger.info("-------------------only  reverse sanction is saved--------------");

		}else if(SUCCESS_LITERAL.equalsIgnoreCase(disbursementReason) || "First Disbursement".equalsIgnoreCase(disbursementReason)){
			/*loansResponse = setLoanResponse("Only Disbursement Information Successfully Stored ", true , HttpStatus.OK.value()) ;*/
			logger.info("-------------------only  reverse disbursement is saved--------------");

		} else {
			logger.info("Failure msg while save LoanSanctionAndDisbursedRequest in saveLoanSanctionDisbursementDetailFromBank() to CW ----------------> msg "+ sanctionReason);
			/*loansResponse = setLoanResponse(sanctionReason.split("[\\{}]")[0], false , HttpStatus.BAD_REQUEST.value()) ;*/
			
		}
		logger.info("Exit saveLoanDisbursementDetail() ----------------> Sanction Reason => "+ sanctionReason +"  Disbursement reason =>" +  disbursementReason);
		return sanctionReason+" "+disbursementReason ;
	}
	
	
	private String saveDisbursementDetail( Long orgId , Long sanctionPrimaryId , LoanSanctionAndDisbursedRequest loanSanctionAndDisbursedRequest) {
		String jsonString = null ;
		Integer apiType = null;
		String disbursementReason  = null ; 
		//saving disbursement with validation
		if(! CommonUtils.isListNullOrEmpty(loanSanctionAndDisbursedRequest.getLoanDisbursementRequestsList())) {
			try {
				
				jsonString = MultipleJSONObjectHelper.getStringfromObject(loanSanctionAndDisbursedRequest.getLoanDisbursementRequestsList());
			} catch (Exception e) {
				apiType = CommonUtility.ApiType.REVERSE_DISBURSEMENT ;
				auditComponentBankToCW.saveBankToCWReqRes(jsonString, loanSanctionAndDisbursedRequest.getApplicationId() , apiType  , null , "Error/Exception while converting the object in String = > MSG "+e.getMessage() , orgId, null);
				logger.error("--------------Error/Eception while converting object to String  ------------ MSG => "+ e.getMessage());
			}
			try {
				loanSanctionAndDisbursedRequest.setLoanDisbursementRequestsList( loanDisbursementService.bankRequestValidationAndSave(sanctionPrimaryId  , loanSanctionAndDisbursedRequest.getLoanDisbursementRequestsList() , orgId , CommonUtility.ApiType.REVERSE_DISBURSEMENT)) ;
			} catch (IOException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			if(!CommonUtils.isListNullOrEmpty(loanSanctionAndDisbursedRequest.getLoanDisbursementRequestsList())){
				disbursementReason = SUCCESS_LITERAL;
				apiType = CommonUtility.ApiType.REVERSE_DISBURSEMENT ;
				 
				auditComponentBankToCW.saveBankToCWReqRes(jsonString, loanSanctionAndDisbursedRequest.getApplicationId() , apiType , disbursementReason , null  , orgId, null);
			}
		}else {
			disbursementReason = "No Disbursement has done for aplicaiotnId "+ loanSanctionAndDisbursedRequest.getApplicationId() +" from bank Side and this sanction detail loanSanctionDetail => " + loanSanctionAndDisbursedRequest.getLoanSanctionRequest().toString() ;
			  
			auditComponentBankToCW.saveBankToCWReqRes(jsonString, loanSanctionAndDisbursedRequest.getApplicationId() , CommonUtility.ApiType.REVERSE_DISBURSEMENT , disbursementReason , null  , orgId, null);
		}
		return disbursementReason;
	}
	
}
