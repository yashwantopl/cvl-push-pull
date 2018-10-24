package com.capitaworld.service.loans.config;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.capitaworld.service.gst.client.GstClient;
import com.capitaworld.service.loans.domain.fundprovider.ProductMasterTemp;
import com.capitaworld.service.loans.domain.sanction.LoanSanctionDomain;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.NhbsApplicationRequest;
import com.capitaworld.service.loans.model.PaymentRequest;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.capitaworld.service.loans.service.fundseeker.corporate.DirectorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.matchengine.model.ProposalMappingResponse;
import com.capitaworld.service.notification.client.NotificationClient;
import com.capitaworld.service.notification.exceptions.NotificationException;
import com.capitaworld.service.notification.model.Notification;
import com.capitaworld.service.notification.model.NotificationRequest;
import com.capitaworld.service.notification.utils.ContentType;
import com.capitaworld.service.notification.utils.NotificationAlias;
import com.capitaworld.service.notification.utils.NotificationType;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
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

	@Autowired
	private LoanApplicationService loanApplicationService;

	@Autowired
	private ProposalDetailsClient proposalDetailsClient;

	@Autowired
	private OneFormClient oneFormClient;

	@Autowired
	private DirectorBackgroundDetailsService directorBackgroundDetailsService;

	@Autowired
	public CorporateApplicantService corporateApplicantService;

	@Autowired
	public GstClient gstClient;

	private static final String EMAIL_ADDRESS_FROM = "no-reply@capitaworld.com";

	@Value("${capitaworld.sidbi.mail.to.maker.checker}")
	private Boolean mailToMakerChecker;

	// ==================Sending Mail to all Makers after FS receives In-principle
	// Approval==================

	@Async
	public void sendEmailToAllMakersWhenFSRecievesInPrinciple(Map<String, Object> proposalresp,
			PaymentRequest paymentRequest, Long userId, Long orgId) {
		if (mailToMakerChecker) {

			try {

				logger.info("Into sending Mail to all Makers after FS gets In-Principle Approval===>{}");
				String subject = "Intimation : New Proposal -  Application ID " + paymentRequest.getApplicationId();
				Map<String, Object> mailParameters = new HashMap<String, Object>();
				mailParameters.put("fs_name",
						paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
				mailParameters.put("product_type",
						proposalresp.get("loan_type") != null ? proposalresp.get("loan_type").toString() : "NA");
				mailParameters.put("loan_amount",
						proposalresp.get("amount") != null ? Double.valueOf(proposalresp.get("amount").toString())
								: "NA");
				mailParameters.put("emi_amount",
						proposalresp.get("emi_amount") != null
								? Double.valueOf(proposalresp.get("emi_amount").toString())
								: "NA");
				mailParameters.put("interest_rate",
						proposalresp.get("rate_interest") != null
								? Double.valueOf(proposalresp.get("rate_interest").toString())
								: "NA");
				mailParameters.put("application_id",
						paymentRequest.getApplicationId() != null ? paymentRequest.getApplicationId().toString()
								: "NA");

				UserResponse response = null;

				try {
					response = userClient.getEmailMobile(userId);
				} catch (Exception e) {
					logger.info("Something went wrong while calling Users client===>{}");
					e.printStackTrace();
				}

				if (!CommonUtils.isObjectNullOrEmpty(response)) {
					UsersRequest signUpUser = MultipleJSONObjectHelper
							.getObjectFromMap((Map<String, Object>) response.getData(), UsersRequest.class);

					String mobile = signUpUser.getMobile();
					System.out.println("Mobile no:-" + mobile);
					mailParameters.put("mobile_no", mobile != null ? mobile : "NA");

				}

				LoanApplicationRequest applicationRequest = loanApplicationService
						.getFromClient(paymentRequest.getApplicationId());
				String address = null;
				if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
						&& applicationRequest.getBusinessTypeId() == 1){
					CorporateApplicantRequest applicantRequest = corporateapplicantService
							.getCorporateApplicant(paymentRequest.getApplicationId());
					if (!CommonUtils.isObjectNullOrEmpty(applicantRequest)
							&& !CommonUtils.isObjectNullOrEmpty(applicantRequest.getFirstAddress())) {
						String premiseNumber = null;
						String streetName = null;
						String landMark = null;
						premiseNumber = applicantRequest.getFirstAddress().getPremiseNumber()!=null?applicantRequest.getFirstAddress().getPremiseNumber():"";
						streetName = applicantRequest.getFirstAddress().getStreetName()!=null?applicantRequest.getFirstAddress().getStreetName():"";
						landMark = applicantRequest.getFirstAddress().getLandMark()!=null?applicantRequest.getFirstAddress().getLandMark():"";
						address = premiseNumber.toString()+" "+streetName.toString()+" "+landMark.toString();
					}	
				}
				else{
					
					// For getting Address of Primary Director
					// =========================================================================================================
					List<DirectorBackgroundDetailRequest> NTBResponse = null;
					if (applicationRequest.getBusinessTypeId() == 2) {
						NTBResponse = directorBackgroundDetailsService
								.getDirectorBasicDetailsListForNTB(paymentRequest.getApplicationId());
						if (!CommonUtils.isObjectNullOrEmpty(NTBResponse)) {
							int isMainDirector = 0;
							for (DirectorBackgroundDetailRequest director : NTBResponse) {
								if (!CommonUtils.isObjectNullOrEmpty(director) && director.getIsMainDirector()) {
									address = director.getAddress() != null ? director.getAddress() : "NA";
									isMainDirector = 1;
								}
							}
							if (isMainDirector == 0) {
								address = "NA";
							}
						} else {
							address = "NA";
						}
					} else {
						address = "NA";
					}
					// =========================================================================================================

				}
				
				mailParameters.put("address", address != null ? address : "NA");

				Long branchId = null;
				if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get("branch_id"))) {
					branchId = Long.valueOf(proposalresp.get("branch_id").toString());
				}

				UserResponse userResponse = userClient.getUserDetailByOrgRoleBranchId(orgId,
						com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_MAKER, branchId);
				List<Map<String, Object>> usersRespList = (List<Map<String, Object>>) userResponse.getListData();

				String to = null;
				if (!CommonUtils.isObjectNullOrEmpty(usersRespList)) {
					for (int i = 0; i < usersRespList.size(); i++) {
						UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),
								UsersRequest.class);

						String name = null;

						try {
							logger.error("Into getting FP Name======>" + userObj);
							UserResponse userResponseForName = userClient.getFPDetails(userObj);
							FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
									.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
											FundProviderDetailsRequest.class);
							name = fundProviderDetailsRequest.getFirstName() + " "
									+ (fundProviderDetailsRequest.getLastName() == null ? ""
											: fundProviderDetailsRequest.getLastName());
						} catch (Exception e) {
							logger.error("error while fetching FP name");
							e.printStackTrace();
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
							// System.out.println("Maker ID:---"+userObj.getEmail());
							to = userObj.getEmail();

							if ("null ".equals(name)) {
								mailParameters.put("maker_name", "Sir/Madam");
							} else {
								mailParameters.put("maker_name", name != null ? name : "Sir/Madam");
							}
							createNotificationForEmail(to, userId.toString(), mailParameters,
									NotificationAlias.EMAIL_ALL_MAKERS_AFTER_INPRINCIPLE_TO_FS, subject);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
							// System.out.println("Maker ID:---"+userObj.getEmail());
							Map<String, Object> smsParameters = new HashMap<String, Object>();
							to = "91" + userObj.getMobile();
							smsParameters.put("fs_name",
									paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
							smsParameters.put("product_type",
									proposalresp.get("loan_type") != null ? proposalresp.get("loan_type").toString()
											: "NA");
							smsParameters.put("url", "www.bitly.com");

							sendSMSNotification(userId.toString(), smsParameters,
									NotificationAlias.SMS_ALL_MAKERS_AFTER_INPRINCIPLE_TO_FS, to);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
							// System.out.println("Maker ID:---"+userObj.getEmail());
							Map<String, Object> sysParameters = new HashMap<String, Object>();
							sysParameters.put("fs_name",
									paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");

							sendSYSNotification(paymentRequest.getApplicationId(), userObj.getId().toString(),
									sysParameters, NotificationAlias.SYS_ALL_MAKERS_AFTER_INPRINCIPLE_TO_FS,
									userObj.getId().toString(), userObj.getId().toString());
						}

					}

				} else {
					logger.info("No Maker found=================>");
				}

			} catch (Exception e) {
				logger.info("An exception getting while sending mail to all Makers=============>{}");

				e.printStackTrace();
			}

		} else {

			logger.info("Mail to Makers after In-principle to FS is disabled==========>");
		}
	}

	// ==========================================================================================================

	// ==================Sending Mail to all Checkers after FS receives In-principle
	// Approval==================

	@Async
	public void sendEmailToAllCheckersWhenFSRecievesInPrinciple(Map<String, Object> proposalresp,
			PaymentRequest paymentRequest, Long userId, Long orgId) {
		if (mailToMakerChecker) {

			try {

				logger.info("Into sending Mail to all Checkers after FS gets In-Principle Approval===>{}");
				String subject = "Intimation : New Proposal -  Application ID " + paymentRequest.getApplicationId();
				Map<String, Object> mailParameters = new HashMap<String, Object>();
				mailParameters.put("fs_name",
						paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
				mailParameters.put("product_type",
						proposalresp.get("loan_type") != null ? proposalresp.get("loan_type").toString() : "NA");
				mailParameters.put("loan_amount",
						proposalresp.get("amount") != null ? Double.valueOf(proposalresp.get("amount").toString())
								: "NA");
				mailParameters.put("emi_amount",
						proposalresp.get("emi_amount") != null
								? Double.valueOf(proposalresp.get("emi_amount").toString())
								: "NA");
				mailParameters.put("interest_rate",
						proposalresp.get("rate_interest") != null
								? Double.valueOf(proposalresp.get("rate_interest").toString())
								: "NA");
				mailParameters.put("application_id", paymentRequest.getApplicationId());

				UserResponse response = null;

				try {
					response = userClient.getEmailMobile(userId);
				} catch (Exception e) {
					logger.info("Something went wrong while calling Users client===>{}");
					e.printStackTrace();
				}

				if (!CommonUtils.isObjectNullOrEmpty(response)) {
					UsersRequest signUpUser = MultipleJSONObjectHelper
							.getObjectFromMap((Map<String, Object>) response.getData(), UsersRequest.class);

					String mobile = signUpUser.getMobile();
					System.out.println("Mobile no:-" + mobile);
					mailParameters.put("mobile_no", mobile != null ? mobile : "NA");

				}

				LoanApplicationRequest applicationRequest = loanApplicationService
						.getFromClient(paymentRequest.getApplicationId());
				String address = null;
				if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
						&& applicationRequest.getBusinessTypeId() == 1){
					CorporateApplicantRequest applicantRequest = corporateapplicantService
							.getCorporateApplicant(paymentRequest.getApplicationId());
					if (!CommonUtils.isObjectNullOrEmpty(applicantRequest)
							&& !CommonUtils.isObjectNullOrEmpty(applicantRequest.getFirstAddress())) {
						String premiseNumber = null;
						String streetName = null;
						String landMark = null;
						premiseNumber = applicantRequest.getFirstAddress().getPremiseNumber()!=null?applicantRequest.getFirstAddress().getPremiseNumber():"";
						streetName = applicantRequest.getFirstAddress().getStreetName()!=null?applicantRequest.getFirstAddress().getStreetName():"";
						landMark = applicantRequest.getFirstAddress().getLandMark()!=null?applicantRequest.getFirstAddress().getLandMark():"";
						address = premiseNumber.toString()+" "+streetName.toString()+" "+landMark.toString();
					}	
				}
				else{
					
					// For getting Address of Primary Director
					// =========================================================================================================
					List<DirectorBackgroundDetailRequest> NTBResponse = null;
					if (applicationRequest.getBusinessTypeId() == 2) {
						NTBResponse = directorBackgroundDetailsService
								.getDirectorBasicDetailsListForNTB(paymentRequest.getApplicationId());
						if (!CommonUtils.isObjectNullOrEmpty(NTBResponse)) {
							int isMainDirector = 0;
							for (DirectorBackgroundDetailRequest director : NTBResponse) {
								if (!CommonUtils.isObjectNullOrEmpty(director) && director.getIsMainDirector()) {
									address = director.getAddress() != null ? director.getAddress() : "NA";
									isMainDirector = 1;
								}
							}
							if (isMainDirector == 0) {
								address = "NA";
							}
						} else {
							address = "NA";
						}
					} else {
						address = "NA";
					}
					// =========================================================================================================

				}
				mailParameters.put("address", address != null ? address : "NA");

				Long branchId = null;
				if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get("branch_id"))) {
					branchId = Long.valueOf(proposalresp.get("branch_id").toString());
				}

				UserResponse userResponse = userClient.getUserDetailByOrgRoleBranchId(orgId,
						com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_CHECKER, branchId);
				List<Map<String, Object>> usersRespList = (List<Map<String, Object>>) userResponse.getListData();
				String to = null;
				if (!CommonUtils.isObjectNullOrEmpty(usersRespList)) {
					for (int i = 0; i < usersRespList.size(); i++) {
						UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),
								UsersRequest.class);

						String name = null;

						try {
							logger.error("Into getting FP Name======>" + userObj);
							UserResponse userResponseForName = userClient.getFPDetails(userObj);
							FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
									.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
											FundProviderDetailsRequest.class);
							name = fundProviderDetailsRequest.getFirstName() + " "
									+ (fundProviderDetailsRequest.getLastName() == null ? ""
											: fundProviderDetailsRequest.getLastName());
						} catch (Exception e) {
							logger.error("error while fetching FP name");
							e.printStackTrace();
						}
						if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
							// System.out.println("Checker ID:---"+userObj.getEmail());
							to = userObj.getEmail();
							if ("null ".equals(name)) {
								mailParameters.put("checker_name", "Sir/Madam");
							} else {
								mailParameters.put("checker_name", name != null ? name : "Sir/Madam");
							}

							createNotificationForEmail(to, userId.toString(), mailParameters,
									NotificationAlias.EMAIL_ALL_CHECKERS_AFTER_INPRINCIPLE_TO_FS, subject);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
							// System.out.println("Maker ID:---"+userObj.getEmail());
							Map<String, Object> smsParameters = new HashMap<String, Object>();
							to = "91" + userObj.getMobile();
							if ("null ".equals(name)) {
								smsParameters.put("checker_name", "Sir/Madam");
							} else {
								smsParameters.put("checker_name", name != null ? name : "Sir/Madam");
							}
							smsParameters.put("fs_name",
									paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
							smsParameters.put("product_type",
									proposalresp.get("loan_type") != null ? proposalresp.get("loan_type").toString()
											: "NA");
							smsParameters.put("url", "www.bitly.com");

							sendSMSNotification(userId.toString(), smsParameters,
									NotificationAlias.SMS_ALL_CHECKERS_AFTER_INPRINCIPLE_TO_FS, to);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
							// System.out.println("Maker ID:---"+userObj.getEmail());
							Map<String, Object> sysParameters = new HashMap<String, Object>();
							sysParameters.put("fs_name",
									paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");

							sendSYSNotification(paymentRequest.getApplicationId(), userObj.getId().toString(),
									sysParameters, NotificationAlias.SYS_ALL_CHECKERS_AFTER_INPRINCIPLE_TO_FS,
									userObj.getId().toString(), userObj.getId().toString());
						}

					}

				} else {
					logger.info("No Checker found=================>");
				}

			} catch (Exception e) {
				logger.info("An exception getting while sending mail to all Checkers=============>{}");

				e.printStackTrace();
			}

		} else {

			logger.info("Mail to Checkers after In-principle to FS is disabled==========>");
		}
	};

	// ==========================================================================================================

	// ==================Sending Mail to HO after FS receives In-principle
	// Approval==================

	@Async
	public void sendEmailToHOWhenFSRecievesInPrinciple(Map<String, Object> proposalresp, PaymentRequest paymentRequest,
			Long userId, Long orgId) {
		if (mailToMakerChecker) {

			try {

				logger.info("Into sending Mail to all Checkers after FS gets In-Principle Approval===>{}");
				String subject = "Intimation : New Proposal -  Application ID " + paymentRequest.getApplicationId();
				Map<String, Object> mailParameters = new HashMap<String, Object>();
				mailParameters.put("fs_name",
						paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
				mailParameters.put("product_type",
						proposalresp.get("loan_type") != null ? proposalresp.get("loan_type").toString() : "NA");
				mailParameters.put("loan_amount",
						proposalresp.get("amount") != null ? Double.valueOf(proposalresp.get("amount").toString())
								: "NA");
				mailParameters.put("emi_amount",
						proposalresp.get("emi_amount") != null
								? Double.valueOf(proposalresp.get("emi_amount").toString())
								: "NA");
				mailParameters.put("interest_rate",
						proposalresp.get("rate_interest") != null
								? Double.valueOf(proposalresp.get("rate_interest").toString())
								: "NA");
				mailParameters.put("application_id",
						paymentRequest.getApplicationId() != null ? paymentRequest.getApplicationId().toString()
								: "NA");

				UserResponse response = null;

				try {
					response = userClient.getEmailMobile(userId);
				} catch (Exception e) {
					logger.info("Something went wrong while calling Users client===>{}");
					e.printStackTrace();
				}

				if (!CommonUtils.isObjectNullOrEmpty(response)) {
					UsersRequest signUpUser = MultipleJSONObjectHelper
							.getObjectFromMap((Map<String, Object>) response.getData(), UsersRequest.class);

					String mobile = signUpUser.getMobile();
					System.out.println("Mobile no:-" + mobile);
					mailParameters.put("mobile_no", mobile != null ? mobile : "NA");

				}

				LoanApplicationRequest applicationRequest = loanApplicationService
						.getFromClient(paymentRequest.getApplicationId());
				String address = null;
				if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
						&& applicationRequest.getBusinessTypeId() == 1){
					CorporateApplicantRequest applicantRequest = corporateapplicantService
							.getCorporateApplicant(paymentRequest.getApplicationId());
					if (!CommonUtils.isObjectNullOrEmpty(applicantRequest)
							&& !CommonUtils.isObjectNullOrEmpty(applicantRequest.getFirstAddress())) {
						String premiseNumber = null;
						String streetName = null;
						String landMark = null;
						premiseNumber = applicantRequest.getFirstAddress().getPremiseNumber()!=null?applicantRequest.getFirstAddress().getPremiseNumber():"";
						streetName = applicantRequest.getFirstAddress().getStreetName()!=null?applicantRequest.getFirstAddress().getStreetName():"";
						landMark = applicantRequest.getFirstAddress().getLandMark()!=null?applicantRequest.getFirstAddress().getLandMark():"";
						address = premiseNumber.toString()+" "+streetName.toString()+" "+landMark.toString();
					}	
				}
				else{
					
					// For getting Address of Primary Director
					// =========================================================================================================
					List<DirectorBackgroundDetailRequest> NTBResponse = null;
					if (applicationRequest.getBusinessTypeId() == 2) {
						NTBResponse = directorBackgroundDetailsService
								.getDirectorBasicDetailsListForNTB(paymentRequest.getApplicationId());
						if (!CommonUtils.isObjectNullOrEmpty(NTBResponse)) {
							int isMainDirector = 0;
							for (DirectorBackgroundDetailRequest director : NTBResponse) {
								if (!CommonUtils.isObjectNullOrEmpty(director) && director.getIsMainDirector()) {
									address = director.getAddress() != null ? director.getAddress() : "NA";
									isMainDirector = 1;
								}
							}
							if (isMainDirector == 0) {
								address = "NA";
							}
						} else {
							address = "NA";
						}
					} else {
						address = "NA";
					}
					// =========================================================================================================

				}
				mailParameters.put("address", address != null ? address : "NA");

				Long branchId = null;
				if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get("branch_id"))) {
					branchId = Long.valueOf(proposalresp.get("branch_id").toString());
				}

				UserResponse userResponse = userClient.getUserDetailByOrgRoleBranchId(orgId,
						com.capitaworld.service.users.utils.CommonUtils.UserRoles.HEAD_OFFICER, branchId);
				List<Map<String, Object>> usersRespList = (List<Map<String, Object>>) userResponse.getListData();
				String to = null;
				if (!CommonUtils.isObjectNullOrEmpty(usersRespList)) {
					for (int i = 0; i < usersRespList.size(); i++) {
						UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),
								UsersRequest.class);

						String name = null;

						try {
							logger.error("Into getting FP Name======>" + userObj);
							UserResponse userResponseForName = userClient.getFPDetails(userObj);
							FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
									.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
											FundProviderDetailsRequest.class);
							name = fundProviderDetailsRequest.getFirstName() + " "
									+ (fundProviderDetailsRequest.getLastName() == null ? ""
											: fundProviderDetailsRequest.getLastName());
						} catch (Exception e) {
							logger.error("error while fetching FP name");
							e.printStackTrace();
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
							// System.out.println("Checker ID:---"+userObj.getEmail());
							to = userObj.getEmail();
							if ("null ".equals(name)) {
								mailParameters.put("ho_name", "Sir/Madam");
							} else {
								mailParameters.put("ho_name", name != null ? name : "Sir/Madam");
							}

							createNotificationForEmail(to, userId.toString(), mailParameters,
									NotificationAlias.EMAIL_HO_INPRINCIPLE_TO_FS, subject);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
							// System.out.println("Maker ID:---"+userObj.getEmail());
							Map<String, Object> smsParameters = new HashMap<String, Object>();
							to = "91" + userObj.getMobile();
							smsParameters.put("fs_name",
									paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
							smsParameters.put("product_type",
									proposalresp.get("loan_type") != null ? proposalresp.get("loan_type").toString()
											: "NA");
							smsParameters.put("url", "www.bitly.com");

							sendSMSNotification(userId.toString(), smsParameters,
									NotificationAlias.SMS_HO_INPRINCIPLE_TO_FS, to);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
							// System.out.println("Maker ID:---"+userObj.getEmail());
							Map<String, Object> sysParameters = new HashMap<String, Object>();
							sysParameters.put("fs_name",
									paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
							sysParameters.put("product_type",
									proposalresp.get("loan_type") != null ? proposalresp.get("loan_type").toString()
											: "NA");

							sendSYSNotification(paymentRequest.getApplicationId(), userObj.getId().toString(),
									sysParameters, NotificationAlias.SYS_HO_INPRINCIPLE_TO_FS,
									userObj.getId().toString(), userObj.getId().toString());
						}

					}

				} else {
					logger.info("No HO found=================>");
				}

			} catch (Exception e) {
				logger.info("An exception getting while sending mail to HO=============>{}");

				e.printStackTrace();
			}

		} else {

			logger.info("Mail to HO after In-principle to FS is disabled==========>");
		}
	};

	// ==========================================================================================================

	// ==================Sending Mail to BO after FS receives In-principle
	// Approval==================

	@Async
	public void sendEmailToAllBOWhenFSRecievesInPrinciple(Map<String, Object> proposalresp,
			PaymentRequest paymentRequest, Long userId, Long orgId) {
		if (mailToMakerChecker) {

			try {

				logger.info("Into sending Mail to all BO after FS gets In-Principle Approval===>{}");
				String subject = "Intimation : New Proposal -  Application ID " + paymentRequest.getApplicationId();
				Map<String, Object> mailParameters = new HashMap<String, Object>();
				mailParameters.put("fs_name",
						paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
				mailParameters.put("product_type",
						proposalresp.get("loan_type") != null ? proposalresp.get("loan_type").toString() : "NA");
				mailParameters.put("loan_amount",
						proposalresp.get("amount") != null ? Double.valueOf(proposalresp.get("amount").toString())
								: "NA");
				mailParameters.put("emi_amount",
						proposalresp.get("emi_amount") != null
								? Double.valueOf(proposalresp.get("emi_amount").toString())
								: "NA");
				mailParameters.put("interest_rate",
						proposalresp.get("rate_interest") != null
								? Double.valueOf(proposalresp.get("rate_interest").toString())
								: "NA");
				mailParameters.put("application_id",
						paymentRequest.getApplicationId() != null ? paymentRequest.getApplicationId().toString()
								: "NA");

				UserResponse response = null;

				try {
					response = userClient.getEmailMobile(userId);
				} catch (Exception e) {
					logger.info("Something went wrong while calling Users client===>{}");
					e.printStackTrace();
				}

				if (!CommonUtils.isObjectNullOrEmpty(response)) {
					UsersRequest signUpUser = MultipleJSONObjectHelper
							.getObjectFromMap((Map<String, Object>) response.getData(), UsersRequest.class);

					String mobile = signUpUser.getMobile();
					System.out.println("Mobile no:-" + mobile);
					mailParameters.put("mobile_no", mobile != null ? mobile : "NA");

				}

				LoanApplicationRequest applicationRequest = loanApplicationService
						.getFromClient(paymentRequest.getApplicationId());
				String address = null;
				if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
						&& applicationRequest.getBusinessTypeId() == 1){
					CorporateApplicantRequest applicantRequest = corporateapplicantService
							.getCorporateApplicant(paymentRequest.getApplicationId());
					if (!CommonUtils.isObjectNullOrEmpty(applicantRequest)
							&& !CommonUtils.isObjectNullOrEmpty(applicantRequest.getFirstAddress())) {
						String premiseNumber = null;
						String streetName = null;
						String landMark = null;
						premiseNumber = applicantRequest.getFirstAddress().getPremiseNumber()!=null?applicantRequest.getFirstAddress().getPremiseNumber():"";
						streetName = applicantRequest.getFirstAddress().getStreetName()!=null?applicantRequest.getFirstAddress().getStreetName():"";
						landMark = applicantRequest.getFirstAddress().getLandMark()!=null?applicantRequest.getFirstAddress().getLandMark():"";
						address = premiseNumber.toString()+" "+streetName.toString()+" "+landMark.toString();
					}	
				}
				else{
					
					// For getting Address of Primary Director
					// =========================================================================================================
					List<DirectorBackgroundDetailRequest> NTBResponse = null;
					if (applicationRequest.getBusinessTypeId() == 2) {
						NTBResponse = directorBackgroundDetailsService
								.getDirectorBasicDetailsListForNTB(paymentRequest.getApplicationId());
						if (!CommonUtils.isObjectNullOrEmpty(NTBResponse)) {
							int isMainDirector = 0;
							for (DirectorBackgroundDetailRequest director : NTBResponse) {
								if (!CommonUtils.isObjectNullOrEmpty(director) && director.getIsMainDirector()) {
									address = director.getAddress() != null ? director.getAddress() : "NA";
									isMainDirector = 1;
								}
							}
							if (isMainDirector == 0) {
								address = "NA";
							}
						} else {
							address = "NA";
						}
					} else {
						address = "NA";
					}
					// =========================================================================================================

				}
				mailParameters.put("address", address != null ? address : "NA");

				Long branchId = null;
				if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get("branch_id"))) {
					branchId = Long.valueOf(proposalresp.get("branch_id").toString());
				}

				UserResponse userResponse = userClient.getUserDetailByOrgRoleBranchId(orgId,
						com.capitaworld.service.users.utils.CommonUtils.UserRoles.BRANCH_OFFICER, branchId);
				List<Map<String, Object>> usersRespList = (List<Map<String, Object>>) userResponse.getListData();
				String to = null;
				if (!CommonUtils.isObjectNullOrEmpty(usersRespList)) {
					for (int i = 0; i < usersRespList.size(); i++) {
						UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),
								UsersRequest.class);

						String name = null;

						try {
							logger.error("Into getting FP Name======>" + userObj);
							UserResponse userResponseForName = userClient.getFPDetails(userObj);
							FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
									.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
											FundProviderDetailsRequest.class);
							name = fundProviderDetailsRequest.getFirstName() + " "
									+ (fundProviderDetailsRequest.getLastName() == null ? ""
											: fundProviderDetailsRequest.getLastName());
						} catch (Exception e) {
							logger.error("error while fetching FP name");
							e.printStackTrace();
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
							// System.out.println("Checker ID:---"+userObj.getEmail());
							to = userObj.getEmail();
							if ("null ".equals(name)) {
								mailParameters.put("bo_name", "Sir/Madam");
							} else {
								mailParameters.put("bo_name", name != null ? name : "Sir/Madam");
							}

							createNotificationForEmail(to, userId.toString(), mailParameters,
									NotificationAlias.EMAIL_ALL_BO_INPRINCIPLE_TO_FS, subject);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
							// System.out.println("Maker ID:---"+userObj.getEmail());
							Map<String, Object> smsParameters = new HashMap<String, Object>();
							to = "91" + userObj.getMobile();
							smsParameters.put("fs_name",
									paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
							smsParameters.put("product_type",
									proposalresp.get("loan_type") != null ? proposalresp.get("loan_type").toString()
											: "NA");
							smsParameters.put("url", "www.bitly.com");

							sendSMSNotification(userId.toString(), smsParameters,
									NotificationAlias.SMS_ALL_BO_INPRINCIPLE_TO_FS, to);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
							// System.out.println("Maker ID:---"+userObj.getEmail());
							Map<String, Object> sysParameters = new HashMap<String, Object>();
							sysParameters.put("fs_name",
									paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
							sysParameters.put("product_type",
									proposalresp.get("loan_type") != null ? proposalresp.get("loan_type").toString()
											: "NA");

							sendSYSNotification(paymentRequest.getApplicationId(), userObj.getId().toString(),
									sysParameters, NotificationAlias.SYS_ALL_BO_INPRINCIPLE_TO_FS,
									userObj.getId().toString(), userObj.getId().toString());
						}

					}

				} else {
					logger.info("No BO found=================>");
				}

			} catch (Exception e) {
				logger.info("An exception getting while sending mail to BO=============>{}");

				e.printStackTrace();
			}

		} else {

			logger.info("Mail to BO after In-principle to FS is disabled==========>");
		}
	}

	// ====================Sending Mail to Maker and all Makers and Checkers when maker accepts Proposal==========
	@Async
	public void sendMailToMakerandAllMakersWhenMakerAcceptProposal(NhbsApplicationRequest request) {
		logger.info("Enter in sending mail to Maker and all Makers When Maker accepts Proposal");
		try {
			Long NotificationAliasId=null;
			LoanApplicationRequest applicationRequest = loanApplicationService
					.getFromClient(request.getApplicationId());
			Map<String, Object> parameters = new HashMap<String, Object>();
			
			String address = null;
			String state = null;
			String city = null;
			
			// For getting Fund Seeker's Name
			// =========================================================================================================
			String fsName = null;
			List<DirectorBackgroundDetailRequest> NTBResponse = null;
			if (applicationRequest.getBusinessTypeId() == 2) {
				NTBResponse = directorBackgroundDetailsService
						.getDirectorBasicDetailsListForNTB(request.getApplicationId());
				if (!CommonUtils.isObjectNullOrEmpty(NTBResponse)) {
					int isMainDirector = 0;
					for (DirectorBackgroundDetailRequest director : NTBResponse) {
						if (!CommonUtils.isObjectNullOrEmpty(director) && director.getIsMainDirector()) {
							fsName = director.getDirectorsName() != null ? director.getDirectorsName() : "NA";
							address = director.getAddress();
							if(!CommonUtils.isObjectNullOrEmpty(director.getStateId())){
								state = CommonDocumentUtils.getState(Long.valueOf(director.getStateId().toString()), oneFormClient);	
							}
							
							if(!CommonUtils.isObjectNullOrEmpty(director.getCityId())){
								city = CommonDocumentUtils.getState(Long.valueOf(director.getCityId().toString()), oneFormClient);	
							}							
							isMainDirector = 1;
						}
					}
					if (isMainDirector == 0) {
						fsName = NTBResponse.get(0).getDirectorsName() != null ? NTBResponse.get(0).getDirectorsName()
								: "NA";
					}
				} else {
					fsName = "NA";
				}
			} else {
				fsName = applicationRequest.getUserName() != null ? applicationRequest.getUserName() : "NA";
			}
			parameters.put("fs_name", fsName != null ? fsName : "NA");
			// =========================================================================================================
			
			if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
					&& applicationRequest.getBusinessTypeId() == 1){
				CorporateApplicantRequest applicantRequest = corporateapplicantService
						.getCorporateApplicant(request.getApplicationId());
				if (!CommonUtils.isObjectNullOrEmpty(applicantRequest)
						&& !CommonUtils.isObjectNullOrEmpty(applicantRequest.getFirstAddress())) {
					String premiseNumber = null;
					String streetName = null;
					String landMark = null;
					premiseNumber = applicantRequest.getFirstAddress().getPremiseNumber()!=null?applicantRequest.getFirstAddress().getPremiseNumber():"";
					streetName = applicantRequest.getFirstAddress().getStreetName()!=null?applicantRequest.getFirstAddress().getStreetName():"";
					landMark = applicantRequest.getFirstAddress().getLandMark()!=null?applicantRequest.getFirstAddress().getLandMark():"";
					address = premiseNumber.toString()+" "+streetName.toString()+" "+landMark.toString();

					List<Long> stateList = new ArrayList<Long>();

					Long stateId = null;
					if (!CommonUtils.isObjectNullOrEmpty(applicantRequest.getFirstAddress().getStateId())) {
						stateId = Long.valueOf(applicantRequest.getFirstAddress().getStateId());
						stateList.add(stateId);
					}

					if (!CommonUtils.isListNullOrEmpty(stateList)) {
						try {
							logger.info("Calling One form client for getting state by state list Id");
							OneFormResponse oneFormResponse = oneFormClient.getStateByStateListId(stateList);
							List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
									.getListData();
							if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
								MasterResponse masterResponse = MultipleJSONObjectHelper
										.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
								state = masterResponse.getValue();
								city = CommonDocumentUtils.getCity(applicantRequest.getFirstAddress().getCityId(), oneFormClient);
							} else {
								state = "NA";
								city = "NA";
							}
							
						} catch (Exception e) {
							logger.info("Error Calling One form client for getting state by state list Id");

							e.printStackTrace();
						}
					}
				}
			}

			//	when proposal belongs to PL			
			if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
					&& applicationRequest.getBusinessTypeId() == CommonUtils.BusinessType.RETAIL_PERSONAL_LOAN.getId()){
				fsName=applicationRequest.getUserName();
				address=applicationRequest.getAddress();
				NotificationAliasId= NotificationAlias.PL_EMAIL_TO_FS_WHEN_MAKKER_ACCEPT_PROPOSAL;
			}else {
					NotificationAliasId=NotificationAlias.EMAIL_FS_ACCEPTED_BY_MAKER;
			}
			parameters.put("address", address != null ? address : "NA");
			parameters.put("state", state != null ? state : "NA");
			parameters.put("city", city !=null ? city : "NA");
			
			ProposalMappingResponse proposalResponse = null;
			Map<String, Object> proposalresp = null;
			try {
				logger.info("Calling Proposal details client for getting Branch Id:-" + request.getApplicationId());
				proposalResponse = proposalDetailsClient.getInPricipleById(request.getApplicationId());
				logger.info("Got Inprinciple response from Proposal Details Client" + proposalResponse);
				proposalresp = MultipleJSONObjectHelper
						.getObjectFromMap((Map<String, Object>) proposalResponse.getData(), Map.class);
			} catch (Exception e) {
				logger.info(
						"Error calling Proposal Details Client for getting Branch Id:-" + request.getApplicationId());
				e.printStackTrace();
			}

			Long branchId = null;
			if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get("branch_id"))) {
				branchId = Long.valueOf(proposalresp.get("branch_id").toString());
			}

			// ===========Getting emailId and Name of Maker who accepted the
			// Proposal================

			UserResponse assignedMakerResponse = null;
			try {
				assignedMakerResponse = userClient.getEmailMobile(request.getUserId());
			} catch (Exception e) {
				logger.info("Something went wrong while calling Users client===>{}");
				e.printStackTrace();
			}
			UsersRequest assignedMaker = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedMakerResponse)) {
				assignedMaker = MultipleJSONObjectHelper
						.getObjectFromMap((Map<String, Object>) assignedMakerResponse.getData(), UsersRequest.class);
			}

			UsersRequest assignedMakerForName = new UsersRequest();
			assignedMakerForName.setId(request.getUserId());

			String makerName = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedMakerForName)) {

				try {
					logger.error("Into getting FP Name======>" + assignedMakerForName);
					UserResponse userResponseForName = userClient.getFPDetails(assignedMakerForName);
					FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
					makerName = fundProviderDetailsRequest.getFirstName() + " "
							+ (fundProviderDetailsRequest.getLastName() == null ? ""
									: fundProviderDetailsRequest.getLastName());
				} catch (Exception e) {
					logger.error("error while fetching FP name");
					e.printStackTrace();
				}
			}

			// =======================================================================================

			String assignedMakerName = null;

			if ("null ".equals(makerName)) {
				assignedMakerName = "Maker";
			} else {
				assignedMakerName = makerName != null ? makerName : "Maker";
			}
			parameters.put("maker_name", assignedMakerName);
			if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get("loan_type"))) {
				parameters.put("product_type",
						proposalresp.get("loan_type").toString() != null ? proposalresp.get("loan_type").toString()
								: "NA");
			} else {
				parameters.put("product_type", "NA");
			}
			parameters.put("loan_amount",
					applicationRequest.getLoanAmount() != null ? applicationRequest.getLoanAmount() : "NA");
			parameters.put("application_id", request.getApplicationId().toString());
			
			UserResponse response = null;
			UsersRequest signUpUser = null;

			try {
				response = userClient.getEmailMobile(applicationRequest.getUserId());
			} catch (Exception e) {
				logger.info("Something went wrong while calling Users client from sending mail to fs===>{}");
				e.printStackTrace();
			}

			if (!CommonUtils.isObjectNullOrEmpty(response)) {
				try {
					signUpUser = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) response.getData(),
							UsersRequest.class);
				} catch (Exception e) {
					logger.info("Exception getting signup user at Sending email to fs when maker accept proposal");
					e.printStackTrace();
				}
			}

			String mobile = signUpUser.getMobile();
			parameters.put("mobile_no", mobile!=null?mobile:"NA");

			// ====================Sending Mail to Maker who accepts Proposal=====================
			String subject = "Intimation: Proposal Accepted - " + assignedMakerName + "-Application ID "
					+ request.getApplicationId();

			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker)) {
				if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getEmail())) {

					String toIds = assignedMaker.getEmail();
					logger.info("Email Sending TO MAKER when Maker accepts Proposal===to==>{}" + toIds);

					createNotificationForEmail(toIds, request.getUserId().toString(), parameters,
							NotificationAlias.EMAIL_MAKER_ACCEPT_PROPOSAL_OF_FS, subject);
				}

				if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getMobile())) {
					// System.out.println("Maker ID:---"+userObj.getEmail());
					Map<String, Object> smsParameters = new HashMap<String, Object>();
					String to = "91" + assignedMaker.getMobile();
					smsParameters.put("maker_name", assignedMakerName);
					smsParameters.put("fs_name", fsName != null ? fsName : "NA");
					if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get("loan_type"))) {
						smsParameters.put("product_type",
								proposalresp.get("loan_type").toString() != null
										? proposalresp.get("loan_type").toString()
										: "NA");
					} else {
						smsParameters.put("product_type", "NA");
					}
					smsParameters.put("url", "www.bitly.com");

					sendSMSNotification(request.getUserId().toString(), smsParameters,
							NotificationAlias.SMS_MAKER_ACCEPT_PROPOSAL_OF_FS, to);
				}

				if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getId())) {
					// System.out.println("Maker ID:---"+userObj.getEmail());
					Map<String, Object> sysParameters = new HashMap<String, Object>();
					sysParameters.put("fs_name", fsName != null ? fsName : "NA");

					sendSYSNotification(request.getApplicationId(), request.getUserId().toString(), sysParameters,
							NotificationAlias.SYS_MAKER_ACCEPT_PROPOSAL_OF_FS, request.getUserId().toString(),
							request.getUserId().toString());
				}

			}
			// ======MAAZ=========sending email to fs when maker accepted
			// proposL==Email_FS_Accepted_By_MAKER====== add mail for  Email_FS_Accepted_By_MAKER
			sendMailToFsWhenMakerAcceptPorposal(fsName, proposalresp, assignedMakerName,applicationRequest,signUpUser,address,NotificationAliasId);

			// ====================Sending Mail to other Makers that maker has accepted Proposal==============

			UserResponse makerResponse = userClient.getUserDetailByOrgRoleBranchId(applicationRequest.getNpOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_MAKER, branchId);
			List<Map<String, Object>> makerRespList = (List<Map<String, Object>>) makerResponse.getListData();
			if (!CommonUtils.isObjectNullOrEmpty(makerRespList)) {
				for (int i = 0; i < makerRespList.size(); i++) {
					UsersRequest makerObj = MultipleJSONObjectHelper.getObjectFromMap(makerRespList.get(i),
							UsersRequest.class);

					String name = null;

					try {
						logger.error("Into getting FP Name======>" + makerObj);
						UserResponse userResponseForName = userClient.getFPDetails(makerObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
										: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error("error while fetching FP name");
						e.printStackTrace();
					}

					if (!CommonUtils.isObjectNullOrEmpty(makerObj.getEmail())
							&& !(makerObj.getEmail().equals(assignedMaker.getEmail()))) {
						// System.out.println("Checker ID:---"+userObj.getEmail());
						String to = makerObj.getEmail();
						parameters.put("assigned_maker_name", assignedMakerName);
						if ("null ".equals(name)) {
							parameters.put("maker_name", "Sir/Madam");
						} else {
							parameters.put("maker_name", name != null ? name : "Sir/Madam");
						}
						createNotificationForEmail(to, makerObj.getId().toString(), parameters,
								NotificationAlias.EMAIL_ALL_MAKERS_AFTER_MAKER_ACCEPT_PROPOSAL_OF_FS, subject);
					}

					if (!CommonUtils.isObjectNullOrEmpty(makerObj.getMobile())
							&& !(makerObj.getMobile().equals(assignedMaker.getMobile()))) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						String to = "91" + makerObj.getMobile();
						smsParameters.put("maker_name", assignedMakerName);
						smsParameters.put("fs_name", fsName != null ? fsName : "NA");
						if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get("loan_type"))) {
							smsParameters.put("product_type",
									proposalresp.get("loan_type").toString() != null
											? proposalresp.get("loan_type").toString()
											: "NA");
						} else {
							smsParameters.put("product_type", "NA");
						}
						smsParameters.put("url", "www.bitly.com");

						sendSMSNotification(makerObj.getId().toString(), smsParameters,
								NotificationAlias.SMS_ALL_MAKERS_AFTER_MAKER_ACCEPT_PROPOSAL_OF_FS, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(makerObj.getId())
							&& !(makerObj.getId().equals(assignedMaker.getId()))) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put("maker_name", assignedMakerName);
						sysParameters.put("fs_name", fsName != null ? fsName : "NA");

						sendSYSNotification(request.getApplicationId(), makerObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_ALL_MAKERS_AFTER_MAKER_ACCEPT_PROPOSAL_OF_FS,
								makerObj.getId().toString(), makerObj.getId().toString());
					}

				}

			} else {
				logger.info("No Maker found=================>");
			}

			// =======================================================================================================

			// ====================Sending Mail to all Checkers of that branch that maker
			// has accepted Proposal=====================

			UserResponse checkerResponse = userClient.getUserDetailByOrgRoleBranchId(applicationRequest.getNpOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_CHECKER, branchId);
			List<Map<String, Object>> checkerRespList = (List<Map<String, Object>>) checkerResponse.getListData();
			if (!CommonUtils.isObjectNullOrEmpty(checkerRespList)) {
				for (int i = 0; i < checkerRespList.size(); i++) {
					UsersRequest checkerObj = MultipleJSONObjectHelper.getObjectFromMap(checkerRespList.get(i),
							UsersRequest.class);

					String name = null;

					try {
						logger.error("Into getting FP Name======>" + checkerObj);
						UserResponse userResponseForName = userClient.getFPDetails(checkerObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
										: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error("error while fetching FP name");
						e.printStackTrace();
					}

					if (!CommonUtils.isObjectNullOrEmpty(checkerObj.getEmail())) {
						// System.out.println("Checker ID:---"+userObj.getEmail());
						String to = checkerObj.getEmail();
						parameters.put("maker_name", assignedMakerName);
						if ("null ".equals(name)) {
							parameters.put("checker_name", "Sir/Madam");
						} else {
							parameters.put("checker_name", name != null ? name : "Sir/Madam");
						}
						createNotificationForEmail(to, checkerObj.getId().toString(), parameters,
								NotificationAlias.EMAIL_ALL_CHECKERS_AFTER_MAKER_ACCEPT_PROPOSAL, subject);
					}

					if (!CommonUtils.isObjectNullOrEmpty(checkerObj.getMobile())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						String to = "91" + checkerObj.getMobile();
						if ("null ".equals(name)) {
							smsParameters.put("checker_name", "Sir/Madam");
						} else {
							smsParameters.put("checker_name", name != null ? name : "Sir/Madam");
						}
						smsParameters.put("maker_name", assignedMakerName);
						smsParameters.put("fs_name", fsName != null ? fsName : "NA");
						if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get("loan_type"))) {
							smsParameters.put("product_type",
									proposalresp.get("loan_type").toString() != null
											? proposalresp.get("loan_type").toString()
											: "NA");
						} else {
							smsParameters.put("product_type", "NA");
						}
						smsParameters.put("url", "www.bitly.com");

						sendSMSNotification(checkerObj.getId().toString(), smsParameters,
								NotificationAlias.SMS_ALL_CHECKERS_AFTER_MAKER_ACCEPT_PROPOSAL, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(checkerObj.getId())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put("maker_name", assignedMakerName);
						sysParameters.put("fs_name", fsName != null ? fsName : "NA");
						if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get("loan_type"))) {
							sysParameters.put("product_type",
									proposalresp.get("loan_type").toString() != null
											? proposalresp.get("loan_type").toString()
											: "NA");
						} else {
							sysParameters.put("product_type", "NA");
						}
						sendSYSNotification(request.getApplicationId(), checkerObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_ALL_CHECKERS_AFTER_MAKER_ACCEPT_PROPOSAL,
								checkerObj.getId().toString(), checkerObj.getId().toString());
					}

				}

			} else {
				logger.info("No Checker found=================>");
			}

			// =======================================================================================================

			// ====================Sending Mail to HO of that branch that maker has accepted
			// Proposal=====================

			UserResponse hoResponse = userClient.getUserDetailByOrgRoleBranchId(applicationRequest.getNpOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.HEAD_OFFICER, branchId);
			List<Map<String, Object>> hoRespList = (List<Map<String, Object>>) hoResponse.getListData();
			if (!CommonUtils.isObjectNullOrEmpty(hoRespList)) {
				for (int i = 0; i < hoRespList.size(); i++) {
					UsersRequest hoObj = MultipleJSONObjectHelper.getObjectFromMap(hoRespList.get(i),
							UsersRequest.class);

					String name = null;

					try {
						logger.error("Into getting FP Name======>" + hoObj);
						UserResponse userResponseForName = userClient.getFPDetails(hoObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
										: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error("error while fetching FP name");
						e.printStackTrace();
					}

					if (!CommonUtils.isObjectNullOrEmpty(hoObj.getEmail())) {
						// System.out.println("Checker ID:---"+userObj.getEmail());
						String to = hoObj.getEmail();
						parameters.put("maker_name", assignedMakerName);
						if ("null ".equals(name)) {
							parameters.put("ho_name", "Sir/Madam");
						} else {
							parameters.put("ho_name", name != null ? name : "Sir/Madam");
						}
						createNotificationForEmail(to, hoObj.getId().toString(), parameters,
								NotificationAlias.EMAIL_HO_MAKER_ACCEPT_PROPOSAL_OF_FS, subject);
					}

					if (!CommonUtils.isObjectNullOrEmpty(hoObj.getMobile())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						String to = "91" + hoObj.getMobile();
						smsParameters.put("maker_name", assignedMakerName);
						smsParameters.put("fs_name", fsName != null ? fsName : "NA");
						if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get("loan_type"))) {
							smsParameters.put("product_type",
									proposalresp.get("loan_type").toString() != null
											? proposalresp.get("loan_type").toString()
											: "NA");
						} else {
							smsParameters.put("product_type", "NA");
						}
						smsParameters.put("url", "www.bitly.com");

						sendSMSNotification(hoObj.getId().toString(), smsParameters,
								NotificationAlias.SMS_HO_MAKER_ACCEPT_PROPOSAL_OF_FS, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(hoObj.getId())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put("maker_name", assignedMakerName);
						sysParameters.put("fs_name", fsName != null ? fsName : "NA");
						if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get("loan_type"))) {
							sysParameters.put("product_type",
									proposalresp.get("loan_type").toString() != null
											? proposalresp.get("loan_type").toString()
											: "NA");
						} else {
							sysParameters.put("product_type", "NA");
						}
						sendSYSNotification(request.getApplicationId(), hoObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_HO_MAKER_ACCEPT_PROPOSAL_OF_FS, hoObj.getId().toString(),
								hoObj.getId().toString());
					}

				}

			} else {
				logger.info("No HO found=================>");
			}

			// =======================================================================================================

			// ====================Sending Mail to all BO of that branch that maker has
			// accepted Proposal=====================

			UserResponse boResponse = userClient.getUserDetailByOrgRoleBranchId(applicationRequest.getNpOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.BRANCH_OFFICER, branchId);
			List<Map<String, Object>> boRespList = (List<Map<String, Object>>) boResponse.getListData();
			if (!CommonUtils.isObjectNullOrEmpty(boRespList)) {
				for (int i = 0; i < boRespList.size(); i++) {
					UsersRequest boObj = MultipleJSONObjectHelper.getObjectFromMap(boRespList.get(i),
							UsersRequest.class);

					String name = null;

					try {
						logger.error("Into getting FP Name======>" + boObj);
						UserResponse userResponseForName = userClient.getFPDetails(boObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
										: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error("error while fetching FP name");
						e.printStackTrace();
					}

					if (!CommonUtils.isObjectNullOrEmpty(boObj.getEmail())) {
						// System.out.println("Checker ID:---"+userObj.getEmail());
						String to = boObj.getEmail();
						parameters.put("maker_name", assignedMakerName);
						if ("null ".equals(name)) {
							parameters.put("bo_name", "Sir/Madam");
						} else {
							parameters.put("bo_name", name != null ? name : "Sir/Madam");
						}
						createNotificationForEmail(to, boObj.getId().toString(), parameters,
								NotificationAlias.EMAIL_ALL_BO_MAKER_ACCEPT_PROPOSAL_OF_FS, subject);
					}

					if (!CommonUtils.isObjectNullOrEmpty(boObj.getMobile())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						String to = "91" + boObj.getMobile();
						smsParameters.put("maker_name", assignedMakerName);
						smsParameters.put("fs_name", fsName != null ? fsName : "NA");
						if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get("loan_type"))) {
							smsParameters.put("product_type",
									proposalresp.get("loan_type").toString() != null
											? proposalresp.get("loan_type").toString()
											: "NA");
						} else {
							smsParameters.put("product_type", "NA");
						}
						smsParameters.put("url", "www.bitly.com");

						sendSMSNotification(boObj.getId().toString(), smsParameters,
								NotificationAlias.SMS_ALL_BO_MAKER_ACCEPT_PROPOSAL_OF_FS, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(boObj.getId())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put("maker_name", assignedMakerName);
						sysParameters.put("fs_name", fsName != null ? fsName : "NA");
						if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get("loan_type"))) {
							sysParameters.put("product_type",
									proposalresp.get("loan_type").toString() != null
											? proposalresp.get("loan_type").toString()
											: "NA");
						} else {
							sysParameters.put("product_type", "NA");
						}
						sendSYSNotification(request.getApplicationId(), boObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_ALL_BO_MAKER_ACCEPT_PROPOSAL_OF_FS, boObj.getId().toString(),
								boObj.getId().toString());
					}

				}

			} else {
				logger.info("No BO found=================>");
			}

			// =======================================================================================================
		
			}catch (Exception e) {
			logger.info("Throw exception while sending mail to Maker and all Makers when Maker accepts Proposal");
			e.printStackTrace();
		}
	}

	// ============maaz=========================================================================================
	@Async
	public void sendMailToFsWhenMakerAcceptPorposal(String fsName, Map<String, Object> proposalresp,String assignedMakerName,LoanApplicationRequest applicationRequest, UsersRequest signUpUser, String address,Long NotificationAliasId){
		logger.info("Sending email to fs when maker accept proposal");
		Map<String, Object> mailParameter = new HashMap<String, Object>();
		mailParameter.put("fs_name", fsName != null ? fsName.toString() : "NA");
		mailParameter.put("entity_name", fsName != null ? fsName.toString() : "NA");
		mailParameter.put("loan_type",
				proposalresp.get("loan_type") != null ? proposalresp.get("loan_type").toString() : "NA");
		mailParameter.put("maker_name", assignedMakerName);
		mailParameter.put("application_id", applicationRequest.getId()!=null?applicationRequest.getId().toString():"NA");
		
		String mobile = signUpUser.getMobile();
		mailParameter.put("mobile_number", mobile != null ? mobile : "NA");
		mailParameter.put("address", address);
		String emailSubject = "Maker Assigned - For Quick Business Loan Approval ";

		try {
			if(fsName!=null&& address!=null&&mobile!=null&&assignedMakerName!=null&&applicationRequest.getId()!=null) {
				createNotificationForEmail(signUpUser.getEmail(), applicationRequest.getUserId().toString(),
						mailParameter, NotificationAliasId, emailSubject);
				logger.info("Email send to fs when maker accepted porposal");
				System.out.println("Email send to fs when maker accepted porposal");
			}else {
				logger.error("Email is not sent becouse of required values are null:"+  "signUpUser.getEmail:"+signUpUser.getEmail()+"applicationRequest.getUserId:"+applicationRequest.getUserId()
				+" mailParameter:"+mailParameter+"  NotificationAliasId"+NotificationAliasId.toString()+"emailSubject:"+emailSubject);
			}
		} catch (Exception e) {
			logger.info("Error in sending email to fs when porposal asigned to maker :" + e);
			e.printStackTrace();
		}
	}

	@Async
	public void sendMailWhenMakerAssignDDRToChecker(NhbsApplicationRequest request) {
		logger.info("Enter in sending mail to Checker/HO/BO When Maker Assign DDR To Checker");
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			LoanApplicationRequest applicationRequest = loanApplicationService
					.getFromClient(request.getApplicationId());
			ProposalMappingResponse proposalResponse = null;
			Map<String, Object> proposalresp = null;
			try {
				logger.info("Calling Proposal details client for getting Branch Id:-" + request.getApplicationId());
				proposalResponse = proposalDetailsClient.getInPricipleById(request.getApplicationId());
				logger.info("Got Inprinciple response from Proposal Details Client" + proposalResponse);
				proposalresp = MultipleJSONObjectHelper
						.getObjectFromMap((Map<String, Object>) proposalResponse.getData(), Map.class);
			} catch (Exception e) {
				logger.info(
						"Error calling Proposal Details Client for getting Branch Id:-" + request.getApplicationId());

				e.printStackTrace();

			}

			Long branchId = null;
			if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get("branch_id"))) {
				branchId = Long.valueOf(proposalresp.get("branch_id").toString());
			}

			// =========================================Getting
			// Maker=====================================

			UsersRequest assignedMakerForName = new UsersRequest();
			assignedMakerForName.setId(request.getUserId());

			String makerName = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedMakerForName)) {

				try {
					logger.error("Into getting FP Name======>" + assignedMakerForName);
					UserResponse userResponseForName = userClient.getFPDetails(assignedMakerForName);
					FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
					makerName = fundProviderDetailsRequest.getFirstName() + " "
							+ (fundProviderDetailsRequest.getLastName() == null ? ""
									: fundProviderDetailsRequest.getLastName());
				} catch (Exception e) {
					logger.error("error while fetching FP name");
					e.printStackTrace();
				}
			}

			if ("null ".equals(makerName)) {
				makerName = "Maker";
			} else {
				makerName = makerName != null ? makerName : "Maker";
			}

			// ========================================================================================================

			// =========================================Getting
			// Checker=====================================

			UserResponse checkerResponse = null;
			try {
				checkerResponse = userClient.getEmailMobile(request.getNpUserId());
			} catch (Exception e) {
				logger.info("Something went wrong while calling Users client===>{}");
				e.printStackTrace();
			}
			UsersRequest assignedChecker = null;
			if (!CommonUtils.isObjectNullOrEmpty(checkerResponse)) {
				assignedChecker = MultipleJSONObjectHelper
						.getObjectFromMap((Map<String, Object>) checkerResponse.getData(), UsersRequest.class);
			}

			UsersRequest assignedCheckerForName = new UsersRequest();
			assignedCheckerForName.setId(request.getNpUserId());

			String checkerName = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedCheckerForName)) {

				try {
					logger.error("Into getting FP Name======>" + assignedCheckerForName);
					UserResponse userResponseForName = userClient.getFPDetails(assignedCheckerForName);
					FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
					checkerName = fundProviderDetailsRequest.getFirstName() + " "
							+ (fundProviderDetailsRequest.getLastName() == null ? ""
									: fundProviderDetailsRequest.getLastName());
				} catch (Exception e) {
					logger.error("error while fetching FP name");
					e.printStackTrace();
				}
			}

			if ("null ".equals(checkerName)) {
				checkerName = "Sir/Madam";
			} else {
				checkerName = checkerName != null ? checkerName : "Sir/Madam";
			}

			// ========================================================================================================

			parameters.put("maker_name", makerName != null ? makerName : "NA");
			parameters.put("checker_name", checkerName != null ? checkerName : "NA");
			parameters.put("application_id", request.getApplicationId().toString());
			
			String address = null;
			
			// For getting Fund Seeker's Name
			// =========================================================================================================
			String fsName = null;
			List<DirectorBackgroundDetailRequest> NTBResponse = null;
			if (applicationRequest.getBusinessTypeId() == 2) {
				NTBResponse = directorBackgroundDetailsService
						.getDirectorBasicDetailsListForNTB(request.getApplicationId());
				if (!CommonUtils.isObjectNullOrEmpty(NTBResponse)) {
					int isMainDirector = 0;
					for (DirectorBackgroundDetailRequest director : NTBResponse) {
						if (!CommonUtils.isObjectNullOrEmpty(director) && director.getIsMainDirector()) {
							fsName = director.getDirectorsName() != null ? director.getDirectorsName() : "NA";
							address = director.getAddress();
							isMainDirector = 1;
						}
					}
					if (isMainDirector == 0) {
						fsName = NTBResponse.get(0).getDirectorsName() != null ? NTBResponse.get(0).getDirectorsName()
								: "NA";
					}
				} else {
					fsName = "NA";
				}
			} else {
				fsName = applicationRequest.getUserName() != null ? applicationRequest.getUserName() : "NA";
			}
			parameters.put("fs_name", fsName != null ? fsName : "NA");
			// =========================================================================================================
			
			if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
					&& applicationRequest.getBusinessTypeId() == 1){
				CorporateApplicantRequest applicantRequest = corporateapplicantService
						.getCorporateApplicant(request.getApplicationId());
				if (!CommonUtils.isObjectNullOrEmpty(applicantRequest)
						&& !CommonUtils.isObjectNullOrEmpty(applicantRequest.getFirstAddress())) {
					String premiseNumber = null;
					String streetName = null;
					String landMark = null;
					premiseNumber = applicantRequest.getFirstAddress().getPremiseNumber()!=null?applicantRequest.getFirstAddress().getPremiseNumber():"";
					streetName = applicantRequest.getFirstAddress().getStreetName()!=null?applicantRequest.getFirstAddress().getStreetName():"";
					landMark = applicantRequest.getFirstAddress().getLandMark()!=null?applicantRequest.getFirstAddress().getLandMark():"";
					address = premiseNumber.toString()+" "+streetName.toString()+" "+landMark.toString();
				}
			}
			
			parameters.put("address", address != null ? address : "NA");

			
			if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get("loan_type"))) {

				parameters.put("product_type",
						proposalresp.get("loan_type").toString() != null ? proposalresp.get("loan_type").toString()
								: "NA");
			} else {

				parameters.put("product_type", "NA");

			}

			UserResponse signUpuser = null;
			try {
				signUpuser = userClient.getEmailMobile(applicationRequest.getUserId());
			} catch (Exception e) {
				logger.info("Something went wrong while calling Users client===>{}");
				e.printStackTrace();
			}

			if (!CommonUtils.isObjectNullOrEmpty(signUpuser)) {
				UsersRequest signUpUser = MultipleJSONObjectHelper
						.getObjectFromMap((Map<String, Object>) signUpuser.getData(), UsersRequest.class);

				String mobile = signUpUser.getMobile();
				System.out.println("Mobile no:-" + mobile);
				parameters.put("mobile_no", mobile != null ? mobile : "NA");

			}

			String subjcet = "Intimation: Assigned - Application ID " + request.getApplicationId();
			if (!CommonUtils.isObjectNullOrEmpty(assignedChecker)
					&& !CommonUtils.isObjectNullOrEmpty(assignedChecker.getEmail())) {

				String toIds = assignedChecker.getEmail();
				logger.info("Email Sending TO CHECKER on sendMailWhenMakerAssignDDRToChecker===to==>{}", toIds);
				// ====================== MAIL TO CHECKER ======================
				createNotificationForEmail(toIds, request.getNpUserId().toString(), parameters,
						NotificationAlias.EMAIL_CHECKER_MAKER_ASSIGN_APPLICATION_TO_CHECKER, subjcet);

			}

			if (!CommonUtils.isObjectNullOrEmpty(assignedChecker.getMobile())) {
				// System.out.println("Maker ID:---"+userObj.getEmail());
				Map<String, Object> smsParameters = new HashMap<String, Object>();
				String to = "91" + assignedChecker.getMobile();
				smsParameters.put("maker_name", makerName != null ? makerName : "Maker");
				smsParameters.put("fs_name", fsName != null ? fsName : "NA");

				smsParameters.put("url", "www.bitly.com");

				sendSMSNotification(request.getNpUserId().toString(), smsParameters,
						NotificationAlias.SMS_CHECKER_MAKER_ASSIGN_APPLICATION_TO_CHECKER, to);
			}

			if (!CommonUtils.isObjectNullOrEmpty(request.getNpUserId())) {
				// System.out.println("Maker ID:---"+userObj.getEmail());
				Map<String, Object> sysParameters = new HashMap<String, Object>();
				sysParameters.put("maker_name", makerName != null ? makerName : "Maker");
				sysParameters.put("fs_name", fsName != null ? fsName : "NA");
				sysParameters.put("product_type",
						proposalresp.get("loan_type").toString() != null ? proposalresp.get("loan_type").toString()
								: "NA");

				sendSYSNotification(request.getApplicationId(), request.getNpUserId().toString(), sysParameters,
						NotificationAlias.SYS_CHECKER_MAKER_ASSIGN_APPLICATION_TO_CHECKER,
						request.getNpUserId().toString(), request.getNpUserId().toString());
			}

			// ====================Sending Mail to HO when Maker Assigns DDR to
			// Checker=====================

			String subject = "Intimation: Assigned DDR- Application ID " + request.getApplicationId();

			UserResponse hoResponse = userClient.getUserDetailByOrgRoleBranchId(applicationRequest.getNpOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.HEAD_OFFICER, branchId);
			List<Map<String, Object>> hoRespList = (List<Map<String, Object>>) hoResponse.getListData();
			if (!CommonUtils.isObjectNullOrEmpty(hoRespList)) {
				for (int i = 0; i < hoRespList.size(); i++) {
					UsersRequest hoObj = MultipleJSONObjectHelper.getObjectFromMap(hoRespList.get(i),
							UsersRequest.class);

					String name = null;

					try {
						logger.error("Into getting FP Name======>" + hoObj);
						UserResponse userResponseForName = userClient.getFPDetails(hoObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
										: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error("error while fetching FP name");
						e.printStackTrace();
					}

					if (!CommonUtils.isObjectNullOrEmpty(hoObj.getEmail())) {
						// System.out.println("Checker ID:---"+userObj.getEmail());
						String to = hoObj.getEmail();
						if ("null ".equals(name)) {
							name = "Sir/Madam";
						} else {
							name = name != null ? name : "Sir/Madam";
						}
						parameters.put("ho_name", name != null ? name : "Sir/Madam");
						createNotificationForEmail(to, request.getUserId().toString(), parameters,
								NotificationAlias.EMAIL_HO_MAKER_ASSIGN_APPLICATION_TO_CHECKER, subject);
					}

					if (!CommonUtils.isObjectNullOrEmpty(hoObj.getMobile())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						String to = "91" + hoObj.getMobile();
						smsParameters.put("maker_name", makerName != null ? makerName : "Maker");
						smsParameters.put("fs_name", fsName != null ? fsName : "NA");

						smsParameters.put("url", "www.bitly.com");

						sendSMSNotification(hoObj.getId().toString(), smsParameters,
								NotificationAlias.SMS_HO_MAKER_ASSIGN_APPLICATION_TO_CHECKER, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(hoObj.getId())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put("maker_name", makerName != null ? makerName : "Maker");
						sysParameters.put("checker_name", checkerName != null ? checkerName : "Checker");
						sysParameters.put("product_type",
								proposalresp.get("loan_type").toString() != null
										? proposalresp.get("loan_type").toString()
										: "NA");
						sysParameters.put("fs_name", fsName != null ? fsName : "NA");

						sendSYSNotification(request.getApplicationId(), hoObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_HO_MAKER_ASSIGN_APPLICATION_TO_CHECKER, hoObj.getId().toString(),
								hoObj.getId().toString());
					}

				}

			} else {
				logger.info("No HO found=================>");
			}

			// =========================================================================================

			// ====================Sending Mail to BO when Maker Assigns DDR to
			// Checker=====================

			subject = "Intimation: Assigned DDR- Application ID " + request.getApplicationId();

			UserResponse boResponse = userClient.getUserDetailByOrgRoleBranchId(applicationRequest.getNpOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.BRANCH_OFFICER, branchId);
			List<Map<String, Object>> boRespList = (List<Map<String, Object>>) boResponse.getListData();
			if (!CommonUtils.isObjectNullOrEmpty(boRespList)) {
				for (int i = 0; i < boRespList.size(); i++) {
					UsersRequest boObj = MultipleJSONObjectHelper.getObjectFromMap(boRespList.get(i),
							UsersRequest.class);

					String name = null;

					try {
						logger.error("Into getting FP Name======>" + boObj);
						UserResponse userResponseForName = userClient.getFPDetails(boObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
										: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error("error while fetching FP name");
						e.printStackTrace();
					}

					if (!CommonUtils.isObjectNullOrEmpty(boObj.getEmail())) {
						// System.out.println("Checker ID:---"+userObj.getEmail());
						String to = boObj.getEmail();
						if ("null ".equals(name)) {
							name = "Sir/Madam";
						} else {
							name = name != null ? name : "Sir/Madam";
						}
						parameters.put("bo_name", name != null ? name : "Sir/Madam");
						createNotificationForEmail(to, request.getUserId().toString(), parameters,
								NotificationAlias.EMAIL_ALL_BO_MAKER_ASSIGN_APPLICATION_TO_CHECKER, subject);
					}

					if (!CommonUtils.isObjectNullOrEmpty(boObj.getMobile())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						String to = "91" + boObj.getMobile();
						smsParameters.put("maker_name", makerName != null ? makerName : "Maker");
						smsParameters.put("fs_name", fsName != null ? fsName : "NA");

						smsParameters.put("url", "www.bitly.com");

						sendSMSNotification(boObj.getId().toString(), smsParameters,
								NotificationAlias.SMS_ALL_BO_MAKER_ASSIGN_APPLICATION_TO_CHECKER, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(boObj.getId())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put("maker_name", makerName != null ? makerName : "Maker");
						sysParameters.put("checker_name", checkerName != null ? checkerName : "Checker");
						sysParameters.put("product_type",
								proposalresp.get("loan_type").toString() != null
										? proposalresp.get("loan_type").toString()
										: "NA");
						sysParameters.put("fs_name", fsName != null ? fsName : "NA");

						sendSYSNotification(request.getApplicationId(), boObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_ALL_BO_MAKER_ASSIGN_APPLICATION_TO_CHECKER,
								boObj.getId().toString(), boObj.getId().toString());
					}

				}

			} else {
				logger.info("No BO found=================>");
			}

			// =========================================================================================

		} catch (Exception e) {
			logger.info("Throw exception while sending mail to Checker/HO/BO when Maker Assign DDR to Checker");
			e.printStackTrace();
		}
	}

	@Async
	public void sendMailWhenMakerReAssignDDRToChecker(NhbsApplicationRequest request, Date lastModifiedDate) {
		logger.info("Enter in sending mail to Checker/HO/BO When Maker Reassign DDR To Checker");
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			LoanApplicationRequest applicationRequest = loanApplicationService
					.getFromClient(request.getApplicationId());
			ProposalMappingResponse proposalResponse = null;
			Map<String, Object> proposalresp = null;
			try {
				logger.info("Calling Proposal details client for getting Branch Id:-" + request.getApplicationId());
				proposalResponse = proposalDetailsClient.getInPricipleById(request.getApplicationId());
				logger.info("Got Inprinciple response from Proposal Details Client" + proposalResponse);
				proposalresp = MultipleJSONObjectHelper
						.getObjectFromMap((Map<String, Object>) proposalResponse.getData(), Map.class);
			} catch (Exception e) {
				logger.info(
						"Error calling Proposal Details Client for getting Branch Id:-" + request.getApplicationId());

				e.printStackTrace();

			}

			Long branchId = null;
			if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get("branch_id"))) {
				branchId = Long.valueOf(proposalresp.get("branch_id").toString());
			}

			// =========================================Getting
			// Maker=====================================

			UsersRequest assignedMakerForName = new UsersRequest();
			assignedMakerForName.setId(request.getUserId());

			String makerName = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedMakerForName)) {

				try {
					logger.error("Into getting FP Name======>" + assignedMakerForName);
					UserResponse userResponseForName = userClient.getFPDetails(assignedMakerForName);
					FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
					makerName = fundProviderDetailsRequest.getFirstName() + " "
							+ (fundProviderDetailsRequest.getLastName() == null ? ""
									: fundProviderDetailsRequest.getLastName());
				} catch (Exception e) {
					logger.error("error while fetching FP name");
					e.printStackTrace();
				}
			}

			if ("null ".equals(makerName)) {
				makerName = "Maker";
			} else {
				makerName = makerName != null ? makerName : "Maker";
			}

			// ========================================================================================================

			// =========================================Getting
			// Checker=====================================

			UserResponse checkerResponse = null;
			try {
				checkerResponse = userClient.getEmailMobile(request.getNpUserId());
			} catch (Exception e) {
				logger.info("Something went wrong while calling Users client===>{}");
				e.printStackTrace();
			}
			UsersRequest assignedChecker = null;
			if (!CommonUtils.isObjectNullOrEmpty(checkerResponse)) {
				assignedChecker = MultipleJSONObjectHelper
						.getObjectFromMap((Map<String, Object>) checkerResponse.getData(), UsersRequest.class);
			}

			UsersRequest assignedCheckerForName = new UsersRequest();
			assignedCheckerForName.setId(request.getNpUserId());

			String checkerName = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedCheckerForName)) {

				try {
					logger.error("Into getting FP Name======>" + assignedCheckerForName);
					UserResponse userResponseForName = userClient.getFPDetails(assignedCheckerForName);
					FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
					checkerName = fundProviderDetailsRequest.getFirstName() + " "
							+ (fundProviderDetailsRequest.getLastName() == null ? ""
									: fundProviderDetailsRequest.getLastName());
				} catch (Exception e) {
					logger.error("error while fetching FP name");
					e.printStackTrace();
				}
			}

			if ("null ".equals(checkerName)) {
				checkerName = "Sir/Madam";
			} else {
				checkerName = checkerName != null ? checkerName : "Sir/Madam";
			}

			// ========================================================================================================

			SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
			parameters.put("maker_name", makerName != null ? makerName : "Maker");
			parameters.put("checker_name", checkerName != null ? checkerName : "NA");
			parameters.put("application_id", request.getApplicationId().toString());
			if (!CommonUtils.isObjectNullOrEmpty(lastModifiedDate)) {
				parameters.put("date", form.format(lastModifiedDate) != null ? form.format(lastModifiedDate) : "NA");
			} else {
				parameters.put("date", "NA");
			}

			if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get("loan_type"))) {

				parameters.put("product_type",
						proposalresp.get("loan_type").toString() != null ? proposalresp.get("loan_type").toString()
								: "NA");
			} else {

				parameters.put("product_type", "NA");

			}

			// For getting Fund Seeker's Name
			// =========================================================================================================
			String fsName = null;
			String address = null;
			List<DirectorBackgroundDetailRequest> NTBResponse = null;
			if (applicationRequest.getBusinessTypeId() == 2) {
				NTBResponse = directorBackgroundDetailsService
						.getDirectorBasicDetailsListForNTB(request.getApplicationId());
				if (!CommonUtils.isObjectNullOrEmpty(NTBResponse)) {
					int isMainDirector = 0;
					for (DirectorBackgroundDetailRequest director : NTBResponse) {
						if (!CommonUtils.isObjectNullOrEmpty(director) && director.getIsMainDirector()) {
							fsName = director.getDirectorsName() != null ? director.getDirectorsName() : "NA";
							address = director.getAddress();
							isMainDirector = 1;
						}
					}
					if (isMainDirector == 0) {
						fsName = NTBResponse.get(0).getDirectorsName() != null ? NTBResponse.get(0).getDirectorsName()
								: "NA";
					}
				} else {
					fsName = "NA";
				}
			} else {
				fsName = applicationRequest.getUserName() != null ? applicationRequest.getUserName() : "NA";
			}
			parameters.put("fs_name", fsName != null ? fsName : "NA");
			// =========================================================================================================

			UserResponse signUpuser = null;
			try {
				signUpuser = userClient.getEmailMobile(applicationRequest.getUserId());
			} catch (Exception e) {
				logger.info("Something went wrong while calling Users client===>{}");
				e.printStackTrace();
			}

			if (!CommonUtils.isObjectNullOrEmpty(signUpuser)) {
				UsersRequest signUpUser = MultipleJSONObjectHelper
						.getObjectFromMap((Map<String, Object>) signUpuser.getData(), UsersRequest.class);

				String mobile = signUpUser.getMobile();
				System.out.println("Mobile no:-" + mobile);
				parameters.put("mobile_no", mobile != null ? mobile : "NA");

			}

			if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
					&& applicationRequest.getBusinessTypeId() == 1){
				CorporateApplicantRequest applicantRequest = corporateapplicantService
						.getCorporateApplicant(request.getApplicationId());
				if (!CommonUtils.isObjectNullOrEmpty(applicantRequest)
						&& !CommonUtils.isObjectNullOrEmpty(applicantRequest.getFirstAddress())) {
					String premiseNumber = null;
					String streetName = null;
					String landMark = null;
					premiseNumber = applicantRequest.getFirstAddress().getPremiseNumber()!=null?applicantRequest.getFirstAddress().getPremiseNumber():"";
					streetName = applicantRequest.getFirstAddress().getStreetName()!=null?applicantRequest.getFirstAddress().getStreetName():"";
					landMark = applicantRequest.getFirstAddress().getLandMark()!=null?applicantRequest.getFirstAddress().getLandMark():"";
					address = premiseNumber.toString()+" "+streetName.toString()+" "+landMark.toString();
				}
			}
			
			parameters.put("address", address != null ? address : "NA");

			String subjcet = "Intimation : Sent Back - Application ID " + request.getApplicationId();
			if (!CommonUtils.isObjectNullOrEmpty(assignedChecker)
					&& !CommonUtils.isObjectNullOrEmpty(assignedChecker.getEmail())) {

				String toIds = assignedChecker.getEmail();
				logger.info("Email Sending TO CHECKER on sendMailWhenMakerAssignDDRToChecker===to==>{}", toIds);
				// ====================== MAIL TO CHECKER ======================
				createNotificationForEmail(toIds, request.getNpUserId().toString(), parameters,
						NotificationAlias.EMAIL_CHECKER_MAKER_REASSIGN_TO_CHECKER, subjcet);

			}

			if (!CommonUtils.isObjectNullOrEmpty(assignedChecker.getMobile())) {
				// System.out.println("Maker ID:---"+userObj.getEmail());
				Map<String, Object> smsParameters = new HashMap<String, Object>();
				String to = "91" + assignedChecker.getMobile();
				smsParameters.put("maker_name", makerName != null ? makerName : "Maker");
				smsParameters.put("fs_name", fsName != null ? fsName : "NA");
				if (!CommonUtils.isObjectNullOrEmpty(lastModifiedDate)) {
					smsParameters.put("date",
							form.format(lastModifiedDate) != null ? form.format(lastModifiedDate) : "NA");
				} else {
					smsParameters.put("date", "NA");
				}
				smsParameters.put("url", "www.bitly.com");

				sendSMSNotification(request.getNpUserId().toString(), smsParameters,
						NotificationAlias.SMS_CHECKER_MAKER_REASSIGN_TO_CHECKER, to);
			}

			if (!CommonUtils.isObjectNullOrEmpty(request.getNpUserId())) {
				// System.out.println("Maker ID:---"+userObj.getEmail());
				Map<String, Object> sysParameters = new HashMap<String, Object>();
				sysParameters.put("maker_name", makerName != null ? makerName : "Maker");
				sysParameters.put("fs_name", fsName != null ? fsName : "NA");

				sendSYSNotification(request.getApplicationId(), request.getNpUserId().toString(), sysParameters,
						NotificationAlias.SYS_CHECKER_MAKER_REASSIGN_TO_CHECKER, request.getNpUserId().toString(),
						request.getNpUserId().toString());
			}

			// ====================Sending Mail to HO when Maker Assigns DDR to
			// Checker=====================

			String subject = "Intimation: DDR Sent Back - Application ID " + request.getApplicationId();

			UserResponse hoResponse = userClient.getUserDetailByOrgRoleBranchId(applicationRequest.getNpOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.HEAD_OFFICER, branchId);
			List<Map<String, Object>> hoRespList = (List<Map<String, Object>>) hoResponse.getListData();
			if (!CommonUtils.isObjectNullOrEmpty(hoRespList)) {
				for (int i = 0; i < hoRespList.size(); i++) {
					UsersRequest hoObj = MultipleJSONObjectHelper.getObjectFromMap(hoRespList.get(i),
							UsersRequest.class);

					String name = null;

					try {
						logger.error("Into getting FP Name======>" + hoObj);
						UserResponse userResponseForName = userClient.getFPDetails(hoObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
										: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error("error while fetching FP name");
						e.printStackTrace();
					}

					if (!CommonUtils.isObjectNullOrEmpty(hoObj.getEmail())) {
						// System.out.println("Checker ID:---"+userObj.getEmail());
						String to = hoObj.getEmail();
						if ("null ".equals(name)) {
							name = "Sir/Madam";
						} else {
							name = name != null ? name : "Sir/Madam";
						}
						parameters.put("ho_name", name != null ? name : "Sir/Madam");
						createNotificationForEmail(to, request.getUserId().toString(), parameters,
								NotificationAlias.EMAIL_HO_MAKER_REASSIGN_TO_CHECKER, subject);
					}

					if (!CommonUtils.isObjectNullOrEmpty(hoObj.getMobile())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						String to = "91" + hoObj.getMobile();
						smsParameters.put("maker_name", makerName != null ? makerName : "Maker");
						smsParameters.put("fs_name", fsName != null ? fsName : "NA");
						if (!CommonUtils.isObjectNullOrEmpty(lastModifiedDate)) {
							smsParameters.put("date",
									form.format(lastModifiedDate) != null ? form.format(lastModifiedDate) : "NA");
						} else {
							smsParameters.put("date", "NA");
						}
						smsParameters.put("url", "www.bitly.com");

						sendSMSNotification(hoObj.getId().toString(), smsParameters,
								NotificationAlias.SMS_HO_MAKER_REASSIGN_TO_CHECKER, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(hoObj.getId())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put("maker_name", makerName != null ? makerName : "Maker");
						sysParameters.put("checker_name", checkerName != null ? checkerName : "Checker");
						sysParameters.put("product_type",
								proposalresp.get("loan_type").toString() != null
										? proposalresp.get("loan_type").toString()
										: "NA");
						sysParameters.put("fs_name", fsName != null ? fsName : "NA");

						sendSYSNotification(request.getApplicationId(), hoObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_HO_MAKER_REASSIGN_TO_CHECKER, hoObj.getId().toString(),
								hoObj.getId().toString());
					}

				}

			} else {
				logger.info("No HO found=================>");
			}

			// =========================================================================================

			// ====================Sending Mail to BO when Maker Assigns DDR to
			// Checker=====================

			subject = "Intimation: DDR Sent Back - Application ID " + request.getApplicationId();

			UserResponse boResponse = userClient.getUserDetailByOrgRoleBranchId(applicationRequest.getNpOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.BRANCH_OFFICER, branchId);
			List<Map<String, Object>> boRespList = (List<Map<String, Object>>) boResponse.getListData();
			if (!CommonUtils.isObjectNullOrEmpty(boRespList)) {
				for (int i = 0; i < boRespList.size(); i++) {
					UsersRequest boObj = MultipleJSONObjectHelper.getObjectFromMap(boRespList.get(i),
							UsersRequest.class);

					String name = null;

					try {
						logger.error("Into getting FP Name======>" + boObj);
						UserResponse userResponseForName = userClient.getFPDetails(boObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
										: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error("error while fetching FP name");
						e.printStackTrace();
					}

					if (!CommonUtils.isObjectNullOrEmpty(boObj.getEmail())) {
						// System.out.println("Checker ID:---"+userObj.getEmail());
						String to = boObj.getEmail();
						if ("null ".equals(name)) {
							name = "Sir/Madam";
						} else {
							name = name != null ? name : "Sir/Madam";
						}
						parameters.put("bo_name", name != null ? name : "Sir/Madam");
						createNotificationForEmail(to, request.getUserId().toString(), parameters,
								NotificationAlias.EMAIL_ALL_BO_MAKER_REASSIGN_TO_CHECKER, subject);
					}

					if (!CommonUtils.isObjectNullOrEmpty(boObj.getMobile())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						String to = "91" + boObj.getMobile();
						smsParameters.put("maker_name", makerName != null ? makerName : "Maker");
						smsParameters.put("fs_name", fsName != null ? fsName : "NA");
						if (!CommonUtils.isObjectNullOrEmpty(lastModifiedDate)) {
							smsParameters.put("date",
									form.format(lastModifiedDate) != null ? form.format(lastModifiedDate) : "NA");
						} else {
							smsParameters.put("date", "NA");
						}
						smsParameters.put("url", "www.bitly.com");

						sendSMSNotification(boObj.getId().toString(), smsParameters,
								NotificationAlias.SMS_ALL_BO_MAKER_REASSIGN_TO_CHECKER, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(boObj.getId())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put("maker_name", makerName != null ? makerName : "Maker");
						sysParameters.put("checker_name", checkerName != null ? checkerName : "Checker");
						sysParameters.put("product_type",
								proposalresp.get("loan_type").toString() != null
										? proposalresp.get("loan_type").toString()
										: "NA");
						sysParameters.put("fs_name", fsName != null ? fsName : "NA");

						sendSYSNotification(request.getApplicationId(), boObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_ALL_BO_MAKER_REASSIGN_TO_CHECKER, boObj.getId().toString(),
								boObj.getId().toString());
					}

				}

			} else {
				logger.info("No BO found=================>");
			}

			// =========================================================================================

		} catch (Exception e) {
			logger.info("Throw exception while sending mail to Checker/HO/BO when Maker Reassign DDR to Checker");
			e.printStackTrace();
		}
	}

	@Async
	public void sendEmailToCheckerWhenAdminMakerSendProductForApproval(ProductMasterTemp productMasterTemp, Long userId,
			String productType) {

		try {

			logger.info("Into sending Mail to Checker when Admin Maker send product for Approval===>{}");
			String subject = "Intimation: New Product - " + productMasterTemp.getName()+" for "+productType;
			Map<String, Object> mailParameters = new HashMap<String, Object>();

			mailParameters.put("product_name",
					productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
			mailParameters.put("product_type", productType != null ? productType : "NA");

			UsersRequest adminForMaker = new UsersRequest();
			adminForMaker.setId(userId);

			String adminMakerName = null;
			try {
				logger.error("Into getting FP Name======>" + adminForMaker);
				UserResponse userResponseForName = userClient.getFPDetails(adminForMaker);
				FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
						(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
				adminMakerName = fundProviderDetailsRequest.getFirstName() + " "
						+ (fundProviderDetailsRequest.getLastName() == null ? ""
								: fundProviderDetailsRequest.getLastName());
			} catch (Exception e) {
				logger.error("error while fetching FP name");
				e.printStackTrace();
			}

			if ("null ".equals(adminMakerName)) {
				adminMakerName = "Maker";
			} else {
				adminMakerName = adminMakerName != null ? adminMakerName : "Maker";
			}
			mailParameters.put("admin_maker", adminMakerName);
			UserResponse userResponse = userClient.getUserDetailByOrgRoleId(productMasterTemp.getUserOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.ADMIN_CHECKER);
			List<Map<String, Object>> usersRespList = (List<Map<String, Object>>) userResponse.getListData();

			String to = null;
			if (!CommonUtils.isObjectNullOrEmpty(usersRespList)) {
				for (int i = 0; i < usersRespList.size(); i++) {
					UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),
							UsersRequest.class);

					String name = null;

					try {
						logger.error("Into getting FP Name======>" + userObj);
						UserResponse userResponseForName = userClient.getFPDetails(userObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
										: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error("error while fetching FP name");
						e.printStackTrace();
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						to = userObj.getEmail();
						if ("null ".equals(name)) {
							mailParameters.put("admin_checker", "Sir/Madam");
						} else {
							mailParameters.put("admin_checker", name != null ? name : "Sir/Madam");
						}

						createNotificationForEmail(to, userId.toString(), mailParameters,
								NotificationAlias.EMAIL_ADMIN_CHECKER_ADMIN_MAKER_CREATES_PRODUCT, subject);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						to = "91" + userObj.getMobile();
						if ("null ".equals(name)) {
							smsParameters.put("admin_checker", "Sir/Madam");
						} else {
							smsParameters.put("admin_checker", name != null ? name : "Sir/Madam");
						}
						smsParameters.put("admin_maker", adminMakerName != null ? adminMakerName : "Maker");
						smsParameters.put("product_name",
								productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
						smsParameters.put("product_type", productType != null ? productType : "NA");
						smsParameters.put("url", "www.bitly.com");

						sendSMSNotification(userId.toString(), smsParameters,
								NotificationAlias.SMS_ADMIN_CHECKER_ADMIN_MAKER_CREATES_PRODUCT, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put("admin_maker", adminMakerName != null ? adminMakerName : "Maker");
						sysParameters.put("product_name",
								productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
						sysParameters.put("product_type", productType != null ? productType : "NA");

						sendSYSNotification(userId, userObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_ADMIN_CHECKER_ADMIN_MAKER_CREATES_PRODUCT,
								userObj.getId().toString(), userObj.getId().toString());
					}

				}

			} else {
				logger.info("No Admin Checker found=================>");
			}

		} catch (Exception e) {

			logger.info(
					"An exception getting while sending Mail to Checker when Admin Maker send product for Approval=============>{}");

			e.printStackTrace();
		}

	}

	@Async
	public void sendEmailToCheckerWhenAdminMakerResendProductForApproval(ProductMasterTemp productMasterTemp,
			Long userId, String productType) {

		try {

			logger.info("Into sending Mail to Checker when Admin Maker resend product for Approval===>{}");
			String subject = "Intimation: Re-sent Product - " + productMasterTemp.getName()+" for "+productType;
			Map<String, Object> mailParameters = new HashMap<String, Object>();

			mailParameters.put("product_name",
					productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
			mailParameters.put("date",
					productMasterTemp.getModifiedDate() != null ? productMasterTemp.getModifiedDate() : "NA");

			UsersRequest adminForMaker = new UsersRequest();
			adminForMaker.setId(userId);

			String adminMakerName = null;
			try {
				logger.error("Into getting FP Name======>" + adminForMaker);
				UserResponse userResponseForName = userClient.getFPDetails(adminForMaker);
				FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
						(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
				adminMakerName = fundProviderDetailsRequest.getFirstName() + " "
						+ (fundProviderDetailsRequest.getLastName() == null ? ""
								: fundProviderDetailsRequest.getLastName());
			} catch (Exception e) {
				logger.error("error while fetching FP name");
				e.printStackTrace();
			}

			if ("null ".equals(adminMakerName)) {
				adminMakerName = "Maker";
			} else {
				adminMakerName = adminMakerName != null ? adminMakerName : "Maker";
			}

			mailParameters.put("admin_maker", adminMakerName != null ? adminMakerName : "Maker");
			UserResponse userResponse = userClient.getUserDetailByOrgRoleId(productMasterTemp.getUserOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.ADMIN_CHECKER);
			List<Map<String, Object>> usersRespList = (List<Map<String, Object>>) userResponse.getListData();

			String to = null;
			if (!CommonUtils.isObjectNullOrEmpty(usersRespList)) {
				for (int i = 0; i < usersRespList.size(); i++) {
					UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),
							UsersRequest.class);

					String name = null;

					try {
						logger.error("Into getting FP Name======>" + userObj);
						UserResponse userResponseForName = userClient.getFPDetails(userObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
										: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error("error while fetching FP name");
						e.printStackTrace();
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						to = userObj.getEmail();
						if ("null ".equals(name)) {
							mailParameters.put("admin_checker", "Sir/Madam");
						} else {
							mailParameters.put("admin_checker", name != null ? name : "Sir/Madam");
						}
						createNotificationForEmail(to, userId.toString(), mailParameters,
								NotificationAlias.EMAIL_ADMIN_CHECKER_ADMIN_MAKER_RESENDS_PRODUCT, subject);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						to = "91" + userObj.getMobile();
						if ("null ".equals(name)) {
							smsParameters.put("admin_checker", "Sir/Madam");
						} else {
							smsParameters.put("admin_checker", name != null ? name : "Sir/Madam");
						}
						smsParameters.put("admin_maker", adminMakerName != null ? adminMakerName : "Maker");
						smsParameters.put("product_name",
								productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
						smsParameters.put("product_type", productType != null ? productType : "NA");
						smsParameters.put("url", "www.bitly.com");

						sendSMSNotification(userId.toString(), smsParameters,
								NotificationAlias.SMS_ADMIN_CHECKER_ADMIN_MAKER_RESENDS_PRODUCT, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put("admin_maker", adminMakerName != null ? adminMakerName : "Maker");
						sysParameters.put("product_name",
								productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
						sysParameters.put("product_type", productType != null ? productType : "NA");

						sendSYSNotification(userId, userObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_ADMIN_CHECKER_ADMIN_MAKER_RESENDS_PRODUCT,
								userObj.getId().toString(), userObj.getId().toString());
					}

				}

			} else {
				logger.info("No Admin Checker found=================>");
			}

		} catch (Exception e) {

			logger.info(
					"An exception getting while sending Mail to Checker when Admin Maker resend product for Approval=============>{}");

			e.printStackTrace();
		}

	}

	@Async
	public void sendEmailToMakerWhenAdminCheckerApprovedProduct(ProductMasterTemp productMasterTemp, Long userId,
			String productType) {

		try {

			logger.info("Into sending Mail to Maker when Admin Checker Approved product===>{}");

			Map<String, Object> mailParameters = new HashMap<String, Object>();

			mailParameters.put("product_name",
					productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
			mailParameters.put("product_type", productType != null ? productType : "NA");
			//String subject = "Intimation : " + productType + " - " + productMasterTemp.getName() + " Approved";
			String subject = "Intimation " + "New Product: "+ productMasterTemp.getName() + " Approved - "+"Product ID: "+productMasterTemp.getProductCode();
			UsersRequest adminForChecker = new UsersRequest();
			adminForChecker.setId(userId);
			String adminCheckerName = null;
			try {
				logger.error("Into getting FP Name======>" + adminForChecker);
				UserResponse userResponseForName = userClient.getFPDetails(adminForChecker);
				FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
						(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
				adminCheckerName = fundProviderDetailsRequest.getFirstName() + " "
						+ (fundProviderDetailsRequest.getLastName() == null ? ""
								: fundProviderDetailsRequest.getLastName());
			} catch (Exception e) {
				logger.error("error while fetching FP name");
				e.printStackTrace();
			}

			if ("null ".equals(adminCheckerName)) {
				adminCheckerName = "Checker";
			} else {
				adminCheckerName = adminCheckerName != null ? adminCheckerName : "Checker";
			}

			mailParameters.put("admin_checker", adminCheckerName);
			//==============================================================================================
			
			UserResponse assignedMakerResponse = null;
			try {
				assignedMakerResponse = userClient.getEmailMobile(productMasterTemp.getCreatedBy());
			} catch (Exception e) {
				logger.info("Something went wrong while calling Users client===>{}");
				e.printStackTrace();
			}
			UsersRequest assignedMaker = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedMakerResponse)) {
				assignedMaker = MultipleJSONObjectHelper
						.getObjectFromMap((Map<String, Object>) assignedMakerResponse.getData(), UsersRequest.class);
			}

			UsersRequest assignedMakerForName = new UsersRequest();
			assignedMakerForName.setId(productMasterTemp.getCreatedBy());

			String makerName = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedMakerForName)) {

				try {
					logger.error("Into getting FP Name======>" + assignedMakerForName);
					UserResponse userResponseForName = userClient.getFPDetails(assignedMakerForName);
					FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
					makerName = fundProviderDetailsRequest.getFirstName() + " "
							+ (fundProviderDetailsRequest.getLastName() == null ? ""
									: fundProviderDetailsRequest.getLastName());
				} catch (Exception e) {
					logger.error("error while fetching FP name");
					e.printStackTrace();
				}
			}

			// =======================================================================================
			
			String to = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getEmail())) {
				// System.out.println("Maker ID:---"+userObj.getEmail());
				to = assignedMaker.getEmail();
				if ("null ".equals(makerName)) {
					mailParameters.put("admin_maker", "Sir/Madam");
				} else {
					mailParameters.put("admin_maker", makerName != null ? makerName : "Sir/Madam");
				}

				createNotificationForEmail(to, userId.toString(), mailParameters,
						NotificationAlias.EMAIL_ADMIN_MAKER_PRODUCT_APPROVED_BY_CHECKER, subject);
			}

			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getMobile())) {
				// System.out.println("Maker ID:---"+userObj.getEmail());
				Map<String, Object> smsParameters = new HashMap<String, Object>();
				to = "91" + assignedMaker.getMobile();
				smsParameters.put("admin_checker", adminCheckerName != null ? adminCheckerName : "Checker");
				smsParameters.put("product_name",
						productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
				smsParameters.put("product_type", productType != null ? productType : "NA");
				smsParameters.put("url", "www.bitly.com");

				sendSMSNotification(userId.toString(), smsParameters,
						NotificationAlias.SMS_ADMIN_MAKER_PRODUCT_APPROVED_BY_CHECKER, to);
			}

			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getId())) {
				// System.out.println("Maker ID:---"+userObj.getEmail());
				Map<String, Object> sysParameters = new HashMap<String, Object>();
				sysParameters.put("admin_checker", adminCheckerName != null ? adminCheckerName : "Checker");
				sysParameters.put("product_name",
						productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
				sysParameters.put("product_type", productType != null ? productType : "NA");

				sendSYSNotification(userId, assignedMaker.getId().toString(), sysParameters,
						NotificationAlias.SYS_ADMIN_MAKER_PRODUCT_APPROVED_BY_CHECKER,
						assignedMaker.getId().toString(), assignedMaker.getId().toString());
			}
			
			
			/*UserResponse userResponse = userClient.getUserDetailByOrgRoleId(productMasterTemp.getUserOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.ADMIN_MAKER);
			List<Map<String, Object>> usersRespList = (List<Map<String, Object>>) userResponse.getListData();

			String to = null;
			if (!CommonUtils.isObjectNullOrEmpty(usersRespList)) {
				for (int i = 0; i < usersRespList.size(); i++) {
					UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),
							UsersRequest.class);

					String name = null;

					try {
						logger.error("Into getting FP Name======>" + userObj);
						UserResponse userResponseForName = userClient.getFPDetails(userObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
										: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error("error while fetching FP name");
						e.printStackTrace();
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						to = userObj.getEmail();
						if ("null ".equals(name)) {
							mailParameters.put("admin_maker", "Sir/Madam");
						} else {
							mailParameters.put("admin_maker", name != null ? name : "Sir/Madam");
						}

						createNotificationForEmail(to, userId.toString(), mailParameters,
								NotificationAlias.EMAIL_ADMIN_MAKER_PRODUCT_APPROVED_BY_CHECKER, subject);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						to = "91" + userObj.getMobile();
						smsParameters.put("admin_checker", adminCheckerName != null ? adminCheckerName : "Checker");
						smsParameters.put("product_name",
								productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
						smsParameters.put("product_type", productType != null ? productType : "NA");
						smsParameters.put("url", "www.bitly.com");

						sendSMSNotification(userId.toString(), smsParameters,
								NotificationAlias.SMS_ADMIN_MAKER_PRODUCT_APPROVED_BY_CHECKER, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put("admin_checker", adminCheckerName != null ? adminCheckerName : "Checker");
						sysParameters.put("product_name",
								productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
						sysParameters.put("product_type", productType != null ? productType : "NA");

						sendSYSNotification(userId, userObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_ADMIN_MAKER_PRODUCT_APPROVED_BY_CHECKER,
								userObj.getId().toString(), userObj.getId().toString());
					}

				}

			} else {
				logger.info("No Admin Maker found=================>");
			}*/

		} catch (Exception e) {

			logger.info(
					"An exception getting while sending Mail to Maker when Admin Checker Approved product=============>{}");

			e.printStackTrace();
		}

	}

	@Async
	public void sendEmailToMakerWhenAdminCheckerRevertedProduct(ProductMasterTemp productMasterTemp, Long userId,
			String productType) {

		try {

			logger.info("Into sending Mail to Maker when Admin Checker reverted product===>{}");
			String subject = "Intimation :Re-Sent Product - " + productMasterTemp.getName() + " - Modification";
			Map<String, Object> mailParameters = new HashMap<String, Object>();

			mailParameters.put("product_name",
					productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
			mailParameters.put("product_type", productType != null ? productType : "NA");

			UsersRequest adminForChecker = new UsersRequest();
			adminForChecker.setId(userId);

			String adminCheckerName = null;
			try {
				logger.error("Into getting FP Name======>" + adminForChecker);
				UserResponse userResponseForName = userClient.getFPDetails(adminForChecker);
				FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
						(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
				adminCheckerName = fundProviderDetailsRequest.getFirstName() + " "
						+ (fundProviderDetailsRequest.getLastName() == null ? ""
								: fundProviderDetailsRequest.getLastName());
			} catch (Exception e) {
				logger.error("error while fetching FP name");
				e.printStackTrace();
			}

			if ("null ".equals(adminCheckerName)) {
				adminCheckerName = "Checker";
			} else {
				adminCheckerName = adminCheckerName != null ? adminCheckerName : "Checker";
			}

			mailParameters.put("admin_checker", adminCheckerName);
           //=========================================================================================================
			
			UserResponse assignedMakerResponse = null;
			try {
				assignedMakerResponse = userClient.getEmailMobile(productMasterTemp.getCreatedBy());
			} catch (Exception e) {
				logger.info("Something went wrong while calling Users client===>{}");
				e.printStackTrace();
			}
			UsersRequest assignedMaker = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedMakerResponse)) {
				assignedMaker = MultipleJSONObjectHelper
						.getObjectFromMap((Map<String, Object>) assignedMakerResponse.getData(), UsersRequest.class);
			}

			UsersRequest assignedMakerForName = new UsersRequest();
			assignedMakerForName.setId(productMasterTemp.getCreatedBy());

			String makerName = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedMakerForName)) {

				try {
					logger.error("Into getting FP Name======>" + assignedMakerForName);
					UserResponse userResponseForName = userClient.getFPDetails(assignedMakerForName);
					FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
					makerName = fundProviderDetailsRequest.getFirstName() + " "
							+ (fundProviderDetailsRequest.getLastName() == null ? ""
									: fundProviderDetailsRequest.getLastName());
				} catch (Exception e) {
					logger.error("error while fetching FP name");
					e.printStackTrace();
				}
			}

			// =======================================================================================
			String to = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getEmail())) {
				// System.out.println("Maker ID:---"+userObj.getEmail());
				to = assignedMaker.getEmail();
				if ("null ".equals(makerName)) {
					mailParameters.put("admin_maker", "Sir/Madam");
				} else {
					mailParameters.put("admin_maker", makerName != null ? makerName : "Sir/Madam");
				}
				createNotificationForEmail(to, userId.toString(), mailParameters,
						NotificationAlias.EMAIL_ADMIN_MAKER_PRODUCT_REVERTED_BY_CHECKER, subject);
			}

			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getMobile())) {
				// System.out.println("Maker ID:---"+userObj.getEmail());
				Map<String, Object> smsParameters = new HashMap<String, Object>();
				to = "91" + assignedMaker.getMobile();
				smsParameters.put("admin_checker", adminCheckerName != null ? adminCheckerName : "Checker");
				smsParameters.put("product_name",
						productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
				smsParameters.put("product_type", productType != null ? productType : "NA");
				smsParameters.put("url", "www.bitly.com");

				sendSMSNotification(userId.toString(), smsParameters,
						NotificationAlias.SMS_ADMIN_MAKER_PRODUCT_REVERTED_BY_CHECKER, to);
			}

			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getId())) {
				// System.out.println("Maker ID:---"+userObj.getEmail());
				Map<String, Object> sysParameters = new HashMap<String, Object>();
				sysParameters.put("admin_checker", adminCheckerName != null ? adminCheckerName : "Checker");
				sysParameters.put("product_name",
						productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
				sysParameters.put("product_type", productType != null ? productType : "NA");

				sendSYSNotification(userId, assignedMaker.getId().toString(), sysParameters,
						NotificationAlias.SYS_ADMIN_MAKER_PRODUCT_REVERTED_BY_CHECKER,
						assignedMaker.getId().toString(), assignedMaker.getId().toString());
			}
			
			/*UserResponse userResponse = userClient.getUserDetailByOrgRoleId(productMasterTemp.getUserOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.ADMIN_MAKER);
			List<Map<String, Object>> usersRespList = (List<Map<String, Object>>) userResponse.getListData();

			String to = null;
			if (!CommonUtils.isObjectNullOrEmpty(usersRespList)) {
				for (int i = 0; i < usersRespList.size(); i++) {
					UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),
							UsersRequest.class);

					String name = null;

					try {
						logger.error("Into getting FP Name======>" + userObj);
						UserResponse userResponseForName = userClient.getFPDetails(userObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
										: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error("error while fetching FP name");
						e.printStackTrace();
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						to = userObj.getEmail();
						if ("null ".equals(name)) {
							mailParameters.put("admin_maker", "Sir/Madam");
						} else {
							mailParameters.put("admin_maker", name != null ? name : "Sir/Madam");
						}
						createNotificationForEmail(to, userId.toString(), mailParameters,
								NotificationAlias.EMAIL_ADMIN_MAKER_PRODUCT_REVERTED_BY_CHECKER, subject);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						to = "91" + userObj.getMobile();
						smsParameters.put("admin_checker", adminCheckerName != null ? adminCheckerName : "Checker");
						smsParameters.put("product_name",
								productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
						smsParameters.put("product_type", productType != null ? productType : "NA");
						smsParameters.put("url", "www.bitly.com");

						sendSMSNotification(userId.toString(), smsParameters,
								NotificationAlias.SMS_ADMIN_MAKER_PRODUCT_REVERTED_BY_CHECKER, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put("admin_checker", adminCheckerName != null ? adminCheckerName : "Checker");
						sysParameters.put("product_name",
								productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
						sysParameters.put("product_type", productType != null ? productType : "NA");

						sendSYSNotification(userId, userObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_ADMIN_MAKER_PRODUCT_REVERTED_BY_CHECKER,
								userObj.getId().toString(), userObj.getId().toString());
					}

				}

			} else {
				logger.info("No Admin Maker found=================>");
			}*/

		} catch (Exception e) {

			logger.info("An exception getting while sending Mail to Maker when Admin Checker reverted product=============>{}");

			e.printStackTrace();
		}

	}

	@Async
	public void sendEmailToMakerHOBOWhenCheckerSanctionLoan(LoanSanctionDomain loanSanctionDomainOld) {
		try {

			logger.info("Into sending Mail to Maker/HO/BO when Checker sanction loan===>{}");
			String subject = "Intimation: Sanction - Application ID " + loanSanctionDomainOld.getApplicationId();
			Map<String, Object> mailParameters = new HashMap<String, Object>();
			LoanApplicationRequest applicationRequest = loanApplicationService
					.getFromClient(loanSanctionDomainOld.getApplicationId());

			ProposalMappingResponse proposalResponse = null;
			Map<String, Object> proposalresp = null;
			try {
				logger.info("Calling Proposal details client :-" + loanSanctionDomainOld.getApplicationId());
				proposalResponse = proposalDetailsClient.getInPricipleById(loanSanctionDomainOld.getApplicationId());
				logger.info("Got Inprinciple response from Proposal Details Client" + proposalResponse);
				proposalresp = MultipleJSONObjectHelper
						.getObjectFromMap((Map<String, Object>) proposalResponse.getData(), Map.class);
			} catch (Exception e) {
				logger.info("Error calling Proposal Details Client" + loanSanctionDomainOld.getApplicationId());

				e.printStackTrace();

			}

			String productType = null;
			if (!CommonUtils.isObjectNullOrEmpty(applicationRequest)) {
				if (!CommonUtils.isObjectNullOrEmpty(applicationRequest.getProductId())) {
					productType = CommonUtils.LoanType.getType(applicationRequest.getProductId()).getName();
				} else {
					productType = "NA";
				}
			} else {
				productType = "NA";
			}

			SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
			String fpName = proposalresp.get("fp_name") != null ? proposalresp.get("fp_name").toString() : "NA";
			mailParameters.put("fp_name", fpName != null ? fpName : "NA");
			mailParameters.put("product_type", productType != null ? productType : "NA");
			mailParameters.put("loan_amount",
					applicationRequest.getLoanAmount() != null
							? Double.valueOf(applicationRequest.getLoanAmount().toString())
							: "NA");
			mailParameters.put("processing_fees",
					loanSanctionDomainOld.getProcessingFee() != null ? loanSanctionDomainOld.getProcessingFee() : "NA");
			mailParameters.put("amount",
					loanSanctionDomainOld.getSanctionAmount() != null ? loanSanctionDomainOld.getSanctionAmount()
							: "NA");
			mailParameters.put("interest_rate",
					loanSanctionDomainOld.getRoi() != null ? loanSanctionDomainOld.getRoi() : "NA");
			mailParameters.put("tenure",
					loanSanctionDomainOld.getTenure() != null ? loanSanctionDomainOld.getTenure() : "NA");
			mailParameters.put("date",
					form.format(loanSanctionDomainOld.getSanctionDate()) != null
							? form.format(loanSanctionDomainOld.getSanctionDate())
							: "NA");

			// For getting Fund Seeker's Name
			// =========================================================================================================
			String fsName = null;
			List<DirectorBackgroundDetailRequest> NTBResponse = null;
			if (applicationRequest.getBusinessTypeId() == 2) {
				NTBResponse = directorBackgroundDetailsService
						.getDirectorBasicDetailsListForNTB(loanSanctionDomainOld.getApplicationId());
				if (!CommonUtils.isObjectNullOrEmpty(NTBResponse)) {
					int isMainDirector = 0;
					for (DirectorBackgroundDetailRequest director : NTBResponse) {
						if (!CommonUtils.isObjectNullOrEmpty(director) && director.getIsMainDirector()) {
							fsName = director.getDirectorsName() != null ? director.getDirectorsName() : "NA";
							isMainDirector = 1;
						}
					}
					if (isMainDirector == 0) {
						fsName = NTBResponse.get(0).getDirectorsName() != null ? NTBResponse.get(0).getDirectorsName()
								: "NA";
					}
				} else {
					fsName = "NA";
				}
			} else {
				fsName = applicationRequest.getUserName() != null ? applicationRequest.getUserName() : "NA";
			}
			mailParameters.put("fs_name", fsName != null ? fsName : "NA");
			// =========================================================================================================

			UsersRequest checkerForName = new UsersRequest();
			checkerForName.setId(Long.valueOf(loanSanctionDomainOld.getModifiedBy()));

			String checkerName = null;
			try {
				logger.error("Into getting FP Name======>" + checkerForName);
				UserResponse userResponseForName = userClient.getFPDetails(checkerForName);
				FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
						(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
				checkerName = fundProviderDetailsRequest.getFirstName() + " "
						+ (fundProviderDetailsRequest.getLastName() == null ? ""
								: fundProviderDetailsRequest.getLastName());
				if ("null ".equals(checkerName)) {
					checkerName = "Checker";
				} else {
					checkerName = checkerName != null ? checkerName : "Checker";
				}
				mailParameters.put("checker_name", checkerName);
			} catch (Exception e) {
				logger.error("error while fetching FP name");
				e.printStackTrace();
			}

			UserResponse makerResponse = null;
			try {
				makerResponse = userClient.getEmailMobile(applicationRequest.getFpMakerId());
			} catch (Exception e) {
				logger.info("Something went wrong while calling Users client===>{}");
				e.printStackTrace();
			}

			UsersRequest maker = null;

			if (!CommonUtils.isObjectNullOrEmpty(makerResponse)) {
				maker = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) makerResponse.getData(),
						UsersRequest.class);
			}

			UsersRequest makerForName = new UsersRequest();
			makerForName.setId(applicationRequest.getFpMakerId());

			String makerName = null;
			try {
				logger.error("Into getting FP Name======>" + makerForName);
				UserResponse userResponseForName = userClient.getFPDetails(makerForName);
				FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
						(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
				makerName = fundProviderDetailsRequest.getFirstName() + " "
						+ (fundProviderDetailsRequest.getLastName() == null ? ""
								: fundProviderDetailsRequest.getLastName());
				if ("null ".equals(makerName)) {
					makerName = "Sir/Madam";
				} else {
					makerName = makerName != null ? makerName : "Sir/Madam";
				}
				mailParameters.put("maker_name", makerName);
			} catch (Exception e) {
				logger.error("error while fetching FP name");
				e.printStackTrace();
			}

			// ===========================Email to
			// Maker======================================

			if (!CommonUtils.isObjectNullOrEmpty(maker) && !CommonUtils.isObjectNullOrEmpty(maker.getEmail())) {
				String toIds = maker.getEmail();
				logger.info("Email Sending TO MAKER when Checker sanction loan===to==>{}", toIds);

				/*
				 * // ====================== MAIL TO MAKER old code======================
				 * createNotificationForEmail(toIds, workflowRequest.getUserId().toString(),
				 * parameters, NotificationAlias.MAIL_MKR_DDR_APPROVE, NotificationType.EMAIL,
				 * subjcet);
				 */
				// ====================== MAIL TO MAKER by new code ======================
				createNotificationForEmail(toIds, applicationRequest.getFpMakerId().toString(), mailParameters,
						NotificationAlias.EMAIL_MAKER_AFTER_CHECKER_SUBMIT_SANCTION_POPUP, subject);

			}

			if (!CommonUtils.isObjectNullOrEmpty(applicationRequest.getFpMakerId())) {
				// System.out.println("Maker ID:---"+userObj.getEmail());
				Map<String, Object> sysParameters = new HashMap<String, Object>();

				sysParameters.put("checker_name", checkerName != null ? checkerName : "Checker");
				sysParameters.put("fs_name", fsName != null ? fsName : "NA");
				sysParameters.put("product_type", productType != null ? productType : "NA");

				sendSYSNotification(loanSanctionDomainOld.getApplicationId(),
						applicationRequest.getFpMakerId().toString(), sysParameters,
						NotificationAlias.SYS_MAKER_AFTER_CHECKER_SUBMIT_SANCTION_POPUP,
						applicationRequest.getFpMakerId().toString(), applicationRequest.getFpMakerId().toString());
			}

			// ==================================================================================

			// ===========================Email to HO======================================

			Long branchId = null;
			if (!CommonUtils.isObjectNullOrEmpty(loanSanctionDomainOld.getBranch())) {
				branchId = loanSanctionDomainOld.getBranch();
			}

			UserResponse userResponse = userClient.getUserDetailByOrgRoleBranchId(applicationRequest.getNpOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.HEAD_OFFICER, branchId);
			List<Map<String, Object>> usersRespList = (List<Map<String, Object>>) userResponse.getListData();

			String to = null;
			if (!CommonUtils.isObjectNullOrEmpty(usersRespList)) {
				for (int i = 0; i < usersRespList.size(); i++) {
					UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),
							UsersRequest.class);

					String name = null;

					try {
						logger.error("Into getting FP Name======>" + userObj);
						UserResponse userResponseForName = userClient.getFPDetails(userObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
										: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error("error while fetching FP name");
						e.printStackTrace();
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						to = userObj.getEmail();
						if ("null ".equals(name)) {
							mailParameters.put("ho_name", "Sir/Madam");
						} else {
							mailParameters.put("ho_name", name != null ? name : "Sir/Madam");
						}

						createNotificationForEmail(to, userObj.getId().toString(), mailParameters,
								NotificationAlias.EMAIL_HO_CHECKER_SANCTIONED, subject);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						to = "91" + userObj.getMobile();
						smsParameters.put("checker_name", checkerName != null ? checkerName : "Checker");
						smsParameters.put("fs_name", fsName != null ? fsName : "NA");
						smsParameters.put("product_type", productType != null ? productType : "NA");
						smsParameters.put("loan_amount",
								applicationRequest.getLoanAmount() != null
										? Double.valueOf(applicationRequest.getLoanAmount().toString())
										: "NA");

						smsParameters.put("url", "www.bitly.com");

						sendSMSNotification(userObj.getId().toString(), smsParameters,
								NotificationAlias.SMS_HO_CHECKER_SANCTIONED, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put("checker_name", checkerName != null ? checkerName : "Checker");
						sysParameters.put("fs_name", fsName != null ? fsName : "NA");
						sysParameters.put("product_type", productType != null ? productType : "NA");

						sendSYSNotification(loanSanctionDomainOld.getApplicationId(), userObj.getId().toString(),
								sysParameters, NotificationAlias.SYS_HO_CHECKER_SANCTIONED, userObj.getId().toString(),
								userObj.getId().toString());
					}

				}

			} else {
				logger.info("No HO found=================>");
			}
			// ==========================================================================================

			// ===========================Email to BO======================================

			userResponse = userClient.getUserDetailByOrgRoleBranchId(applicationRequest.getNpOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.BRANCH_OFFICER, branchId);
			List<Map<String, Object>> boRespList = (List<Map<String, Object>>) userResponse.getListData();

			to = null;
			if (!CommonUtils.isObjectNullOrEmpty(boRespList)) {
				for (int i = 0; i < boRespList.size(); i++) {
					UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(boRespList.get(i),
							UsersRequest.class);

					String name = null;

					try {
						logger.error("Into getting FP Name======>" + userObj);
						UserResponse userResponseForName = userClient.getFPDetails(userObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
										: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error("error while fetching FP name");
						e.printStackTrace();
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						to = userObj.getEmail();
						if ("null ".equals(name)) {
							mailParameters.put("bo_name", "Sir/Madam");
						} else {
							mailParameters.put("bo_name", name != null ? name : "Sir/Madam");
						}

						createNotificationForEmail(to, userObj.getId().toString(), mailParameters,
								NotificationAlias.EMAIL_ALL_BO_CHECKER_SANCTIONED, subject);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						to = "91" + userObj.getMobile();
						smsParameters.put("checker_name", checkerName != null ? checkerName : "Checker");
						smsParameters.put("fs_name", fsName != null ? fsName : "NA");
						smsParameters.put("product_type", productType != null ? productType : "NA");
						smsParameters.put("loan_amount",
								applicationRequest.getLoanAmount() != null
										? Double.valueOf(applicationRequest.getLoanAmount().toString())
										: "NA");

						smsParameters.put("url", "www.bitly.com");

						sendSMSNotification(userObj.getId().toString(), smsParameters,
								NotificationAlias.SMS_ALL_BO_CHECKER_SANCTIONED, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
						// System.out.println("Maker ID:---"+userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put("checker_name", checkerName != null ? checkerName : "Checker");
						sysParameters.put("fs_name", fsName != null ? fsName : "NA");
						sysParameters.put("product_type", productType != null ? productType : "NA");

						sendSYSNotification(loanSanctionDomainOld.getApplicationId(), userObj.getId().toString(),
								sysParameters, NotificationAlias.SYS_ALL_BO_CHECKER_SANCTIONED,
								userObj.getId().toString(), userObj.getId().toString());
					}

				}

			} else {
				logger.info("No BO found=================>");
			}
			// ==========================================================================================

		} catch (Exception e) {
			logger.info(
					"An exception getting while sending mail to Maker/HO/BO when Checker sanction loan=============>{}");

			e.printStackTrace();
		}

	}

	@Async
	public void sendEmailToFSWhenCheckerSanctionLoan(LoanSanctionDomain loanSanctionDomainOld) {

		try {

			logger.info("Into sending Mail to FS when Checker sanction loan===>{}");
			String subject = "Congratulations - Your Loan Has Been Sanctioned!!!";
			Map<String, Object> mailParameters = new HashMap<String, Object>();
			LoanApplicationRequest applicationRequest = loanApplicationService
					.getFromClient(loanSanctionDomainOld.getApplicationId());

			ProposalMappingResponse proposalResponse = null;
			Map<String, Object> proposalresp = null;
			try {
				logger.info("Calling Proposal details client :-" + loanSanctionDomainOld.getApplicationId());
				proposalResponse = proposalDetailsClient.getInPricipleById(loanSanctionDomainOld.getApplicationId());
				logger.info("Got Inprinciple response from Proposal Details Client" + proposalResponse);
				proposalresp = MultipleJSONObjectHelper
						.getObjectFromMap((Map<String, Object>) proposalResponse.getData(), Map.class);
			} catch (Exception e) {
				logger.info("Error calling Proposal Details Client" + loanSanctionDomainOld.getApplicationId());

				e.printStackTrace();

			}

			String productType = null;
			if (!CommonUtils.isObjectNullOrEmpty(applicationRequest)) {
				if (!CommonUtils.isObjectNullOrEmpty(applicationRequest.getProductId())) {
					productType = CommonUtils.LoanType.getType(applicationRequest.getProductId()).getName();
				} else {
					productType = "";
				}
			} else {
				productType = "";
			}

			SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");
			String fpName = proposalresp.get("organisationName") != null ? proposalresp.get("organisationName").toString() : "";
			mailParameters.put("fp_name", fpName != null ? fpName : "");
			mailParameters.put("product_type", productType != null ? productType : "");
			mailParameters.put("loan_amount",
					applicationRequest.getLoanAmount() != null
							? Double.valueOf(applicationRequest.getLoanAmount().toString())
							: "NA");
			mailParameters.put("processing_fees",
					loanSanctionDomainOld.getProcessingFee() != null ? loanSanctionDomainOld.getProcessingFee() : "");
			mailParameters.put("amount",
					loanSanctionDomainOld.getSanctionAmount() != null ? loanSanctionDomainOld.getSanctionAmount()
							: "NA");
			mailParameters.put("interest_rate",
					loanSanctionDomainOld.getRoi() != null ? loanSanctionDomainOld.getRoi() : "");
			mailParameters.put("tenure",
					loanSanctionDomainOld.getTenure() != null ? loanSanctionDomainOld.getTenure() : "");
			mailParameters.put("date",
					form.format(loanSanctionDomainOld.getSanctionDate()) != null
							? form.format(loanSanctionDomainOld.getSanctionDate())
							: "");
			mailParameters.put("remarks",
					loanSanctionDomainOld.getRemark() != null ? loanSanctionDomainOld.getRemark() : "");
			
			// For getting Fund Seeker's Name
			// =========================================================================================================
			String fsName = null;
			List<DirectorBackgroundDetailRequest> NTBResponse = null;
			if (applicationRequest.getBusinessTypeId() == 2) {
				NTBResponse = directorBackgroundDetailsService
						.getDirectorBasicDetailsListForNTB(loanSanctionDomainOld.getApplicationId());
				if (!CommonUtils.isObjectNullOrEmpty(NTBResponse)) {
					int isMainDirector = 0;
					for (DirectorBackgroundDetailRequest director : NTBResponse) {
						if (!CommonUtils.isObjectNullOrEmpty(director) && director.getIsMainDirector()) {
							fsName = director.getDirectorsName() != null ? director.getDirectorsName() : "Sir/Madam";
							isMainDirector = 1;
						}
					}
					if (isMainDirector == 0) {
						fsName = NTBResponse.get(0).getDirectorsName() != null ? NTBResponse.get(0).getDirectorsName()
								: "Sir/Madam";
					}
				} else {
					fsName = "Sir/Madam";
				}
			} else {
				fsName = applicationRequest.getUserName() != null ? applicationRequest.getUserName() : "Sir/Madam";
			}
			mailParameters.put("fs_name", fsName != null ? fsName : "Sir/Madam");
			// =========================================================================================================

			UserResponse fsResponse = null;
			try {
				fsResponse = userClient.getEmailMobile(applicationRequest.getUserId());
			} catch (Exception e) {
				logger.info("Something went wrong while calling Users client===>{}");
				e.printStackTrace();
			}

			UsersRequest fs = null;

			if (!CommonUtils.isObjectNullOrEmpty(fsResponse)) {
				fs = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) fsResponse.getData(),
						UsersRequest.class);
			}

			// ===========================Email to FS======================================

			if (!CommonUtils.isObjectNullOrEmpty(fs) && !CommonUtils.isObjectNullOrEmpty(fs.getEmail())) {
				String toIds = fs.getEmail();
				logger.info("Email Sending TO MAKER when Checker sanction loan===to==>{}", toIds);

				/*
				 * // ====================== MAIL TO MAKER old code======================
				 * createNotificationForEmail(toIds, workflowRequest.getUserId().toString(),
				 * parameters, NotificationAlias.MAIL_MKR_DDR_APPROVE, NotificationType.EMAIL,
				 * subjcet);
				 */
				// ====================== MAIL TO MAKER by new code ======================
				createNotificationForEmail(toIds, applicationRequest.getUserId().toString(), mailParameters,
						NotificationAlias.EMAIL_FS_CHECKER_SANCTIONED, subject);

			}
			if (!CommonUtils.isObjectNullOrEmpty(fs.getMobile())) {
				// System.out.println("Maker ID:---"+userObj.getEmail());
				Map<String, Object> smsParameters = new HashMap<String, Object>();
				String to = "91" + fs.getMobile();
				smsParameters.put("fs_name", fsName != null ? fsName : "Sir/Madam");
				smsParameters.put("fp_name", fpName != null ? fpName : "");
				smsParameters.put("product_type", productType != null ? productType : "");
				smsParameters.put("url", "www.bitly.com");

				sendSMSNotification(applicationRequest.getUserId().toString(), smsParameters,
						NotificationAlias.SMS_FS_CHECKER_SANCTIONED, to);
			}
			if (!CommonUtils.isObjectNullOrEmpty(applicationRequest.getUserId())) {
				// System.out.println("Maker ID:---"+userObj.getEmail());
				Map<String, Object> sysParameters = new HashMap<String, Object>();

				sysParameters.put("fs_name", fsName != null ? fsName : "Sir/Madam");
				sysParameters.put("fp_name", fpName != null ? fpName : "");
				sysParameters.put("product_type", productType != null ? productType : "");

				sendSYSNotification(loanSanctionDomainOld.getApplicationId(), applicationRequest.getUserId().toString(),
						sysParameters, NotificationAlias.SYS_FS_CHECKER_SANCTIONED,
						applicationRequest.getUserId().toString(), applicationRequest.getUserId().toString());
			}

			// ==================================================================================

		} catch (Exception e) {
			logger.info("An exception getting while sending mail to FS when Checker sanction loan=============>{}");

			e.printStackTrace();
		}

	}

	private void createNotificationForEmail(String toNo, String userId, Map<String, Object> mailParameters,
			Long templateId, String emailSubject) throws NotificationException {
		logger.info("Inside send notification===>{}" + toNo);
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
		logger.info("Outside send notification===>{}" + toNo);
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

	private void sendSYSNotification(Long applicationId, String toUserId, Map<String, Object> parameters,
			Long templateId, String fromId, String... to) throws NotificationException {
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
		notification.setApplicationId(applicationId);
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
