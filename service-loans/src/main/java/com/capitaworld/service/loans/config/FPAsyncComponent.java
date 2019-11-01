package com.capitaworld.service.loans.config;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.capitaworld.api.payment.gateway.model.GatewayResponse;
import com.capitaworld.api.reports.ReportRequest;
import com.capitaworld.api.workflow.utility.WorkflowUtils;
import com.capitaworld.client.payment.gateway.GatewayClient;
import com.capitaworld.client.reports.ReportsClient;
import com.capitaworld.service.gst.client.GstClient;
import com.capitaworld.service.loans.domain.fundprovider.ProductMasterTemp;
import com.capitaworld.service.loans.domain.fundprovider.ProposalDetails;
import com.capitaworld.service.loans.domain.fundprovider.RetailModel;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.domain.sanction.LoanSanctionDomain;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.NhbsApplicationRequest;
import com.capitaworld.service.loans.model.PaymentRequest;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.corporate.CorporateFinalInfoRequest;
import com.capitaworld.service.loans.model.retail.RetailApplicantRequest;
import com.capitaworld.service.loans.repository.common.CommonRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.fundprovider.RetailModelRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.ApplicationProposalMappingService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CamReportPdfDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateFinalInfoService;
import com.capitaworld.service.loans.service.fundseeker.corporate.DirectorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.LoanType;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.matchengine.model.ProposalMappingResponse;
import com.capitaworld.service.notification.client.NotificationClient;
import com.capitaworld.service.notification.exceptions.NotificationException;
import com.capitaworld.service.notification.model.Notification;
import com.capitaworld.service.notification.model.NotificationRequest;
import com.capitaworld.service.notification.model.NotificationResponse;
import com.capitaworld.service.notification.utils.ContentType;
import com.capitaworld.service.notification.utils.EmailSubjectAlias;
import com.capitaworld.service.notification.utils.NotificationAlias;
import com.capitaworld.service.notification.utils.NotificationConstants.NotificationProperty.DomainValue;
import com.capitaworld.service.notification.utils.NotificationType;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.BranchUserResponse;
import com.capitaworld.service.users.model.FundProviderDetailsRequest;
import com.capitaworld.service.users.model.UserOrganisationRequest;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;
import com.capitaworld.service.users.utils.CommonUtils.UserRoles;

@Component
public class FPAsyncComponent {

	private static final String USER_NAME = "user_name";

	/**
	 * 
	 */
	private static final String LOAN_TYPE = "loan_type";

	private static final String SIR_MADAM = "Sir/Madam";

	private static final Logger logger = LoggerFactory.getLogger(FPAsyncComponent.class.getName());

	private static final String SUBJECT_INTIMATION_NEW_PROPOSAL = "Intimation : New Proposal ";

	private static final String PARAMETERS_PRODUCT_TYPE = "product_type";
	private static final String PARAMETERS_INTEREST_RATE = "interest_rate";
	private static final String PARAMETERS_MOBILE_NO = "mobile_no";
	private static final String PARAMETERS_MAKER_NAME = "maker_name";
	private static final String PARAMETERS_ADMIN_CHECKER = "admin_checker";
	private static final String PARAMETERS_SIR_MADAM = SIR_MADAM;
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
	private RetailModelRepository retailModelRepo;
	
	@Autowired
	private CommonRepository commonRepo;
	
	@Autowired
	private Environment environment;

	@Autowired
	private RetailApplicantDetailRepository retailDetailRepo;
	
	private static final String EMAIL_ADDRESS_FROM = "no-reply@capitaworld.com";


	@Value("${capitaworld.sidbi.mail.to.maker.checker}")
	private Boolean mailToMakerChecker;

	/*====================This emails are triggered from new payment module.==============================*/
	// ====Sending Mail to all Makers after FS receives In-principle Approval=======changed for the multiple bank=
	@Async
	public void sendEmailToAllMakersWhenFSRecievesInPrinciple(Map<String, Object> proposalresp,PaymentRequest paymentRequest, Long userId, Long orgId) {
		if (mailToMakerChecker) {
			try {
				logger.info("Into sending Mail to all Makers after FS gets In-Principle Approval");
				String subject = SUBJECT_INTIMATION_NEW_PROPOSAL;
				Map<String, Object> mailParameters = new HashMap<String, Object>();
				mailParameters.put(CommonUtils.PARAMETERS_FS_NAME,paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
				mailParameters.put(PARAMETERS_PRODUCT_TYPE,proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE) != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() : "NA");
				mailParameters.put(CommonUtils.PARAMETERS_LOAN_AMOUNT,proposalresp.get(CommonUtils.LITERAL_AMOUNT) != null ? Double.valueOf(proposalresp.get(CommonUtils.LITERAL_AMOUNT).toString())
								: "NA");
				mailParameters.put(CommonUtils.PARAMETERS_EMI_AMOUNT,proposalresp.get(CommonUtils.PARAMETERS_EMI_AMOUNT) != null
								? Double.valueOf(proposalresp.get(CommonUtils.PARAMETERS_EMI_AMOUNT).toString())
								: "NA");
				mailParameters.put(PARAMETERS_INTEREST_RATE,proposalresp.get(CommonUtils.RATE_INTEREST) != null
								? Double.valueOf(proposalresp.get(CommonUtils.RATE_INTEREST).toString())
								: "NA");
				mailParameters.put(CommonUtils.PARAMETERS_APPLICATION_ID,paymentRequest.getApplicationId() != null ? paymentRequest.getApplicationId().toString()
								: "NA");

				UserResponse response = null;
				try {
					response = userClient.getEmailMobile(userId);
				} catch (Exception e) {
					logger.error(SOMETHING_WENT_WRONG_WHILE_CALLING_USERS_CLIENT,e);
				}

				if (!CommonUtils.isObjectNullOrEmpty(response)) {
					UsersRequest signUpUser = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) response.getData(), UsersRequest.class);
					String mobile = signUpUser.getMobile();
					logger.info(MSG_MOBILE_NO , mobile);
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
				Long domainId = DomainValue.MSME.getId();
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
						&& (applicationRequest.getProductId() == CommonUtils.LoanType.PERSONAL_LOAN.getValue() || applicationRequest.getProductId() == CommonUtils.LoanType.HOME_LOAN.getValue()
								|| applicationRequest.getProductId() == CommonUtils.LoanType.AUTO_LOAN.getValue())){
					domainId = DomainValue.RETAIL.getId();
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

				UserResponse userResponse = userClient.getUserDetailByOrgRoleBranchId(orgId,com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_MAKER, branchId);
				List<Map<String, Object>> usersRespList = (List<Map<String, Object>>) userResponse.getListData();
				
				String to = null;
				if (!CommonUtils.isObjectNullOrEmpty(usersRespList)) {
					for (int i = 0; i < usersRespList.size(); i++) {
						UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),UsersRequest.class);
						String name = null;

						try {
							logger.info(MSG_INTO_GETTING_FP_NAME , userObj);
							UserResponse userResponseForName = userClient.getFPDetails(userObj);
							FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
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
								mailParameters.put(PARAMETERS_MAKER_NAME, PARAMETERS_SIR_MADAM);
							} else {
								mailParameters.put(PARAMETERS_MAKER_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
							}

							mailParameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, false);

							String[] bcc = {environment.getRequiredProperty("bccforcam")};
							createNotificationForEmailForFundProvider(to, userId.toString(), mailParameters,
									NotificationAlias.EMAIL_ALL_MAKERS_AFTER_INPRINCIPLE_TO_FS, subject,applicationRequest.getId(),proposalresp,bcc,domainId);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
							logger.info(MSG_MAKER_ID,userObj.getEmail());
							Map<String, Object> smsParameters = new HashMap<String, Object>();
							to = "91" + userObj.getMobile();
							smsParameters.put(CommonUtils.PARAMETERS_FS_NAME,
									paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
							smsParameters.put(PARAMETERS_PRODUCT_TYPE,
									proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE) != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
											: "NA");
							smsParameters.put("url", URL_WWW_PSBLOANS_COM);

							sendSMSNotification(userId.toString(), smsParameters,
									NotificationAlias.SMS_ALL_MAKERS_AFTER_INPRINCIPLE_TO_FS,domainId, to);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
							logger.info(MSG_MAKER_ID,userObj.getEmail());
							Map<String, Object> sysParameters = new HashMap<String, Object>();
							sysParameters.put(CommonUtils.PARAMETERS_FS_NAME,
									paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");

							sendSYSNotification(paymentRequest.getApplicationId(), userObj.getId().toString(),
									sysParameters, NotificationAlias.SYS_ALL_MAKERS_AFTER_INPRINCIPLE_TO_FS,
									userObj.getId().toString(),domainId, userObj.getId().toString());
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
			Long domainId = DomainValue.MSME.getId();
			try {
				Long proposalId=Long.valueOf(String.valueOf(proposalresp.get(PROPOSAL_ID)));
				logger.info("Into sending Mail to all Checkers after FS gets In-Principle Approval");
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
						&& (applicationRequest.getProductId() == CommonUtils.LoanType.PERSONAL_LOAN.getValue() || applicationRequest.getProductId() == CommonUtils.LoanType.HOME_LOAN.getValue())){
					domainId = DomainValue.RETAIL.getId();
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
							logger.info(MSG_CHECKER_ID,userObj.getEmail());
							to = userObj.getEmail();
							if (LITERAL_NULL.equals(name)) {
								mailParameters.put(PARAMETERS_CHECKER_NAME, PARAMETERS_SIR_MADAM);
							} else {
								mailParameters.put(PARAMETERS_CHECKER_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
							}
							mailParameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, true);
							createNotificationForEmailForFundProvider(to, userId.toString(), mailParameters,
									NotificationAlias.EMAIL_ALL_CHECKERS_AFTER_INPRINCIPLE_TO_FS, subject,applicationRequest.getId(),proposalresp,null,domainId);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
							logger.info(MSG_MAKER_ID,userObj.getEmail());
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

							sendSMSNotification(userId.toString(), smsParameters,NotificationAlias.SMS_ALL_CHECKERS_AFTER_INPRINCIPLE_TO_FS,domainId, to);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
							logger.info(MSG_MAKER_ID,userObj.getEmail());
							Map<String, Object> sysParameters = new HashMap<String, Object>();
							sysParameters.put(CommonUtils.PARAMETERS_FS_NAME,
									paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");

							sendSYSNotification(paymentRequest.getApplicationId(), userObj.getId().toString(),
									sysParameters, NotificationAlias.SYS_ALL_CHECKERS_AFTER_INPRINCIPLE_TO_FS, userObj.getId().toString(),domainId, userObj.getId().toString());
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

	//====Sending Mail to HO after FS receives In-principle Approval=======changed for the multiple bank=
	@Async
	public void sendEmailToHOWhenFSRecievesInPrinciple(Map<String, Object> proposalresp, PaymentRequest paymentRequest,
													   Long userId, Long orgId) {
		if (mailToMakerChecker) {
			Long domainId = DomainValue.MSME.getId();
			try {
				logger.info("Into sending Mail to all Checkers after FS gets In-Principle Approval");
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
					UsersRequest signUpUser = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) response.getData(), UsersRequest.class);

					String mobile = signUpUser.getMobile();
					logger.info(MSG_MOBILE_NO , mobile);
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
						&& (applicationRequest.getProductId() == CommonUtils.LoanType.PERSONAL_LOAN.getValue() || applicationRequest.getProductId() == CommonUtils.LoanType.HOME_LOAN.getValue())){
					domainId = DomainValue.RETAIL.getId();
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
							logger.info(MSG_CHECKER_ID,userObj.getEmail());
							to = userObj.getEmail();
							if (LITERAL_NULL.equals(name)) {
								mailParameters.put(PARAMETERS_HO_NAME, PARAMETERS_SIR_MADAM);
							} else {
								mailParameters.put(PARAMETERS_HO_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
							}
							mailParameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, true);
							createNotificationForEmailForFundProvider(to, userId.toString(), mailParameters,
									NotificationAlias.EMAIL_HO_INPRINCIPLE_TO_FS, subject,applicationRequest.getId(),proposalresp,null,domainId);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
							logger.info(MSG_MAKER_ID,userObj.getEmail());
							Map<String, Object> smsParameters = new HashMap<String, Object>();
							to = "91" + userObj.getMobile();
							smsParameters.put(CommonUtils.PARAMETERS_FS_NAME,
									paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
							smsParameters.put(PARAMETERS_PRODUCT_TYPE,
									proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE) != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
											: "NA");
							smsParameters.put("url", URL_WWW_PSBLOANS_COM);

							sendSMSNotification(userId.toString(), smsParameters,NotificationAlias.SMS_HO_INPRINCIPLE_TO_FS,domainId, to);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
							logger.info(MSG_MAKER_ID+userObj.getEmail());
							Map<String, Object> sysParameters = new HashMap<String, Object>();
							sysParameters.put(CommonUtils.PARAMETERS_FS_NAME,paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
							sysParameters.put(PARAMETERS_PRODUCT_TYPE,proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE) != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
											: "NA");

							sendSYSNotification(paymentRequest.getApplicationId(), userObj.getId().toString(),
									sysParameters, NotificationAlias.SYS_HO_INPRINCIPLE_TO_FS,
									userObj.getId().toString(),domainId, userObj.getId().toString());
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
				Long domainId = DomainValue.MSME.getId();
				logger.info("Into sending Mail to all BO after FS gets In-Principle Approval===>{}",userId);
				String subject = SUBJECT_INTIMATION_NEW_PROPOSAL;

				Map<String, Object> mailParameters = new HashMap<String, Object>();
				mailParameters.put(CommonUtils.PARAMETERS_FS_NAME,paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
				mailParameters.put(PARAMETERS_PRODUCT_TYPE,proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE) != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() : "NA");
				mailParameters.put(CommonUtils.PARAMETERS_LOAN_AMOUNT,proposalresp.get(CommonUtils.LITERAL_AMOUNT) != null ? Double.valueOf(proposalresp.get(CommonUtils.LITERAL_AMOUNT).toString())
								: "NA");
				mailParameters.put(CommonUtils.PARAMETERS_EMI_AMOUNT,proposalresp.get(CommonUtils.PARAMETERS_EMI_AMOUNT) != null
								? Double.valueOf(proposalresp.get(CommonUtils.PARAMETERS_EMI_AMOUNT).toString())
								: "NA");
				mailParameters.put(PARAMETERS_INTEREST_RATE,proposalresp.get(CommonUtils.RATE_INTEREST) != null
								? Double.valueOf(proposalresp.get(CommonUtils.RATE_INTEREST).toString())
								: "NA");
				mailParameters.put(CommonUtils.PARAMETERS_APPLICATION_ID,paymentRequest.getApplicationId() != null ? paymentRequest.getApplicationId().toString()
								: "NA");
				UserResponse response = null;
				try {
					response = userClient.getEmailMobile(userId);
				} catch (Exception e) {
					logger.error(SOMETHING_WENT_WRONG_WHILE_CALLING_USERS_CLIENT,e);
				}
				if (!CommonUtils.isObjectNullOrEmpty(response)) {
					UsersRequest signUpUser = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) response.getData(), UsersRequest.class);
					String mobile = signUpUser.getMobile();
					logger.info(MSG_MOBILE_NO , mobile);
					mailParameters.put(PARAMETERS_MOBILE_NO, mobile != null ? mobile : "NA");
				}
				Long proposalId=Long.valueOf(String.valueOf(proposalresp.get(PROPOSAL_ID)));
				LoanApplicationRequest applicationRequest = loanApplicationService.getFromClient(proposalId);
				String address = null;
				if(!CommonUtils.isObjectNullOrEmpty(applicationRequest) && applicationRequest.getBusinessTypeId() == 1){
					/*
					 * changes made for multiple bank
					CorporateApplicantRequest applicantRequest = corporateapplicantService.getCorporateApplicant(paymentRequest.getApplicationId());*/
					CorporateFinalInfoRequest applicantRequest = corporateFinalInfoService.getByProposalId(null, proposalId);
					if (!CommonUtils.isObjectNullOrEmpty(applicantRequest) && !CommonUtils.isObjectNullOrEmpty(applicantRequest.getFirstAddress())) {
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
						&& (applicationRequest.getProductId() == CommonUtils.LoanType.PERSONAL_LOAN.getValue() || applicationRequest.getProductId() == CommonUtils.LoanType.HOME_LOAN.getValue())){
					domainId = DomainValue.RETAIL.getId();
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
							logger.info(MSG_CHECKER_ID,userObj.getEmail());
							to = userObj.getEmail();
							if (LITERAL_NULL.equals(name)) {
								mailParameters.put(PARAMETERS_BO_NAME, PARAMETERS_SIR_MADAM);
							} else {
								mailParameters.put(PARAMETERS_BO_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
							}
							mailParameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, true);
							createNotificationForEmailForFundProvider(to, userId.toString(), mailParameters,
									NotificationAlias.EMAIL_ALL_BO_INPRINCIPLE_TO_FS, subject,applicationRequest.getId(),proposalresp,null,domainId);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getMobile())) {
							logger.info(MSG_MAKER_ID,userObj.getEmail());
							Map<String, Object> smsParameters = new HashMap<>();
							to = "91" + userObj.getMobile();
							smsParameters.put(CommonUtils.PARAMETERS_FS_NAME,
									paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
							smsParameters.put(PARAMETERS_PRODUCT_TYPE,
									proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE) != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
											: "NA");
							smsParameters.put("url", URL_WWW_PSBLOANS_COM);

							sendSMSNotification(userId.toString(), smsParameters,NotificationAlias.SMS_ALL_BO_INPRINCIPLE_TO_FS, domainId,to);
						}

						if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
							logger.info(MSG_MAKER_ID,userObj.getEmail());
							Map<String, Object> sysParameters = new HashMap<String, Object>();
							sysParameters.put(CommonUtils.PARAMETERS_FS_NAME,
									paymentRequest.getNameOfEntity() != null ? paymentRequest.getNameOfEntity() : "NA");
							sysParameters.put(PARAMETERS_PRODUCT_TYPE,
									proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE) != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
											: "NA");

							sendSYSNotification(paymentRequest.getApplicationId(), userObj.getId().toString(),
									sysParameters, NotificationAlias.SYS_ALL_BO_INPRINCIPLE_TO_FS,
									userObj.getId().toString(),domainId, userObj.getId().toString());
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

	// ====================Sending Mail to Maker and all Makers and Checkers when maker accepts Proposal==========
	@Async
	public void sendMailToMakerandAllMakersWhenMakerAcceptProposal(NhbsApplicationRequest request) {
		logger.info("Enter in sending mail to Maker and all Makers When Maker accepts Proposal");
		try {
			Long NotificationAliasId=null;
			Long domainId = DomainValue.MSME.getId();
			ProposalMappingResponse proposalResponse = null;
			Map<String, Object> proposalresp = null;
			try {
				logger.info(CALLING_PROPOSAL_DETAILS_CLIENT_FOR_GETTING_BRANCH_ID , request.getApplicationId());
				GatewayResponse inpRespose = gatewayClient.getInPrincipleByApplicationId(request.getApplicationId());
				logger.info(GOT_INPRINCIPLE_RESPONSE_FROM_PROPOSAL_DETAILS_CLIENT , proposalResponse);
				proposalresp = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) inpRespose.getData(), Map.class);
			} catch (Exception e) {
				logger.error(ERROR_CALLING_PROPOSAL_DETAILS_CLIENT_FOR_GETTING_BRANCH_ID , request.getApplicationId());
				logger.error(CommonUtils.EXCEPTION,e);
			}

			ProposalDetails prop = proposalDetailsRepository.getLastProposalByApplicationId(request.getApplicationId());
			if(prop == null) {
				return;
			}
			
//			convert loan applicationService to applicationProposalMappdingtable for multiple bank changes
			LoanApplicationRequest applicationRequest = loanApplicationService.getFromClient(prop.getId());
			Map<String, Object> parameters = new HashMap<String, Object>();
			String address = null;
			String state = null;
			String city = null;

			// For getting Fund Seeker's Name
			// =========================================================================================================
			String fsName = null;
			if (applicationRequest.getBusinessTypeId() == 2) {
				fsName = getFsNameForNTB(request.getApplicationId());
			} else {
				fsName = applicationRequest.getUserName() != null ? applicationRequest.getUserName() : "NA";
			}
			if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
				&& (applicationRequest.getProductId() == CommonUtils.LoanType.PERSONAL_LOAN.getValue() 
				|| applicationRequest.getProductId() == CommonUtils.LoanType.HOME_LOAN.getValue()
				|| applicationRequest.getProductId() == CommonUtils.LoanType.AUTO_LOAN.getValue())){
				domainId = DomainValue.RETAIL.getId();
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


			CorporateFinalInfoRequest corFinalByProposalId = corporateFinalInfoService.getByProposalId(request.getUserId(),prop.getId());

			if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
					&& applicationRequest.getBusinessTypeId().equals(CommonUtils.BusinessType.EXISTING_BUSINESS.getId())){
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

			parameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
			parameters.put(CommonUtils.PARAMETERS_ADDRESS, address != null ? address : "NA");
			parameters.put("state", state != null ? state : "NA");
			parameters.put("city", city !=null ? city : "NA");
			
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

			String assignedMakerName = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker)) {

				try {
					logger.info(MSG_INTO_GETTING_FP_NAME , assignedMaker);
					UserResponse userResponseForName = userClient.getFPDetails(assignedMaker);
					if(userResponseForName != null && userResponseForName.getData() != null) {
					FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
					assignedMakerName = fundProviderDetailsRequest.getFirstName() + " "
							+ (fundProviderDetailsRequest.getLastName() == null ? ""
							: fundProviderDetailsRequest.getLastName());
					}
				} catch (Exception e) {
					logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
				}
			}
			
			if (LITERAL_NULL.equals(assignedMakerName)) {
				assignedMakerName = LITERAL_MAKER;
			} else {
				assignedMakerName = assignedMakerName != null ? assignedMakerName : LITERAL_MAKER;
			}
			parameters.put(PARAMETERS_MAKER_NAME, assignedMakerName);
			if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE))) {
				parameters.put(PARAMETERS_PRODUCT_TYPE,
						proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
								: "NA");
			} else {
				parameters.put(PARAMETERS_PRODUCT_TYPE, "NA");
			}
			parameters.put(CommonUtils.PARAMETERS_LOAN_AMOUNT,applicationRequest.getLoanAmount() != null ? applicationRequest.getLoanAmount() : "NA");
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
			parameters.put("url", URL_WWW_PSBLOANS_COM);

			// ====================Sending Mail to Maker who accepts Proposal=====================
//			String subject = "Intimation: Proposal Accepted" + assignedMakerName + "ApplicationId"+ applicationRequest.getApplicationCode();
			
			UserResponse allBranchUsers = userClient.getAllBranchUsers(branchId);
			List<String> ccList=new ArrayList<>();
			if(allBranchUsers != null && !allBranchUsers.getListData().isEmpty()) {
				for (Object allUser: allBranchUsers.getListData()) {
					if(allBranchUsers != null) {
						Map<String,Object> objectFromMap2 = MultipleJSONObjectHelper.getObjectFromMap((Map<?, ?>)allUser, Map.class);
						UsersRequest objectFromMap = MultipleJSONObjectHelper.getObjectFromMap(objectFromMap2, UsersRequest.class);
						objectFromMap.setUserRoleId(Long.valueOf(String.valueOf(objectFromMap2.get("userRole"))));
						if(objectFromMap != null && objectFromMap.getUserRoleId() != null
							&& ((objectFromMap.getUserRoleId() == UserRoles.HEAD_OFFICER) 
							||(objectFromMap.getUserRoleId() == UserRoles.BRANCH_OFFICER)
							||(objectFromMap.getUserRoleId() == UserRoles.FP_CHECKER)
							||(objectFromMap.getUserRoleId() == UserRoles.FP_MAKER))) {
							ccList.add(objectFromMap.getEmail());
							/** sms for all ho and bo*/
							if (!CommonUtils.isObjectNullOrEmpty(objectFromMap.getMobile())) {
								String to = "91" + objectFromMap.getMobile();
								sendSMSNotification(String.valueOf(objectFromMap.getUserId()), parameters,NotificationAlias.SMS_ALL_MAKERS_AFTER_MAKER_ACCEPT_PROPOSAL_OF_FS,domainId,to);
							}
	
							if (!CommonUtils.isObjectNullOrEmpty(objectFromMap.getUserId())) {
								sendSYSNotification(request.getApplicationId(), String.valueOf(objectFromMap.getUserId()), parameters,NotificationAlias.SYS_ALL_MAKERS_AFTER_MAKER_ACCEPT_PROPOSAL_OF_FS,
										String.valueOf(objectFromMap.getUserId()),domainId, String.valueOf(objectFromMap.getUserId()));
							}
						}
					}
				}
			}
			String[] cc= {};
			if(!ccList.isEmpty()) {
				cc = Arrays.copyOf(ccList.toArray(), ccList.size(),String[].class);
			}
			
			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker)) {
				if (!CommonUtils.isObjectNullOrEmpty(applicationRequest) && !CommonUtils.isObjectNullOrEmpty(applicationRequest.getBusinessTypeId()) 
						&& !applicationRequest.getBusinessTypeId().equals(CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId())) {	
						if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getEmail())) {
							String toIds = assignedMaker.getEmail();
							logger.info("Email Sending TO MAKER when Maker accepts Proposal===to==>{}" , toIds);
							parameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, true);
							createNotificationForEmail(toIds, request.getUserId().toString(), parameters,NotificationAlias.EMAIL_MAKER_ACCEPT_PROPOSAL_OF_FS,
								EmailSubjectAlias.EMAIL_MAKER_ACCEPT_PROPOSAL_OF_FS.getSubjectId(),domainId,cc);
						}
					if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getMobile())) {
						String to = "91" + assignedMaker.getMobile();
						parameters.put(PARAMETERS_MAKER_NAME, assignedMakerName);
						parameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
						if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE))) {
							parameters.put(PARAMETERS_PRODUCT_TYPE,
						proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null
											? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString(): "NA");
						} else {
							parameters.put(PARAMETERS_PRODUCT_TYPE, "NA");
						}
						parameters.put("url", URL_WWW_PSBLOANS_COM);
						sendSMSNotification(request.getUserId().toString(), parameters, NotificationAlias.SMS_MAKER_ACCEPT_PROPOSAL_OF_FS, domainId,to);
					}
	
					if (!CommonUtils.isObjectNullOrEmpty(request.getUserId())) {
						parameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
	
						sendSYSNotification(request.getApplicationId(), request.getUserId().toString(), parameters,
								NotificationAlias.SYS_MAKER_ACCEPT_PROPOSAL_OF_FS, request.getUserId().toString(),domainId,
								request.getUserId().toString());
					}
				}

			}
			// ==sending email to fs when maker accepted proposL==Email_FS_Accepted_By_MAKER==add mail for  Email_FS_Accepted_By_MAKER
		sendMailToFsWhenMakerAcceptPorposal(fsName, proposalresp, assignedMakerName,applicationRequest,signUpUser,address,NotificationAliasId,domainId);
			// ====================Sending Mail to other Makers that maker has accepted Proposal============== ------ no sending mail on uniform product			
			/*if (!CommonUtils.isObjectNullOrEmpty(applicationRequest) && !CommonUtils.isObjectNullOrEmpty(applicationRequest.getBusinessTypeId()) 
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
						logger.info(MSG_INTO_GETTING_FP_NAME , makerObj);
						UserResponse userResponseForName = userClient.getFPDetails(makerObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
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
								NotificationAlias.EMAIL_ALL_MAKERS_AFTER_MAKER_ACCEPT_PROPOSAL_OF_FS, subject,domainId,null);
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
											? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString(): "NA");
						} else {
							smsParameters.put(PARAMETERS_PRODUCT_TYPE, "NA");
						}
						smsParameters.put("url", URL_WWW_PSBLOANS_COM);

						sendSMSNotification(makerObj.getId().toString(), smsParameters,
								NotificationAlias.SMS_ALL_MAKERS_AFTER_MAKER_ACCEPT_PROPOSAL_OF_FS,domainId,to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(makerObj.getId())
							&& !(makerObj.getId().equals(assignedMaker.getId()))) {
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put(PARAMETERS_MAKER_NAME, assignedMakerName);
						sysParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");

						sendSYSNotification(request.getApplicationId(), makerObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_ALL_MAKERS_AFTER_MAKER_ACCEPT_PROPOSAL_OF_FS,
								makerObj.getId().toString(),domainId, makerObj.getId().toString());
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
					UsersRequest checkerObj = MultipleJSONObjectHelper.getObjectFromMap(checkerRespList.get(i),UsersRequest.class);
					String name = null;
					try {
						logger.info(MSG_INTO_GETTING_FP_NAME , checkerObj);
						UserResponse userResponseForName = userClient.getFPDetails(checkerObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? "": fundProviderDetailsRequest.getLastName());
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
								NotificationAlias.EMAIL_ALL_CHECKERS_AFTER_MAKER_ACCEPT_PROPOSAL, subject,domainId,null);
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
											? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString(): "NA");
						} else {
							smsParameters.put(PARAMETERS_PRODUCT_TYPE, "NA");
						}
						smsParameters.put("url", URL_WWW_PSBLOANS_COM);

						sendSMSNotification(checkerObj.getId().toString(), smsParameters,
								NotificationAlias.SMS_ALL_CHECKERS_AFTER_MAKER_ACCEPT_PROPOSAL,domainId, to);
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
								checkerObj.getId().toString(),domainId,checkerObj.getId().toString());
					}
				}
			} else {
				logger.info("No Checker found=================>");
			}

			// ====================Sending Mail to HO of that branch that maker has accepted Proposal=====================
				UserResponse hoResponse = userClient.getUserDetailByOrgRoleBranchId(applicationRequest.getNpOrgId(),
						com.capitaworld.service.users.utils.CommonUtils.UserRoles.HEAD_OFFICER, branchId);
				List<Map<String, Object>> hoRespList = (List<Map<String, Object>>) hoResponse.getListData();
				if (!CommonUtils.isObjectNullOrEmpty(hoRespList)) {
					for (int i = 0; i < hoRespList.size(); i++) {
						UsersRequest hoObj = MultipleJSONObjectHelper.getObjectFromMap(hoRespList.get(i),UsersRequest.class);
						String name = null;
	
						try {
							logger.info(MSG_INTO_GETTING_FP_NAME , hoObj);
							UserResponse userResponseForName = userClient.getFPDetails(hoObj);
							FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
									.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),FundProviderDetailsRequest.class);
							name = fundProviderDetailsRequest.getFirstName() + " "+ (fundProviderDetailsRequest.getLastName() == null ? "": fundProviderDetailsRequest.getLastName());
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
							createNotificationForEmail(to, hoObj.getId().toString(), parameters,NotificationAlias.EMAIL_HO_MAKER_ACCEPT_PROPOSAL_OF_FS, subject,domainId,null);
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
	
							sendSMSNotification(hoObj.getId().toString(), smsParameters,NotificationAlias.SMS_HO_MAKER_ACCEPT_PROPOSAL_OF_FS,domainId, to);
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
									NotificationAlias.SYS_HO_MAKER_ACCEPT_PROPOSAL_OF_FS, hoObj.getId().toString(),domainId,hoObj.getId().toString());
						}
	
					}
	
				} else {
					logger.info(MSG_NO_HO_FOUND);
				}
			// ====================Sending Mail to all BO of that branch that maker has accepted Proposal=====================
				UserResponse boResponse = userClient.getUserDetailByOrgRoleBranchId(applicationRequest.getNpOrgId(),
						com.capitaworld.service.users.utils.CommonUtils.UserRoles.BRANCH_OFFICER, branchId);
				List<Map<String, Object>> boRespList = (List<Map<String, Object>>) boResponse.getListData();
				if (!CommonUtils.isObjectNullOrEmpty(boRespList)) {
					for (int i = 0; i < boRespList.size(); i++) {
						UsersRequest boObj = MultipleJSONObjectHelper.getObjectFromMap(boRespList.get(i),
								UsersRequest.class);
	
						String name = null;
						try {
							logger.error(MSG_INTO_GETTING_FP_NAME , boObj);
							UserResponse userResponseForName = userClient.getFPDetails(boObj);
							FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
									.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),FundProviderDetailsRequest.class);
							name = fundProviderDetailsRequest.getFirstName() + " "
									+ (fundProviderDetailsRequest.getLastName() == null ? "": fundProviderDetailsRequest.getLastName());
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
							createNotificationForEmail(to, boObj.getId().toString(), parameters,NotificationAlias.EMAIL_ALL_BO_MAKER_ACCEPT_PROPOSAL_OF_FS, subject,domainId,null);
						}
	
						if (!CommonUtils.isObjectNullOrEmpty(boObj.getMobile())) {
							Map<String, Object> smsParameters = new HashMap<String, Object>();
							String to = "91" + boObj.getMobile();
							smsParameters.put(PARAMETERS_MAKER_NAME, assignedMakerName);
							smsParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
							if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE))) {
								smsParameters.put(PARAMETERS_PRODUCT_TYPE,
										proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null
												? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString(): "NA");
							} else {
								smsParameters.put(PARAMETERS_PRODUCT_TYPE, "NA");
							}
							smsParameters.put("url", URL_WWW_PSBLOANS_COM);
	
							sendSMSNotification(boObj.getId().toString(), smsParameters,NotificationAlias.SMS_ALL_BO_MAKER_ACCEPT_PROPOSAL_OF_FS,domainId,to);
						}
	
						if (!CommonUtils.isObjectNullOrEmpty(boObj.getId())) {
							Map<String, Object> sysParameters = new HashMap<String, Object>();
							sysParameters.put(PARAMETERS_MAKER_NAME, assignedMakerName);
							sysParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
							if (!CommonUtils.isObjectNullOrEmpty(proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE))) {
								sysParameters.put(PARAMETERS_PRODUCT_TYPE,
										proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null
												? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() : "NA");
							} else {
								sysParameters.put(PARAMETERS_PRODUCT_TYPE, "NA");
							}
							sendSYSNotification(request.getApplicationId(), boObj.getId().toString(), sysParameters,
									NotificationAlias.SYS_ALL_BO_MAKER_ACCEPT_PROPOSAL_OF_FS, boObj.getId().toString(),domainId,boObj.getId().toString());
						}
					}
				} else {
					logger.info(MSG_NO_BO_FOUND);
				}
			}*/
		}catch (Exception e) {
			logger.error("Throw exception while sending mail to Maker and all Makers when Maker accepts Proposal : ",e);
		}
	}

	// ============Send mail to FS when maker accept the proposal========================
	@Async
	public void sendMailToFsWhenMakerAcceptPorposal(String fsName, Map<String, Object> proposalresp,String assignedMakerName,LoanApplicationRequest applicationRequest, UsersRequest signUpUser, String address,Long notificationAliasId,Long domainId){
		logger.info("Sending email to fs when maker accept proposal {}",proposalresp);
		Map<String, Object> mailParameter = new HashMap<String, Object>();
		mailParameter.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null && !fsName.equals("") ? fsName : "NA");
		mailParameter.put("entity_name", fsName != null && !fsName.equals("")  ? fsName : "NA");
		mailParameter.put(CommonUtils.PARAMETERS_LOAN_TYPE,
				proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE) != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() : "NA");
		mailParameter.put(PARAMETERS_MAKER_NAME, assignedMakerName);
		mailParameter.put(CommonUtils.PARAMETERS_APPLICATION_ID, applicationRequest.getApplicationCode()!=null?applicationRequest.getApplicationCode():"NA");

		String mobile = signUpUser.getMobile();
		mailParameter.put("mobile_number", mobile != null ? mobile : "NA");
		mailParameter.put(CommonUtils.PARAMETERS_ADDRESS, address);
//		String emailSubject = "";
		Object subjectId = EmailSubjectAlias.EMAIL_FS_ACCEPTED_BY_MAKER.getSubjectId();
		if (applicationRequest.getBusinessTypeId() == CommonUtils.BusinessType.EXISTING_BUSINESS.getId()) {
//			emailSubject = "Maker Assigned - For Quick Business Loan Approval ";
		}else if (applicationRequest.getProductId() == CommonUtils.LoanType.PERSONAL_LOAN.getValue() || applicationRequest.getProductId() == CommonUtils.LoanType.HOME_LOAN.getValue() 
					|| applicationRequest.getProductId() == CommonUtils.LoanType.AUTO_LOAN.getValue()){
//			emailSubject = "Maker Assigned - For Quick "+proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()+" Approval ";
			subjectId = EmailSubjectAlias.PL_EMAIL_TO_FS_WHEN_MAKKER_ACCEPT_PROPOSAL.getSubjectId();
		}
		try {
			if(fsName!=null&& address!=null&&mobile!=null&&assignedMakerName!=null&&applicationRequest.getId()!=null) {
				mailParameter.put(CommonUtils.PARAMETERS_IS_DYNAMIC, false);
				createNotificationForEmail(signUpUser.getEmail(), applicationRequest.getUserId().toString(),
						mailParameter, notificationAliasId, subjectId,domainId,null);
				logger.info("Email send to fs when maker accepted porposal");
			}else {
				logger.error("Email is not sent becouse of required values are null:signUpUser.getEmail:{} applicationRequest.getUserId: {} mailParameter: {} NotificationAliasId {} subjectId {}"
						,signUpUser.getEmail(),applicationRequest.getUserId(),mailParameter,notificationAliasId,subjectId);
			}
		} catch (Exception e) {
			logger.error("Error in sending email to fs when porposal asigned to maker :{}" , e);
		}
	}

	@Async
	public void sendMailWhenMakerAssignDDRToChecker(NhbsApplicationRequest request) {
		Long domainId=DomainValue.MSME.getId();
		logger.info("Enter in sending mail to Checker/HO/BO When Maker Assign DDR To Checker");
		try {
			ProposalDetails prop = proposalDetailsRepository.getLastProposalByApplicationId(request.getApplicationId());
			if(prop == null) {
				return;
			}
			Map<String, Object> parameters = new HashMap<String, Object>();
			LoanApplicationRequest applicationRequest = loanApplicationService.getFromClient(prop.getId());
			Long branchId = null;
			if (!CommonUtils.isObjectNullOrEmpty(prop.getBranchId())) {
				branchId = Long.valueOf(String.valueOf(prop.getBranchId()));
			}

			// =========================================Getting Maker=====================================

			UsersRequest assignedMakerForName = new UsersRequest();
			assignedMakerForName.setId(request.getUserId());

			String makerName = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedMakerForName)) {
				try {
					logger.error(MSG_INTO_GETTING_FP_NAME , assignedMakerForName);
					UserResponse userResponseForName = userClient.getFPDetails(assignedMakerForName);
					if(userResponseForName != null && userResponseForName.getData() != null) {
					FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
					makerName = fundProviderDetailsRequest.getFirstName() + " "
							+ (fundProviderDetailsRequest.getLastName() == null ? "": fundProviderDetailsRequest.getLastName());
					}
				} catch (Exception e) {
					logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
				}
			}
			makerName = makerName != null ? makerName : LITERAL_MAKER;

			// =========================================Getting Checker=====================================

			UserResponse checkerResponse = null;
			try {
				checkerResponse = userClient.getEmailMobile(request.getNpUserId());
			} catch (Exception e) {
				logger.error(SOMETHING_WENT_WRONG_WHILE_CALLING_USERS_CLIENT,e);
			}
			UsersRequest assignedChecker = null;
			if (!CommonUtils.isObjectNullOrEmpty(checkerResponse)) {
				assignedChecker = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) checkerResponse.getData(), UsersRequest.class);
			}

			UsersRequest assignedCheckerForName = new UsersRequest();
			assignedCheckerForName.setId(request.getNpUserId());

			String checkerName = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedCheckerForName)) {
				try {
					logger.info(MSG_INTO_GETTING_FP_NAME , assignedCheckerForName);
					UserResponse userResponseForName = userClient.getFPDetails(assignedCheckerForName);
					if(userResponseForName  != null && userResponseForName.getData() != null) {
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
						checkerName = fundProviderDetailsRequest.getFirstName() + " "
							+ (fundProviderDetailsRequest.getLastName() == null ? "": fundProviderDetailsRequest.getLastName());
					}
				} catch (Exception e) {
					logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
				}
			}
			checkerName = checkerName != null ? checkerName : PARAMETERS_SIR_MADAM;
			parameters.put(PARAMETERS_MAKER_NAME, makerName != null ? makerName : "NA");
			parameters.put(PARAMETERS_CHECKER_NAME, checkerName != null ? checkerName : "NA");
			parameters.put(CommonUtils.PARAMETERS_APPLICATION_ID, request.getApplicationId().toString());

			String address = null;

			// For getting Fund Seeker's Name
			// =========================================================================================================
			String fsName = null;
			if (applicationRequest.getBusinessTypeId() == 2) {
				fsName=getFsNameForNTB(request.getApplicationId());
			} else {
				fsName = applicationRequest.getUserName() != null ? applicationRequest.getUserName() : "NA";
			}
			parameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
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
					&& (CommonUtils.LoanType.PERSONAL_LOAN.getValue() == applicationRequest.getProductId() || CommonUtils.LoanType.HOME_LOAN.getValue() == applicationRequest.getProductId())){
				domainId = DomainValue.RETAIL.getId();
				address = applicationRequest.getAddress();
			}
			parameters.put(CommonUtils.PARAMETERS_ADDRESS, address != null ? address : "NA");
			if (!CommonUtils.isObjectNullOrEmpty(applicationRequest.getProductId()	/*proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE)*/)) {
				parameters.put(PARAMETERS_PRODUCT_TYPE,LoanType.getType(applicationRequest.getProductId()).getName());
						/*proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null ? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() : "NA");*/
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
				UsersRequest signUpUser = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) signUpuser.getData(), UsersRequest.class);

				String mobile = signUpUser.getMobile();
				logger.info(MSG_MOBILE_NO , mobile);
				parameters.put(PARAMETERS_MOBILE_NO, mobile != null ? mobile : "NA");

			}
			parameters.put("application_code", applicationRequest.getApplicationCode());
			String subject = "Intimation: Assigned - #ApplicationId=" + applicationRequest.getApplicationCode();
			if (!CommonUtils.isObjectNullOrEmpty(assignedChecker) && !CommonUtils.isObjectNullOrEmpty(assignedChecker.getEmail())) {

				String toIds = assignedChecker.getEmail();
				logger.info("Email Sending TO CHECKER on sendMailWhenMakerAssignDDRToChecker===to==>{}", toIds);
				// ====================== MAIL TO CHECKER ======================
				parameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, true);
				UserResponse allBranchUsers = userClient.getAllBranchUsers(branchId);
				List<String> ccList=new ArrayList<>();
				if(allBranchUsers != null && !allBranchUsers.getListData().isEmpty()) {
					for (Object allUser: allBranchUsers.getListData()) {
						UsersRequest objectFromMap = MultipleJSONObjectHelper.getObjectFromMap((Map<?, ?>)allUser, UsersRequest.class);
						if(allBranchUsers != null && (objectFromMap != null && objectFromMap.getUserRoleId() != null && objectFromMap.getUserRoleId() == UserRoles.HEAD_OFFICER) 
							||(objectFromMap.getUserRoleId() == UserRoles.BRANCH_OFFICER)) {
							ccList.add(objectFromMap.getEmail());
							/** sms for all ho and bo*/
							if (!CommonUtils.isObjectNullOrEmpty(objectFromMap.getMobile())) {
								String to = "91" + objectFromMap.getMobile();
								parameters.put(PARAMETERS_MAKER_NAME, makerName != null ? makerName : LITERAL_MAKER);
								parameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
								parameters.put("url", URL_WWW_PSBLOANS_COM);
								sendSMSNotification(objectFromMap.getUserId().toString(), parameters,NotificationAlias.SMS_CHECKER_MAKER_ASSIGN_APPLICATION_TO_CHECKER,domainId,to);
							}
							/** system notification for all ho and bo */
							if (!CommonUtils.isObjectNullOrEmpty(request.getNpUserId())) {
								sendSYSNotification(request.getApplicationId(), objectFromMap.getUserId().toString(), parameters,
										NotificationAlias.SYS_CHECKER_MAKER_ASSIGN_APPLICATION_TO_CHECKER,objectFromMap.getUserId().toString(),domainId, objectFromMap.getUserId().toString());
							}
						}
					}
				}
				String[] cc= {};
				if(!ccList.isEmpty()) {
					cc = Arrays.copyOf(ccList.toArray(), ccList.size(),String[].class);
				}
				if (!CommonUtils.isObjectNullOrEmpty(applicationRequest) && !CommonUtils.isObjectNullOrEmpty(applicationRequest.getBusinessTypeId()) 
						&& !applicationRequest.getBusinessTypeId().equals(CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId())) {
					createNotificationForEmail(toIds, request.getNpUserId().toString(), parameters, NotificationAlias.EMAIL_CHECKER_MAKER_ASSIGN_APPLICATION_TO_CHECKER, subject,domainId,cc);
				}
			/** sms notification for checker*/
			if (!CommonUtils.isObjectNullOrEmpty(assignedChecker.getMobile())) {
				String to = "91" + assignedChecker.getMobile();
				sendSMSNotification(request.getNpUserId().toString(), parameters,NotificationAlias.SMS_CHECKER_MAKER_ASSIGN_APPLICATION_TO_CHECKER,domainId,to);
			}

			/** system notification for all checker */
			if (!CommonUtils.isObjectNullOrEmpty(request.getNpUserId())) {
				sendSYSNotification(request.getApplicationId(), request.getNpUserId().toString(), parameters,
						NotificationAlias.SYS_CHECKER_MAKER_ASSIGN_APPLICATION_TO_CHECKER,request.getNpUserId().toString(),domainId, request.getNpUserId().toString());
			}

			// ====================Sending Mail to HO when Maker Assigns DDR to Checker===========
		/*	String subject = "Intimation: Assigned DDR";
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
								+ (fundProviderDetailsRequest.getLastName() == null ? "": fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
					}
					Integer businessTypeId = appProposalMappingRepo.getBusinessIdByUserId(request.getUserId());
					if (!CommonUtils.isObjectNullOrEmpty(businessTypeId) && ! businessTypeId.equals(CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId())) {
						if (!CommonUtils.isObjectNullOrEmpty(hoObj.getEmail())) {
							String to = hoObj.getEmail();
							if (LITERAL_NULL.equals(name)) {
								name = PARAMETERS_SIR_MADAM;
							} else {
								name = name != null ? name : PARAMETERS_SIR_MADAM;
							}
							parameters.put(PARAMETERS_HO_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
							createNotificationForEmail(to, request.getUserId().toString(), parameters,NotificationAlias.EMAIL_HO_MAKER_ASSIGN_APPLICATION_TO_CHECKER, subject,domainId,null);
						}
	
						if (!CommonUtils.isObjectNullOrEmpty(hoObj.getMobile())) {
							Map<String, Object> smsParameters = new HashMap<>();
							String to = "91" + hoObj.getMobile();
							smsParameters.put(PARAMETERS_MAKER_NAME, makerName != null && !makerName.equals("") ? makerName : LITERAL_MAKER);
							smsParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
							smsParameters.put("url", URL_WWW_PSBLOANS_COM);
							sendSMSNotification(hoObj.getId().toString(), smsParameters,NotificationAlias.SMS_HO_MAKER_ASSIGN_APPLICATION_TO_CHECKER,domainId,to);
						}
	
						if (!CommonUtils.isObjectNullOrEmpty(hoObj.getId())) {
							Map<String, Object> sysParameters = new HashMap<>();
							sysParameters.put(PARAMETERS_MAKER_NAME, makerName != null && !makerName.equals("") ? makerName : LITERAL_MAKER);
							sysParameters.put(PARAMETERS_CHECKER_NAME, checkerName != null ? checkerName : LITERAL_CHECKER);
							sysParameters.put(PARAMETERS_PRODUCT_TYPE,
							   proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() : "NA");
							sysParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
	
							sendSYSNotification(request.getApplicationId(), hoObj.getId().toString(), sysParameters,
									NotificationAlias.SYS_HO_MAKER_ASSIGN_APPLICATION_TO_CHECKER, hoObj.getId().toString(),domainId,hoObj.getId().toString());
						}
					}
				}

			} else {
				logger.info(MSG_NO_HO_FOUND);
			}*/

			// ====================Sending Mail to BO when Maker Assigns DDR to Checker=====================

			/*subject = "Intimation: Assigned DDR- #ApplicationId=" + request.getApplicationId();

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
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? ""
								: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
					}
					Integer businessTypeId = appProposalMappingRepo.getBusinessIdByUserId(request.getUserId());
					if (!CommonUtils.isObjectNullOrEmpty(businessTypeId) 
							&& ! businessTypeId.equals(CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId())) {
						if (!CommonUtils.isObjectNullOrEmpty(boObj.getEmail())) {
							String to = boObj.getEmail();
							if (LITERAL_NULL.equals(name)) {
								name = PARAMETERS_SIR_MADAM;
							} else {
								name = name != null ? name : PARAMETERS_SIR_MADAM;
							}
							parameters.put(PARAMETERS_BO_NAME,name != null && !name.equals("") ? name : PARAMETERS_SIR_MADAM);
							createNotificationForEmail(to, request.getUserId().toString(), parameters,
									NotificationAlias.EMAIL_ALL_BO_MAKER_ASSIGN_APPLICATION_TO_CHECKER, subject,domainId,null);
						}
	
						if (!CommonUtils.isObjectNullOrEmpty(boObj.getMobile())) {
							Map<String, Object> smsParameters = new HashMap<>();
							String to = "91" + boObj.getMobile();
							smsParameters.put(PARAMETERS_MAKER_NAME,makerName != null && !makerName.equals("")? makerName : LITERAL_MAKER);
							smsParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null && !fsName.equals("")? fsName : "NA");
							smsParameters.put("url", URL_WWW_PSBLOANS_COM);
							sendSMSNotification(boObj.getId().toString(), smsParameters,NotificationAlias.SMS_ALL_BO_MAKER_ASSIGN_APPLICATION_TO_CHECKER, domainId,to);
						}
	
						if (!CommonUtils.isObjectNullOrEmpty(boObj.getId())) {
							Map<String, Object> sysParameters = new HashMap<>();
							sysParameters.put(PARAMETERS_MAKER_NAME, makerName != null && !makerName.equals("")? makerName : LITERAL_MAKER);
							sysParameters.put(PARAMETERS_CHECKER_NAME, checkerName != null && !checkerName.equals("") ? checkerName : LITERAL_CHECKER);
							sysParameters.put(PARAMETERS_PRODUCT_TYPE, proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null
											? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString()
											: "NA");
							sysParameters.put(CommonUtils.PARAMETERS_FS_NAME,fsName != null && !fsName.equals("") ? fsName : "NA");
							sendSYSNotification(request.getApplicationId(), boObj.getId().toString(), sysParameters,
									NotificationAlias.SYS_ALL_BO_MAKER_ASSIGN_APPLICATION_TO_CHECKER,boObj.getId().toString(), domainId, boObj.getId().toString());
						}
					}
				}

			} else {
				logger.info(MSG_NO_BO_FOUND);
			}*/
		} 
			}catch (Exception e) {
			logger.error("Throw exception while sending mail to Checker/HO/BO when Maker Assign DDR to Checker : ",e);
		}
	}
	
	@Async
	public void sendMailWhenMakerReAssignDDRToChecker(NhbsApplicationRequest request, Date lastModifiedDate) {
		Long domainId =DomainValue.MSME.getId();
		logger.info("Enter in sending mail to Checker/HO/BO When Maker Reassign DDR To Checker");
		try {
			Map<String, Object> parameters = new HashMap<>();
			ProposalDetails prop = proposalDetailsRepository.getLastProposalByApplicationId(request.getApplicationId());
			if(prop == null) {
				return;
			}
			LoanApplicationRequest applicationRequest = loanApplicationService.getFromClient(prop.getId());
			Long branchId = null;
			if (!CommonUtils.isObjectNullOrEmpty(prop.getBranchId())) {
				branchId = Long.valueOf(String.valueOf(prop.getBranchId()));
			}
			// =========================================Getting Maker=====================================
			UsersRequest assignedMakerForName = new UsersRequest();
			assignedMakerForName.setId(request.getUserId());
			String makerName = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedMakerForName)) {
				try {
					logger.info(MSG_INTO_GETTING_FP_NAME , assignedMakerForName);
					UserResponse userResponseForName = userClient.getFPDetails(assignedMakerForName);
					if(userResponseForName != null && userResponseForName.getData() != null) {
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
						if(fundProviderDetailsRequest != null) {
							makerName = fundProviderDetailsRequest.getFirstName() + " " + (fundProviderDetailsRequest.getLastName() == null ? ""
							: fundProviderDetailsRequest.getLastName());
						}
					}
				} catch (Exception e) {
					logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
				}
			}
			makerName = makerName != null && !makerName.equals("") ? makerName : LITERAL_MAKER;
			// =========================================Getting Checker=====================================
			UserResponse checkerResponse = null;
			try {
				checkerResponse = userClient.getEmailMobile(request.getNpUserId());
			} catch (Exception e) {
				logger.info(SOMETHING_WENT_WRONG_WHILE_CALLING_USERS_CLIENT);
			}
			UsersRequest assignedChecker = null;
			if (!CommonUtils.isObjectNullOrEmpty(checkerResponse)) {
				assignedChecker = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) checkerResponse.getData(), UsersRequest.class);
			}

			UsersRequest assignedCheckerForName = new UsersRequest();
			assignedCheckerForName.setId(request.getNpUserId());
			String checkerName = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedCheckerForName)) {
				try {
					logger.info(MSG_INTO_GETTING_FP_NAME , assignedCheckerForName);
					UserResponse userResponseForName = userClient.getFPDetails(assignedCheckerForName);
					if(userResponseForName != null && userResponseForName.getData() != null) {
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
								(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
						checkerName = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? "" : fundProviderDetailsRequest.getLastName());
					}
				} catch (Exception e) {
					logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
				}
			}
			checkerName = checkerName!=null && !checkerName.equals("") ? checkerName : PARAMETERS_SIR_MADAM;
			SimpleDateFormat form = new SimpleDateFormat(DATE_FORMAT_DD_MM_YYYY);
			parameters.put(PARAMETERS_MAKER_NAME, makerName != null && !makerName.equals("") ? makerName : LITERAL_MAKER);
			parameters.put(PARAMETERS_CHECKER_NAME, checkerName);
			parameters.put(CommonUtils.PARAMETERS_APPLICATION_ID, request.getApplicationId().toString());
			if (!CommonUtils.isObjectNullOrEmpty(lastModifiedDate)) {
				parameters.put("date", form.format(lastModifiedDate) != null ? form.format(lastModifiedDate) : "NA");
			} 
			if (!CommonUtils.isObjectNullOrEmpty(applicationRequest.getProductId())) {
				parameters.put(PARAMETERS_PRODUCT_TYPE,LoanType.getType(applicationRequest.getProductId()).getName());
			} else {
				parameters.put(PARAMETERS_PRODUCT_TYPE, "NA");
			}

			// For getting Fund Seeker's Name
			String fsName = null;
			String address = null;
			if (applicationRequest.getBusinessTypeId() == 2) {
				fsName=getFsNameForNTB(prop.getApplicationId());
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
				logger.info(MSG_MOBILE_NO , mobile);
				parameters.put(PARAMETERS_MOBILE_NO, mobile != null ? mobile : "NA");
			}

			if(!CommonUtils.isObjectNullOrEmpty(applicationRequest)
					&& applicationRequest.getBusinessTypeId() == 1){
				Long proposalId=Long.valueOf(String.valueOf(prop.getId()));
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
					&& (CommonUtils.LoanType.PERSONAL_LOAN.getValue() == applicationRequest.getProductId() || CommonUtils.LoanType.HOME_LOAN.getValue() == applicationRequest.getProductId())){
				domainId = DomainValue.RETAIL.getId();
				address = applicationRequest.getAddress();
			}
			parameters.put(CommonUtils.PARAMETERS_ADDRESS, address != null ? address : "NA");
			parameters.put("application_code", applicationRequest.getApplicationCode());

			String subjcet = "Intimation : Sent Back - #ApplicationId=" + applicationRequest.getApplicationCode();
			// ====================== MAIL TO CHECKER ======================
			if (!CommonUtils.isObjectNullOrEmpty(assignedChecker) && !CommonUtils.isObjectNullOrEmpty(assignedChecker.getEmail())) {
				String toIds = assignedChecker.getEmail();
				logger.info("Email Sending TO CHECKER on sendMailWhenMakerAssignDDRToChecker===to==>{}", toIds);
				parameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, true);
				UserResponse allBranchUsers = userClient.getAllBranchUsers(branchId);
				List<String> ccList=new ArrayList<>();
				if(allBranchUsers != null && !allBranchUsers.getListData().isEmpty()) {
					for (Object allUser: allBranchUsers.getListData()) {
						UsersRequest objectFromMap = MultipleJSONObjectHelper.getObjectFromMap((Map<?, ?>)allUser, UsersRequest.class);
						if(allBranchUsers != null && (objectFromMap != null && objectFromMap.getUserRoleId() != null 
							&& objectFromMap.getUserRoleId() == UserRoles.HEAD_OFFICER) 
							||(objectFromMap.getUserRoleId() == UserRoles.BRANCH_OFFICER)) {
							ccList.add(objectFromMap.getEmail());
							/** sms for all ho and bo*/
							if (!CommonUtils.isObjectNullOrEmpty(objectFromMap.getMobile())) {
								String to = "91" + objectFromMap.getMobile();
								parameters.put(PARAMETERS_MAKER_NAME, makerName != null ? makerName : LITERAL_MAKER);
								parameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
								parameters.put("url", URL_WWW_PSBLOANS_COM);
								sendSMSNotification(objectFromMap.getUserId().toString(), parameters,NotificationAlias.SMS_CHECKER_MAKER_ASSIGN_APPLICATION_TO_CHECKER,domainId,to);
							}
							/** system notification for all ho and bo */
							if (!CommonUtils.isObjectNullOrEmpty(request.getNpUserId())) {
								sendSYSNotification(request.getApplicationId(), objectFromMap.getUserId().toString(), parameters,
										NotificationAlias.SYS_CHECKER_MAKER_ASSIGN_APPLICATION_TO_CHECKER,objectFromMap.getUserId().toString(),domainId, objectFromMap.getUserId().toString());
							}
						}
					}
				}
				String[] cc= {};
				if(!ccList.isEmpty()) {
					cc = Arrays.copyOf(ccList.toArray(), ccList.size(),String[].class);
				}
				parameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, true);
				createNotificationForEmail(toIds, request.getNpUserId().toString(), parameters,NotificationAlias.EMAIL_CHECKER_MAKER_REASSIGN_TO_CHECKER, subjcet,domainId ,cc);
				parameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, false);
			}
			parameters.put("url", URL_WWW_PSBLOANS_COM);
			if (!CommonUtils.isObjectNullOrEmpty(assignedChecker.getMobile())) {
				String to = "91" + assignedChecker.getMobile();
				parameters.put("date",form.format(lastModifiedDate) != null ? form.format(lastModifiedDate) : "NA");
				sendSMSNotification(request.getNpUserId().toString(), parameters,NotificationAlias.SMS_CHECKER_MAKER_REASSIGN_TO_CHECKER,domainId , to);
			}

			if (!CommonUtils.isObjectNullOrEmpty(request.getNpUserId())) {
				sendSYSNotification(request.getApplicationId(), request.getNpUserId().toString(), parameters,
						NotificationAlias.SYS_CHECKER_MAKER_REASSIGN_TO_CHECKER, request.getNpUserId().toString(),domainId ,request.getNpUserId().toString());
			}

			// ====================Sending Mail to HO when Maker Assigns DDR to Checker====
		/*	String subject = "Intimation: DDR Sent Back - #ApplicationId=" + request.getApplicationId();
			UserResponse hoResponse = userClient.getUserDetailByOrgRoleBranchId(applicationRequest.getNpOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.HEAD_OFFICER, branchId);
			List<Map<String, Object>> hoRespList = (List<Map<String, Object>>) hoResponse.getListData();
			if (!CommonUtils.isObjectNullOrEmpty(hoRespList)) {
				for (int i = 0; i < hoRespList.size(); i++) {
					UsersRequest hoObj = MultipleJSONObjectHelper.getObjectFromMap(hoRespList.get(i),UsersRequest.class);
					String name = null;
					try {
						logger.info(MSG_INTO_GETTING_FP_NAME , hoObj);
						UserResponse userResponseForName = userClient.getFPDetails(hoObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? "" : fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
					}
					Integer businessTypeId = appProposalMappingRepo.getBusinessIdByUserId(request.getUserId());
					if (!CommonUtils.isObjectNullOrEmpty(businessTypeId) && ! businessTypeId.equals(CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId())) {
						if (!CommonUtils.isObjectNullOrEmpty(hoObj.getEmail())) {
							String to = hoObj.getEmail();
							if (LITERAL_NULL.equals(name)) {
								name = PARAMETERS_SIR_MADAM;
							} else {
								name = name != null ? name : PARAMETERS_SIR_MADAM;
							}
							parameters.put(PARAMETERS_HO_NAME, name != null ? name : PARAMETERS_SIR_MADAM);
							createNotificationForEmail(to, request.getUserId().toString(), parameters,NotificationAlias.EMAIL_HO_MAKER_REASSIGN_TO_CHECKER, subject,domainId,null);
							parameters.put("isDynamic", false);
						}
						parameters.put("url", URL_WWW_PSBLOANS_COM);
						if (!CommonUtils.isObjectNullOrEmpty(hoObj.getMobile())) {
//							Map<String, Object> smsParameters = new HashMap<String, Object>();
							String to = "91" + hoObj.getMobile();
//							smsParameters.put(PARAMETERS_MAKER_NAME, makerName != null ? makerName : LITERAL_MAKER);
//							smsParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
//							if (!CommonUtils.isObjectNullOrEmpty(lastModifiedDate)) {
//								smsParameters.put("date",
//										form.format(lastModifiedDate) != null ? form.format(lastModifiedDate) : "NA");
//							} else {
//								smsParameters.put("date", "NA");
//							}
//							smsParameters.put("url", URL_WWW_PSBLOANS_COM);
							sendSMSNotification(hoObj.getId().toString(), parameters,NotificationAlias.SMS_HO_MAKER_REASSIGN_TO_CHECKER,domainId,to);
						}
	
						if (!CommonUtils.isObjectNullOrEmpty(hoObj.getId())) {
//							Map<String, Object> sysParameters = new HashMap<String, Object>();
//							sysParameters.put(PARAMETERS_MAKER_NAME, makerName != null ? makerName : LITERAL_MAKER);
//							sysParameters.put(PARAMETERS_CHECKER_NAME, checkerName != null ? checkerName : LITERAL_CHECKER);
//							sysParameters.put(PARAMETERS_PRODUCT_TYPE,
//									proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() != null
//											? proposalresp.get(CommonUtils.PARAMETERS_LOAN_TYPE).toString() : "NA");
//							sysParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
							sendSYSNotification(request.getApplicationId(), hoObj.getId().toString(), parameters,
									NotificationAlias.SYS_HO_MAKER_REASSIGN_TO_CHECKER, hoObj.getId().toString(),domainId,hoObj.getId().toString());
						}
					}
				}
			} else {
				logger.info(MSG_NO_HO_FOUND);
			}*/
			// ====================Sending Mail to BO when Maker Assigns DDR to Checker======

		/*	subject = "Intimation: DDR Sent Back - #ApplicationId=" + request.getApplicationId();
			UserResponse boResponse = userClient.getUserDetailByOrgRoleBranchId(applicationRequest.getNpOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.BRANCH_OFFICER, branchId);
			List<Map<String, Object>> boRespList = (List<Map<String, Object>>) boResponse.getListData();
			Integer businessTypeId = appProposalMappingRepo.getBusinessIdByUserId(request.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(businessTypeId) && ! businessTypeId.equals(CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId())) {
				if (!CommonUtils.isObjectNullOrEmpty(boRespList)) {
					for (int i = 0; i < boRespList.size(); i++) {
						UsersRequest boObj = MultipleJSONObjectHelper.getObjectFromMap(boRespList.get(i),UsersRequest.class);
						String name = null;
						try {
							logger.info(MSG_INTO_GETTING_FP_NAME , boObj);
							UserResponse userResponseForName = userClient.getFPDetails(boObj);
							FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
									.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
											FundProviderDetailsRequest.class);
							name = fundProviderDetailsRequest.getFirstName() + " "
									+ (fundProviderDetailsRequest.getLastName() == null ? "" : fundProviderDetailsRequest.getLastName());
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
							createNotificationForEmail(to, request.getUserId().toString(), parameters,NotificationAlias.EMAIL_ALL_BO_MAKER_REASSIGN_TO_CHECKER, subject,domainId,null);
						}
	
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
							sendSMSNotification(boObj.getId().toString(), smsParameters, NotificationAlias.SMS_ALL_BO_MAKER_REASSIGN_TO_CHECKER,domainId, to);
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
									NotificationAlias.SYS_ALL_BO_MAKER_REASSIGN_TO_CHECKER, boObj.getId().toString(),domainId,boObj.getId().toString());
						}
					}
				}
			} else {
				logger.info(MSG_NO_BO_FOUND);
			}*/
		} catch (Exception e) {
			logger.info("Throw exception while sending mail to Checker/HO/BO when Maker Reassign DDR to Checker");
		}
	}
	@Async
	public void sendEmailToCheckerWhenAdminMakerSendProductForApproval(ProductMasterTemp productMasterTemp, Long userId,String productType) {
		try {
			Long domainId=DomainValue.MSME.getId();
			logger.info("Into sending Mail to Checker when Admin Maker send product for Approval ");
			String subject = "Intimation: New Product - " + productMasterTemp.getName()+" for "+productType;
			Map<String, Object> mailParameters = new HashMap<String, Object>();
			mailParameters.put(PARAMETERS_PRODUCT_NAME,productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
			mailParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
			if(productMasterTemp != null && productMasterTemp.getProductId() != null && (productMasterTemp.getProductId() == LoanType.HOME_LOAN.getValue() || productMasterTemp.getProductId() == LoanType.PERSONAL_LOAN.getValue())) {
				 domainId=DomainValue.RETAIL.getId();
			}
			
			UsersRequest adminForMaker = new UsersRequest();
			adminForMaker.setId(userId);

			String adminMakerName = null;
			try {
				logger.info(MSG_INTO_GETTING_FP_NAME , adminForMaker);
				UserResponse userResponseForName = userClient.getFPDetails(adminForMaker);
				FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
						(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
				adminMakerName = fundProviderDetailsRequest.getFirstName() + " "+ (fundProviderDetailsRequest.getLastName() == null 
						? "": fundProviderDetailsRequest.getLastName());
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
			UsersRequest firstAdminChecker = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(0),UsersRequest.class);
			List<String> ccEmails=new ArrayList<>();
			
			
			if (!CommonUtils.isObjectNullOrEmpty(usersRespList)) {
				for (int i = 0; i < usersRespList.size(); i++) {
					String to = null;
					UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),UsersRequest.class);
					ccEmails.add(userObj.getEmail());
					String name = null;
					try {
						logger.info(MSG_INTO_GETTING_FP_NAME , userObj);
						UserResponse userResponseForName = userClient.getFPDetails(userObj);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
										FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "
								+ (fundProviderDetailsRequest.getLastName() == null ? "": fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
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

						sendSMSNotification(userId.toString(), smsParameters,NotificationAlias.SMS_ADMIN_CHECKER_ADMIN_MAKER_CREATES_PRODUCT,domainId, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
						logger.info(MSG_MAKER_ID,userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put(PARAMETERS_ADMIN_MAKER, adminMakerName != null ? adminMakerName : LITERAL_MAKER);
						sysParameters.put(PARAMETERS_PRODUCT_NAME,
								productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
						sysParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");

						sendSYSNotification(userId, userObj.getId().toString(), sysParameters,
								NotificationAlias.SYS_ADMIN_CHECKER_ADMIN_MAKER_CREATES_PRODUCT,userObj.getId().toString(),domainId, userObj.getId().toString());
					}

				}
				if(!ccEmails.isEmpty()) {
					ccEmails.remove(ccEmails.indexOf(firstAdminChecker.getEmail()));
				}
				String[] cc=Arrays.copyOf(ccEmails.toArray(),ccEmails.size(),String[].class);
				if (!CommonUtils.isObjectNullOrEmpty(firstAdminChecker.getEmail())) {
					logger.info(MSG_MAKER_ID,firstAdminChecker.getEmail());
					if (LITERAL_NULL.equals(firstAdminChecker.getName())) {
						mailParameters.put(PARAMETERS_ADMIN_CHECKER, PARAMETERS_SIR_MADAM);
					} else {
						mailParameters.put(PARAMETERS_ADMIN_CHECKER, firstAdminChecker.getName() != null ? firstAdminChecker.getName() : PARAMETERS_SIR_MADAM);
					}

					createNotificationForEmail(firstAdminChecker.getEmail(), userId.toString(), mailParameters, NotificationAlias.EMAIL_ADMIN_CHECKER_ADMIN_MAKER_CREATES_PRODUCT, subject,domainId,cc);
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
	public void sendEmailToCheckerWhenAdminMakerResendProductForApproval(ProductMasterTemp productMasterTemp,Long userId, String productType) {
		try {
			Long domainId=DomainValue.MSME.getId();
			logger.info("Into sending Mail to Checker when Admin Maker resend product for Approval");
			String subject = "Intimation: Re-sent Product - " + productMasterTemp.getName()+" for "+productType;
			Map<String, Object> mailParameters = new HashMap<>();
			mailParameters.put(PARAMETERS_PRODUCT_NAME,productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
			mailParameters.put("product_type",productType != null ? productType : "NA");
			mailParameters.put("date",productMasterTemp.getModifiedDate() != null ? productMasterTemp.getModifiedDate() : "NA");
			UsersRequest adminForMaker = new UsersRequest();
			adminForMaker.setId(userId);
			String adminMakerName = null;
			try {
				logger.info(MSG_INTO_GETTING_FP_NAME , adminForMaker);
				UserResponse userResponseForName = userClient.getFPDetails(adminForMaker);
				FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
						(Map<Object, Object>) userResponseForName.getData(), FundProviderDetailsRequest.class);
				adminMakerName = fundProviderDetailsRequest.getFirstName() + " "
						+ (fundProviderDetailsRequest.getLastName() == null ? "" : fundProviderDetailsRequest.getLastName());
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
			String to = "";
			String[] cc= {};
			if (!CommonUtils.isObjectNullOrEmpty(usersRespList)) {
				
				UsersRequest firstBranchUser = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(0),UsersRequest.class);
					String name = null;
					try {
						UserResponse userResponseForName = userClient.getFPDetails(firstBranchUser);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),FundProviderDetailsRequest.class);
						name = fundProviderDetailsRequest.getFirstName() + " "+ (fundProviderDetailsRequest.getLastName() == null ? ""
								: fundProviderDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
					}
					if(productMasterTemp!=null && productMasterTemp.getProductId()!= null 
							&& (productMasterTemp.getProductId() == LoanType.PERSONAL_LOAN.getValue() 
							|| productMasterTemp.getProductId() == LoanType.HOME_LOAN.getValue())) {
						domainId = DomainValue.RETAIL.getId();
					}
					if (LITERAL_NULL.equals(name)) {
						mailParameters.put(PARAMETERS_ADMIN_CHECKER, PARAMETERS_SIR_MADAM);
					} else {
						mailParameters.put(PARAMETERS_ADMIN_CHECKER, name != null ? name : PARAMETERS_SIR_MADAM);
					}
					/** getting user for cc*/
					final Long tempDomId = domainId;
					List<String> tocc=new ArrayList<>();
					for (Map<String,Object> us : usersRespList) {
						UsersRequest user = MultipleJSONObjectHelper.getObjectFromMap(us,UsersRequest.class);
						try {
							tocc.add(user.getEmail());
							if (!CommonUtils.isObjectNullOrEmpty(user.getMobile())) {
								logger.info(MSG_MAKER_ID,user.getEmail());
								String nameForSmsandSystem = null;
								try {
									logger.info(MSG_INTO_GETTING_FP_NAME , user);
									UserResponse userResponseForName = userClient.getFPDetails(user);
									FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
													FundProviderDetailsRequest.class);
									nameForSmsandSystem = fundProviderDetailsRequest.getFirstName() + " "+ (fundProviderDetailsRequest.getLastName() == null ? ""
											: fundProviderDetailsRequest.getLastName());
								} catch (Exception e) {
									logger.error(ERROR_WHILE_FETCHING_FP_NAME,e);
								}
								
								String toSms = "91" + user.getMobile();
								if (LITERAL_NULL.equals(nameForSmsandSystem)) {
									mailParameters.put(PARAMETERS_ADMIN_CHECKER, PARAMETERS_SIR_MADAM);
								} else {
									mailParameters.put(PARAMETERS_ADMIN_CHECKER, nameForSmsandSystem != null ? nameForSmsandSystem : PARAMETERS_SIR_MADAM);
								}
								mailParameters.put(PARAMETERS_PRODUCT_NAME, productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
								mailParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
								mailParameters.put("url", URL_WWW_PSBLOANS_COM);
								sendSMSNotification(userId.toString(), mailParameters,NotificationAlias.SMS_ADMIN_CHECKER_ADMIN_MAKER_RESENDS_PRODUCT,tempDomId, toSms);
							}
		
							if (!CommonUtils.isObjectNullOrEmpty(user.getId())) {
								logger.info(MSG_MAKER_ID,user.getEmail());
								sendSYSNotification(userId, user.getId().toString(), mailParameters,
										NotificationAlias.SYS_ADMIN_CHECKER_ADMIN_MAKER_RESENDS_PRODUCT,user.getId().toString(), tempDomId,user.getId().toString());
								
							}
						} catch (NotificationException e) {
							logger.error("Exception in sending system notification and sms to user {}",user.getMobile());
						}
					}
					
					to=tocc.get(0);
					if (!CommonUtils.isObjectNullOrEmpty(tocc.get(0))) {
						tocc.remove(tocc.indexOf(to));
					}	
					cc = Arrays.copyOf(tocc.toArray(), tocc.size(),String[].class);
					createNotificationForEmail(to, userId.toString(), mailParameters, NotificationAlias.EMAIL_ADMIN_CHECKER_ADMIN_MAKER_RESENDS_PRODUCT, subject,domainId,cc);
			  }else {
				logger.info("No Admin Checker found=================>");
			  }
		} catch (Exception e) {
			logger.error("An exception getting while sending Mail to Checker when Admin Maker resend product for Approval=============>{}",e);
		}

	}
	@Async
	public void sendEmailToMakerWhenAdminCheckerApprovedProduct(ProductMasterTemp productMasterTemp, Long userId,String productType) {
		try {
			Long domainId = DomainValue.MSME.getId();
			logger.info("Into sending Mail to Maker when Admin Checker Approved product");
			Map<String, Object> mailParameters = new HashMap<String, Object>();
			mailParameters.put(PARAMETERS_PRODUCT_NAME,productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
			mailParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
			mailParameters.put("product_code", productMasterTemp.getProductCode() != null ? productMasterTemp.getProductCode() : "");
			String subject = "Intimation  New Product: "+ productMasterTemp.getName() + " Approved - "+"Product ID: "+productMasterTemp.getProductCode();
			UsersRequest adminForChecker = new UsersRequest();
			adminForChecker.setId(userId);
			String adminCheckerName = null;
			try {
				logger.info(MSG_INTO_GETTING_FP_NAME , adminForChecker);
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
			if(productMasterTemp!=null && productMasterTemp.getProductId()!= null && (productMasterTemp.getProductId() == LoanType.PERSONAL_LOAN.getValue() || 
					productMasterTemp.getProductId() == LoanType.HOME_LOAN.getValue())) {
				domainId = DomainValue.RETAIL.getId();
			}
			ArrayList<String>ccList=new ArrayList<>();
			UserResponse userResponse = userClient.getUserDetailByOrgRoleId(productMasterTemp.getUserOrgId(),
					com.capitaworld.service.users.utils.CommonUtils.UserRoles.ADMIN_MAKER);
			List<Map<String, Object>> usersRespList = (List<Map<String, Object>>) userResponse.getListData();
			for (Object obj: userResponse.getListData()) {
				UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap((Map) obj,Object.class);
				ccList.add(userObj.getEmail());
			}
			String[] cc= (String[]) ccList.toArray();
			String to = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getEmail())) {
				to = assignedMaker.getEmail();
				if (LITERAL_NULL.equals(makerName)) {
					mailParameters.put(PARAMETERS_ADMIN_MAKER, PARAMETERS_SIR_MADAM);
				} else {
					mailParameters.put(PARAMETERS_ADMIN_MAKER, makerName != null ? makerName : PARAMETERS_SIR_MADAM);
				}
				createNotificationForEmail(to, userId.toString(), mailParameters,NotificationAlias.EMAIL_ADMIN_MAKER_PRODUCT_APPROVED_BY_CHECKER, subject,domainId,cc);
			}

			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getMobile())) {
				Map<String, Object> smsParameters = new HashMap<>();
				to = "91" + assignedMaker.getMobile();
				smsParameters.put(PARAMETERS_ADMIN_CHECKER, adminCheckerName != null ? adminCheckerName : LITERAL_CHECKER);
				smsParameters.put(PARAMETERS_PRODUCT_NAME,productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
				smsParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
				smsParameters.put("url", URL_WWW_PSBLOANS_COM);
				sendSMSNotification(userId.toString(), smsParameters,NotificationAlias.SMS_ADMIN_MAKER_PRODUCT_APPROVED_BY_CHECKER,domainId, to);
			}

			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getId())) {
				Map<String, Object> sysParameters = new HashMap<>();

				sysParameters.put(PARAMETERS_ADMIN_CHECKER, adminCheckerName != null ? adminCheckerName : LITERAL_CHECKER);
				sysParameters.put(PARAMETERS_PRODUCT_NAME,productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
				sysParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
				sendSYSNotification(userId, assignedMaker.getId().toString(), sysParameters,
						NotificationAlias.SYS_ADMIN_MAKER_PRODUCT_APPROVED_BY_CHECKER,assignedMaker.getId().toString(),domainId,assignedMaker.getId().toString());
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
	public void sendEmailToMakerWhenAdminCheckerRevertedProduct(ProductMasterTemp productMasterTemp, Long userId,String productType) {
		try {
			Long domainId=DomainValue.MSME.getId();
			logger.info("Into sending Mail to Maker when Admin Checker reverted product");
//			String subject = "Intimation :Re-Sent Product - " + productMasterTemp.getName() + " - Modification";
			Map<String, Object> mailParameters = new HashMap<String, Object>();
			mailParameters.put(PARAMETERS_PRODUCT_NAME,
					productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
			mailParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
			
			//Aruns Modification
			Integer businessTypeId = appProposalMappingRepo.getBusinessIdByUserId(userId);
			if (!CommonUtils.isObjectNullOrEmpty(businessTypeId) && ! businessTypeId.equals(CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId())) {
			
			UsersRequest adminForChecker = new UsersRequest();
			adminForChecker.setId(userId);

			String adminCheckerName = null;
			try {
				logger.info(MSG_INTO_GETTING_FP_NAME , adminForChecker);
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
			
			if(productMasterTemp!=null && productMasterTemp.getProductId()!= null && (productMasterTemp.getProductId() == LoanType.PERSONAL_LOAN.getValue() || 
					productMasterTemp.getProductId() == LoanType.HOME_LOAN.getValue())) {
				domainId = DomainValue.RETAIL.getId();
			}
			
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
			String to = null;
			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getEmail())) {
				to = assignedMaker.getEmail();
				if (LITERAL_NULL.equals(makerName)) {
					mailParameters.put(PARAMETERS_ADMIN_MAKER, PARAMETERS_SIR_MADAM);
				} else {
					mailParameters.put(PARAMETERS_ADMIN_MAKER, makerName != null ? makerName : PARAMETERS_SIR_MADAM);
				}
				createNotificationForEmail(to, userId.toString(), mailParameters,NotificationAlias.EMAIL_ADMIN_MAKER_PRODUCT_REVERTED_BY_CHECKER, 
						EmailSubjectAlias.EMAIL_ADMIN_MAKER_PRODUCT_REVERTED_BY_CHECKER.getSubjectId(),domainId,null);
			}
			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getMobile())) {
				Map<String, Object> smsParameters = new HashMap<String, Object>();
				to = "91" + assignedMaker.getMobile();
				smsParameters.put(PARAMETERS_ADMIN_CHECKER, adminCheckerName != null ? adminCheckerName : LITERAL_CHECKER);
				smsParameters.put(PARAMETERS_PRODUCT_NAME,
						productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
				smsParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
				smsParameters.put("url", URL_WWW_PSBLOANS_COM);
				sendSMSNotification(userId.toString(), smsParameters,NotificationAlias.SMS_ADMIN_MAKER_PRODUCT_REVERTED_BY_CHECKER,domainId, to);
			}

			if (!CommonUtils.isObjectNullOrEmpty(assignedMaker.getId())) {
				Map<String, Object> sysParameters = new HashMap<String, Object>();
				sysParameters.put(PARAMETERS_ADMIN_CHECKER, adminCheckerName != null ? adminCheckerName : LITERAL_CHECKER);
				sysParameters.put(PARAMETERS_PRODUCT_NAME,
						productMasterTemp.getName() != null ? productMasterTemp.getName() : "NA");
				sysParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");

				sendSYSNotification(userId, assignedMaker.getId().toString(), sysParameters,
						NotificationAlias.SYS_ADMIN_MAKER_PRODUCT_REVERTED_BY_CHECKER,assignedMaker.getId().toString(),domainId, assignedMaker.getId().toString());
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
	@SuppressWarnings("unchecked")
	@Async
	public void sendEmailToMakerHOBOWhenCheckerSanctionLoan(LoanSanctionDomain loanSanctionDomainOld) {
		try {
			Long domainId=DomainValue.MSME.getId();
			logger.info("Into sending Mail to Maker/HO/BO when Checker sanction loan ");
//			String subject = "Intimation: Sanction - #ApplicationId=" + loanSanctionDomainOld.getApplicationId();
			Map<String, Object> mailParameters = new HashMap<>();
			ProposalMappingResponse proposal = proposalDetailsClient.getProposalByApplicationIdAndUserOrgId(loanSanctionDomainOld.getOrgId(),loanSanctionDomainOld.getApplicationId());
			ProposalMappingRequest prpo= MultipleJSONObjectHelper.getObjectFromMap((Map)proposal.getData(), ProposalMappingRequest.class);
			LoanApplicationRequest applicationRequest = loanApplicationService.getFromClient(prpo.getId());
			
			Map<String, Object> proposalresp = null;
			try {
				logger.info("Calling Proposal details client {}" , loanSanctionDomainOld.getApplicationId());
				GatewayResponse inpData = gatewayClient.getInPrincipleByApplicationId(loanSanctionDomainOld.getApplicationId());
				proposalresp=(Map<String, Object>) inpData.getData();
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
			mailParameters.put("date", form.format(loanSanctionDomainOld.getSanctionDate()) != null
							? form.format(loanSanctionDomainOld.getSanctionDate()): "NA");

			// For getting Fund Seeker's Name================================================================================
			String fsName = null;
			if (applicationRequest.getBusinessTypeId() == 2) {
				fsName = getFsNameForNTB(loanSanctionDomainOld.getApplicationId());
			} else {
				fsName = applicationRequest.getUserName() != null ? applicationRequest.getUserName() : "NA";
			}
			mailParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
			UsersRequest checkerForName = new UsersRequest();
			String checkerName = LITERAL_CHECKER;
			String[] cc= {};
			if(loanSanctionDomainOld.getModifiedBy() != null) {
				checkerForName.setId(Long.valueOf(loanSanctionDomainOld.getModifiedBy()));
				logger.info(MSG_INTO_GETTING_FP_NAME , checkerForName);
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
			mailParameters.put(CommonUtils.PARAMETERS_LOAN_AMOUNT,loanSanctionDomainOld.getSanctionAmount() != null ? loanSanctionDomainOld.getSanctionAmount(): "NA");
			mailParameters.put(PARAMETERS_CHECKER_NAME, checkerName != null ? checkerName : LITERAL_CHECKER);
			mailParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
			mailParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
			
			UsersRequest maker = new UsersRequest();
			if(!CommonUtils.isObjectNullOrEmpty(applicationRequest) && !applicationRequest.getBusinessTypeId().equals(CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId())){
				/** attaching branch user in cc */
				try {
					UserResponse allBranchUsers = userClient.getAllBranchUsers(prpo.getBranchId());
					List<Map<String, Object>> branchUser= (List<Map<String, Object>>) allBranchUsers.getListData();
					List<String> ccList=new ArrayList<>();
					if(branchUser != null && !branchUser.isEmpty()) {
						for (Map<String, Object> map : branchUser) {
							BranchUserResponse resp= MultipleJSONObjectHelper.getObjectFromMap(map, BranchUserResponse.class);
							if(applicationRequest.getFpMakerId() != null && applicationRequest.getFpMakerId().equals(Long.valueOf(resp.getUserId()))) {
								maker.setEmail(resp.getEmail());
								maker.setMobile(resp.getMobile());
							}
							
							if(resp.getUserRole().equals(String.valueOf(UserRoles.HEAD_OFFICER)) || resp.getUserRole().equals(String.valueOf(UserRoles.BRANCH_OFFICER))) {
								ccList.add(resp.getEmail());
								
								if (!CommonUtils.isObjectNullOrEmpty(applicationRequest.getFpMakerId())) {
									/** sending system notification to Ho,BO user*/
									sendSYSNotification(loanSanctionDomainOld.getApplicationId(),
											resp.getUserId().toString(), mailParameters,
											NotificationAlias.SYS_MAKER_AFTER_CHECKER_SUBMIT_SANCTION_POPUP,
											applicationRequest.getFpMakerId().toString(), domainId,resp.getUserId().toString());
								}
								/** sending sms notification to Ho,BO user*/
								if (!CommonUtils.isObjectNullOrEmpty(resp.getMobile())) {
									
									mailParameters.put("url", URL_WWW_PSBLOANS_COM);
									sendSMSNotification(resp.getUserId().toString(), mailParameters,NotificationAlias.SMS_HO_CHECKER_SANCTIONED,domainId, resp.getMobile());
								}
							}
						}
					}
					
					if(!ccList.isEmpty()) {
						cc=new String[ccList.size()];
						cc= Arrays.copyOf(ccList.toArray(),ccList.size(),String[].class);
					}
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
				} catch (Exception e) {
					logger.error("Exception in attaching branch user in cc {}",e);
				}
				mailParameters.put(PARAMETERS_CHECKER_NAME, checkerName);
				if((applicationRequest.getProductId() == LoanType.HOME_LOAN.getValue() 
					|| applicationRequest.getProductId() == LoanType.PERSONAL_LOAN.getValue())) {
					domainId= DomainValue.RETAIL.getId();
				}
				// ===========================Email to Maker==and branch users are in cc======================
				if (!CommonUtils.isObjectNullOrEmpty(maker) && !CommonUtils.isObjectNullOrEmpty(maker.getEmail())) {
					String toIds = maker.getEmail();
					logger.info("Email Sending TO MAKER when Checker sanction loan===to==>{}", toIds);
					mailParameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, true);
					createNotificationForEmail(toIds, applicationRequest.getFpMakerId().toString(), mailParameters,
							NotificationAlias.EMAIL_MAKER_AFTER_CHECKER_SUBMIT_SANCTION_POPUP, 
							EmailSubjectAlias.EMAIL_MAKER_AFTER_CHECKER_SUBMIT_SANCTION_POPUP.getSubjectId(),domainId,cc);
				}
				/** sending system notification to maker user*/
				if (!CommonUtils.isObjectNullOrEmpty(applicationRequest.getFpMakerId())) {
					mailParameters.put(PARAMETERS_CHECKER_NAME, checkerName != null ? checkerName : LITERAL_CHECKER);
					mailParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
					mailParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");
					sendSYSNotification(loanSanctionDomainOld.getApplicationId(),
							applicationRequest.getFpMakerId().toString(), mailParameters,
							NotificationAlias.SYS_MAKER_AFTER_CHECKER_SUBMIT_SANCTION_POPUP,
							applicationRequest.getFpMakerId().toString(), domainId,applicationRequest.getFpMakerId().toString());
				}
			
				
			}
			// ===========================Email to HO======================================
		/*	Long branchId = null;
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
								NotificationAlias.EMAIL_HO_CHECKER_SANCTIONED, subject,domainId);
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
								NotificationAlias.SMS_HO_CHECKER_SANCTIONED,domainId, to);
					}
					if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
						logger.info(MSG_MAKER_ID,userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put(PARAMETERS_CHECKER_NAME, checkerName != null ? checkerName : LITERAL_CHECKER);
						sysParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
						sysParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");

						sendSYSNotification(loanSanctionDomainOld.getApplicationId(), userObj.getId().toString(),
								sysParameters, NotificationAlias.SYS_HO_CHECKER_SANCTIONED, userObj.getId().toString(),domainId,
								userObj.getId().toString());
					}
				}

			} else {
				logger.info(MSG_NO_HO_FOUND);
			}*/
			// ===========================Email to BO======================================
/*
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
						createNotificationForEmail(to, userObj.getId().toString(), mailParameters,NotificationAlias.EMAIL_ALL_BO_CHECKER_SANCTIONED, subject,domainId);
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
								NotificationAlias.SMS_ALL_BO_CHECKER_SANCTIONED,domainId, to);
					}

					if (!CommonUtils.isObjectNullOrEmpty(userObj.getId())) {
						logger.info(MSG_MAKER_ID,userObj.getEmail());
						Map<String, Object> sysParameters = new HashMap<String, Object>();
						sysParameters.put(PARAMETERS_CHECKER_NAME, checkerName != null ? checkerName : LITERAL_CHECKER);
						sysParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : "NA");
						sysParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "NA");

						sendSYSNotification(loanSanctionDomainOld.getApplicationId(), userObj.getId().toString(),
								sysParameters, NotificationAlias.SYS_ALL_BO_CHECKER_SANCTIONED,
								userObj.getId().toString(),domainId, userObj.getId().toString());
					}
				}
			} else {
				logger.info(MSG_NO_BO_FOUND);
			}*/
		} catch (Exception e) {
			logger.error(
					"An exception getting while sending mail to Maker/HO/BO when Checker sanction loan=============>{}",e);
		}
	}
	@SuppressWarnings("unchecked")
	@Async
	public void sendEmailToFSWhenCheckerSanctionLoan(LoanSanctionDomain loanSanctionDomainOld) {
		try {
			Long domainId = DomainValue.MSME.getId();
			logger.info("Into sending Mail to FS when Checker sanction loan for :{}",loanSanctionDomainOld.getApplicationId());
//			String subject = "Congratulations - Your Loan Has Been Sanctioned!!!";
			Object subjectId=EmailSubjectAlias.EMAIL_FS_CHECKER_SANCTIONED.getSubjectId();
			Map<String, Object> mailParameters = new HashMap<>();
			ProposalMappingResponse proposal = proposalDetailsClient.getProposalByApplicationIdAndUserOrgId(loanSanctionDomainOld.getOrgId(),loanSanctionDomainOld.getApplicationId());
			ProposalMappingRequest prpo= MultipleJSONObjectHelper.getObjectFromMap((Map)proposal.getData(), ProposalMappingRequest.class);
			LoanApplicationRequest applicationRequest = loanApplicationService.getFromClient(prpo.getId());

			if(applicationRequest != null && applicationRequest.getProductId() != null 
					&& (applicationRequest.getProductId() == LoanType.HOME_LOAN.getValue() 
					||applicationRequest.getProductId() == LoanType.PERSONAL_LOAN.getValue()
					|| applicationRequest.getProductId() == LoanType.AUTO_LOAN.getValue())) {
				domainId = DomainValue.RETAIL.getId();
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
			if(!CommonUtils.isObjectNullOrEmpty(loanSanctionDomainOld.getIsSanctionedFrom()) 
					&& loanSanctionDomainOld.getIsSanctionedFrom().equals(CommonUtils.sanctionedFrom.INELIGIBLE_USERS_OFFLINE_APPLICATION)){
//				subject = "Congratulations - Your Loan for Manual Application Has Been Sanctioned!!!";
				subjectId=EmailSubjectAlias.EMAIL_FS_CHECKER_SANCTIONED_OFFLINE.getSubjectId();
			}
			mailParameters.put(CommonUtils.PARAMETERS_FP_NAME, fpName != null ? fpName : "");
			mailParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "");
			mailParameters.put(CommonUtils.PARAMETERS_LOAN_AMOUNT,loanSanctionDomainOld.getSanctionAmount() != null ? loanSanctionDomainOld.getSanctionAmount(): "NA");
			mailParameters.put("processing_fees",loanSanctionDomainOld.getProcessingFee() != null ? loanSanctionDomainOld.getProcessingFee() : "");
			mailParameters.put(CommonUtils.LITERAL_AMOUNT,loanSanctionDomainOld.getSanctionAmount() != null ? loanSanctionDomainOld.getSanctionAmount(): "NA");
			mailParameters.put(PARAMETERS_INTEREST_RATE,loanSanctionDomainOld.getRoi() != null ? loanSanctionDomainOld.getRoi() : "");
			mailParameters.put("tenure",loanSanctionDomainOld.getTenure() != null ? loanSanctionDomainOld.getTenure() : "");
			mailParameters.put("date",form.format(loanSanctionDomainOld.getSanctionDate()) != null
							? form.format(loanSanctionDomainOld.getSanctionDate()): "");
			mailParameters.put("remarks",loanSanctionDomainOld.getRemark() != null ? loanSanctionDomainOld.getRemark() : "");

			// For getting Fund Seeker's Name
			// =========================================================================================================
			String fsName = null;
			if (applicationRequest.getBusinessTypeId() == CommonUtils.BusinessType.NEW_TO_BUSINESS.getId()) {
				fsName = getFsNameForNTB(loanSanctionDomainOld.getApplicationId());
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
				// ====================== MAIL TO MAKER by new code ======================
				mailParameters.put(CommonUtils.PARAMETERS_IS_DYNAMIC, false);
				createNotificationForEmail(toIds, applicationRequest.getUserId().toString(), mailParameters,
						NotificationAlias.EMAIL_FS_CHECKER_SANCTIONED, subjectId,domainId,null);

			}
			if (fs != null && !CommonUtils.isObjectNullOrEmpty(fs.getMobile())) {
//				Map<String, Object> smsParameters = new HashMap<String, Object>();
				String to = "91" + fs.getMobile();
				mailParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : PARAMETERS_SIR_MADAM);
				mailParameters.put(CommonUtils.PARAMETERS_FP_NAME, fpName != null ? fpName : "");
				mailParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "");
				mailParameters.put("url", URL_WWW_PSBLOANS_COM);

				sendSMSNotification(applicationRequest.getUserId().toString(), mailParameters,
						NotificationAlias.SMS_FS_CHECKER_SANCTIONED,domainId, to);
			}
			if (!CommonUtils.isObjectNullOrEmpty(applicationRequest.getUserId())) {
//				Map<String, Object> sysParameters = new HashMap<String, Object>();

				mailParameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName != null ? fsName : PARAMETERS_SIR_MADAM);
				mailParameters.put(CommonUtils.PARAMETERS_FP_NAME, fpName != null ? fpName : "");
				mailParameters.put(PARAMETERS_PRODUCT_TYPE, productType != null ? productType : "");

				sendSYSNotification(loanSanctionDomainOld.getApplicationId(), applicationRequest.getUserId().toString(),
						mailParameters, NotificationAlias.SYS_FS_CHECKER_SANCTIONED,
						applicationRequest.getUserId().toString(),domainId, applicationRequest.getUserId().toString());
			}

		} catch (Exception e) {
			logger.error("An exception getting while sending mail to FS when Checker sanction loan=============>{}",e);
		}

	}

	private String getFsNameForNTB(Long applicationId) throws LoansException {
		String fsName = SIR_MADAM;
		List<DirectorBackgroundDetailRequest> NTBResponse;
		NTBResponse = directorBackgroundDetailsService
				.getDirectorBasicDetailsListForNTB(applicationId);
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
		return fsName;
	}
	
	@Async
	public void sendEmailToFsWhenSubProductOfRetailSelectedByUser(LoanApplicationMaster loansMaster) {
		logger.info("Inside sending email of product selected for :{}",loansMaster.getId());
		if (!CommonUtils.isObjectNullOrEmpty(loansMaster) && loansMaster.getProductId() != null && (loansMaster.getProductId() == CommonUtils.LoanType.PERSONAL_LOAN.getValue() || loansMaster.getProductId() == CommonUtils.LoanType.HOME_LOAN.getValue())) {
			try {
				 Long domainId = DomainValue.RETAIL.getId();
				 RetailApplicantDetail retailApp = retailDetailRepo.findByApplicationId(loansMaster.getId());
				 String fsName=retailApp.getFirstName()!=null?retailApp.getFirstName().concat(retailApp.getLastName()!=null?retailApp.getLastName():""):"";
				 String productName = CommonUtils.LoanType.getType(loansMaster.getProductId()).getName();
				 UserResponse userResp = userClient.getEmailMobile(loansMaster.getUserId());
				 UsersRequest userReq=MultipleJSONObjectHelper.getObjectFromMap((Map) userResp.getData(), UsersRequest.class);
				 Map<String, Object>param=new HashMap<>();
				 param.put(USER_NAME, fsName);
				 param.put(PARAMETERS_PRODUCT_NAME, productName);
				 String subject = productName+" selected for application";
				 createNotificationForEmail(userReq.getEmail(), String.valueOf(loansMaster.getUserId()), param, NotificationAlias.PL_EMAIL_FS_PRODUCT_SELECTED_SUB_PRODUCT, subject,domainId,null);
				 sendSMSNotification(String.valueOf(loansMaster.getUserId()), param, NotificationAlias.SMS_FS_SUB_PRODUCT_SELECTED_FOR_RETAIL,domainId, userReq.getMobile());
			} catch (Exception e) {
				logger.error("Exception in sending email when sub product of retail selected:{}",e);
			} 
		}
	}
	//	This email is only sent on HL or PL
	
	@Async
	public void sendEmailToAdminMakerWhenPurposeOfLoanApprovedOrRevertBeck(Long makerUserId,Long retailModelId,Long workFlowAction,Boolean toAdminMaker) {
		Long domainId = DomainValue.RETAIL.getId();
		logger.info("Inside sending email and sms of when purpose of loan Revert beck :{}",makerUserId);
		if (!CommonUtils.isObjectNullOrEmpty(makerUserId)) {
			try {
				UserResponse userResp = userClient.getUserBasicDetails(makerUserId);
				UsersRequest userReq= MultipleJSONObjectHelper.getObjectFromMap((Map) userResp.getData(), UsersRequest.class);
				
				Map<String, Object>param=new HashMap<>();
				RetailModel retailModel = retailModelRepo.findById(retailModelId);
				String modelName=retailModel.getName()!=null?retailModel.getName():"";
				param.put("purpose_of_loan_model_name", modelName);
				param.put(PARAMETERS_CHECKER_NAME, "CHecker");
				if(retailModel.getBusinessTypeId() !=null && retailModel.getBusinessTypeId() == CommonUtils.BusinessType.RETAIL_PERSONAL_LOAN.getId()) {
					 param.put(LOAN_TYPE, "Personal Loan");
				}else if(retailModel.getBusinessTypeId() != null && retailModel.getBusinessTypeId() == CommonUtils.BusinessType.RETAIL_HOME_LOAN.getId()) {
					 param.put(LOAN_TYPE, "Home Loan");
				}else if(retailModel.getBusinessTypeId() != null && retailModel.getBusinessTypeId() == CommonUtils.BusinessType.RETAIL_AUTO_LOAN.getId()) {
					 param.put(LOAN_TYPE, "Auto Loan");
				}else { 
					 param.put(LOAN_TYPE, "Retail Loan");
				} 
				 String subject = "";
				/** Mail to Admin Makers	when purpose of loan approved or reverted	 */				 
				 List<String> ccUserList=new ArrayList<>();
				 String[] cc= {};
				if(toAdminMaker) {
					UsersRequest toUser=new UsersRequest();
					List<Object[]> adminMakerDetails = commonRepo.getBranchUserDetailsBasedOnRoleId(userReq.getUserOrgId(),10);
					List<UsersRequest> adminMakerList = extractObjectListToUserRequest(adminMakerDetails);
					ccUserList=adminMakerList.stream().map(mk->mk.getEmail()).collect(Collectors.toList());
					if(!adminMakerList.isEmpty()) {
						if(adminMakerList.get(0) != null) {
							toUser=adminMakerList.get(0);
							if(ccUserList.contains(toUser.getEmail())) {
								ccUserList.remove(ccUserList.indexOf(toUser.getEmail()));
							}
						}
					}
					if(!ccUserList.isEmpty()) {
						cc=new String[ccUserList.size()];
						cc= Arrays.copyOf(ccUserList.toArray(),ccUserList.size(),String[].class);
					}
//					for (UsersRequest req:adminMakerList) {
						 String fpName = toUser.getFirstName()!=null?String.valueOf(toUser.getFirstName())+" "+toUser.getLastName():SIR_MADAM;
						 param.put(USER_NAME, fpName);
						 if(workFlowAction == WorkflowUtils.Action.APPROVED) {
							 subject = "New purpose of loan model "+modelName+" Approved";
							 createNotificationForEmail(toUser.getEmail(), String.valueOf(makerUserId), param, NotificationAlias.EMAIL_ADMIN_MAKER_WHEN_PURPOSE_OF_LOAN_APPROVED, subject,domainId,cc);
							 /** sending sms to all user*/
							 adminMakerList.stream().forEach(usersRequest ->{
								 try {
									sendSMSNotification(String.valueOf(usersRequest.getUserId()), param, NotificationAlias.SMS_ADMIN_MAKER_WHEN_PURPOSE_OF_LOAN_APPROVED,domainId, usersRequest.getMobile());
								} catch (NotificationException e) {
									logger.error("Exception in sending sms for {} and for template {}",usersRequest.getMobile(),NotificationAlias.SMS_ADMIN_MAKER_WHEN_PURPOSE_OF_LOAN_APPROVED);
								}
							 });
						 }else if(workFlowAction == WorkflowUtils.Action.SEND_BACK){
							 subject = "Intimation: Re-Sent Purpose of loan Model - "+modelName+" – For Modification";
							 createNotificationForEmail(toUser.getEmail(), String.valueOf(makerUserId), param, NotificationAlias.EMAIL_ADMIN_MAKER_WHEN_PURPOSE_OF_LOAN_REVERTED, subject,domainId,cc);
							 /** sending sms to all user*/
							 adminMakerList.stream().forEach(usersRequest ->{
								 try {
									sendSMSNotification(String.valueOf(usersRequest.getUserId()), param, NotificationAlias.SMS_ADMIN_MAKER_WHEN_PURPOSE_OF_LOAN_REVERT_BECK,domainId, usersRequest.getMobile());
								} catch (NotificationException e) {
									logger.error("Exception in sending sms for {} and for template {}",usersRequest.getMobile(),NotificationAlias.SMS_ADMIN_MAKER_WHEN_PURPOSE_OF_LOAN_REVERT_BECK);
								} 
							 });
						 }
//					}
				}else {
					Object[] fpFullName = commonRepo.getFpFullName(makerUserId);
					String makerName=fpFullName[0]!=null?String.valueOf(fpFullName[0])+" "+fpFullName[1]!=null?String.valueOf(fpFullName[1]):LITERAL_MAKER:LITERAL_MAKER;
					param.put(PARAMETERS_MAKER_NAME, makerName);
					
					List<Object[]> adminMakerDetails = commonRepo.getBranchUserDetailsBasedOnRoleId(userReq.getUserOrgId(),11);
					List<UsersRequest> adminMakerList = extractObjectListToUserRequest(adminMakerDetails);
					UsersRequest toUser=new UsersRequest();
					ccUserList=adminMakerList.stream().map(mk->mk.getEmail()).collect(Collectors.toList());
					if(!adminMakerList.isEmpty()) {
						if(adminMakerList.get(0) != null) {
							toUser=adminMakerList.get(0);
							if(ccUserList.contains(toUser.getEmail())) {
								ccUserList.remove(ccUserList.indexOf(toUser.getEmail()));
							}
						}
					}
					if(!ccUserList.isEmpty()) {
						cc=new String[ccUserList.size()];
						cc= Arrays.copyOf(ccUserList.toArray(),ccUserList.size(),String[].class);
					} 
					/** Mail to Admin Checker where pusrpose of loan created or purpose of loan approved after send back*/
//					for (UsersRequest req : adminMakerList) {
						 String fpName = toUser.getFirstName()!=null?String.valueOf(toUser.getFirstName())+" "+toUser.getLastName():SIR_MADAM;
						 param.put(USER_NAME, fpName);
						
						if(workFlowAction == WorkflowUtils.Action.PENDING) {
							 subject = "Intimation: New Purpose of Loan module - "+modelName;
							 createNotificationForEmail(toUser.getEmail(), String.valueOf(makerUserId), param, NotificationAlias.EMAIL_ADMIN_CHECKER_PURPOSE_OF_LOAN_CREATED, subject,domainId,cc);
							/** sending sms to all user*/
							 adminMakerList.stream().forEach(usersRequest -> {
								 try {
									sendSMSNotification(String.valueOf(usersRequest.getUserId()), param, NotificationAlias.SMS_ADMIN_CHECKER_PURPOSE_OF_LOAN_CREATED,domainId, usersRequest.getMobile());
								} catch (NotificationException e) {
									logger.error("Exception in sending sms for {} and for template {}",usersRequest.getMobile(),NotificationAlias.SMS_ADMIN_CHECKER_PURPOSE_OF_LOAN_CREATED);
								}
							 });
						 }else if(workFlowAction == WorkflowUtils.Action.SEND_FOR_APPROVAL){
							 subject = "Intimation: Re-sent Purpose of loan module  - "+modelName;
							 createNotificationForEmail(toUser.getEmail(), String.valueOf(makerUserId), param, NotificationAlias.EMAIL_ADMINCHECKER_PURPOSE_OF_LOAN_RE_APPROVAL, subject,domainId,cc);
							/** sending sms to all user*/
							 adminMakerList.stream().forEach(usersRequest -> {
								 try {
									sendSMSNotification(String.valueOf(usersRequest.getUserId()), param, NotificationAlias.SMS_ADMIN_CHECKER_PURPOSE_OF_LOAN_RE_APPROVAL_BY_MAKER,domainId, usersRequest.getMobile());
								} catch (NotificationException e) {
									logger.error("Exception in sending sms for {} and for template {}",usersRequest.getMobile(),NotificationAlias.SMS_ADMIN_CHECKER_PURPOSE_OF_LOAN_RE_APPROVAL_BY_MAKER);
								}
							 });
						 }
					}
//				}
			} catch (Exception e) {
				logger.error("Exception in sending email and sms of when purpose of loan approved:{}",e);
			} 
		}
	}

	private List<UsersRequest> extractObjectListToUserRequest(List<Object[]> userList){
		List<UsersRequest> userReq=new ArrayList<>();
		for (Object[] obj: userList) {
			UsersRequest req=new UsersRequest();
			req.setEmail(String.valueOf(obj[0]));
			req.setMobile(String.valueOf(obj[1]));
			if(null != obj[2] && null != obj[3]) {
				req.setFirstName(String.valueOf(obj[2]));
				req.setLastName(String.valueOf(obj[3]));
			}else {
				req.setFirstName(String.valueOf(obj[4]));
				req.setLastName("");
			}
			req.setUserId(Long.parseLong(String.valueOf(obj[5])));
			userReq.add(req);
		}
		return userReq;
	}

	private void createNotificationForEmail(String toNo, String userId, Map<String, Object> mailParameters,
											Long templateId, Object emailSubject,Long domainId,String[] cc) throws NotificationException {
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
		if(cc != null) {
			notification.setCc(cc);
		}
		notification.setParameters(mailParameters);
		notification.setIsDynamic(notificationRequest.getIsDynamic());
		notificationRequest.setDomainId(domainId);
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
						Long templateId, String emailSubject,Long applicationId,Map<String, Object> proposalresp,String bcc[],Long domainId) throws NotificationException {
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
		notificationRequest.setDomainId(domainId);
		sendEmail(notificationRequest);
		logger.info("Outside send notification===>{}" , toNo);
	}


	private void sendSMSNotification(String userId, Map<String, Object> parameters, Long templateId,Long domainId ,String... to)
			throws NotificationException {
		logger.info("Inside send SMS===>{}",to[0]);
		NotificationRequest req = new NotificationRequest();
		req.setClientRefId(userId);
		Notification notification = new Notification();
		notification.setContentType(ContentType.TEMPLATE);
		notification.setTemplateId(templateId);
		notification.setTo(to);
		notification.setType(NotificationType.SMS);
		notification.setParameters(parameters);
		req.addNotification(notification);
		req.setDomainId(domainId);
		sendEmail(req);
		logger.info("Outside send SMS===>{}",to[0]);

	}

	private void sendSYSNotification(Long applicationId, String toUserId, Map<String, Object> parameters,
									 Long templateId, String fromId,Long domainId, String... to) throws NotificationException {
		logger.info("Inside send SYSTEM notification===>{}",applicationId);
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
		req.setDomainId(domainId);
		sendEmail(req);
		logger.info("Outside send SYSTEM notification===>{}",applicationId);

	}

	private void sendEmail(NotificationRequest notificationRequest) throws NotificationException {
		NotificationResponse send = notificationClient.send(notificationRequest);
		logger.info("Notification Sent status :{}",send.getResponse_code_message());
	}
 
}
