package com.capitaworld.service.loans.service.sanctionimpl;

import java.util.Date;
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

import com.capitaworld.service.loans.config.FPAsyncComponent;
import com.capitaworld.service.loans.domain.sanction.LoanSanctionDomain;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.LoanSanctionRequest;
import com.capitaworld.service.loans.repository.OfflineProcessedAppRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.sanction.LoanSanctionRepository;
import com.capitaworld.service.loans.service.sanction.LoanSanctionService;
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
	private FPAsyncComponent fpAsyncComponent;

	@Autowired
	private OfflineProcessedAppRepository offlineProcessedAppRepository;

	@Autowired
	private NotificationClient notiClient;

	@Autowired
	private LoanApplicationService loanApplicationService;

	@Autowired
	private ApplicationProposalMappingRepository applicationProposalMappingRepository;

	@Override
	public Boolean saveLoanSanctionDetail(LoanSanctionRequest loanSanctionRequest) throws LoansException {
		try {
		logger.info("Enter in saveLoanSanctionDetail() ----------------------->  LoanSanctionRequest==> "+ loanSanctionRequest);
		
		LoanSanctionDomain loanSanctionDomainOld =loanSanctionRepository.findByAppliationId(loanSanctionRequest.getApplicationId());
		if(CommonUtils.isObjectNullOrEmpty(loanSanctionDomainOld) ) {
			loanSanctionDomainOld = new LoanSanctionDomain();
			BeanUtils.copyProperties(loanSanctionRequest, loanSanctionDomainOld,"id");
			loanSanctionDomainOld.setOrgId(!CommonUtils.isObjectNullOrEmpty(loanSanctionRequest.getOrgId()) ? loanSanctionRequest.getOrgId() : null);
			if(loanSanctionRequest.getIsIneligibleProposal() != null && loanSanctionRequest.getIsIneligibleProposal() == true) {
				loanSanctionDomainOld.setIsSanctionedFrom(loanSanctionRequest.getIsSanctionedFrom());
				/*IneligibleProposalDetails ineligibleProposalDetails = (IneligibleProposalDetails) offlineProcessedAppRepository.findByAppliationId(loanSanctionRequest.getApplicationId());
				ineligibleProposalDetails.setIsSanctioned(true);*/
				//update sanctioned is true flag in ineligible proposal table
				Long userId = null;
				if(!CommonUtils.isObjectNullOrEmpty(loanSanctionRequest.getActionBy())) {
					userId = Long.valueOf(loanSanctionRequest.getActionBy());
				}
				offlineProcessedAppRepository.updateSanctionedFlag(loanSanctionRequest.getApplicationId(), loanSanctionRequest.getOrgId(), loanSanctionRequest.getBranch(), userId);
			} else if(CommonUtils.isObjectNullOrEmpty(loanSanctionRequest.getIsIneligibleProposal()) || loanSanctionRequest.getIsIneligibleProposal() == false) {
				loanSanctionDomainOld.setIsSanctionedFrom(CommonUtils.sanctionedFrom.ELIGIBLE_USERS);
			}
			loanSanctionDomainOld.setCreatedBy(loanSanctionRequest.getActionBy());
			loanSanctionDomainOld.setCreatedDate(new Date());
			loanSanctionDomainOld.setIsActive(true);
		}else{
			//BeanUtils.copyProperties(loanSanctionRequest, loanSanctionDomainOld,"id");
			if(loanSanctionRequest.getIsIneligibleProposal() != null && loanSanctionRequest.getIsIneligibleProposal()) {
				loanSanctionDomainOld.setIsSanctionedFrom(loanSanctionRequest.getIsSanctionedFrom());
				/*IneligibleProposalDetails ineligibleProposalDetails = (IneligibleProposalDetails) offlineProcessedAppRepository.findByAppliationId(loanSanctionRequest.getApplicationId());
				ineligibleProposalDetails.setIsSanctioned(true);*/
				//update sanctioned is true flag in ineligible proposal table
				Long userId = null;
				if(!CommonUtils.isObjectNullOrEmpty(loanSanctionRequest.getActionBy())) {
					userId = Long.valueOf(loanSanctionRequest.getActionBy());
				}
				offlineProcessedAppRepository.updateSanctionedFlag(loanSanctionRequest.getApplicationId(), loanSanctionRequest.getOrgId(), loanSanctionRequest.getBranch(), userId);
			} else {
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
			/*loanSanctionDomainOld.setIsSanctionedFrom(1l);*/
		}
		//==================Sending Mail notification to Maker=============================
		try{
//			fpAsyncComponent.sendEmailToFSWhenCheckerSanctionLoan(loanSanctionDomainOld);
//			fpAsyncComponent.sendEmailToMakerHOBOWhenCheckerSanctionLoan(loanSanctionDomainOld);
			
			Boolean sanctionMailStatus = sendMailToHOBOCheckerMakerForMultipleBanks(loanSanctionDomainOld.getApplicationId(),loanSanctionDomainOld);
			if(sanctionMailStatus) {
				logger.info("Sanction email has been sent"); 
			}
		}catch(Exception e){
			logger.error("Exception : {}",e);
		}
		//=================================================================================
		return loanSanctionRepository.save(loanSanctionDomainOld) != null;
		}catch (Exception e) {
			logger.error("Error/Exception in saveLoanSanctionDetail() -----------------------> Message : ",e);
			throw new LoansException(e);
		}

	}


	public Boolean sendMailToHOBOCheckerMakerForMultipleBanks(Long applicationId,LoanSanctionDomain loanSanctionDomainOld ){
		logger.info("inside notification start for sanction");

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
		logger.info("outside notification end for sanction");
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
	public String sanctionRequestValidation( Long applicationId,Long orgId) throws LoansException {
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
	        	throw new LoansException(e);
			}
	}

	@Override
	public Integer saveSanctionDetailFromPopup(LoanSanctionRequest loanSanctionRequest) throws LoansException {
		logger.info("Enter in saveSanctionDetailFromPopup() ----------------------------- sanctionRequest Data : "+ loanSanctionRequest.toString());
		try {

			if(loanSanctionRequest.getIsSanctionedFrom() == 2){
				//FIRST CHECK IF CURRENT PROPOSAL IS ELIGIBL FOR SANCTIONED OR NOT
				Integer status = offlineProcessedAppRepository.checkBeforeOfflineSanctioned(loanSanctionRequest.getApplicationId());
				if(status == 4) {//OFFLINE
					loanSanctionRequest.setSanctionDate(new Date());
					Boolean result = saveLoanSanctionDetail(loanSanctionRequest);
					return !result ? 0 : 4;
				} else {
					return status;
				}
			} else {//OFFLINE
				loanSanctionRequest.setSanctionDate(loanSanctionRequest.getSanctionDate() != null ? loanSanctionRequest.getSanctionDate() :new Date());
				Boolean result = saveLoanSanctionDetail(loanSanctionRequest);
				return !result ? 0 : 4;
			}
			/*loanSanctionRequest.setSanctionDate(new Date());
			return saveLoanSanctionDetail(loanSanctionRequest);*/
			/*logger.info("going to fetch username/password");
			UserOrganisationRequest userOrganisationRequest = userClient.getByOrgId(loanSanctionRequest.getOrgId());
			if(CommonUtils.isObjectListNull( userOrganisationRequest, userOrganisationRequest.getUsername(),  userOrganisationRequest.getPassword() )){
				logger.warn("username/password found null ");
				return false;
			}
			loanSanctionRequest.setUserName(userOrganisationRequest.getUsername());
			loanSanctionRequest.setPassword(userOrganisationRequest.getPassword());*/
		} catch (Exception e) {
			logger.error("Error/Exception in saveSanctionDetailFromPopup() ----------------------->  Message : ",e);
			return 0;
		}
	}

}
