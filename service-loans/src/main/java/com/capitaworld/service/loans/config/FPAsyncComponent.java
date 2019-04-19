package com.capitaworld.service.loans.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capitaworld.api.payment.gateway.model.GatewayResponse;
import com.capitaworld.api.reports.ReportRequest;
import com.capitaworld.client.payment.gateway.GatewayClient;
import com.capitaworld.client.reports.ReportsClient;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.capitaworld.service.gst.client.GstClient;
import com.capitaworld.service.loans.domain.fundprovider.ProductMasterTemp;
import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.domain.sanction.LoanSanctionDomain;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.NhbsApplicationRequest;
import com.capitaworld.service.loans.model.PaymentRequest;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.corporate.CorporateFinalInfoRequest;
import com.capitaworld.service.loans.model.retail.RetailApplicantRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.ApplicationProposalMappingService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateFinalInfoService;
import com.capitaworld.service.loans.service.fundseeker.corporate.DirectorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
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
import com.capitaworld.service.users.model.UserOrganisationRequest;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;

@Component
public class FPAsyncComponent {

	private static final Logger logger = LoggerFactory.getLogger(FPAsyncComponent.class.getName());

	private static final String SUBJECT_INTIMATION_NEW_PROPOSAL = "Intimation : New Proposal ";

	private static final String PARAMETERS_PRODUCT_TYPE = "product_type";
	private static final String PARAMETERS_INTEREST_RATE = "interest_rate";
	private static final String PARAMETERS_MOBILE_NO = "mobile_no";
	private static final String PARAMETERS_MAKER_NAME = "maker_name";
	private static final String PARAMETERS_ADMIN_CHECKER = "admin_checker";
	private static final String PARAMETERS_SIR_MADAM = "Sir/Madam";
	private static final String PARAMETERS_ADMIN_MAKER = "admin_maker";
	private static final String PARAMETERS_PRODUCT_NAME = "product_name";
	private static final String PARAMETERS_BO_NAME = "bo_name";
	private static final String PARAMETERS_HO_NAME = "ho_name";
	private static final String PARAMETERS_CHECKER_NAME = "checker_name";

	private static final String LITERAL_NULL = "null ";
	private static final String LITERAL_MAKER = "Maker";
	private static final String LITERAL_CHECKER = "Checker";

	private static final String BRANCH_ID = "branch_id";
	private static final String SOMETHING_WENT_WRONG_WHILE_CALLING_USERS_CLIENT = "Something went wrong while calling Users client===>{}";
	private static final String GOT_INPRINCIPLE_RESPONSE_FROM_PROPOSAL_DETAILS_CLIENT = "Got Inprinciple response from Proposal Details Client";
	private static final String CALLING_PROPOSAL_DETAILS_CLIENT_FOR_GETTING_BRANCH_ID = "Calling Proposal details client for getting Branch Id:-";

	private static final String ERROR_WHILE_FETCHING_FP_NAME = "error while fetching FP name : ";
	private static final String ERROR_CALLING_PROPOSAL_DETAILS_CLIENT_FOR_GETTING_BRANCH_ID = "Error calling Proposal Details Client for getting Branch Id:-";

	private static final String MSG_INTO_GETTING_FP_NAME = "Into getting FP Name======>";
	private static final String MSG_NO_BO_FOUND = "No BO found=================>";
	private static final String MSG_NO_HO_FOUND = "No HO found=================>";
	private static final String MSG_MOBILE_NO = "Mobile no:-";
	private static final String MSG_MAKER_ID = "Maker ID:---";
	private static final String MSG_CHECKER_ID = "Checker ID:---";

	private static final String URL_WWW_PSBLOANS_COM = "https://www.psbloansin59minutes.com";
	private static final String DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy";

    /*By Maaz*/
    private static final String PROPOSAL_ID="proposalId";
	@Autowired
	private NotificationClient notificationClient;

	@Autowired
	private UsersClient userClient;

	@Autowired
	private CorporateApplicantService corporateapplicantService;

	@Autowired
	private RetailApplicantService retailapplicantService;

	@Autowired
	private LoanApplicationService loanApplicationService;

	@Autowired
	private ProposalDetailsClient proposalDetailsClient;

	@Autowired
	private OneFormClient oneFormClient;

	@Autowired
	private DirectorBackgroundDetailsService directorBackgroundDetailsService;

	@Autowired
	public GstClient gstClient;
	
	@Autowired
	ApplicationProposalMappingRepository appProposalMappingRepo;
	
	@Autowired
	private CorporateFinalInfoService corporateFinalInfoService;

	@Autowired
	private ApplicationProposalMappingService appPropMappService;

	@Autowired
	private ReportsClient reportsClient;

	@Autowired
	private CamReportPdfDetailsService camReportPdfDetailsService;

	@Autowired
	private ProposalDetailsRepository proposalDetailsRepository;

	@Autowired
	private GatewayClient gatewayClient;
	
	@Autowired
	private Environment environment;

	private static final String EMAIL_ADDRESS_FROM = "no-reply@capitaworld.com";

	private static final String PSB_URL= "https://www.psbloansin59minutes.com";

	@Value("${capitaworld.sidbi.mail.to.maker.checker}")
	private Boolean mailToMakerChecker;
	/*====================This emails are triggered from new payment module.==============================*/
	// ====Sending Mail to all Makers after FS receives In-principle Approval=======changed for the multiple bank=
	@Async
	public void sendEmailToAllMakersWhenFSRecievesInPrinciple(Map<String, Object> proposalresp,
															  PaymentRequest paymentRequest, Long userId, Long orgId) {
		if (mailToMakerChecker) {

			try {

				logger.info("Into sending Mail to all Makers after FS gets In-Principle Approval===>{}");

				String subject = SUBJECT_INTIMATION_NEW_PROPOSAL;
				Map<String, Object> mailParameters = new HashMap<String, Object>();
				mailParameters.put(CommonUtils.PARAMETERS_FS_NAME,
						paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
				mailParameters.put(PARAMETERS_PRODUCT_TYPE,
						proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE) != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() : "NA");
				mailParameters.put(CommonUtils.PARAMETERS_LOAN_AMOUNT,
						proposalresp.get(CommonUtils.LITERAL_AMOUNT) != null ? Double.valueOf(proposalresp.get(CommonUtils.LITERAL_AMOUNT).toString())
								: "NA");
				mailParameters.put(CommonUtils.PARAMETERS_EMI_AMOUNT,
						proposalresp.get(CommonUtils.PARAMETERS_EMI_AMOUNT) != null
								? Double.valueOf(proposalresp.get(CommonUtils.PARAMETERS_EMI_AMOUNT).toString())
								: "NA");
				mailParameters.put(PARAMETERS_INTEREST_RATE,
						proposalresp.get(CommonUtils.RATE_INTEREST) != null
								? Double.valueOf(proposalresp.get(CommonUtils.RATE_INTEREST).toString())
								: "NA");
				mailParameters.put(CommonUtils.PARAMETERS_APPLICATION_ID,
						paymentRequest.getApplicationId() != null ? paymentRequest.getApplicationId().toString()
								: "NA");

				UserResponse response = null;

				try {
					response = userClient.getEmailMobile(userId);
				} catch (Exception e) {
					logger.error(SOMETHING_WENT_WRONG_WHILE_CALLING_USERS_CLIENT,e);
				}

				if (!CommonUtils.isObjectNullOrEmpty(response)) {
					UsersRequest signUpUser = MultipleJSONObjectHelper
							.getObjectFromMap((Map<String, Object>) response.getData(), UsersRequest.class);

					String mobile = signUpUser.getMobile();
					logger.info(MSG_MOBILE_NO + mobile);
					mailParameters.put(PARAMETERS_MOBILE_NO, mobile != null ? mobile : "NA");

				}

				// CHANGES FOR NOTIFICATION PURPOSE---STARTS HERE- Multiple bank---->
				ProposalMappingResponse proposalResponse = null;
				//Map<String, Object> proposalresp1 = null;
				/*
				 * Not need this call its already in param
				 * try {
					if(paymentRequest.getApplicationId()!=null){
					proposalResponse = proposalDetailsClient.getInPricipleById(paymentRequest.getApplicationId());
					proposalresp = MultipleJSONObjectHelper
							.getObjectFromMap((Map<String, Object>) proposalResponse.getData(), Map.class);
				}
					}catch (Exception e) {
					logger.info("Error calling Proposal Details Client for getting Branch Id:- {}" , paymentRequest.getApplicationId());
				}
*/
				Long propsalId = Long.valueOf(String.valueOf(proposalresp.get(PROPOSAL_ID)));
				LoanApplicationRequest applicationRequest = loanApplicationService.getFromClient(propsalId);
				String address = null;
				// This notification not sent on uniform product
				if(!CommonUtils.isObjectNullOrEmpty(applicationRequest) && applicationRequest.getBusinessTypeId().equals(CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId())){
				if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
						&& applicationRequest.getBusinessTypeId() == 1){
					// CHANGES FOR  MULTPLE BANK PURPOSE NOTIFICATION- NEW CODE --->
					CorporateFinalInfoRequest applicantRequest = null;
					try {
						/*if(applicantRequest.getUserId()!=null && proposalResponse.getId()!=null){*/
						applicantRequest = corporateFinalInfoService.getByProposalId(applicationRequest.getUserId(),propsalId);
						logger.info("THIS IS USER ID --------- AND" + " "+applicantRequest.getUserId()+ ""
								+ "THIS IS PROPOSAL MAPPING ID==========>>>>"+( proposalResponse != null ? proposalResponse.getId() : null));
						/*}*/
					}catch (Exception e) {
						logger.error("EXCEPTION IS GETTING WHILE GETBY PROPOSALID IN FPASYNCOMPONENT=====>:{}",e);
					}
					//      OLD CODE==============>
/*					CorporateApplicantRequest applicantRequest = corporateapplicantService
							.getCorporateApplicant(paymentRequest.getApplicationId());*/
					if (!CommonUtils.isObjectNullOrEmpty(applicantRequest)
							&& !CommonUtils.isObjectNullOrEmpty(applicantRequest.getFirstAddress())) {
						String premiseNumber = null;
						String streetName = null;
						String landMark = null;
						premiseNumber = applicantRequest.getFirstAddress().getPremiseNumber()!=null?applicantRequest.getFirstAddress().getPremiseNumber():"";
						streetName = applicantRequest.getFirstAddress().getStreetName()!=null?applicantRequest.getFirstAddress().getStreetName():"";
						landMark = applicantRequest.getFirstAddress().getLandMark()!=null?applicantRequest.getFirstAddress().getLandMark():"";
						address = premiseNumber+" "+streetName+" "+landMark;
					}
				}
				else if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
						&& applicationRequest.getBusinessTypeId() == 3){
					address = applicationRequest.getAddress();
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
				}

				mailParameters.put(CommonUtils.PARAMETERS_ADDRESS, address != null ? address : "NA");

				Long branchId = null;
				if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(BRANCH_ID))) {
					branchId = Long.valueOf(proposalresp.get(BRANCH_ID).toString());
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
							logger.info(MSG_INTO_GETTING_FP_NAME + userObj);
							UserResponse userResponseForName = userClient.getFPDetails(userObj);
							FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
									.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
											FundProviderDetailsRequest.class);
							name = fundProviderDetailsRequest.getFirstName() + " "
									+ (fundProviderDetailsRequest.getLastName() == null ? ""
									: fundProviderDetailsRequest.getLastName());
						} catch (Exception e) {
							logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
							logger.info(MSG_MAKER_ID+userObj.getEmail());
							to = userObj.getEmail();

							if (LITERAL_NULL.equals(name)) {
								mailParameters.put(PARAMETERS_MAKER_NAME, PARAMETERS_SIR_MADAM);
							} else {
								mailParameters.put(PARAMETERS_MAKER_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
							}

							mailParameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, false);

							String[] bcc = {environment.getRequiredProperty("bccforcam")};
							createNotificationForEmailForFundProvider(to, userId.toString(), mailParameters,
									NotificationAlias.EMAIL_ALL_MAKERS_AFTER_INPRINCIPLE_TO_FS, subject,applicationRequest.getId(),proposalresp,bcc);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
							logger.info(MSG_MAKER_ID+userObj.getEmail());
							Map<String, Object> smsParameters = new HashMap<String, Object>();
							to = "91" + userObj.getMobile();
							smsParameters.put(CommonUtils.PARAMETERS_FS_NAME,
									paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
							smsParameters.put(PARAMETERS_PRODUCT_TYPE,
									proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE) != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
											: "NA");
							smsParameters.put("url", URL_WWW_PSBLOANS_COM);

							sendSMSNotification(userId.toString(), smsParameters,
									NotificationAlias.SMS_ALL_MAKERS_AFTER_INPRINCIPLE_TO_FS, to);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
							logger.info(MSG_MAKER_ID+userObj.getEmail());
							Map<String, Object> sysParameters = new HashMap<String, Object>();
							sysParameters.put(CommonUtils.PARAMETERS_FS_NAME,
									paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");

							sendSYSNotification(paymentRequest.getApplicationId(), userObj.getId().toString(),
									sysParameters, NotificationAlias.SYS_ALL_MAKERS_AFTER_INPRINCIPLE_TO_FS,
									userObj.getId().toString(), userObj.getId().toString());
						}
					}
				} else {
					logger.info("No Maker found=================>");
				}
				
			}
			} catch (Exception e) {
				logger.error("An exception getting while sending mail to all Makers=============>{}",e);
			}
		} else {
			logger.info("Mail to Makers after In-principle to FS is disabled==========>");
		}
	}

	//====Sending Mail to all Checkers after FS receives In-principle Approval=======changed for the multiple bank=
	@Async
	public void sendEmailToAllCheckersWhenFSRecievesInPrinciple(Map<String, Object> proposalresp,
																PaymentRequest paymentRequest, Long userId, Long orgId) {
		if (mailToMakerChecker) {
			try {
				Long proposalId=Long.valueOf(String.valueOf(proposalresp.get(PROPOSAL_ID)));
				logger.info("Into sending Mail to all Checkers after FS gets In-Principle Approval===>{}");
				String subject = SUBJECT_INTIMATION_NEW_PROPOSAL;

				Map<String, Object> mailParameters = new HashMap<String, Object>();

				mailParameters.put(CommonUtils.PARAMETERS_FS_NAME,
						paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
				mailParameters.put(PARAMETERS_PRODUCT_TYPE,
						proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE) != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() : "NA");
				mailParameters.put(CommonUtils.PARAMETERS_LOAN_AMOUNT,
						proposalresp.get(CommonUtils.LITERAL_AMOUNT) != null ? Double.valueOf(proposalresp.get(CommonUtils.LITERAL_AMOUNT).toString())
								: "NA");
				mailParameters.put(CommonUtils.PARAMETERS_EMI_AMOUNT,
						proposalresp.get(CommonUtils.PARAMETERS_EMI_AMOUNT) != null
								? Double.valueOf(proposalresp.get(CommonUtils.PARAMETERS_EMI_AMOUNT).toString())
								: "NA");
				mailParameters.put(PARAMETERS_INTEREST_RATE,
						proposalresp.get(CommonUtils.RATE_INTEREST) != null
								? Double.valueOf(proposalresp.get(CommonUtils.RATE_INTEREST).toString())
								: "NA");
				mailParameters.put(CommonUtils.PARAMETERS_APPLICATION_ID, paymentRequest.getApplicationId());
				UserResponse response = null;
				try {
					response = userClient.getEmailMobile(userId);
				} catch (Exception e) {
					logger.error(SOMETHING_WENT_WRONG_WHILE_CALLING_USERS_CLIENT,e);
				}
				if (!CommonUtils.isObjectNullOrEmpty(response)) {
					UsersRequest signUpUser = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) response.getData(), UsersRequest.class);
					String mobile = signUpUser.getMobile();
					logger.info(MSG_MOBILE_NO + mobile);
					mailParameters.put(PARAMETERS_MOBILE_NO, mobile != null ? mobile : "NA");

				}
				LoanApplicationRequest applicationRequest = loanApplicationService.getFromClient(proposalId);
				String address = null;
				if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
						&& applicationRequest.getBusinessTypeId() == 1){

//					CorporateApplicantRequest applicantRequest = corporateapplicantService.getCorporateApplicant(paymentRequest.getApplicationId());
					CorporateFinalInfoRequest applicantRequest  = corporateFinalInfoService.getByProposalId(null, proposalId);
					if (!CommonUtils.isObjectNullOrEmpty(applicantRequest)
							&& !CommonUtils.isObjectNullOrEmpty(applicantRequest.getFirstAddress())) {
						String premiseNumber = null;
						String streetName = null;
						String landMark = null;
						premiseNumber = applicantRequest.getFirstAddress().getPremiseNumber()!=null?applicantRequest.getFirstAddress().getPremiseNumber():"";
						streetName = applicantRequest.getFirstAddress().getStreetName()!=null?applicantRequest.getFirstAddress().getStreetName():"";
						landMark = applicantRequest.getFirstAddress().getLandMark()!=null?applicantRequest.getFirstAddress().getLandMark():"";
						address = premiseNumber+" "+streetName+" "+landMark;
					}
				}
				else if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
						&& applicationRequest.getBusinessTypeId() == 3){
					address = applicationRequest.getAddress();
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
				mailParameters.put(CommonUtils.PARAMETERS_ADDRESS, address != null ? address : "NA");

				Long branchId = null;
				if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(BRANCH_ID))) {
					branchId = Long.valueOf(proposalresp.get(BRANCH_ID).toString());
				}

				UserResponse userResponse = userClient.getUserDetailByOrgRoleBranchId(orgId,
						com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_CHECKER, branchId);
				List<Map<String, Object>> usersRespList = (List<Map<String, Object>>) userResponse.getListData();
				String to = null;
				if (!CommonUtils.isObjectNullOrEmpty(usersRespList)) {
					for (int i = 0; i < usersRespList.size(); i++) {
						UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),UsersRequest.class);
						String name = null;
						try {
							logger.info(MSG_INTO_GETTING_FP_NAME + userObj);
							UserResponse userResponseForName = userClient.getFPDetails(userObj);
							FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
									.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
											FundProviderDetailsRequest.class);
							name = fundProviderDetailsRequest.getFirstName() + " "
									+ (fundProviderDetailsRequest.getLastName() == null ? ""
									: fundProviderDetailsRequest.getLastName());
						} catch (Exception e) {
							logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
						}
						if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
							logger.info(MSG_CHECKER_ID+userObj.getEmail());
							to = userObj.getEmail();
							if (LITERAL_NULL.equals(name)) {
								mailParameters.put(PARAMETERS_CHECKER_NAME, PARAMETERS_SIR_MADAM);
							} else {
								mailParameters.put(PARAMETERS_CHECKER_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
							}
							mailParameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, true);
							createNotificationForEmailForFundProvider(to, userId.toString(), mailParameters,
									NotificationAlias.EMAIL_ALL_CHECKERS_AFTER_INPRINCIPLE_TO_FS, subject,applicationRequest.getId(),proposalresp,null);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
							logger.info(MSG_MAKER_ID+userObj.getEmail());
							Map<String, Object> smsParameters = new HashMap<String, Object>();
							to = "91" + userObj.getMobile();
							if (LITERAL_NULL.equals(name)) {
								smsParameters.put(PARAMETERS_CHECKER_NAME, PARAMETERS_SIR_MADAM);
							} else {
								smsParameters.put(PARAMETERS_CHECKER_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
							}
							smsParameters.put(CommonUtils.PARAMETERS_FS_NAME,
									paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
							smsParameters.put(PARAMETERS_PRODUCT_TYPE,
									proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE) != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
											: "NA");
							smsParameters.put("url", URL_WWW_PSBLOANS_COM);

							sendSMSNotification(userId.toString(), smsParameters,
									NotificationAlias.SMS_ALL_CHECKERS_AFTER_INPRINCIPLE_TO_FS, to);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
							logger.info(MSG_MAKER_ID+userObj.getEmail());
							Map<String, Object> sysParameters = new HashMap<String, Object>();
							sysParameters.put(CommonUtils.PARAMETERS_FS_NAME,
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
				logger.error("An exception getting while sending mail to all Checkers=============>{}",e);
			}
		} else {
			logger.info("Mail to Checkers after In-principle to FS is disabled==========>");
		}
	}

	// ==========================================================================================================

	//====Sending Mail to HO after FS receives In-principle Approval=======changed for the multiple bank=
	@Async
	public void sendEmailToHOWhenFSRecievesInPrinciple(Map<String, Object> proposalresp, PaymentRequest paymentRequest,
													   Long userId, Long orgId) {
		if (mailToMakerChecker) {
			try {
				logger.info("Into sending Mail to all Checkers after FS gets In-Principle Approval===>{}");
				String subject = SUBJECT_INTIMATION_NEW_PROPOSAL;

				Map<String, Object> mailParameters = new HashMap<String, Object>();
				mailParameters.put(CommonUtils.PARAMETERS_FS_NAME,
						paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
				mailParameters.put(PARAMETERS_PRODUCT_TYPE,
						proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE) != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() : "NA");
				mailParameters.put(CommonUtils.PARAMETERS_LOAN_AMOUNT,
						proposalresp.get(CommonUtils.LITERAL_AMOUNT) != null ? Double.valueOf(proposalresp.get(CommonUtils.LITERAL_AMOUNT).toString())
								: "NA");
				mailParameters.put(CommonUtils.PARAMETERS_EMI_AMOUNT,
						proposalresp.get(CommonUtils.PARAMETERS_EMI_AMOUNT) != null
								? Double.valueOf(proposalresp.get(CommonUtils.PARAMETERS_EMI_AMOUNT).toString())
								: "NA");
				mailParameters.put(PARAMETERS_INTEREST_RATE,
						proposalresp.get(CommonUtils.RATE_INTEREST) != null
								? Double.valueOf(proposalresp.get(CommonUtils.RATE_INTEREST).toString())
								: "NA");
				mailParameters.put(CommonUtils.PARAMETERS_APPLICATION_ID,
						paymentRequest.getApplicationId() != null ? paymentRequest.getApplicationId().toString()
								: "NA");

				UserResponse response = null;

				try {
					response = userClient.getEmailMobile(userId);
				} catch (Exception e) {
					logger.error(SOMETHING_WENT_WRONG_WHILE_CALLING_USERS_CLIENT,e);
				}
				if (!CommonUtils.isObjectNullOrEmpty(response)) {
					UsersRequest signUpUser = MultipleJSONObjectHelper
							.getObjectFromMap((Map<String, Object>) response.getData(), UsersRequest.class);

					String mobile = signUpUser.getMobile();
					logger.info(MSG_MOBILE_NO + mobile);
					mailParameters.put(PARAMETERS_MOBILE_NO, mobile != null ? mobile : "NA");
				}
				Long proposalId=Long.valueOf(String.valueOf(proposalresp.get(PROPOSAL_ID)));
				LoanApplicationRequest applicationRequest = loanApplicationService.getFromClient(proposalId);
				String address = null;
				if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
						&& applicationRequest.getBusinessTypeId() == 1){
					/*
					 * changes made for multiple bank flow
					 * CorporateApplicantRequest applicantRequest = corporateapplicantService.getCorporateApplicant(paymentRequest.getApplicationId());*/
					CorporateFinalInfoRequest applicantRequest = corporateFinalInfoService.getByProposalId(null, proposalId);
					if (!CommonUtils.isObjectNullOrEmpty(applicantRequest)
							&& !CommonUtils.isObjectNullOrEmpty(applicantRequest.getFirstAddress())) {
						String premiseNumber = null;
						String streetName = null;
						String landMark = null;
						premiseNumber = applicantRequest.getFirstAddress().getPremiseNumber()!=null?applicantRequest.getFirstAddress().getPremiseNumber():"";
						streetName = applicantRequest.getFirstAddress().getStreetName()!=null?applicantRequest.getFirstAddress().getStreetName():"";
						landMark = applicantRequest.getFirstAddress().getLandMark()!=null?applicantRequest.getFirstAddress().getLandMark():"";
						address = premiseNumber+" "+streetName+" "+landMark;
					}
				}
				else if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
						&& applicationRequest.getBusinessTypeId() == 3){
					address = applicationRequest.getAddress();
				}
				else{
					
					// For getting Address of Primary Director
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
				}
				mailParameters.put(CommonUtils.PARAMETERS_ADDRESS, address != null ? address : "NA");

				Long branchId = null;
				if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(BRANCH_ID))) {
					branchId = Long.valueOf(proposalresp.get(BRANCH_ID).toString());
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
							logger.info(MSG_INTO_GETTING_FP_NAME + userObj);
							UserResponse userResponseForName = userClient.getFPDetails(userObj);
							FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
									.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
											FundProviderDetailsRequest.class);
							name = fundProviderDetailsRequest.getFirstName() + " "
									+ (fundProviderDetailsRequest.getLastName() == null ? ""
									: fundProviderDetailsRequest.getLastName());
						} catch (Exception e) {
							logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
							logger.info(MSG_CHECKER_ID+userObj.getEmail());
							to = userObj.getEmail();
							if (LITERAL_NULL.equals(name)) {
								mailParameters.put(PARAMETERS_HO_NAME, PARAMETERS_SIR_MADAM);
							} else {
								mailParameters.put(PARAMETERS_HO_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
							}
							mailParameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, true);
							createNotificationForEmailForFundProvider(to, userId.toString(), mailParameters,
									NotificationAlias.EMAIL_HO_INPRINCIPLE_TO_FS, subject,applicationRequest.getId(),proposalresp,null);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
							logger.info(MSG_MAKER_ID+userObj.getEmail());
							Map<String, Object> smsParameters = new HashMap<String, Object>();
							to = "91" + userObj.getMobile();
							smsParameters.put(CommonUtils.PARAMETERS_FS_NAME,
									paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
							smsParameters.put(PARAMETERS_PRODUCT_TYPE,
									proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE) != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
											: "NA");
							smsParameters.put("url", URL_WWW_PSBLOANS_COM);

							sendSMSNotification(userId.toString(), smsParameters,
									NotificationAlias.SMS_HO_INPRINCIPLE_TO_FS, to);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
							logger.info(MSG_MAKER_ID+userObj.getEmail());
							Map<String, Object> sysParameters = new HashMap<String, Object>();
							sysParameters.put(CommonUtils.PARAMETERS_FS_NAME,
									paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
							sysParameters.put(PARAMETERS_PRODUCT_TYPE,
									proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE) != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
											: "NA");

							sendSYSNotification(paymentRequest.getApplicationId(), userObj.getId().toString(),
									sysParameters, NotificationAlias.SYS_HO_INPRINCIPLE_TO_FS,
									userObj.getId().toString(), userObj.getId().toString());
						}
					}
				} else {
					logger.info(MSG_NO_HO_FOUND);
				}
			} catch (Exception e) {
				logger.error("An exception getting while sending mail to HO=============>{}",e);
			}
		} else {
			logger.info("Mail to HO after In-principle to FS is disabled==========>");
		}
	}
	// ==================Sending Mail to BO after FS receives In-principle Approval==================
	/*
	 * This method need to be changed with proposalmapping
	 * */
 	@Async
	public void sendEmailToAllBOWhenFSRecievesInPrinciple(Map<String, Object> proposalresp,PaymentRequest paymentRequest, Long userId, Long orgId) {
		if (mailToMakerChecker) {
			try {
				logger.info("Into sending Mail to all BO after FS gets In-Principle Approval===>{}");
				String subject = SUBJECT_INTIMATION_NEW_PROPOSAL;

				Map<String, Object> mailParameters = new HashMap<String, Object>();
				mailParameters.put(CommonUtils.PARAMETERS_FS_NAME,
						paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
				mailParameters.put(PARAMETERS_PRODUCT_TYPE,
						proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE) != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() : "NA");
				mailParameters.put(CommonUtils.PARAMETERS_LOAN_AMOUNT,
						proposalresp.get(CommonUtils.LITERAL_AMOUNT) != null ? Double.valueOf(proposalresp.get(CommonUtils.LITERAL_AMOUNT).toString())
								: "NA");
				mailParameters.put(CommonUtils.PARAMETERS_EMI_AMOUNT,
						proposalresp.get(CommonUtils.PARAMETERS_EMI_AMOUNT) != null
								? Double.valueOf(proposalresp.get(CommonUtils.PARAMETERS_EMI_AMOUNT).toString())
								: "NA");
				mailParameters.put(PARAMETERS_INTEREST_RATE,
						proposalresp.get(CommonUtils.RATE_INTEREST) != null
								? Double.valueOf(proposalresp.get(CommonUtils.RATE_INTEREST).toString())
								: "NA");
				mailParameters.put(CommonUtils.PARAMETERS_APPLICATION_ID,
						paymentRequest.getApplicationId() != null ? paymentRequest.getApplicationId().toString()
								: "NA");

				UserResponse response = null;

				try {
					response = userClient.getEmailMobile(userId);
				} catch (Exception e) {
					logger.error(SOMETHING_WENT_WRONG_WHILE_CALLING_USERS_CLIENT,e);
				}

				if (!CommonUtils.isObjectNullOrEmpty(response)) {
					UsersRequest signUpUser = MultipleJSONObjectHelper
							.getObjectFromMap((Map<String, Object>) response.getData(), UsersRequest.class);

					String mobile = signUpUser.getMobile();
					logger.info(MSG_MOBILE_NO + mobile);
					mailParameters.put(PARAMETERS_MOBILE_NO, mobile != null ? mobile : "NA");
				}
				Long proposalId=Long.valueOf(String.valueOf(proposalresp.get(PROPOSAL_ID)));
				LoanApplicationRequest applicationRequest = loanApplicationService.getFromClient(proposalId);
				String address = null;
				if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
						&& applicationRequest.getBusinessTypeId() == 1){
					/*
					 * changes made for multiple bank
					CorporateApplicantRequest applicantRequest = corporateapplicantService.getCorporateApplicant(paymentRequest.getApplicationId());*/
					CorporateFinalInfoRequest applicantRequest = corporateFinalInfoService.getByProposalId(null, proposalId);
					if (!CommonUtils.isObjectNullOrEmpty(applicantRequest)
							&& !CommonUtils.isObjectNullOrEmpty(applicantRequest.getFirstAddress())) {
						String premiseNumber = null;
						String streetName = null;
						String landMark = null;
						premiseNumber = applicantRequest.getFirstAddress().getPremiseNumber()!=null?applicantRequest.getFirstAddress().getPremiseNumber():"";
						streetName = applicantRequest.getFirstAddress().getStreetName()!=null?applicantRequest.getFirstAddress().getStreetName():"";
						landMark = applicantRequest.getFirstAddress().getLandMark()!=null?applicantRequest.getFirstAddress().getLandMark():"";
						address = premiseNumber+" "+streetName+" "+landMark;
					}
				}
				else if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
						&& applicationRequest.getBusinessTypeId() == 3){
					address = applicationRequest.getAddress();
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
				}
				mailParameters.put(CommonUtils.PARAMETERS_ADDRESS, address != null ? address : "NA");

				Long branchId = null;
				if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(BRANCH_ID))) {
					branchId = Long.valueOf(proposalresp.get(BRANCH_ID).toString());
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
							logger.info(MSG_INTO_GETTING_FP_NAME + userObj);
							UserResponse userResponseForName = userClient.getFPDetails(userObj);
							FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
									.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
											FundProviderDetailsRequest.class);
							name = fundProviderDetailsRequest.getFirstName() + " "
									+ (fundProviderDetailsRequest.getLastName() == null ? ""
									: fundProviderDetailsRequest.getLastName());
						} catch (Exception e) {
							logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
							logger.info(MSG_CHECKER_ID+userObj.getEmail());
							to = userObj.getEmail();
							if (LITERAL_NULL.equals(name)) {
								mailParameters.put(PARAMETERS_BO_NAME, PARAMETERS_SIR_MADAM);
							} else {
								mailParameters.put(PARAMETERS_BO_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
							}
							mailParameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, true);
							createNotificationForEmailForFundProvider(to, userId.toString(), mailParameters,
									NotificationAlias.EMAIL_ALL_BO_INPRINCIPLE_TO_FS, subject,applicationRequest.getId(),proposalresp,null);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
							logger.info(MSG_MAKER_ID+userObj.getEmail());
							Map<String, Object> smsParameters = new HashMap<String, Object>();
							to = "91" + userObj.getMobile();
							smsParameters.put(CommonUtils.PARAMETERS_FS_NAME,
									paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
							smsParameters.put(PARAMETERS_PRODUCT_TYPE,
									proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE) != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
											: "NA");
							smsParameters.put("url", URL_WWW_PSBLOANS_COM);

							sendSMSNotification(userId.toString(), smsParameters,
									NotificationAlias.SMS_ALL_BO_INPRINCIPLE_TO_FS, to);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
							logger.info(MSG_MAKER_ID+userObj.getEmail());
							Map<String, Object> sysParameters = new HashMap<String, Object>();
							sysParameters.put(CommonUtils.PARAMETERS_FS_NAME,
									paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
							sysParameters.put(PARAMETERS_PRODUCT_TYPE,
									proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE) != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
											: "NA");

							sendSYSNotification(paymentRequest.getApplicationId(), userObj.getId().toString(),
									sysParameters, NotificationAlias.SYS_ALL_BO_INPRINCIPLE_TO_FS,
									userObj.getId().toString(), userObj.getId().toString());
						}
					}
				} else {
					logger.info(MSG_NO_BO_FOUND);
				}
			} catch (Exception e) {
				logger.error("An exception getting while sending mail to BO=============>{}",e);
			}
		} else {
			logger.info("Mail to BO after In-principle to FS is disabled==========>");
		}
	}
/*===============================================================================================================================*/



	// ====================Sending Mail to Maker and all Makers and Checkers when maker accepts Proposal==========
	@Async
	public void sendMailToMakerandAllMakersWhenMakerAcceptProposal(NhbsApplicationRequest request) {
		logger.info("Enter in sending mail to Maker and all Makers When Maker accepts Proposal");
		try {
			Long NotificationAliasId=null;

			ProposalMappingResponse proposalResponse = null;
			Map<String, Object> proposalresp = null;
			try {
				logger.info(CALLING_PROPOSAL_DETAILS_CLIENT_FOR_GETTING_BRANCH_ID , request.getApplicationId());
//				proposalResponse = proposalDetailsClient.getInPricipleById(request.getApplicationId());
				GatewayResponse inpRespose = gatewayClient.getInPrincipleByApplicationId(request.getApplicationId());
				logger.info(GOT_INPRINCIPLE_RESPONSE_FROM_PROPOSAL_DETAILS_CLIENT , proposalResponse);
				proposalresp = MultipleJSONObjectHelper
						.getObjectFromMap((Map<String, Object>) inpRespose.getData(), Map.class);
			} catch (Exception e) {
				logger.error(ERROR_CALLING_PROPOSAL_DETAILS_CLIENT_FOR_GETTING_BRANCH_ID , request.getApplicationId());
				logger.error(CommonUtils.EXCEPTION,e);
			}

//			convert loan applicationService to applicationProposalMappdingtable for multiple bank changes
			LoanApplicationRequest applicationRequest = null;
			Long proposalId=Long.valueOf(String.valueOf(proposalresp.get(PROPOSAL_ID)));
			if(proposalId != null) {
				applicationRequest  = loanApplicationService.getFromClient(proposalId);
			}
			Map<String, Object> parameters = new HashMap<String, Object>();
//			instead of loan application use this Application proposal mapping
//			ApplicationProposalMapping applicationRequest = appPropMappService.getByApplicationId(request.getApplicationId());
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
			// =========================================================================================================

			if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
					&& applicationRequest.getBusinessTypeId().equals(CommonUtils.BusinessType.EXISTING_BUSINESS.getId())){
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
					address = premiseNumber+" "+streetName+" "+landMark;

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
							logger.error("Error Calling One form client for getting state by state list Id : ",e);
						}
					}
				}
			}

			//	when proposal belongs to PL
			if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
					&& applicationRequest.getBusinessTypeId().equals(CommonUtils.BusinessType.RETAIL_PERSONAL_LOAN.getId())){
				fsName=applicationRequest.getUserName();
				address=applicationRequest.getAddress();
				RetailApplicantRequest retailApplicantRequest = retailapplicantService.get(request.getApplicationId());
				if (!CommonUtils.isObjectNullOrEmpty(retailApplicantRequest) && !CommonUtils.isObjectNullOrEmpty(retailApplicantRequest.getAddressState()) ) {
						state = CommonDocumentUtils.getState(retailApplicantRequest.getAddressState(), oneFormClient);
				}
				if (!CommonUtils.isObjectNullOrEmpty(retailApplicantRequest) && !CommonUtils.isObjectNullOrEmpty(retailApplicantRequest.getAddressCity()) ) {
						city = CommonDocumentUtils.getState(retailApplicantRequest.getAddressCity(), oneFormClient);
				}
				NotificationAliasId= NotificationAlias.PL_EMAIL_TO_FS_WHEN_MAKKER_ACCEPT_PROPOSAL;
			}else {
				NotificationAliasId=NotificationAlias.EMAIL_FS_ACCEPTED_BY_MAKER;
			}
			parameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
			parameters.put(CommonUtils.PARAMETERS_ADDRESS, address != null ? address : "NA");
			parameters.put("state", state != null ? state : "NA");
			parameters.put("city", city !=null ? city : "NA");

			// =========================================================================================================
			/*
			 * implementation for multiple bank
			 *  */
			CorporateFinalInfoRequest corFinalByProposalId =null;
			try {
				logger.info(CALLING_PROPOSAL_DETAILS_CLIENT_FOR_GETTING_BRANCH_ID + request.getApplicationId());
				proposalResponse = proposalDetailsClient.getInPricipleById(request.getApplicationId());
				logger.info(GOT_INPRINCIPLE_RESPONSE_FROM_PROPOSAL_DETAILS_CLIENT + proposalResponse);
				proposalresp = MultipleJSONObjectHelper
						.getObjectFromMap((Map<String, Object>) proposalResponse.getData(), Map.class);
			} catch (Exception e) {
                logger.error(ERROR_CALLING_PROPOSAL_DETAILS_CLIENT_FOR_GETTING_BRANCH_ID + request.getApplicationId());
                logger.error(CommonUtils.EXCEPTION, e);
            }
			corFinalByProposalId = corporateFinalInfoService.getByProposalId(request.getUserId(),proposalResponse.getId());

			if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
					&& applicationRequest.getBusinessTypeId().equals(CommonUtils.BusinessType.EXISTING_BUSINESS.getId())){
//				CorporateApplicantRequest applicantRequest = corporateapplicantService.getCorporateApplicant(request.getApplicationId());
				if (!CommonUtils.isObjectNullOrEmpty(corFinalByProposalId)
						&& !CommonUtils.isObjectNullOrEmpty(corFinalByProposalId.getFirstAddress())) {
					String premiseNumber = null;
					String streetName = null;
					String landMark = null;
					premiseNumber = corFinalByProposalId.getFirstAddress().getPremiseNumber()!=null?corFinalByProposalId.getFirstAddress().getPremiseNumber():"";
					streetName = corFinalByProposalId.getFirstAddress().getStreetName()!=null?corFinalByProposalId.getFirstAddress().getStreetName():"";
					landMark = corFinalByProposalId.getFirstAddress().getLandMark()!=null?corFinalByProposalId.getFirstAddress().getLandMark():"";
					address = premiseNumber.toString()+" "+streetName.toString()+" "+landMark.toString();

					List<Long> stateList = new ArrayList<Long>();

					Long stateId = null;
					if (!CommonUtils.isObjectNullOrEmpty(corFinalByProposalId.getFirstAddress().getStateId())) {
						stateId = Long.valueOf(corFinalByProposalId.getFirstAddress().getStateId());
						stateList.add(stateId);
					}

					if (!CommonUtils.isListNullOrEmpty(stateList)) {
						try {
							logger.info("Calling One form client for getting state by state list Id");
							OneFormResponse oneFormResponse = oneFormClient.getStateByStateListId(stateList);
							List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
							if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
								MasterResponse masterResponse = MultipleJSONObjectHelper
										.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
								state = masterResponse.getValue();
								city = CommonDocumentUtils.getCity(corFinalByProposalId.getFirstAddress().getCityId(), oneFormClient);
							} else {
								state = "NA";
								city = "NA";
							}

						} catch (Exception e) {
							logger.error("Error Calling One form client for getting state by state list Id = {}",e);
						}
					}
				}
			}


			Long branchId = null;
			if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(BRANCH_ID))) {
				branchId = Long.valueOf(proposalresp.get(BRANCH_ID).toString());
			}

			// ===========Getting emailId and Name of Maker who accepted the Proposal================

			UserResponse assignedMakerResponse = null;
			try {
				assignedMakerResponse = userClient.getEmailMobile(request.getUserId());
			} catch (Exception e) {
				logger.error(SOMETHING_WENT_WRONG_WHILE_CALLING_USERS_CLIENT,e);
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
					logger.info(MSG_INTO_GETTING_FP_NAME + assignedMakerForName);
					UserResponse userResponseForName = userClient.getFPDetails(assignedMakerForName);
					FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
					makerName = fundProviderDetailsRequest.getFirstName() + " "
							+ (fundProviderDetailsRequest.getLastName() == null ? ""
							: fundProviderDetailsRequest.getLastName());
				} catch (Exception e) {
					logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
				}
			}

			// =======================================================================================

			String assignedMakerName = null;

			if (LITERAL_NULL.equals(makerName)) {
				assignedMakerName = LITERAL_MAKER;
			} else {
				assignedMakerName = makerName != null ? makerName : LITERAL_MAKER;
			}
			parameters.put(PARAMETERS_MAKER_NAME, assignedMakerName);
			if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE))) {
				parameters.put(PARAMETERS_PRODUCT_TYPE,
						proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
								: "NA");
			} else {
				parameters.put(PARAMETERS_PRODUCT_TYPE, "NA");
			}
			parameters.put(CommonUtils.PARAMETERS_LOAN_AMOUNT,
					applicationRequest.getLoanAmount() != null ? applicationRequest.getLoanAmount() : "NA");
			parameters.put(CommonUtils.PARAMETERS_APPLICATION_ID, applicationRequest.getApplicationCode()!=null?applicationRequest.getApplicationCode():"");

			UserResponse response = null;
			UsersRequest signUpUser = null;

			try {
				response = userClient.getEmailMobile(applicationRequest.getUserId());
			} catch (Exception e) {
				logger.error("Something went wrong while calling Users client from sending mail to fs===>{}",e);
			}

			if (!CommonUtils.isObjectNullOrEmpty(response)) {
				try {
					signUpUser = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) response.getData(),
							UsersRequest.class);
				} catch (Exception e) {
					logger.error("Exception getting signup user at Sending email to fs when maker accept proposal : ",e);
				}
			}

			String mobile = signUpUser.getMobile();
			parameters.put(PARAMETERS_MOBILE_NO, mobile!=null?mobile:"NA");

			// ====================Sending Mail to Maker who accepts Proposal=====================
			String subject = "Intimation: Proposal #Accepted=" + assignedMakerName + "#ApplicationId="+ request.getApplicationId();
//---------- no mail on uniform product
			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker)) {
				if (!CommonUtils.isObjectNullOrEmpty(applicationRequest) && !CommonUtils.isObjectNullOrEmpty(applicationRequest.getBusinessTypeId()) 
						&& !applicationRequest.getBusinessTypeId().equals(CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId())) {	
						if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getEmail())) {
		
							String toIds = assignedMaker.getEmail();
							logger.info("Email Sending TO MAKER when Maker accepts Proposal===to==>{}" + toIds);
							parameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, true);
							createNotificationForEmail(toIds, request.getUserId().toString(), parameters,
									NotificationAlias.EMAIL_MAKER_ACCEPT_PROPOSAL_OF_FS, subject);
						}
					if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getMobile())) {
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						String to = "91" + assignedMaker.getMobile();
						smsParameters.put(PARAMETERS_MAKER_NAME, assignedMakerName);
						smsParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
						if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE))) {
							smsParameters.put(PARAMETERS_PRODUCT_TYPE,
						proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null
											? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
											: "NA");
						} else {
							smsParameters.put(PARAMETERS_PRODUCT_TYPE, "NA");
						}
						smsParameters.put("url", URL_WWW_PSBLOANS_COM);
	
						sendSMSNotification(request.getUserId().toString(), smsParameters,
								NotificationAlias.SMS_MAKER_ACCEPT_PROPOSAL_OF_FS, to);
					}
	
					if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getId())) {
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
	
						sendSYSNotification(request.getApplicationId(), request.getUserId().toString(), sysParameters,
								NotificationAlias.SYS_MAKER_ACCEPT_PROPOSAL_OF_FS, request.getUserId().toString(),
								request.getUserId().toString());
					}
				}

			}
			// ==sending email to fs when maker accepted proposL==Email_FS_Accepted_By_MAKER==add mail for  Email_FS_Accepted_By_MAKER
			sendMailToFsWhenMakerAcceptPorposal(fsName, proposalresp, assignedMakerName,applicationRequest,signUpUser,address,NotificationAliasId);
			// ====================Sending Mail to other Makers that maker has accepted Proposal==============
//---------- no sending mail on uniform product			
			if (!CommonUtils.isObjectNullOrEmpty(applicationRequest) && !CommonUtils.isObjectNullOrEmpty(applicationRequest.getBusinessTypeId()) 
					&& !applicationRequest.getBusinessTypeId().equals(CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId())) {
			UserResponse makerResponse = userClient.getUserDetailByOrgRoleBranchId(applicationRequest.getNpOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_MAKER, branchId);
			List<Map<String, Object>> makerRespList = (List<Map<String, Object>>) makerResponse.getListData();
			if (!CommonUtils.isObjectNullOrEmpty(makerRespList)) {
				for (int i = 0; i < makerRespList.size(); i++) {
					UsersRequest makerObj = MultipleJSONObjectHelper.getObjectFromMap(makerRespList.get(i),
							UsersRequest.class);

					String name = null;

					try {
						logger.info(MSG_INTO_GETTING_FP_NAME + makerObj);
						UserResponse userResponseForName = userClient.getFPDetails(makerObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
								: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
					}

					if (!CommonUtils.isObjectNullOrEmpty(makerObj.getEmail())
							&& !(makerObj.getEmail().equals(assignedMaker.getEmail()))) {
						String to = makerObj.getEmail();
						parameters.put("assigned_maker_name", assignedMakerName);
						if (LITERAL_NULL.equals(name)) {
							parameters.put(PARAMETERS_MAKER_NAME, PARAMETERS_SIR_MADAM);
						} else {
							parameters.put(PARAMETERS_MAKER_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
						}
						parameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, true);
						createNotificationForEmail(to, makerObj.getId().toString(), parameters,
								NotificationAlias.EMAIL_ALL_MAKERS_AFTER_MAKER_ACCEPT_PROPOSAL_OF_FS, subject);
					}

					if (!CommonUtils.isObjectNullOrEmpty(makerObj.getMobile())
							&& !(makerObj.getMobile().equals(assignedMaker.getMobile()))) {
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						String to = "91" + makerObj.getMobile();
						smsParameters.put(PARAMETERS_MAKER_NAME, assignedMakerName);
						smsParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
						if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE))) {
							smsParameters.put(PARAMETERS_PRODUCT_TYPE,
									proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null
											? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
											: "NA");
						} else {
							smsParameters.put(PARAMETERS_PRODUCT_TYPE, "NA");
						}
						smsParameters.put("url", URL_WWW_PSBLOANS_COM);

						sendSMSNotification(makerObj.getId().toString(), smsParameters,
								NotificationAlias.SMS_ALL_MAKERS_AFTER_MAKER_ACCEPT_PROPOSAL_OF_FS, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(makerObj.getId())
							&& !(makerObj.getId().equals(assignedMaker.getId()))) {
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put(PARAMETERS_MAKER_NAME, assignedMakerName);
						sysParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");

						sendSYSNotification(request.getApplicationId(), makerObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_ALL_MAKERS_AFTER_MAKER_ACCEPT_PROPOSAL_OF_FS,
								makerObj.getId().toString(), makerObj.getId().toString());
					}

				}

			} else {
				logger.info("No Maker found=================>");
			}

// ====================Sending Mail to all Checkers of that branch that maker has accepted Proposal=====================

			UserResponse checkerResponse = userClient.getUserDetailByOrgRoleBranchId(applicationRequest.getNpOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_CHECKER, branchId);
			List<Map<String, Object>> checkerRespList = (List<Map<String, Object>>) checkerResponse.getListData();
			if (!CommonUtils.isObjectNullOrEmpty(checkerRespList)) {
				for (int i = 0; i < checkerRespList.size(); i++) {
					UsersRequest checkerObj = MultipleJSONObjectHelper.getObjectFromMap(checkerRespList.get(i),
							UsersRequest.class);

					String name = null;

					try {
						logger.info(MSG_INTO_GETTING_FP_NAME + checkerObj);
						UserResponse userResponseForName = userClient.getFPDetails(checkerObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
								: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
					}

					if (!CommonUtils.isObjectNullOrEmpty(checkerObj.getEmail())) {
						String to = checkerObj.getEmail();
						parameters.put(PARAMETERS_MAKER_NAME, assignedMakerName);
						if (LITERAL_NULL.equals(name)) {
							parameters.put(PARAMETERS_CHECKER_NAME, PARAMETERS_SIR_MADAM);
						} else {
							parameters.put(PARAMETERS_CHECKER_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
						}
						parameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, true);
						createNotificationForEmail(to, checkerObj.getId().toString(), parameters,
								NotificationAlias.EMAIL_ALL_CHECKERS_AFTER_MAKER_ACCEPT_PROPOSAL, subject);
					}

					if (!CommonUtils.isObjectNullOrEmpty(checkerObj.getMobile())) {
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						String to = "91" + checkerObj.getMobile();
						if (LITERAL_NULL.equals(name)) {
							smsParameters.put(PARAMETERS_CHECKER_NAME, PARAMETERS_SIR_MADAM);
						} else {
							smsParameters.put(PARAMETERS_CHECKER_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
						}
						smsParameters.put(PARAMETERS_MAKER_NAME, assignedMakerName);
						smsParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
						if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE))) {
							smsParameters.put(PARAMETERS_PRODUCT_TYPE,
									proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null
											? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
											: "NA");
						} else {
							smsParameters.put(PARAMETERS_PRODUCT_TYPE, "NA");
						}
						smsParameters.put("url", URL_WWW_PSBLOANS_COM);

						sendSMSNotification(checkerObj.getId().toString(), smsParameters,
								NotificationAlias.SMS_ALL_CHECKERS_AFTER_MAKER_ACCEPT_PROPOSAL, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(checkerObj.getId())) {
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put(PARAMETERS_MAKER_NAME, assignedMakerName);
						sysParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
						if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE))) {
							sysParameters.put(PARAMETERS_PRODUCT_TYPE,
									proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null
											? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
											: "NA");
						} else {
							sysParameters.put(PARAMETERS_PRODUCT_TYPE, "NA");
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
							logger.info(MSG_INTO_GETTING_FP_NAME + hoObj);
							UserResponse userResponseForName = userClient.getFPDetails(hoObj);
							FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
									.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
											FundProviderDetailsRequest.class);
							name = fundProviderDetailsRequest.getFirstName() + " "
									+ (fundProviderDetailsRequest.getLastName() == null ? ""
									: fundProviderDetailsRequest.getLastName());
						} catch (Exception e) {
							logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
						}
	
						if (!CommonUtils.isObjectNullOrEmpty(hoObj.getEmail())) {
							String to = hoObj.getEmail();
							parameters.put(PARAMETERS_MAKER_NAME, assignedMakerName);
							if (LITERAL_NULL.equals(name)) {
								parameters.put(PARAMETERS_HO_NAME, PARAMETERS_SIR_MADAM);
							} else {
								parameters.put(PARAMETERS_HO_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
							}
							parameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, true);
							createNotificationForEmail(to, hoObj.getId().toString(), parameters,
									NotificationAlias.EMAIL_HO_MAKER_ACCEPT_PROPOSAL_OF_FS, subject);
						}
	
						if (!CommonUtils.isObjectNullOrEmpty(hoObj.getMobile())) {
							Map<String, Object> smsParameters = new HashMap<String, Object>();
							String to = "91" + hoObj.getMobile();
							smsParameters.put(PARAMETERS_MAKER_NAME, assignedMakerName);
							smsParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
							if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE))) {
								smsParameters.put(PARAMETERS_PRODUCT_TYPE,
										proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null
												? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
												: "NA");
							} else {
								smsParameters.put(PARAMETERS_PRODUCT_TYPE, "NA");
							}
							smsParameters.put("url", URL_WWW_PSBLOANS_COM);
	
							sendSMSNotification(hoObj.getId().toString(), smsParameters,
									NotificationAlias.SMS_HO_MAKER_ACCEPT_PROPOSAL_OF_FS, to);
						}
	
						if (!CommonUtils.isObjectNullOrEmpty(hoObj.getId())) {
							Map<String, Object> sysParameters = new HashMap<String, Object>();
							sysParameters.put(PARAMETERS_MAKER_NAME, assignedMakerName);
							sysParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
							if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE))) {
								sysParameters.put(PARAMETERS_PRODUCT_TYPE,
										proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null
												? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
												: "NA");
							} else {
								sysParameters.put(PARAMETERS_PRODUCT_TYPE, "NA");
							}
							sendSYSNotification(request.getApplicationId(), hoObj.getId().toString(), sysParameters,
									NotificationAlias.SYS_HO_MAKER_ACCEPT_PROPOSAL_OF_FS, hoObj.getId().toString(),
									hoObj.getId().toString());
						}
	
					}
	
				} else {
					logger.info(MSG_NO_HO_FOUND);
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
							logger.error(MSG_INTO_GETTING_FP_NAME + boObj);
							UserResponse userResponseForName = userClient.getFPDetails(boObj);
							FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
									.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
											FundProviderDetailsRequest.class);
							name = fundProviderDetailsRequest.getFirstName() + " "
									+ (fundProviderDetailsRequest.getLastName() == null ? ""
									: fundProviderDetailsRequest.getLastName());
						} catch (Exception e) {
							logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
						}
	
						if (!CommonUtils.isObjectNullOrEmpty(boObj.getEmail())) {
							String to = boObj.getEmail();
							parameters.put(PARAMETERS_MAKER_NAME, assignedMakerName);
							if (LITERAL_NULL.equals(name)) {
								parameters.put(PARAMETERS_BO_NAME, PARAMETERS_SIR_MADAM);
							} else {
								parameters.put(PARAMETERS_BO_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
							}
							parameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, true);
							createNotificationForEmail(to, boObj.getId().toString(), parameters,
									NotificationAlias.EMAIL_ALL_BO_MAKER_ACCEPT_PROPOSAL_OF_FS, subject);
						}
	
						if (!CommonUtils.isObjectNullOrEmpty(boObj.getMobile())) {
							Map<String, Object> smsParameters = new HashMap<String, Object>();
							String to = "91" + boObj.getMobile();
							smsParameters.put(PARAMETERS_MAKER_NAME, assignedMakerName);
							smsParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
							if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE))) {
								smsParameters.put(PARAMETERS_PRODUCT_TYPE,
										proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null
												? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
												: "NA");
							} else {
								smsParameters.put(PARAMETERS_PRODUCT_TYPE, "NA");
							}
							smsParameters.put("url", URL_WWW_PSBLOANS_COM);
	
							sendSMSNotification(boObj.getId().toString(), smsParameters,
									NotificationAlias.SMS_ALL_BO_MAKER_ACCEPT_PROPOSAL_OF_FS, to);
						}
	
						if (!CommonUtils.isObjectNullOrEmpty(boObj.getId())) {
							Map<String, Object> sysParameters = new HashMap<String, Object>();
							sysParameters.put(PARAMETERS_MAKER_NAME, assignedMakerName);
							sysParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
							if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE))) {
								sysParameters.put(PARAMETERS_PRODUCT_TYPE,
										proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null
												? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
												: "NA");
							} else {
								sysParameters.put(PARAMETERS_PRODUCT_TYPE, "NA");
							}
							sendSYSNotification(request.getApplicationId(), boObj.getId().toString(), sysParameters,
									NotificationAlias.SYS_ALL_BO_MAKER_ACCEPT_PROPOSAL_OF_FS, boObj.getId().toString(),
									boObj.getId().toString());
						}
	
					}
	
				} else {
					logger.info(MSG_NO_BO_FOUND);
				}
			// =======================================================================================================
			}
		}catch (Exception e) {
			logger.error("Throw exception while sending mail to Maker and all Makers when Maker accepts Proposal : ",e);
		}
	}

	// ============Send mail to FS when maker accept the proposal========================
	@Async
	public void sendMailToFsWhenMakerAcceptPorposal(String fsName, Map<String, Object> proposalresp,String assignedMakerName,LoanApplicationRequest applicationRequest, UsersRequest signUpUser, String address,Long NotificationAliasId){
		logger.info("Sending email to fs when maker accept proposal");
		Map<String, Object> mailParameter = new HashMap<String, Object>();
		mailParameter.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
		mailParameter.put("entity_name", fsName != null ? fsName : "NA");
		mailParameter.put(CommonUtils.PARAMETERS_LOAN_TYPE,
				proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE) != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() : "NA");
		mailParameter.put(PARAMETERS_MAKER_NAME, assignedMakerName);
		mailParameter.put(CommonUtils.PARAMETERS_APPLICATION_ID, applicationRequest.getApplicationCode()!=null?applicationRequest.getApplicationCode().toString():"NA");

		String mobile = signUpUser.getMobile();
		mailParameter.put("mobile_number", mobile != null ? mobile : "NA");
		mailParameter.put(CommonUtils.PARAMETERS_ADDRESS, address);
		String emailSubject = "Maker Assigned - For Quick Business Loan Approval ";
		if("Personal Loan".equals(proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString())){
			emailSubject = "Maker Assigned - For Quick Personal Loan Approval ";
		}
		try {
			if(fsName!=null&& address!=null&&mobile!=null&&assignedMakerName!=null&&applicationRequest.getId()!=null) {
				mailParameter.put(CommonUtils.PARAMETERS_IS_DYNAMIC, false);
				createNotificationForEmail(signUpUser.getEmail(), applicationRequest.getUserId().toString(),
						mailParameter, NotificationAliasId, emailSubject);
				logger.info("Email send to fs when maker accepted porposal");
			}else {
				logger.error("Email is not sent becouse of required values are null:"+  "signUpUser.getEmail:"+signUpUser.getEmail()+"applicationRequest.getUserId:"+applicationRequest.getUserId()
						+" mailParameter:"+mailParameter+"  NotificationAliasId"+NotificationAliasId.toString()+"emailSubject:"+emailSubject);
			}
		} catch (Exception e) {
			logger.error("Error in sending email to fs when porposal asigned to maker :" + e);
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
				logger.info(CALLING_PROPOSAL_DETAILS_CLIENT_FOR_GETTING_BRANCH_ID , request.getApplicationId());
//				proposalResponse = proposalDetailsClient.getInPricipleById(request.getApplicationId());
				GatewayResponse inpData = gatewayClient.getInPrincipleByApplicationId(request.getApplicationId());
				logger.info(GOT_INPRINCIPLE_RESPONSE_FROM_PROPOSAL_DETAILS_CLIENT ,proposalResponse);
				proposalresp = MultipleJSONObjectHelper
						.getObjectFromMap((Map<String, Object>) inpData.getData(), Map.class);
			} catch (Exception e) {
				logger.info(ERROR_CALLING_PROPOSAL_DETAILS_CLIENT_FOR_GETTING_BRANCH_ID , request.getApplicationId());
			}

			Long branchId = null;
			if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(BRANCH_ID))) {
				branchId = Long.valueOf(proposalresp.get(BRANCH_ID).toString());
			}

			// =========================================Getting
			// Maker=====================================

			UsersRequest assignedMakerForName = new UsersRequest();
			assignedMakerForName.setId(request.getUserId());

			String makerName = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedMakerForName)) {

				try {
					logger.error(MSG_INTO_GETTING_FP_NAME + assignedMakerForName);
					UserResponse userResponseForName = userClient.getFPDetails(assignedMakerForName);
					FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
					makerName = fundProviderDetailsRequest.getFirstName() + " "
							+ (fundProviderDetailsRequest.getLastName() == null ? ""
							: fundProviderDetailsRequest.getLastName());
				} catch (Exception e) {
					logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
				}
			}

			if (LITERAL_NULL.equals(makerName)) {
				makerName = LITERAL_MAKER;
			} else {
				makerName = makerName != null ? makerName : LITERAL_MAKER;
			}

			// ========================================================================================================

			// =========================================Getting
			// Checker=====================================

			UserResponse checkerResponse = null;
			try {
				checkerResponse = userClient.getEmailMobile(request.getNpUserId());
			} catch (Exception e) {
				logger.error(SOMETHING_WENT_WRONG_WHILE_CALLING_USERS_CLIENT,e);
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
					logger.info(MSG_INTO_GETTING_FP_NAME + assignedCheckerForName);
					UserResponse userResponseForName = userClient.getFPDetails(assignedCheckerForName);
					FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
					checkerName = fundProviderDetailsRequest.getFirstName() + " "
							+ (fundProviderDetailsRequest.getLastName() == null ? ""
							: fundProviderDetailsRequest.getLastName());
				} catch (Exception e) {
					logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
				}
			}

			if (LITERAL_NULL.equals(checkerName)) {
				checkerName = PARAMETERS_SIR_MADAM;
			} else {
				checkerName = checkerName != null ? checkerName : PARAMETERS_SIR_MADAM;
			}

			// ========================================================================================================

			parameters.put(PARAMETERS_MAKER_NAME, makerName != null ? makerName : "NA");
			parameters.put(PARAMETERS_CHECKER_NAME, checkerName != null ? checkerName : "NA");
			parameters.put(CommonUtils.PARAMETERS_APPLICATION_ID, request.getApplicationId().toString());

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
			parameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
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
					address = premiseNumber+" "+streetName+" "+landMark;
				}
			}
			else if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
					&& applicationRequest.getBusinessTypeId() == 3){
				address = applicationRequest.getAddress();
			}
			parameters.put(CommonUtils.PARAMETERS_ADDRESS, address != null ? address : "NA");


			if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE))) {

				parameters.put(PARAMETERS_PRODUCT_TYPE,
						proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
								: "NA");
			} else {

				parameters.put(PARAMETERS_PRODUCT_TYPE, "NA");

			}

			UserResponse signUpuser = null;
			try {
				signUpuser = userClient.getEmailMobile(applicationRequest.getUserId());
			} catch (Exception e) {
				logger.error(SOMETHING_WENT_WRONG_WHILE_CALLING_USERS_CLIENT,e);
			}

			if (!CommonUtils.isObjectNullOrEmpty(signUpuser)) {
				UsersRequest signUpUser = MultipleJSONObjectHelper
						.getObjectFromMap((Map<String, Object>) signUpuser.getData(), UsersRequest.class);

				String mobile = signUpUser.getMobile();
				logger.info(MSG_MOBILE_NO + mobile);
				parameters.put(PARAMETERS_MOBILE_NO, mobile != null ? mobile : "NA");

			}

			String subjcet = "Intimation: Assigned - #ApplicationId=" + request.getApplicationId();
			if (!CommonUtils.isObjectNullOrEmpty(assignedChecker)
					&& !CommonUtils.isObjectNullOrEmpty(assignedChecker.getEmail())) {

				String toIds = assignedChecker.getEmail();
				logger.info("Email Sending TO CHECKER on sendMailWhenMakerAssignDDRToChecker===to==>{}", toIds);
				// ====================== MAIL TO CHECKER ======================
				parameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, true);
//				Arun's Code(1786 - 1788)
				if (!CommonUtils.isObjectNullOrEmpty(applicationRequest) && !CommonUtils.isObjectNullOrEmpty(applicationRequest.getBusinessTypeId()) 
						&& !applicationRequest.getBusinessTypeId().equals(CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId())) {
				createNotificationForEmail(toIds, request.getNpUserId().toString(), parameters,
						NotificationAlias.EMAIL_CHECKER_MAKER_ASSIGN_APPLICATION_TO_CHECKER, subjcet);

			}

			if (!CommonUtils.isObjectNullOrEmpty(assignedChecker.getMobile())) {
				Map<String, Object> smsParameters = new HashMap<String, Object>();
				String to = "91" + assignedChecker.getMobile();
				smsParameters.put(PARAMETERS_MAKER_NAME, makerName != null ? makerName : LITERAL_MAKER);
				smsParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");

				smsParameters.put("url", URL_WWW_PSBLOANS_COM);

				sendSMSNotification(request.getNpUserId().toString(), smsParameters,
						NotificationAlias.SMS_CHECKER_MAKER_ASSIGN_APPLICATION_TO_CHECKER, to);
			}

			if (!CommonUtils.isObjectNullOrEmpty(request.getNpUserId())) {
				Map<String, Object> sysParameters = new HashMap<String, Object>();
				sysParameters.put(PARAMETERS_MAKER_NAME, makerName != null ? makerName : LITERAL_MAKER);
				sysParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
				sysParameters.put(PARAMETERS_PRODUCT_TYPE,
						proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
								: "NA");

				sendSYSNotification(request.getApplicationId(), request.getNpUserId().toString(), sysParameters,
						NotificationAlias.SYS_CHECKER_MAKER_ASSIGN_APPLICATION_TO_CHECKER,
						request.getNpUserId().toString(), request.getNpUserId().toString());
			}

			// ====================Sending Mail to HO when Maker Assigns DDR to Checker===========

			String subject = "Intimation: Assigned DDR- #ApplicationId=" + request.getApplicationId();
			UserResponse hoResponse = userClient.getUserDetailByOrgRoleBranchId(applicationRequest.getNpOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.HEAD_OFFICER, branchId);
			List<Map<String, Object>> hoRespList = (List<Map<String, Object>>) hoResponse.getListData();
			if (!CommonUtils.isObjectNullOrEmpty(hoRespList)) {
				for (int i = 0; i < hoRespList.size(); i++) {
					UsersRequest hoObj = MultipleJSONObjectHelper.getObjectFromMap(hoRespList.get(i),
							UsersRequest.class);
					String name = null;
					try {
						logger.info(MSG_INTO_GETTING_FP_NAME + hoObj);
						UserResponse userResponseForName = userClient.getFPDetails(hoObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
								: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
					}

					if (!CommonUtils.isObjectNullOrEmpty(hoObj.getEmail())) {
						String to = hoObj.getEmail();
						if (LITERAL_NULL.equals(name)) {
							name = PARAMETERS_SIR_MADAM;
						} else {
							name = name != null ? name : PARAMETERS_SIR_MADAM;
						}
						
						parameters.put(PARAMETERS_HO_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
//						Arun's Code
						Integer businessTypeId = appProposalMappingRepo.getBusinessIdByUserId(request.getUserId());
						if (!CommonUtils.isObjectNullOrEmpty(businessTypeId) 
								&& ! businessTypeId.equals(CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId())) {
						createNotificationForEmail(to, request.getUserId().toString(), parameters,
								NotificationAlias.EMAIL_HO_MAKER_ASSIGN_APPLICATION_TO_CHECKER, subject);
					}}

					if (!CommonUtils.isObjectNullOrEmpty(hoObj.getMobile())) {
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						String to = "91" + hoObj.getMobile();
						smsParameters.put(PARAMETERS_MAKER_NAME, makerName != null ? makerName : LITERAL_MAKER);
						smsParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");

						smsParameters.put("url", URL_WWW_PSBLOANS_COM);

						sendSMSNotification(hoObj.getId().toString(), smsParameters,
								NotificationAlias.SMS_HO_MAKER_ASSIGN_APPLICATION_TO_CHECKER, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(hoObj.getId())) {
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put(PARAMETERS_MAKER_NAME, makerName != null ? makerName : LITERAL_MAKER);
						sysParameters.put(PARAMETERS_CHECKER_NAME, checkerName != null ? checkerName : LITERAL_CHECKER);
						sysParameters.put(PARAMETERS_PRODUCT_TYPE,
								proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null
										? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
										: "NA");
						sysParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");

						sendSYSNotification(request.getApplicationId(), hoObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_HO_MAKER_ASSIGN_APPLICATION_TO_CHECKER, hoObj.getId().toString(),
								hoObj.getId().toString());
					}
				}

			} else {
				logger.info(MSG_NO_HO_FOUND);
			}

			// =========================================================================================

			// ====================Sending Mail to BO when Maker Assigns DDR to
			// Checker=====================

			subject = "Intimation: Assigned DDR- #ApplicationId=" + request.getApplicationId();

			UserResponse boResponse = userClient.getUserDetailByOrgRoleBranchId(applicationRequest.getNpOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.BRANCH_OFFICER, branchId);
			List<Map<String, Object>> boRespList = (List<Map<String, Object>>) boResponse.getListData();
			if (!CommonUtils.isObjectNullOrEmpty(boRespList)) {
				for (int i = 0; i < boRespList.size(); i++) {
					UsersRequest boObj = MultipleJSONObjectHelper.getObjectFromMap(boRespList.get(i),
							UsersRequest.class);

					String name = null;

					try {
						logger.info(MSG_INTO_GETTING_FP_NAME + boObj);
						UserResponse userResponseForName = userClient.getFPDetails(boObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
								: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
					}

					if (!CommonUtils.isObjectNullOrEmpty(boObj.getEmail())) {
						String to = boObj.getEmail();
						if (LITERAL_NULL.equals(name)) {
							name = PARAMETERS_SIR_MADAM;
						} else {
							name = name != null ? name : PARAMETERS_SIR_MADAM;
						}
						parameters.put(PARAMETERS_BO_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
//						Arun's Code
						Integer businessTypeId = appProposalMappingRepo.getBusinessIdByUserId(request.getUserId());
						if (!CommonUtils.isObjectNullOrEmpty(businessTypeId) 
								&& ! businessTypeId.equals(CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId())) {
						createNotificationForEmail(to, request.getUserId().toString(), parameters,
								NotificationAlias.EMAIL_ALL_BO_MAKER_ASSIGN_APPLICATION_TO_CHECKER, subject);
					}}

					if (!CommonUtils.isObjectNullOrEmpty(boObj.getMobile())) {
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						String to = "91" + boObj.getMobile();
						smsParameters.put(PARAMETERS_MAKER_NAME, makerName != null ? makerName : LITERAL_MAKER);
						smsParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");

						smsParameters.put("url", URL_WWW_PSBLOANS_COM);

						sendSMSNotification(boObj.getId().toString(), smsParameters,
								NotificationAlias.SMS_ALL_BO_MAKER_ASSIGN_APPLICATION_TO_CHECKER, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(boObj.getId())) {
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put(PARAMETERS_MAKER_NAME, makerName != null ? makerName : LITERAL_MAKER);
						sysParameters.put(PARAMETERS_CHECKER_NAME, checkerName != null ? checkerName : LITERAL_CHECKER);
						sysParameters.put(PARAMETERS_PRODUCT_TYPE,
								proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null
										? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
										: "NA");
						sysParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");

						sendSYSNotification(request.getApplicationId(), boObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_ALL_BO_MAKER_ASSIGN_APPLICATION_TO_CHECKER,
								boObj.getId().toString(), boObj.getId().toString());
					}

				}

			} else {
				logger.info(MSG_NO_BO_FOUND);
			}

			// =========================================================================================

		} }catch (Exception e) {
			logger.error("Throw exception while sending mail to Checker/HO/BO when Maker Assign DDR to Checker : ",e);
		}
	}
	// changed
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
				logger.info(CALLING_PROPOSAL_DETAILS_CLIENT_FOR_GETTING_BRANCH_ID , request.getApplicationId());
//				proposalResponse = proposalDetailsClient.getInPricipleById(request.getApplicationId());
				GatewayResponse inpData = gatewayClient.getInPrincipleByApplicationId(request.getApplicationId());
				logger.info(GOT_INPRINCIPLE_RESPONSE_FROM_PROPOSAL_DETAILS_CLIENT , proposalResponse);
				proposalresp = MultipleJSONObjectHelper
						.getObjectFromMap((Map<String, Object>) inpData.getData(), Map.class);
			} catch (Exception e) {
				logger.error(ERROR_CALLING_PROPOSAL_DETAILS_CLIENT_FOR_GETTING_BRANCH_ID , request.getApplicationId());
				logger.error(CommonUtils.EXCEPTION,e);
			}
			Long branchId = null;
			if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(BRANCH_ID))) {
				branchId = Long.valueOf(proposalresp.get(BRANCH_ID).toString());
			}
			// =========================================Getting Maker=====================================
			UsersRequest assignedMakerForName = new UsersRequest();
			assignedMakerForName.setId(request.getUserId());
			String makerName = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedMakerForName)) {
				try {
					logger.info(MSG_INTO_GETTING_FP_NAME , assignedMakerForName);
					UserResponse userResponseForName = userClient.getFPDetails(assignedMakerForName);
					FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
					makerName = fundProviderDetailsRequest.getFirstName() + " "
							+ (fundProviderDetailsRequest.getLastName() == null ? ""
							: fundProviderDetailsRequest.getLastName());
				} catch (Exception e) {
					logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
				}
			}
			if (LITERAL_NULL.equals(makerName)) {
				makerName = LITERAL_MAKER;
			} else {
				makerName = makerName != null ? makerName : LITERAL_MAKER;
			}
			// =========================================Getting Checker=====================================
			UserResponse checkerResponse = null;
			try {
				checkerResponse = userClient.getEmailMobile(request.getNpUserId());
			} catch (Exception e) {
				logger.info(SOMETHING_WENT_WRONG_WHILE_CALLING_USERS_CLIENT);
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
					logger.info(MSG_INTO_GETTING_FP_NAME , assignedCheckerForName);
					UserResponse userResponseForName = userClient.getFPDetails(assignedCheckerForName);
					FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
					checkerName = fundProviderDetailsRequest.getFirstName() + " "
							+ (fundProviderDetailsRequest.getLastName() == null ? ""
							: fundProviderDetailsRequest.getLastName());
				} catch (Exception e) {
					logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
				}
			}
			if (LITERAL_NULL.equals(checkerName)) {
				checkerName = PARAMETERS_SIR_MADAM;
			} else {
				checkerName = checkerName != null ? checkerName : PARAMETERS_SIR_MADAM;
			}
			SimpleDateFormat form = new SimpleDateFormat(DATE_FORMAT_DD_MM_YYYY);
			parameters.put(PARAMETERS_MAKER_NAME, makerName != null ? makerName : LITERAL_MAKER);
			parameters.put(PARAMETERS_CHECKER_NAME, checkerName != null ? checkerName : "NA");
			parameters.put(CommonUtils.PARAMETERS_APPLICATION_ID, request.getApplicationId().toString());
			if (!CommonUtils.isObjectNullOrEmpty(lastModifiedDate)) {
				parameters.put("date", form.format(lastModifiedDate) != null ? form.format(lastModifiedDate) : "NA");
			} else {
				parameters.put("date", "NA");
			}
			if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE))) {

				parameters.put(PARAMETERS_PRODUCT_TYPE,
						proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
								: "NA");
			} else {
				parameters.put(PARAMETERS_PRODUCT_TYPE, "NA");
			}

			// For getting Fund Seeker's Name
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
			parameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
			UserResponse signUpuser = null;
			try {
				signUpuser = userClient.getEmailMobile(applicationRequest.getUserId());
			} catch (Exception e) {
				logger.error(SOMETHING_WENT_WRONG_WHILE_CALLING_USERS_CLIENT,e);
			}
			if (!CommonUtils.isObjectNullOrEmpty(signUpuser)) {
				UsersRequest signUpUser = MultipleJSONObjectHelper
						.getObjectFromMap((Map<String, Object>) signUpuser.getData(), UsersRequest.class);
				String mobile = signUpUser.getMobile();
				logger.info(MSG_MOBILE_NO + mobile);
				parameters.put(PARAMETERS_MOBILE_NO, mobile != null ? mobile : "NA");

			}

			if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
					&& applicationRequest.getBusinessTypeId() == 1){
//				CorporateApplicantRequest applicantRequest = corporateapplicantService.getCorporateApplicant(request.getApplicationId());
				Long proposalId=Long.valueOf(String.valueOf(proposalresp.get(PROPOSAL_ID)));
				if(proposalId!=null) {
					CorporateApplicantRequest applicantRequest = corporateapplicantService.getCorporateApplicantByProposalId(applicationRequest.getUserId(), proposalId);
					if (!CommonUtils.isObjectNullOrEmpty(applicantRequest)
							&& !CommonUtils.isObjectNullOrEmpty(applicantRequest.getFirstAddress())) {
						String premiseNumber = null;
						String streetName = null;
						String landMark = null;
						premiseNumber = applicantRequest.getFirstAddress().getPremiseNumber()!=null?applicantRequest.getFirstAddress().getPremiseNumber():"";
						streetName = applicantRequest.getFirstAddress().getStreetName()!=null?applicantRequest.getFirstAddress().getStreetName():"";
						landMark = applicantRequest.getFirstAddress().getLandMark()!=null?applicantRequest.getFirstAddress().getLandMark():"";
						address = premiseNumber+" "+streetName+" "+landMark;
					}
				}
			}
			else if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
					&& applicationRequest.getBusinessTypeId() == 3){
				address = applicationRequest.getAddress();
			}

			parameters.put(CommonUtils.PARAMETERS_ADDRESS, address != null ? address : "NA");

			String subjcet = "Intimation : Sent Back - #ApplicationId=" + request.getApplicationId();
			if (!CommonUtils.isObjectNullOrEmpty(assignedChecker)
					&& !CommonUtils.isObjectNullOrEmpty(assignedChecker.getEmail())) {

				String toIds = assignedChecker.getEmail();
				logger.info("Email Sending TO CHECKER on sendMailWhenMakerAssignDDRToChecker===to==>{}", toIds);
				parameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, true);
				// ====================== MAIL TO CHECKER ======================
				createNotificationForEmail(toIds, request.getNpUserId().toString(), parameters,
						NotificationAlias.EMAIL_CHECKER_MAKER_REASSIGN_TO_CHECKER, subjcet);
				parameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, false);
			}

			if (!CommonUtils.isObjectNullOrEmpty(assignedChecker.getMobile())) {
				Map<String, Object> smsParameters = new HashMap<String, Object>();
				String to = "91" + assignedChecker.getMobile();
				smsParameters.put(PARAMETERS_MAKER_NAME, makerName != null ? makerName : LITERAL_MAKER);
				smsParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
				if (!CommonUtils.isObjectNullOrEmpty(lastModifiedDate)) {
					smsParameters.put("date",
							form.format(lastModifiedDate) != null ? form.format(lastModifiedDate) : "NA");
				} else {
					smsParameters.put("date", "NA");
				}
				smsParameters.put("url", URL_WWW_PSBLOANS_COM);

				sendSMSNotification(request.getNpUserId().toString(), smsParameters,
						NotificationAlias.SMS_CHECKER_MAKER_REASSIGN_TO_CHECKER, to);
			}

			if (!CommonUtils.isObjectNullOrEmpty(request.getNpUserId())) {
				Map<String, Object> sysParameters = new HashMap<String, Object>();
				sysParameters.put(PARAMETERS_MAKER_NAME, makerName != null ? makerName : LITERAL_MAKER);
				sysParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");

				sendSYSNotification(request.getApplicationId(), request.getNpUserId().toString(), sysParameters,
						NotificationAlias.SYS_CHECKER_MAKER_REASSIGN_TO_CHECKER, request.getNpUserId().toString(),
						request.getNpUserId().toString());
			}

			// ====================Sending Mail to HO when Maker Assigns DDR to Checker====
			String subject = "Intimation: DDR Sent Back - #ApplicationId=" + request.getApplicationId();
			UserResponse hoResponse = userClient.getUserDetailByOrgRoleBranchId(applicationRequest.getNpOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.HEAD_OFFICER, branchId);
			List<Map<String, Object>> hoRespList = (List<Map<String, Object>>) hoResponse.getListData();
			if (!CommonUtils.isObjectNullOrEmpty(hoRespList)) {
				for (int i = 0; i < hoRespList.size(); i++) {
					UsersRequest hoObj = MultipleJSONObjectHelper.getObjectFromMap(hoRespList.get(i),
							UsersRequest.class);
					String name = null;
					try {
						logger.info(MSG_INTO_GETTING_FP_NAME , hoObj);
						UserResponse userResponseForName = userClient.getFPDetails(hoObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
								: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
					}

					if (!CommonUtils.isObjectNullOrEmpty(hoObj.getEmail())) {
						String to = hoObj.getEmail();
						if (LITERAL_NULL.equals(name)) {
							name = PARAMETERS_SIR_MADAM;
						} else {
							name = name != null ? name : PARAMETERS_SIR_MADAM;
						}
						parameters.put(PARAMETERS_HO_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
//						Arun's Code
						Integer businessTypeId = appProposalMappingRepo.getBusinessIdByUserId(request.getUserId());
						if (!CommonUtils.isObjectNullOrEmpty(businessTypeId) 
								&& ! businessTypeId.equals(CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId())) {
						createNotificationForEmail(to, request.getUserId().toString(), parameters,
								NotificationAlias.EMAIL_HO_MAKER_REASSIGN_TO_CHECKER, subject);
						parameters.put("isDynamic", false);
					}}

					if (!CommonUtils.isObjectNullOrEmpty(hoObj.getMobile())) {
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						String to = "91" + hoObj.getMobile();
						smsParameters.put(PARAMETERS_MAKER_NAME, makerName != null ? makerName : LITERAL_MAKER);
						smsParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
						if (!CommonUtils.isObjectNullOrEmpty(lastModifiedDate)) {
							smsParameters.put("date",
									form.format(lastModifiedDate) != null ? form.format(lastModifiedDate) : "NA");
						} else {
							smsParameters.put("date", "NA");
						}
						smsParameters.put("url", URL_WWW_PSBLOANS_COM);

						sendSMSNotification(hoObj.getId().toString(), smsParameters,
								NotificationAlias.SMS_HO_MAKER_REASSIGN_TO_CHECKER, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(hoObj.getId())) {
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put(PARAMETERS_MAKER_NAME, makerName != null ? makerName : LITERAL_MAKER);
						sysParameters.put(PARAMETERS_CHECKER_NAME, checkerName != null ? checkerName : LITERAL_CHECKER);
						sysParameters.put(PARAMETERS_PRODUCT_TYPE,
								proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null
										? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
										: "NA");
						sysParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");

						sendSYSNotification(request.getApplicationId(), hoObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_HO_MAKER_REASSIGN_TO_CHECKER, hoObj.getId().toString(),
								hoObj.getId().toString());
					}
				}
			} else {
				logger.info(MSG_NO_HO_FOUND);
			}
			// ====================Sending Mail to BO when Maker Assigns DDR to Checker======

			subject = "Intimation: DDR Sent Back - #ApplicationId=" + request.getApplicationId();

			UserResponse boResponse = userClient.getUserDetailByOrgRoleBranchId(applicationRequest.getNpOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.BRANCH_OFFICER, branchId);
			List<Map<String, Object>> boRespList = (List<Map<String, Object>>) boResponse.getListData();
			if (!CommonUtils.isObjectNullOrEmpty(boRespList)) {
				for (int i = 0; i < boRespList.size(); i++) {
					UsersRequest boObj = MultipleJSONObjectHelper.getObjectFromMap(boRespList.get(i),
							UsersRequest.class);
					String name = null;
					try {
						logger.info(MSG_INTO_GETTING_FP_NAME , boObj);
						UserResponse userResponseForName = userClient.getFPDetails(boObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
								: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
					}

					if (!CommonUtils.isObjectNullOrEmpty(boObj.getEmail())) {
						String to = boObj.getEmail();
						if (LITERAL_NULL.equals(name)) {
							name = PARAMETERS_SIR_MADAM;
						} else {
							name = name != null ? name : PARAMETERS_SIR_MADAM;
						}
						parameters.put(PARAMETERS_BO_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
						//						Arun's Code
						Integer businessTypeId = appProposalMappingRepo.getBusinessIdByUserId(request.getUserId());
						if (!CommonUtils.isObjectNullOrEmpty(businessTypeId) 
								&& ! businessTypeId.equals(CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId())) {
						createNotificationForEmail(to, request.getUserId().toString(), parameters,
								NotificationAlias.EMAIL_ALL_BO_MAKER_REASSIGN_TO_CHECKER, subject);
					}}

					if (!CommonUtils.isObjectNullOrEmpty(boObj.getMobile())) {
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						String to = "91" + boObj.getMobile();
						smsParameters.put(PARAMETERS_MAKER_NAME, makerName != null ? makerName : LITERAL_MAKER);
						smsParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
						if (!CommonUtils.isObjectNullOrEmpty(lastModifiedDate)) {
							smsParameters.put("date",
									form.format(lastModifiedDate) != null ? form.format(lastModifiedDate) : "NA");
						} else {
							smsParameters.put("date", "NA");
						}
						smsParameters.put("url", URL_WWW_PSBLOANS_COM);

						sendSMSNotification(boObj.getId().toString(), smsParameters,
								NotificationAlias.SMS_ALL_BO_MAKER_REASSIGN_TO_CHECKER, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(boObj.getId())) {
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put(PARAMETERS_MAKER_NAME, makerName != null ? makerName : LITERAL_MAKER);
						sysParameters.put(PARAMETERS_CHECKER_NAME, checkerName != null ? checkerName : LITERAL_CHECKER);
						sysParameters.put(PARAMETERS_PRODUCT_TYPE,
								proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null
										? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
										: "NA");
						sysParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");

						sendSYSNotification(request.getApplicationId(), boObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_ALL_BO_MAKER_REASSIGN_TO_CHECKER, boObj.getId().toString(),
								boObj.getId().toString());
					}

				}

			} else {
				logger.info(MSG_NO_BO_FOUND);
			}
		} catch (Exception e) {
			logger.info("Throw exception while sending mail to Checker/HO/BO when Maker Reassign DDR to Checker");
		}
	}
	@Async
	public void sendEmailToCheckerWhenAdminMakerSendProductForApproval(ProductMasterTemp productMasterTemp, Long userId,
																	   String productType) {

		try {

			logger.info("Into sending Mail to Checker when Admin Maker send product for Approval ");
			String subject = "Intimation: New Product - " + productMasterTemp.getName()+" for "+productType;
			Map<String, Object> mailParameters = new HashMap<String, Object>();

			mailParameters.put(PARAMETERS_PRODUCT_NAME,
					productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
			mailParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");

			UsersRequest adminForMaker = new UsersRequest();
			adminForMaker.setId(userId);

			String adminMakerName = null;
			try {
				logger.info(MSG_INTO_GETTING_FP_NAME , adminForMaker);
				UserResponse userResponseForName = userClient.getFPDetails(adminForMaker);
				FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
						(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
				adminMakerName = fundProviderDetailsRequest.getFirstName() + " "
						+ (fundProviderDetailsRequest.getLastName() == null ? ""
						: fundProviderDetailsRequest.getLastName());
			} catch (Exception e) {
				logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
			}

			if (LITERAL_NULL.equals(adminMakerName)) {
				adminMakerName = LITERAL_MAKER;
			} else {
				adminMakerName = adminMakerName != null ? adminMakerName : LITERAL_MAKER;
			}
			mailParameters.put(PARAMETERS_ADMIN_MAKER, adminMakerName);
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
						logger.info(MSG_INTO_GETTING_FP_NAME , userObj);
						UserResponse userResponseForName = userClient.getFPDetails(userObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
								: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
						logger.info(MSG_MAKER_ID,userObj.getEmail());
						to = userObj.getEmail();
						if (LITERAL_NULL.equals(name)) {
							mailParameters.put(PARAMETERS_ADMIN_CHECKER, PARAMETERS_SIR_MADAM);
						} else {
							mailParameters.put(PARAMETERS_ADMIN_CHECKER, name != null ? name : PARAMETERS_SIR_MADAM);
						}

						createNotificationForEmail(to, userId.toString(), mailParameters,
								NotificationAlias.EMAIL_ADMIN_CHECKER_ADMIN_MAKER_CREATES_PRODUCT, subject);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
						logger.info(MSG_MAKER_ID,userObj.getEmail());
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						to = "91" + userObj.getMobile();
						if (LITERAL_NULL.equals(name)) {
							smsParameters.put(PARAMETERS_ADMIN_CHECKER, PARAMETERS_SIR_MADAM);
						} else {
							smsParameters.put(PARAMETERS_ADMIN_CHECKER, name != null ? name : PARAMETERS_SIR_MADAM);
						}
						smsParameters.put(PARAMETERS_ADMIN_MAKER, adminMakerName != null ? adminMakerName : LITERAL_MAKER);
						smsParameters.put(PARAMETERS_PRODUCT_NAME,
								productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
						smsParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
						smsParameters.put("url", URL_WWW_PSBLOANS_COM);

						sendSMSNotification(userId.toString(), smsParameters,
								NotificationAlias.SMS_ADMIN_CHECKER_ADMIN_MAKER_CREATES_PRODUCT, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
						logger.info(MSG_MAKER_ID,userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put(PARAMETERS_ADMIN_MAKER, adminMakerName != null ? adminMakerName : LITERAL_MAKER);
						sysParameters.put(PARAMETERS_PRODUCT_NAME,
								productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
						sysParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");

						sendSYSNotification(userId, userObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_ADMIN_CHECKER_ADMIN_MAKER_CREATES_PRODUCT,
								userObj.getId().toString(), userObj.getId().toString());
					}

				}

			} else {
				logger.info("No Admin Checker found=================>");
			}

		} catch (Exception e) {

			logger.error(
					"An exception getting while sending Mail to Checker when Admin Maker send product for Approval=============>{}",e);
		}

	}
	@Async
	public void sendEmailToCheckerWhenAdminMakerResendProductForApproval(ProductMasterTemp productMasterTemp,
																		 Long userId, String productType) {
		try {
			logger.info("Into sending Mail to Checker when Admin Maker resend product for Approval");
			String subject = "Intimation: Re-sent Product - " + productMasterTemp.getName()+" for "+productType;
			Map<String, Object> mailParameters = new HashMap<String, Object>();
			mailParameters.put(PARAMETERS_PRODUCT_NAME,
					productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
			mailParameters.put("date",
					productMasterTemp.getModifiedDate() != null ? productMasterTemp.getModifiedDate() : "NA");
			UsersRequest adminForMaker = new UsersRequest();
			adminForMaker.setId(userId);
			String adminMakerName = null;
			try {
				logger.info(MSG_INTO_GETTING_FP_NAME , adminForMaker);
				UserResponse userResponseForName = userClient.getFPDetails(adminForMaker);
				FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
						(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
				adminMakerName = fundProviderDetailsRequest.getFirstName() + " "
						+ (fundProviderDetailsRequest.getLastName() == null ? ""
						: fundProviderDetailsRequest.getLastName());
			} catch (Exception e) {
				logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
			}
			if (LITERAL_NULL.equals(adminMakerName)) {
				adminMakerName = LITERAL_MAKER;
			} else {
				adminMakerName = adminMakerName != null ? adminMakerName : LITERAL_MAKER;
			}
			mailParameters.put(PARAMETERS_ADMIN_MAKER, adminMakerName != null ? adminMakerName : LITERAL_MAKER);
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
						logger.info(MSG_INTO_GETTING_FP_NAME , userObj);
						UserResponse userResponseForName = userClient.getFPDetails(userObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
								: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
					}
					if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
						logger.info(MSG_MAKER_ID,userObj.getEmail());
						to = userObj.getEmail();
						

						if (LITERAL_NULL.equals(name)) {
							mailParameters.put(PARAMETERS_ADMIN_CHECKER, PARAMETERS_SIR_MADAM);
						} else {
							mailParameters.put(PARAMETERS_ADMIN_CHECKER, name != null ? name : PARAMETERS_SIR_MADAM);
						}
//						Arun's Modification(2505 - 2508)
						Integer businessTypeId = appProposalMappingRepo.getBusinessIdByUserId(userId);
						if (!CommonUtils.isObjectNullOrEmpty(businessTypeId) 
								&& ! businessTypeId.equals(CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId())) {
						createNotificationForEmail(to, userId.toString(), mailParameters,
								NotificationAlias.EMAIL_ADMIN_CHECKER_ADMIN_MAKER_RESENDS_PRODUCT, subject);
					}
					
					
					if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
						logger.info(MSG_MAKER_ID,userObj.getEmail());
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						to = "91" + userObj.getMobile();
						if (LITERAL_NULL.equals(name)) {
							smsParameters.put(PARAMETERS_ADMIN_CHECKER, PARAMETERS_SIR_MADAM);
						} else {
							smsParameters.put(PARAMETERS_ADMIN_CHECKER, name != null ? name : PARAMETERS_SIR_MADAM);
						}
						smsParameters.put(PARAMETERS_ADMIN_MAKER, adminMakerName != null ? adminMakerName : LITERAL_MAKER);
						smsParameters.put(PARAMETERS_PRODUCT_NAME,
								productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
						smsParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
						smsParameters.put("url", URL_WWW_PSBLOANS_COM);

						sendSMSNotification(userId.toString(), smsParameters,
								NotificationAlias.SMS_ADMIN_CHECKER_ADMIN_MAKER_RESENDS_PRODUCT, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
						logger.info(MSG_MAKER_ID,userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<>();
						sysParameters.put(PARAMETERS_ADMIN_MAKER, adminMakerName != null ? adminMakerName : LITERAL_MAKER);
						sysParameters.put(PARAMETERS_PRODUCT_NAME,
								productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
						sysParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
						sendSYSNotification(userId, userObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_ADMIN_CHECKER_ADMIN_MAKER_RESENDS_PRODUCT,
								userObj.getId().toString(), userObj.getId().toString());
					}

				}

			} }else {
				logger.info("No Admin Checker found=================>");
			}

		} catch (Exception e) {

			logger.error(
					"An exception getting while sending Mail to Checker when Admin Maker resend product for Approval=============>{}",e);
		}

	}
	@Async
	public void sendEmailToMakerWhenAdminCheckerApprovedProduct(ProductMasterTemp productMasterTemp, Long userId,
																String productType) {
		try {
			logger.info("Into sending Mail to Maker when Admin Checker Approved product");
			Map<String, Object> mailParameters = new HashMap<String, Object>();
			mailParameters.put(PARAMETERS_PRODUCT_NAME,
					productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
			mailParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
			//String subject = "Intimation : " + productType + " - " + productMasterTemp.getName() + " Approved";
			String subject = "Intimation " + "New Product: "+ productMasterTemp.getName() + " Approved - "+"Product ID: "+productMasterTemp.getProductCode();
			UsersRequest adminForChecker = new UsersRequest();
			adminForChecker.setId(userId);
			String adminCheckerName = null;
			try {
				logger.info(MSG_INTO_GETTING_FP_NAME + adminForChecker);
				UserResponse userResponseForName = userClient.getFPDetails(adminForChecker);
				FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
						(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
				adminCheckerName = fundProviderDetailsRequest.getFirstName() + " "
						+ (fundProviderDetailsRequest.getLastName() == null ? ""
						: fundProviderDetailsRequest.getLastName());
			} catch (Exception e) {
				logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
			}
			if (LITERAL_NULL.equals(adminCheckerName)) {
				adminCheckerName = LITERAL_CHECKER;
			} else {
				adminCheckerName = adminCheckerName != null ? adminCheckerName : LITERAL_CHECKER;
			}
			mailParameters.put(PARAMETERS_ADMIN_CHECKER, adminCheckerName);
			UserResponse assignedMakerResponse = null;
			try {
				assignedMakerResponse = userClient.getEmailMobile(productMasterTemp.getCreatedBy());
			} catch (Exception e) {
				logger.info(SOMETHING_WENT_WRONG_WHILE_CALLING_USERS_CLIENT);
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
					logger.info(MSG_INTO_GETTING_FP_NAME , assignedMakerForName);
					UserResponse userResponseForName = userClient.getFPDetails(assignedMakerForName);
					FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
					makerName = fundProviderDetailsRequest.getFirstName() + " "
							+ (fundProviderDetailsRequest.getLastName() == null ? ""
							: fundProviderDetailsRequest.getLastName());
				} catch (Exception e) {
					logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
				}
			}

			// =======================================================================================

			String to = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getEmail())) {
				to = assignedMaker.getEmail();
				if (LITERAL_NULL.equals(makerName)) {
					mailParameters.put(PARAMETERS_ADMIN_MAKER, PARAMETERS_SIR_MADAM);
				} else {
					mailParameters.put(PARAMETERS_ADMIN_MAKER, makerName != null ? makerName : PARAMETERS_SIR_MADAM);
				}
				createNotificationForEmail(to, userId.toString(), mailParameters,
						NotificationAlias.EMAIL_ADMIN_MAKER_PRODUCT_APPROVED_BY_CHECKER, subject);
			}

			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getMobile())) {
				Map<String, Object> smsParameters = new HashMap<>();
				to = "91" + assignedMaker.getMobile();
				smsParameters.put(PARAMETERS_ADMIN_CHECKER, adminCheckerName != null ? adminCheckerName : LITERAL_CHECKER);
				smsParameters.put(PARAMETERS_PRODUCT_NAME,
						productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
				smsParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
				smsParameters.put("url", URL_WWW_PSBLOANS_COM);
				sendSMSNotification(userId.toString(), smsParameters,
						NotificationAlias.SMS_ADMIN_MAKER_PRODUCT_APPROVED_BY_CHECKER, to);
			}

			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getId())) {
				Map<String, Object> sysParameters = new HashMap<>();

				sysParameters.put(PARAMETERS_ADMIN_CHECKER, adminCheckerName != null ? adminCheckerName : LITERAL_CHECKER);
				sysParameters.put(PARAMETERS_PRODUCT_NAME,
						productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
				sysParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
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
						logger.error(MSG_INTO_GETTING_FP_NAME + userObj);
						UserResponse userResponseForName = userClient.getFPDetails(userObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
										: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
						logger.info(MSG_MAKER_ID+userObj.getEmail());
						to = userObj.getEmail();
						if (LITERAL_NULL.equals(name)) {
							mailParameters.put(PARAMETERS_ADMIN_MAKER, PARAMETERS_SIR_MADAM);
						} else {
							mailParameters.put(PARAMETERS_ADMIN_MAKER, name != null ? name : PARAMETERS_SIR_MADAM);
						}

						createNotificationForEmail(to, userId.toString(), mailParameters,
								NotificationAlias.EMAIL_ADMIN_MAKER_PRODUCT_APPROVED_BY_CHECKER, subject);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
						logger.info(MSG_MAKER_ID+userObj.getEmail());
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						to = "91" + userObj.getMobile();
						smsParameters.put(PARAMETERS_ADMIN_CHECKER, adminCheckerName != null ? adminCheckerName : LITERAL_CHECKER);
						smsParameters.put(PARAMETERS_PRODUCT_NAME,
								productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
						smsParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
						smsParameters.put(CommonUtils.URL ,CommonUtils.PSB_URL);

						sendSMSNotification(userId.toString(), smsParameters,
								NotificationAlias.SMS_ADMIN_MAKER_PRODUCT_APPROVED_BY_CHECKER, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
						logger.info(MSG_MAKER_ID+userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put(PARAMETERS_ADMIN_CHECKER, adminCheckerName != null ? adminCheckerName : LITERAL_CHECKER);
						sysParameters.put(PARAMETERS_PRODUCT_NAME,
								productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
						sysParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");

						sendSYSNotification(userId, userObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_ADMIN_MAKER_PRODUCT_APPROVED_BY_CHECKER,
								userObj.getId().toString(), userObj.getId().toString());
					}

				}

			} else {
				logger.info("No Admin Maker found=================>");
			}*/

		} catch (Exception e) {
			logger.error("An exception getting while sending Mail to Maker when Admin Checker Approved product=============>{}",e);
		}

	}
	@Async
	public void sendEmailToMakerWhenAdminCheckerRevertedProduct(ProductMasterTemp productMasterTemp, Long userId,
																String productType) {
		try {
			logger.info("Into sending Mail to Maker when Admin Checker reverted product===>{}");
			String subject = "Intimation :Re-Sent Product - " + productMasterTemp.getName() + " - Modification";
			Map<String, Object> mailParameters = new HashMap<String, Object>();
			mailParameters.put(PARAMETERS_PRODUCT_NAME,
					productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
			mailParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
			
			//Aruns Modification
			Integer businessTypeId = appProposalMappingRepo.getBusinessIdByUserId(userId);
			if (!CommonUtils.isObjectNullOrEmpty(businessTypeId) 
					&& ! businessTypeId.equals(CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId())) {
			
			UsersRequest adminForChecker = new UsersRequest();
			adminForChecker.setId(userId);

			String adminCheckerName = null;
			try {
				logger.info(MSG_INTO_GETTING_FP_NAME + adminForChecker);
				UserResponse userResponseForName = userClient.getFPDetails(adminForChecker);
				FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
						(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
				adminCheckerName = fundProviderDetailsRequest.getFirstName() + " "
						+ (fundProviderDetailsRequest.getLastName() == null ? ""
						: fundProviderDetailsRequest.getLastName());
			} catch (Exception e) {
				logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
			}
			
			
			
			if (LITERAL_NULL.equals(adminCheckerName)) {
				adminCheckerName = LITERAL_CHECKER;
			} else {
				adminCheckerName = adminCheckerName != null ? adminCheckerName : LITERAL_CHECKER;
			}
			mailParameters.put(PARAMETERS_ADMIN_CHECKER, adminCheckerName);
			
			UserResponse assignedMakerResponse = null;
			try {
				assignedMakerResponse = userClient.getEmailMobile(productMasterTemp.getCreatedBy());
			} catch (Exception e) {
				logger.info(SOMETHING_WENT_WRONG_WHILE_CALLING_USERS_CLIENT);
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
					logger.info(MSG_INTO_GETTING_FP_NAME + assignedMakerForName);
					UserResponse userResponseForName = userClient.getFPDetails(assignedMakerForName);
					FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
					makerName = fundProviderDetailsRequest.getFirstName() + " "
							+ (fundProviderDetailsRequest.getLastName() == null ? ""
							: fundProviderDetailsRequest.getLastName());
				} catch (Exception e) {
					logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
				}
			}
			String to = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getEmail())) {
				to = assignedMaker.getEmail();
				if (LITERAL_NULL.equals(makerName)) {
					mailParameters.put(PARAMETERS_ADMIN_MAKER, PARAMETERS_SIR_MADAM);
				} else {
					mailParameters.put(PARAMETERS_ADMIN_MAKER, makerName != null ? makerName : PARAMETERS_SIR_MADAM);
				}
				createNotificationForEmail(to, userId.toString(), mailParameters,
						NotificationAlias.EMAIL_ADMIN_MAKER_PRODUCT_REVERTED_BY_CHECKER, subject);
			}
			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getMobile())) {
				Map<String, Object> smsParameters = new HashMap<String, Object>();
				to = "91" + assignedMaker.getMobile();
				smsParameters.put(PARAMETERS_ADMIN_CHECKER, adminCheckerName != null ? adminCheckerName : LITERAL_CHECKER);
				smsParameters.put(PARAMETERS_PRODUCT_NAME,
						productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
				smsParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
				smsParameters.put("url", URL_WWW_PSBLOANS_COM);

				sendSMSNotification(userId.toString(), smsParameters,
						NotificationAlias.SMS_ADMIN_MAKER_PRODUCT_REVERTED_BY_CHECKER, to);
			}

			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getId())) {
				Map<String, Object> sysParameters = new HashMap<String, Object>();
				sysParameters.put(PARAMETERS_ADMIN_CHECKER, adminCheckerName != null ? adminCheckerName : LITERAL_CHECKER);
				sysParameters.put(PARAMETERS_PRODUCT_NAME,
						productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
				sysParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");

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
						logger.error(MSG_INTO_GETTING_FP_NAME + userObj);
						UserResponse userResponseForName = userClient.getFPDetails(userObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
										: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
						logger.info(MSG_MAKER_ID+userObj.getEmail());
						to = userObj.getEmail();
						if (LITERAL_NULL.equals(name)) {
							mailParameters.put(PARAMETERS_ADMIN_MAKER, PARAMETERS_SIR_MADAM);
						} else {
							mailParameters.put(PARAMETERS_ADMIN_MAKER, name != null ? name : PARAMETERS_SIR_MADAM);
						}
						createNotificationForEmail(to, userId.toString(), mailParameters,
								NotificationAlias.EMAIL_ADMIN_MAKER_PRODUCT_REVERTED_BY_CHECKER, subject);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
						logger.info(MSG_MAKER_ID+userObj.getEmail());
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						to = "91" + userObj.getMobile();
						smsParameters.put(PARAMETERS_ADMIN_CHECKER, adminCheckerName != null ? adminCheckerName : LITERAL_CHECKER);
						smsParameters.put(PARAMETERS_PRODUCT_NAME,
								productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
						smsParameters.put("url",PSB_URL);
						smsParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
						smsParameters.put(CommonUtils.URL ,CommonUtils.PSB_URL);

						sendSMSNotification(userId.toString(), smsParameters,
								NotificationAlias.SMS_ADMIN_MAKER_PRODUCT_REVERTED_BY_CHECKER, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
						logger.info(MSG_MAKER_ID+userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put(PARAMETERS_ADMIN_CHECKER, adminCheckerName != null ? adminCheckerName : LITERAL_CHECKER);
						sysParameters.put(PARAMETERS_PRODUCT_NAME,
								productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
						sysParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");

						sendSYSNotification(userId, userObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_ADMIN_MAKER_PRODUCT_REVERTED_BY_CHECKER,
								userObj.getId().toString(), userObj.getId().toString());
					}

				}

			} else {
				logger.info("No Admin Maker found=================>");
			}*/

		} }catch (Exception e) {
			logger.error("An exception getting while sending Mail to Maker when Admin Checker reverted product=============>{}",e);
		}

	}
	@Async
	public void sendEmailToMakerHOBOWhenCheckerSanctionLoan(LoanSanctionDomain loanSanctionDomainOld) {
		try {
			logger.info("Into sending Mail to Maker/HO/BO when Checker sanction loan ");
			String subject = "Intimation: Sanction - #ApplicationId=" + loanSanctionDomainOld.getApplicationId();
			Map<String, Object> mailParameters = new HashMap<>();
			ProposalMappingResponse proposal = proposalDetailsClient.getProposalByApplicationIdAndUserOrgId(loanSanctionDomainOld.getOrgId(),loanSanctionDomainOld.getApplicationId());
			ProposalMappingRequest prpo= MultipleJSONObjectHelper.getObjectFromMap((Map)proposal.getData(), ApplicationProposalMapping.class);
			LoanApplicationRequest applicationRequest = loanApplicationService.getFromClient(prpo.getId());
			
			ProposalMappingResponse proposalResponse = null;
			Map<String, Object> proposalresp = null;
			try {
				logger.info("Calling Proposal details client {}" , loanSanctionDomainOld.getApplicationId());
				proposalResponse = proposalDetailsClient.getInPricipleById(loanSanctionDomainOld.getApplicationId());
				logger.info(GOT_INPRINCIPLE_RESPONSE_FROM_PROPOSAL_DETAILS_CLIENT , proposalResponse);
				proposalresp = MultipleJSONObjectHelper
						.getObjectFromMap((Map<String, Object>) proposalResponse.getData(), Map.class);
			} catch (Exception e) {
				logger.info("Error calling Proposal Details Client {}" , loanSanctionDomainOld.getApplicationId());
				logger.error(CommonUtils.EXCEPTION,e);
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

			SimpleDateFormat form = new SimpleDateFormat(DATE_FORMAT_DD_MM_YYYY);
			String fpName = proposalresp.get(CommonUtils.PARAMETERS_FP_NAME) != null ? proposalresp.get(CommonUtils.PARAMETERS_FP_NAME).toString() : "NA";
			mailParameters.put(CommonUtils.PARAMETERS_FP_NAME, fpName != null ? fpName : "NA");
			mailParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
			mailParameters.put(CommonUtils.LITERAL_AMOUNT,
					loanSanctionDomainOld.getSanctionAmount() != null ? loanSanctionDomainOld.getSanctionAmount()
							: "NA");
			mailParameters.put("processing_fees",
					loanSanctionDomainOld.getProcessingFee() != null ? loanSanctionDomainOld.getProcessingFee() : "NA");
			mailParameters.put(CommonUtils.LITERAL_AMOUNT,
					loanSanctionDomainOld.getSanctionAmount() != null ? loanSanctionDomainOld.getSanctionAmount()
							: "NA");
			mailParameters.put(PARAMETERS_INTEREST_RATE,
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
			mailParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
			// =========================================================================================================

			UsersRequest checkerForName = new UsersRequest();
			String checkerName = null;
			if(loanSanctionDomainOld.getModifiedBy() != null) {
				checkerForName.setId(Long.valueOf(loanSanctionDomainOld.getModifiedBy()));
				try {
					logger.info(MSG_INTO_GETTING_FP_NAME , checkerForName);
					UserResponse userResponseForName = userClient.getFPDetails(checkerForName);
					FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
					checkerName = fundProviderDetailsRequest.getFirstName() + " "
							+ (fundProviderDetailsRequest.getLastName() == null ? ""
							: fundProviderDetailsRequest.getLastName());
					if (LITERAL_NULL.equals(checkerName)) {
						checkerName = LITERAL_CHECKER;
					} else {
						checkerName = checkerName != null ? checkerName : LITERAL_CHECKER;
					}
					mailParameters.put(PARAMETERS_CHECKER_NAME, checkerName);
				} catch (Exception e) {
					logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
				}
			}

			UserResponse makerResponse = null;
			try {
				makerResponse = userClient.getEmailMobile(applicationRequest.getFpMakerId());
			} catch (Exception e) {
				logger.error(SOMETHING_WENT_WRONG_WHILE_CALLING_USERS_CLIENT,e);
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
				logger.info(MSG_INTO_GETTING_FP_NAME , makerForName);
				UserResponse userResponseForName = userClient.getFPDetails(makerForName);
				FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
						(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
				makerName = fundProviderDetailsRequest.getFirstName() + " "
						+ (fundProviderDetailsRequest.getLastName() == null ? ""
						: fundProviderDetailsRequest.getLastName());
				if (LITERAL_NULL.equals(makerName)) {
					makerName = PARAMETERS_SIR_MADAM;
				} else {
					makerName = makerName != null ? makerName : PARAMETERS_SIR_MADAM;
				}
				mailParameters.put(PARAMETERS_MAKER_NAME, makerName);
			} catch (Exception e) {
				logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
			}
			if(!CommonUtils.isObjectNullOrEmpty(applicationRequest) && !applicationRequest.getBusinessTypeId().equals(CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId())){
				// ===========================Email to Maker======================================
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
					mailParameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, true);
					createNotificationForEmail(toIds, applicationRequest.getFpMakerId().toString(), mailParameters,
							NotificationAlias.EMAIL_MAKER_AFTER_CHECKER_SUBMIT_SANCTION_POPUP, subject);
	
				}
				if (!CommonUtils.isObjectNullOrEmpty(applicationRequest.getFpMakerId())) {
					Map<String, Object> sysParameters = new HashMap<>();
	
					sysParameters.put(PARAMETERS_CHECKER_NAME, checkerName != null ? checkerName : LITERAL_CHECKER);
					sysParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
					sysParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
	
					sendSYSNotification(loanSanctionDomainOld.getApplicationId(),
							applicationRequest.getFpMakerId().toString(), sysParameters,
							NotificationAlias.SYS_MAKER_AFTER_CHECKER_SUBMIT_SANCTION_POPUP,
							applicationRequest.getFpMakerId().toString(), applicationRequest.getFpMakerId().toString());
				}
			
			}
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
						logger.info(MSG_INTO_GETTING_FP_NAME , userObj);
						UserResponse userResponseForName = userClient.getFPDetails(userObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
								: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
						logger.info(MSG_MAKER_ID,userObj.getEmail());
						to = userObj.getEmail();
						if (LITERAL_NULL.equals(name)) {
							mailParameters.put(PARAMETERS_HO_NAME, PARAMETERS_SIR_MADAM);
						} else {
							mailParameters.put(PARAMETERS_HO_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
						}
						mailParameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, true);
						createNotificationForEmail(to, userObj.getId().toString(), mailParameters,
								NotificationAlias.EMAIL_HO_CHECKER_SANCTIONED, subject);
					}
					if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
						logger.info(MSG_MAKER_ID,userObj.getEmail());
						Map<String, Object> smsParameters = new HashMap<>();
						to = "91" + userObj.getMobile();
						smsParameters.put(PARAMETERS_CHECKER_NAME, checkerName != null ? checkerName : LITERAL_CHECKER);
						smsParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
						smsParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
						smsParameters.put(CommonUtils.PARAMETERS_LOAN_AMOUNT,loanSanctionDomainOld.getSanctionAmount() != null ? loanSanctionDomainOld.getSanctionAmount(): "NA");
						smsParameters.put("url", URL_WWW_PSBLOANS_COM);

						sendSMSNotification(userObj.getId().toString(), smsParameters,
								NotificationAlias.SMS_HO_CHECKER_SANCTIONED, to);
					}
					if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
						logger.info(MSG_MAKER_ID,userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put(PARAMETERS_CHECKER_NAME, checkerName != null ? checkerName : LITERAL_CHECKER);
						sysParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
						sysParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");

						sendSYSNotification(loanSanctionDomainOld.getApplicationId(), userObj.getId().toString(),
								sysParameters, NotificationAlias.SYS_HO_CHECKER_SANCTIONED, userObj.getId().toString(),
								userObj.getId().toString());
					}
				}

			} else {
				logger.info(MSG_NO_HO_FOUND);
			}
			// ===========================Email to BO======================================

			userResponse = userClient.getUserDetailByOrgRoleBranchId(applicationRequest.getNpOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.BRANCH_OFFICER, branchId);
			List<Map<String, Object>> boRespList = (List<Map<String, Object>>) userResponse.getListData();
			if (!CommonUtils.isObjectNullOrEmpty(boRespList)) {
				for (int i = 0; i < boRespList.size(); i++) {
					UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(boRespList.get(i),
							UsersRequest.class);
					String name = null;
					try {
						logger.info(MSG_INTO_GETTING_FP_NAME , userObj);
						UserResponse userResponseForName = userClient.getFPDetails(userObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
								: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
					}
					if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
						logger.info(MSG_MAKER_ID,userObj.getEmail());
						to = userObj.getEmail();
						if (LITERAL_NULL.equals(name)) {
							mailParameters.put(PARAMETERS_BO_NAME, PARAMETERS_SIR_MADAM);
						} else {
							mailParameters.put(PARAMETERS_BO_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
						}
						mailParameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, true);
						createNotificationForEmail(to, userObj.getId().toString(), mailParameters,
								NotificationAlias.EMAIL_ALL_BO_CHECKER_SANCTIONED, subject);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
						logger.info(MSG_MAKER_ID,userObj.getEmail());
						Map<String, Object> smsParameters = new HashMap<String, Object>();
						to = "91" + userObj.getMobile();
						smsParameters.put(PARAMETERS_CHECKER_NAME, checkerName != null ? checkerName : LITERAL_CHECKER);
						smsParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
						smsParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
						smsParameters.put(CommonUtils.PARAMETERS_LOAN_AMOUNT,loanSanctionDomainOld.getSanctionAmount() != null ? loanSanctionDomainOld.getSanctionAmount(): "NA");

						smsParameters.put("url", URL_WWW_PSBLOANS_COM);

						sendSMSNotification(userObj.getId().toString(), smsParameters,
								NotificationAlias.SMS_ALL_BO_CHECKER_SANCTIONED, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
						logger.info(MSG_MAKER_ID,userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put(PARAMETERS_CHECKER_NAME, checkerName != null ? checkerName : LITERAL_CHECKER);
						sysParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
						sysParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");

						sendSYSNotification(loanSanctionDomainOld.getApplicationId(), userObj.getId().toString(),
								sysParameters, NotificationAlias.SYS_ALL_BO_CHECKER_SANCTIONED,
								userObj.getId().toString(), userObj.getId().toString());
					}
				}
			} else {
				logger.info(MSG_NO_BO_FOUND);
			}
		} catch (Exception e) {
			logger.error(
					"An exception getting while sending mail to Maker/HO/BO when Checker sanction loan=============>{}",e);
		}
	}
	@Async
	public void sendEmailToFSWhenCheckerSanctionLoan(LoanSanctionDomain loanSanctionDomainOld) {
		try {
			logger.info("Into sending Mail to FS when Checker sanction loan");
			String subject = "Congratulations - Your Loan Has Been Sanctioned!!!";
			Map<String, Object> mailParameters = new HashMap<String, Object>();
			ProposalMappingResponse proposal = proposalDetailsClient.getProposalByApplicationIdAndUserOrgId(loanSanctionDomainOld.getOrgId(),loanSanctionDomainOld.getApplicationId());
			ProposalMappingRequest prpo= MultipleJSONObjectHelper.getObjectFromMap((Map)proposal.getData(), ProposalMappingRequest.class);
			LoanApplicationRequest applicationRequest = loanApplicationService.getFromClient(prpo.getId());

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

			SimpleDateFormat form = new SimpleDateFormat(DATE_FORMAT_DD_MM_YYYY);
			String fpName = " - ";
			//==================For getting Organisation Name==================
			UserResponse userResponse = null;
			Map<String, Object> usersResp = null;
			UserOrganisationRequest organisationRequest = null;
			userResponse = userClient.getOrgNameByOrgId(loanSanctionDomainOld.getOrgId());
			if(!CommonUtils.isObjectNullOrEmpty(userResponse)) {
				usersResp = (Map<String, Object>) userResponse.getData();
				organisationRequest = MultipleJSONObjectHelper.getObjectFromMap(usersResp,UserOrganisationRequest.class);
				fpName = organisationRequest.getOrganisationName();
			}
			//============================================================
			if(!CommonUtils.isObjectNullOrEmpty(loanSanctionDomainOld.getIsSanctionedFrom()) && loanSanctionDomainOld.getIsSanctionedFrom().equals(CommonUtils.sanctionedFrom.INELIGIBLE_USERS_OFFLINE_APPLICATION) ){
				subject = "Congratulations - Your Loan for Manual Application Has Been Sanctioned!!!";
			}
			mailParameters.put(CommonUtils.PARAMETERS_FP_NAME, fpName != null ? fpName : "");
			mailParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "");
			mailParameters.put(CommonUtils.PARAMETERS_LOAN_AMOUNT,loanSanctionDomainOld.getSanctionAmount() != null ? loanSanctionDomainOld.getSanctionAmount(): "NA");
			mailParameters.put("processing_fees",loanSanctionDomainOld.getProcessingFee() != null ? loanSanctionDomainOld.getProcessingFee() : "");
			mailParameters.put(CommonUtils.LITERAL_AMOUNT,loanSanctionDomainOld.getSanctionAmount() != null ? loanSanctionDomainOld.getSanctionAmount()
							: "NA");
			mailParameters.put(PARAMETERS_INTEREST_RATE,loanSanctionDomainOld.getRoi() != null ? loanSanctionDomainOld.getRoi() : "");
			mailParameters.put("tenure",loanSanctionDomainOld.getTenure() != null ? loanSanctionDomainOld.getTenure() : "");
			mailParameters.put("date",form.format(loanSanctionDomainOld.getSanctionDate()) != null
							? form.format(loanSanctionDomainOld.getSanctionDate())
							: "");
			mailParameters.put("remarks",loanSanctionDomainOld.getRemark() != null ? loanSanctionDomainOld.getRemark() : "");

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
							fsName = director.getDirectorsName() != null ? director.getDirectorsName() : PARAMETERS_SIR_MADAM;
							isMainDirector = 1;
						}
					}
					if (isMainDirector == 0) {
						fsName = NTBResponse.get(0).getDirectorsName() != null ? NTBResponse.get(0).getDirectorsName()
								: PARAMETERS_SIR_MADAM;
					}
				} else {
					fsName = PARAMETERS_SIR_MADAM;
				}
			} else {
				fsName = applicationRequest.getUserName() != null ? applicationRequest.getUserName() : PARAMETERS_SIR_MADAM;
			}
			mailParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : PARAMETERS_SIR_MADAM);
			// =========================================================================================================

			UserResponse fsResponse = null;
			try {
				fsResponse = userClient.getEmailMobile(applicationRequest.getUserId());
			} catch (Exception e) {
				logger.error(SOMETHING_WENT_WRONG_WHILE_CALLING_USERS_CLIENT,e);
			}

			UsersRequest fs = null;

			if (fsResponse != null && !CommonUtils.isObjectNullOrEmpty(fsResponse) ) {
				fs = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) fsResponse.getData(),
						UsersRequest.class);
			}

			// ===========================Email to FS======================================

			if (fs != null && !CommonUtils.isObjectNullOrEmpty(fs) && !CommonUtils.isObjectNullOrEmpty(fs.getEmail())) {
				String toIds = fs.getEmail();
				logger.info("Email Sending TO MAKER when Checker sanction loan===to==>{}", toIds);

				/*
				 * // ====================== MAIL TO MAKER old code======================
				 * createNotificationForEmail(toIds, workflowRequest.getUserId().toString(),
				 * parameters, NotificationAlias.MAIL_MKR_DDR_APPROVE, NotificationType.EMAIL,
				 * subjcet);
				 */
				// ====================== MAIL TO MAKER by new code ======================
				mailParameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, false);
				createNotificationForEmail(toIds, applicationRequest.getUserId().toString(), mailParameters,
						NotificationAlias.EMAIL_FS_CHECKER_SANCTIONED, subject);

			}
			if (fs != null && !CommonUtils.isObjectNullOrEmpty(fs.getMobile())) {
				Map<String, Object> smsParameters = new HashMap<String, Object>();
				String to = "91" + fs.getMobile();
				smsParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : PARAMETERS_SIR_MADAM);
				smsParameters.put(CommonUtils.PARAMETERS_FP_NAME, fpName != null ? fpName : "");
				smsParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "");
				smsParameters.put("url", URL_WWW_PSBLOANS_COM);

				sendSMSNotification(applicationRequest.getUserId().toString(), smsParameters,
						NotificationAlias.SMS_FS_CHECKER_SANCTIONED, to);
			}
			if (!CommonUtils.isObjectNullOrEmpty(applicationRequest.getUserId())) {
				Map<String, Object> sysParameters = new HashMap<String, Object>();

				sysParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : PARAMETERS_SIR_MADAM);
				sysParameters.put(CommonUtils.PARAMETERS_FP_NAME, fpName != null ? fpName : "");
				sysParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "");

				sendSYSNotification(loanSanctionDomainOld.getApplicationId(), applicationRequest.getUserId().toString(),
						sysParameters, NotificationAlias.SYS_FS_CHECKER_SANCTIONED,
						applicationRequest.getUserId().toString(), applicationRequest.getUserId().toString());
			}

		} catch (Exception e) {
			logger.error("An exception getting while sending mail to FS when Checker sanction loan=============>{}",e);
		}

	}
	private void createNotificationForEmail(String toNo, String userId, Map<String, Object> mailParameters,
											Long templateId, String emailSubject) throws NotificationException {
		logger.info("Inside send notification===>{}" , toNo);

		NotificationRequest notificationRequest = new NotificationRequest();
		notificationRequest.setClientRefId(userId);
		try{
			notificationRequest.setIsDynamic(((Boolean) mailParameters.get(CommonUtils.PARAMETERS_IS_DYNAMIC)).booleanValue());
		}catch (Exception e) {
			notificationRequest.setIsDynamic(false);
		}

		String[] to = { toNo };
		Notification notification = new Notification();
		notification.setContentType(ContentType.TEMPLATE);
		notification.setTemplateId(templateId);
		notification.setSubject(emailSubject);
		notification.setTo(to);
		notification.setType(NotificationType.EMAIL);
		notification.setFrom(EMAIL_ADDRESS_FROM);
		notification.setParameters(mailParameters);
		notification.setIsDynamic(notificationRequest.getIsDynamic());

		notificationRequest.addNotification(notification);
		sendEmail(notificationRequest);
		logger.info("Outside send notification===>{}" , toNo);
	}

/*
	private void createNotificationForEmailForFundProvider(String toNo, String userId, Map<String, Object> mailParameters,
														   Long templateId, String emailSubject,Long applicationId,Map<String, Object> proposalresp,String[] bcc) throws NotificationException {
		logger.info("Inside send notification===>{}" + toNo);
		NotificationRequest notificationRequest = new NotificationRequest();
		notificationRequest.setClientRefId(userId);
		try{
			notificationRequest.setIsDynamic(((Boolean) mailParameters.get(CommonUtils.PARAMETERS_IS_DYNAMIC)).booleanValue());
		}catch (Exception e) {
			notificationRequest.setIsDynamic(false);
		}

		String[] to = { toNo };
		Notification notification = new Notification();
		notification.setContentType(ContentType.TEMPLATE);
		notification.setTemplateId(templateId);
		notification.setSubject(emailSubject);
		notification.setTo(to);
		notification.setType(NotificationType.EMAIL);
		notification.setFrom(EMAIL_ADDRESS_FROM);
		notification.setParameters(mailParameters);
		notification.setIsDynamic(notificationRequest.getIsDynamic());
if(!CommonUtils.isObjectNullOrEmpty(bcc))
			notification.setBcc(bcc);



		// start attach CAM to Mail

		Long fpProductId = Long.parseLong(proposalresp.get("fp_product_id").toString());
		Map<String,Object> response = camReportPdfDetailsService.getCamReportPrimaryDetails(applicationId,fpProductId,null,false);
		ReportRequest reportRequest = new ReportRequest();
		reportRequest.setParams(response);
		reportRequest.setTemplate("CAMREPORTPRIMARYSIDBI");
		reportRequest.setType("CAMREPORTPRIMARYSIDBI");

        try
        {
            byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
            notification.setFileName("CAM.pdf");
            notification.setContentInBytes(byteArr);
        }
        catch (Exception e)
        {
            logger.error("error while attaching cam report : ",e);
        }



		// end attach CAM to Mail

		notificationRequest.addNotification(notification);
		sendEmail(notificationRequest);
		logger.info("Outside send notification===>{}" + toNo);
	}*/



	private void createNotificationForEmailForFundProvider(String toNo, String userId, Map<String, Object> mailParameters,
														   Long templateId, String emailSubject,Long applicationId,Map<String, Object> proposalresp,String bcc[]) throws NotificationException {
		logger.info("Inside send notification===>{}" + toNo);

		NotificationRequest notificationRequest = new NotificationRequest();
		notificationRequest.setClientRefId(userId);
		try{
			notificationRequest.setIsDynamic(((Boolean) mailParameters.get(CommonUtils.PARAMETERS_IS_DYNAMIC)).booleanValue());
		}catch (Exception e) {
			notificationRequest.setIsDynamic(false);
		}

		String to[] = { toNo };
		Notification notification = new Notification();
		notification.setContentType(ContentType.TEMPLATE);
		notification.setTemplateId(templateId);
		notification.setSubject(emailSubject);
		notification.setTo(to);
		notification.setType(NotificationType.EMAIL);
		notification.setFrom(EMAIL_ADDRESS_FROM);
		notification.setParameters(mailParameters);
		notification.setIsDynamic(notificationRequest.getIsDynamic());
		if(!CommonUtils.isObjectNullOrEmpty(bcc))
			notification.setBcc(bcc);



		// start attach CAM to Mail

		Long fpProductId = Long.parseLong(proposalresp.get("fp_product_id").toString());
		Map<String,Object> response = camReportPdfDetailsService.getCamReportPrimaryDetails(applicationId,fpProductId,null,false);
		ReportRequest reportRequest = new ReportRequest();
		reportRequest.setParams(response);
		reportRequest.setTemplate("CAMREPORTPRIMARYSIDBI");
		reportRequest.setType("CAMREPORTPRIMARYSIDBI");

        try
        {
            byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
            notification.setFileName("CAM.pdf");
            notification.setContentInBytes(byteArr);
        }
        catch (Exception e)
        {
            logger.error("error while attaching cam report : ",e);
        }



		// end attach CAM to Mail


		notificationRequest.addNotification(notification);
		sendEmail(notificationRequest);
		logger.info("Outside send notification===>{}" + toNo);
	}


	private void sendSMSNotification(String userId, Map<String, Object> parameters, Long templateId, String... to)
			throws NotificationException {
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
