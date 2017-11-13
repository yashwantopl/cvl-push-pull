package com.capitaworld.service.loans.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantService;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.retail.RetailApplicantRequest;
import com.capitaworld.service.loans.service.ProposalService;
import com.capitaworld.service.loans.service.fundprovider.ProductMasterService;
import com.capitaworld.service.loans.utils.CommonNotificationUtils.NotificationTemplate;
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.matchengine.model.ConnectionResponse;
import com.capitaworld.service.matchengine.model.ProposalCountResponse;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.matchengine.model.ProposalMappingResponse;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.notification.client.NotificationClient;
import com.capitaworld.service.notification.exceptions.NotificationException;
import com.capitaworld.service.notification.model.Notification;
import com.capitaworld.service.notification.model.NotificationRequest;
import com.capitaworld.service.notification.utils.ContentType;
import com.capitaworld.service.notification.utils.NotificationType;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;

@Component
public class AsyncComponent {
	private static final Logger logger = LoggerFactory.getLogger(AsyncComponent.class.getName());

	@Autowired
	private Environment environment;
	
	@Autowired
	private UsersClient usersClient;
	
	@Autowired
	private LoanApplicationService loanApplicationService;
	
	@Autowired
	private NotificationClient notificationClient;
	
	@Autowired
	private ProposalDetailsClient proposalDetailsClient;
	
	@Autowired
	private ProductMasterService productMasterService;
	
	@Autowired
	private CorporateApplicantService corporateApplicantService;
	
	@Autowired
	private RetailApplicantService retailApplicantService;
	
	
	
	private static final String EMAIL_ADDRESS_FROM = "com.capitaworld.mail.url";
	
	
	/**
	 * FS Mail Number :- 4
	 *  Send Mail when Fund seeker login first time in our system and logout without selecting any application
	 * @param userId :- FS Login UserId
	 * This Method Called From LoanApplicationController
	 */
	@SuppressWarnings("unchecked")
	@Async
	public void sendMailWhenUserHasNoApplication(Long userId){
		logger.info("Enter in sending mail when user has no application");
		try {
			Long totalApplication = loanApplicationService.getTotalUserApplication(userId);
			if(totalApplication > 0) {
				logger.info("Exits,User has more then one application");
				return;
			}
			logger.info("Call user client for get email and name by user id");
			UserResponse userResponse = usersClient.getEmailAndNameByUserId(userId);
			if (!CommonUtils.isObjectNullOrEmpty(userResponse.getData())) {
				UsersRequest request = MultipleJSONObjectHelper
    					.getObjectFromMap((LinkedHashMap<String, Object>) userResponse.getData(), UsersRequest.class);
    			if(!CommonUtils.isObjectNullOrEmpty(request)) {
    				Map<String, Object> parameters = new HashMap<String, Object>();
    				parameters.put("fs_name", request.getName());
    				String[] toIds = {request.getEmail()};
    				sendNotification(toIds,userId.toString(),parameters, NotificationTemplate.LOGOUT_IMMEDIATELY,null,false,null);
    				logger.info("Exits, Successfully sent mail when user has no application ---->"+request.getEmail());
    				sendRemainderMailWhenUserHasNoApplication(userId,parameters,toIds);
    			}
    		} else {
    			logger.info("User response null while getting email id and user type");
    		}
		} catch(Exception e) {
			logger.info("Throw exception while sending mail, logout immediately");
			e.printStackTrace();
		}
	}
	
	@Async
	private void sendRemainderMailWhenUserHasNoApplication(Long userId,Map<String, Object> parameters,String[] toIds) {
		logger.info("start Sent remainder Mail when user not fill any application till 2 days ------->");
		try {
			new Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					try {
						Long totalApplication = loanApplicationService.getTotalUserApplication(userId);
						if(totalApplication > 0) {
							logger.info("Logout Immediately remainder, Exits method when User has more then one application");
							return;
						}
						sendNotification(toIds,userId.toString(),parameters, NotificationTemplate.LOGOUT_IMMEDIATELY_REMAINDER,null,false,null);
						logger.info("Logout Immediately remainder,Successfully sent mail to this email ===>" + toIds);
					} catch (NotificationException e) {
						logger.error("Error while send mail in notfication");
						e.printStackTrace();
					}
				}
			}, 172800000);
		} catch (Exception e) {
			logger.error("Error while call timer method in notification");
			e.printStackTrace();
		}
	}
	
	/**
	 * FS Mail Number :- 8
	 *  Send Mail when Fund seeker submit profile-primary form and go to matches page
	 * @param userId :- FS Login UserId
	 * This Method Called From MatchesController
	 */
	@SuppressWarnings("unchecked")
	@Async
	public void sendMailWhenUserCompletePrimaryForm(Long userId, Long applicationId){
		logger.info("Enter in sending mail when user Complete Primary Form");
		try {
			UserResponse userResponse = usersClient.getEmailAndNameByUserId(userId);
			if (!CommonUtils.isObjectNullOrEmpty(userResponse.getData())) {
				UsersRequest request = MultipleJSONObjectHelper
    					.getObjectFromMap((LinkedHashMap<String, Object>) userResponse.getData(), UsersRequest.class);
    			if(!CommonUtils.isObjectNullOrEmpty(request)) {
    				Map<String, Object> parameters = getFSMapData(userId,applicationId);
    				String[] toIds = {request.getEmail()};
    				sendNotification(toIds,userId.toString(),parameters, NotificationTemplate.PRIMARY_FILL_COMPLETE,null,false,null);
    				logger.info("Exits, Successfully sent mail when user complete primary form ---->"+request.getEmail());
    			}
    		}
		} catch(Exception e) {
			logger.info("Throw exception while sending mail, Primary Complete");
			e.printStackTrace();
		}
	}
	
	/**
	 * FS Mail Number :- 8
	 *  Send Mail when Fund seeker submit profile-primary form and go to matches page
	 * @param userId :- FS Login UserId
	 * This Method Called From LoanApplicationController.java
	 */
	@SuppressWarnings("unchecked")
	@Async
	public void sendMailForFirstTimeUserViewMatches(Long applicationId,Long userId) {
		logger.info("Enter in sending mail when user go first time in matches page----" +NotificationTemplate.FS_GO_MATCHES_PAGE.getValue());
		try {
			UserResponse userResponse = usersClient.getEmailAndNameByUserId(userId);
			if (!CommonUtils.isObjectNullOrEmpty(userResponse.getData())) {
				UsersRequest request = MultipleJSONObjectHelper
    					.getObjectFromMap((LinkedHashMap<String, Object>) userResponse.getData(), UsersRequest.class);
    			if(!CommonUtils.isObjectNullOrEmpty(request)) {
    				Map<String, Object> parameters = getFSMapData(userId,applicationId);
    				String[] toIds = {request.getEmail()};
    				sendNotification(toIds,userId.toString(),parameters, NotificationTemplate.FS_GO_MATCHES_PAGE,null,true,300000);
    				logger.info("Exits, Successfully sent mail when user go first time in matches page---->"+request.getEmail());
    			}
    		}
		} catch(Exception e) {
			logger.info("Throw exception while sending mail, Primary Complete");
			e.printStackTrace();
		}
	}
	
	private Map<String, Object> getFSMapData(Long userId,Long applicationId) throws Exception{
		Map<String, Object> parameters = new HashMap<String, Object>();
		String fsName = loanApplicationService.getFsApplicantName(applicationId);
		parameters.put("fs_name", !CommonUtils.isObjectNullOrEmpty(fsName) ? fsName : "NA");
		LoanApplicationRequest loanBasicDetails = loanApplicationService.getLoanBasicDetails(applicationId, userId);
		if(loanBasicDetails != null) {
			parameters.put("application_id", loanBasicDetails.getApplicationCode());
			parameters.put("loan", CommonUtils.getLoanNameForMail(loanBasicDetails.getProductId()));	
		} else {
			parameters.put("application_id", "NA");
			parameters.put("loan", "NA");
		}
		
		try {
			logger.info("Stating get total match count");
			ProposalMappingRequest proposalMappingRequest = new ProposalMappingRequest();
			proposalMappingRequest.setApplicationId(applicationId);
			ProposalMappingResponse proposalDetailsResponse = proposalDetailsClient.connections(proposalMappingRequest);
			if(!CommonUtils.isObjectNullOrEmpty(proposalDetailsResponse)) {
				ConnectionResponse connectionResponse =	(ConnectionResponse) MultipleJSONObjectHelper
    					.getObjectFromMap((Map<String, Object>)proposalDetailsResponse.getData(),ConnectionResponse.class);
				if(!CommonUtils.isObjectNullOrEmpty(connectionResponse)) {
					logger.info("successfully get total matches count -----> "+connectionResponse.getSuggetionByMatchesList().size());
					parameters.put("total_matches", connectionResponse.getSuggetionByMatchesList().size());	
				} else {
					logger.warn("ConnectionResponse null or emprty whilt gettin total matches count");
					parameters.put("total_matches",0);
				}
			} else {
				logger.warn("Something went wrong, Proposal service not available");
				parameters.put("total_matches",0);
			}
				
		} catch(Exception e) {
			logger.warn("Error while get total suggestion matches list when primary locked mail sending");
			e.printStackTrace();
			parameters.put("total_matches", 0);
		}
		return parameters;
	}
	
	/**
	 * FS Mail Number :- 14
	 *  Send Mail when FP Click View More Details But FS not Complete Final Details 
	 * @param userId :- FP Login UserId
	 * This Method Called From LoanApplicationController
	 */
	@SuppressWarnings("unchecked")
	@Async
	public void sendMailWhenUserNotCompleteFinalDetails(Long fpUserId, Long applicationId){
		logger.info("Enter in sending mail when FP Click View More Details But FS not filled final details");
		try {
			Long lastFpProductId = getLastAccessId(fpUserId);
			if(CommonUtils.isObjectNullOrEmpty(lastFpProductId)) {
				logger.warn("Return, FP Product Id null or empty =========================>"+fpUserId);
				return;
			}
			Long userId = loanApplicationService.getUserIdByApplicationId(applicationId);
			UserResponse response = usersClient.checkUserUnderSp(userId);
			if(!CommonUtils.isObjectNullOrEmpty(response)) {
				if(!(Boolean)response.getData()) {
					UserResponse userResponse = usersClient.getEmailAndNameByUserId(userId);
					if (!CommonUtils.isObjectNullOrEmpty(userResponse.getData())) {
						UsersRequest request = MultipleJSONObjectHelper
		    					.getObjectFromMap((LinkedHashMap<String, Object>) userResponse.getData(), UsersRequest.class);
		    			if(!CommonUtils.isObjectNullOrEmpty(request)) {
		    				Map<String, Object> parameters = new HashMap<String, Object>();
		    				String fsName = loanApplicationService.getFsApplicantName(applicationId);
		    				parameters.put("fs_name", !CommonUtils.isObjectNullOrEmpty(fsName) ? fsName : "NA");
		    				LoanApplicationRequest loanBasicDetails = loanApplicationService.getLoanBasicDetails(applicationId, userId);
		    				if(loanBasicDetails != null) {
		    					parameters.put("application_id", loanBasicDetails.getApplicationCode());
		        				parameters.put("loan", CommonUtils.getLoanNameForMail(loanBasicDetails.getProductId()));	
		    				} else {
		    					parameters.put("application_id", "NA");
		        				parameters.put("loan", "NA");
		    				}
		    				String fpName = "NA";
		    				try {
		    					logger.info("Start Getting Fp Name By Fp Product Id =======>"+ lastFpProductId);
		    					Object o[] = productMasterService.getUserDetailsByPrductId(lastFpProductId);
		    					if(o!=null) {
		    						fpName = o[1].toString();
		    						logger.info("Successfully get fo name------->" + fpName);
	    						} else {
	    							logger.info("Fund Provider name can't find using "+ lastFpProductId +" id");
	    						}
		    					parameters.put("fp_name",fpName);
		    				} catch (Exception e) {
		    					logger.warn("Error while get fund provider name");
		    					e.printStackTrace();
		    					parameters.put("fp_name","NA");
		    				}
		    				
		    				try {
		    					logger.info("Stating get total match count");
		    					ProposalMappingRequest proposalMappingRequest = new ProposalMappingRequest();
		    					proposalMappingRequest.setApplicationId(applicationId);
		    					ProposalCountResponse proposalCountResponse = proposalDetailsClient.proposalCountOfFundSeeker(proposalMappingRequest);
		    					if(!CommonUtils.isObjectNullOrEmpty(proposalCountResponse)) {
		    						logger.info("Successfully get total matches count ----> "+proposalCountResponse.getMatches());
			    					parameters.put("total_matches", !CommonUtils.isObjectNullOrEmpty(proposalCountResponse.getMatches()) ? proposalCountResponse.getMatches() : 0);	
		    					} else {
		    						logger.info("Something went to wrong while get total matches count");
		    					}
		    				} catch(Exception e) {
		    					logger.warn("Error while get total suggestion matches list when final details not filling mail sending");
		    					e.printStackTrace();
		    					parameters.put("total_matches", 0);
		    				}
		    				String[] toIds = {request.getEmail()};
		    				sendNotification(toIds,userId.toString(),parameters, NotificationTemplate.FP_VIEW_MORE_DETAILS,fpName,false,null);
		    				logger.info("Exits, Successfully sent mail when fp view more details but fs not filled final details ---->"+request.getEmail());
		    			}
		    		}
				}
			}
		} catch(Exception e) {
			logger.info("Throw exception while sending mail, Primary Complete");
			e.printStackTrace();
		}
	}
	

	private Long getLastAccessId(Long userId) {
		try {
			logger.info("Start Getting get last application or fpProduct Id =======>"+userId);
			UserResponse userLastAppResponse = usersClient.getLastAccessApplicant(new UsersRequest(userId));
			if (!CommonUtils.isObjectNullOrEmpty(userLastAppResponse.getId())) {
				logger.info("Successfully get fp product id=======>"+userLastAppResponse.getId());
				return userLastAppResponse.getId();
			}
			return null;
		} catch (Exception e) {
			logger.warn("Error while get fund provider name");
			e.printStackTrace();
			return null;
		}
	}
	
	private void sendNotification(String[] toIds, String userId, Map<String, Object> parameters, NotificationTemplate template,String fpName,boolean isTimerMail,Integer milisecond) throws NotificationException {
		NotificationRequest notificationRequest = new NotificationRequest();
		notificationRequest.setClientRefId(userId);
		//MAKE NOTIFICATION OBJECT
		Notification notification = new Notification();
		notification.setTo(toIds);
		notification.setType(NotificationType.EMAIL);
		notification.setTemplateId(template.getValue());
		notification.setContentType(ContentType.TEMPLATE);
		notification.setParameters(parameters);
		notification.setFrom(environment.getRequiredProperty(EMAIL_ADDRESS_FROM));
		notification.setSubject(template.isSubjConfig() ? fpName + template.getSubject() : template.getSubject());
		notificationRequest.addNotification(notification);
		//SEND MAIL
		if(isTimerMail) {
			sendMailWithTimer(notificationRequest,milisecond,template.getSubject());
		} else {
			sendMail(notificationRequest);	
		}
	}
	
	private void sendMail(NotificationRequest notificationRequest) throws NotificationException {
		notificationClient.send(notificationRequest);	
	}
	
	private void sendMailWithTimer(NotificationRequest notificationRequest,Integer milisecond,String value) {
		logger.info("start Sent Mail with timer ------->" + milisecond + "<-------->"+value);
		try {
			new Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					try {
						logger.info("End Sent Mail Wth Timer------->" + milisecond + "<-------->"+value);
						sendMail(notificationRequest);
					} catch (NotificationException e) {
						logger.error("Error while send mail in notfication");
						e.printStackTrace();
					}
				}
			}, milisecond);
		} catch (Exception e) {
			logger.error("Error while call timer method in notification");
			e.printStackTrace();
		}
	}
	
	private String getFundSeekerName(Long applicationId,Long fsUserId) {
		try {
			logger.info("Starting get fund seeker name service");
			int fsProdId = loanApplicationService.getProductIdByApplicationId(applicationId, fsUserId);
			int fsType = CommonUtils.getUserMainType(fsProdId);
			if(CommonUtils.UserMainType.CORPORATE == fsType){
				logger.info("In Corporate, Find fpProd Id by userid and applicationId");
				CorporateApplicantRequest corporateApplicantRequest = corporateApplicantService.getCorporateApplicant(fsUserId, applicationId);
				if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantRequest)) {
					logger.info("Successfully get fundseeker name =====> "+corporateApplicantRequest.getOrganisationName());
					return corporateApplicantRequest.getOrganisationName();
				}
			} else if(CommonUtils.UserMainType.RETAIL == fsType){
				logger.info("In Retails, Find fpProd Id by userid and applicationId");
				RetailApplicantRequest retailApplicantRequest = retailApplicantService.get(fsUserId, applicationId);
				if(!CommonUtils.isObjectNullOrEmpty(retailApplicantRequest)) {
					String fsName = (!CommonUtils.isObjectNullOrEmpty(retailApplicantRequest.getFirstName()) ? retailApplicantRequest.getFirstName() : "") 
							+ " " + (!CommonUtils.isObjectNullOrEmpty(retailApplicantRequest.getLastName()) ? retailApplicantRequest.getLastName() : "");
					logger.info("Successfully get fundseeker name =====> "+fsName);
					return fsName;	
				}
			}
			return null;
		}catch (Exception e) {
			logger.warn("Something went wrong while get fundseeker name");
			e.printStackTrace();
			return null;
		}
		
	}
	
}
