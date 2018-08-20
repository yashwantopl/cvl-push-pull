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

import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.model.PaymentRequest;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;
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
	
	@Autowired
	private CorporateApplicantService corporateapplicantService;
	
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
					mailParameters.put("fs_name", paymentRequest.getNameOfEntity()!=null?paymentRequest.getNameOfEntity():"NA");
					mailParameters.put("product_type", proposalresp.get("loan_type")!=null?proposalresp.get("loan_type").toString():"NA");
					mailParameters.put("loan_amount", proposalresp.get("amount")!=null?Double.valueOf(proposalresp.get("amount").toString() ):"NA");
					mailParameters.put("emi_amount", proposalresp.get("emi_amount")!=null?Double.valueOf(proposalresp.get("emi_amount").toString() ):"NA");
					mailParameters.put("interest_rate", proposalresp.get("rate_interest")!=null?Double.valueOf(proposalresp.get("rate_interest").toString() ):"NA");
					mailParameters.put("application_id", paymentRequest.getApplicationId()!=null?paymentRequest.getApplicationId():"NA");
				
					UserResponse response = null;
					
					try {
						response = userClient.getEmailMobile(userId);
					}
					catch(Exception e) {
						logger.info("Something went wrong while calling Users client===>{}");
						e.printStackTrace();
					}
					
					if(!CommonUtils.isObjectNullOrEmpty(response)) {
						UsersRequest signUpUser = MultipleJSONObjectHelper
								.getObjectFromMap((Map<String, Object>) response.getData(), UsersRequest.class);

						String mobile = signUpUser.getMobile();
						System.out.println("Mobile no:-"+mobile);
						mailParameters.put("mobile_no", mobile!=null?mobile:"NA");
						
					}
					
					CorporateApplicantRequest applicantRequest = corporateapplicantService.getCorporateApplicant(paymentRequest.getApplicationId());
					String address = null;
					if(!CommonUtils.isObjectNullOrEmpty(applicantRequest) 
							&& !CommonUtils.isObjectNullOrEmpty(applicantRequest.getFirstAddress())){
						address = applicantRequest.getFirstAddress().getPremiseNumber()!=null?applicantRequest.getFirstAddress().getPremiseNumber():""	
	                              +" "+applicantRequest.getFirstAddress().getStreetName()!=null?applicantRequest.getFirstAddress().getStreetName():""
	                              +" "+applicantRequest.getFirstAddress().getLandMark()!=null?applicantRequest.getFirstAddress().getLandMark():"";  
					
					}
					System.out.println("Address:-"+address);
					mailParameters.put("address", address!=null?address:"NA");
					
					
					Long branchId = null;
					if(!CommonUtils.isObjectNullOrEmpty(proposalresp.get("branch_id"))) {
						branchId = Long.valueOf(proposalresp.get("branch_id").toString());	
					}
	                
					UserResponse userResponse = userClient.getUserDetailByOrgRoleBranchId(orgId,com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_MAKER,branchId);
					List<Map<String, Object>> usersRespList = (List<Map<String, Object>>) userResponse.getListData();
							
					String to = null;
					if(!CommonUtils.isObjectNullOrEmpty(usersRespList)) {
						for (int i = 0; i < usersRespList.size(); i++) {
							UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),
									UsersRequest.class);
							
							String name = null;
							
							try {
								logger.error("Into getting FP Name======>"+userObj);
								UserResponse userResponseForName = userClient.getFPDetails(userObj);
								FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
										FundProviderDetailsRequest.class);
								name = fundProviderDetailsRequest.getFirstName() + " " + (fundProviderDetailsRequest.getLastName() == null ? "": fundProviderDetailsRequest.getLastName());
							} catch (Exception e) {
								logger.error("error while fetching FP name");
								e.printStackTrace();
							}
							
							if(!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
								//System.out.println("Maker ID:---"+userObj.getEmail());
								to = userObj.getEmail();	
								mailParameters.put("maker_name", name!=null?name:"NA");
								
								createNotificationForEmail(to, userId.toString(),
										mailParameters, NotificationAlias.EMAIL_ALL_MAKERS_AFTER_INPRINCIPLE_TO_FS, subject);
							}
							
							if(!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
								//System.out.println("Maker ID:---"+userObj.getEmail());
								Map<String, Object> smsParameters = new HashMap<String, Object>();
								to = userObj.getMobile();	
								smsParameters.put("fs_name", paymentRequest.getNameOfEntity()!=null?paymentRequest.getNameOfEntity():"NA");
								smsParameters.put("product_type", proposalresp.get("loan_type")!=null?proposalresp.get("loan_type").toString():"NA");
								smsParameters.put("url", "www.bitly.com");
								
								sendSMSNotification(userId.toString(),
										smsParameters, NotificationAlias.SMS_ALL_MAKERS_AFTER_INPRINCIPLE_TO_FS, to);
							}
							
							if(!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
								//System.out.println("Maker ID:---"+userObj.getEmail());
								Map<String, Object> sysParameters = new HashMap<String, Object>();
								sysParameters.put("fs_name", paymentRequest.getNameOfEntity()!=null?paymentRequest.getNameOfEntity():"NA");
								
								sendSYSNotification(userId.toString(),
										sysParameters, NotificationAlias.SYS_ALL_MAKERS_AFTER_INPRINCIPLE_TO_FS, userId.toString(), userObj.getId().toString());
							}
							
					    	
						}
						
					}
					else {
						logger.info("No Maker found=================>");
					}
					 	
					
				}catch (Exception e) {
					logger.info("An exception getting while sending mail to all Makers=============>{}");

					e.printStackTrace();
				}
				
			}
			else {
				
				logger.info("Mail to Makers after In-principle to FS is disabled==========>");
			}
		}
		
		//==========================================================================================================
		
	
		// ==================Sending Mail to all Checkers after FS receives In-principle Approval==================
		
			@Async
			public void sendEmailToAllCheckersWhenFSRecievesInPrinciple(Map<String,Object> proposalresp, PaymentRequest paymentRequest, Long userId, Long orgId) {
				if(mailToMakerChecker) {
					
					try {
						
						logger.info("Into sending Mail to all Checkers after FS gets In-Principle Approval===>{}");
						String subject = "Intimation : New Proposal -  Application ID "+paymentRequest.getApplicationId();
						Map<String, Object> mailParameters = new HashMap<String, Object>();
						mailParameters.put("fs_name", paymentRequest.getNameOfEntity()!=null?paymentRequest.getNameOfEntity():"NA");
						mailParameters.put("product_type", proposalresp.get("loan_type")!=null?proposalresp.get("loan_type").toString():"NA");
						mailParameters.put("loan_amount", proposalresp.get("amount")!=null?Double.valueOf(proposalresp.get("amount").toString() ):"NA");
						mailParameters.put("emi_amount", proposalresp.get("emi_amount")!=null?Double.valueOf(proposalresp.get("emi_amount").toString() ):"NA");
						mailParameters.put("interest_rate", proposalresp.get("rate_interest")!=null?Double.valueOf(proposalresp.get("rate_interest").toString() ):"NA");
						mailParameters.put("application_id", paymentRequest.getApplicationId());
						
						UserResponse response = null;
						
						try {
							response = userClient.getEmailMobile(userId);
						}
						catch(Exception e) {
							logger.info("Something went wrong while calling Users client===>{}");
							e.printStackTrace();
						}
						
						if(!CommonUtils.isObjectNullOrEmpty(response)) {
							UsersRequest signUpUser = MultipleJSONObjectHelper
									.getObjectFromMap((Map<String, Object>) response.getData(), UsersRequest.class);

							String mobile = signUpUser.getMobile();
							System.out.println("Mobile no:-"+mobile);
							mailParameters.put("mobile_no", mobile!=null?mobile:"NA");
							
						}
						
						CorporateApplicantRequest applicantRequest = corporateapplicantService.getCorporateApplicant(paymentRequest.getApplicationId());
						String address = null;
						if(!CommonUtils.isObjectNullOrEmpty(applicantRequest) 
								&& !CommonUtils.isObjectNullOrEmpty(applicantRequest.getFirstAddress())){
							address = applicantRequest.getFirstAddress().getPremiseNumber()!=null?applicantRequest.getFirstAddress().getPremiseNumber():""	
		                              +" "+applicantRequest.getFirstAddress().getStreetName()!=null?applicantRequest.getFirstAddress().getStreetName():""
		                              +" "+applicantRequest.getFirstAddress().getLandMark()!=null?applicantRequest.getFirstAddress().getLandMark():"";  
						
						}
						System.out.println("Address:-"+address);
						mailParameters.put("address", address!=null?address:"NA");
						
						
						Long branchId = null;
						if(!CommonUtils.isObjectNullOrEmpty(proposalresp.get("branch_id"))) {
							branchId = Long.valueOf(proposalresp.get("branch_id").toString());	
						}
		                
						UserResponse userResponse = userClient.getUserDetailByOrgRoleBranchId(orgId,com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_CHECKER,branchId);
						List<Map<String, Object>> usersRespList = (List<Map<String, Object>>) userResponse.getListData();
						String to = null;
						if(!CommonUtils.isObjectNullOrEmpty(usersRespList)) {
							for (int i = 0; i < usersRespList.size(); i++) {
									UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),
											UsersRequest.class);
									
									String name = null;
									
									try {
										logger.error("Into getting FP Name======>"+userObj);
										UserResponse userResponseForName = userClient.getFPDetails(userObj);
										FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
												FundProviderDetailsRequest.class);
										name = fundProviderDetailsRequest.getFirstName() + " " + (fundProviderDetailsRequest.getLastName() == null ? "": fundProviderDetailsRequest.getLastName());
									} catch (Exception e) {
										logger.error("error while fetching FP name");
										e.printStackTrace();
									}
									if(!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
									//	System.out.println("Checker ID:---"+userObj.getEmail());
										to = userObj.getEmail();	
										mailParameters.put("checker_name", name!=null?name:"NA");
										
										createNotificationForEmail(to, userId.toString(),
												mailParameters, NotificationAlias.EMAIL_ALL_CHECKERS_AFTER_INPRINCIPLE_TO_FS, subject);
									}
									
									if(!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
										//System.out.println("Maker ID:---"+userObj.getEmail());
										Map<String, Object> smsParameters = new HashMap<String, Object>();
										to = userObj.getMobile();	
										smsParameters.put("checker_name", name!=null?name:"NA");
										smsParameters.put("fs_name", paymentRequest.getNameOfEntity()!=null?paymentRequest.getNameOfEntity():"NA");
										smsParameters.put("product_type", proposalresp.get("loan_type")!=null?proposalresp.get("loan_type").toString():"NA");
										smsParameters.put("url", "www.bitly.com");
										
										sendSMSNotification(userId.toString(),
												smsParameters, NotificationAlias.SMS_ALL_CHECKERS_AFTER_INPRINCIPLE_TO_FS, to);
									}
									
									if(!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
										//System.out.println("Maker ID:---"+userObj.getEmail());
										Map<String, Object> sysParameters = new HashMap<String, Object>();
										sysParameters.put("fs_name", paymentRequest.getNameOfEntity()!=null?paymentRequest.getNameOfEntity():"NA");
										
										sendSYSNotification(userId.toString(),
												sysParameters, NotificationAlias.SYS_ALL_CHECKERS_AFTER_INPRINCIPLE_TO_FS, userId.toString(), userObj.getId().toString());
									}
							    	
								}
							
						}
						else {
							logger.info("No Checker found=================>");
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
			
			// ==================Sending Mail to HO after FS receives In-principle Approval==================
			
				@Async
				public void sendEmailToHOWhenFSRecievesInPrinciple(Map<String,Object> proposalresp, PaymentRequest paymentRequest, Long userId, Long orgId) {
					if(mailToMakerChecker) {
						
						try {
							
							logger.info("Into sending Mail to all Checkers after FS gets In-Principle Approval===>{}");
							String subject = "Intimation : New Proposal -  Application ID "+paymentRequest.getApplicationId();
							Map<String, Object> mailParameters = new HashMap<String, Object>();
							mailParameters.put("fs_name", paymentRequest.getNameOfEntity()!=null?paymentRequest.getNameOfEntity():"NA");
							mailParameters.put("product_type", proposalresp.get("loan_type")!=null?proposalresp.get("loan_type").toString():"NA");
							mailParameters.put("loan_amount", proposalresp.get("amount")!=null?Double.valueOf(proposalresp.get("amount").toString() ):"NA");
							mailParameters.put("emi_amount", proposalresp.get("emi_amount")!=null?Double.valueOf(proposalresp.get("emi_amount").toString() ):"NA");
							mailParameters.put("interest_rate", proposalresp.get("rate_interest")!=null?Double.valueOf(proposalresp.get("rate_interest").toString() ):"NA");
							mailParameters.put("application_id", paymentRequest.getApplicationId());
							
							UserResponse response = null;
							
							try {
								response = userClient.getEmailMobile(userId);
							}
							catch(Exception e) {
								logger.info("Something went wrong while calling Users client===>{}");
								e.printStackTrace();
							}
							
							if(!CommonUtils.isObjectNullOrEmpty(response)) {
								UsersRequest signUpUser = MultipleJSONObjectHelper
										.getObjectFromMap((Map<String, Object>) response.getData(), UsersRequest.class);

								String mobile = signUpUser.getMobile();
								System.out.println("Mobile no:-"+mobile);
								mailParameters.put("mobile_no", mobile!=null?mobile:"NA");
								
							}
							
							CorporateApplicantRequest applicantRequest = corporateapplicantService.getCorporateApplicant(paymentRequest.getApplicationId());
							String address = null;
							if(!CommonUtils.isObjectNullOrEmpty(applicantRequest) 
									&& !CommonUtils.isObjectNullOrEmpty(applicantRequest.getFirstAddress())){
								address = applicantRequest.getFirstAddress().getPremiseNumber()!=null?applicantRequest.getFirstAddress().getPremiseNumber():""	
			                              +" "+applicantRequest.getFirstAddress().getStreetName()!=null?applicantRequest.getFirstAddress().getStreetName():""
			                              +" "+applicantRequest.getFirstAddress().getLandMark()!=null?applicantRequest.getFirstAddress().getLandMark():"";  
							
							}
							System.out.println("Address:-"+address);
							mailParameters.put("address", address!=null?address:"NA");
							
							
							Long branchId = null;
							if(!CommonUtils.isObjectNullOrEmpty(proposalresp.get("branch_id"))) {
								branchId = Long.valueOf(proposalresp.get("branch_id").toString());	
							}
			                
							UserResponse userResponse = userClient.getUserDetailByOrgRoleBranchId(orgId,com.capitaworld.service.users.utils.CommonUtils.UserRoles.HEAD_OFFICER,branchId);
							List<Map<String, Object>> usersRespList = (List<Map<String, Object>>) userResponse.getListData();
							String to = null;
							if(!CommonUtils.isObjectNullOrEmpty(usersRespList)) {
								for (int i = 0; i < usersRespList.size(); i++) {
										UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),
												UsersRequest.class);
										
										String name = null;
										
										try {
											logger.error("Into getting FP Name======>"+userObj);
											UserResponse userResponseForName = userClient.getFPDetails(userObj);
											FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
													FundProviderDetailsRequest.class);
											name = fundProviderDetailsRequest.getFirstName() + " " + (fundProviderDetailsRequest.getLastName() == null ? "": fundProviderDetailsRequest.getLastName());
										} catch (Exception e) {
											logger.error("error while fetching FP name");
											e.printStackTrace();
										}
										
										if(!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
										//	System.out.println("Checker ID:---"+userObj.getEmail());
											to = userObj.getEmail();	
											mailParameters.put("ho_name", name!=null?name:"NA");
											
											createNotificationForEmail(to, userId.toString(),
													mailParameters, NotificationAlias.EMAIL_HO_INPRINCIPLE_TO_FS, subject);
										}
										
										if(!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
											//System.out.println("Maker ID:---"+userObj.getEmail());
											Map<String, Object> smsParameters = new HashMap<String, Object>();
											to = userObj.getMobile();	
											smsParameters.put("fs_name", paymentRequest.getNameOfEntity()!=null?paymentRequest.getNameOfEntity():"NA");
											smsParameters.put("product_type", proposalresp.get("loan_type")!=null?proposalresp.get("loan_type").toString():"NA");
											smsParameters.put("url", "www.bitly.com");
											
											sendSMSNotification(userId.toString(),
													smsParameters, NotificationAlias.SMS_HO_INPRINCIPLE_TO_FS, to);
										}
										
										if(!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
											//System.out.println("Maker ID:---"+userObj.getEmail());
											Map<String, Object> sysParameters = new HashMap<String, Object>();
											sysParameters.put("fs_name", paymentRequest.getNameOfEntity()!=null?paymentRequest.getNameOfEntity():"NA");
											sysParameters.put("product_type", proposalresp.get("loan_type")!=null?proposalresp.get("loan_type").toString():"NA");
																	
											sendSYSNotification(userId.toString(),
													sysParameters, NotificationAlias.SYS_HO_INPRINCIPLE_TO_FS, userId.toString(), userObj.getId().toString());
										}
								    	
									}
								
							}
							else {
								logger.info("No HO found=================>");
							}
							 	
							
						}catch (Exception e) {
							logger.info("An exception getting while sending mail to HO=============>{}");

							e.printStackTrace();
						}
						
					}
					else {
						
						logger.info("Mail to HO after In-principle to FS is disabled==========>");
					}
				};
				
				//==========================================================================================================
				
				// ==================Sending Mail to BO after FS receives In-principle Approval==================
				
				@Async
				public void sendEmailToAllBOWhenFSRecievesInPrinciple(Map<String,Object> proposalresp, PaymentRequest paymentRequest, Long userId, Long orgId) {
					if(mailToMakerChecker) {
						
						try {
							
							logger.info("Into sending Mail to all BO after FS gets In-Principle Approval===>{}");
							String subject = "Intimation : New Proposal -  Application ID "+paymentRequest.getApplicationId();
							Map<String, Object> mailParameters = new HashMap<String, Object>();
							mailParameters.put("fs_name", paymentRequest.getNameOfEntity()!=null?paymentRequest.getNameOfEntity():"NA");
							mailParameters.put("product_type", proposalresp.get("loan_type")!=null?proposalresp.get("loan_type").toString():"NA");
							mailParameters.put("loan_amount", proposalresp.get("amount")!=null?Double.valueOf(proposalresp.get("amount").toString() ):"NA");
							mailParameters.put("emi_amount", proposalresp.get("emi_amount")!=null?Double.valueOf(proposalresp.get("emi_amount").toString() ):"NA");
							mailParameters.put("interest_rate", proposalresp.get("rate_interest")!=null?Double.valueOf(proposalresp.get("rate_interest").toString() ):"NA");
							mailParameters.put("application_id", paymentRequest.getApplicationId());
							
							UserResponse response = null;
							
							try {
								response = userClient.getEmailMobile(userId);
							}
							catch(Exception e) {
								logger.info("Something went wrong while calling Users client===>{}");
								e.printStackTrace();
							}
							
							if(!CommonUtils.isObjectNullOrEmpty(response)) {
								UsersRequest signUpUser = MultipleJSONObjectHelper
										.getObjectFromMap((Map<String, Object>) response.getData(), UsersRequest.class);

								String mobile = signUpUser.getMobile();
								System.out.println("Mobile no:-"+mobile);
								mailParameters.put("mobile_no", mobile!=null?mobile:"NA");
								
							}
							
							CorporateApplicantRequest applicantRequest = corporateapplicantService.getCorporateApplicant(paymentRequest.getApplicationId());
							String address = null;
							if(!CommonUtils.isObjectNullOrEmpty(applicantRequest) 
									&& !CommonUtils.isObjectNullOrEmpty(applicantRequest.getFirstAddress())){
								address = applicantRequest.getFirstAddress().getPremiseNumber()!=null?applicantRequest.getFirstAddress().getPremiseNumber():""	
			                              +" "+applicantRequest.getFirstAddress().getStreetName()!=null?applicantRequest.getFirstAddress().getStreetName():""
			                              +" "+applicantRequest.getFirstAddress().getLandMark()!=null?applicantRequest.getFirstAddress().getLandMark():"";  
							
							}
							System.out.println("Address:-"+address);
							mailParameters.put("address", address!=null?address:"NA");
							
							
							Long branchId = null;
							if(!CommonUtils.isObjectNullOrEmpty(proposalresp.get("branch_id"))) {
								branchId = Long.valueOf(proposalresp.get("branch_id").toString());	
							}
			                
							UserResponse userResponse = userClient.getUserDetailByOrgRoleBranchId(orgId,com.capitaworld.service.users.utils.CommonUtils.UserRoles.BRANCH_OFFICER,branchId);
							List<Map<String, Object>> usersRespList = (List<Map<String, Object>>) userResponse.getListData();
							String to = null;
							if(!CommonUtils.isObjectNullOrEmpty(usersRespList)) {
								for (int i = 0; i < usersRespList.size(); i++) {
										UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),
												UsersRequest.class);
										
                                        String name = null;
										
										try {
											logger.error("Into getting FP Name======>"+userObj);
											UserResponse userResponseForName = userClient.getFPDetails(userObj);
											FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
													FundProviderDetailsRequest.class);
											name = fundProviderDetailsRequest.getFirstName() + " " + (fundProviderDetailsRequest.getLastName() == null ? "": fundProviderDetailsRequest.getLastName());
										} catch (Exception e) {
											logger.error("error while fetching FP name");
											e.printStackTrace();
										}
										
										if(!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
										//	System.out.println("Checker ID:---"+userObj.getEmail());
											to = userObj.getEmail();	
											mailParameters.put("bo_name", name!=null?name:"NA");
											
											createNotificationForEmail(to, userId.toString(),
													mailParameters, NotificationAlias.EMAIL_ALL_BO_INPRINCIPLE_TO_FS, subject);
										}
										
										if(!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
											//System.out.println("Maker ID:---"+userObj.getEmail());
											Map<String, Object> smsParameters = new HashMap<String, Object>();
											to = userObj.getMobile();	
											smsParameters.put("fs_name", paymentRequest.getNameOfEntity()!=null?paymentRequest.getNameOfEntity():"NA");
											smsParameters.put("product_type", proposalresp.get("loan_type")!=null?proposalresp.get("loan_type").toString():"NA");
											smsParameters.put("url", "www.bitly.com");
											
											sendSMSNotification(userId.toString(),
													smsParameters, NotificationAlias.SMS_ALL_BO_INPRINCIPLE_TO_FS, to);
										}
										
										if(!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
											//System.out.println("Maker ID:---"+userObj.getEmail());
											Map<String, Object> sysParameters = new HashMap<String, Object>();
											sysParameters.put("fs_name", paymentRequest.getNameOfEntity()!=null?paymentRequest.getNameOfEntity():"NA");
											sysParameters.put("product_type", proposalresp.get("loan_type")!=null?proposalresp.get("loan_type").toString():"NA");
																	
											sendSYSNotification(userId.toString(),
													sysParameters, NotificationAlias.SYS_ALL_BO_INPRINCIPLE_TO_FS, userId.toString(), userObj.getId().toString());
										}
								    	
									}
								
							}
							else {
								logger.info("No BO found=================>");
							}
							 	
							
						}catch (Exception e) {
							logger.info("An exception getting while sending mail to BO=============>{}");

							e.printStackTrace();
						}
						
					}
					else {
						
						logger.info("Mail to BO after In-principle to FS is disabled==========>");
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
		
		private void sendSMSNotification(String userId, Map<String, Object> parameters, Long templateId, String... to)
				throws NotificationException {
			// String to[] = {toNo};
			logger.info("Inside send SMS===>{}");
			NotificationRequest req = new NotificationRequest();
			req.setClientRefId(userId);
			Notification notification = new Notification();
			notification.setContentType(ContentType.TEMPLATE);
			notification.setTemplateId(templateId);
			notification.setTo(to);
			notification.setType(NotificationType.SMS);
			notification.setParameters(parameters);
			req.addNotification(notification);

			sendEmail(req);
			logger.info("Outside send SMS===>{}");

		}
		
		private void sendSYSNotification(String toUserId, Map<String, Object> parameters, Long templateId,
				String fromId, String... to) throws NotificationException {
			// String to[] = {toNo};
			logger.info("Inside send SYSTEM notification===>{}");
			NotificationRequest req = new NotificationRequest();
			req.setClientRefId(toUserId);
			Notification notification = new Notification();
			notification.setContentType(ContentType.TEMPLATE);
			notification.setTemplateId(templateId);
			notification.setTo(to);
			notification.setType(NotificationType.SYSTEM);
			notification.setParameters(parameters);
			notification.setFrom(fromId);
			req.addNotification(notification);

			sendEmail(req);
			logger.info("Outside send SYSTEM notification===>{}");

		}

		private void sendEmail(NotificationRequest notificationRequest) throws NotificationException {
			logger.info("Inside send Email===>{}");
			notificationClient.send(notificationRequest);
			logger.info("Outside send Email===>{}");
		}
	
}
