package com.capitaworld.service.loans.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.capitaworld.service.loans.model.PaymentRequest;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.notification.client.NotificationClient;
import com.capitaworld.service.notification.exceptions.NotificationException;
import com.capitaworld.service.notification.model.Notification;
import com.capitaworld.service.notification.model.NotificationRequest;
import com.capitaworld.service.notification.utils.ContentType;
import com.capitaworld.service.notification.utils.NotificationAlias;
import com.capitaworld.service.notification.utils.NotificationType;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.FundProviderDetailsRequest;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;

@Component
public class FPAsyncComponent {
	
	private static final Logger logger = LoggerFactory.getLogger(FPAsyncComponent.class.getName());

	@Autowired
	private NotificationClient notificationClient;
	
	@Autowired
	private UsersClient userClient;
	
	private static final String EMAIL_ADDRESS_FROM = "no-reply@capitaworld.com";
	
	@Value("${capitaworld.sidbi.mail.to.maker.checker}")
	private Boolean mailToMakerChecker;
	
	// ==================Sending Mail to all Makers after FS receives In-principle Approval==================
	
		@Async
		public void sendEmailToAllMakersWhenFSRecievesInPrinciple(Map<String,Object> proposalresp, PaymentRequest paymentRequest, Long userId, Long orgId) {
			if(mailToMakerChecker) {
				
				try {
					
					logger.info("Into sending Mail to all Makers after FS gets In-Principle Approval===>{}");
					String subject = "Intimation : New Proposal -  Application ID "+paymentRequest.getApplicationId();
					Map<String, Object> mailParameters = new HashMap<String, Object>();
					mailParameters.put("fs_name", paymentRequest.getNameOfEntity()!=null?paymentRequest.getNameOfEntity():" ");
					mailParameters.put("product_type", proposalresp.get("loan_type")!=null?proposalresp.get("loan_type").toString():" ");
					mailParameters.put("loan_amount", proposalresp.get("amount")!=null?Double.valueOf(proposalresp.get("amount").toString() ):" ");
					mailParameters.put("emi_amount", proposalresp.get("emi_amount")!=null?Double.valueOf(proposalresp.get("emi_amount").toString() ):" ");
					mailParameters.put("interest_rate", proposalresp.get("rate_interest")!=null?Double.valueOf(proposalresp.get("rate_interest").toString() ):" ");
					mailParameters.put("application_id", paymentRequest.getApplicationId());
					
					mailParameters.put("mobile_no", " ");
					mailParameters.put("address", " ");
					
					
					Long branchId = null;
					if(!CommonUtils.isObjectNullOrEmpty(proposalresp.get("branch_id"))) {
						branchId = Long.valueOf(proposalresp.get("branch_id").toString());	
						 //System.out.println("BranchId:--- "+branchId);
						 //System.out.println("orgId:--- "+orgId);
					}
	                
					UserResponse userResponse = userClient.getUserDetailByOrgRoleBranchId(orgId,com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_MAKER,branchId);
					List<Map<String, Object>> usersRespList = (List<Map<String, Object>>) userResponse.getListData();
					//String[] to = new String[usersRespList.size()];
				
					
					String to = null;
					for (int i = 0; i < usersRespList.size(); i++) {
						//System.out.println("Inside For Loop:---");
						UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),
								UsersRequest.class);
						FundProviderDetailsRequest fundProviderDetailsRequest = null;
						try {
							UserResponse userResponseForName = userClient.getFPDetails(userObj);
							fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
									FundProviderDetailsRequest.class);
							
						} catch (Exception e) {
							logger.error("error while fetching Maker name======>");
							e.printStackTrace();
						}
						System.out.println("Maker details:-"+fundProviderDetailsRequest);
						String makerName = fundProviderDetailsRequest.getFirstName()!=null?fundProviderDetailsRequest.getFirstName():""+" "
						                   +fundProviderDetailsRequest.getLastName()!=null?fundProviderDetailsRequest.getLastName():"";
						
						if(!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
							//System.out.println("Maker ID:---"+userObj.getEmail());
							to = userObj.getEmail();	
							mailParameters.put("maker_name", makerName);
							//System.out.println("Mail ID:---"+to);
							createNotificationForEmail(to, userId.toString(),
									mailParameters, NotificationAlias.EMAIL_ALL_MAKERS_AFTER_INPRINCIPLE_TO_FS, subject);
						}
				    	
					} 	
					
				}catch (Exception e) {
					logger.info("An exception getting while sending mail to all Makers=============>{}");

					e.printStackTrace();
				}
				
			}
			else {
				
				logger.info("Mail to Makers after In-principle to FS is disabled==========>");
			}
		};
		
		//==========================================================================================================
		
	
		// ==================Sending Mail to all Checkers after FS receives In-principle Approval==================
		
			@Async
			public void sendEmailToAllCheckersWhenFSRecievesInPrinciple(Map<String,Object> proposalresp, PaymentRequest paymentRequest, Long userId, Long orgId) {
				if(mailToMakerChecker) {
					
					try {
						
						logger.info("Into sending Mail to all Checkers after FS gets In-Principle Approval===>{}");
						String subject = "Intimation : New Proposal -  Application ID "+paymentRequest.getApplicationId();
						Map<String, Object> mailParameters = new HashMap<String, Object>();
						mailParameters.put("fs_name", paymentRequest.getNameOfEntity()!=null?paymentRequest.getNameOfEntity():" ");
						mailParameters.put("product_type", proposalresp.get("loan_type")!=null?proposalresp.get("loan_type").toString():" ");
						mailParameters.put("loan_amount", proposalresp.get("amount")!=null?Double.valueOf(proposalresp.get("amount").toString() ):" ");
						mailParameters.put("emi_amount", proposalresp.get("emi_amount")!=null?Double.valueOf(proposalresp.get("emi_amount").toString() ):" ");
						mailParameters.put("interest_rate", proposalresp.get("rate_interest")!=null?Double.valueOf(proposalresp.get("rate_interest").toString() ):" ");
						mailParameters.put("application_id", paymentRequest.getApplicationId());
						
						mailParameters.put("mobile_no", " ");
						mailParameters.put("address", " ");
						
						
						Long branchId = null;
						if(!CommonUtils.isObjectNullOrEmpty(proposalresp.get("branch_id"))) {
							branchId = Long.valueOf(proposalresp.get("branch_id").toString());	
							// System.out.println("BranchId:--- "+branchId);
							// System.out.println("orgId:--- "+orgId);
						}
		                
						UserResponse userResponse = userClient.getUserDetailByOrgRoleBranchId(orgId,com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_CHECKER,branchId);
						List<Map<String, Object>> usersRespList = (List<Map<String, Object>>) userResponse.getListData();
						//String[] to = new String[usersRespList.size()];
						String to = null;
						for (int i = 0; i < usersRespList.size(); i++) {
						//	System.out.println("Inside For Loop:---");
							UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),
									UsersRequest.class);
							if(!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
							//	System.out.println("Checker ID:---"+userObj.getEmail());
								to = userObj.getEmail();	
								mailParameters.put("checker_name", userObj.getUsername()!=null?userObj.getUsername():"");
							//	System.out.println("Mail ID:---"+to);
								
								createNotificationForEmail(to, userId.toString(),
										mailParameters, NotificationAlias.EMAIL_ALL_CHECKERS_AFTER_INPRINCIPLE_TO_FS, subject);
							}
					    	
						} 	
						
					}catch (Exception e) {
						logger.info("An exception getting while sending mail to all Checkers=============>{}");

						e.printStackTrace();
					}
					
				}
				else {
					
					logger.info("Mail to Checkers after In-principle to FS is disabled==========>");
				}
			};
			
			//==========================================================================================================
			
	
		
		private void createNotificationForEmail(String toNo, String userId, Map<String, Object> mailParameters,
				Long templateId, String emailSubject) throws NotificationException {
			logger.info("Inside send notification===>{}"+toNo);
			NotificationRequest notificationRequest = new NotificationRequest();
			notificationRequest.setClientRefId(userId);
			String to[] = { toNo };
			Notification notification = new Notification();
			notification.setContentType(ContentType.TEMPLATE);
			notification.setTemplateId(templateId);
			notification.setSubject(emailSubject);
			notification.setTo(to);
			notification.setType(NotificationType.EMAIL);
			notification.setFrom(EMAIL_ADDRESS_FROM);
			notification.setParameters(mailParameters);
			notificationRequest.addNotification(notification);
			sendEmail(notificationRequest);
			logger.info("Outside send notification===>{}"+toNo);
		}

		private void sendEmail(NotificationRequest notificationRequest) throws NotificationException {
			logger.info("Inside send Email for Maker===>{}");
			notificationClient.send(notificationRequest);
			logger.info("Outside send Email for Maker===>{}");
		}
	
}
