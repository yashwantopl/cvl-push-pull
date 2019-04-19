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

	private static final String SMS_SENDING_PROCESS_COMPLETE_STATUS_IS = "SMS sending process complete STATUS is :{}";
	private static final String ERROR_IN_SENDING_EMAIL_TO_AND_WHEN_BRANCH_TRANSFER = "-----Error in sending email to {}  and when Branch transfer------{}";
	private static final String EMAIL_SEND_TO_AND_WHEN_BRANCH_TRANSFER = "------Email send to {}  and  when Branch transfer-----{}";
	private static final String CHECKER_LITERAL = "Checker";
	private static final String LOGGER_PARAMETER_FP_NAME_CHEKER = "parameter fpName Cheker=====>{}";
	private static final String PARAM_SIR_MADAM = "Sir/Madam";
	private static final String LOGGER_PARAMETER_FP_NAME_HO = "parameter fpName HO=====>{}";
	private static final String LOGGER_MOBILE_NO = "Mobile No ====>{}";
	private static final String LOGGER_EMAIL_ID = "Email id ====>{}";
	private static final Logger logger = LoggerFactory.getLogger(LoanSanctionServiceImpl.class);
	private static final String LOGGER_SUBJECT = "Subject ====>{}";

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
	private static final String CHECKER=CHECKER_LITERAL;
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
			fpAsyncComponent.sendEmailToFSWhenCheckerSanctionLoan(loanSanctionDomainOld);
		}catch(Exception e){
			logger.error("Exception : {}",e);
		}
		Integer count = proposalDetailsRepository.getCountOfProposalDetailsByApplicationId(loanSanctionDomainOld.getApplicationId());
		if(count > 1) {
			try {
				sendMailToHOBOCheckerMakerForMultipleBanks(loanSanctionDomainOld.getApplicationId());
			}catch (IndexOutOfBoundsException e) {
				logger.info("Application not from multiple bank applicationid:{}",loanSanctionDomainOld.getApplicationId());
			}
		}else {
			fpAsyncComponent.sendEmailToMakerHOBOWhenCheckerSanctionLoan(loanSanctionDomainOld);			
		}
		//=================================================================================
		return loanSanctionRepository.save(loanSanctionDomainOld) != null;
		}catch (Exception e) {
			logger.error("Error/Exception in saveLoanSanctionDetail() -----------------------> Message : ",e);
			throw new LoansException(e);
		}

	}


	public Boolean sendMailToHOBOCheckerMakerForMultipleBanks(Long applicationId) {
		logger.info("inside notification start for sanction");
		Boolean isSent = false;
		List<Object[]> proposalDetailByApplicationId = proposalDetailsRepository.findProposalDetailByApplicationId(applicationId);
				if(proposalDetailByApplicationId != null && proposalDetailByApplicationId.get(1) != null){
					// check is their any sanction
					Boolean isSanction = false;
					for(Object[] arr : proposalDetailByApplicationId ){
						Integer proposalStatus = CommonUtils.convertInteger(arr[1]);
						Long branchId = CommonUtils.convertLong(arr[3]);
						if (proposalStatus == 5 && branchId != null) {
							isSanction =true;
						}
					}
					
					for(Object[] arr : proposalDetailByApplicationId ){
						Integer proposalStatus = CommonUtils.convertInteger(arr[1]);
						Long branchId = CommonUtils.convertLong(arr[3]);
						if (isSanction && (proposalStatus != 5)) {
							UserResponse userResponse=  userClient.getBranchUsersListByBranchId(branchId);
							for (int i=0;i<userResponse.getListData().size();i++) {
								try {
									String to[] = null;
									String smsTo = null;
									Map<String, Object> parameters = new HashMap<>();
									String fpName = "";
									String subject = "Intimation - Another Bank has Sanctioned the Proposal";
									Boolean result = false;
									String checkerName = CHECKER_LITERAL;
		
										BranchUserResponse request = MultipleJSONObjectHelper.getObjectFromMap((Map) userResponse.getListData().get(i), BranchUserResponse.class);
										logger.info("BranchUser=>{}",request );
										String userId = request.getUserId();
										fpName = request.getUserName();
										String organizationName = loanApplicationService.getFsApplicantName(applicationId);
		
										ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.getByApplicationId(applicationId);
										String applicationCode = applicationProposalMapping.getApplicationCode();
		
										parameters.put(USER_NAME, fpName);
										if (request.getUserRole().equals(CHECKER)){
											if(!request.getUserName().equals("") && request.getUserName() != null) {
												checkerName = request.getUserName();
											}
											logger.info("Checker Name =====>{}",checkerName);
										}
										to = new String[]{request.getEmail()};
										smsTo = request.getMobile();
		
										if (request.getUserRole().equals(HO)) {
											fpName = request.getUserName();
											parameters.put(APPLICATION_ID, applicationCode);
											parameters.put(NAME_OF_ENTITY, organizationName != null ? organizationName : "Fund Seeker");
											parameters.put(CHECKER_NAME, checkerName);
											if (fpName != "" && fpName != null) {
												parameters.put(HO_NAME, fpName);
											}
											else{
												parameters.put(HO_NAME, PARAM_SIR_MADAM);
											}
		
											logger.info(LOGGER_SUBJECT,subject);
											logger.info(LOGGER_PARAMETER_FP_NAME_HO,parameters.get(HO_NAME));
											logger.info(LOGGER_EMAIL_ID,to[0]);
											logger.info(LOGGER_MOBILE_NO,smsTo);
		
											result = sendEmail(to,userId,parameters, NotificationAlias.EMAIL_SANCTION_HO_MULTIPLE_BANK,subject);
											if(result) {
												logger.info(EMAIL_SEND_TO_AND_WHEN_BRANCH_TRANSFER, to[0],request.getUserRole());
											}else{
												logger.error(ERROR_IN_SENDING_EMAIL_TO_AND_WHEN_BRANCH_TRANSFER,to[0],request.getUserRole());
											}
		
											Boolean smsStatus = createNotificationForSMS(smsTo,userId,parameters,NotificationAlias.SMS_SANCTION_HO_MULTIPLE_BANK);
											logger.info(SMS_SENDING_PROCESS_COMPLETE_STATUS_IS , smsStatus);
		
										}
										else if (request.getUserRole().equals(BO)) {
											fpName = request.getUserName();
											parameters.put(APPLICATION_ID, applicationCode);
											parameters.put(NAME_OF_ENTITY, organizationName);
											if (fpName != "" && fpName != null) {
												parameters.put(BO_CHECKER, fpName);
											}
											else{
												parameters.put(BO_CHECKER, PARAM_SIR_MADAM);
											}
		
											logger.info(LOGGER_SUBJECT , subject);
											logger.info("parameter fpName BO=====>{}",parameters.get(HO_NAME));
											logger.info(LOGGER_EMAIL_ID,to[0]);
											logger.info(LOGGER_MOBILE_NO,smsTo);
		
											result = sendEmail(to,userId,parameters, NotificationAlias.EMAIL_SANCTION_BO_MULTIPLE_BANK,subject);
											if(result) {
												logger.info(EMAIL_SEND_TO_AND_WHEN_BRANCH_TRANSFER, to[0],request.getUserRole());
											}else{
												logger.error(ERROR_IN_SENDING_EMAIL_TO_AND_WHEN_BRANCH_TRANSFER,to[0],request.getUserRole());
											}
		
											Boolean smsStatus = createNotificationForSMS(smsTo,userId,parameters,NotificationAlias.SMS_SANCTION_BO_MULTIPLE_BANK);
											logger.info(SMS_SENDING_PROCESS_COMPLETE_STATUS_IS, smsStatus);
										}
										else if(request.getUserRole().equals(CHECKER)) {
											fpName = request.getUserName();
											parameters.put(APPLICATION_ID, applicationCode);
											parameters.put(NAME_OF_ENTITY, organizationName);
											parameters.put(CHECKER_NAME, !fpName.equals("")?fpName:PARAM_SIR_MADAM);
		
											logger.info(LOGGER_SUBJECT , subject);
											logger.info(LOGGER_PARAMETER_FP_NAME_CHEKER,parameters.get(HO_NAME));
											logger.info(LOGGER_EMAIL_ID,to[0]);
											logger.info(LOGGER_MOBILE_NO,smsTo);
		
											result = sendEmail(to, userId, parameters, NotificationAlias.EMAIL_SANCTION_CHECKER_MULTIPLE_BANK, subject);
											if(result) {
												logger.info(EMAIL_SEND_TO_AND_WHEN_BRANCH_TRANSFER, to[0],request.getUserRole());
											}else{
												logger.error(ERROR_IN_SENDING_EMAIL_TO_AND_WHEN_BRANCH_TRANSFER,to[0],request.getUserRole());
											}
		
											Boolean smsStatus = createNotificationForSMS(smsTo,userId,parameters,NotificationAlias.SMS_SANCTION_CHECKER_MULTIPLE_BANK);
											logger.info(SMS_SENDING_PROCESS_COMPLETE_STATUS_IS , smsStatus);
		
										}
										isSent = true;
										logger.info("{} ====> {} ===>{} ====> {}  ====> {} ====> {}",userId,request.getUserRole(),request.getEmail(),fpName,organizationName,request.getMobile()  );
								} catch (Exception e) {
									logger.error("Exception",e);
								}
							}
						}
		
					}
				}
	 
		logger.info("outside notification end for sanction");
		return isSent;
	}


	private Boolean sendEmail(String[] toNo,String userId,Map<String, Object> parameters,Long templateId,String subject) {
		logger.info("inside email for {}",toNo[0]);
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
			logger.info(" email status :  {}",status);


			isSent=status;
		} catch (NotificationException e) {
			logger.debug("Error in sending mail To {} for {}",notificationRequest.getNotifications().get(0).getTo(),notificationRequest.getAlias());
			logger.debug("Error :{}",e.getMessage());
			logger.error("Exception",e);
			isSent = false;
		}
		logger.info("outside email for {}",toNo[0]);
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
