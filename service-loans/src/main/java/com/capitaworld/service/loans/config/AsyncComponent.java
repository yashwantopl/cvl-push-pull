package com.capitaworld.service.loans.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
import com.capitaworld.service.loans.model.PaymentRequest;
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
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;
import com.ibm.icu.text.SimpleDateFormat;

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
	
	@Autowired
	private OneFormClient oneFormClient;
	
	
	
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
				if(totalApplication == 1) {
					logger.info("Call method for sent mail if profile details filled or not ====>" + totalApplication);
					sentMailWhenUserLogoutWithoutFillingFirstProfileOrPrimaryData(userId);	
					return;
				} else {
					logger.info("Exits,User has more then one application ====>" + totalApplication);
					return;	
				}
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
	
	/**
	 * FS Mail Number :- 6
	 *  When user logout without filling first profile or primary details
	 * @param userId :- FS Login UserId
	 * This Method Called From sendMailWhenUserHasNoApplication method
	 */
	@Async
	private void sentMailWhenUserLogoutWithoutFillingFirstProfileOrPrimaryData(Long userId) {
		logger.info("Start sent mail process for user logout withour filled first application profile or primary details");
		try {
			List<LoanApplicationRequest> loanApplicationRequestList = loanApplicationService.getList(userId);
			if(loanApplicationRequestList.size() > 1 || loanApplicationRequestList.size() == 0) {
				logger.info("User has more one application or not application list========>"+loanApplicationRequestList.size());
				return;
			}
			NotificationTemplate template = NotificationTemplate.LOGOUT_WITHOUT_FILLED_PROFILE_DETAILS;
			LoanApplicationRequest loanApplicationRequest = loanApplicationRequestList.get(0);
			if(!CommonUtils.isObjectNullOrEmpty(loanApplicationRequest)) {
				
				if(!CommonUtils.isObjectNullOrEmpty(loanApplicationRequest.getIsApplicantDetailsFilled())) {
					if(loanApplicationRequest.getIsApplicantDetailsFilled()) {//CHECK USER HAS FILLED PROFILE DETAILS
						if(!CommonUtils.isObjectNullOrEmpty(loanApplicationRequest.getIsApplicantPrimaryFilled())) {
							if(loanApplicationRequest.getIsApplicantPrimaryFilled()) {// CHECK USER HAS FILLED PRIMARY DETAILS
								logger.info("User has filled profile and primary details ----> "+loanApplicationRequest.getApplicationCode() + "======ID======="+loanApplicationRequest.getId());
								return;	
							} else {
								//SENT MAIL FOR PRIMARY DETAILS
								logger.info("Mail Template Ready for user has not filled primary details");
								template = NotificationTemplate.LOGOUT_WITHOUT_FILLED_PRIMARY_DETAILS;
							}	
						} else {
							//SENT MAIL FOR PRIMARY DETAILS
							logger.info("Mail Template Ready for user has not filled primary details");
							template = NotificationTemplate.LOGOUT_WITHOUT_FILLED_PRIMARY_DETAILS;
						}
					} else {
						logger.info("Mail Template Ready for user has not filled profile details");
					}
				}
				UserResponse userResponse = usersClient.getEmailAndNameByUserId(userId);
				if (!CommonUtils.isObjectNullOrEmpty(userResponse.getData())) {
					@SuppressWarnings("unchecked")
					UsersRequest request = MultipleJSONObjectHelper
	    					.getObjectFromMap((LinkedHashMap<String, Object>) userResponse.getData(), UsersRequest.class);
	    			if(!CommonUtils.isObjectNullOrEmpty(request)) {
	    				Map<String, Object> parameters = new HashMap<String, Object>();
	    				if(template.getValue() == NotificationTemplate.LOGOUT_WITHOUT_FILLED_PROFILE_DETAILS.getValue()) {
	    					parameters.put("fs_name", request.getName());
	    					parameters.put("application_id", !CommonUtils.isObjectNullOrEmpty(loanApplicationRequest.getApplicationCode()) ? loanApplicationRequest.getApplicationCode() : "NA");	
	    				} else if(template.getValue() == NotificationTemplate.LOGOUT_WITHOUT_FILLED_PRIMARY_DETAILS.getValue()) {
	    					String fsName = loanApplicationService.getFsApplicantName(loanApplicationRequest.getId());
		    				parameters.put("fs_name", !CommonUtils.isObjectNullOrEmpty(fsName) ? fsName : request.getName());
	    					Integer totalCount = 0;
	    					try {
	    						UserResponse response =  usersClient.getActiveUserCount(CommonUtils.UserType.FUND_PROVIDER);
	    						if(!CommonUtils.isObjectNullOrEmpty(response)) {
	    							if(!CommonUtils.isObjectNullOrEmpty(response.getData())) {
	    								totalCount = (Integer) response.getData();
	    							}
	    						}
	    					} catch(Exception e) {
	    						logger.error("Throw Excecption While Get Total Fp User Count");
	    						e.printStackTrace();
	    					}
	    					parameters.put("total_fp_count", totalCount);	
	    				}
	    				String[] toIds = {request.getEmail()};
	    				sendNotification(toIds,userId.toString(),parameters, template,null,false,null);
	    				logger.info("Exits, Successfully sent mail when user not filled first profile or primary data ---->"+request.getEmail() + "-----Subject----"+NotificationTemplate.getSubjectName(template.getValue(), null));
	    			}
	    		} else {
	    			logger.info("User response null while getting email id and user type");
	    		}
			} else {
				logger.info("LoanAoplicationRequest object null or empty");	
			}
		} catch(Exception e){
			logger.info("Throw Exception while sent Mail When User Logout Without Filling First Profile or primary Data");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * FS Mail Number :- 12
	 *  Sent Mail After 3 hour from primary submit If user not filled final detail.
	 * @param userId :- FS Login UserId
	 * This Method Called From LoanApplicationController(lockPrimary) method
	 */
	@Async
	public void sentMailWhenUserLogoutWithoutFillingFinalData(Long userId, Long applicationId) {
		logger.info("Start Sent Mail Process When User not Fill Final Detail After 3 Hour From Primary Submit ------->"+applicationId);
		try {
			new Timer().schedule(new java.util.TimerTask() {
				@Override
				public void run() {
					try {
						Boolean finalDetailFilled = loanApplicationService.isFinalDetailFilled(applicationId, userId);
						if(finalDetailFilled) {
							logger.info("FS user filled final detail within 3 hour from primary submit------->"+applicationId);
							return;
						}
						UserResponse userResponse = usersClient.getEmailAndNameByUserId(userId);
						if (!CommonUtils.isObjectNullOrEmpty(userResponse.getData())) {
							UsersRequest request = MultipleJSONObjectHelper
			    					.getObjectFromMap((LinkedHashMap<String, Object>) userResponse.getData(), UsersRequest.class);
			    			if(!CommonUtils.isObjectNullOrEmpty(request)) {
			    				Map<String, Object> parameters = new HashMap<String, Object>();
			    				String fsName = loanApplicationService.getFsApplicantName(applicationId);
			    				parameters.put("fs_name", !CommonUtils.isObjectNullOrEmpty(fsName) ? fsName : request.getName());
			    				String[] toIds = {request.getEmail()};
			    				sendNotification(toIds,userId.toString(),parameters, NotificationTemplate.LOGOUT_WITHOUT_FILLED_FINAL_DETAILS,null,false,null);
			    				logger.info("Exits, Successfully sent mail when User not Fill Final Detail After 3 Hour From Primary Submit---->"+request.getEmail() + "------appID---"+applicationId);
			    			}
			    		} else {
			    			logger.info("User response null while getting email id and user type,FS Mail Number :- 12----->"+applicationId);
			    		}
					} catch (Exception e) {
						logger.error("Error while sent mail when User not Fill Final Detail After 3 Hour From Primary Submit----->"+applicationId);
						e.printStackTrace();
					}
				}
			}, 10800000);
			//10800000   ---> 3 Hour
		} catch (Exception e) {
			logger.error("Error while sent mail when User not Fill Final Detail After 3 Hour From Primary Submit----->"+applicationId);
			e.printStackTrace();
		}
	}
	
	
	/**
	 * FS Mail Number :- 5
	 *  When user logout without selecting any application after two days this mail sent for remainder
	 * @param userId :- FS Login UserId
	 * This Method Called From sendMailWhenUserHasNoApplication method
	 */
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
						logger.error("Error while sent logout immediately reminder mail");
						e.printStackTrace();
					}
				}
			}, 172800000);
		} catch (Exception e) {
			logger.error("Error while sent logout immediately reminder mail");
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
	
	
	public UsersRequest getUserNameAndEmail(Long userId){
		try {
			UserResponse userResponse = usersClient.getEmailAndNameByUserId(userId);
			if (!CommonUtils.isObjectNullOrEmpty(userResponse.getData())) {
				UsersRequest request = MultipleJSONObjectHelper
    					.getObjectFromMap((LinkedHashMap<String, Object>) userResponse.getData(), UsersRequest.class);
    			if(!CommonUtils.isObjectNullOrEmpty(request)) {
    				return request;
    			}
    		}
		} catch(Exception e) {
			logger.info("Throw exception while get name and email by userid");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * FS Mail Number :- 9
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
			parameters.put("application_id", !CommonUtils.isObjectNullOrEmpty(loanBasicDetails.getApplicationCode()) ? loanBasicDetails.getApplicationCode() : "NA");
			parameters.put("loan", CommonUtils.getLoanNameForMail(loanBasicDetails.getProductId()));	
		} else {
			parameters.put("application_id", "NA");
			parameters.put("loan", "NA");
		}
		
		try {
			logger.info("Stating get total match count");
			ProposalMappingRequest proposalMappingRequest = new ProposalMappingRequest();
			proposalMappingRequest.setApplicationId(applicationId);
			proposalMappingRequest.setUserType(Long.valueOf(CommonUtils.UserType.FUND_SEEKER));
			ProposalMappingResponse proposalDetailsResponse = proposalDetailsClient.connections(proposalMappingRequest);
			if(!CommonUtils.isObjectNullOrEmpty(proposalDetailsResponse)) {
				ConnectionResponse connectionResponse =	(ConnectionResponse) MultipleJSONObjectHelper
    					.getObjectFromMap((Map<String, Object>)proposalDetailsResponse.getData(),ConnectionResponse.class);
				if(!CommonUtils.isObjectNullOrEmpty(connectionResponse)) {
					logger.info("successfully get total matches count suggestion list -----> "+connectionResponse.getSuggetionList().size());
					logger.info("successfully get total matches count -----> "+connectionResponse.getSuggetionByMatchesList().size());
					parameters.put("total_matches", connectionResponse.getSuggetionByMatchesList().size());	
				} else {
					logger.warn("ConnectionResponse null or empty whilte getting total matches count");
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
		    					parameters.put("application_id",  !CommonUtils.isObjectNullOrEmpty(loanBasicDetails.getApplicationCode()) ? loanBasicDetails.getApplicationCode() : "NA");
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
	
	/**
	 * FS Mail Number :- 14
	 *  Send Mail when FP Send Direct request to fundseeker
	 * @param userId :- FP Login UserId
	 * This Method Called From ProposalController
	 */
	@Async
	public void sentMailWhenFPSentFSDirectREquest(Long fpUserId,Long fpProductId,Long applicationId) {
		logger.info("Sent Mail When FundProvider sent direct matches request to Fundseeker");
		try {
			Long userId = loanApplicationService.getUserIdByApplicationId(applicationId);
			logger.info("FPSentDirectRequestToFS, Check FS User Under SP or Not (FS ID) ---->"+userId);
			UserResponse response = usersClient.checkUserUnderSp(userId);
			if(!CommonUtils.isObjectNullOrEmpty(response)) {
				if(!(Boolean)response.getData()) {
					logger.info("FPSentDirectRequestToFS, Get Email And Name By FS User ID ---->"+userId);
					UserResponse userResponse = usersClient.getEmailAndNameByUserId(userId);
					if (!CommonUtils.isObjectNullOrEmpty(userResponse.getData())) {
						UsersRequest request = MultipleJSONObjectHelper
		    					.getObjectFromMap((LinkedHashMap<String, Object>) userResponse.getData(), UsersRequest.class);
		    			if(!CommonUtils.isObjectNullOrEmpty(request)) {
		    				logger.info("FPSentDirectRequestToFS, Start Fill Parameters Details (ApplicationId) ---->"+applicationId);
		    				Map<String, Object> parameters = new HashMap<String, Object>();
		    				String fsName = loanApplicationService.getFsApplicantName(applicationId);
		    				parameters.put("fs_name", !CommonUtils.isObjectNullOrEmpty(fsName) ? fsName : request.getName());
		    				LoanApplicationRequest loanBasicDetails = loanApplicationService.getLoanBasicDetails(applicationId, userId);
		    				if(loanBasicDetails != null) {
		    					logger.info("FPSentDirectRequestToFS, Application Code ----->"+loanBasicDetails.getApplicationCode());
		    					parameters.put("application_id", !CommonUtils.isObjectNullOrEmpty(loanBasicDetails.getApplicationCode()) ? loanBasicDetails.getApplicationCode() : "NA");
		    					logger.info("FPSentDirectRequestToFS, Type of loan ----->"+CommonUtils.getLoanNameForMail(loanBasicDetails.getProductId()));
		    					parameters.put("loan", CommonUtils.getLoanNameForMail(loanBasicDetails.getProductId()));	
		    				} else {
		    					parameters.put("application_id", "NA");
		        				parameters.put("loan", "NA");
		    				}
		    				logger.info("FPSentDirectRequestToFS, Start get fp name (fpProductId) ---->"+fpProductId);
		    				String fpName = "NA";
		    				try {
		    					logger.info("Start Getting Fp Name By Fp Product Id =======>"+ fpProductId);
		    					Object o[] = productMasterService.getUserDetailsByPrductId(fpProductId);
		    					if(o!=null) {
		    						fpName = o[1].toString();
		    						logger.info("Successfully get fo name------->" + fpName);
	    						} else {
	    							logger.info("Fund Provider name can't find using "+ fpProductId +" id");
	    						}
		    					parameters.put("fp_name",fpName);
		    				} catch (Exception e) {
		    					logger.warn("Error while get fund provider name");
		    					e.printStackTrace();
		    					parameters.put("fp_name","NA");
		    				}
		    				logger.info("FPSentDirectRequestToFS, End Parameter fill, And Start sending mail to ---->"+request.getEmail());
		    				String[] toIds = {request.getEmail()};
		    				sendNotification(toIds,userId.toString(),parameters, NotificationTemplate.FP_DIRECT_SENT_REQUEST_TO_FP,fpName,false,null);
		    				logger.info("Exits, Successfully sent mail when fp sent directly request to fs user (FP NAME)---->"+fpName);
		    			}
					} else {
						logger.info("FPSentDirectRequestToFS, User Data Null or Empty (usersClient.getEmailAndNameByUserId) ---->"+userId);
					}
				} else {
					logger.info("FPSentDirectRequestToFS, FS User Under SP (FS ID) ---->"+userId);
				}
			} else {
				logger.info("FPSentDirectRequestToFS, UserResponse Null Or Empty (usersClient.checkUserUnderSp)---->"+userId);
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
		notification.setSubject(NotificationTemplate.getSubjectName(template.getValue(), fpName));
		notificationRequest.addNotification(notification);
		//SEND MAIL
		if(isTimerMail) {
			sendMailWithTimer(notificationRequest,milisecond,NotificationTemplate.getSubjectName(template.getValue(), fpName));
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
	public void sendMailWhenFSSelectOnlinePayment(Long userId,PaymentRequest paymentInfo, NotificationTemplate emailNotificationTemplate,Long sysTemplateId) {
		try {
			if(CommonUtils.isObjectNullOrEmpty(paymentInfo.getEmailAddress())) {
				logger.info("Email Address null or Empty while send mail when user select online payment");
			}
			Map<String,Object> parameters = new HashMap<>();
			SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			String fsName = loanApplicationService.getFsApplicantName(paymentInfo.getApplicationId());
			parameters.put("fs_name", !CommonUtils.isObjectNullOrEmpty(fsName) ? fsName : "NA");
			parameters.put("entity_name", !CommonUtils.isObjectNullOrEmpty(paymentInfo.getNameOfEntity()) ? paymentInfo.getNameOfEntity() : "NA");
			parameters.put("mobile_number", !CommonUtils.isObjectNullOrEmpty(paymentInfo.getMobileNumber()) ? paymentInfo.getMobileNumber() : "NA");
			
			String regOfficeAdd = "";
			if(!CommonUtils.isObjectNullOrEmpty(paymentInfo.getAddress())) {
				regOfficeAdd = !CommonUtils.isObjectNullOrEmpty(paymentInfo.getAddress().getPremiseNumber()) ? paymentInfo.getAddress().getPremiseNumber() + ", " : "";
				regOfficeAdd += !CommonUtils.isObjectNullOrEmpty(paymentInfo.getAddress().getStreetName()) ? paymentInfo.getAddress().getStreetName() + ", " : "";
				regOfficeAdd += !CommonUtils.isObjectNullOrEmpty(paymentInfo.getAddress().getLandMark()) ? paymentInfo.getAddress().getLandMark() + ", " : "";
				String countryName = getCountryName(paymentInfo.getAddress().getCountryId());
				regOfficeAdd += !CommonUtils.isObjectNullOrEmpty(countryName) ? countryName + ", " : "";
				String stateName = getStateName(paymentInfo.getAddress().getStateId());
				regOfficeAdd += !CommonUtils.isObjectNullOrEmpty(stateName) ? stateName+ ", " : "";
				String cityName = getCityName(paymentInfo.getAddress().getCityId());
				regOfficeAdd += !CommonUtils.isObjectNullOrEmpty(cityName) ? cityName : "";
			}
			parameters.put("address",!CommonUtils.isObjectNullOrEmpty(regOfficeAdd) ? regOfficeAdd : "NA");
			parameters.put("appointment_date",!CommonUtils.isObjectNullOrEmpty(paymentInfo.getAppointmentDate()) ? dt.format(paymentInfo.getAppointmentDate()) : "NA");
			parameters.put("appointment_time",paymentInfo.getAppointmentTime());
			
			String[] toIds = {paymentInfo.getEmailAddress()};
			sendNotification(toIds,userId.toString(),parameters, emailNotificationTemplate,null,false,null);
			if(!CommonUtils.isObjectNullOrEmpty(sysTemplateId)) {
				String[] toUserIds = {userId.toString()};
				synNotification(toUserIds, userId, sysTemplateId, parameters, paymentInfo.getApplicationId(), null);
				logger.info("Saved System Notification when FS select Online Payment--------------------------------->"+paymentInfo.getEmailAddress());				
			}
			logger.info("Send Mail when FS select Online Payment--------------------------------->"+paymentInfo.getEmailAddress());
			
		} catch (Exception e) {
			logger.info("Throw Exception while send FS select online payment !!");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * SEND MAIL TO CHECKER WHEN MAKER LOCK FINAL DETAILS 
	 * @param checkerId
	 * @param makerId
	 * @param applicationCode
	 * @param productId
	 * @param fsName
	 */
	public void sendEmailWhenMakerLockFinalDetails(Long checkerId,Long makerId,
			String applicationCode,Integer productId,String fsName) {
		logger.info("Enter in send mail when aker has lock final details then send to checker ");
		try {
			UsersRequest checkerUserName = getUserNameAndEmail(checkerId);
			UsersRequest makerUserName = getUserNameAndEmail(makerId);
			if(CommonUtils.isObjectNullOrEmpty(checkerUserName) || CommonUtils.isObjectNullOrEmpty(makerUserName)) {
				logger.info("Check request or maker request null or empty");
				return;
			}
			Map<String,Object> parameters = new HashMap<>();
			parameters.put("checker_name",checkerUserName.getName());
			parameters.put("fs_name",fsName);
			parameters.put("lone_type", CommonUtils.getLoanName(productId));
			String[] toIds = {checkerUserName.getEmail()};
			String subject = makerUserName.getName()+ " has lock final details for " + applicationCode;
			sendNotification(toIds,checkerId.toString(),parameters, NotificationTemplate.EMAIL_CKR_MKR_FINAL_LOCK,subject,false,null);
			logger.info("Successfully send mail ------------------>" + checkerUserName.getEmail());
		} catch (Exception e) {
			logger.info("Throw exception while sending final lock mail");
			e.printStackTrace();
		}
		
		
		
	};
	
	
	/**
	 * 
	 * @param toIds :- TO APPLICATION USER ID
	 * @param fromId :- CURRENT USER ID 
	 * @param templateId :- NOTIFICATION TEMPLATE ID
	 * @param parameters :- MAP
	 * @param applicationId :- CURRENT APPLICATION ID
	 * @param fpProductId :- NON MANDATOY
	 * @return
	 */
	private void synNotification(String[] toIds, Long fromId, Long templateId,
			Map<String, Object> parameters, Long applicationId, Long fpProductId) {

		NotificationRequest notificationRequest = new NotificationRequest();
		notificationRequest.setClientRefId(fromId.toString());

		Notification notification = new Notification();

		notification.setTo(toIds);
		notification.setType(NotificationType.SYSTEM);
		notification.setTemplateId(templateId);
		notification.setContentType(ContentType.TEMPLATE);
		notification.setParameters(parameters);
		notification.setFrom(fromId.toString());
		notification.setProductId(fpProductId);
		notification.setApplicationId(applicationId);
		notificationRequest.addNotification(notification);
		try {
			notificationClient.send(notificationRequest);
		} catch (NotificationException e) {
			logger.info("Throw Exception While Send Sys Notication");
			e.printStackTrace();
		}

	}
	
	@SuppressWarnings("unchecked")
	private String getCityName(Long cityId) {
		try {
			if(CommonUtils.isObjectNullOrEmpty(cityId)) {
				return null;
			}
			List<Long> cityList = new ArrayList<>(1);
			cityList.add(cityId);
			OneFormResponse oneFormResponse = oneFormClient.getCityByCityListId(cityList);
			List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
					.getListData();
			if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
				MasterResponse masterResponse = MultipleJSONObjectHelper
						.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
				return masterResponse.getValue();
			}
		} catch (Exception e) {
			logger.info("Throw Exception while get city name by city Id in Asyn Mail Integation");
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private String getStateName(Integer stateId) {
		try {
			if(CommonUtils.isObjectNullOrEmpty(stateId)) {
				return null;
			}
			List<Long> stateList = new ArrayList<>(1);
			stateList.add(stateId.longValue());
			OneFormResponse oneFormResponse = oneFormClient.getStateByStateListId(stateList);
			List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
					.getListData();
			if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
				MasterResponse masterResponse = MultipleJSONObjectHelper
						.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
				return masterResponse.getValue();
			}
		} catch (Exception e) {
			logger.info("Throw Exception while get city name by city Id in Asyn Mail Integation");
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private String getCountryName(Integer country) {
		try {
			if(CommonUtils.isObjectNullOrEmpty(country)) {
				return null;
			}
			List<Long> countryList = new ArrayList<>(1);
			countryList.add(country.longValue());
			OneFormResponse oneFormResponse = oneFormClient.getCountryByCountryListId(countryList);
			List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
					.getListData();
			if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
				MasterResponse masterResponse = MultipleJSONObjectHelper
						.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
				return masterResponse.getValue();
			}
		} catch (Exception e) {
			logger.info("Throw Exception while get country name by country Id in DDR Onform");
			e.printStackTrace();
		}
		return null;
	}
	
}
