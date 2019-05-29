package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.cibil.client.CIBILClient;
import com.capitaworld.client.eligibility.EligibilityClient;
import com.capitaworld.connect.api.ConnectRequest;
import com.capitaworld.connect.api.ConnectResponse;
import com.capitaworld.connect.client.ConnectClient;
import com.capitaworld.itr.api.model.ITRConnectionResponse;
import com.capitaworld.itr.client.ITRClient;
import com.capitaworld.service.analyzer.client.AnalyzerClient;
import com.capitaworld.service.analyzer.model.common.AnalyzerResponse;
import com.capitaworld.service.analyzer.model.common.Data;
import com.capitaworld.service.analyzer.model.common.ReportRequest;
import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.model.StorageDetailsResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.gst.client.GstClient;
import com.capitaworld.service.loans.config.FPAsyncComponent;
import com.capitaworld.service.loans.config.MCAAsyncComponent;
import com.capitaworld.service.loans.domain.common.MinMaxProductDetail;
import com.capitaworld.service.loans.domain.common.PaymentGatewayAuditMaster;
import com.capitaworld.service.loans.domain.fundprovider.ProductMaster;
import com.capitaworld.service.loans.domain.fundprovider.ProposalDetails;
import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.ApplicationStatusMaster;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.AchievementDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.AssociatedConcernDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateCoApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CreditRatingOrganizationDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.DirectorBackgroundDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.ExistingProductDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinanceMeansDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.GuarantorsCorporateDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.MonthlyTurnoverDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryTermLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryUnsecuredLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryWorkingCapitalLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PromotorBackgroundDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.ProposedProductDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.SecurityCorporateDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.TotalCostOfProject;
import com.capitaworld.service.loans.domain.fundseeker.retail.CoApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.GuarantorDetails;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryCarLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryHomeLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryLapLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryLasLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryPersonalLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.domain.sanction.LoanSanctionDomain;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.AchievementDetailRequest;
import com.capitaworld.service.loans.model.AdminPanelLoanDetailsResponse;
import com.capitaworld.service.loans.model.AssociatedConcernDetailRequest;
import com.capitaworld.service.loans.model.CreditRatingOrganizationDetailRequest;
import com.capitaworld.service.loans.model.DashboardProfileResponse;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.ExistingProductDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoanApplicationDetailsForSp;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.LoanDisbursementRequest;
import com.capitaworld.service.loans.model.LoanEligibilityRequest;
import com.capitaworld.service.loans.model.MonthlyTurnoverDetailRequest;
import com.capitaworld.service.loans.model.PaymentRequest;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.ProposedProductDetailRequest;
import com.capitaworld.service.loans.model.ReportResponse;
import com.capitaworld.service.loans.model.SecurityCorporateDetailRequest;
import com.capitaworld.service.loans.model.api_model.CorporateProfileRequest;
import com.capitaworld.service.loans.model.api_model.FinanceMeansDetailRequest;
import com.capitaworld.service.loans.model.api_model.GuarantorsCorporateDetailRequest;
import com.capitaworld.service.loans.model.api_model.ProfileReqRes;
import com.capitaworld.service.loans.model.api_model.TotalCostOfProjectRequest;
import com.capitaworld.service.loans.model.common.BasicDetailFS;
import com.capitaworld.service.loans.model.common.CGTMSECalcDataResponse;
import com.capitaworld.service.loans.model.common.ChatDetails;
import com.capitaworld.service.loans.model.common.DisbursementRequest;
import com.capitaworld.service.loans.model.common.EkycRequest;
import com.capitaworld.service.loans.model.common.EkycResponse;
import com.capitaworld.service.loans.model.common.HunterRequestDataResponse;
import com.capitaworld.service.loans.model.common.MinMaxProductDetailRequest;
import com.capitaworld.service.loans.model.common.ProposalList;
import com.capitaworld.service.loans.model.common.SanctioningDetailResponse;
import com.capitaworld.service.loans.model.corporate.CorporateFinalInfoRequest;
import com.capitaworld.service.loans.model.corporate.CorporateProduct;
import com.capitaworld.service.loans.model.mobile.MLoanDetailsResponse;
import com.capitaworld.service.loans.model.mobile.MobileLoanRequest;
import com.capitaworld.service.loans.repository.common.LoanRepository;
import com.capitaworld.service.loans.repository.common.LogDetailsRepository;
import com.capitaworld.service.loans.repository.common.MinMaxProductDetailRepository;
import com.capitaworld.service.loans.repository.common.PaymentGatewayAuditMasterRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AchievementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssociatedConcernDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateCoApplicantRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CreditRatingOrganizationDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.DirectorBackgroundDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ExistingProductDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinanceMeansDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinancialArrangementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.GuarantorsCorporateDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.MonthlyTurnoverDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OwnershipDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryTermLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryUnsecuredLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryWorkingCapitalLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PromotorBackgroundDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ProposedProductDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SecurityCorporateDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.TotalCostOfProjectRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.CoApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.GuarantorDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryHomeLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryLapLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.repository.sanction.LoanSanctionRepository;
import com.capitaworld.service.loans.service.ProposalService;
import com.capitaworld.service.loans.service.common.ApplicationSequenceService;
import com.capitaworld.service.loans.service.common.DashboardService;
import com.capitaworld.service.loans.service.common.LogService;
import com.capitaworld.service.loans.service.common.PincodeDateService;
import com.capitaworld.service.loans.service.fundprovider.OrganizationReportsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ApplicationProposalMappingService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CMAService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateCoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateFinalInfoService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateUploadService;
import com.capitaworld.service.loans.service.fundseeker.corporate.DDRFormService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.irr.IrrService;
import com.capitaworld.service.loans.service.networkpartner.NetworkPartnerService;
import com.capitaworld.service.loans.service.sanction.LoanDisbursementService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.BusinessType;
import com.capitaworld.service.loans.utils.CommonUtils.LoanType;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.matchengine.exception.MatchException;
import com.capitaworld.service.matchengine.model.ProposalCountResponse;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.matchengine.model.ProposalMappingResponse;
import com.capitaworld.service.matchengine.utils.MatchConstant;
import com.capitaworld.service.notification.client.NotificationClient;
import com.capitaworld.service.notification.exceptions.NotificationException;
import com.capitaworld.service.notification.model.Notification;
import com.capitaworld.service.notification.model.NotificationRequest;
import com.capitaworld.service.notification.utils.ContentType;
import com.capitaworld.service.notification.utils.NotificationAlias;
import com.capitaworld.service.notification.utils.NotificationType;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.AssessmentOptionForFS;
import com.capitaworld.service.oneform.enums.CampaignCode;
import com.capitaworld.service.oneform.enums.Constitution;
import com.capitaworld.service.oneform.enums.Currency;
import com.capitaworld.service.oneform.enums.Denomination;
import com.capitaworld.service.oneform.enums.EducationQualificationNTB;
import com.capitaworld.service.oneform.enums.FinanceCategory;
import com.capitaworld.service.oneform.enums.Gender;
import com.capitaworld.service.oneform.enums.Industry;
import com.capitaworld.service.oneform.enums.LogDateTypeMaster;
import com.capitaworld.service.oneform.enums.MaritalStatus;
import com.capitaworld.service.oneform.enums.OccupationNature;
import com.capitaworld.service.oneform.enums.Particular;
import com.capitaworld.service.oneform.model.IrrBySectorAndSubSector;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.rating.RatingClient;
import com.capitaworld.service.rating.exception.RatingException;
import com.capitaworld.service.rating.model.IndustryResponse;
import com.capitaworld.service.rating.model.IrrRequest;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.scoring.model.scoringmodel.ScoringModelReqRes;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.FpProfileBasicDetailRequest;
import com.capitaworld.service.users.model.FundProviderDetailsRequest;
import com.capitaworld.service.users.model.NetworkPartnerDetailsRequest;
import com.capitaworld.service.users.model.RegisteredUserResponse;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;
import com.capitaworld.service.users.model.mobile.MobileUserRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class LoanApplicationServiceImpl implements LoanApplicationService {

	@PersistenceContext
	private EntityManager entityManager;
	
	private static final Logger logger = LoggerFactory.getLogger(LoanApplicationServiceImpl.class.getName());

	private static final String CONNECTOR_RESPONSE_MSG = "Connector Response -->";
	private static final String BEFORE_START_SAVING_PHASE_1_SIDBI_API_MSG = "Before Start Saving Phase 1 Sidbi API -->";
	private static final String FP_PRODUCT_ID_MSG = "FpProductId ==>{}";
	private static final String PROPOSAL_MAPPING_RESPONSE_MSG = "Proposal Mapping Response --> ";
	private static final String PROPOSAL_MAPPING_RESPONSE_NULL_OR_EMPTY_MSG = "Proposal Mapping Response Null or Empty --> ";
	private static final String EXCEPTION_IN_EDR_IN_SAVE_PHESE1_DATA_TO_SIDBI_MSG = "Exception in  EligibilityDetailRequest in savePhese1DataToSidbi() for ApplicationId ==>{} ";
	private static final String FETCHED_DIRECTORS_BACKGROUND_DETAILS_FOR_APPLICATION_ID_MSG = "Fetched Director's background details for application Id : ";
	private static final String INVALID_TOKEN_DETAILS_MSG = "Invalid Token Details";
	private static final String EXCEPTION_OCCURED_WHILE_SENDING_MAIL_TO_HO_MSG = "Exception occured while Sending Mail to HO : ";
	private static final String EXCEPTION_OCCURED_WHILE_SENDING_MAIL_TO_ALL_BO_MSG = "Exception occured while Sending Mail to All BO : ";
	private static final String INSIDE_SENDING_MAIL_TO_HO_AFTER_IN_PRINCIPLE_APPROVAL_MSG = "Inside sending mail to HO after In-principle Approval";
	private static final String INSIDE_SENDING_MAIL_TO_MAKER_AFTER_IN_PRINCIPLE_APPROVAL_MSG = "Inside sending mail to Maker after In-principle Approval";
	private static final String INSIDE_SENDING_MAIL_TO_BO_AFTER_IN_PRINCIPLE_APPROVAL_MSG = "Inside sending mail to BO after In-principle Approval";
	private static final String INSIDE_SENDING_MAIL_TO_CHECKER_AFTER_IN_PRINCIPLE_APPROVAL_MSG = "Inside sending mail to Checker after In-principle Approval";
	private static final String EXCEPTION_OCCURED_WHILE_SENDING_MAIL_TO_ALL_CHECKERS_MSG  = "Exception occured while Sending Mail to All Checkers : ";
	private static final String EXCEPTION_OCCURED_WHILE_SENDING_MAIL_TO_ALL_MAKERS_MSG = "Exception occured while Sending Mail to All Makers : ";
	private static final String SOMETHING_WENT_WRONG_WHILE_CALL_PROPOSAL_CLIENT_FOR_MSG = "Something went wrong while call proposal client for ";
	private static final String SOMETHING_WENT_WRONG_WHILE_CALL_CONNECT_CLIENT_FOR_MSG = "Something went wrong while call connect client for ";
	private static final String CONNECTOR_RESPONSE_NULL_OR_EMPTY_MSG = "Connector Response null or empty";
	private static final String ERROR_MSG = "----- Error Msg : ";
	private static final String OTHER_LITERAL = "OTHER";
	private static final String DIRECT_LITERAL = "Direct";
	private static final String SIDBI_FEES = "SIDBI_FEES";
	private static final String ORG_ID = "org_id";
	private static final String MSG_LITERAL = " Msg : ";
	private static final String PROPOSAL_ID="proposalId";

	@Autowired
	private DMSClient dmsClient;

	@Autowired
	private Environment environment;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private ApplicationProposalMappingRepository applicationProposalMappingRepository;

	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;

	@Autowired
	private CorporateCoApplicantService corporateCoApplicantService;

	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;

	@Autowired
	private CoApplicantDetailRepository coApplicantDetailRepository;

	@Autowired
	private CorporateCoApplicantRepository corporateCoApplicantRepository;

	@Autowired
	private GuarantorDetailsRepository guarantorDetailsRepository;

	@Autowired
	private ApplicationSequenceService applicationSequenceService;

	@Autowired
	private MinMaxProductDetailRepository minMaxProductDetailRepository;

	@Autowired
	private UsersClient userClient;

	@Autowired
	private OneFormClient oneFormClient;

	@Autowired
	private ProposalDetailsClient proposalDetailsClient;

	@Autowired
	private DashboardService dashboardService;

	@Autowired
	private CorporateUploadService corporateUploadService;

	@Autowired
	private LogService logService;

	@Autowired
	private ScoringClient scoringClient;

	@Autowired
	private ProposalService proposalService;

	@Autowired
	private MatchEngineClient matchEngineClient;

	@Autowired
	private PrimaryLapLoanDetailRepository primaryLapLoanDetailRepository;

	@Autowired
	private PrimaryHomeLoanDetailRepository primaryHomeLoanDetailRepository;

	@Autowired
	private ProductMasterRepository productMasterRepository;

	@Autowired
	private OrganizationReportsService organizationReportsService;

	@Autowired
	private LogDetailsRepository logDetailsRepository;

	@Autowired
	private NotificationClient notificationClient;

	@Autowired
	private RatingClient ratingClient;

	@Autowired
	private EligibilityClient eligibilityClient;

	@Autowired
	private DirectorBackgroundDetailsRepository directorBackgroundDetailsRepository;

	@Autowired
	private FinancialArrangementDetailsRepository financialArrangementDetailsRepository;

	@Autowired
	private AchievementDetailsRepository achievementDetailsRepository;

	@Autowired
	private ExistingProductDetailsRepository existingProductDetailsRepository;

	@Autowired
	private ProposedProductDetailsRepository proposedProductDetailsRepository;

	@Autowired
	private OwnershipDetailsRepository ownershipDetailsRepository;

	@Autowired
	private CreditRatingOrganizationDetailsRepository creditRatingOrganizationDetailsRepository;

	@Autowired
	private GuarantorsCorporateDetailRepository guarantorsCorporateDetailRepository;

	@Autowired
	private MonthlyTurnoverDetailRepository monthlyTurnoverDetailRepository;

	@Autowired
	private AssociatedConcernDetailRepository associatedConcernDetailRepository;

	@Autowired
	private CIBILClient cibilClient;

	@Autowired
	private FPAsyncComponent fpasyncComponent;

	@Autowired
	private CMAService cmaService;

	@Autowired
	private PincodeDateService pincodeDateService;
	
	private FPAsyncComponent fpAsyncComp;

	@Autowired
	private ProposalDetailsRepository proposalDetailsRepository;

	@Value("${capitaworld.service.gateway.product}")
	private String product;

	@Value("${capitaworld.service.gateway.nhbsAmount}")
	private String nhbsAmount;

	@Value("${capitaworld.service.gateway.sidbiAmount}")
	private String sidbiAmount;

	@Value("${capitaworld.sidbi.integration.is_production}")
	private String isProduction;

	@Autowired
	private ConnectClient connectClient;

	@Autowired
	private AnalyzerClient analyzerClient;

	@Autowired
	private NetworkPartnerService networkPartnerService;

	@Autowired
	private PrimaryWorkingCapitalLoanDetailRepository primaryWorkingCapitalLoanDetailRepository;

	@Autowired
	private PrimaryTermLoanDetailRepository primaryTermLoanDetailRepository;

	@Autowired
	private PrimaryUnsecuredLoanDetailRepository primaryUnsecuredLoanDetailRepository;

	@Autowired
	private PrimaryCorporateDetailRepository primaryCorporateRepository;

	@Autowired
	private DDRFormService dDRFormService;

	@Autowired
	private IrrService irrService;

	@Autowired
	private AssetsDetailsRepository assetsDetailsRepository;

	@Autowired
	private MCAAsyncComponent mcaAsyncComponent;

	@Autowired
	private FinanceMeansDetailRepository financeMeansDetailRepository;

	@Autowired
	private TotalCostOfProjectRepository totalCostOfProjectRepository;

	@Autowired
	private SecurityCorporateDetailsRepository securityCorporateDetailsRepository;

	@Autowired
	private PromotorBackgroundDetailsRepository promotorBackgroundDetailsRepository;

	@Autowired
	private ITRClient itrClient;

	@Autowired
	private LoanSanctionRepository loanSanctionRepository;

	@Autowired
	private LoanDisbursementService loanDisbursementService;

	@Autowired
	private LiabilitiesDetailsRepository liabilitiesDetailsRepository;

	@Autowired
	private OperatingStatementDetailsRepository operatingStatementDetailsRepository;

	@Autowired
	private GstClient gstClient;

	@Autowired
	private LoanApplicationService loanApplicationService;

	@Autowired
	private LoanRepository loanRepository;
	
	@Autowired
	private PaymentGatewayAuditMasterRepository paymentGatewayAuditMasterRepository;

	@Autowired
	private ApplicationProposalMappingService appPropMappService;

	@Autowired
	private CorporateFinalInfoService corporateFinalInfoService;
	public static final String EMAIL_ADDRESS_FROM = "no-reply@capitaworld.com";

	private static final String INVALID_LOAN_APPLICATION_ID =  "Invalid Loan Application ID==>";
	private static final String ERROR_WHILE_GETTING_INDIVIDUAL_LOAN_DETAILS = "Error while getting Individual Loan Details:-";
	private static final String EMAIL_MOBILE_OR_DATA_IN_EMAIL_MOBILE_MUST_NOT_BE_NULL = "emailMobile or Data in emailMobile must not be null===>{}";
	private static final String MESSAGE_LITERAL = "message";
	private static final String RESULT_LITERAL  = "result";
	private static final String TENURE_LITERAL = "tenure";
	private static final String PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT = "Please Fill PROFILE details to Move Next !";
	private static final String PLEASE_FILL_CO_APPLICANT_1_DETAILS_TO_MOVE_NEXT = "Please Fill CO-APPLICANT-1 details to Move Next !";
	private static final String PLEASE_FILL_CO_APPLICANT_2_DETAILS_TO_MOVE_NEXT = "Please Fill CO-APPLICANT-2 details to Move Next !";
	private static final String PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT = "Please Fill PRIMARY INFORMATION details to Move Next !";
	private static final String PLEASE_LOCK_PRIMARY_DETAILS_TO_MOVE_NEXT = "Please LOCK PRIMARY DETAILS to Move next !";
	private static final String PLEASE_FILL_FINAL_MCQ_DETAILS_TO_MOVE_NEXT = "Please Fill FINAL MCQ details to Move Next !";
	private static final String PLEASE_FILL_FINAL_INFORMATION_DETAILS_TO_MOVE_NEXT = "Please Fill FINAL INFORMATION details to Move Next !";
	private static final String PLEASE_GUARANTOR_1_DETAILS_TO_MOVE_NEXT = "Please GUARANTOR-1 details to Move Next !";
	private static final String PLEASE_FILL_GUARANTOR_1_DETAILS_TO_MOVE_NEXT = "Please Fill GUARANTOR-1 details to Move Next !";
	private static final String PLEASE_GUARANTOR_2_DETAILS_TO_MOVE_NEXT = "Please GUARANTOR-2 details to Move Next !";
	private static final String PLEASE_FILL_GUARANTOR_2_DETAILS_TO_MOVE_NEXT = "Please Fill GUARANTOR-2 details to Move Next !";
	private static final String PLEASE_FILL_CO_APPLICANT_1_FINAL_DETAILS_TO_MOVE_NEXT = "Please Fill CO-APPLICANT-1 FINAL details to Move Next !";
	private static final String PLEASE_FILL_CO_APPLICANT_2_FINAL_DETAILS_TO_MOVE_NEXT = "Please Fill CO-APPLICANT-2 FINAL details to Move Next !";
	private static final String GET_LOAN_DETAILS_FOR_ADMIN_PANEL_FROM_AND_TO_DATE_FOR_ADMIN_PANEL = "GetLoanDetailsForAdminPanel, from and todate for admin panel --------> ";
	private static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS  = "yyyy/MM/dd hh:mm:ss";
	private static final String DATE_FORMAT_DD_MM_YYYY = "dd-MM-yyyy";

    @Value("${cw.gst.unit.test}")
    private String IS_UNIT_TEST;

    @Value("${cw.ismca.on}")
    private String IS_MCA_ON;

	@Override
	public boolean saveOrUpdate(FrameRequest commonRequest, Long userId) throws LoansException {
		try {
			LoanApplicationMaster applicationMaster = null;
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(commonRequest.getClientId()) ? userId
					: commonRequest.getClientId());
			boolean codeExist = false;
			try {
				logger.info("Campaign Code=====>{}", commonRequest.getCampaignCodes());
				if (!CommonUtils.isListNullOrEmpty(commonRequest.getCampaignCodes())) {
					codeExist = commonRequest.getCampaignCodes()
							.contains(CommonUtils.CampaignCodes.ALL1MSME.getValue());
					logger.info("codeExist====>{}", codeExist);
				}
			} catch (Exception e) {
				logger.error("Error while Set Campaign Code to LoanApplication Master : ",e);
			}

			for (Map<String, Object> obj : commonRequest.getDataList()) {
				LoanApplicationRequest loanApplicationRequest = (LoanApplicationRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, LoanApplicationRequest.class);
				LoanType type = CommonUtils.LoanType.getType(loanApplicationRequest.getProductId());
				if (type == null) {
					continue;
				}
				applicationMaster = getLoanByType(type);
				logger.info("userId==>" + finalUserId);
				BeanUtils.copyProperties(loanApplicationRequest, applicationMaster, "name");
				applicationMaster.setUserId(finalUserId);
				applicationMaster.setCreatedBy(userId);
				applicationMaster.setCreatedDate(new Date());
				applicationMaster.setModifiedBy(userId);
				applicationMaster.setModifiedDate(new Date());
				applicationMaster.setIsActive(true);
				if (CommonUtils.UserMainType.CORPORATE == CommonUtils
						.getUserMainType(loanApplicationRequest.getProductId())) {
					ApplicationStatusMaster applicationStatusMaster = new ApplicationStatusMaster();
					applicationStatusMaster.setId(CommonUtils.ApplicationStatus.OPEN);
					applicationMaster.setApplicationStatusMaster(applicationStatusMaster);
					applicationMaster.setDdrStatusId(CommonUtils.DdrStatus.OPEN);
					if (codeExist) {
						applicationMaster.setCampaignCode(CommonUtils.CampaignCodes.ALL1MSME.getValue());
					}
				}
				applicationMaster
						.setApplicationCode(applicationSequenceService.getApplicationSequenceNumber(type.getValue())+"-"+applicationMaster.getId());
				loanApplicationRepository.save(applicationMaster);
			}

			try {
				// Inactivating Campaign Codes
				if (codeExist) {
					inactiveCampaignDetails(finalUserId, CommonUtils.CampaignCodes.ALL1MSME.getValue());
				}
			} catch (Exception e) {
				logger.error("Error while inactivating campaign details : ",e);
			}
			return true;
		} catch (Exception e) {
			logger.error("Error while Saving Loan Details:-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

//	private List<String> getCampaignCodes(Long userId) {
//		try {
//			UserResponse response = userClient.getCampaignCodesByUserId(userId);
//			if (CommonUtils.isObjectNullOrEmpty(response) || CommonUtils.isObjectNullOrEmpty(response.getData())) {
//				logger.info("No Codes Found for UserId===>{}", userId);
//			} else {
//				return (List<String>) response.getData();
//			}
//		} catch (Exception e) {
//			logger.error("Error while Getting Campaign Codes using Users Client : ",e);
//		}
//		return Collections.emptyList();
//	}

	private void inactiveCampaignDetails(Long userId, String code) {
		try {
			UserResponse response = userClient.inactiveCampaign(userId, code);
			if (CommonUtils.isObjectNullOrEmpty(response)) {
				logger.info("Response Found full to inactive User Details===>{}", userId);
			} else {
				if (HttpStatus.OK.value() == response.getStatus()) {
					logger.info("Successfully inactivated Campaign Details for userId===>{}====Code====>{}", userId,
							code);
				} else {
					logger.info(
							"Something Went Wrong while inactivatig  Campaign Details for userId===>{}====Code====>{}===Status==>{}",
							userId, code, response.getStatus());
				}
			}
		} catch (Exception e) {
			logger.error("Error while Getting Campaign Codes using Users Client : ",e);
		}
	}

	@Override
	public LoanApplicationRequest saveFromCampaign(Long userId, Long clientId, String campaignCode) throws LoansException {
		try {
			String loanCode = com.capitaworld.service.users.utils.CommonUtils.getLoanCodeFromCode(campaignCode);
			LoanType type = CommonUtils.getProductByLoanCode(loanCode);
			LoanApplicationMaster applicationMaster = null;
			// LoanType type = CommonUtils.LoanType.getType(productId);
			if (type == null) {
				logger.warn("Loan Type is NULL while Creating new Loan From Campaign================>");
				return null;
			}
			LoanApplicationRequest request = new LoanApplicationRequest();
			Long finalUserId = CommonUtils.isObjectNullOrEmpty(clientId) ? userId : clientId;
			applicationMaster = getLoanByType(type);
			applicationMaster.setUserId(finalUserId);
			applicationMaster.setProductId(type.getValue());
			applicationMaster.setCreatedBy(userId);
			applicationMaster.setCreatedDate(new Date());
			if (CommonUtils.UserMainType.CORPORATE == CommonUtils.getUserMainType(type.getValue())) {
				applicationMaster
						.setApplicationStatusMaster(new ApplicationStatusMaster(CommonUtils.ApplicationStatus.OPEN));
			}
			applicationMaster.setDdrStatusId(CommonUtils.DdrStatus.OPEN);
			applicationMaster.setCategoryCode(loanCode.toLowerCase());
			applicationMaster.setCampaignCode(campaignCode);
			applicationMaster
					.setApplicationCode(applicationSequenceService.getApplicationSequenceNumber(type.getValue())+"-"+applicationMaster.getId());
			applicationMaster.setIsActive(true);
			applicationMaster = loanApplicationRepository.save(applicationMaster);
			BeanUtils.copyProperties(applicationMaster, request);
			UsersRequest usersRequest = new UsersRequest();
			usersRequest.setLastAccessApplicantId(applicationMaster.getId());
			usersRequest.setId(finalUserId);
			userClient.setLastAccessApplicant(usersRequest);
			return request;
		} catch (Exception e) {
			logger.error("Error while Saving Loan Details From Campaign:-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public boolean saveOrUpdateFromLoanEligibilty(FrameRequest commonRequest, Long userId) throws LoansException {
		logger.info("Entry in saveOrUpdateFromLoanEligibilty");
		try {
			LoanApplicationMaster applicationMaster = null;
			for (Map<String, Object> obj : commonRequest.getDataList()) {
				LoanEligibilityRequest loanEligibilityRequest = (LoanEligibilityRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, LoanEligibilityRequest.class);
				LoanType type = CommonUtils.LoanType.getType(loanEligibilityRequest.getProductId());
				if (type == null) {
					continue;
				}
				applicationMaster = getLoanByType(type);
				if (applicationMaster == null) {
					continue;
				}
				logger.info("userId==>" + userId);
				if (!CommonUtils.isObjectNullOrEmpty(loanEligibilityRequest.getTenure())) {
					applicationMaster.setTenure(loanEligibilityRequest.getTenure() * 12);
				}
				applicationMaster.setCategoryCode(loanEligibilityRequest.getCategoryCode()); // categaoryCode set
				applicationMaster.setProductId(loanEligibilityRequest.getProductId());
				applicationMaster.setUserId(userId);
				applicationMaster.setCreatedBy(userId);
				applicationMaster.setCreatedDate(new Date());
				applicationMaster.setModifiedBy(userId);
				applicationMaster.setModifiedDate(new Date());
				applicationMaster.setIsActive(true);
				applicationMaster
						.setApplicationCode(applicationSequenceService.getApplicationSequenceNumber(type.getValue())+"-"+applicationMaster.getId());
				applicationMaster = loanApplicationRepository.save(applicationMaster);

				// for save primary details

				switch (type) {
				case WORKING_CAPITAL:

					break;
				case TERM_LOAN:

					break;
				/*
				 * case LAS_LOAN: applicationMaster = new PrimaryLasLoanDetail(); break;
				 */
				case LAP_LOAN:
					PrimaryLapLoanDetail lapLoanDetail = primaryLapLoanDetailRepository
							.findOne(applicationMaster.getId());
					lapLoanDetail.setPropertyValue(loanEligibilityRequest.getMarketValue());
					lapLoanDetail.setPropertyType(loanEligibilityRequest.getPropertyType());
					primaryLapLoanDetailRepository.save(lapLoanDetail);

					// create record in fs retail applicant
					saveRetailApplicantDetailFromLoanEligibility(applicationMaster, loanEligibilityRequest);
					break;
				case PERSONAL_LOAN:
					// create record in fs retail applicant
					RetailApplicantDetail retailApplicantDetail = new RetailApplicantDetail();
					retailApplicantDetail.setEmployedWithId(loanEligibilityRequest.getEmploymentType());
					saveRetailApplicantDetailFromLoanEligibility(applicationMaster, loanEligibilityRequest);
					break;
				case HOME_LOAN:
					PrimaryHomeLoanDetail primaryHomeLoanDetail = primaryHomeLoanDetailRepository
							.findOne(applicationMaster.getId());
					if (primaryHomeLoanDetail.getPropertyUsedType() == 3) {
						primaryHomeLoanDetail.setPropertyPrice(loanEligibilityRequest.getMarketValue());
					}
					if (primaryHomeLoanDetail.getPropertyType() == 6) {
						primaryHomeLoanDetail.setLandPlotCost(loanEligibilityRequest.getMarketValue());
					}
					primaryHomeLoanDetailRepository.save(primaryHomeLoanDetail);
					// create record in fs retail applicant
					saveRetailApplicantDetailFromLoanEligibility(applicationMaster, loanEligibilityRequest);
					break;
				case CAR_LOAN:
					break;

				default:
					continue;
				}
			}
			logger.info("Exit from saveOrUpdateFromLoanEligibilty");
			return true;
		} catch (Exception e) {
			logger.error("Error while Saving Loan Details:-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	public Boolean saveRetailApplicantDetailFromLoanEligibility(LoanApplicationMaster applicationMaster,
			LoanEligibilityRequest loanEligibilityRequest) {
		try {
			RetailApplicantDetail retailApplicantDetail = new RetailApplicantDetail();
			retailApplicantDetail.setApplicationId(applicationMaster);
			retailApplicantDetail.setOccupationId(loanEligibilityRequest.getEmploymentType());
			retailApplicantDetail.setBirthDate(loanEligibilityRequest.getDateOfBirth());
			retailApplicantDetail.setMonthlyIncome(loanEligibilityRequest.getIncome());
			retailApplicantDetail.setMonthlyLoanObligation(loanEligibilityRequest.getObligation());
			retailApplicantDetail.setIsActive(true);
			retailApplicantDetail.setCreatedBy(applicationMaster.getUserId());
			retailApplicantDetail.setModifiedBy(applicationMaster.getUserId());
			retailApplicantDetail.setCreatedDate(new Date());
			retailApplicantDetail.setModifiedDate(new Date());
			retailApplicantDetailRepository.save(retailApplicantDetail);
			return true;
		} catch (Exception e) {
			logger.error("Error while Saving RetailApplicantDetailFromLoanEligibility:-",e);
			return false;
		}
	}

	@Override
	public LoanApplicationRequest getLoanBasicDetails(Long id, Long userId) {
		LoanApplicationMaster applicationMaster = loanApplicationRepository.getById(id);
		if (applicationMaster == null) {
			return null;
		}
		LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
		BeanUtils.copyProperties(applicationMaster, applicationRequest);
		/*
		 * 
		 * applicationRequest.setApplicationCode(applicationMaster.getApplicationCode())
		 * ; applicationRequest.setProductId(applicationMaster.getProductId());
		 * applicationRequest.setAmount(applicationMaster.getAmount());
		 * applicationRequest.setDenominationId(applicationMaster.getDenominationId());
		 */
		int userMainType = 0;
		if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getProductId())) {
			userMainType = CommonUtils.UserMainType.CORPORATE;
		} else {
			applicationRequest.setLoanTypeSub(CommonUtils.getCorporateLoanType(applicationMaster.getProductId()));
			userMainType = CommonUtils.getUserMainType(applicationMaster.getProductId());
		}
		if (userMainType == CommonUtils.UserMainType.CORPORATE) {
			CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
					.findOneByApplicationIdId(id);
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
				applicationRequest.setName(corporateApplicantDetail.getOrganisationName());
				applicationRequest.setCreatedDate(applicationMaster.getCreatedDate());
				applicationRequest.setTypeOfPayment(applicationMaster.getTypeOfPayment());
				applicationRequest.setAmount(applicationMaster.getPaymentAmount());
				applicationRequest.setGstIn(corporateApplicantDetail.getGstIn());
			}
		}
		return applicationRequest;
	}

	@Override
	public LoanApplicationRequest get(Long id, Long userId,Long userOrdId) throws LoansException {
		try {
			LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
			LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(id, userId);
			if (applicationMaster == null) {
				throw new NullPointerException(INVALID_LOAN_APPLICATION_ID + id + " of User ID==>" + userId);
			}
			BeanUtils.copyProperties(applicationMaster, applicationRequest, "name");
			//applicationRequest.setProfilePrimaryLocked(applicationMaster.getIsPrimaryLocked() != null ? applicationMaster.getIsPrimaryLocked() : false);
			//applicationRequest.setFinalLocked(applicationMaster.getIsFinalLocked() != null ? applicationMaster.getIsFinalLocked() : false);

			// start for multiple loan Hiren

            ApplicationProposalMapping applicationProposalMapping=applicationProposalMappingRepository.getByApplicationIdAndOrgId(id,userOrdId);
            if(CommonUtils.isObjectNullOrEmpty(applicationProposalMapping)){
            	// TODO should get application info from app_proposal_mapping table by proposalId
				applicationProposalMapping = applicationProposalMappingRepository.getByApplicationId(id);
			}
            
            if(CommonUtils.isObjectNullOrEmpty(applicationProposalMapping)){
				throw new NullPointerException(INVALID_LOAN_APPLICATION_ID + id + " of User Org Id==>" + userOrdId);
			}else{
				applicationRequest.setProposalMappingId(applicationProposalMapping.getProposalId());
				applicationMaster.setProductId(applicationProposalMapping.getProductId());
				applicationMaster.setIsPrimaryLocked(applicationProposalMapping.getIsPrimaryLocked());
				applicationRequest.setFinalLocked(applicationProposalMapping.getFinalLocked());
				applicationMaster.setApplicationCode(applicationProposalMapping.getApplicationCode());
				applicationRequest.setIsMcqSkipped(applicationProposalMapping.getIsMcqSkipped() != null ? applicationProposalMapping.getIsMcqSkipped() : false);
				applicationRequest.setDdrStatusId(applicationProposalMapping.getDdrStatusId());
			}

            // end for multiple loan Hiren

			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getProductId())) {
				return applicationRequest;
			}

			applicationRequest.setHasAlreadyApplied(
					hasAlreadyApplied(userId, applicationMaster.getId(), applicationMaster.getProductId()));

			int userMainType = CommonUtils.getUserMainType(applicationMaster.getProductId());
			if (userMainType == CommonUtils.UserMainType.CORPORATE) {
				applicationRequest.setLoanTypeMain(CommonUtils.CORPORATE);
				String currencyAndDenomination = "NA";
				if (!CommonUtils.isObjectNullOrEmpty(applicationMaster.getCurrencyId())
						&& !CommonUtils.isObjectNullOrEmpty(applicationMaster.getDenominationId())) {
					try {
						currencyAndDenomination = CommonDocumentUtils.getCurrency(applicationMaster.getCurrencyId());
						currencyAndDenomination = currencyAndDenomination.concat(
								" in " + CommonDocumentUtils.getDenomination(applicationMaster.getDenominationId()));
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
				}
				applicationRequest.setCurrencyValue(currencyAndDenomination);
				applicationRequest.setLoanTypeSub(CommonUtils.getCorporateLoanType(applicationMaster.getProductId()));

				/*if (!CommonUtils.isObjectNullOrEmpty(applicationRequest.getTypeOfPayment())
						&& applicationRequest.getTypeOfPayment().equals(CommonUtils.PaymentMode.ONLINE)) {
					GatewayRequest gatewayRequest = networkPartnerService
							.getPaymentStatuOfApplication(applicationRequest.getId());
					if (!CommonUtils.isObjectNullOrEmpty(gatewayRequest)) {
							applicationRequest.setPaymentStatus(gatewayRequest.getStatus());
					}
				}*/
			} else {
				applicationRequest.setLoanTypeMain(CommonUtils.RETAIL);
				Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, applicationMaster.getId());
				applicationRequest.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
				applicationRequest.setLoanTypeSub("DEBT");
			}
			applicationRequest.setProfilePrimaryLocked(applicationMaster.getIsPrimaryLocked());
			try {
				ProposalMappingResponse response = proposalDetailsClient
						.getFundSeekerApplicationStatus(applicationMaster.getId());
				applicationRequest.setStatus(
						CommonUtils.isObjectNullOrEmpty(response.getData()) ? null : (Integer) response.getData());
				applicationRequest.setName(LoanType.getType(applicationMaster.getProductId()).getName());
				return applicationRequest;
			} catch (Exception e) {
				logger.error("Error while getting Status From Proposal Client : ",e);
				return applicationRequest;
			}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_INDIVIDUAL_LOAN_DETAILS,e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public LoanApplicationRequest inActive(Long id, Long userId) throws LoansException {
		loanApplicationRepository.inActive(id, userId);
		List<LoanApplicationMaster> userLoans = loanApplicationRepository.getUserLoans(userId);
		UsersRequest usersRequest = new UsersRequest();
		if (!CommonUtils.isListNullOrEmpty(userLoans)) {
			LoanApplicationMaster loan = userLoans.get(0);
			usersRequest.setLastAccessApplicantId(loan.getId());
			usersRequest.setId(userId);
			userClient.setLastAccessApplicant(usersRequest);
			return new LoanApplicationRequest(loan.getId(), loan.getProductId());
		} else {
			usersRequest.setId(userId);
			usersRequest.setLastAccessApplicantId(null);
			userClient.setLastAccessApplicant(usersRequest);
		}
		return null;
	}

	@Override
	public List<LoanApplicationRequest> getList(Long userId) throws LoansException {
		try {
			
			logger.info("In GetList");
			List<ApplicationProposalMapping> results = applicationProposalMappingRepository.getUserLoans(userId);
			//List<LoanApplicationMaster> results = loanApplicationRepository.getUserLoans(userId);
			List<LoanApplicationRequest> requests = new ArrayList<>(results.size());
			if("N".equals(IS_UNIT_TEST)) {
			for (ApplicationProposalMapping master : results) {
				LoanApplicationRequest request = new LoanApplicationRequest();
				request.setId(master.getApplicationId());
				BeanUtils.copyProperties(master, request, "name");
				if (CommonUtils.isObjectNullOrEmpty(master.getProductId())) {
					request.setLoanTypeMain(CommonUtils.CORPORATE);
					request.setLoanTypeSub("DEBT");
					request.setApplicationStatus(CommonUtils.ApplicationStatusMessage.IN_PROGRESS.getValue());
					requests.add(request);
					continue;
				}
				request.setAmount(master.getLoanAmount());
				request.setHasAlreadyApplied(hasAlreadyApplied(userId, master.getApplicationId(), master.getProductId()));
				int userMainType = CommonUtils.getUserMainType(master.getProductId());
				if (userMainType == CommonUtils.UserMainType.CORPORATE) {
					request.setLoanTypeMain(CommonUtils.CORPORATE);
					String currencyAndDenomination = "NA";
					if (!CommonUtils.isObjectNullOrEmpty(master.getCurrencyId())
							&& !CommonUtils.isObjectNullOrEmpty(master.getDenominationId())) {
						try {
							currencyAndDenomination = CommonDocumentUtils.getCurrency(master.getCurrencyId());
							currencyAndDenomination = currencyAndDenomination
									.concat(" in " + CommonDocumentUtils.getDenomination(master.getDenominationId().intValue()));
						} catch (Exception e) {
							logger.error(CommonUtils.EXCEPTION,e);
						}
					}
					request.setCurrencyValue(currencyAndDenomination);
					request.setLoanTypeSub(CommonUtils.getCorporateLoanType(master.getProductId()));
				} else {
					request.setLoanTypeMain(CommonUtils.RETAIL);
					Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, master.getApplicationId());
					request.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
					request.setLoanTypeSub("DEBT");
				}
				request.setProfilePrimaryLocked(master.getIsPrimaryLocked());
				request.setFinalLocked(master.getIsFinalLocked());
				try {
					if (!CommonUtils.isObjectNullOrEmpty(master.getApplicationStatusMaster())) {
						request.setStatus(Integer.valueOf(master.getApplicationStatusMaster().getId().toString()));
						request.setIsNhbsApplication(true);
						request.setDdrStatusId(master.getDdrStatusId());
					} else {
						ProposalMappingResponse response = proposalDetailsClient
								.getFundSeekerApplicationStatus(master.getApplicationId());
						request.setStatus(CommonUtils.isObjectNullOrEmpty(response.getData()) ? null
								: (Integer) response.getData());
						request.setIsNhbsApplication(false);
					}
					request.setName(LoanType.getType(master.getProductId()).getName());
					requests.add(request);
				} catch (Exception e) {
					logger.error("Error while Getting Loan Status from Proposal Client or Proposal Service is not available:-",e);
				}
				long proposalStatusId = 0l;
				try {
					ProposalMappingResponse response = proposalDetailsClient
							.getActiveProposalByApplicationID(master.getApplicationId());

					if (!CommonUtils.isObjectNullOrEmpty(response)
							&& !CommonUtils.isObjectNullOrEmpty(response.getData())) {
						ProposalMappingRequest proposalrequest = MultipleJSONObjectHelper.getObjectFromMap(
								(LinkedHashMap<String, Object>) response.getData(), ProposalMappingRequest.class);
						proposalStatusId = proposalrequest.getProposalStatusId().longValue();
					}
				} catch (Exception e) {
					logger.error("Error while calling getActiveProposalByApplicationID:-",e);
				}

				Integer status = request.getStatus();
				Long ddrStatus = request.getDdrStatusId();
				String applicationStatus = null;
				if (status == CommonUtils.ApplicationStatus.OPEN.intValue()) {
					if (request
							.getPaymentStatus() ==CommonUtils.PaymentStatus.SUCCESS) {
						applicationStatus = CommonUtils.ApplicationStatusMessage.DDR_IN_PROGRESS.getValue();
					} else {
						applicationStatus = CommonUtils.ApplicationStatusMessage.IN_PROGRESS.getValue();
					}
				} else if (ddrStatus == CommonUtils.DdrStatus.APPROVED) {
					if (proposalStatusId == MatchConstant.ProposalStatus.APPROVED) {
						applicationStatus = CommonUtils.ApplicationStatusMessage.SANCTIONED.getValue();
					} else if (proposalStatusId == MatchConstant.ProposalStatus.HOLD) {
						applicationStatus = CommonUtils.ApplicationStatusMessage.HOLD.getValue();
					} else if (proposalStatusId == MatchConstant.ProposalStatus.DECLINE) {
						applicationStatus = CommonUtils.ApplicationStatusMessage.REJECT.getValue();
					} else if (proposalStatusId == MatchConstant.ProposalStatus.DISBURSED) {
						applicationStatus = CommonUtils.ApplicationStatusMessage.DISBURSED.getValue();
					} else if (proposalStatusId == MatchConstant.ProposalStatus.ACCEPT) {
						applicationStatus = CommonUtils.ApplicationStatusMessage.DDR_APPROVED_BUT_NOT_SANCTIONED
								.getValue();
					} else {
						applicationStatus = CommonUtils.ApplicationStatusMessage.DDR_IN_PROGRESS.getValue();
					}
				} else {
					applicationStatus = CommonUtils.ApplicationStatusMessage.DDR_IN_PROGRESS.getValue();
				}
				request.setApplicationStatus(applicationStatus);
			}
				List<LoanApplicationMaster> offlineResults = loanApplicationRepository.getUserLoans(userId);
				for (LoanApplicationMaster master : offlineResults) {
					LoanApplicationRequest request = new LoanApplicationRequest();
					request.setId(master.getId());
					BeanUtils.copyProperties(master, request, "name");
					if (request.getBusinessTypeId().equals(CommonUtils.BusinessType.EXISTING_BUSINESS.getId())
							&& CommonUtils.isObjectNullOrEmpty(master.getProductId())) {
						request.setLoanTypeMain(CommonUtils.CORPORATE);
						request.setLoanTypeSub("DEBT");
						if(CommonUtils.isObjectNullOrEmpty(master.getIsPrimaryLocked()) || !master.getIsPrimaryLocked()){
							request.setName("MSME Application");
						}else{
							request.setName("Offline");
						}
						request.setApplicationStatus(CommonUtils.ApplicationStatusMessage.IN_ELIGIBLE.getValue());
						List<LoanApplicationRequest> tempList = requests.stream()
								.filter(appId -> request.getId().equals(appId.getId()))
								.collect(Collectors.toList());
						if(CommonUtils.isListNullOrEmpty(tempList)){
							requests.add(request);
						}
					}
				}
		}
			return requests;
		} catch (Exception e) {
			logger.error("Error while Getting Loan Details:-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(keyExtractor.apply(t));
	}

	@Override
	public List<LoanApplicationRequest> getApplicationList(Long userId) throws LoansException {

		try {
			logger.info("In GetList");
			List<ApplicationProposalMapping> applicationListFromProposal = applicationProposalMappingRepository.getUserLoans(userId);
			ConnectResponse connectResponse = connectClient.getApplicationListByUserId(userId);
			ObjectMapper mapper = new ObjectMapper();
			List<ConnectRequest> connectRequests = mapper.convertValue(connectResponse.getDataList(),new TypeReference<List<ConnectRequest>>(){});
			List<ConnectRequest> incompletedApplications =  connectRequests.stream().filter(cr -> cr.getStageId() < 7).collect(Collectors.toList());
			List<LoanApplicationRequest> requests = new ArrayList<>(applicationListFromProposal.size() + connectRequests.size());
			if("N".equals(IS_UNIT_TEST)) {
				for (ApplicationProposalMapping master : applicationListFromProposal) {
					LoanApplicationRequest request = new LoanApplicationRequest();
					request.setId(master.getApplicationId());
					BeanUtils.copyProperties(master, request, "name");
					if (CommonUtils.isObjectNullOrEmpty(master.getProductId())) {
						request.setLoanTypeMain(CommonUtils.CORPORATE);
						request.setLoanTypeSub("DEBT");
						request.setApplicationStatus(CommonUtils.ApplicationStatusMessage.IN_PROGRESS.getValue());
						requests.add(request);
						continue;
					}
					request.setHasAlreadyApplied(hasAlreadyApplied(userId, master.getApplicationId(), master.getProductId(),master.getProposalId()));
					int userMainType = CommonUtils.getUserMainType(master.getProductId());
					if (userMainType == CommonUtils.UserMainType.CORPORATE) {
						request.setLoanTypeMain(CommonUtils.CORPORATE);
						String currencyAndDenomination = "NA";
						if (!CommonUtils.isObjectNullOrEmpty(master.getCurrencyId())
								&& !CommonUtils.isObjectNullOrEmpty(master.getDenominationId())) {
							try {
								currencyAndDenomination = CommonDocumentUtils.getCurrency(master.getCurrencyId());
								currencyAndDenomination = currencyAndDenomination
										.concat(" in " + CommonDocumentUtils.getDenomination(master.getDenominationId().intValue()));
							} catch (Exception e) {
								logger.error(CommonUtils.EXCEPTION,e);
							}
						}
						request.setCurrencyValue(currencyAndDenomination);
						request.setLoanTypeSub(CommonUtils.getCorporateLoanType(master.getProductId()));
					} else {
						request.setLoanTypeMain(CommonUtils.RETAIL);
						Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, master.getApplicationId());
						request.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
						request.setLoanTypeSub("DEBT");
					}
					request.setProfilePrimaryLocked(master.getIsPrimaryLocked());
					request.setFinalLocked(master.getIsFinalLocked());
					try {
						if (!CommonUtils.isObjectNullOrEmpty(master.getApplicationStatusMaster())) {
							request.setStatus(Integer.valueOf(master.getApplicationStatusMaster().getId().toString()));
							request.setIsNhbsApplication(true);
							request.setDdrStatusId(master.getDdrStatusId());
						} else {
							ProposalMappingResponse response = proposalDetailsClient
									.getFundSeekerApplicationStatus(master.getApplicationId());
							request.setStatus(CommonUtils.isObjectNullOrEmpty(response.getData()) ? null
									: (Integer) response.getData());
							request.setIsNhbsApplication(false);
						}
						request.setName(LoanType.getType(master.getProductId()).getName());
						requests.add(request);
					} catch (Exception e) {
						logger.error("Error while Getting Loan Status from Proposal Client or Proposal Service is not available:-",e);
					}
					long proposalStatusId = 0l;
					try {
						ProposalMappingResponse response = proposalDetailsClient
								.getActiveProposalByApplicationID(master.getApplicationId());

						if (!CommonUtils.isObjectNullOrEmpty(response)
								&& !CommonUtils.isObjectNullOrEmpty(response.getData())) {
							ProposalMappingRequest proposalrequest = MultipleJSONObjectHelper.getObjectFromMap(
									(LinkedHashMap<String, Object>) response.getData(), ProposalMappingRequest.class);
							proposalStatusId = proposalrequest.getProposalStatusId().longValue();
						}
					} catch (Exception e) {
						logger.error("Error while calling getActiveProposalByApplicationID:-",e);
					}

					Integer status = request.getStatus();
					Long ddrStatus = request.getDdrStatusId();
					String applicationStatus = null;
					if (status == CommonUtils.ApplicationStatus.OPEN.intValue()) {
						if (request
								.getPaymentStatus() == CommonUtils.PaymentStatus.SUCCESS) {
							applicationStatus = CommonUtils.ApplicationStatusMessage.DDR_IN_PROGRESS.getValue();
						} else {
							applicationStatus = CommonUtils.ApplicationStatusMessage.IN_PROGRESS.getValue();
						}
					} else if (ddrStatus == CommonUtils.DdrStatus.APPROVED) {
						if (proposalStatusId == MatchConstant.ProposalStatus.APPROVED) {
							applicationStatus = CommonUtils.ApplicationStatusMessage.SANCTIONED.getValue();
						} else if (proposalStatusId == MatchConstant.ProposalStatus.HOLD) {
							applicationStatus = CommonUtils.ApplicationStatusMessage.HOLD.getValue();
						} else if (proposalStatusId == MatchConstant.ProposalStatus.DECLINE) {
							applicationStatus = CommonUtils.ApplicationStatusMessage.REJECT.getValue();
						} else if (proposalStatusId == MatchConstant.ProposalStatus.DISBURSED) {
							applicationStatus = CommonUtils.ApplicationStatusMessage.DISBURSED.getValue();
						} else if (proposalStatusId == MatchConstant.ProposalStatus.ACCEPT) {
							applicationStatus = CommonUtils.ApplicationStatusMessage.DDR_APPROVED_BUT_NOT_SANCTIONED
									.getValue();
						} else {
							applicationStatus = CommonUtils.ApplicationStatusMessage.DDR_IN_PROGRESS.getValue();
						}
					} else {
						applicationStatus = CommonUtils.ApplicationStatusMessage.DDR_IN_PROGRESS.getValue();
					}
					request.setApplicationStatus(applicationStatus);
				}
			}
			if("N".equals(IS_UNIT_TEST)) {
				for (ConnectRequest master : incompletedApplications) {
					LoanApplicationRequest request = new LoanApplicationRequest();
					BeanUtils.copyProperties(master, request, "name");
					request.setId(master.getApplicationId());
					request.setLoanTypeMain(CommonUtils.CORPORATE);
					request.setLoanTypeSub("DEBT");
					request.setApplicationStatus(CommonUtils.ApplicationStatusMessage.IN_PROGRESS.getValue());
					requests.add(request);
				}
			}

			return requests;
		} catch (Exception e) {
			logger.error("Error while Getting Loan Details:-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
	
	
	//Arun Code
	public List<LoanApplicationRequest> getDetailsForSanctionPopup(Long userId) throws LoansException {

		try {
			logger.info("In GetDetail");
			List<ApplicationProposalMapping> applicationListFromProposal = applicationProposalMappingRepository.getUserLoans(userId);
			ConnectResponse connectResponse = connectClient.getApplicationListByUserId(userId);
			ObjectMapper mapper = new ObjectMapper();
			List<ConnectRequest> connectRequests = mapper.convertValue(connectResponse.getDataList(),new TypeReference<List<ConnectRequest>>(){});
			List<ConnectRequest> incompletedApplications =  connectRequests.stream().filter(cr -> cr.getStageId() < 7).collect(Collectors.toList());
			List<LoanApplicationRequest> requests = new ArrayList<>(applicationListFromProposal.size() + connectRequests.size());
			if("N".equals(IS_UNIT_TEST)) {
				for (ApplicationProposalMapping master : applicationListFromProposal) {
					LoanApplicationRequest request = new LoanApplicationRequest();
					request.setId(master.getApplicationId());
					BeanUtils.copyProperties(master, request, "name");
					if (CommonUtils.isObjectNullOrEmpty(master.getProductId())) {
						request.setLoanTypeMain(CommonUtils.CORPORATE);
						request.setLoanTypeSub("DEBT");
						request.setApplicationStatus(CommonUtils.ApplicationStatusMessage.IN_PROGRESS.getValue());
						requests.add(request);
						continue;
					}
					request.setHasAlreadyApplied(hasAlreadyApplied(userId, master.getApplicationId(), master.getProductId(),master.getProposalId()));
					int userMainType = CommonUtils.getUserMainType(master.getProductId());
					if (userMainType == CommonUtils.UserMainType.CORPORATE) {
						request.setLoanTypeMain(CommonUtils.CORPORATE);
						String currencyAndDenomination = "NA";
						if (!CommonUtils.isObjectNullOrEmpty(master.getCurrencyId())
								&& !CommonUtils.isObjectNullOrEmpty(master.getDenominationId())) {
							try {
								currencyAndDenomination = CommonDocumentUtils.getCurrency(master.getCurrencyId());
								currencyAndDenomination = currencyAndDenomination
										.concat(" in " + CommonDocumentUtils.getDenomination(master.getDenominationId().intValue()));
							} catch (Exception e) {
								logger.error(CommonUtils.EXCEPTION,e);
							}
						}
						request.setCurrencyValue(currencyAndDenomination);
						request.setLoanTypeSub(CommonUtils.getCorporateLoanType(master.getProductId()));
					} else {
						request.setLoanTypeMain(CommonUtils.RETAIL);
						Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, master.getApplicationId());
						request.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
						request.setLoanTypeSub("DEBT");
					}
					request.setProfilePrimaryLocked(master.getIsPrimaryLocked());
					request.setFinalLocked(master.getIsFinalLocked());
					try {
						if (!CommonUtils.isObjectNullOrEmpty(master.getApplicationStatusMaster())) {
							request.setStatus(Integer.valueOf(master.getApplicationStatusMaster().getId().toString()));
							request.setIsNhbsApplication(true);
							request.setDdrStatusId(master.getDdrStatusId());
						} else {
							ProposalMappingResponse response = proposalDetailsClient
									.getFundSeekerApplicationStatus(master.getApplicationId());
							request.setStatus(CommonUtils.isObjectNullOrEmpty(response.getData()) ? null
									: (Integer) response.getData());
							request.setIsNhbsApplication(false);
						}
						request.setName(LoanType.getType(master.getProductId()).getName());
						requests.add(request);
					} catch (Exception e) {
						logger.error("Error while Getting Loan Status from Proposal Client or Proposal Service is not available:-",e);
					}
					long proposalStatusId = 0l;
					try {
						ProposalMappingResponse response = proposalDetailsClient
								.getActiveProposalByApplicationID(master.getApplicationId());

						if (!CommonUtils.isObjectNullOrEmpty(response)
								&& !CommonUtils.isObjectNullOrEmpty(response.getData())) {
							ProposalMappingRequest proposalrequest = MultipleJSONObjectHelper.getObjectFromMap(
									(LinkedHashMap<String, Object>) response.getData(), ProposalMappingRequest.class);
							proposalStatusId = proposalrequest.getProposalStatusId().longValue();
						}
					} catch (Exception e) {
						logger.error("Error while calling getActiveProposalByApplicationID:-",e);
					}

					Integer status = request.getStatus();
					Long ddrStatus = request.getDdrStatusId();
					String applicationStatus = null;
					if (status == CommonUtils.ApplicationStatus.OPEN.intValue()) {
						if (request
								.getPaymentStatus() == CommonUtils.PaymentStatus.SUCCESS) {
							applicationStatus = CommonUtils.ApplicationStatusMessage.DDR_IN_PROGRESS.getValue();
						} else {
							applicationStatus = CommonUtils.ApplicationStatusMessage.IN_PROGRESS.getValue();
						}
					} else if (ddrStatus == CommonUtils.DdrStatus.APPROVED) {
						if (proposalStatusId == MatchConstant.ProposalStatus.APPROVED) {
							applicationStatus = CommonUtils.ApplicationStatusMessage.SANCTIONED.getValue();
						} else if (proposalStatusId == MatchConstant.ProposalStatus.HOLD) {
							applicationStatus = CommonUtils.ApplicationStatusMessage.HOLD.getValue();
						} else if (proposalStatusId == MatchConstant.ProposalStatus.DECLINE) {
							applicationStatus = CommonUtils.ApplicationStatusMessage.REJECT.getValue();
						} else if (proposalStatusId == MatchConstant.ProposalStatus.DISBURSED) {
							applicationStatus = CommonUtils.ApplicationStatusMessage.DISBURSED.getValue();
						} else if (proposalStatusId == MatchConstant.ProposalStatus.ACCEPT) {
							applicationStatus = CommonUtils.ApplicationStatusMessage.DDR_APPROVED_BUT_NOT_SANCTIONED
									.getValue();
						} else {
							applicationStatus = CommonUtils.ApplicationStatusMessage.DDR_IN_PROGRESS.getValue();
						}
					} else {
						applicationStatus = CommonUtils.ApplicationStatusMessage.DDR_IN_PROGRESS.getValue();
					}
					request.setApplicationStatus(applicationStatus);
				}
			}
			if("N".equals(IS_UNIT_TEST)) {
				for (ConnectRequest master : incompletedApplications) {
					LoanApplicationRequest request = new LoanApplicationRequest();
					BeanUtils.copyProperties(master, request, "name");
					request.setId(master.getApplicationId());
					request.setLoanTypeMain(CommonUtils.CORPORATE);
					request.setLoanTypeSub("DEBT");
					request.setApplicationStatus(CommonUtils.ApplicationStatusMessage.IN_PROGRESS.getValue());
					requests.add(request);
				}
			}

			return requests;
		} catch (Exception e) {
			logger.error("Error while Getting Loan Details:-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
	
	
	
	
	
	
	
	

	@Override
	public List<MLoanDetailsResponse> getLoanListForMobile(Long userId) {
		List<LoanApplicationMaster> loanApplicationMasterList = loanApplicationRepository.getUserLoans(userId);
		List<MLoanDetailsResponse> responseList = new ArrayList<>(loanApplicationMasterList.size());
		for (LoanApplicationMaster loanApplicationMaster : loanApplicationMasterList) {
			MLoanDetailsResponse response = new MLoanDetailsResponse();
			response.setId(loanApplicationMaster.getId());
			response.setApplicationCode(loanApplicationMaster.getApplicationCode());
			response.setLoan(CommonUtils.LoanType.getType(loanApplicationMaster.getProductId()).getName());
			response.setAmount(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getAmount())
					? loanApplicationMaster.getAmount()
					: 0.0);
			response.setCreatedDate(loanApplicationMaster.getCreatedDate());
			response.setProductId(loanApplicationMaster.getProductId());
			int userMainType = CommonUtils.getUserMainType(loanApplicationMaster.getProductId());
			if (userMainType == CommonUtils.UserMainType.CORPORATE) {
				response.setLoanType(CommonUtils.CORPORATE);
				String currencyAndDenomination = "NA";
				if (!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getCurrencyId())
						&& !CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getDenominationId())) {
					try {
						currencyAndDenomination = CommonDocumentUtils
								.getCurrency(loanApplicationMaster.getCurrencyId());
						currencyAndDenomination = currencyAndDenomination.concat(" in "
								+ CommonDocumentUtils.getDenomination(loanApplicationMaster.getDenominationId()));
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
				}
				response.setCurrency(currencyAndDenomination);
			} else {
				response.setLoanType(CommonUtils.RETAIL);
				Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, loanApplicationMaster.getId());
				response.setCurrency(CommonDocumentUtils.getCurrency(currencyId));
			}
			responseList.add(response);
		}
		return responseList;
	}

	@Override
	public List<LoanApplicationDetailsForSp> getLoanDetailsByUserIdList(Long userId) {
		return loanApplicationRepository.getListByUserId(userId);
	}

	@Override
	public boolean lockPrimary(Long applicationId, Long userId, boolean flag) throws LoansException {
		try {
			LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
			if (applicationMaster == null) {
				throw new LoansException(
						"LoanapplicationMaster object Must not be null while locking the Profile And Primary Details==>"
								+ applicationMaster);
			}

			applicationMaster.setIsPrimaryLocked(flag);
			loanApplicationRepository.save(applicationMaster);
			// create log when teaser submit
			logService.saveFsLog(applicationId, LogDateTypeMaster.TEASER_SUBMIT.getId());

			return true;
		} catch (Exception e) {
			logger.error("Error while Locking Profile and Primary Information : ",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

    @Override
    public LoanApplicationRequest lockFinalByProposalId(Long applicationId, Long proposalId,Long userId, boolean flag) throws Exception {
        try {

            LoanApplicationRequest loanApplicationRequest = new LoanApplicationRequest();
            loanApplicationRequest.setIsMailSent(false);
            LoanApplicationMaster applicationMaster1 = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
            ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.getByProposalIdAndApplicationId(proposalId,applicationId);
            if (applicationProposalMapping == null) {
                throw new Exception(
                        "applicationProposalMapping object Must not be null while locking the Profile And Primary Details==>"
                                + applicationProposalMapping);
            }
            applicationProposalMapping.setIsFinalLocked(flag);
            applicationProposalMapping
                    .setApplicationStatusMaster(new ApplicationStatusMaster(CommonUtils.ApplicationStatus.SUBMITTED));
            applicationProposalMappingRepository.save(applicationProposalMapping);

            // send FP notification
            ProposalMappingRequest request = new ProposalMappingRequest();
            request.setApplicationId(applicationId);
            request.setProposalStatusId(MatchConstant.ProposalStatus.ACCEPT);
            ProposalMappingResponse response = proposalDetailsClient.proposalListOfFundSeeker(request);
            NotificationRequest notificationRequest = new NotificationRequest();
            notificationRequest.setClientRefId(userId.toString());
            String fsName = getApplicantName(applicationId);
            for (int i = 0; i < response.getDataList().size(); i++) {
                ProposalMappingRequest proposalrequest = MultipleJSONObjectHelper.getObjectFromMap(
                        (LinkedHashMap<String, Object>) response.getDataList().get(i), ProposalMappingRequest.class);

                ProductMaster master = productMasterRepository.findOne(proposalrequest.getFpProductId());
                if (!master.getIsActive()) {
                    logger.info("Product Id is InActive while get fundSeeker proposals=====>"
                            + proposalrequest.getFpProductId());
                    continue;
                }
                UsersRequest userRequest = new UsersRequest();
                userRequest.setId(master.getUserId());
                Map<String, Object> parameters = new HashMap<String, Object>();
                // calling USER for getting fp details
                UserResponse userResponse = userClient.getFPDetails(userRequest);

                try {
                    if (CommonUtils.isObjectNullOrEmpty(fsName)) {
                        parameters.put("fs_name", "NA");
                    } else {
                        parameters.put("fs_name", fsName);
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                    parameters.put("fs_name", "NA");
                }
                String[] a = { master.getUserId().toString() };
                notificationRequest
                        .addNotification(createNotification(a, userId, 0L, NotificationAlias.SYS_FS_FINAL_LOCK,
                                parameters, applicationId, proposalrequest.getFpProductId()));
            }
            logger.info("Before send mail-------------------------------------->");
            int userMainType = CommonUtils.getUserMainType(applicationProposalMapping.getProductId());
            if (userMainType == CommonUtils.UserMainType.CORPORATE) {
                logger.info("Current loan is corporate-------------------------------------->");
                if (!CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getNpAssigneeId())
                        && !CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getNpUserId())) {
                    logger.info("Start sending mail when maker has lock primary details");

                    loanApplicationRequest.setId(applicationProposalMapping.getApplicationId());
                    loanApplicationRequest.setProposalMappingId(applicationProposalMapping.getProposalId());
                    loanApplicationRequest.setNpAssigneeId(applicationProposalMapping.getNpAssigneeId());
                    loanApplicationRequest.setNpUserId(applicationProposalMapping.getNpUserId());
                    loanApplicationRequest.setApplicationCode(applicationProposalMapping.getApplicationCode());
                    loanApplicationRequest.setProductId(applicationProposalMapping.getProductId());
                    loanApplicationRequest.setName(fsName);
                    loanApplicationRequest.setIsMailSent(true);

                } else {
                    logger.info("NP userId or assign id null or empty-------------------------------------->");
                }

            }
            notificationClient.send(notificationRequest);
            UsersRequest resp = getEmailMobile(applicationProposalMapping.getNpAssigneeId());
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("url", "https://bit.ly/2IGwvBF");
            try {
                String makerName = getNPName(applicationProposalMapping.getNpUserId());
                if (makerName != null) {
                    parameters.put("maker_name", getNPName(applicationProposalMapping.getNpUserId()));
                    parameters.put("checker_name", getNPName(applicationProposalMapping.getNpAssigneeId()));
                    sendSMSNotification(applicationProposalMapping.getNpAssigneeId().toString(), parameters,
                            NotificationAlias.SMS_MAKER_LOCKS_ONEFORM, resp.getMobile());

                    logService.saveFsLog(applicationId, LogDateTypeMaster.FINAL_SUBMIT.getId());
                }
            } catch (Exception e) {
                logger.info("maker name is null so sms is not sent");
            }
            return loanApplicationRequest;
        } catch (Exception e) {
            logger.error("Error while Locking Final Information : {}",e);
            throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);

        }
    }

	@Override
	public LoanApplicationRequest lockFinal(Long applicationId, Long userId, boolean flag) throws LoansException {
		try {

			LoanApplicationRequest loanApplicationRequest = new LoanApplicationRequest();
			loanApplicationRequest.setIsMailSent(false);
			LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
			if (applicationMaster == null) {
				throw new LoansException(
						"LoanapplicationMaster object Must not be null while locking the Profile And Primary Details==>"
								+ applicationMaster);
			}
			applicationMaster.setIsFinalLocked(flag);
			applicationMaster
					.setApplicationStatusMaster(new ApplicationStatusMaster(CommonUtils.ApplicationStatus.SUBMITTED));
			loanApplicationRepository.save(applicationMaster);

			// send FP notification
			ProposalMappingRequest request = new ProposalMappingRequest();
			request.setApplicationId(applicationId);
			request.setProposalStatusId(MatchConstant.ProposalStatus.ACCEPT);
			ProposalMappingResponse response = proposalDetailsClient.proposalListOfFundSeeker(request);
			NotificationRequest notificationRequest = new NotificationRequest();
			notificationRequest.setClientRefId(userId.toString());
			String fsName = getApplicantName(applicationId);
			for (int i = 0; i < response.getDataList().size(); i++) {
				ProposalMappingRequest proposalrequest = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) response.getDataList().get(i), ProposalMappingRequest.class);

				ProductMaster master = productMasterRepository.findOne(proposalrequest.getFpProductId());
				if (!master.getIsActive()) {
					logger.info("Product Id is InActive while get fundSeeker proposals=====>"
							+ proposalrequest.getFpProductId());
					continue;
				}
				UsersRequest userRequest = new UsersRequest();
				userRequest.setId(master.getUserId());
				Map<String, Object> parameters = new HashMap<String, Object>();
				// calling USER for getting fp details
				UserResponse userResponse = userClient.getFPDetails(userRequest);

				if (userResponse != null){
					logger.info("userResponse successfully getFPDetails");
				}

				// FundProviderDetailsRequest fundProviderDetailsRequest =
				// MultipleJSONObjectHelper.getObjectFromMap(
				// (LinkedHashMap<String, Object>) userResponse.getData(),
				// FundProviderDetailsRequest.class);
				// fundProviderDetailsRequest.get
				try {
					if (CommonUtils.isObjectNullOrEmpty(fsName)) {
						parameters.put(CommonUtils.PARAMETERS_FS_NAME, "NA");
					} else {
						parameters.put(CommonUtils.PARAMETERS_FS_NAME, fsName);
					}

				} catch (Exception e) {
					parameters.put(CommonUtils.PARAMETERS_FS_NAME, "NA");
				}
				// String fpName = "";
				// try {
				// Object[] name = getFPName(proposalrequest.getFpProductId());
				// if (!CommonUtils.isObjectNullOrEmpty(name[1])) {
				// fpName = name[1].toString();
				// } else {
				// fpName = "NA";
				// }
				//
				// parameters.put("fp_name", CommonUtils.isObjectNullOrEmpty(fpName) ? "NA" :
				// fpName);
				// } catch (Exception e) {
				// logger.error(CommonUtils.EXCEPTION,e);
				// parameters.put("fp_name", "NA");
				// }
				// try {
				// String fpPName =
				// productMasterRepository.getFpProductName(proposalrequest.getFpProductId());
				// if(CommonUtils.isObjectNullOrEmpty(fpPName))
				// {
				// parameters.put("fp_pname", "NA");
				// }
				// else
				// {
				// parameters.put("fp_pname", fpPName);
				// }
				//
				// } catch (Exception e) {
				// logger.error(CommonUtils.EXCEPTION,e);
				// parameters.put("fp_pname", "NA");
				// }
				//
				String[] a = { master.getUserId().toString() };
				notificationRequest
						.addNotification(createNotification(a, userId, 0L, NotificationAlias.SYS_FS_FINAL_LOCK,
								parameters, applicationId, proposalrequest.getFpProductId()));
			}
			logger.info("Before send mail-------------------------------------->");
			int userMainType = CommonUtils.getUserMainType(applicationMaster.getProductId());
			if (userMainType == CommonUtils.UserMainType.CORPORATE) {
				logger.info("Current loan is corporate-------------------------------------->");
				if (!CommonUtils.isObjectNullOrEmpty(applicationMaster.getNpAssigneeId())
						&& !CommonUtils.isObjectNullOrEmpty(applicationMaster.getNpUserId())) {
					logger.info("Start sending mail when maker has lock primary details");

					loanApplicationRequest.setId(applicationMaster.getId());
					loanApplicationRequest.setNpAssigneeId(applicationMaster.getNpAssigneeId());
					loanApplicationRequest.setNpUserId(applicationMaster.getNpUserId());
					loanApplicationRequest.setApplicationCode(applicationMaster.getApplicationCode());
					loanApplicationRequest.setProductId(applicationMaster.getProductId());
					loanApplicationRequest.setName(fsName);
					loanApplicationRequest.setIsMailSent(true);
					/*
					 * asyncComponent.sendEmailWhenMakerLockFinalDetails(applicationMaster.
					 * getNpAssigneeId(),applicationMaster.getNpUserId(),
					 * applicationMaster.getApplicationCode(),applicationMaster.getProductId(),
					 * fsName,applicationMaster.getId());
					 */
				} else {
					logger.info("NP userId or assign id null or empty-------------------------------------->");
				}

			}
			notificationClient.send(notificationRequest);
			// send FP notification end

			// SMS

			UsersRequest resp = getEmailMobile(applicationMaster.getNpAssigneeId());
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("url", "https://bit.ly/2IGwvBF");
			try {
				String makerName = getNPName(applicationMaster.getNpUserId());
				if (makerName != null) {
					parameters.put("maker_name", getNPName(applicationMaster.getNpUserId()));
					parameters.put("checker_name", getNPName(applicationMaster.getNpAssigneeId()));
					sendSMSNotification(applicationMaster.getNpAssigneeId().toString(), parameters,
							NotificationAlias.SMS_MAKER_LOCKS_ONEFORM, resp.getMobile());

					// create log when teaser submit
					logService.saveFsLog(applicationId, LogDateTypeMaster.FINAL_SUBMIT.getId());
				}
			} catch (Exception e) {
				logger.error("maker name is null so sms is not sent : ",e);
			}
			return loanApplicationRequest;
		} catch (Exception e) {
			logger.error("Error while Locking Final Information : ",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);

		}
	}

	private void sendSMSNotification(String userId, Map<String, Object> parameters, Long templateId, String... to)
			throws NotificationException {
		NotificationRequest req = new NotificationRequest();
		req.setClientRefId(userId);
		Notification notification = new Notification();
		notification.setContentType(ContentType.TEMPLATE);
		notification.setTemplateId(templateId);
		notification.setTo(to);
		notification.setType(NotificationType.SMS);
		notification.setParameters(parameters);
		req.addNotification(notification);

		notificationClient.send(req);

	}

	private String getNPName(Long npUserId) throws IOException {
		if (CommonUtils.isObjectNullOrEmpty(npUserId)) {
			logger.warn("Np Usesr Id is NULL===>");
			return null;
		}
		UsersRequest usersRequest = new UsersRequest();
		usersRequest.setId(npUserId);
		UserResponse userResponse = userClient.getNPDetails(usersRequest);
		if (CommonUtils.isObjectListNull(userResponse, userResponse.getData())) {
			logger.warn("User Response or Data in UserResponse must not be null===>{}", userResponse);
			return null;
		}

		NetworkPartnerDetailsRequest networkPartnerDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
				(LinkedHashMap<String, Object>) userResponse.getData(), NetworkPartnerDetailsRequest.class);
		return (networkPartnerDetailsRequest.getFirstName() + " " + networkPartnerDetailsRequest.getLastName());
	}

	private UsersRequest getEmailMobile(Long userId) throws IOException {
		if (CommonUtils.isObjectNullOrEmpty(userId)) {
			logger.warn("Usesr Id is NULL===>");
			return null;
		}
		UserResponse emailMobile = userClient.getEmailMobile(userId);
		if (CommonUtils.isObjectListNull(emailMobile, emailMobile.getData())) {
			logger.warn(EMAIL_MOBILE_OR_DATA_IN_EMAIL_MOBILE_MUST_NOT_BE_NULL, emailMobile);
			return null;
		}

		return MultipleJSONObjectHelper
				.getObjectFromMap((LinkedHashMap<String, Object>) emailMobile.getData(), UsersRequest.class);
	}

	private Object[] getFPName(Long fpMappingId) {
		List<Object[]> pm = productMasterRepository.findById(fpMappingId);
		CommonDocumentUtils.endHook(logger, "getUserDetailsByPrductId");
		return (pm != null && !pm.isEmpty()) ? pm.get(0) : null;
	}

	private String getApplicantName(long applicationId) throws LoansException {
		try {
			return getFsApplicantName(applicationId);
		} catch (LoansException e1) {
			logger.error("Exception in getApplicantName : ",e1);
			return "NA";
		}
	}

	private static Notification createNotification(String[] toIds, Long fromId, Long fromUserTypeId, Long templateId,
			Map<String, Object> parameters, Long applicationId, Long fpProductId) {

		Notification notification = new Notification();

		notification.setTo(toIds);
		notification.setType(NotificationType.SYSTEM);
		notification.setTemplateId(templateId);
		notification.setContentType(ContentType.TEMPLATE);
		notification.setParameters(parameters);
		notification.setFrom(fromId.toString());
		notification.setProductId(fpProductId);
		notification.setApplicationId(applicationId);

		return notification;

	}

	@Override
	public UserResponse setLastAccessApplication(Long applicationId, Long userId) throws LoansException {
		try {
			UsersRequest usersRequest = new UsersRequest();
			usersRequest.setLastAccessApplicantId(applicationId);
			usersRequest.setId(userId);
			UsersClient client = new UsersClient(environment.getRequiredProperty(CommonUtils.USER_CLIENT_URL));
			return client.setLastAccessApplicant(usersRequest);
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);

		}

	}

	@Override
	public boolean hasAlreadyApplied(Long userId, Long applicationId,Integer productId) {
		if (CommonUtils.UserMainType.CORPORATE == CommonUtils.getUserMainType(productId)) {
			return (corporateApplicantDetailRepository.hasAlreadyApplied(userId,applicationId) > 0 ? true : false);
		} else {
			return (retailApplicantDetailRepository.hasAlreadyApplied(userId, applicationId) > 0 ? true : false);
		}
	}

	public boolean hasAlreadyApplied(Long userId, Long applicationId,Integer productId,Long proposalId) {
		if (CommonUtils.UserMainType.CORPORATE == CommonUtils.getUserMainType(productId)) {
			return (corporateApplicantDetailRepository.hasAlreadyApplied(userId,applicationId,proposalId) > 0 ? true : false);
		} else {
			return (retailApplicantDetailRepository.hasAlreadyApplied(userId, applicationId) > 0 ? true : false);
		}
	}

	@Override
	public Integer getProductIdByApplicationId(Long applicationId, Long userId) throws LoansException {
		try {
			//return loanApplicationRepository.getProductIdByApplicationId(applicationId, userId);
			return applicationProposalMappingRepository.getProductIdByApplicationId(applicationId, userId);
		} catch (Exception e) {
			logger.error("Error while getting Product Id by Application Id : ",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Object[] getApplicationDetailsById(Long applicationId) {
		List<Object[]> data = loanApplicationRepository.getUserDetailsByApplicationId(applicationId);
		return (!CommonUtils.isListNullOrEmpty(data)) ? data.get(0) : null;
	}

	@Override
	public Object[] getApplicationDetailsByProposalId(Long applicationId,Long proposalMappingId) {
		List<Object[]> data = applicationProposalMappingRepository.getUserDetailsByApplicationIdAndPropsoalMappingId(applicationId,proposalMappingId);
		return (!CommonUtils.isListNullOrEmpty(data)) ? data.toArray() : null;
	}

	@Override
	public void updateFinalCommonInformation(Long applicationId, Long userId, Boolean flag, String finalFilledCount)
			throws LoansException {
		try {
			loanApplicationRepository.setIsApplicantFinalMandatoryFilled(applicationId, userId, flag);
			loanApplicationRepository.setFinalFilledCount(applicationId, userId, finalFilledCount);
		} catch (Exception e) {
			logger.error("Error while updating final information flag : ",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Boolean isProfileAndPrimaryDetailFilled(Long applicationId, Long userId) throws LoansException {
		try {
			LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
			int userMainType = CommonUtils.getUserMainType(applicationMaster.getProductId());
			if (userMainType == CommonUtils.UserMainType.CORPORATE) {
				boolean isAnythingIsNull = CommonUtils.isObjectListNull(applicationMaster.getIsApplicantDetailsFilled(),
						applicationMaster.getIsApplicantPrimaryFilled());
				if (isAnythingIsNull)
					return false;

				return (applicationMaster.getIsApplicantDetailsFilled()
						&& applicationMaster.getIsApplicantPrimaryFilled());
			} else {
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
						|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue())
					return false;

				Long coApps = coApplicantDetailRepository.getCoAppCountByApplicationAndUserId(applicationId, userId);

				if (coApps == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue())
						return false;
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue())
						return false;
				} else if (coApps == 1 && (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
						|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue())) {
						return false;
				}

				Long guarantors = guarantorDetailsRepository.getGuarantorCountByApplicationAndUserId(applicationId,
						userId);

				if (guarantors == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue())
						return false;
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue())
						return false;
				} else if (guarantors == 1 && (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
						|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue())) {
						return false;
				}

				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
						|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue())
					return false;

				/*
				 * if
				 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
				 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) return
				 * false;
				 */

				return true;
			}
		} catch (Exception e) {
			logger.error("Error while getting isProfileAndPrimaryDetailFilled ?",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Boolean isPrimaryLocked(Long applicationId, Long userId) throws LoansException {
		try {
			Long count = loanApplicationRepository.checkPrimaryDetailIsLocked(applicationId);
			return (count != null ? count > 0 : false);
		} catch (Exception e) {
			logger.error("Error while getting isPrimaryLocked ?",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Boolean isPrimaryLockedByProposalId(Long proposalId, Long userId) throws LoansException {
		try {
			Long count = applicationProposalMappingRepository.checkPrimaryDetailIsLocked(proposalId);
			return (count != null ? count > 0 : false);
		} catch (Exception e) {
			logger.error("Error while getting isPrimaryLocked ?",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Boolean isApplicationIdActive(Long applicationId) throws LoansException { // PREVIOUS
		try {
			Long count = loanApplicationRepository.checkApplicationIdActive(applicationId);
			return (count != null ? count > 0 : false);
		} catch (Exception e) {
			logger.error("Error while getting isApplicationIdActive ?",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}


	@Override
	public Boolean getByProposalId(Long proposalId) throws Exception { // NEW BASED ON PROPOSAL ID
		try {
			Long count = applicationProposalMappingRepository.getByProposalId(proposalId);
			return (count != null ? count > 0 : false);
		} catch (Exception e) {
			logger.error("Error while getting isApplicationIdActive ?",e);
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Boolean isFinalDetailFilled(Long applicationId, Long userId) throws LoansException {
		try {
			LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster)) {
				return false;
			}

			int userMainType = CommonUtils.getUserMainType(applicationMaster.getProductId());
			if (userMainType == CommonUtils.UserMainType.CORPORATE) {
				boolean isAnythingIsNull = false;
				if (applicationMaster.getProductId() == LoanType.UNSECURED_LOAN.getValue()) {
					isAnythingIsNull = CommonUtils.isObjectListNull(applicationMaster.getIsFinalMcqFilled(),
							applicationMaster.getIsApplicantFinalFilled(), applicationMaster.getIsFinalUploadFilled());
				} else {
					isAnythingIsNull = CommonUtils.isObjectListNull(applicationMaster.getIsFinalMcqFilled(),
							applicationMaster.getIsApplicantFinalFilled(),
							applicationMaster.getIsFinalDprUploadFilled(), applicationMaster.getIsFinalUploadFilled());
				}
				if (isAnythingIsNull)
					return false;

				if (applicationMaster.getProductId() == LoanType.UNSECURED_LOAN.getValue()) {
					return (applicationMaster.getIsFinalMcqFilled() && applicationMaster.getIsApplicantFinalFilled()
							&& applicationMaster.getIsFinalUploadFilled());
				} else {
					return (applicationMaster.getIsFinalMcqFilled() && applicationMaster.getIsApplicantFinalFilled()
							&& applicationMaster.getIsFinalDprUploadFilled()
							&& applicationMaster.getIsFinalUploadFilled());
				}

			} else {
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantFinalFilled())
						|| !applicationMaster.getIsApplicantFinalFilled().booleanValue())
					return false;

				Long coApps = coApplicantDetailRepository.getCoAppCountByApplicationAndUserId(applicationId, userId);
				/*
				 * if (CommonUtils.isObjectNullOrEmpty(coApps) && coApps == 0) return false;
				 */

				if (coApps == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1FinalFilled())
							|| !applicationMaster.getIsCoApp1FinalFilled().booleanValue())
						return false;
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2FinalFilled())
							|| !applicationMaster.getIsCoApp2FinalFilled().booleanValue())
						return false;
				} else if (coApps == 1 && (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1FinalFilled())
						|| !applicationMaster.getIsCoApp1FinalFilled().booleanValue())) {
						return false;
				}

				Long guarantors = guarantorDetailsRepository.getGuarantorCountByApplicationAndUserId(applicationId,
						userId);
				/*
				 * if (CommonUtils.isObjectNullOrEmpty(guarantors) && guarantors == 0) return
				 * false;
				 */

				if (guarantors == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1FinalFilled())
							|| !applicationMaster.getIsGuarantor1FinalFilled().booleanValue())
						return false;
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2FinalFilled())
							|| !applicationMaster.getIsGuarantor2FinalFilled().booleanValue())
						return false;
				} else if (guarantors == 1 && (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1FinalFilled())
						|| !applicationMaster.getIsGuarantor1FinalFilled().booleanValue())) {
						return false;
				}

				// Here we are using MCQ column for Final Home loan and Final
				// Car Loan

				com.capitaworld.service.oneform.enums.LoanType loanType = com.capitaworld.service.oneform.enums.LoanType
						.getById(applicationMaster.getProductId());
				if (CommonUtils.isObjectNullOrEmpty(loanType)) {
					logger.warn("Invalid Product Id==>" + applicationMaster.getProductId());
					return false;
				}

				if ((loanType.getId() == CommonUtils.LoanType.HOME_LOAN.getValue() || loanType.getId() == CommonUtils.LoanType.CAR_LOAN.getValue())
					&& (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsFinalMcqFilled()) || !applicationMaster.getIsFinalMcqFilled().booleanValue())) {
						return false;
				}
				return true;
			}
		} catch (Exception e) {
			logger.error("Error while getting isFinalDetailFilled ?",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Boolean isFinalLockedByProposalId(Long proposalId,Long userId) throws Exception {
		try {
			Long count = applicationProposalMappingRepository.checkFinalDetailIsLocked(proposalId);
			return (count != null ? count > 0 : false);
		} catch (Exception e) {
			logger.error("Error while getting isFinalLocked ? = {}",e);
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Boolean isFinalLocked(Long applicationId, Long userId) throws LoansException {
		try {
			Long count = loanApplicationRepository.checkFinalDetailIsLocked(applicationId);
			return (count != null ? count > 0 : false);
		} catch (Exception e) {
			logger.error("Error while getting isFinalLocked ?",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Boolean isMcqSkipped(Long applicationId) throws LoansException {
		Long count = loanApplicationRepository.checkMcqSkipped(applicationId);
		return (count != null ? count > 0 : false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getSelfViewAndPrimaryLocked(Long applicationId, Long userId) throws LoansException {
		try {
			JSONObject json = new JSONObject();
			Long selfViewCount = loanApplicationRepository.isSelfApplicantView(applicationId, userId);
			json.put("isSelfView", (!CommonUtils.isObjectNullOrEmpty(selfViewCount) && selfViewCount > 0));
			json.put("isPrimaryLocked", isPrimaryLocked(applicationId, userId));
			return json;
		} catch (Exception e) {
			logger.error("Error while getting isFinalLocked ?",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Integer getCurrencyId(Long applicationId, Long userId) throws LoansException {
		return loanApplicationRepository.getCurrencyId(applicationId, userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getCurrencyAndDenomination(Long applicationId, Long userId) throws LoansException {
		try {
			Integer currencyId = loanApplicationRepository.getCurrencyId(applicationId, userId);
			Integer denominationId = loanApplicationRepository.getDenominationId(applicationId, userId);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("currency", CommonDocumentUtils.getCurrency(currencyId));
			jsonObject.put("denomination", CommonDocumentUtils.getDenomination(denominationId));
			return jsonObject;
		} catch (Exception e) {
			logger.error("Error while getting Currency and Denomination Value : ",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public JSONObject isAllowToMoveAhead(Long applicationId, Long userId, Integer nextTabType,
			Long coAppllicantOrGuarantorId) throws LoansException {
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
		int userMainType = CommonUtils.getUserMainType(loanApplicationMaster.getProductId());
		if (CommonUtils.UserMainType.CORPORATE == userMainType) {
			return corporateValidating(loanApplicationMaster, nextTabType, coAppllicantOrGuarantorId);
		} else {
			return retailValidating(loanApplicationMaster, nextTabType, coAppllicantOrGuarantorId);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getBowlCountByProposalId(Long applicationId, Long propsoalId) {
		ApplicationProposalMapping loanApplicationMaster = applicationProposalMappingRepository.getByProposalIdAndApplicationId(propsoalId,applicationId);
		JSONObject response = new JSONObject();
		if (!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster)) {
			response.put("primaryFilledCount", loanApplicationMaster.getPrimaryFilledCount());
			response.put("profileFilledCount", loanApplicationMaster.getDetailsFilledCount());
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getBowlCount(Long applicationId, Long userId) {
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
		JSONObject response = new JSONObject();
		if (!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster)) {
			response.put("primaryFilledCount", loanApplicationMaster.getPrimaryFilledCount());
			response.put("profileFilledCount", loanApplicationMaster.getDetailsFilledCount());
			response.put("finalFilledCount", loanApplicationMaster.getFinalFilledCount());
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	private JSONObject corporateValidating(ApplicationProposalMapping proposalMapping, Integer toTabType,
			Long coAppllicantOrGuarantorId) throws Exception {

		List<Long> coAppIds = null;

		Long coAppCount;

		Boolean isFinalMcqFilled = proposalMapping.getIsFinalMcqFilled() != null ? proposalMapping.getIsFinalMcqFilled() : false;
		Boolean isMcqSkipped = proposalMapping.getIsMcqSkipped() != null ? proposalMapping.getIsMcqSkipped() : false;

		int index = 0;
		final String INVALID_MSG = "Requested data is Invalid.";
		JSONObject response = new JSONObject();
		response.put(MESSAGE_LITERAL, "NA");
		response.put(RESULT_LITERAL, true);

		switch (toTabType) {

		case CommonUtils.TabType.PROFILE_CO_APPLICANT:
			if (CommonUtils.isObjectNullOrEmpty(proposalMapping.getIsApplicantDetailsFilled())
					|| !proposalMapping.getIsApplicantDetailsFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			coAppIds = corporateCoApplicantService.getCoAppIds(proposalMapping.getApplicationId(),
					proposalMapping.getUserId());
			if (CommonUtils.isListNullOrEmpty(coAppIds)) {
				response.put(MESSAGE_LITERAL, INVALID_MSG);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			index = coAppIds.indexOf(coAppllicantOrGuarantorId);
			if (index == -1) {
				response.put(MESSAGE_LITERAL, INVALID_MSG);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			if (index == 1 && (CommonUtils.isObjectNullOrEmpty(proposalMapping.getIsCoApp1DetailsFilled())
					|| !proposalMapping.getIsCoApp1DetailsFilled().booleanValue())) {
					response.put(MESSAGE_LITERAL, "Please CO-APPLICANT-1 details to Move Next !");
					response.put(RESULT_LITERAL, false);
					return response;
			}
			break;

		case CommonUtils.TabType.MATCHES:
			boolean isPrimaryLocked = isPrimaryLocked(proposalMapping.getApplicationId(), proposalMapping.getUserId());
			if (!isPrimaryLocked) {
				response.put(MESSAGE_LITERAL, "Please LOCK PRIMARY DETAILS to See the matches !");
				response.put(RESULT_LITERAL, false);
				return response;
			}
			break;

		case CommonUtils.TabType.CONNECTIONS:
			if (!CommonUtils.isObjectNullOrEmpty(proposalMapping.getProposalId()))
				isPrimaryLocked = isPrimaryLockedByProposalId(proposalMapping.getProposalId(), proposalMapping.getUserId());
			else
				isPrimaryLocked = isPrimaryLocked(proposalMapping.getApplicationId(), proposalMapping.getUserId());
			if (!isPrimaryLocked) {
				response.put(MESSAGE_LITERAL, "Please LOCK PRIMARY DETAILS to See the connections !");
				response.put(RESULT_LITERAL, false);
				return response;
			}
			break;
		case CommonUtils.TabType.PRIMARY_INFORMATION:
			if (CommonUtils.isObjectNullOrEmpty(proposalMapping.getIsApplicantDetailsFilled())
					|| !proposalMapping.getIsApplicantDetailsFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			// Co-Applicant Profile Checking

			coAppCount = corporateCoApplicantRepository.getCoAppCountByApplicationAndUserId(proposalMapping.getApplicationId(),
					proposalMapping.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(coAppCount) || coAppCount > 0) {
				if (coAppCount == 1 && (CommonUtils.isObjectNullOrEmpty(proposalMapping.getIsCoApp1DetailsFilled())
						|| !proposalMapping.getIsCoApp1DetailsFilled().booleanValue())) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
				}

				if (coAppCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(proposalMapping.getIsCoApp1DetailsFilled())
							|| !proposalMapping.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(proposalMapping.getIsCoApp2DetailsFilled())
							|| !proposalMapping.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_2_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}

				}
			}

			break;
		case CommonUtils.TabType.PRIMARY_UPLOAD:
			if (CommonUtils.isObjectNullOrEmpty(proposalMapping.getIsApplicantDetailsFilled())
					|| !proposalMapping.getIsApplicantDetailsFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			if (CommonUtils.isObjectNullOrEmpty(proposalMapping.getIsApplicantPrimaryFilled())
					|| !proposalMapping.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			break;
		case CommonUtils.TabType.FINAL_MCQ:

			// Co-Applicant Profile Checking

			coAppCount = corporateCoApplicantRepository.getCoAppCountByApplicationAndUserId(proposalMapping.getApplicationId(),
					proposalMapping.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(coAppCount) || coAppCount > 0) {
				if (coAppCount == 1 && (CommonUtils.isObjectNullOrEmpty(proposalMapping.getIsCoApp1DetailsFilled())
						|| !proposalMapping.getIsCoApp1DetailsFilled().booleanValue())) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
				}

				if (coAppCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(proposalMapping.getIsCoApp1DetailsFilled())
							|| !proposalMapping.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(proposalMapping.getIsCoApp2DetailsFilled())
							|| !proposalMapping.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_2_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}

				}
			}
			if (!CommonUtils.isObjectNullOrEmpty(proposalMapping.getProposalId()))
				isPrimaryLocked = isPrimaryLockedByProposalId(proposalMapping.getProposalId(), proposalMapping.getUserId());
			else
				isPrimaryLocked = isPrimaryLocked(proposalMapping.getApplicationId(), proposalMapping.getUserId());

			if (!isPrimaryLocked) {
				response.put(MESSAGE_LITERAL, PLEASE_LOCK_PRIMARY_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			if (CommonUtils.isObjectNullOrEmpty(proposalMapping.getIsApplicantDetailsFilled())
					|| !proposalMapping.getIsApplicantDetailsFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			if (CommonUtils.isObjectNullOrEmpty(proposalMapping.getIsApplicantPrimaryFilled())
					|| !proposalMapping.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			/*
			 * if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
			 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL,
			 * PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
			 * response.put(RESULT_LITERAL, false); return response; }
			 */
			break;
		case CommonUtils.TabType.FINAL_INFORMATION:
			if (!CommonUtils.isObjectNullOrEmpty(proposalMapping.getProposalId()))
				isPrimaryLocked = isPrimaryLockedByProposalId(proposalMapping.getProposalId(), proposalMapping.getUserId());
			else
				isPrimaryLocked = isPrimaryLocked(proposalMapping.getApplicationId(), proposalMapping.getUserId());
			if (!isPrimaryLocked) {
				response.put(MESSAGE_LITERAL, PLEASE_LOCK_PRIMARY_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			/*
			 * if (CommonUtils.isObjectNullOrEmpty(applicationMaster.
			 * getIsApplicantDetailsFilled()) ||
			 * !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
			 * response.put(RESULT_LITERAL, false); return response; } if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.
			 * getIsApplicantPrimaryFilled()) ||
			 * !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL,
			 * PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
			 * response.put(RESULT_LITERAL, false); return response; }
			 */
			// if
			// (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled())
			// || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			// response.put(MESSAGE_LITERAL, "Please Fill PRIMARY INFORMATION details to Move Next
			// !");
			// response.put(RESULT_LITERAL, false);
			// return response;
			// }
			if (CommonUtils.BusinessType.EXISTING_BUSINESS.getId().equals(proposalMapping.getBusinessTypeId()) && !isFinalMcqFilled && !isMcqSkipped ) {
					response.put(MESSAGE_LITERAL, PLEASE_FILL_FINAL_MCQ_DETAILS_TO_MOVE_NEXT);
					response.put(RESULT_LITERAL, false);
					return response;
			}
			break;
		case CommonUtils.TabType.FINAL_DPR_UPLOAD:
			if (!CommonUtils.isObjectNullOrEmpty(proposalMapping.getProposalId()))
				isPrimaryLocked = isPrimaryLockedByProposalId(proposalMapping.getProposalId(), proposalMapping.getUserId());
			else
				isPrimaryLocked = isPrimaryLocked(proposalMapping.getApplicationId(), proposalMapping.getUserId());
			logger.info("isPrimaryLocked : "+isPrimaryLocked);
			/*
			 * if (!isPrimaryLocked) { response.put(MESSAGE_LITERAL,
			 * PLEASE_LOCK_PRIMARY_DETAILS_TO_MOVE_NEXT); response.put(RESULT_LITERAL, false);
			 * return response; } if (CommonUtils.isObjectNullOrEmpty(applicationMaster.
			 * getIsApplicantDetailsFilled()) ||
			 * !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
			 * response.put(RESULT_LITERAL, false); return response; }
			 */
			// if
			// (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
			// || !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
			// response.put(MESSAGE_LITERAL, "Please Fill PRIMARY INFORMATION details to Move Next
			// !");
			// response.put(RESULT_LITERAL, false);
			// return response;
			// }
			/*
			 * if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
			 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL,
			 * PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
			 * response.put(RESULT_LITERAL, false); return response; }
			 */
			if (CommonUtils.BusinessType.EXISTING_BUSINESS.getId().equals(proposalMapping.getBusinessTypeId()) && !isFinalMcqFilled && !isMcqSkipped ) {
					response.put(MESSAGE_LITERAL, PLEASE_FILL_FINAL_MCQ_DETAILS_TO_MOVE_NEXT);
					response.put(RESULT_LITERAL, false);
					return response;
			}

			if (CommonUtils.isObjectNullOrEmpty(proposalMapping.getIsApplicantFinalFilled())
					|| !proposalMapping.getIsApplicantFinalFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_FINAL_INFORMATION_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			break;
		case CommonUtils.TabType.FINAL_UPLOAD:
			if (!CommonUtils.isObjectNullOrEmpty(proposalMapping.getProposalId()))
				isPrimaryLocked = isPrimaryLockedByProposalId(proposalMapping.getProposalId(), proposalMapping.getUserId());
			else
				isPrimaryLocked = isPrimaryLocked(proposalMapping.getApplicationId(), proposalMapping.getUserId());
			if (!isPrimaryLocked) {
				response.put(MESSAGE_LITERAL, PLEASE_LOCK_PRIMARY_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			/*
			 * if (CommonUtils.isObjectNullOrEmpty(applicationMaster.
			 * getIsApplicantDetailsFilled()) ||
			 * !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
			 * response.put(RESULT_LITERAL, false); return response; } if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.
			 * getIsApplicantPrimaryFilled()) ||
			 * !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL,
			 * PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
			 * response.put(RESULT_LITERAL, false); return response; }
			 */
			/*
			 * if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
			 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL,
			 * PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
			 * response.put(RESULT_LITERAL, false); return response; }
			 */
			if (CommonUtils.BusinessType.EXISTING_BUSINESS.getId().equals(proposalMapping.getBusinessTypeId()) && !isFinalMcqFilled && !isMcqSkipped ) {
					response.put(MESSAGE_LITERAL, PLEASE_FILL_FINAL_MCQ_DETAILS_TO_MOVE_NEXT);
					response.put(RESULT_LITERAL, false);
					return response;
			}

			if (CommonUtils.isObjectNullOrEmpty(proposalMapping.getIsApplicantFinalFilled())
					|| !proposalMapping.getIsApplicantFinalFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_FINAL_INFORMATION_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			if (CommonUtils.isObjectNullOrEmpty(proposalMapping.getIsFinalDprUploadFilled())
					|| !proposalMapping.getIsFinalDprUploadFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, "Please Fill Financial Model details to Move Next !");
				response.put(RESULT_LITERAL, false);
				return response;
			}

			/*
			 * if (applicationMaster.getProductId() == LoanType.TERM_LOAN.getValue()) { if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsFinalDprUploadFilled(
			 * )) || !applicationMaster.getIsFinalDprUploadFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL, "Please Fill DPR details to Move Next !");
			 * response.put(RESULT_LITERAL, false); return response; } }
			 */
			break;
		default:
			break;
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	private JSONObject corporateValidating(LoanApplicationMaster applicationMaster, Integer toTabType,
			Long coAppllicantOrGuarantorId) throws LoansException {
		List<Long> coAppIds = null;

		Long coAppCount;

		Boolean isFinalMcqFilled = applicationMaster.getIsFinalMcqFilled() != null ? applicationMaster.getIsFinalMcqFilled() : false;
		Boolean isMcqSkipped = applicationMaster.getIsMcqSkipped() != null ? applicationMaster.getIsMcqSkipped() : false;

		int index = 0;
		final String INVALID_MSG = "Requested data is Invalid.";
		JSONObject response = new JSONObject();
		response.put(MESSAGE_LITERAL, "NA");
		response.put(RESULT_LITERAL, true);

		switch (toTabType) {

		case CommonUtils.TabType.PROFILE_CO_APPLICANT:
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			coAppIds = corporateCoApplicantService.getCoAppIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (CommonUtils.isListNullOrEmpty(coAppIds)) {
				response.put(MESSAGE_LITERAL, INVALID_MSG);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			index = coAppIds.indexOf(coAppllicantOrGuarantorId);
			if (index == -1) {
				response.put(MESSAGE_LITERAL, INVALID_MSG);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			if (index == 1 && (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
					|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue())) {
					response.put(MESSAGE_LITERAL, "Please CO-APPLICANT-1 details to Move Next !");
					response.put(RESULT_LITERAL, false);
					return response;
			}
			break;

		case CommonUtils.TabType.MATCHES:
			boolean isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put(MESSAGE_LITERAL, "Please LOCK PRIMARY DETAILS to See the matches !");
				response.put(RESULT_LITERAL, false);
				return response;
			}
			break;

		case CommonUtils.TabType.CONNECTIONS:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put(MESSAGE_LITERAL, "Please LOCK PRIMARY DETAILS to See the connections !");
				response.put(RESULT_LITERAL, false);
				return response;
			}
			break;
		case CommonUtils.TabType.PRIMARY_INFORMATION:
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			// Co-Applicant Profile Checking

			coAppCount = corporateCoApplicantRepository.getCoAppCountByApplicationAndUserId(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(coAppCount) || coAppCount > 0) {
				if (coAppCount == 1 && (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
						|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue())) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
				}

				if (coAppCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_2_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}

				}
			}

			break;
		case CommonUtils.TabType.PRIMARY_UPLOAD:
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
					|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			break;
		case CommonUtils.TabType.FINAL_MCQ:

			// Co-Applicant Profile Checking

			coAppCount = corporateCoApplicantRepository.getCoAppCountByApplicationAndUserId(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(coAppCount) || coAppCount > 0) {
				if (coAppCount == 1 && (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
						|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue())) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
				}

				if (coAppCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_2_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}

				}
			}
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put(MESSAGE_LITERAL, PLEASE_LOCK_PRIMARY_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
					|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			/*
			 * if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
			 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL,
			 * PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
			 * response.put(RESULT_LITERAL, false); return response; }
			 */
			break;
		case CommonUtils.TabType.FINAL_INFORMATION:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put(MESSAGE_LITERAL, PLEASE_LOCK_PRIMARY_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			/*
			 * if (CommonUtils.isObjectNullOrEmpty(applicationMaster.
			 * getIsApplicantDetailsFilled()) ||
			 * !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
			 * response.put(RESULT_LITERAL, false); return response; } if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.
			 * getIsApplicantPrimaryFilled()) ||
			 * !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL,
			 * PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
			 * response.put(RESULT_LITERAL, false); return response; }
			 */
			// if
			// (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled())
			// || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			// response.put(MESSAGE_LITERAL, "Please Fill PRIMARY INFORMATION details to Move Next
			// !");
			// response.put(RESULT_LITERAL, false);
			// return response;
			// }
			if (CommonUtils.BusinessType.EXISTING_BUSINESS.getId().equals(applicationMaster.getBusinessTypeId()) && !isFinalMcqFilled && !isMcqSkipped ) {
					response.put(MESSAGE_LITERAL, PLEASE_FILL_FINAL_MCQ_DETAILS_TO_MOVE_NEXT);
					response.put(RESULT_LITERAL, false);
					return response;
			}
			break;
		case CommonUtils.TabType.FINAL_DPR_UPLOAD:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			logger.info("isPrimaryLocked : "+isPrimaryLocked);
			/*
			 * if (!isPrimaryLocked) { response.put(MESSAGE_LITERAL,
			 * PLEASE_LOCK_PRIMARY_DETAILS_TO_MOVE_NEXT); response.put(RESULT_LITERAL, false);
			 * return response; } if (CommonUtils.isObjectNullOrEmpty(applicationMaster.
			 * getIsApplicantDetailsFilled()) ||
			 * !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
			 * response.put(RESULT_LITERAL, false); return response; }
			 */
			// if
			// (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
			// || !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
			// response.put(MESSAGE_LITERAL, "Please Fill PRIMARY INFORMATION details to Move Next
			// !");
			// response.put(RESULT_LITERAL, false);
			// return response;
			// }
			/*
			 * if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
			 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL,
			 * PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
			 * response.put(RESULT_LITERAL, false); return response; }
			 */
			if (CommonUtils.BusinessType.EXISTING_BUSINESS.getId().equals(applicationMaster.getBusinessTypeId()) && !isFinalMcqFilled && !isMcqSkipped ) {
					response.put(MESSAGE_LITERAL, PLEASE_FILL_FINAL_MCQ_DETAILS_TO_MOVE_NEXT);
					response.put(RESULT_LITERAL, false);
					return response;
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantFinalFilled())
					|| !applicationMaster.getIsApplicantFinalFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_FINAL_INFORMATION_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			break;
		case CommonUtils.TabType.FINAL_UPLOAD:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put(MESSAGE_LITERAL, PLEASE_LOCK_PRIMARY_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			/*
			 * if (CommonUtils.isObjectNullOrEmpty(applicationMaster.
			 * getIsApplicantDetailsFilled()) ||
			 * !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
			 * response.put(RESULT_LITERAL, false); return response; } if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.
			 * getIsApplicantPrimaryFilled()) ||
			 * !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL,
			 * PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
			 * response.put(RESULT_LITERAL, false); return response; }
			 */
			/*
			 * if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
			 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL,
			 * PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
			 * response.put(RESULT_LITERAL, false); return response; }
			 */
			if (CommonUtils.BusinessType.EXISTING_BUSINESS.getId().equals(applicationMaster.getBusinessTypeId()) && !isFinalMcqFilled && !isMcqSkipped ) {
					response.put(MESSAGE_LITERAL, PLEASE_FILL_FINAL_MCQ_DETAILS_TO_MOVE_NEXT);
					response.put(RESULT_LITERAL, false);
					return response;
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantFinalFilled())
					|| !applicationMaster.getIsApplicantFinalFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_FINAL_INFORMATION_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsFinalDprUploadFilled())
					|| !applicationMaster.getIsFinalDprUploadFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, "Please Fill Financial Model details to Move Next !");
				response.put(RESULT_LITERAL, false);
				return response;
			}

			/*
			 * if (applicationMaster.getProductId() == LoanType.TERM_LOAN.getValue()) { if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsFinalDprUploadFilled(
			 * )) || !applicationMaster.getIsFinalDprUploadFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL, "Please Fill DPR details to Move Next !");
			 * response.put(RESULT_LITERAL, false); return response; } }
			 */
			break;
		default:
			break;
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	private JSONObject retailValidating(LoanApplicationMaster applicationMaster, Integer toTabType,
			Long coAppllicantOrGuarantorId) throws LoansException {
		List<Long> coAppIds = null;
		List<Long> guaIds = null;
		Long coAppCount = null;
		Long guarantorCount = null;
		int index = 0;
		final String INVALID_MSG = "Requested data is Invalid.";

		JSONObject response = new JSONObject();
		response.put(MESSAGE_LITERAL, "NA");
		response.put(RESULT_LITERAL, true);
		switch (toTabType) {
		case CommonUtils.TabType.MATCHES:
			boolean isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put(MESSAGE_LITERAL, "Please LOCK PRIMARY DETAILS to See the matches !");
				response.put(RESULT_LITERAL, false);
				return response;
			}
			break;
		case CommonUtils.TabType.CONNECTIONS:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put(MESSAGE_LITERAL, "Please LOCK PRIMARY DETAILS to See the connections !");
				response.put(RESULT_LITERAL, false);
				return response;
			}
			break;
		case CommonUtils.TabType.PROFILE_CO_APPLICANT:
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			coAppIds = coApplicantDetailRepository.getCoAppIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (CommonUtils.isListNullOrEmpty(coAppIds)) {
				response.put(MESSAGE_LITERAL, INVALID_MSG);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			index = coAppIds.indexOf(coAppllicantOrGuarantorId);
			if (index == -1) {
				response.put(MESSAGE_LITERAL, INVALID_MSG);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			if (index == 1 && (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
					|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue())) {
					response.put(MESSAGE_LITERAL, "Please CO-APPLICANT-1 details to Move Next !");
					response.put(RESULT_LITERAL, false);
					return response;
			}
			break;
		case CommonUtils.TabType.PROFILE_GUARANTOR:
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			coAppCount = coApplicantDetailRepository.getCoAppCountByApplicationAndUserId(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(coAppCount) || coAppCount > 0) {
				if (coAppCount == 1 && (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
						|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue())) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
				}
				if (coAppCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_2_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}

				}
			}

			guaIds = guarantorDetailsRepository.getGuarantorIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (CommonUtils.isListNullOrEmpty(guaIds)) {
				response.put(MESSAGE_LITERAL, INVALID_MSG);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			index = guaIds.indexOf(coAppllicantOrGuarantorId);
			if (index == -1) {
				response.put(MESSAGE_LITERAL, INVALID_MSG);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			if (index == 1 && (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
					|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue())) {
					response.put(MESSAGE_LITERAL, PLEASE_GUARANTOR_1_DETAILS_TO_MOVE_NEXT);
					response.put(RESULT_LITERAL, false);
					return response;
			}

			break;
		case CommonUtils.TabType.PRIMARY_INFORMATION:
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			// Co-Applicant Profile Checking
			coAppCount = coApplicantDetailRepository.getCoAppCountByApplicationAndUserId(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(coAppCount) || coAppCount > 0) {
				if (coAppCount == 1 && (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
						|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue())) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
				}

				if (coAppCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_2_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}

				}
			}
			// Guarantor Profile Checking
			guarantorCount = guarantorDetailsRepository
					.getGuarantorCountByApplicationAndUserId(applicationMaster.getId(), applicationMaster.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(guarantorCount) || guarantorCount > 0) {
				if (guarantorCount == 1 && (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
						|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue())) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_GUARANTOR_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
				}

				if (guarantorCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_GUARANTOR_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_GUARANTOR_2_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				}
			}

			break;
		case CommonUtils.TabType.PRIMARY_UPLOAD:
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			// Co-Applicant Profile Checking
			coAppCount = coApplicantDetailRepository.getCoAppCountByApplicationAndUserId(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(coAppCount) || coAppCount > 0) {
				if (coAppCount == 1 && (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
						|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue())) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
				}

				if (coAppCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_2_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}

				}
			}
			// Guarantor Profile Checking
			guarantorCount = guarantorDetailsRepository
					.getGuarantorCountByApplicationAndUserId(applicationMaster.getId(), applicationMaster.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(guarantorCount) || guarantorCount > 0) {
				if (guarantorCount == 1 && (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
						|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue())) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_GUARANTOR_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
				}

				if (guarantorCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_GUARANTOR_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_GUARANTOR_2_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				}
			}

			// Primary Information Tab Validating
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
					|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			break;
		case CommonUtils.TabType.FINAL_INFORMATION:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put(MESSAGE_LITERAL, PLEASE_LOCK_PRIMARY_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			// Co-Applicant Profile Checking
			coAppCount = coApplicantDetailRepository.getCoAppCountByApplicationAndUserId(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(coAppCount) || coAppCount > 0) {
				if (coAppCount == 1 && (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
						|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue())) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
				}

				if (coAppCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_2_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}

				}
			}
			// Guarantor Profile Checking
			guarantorCount = guarantorDetailsRepository
					.getGuarantorCountByApplicationAndUserId(applicationMaster.getId(), applicationMaster.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(guarantorCount) || guarantorCount > 0) {
				if (guarantorCount == 1 && (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
						|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue())) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_GUARANTOR_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
				}

				if (guarantorCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_GUARANTOR_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_GUARANTOR_2_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				}
			}

			// Primary Information Tab Validating
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
					|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			// Primary Upload Tab Validating
			/*
			 * if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
			 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL, "Please Fill PRIMARY UPLOAD details to Move Next !");
			 * response.put(RESULT_LITERAL, false); return response; }
			 */
			break;
		case CommonUtils.TabType.FINAL_CO_APPLICANT:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put(MESSAGE_LITERAL, PLEASE_LOCK_PRIMARY_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			// Co-Applicant Profile Checking
			coAppIds = coApplicantDetailRepository.getCoAppIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (CommonUtils.isListNullOrEmpty(coAppIds)) {
				response.put(MESSAGE_LITERAL, INVALID_MSG);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			index = coAppIds.indexOf(coAppllicantOrGuarantorId);
			if (index == -1) {
				response.put(MESSAGE_LITERAL, INVALID_MSG);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			// CO-APPLICANT Profile Check
			for (int i = 0; i < coAppIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_2_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				}
			}

			// Guarnator Profile Check
			guaIds = guarantorDetailsRepository.getGuarantorIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < guaIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_GUARANTOR_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_GUARANTOR_2_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				}
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
					|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			/*
			 * if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
			 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL, "Please Fill PRIMARY UPLOAD details to Move Next !");
			 * response.put(RESULT_LITERAL, false); return response; }
			 */
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantFinalFilled())
					|| !applicationMaster.getIsApplicantFinalFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_FINAL_INFORMATION_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			// CO-APPLICANT Final Check
			if (index == 1 && (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1FinalFilled())
					|| !applicationMaster.getIsCoApp1FinalFilled().booleanValue())) {
					response.put(MESSAGE_LITERAL, "Please Fill CO-APPLICANT-1 Final Details to Move Next !");
					response.put(RESULT_LITERAL, false);
					return response;
			}

			break;
		case CommonUtils.TabType.FINAL_GUARANTOR:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put(MESSAGE_LITERAL, PLEASE_LOCK_PRIMARY_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			// CO-APPLICANT Profile Check
			coAppIds = coApplicantDetailRepository.getCoAppIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < coAppIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_2_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				}
			}

			// Guarantor Profile Check
			guaIds = guarantorDetailsRepository.getGuarantorIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (CommonUtils.isListNullOrEmpty(guaIds)) {
				response.put(MESSAGE_LITERAL, INVALID_MSG);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			index = guaIds.indexOf(coAppllicantOrGuarantorId);
			if (index == -1) {
				response.put(MESSAGE_LITERAL, INVALID_MSG);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			for (int i = 0; i < guaIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_GUARANTOR_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_GUARANTOR_2_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				}
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
					|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			/*
			 * if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
			 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL, "Please Fill PRIMARY UPLOAD details to Move Next !");
			 * response.put(RESULT_LITERAL, false); return response; }
			 */
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantFinalFilled())
					|| !applicationMaster.getIsApplicantFinalFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_FINAL_INFORMATION_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			// CO-APPLICANT FINAL Check
			coAppIds = coApplicantDetailRepository.getCoAppIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < coAppIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1FinalFilled())
							|| !applicationMaster.getIsCoApp1FinalFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_FINAL_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2FinalFilled())
							|| !applicationMaster.getIsCoApp2FinalFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_2_FINAL_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				}
			}

			// FOR FINAL GUARANTOR
			if (index == 1 && (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1FinalFilled())
					|| !applicationMaster.getIsGuarantor1FinalFilled().booleanValue())) {
					response.put(MESSAGE_LITERAL, "Please Fill GUARANTOR-1 Final Details to Move Next !");
					response.put(RESULT_LITERAL, false);
					return response;
			}

			break;
		// for Final HomeLoan and CarLoan
		case CommonUtils.TabType.FINAL_MCQ:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put(MESSAGE_LITERAL, PLEASE_LOCK_PRIMARY_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			// CO-APPLICANT Profile Check
			coAppIds = coApplicantDetailRepository.getCoAppIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < coAppIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_2_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				}
			}

			// Guarantor Profile Check
			guaIds = guarantorDetailsRepository.getGuarantorIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < guaIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_GUARANTOR_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_GUARANTOR_2_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				}
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
					|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			/*
			 * if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
			 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL, "Please Fill PRIMARY UPLOAD details to Move Next !");
			 * response.put(RESULT_LITERAL, false); return response; }
			 */
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantFinalFilled())
					|| !applicationMaster.getIsApplicantFinalFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_FINAL_INFORMATION_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			// CO-APPLICANT FINAL Check
			for (int i = 0; i < coAppIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1FinalFilled())
							|| !applicationMaster.getIsCoApp1FinalFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_FINAL_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2FinalFilled())
							|| !applicationMaster.getIsCoApp2FinalFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_2_FINAL_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				}
			}

			// FOR FINAL GUARANTOR
			guaIds = guarantorDetailsRepository.getGuarantorIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < guaIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1FinalFilled())
							|| !applicationMaster.getIsGuarantor1FinalFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, "Please Fill GUARANTOR-1 FINAL details to Move Next !");
						response.put(RESULT_LITERAL, false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2FinalFilled())
							|| !applicationMaster.getIsGuarantor2FinalFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, "Please Fill GUARANTOR-2 FINAL details to Move Next !");
						response.put(RESULT_LITERAL, false);
						return response;
					}
				}
			}

			break;

		case CommonUtils.TabType.FINAL_UPLOAD:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put(MESSAGE_LITERAL, PLEASE_LOCK_PRIMARY_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			// CO-APPLICANT Profile Check
			coAppIds = coApplicantDetailRepository.getCoAppIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < coAppIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_2_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				}
			}

			// Guarantor Profile Check
			guaIds = guarantorDetailsRepository.getGuarantorIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < guaIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_GUARANTOR_1_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_GUARANTOR_2_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				}
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
					|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			/*
			 * if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
			 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			 * response.put(MESSAGE_LITERAL, "Please Fill PRIMARY UPLOAD details to Move Next !");
			 * response.put(RESULT_LITERAL, false); return response; }
			 */
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantFinalFilled())
					|| !applicationMaster.getIsApplicantFinalFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_FINAL_INFORMATION_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			// CO-APPLICANT FINAL Check
			for (int i = 0; i < coAppIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1FinalFilled())
							|| !applicationMaster.getIsCoApp1FinalFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_1_FINAL_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2FinalFilled())
							|| !applicationMaster.getIsCoApp2FinalFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, PLEASE_FILL_CO_APPLICANT_2_FINAL_DETAILS_TO_MOVE_NEXT);
						response.put(RESULT_LITERAL, false);
						return response;
					}
				}
			}

			// FOR FINAL GUARANTOR
			guaIds = guarantorDetailsRepository.getGuarantorIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < guaIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1FinalFilled())
							|| !applicationMaster.getIsGuarantor1FinalFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, "Please Fill GUARANTOR-1 FINAL details to Move Next !");
						response.put(RESULT_LITERAL, false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2FinalFilled())
							|| !applicationMaster.getIsGuarantor2FinalFilled().booleanValue()) {
						response.put(MESSAGE_LITERAL, "Please Fill GUARANTOR-2 FINAL details to Move Next !");
						response.put(RESULT_LITERAL, false);
						return response;
					}
				}
			}

			com.capitaworld.service.oneform.enums.LoanType loanType = com.capitaworld.service.oneform.enums.LoanType
					.getById(applicationMaster.getProductId());
			if ((!CommonUtils.isObjectNullOrEmpty(loanType)
					&& (loanType.getId() == CommonUtils.LoanType.HOME_LOAN.getValue()
							|| loanType.getId() == CommonUtils.LoanType.CAR_LOAN.getValue()))
                    && (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsFinalMcqFilled())
                            || !applicationMaster.getIsFinalMcqFilled().booleanValue())) {
					if (loanType.getId() == CommonUtils.LoanType.CAR_LOAN.getValue()) {
						response.put(MESSAGE_LITERAL, "Please Fill CAR-LOAN FINAL details to Move Next !");
					} else {
						response.put(MESSAGE_LITERAL, "Please Fill HOME-LOAN FINAL details to Move Next !");
					}
					response.put(RESULT_LITERAL, false);
					return response;
			}
			break;
		default:
			break;
		}
		return response;
	}
	
	private JSONObject retailValidating(ApplicationProposalMapping applicationProposalMapping, Integer toTabType,
			Long coAppllicantOrGuarantorId) throws LoansException {
		List<Long> coAppIds = null;
		List<Long> guaIds = null;
		Long coAppCount = null;
		Long guarantorCount = null;
		int index = 0;
		final String INVALID_MSG = "Requested data is Invalid.";

		JSONObject response = new JSONObject();
		response.put(MESSAGE_LITERAL, "NA");
		response.put(RESULT_LITERAL, true);
		switch (toTabType) {
		case CommonUtils.TabType.MATCHES:
			boolean isPrimaryLocked = isPrimaryLockedByProposalId(applicationProposalMapping.getProposalId(), applicationProposalMapping.getUserId());
			if (!isPrimaryLocked) {
				response.put(MESSAGE_LITERAL, "Please LOCK PRIMARY DETAILS to See the matches !");
				response.put(RESULT_LITERAL, false);
				return response;
			}
			break;
		case CommonUtils.TabType.CONNECTIONS:
			isPrimaryLocked = isPrimaryLockedByProposalId(applicationProposalMapping.getProposalId(), applicationProposalMapping.getUserId());
			if (!isPrimaryLocked) {
				response.put(MESSAGE_LITERAL, "Please LOCK PRIMARY DETAILS to See the connections !");
				response.put(RESULT_LITERAL, false);
				return response;
			}
			break;

		case CommonUtils.TabType.PRIMARY_INFORMATION:
			if (CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getIsApplicantDetailsFilled())
					|| !applicationProposalMapping.getIsApplicantDetailsFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			
			break;
			
		case CommonUtils.TabType.PRIMARY_UPLOAD:
			if (CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getIsApplicantDetailsFilled())
					|| !applicationProposalMapping.getIsApplicantDetailsFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			// Primary Information Tab Validating
			if (CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getIsApplicantPrimaryFilled())
					|| !applicationProposalMapping.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}
			break;
			
		case CommonUtils.TabType.FINAL_INFORMATION:
			isPrimaryLocked = isPrimaryLockedByProposalId(applicationProposalMapping.getProposalId(), applicationProposalMapping.getUserId());
			if (!isPrimaryLocked) {
				response.put(MESSAGE_LITERAL, PLEASE_LOCK_PRIMARY_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getIsApplicantDetailsFilled())
					|| !applicationProposalMapping.getIsApplicantDetailsFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			// Primary Information Tab Validating
			if (CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getIsApplicantPrimaryFilled())
					|| !applicationProposalMapping.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PRIMARY_INFORMATION_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			break;
		
		case CommonUtils.TabType.FINAL_UPLOAD:
			isPrimaryLocked = isPrimaryLockedByProposalId(applicationProposalMapping.getProposalId(), applicationProposalMapping.getUserId());
			if (!isPrimaryLocked) {
				response.put(MESSAGE_LITERAL, PLEASE_LOCK_PRIMARY_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getIsApplicantDetailsFilled())
					|| !applicationProposalMapping.getIsApplicantDetailsFilled().booleanValue()) {
				response.put(MESSAGE_LITERAL, PLEASE_FILL_PROFILE_DETAILS_TO_MOVE_NEXT);
				response.put(RESULT_LITERAL, false);
				return response;
			}

			com.capitaworld.service.oneform.enums.LoanType loanType = com.capitaworld.service.oneform.enums.LoanType
					.getById(applicationProposalMapping.getProductId());
			if ((!CommonUtils.isObjectNullOrEmpty(loanType)
					&& (loanType.getId() == CommonUtils.LoanType.HOME_LOAN.getValue()
							|| loanType.getId() == CommonUtils.LoanType.CAR_LOAN.getValue()))
                    && (CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getIsFinalMcqFilled())
                            || !applicationProposalMapping.getIsFinalMcqFilled().booleanValue())) {
					if (loanType.getId() == CommonUtils.LoanType.CAR_LOAN.getValue()) {
						response.put(MESSAGE_LITERAL, "Please Fill CAR-LOAN FINAL details to Move Next !");
					} else {
						response.put(MESSAGE_LITERAL, "Please Fill HOME-LOAN FINAL details to Move Next !");
					}
					response.put(RESULT_LITERAL, false);
					return response;
			}
			break;
		
		default:
			break;
		}
		return response;
	}

	@Override
	public String getFsApplicantName(Long applicationId) throws LoansException {
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicationId);
		if (CommonUtils.isObjectNullOrEmpty(applicationMaster))
			return null;

		if (applicationMaster.getProductId() != null && applicationMaster.getBusinessTypeId() != null) {
			if (applicationMaster.getBusinessTypeId().intValue() == CommonUtils.BusinessType.NEW_TO_BUSINESS.getId()
					.intValue()) {
				List<DirectorBackgroundDetail> directorBackgroundDetails = directorBackgroundDetailsRepository.listPromotorBackgroundFromAppId(applicationId);
				DirectorBackgroundDetail directorBackgroundDetail = directorBackgroundDetails.stream()
						.filter(DirectorBackgroundDetail::getIsMainDirector).findAny().orElse(null);
				if (directorBackgroundDetail != null) {
					return directorBackgroundDetail.getDirectorsName();
				}
			} else if (applicationMaster.getBusinessTypeId().intValue() == CommonUtils.BusinessType.RETAIL_PERSONAL_LOAN.getId() ||
					applicationMaster.getBusinessTypeId().intValue() == CommonUtils.BusinessType.RETAIL_HOME_LOAN.getId()) {
				RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository
						.findByApplicationId(applicationId);
				return retailApplicantDetail.getFirstName() + " " + retailApplicantDetail.getLastName();
			}
			else {
				if (CommonUtils.getUserMainType(applicationMaster.getProductId()) == CommonUtils.UserMainType.RETAIL) {
					RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository
							.findByApplicationId(applicationId);
					return retailApplicantDetail.getFirstName() + " " + retailApplicantDetail.getLastName();
				} else if (CommonUtils.getUserMainType(applicationMaster.getProductId()) == CommonUtils.UserMainType.CORPORATE) {
					  CorporateApplicantDetail crApp = corporateApplicantDetailRepository.getCorporateApplicantDetailByApplicationId(applicationId);

					return crApp.getOrganisationName();
				}
			}
		} else {
			if (applicationMaster.getBusinessTypeId() != null && applicationMaster.getBusinessTypeId().intValue() == CommonUtils.BusinessType.NEW_TO_BUSINESS.getId()
					.intValue()) {
				List<DirectorBackgroundDetail> directorBackgroundDetails = directorBackgroundDetailsRepository
						.listPromotorBackgroundFromAppId(applicationId);
				DirectorBackgroundDetail directorBackgroundDetail = directorBackgroundDetails.stream()
						.filter(DirectorBackgroundDetail::getIsMainDirector).findAny().orElse(null);
				if (directorBackgroundDetail != null) {
					return directorBackgroundDetail.getDirectorsName();
				}
			}
			  CorporateApplicantDetail crApp =corporateApplicantDetailRepository.getCorporateApplicantDetailByApplicationId(applicationId);
			return crApp.getOrganisationName();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegisteredUserResponse> getUsersRegisteredLoanDetails(MobileLoanRequest loanRequest) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(loanRequest.getToDate());

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 0);
		logger.info("GetUsersRegisteredLoanDetails, from and todate for admin panel --------> " + cal.getTime());
		loanRequest.setToDate(cal.getTime());
		UserResponse userResponse = userClient.getRegisterdUserList(
				new MobileUserRequest(loanRequest.getUserType(), loanRequest.getFromDate(), loanRequest.getToDate()));

		List userList = (List) userResponse.getData();
		List<RegisteredUserResponse> response = new ArrayList<>();
		for (Object user : userList) {
			RegisteredUserResponse users = null;
			try {
				users = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) user,
						RegisteredUserResponse.class);
			} catch (IOException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			if (CommonUtils.isObjectNullOrEmpty(users)) {
				continue;
			}
			if (CommonUtils.CW_SP_USER_ID.equals(users.getUserId())) {
				continue;
			}
			if (!users.getIsOtpVerified() || !users.getIsEmailVerified()) {
				response.add(users);
				continue;
			}
			if (loanRequest.getUserType().intValue() == CommonUtils.UserType.FUND_SEEKER) {
				List<JSONObject> jsonList = new ArrayList<>();
				List<LoanApplicationMaster> userLoans = loanApplicationRepository.getUserLoans(users.getUserId());
				for (LoanApplicationMaster loanMstr : userLoans) {
					JSONObject obj = new JSONObject();
					obj.put("name", CommonUtils.LoanType.getType(loanMstr.getProductId()));

					String currency = "";
					int userMainType = CommonUtils.getUserMainType(loanMstr.getProductId());
					if (userMainType == CommonUtils.UserMainType.CORPORATE) {
						if (!CommonUtils.isObjectNullOrEmpty(loanMstr.getCurrencyId())
								&& !CommonUtils.isObjectNullOrEmpty(loanMstr.getDenominationId())) {
							currency = CommonDocumentUtils.getCurrency(loanMstr.getCurrencyId());
							currency = currency
									.concat(" in " + CommonDocumentUtils.getDenomination(loanMstr.getDenominationId()));
						}
					} else if (userMainType == CommonUtils.UserMainType.RETAIL) {
						Integer currencyId = retailApplicantDetailRepository.getCurrency(users.getUserId(),
								loanMstr.getId());
						currency = CommonDocumentUtils.getCurrency(currencyId);
					}
					obj.put("product", CommonUtils.getUserMainTypeName(loanMstr.getProductId()));
					obj.put("profileFilled", CommonUtils.getTotalBowlCount(loanMstr.getDetailsFilledCount(),
							loanMstr.getPrimaryFilledCount(), loanMstr.getFinalFilledCount()) / 3);
					obj.put("loanCode", loanMstr.getApplicationCode());
					DecimalFormat decimalFormat = new DecimalFormat("#.##");
					obj.put(CommonUtils.LITERAL_AMOUNT,
							!CommonUtils.isObjectListNull(loanMstr.getAmount())
									? decimalFormat.format(loanMstr.getAmount())
									: 0);
					obj.put("currency", currency);
					obj.put(TENURE_LITERAL, loanMstr.getTenure() != null ? String.valueOf(loanMstr.getTenure() / 12) : null);
					ProposalMappingRequest proposalMappingRequest = new ProposalMappingRequest();
					proposalMappingRequest.setApplicationId(loanMstr.getId());
					ProposalCountResponse proposalCountResponse = null;
					try {
						proposalCountResponse = proposalDetailsClient.proposalCountOfFundSeeker(proposalMappingRequest);
					} catch (Exception e) {
						logger.error("Throw Exception while get matches count for registration user details------------->"+ loanMstr.getId()+" :: ",e);
					}
					if (proposalCountResponse != null && !CommonUtils.isObjectNullOrEmpty(proposalCountResponse)) {
						obj.put("totalMatches", proposalCountResponse.getTotal());
						obj.put("matches", proposalCountResponse.getMatches());
						obj.put("directSent", proposalCountResponse.getSent());
						obj.put("directRecieved", proposalCountResponse.getReceived());
						obj.put("hold", proposalCountResponse.getHold());
						obj.put("reject", proposalCountResponse.getRejected());
						obj.put("approved", proposalCountResponse.getAdvanced());
						obj.put("accept", proposalCountResponse.getPrimary());

					}

					if (!CommonUtils.isObjectNullOrEmpty(loanMstr.getProductId())) {
						int productId = CommonUtils.getUserMainType(loanMstr.getProductId());
						if (productId == CommonUtils.UserMainType.CORPORATE) {
							List<Object[]> corporateDataList = corporateApplicantDetailRepository
									.getByNameAndLastUpdateDate(loanMstr.getUserId(), loanMstr.getId());
							if (!CommonUtils.isListNullOrEmpty(corporateDataList)) {
								Object[] corporateData = corporateDataList.get(0);
								obj.put("oneFormName",
										!CommonUtils.isObjectNullOrEmpty(corporateData[0]) ? corporateData[0].toString()
												: null);
							}
						} else {
							List<Object[]> retailDataList = retailApplicantDetailRepository
									.getNameAndLastUpdatedDate(loanMstr.getUserId(), loanMstr.getId());
							if (!CommonUtils.isListNullOrEmpty(retailDataList)) {
								Object[] retailData = retailDataList.get(0);
								obj.put("oneFormName",
										(!CommonUtils.isObjectNullOrEmpty(retailData[0]) ? retailData[0].toString()
												: null)
												+ " "
												+ (!CommonUtils.isObjectNullOrEmpty(retailData[1])
														? retailData[1].toString()
														: null));
							}
						}
					}

					jsonList.add(obj);
				}
				users.setLoanList(jsonList);
			}
			response.add(users);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminPanelLoanDetailsResponse> getLoanDetailsForAdminPanel(Integer type, MobileLoanRequest loanRequest)
			throws IOException , LoansException {

		List<AdminPanelLoanDetailsResponse> responseList = new ArrayList<>();
		UserResponse userResponse = userClient.getFsIsSelfActiveUserId();
		if (userResponse.getStatus() != HttpStatus.OK.value()) {
			return Collections.emptyList();
		}
		List<LinkedHashMap<String, Object>> dataList = (List<LinkedHashMap<String, Object>>) userResponse.getData();
		List<UsersRequest> listOfObjects = new ArrayList<>(dataList.size());
		for (LinkedHashMap<String, Object> data : dataList) {
			UsersRequest userRequest = MultipleJSONObjectHelper.getObjectFromMap(data, UsersRequest.class);
			if (CommonUtils.CW_SP_USER_ID.equals(userRequest.getId())) {
				continue;
			}
			listOfObjects.add(userRequest);
		}
		List<Long> userIds = new ArrayList<>();
		for (UsersRequest obj : listOfObjects) {
			userIds.add(obj.getId());
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(loanRequest.getToDate());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 0);
		logger.info(GET_LOAN_DETAILS_FOR_ADMIN_PANEL_FROM_AND_TO_DATE_FOR_ADMIN_PANEL + cal.getTime());
		loanRequest.setToDate(cal.getTime());

		List<LoanApplicationMaster> loanApplicationList = loanApplicationRepository.getLoanDetailsForAdminPanel(userIds,
				loanRequest.getFromDate(), loanRequest.getToDate());

		SimpleDateFormat dt = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
		for (LoanApplicationMaster loanApplicationMaster : loanApplicationList) {
			AdminPanelLoanDetailsResponse response = new AdminPanelLoanDetailsResponse();

			UsersRequest usersRequest = listOfObjects.stream()
					.filter(x -> x.getId().equals(loanApplicationMaster.getUserId())).findFirst().orElse(null);
			response.setEmail(!CommonUtils.isObjectNullOrEmpty(usersRequest) ? usersRequest.getEmail() : null);
			response.setApplicationId(loanApplicationMaster.getApplicationCode());
			response.setCreateDate(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getCreatedDate())
					? loanApplicationMaster.getCreatedDate()
					: null);
			response.setProductName(CommonUtils.getUserMainTypeName(loanApplicationMaster.getProductId()));

			LoanType loanType = CommonUtils.LoanType.getType(loanApplicationMaster.getProductId());
			response.setSubProduct(!CommonUtils.isObjectNullOrEmpty(loanType) ? loanType.getName() : "NA");
			response.setAbsoluteAmount(loanApplicationMaster.getAmount());
			response.setAbsoluteDisplayAmount(loanApplicationMaster.getAmount());
			response.setAmounInRuppes(false);
			int userMainType = CommonUtils.getUserMainType(loanApplicationMaster.getProductId());
			if (userMainType == CommonUtils.UserMainType.CORPORATE) {
				if (!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getCurrencyId())
						&& !CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getDenominationId())) {
					response.setCurrency(CommonDocumentUtils.getCurrency(loanApplicationMaster.getCurrencyId()));
					if (loanApplicationMaster.getCurrencyId().equals(Currency.RUPEES.getId())) {
						response.setAmounInRuppes(true);
						Double absoluteAmount = CommonDocumentUtils.convertAmountInAbsolute(
								loanApplicationMaster.getDenominationId(), loanApplicationMaster.getAmount());
						response.setAbsoluteAmount(absoluteAmount);
						response.setAbsoluteDisplayAmount(absoluteAmount);
					}
				}
			} else if (userMainType == CommonUtils.UserMainType.RETAIL) {
				Integer currencyId = retailApplicantDetailRepository.getCurrency(loanApplicationMaster.getUserId(),
						loanApplicationMaster.getId());
				response.setCurrency(CommonDocumentUtils.getCurrency(currencyId));
				if (!CommonUtils.isObjectNullOrEmpty(currencyId) && currencyId.equals(Currency.RUPEES.getId()) ) {
						response.setAmounInRuppes(true);
				}
			}

			if (type == 1) {
				response.setTenure(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getTenure())
						? Double.valueOf((loanApplicationMaster.getTenure() / 12))
						: null);
			} else {
				response.setProfileAndPrimaryLocked(CommonUtils.getYesNo(loanApplicationMaster.getIsPrimaryLocked()));
				response.setFinalLocked(CommonUtils.getYesNo(loanApplicationMaster.getIsFinalLocked()));
				response.setProfileCount(CommonUtils.getBowlCount(loanApplicationMaster.getDetailsFilledCount(), null));
				response.setPrimaryCount(CommonUtils.getBowlCount(loanApplicationMaster.getPrimaryFilledCount(), null));
				response.setFinalCount(CommonUtils.getBowlCount(loanApplicationMaster.getFinalFilledCount(), null));
				response.setTotalCount(CommonUtils.getTotalBowlCount(loanApplicationMaster.getDetailsFilledCount(),
						loanApplicationMaster.getPrimaryFilledCount(), loanApplicationMaster.getFinalFilledCount())
						/ 3);
			}

			if (!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getProductId())) {
				int productId = CommonUtils.getUserMainType(loanApplicationMaster.getProductId());
				if (productId == CommonUtils.UserMainType.CORPORATE) {
					if (type == 1) {
						CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
								.getByApplicationAndUserId(loanApplicationMaster.getUserId(),
										loanApplicationMaster.getId());
						if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
							response.setName(corporateApplicantDetail.getOrganisationName());
							response.setCity(CommonDocumentUtils.getCity(corporateApplicantDetail.getRegisteredCityId(),
									oneFormClient));
							response.setState(CommonDocumentUtils.getState(
									!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredStateId())
											? corporateApplicantDetail.getRegisteredStateId().longValue()
											: null,
									oneFormClient));
							response.setCountry(CommonDocumentUtils.getCountry(
									!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCountryId())
											? corporateApplicantDetail.getRegisteredCountryId().longValue()
											: null,
									oneFormClient));
							response.setConstitution(
									!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getConstitutionId())
											? Constitution.getById(corporateApplicantDetail.getConstitutionId())
													.getValue()
											: null);
						}
					} else {
						List<Object[]> corporateDataList = corporateApplicantDetailRepository
								.getByNameAndLastUpdateDate(loanApplicationMaster.getUserId(),
										loanApplicationMaster.getId());
						if (!CommonUtils.isListNullOrEmpty(corporateDataList)) {
							Object[] corporateData = corporateDataList.get(0);
							response.setName(
									!CommonUtils.isObjectNullOrEmpty(corporateData[0]) ? corporateData[0].toString()
											: null);
							if (!CommonUtils.isObjectNullOrEmpty(corporateData[1])) {
								response.setLastUpdatedDate(corporateData[1].toString());
							}
						}
					}

				} else {
					if (type == 1) {
						RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository
								.getByApplicationAndUserId(loanApplicationMaster.getUserId(),
										loanApplicationMaster.getId());
						if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
							response.setName(
									retailApplicantDetail.getFirstName() + " " + retailApplicantDetail.getLastName());
							response.setCity(CommonDocumentUtils.getCity(retailApplicantDetail.getPermanentCityId(),
									oneFormClient));
							response.setState(CommonDocumentUtils.getState(
									!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentStateId())
											? retailApplicantDetail.getPermanentStateId().longValue()
											: null,
									oneFormClient));
							response.setCountry(CommonDocumentUtils.getCountry(
									!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCountryId())
											? retailApplicantDetail.getPermanentCountryId().longValue()
											: null,
									oneFormClient));
							response.setConstitution(
									!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getGenderId())
											? Gender.getById(retailApplicantDetail.getGenderId()).getValue()
											: null);

							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getBirthDate())) {
								response.setAge(CommonUtils.calculateAge(retailApplicantDetail.getBirthDate()));
							}

						}
					} else {
						List<Object[]> retailDataList = retailApplicantDetailRepository.getNameAndLastUpdatedDate(
								loanApplicationMaster.getUserId(), loanApplicationMaster.getId());
						if (!CommonUtils.isListNullOrEmpty(retailDataList)) {
							Object[] retailData = retailDataList.get(0);
							response.setName(
									(!CommonUtils.isObjectNullOrEmpty(retailData[0]) ? retailData[0].toString() : null)
											+ " "
											+ (!CommonUtils.isObjectNullOrEmpty(retailData[1])
													? retailData[1].toString()
													: null));
							if (!CommonUtils.isObjectNullOrEmpty(retailData[2])) {
								response.setLastUpdatedDate(retailData[2].toString());
							}
						}
					}

				}
			}
			responseList.add(response);
		}
		return responseList;
	}

	// report1
	@Override
	public List<AdminPanelLoanDetailsResponse> getPostLoginForAdminPanel(MobileLoanRequest loanRequest)
			throws IOException, LoansException {
		List<AdminPanelLoanDetailsResponse> responseList = new ArrayList<>();
		UserResponse userResponse = userClient.getFsIsSelfActiveForAdminPanel();
		if (userResponse.getStatus() != HttpStatus.OK.value()) {
			return Collections.emptyList();
		}
		List<LinkedHashMap<String, Object>> dataList = (List<LinkedHashMap<String, Object>>) userResponse.getData();
		List<UsersRequest> listOfObjects = new ArrayList<>(dataList.size());
		for (LinkedHashMap<String, Object> data : dataList) {
			UsersRequest userRequest = MultipleJSONObjectHelper.getObjectFromMap(data, UsersRequest.class);
			if (CommonUtils.CW_SP_USER_ID.equals(userRequest.getId())) {
				continue;
			}
			listOfObjects.add(userRequest);
		}
		List<Long> userIds = new ArrayList<>();
		for (UsersRequest obj : listOfObjects) {
			userIds.add(obj.getId());
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(loanRequest.getToDate());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 0);
		logger.info(GET_LOAN_DETAILS_FOR_ADMIN_PANEL_FROM_AND_TO_DATE_FOR_ADMIN_PANEL + cal.getTime());
		loanRequest.setToDate(cal.getTime());

		List<LoanApplicationMaster> loanApplicationList = loanApplicationRepository.getLoanDetailsForAdminPanel(userIds,
				loanRequest.getFromDate(), loanRequest.getToDate());
		for (LoanApplicationMaster loanApplicationMaster : loanApplicationList) {
			AdminPanelLoanDetailsResponse response = new AdminPanelLoanDetailsResponse();
			UsersRequest usersRequest = listOfObjects.stream()
					.filter(x -> x.getId().equals(loanApplicationMaster.getUserId())).findFirst().orElse(null);
			response.setEmail(
					!CommonUtils.isObjectNullOrEmpty(usersRequest.getEmail()) ? usersRequest.getEmail() : null);
			response.setMobile(
					!CommonUtils.isObjectNullOrEmpty(usersRequest.getMobile()) ? usersRequest.getMobile() : null);
			response.setCampaignCode(!CommonUtils.isObjectNullOrEmpty(usersRequest.getCampaignCode())
					? CampaignCode.getById(Integer.valueOf(usersRequest.getCampaignCode())).toString()
					: DIRECT_LITERAL);
			response.setLastLoginDate(
					!CommonUtils.isObjectNullOrEmpty(usersRequest.getSignUpDate()) ? usersRequest.getSignUpDate()
							: null);
			response.setProductName(CommonUtils.LoanType.getType(loanApplicationMaster.getProductId()).getName());

			response.setProfileCount(CommonUtils.getBowlCount(loanApplicationMaster.getDetailsFilledCount(), null));
			response.setPrimaryCount(CommonUtils.getBowlCount(loanApplicationMaster.getPrimaryFilledCount(), null));
			response.setFinalCount(CommonUtils.getBowlCount(loanApplicationMaster.getFinalFilledCount(), null));
			Double absoluteAmount = CommonDocumentUtils.convertAmountInAbsolute(
					loanApplicationMaster.getDenominationId(), loanApplicationMaster.getAmount());
			response.setAbsoluteDisplayAmount(absoluteAmount);
			response.setCreateDate(loanApplicationMaster.getCreatedDate());
			// pincode
			if (loanApplicationMaster.getProductId() == 1 || loanApplicationMaster.getProductId() == 2
					|| loanApplicationMaster.getProductId() == 15) {//
				CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
						.getByApplicationAndUserId(loanApplicationMaster.getUserId(), loanApplicationMaster.getId());
				if (corporateApplicantDetail != null && !CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredPincode()) ) {
						response.setPincode(corporateApplicantDetail.getRegisteredPincode().toString());
				}
			} else if (loanApplicationMaster.getProductId() == 3 || loanApplicationMaster.getProductId() == 12
					|| loanApplicationMaster.getProductId() == 7 || loanApplicationMaster.getProductId() == 13
					|| loanApplicationMaster.getProductId() == 14) {
				RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository
						.getByApplicationAndUserId(loanApplicationMaster.getUserId(), loanApplicationMaster.getId());
				if (retailApplicantDetail != null) {
					String applicantName = "";
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName())) {
						applicantName += retailApplicantDetail.getFirstName();
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMiddleName())) {
						applicantName += " " + retailApplicantDetail.getMiddleName();
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getLastName())) {
						applicantName += " " + retailApplicantDetail.getLastName();
					}
					response.setName(applicantName);
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentPincode())) {
						response.setPincode(retailApplicantDetail.getPermanentPincode().toString());
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getBirthDate())) {
						response.setAge(CommonUtils.calculateAge(retailApplicantDetail.getBirthDate()));
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMonthlyIncome())) {
						response.setApplicantMonthlyIncome(retailApplicantDetail.getMonthlyIncome());
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getOccupationId())) {
						response.setIncomeType(
								OccupationNature.getById(retailApplicantDetail.getOccupationId()).toString());
					}
				}

			}
			responseList.add(response);
		}
		logger.info(""+responseList);
		return responseList;
	}

	@Override
	public List<AdminPanelLoanDetailsResponse> getPostLoginForAdminPanelOfNotEligibility(MobileLoanRequest loanRequest)
			throws IOException, LoansException {
		List<AdminPanelLoanDetailsResponse> responseList = new ArrayList<>();
		UserResponse userResponse = userClient.getFsIsSelfActiveForAdminPanel();
		if (userResponse.getStatus() != HttpStatus.OK.value()) {
			return Collections.emptyList();
		}
		@SuppressWarnings("unchecked")
		List<LinkedHashMap<String, Object>> dataList = (List<LinkedHashMap<String, Object>>) userResponse.getData();
		List<UsersRequest> listOfObjects = new ArrayList<>(dataList.size());
		for (LinkedHashMap<String, Object> data : dataList) {
			UsersRequest userRequest = MultipleJSONObjectHelper.getObjectFromMap(data, UsersRequest.class);
			if (CommonUtils.CW_SP_USER_ID.equals(userRequest.getId())) {
				continue;
			}
			listOfObjects.add(userRequest);
		}
		List<Long> userIds = new ArrayList<>();
		for (UsersRequest obj : listOfObjects) {
			userIds.add(obj.getId());
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(loanRequest.getToDate());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 0);
		logger.info(GET_LOAN_DETAILS_FOR_ADMIN_PANEL_FROM_AND_TO_DATE_FOR_ADMIN_PANEL + cal.getTime());
		loanRequest.setToDate(cal.getTime());

		List<LoanApplicationMaster> loanApplicationList = loanApplicationRepository.getLoanDetailsForAdminPanel(userIds,
				loanRequest.getFromDate(), loanRequest.getToDate());
		for (LoanApplicationMaster loanApplicationMaster : loanApplicationList) {
			if (loanApplicationMaster.getEligibleAmnt() == null) {
				AdminPanelLoanDetailsResponse response = new AdminPanelLoanDetailsResponse();
				UsersRequest usersRequest = listOfObjects.stream()
						.filter(x -> x.getId().equals(loanApplicationMaster.getUserId())).findFirst().orElse(null);
				response.setEmail(
						!CommonUtils.isObjectNullOrEmpty(usersRequest.getEmail()) ? usersRequest.getEmail() : null);
				response.setMobile(
						!CommonUtils.isObjectNullOrEmpty(usersRequest.getMobile()) ? usersRequest.getMobile() : null);
				response.setCampaignCode(!CommonUtils.isObjectNullOrEmpty(usersRequest.getCampaignCode())
						? CampaignCode.getById(Integer.valueOf(usersRequest.getCampaignCode())).toString()
						: DIRECT_LITERAL);
				response.setLastLoginDate(
						!CommonUtils.isObjectNullOrEmpty(usersRequest.getSignUpDate()) ? usersRequest.getSignUpDate()
								: null);
				response.setProductName(CommonUtils.LoanType.getType(loanApplicationMaster.getProductId()).getName());

				response.setProfileCount(CommonUtils.getBowlCount(loanApplicationMaster.getDetailsFilledCount(), null));
				response.setPrimaryCount(CommonUtils.getBowlCount(loanApplicationMaster.getPrimaryFilledCount(), null));
				response.setFinalCount(CommonUtils.getBowlCount(loanApplicationMaster.getFinalFilledCount(), null));
				Double absoluteAmount = CommonDocumentUtils.convertAmountInAbsolute(
						loanApplicationMaster.getDenominationId(), loanApplicationMaster.getAmount());
				response.setAbsoluteDisplayAmount(absoluteAmount);
				response.setCreateDate(loanApplicationMaster.getCreatedDate());
				// pincode
				if (loanApplicationMaster.getProductId() == 1 || loanApplicationMaster.getProductId() == 2
						|| loanApplicationMaster.getProductId() == 15) {//
					CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
							.getByApplicationAndUserId(loanApplicationMaster.getUserId(),
									loanApplicationMaster.getId());
					if (corporateApplicantDetail != null && !CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredPincode()) ) {
							response.setPincode(corporateApplicantDetail.getRegisteredPincode().toString());
					}
				} else if (loanApplicationMaster.getProductId() == 3 || loanApplicationMaster.getProductId() == 12
						|| loanApplicationMaster.getProductId() == 7 || loanApplicationMaster.getProductId() == 13
						|| loanApplicationMaster.getProductId() == 14) {
					RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository
							.getByApplicationAndUserId(loanApplicationMaster.getUserId(),
									loanApplicationMaster.getId());
					if (retailApplicantDetail != null) {
						String applicantName = "";
						if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName())) {
							applicantName += retailApplicantDetail.getFirstName();
						}
						if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMiddleName())) {
							applicantName += " " + retailApplicantDetail.getMiddleName();
						}
						if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getLastName())) {
							applicantName += " " + retailApplicantDetail.getLastName();
						}
						response.setName(applicantName);
						if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentPincode())) {
							response.setPincode(retailApplicantDetail.getPermanentPincode().toString());
						}
						if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getBirthDate())) {
							response.setAge(CommonUtils.calculateAge(retailApplicantDetail.getBirthDate()));
						}
						if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMonthlyIncome())) {
							response.setApplicantMonthlyIncome(retailApplicantDetail.getMonthlyIncome());
						}
						if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getOccupationId())) {
							response.setIncomeType(
									OccupationNature.getById(retailApplicantDetail.getOccupationId()).toString());
						}
					}
					List<CoApplicantDetail> coApplicantDetails = coApplicantDetailRepository
							.getList(loanApplicationMaster.getId(), loanApplicationMaster.getUserId());
					if (coApplicantDetails != null && !coApplicantDetails.isEmpty()) {
						String coApplicantName = "";
						if (coApplicantDetails.size() > 0) {
							CoApplicantDetail coApplicantDetail = coApplicantDetails.get(0);
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getFirstName())) {
								coApplicantName += coApplicantDetail.getFirstName();
							}
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getMiddleName())) {
								coApplicantName += " " + coApplicantDetail.getMiddleName();
							}
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getLastName())) {
								coApplicantName += " " + coApplicantDetail.getLastName();
							}
							response.setCoApplicant1Name(coApplicantName);
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBirthDate())) {
								response.setCoApplicant1Age(CommonUtils.calculateAge(coApplicantDetail.getBirthDate()));
							}
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getMonthlyIncome())) {
								response.setCoApplicant1MonthlyIncome(coApplicantDetail.getMonthlyIncome());
							}
						}
						if (coApplicantDetails.size() > 2) {
							CoApplicantDetail coApplicantDetail1 = coApplicantDetails.get(1);
							coApplicantName = "";
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getFirstName())) {
								coApplicantName += coApplicantDetail1.getFirstName();
							}
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getMiddleName())) {
								coApplicantName += " " + coApplicantDetail1.getMiddleName();
							}
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getLastName())) {
								coApplicantName += " " + coApplicantDetail1.getLastName();
							}
							response.setCoApplicant2Name(coApplicantName);
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getBirthDate())) {
								response.setCoApplicant2Age(
										CommonUtils.calculateAge(coApplicantDetail1.getBirthDate()));
							}
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getMonthlyIncome())) {
								response.setCoApplicant2MonthlyIncome(coApplicantDetail1.getMonthlyIncome());
							}
						}
					}
				}
				responseList.add(response);
			}
		}
		logger.info(""+responseList);
		return responseList;
	}

	@Override
	public List<AdminPanelLoanDetailsResponse> getPostLoginForAdminPanelOfEligibility(MobileLoanRequest loanRequest)
			throws IOException, LoansException {
		List<AdminPanelLoanDetailsResponse> responseList = new ArrayList<>();
		UserResponse userResponse = userClient.getFsIsSelfActiveForAdminPanel();
		if (userResponse.getStatus() != HttpStatus.OK.value()) {
			return Collections.emptyList();
		}
		List<LinkedHashMap<String, Object>> dataList = (List<LinkedHashMap<String, Object>>) userResponse.getData();
		List<UsersRequest> listOfObjects = new ArrayList<>(dataList.size());
		for (LinkedHashMap<String, Object> data : dataList) {
			UsersRequest userRequest = MultipleJSONObjectHelper.getObjectFromMap(data, UsersRequest.class);
			if (CommonUtils.CW_SP_USER_ID.equals(userRequest.getId())) {
				continue;
			}
			listOfObjects.add(userRequest);
		}
		List<Long> userIds = new ArrayList<>();
		for (UsersRequest obj : listOfObjects) {
			userIds.add(obj.getId());
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(loanRequest.getToDate());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 0);
		logger.info(GET_LOAN_DETAILS_FOR_ADMIN_PANEL_FROM_AND_TO_DATE_FOR_ADMIN_PANEL + cal.getTime());
		loanRequest.setToDate(cal.getTime());

		List<LoanApplicationMaster> loanApplicationList = loanApplicationRepository.getLoanDetailsForAdminPanel(userIds,
				loanRequest.getFromDate(), loanRequest.getToDate());
		SimpleDateFormat dt = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
		for (LoanApplicationMaster loanApplicationMaster : loanApplicationList) {
			// code for got eligibility
			if (loanApplicationMaster.getEligibleAmnt() != null && CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getIsFinalLocked()) ) {
					AdminPanelLoanDetailsResponse response = new AdminPanelLoanDetailsResponse();
					UsersRequest usersRequest = listOfObjects.stream()
							.filter(x -> x.getId().equals(loanApplicationMaster.getUserId())).findFirst().orElse(null);
					response.setEmail(
							!CommonUtils.isObjectNullOrEmpty(usersRequest.getEmail()) ? usersRequest.getEmail() : null);
					response.setMobile(
							!CommonUtils.isObjectNullOrEmpty(usersRequest.getMobile()) ? usersRequest.getMobile()
									: null);
					response.setCampaignCode(!CommonUtils.isObjectNullOrEmpty(usersRequest.getCampaignCode())
							? CampaignCode.getById(Integer.valueOf(usersRequest.getCampaignCode())).toString()
							: DIRECT_LITERAL);
					response.setLastLoginDate(!CommonUtils.isObjectNullOrEmpty(usersRequest.getSignUpDate())
							? usersRequest.getSignUpDate()
							: null);
					response.setProductName(
							CommonUtils.LoanType.getType(loanApplicationMaster.getProductId()).getName());

					response.setProfileCount(
							CommonUtils.getBowlCount(loanApplicationMaster.getDetailsFilledCount(), null));
					response.setPrimaryCount(
							CommonUtils.getBowlCount(loanApplicationMaster.getPrimaryFilledCount(), null));
					response.setFinalCount(CommonUtils.getBowlCount(loanApplicationMaster.getFinalFilledCount(), null));
					Double absoluteAmount = CommonDocumentUtils.convertAmountInAbsolute(
							loanApplicationMaster.getDenominationId(), loanApplicationMaster.getAmount());
					response.setAbsoluteDisplayAmount(absoluteAmount);
					response.setCreateDate(loanApplicationMaster.getCreatedDate());
					// pincode
					if (loanApplicationMaster.getProductId() == 1 || loanApplicationMaster.getProductId() == 2
							|| loanApplicationMaster.getProductId() == 15) {//
						CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
								.getByApplicationAndUserId(loanApplicationMaster.getUserId(),
										loanApplicationMaster.getId());
						if (corporateApplicantDetail != null && !CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredPincode()) ) {
								response.setPincode(corporateApplicantDetail.getRegisteredPincode().toString());
						}
					} else if (loanApplicationMaster.getProductId() == 3 || loanApplicationMaster.getProductId() == 12
							|| loanApplicationMaster.getProductId() == 7 || loanApplicationMaster.getProductId() == 13
							|| loanApplicationMaster.getProductId() == 14) {
						RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository
								.getByApplicationAndUserId(loanApplicationMaster.getUserId(),
										loanApplicationMaster.getId());
						if (retailApplicantDetail != null) {
							String applicantName = "";
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName())) {
								applicantName += retailApplicantDetail.getFirstName();
							}
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMiddleName())) {
								applicantName += " " + retailApplicantDetail.getMiddleName();
							}
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getLastName())) {
								applicantName += " " + retailApplicantDetail.getLastName();
							}
							response.setName(applicantName);
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentPincode())) {
								response.setPincode(retailApplicantDetail.getPermanentPincode().toString());
							}
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getBirthDate())) {
								response.setAge(CommonUtils.calculateAge(retailApplicantDetail.getBirthDate()));
							}
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMonthlyIncome())) {
								response.setApplicantMonthlyIncome(retailApplicantDetail.getMonthlyIncome());
							}
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getOccupationId())) {
								response.setIncomeType(
										OccupationNature.getById(retailApplicantDetail.getOccupationId()).toString());
							}
						}
						responseList.add(response);
					}

			}
		}
		logger.info(""+responseList);
		return responseList;
	}

	@Override
	public List<AdminPanelLoanDetailsResponse> getPostLoginForAdminPanelOfFinalLockedRejectedByUbi(
			MobileLoanRequest loanRequest) throws IOException, LoansException {

		List<List<Long>> master = organizationReportsService.getApplicationIdAndUserId();
		List<Long> userId = null;
		List<Long> applicationId = null;
		if (master != null) {
			applicationId = master.get(0);
			userId = master.get(1);
		}
		UsersRequest uRequest = new UsersRequest();
		uRequest.setIds(userId);
		List<AdminPanelLoanDetailsResponse> responseList = new ArrayList<>();
		UserResponse userResponse = userClient.getDetailsOfUsersForAdminPanel(uRequest);
		if (userResponse.getStatus() != HttpStatus.OK.value()) {
			return Collections.emptyList();
		}
		List<LinkedHashMap<String, Object>> dataList = (List<LinkedHashMap<String, Object>>) userResponse.getData();
		List<UsersRequest> listOfObjects = new ArrayList<>(dataList.size());
		for (LinkedHashMap<String, Object> data : dataList) {
			UsersRequest userRequest = MultipleJSONObjectHelper.getObjectFromMap(data, UsersRequest.class);
			if (CommonUtils.CW_SP_USER_ID.equals(userRequest.getId())) {
				continue;
			}
			listOfObjects.add(userRequest);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(loanRequest.getToDate());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 0);
		logger.info(GET_LOAN_DETAILS_FOR_ADMIN_PANEL_FROM_AND_TO_DATE_FOR_ADMIN_PANEL + cal.getTime());
		loanRequest.setToDate(cal.getTime());

		List<LoanApplicationMaster> loanApplicationList = loanApplicationRepository.getLoanDetailsForAdminPanelUbi(
				userId, applicationId, loanRequest.getFromDate(), loanRequest.getToDate());
		SimpleDateFormat dt = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
		for (LoanApplicationMaster loanApplicationMaster : loanApplicationList) {
			AdminPanelLoanDetailsResponse response = new AdminPanelLoanDetailsResponse();
			UsersRequest usersRequest = listOfObjects.stream()
					.filter(x -> x.getId().equals(loanApplicationMaster.getUserId())).findFirst().orElse(null);
			response.setEmail(
					!CommonUtils.isObjectNullOrEmpty(usersRequest.getEmail()) ? usersRequest.getEmail() : null);
			response.setMobile(
					!CommonUtils.isObjectNullOrEmpty(usersRequest.getMobile()) ? usersRequest.getMobile() : null);
			response.setCampaignCode(!CommonUtils.isObjectNullOrEmpty(usersRequest.getCampaignCode())
					? CampaignCode.getById(Integer.valueOf(usersRequest.getCampaignCode())).toString()
					: DIRECT_LITERAL);
			response.setLastLoginDate(
					!CommonUtils.isObjectNullOrEmpty(usersRequest.getSignUpDate()) ? usersRequest.getSignUpDate()
							: null);
			response.setProductName(CommonUtils.LoanType.getType(loanApplicationMaster.getProductId()).getName());

			response.setProfileCount(CommonUtils.getBowlCount(loanApplicationMaster.getDetailsFilledCount(), null));
			response.setPrimaryCount(CommonUtils.getBowlCount(loanApplicationMaster.getPrimaryFilledCount(), null));
			response.setFinalCount(CommonUtils.getBowlCount(loanApplicationMaster.getFinalFilledCount(), null));
			Double absoluteAmount = CommonDocumentUtils.convertAmountInAbsolute(
					loanApplicationMaster.getDenominationId(), loanApplicationMaster.getAmount());
			response.setAbsoluteDisplayAmount(absoluteAmount);
			response.setCreateDate(loanApplicationMaster.getCreatedDate());
			// pincode
			if (loanApplicationMaster.getProductId() == 1 || loanApplicationMaster.getProductId() == 2
					|| loanApplicationMaster.getProductId() == 15) {//
				CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
						.getByApplicationAndUserId(loanApplicationMaster.getUserId(), loanApplicationMaster.getId());
				if (corporateApplicantDetail != null && !CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredPincode()) ) {
						response.setPincode(corporateApplicantDetail.getRegisteredPincode().toString());
				}
			} else if (loanApplicationMaster.getProductId() == 3 || loanApplicationMaster.getProductId() == 12
					|| loanApplicationMaster.getProductId() == 7 || loanApplicationMaster.getProductId() == 13
					|| loanApplicationMaster.getProductId() == 14) {
				RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository
						.getByApplicationAndUserId(loanApplicationMaster.getUserId(), loanApplicationMaster.getId());
				if (retailApplicantDetail != null) {
					String applicantName = "";
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName())) {
						applicantName += retailApplicantDetail.getFirstName();
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMiddleName())) {
						applicantName += " " + retailApplicantDetail.getMiddleName();
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getLastName())) {
						applicantName += " " + retailApplicantDetail.getLastName();
					}
					response.setName(applicantName);
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentPincode())) {
						response.setPincode(retailApplicantDetail.getPermanentPincode().toString());
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getBirthDate())) {
						response.setAge(CommonUtils.calculateAge(retailApplicantDetail.getBirthDate()));
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMonthlyIncome())) {
						response.setApplicantMonthlyIncome(retailApplicantDetail.getMonthlyIncome());
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getOccupationId())) {
						response.setIncomeType(
								OccupationNature.getById(retailApplicantDetail.getOccupationId()).toString());
					}
				}
				List<CoApplicantDetail> coApplicantDetails = coApplicantDetailRepository
						.getList(loanApplicationMaster.getId(), loanApplicationMaster.getUserId());
				if (coApplicantDetails != null && !coApplicantDetails.isEmpty()) {
					String coApplicantName = "";
					if (coApplicantDetails.size() > 0) {
						CoApplicantDetail coApplicantDetail = coApplicantDetails.get(0);
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getFirstName())) {
							coApplicantName += coApplicantDetail.getFirstName();
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getMiddleName())) {
							coApplicantName += " " + coApplicantDetail.getMiddleName();
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getLastName())) {
							coApplicantName += " " + coApplicantDetail.getLastName();
						}
						response.setCoApplicant1Name(coApplicantName);
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBirthDate())) {
							response.setCoApplicant1Age(CommonUtils.calculateAge(coApplicantDetail.getBirthDate()));
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getMonthlyIncome())) {
							response.setCoApplicant1MonthlyIncome(coApplicantDetail.getMonthlyIncome());
						}
					}
					if (coApplicantDetails.size() > 2) {
						CoApplicantDetail coApplicantDetail1 = coApplicantDetails.get(1);
						coApplicantName = "";
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getFirstName())) {
							coApplicantName += coApplicantDetail1.getFirstName();
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getMiddleName())) {
							coApplicantName += " " + coApplicantDetail1.getMiddleName();
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getLastName())) {
							coApplicantName += " " + coApplicantDetail1.getLastName();
						}
						response.setCoApplicant2Name(coApplicantName);
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getBirthDate())) {
							response.setCoApplicant2Age(CommonUtils.calculateAge(coApplicantDetail1.getBirthDate()));
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getMonthlyIncome())) {
							response.setCoApplicant2MonthlyIncome(coApplicantDetail1.getMonthlyIncome());
						}
					}
				}
			}
			responseList.add(response);

		}
		logger.info(""+responseList);
		return responseList;
	}

	@Override
	public List<AdminPanelLoanDetailsResponse> getPostLoginForAdminPanelOfApprovedByUbi(MobileLoanRequest loanRequest)
			throws IOException, LoansException {
		List<ReportResponse> master = organizationReportsService.getFpProductMappingId();
		List<Long> userId = new ArrayList<>();
		List<Long> applicationId = new ArrayList<>();
		for (ReportResponse reportResponse : master) {
			applicationId.add(reportResponse.getApplicationId());
			userId.add(reportResponse.getUserId());
		}
		UsersRequest uRequest = new UsersRequest();
		uRequest.setIds(userId);
		List<AdminPanelLoanDetailsResponse> responseList = new ArrayList<>();
		UserResponse userResponse = userClient.getDetailsOfUsersForAdminPanel(uRequest);
		if (userResponse.getStatus() != HttpStatus.OK.value()) {
			return Collections.emptyList();
		}
		List<LinkedHashMap<String, Object>> dataList = (List<LinkedHashMap<String, Object>>) userResponse.getData();
		List<UsersRequest> listOfObjects = new ArrayList<>(dataList.size());
		for (LinkedHashMap<String, Object> data : dataList) {
			UsersRequest userRequest = MultipleJSONObjectHelper.getObjectFromMap(data, UsersRequest.class);
			if (CommonUtils.CW_SP_USER_ID.equals(userRequest.getId())) {
				continue;
			}
			listOfObjects.add(userRequest);
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(loanRequest.getToDate());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 0);
		logger.info(GET_LOAN_DETAILS_FOR_ADMIN_PANEL_FROM_AND_TO_DATE_FOR_ADMIN_PANEL + cal.getTime());
		loanRequest.setToDate(cal.getTime());

		List<LoanApplicationMaster> loanApplicationList = loanApplicationRepository.getLoanDetailsForAdminPanelUbi(
				userId, applicationId, loanRequest.getFromDate(), loanRequest.getToDate());
		SimpleDateFormat dt = new SimpleDateFormat(DATE_FORMAT_YYYY_MM_DD_HH_MM_SS);
		for (LoanApplicationMaster loanApplicationMaster : loanApplicationList) {
			AdminPanelLoanDetailsResponse response = new AdminPanelLoanDetailsResponse();
			UsersRequest usersRequest = listOfObjects.stream()
					.filter(x -> x.getId().equals(loanApplicationMaster.getUserId())).findFirst().orElse(null);
			response.setEmail(
					!CommonUtils.isObjectNullOrEmpty(usersRequest.getEmail()) ? usersRequest.getEmail() : null);
			response.setMobile(
					!CommonUtils.isObjectNullOrEmpty(usersRequest.getMobile()) ? usersRequest.getMobile() : null);
			response.setCampaignCode(!CommonUtils.isObjectNullOrEmpty(usersRequest.getCampaignCode())
					? CampaignCode.getById(Integer.valueOf(usersRequest.getCampaignCode())).toString()
					: DIRECT_LITERAL);
			response.setLastLoginDate(
					!CommonUtils.isObjectNullOrEmpty(usersRequest.getSignUpDate()) ? usersRequest.getSignUpDate()
							: null);
			response.setProductName(CommonUtils.LoanType.getType(loanApplicationMaster.getProductId()).getName());
			ReportResponse reportResponse = master.stream()
					.filter(x -> x.getApplicationId().equals(loanApplicationMaster.getId())).findFirst().orElse(null);
			response.setLastApprovedDate(logDetailsRepository.getDateByADFForAdminPanel(loanApplicationMaster.getId(),
					reportResponse.getFpProductId()));
			response.setProfileCount(CommonUtils.getBowlCount(loanApplicationMaster.getDetailsFilledCount(), null));
			response.setPrimaryCount(CommonUtils.getBowlCount(loanApplicationMaster.getPrimaryFilledCount(), null));
			response.setFinalCount(CommonUtils.getBowlCount(loanApplicationMaster.getFinalFilledCount(), null));
			Double absoluteAmount = CommonDocumentUtils.convertAmountInAbsolute(
					loanApplicationMaster.getDenominationId(), loanApplicationMaster.getAmount());
			response.setAbsoluteDisplayAmount(absoluteAmount);
			response.setCreateDate(loanApplicationMaster.getCreatedDate());
			// pincode
			if (loanApplicationMaster.getProductId() == 1 || loanApplicationMaster.getProductId() == 2
					|| loanApplicationMaster.getProductId() == 15) {//
				CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
						.getByApplicationAndUserId(loanApplicationMaster.getUserId(), loanApplicationMaster.getId());
				if (corporateApplicantDetail != null && !CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredPincode()) ) {
						response.setPincode(corporateApplicantDetail.getRegisteredPincode().toString());
				}
			} else if (loanApplicationMaster.getProductId() == 3 || loanApplicationMaster.getProductId() == 12
					|| loanApplicationMaster.getProductId() == 7 || loanApplicationMaster.getProductId() == 13
					|| loanApplicationMaster.getProductId() == 14) {
				RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository
						.getByApplicationAndUserId(loanApplicationMaster.getUserId(), loanApplicationMaster.getId());
				if (retailApplicantDetail != null) {
					String applicantName = "";
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName())) {
						applicantName += retailApplicantDetail.getFirstName();
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMiddleName())) {
						applicantName += " " + retailApplicantDetail.getMiddleName();
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getLastName())) {
						applicantName += " " + retailApplicantDetail.getLastName();
					}
					response.setName(applicantName);
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentPincode())) {
						response.setPincode(retailApplicantDetail.getPermanentPincode().toString());
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getBirthDate())) {
						response.setAge(CommonUtils.calculateAge(retailApplicantDetail.getBirthDate()));
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMonthlyIncome())) {
						response.setApplicantMonthlyIncome(retailApplicantDetail.getMonthlyIncome());
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getOccupationId())) {
						response.setIncomeType(
								OccupationNature.getById(retailApplicantDetail.getOccupationId()).toString());
					}
				}
				List<CoApplicantDetail> coApplicantDetails = coApplicantDetailRepository
						.getList(loanApplicationMaster.getId(), loanApplicationMaster.getUserId());
				if (coApplicantDetails != null && !coApplicantDetails.isEmpty()) {
					String coApplicantName = "";
					if (coApplicantDetails.size() > 0) {
						CoApplicantDetail coApplicantDetail = coApplicantDetails.get(0);
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getFirstName())) {
							coApplicantName += coApplicantDetail.getFirstName();
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getMiddleName())) {
							coApplicantName += " " + coApplicantDetail.getMiddleName();
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getLastName())) {
							coApplicantName += " " + coApplicantDetail.getLastName();
						}
						response.setCoApplicant1Name(coApplicantName);
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBirthDate())) {
							response.setCoApplicant1Age(CommonUtils.calculateAge(coApplicantDetail.getBirthDate()));
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getMonthlyIncome())) {
							response.setCoApplicant1MonthlyIncome(coApplicantDetail.getMonthlyIncome());
						}
					}
					if (coApplicantDetails.size() > 2) {
						CoApplicantDetail coApplicantDetail1 = coApplicantDetails.get(1);
						coApplicantName = "";
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getFirstName())) {
							coApplicantName += coApplicantDetail1.getFirstName();
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getMiddleName())) {
							coApplicantName += " " + coApplicantDetail1.getMiddleName();
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getLastName())) {
							coApplicantName += " " + coApplicantDetail1.getLastName();
						}
						response.setCoApplicant2Name(coApplicantName);
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getBirthDate())) {
							response.setCoApplicant2Age(CommonUtils.calculateAge(coApplicantDetail1.getBirthDate()));
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getMonthlyIncome())) {
							response.setCoApplicant2MonthlyIncome(coApplicantDetail1.getMonthlyIncome());
						}
					}
				}
			}
			responseList.add(response);

		}
		logger.info(""+responseList);
		return responseList;
	}

	@Override
	public List<ChatDetails> getChatListByApplicationId(Long applicationId) {
		ProposalMappingRequest mappingRequest = new ProposalMappingRequest();
		mappingRequest.setFpProductId(applicationId);
		try {
			List<LinkedHashMap<String, Object>> mappingRequestList = (List<LinkedHashMap<String, Object>>) proposalDetailsClient
					.getFundProviderChatList(mappingRequest).getDataList();
			if (!CommonUtils.isListNullOrEmpty(mappingRequestList)) {
				List<ChatDetails> chatDetailList = new ArrayList<ChatDetails>(mappingRequestList.size());
				for (LinkedHashMap<String, Object> linkedHashMap : mappingRequestList) {
					try {
						ChatDetails chatDetails = new ChatDetails();
						ProposalMappingRequest proposalMappingRequest = MultipleJSONObjectHelper.getObjectFromMap(
								(LinkedHashMap<String, Object>) linkedHashMap, ProposalMappingRequest.class);
						Object[] object = getApplicationDetailsById(proposalMappingRequest.getApplicationId());
						DashboardProfileResponse dashboardProfileResponse = dashboardService.getBasicProfileInfo(
								proposalMappingRequest.getApplicationId(), (Long) object[0], false);
						chatDetails.setProposalId(proposalMappingRequest.getId());
						chatDetails.setAppAndFpMappingId(proposalMappingRequest.getApplicationId());
						chatDetails
								.setIsAppFpProdActive(isApplicationIdActive(proposalMappingRequest.getApplicationId()));
						chatDetails.setName(dashboardProfileResponse.getName());
						List<LinkedHashMap<String, Object>> detailsResponseList = (List<LinkedHashMap<String, Object>>) corporateUploadService
								.getProfilePic(proposalMappingRequest.getApplicationId(),
										getProfilePicKeyByProductId(dashboardProfileResponse.getProductId()),
										DocumentAlias.UERT_TYPE_APPLICANT)
								.getDataList();
						if (!CommonUtils.isListNullOrEmpty(detailsResponseList)) {
							StorageDetailsResponse storageDetailsResponse = MultipleJSONObjectHelper.getObjectFromMap(
									(LinkedHashMap<String, Object>) detailsResponseList.get(0),
									StorageDetailsResponse.class);
							chatDetails.setProfile(storageDetailsResponse.getFilePath());
						}
						chatDetailList.add(chatDetails);
					} catch (IOException e) {
						logger.error(CommonUtils.EXCEPTION,e);
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
				}
				return chatDetailList;
			}
		} catch (MatchException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		return Collections.emptyList();
	}

	public Long getProfilePicKeyByProductId(Integer id) {
		switch (id) {
		case 1:// WORKING CAPITAL
			return DocumentAlias.WORKING_CAPITAL_PROFIEL_PICTURE;
		case 2:// Term CAPITAL
			return DocumentAlias.TERM_LOAN_PROFIEL_PICTURE;
		case 3:// HOME LOAN
			return DocumentAlias.HOME_LOAN_PROFIEL_PICTURE;
		case 7:// PERSONAL LOAN
			return DocumentAlias.PERSONAL_LOAN_PROFIEL_PICTURE;
		case 12:// CAR_LOAN
			return DocumentAlias.CAR_LOAN_PROFIEL_PICTURE;
		case 13:// LOAN_AGAINST_PROPERTY
			return DocumentAlias.LAP_LOAN_PROFIEL_PICTURE;
		case 14:// LAS_LOAN_PROFIEL_PICTURE
			return DocumentAlias.LAS_LOAN_PROFIEL_PICTURE;
		case 15:// UNSECURED_LOAN_PROFIEL_PICTURE
			return DocumentAlias.UNSECURED_LOAN_PROFIEL_PICTURE;

		default:
			return null;
		}
	}

	@Override
	public List<FpProfileBasicDetailRequest> getFpNegativeList(Long applicationId) {
		try {
			LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicationId);
			if (!CommonUtils.isObjectNullOrEmpty(applicationMaster)) {
				List<Long> fpUserIdList = productMasterRepository
						.getUserIdListByProductId(applicationMaster.getProductId());
				if (!CommonUtils.isListNullOrEmpty(fpUserIdList)) {
					// get fp name from user client

					UserResponse userResponse = userClient.getFPNameListByUserId(fpUserIdList);
					if (userResponse != null && userResponse.getData() != null) {
						return  (List<FpProfileBasicDetailRequest>) userResponse.getData();
					}

				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		return Collections.emptyList();
	}

	@Override
	public void saveSuggestionList(ProposalList proposalList) {
		try {

			// change proposal status
			if (!CommonUtils.isListNullOrEmpty(proposalList.getSuggetionIds())) {
				proposalDetailsClient.saveSuggestionList(proposalList.getSuggetionIds());
			}

		} catch (Exception e) {
			logger.error("Exception in saveSuggestionList : ",e);
		}
	}

	public EkycResponse getDetailsForEkycAuthentication(EkycRequest ekycRequest) {
		EkycResponse ekycResponse = new EkycResponse();
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(ekycRequest.getApplicationId());

		if (CommonUtils.getUserMainType(loanApplicationMaster.getProductId()) == CommonUtils.UserMainType.CORPORATE) {
			if (ekycRequest.getApplicantType() == CommonUtils.CORPORATE_USER) {
				CorporateApplicantDetail corp = corporateApplicantDetailRepository
						.getByApplicationAndUserId(loanApplicationMaster.getUserId(), ekycRequest.getApplicationId());
				ekycResponse.setOrganizationName(corp.getOrganisationName());
				ekycResponse.setPanNo(corp.getPanNo());
				return ekycResponse;
			}
			if (ekycRequest.getApplicantType() == CommonUtils.CORPORATE_COAPPLICANT) {
				CorporateCoApplicantDetail corpCoapp = corporateCoApplicantRepository.get(
						ekycRequest.getApplicationId(), loanApplicationMaster.getUserId(),
						ekycRequest.getApplicantsId());
				ekycResponse.setOrganizationName(corpCoapp.getOrganisationName());
				ekycResponse.setPanNo(corpCoapp.getPanNo());
				return ekycResponse;
			}

		} else {
			if (ekycRequest.getApplicantType() == CommonUtils.RETAIL_APPLICANT) {
				RetailApplicantDetail retail = retailApplicantDetailRepository
						.getByApplicationAndUserId(loanApplicationMaster.getUserId(), ekycRequest.getApplicantsId());
				String fullName = retail.getFirstName() + " " + retail.getLastName();
				Date date = retail.getBirthDate();
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_DD_MM_YYYY);
				String strDate = sdf.format(date);
				ekycResponse.setFullName(fullName);
				ekycResponse.setPanNo(retail.getPan());
				ekycResponse.setAadharNo(retail.getAadharNumber());
				ekycResponse.setNameAsPerAadhar(retail.getNameAsPerAadharCard());
				ekycResponse.setDob(strDate);
				return ekycResponse;
			} else if (ekycRequest.getApplicantType() == CommonUtils.RETAIL_COAPPLICANT) {
				CoApplicantDetail coApp = coApplicantDetailRepository.get(ekycRequest.getApplicationId(),
						loanApplicationMaster.getUserId(), ekycRequest.getApplicantsId());
				String fullName = coApp.getFirstName() + " " + coApp.getLastName();
				Date date = coApp.getBirthDate();
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_DD_MM_YYYY);
				String strDate = sdf.format(date);
				ekycResponse.setFullName(fullName);
				ekycResponse.setPanNo(coApp.getPan());
				ekycResponse.setAadharNo(coApp.getAadharNumber());
				ekycResponse.setNameAsPerAadhar(coApp.getNameAsPerAadharCard());
				ekycResponse.setDob(strDate);
				return ekycResponse;

			} else if (ekycRequest.getApplicantType() == CommonUtils.RETAIL_GUARANTOR) {
				GuarantorDetails gua = guarantorDetailsRepository.get(ekycRequest.getApplicationId(),
						loanApplicationMaster.getUserId(), ekycRequest.getApplicantsId());
				String fullName = gua.getFirstName() + " " + gua.getLastName();
				Date date = gua.getBirthDate();
				SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_DD_MM_YYYY);
				String strDate = sdf.format(date);
				ekycResponse.setFullName(fullName);
				ekycResponse.setPanNo(gua.getPan());
				ekycResponse.setAadharNo(gua.getAadharNumber());
				ekycResponse.setNameAsPerAadhar(gua.getNameAsPerAadharCard());
				ekycResponse.setDob(strDate);
				return ekycResponse;
			}
		}
		return ekycResponse;

	}

	public String getMcaCompanyId(Long applicationId, Long userId) {
		try {
			return loanApplicationRepository.getMCACompanyIdByIdAndUserId(applicationId, userId).getMcaCompanyId();
		} catch (Exception e) {
			return null;
		}
	}

	public String getMCACompanyIdById(Long applicationId) {
		try {
			return loanApplicationRepository.getMCACompanyIdById(applicationId).getMcaCompanyId();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void updateLoanApplication(LoanApplicationRequest loanRequest) {

		LoanApplicationMaster master = loanApplicationRepository.getByIdAndUserId(loanRequest.getId(),
				loanRequest.getUserId());
		if (!CommonUtils.isObjectNullOrEmpty(master)) {
			logger.info("In LOANAPPLICATIONMASTER");
			master.setMcaCompanyId(loanRequest.getMcaCompanyId());
			master.setIsMca(loanRequest.getIsMca());
			loanApplicationRepository.save(master);
		} else {
			logger.error("NUll LOANAPPLICATIONMASTER");
		}
	}

	@Override
	public Boolean isMca(Long applicationId, Long userId) {
		try {
			return loanApplicationRepository.getMCACompanyIdByIdAndUserId(applicationId, userId).getIsMca();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Long getTotalUserApplication(Long userId) {
		logger.info("Enter in get Total User Application");
		Long totalApp = loanApplicationRepository.getTotalUserApplication(userId);
		logger.info("Exit in get Total User Application --->" + totalApp);
		return totalApp;
	}

	@Override
	public Long getUserIdByApplicationId(Long applicationId) {
		return loanApplicationRepository.getUserIdByApplicationId(applicationId);
	}

	@Override
	public Long getApplicationIdByProposalId(Long proposalId) {
		return applicationProposalMappingRepository.getApplicationIdByProposalId(proposalId);  // NEW
	}

	@Override
	public Long getUserIdByProposalId(Long proposalId) {
		return applicationProposalMappingRepository.getUserIdByProposalId(proposalId);  // NEW
	}


	@Override
	public boolean isCampaignCodeExist(Long userId, Long clientId, String code) throws LoansException {
		try {
			Long finalUserId = CommonUtils.isObjectNullOrEmpty(clientId) ? userId : clientId;
			Long long1 = loanApplicationRepository.getApplicantCountByCode(finalUserId, code);
			return long1 > 0;
		} catch (Exception e) {
			logger.error("Error while Checking Code is Exists or not : ",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public String getCampaignCodeByApplicationId(Long applicationId) throws LoansException {
		try {
			return loanApplicationRepository.getCampaignCodeByApplicationId(applicationId);
		} catch (Exception e) {
			logger.error("Error while getting Code by Application Id : ",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	private LoanApplicationMaster getLoanByType(LoanType type) {
		LoanApplicationMaster applicationMaster = null;
		switch (type) {
		case WORKING_CAPITAL:
			applicationMaster = new PrimaryWorkingCapitalLoanDetail();
			break;
		case TERM_LOAN:
			applicationMaster = new PrimaryTermLoanDetail();
			break;
		case LAS_LOAN:
			applicationMaster = new PrimaryLasLoanDetail();
			break;
		case LAP_LOAN:
			applicationMaster = new PrimaryLapLoanDetail();
			break;
		case PERSONAL_LOAN:
			applicationMaster = new PrimaryPersonalLoanDetail();
			break;
		case HOME_LOAN:
			applicationMaster = new PrimaryHomeLoanDetail();
			break;
		case CAR_LOAN:
			applicationMaster = new PrimaryCarLoanDetail();
			break;
		case UNSECURED_LOAN:
			applicationMaster = new PrimaryUnsecuredLoanDetail();
			break;
		default:
			break;

		}
		return applicationMaster;
	}

	@Override
	public Boolean isTermLoanLessThanLimit(Long applicationId) {
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicationId);
		if (CommonUtils.isObjectNullOrEmpty(applicationMaster)) {
			return null;
		} else {
			return CommonUtils.isTermLoanLessThanLimit(applicationMaster.getDenominationId(),
					applicationMaster.getAmount());
		}

	}

	@Override
	public Integer setEligibleLoanAmount(LoanApplicationRequest applicationRequest) throws LoansException {
		logger.info("Entry in setEligibleLoanAmount()");
		try {
			Long finalUserId = CommonUtils.isObjectNullOrEmpty(applicationRequest.getClientId())
					? applicationRequest.getUserId()
					: applicationRequest.getClientId();
			int i = loanApplicationRepository.setEligibleAmount(applicationRequest.getId(), finalUserId,
					applicationRequest.getAmount());
			logger.info("No Of updated row in Eligible Amount===>" + i);
			logger.info("Exit from setEligibleLoanAmount()");
			return i;
		} catch (Exception e) {
			logger.error("Error while updating Eligibility Amount : ",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public void updateFlow(Long applicationId, Long clientId, Long userId) throws LoansException {
		logger.info("Entry in updateFlow()");
		try {
			Long finalUserId = CommonUtils.isObjectNullOrEmpty(clientId) ? userId : clientId;
			LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId,
					finalUserId);
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster)) {
				logger.info("applicationMaster found null in updateFlow");
				logger.info("Exit from updateFlow()");
				return;
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getCampaignCode())) {
				logger.info("Campaign Code is already null so no need to re update the row.");
			} else {
				applicationMaster.setCampaignCode(null);
				applicationMaster.setModifiedBy(userId);
				applicationMaster.setModifiedDate(new Date());
				loanApplicationRepository.save(applicationMaster);
			}
		} catch (Exception e) {
			logger.error("Error while Coverting UBI flow to Normal : ",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public Long getIrrByApplicationId(Long id) throws LoansException {
		try {

			CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
					.findOneByApplicationIdId(id);
			if (corporateApplicantDetail == null) {
				throw new NullPointerException(INVALID_LOAN_APPLICATION_ID + id);
			}

			try {
				if (corporateApplicantDetail.getKeyVerticalSector() != null
						&& corporateApplicantDetail.getKeyVerticalSubsector() != null) {
					IrrBySectorAndSubSector irr = new IrrBySectorAndSubSector();
					irr.setSectorId(corporateApplicantDetail.getKeyVerticalSector());
					irr.setSubSectorId(corporateApplicantDetail.getKeyVerticalSubsector());

					// IrrBySectorAndSubSector res
					// =(IrrBySectorAndSubSector)oneFormClient.getIrrBySectorAndSubSector(irr).getData();
					IrrBySectorAndSubSector res = (IrrBySectorAndSubSector) MultipleJSONObjectHelper.getObjectFromMap(
							(Map<String, Object>) oneFormClient.getIrrBySectorAndSubSector(irr).getData(),
							IrrBySectorAndSubSector.class);
					return res.getIrr();
				}
			} catch (Exception e) {
				logger.error("Error while getting Status From Proposal Client,getKeyVerticalSector can not be null : ",e);
				return null;
			}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_INDIVIDUAL_LOAN_DETAILS,e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
		return null;
	}

	/**@Override
	public Object updateLoanApplicationMaster(PaymentRequest paymentRequest, Long userId) throws LoansException {
		logger.info("Start updateLoanApplicationMaster()");
		try {

			logger.info("User Id-----------------> " + userId);
			logger.info("Payment Request--------------------> " + paymentRequest.toString());
			LoanApplicationMaster loanApplicationMaster = loanApplicationRepository
					.getById(paymentRequest.getApplicationId());
			if (CommonUtils.isObjectNullOrEmpty(loanApplicationMaster)) {
				logger.info("LoanMaster is null or Empty by this applicationid--------------------->"
						+ paymentRequest.getApplicationId());
				return null;
			}
			logger.info("Loan Master------------------>" + loanApplicationMaster);

			if (SIDBI_FEES.equalsIgnoreCase(paymentRequest.getPurposeCode())) {

				loanApplicationMaster.setTypeOfPayment(paymentRequest.getTypeOfPayment());
				loanApplicationRepository.save(loanApplicationMaster);

			} else {

				loanApplicationMaster.setTypeOfPayment(paymentRequest.getTypeOfPayment());
				loanApplicationMaster.setPaymentAmount(paymentRequest.getPaymentAmount());
				loanApplicationMaster.setAppointmentDate(paymentRequest.getAppointmentDate());
				loanApplicationMaster.setAppointmentTime(paymentRequest.getAppointmentTime());
				loanApplicationMaster.setIsAcceptConsent(paymentRequest.getIsAcceptConsent());
				loanApplicationRepository.save(loanApplicationMaster);
				CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
						.findOneByApplicationIdId(paymentRequest.getApplicationId());

				if (CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
					corporateApplicantDetail = new CorporateApplicantDetail();
					corporateApplicantDetail.setApplicationId(loanApplicationMaster);
					corporateApplicantDetail.setCreatedBy(userId);
					corporateApplicantDetail.setCreatedDate(new Date());
					corporateApplicantDetail.setIsActive(true);
				}

				corporateApplicantDetail.setOrganisationName(paymentRequest.getNameOfEntity());
				corporateApplicantDetail.setAdministrativePremiseNumber(paymentRequest.getAddress().getPremiseNumber());
				corporateApplicantDetail.setAdministrativeStreetName(paymentRequest.getAddress().getStreetName());
				corporateApplicantDetail.setAdministrativeLandMark(paymentRequest.getAddress().getLandMark());
				corporateApplicantDetail.setAdministrativeCountryId(paymentRequest.getAddress().getCountryId());
				corporateApplicantDetail.setAdministrativeStateId(paymentRequest.getAddress().getStateId());
				corporateApplicantDetail.setAdministrativeCityId(paymentRequest.getAddress().getCityId());
				corporateApplicantDetail.setAdministrativePincode(paymentRequest.getAddress().getPincode());
				corporateApplicantDetail.setModifiedBy(userId);
				corporateApplicantDetail.setModifiedDate(new Date());
				corporateApplicantDetailRepository.save(corporateApplicantDetail);
			}
			if (CommonUtils.PaymentMode.ONLINE.equalsIgnoreCase(paymentRequest.getTypeOfPayment())
					&& paymentRequest.getPurposeCode().equals("NHBS_FEES")) {
				logger.info("Start updateLoanApplicationMaster when Payment Mode in ONLINE() in NHBS");
				GatewayRequest gatewayRequest = new GatewayRequest();
				try {
					gatewayRequest.setApplicationId(paymentRequest.getApplicationId());
					gatewayRequest.setEmail(paymentRequest.getEmailAddress());
					gatewayRequest.setPhone(paymentRequest.getMobileNumber());
					gatewayRequest.setAmount(Double.valueOf(nhbsAmount));
					gatewayRequest.setFirstName(paymentRequest.getNameOfEntity());
					gatewayRequest.setUserId(userId);
					gatewayRequest.setGatewayType(paymentRequest.getGatewayType());
					gatewayRequest.setProductInfo(paymentRequest.getPurposeCode());
					gatewayRequest.setPaymentType(paymentRequest.getTypeOfPayment());
					gatewayRequest.setPurposeCode(paymentRequest.getPurposeCode());
					// gatewayRequest.setResponseParams(paymentRequest.getResponseParams());
					Object values = gatewayClient.payout(gatewayRequest);
					logger.info("Response for gateway is:- " + values);
					logger.info("End updateLoanApplicationMaster when Payment Mode in ONLINE() in NHBS");
					return values;
				} catch (Exception e) {
					logger.error("Error while Saving Payment History to Patyment Module when Payment Mode is ONLINE : ",e);
					throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
				}
			} else if (CommonUtils.PaymentMode.ONLINE.equalsIgnoreCase(paymentRequest.getTypeOfPayment())
					&& paymentRequest.getPurposeCode().equals(SIDBI_FEES)) {

				logger.info("Start updateLoanApplicationMaster when Payment Mode in ONLINE() in SIDBI");

				GatewayRequest gatewayRequest = new GatewayRequest();

				UsersRequest usersRequest = null;
				try {
					UserResponse emailMobile = userClient.getEmailMobile(userId);
					usersRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(LinkedHashMap<String, Object>) emailMobile.getData(), UsersRequest.class);
				} catch (Exception e) {
					logger.error("Throw Exception While Get User Email and Mobile : ",e);
				}

				if (usersRequest != null && !CommonUtils.isObjectNullOrEmpty(usersRequest)) {
					gatewayRequest.setEmail(usersRequest.getEmail());
					gatewayRequest.setPhone(usersRequest.getMobile());
				} else {
					return "No Email or Mobile Number found, insufficient parameters for Gateway!!!";
				}
				gatewayRequest.setApplicationId(paymentRequest.getApplicationId());
				gatewayRequest.setGatewayType(paymentRequest.getGatewayType());
				gatewayRequest.setAmount(Double.valueOf(sidbiAmount));
				gatewayRequest.setFirstName(paymentRequest.getNameOfEntity());
				gatewayRequest.setUserId(userId);
				gatewayRequest.setProductInfo(paymentRequest.getPurposeCode());
				gatewayRequest.setPaymentType(paymentRequest.getTypeOfPayment());
				gatewayRequest.setPurposeCode(paymentRequest.getPurposeCode());
				gatewayRequest.setRequestType(paymentRequest.getRequestType());
				gatewayRequest.setBusinessTypeId(paymentRequest.getBusinessTypeId());

				Object values = gatewayClient.payout(gatewayRequest);

				logger.info("Response for gateway is:- " + values);
				logger.info("End updateLoanApplicationMaster when Payment Mode in ONLINE() in SIDBI");
				return values;
				return null;
			}
		} catch (Exception e) {
			logger.error("Error while Saving payment information in Loan : ",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
		return paymentRequest.getTypeOfPayment();
	}

	@Override
	public void updateSkipPayment(Long userId, Long applicationId, Long orgId, Long fpProductId) throws LoansException {

		logger.info("Enter in Update Skip Payment Details !!");

		// UPDATE PAYMENT STATE IN LOAN MASTER
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);

		if (loanApplicationMaster == null) {
			throw new NullPointerException(INVALID_LOAN_APPLICATION_ID + applicationId);
		}

		 * LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
		 * BeanUtils.copyProperties(loanApplicationMaster, applicationRequest);

		loanApplicationMaster.setPaymentStatus(CommonUtils.PaymentStatus.BYPASS);
		loanApplicationRepository.save(loanApplicationMaster);

		// UPDATE CONNECT POST PAYMENT
		try {
			ConnectResponse connectResponse = connectClient.postPayment(applicationId, userId,
					loanApplicationMaster.getBusinessTypeId());

			if (!CommonUtils.isObjectListNull(connectResponse)) {
				logger.info(CONNECTOR_RESPONSE_MSG + connectResponse.toString());
				logger.info(BEFORE_START_SAVING_PHASE_1_SIDBI_API_MSG + orgId);
				if (orgId == 10L) {
					logger.info("Start Saving Phase 1 sidbi API -------------------->" + loanApplicationMaster.getId());
					Long fpMappingId = null;
					try {
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
					savePhese1DataToSidbi(loanApplicationMaster.getId(), userId, orgId, fpProductId);
				}

				if (connectResponse.getProceed() && loanApplicationMaster.getCompanyCinNumber() != null && "Y".equals(IS_MCA_ON) ) {
						mcaAsyncComponent.callMCAForData(loanApplicationMaster.getCompanyCinNumber(),
								loanApplicationMaster.getId(), loanApplicationMaster.getUserId());
				}
			} else {
				logger.info(CONNECTOR_RESPONSE_NULL_OR_EMPTY_MSG);
				throw new LoansException(SOMETHING_WENT_WRONG_WHILE_CALL_CONNECT_CLIENT_FOR_MSG + applicationId);
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			throw new LoansException(SOMETHING_WENT_WRONG_WHILE_CALL_CONNECT_CLIENT_FOR_MSG + applicationId);
		}

		// TRUE MATCHES PROPOSAL
		try {
			ProposalMappingResponse proposalMappingResponse = proposalDetailsClient
					.activateProposalOnPayment(applicationId);
			if (!CommonUtils.isObjectNullOrEmpty(proposalMappingResponse)) {
				logger.info(PROPOSAL_MAPPING_RESPONSE_MSG + proposalMappingResponse.toString());
				if (proposalMappingResponse.getStatus() != HttpStatus.OK.value()) {
					throw new LoansException(proposalMappingResponse.getMessage());
				}
			} else {
				logger.info(PROPOSAL_MAPPING_RESPONSE_NULL_OR_EMPTY_MSG);
				throw new LoansException(SOMETHING_WENT_WRONG_WHILE_CALL_PROPOSAL_CLIENT_FOR_MSG + applicationId);
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			throw new LoansException(SOMETHING_WENT_WRONG_WHILE_CALL_PROPOSAL_CLIENT_FOR_MSG + applicationId);
		}

		logger.info("Exit on Update Skip Payment Details ");
	}

	@Override
	public void updateSkipPaymentWhiteLabel(Long userId, Long applicationId, Integer businessTypeId, Long orgId,
			Long fpProductId) throws LoansException {
		try {
			logger.info("Enter in Update Skip Payment Details for WhiteLabel!!");

			// UPDATE PAYMENT STATE IN LOAN MASTER
			LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);

			if (loanApplicationMaster == null) {
				throw new NullPointerException(INVALID_LOAN_APPLICATION_ID + applicationId);
			}
			LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
			BeanUtils.copyProperties(loanApplicationMaster, applicationRequest);
			loanApplicationMaster.setPaymentStatus(CommonUtils.PaymentStatus.BYPASS);
			loanApplicationRepository.save(loanApplicationMaster);

			// UPDATE CONNECT POST PAYMENT
			try {
				ConnectResponse connectResponse = connectClient.postPayment(applicationId, userId,
						loanApplicationMaster.getBusinessTypeId());

				if (!CommonUtils.isObjectListNull(connectResponse)) {
					logger.info(CONNECTOR_RESPONSE_MSG + connectResponse.toString());
					logger.info(BEFORE_START_SAVING_PHASE_1_SIDBI_API_MSG + orgId);
				if (orgId == 10L) {
					logger.info("Start Saving Phase 1 sidbi API -------------------->" + loanApplicationMaster.getId());
					Long fpMappingId = null;
					try {
						savePhese1DataToSidbi(loanApplicationMaster.getId(), userId, orgId, fpProductId);
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
				}

					if (connectResponse.getProceed() && loanApplicationMaster.getCompanyCinNumber() != null && "Y".equals(IS_MCA_ON) ) {
						mcaAsyncComponent.callMCAForData(loanApplicationMaster.getCompanyCinNumber(),
								loanApplicationMaster.getId(), loanApplicationMaster.getUserId());
				}
			} else {
				logger.info(CONNECTOR_RESPONSE_NULL_OR_EMPTY_MSG);
				throw new LoansException(SOMETHING_WENT_WRONG_WHILE_CALL_CONNECT_CLIENT_FOR_MSG + applicationId);
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			throw new LoansException(SOMETHING_WENT_WRONG_WHILE_CALL_CONNECT_CLIENT_FOR_MSG + applicationId);
		}

		// TRUE MATCHES PROPOSAL
		try {
			ProposalMappingResponse proposalMappingResponse = proposalDetailsClient
					.activateProposalOnPayment(applicationId);
			if (!CommonUtils.isObjectNullOrEmpty(proposalMappingResponse)) {
				logger.info(PROPOSAL_MAPPING_RESPONSE_MSG + proposalMappingResponse.toString());
				if (proposalMappingResponse.getStatus() != HttpStatus.OK.value()) {
					throw new LoansException(proposalMappingResponse.getMessage());
				}
			} else {
				logger.info(PROPOSAL_MAPPING_RESPONSE_NULL_OR_EMPTY_MSG);
				throw new LoansException(SOMETHING_WENT_WRONG_WHILE_CALL_PROPOSAL_CLIENT_FOR_MSG + applicationId);
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			throw new LoansException(SOMETHING_WENT_WRONG_WHILE_CALL_PROPOSAL_CLIENT_FOR_MSG + applicationId);
		}

			// Sending In-Principle for WhiteLabel
			// ====================================================================
			GatewayRequest gatewayRequest = new GatewayRequest();

			gatewayRequest.setUserId(userId);
			gatewayRequest.setApplicationId(applicationId);
			gatewayRequest.setBusinessTypeId(businessTypeId);

			Boolean status = null;
			status =null;
			logger.info("In-Principle send for WhiteLabel Status=====>" + status);

			// =======================changes made for multiple bank===================

			ProposalMappingResponse response = null;
			Map<String, Object> proposalresp = null;
			try {
				logger.info("Calling Proposal details client for getting In-principle response for applicationId:-"
						+ applicationId);
				response = proposalDetailsClient.getInPricipleById(applicationId);
				logger.info("Got Inprinciple response from Proposal Details Client" + response);
				proposalresp = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) response.getData(),
						Map.class);
			} catch (Exception e) {
				logger.error("Error calling Proposal Details Client for getting In-principle response for applicationId:-" + applicationId + " :: ",e);
			}
		Long prposalId=Long.valueOf(String.valueOf(proposalresp.get(PROPOSAL_ID)));
			LoanApplicationRequest loansRequest = loanApplicationService.getFromClient(prposalId);

			PaymentRequest paymentRequest = new PaymentRequest();
			paymentRequest.setApplicationId(applicationId);
			paymentRequest.setNameOfEntity(loansRequest.getUserName());

			// ==================Sending Mail to all Checker's & Maker's & HO & BO of that
			// branch after FS recieves In-principle Approval==================

			try {
				logger.info(INSIDE_SENDING_MAIL_TO_MAKER_AFTER_IN_PRINCIPLE_APPROVAL_MSG);
				fpasyncComponent.sendEmailToAllMakersWhenFSRecievesInPrinciple(proposalresp, paymentRequest, userId, orgId);
			} catch (Exception e) {
				logger.error(EXCEPTION_OCCURED_WHILE_SENDING_MAIL_TO_ALL_MAKERS_MSG,e);
			}

			try {
				logger.info(INSIDE_SENDING_MAIL_TO_CHECKER_AFTER_IN_PRINCIPLE_APPROVAL_MSG);
				fpasyncComponent.sendEmailToAllCheckersWhenFSRecievesInPrinciple(proposalresp, paymentRequest, userId,
						orgId);
			} catch (Exception e) {
				logger.error(EXCEPTION_OCCURED_WHILE_SENDING_MAIL_TO_ALL_CHECKERS_MSG,e);
			}

			try {
				logger.info(INSIDE_SENDING_MAIL_TO_HO_AFTER_IN_PRINCIPLE_APPROVAL_MSG);
				fpasyncComponent.sendEmailToHOWhenFSRecievesInPrinciple(proposalresp, paymentRequest, userId, orgId);
			} catch (Exception e) {
				logger.error(EXCEPTION_OCCURED_WHILE_SENDING_MAIL_TO_HO_MSG,e);
			}

			try {
				logger.info(INSIDE_SENDING_MAIL_TO_BO_AFTER_IN_PRINCIPLE_APPROVAL_MSG);
				fpasyncComponent.sendEmailToAllBOWhenFSRecievesInPrinciple(proposalresp, paymentRequest, userId, orgId);
			} catch (Exception e) {
				logger.error(EXCEPTION_OCCURED_WHILE_SENDING_MAIL_TO_ALL_BO_MSG,e);
			}

			// =======================================================================================================================================

			logger.info("Exit on Update Skip Payment WhiteLabel");
		}
		catch (Exception e){
			throw new LoansException(e);
		}

	}

	@Override
	public void sendInPrincipleForPersonalLoan(Long userId, Long applicationId, Integer businessTypeId, Long orgId,
			Long fpProductId) throws LoansException {
		try {
			logger.info("Enter in sendInPrincipleForPersonalLoan!!");

			// UPDATE PAYMENT STATE IN LOAN MASTER
			LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);

			if (loanApplicationMaster == null) {
				throw new NullPointerException(INVALID_LOAN_APPLICATION_ID + applicationId);
			}
			LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
			BeanUtils.copyProperties(loanApplicationMaster, applicationRequest);
			loanApplicationMaster.setPaymentStatus(CommonUtils.PaymentStatus.BYPASS);
			loanApplicationRepository.save(loanApplicationMaster);

			// UPDATE CONNECT POST PAYMENT
			try {
				ConnectResponse connectResponse = connectClient.postPayment(applicationId, userId,
						loanApplicationMaster.getBusinessTypeId());

				if (!CommonUtils.isObjectListNull(connectResponse)) {
					logger.info(CONNECTOR_RESPONSE_MSG + connectResponse.toString());
					logger.info(BEFORE_START_SAVING_PHASE_1_SIDBI_API_MSG + orgId);
					// if(orgId==10L) {

					 * logger.info("Start Saving Phase 1 sidbi API -------------------->" +
					 * loanApplicationMaster.getId()); Long fpMappingId = null; try {
					 * savePhese1DataToSidbi(loanApplicationMaster.getId(),
					 * userId,orgId,fpProductId); }catch(Exception e) { logger.error(CommonUtils.EXCEPTION,e); }

					// }

					if (connectResponse.getProceed() && loanApplicationMaster.getCompanyCinNumber() != null && "Y".equals(IS_MCA_ON) ) {
						mcaAsyncComponent.callMCAForData(loanApplicationMaster.getCompanyCinNumber(),
								loanApplicationMaster.getId(), loanApplicationMaster.getUserId());
				}
			} else {
				logger.info(CONNECTOR_RESPONSE_NULL_OR_EMPTY_MSG);
				throw new LoansException(SOMETHING_WENT_WRONG_WHILE_CALL_CONNECT_CLIENT_FOR_MSG + applicationId);
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			throw new LoansException(SOMETHING_WENT_WRONG_WHILE_CALL_CONNECT_CLIENT_FOR_MSG + applicationId);
		}

		// TRUE MATCHES PROPOSAL
		try {
			ProposalMappingResponse proposalMappingResponse = proposalDetailsClient
					.activateProposalOnPayment(applicationId);
			if (!CommonUtils.isObjectNullOrEmpty(proposalMappingResponse)) {
				logger.info(PROPOSAL_MAPPING_RESPONSE_MSG + proposalMappingResponse.toString());
				if (proposalMappingResponse.getStatus() != HttpStatus.OK.value()) {
					throw new LoansException(proposalMappingResponse.getMessage());
				}
			} else {
				logger.info(PROPOSAL_MAPPING_RESPONSE_NULL_OR_EMPTY_MSG);
				throw new LoansException(SOMETHING_WENT_WRONG_WHILE_CALL_PROPOSAL_CLIENT_FOR_MSG + applicationId);
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			throw new LoansException(SOMETHING_WENT_WRONG_WHILE_CALL_PROPOSAL_CLIENT_FOR_MSG + applicationId);
		}

			// Sending In-Principle for Personal Loan
			// ====================================================================
			GatewayRequest gatewayRequest = new GatewayRequest();

			gatewayRequest.setUserId(userId);
			gatewayRequest.setApplicationId(applicationId);
			gatewayRequest.setBusinessTypeId(businessTypeId);

			Boolean status = null;
			status = null;
			logger.info("In-Principle send for Personal Loan Status=====>" + status);
			// ====================================================================

			ProposalMappingResponse response = null;
			Map<String, Object> proposalresp = null;
			try {
				logger.info("Calling Proposal details client for getting In-principle response for applicationId:-"
						+ applicationId);
				response = proposalDetailsClient.getInPricipleById(applicationId);
				logger.info("Got Inprinciple response from Proposal Details Client" + response);
				proposalresp = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) response.getData(),
						Map.class);
			} catch (Exception e) {
				logger.error("Error calling Proposal Details Client for getting In-principle response for applicationId:-" + applicationId + " :: ",e);
			}

			Long prposalId=Long.valueOf(String.valueOf(proposalresp.get(PROPOSAL_ID)));
		LoanApplicationRequest loansRequest = loanApplicationService.getFromClient(prposalId);

			PaymentRequest paymentRequest = new PaymentRequest();
			paymentRequest.setApplicationId(applicationId);
			paymentRequest.setNameOfEntity(loansRequest.getUserName());

			// ==================Sending Mail to all Checker's & Maker's & HO & BO of that
			// branch after FS recieves In-principle Approval==================

			try {
				logger.info(INSIDE_SENDING_MAIL_TO_MAKER_AFTER_IN_PRINCIPLE_APPROVAL_MSG);
				fpasyncComponent.sendEmailToAllMakersWhenFSRecievesInPrinciple(proposalresp, paymentRequest, userId, orgId);
			} catch (Exception e) {
				logger.error(EXCEPTION_OCCURED_WHILE_SENDING_MAIL_TO_ALL_MAKERS_MSG,e);
			}

			try {
				logger.info(INSIDE_SENDING_MAIL_TO_CHECKER_AFTER_IN_PRINCIPLE_APPROVAL_MSG);
				fpasyncComponent.sendEmailToAllCheckersWhenFSRecievesInPrinciple(proposalresp, paymentRequest, userId,
						orgId);
			} catch (Exception e) {
				logger.error(EXCEPTION_OCCURED_WHILE_SENDING_MAIL_TO_ALL_CHECKERS_MSG,e);
			}

			try {
				logger.info(INSIDE_SENDING_MAIL_TO_HO_AFTER_IN_PRINCIPLE_APPROVAL_MSG);
				fpasyncComponent.sendEmailToHOWhenFSRecievesInPrinciple(proposalresp, paymentRequest, userId, orgId);
			} catch (Exception e) {
				logger.error(EXCEPTION_OCCURED_WHILE_SENDING_MAIL_TO_HO_MSG,e);
			}

			try {
				logger.info(INSIDE_SENDING_MAIL_TO_BO_AFTER_IN_PRINCIPLE_APPROVAL_MSG);
				fpasyncComponent.sendEmailToAllBOWhenFSRecievesInPrinciple(proposalresp, paymentRequest, userId, orgId);
			} catch (Exception e) {
				logger.error(EXCEPTION_OCCURED_WHILE_SENDING_MAIL_TO_ALL_BO_MSG,e);
			}

			// =======================================================================================================================================

			logger.info("Exit on sendInPrincipleForPersonalLoan");
		}
		catch (Exception e){
			throw new LoansException(e);
		}
	}

	/**
	 * This method no longer used
	 * */
	/*@Override
	public LoanApplicationRequest updateLoanApplicationMasterPaymentStatus(PaymentRequest paymentRequest, Long userId)
			throws LoansException {
		logger.info("start updateLoanApplicationMasterPaymentStatus()");
		try {

			GatewayRequest gatewayRequest = new GatewayRequest();
			gatewayRequest.setApplicationId(paymentRequest.getApplicationId());
			gatewayRequest.setUserId(userId);
			gatewayRequest.setClientId(userId);
			gatewayRequest.setStatus(paymentRequest.getStatus());
			gatewayRequest.setTxnId(paymentRequest.getTrxnId());
			gatewayRequest.setFirstName(paymentRequest.getNameOfEntity());
			gatewayRequest.setResponseParams(paymentRequest.getResponseParams());

			Boolean updatePayment = false;
			ProposalMappingResponse respProp = null;
			if (SIDBI_FEES.equals(paymentRequest.getPurposeCode()) && "Success".equals(paymentRequest.getStatus()) ) {
					try {
						logger.info("Start update true proposal-------------------------->"
								+ paymentRequest.getApplicationId());
						respProp = proposalDetailsClient.activateProposalOnPayment(paymentRequest.getApplicationId());
					} catch (Exception e) {
						logger.info("Throw Exception WHile Activate Proposals : ",e);
					}

			}
			try {
			updatePayment = gatewayClient.updatePayment(gatewayRequest);
			} catch (Exception e) {
				logger.error("THROW EXCEPTION WHILE UPDATE PAYMENT ON GATEWAY CLIENT : ",e);
			}

			if (SIDBI_FEES.equals(paymentRequest.getPurposeCode())) {
				Long orgId = null;
				LoanApplicationMaster loanApplicationMaster = loanApplicationRepository
						.findOne(paymentRequest.getApplicationId());

				if (loanApplicationMaster == null) {
					throw new NullPointerException(
							INVALID_LOAN_APPLICATION_ID + paymentRequest.getApplicationId());
				}
				orgId = loanApplicationMaster.getNpOrgId();
				LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
				BeanUtils.copyProperties(loanApplicationMaster, applicationRequest);
				loanApplicationMaster.setPaymentStatus(paymentRequest.getStatus());
				logger.info("Business Type Id===============>" + loanApplicationMaster.getBusinessTypeId());
				loanApplicationRepository.save(loanApplicationMaster);
				try {

					if ("Success".equals(paymentRequest.getStatus())) {

						Long fpProductId = null;
						if (respProp != null && respProp.getData() != null) {
							ProposalMappingRequest mappingRequest = MultipleJSONObjectHelper.getObjectFromMap(
									(LinkedHashMap<String, Object>) respProp.getData(), ProposalMappingRequest.class);
							fpProductId = mappingRequest.getFpProductId();
							logger.info("fpProductId : "+fpProductId);
						}
						logger.info("Call Connector client for update payment status");
						ConnectResponse connectResponse = connectClient.postPayment(paymentRequest.getApplicationId(),
								userId, loanApplicationMaster.getBusinessTypeId());

						if (!CommonUtils.isObjectListNull(connectResponse)) {
							logger.info(
									CONNECTOR_RESPONSE_MSG + connectResponse.toString());
							logger.info(BEFORE_START_SAVING_PHASE_1_SIDBI_API_MSG + orgId);
//						if(orgId==10L) {
							logger.info("Start Saving Phase 1 sidbi API -------------------->"
									+ loanApplicationMaster.getId());
							*//*try {
								savePhese1DataToSidbi(loanApplicationMaster.getId(), userId, orgId, fpProductId);
							} catch (Exception e) {
								logger.error("Error while Saving Phase1 data to Organization Id====>{}", orgId);
								logger.error(CommonUtils.EXCEPTION,e);
							}*//*
//						}
							logger.info("connectResponse.getProceed()==============>>>" + connectResponse.getProceed());
							if (connectResponse.getProceed()) {
								logger.info("loanApplicationMaster.getCompanyCinNumber()==============>>>"
										+ loanApplicationMaster.getCompanyCinNumber());
								if (loanApplicationMaster.getCompanyCinNumber() != null && "Y".equals(IS_MCA_ON) ) {
									mcaAsyncComponent.callMCAForData(loanApplicationMaster.getCompanyCinNumber(),
											loanApplicationMaster.getId(), loanApplicationMaster.getUserId());
								}
							}

						} else {
							logger.info(CONNECTOR_RESPONSE_NULL_OR_EMPTY_MSG);
						}

						ProposalMappingResponse response = proposalDetailsClient.getInPricipleById(paymentRequest.getApplicationId());
						if (response != null && response.getData() != null) {
							logger.info("Inside Congratulations");

							Map<String, Object> proposalresp = MultipleJSONObjectHelper
									.getObjectFromMap((Map<String, Object>) response.getData(), Map.class);
//					ProposalMappingResponse resp = proposalDetailsClient.getActivateProposalById(Long.valueOf(proposalresp.get("fp_product_id").toString()), paymentRequest.getApplicationId());
//					ProposalMappingRequest proposalMappingRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) resp.getData(), ProposalMappingRequest.class);

							// ==================Sending Mail to all Checker's & Maker's & HO & BO of that
							// branch after FS recieves In-principle Approval==================

							try {
								logger.info(INSIDE_SENDING_MAIL_TO_MAKER_AFTER_IN_PRINCIPLE_APPROVAL_MSG);
								fpasyncComponent.sendEmailToAllMakersWhenFSRecievesInPrinciple(proposalresp,
										paymentRequest, userId, orgId);
							} catch (Exception e) {
								logger.error(EXCEPTION_OCCURED_WHILE_SENDING_MAIL_TO_ALL_MAKERS_MSG,e);
							}

							try {
								logger.info(INSIDE_SENDING_MAIL_TO_CHECKER_AFTER_IN_PRINCIPLE_APPROVAL_MSG);
								fpasyncComponent.sendEmailToAllCheckersWhenFSRecievesInPrinciple(proposalresp,
										paymentRequest, userId, orgId);
							} catch (Exception e) {
								logger.error(EXCEPTION_OCCURED_WHILE_SENDING_MAIL_TO_ALL_CHECKERS_MSG,e);
							}

							try {
								logger.info(INSIDE_SENDING_MAIL_TO_HO_AFTER_IN_PRINCIPLE_APPROVAL_MSG);
								fpasyncComponent.sendEmailToHOWhenFSRecievesInPrinciple(proposalresp, paymentRequest,
										userId, orgId);
							} catch (Exception e) {
								logger.error(EXCEPTION_OCCURED_WHILE_SENDING_MAIL_TO_HO_MSG,e);
							}

							try {
								logger.info(INSIDE_SENDING_MAIL_TO_BO_AFTER_IN_PRINCIPLE_APPROVAL_MSG);
								fpasyncComponent.sendEmailToAllBOWhenFSRecievesInPrinciple(proposalresp, paymentRequest,
										userId, orgId);
							} catch (Exception e) {
								logger.error(EXCEPTION_OCCURED_WHILE_SENDING_MAIL_TO_ALL_BO_MSG,e);
							}

							// =======================================================================================================================================

							if (proposalresp != null) {
								applicationRequest.setLoanAmount(proposalresp.get(CommonUtils.LITERAL_AMOUNT) != null
										? Double.valueOf(proposalresp.get(CommonUtils.LITERAL_AMOUNT).toString())
										: 0.0);
								applicationRequest.setTypeOfLoan(
										CommonUtils.LoanType.getType(applicationRequest.getProductId()).toString());
								applicationRequest.setInterestRate(proposalresp.get(CommonUtils.RATE_INTEREST) != null
										? Double.valueOf(proposalresp.get(CommonUtils.RATE_INTEREST).toString())
										: 0.0);
								applicationRequest.setOnlinePaymentSuccess(updatePayment);
								applicationRequest.setNameOfEntity(paymentRequest.getNameOfEntity());
								orgId = proposalresp.get(ORG_ID) != null
										? Long.valueOf(proposalresp.get(ORG_ID).toString())
										: null;
								applicationRequest
										.setFundProvider(orgId != null ? CommonUtils.getOrganizationName(orgId) : null);
							}

						} else {
							throw new NullPointerException("Invalid user");
						}
					} else {
						logger.info("Payment Failed");
					}
				} catch (Exception e) {
					logger.error("THROW EXCEPTION WHILE CALLING PROPOSAL DETAILS FROM MATCHE ENGINE : ",e);
				}
				logger.info("End of Congratulations");
				return applicationRequest;
			}

			LoanApplicationRequest loanRequest = getFromClient(paymentRequest.getApplicationId());

			logger.info("Status===>{}", updatePayment);
			if (!CommonUtils.isObjectNullOrEmpty(updatePayment)) {
				loanRequest.setPaymentStatus(updatePayment.toString());
			}
			if (CommonUtils.isObjectNullOrEmpty(loanRequest)) {
				logger.warn("Invalid Application Id in Updating Payment Status====>{}", paymentRequest.getApplicationId());
				return null;
			}

			try {
				if (CommonUtils.isObjectNullOrEmpty(loanRequest.getNpUserId())) {
					return loanRequest;
				}

				UsersRequest usersRequest = new UsersRequest();
				usersRequest.setId(loanRequest.getNpUserId());
				UserResponse userResponse = userClient.getNPDetails(usersRequest);
				if (CommonUtils.isObjectListNull(userResponse, userResponse.getData())) {
					logger.warn("User Response or Data in UserResponse must not be null===>{}", userResponse);
				} else {
					NetworkPartnerDetailsRequest npRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(LinkedHashMap<String, Object>) userResponse.getData(), NetworkPartnerDetailsRequest.class);
					loanRequest.setProviderName(npRequest.getFirstName() + " " + npRequest.getLastName());
				}

				UserResponse emailMobile = userClient.getEmailMobile(loanRequest.getNpUserId());
				if (CommonUtils.isObjectListNull(emailMobile, emailMobile.getData())) {
					logger.warn(EMAIL_MOBILE_OR_DATA_IN_EMAIL_MOBILE_MUST_NOT_BE_NULL, emailMobile);
					return loanRequest;
				} else {
					UsersRequest userEmailMobile = MultipleJSONObjectHelper.getObjectFromMap(
							(LinkedHashMap<String, Object>) emailMobile.getData(), UsersRequest.class);
					loanRequest.setEmail(userEmailMobile.getEmail());
					loanRequest.setMobile(userEmailMobile.getMobile());
				}
			} catch (Exception e) {
				logger.error("Error while Getting Client Details from Users : ",e);
			}
			logger.info("End updateLoanApplicationMasterPaymentStatus() with success");
			return loanRequest;
		} catch (Exception e) {
			logger.error("End updateLoanApplicationMasterPaymentStatus() with Exception : ",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}*/

	/*@Override
	public GatewayRequest getPaymentStatus(PaymentRequest paymentRequest, Long userId, Long ClientId) throws LoansException {
		logger.info("start getPaymentStatus()");
		try {
			GatewayRequest gatewayRequest = new GatewayRequest();
			gatewayRequest.setApplicationId(paymentRequest.getApplicationId());
			gatewayRequest.setStatus(CommonUtils.PaymentStatus.SUCCESS);
			gatewayRequest.setUserId(userId);
			gatewayRequest.setClientId(ClientId);
			logger.info("End getPaymentStatus() with success");
			return gatewayClient.getPaymentStatus(gatewayRequest);
		} catch (Exception e) {
			logger.error("End updateLoanApplicationMasterPaymentStatus() with Exception : ",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
*/
	@Override
	public Integer getIndustryIrrByApplication(Long applicationId) {
		IrrRequest irrIndustryRequest = new IrrRequest();

		Long irrId = null;
		try {
			irrId = getIrrByApplicationId(applicationId);
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}

		irrIndustryRequest.setIrrIndustryId(irrId);
		try {
			irrIndustryRequest = ratingClient.getIrrIndustry(irrIndustryRequest);
		} catch (RatingException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		IndustryResponse industryResponse = irrIndustryRequest.getIndustryResponse();
		return !CommonUtils.isObjectNullOrEmpty(industryResponse) ? industryResponse.getBusinessTypeId() : null;
	}

	@Override
	public Long getDDRStatusId(Long applicationId) {
		LoanApplicationMaster applicationMaster = loanApplicationRepository.getById(applicationId);
		return !CommonUtils.isObjectNullOrEmpty(applicationMaster) ? applicationMaster.getDdrStatusId() : null;

	}

	@Override
	public Boolean updateDDRStatus(Long applicationId, Long userId, Long clientId, Long statusId) throws LoansException {
		logger.info("start getPaymentStatus()");
		try {
			LoanApplicationMaster applicationMaster = loanApplicationRepository.getById(applicationId);
			if (applicationMaster == null) {
				throw new LoansException("LoanapplicationMaster object Must not be null while Updating DDR Status==>"
						+ applicationMaster);
			}

			if (statusId.equals(CommonUtils.DdrStatus.APPROVED)) {
				applicationMaster.setApprovedDate(new Date());
			}

			Long appStatusId = null;
			if (CommonUtils.DdrStatus.APPROVED.equals(statusId)) {
				appStatusId = CommonUtils.ApplicationStatus.APPROVED;
			} else if (CommonUtils.DdrStatus.REVERTED.equals(statusId)) {
				appStatusId = CommonUtils.ApplicationStatus.REVERTED;
			} else if (CommonUtils.DdrStatus.SUBMITTED.equals(statusId)) {
				appStatusId = CommonUtils.ApplicationStatus.ASSIGNED_TO_CHECKER;
			} else if (CommonUtils.DdrStatus.SUBMITTED_TO_APPROVER.equals(statusId)) {
				appStatusId = CommonUtils.ApplicationStatus.SUBMITTED_TO_APPROVER;
			}
			if (!CommonUtils.isObjectNullOrEmpty(appStatusId)) {
				applicationMaster.setApplicationStatusMaster(new ApplicationStatusMaster(appStatusId));
			}

			applicationMaster.setDdrStatusId(statusId);
			applicationMaster.setModifiedBy(userId);
			applicationMaster.setModifiedDate(new Date());
			loanApplicationRepository.save(applicationMaster);
			return true;
		} catch (Exception e) {
			logger.error("Error while Updating DDR Status : ",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public Boolean updateDDRStatusByProposalId(Long applicationId, Long userId, Long proposalId, Long statusId) throws Exception {
		logger.info("start updateDDRStatusNew()");
		try {
			ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.getByProposalIdAndApplicationId(proposalId,applicationId);

			if (applicationProposalMapping == null) {
				throw new Exception("LoanapplicationMaster object Must not be null while Updating DDR Status==>"
						+ applicationProposalMapping);
			}

			if (statusId.equals(CommonUtils.DdrStatus.APPROVED)) {
				applicationProposalMapping.setApprovedDate(new Date());
			}

			Long appStatusId = null;
			if (CommonUtils.DdrStatus.APPROVED.equals(statusId)) {
				appStatusId = CommonUtils.ApplicationStatus.APPROVED;
			} else if (CommonUtils.DdrStatus.REVERTED.equals(statusId)) {
				appStatusId = CommonUtils.ApplicationStatus.REVERTED;
			} else if (CommonUtils.DdrStatus.SUBMITTED.equals(statusId)) {
				appStatusId = CommonUtils.ApplicationStatus.ASSIGNED_TO_CHECKER;
			} else if (CommonUtils.DdrStatus.SUBMITTED_TO_APPROVER.equals(statusId)) {
				appStatusId = CommonUtils.ApplicationStatus.SUBMITTED_TO_APPROVER;
			}
			if (!CommonUtils.isObjectNullOrEmpty(appStatusId)) {
				applicationProposalMapping.setApplicationStatusMaster(new ApplicationStatusMaster(appStatusId));
			}

			applicationProposalMapping.setDdrStatusId(statusId);
			applicationProposalMapping.setModifiedBy(userId);
			applicationProposalMapping.setModifiedDate(new Date());
			applicationProposalMappingRepository.save(applicationProposalMapping);
			return true;
		} catch (Exception e) {
			logger.error("Error while Updating DDR Status = {}",e);
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public LoanApplicationRequest getFromClient(Long id) throws LoansException {
		try {
			ApplicationProposalMapping applicationMaster = appPropMappService.getApplicationProposalMappingByProposalId(id);
//			LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(id);
			if (applicationMaster == null) {
				throw new NullPointerException(INVALID_LOAN_APPLICATION_ID + id);
			}
			LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
			BeanUtils.copyProperties(applicationMaster, applicationRequest);
			applicationRequest.setProfilePrimaryLocked(applicationMaster.getIsPrimaryLocked());
			applicationRequest.setFinalLocked(applicationMaster.getIsFinalLocked());
			applicationRequest.setUserName(getFsApplicantName(applicationMaster.getApplicationId()));
			applicationRequest.setProposalId(applicationMaster.getProposalId());
			UserResponse emailMobile = userClient.getEmailMobile(applicationRequest.getUserId());
			if (CommonUtils.isObjectListNull(emailMobile, emailMobile.getData())) {
				logger.warn(EMAIL_MOBILE_OR_DATA_IN_EMAIL_MOBILE_MUST_NOT_BE_NULL, emailMobile);
				return applicationRequest;
			} else {
				UsersRequest userEmailMobile = MultipleJSONObjectHelper
						.getObjectFromMap((LinkedHashMap<String, Object>) emailMobile.getData(), UsersRequest.class);
				applicationRequest.setEmail(userEmailMobile.getEmail());
				applicationRequest.setMobile(userEmailMobile.getMobile());
			}
			// SETTING ADDRESS
			String address = null;
			if (!CommonUtils.isObjectNullOrEmpty(applicationMaster.getProductId())) {
				int mainType = CommonUtils.getUserMainType(applicationMaster.getProductId().intValue());
				if (CommonUtils.UserMainType.CORPORATE == mainType) {
					CorporateApplicantDetail corpApplicantDetail =new CorporateApplicantDetail();
					CorporateFinalInfoRequest applicantDetail = corporateFinalInfoService.getByProposalId(applicationRequest.getUserId(), applicationMaster.getProposalId());
					BeanUtils.copyProperties(applicantDetail, corpApplicantDetail );
					if (!CommonUtils.isObjectNullOrEmpty(applicantDetail)) {
						address = CommonDocumentUtils.getAdministrativeOfficeAddress(corpApplicantDetail, oneFormClient);
					}
				} else {
					RetailApplicantDetail applicantDetail = retailApplicantDetailRepository
							.findByApplicationId(id);
					if (!CommonUtils.isObjectNullOrEmpty(applicantDetail)) {
						address = CommonDocumentUtils.getPermenantAddress(applicantDetail, oneFormClient);
					}
				}
				applicationRequest.setAddress(!CommonUtils.isObjectNullOrEmpty(address) ? address : "NA");
			} else {
				logger.info("No ProductId Found========>");
			}

			return applicationRequest;
		} catch (Exception e) {
			logger.error("Error while getting Individual Loan Details For Client:-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public LoanApplicationRequest getBasicInformation(Long id) {
		try {
			LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(id);
			if (applicationMaster == null) {
				throw new NullPointerException(INVALID_LOAN_APPLICATION_ID + id);
			}
			LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
			BeanUtils.copyProperties(applicationMaster, applicationRequest);
			applicationRequest.setProfilePrimaryLocked(applicationMaster.getIsPrimaryLocked());
			applicationRequest.setFinalLocked(applicationMaster.getIsFinalLocked());
			applicationRequest.setUserName(getFsApplicantName(applicationMaster.getId()));
			applicationRequest.setCreatedDate(applicationMaster.getCreatedDate());
			UserResponse emailMobile = userClient.getEmailMobile(applicationRequest.getUserId());
			if (CommonUtils.isObjectListNull(emailMobile, emailMobile.getData())) {
				logger.warn(EMAIL_MOBILE_OR_DATA_IN_EMAIL_MOBILE_MUST_NOT_BE_NULL, emailMobile);
				return applicationRequest;
			} else {
				UsersRequest userEmailMobile = MultipleJSONObjectHelper
						.getObjectFromMap((LinkedHashMap<String, Object>) emailMobile.getData(), UsersRequest.class);
				applicationRequest.setEmail(userEmailMobile.getEmail());
				applicationRequest.setMobile(userEmailMobile.getMobile());
			}
			return applicationRequest;
		} catch (Exception e) {
			logger.error("Error while getting Individual Loan Details For Client:-  {}",e);
			return null;
		}
	}

	@Override
	public Boolean isApplicationEligibleForIrr(Long applicationId) throws LoansException {
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicationId);
		if (CommonUtils.isObjectNullOrEmpty(applicationMaster)) {
			return false;
		} else {
			if (!CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryLocked()) ) {
				try {

					CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
							.findOneByApplicationIdId(applicationId);
					if (corporateApplicantDetail == null) {
						throw new NullPointerException(INVALID_LOAN_APPLICATION_ID + applicationId);
					}

					try {
						if (corporateApplicantDetail.getKeyVerticalSector() != null
								&& corporateApplicantDetail.getKeyVerticalSubsector() != null) {
							return true;
						} else {
							return false;
						}
					} catch (Exception e) {
						logger.error("Error while getting Status From isApplicationEligibleForIrr : ",e);
						return null;
					}
				} catch (Exception e) {
					logger.error(ERROR_WHILE_GETTING_INDIVIDUAL_LOAN_DETAILS,e);
					throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
				}
			}
		}
		return false;
	}

	@Override
	public DisbursementRequest getDisbursementDetails(DisbursementRequest disbursementRequest) {

				try {
					// set fs details
					LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(disbursementRequest.getApplicationId());
					ApplicationProposalMapping applicationProposalMapping=applicationProposalMappingRepository.getByApplicationIdAndProposalId(disbursementRequest.getApplicationId(), disbursementRequest.getProposalId());
					
					disbursementRequest.setFsName(getFsApplicantName(disbursementRequest.getApplicationId()));
					disbursementRequest.setFsAddress(getAddressByApplicationId(disbursementRequest.getApplicationId()));
					// fs image
					if(CommonUtils.isObjectNullOrEmpty(disbursementRequest.getIsIneligibleProposal()) || disbursementRequest.getIsIneligibleProposal() == false) {
						DocumentRequest documentRequest = new DocumentRequest();
						documentRequest.setApplicationId(disbursementRequest.getApplicationId());
						documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
						documentRequest.setProductDocumentMappingId(CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()==null?applicationProposalMapping.getProductId():loanApplicationMaster.getProductId()));
						DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
						String imagePath = null;
						if (documentResponse != null && documentResponse.getStatus() == 200) {
							List<Map<String, Object>> list = documentResponse.getDataList();
							if (!CommonUtils.isListNullOrEmpty(list)) {
								StorageDetailsResponse response = null;
								response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0), StorageDetailsResponse.class);
								if (!CommonUtils.isObjectNullOrEmpty(response.getFilePath()))
									imagePath = response.getFilePath();
								else
									imagePath = null;
							}
						}
						disbursementRequest.setFsImage(imagePath);

						// set fp details
						ProductMaster productMaster = productMasterRepository.findOne(disbursementRequest.getProductMappingId());
						disbursementRequest.setFpName(productMaster.getName());
						disbursementRequest.setOrgId(productMaster.getUserOrgId());

						UsersRequest request = new UsersRequest();
						request.setId(productMaster.getUserId());
						UserResponse userResponse = userClient.getFPDetails(request);

						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) userResponse.getData(), FundProviderDetailsRequest.class);
						//disbursementRequest.setFpName(fundProviderDetailsRequest.getOrganizationName());
						String fpAddress = "";
						List<Long> stateList = new ArrayList<>();
						if (!CommonUtils.isObjectNullOrEmpty(fundProviderDetailsRequest.getStateId()))
							stateList.add(Long.valueOf(fundProviderDetailsRequest.getStateId()));
						if (!CommonUtils.isListNullOrEmpty(stateList)) {
							try {
								OneFormResponse oneFormResponse = oneFormClient.getStateByStateListId(stateList);
								List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
								if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
									MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
									fpAddress = masterResponse.getValue() + ",";
								}
							} catch (Exception e) {
								logger.error(CommonUtils.EXCEPTION,e);
							}
						}
						disbursementRequest.setFpOrganisationName(fundProviderDetailsRequest.getOrganizationName());

						List<Long> countryList = new ArrayList<>();
						if (!CommonUtils.isObjectNullOrEmpty(fundProviderDetailsRequest.getCountryId()))
							countryList.add(Long.valueOf(fundProviderDetailsRequest.getCountryId()));
						if (!CommonUtils.isListNullOrEmpty(countryList)) {
							try {
								OneFormResponse oneFormResponse = oneFormClient.getCountryByCountryListId(countryList);
								List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
								if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
									MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
									fpAddress += masterResponse.getValue();
								}
							} catch (Exception e) {
								logger.error(CommonUtils.EXCEPTION,e);
							}
						}
						disbursementRequest.setFpAddress(fpAddress);
						disbursementRequest.setLoanName(LoanType.getType(loanApplicationMaster.getProductId()==null?applicationProposalMapping.getProductId():loanApplicationMaster.getProductId()).getName());
						// set fp image
						documentRequest.setUserId(productMaster.getUserId());
						documentRequest.setUserType("user");
						documentRequest.setUserDocumentMappingId(1L);
						documentResponse = dmsClient.listUserDocument(documentRequest);
						imagePath = "";
						if (documentResponse != null && documentResponse.getStatus() == 200) {
							List<Map<String, Object>> list = documentResponse.getDataList();
							if (!CommonUtils.isListNullOrEmpty(list)) {
								StorageDetailsResponse response = null;
								response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0), StorageDetailsResponse.class);
								if (!CommonUtils.isObjectNullOrEmpty(response.getFilePath()))
									imagePath = response.getFilePath();
								else
									imagePath = "";
							}
						}
						disbursementRequest.setFpImage(imagePath);
					}
					//For Fetching Sanctioned amount
					LoanSanctionDomain loanSanctionDomain =loanSanctionRepository.findByAppliationId(disbursementRequest.getApplicationId());
					if(!CommonUtils.isObjectNullOrEmpty(loanSanctionDomain) ){
						disbursementRequest.setSenctionedAmount(loanSanctionDomain.getSanctionAmount());
						disbursementRequest.setTenure(loanSanctionDomain.getTenure());
						disbursementRequest.setRoi(loanSanctionDomain.getRoi());
					}
					//For List of disbursed amount
					List<LoanDisbursementRequest> loanDisbursementRequestList = loanDisbursementService.getDisbursedList(disbursementRequest.getApplicationId());
					if (loanDisbursementRequestList != null && !loanDisbursementRequestList.isEmpty()) {
						disbursementRequest.setLoanDisbursementRequestList(loanDisbursementRequestList);
					}

				} catch (Exception e) {
					logger.error("error while getting details of disbursement :", e);
				}
				return disbursementRequest;
	}

	private String getAddressByApplicationId(Long applicationId) {
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);
		String address = "";
		if (CommonUtils.getUserMainTypeName(loanApplicationMaster.getProductId()) == CommonUtils.CORPORATE) {
			CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
					.findOneByApplicationIdId(applicationId);
			// set state
			List<Long> stateList = new ArrayList<>();
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredStateId()))
				stateList.add(Long.valueOf(corporateApplicantDetail.getRegisteredStateId()));
			if (!CommonUtils.isListNullOrEmpty(stateList)) {
				try {
					OneFormResponse oneFormResponse = oneFormClient.getStateByStateListId(stateList);
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
							.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper
								.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						address = masterResponse.getValue() + ",";
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
			}

			List<Long> countryList = new ArrayList<>();
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCountryId()))
				countryList.add(Long.valueOf(corporateApplicantDetail.getRegisteredCountryId()));
			if (!CommonUtils.isListNullOrEmpty(countryList)) {
				try {
					OneFormResponse oneFormResponse = oneFormClient.getCountryByCountryListId(countryList);
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
							.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper
								.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						address += masterResponse.getValue() + ",";
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
			}
		}
		return address;
	}

	@Override
	public Long createMsmeLoan(Long userId, Boolean isActive, Integer businessTypeId) {
		logger.info("IsActive======================>{}", isActive);

		if (isActive != null && isActive) {
			int inActiveCount = loanApplicationRepository.inActiveCorporateLoan(userId);
			logger.info("Inactivated Application Count of Users are ====== {} ", inActiveCount);
		}
		logger.info("Entry in createMsmeLoan--------------------------->" + userId);
//		LoanApplicationMaster corporateLoan = loanApplicationRepository.getCorporateLoan(userId, businessTypeId);
//		if (!CommonUtils.isObjectNullOrEmpty(corporateLoan)) {
//			logger.info("Corporate Application Id is Already Exists===>{}", corporateLoan.getId());
//			return corporateLoan.getId();
//		}
		logger.info("Successfully get result");
		LoanApplicationMaster corporateLoan = new PrimaryCorporateDetail();
		corporateLoan.setApplicationStatusMaster(new ApplicationStatusMaster(CommonUtils.ApplicationStatus.OPEN));
		corporateLoan.setDdrStatusId(CommonUtils.DdrStatus.OPEN);
		corporateLoan.setCreatedBy(userId);
		corporateLoan.setCreatedDate(new Date());
		corporateLoan.setUserId(userId);
		corporateLoan.setIsActive(true);
		logger.info("after set is active true");
		corporateLoan.setBusinessTypeId(businessTypeId);
		corporateLoan.setCurrencyId(Currency.RUPEES.getId());
		corporateLoan.setDenominationId(Denomination.ABSOLUTE.getId());
		logger.info("Going to Create new Corporate UserId===>{}", userId);
		corporateLoan = loanApplicationRepository.save(corporateLoan);
		logger.info("Created New Corporate Loan of User Id==>{}", userId);
		logger.info("Setting Last Application is as Last access Id in User Table---->" + corporateLoan.getIsActive());
		UsersRequest usersRequest = new UsersRequest();
		usersRequest.setLastAccessApplicantId(corporateLoan.getId());
		usersRequest.setId(userId);
		userClient.setLastAccessApplicant(usersRequest);
		logger.info("Exit in createMsmeLoan");
		return corporateLoan.getId();
	}

	@Override
	public Long createRetailLoan(Long userId, Boolean isActive, Integer businessTypeId) {
		logger.info("Entry in createRetailLoan=>{} and business type id =>{}", userId, businessTypeId);
		LoanApplicationMaster retailLoanObj = loanApplicationRepository.getCorporateLoan(userId, businessTypeId);
		if (!CommonUtils.isObjectNullOrEmpty(retailLoanObj)) {
			return retailLoanObj.getId();
		}
		logger.info("Successfully get result");
		if(CommonUtils.BusinessType.RETAIL_HOME_LOAN.getId().equals(businessTypeId)) {
			retailLoanObj = new PrimaryHomeLoanDetail();	
			retailLoanObj.setApplicationCode(applicationSequenceService.getApplicationSequenceNumber(LoanType.HOME_LOAN.getValue()));
			retailLoanObj.setProductId(LoanType.HOME_LOAN.getValue());
		} else {
			retailLoanObj = new PrimaryPersonalLoanDetail();
			retailLoanObj.setApplicationCode(applicationSequenceService.getApplicationSequenceNumber(LoanType.PERSONAL_LOAN.getValue()));
			retailLoanObj.setProductId(LoanType.PERSONAL_LOAN.getValue());
		}
		retailLoanObj.setApplicationStatusMaster(new ApplicationStatusMaster(CommonUtils.ApplicationStatus.OPEN));
		retailLoanObj.setDdrStatusId(CommonUtils.DdrStatus.OPEN);
		retailLoanObj.setCreatedBy(userId);
		retailLoanObj.setCreatedDate(new Date());
		retailLoanObj.setUserId(userId);
		retailLoanObj.setIsActive(true);
		retailLoanObj.setBusinessTypeId(businessTypeId);
		retailLoanObj.setCurrencyId(Currency.RUPEES.getId());
		retailLoanObj.setDenominationId(Denomination.ABSOLUTE.getId());
		retailLoanObj = loanApplicationRepository.save(retailLoanObj);
		UsersRequest usersRequest = new UsersRequest();
		usersRequest.setLastAccessApplicantId(retailLoanObj.getId());
		usersRequest.setId(userId);
		userClient.setLastAccessApplicant(usersRequest);
		return retailLoanObj.getId();
	}

	@Override
	public boolean updateProductDetails(LoanApplicationRequest loanApplicationRequest) {
		logger.info("Application id -------------------------------->" + loanApplicationRequest.getId());
		logger.info("Request Object---------------------------->" + loanApplicationRequest.toString());
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.getById(loanApplicationRequest.getId());
		if (CommonUtils.isObjectNullOrEmpty(loanApplicationMaster)) {
			logger.info("Loan master no found-------------------------------->" + loanApplicationRequest.getId());
			return false;
		}
		ProposalDetails proposalDetails = proposalDetailsRepository.getLastProposalByApplicationId(loanApplicationMaster.getId());
		ProductMaster productDetails = null;
		if(!CommonUtils.isObjectNullOrEmpty(proposalDetails)){
			productDetails = productMasterRepository.findByIdAndIsActive(proposalDetails.getFpProductId(),true);
		}

		if(!CommonUtils.isObjectNullOrEmpty(productDetails)){
			ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.findOne(proposalDetails.getId());
			if(CommonUtils.isObjectNullOrEmpty(applicationProposalMapping)){
				applicationProposalMapping = new ApplicationProposalMapping();
			}
			applicationProposalMapping.setProposalId(proposalDetails.getId());
			applicationProposalMapping.setApplicationId(proposalDetails.getApplicationId());
			applicationProposalMapping.setLoanAmount(loanApplicationRequest.getAmount());
			applicationProposalMapping.setTenure(loanApplicationRequest.getTenure());
			applicationProposalMapping.setProductId(loanApplicationRequest.getProductId());
			//set application stage
			ApplicationStatusMaster applicationStatusMaster = new ApplicationStatusMaster();
			applicationStatusMaster.setId(CommonUtils.ApplicationStatus.OPEN);
			applicationProposalMapping.setApplicationStatusMaster(applicationStatusMaster);
			//set application ddr stage
			if(BusinessType.EXISTING_BUSINESS.getId() == loanApplicationMaster.getBusinessTypeId()) {
				applicationProposalMapping.setDdrStatusId(CommonUtils.DdrStatus.OPEN);
			}
			applicationProposalMapping.setApplicationCode(loanApplicationMaster.getApplicationCode());
			applicationProposalMapping.setIsPrimaryUploadFilled(true);
			applicationProposalMapping.setIsApplicantDetailsFilled(true);
			applicationProposalMapping.setIsApplicantPrimaryFilled(true);
			applicationProposalMapping.setIsPrimaryLocked(true);
			applicationProposalMapping.setBusinessTypeId(loanApplicationMaster.getBusinessTypeId());
			if (!CommonUtils.isObjectNullOrEmpty(loanApplicationRequest.getNpOrgId())) {
				applicationProposalMapping.setOrgId(loanApplicationRequest.getNpOrgId());
			}
			ApplicationProposalMapping existingDetails = applicationProposalMappingRepository.getByProposalIdAndApplicationId(proposalDetails.getId(),proposalDetails.getApplicationId());//getByApplicationId(proposalDetails.getApplicationId());
			if (!CommonUtils.isObjectNullOrEmpty(existingDetails) && !CommonUtils.isObjectNullOrEmpty(existingDetails.getApplicationCode())) {
				applicationProposalMapping
						.setApplicationCode(existingDetails.getApplicationCode());
			}else {
				LoanType type = CommonUtils.LoanType.getType(loanApplicationRequest.getProductId());
				if (!CommonUtils.isObjectNullOrEmpty(type)) {
					applicationProposalMapping
							.setApplicationCode(applicationSequenceService.getApplicationSequenceNumber(type.getValue())+"-"+loanApplicationMaster.getId());
				}
			}
			applicationProposalMapping.setUserId(loanApplicationMaster.getUserId());
			applicationProposalMapping.setCreatedBy(proposalDetails.getApplicationId());
			applicationProposalMapping.setCurrencyId(Currency.RUPEES.getId());
			applicationProposalMapping.setDenominationId(Denomination.ABSOLUTE.getId());
			applicationProposalMapping.setCreatedDate(new Date());
			applicationProposalMapping.setModifiedBy(proposalDetails.getApplicationId());
			applicationProposalMapping.setModifiedDate(new Date());
			applicationProposalMapping.setIsActive(true);
			applicationProposalMappingRepository.save(applicationProposalMapping);
		}else{
			LoanType type = CommonUtils.LoanType.getType(loanApplicationRequest.getProductId());
			loanApplicationMaster.setAmount(loanApplicationRequest.getAmount());
			loanApplicationMaster.setTenure(loanApplicationRequest.getTenure());
			loanApplicationMaster.setProductId(loanApplicationRequest.getProductId());
			loanApplicationMaster.setIsApplicantDetailsFilled(true);
			loanApplicationMaster.setIsApplicantPrimaryFilled(true);
			loanApplicationMaster.setIsPrimaryLocked(true);
			if (!CommonUtils.isObjectNullOrEmpty(loanApplicationRequest.getNpOrgId())) {
				loanApplicationMaster.setNpOrgId(loanApplicationRequest.getNpOrgId());
			}


			if (!CommonUtils.isObjectNullOrEmpty(type)) {
				loanApplicationMaster
						.setApplicationCode(applicationSequenceService.getApplicationSequenceNumber(type.getValue())+"-"+loanApplicationMaster.getId());
			}
			loanApplicationRepository.save(loanApplicationMaster);
//			if (CommonUtils.LoanType.WORKING_CAPITAL.getValue() == loanApplicationRequest.getProductId()) {
//				PrimaryWorkingCapitalLoanDetail wcLoan = primaryWorkingCapitalLoanDetailRepository
//						.findByApplicationIdIdAndIsActive(loanApplicationMaster.getId(), true);
//				if (CommonUtils.isObjectNullOrEmpty(wcLoan)) {
//					wcLoan = new PrimaryWorkingCapitalLoanDetail();
//					wcLoan.setId(loanApplicationMaster.getId());
//					wcLoan.setApplicationId(loanApplicationMaster);
//					primaryWorkingCapitalLoanDetailRepository.save(wcLoan);
//				}
//			} else if (CommonUtils.LoanType.TERM_LOAN.getValue() == loanApplicationRequest.getProductId()) {
//				PrimaryTermLoanDetail tlLoan = primaryTermLoanDetailRepository
//						.findByApplicationIdIdAndIsActive(loanApplicationMaster.getId(), true);
//				if (CommonUtils.isObjectNullOrEmpty(tlLoan)) {
//					tlLoan = new PrimaryTermLoanDetail();
//					tlLoan.setId(loanApplicationMaster.getId());
//					tlLoan.setApplicationId(loanApplicationMaster);
//					primaryTermLoanDetailRepository.save(tlLoan);
//				}
//			} else if (CommonUtils.LoanType.UNSECURED_LOAN.getValue() == loanApplicationRequest.getProductId()) {
//				PrimaryUnsecuredLoanDetail unsLoan = primaryUnsecuredLoanDetailRepository
//						.findByApplicationIdIdAndIsActive(loanApplicationMaster.getId(), true);
//				if (CommonUtils.isObjectNullOrEmpty(unsLoan)) {
//					unsLoan = new PrimaryUnsecuredLoanDetail();
//					unsLoan.setId(loanApplicationMaster.getId());
//					unsLoan.setApplicationId(loanApplicationMaster);
//					primaryUnsecuredLoanDetailRepository.save(unsLoan);
//				}
//			}
		}


		try {
			logger.info("Call Post Matches -------------------------------------->");
			ConnectResponse postMatches = connectClient.postMatches(loanApplicationMaster.getId(),
					loanApplicationMaster.getUserId(),
					!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getBusinessTypeId())
							? loanApplicationMaster.getBusinessTypeId()
							: CommonUtils.BusinessType.EXISTING_BUSINESS.getId());
			if (!CommonUtils.isObjectNullOrEmpty(postMatches)) {
				logger.info("Response form Connect client ---------------->" + postMatches.toString());
				logger.info("Successfully update loan data-------------------------------->"
						+ loanApplicationRequest.getId());
				return postMatches.getProceed();
			} else {
				logger.info("Response form Connect lient ---------------->" + null);
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		return false;
	}

	@Override
	public int inActiveApplication(Long id, Long userId) {
		logger.info("Entry in inActiveApplication");
		int inActiveApplication = loanApplicationRepository.inActiveApplication(id, userId);
		logger.info("Inactivated Count==================>{}", inActiveApplication);
		logger.info("Exit in inActiveApplication");
		return 0;
	}

	public Map<String, Object> getFpDetailsByFpProductId(Long fpProductId) throws LoansException {
		Map<String, Object> map = null;
		try {
			map = new HashMap<String, Object>();
			ProductMaster productMaster = productMasterRepository.findOne(fpProductId);
			if (productMaster != null) {
				UsersRequest request = new UsersRequest();
				request.setId(productMaster.getUserId());
				UserResponse resp = userClient.getEmailMobile(productMaster.getUserId());
				UsersRequest applicantRequest = MultipleJSONObjectHelper
						.getObjectFromMap((Map<String, Object>) resp.getData(), UsersRequest.class);
				map.put("fpName", applicantRequest.getName());
				map.put("mobileNo", applicantRequest.getMobile());
				map.put("fpUserId", productMaster.getUserId());
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}

		return map;
	}

	public CorporateProduct getFpDetailsByFpProductMappingId(Long fpProductMappingId) throws LoansException {
		logger.info("ENTER IN LOAN APPLICATIONSERVICEIMPL-------------FP PRODUCT MAPPING ID >>>>>>>>>>>"
				+ fpProductMappingId);

		ProductMaster productMaster = productMasterRepository.findByIdAndIsActive(fpProductMappingId, true);
		logger.info("RESPONSE------------------->>>>>>>>>>>" + productMaster);

		CorporateProduct corporateProduct = null;
		if (productMaster != null) {
			corporateProduct = new CorporateProduct();

			BeanUtils.copyProperties(productMaster, corporateProduct);

		}
		return corporateProduct;
	}

	public LoanApplicationRequest getLoanApplicationDetails(Long userId, Long applicationId) {

		LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);

		LoanApplicationRequest applicationRequest = new LoanApplicationRequest();

		BeanUtils.copyProperties(applicationMaster, applicationRequest);

		return applicationRequest;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.capitaworld.service.loans.service.fundseeker.corporate.
	 * LoanApplicationService#getDataForCGTMSE(java.lang.Long)
	 */
	@Override
	public CGTMSECalcDataResponse getDataForCGTMSE(Long applicationId) throws LoansException {
		try {

			logger.info("In getDataForCGTMSE");
			CGTMSECalcDataResponse response = new CGTMSECalcDataResponse();
			CorporateApplicantDetail applicantDetail = corporateApplicantDetailRepository
					.findByApplicationIdIdAndIsActive(applicationId, true);
			if (applicantDetail != null) {
				// key vertical Subsector
				try {
					if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getKeyVerticalSubsector())) {
						Long irrId = getIrrByApplicationId(applicationId);
						Integer businessTypeId = null;
						IrrRequest irrIndustryRequest = new IrrRequest();
						irrIndustryRequest.setIrrIndustryId(irrId);
						irrIndustryRequest = ratingClient.getIrrIndustry(irrIndustryRequest);
						IndustryResponse industryResponse = irrIndustryRequest.getIndustryResponse();
						if (!CommonUtils.isObjectNullOrEmpty(industryResponse)) {

							businessTypeId = industryResponse.getBusinessTypeId();
						}
						String natureOfEntity = null;

						if (com.capitaworld.service.rating.utils.CommonUtils.BusinessType.MANUFACTURING == businessTypeId) {
							natureOfEntity = "Manufacturer";
						} else if (com.capitaworld.service.rating.utils.CommonUtils.BusinessType.SERVICE == businessTypeId) {
							natureOfEntity = "Service";
						} else if (com.capitaworld.service.rating.utils.CommonUtils.BusinessType.TRADING == businessTypeId) {
							natureOfEntity = "Trader";
						}

						response.setSubSector(natureOfEntity);
					}
				} catch (Exception e) {
					logger.error("error while getting key vertical sub-sector : ",e);
				}

				response.setStateId(applicantDetail.getRegisteredStateId() != null
						? Long.valueOf(applicantDetail.getRegisteredStateId().toString())
						: null);

			}

			LoanApplicationMaster loan = loanApplicationRepository.getById(applicationId);

			if (loan != null) {
				response.setLoanAmount(loan.getAmount());
				response.setBusinessTypeId(loan.getBusinessTypeId());

			}

			List<DirectorBackgroundDetail> directorList = directorBackgroundDetailsRepository
					.listPromotorBackgroundFromAppId(applicationId);

			response.setDirectorRespo(new ArrayList<DirectorBackgroundDetailResponse>());
			if (directorList != null && !directorList.isEmpty()) {
				for (DirectorBackgroundDetail detail : directorList) {
					if (detail.getGender() == Gender.FEMALE.getId()) {
						DirectorBackgroundDetailResponse directorDetail = new DirectorBackgroundDetailResponse();
						BeanUtils.copyProperties(detail, directorDetail);
						directorDetail.setGender(Gender.getById(detail.getGender()).toString());
						directorDetail.setShareholding(detail.getShareholding());

						response.addDirectorDetail(directorDetail);
					}
				}
			}
			Calendar cal = Calendar.getInstance();
			Integer yearInt = cal.get(Calendar.YEAR);
			String year = String.valueOf(yearInt - 1);
			logger.info("YEAR ::::::::::::::::::::++++++++++++++>>>> " + year);
			List<Object[]> asset = assetsDetailsRepository.getCMADetail(applicationId, CommonUtils.AUDITED);
			logger.info("==================================>15");
			if (!CommonUtils.isObjectListNull(asset)) {
				response.setGrossBlock((Double) asset.get(0)[4]);
				logger.info("Successfully get from asset ");
			}

			try {

				PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateRepository
						.findOneByApplicationIdId(applicationId);
				response.setIsPurchaseOfEqup(false);

				if (primaryCorporateDetail != null) {
					response.setColleteralValue(primaryCorporateDetail.getCollateralSecurityAmount());
					if (primaryCorporateDetail.getAssessmentId() != null && primaryCorporateDetail.getAssessmentId() == AssessmentOptionForFS.EQUIPMENT_MACHINERY
							.getId() ) {
							response.setIsPurchaseOfEqup(true);
							response.setCostOfMachinery(primaryCorporateDetail.getCostOfMachinery());
					}
				}
				if (loan != null && loan.getBusinessTypeId() != null && loan.getBusinessTypeId() == 2) {
					response.setIsPurchaseOfEqup(true);
					if (primaryCorporateDetail != null) {
						response.setCostOfMachinery(primaryCorporateDetail.getProposedCost());
					}
				}
			} catch (Exception e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			return response;
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			throw new LoansException(e);
		}
	}

	@Override
	public LoanApplicationRequest getProposalDataFromApplicationId(Long applicationId) {

		try {
			logger.info("ENter in get Proposal Data From ApplicationId ------------------->" + applicationId);
			LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
			LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);

			if (CommonUtils.isObjectNullOrEmpty(loanApplicationMaster)) {
				logger.info("Application MAster response null or empty by above applicaiton iD");
				return null;
			}
			BeanUtils.copyProperties(loanApplicationMaster, applicationRequest);
			ProposalMappingResponse response = proposalDetailsClient.getInPricipleById(applicationId);
			if (response != null && response.getData() != null) {
				Map<String, Object> proposalresp = null;
				try {
					proposalresp = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) response.getData(),
							Map.class);
				} catch (IOException e) {
					logger.error("could not extract data : ",e);
				}

				if (!CommonUtils.isObjectNullOrEmpty(proposalresp)) {
					applicationRequest.setLoanAmount(
							proposalresp.get(CommonUtils.LITERAL_AMOUNT) != null ? Double.valueOf(proposalresp.get(CommonUtils.LITERAL_AMOUNT).toString())
									: 0.0);
					applicationRequest.setTenure(
							proposalresp.get(TENURE_LITERAL) != null ? Double.valueOf(proposalresp.get(TENURE_LITERAL).toString())
									: 0.0);
					applicationRequest.setEmiAmount(proposalresp.get("emi_amount") != null
							? Double.valueOf(proposalresp.get("emi_amount").toString())
							: 0.0);
					applicationRequest
							.setTypeOfLoan(CommonUtils.LoanType.getType(applicationRequest.getProductId()).toString());
					applicationRequest.setInterestRate(proposalresp.get(CommonUtils.RATE_INTEREST) != null
							? Double.valueOf(proposalresp.get(CommonUtils.RATE_INTEREST).toString())
							: 0.0);
					applicationRequest.setOnlinePaymentSuccess(true);

					CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
							.findOneByApplicationIdId(applicationId);
					if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
						applicationRequest.setNameOfEntity(corporateApplicantDetail.getOrganisationName());
					}
					Object orgObject = proposalresp.get(ORG_ID);
					if (!CommonUtils.isObjectNullOrEmpty(orgObject)) {
						Integer orgObjectInt = (Integer) orgObject;
						applicationRequest.setFundProvider(CommonUtils.getOrganizationName(orgObjectInt.longValue()));

					}
					return applicationRequest;
				} else {
					logger.info("Proposal Map is null or empty !!");
					return null;
				}
			} else {
				logger.info("Proposal Response is null or empty !!");
			}
		} catch (Exception e) {
			logger.error("Throw Exception WHile Get Proposal Detaisl By APplicationId : ",e);
		}
		return null;
	}




	@Override
	public HunterRequestDataResponse getDataForHunter(Long applicationId) throws LoansException {
		try {

			logger.info("In getDataForHunter with Application ID : " + applicationId);
			HunterRequestDataResponse response = new HunterRequestDataResponse();
			logger.info("Fetching Corporate Applicant Details for application Id : " + applicationId);
			CorporateApplicantDetail applicantDetail = corporateApplicantDetailRepository
					.findByApplicationIdIdAndIsActive(applicationId, true);
			if (applicantDetail != null) {
				logger.info("FetchedS Corporate Applicant Details for application Id : " + applicationId);
				// key vertical Subsector

				String state = null;
				List<Long> stateList = new ArrayList<>();
				if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredStateId()))
					stateList.add(Long.valueOf(applicantDetail.getRegisteredStateId()));
				if (!CommonUtils.isListNullOrEmpty(stateList)) {
					try {
						OneFormResponse oneFormResponse = oneFormClient.getStateByStateListId(stateList);
						List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
								.getListData();
						if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
							MasterResponse masterResponse = MultipleJSONObjectHelper
									.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
							state = masterResponse.getValue();
						}
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
				}

				String city = null;
				List<Long> cityList = new ArrayList<>();
				if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredCityId()))
					cityList.add(Long.valueOf(applicantDetail.getRegisteredCityId()));
				if (!CommonUtils.isListNullOrEmpty(cityList)) {
					try {
						OneFormResponse oneFormResponse = oneFormClient.getCityByCityListId(cityList);
						List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
								.getListData();
						if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
							MasterResponse masterResponse = MultipleJSONObjectHelper
									.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
							city = masterResponse.getValue();
						}
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
				}

				String country = null;
				List<Long> countryList = new ArrayList<>();
				if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredCountryId()))
					countryList.add(Long.valueOf(applicantDetail.getRegisteredCountryId()));
				if (!CommonUtils.isListNullOrEmpty(countryList)) {
					try {
						OneFormResponse oneFormResponse = oneFormClient.getCountryByCountryListId(countryList);
						List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
								.getListData();
						if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
							MasterResponse masterResponse = MultipleJSONObjectHelper
									.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
							country = masterResponse.getValue();
						}
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
				}

				response.setOrganisationName(applicantDetail.getOrganisationName());
				response.setCompanyCity(city);
				response.setCompanyState(state);
				response.setCompanyCountry(country);
				response.setCompanyPincode(applicantDetail.getRegisteredPincode() != null
						? String.valueOf(applicantDetail.getRegisteredPincode())
						: null);
				response.setColleteralValue(applicantDetail.getTotalCollateralDetails());
				response.setIndustry(getIndustryForHunter(applicantDetail.getKeyVericalFunding()));
				response.setCompanyTelephone(applicantDetail.getLandlineNo());
				response.setConstitution(getConstitutionryForHunter(applicantDetail.getConstitutionId()));

				response.setEstablishmentDate(applicantDetail.getEstablishmentYear() + "-"
						+ applicantDetail.getEstablishmentMonth() + "-" + "01");

				response.setCompanyAddress(applicantDetail.getRegisteredPremiseNumber() + ", "
						+ applicantDetail.getRegisteredStreetName() + ", " + applicantDetail.getRegisteredLandMark());
				response.setCompanyEmail(applicantDetail.getEmail());
			}
			logger.info("Fetching Loan APplication Master for application Id : " + applicationId);
			LoanApplicationMaster loan = loanApplicationRepository.getById(applicationId);

			if (loan != null) {
				logger.info("Fetched Loan APplication Master for application Id : " + applicationId);
				response.setLoanAmount(loan.getAmount());
				response.setLoanApplicationId(applicationId + "");

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				response.setDateOfApplication(dateFormat.format(loan.getCreatedDate()));
				response.setDateOfSubmission(dateFormat.format(new Date()));
			}
			logger.info("Fetching Corporate Primary details for application Id : " + applicationId);
			PrimaryCorporateDetail primaryCorporate = primaryCorporateRepository
					.findOneByApplicationIdId(applicationId);
			if (primaryCorporate != null) {
				response.setLoanType(String.valueOf(primaryCorporate.getPurposeOfLoanId()));

				logger.info("Fetching Corporate Primary details Purpose Of Loan from db: "
						+ primaryCorporate.getPurposeOfLoanId());
			}

			logger.info("Fetching Corporate Primary details Purpose Of Loan from change : " + response.getLoanType());

			logger.info("Fetching Director's background details for application Id : " + applicationId);
			List<DirectorBackgroundDetail> directorList = directorBackgroundDetailsRepository
					.listPromotorBackgroundFromAppId(applicationId);

			response.setDirectorRespo(new ArrayList<DirectorBackgroundDetailResponse>());
			if (directorList != null && !directorList.isEmpty()) {
				logger.info(FETCHED_DIRECTORS_BACKGROUND_DETAILS_FOR_APPLICATION_ID_MSG + applicationId);
				for (DirectorBackgroundDetail detail : directorList) {
					DirectorBackgroundDetailResponse directorDetail = new DirectorBackgroundDetailResponse();
					BeanUtils.copyProperties(detail, directorDetail);
					String gender = null;
					if (Gender.MALE.getId() == detail.getGender()) {
						gender = "MALE";
					} else if (Gender.FEMALE.getId() == detail.getGender()) {
						gender = "FEMALE";
					} else if (Gender.THIRD_GENDER.getId() == detail.getGender()) {
						gender = OTHER_LITERAL;
					} else {
						gender = OTHER_LITERAL;
					}
					directorDetail.setGender(gender);
					directorDetail.setShareholding(detail.getShareholding());
					directorDetail.setQualification(getQualificationForHunter(detail.getQualificationId()));
					directorDetail.setMaritalStatus(getMaritalStatusForHunter(detail.getMaritalStatus()));

					String state = null;
					List<Long> stateList = new ArrayList<>();

					if (!CommonUtils.isObjectNullOrEmpty(detail.getStateCode())) {
						ITRConnectionResponse itrConnectionResponse = itrClient
								.getOneFormStateIdFromITRStateId(Long.valueOf(detail.getStateCode()));
						if (!CommonUtils.isObjectNullOrEmpty(itrConnectionResponse)) {

							stateList.add(Long.valueOf(String.valueOf(itrConnectionResponse.getData())));
						}
					} else {
						stateList.add(Long.valueOf(detail.getStateId()));
					}
					if (!CommonUtils.isListNullOrEmpty(stateList)) {
						try {

							OneFormResponse oneFormResponse = oneFormClient.getStateByStateListId(stateList);
							List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
									.getListData();
							if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
								MasterResponse masterResponse = MultipleJSONObjectHelper
										.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
								state = masterResponse.getValue();
							}
						} catch (Exception e) {
							logger.error(CommonUtils.EXCEPTION,e);
						}
					}

					String country = null;
					List<Long> countryList = new ArrayList<>();
					if (!CommonUtils.isObjectNullOrEmpty(detail.getCountryId()))
						countryList.add(Long.valueOf(detail.getCountryId()));
					if (!CommonUtils.isListNullOrEmpty(countryList)) {
						try {
							OneFormResponse oneFormResponse = oneFormClient.getCountryByCountryListId(countryList);
							List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
									.getListData();
							if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
								MasterResponse masterResponse = MultipleJSONObjectHelper
										.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
								country = masterResponse.getValue();
							}
						} catch (Exception e) {
							logger.error(CommonUtils.EXCEPTION,e);
						}
					}

					String city = detail.getCity();
					List<Long> cityList = new ArrayList<>();
					if (!CommonUtils.isObjectNullOrEmpty(detail.getCityId()))
						cityList.add(Long.valueOf(detail.getCityId()));
					if (!CommonUtils.isListNullOrEmpty(cityList)) {
						try {
							OneFormResponse oneFormResponse = oneFormClient.getCityByCityListId(cityList);
							List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
									.getListData();
							if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
								MasterResponse masterResponse = MultipleJSONObjectHelper
										.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
								city = masterResponse.getValue();
							}
						} catch (Exception e) {
							logger.error(CommonUtils.EXCEPTION,e);
						}
					}

					directorDetail.setCountry(country);
					directorDetail.setStateCode(state);
					directorDetail.setCity(city);
					directorDetail.setPincode(detail.getPincode().toString());
					directorDetail.setIsMainDirector(detail.getIsMainDirector());
					response.addDirectorDetail(directorDetail);
				}
			}
			logger.info("Fetching Bank details for application Id : " + applicationId);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setApplicationId(applicationId);
			AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReport(reportRequest);

			if (analyzerResponse != null && analyzerResponse.getStatus() == HttpStatus.OK.value()) {
				logger.info(FETCHED_DIRECTORS_BACKGROUND_DETAILS_FOR_APPLICATION_ID_MSG + applicationId);
				Data data = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) analyzerResponse.getData(),
						Data.class);

				if (data != null && data.getSummaryInfo() != null) {
					response.setCompanyBankAccount(data.getSummaryInfo().getAccNo());
					response.setCompanyBankName(data.getSummaryInfo().getInstName());
				}
			}
			logger.info("End getDataForHunter with Application ID : " + applicationId);
			return response;
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			throw new LoansException(e);
		}
	}

	@Override
	public HunterRequestDataResponse getDataForHunterForNTB(Long applicationId) throws LoansException {
		try {

			logger.info("In getDataForHunter with Application ID : " + applicationId);
			HunterRequestDataResponse response = new HunterRequestDataResponse();
			LoanApplicationMaster loan = loanApplicationRepository.getById(applicationId);
			CorporateApplicantDetail applicantDetail = corporateApplicantDetailRepository
					.findByApplicationIdIdAndIsActive(applicationId, true);
			if (applicantDetail != null) {
				response.setColleteralValue(applicantDetail.getTotalCollateralDetails());
			}
			if (loan != null) {
				logger.info("Fetched Loan APplication Master for application Id : " + applicationId);
				response.setLoanAmount(loan.getAmount());
				response.setLoanApplicationId(applicationId + "");

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				response.setDateOfApplication(dateFormat.format(loan.getCreatedDate()));
				response.setDateOfSubmission(dateFormat.format(new Date()));
			}
			PrimaryCorporateDetail primaryCorporate = primaryCorporateRepository
					.findOneByApplicationIdId(applicationId);
			if (primaryCorporate != null) {
				response.setLoanType(String.valueOf(primaryCorporate.getPurposeOfLoanId()));
			}

			logger.info("Fetching Director's background details for application Id : " + applicationId);
			List<DirectorBackgroundDetail> directorList = directorBackgroundDetailsRepository
					.listPromotorBackgroundFromAppId(applicationId);

			response.setDirectorRespo(new ArrayList<DirectorBackgroundDetailResponse>());
			if (directorList != null && !directorList.isEmpty()) {
				logger.info(FETCHED_DIRECTORS_BACKGROUND_DETAILS_FOR_APPLICATION_ID_MSG + applicationId);
				for (DirectorBackgroundDetail detail : directorList) {
					DirectorBackgroundDetailResponse directorDetail = new DirectorBackgroundDetailResponse();
					BeanUtils.copyProperties(detail, directorDetail);
					String gender = null;
					if (Gender.MALE.getId() == detail.getGender()) {
						gender = "MALE";
					} else if (Gender.FEMALE.getId() == detail.getGender()) {
						gender = "FEMALE";
					} else if (Gender.THIRD_GENDER.getId() == detail.getGender()) {
						gender = OTHER_LITERAL;
					} else {
						gender = OTHER_LITERAL;
					}
					directorDetail.setGender(gender);
					directorDetail.setShareholding(detail.getShareholding());
					directorDetail.setQualification(getQualificationForHunter(detail.getQualificationId()));
					directorDetail.setMaritalStatus(getMaritalStatusForHunter(detail.getMaritalStatus()));

					String state = null;
					List<Long> stateList = new ArrayList<>();

					if (!CommonUtils.isObjectNullOrEmpty(detail.getStateCode())) {
						ITRConnectionResponse itrConnectionResponse = itrClient
								.getOneFormStateIdFromITRStateId(Long.valueOf(detail.getStateCode()));
						if (!CommonUtils.isObjectNullOrEmpty(itrConnectionResponse)) {

							stateList.add(Long.valueOf(String.valueOf(itrConnectionResponse.getData())));
						}
					} else {
						stateList.add(Long.valueOf(detail.getStateId()));
					}
					if (!CommonUtils.isListNullOrEmpty(stateList)) {
						try {

							OneFormResponse oneFormResponse = oneFormClient.getStateByStateListId(stateList);
							List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
									.getListData();
							if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
								MasterResponse masterResponse = MultipleJSONObjectHelper
										.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
								state = masterResponse.getValue();
							}
						} catch (Exception e) {
							logger.error(CommonUtils.EXCEPTION,e);
						}
					}

					String country = null;
					List<Long> countryList = new ArrayList<>();
					if (!CommonUtils.isObjectNullOrEmpty(detail.getCountryId()))
						countryList.add(Long.valueOf(detail.getCountryId()));
					if (!CommonUtils.isListNullOrEmpty(countryList)) {
						try {
							OneFormResponse oneFormResponse = oneFormClient.getCountryByCountryListId(countryList);
							List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
									.getListData();
							if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
								MasterResponse masterResponse = MultipleJSONObjectHelper
										.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
								country = masterResponse.getValue();
							}
						} catch (Exception e) {
							logger.error(CommonUtils.EXCEPTION,e);
						}
					}

					String city = detail.getCity();
					List<Long> cityList = new ArrayList<>();
					if (!CommonUtils.isObjectNullOrEmpty(detail.getCityId()))
						cityList.add(Long.valueOf(detail.getCityId()));
					if (!CommonUtils.isListNullOrEmpty(cityList)) {
						try {
							OneFormResponse oneFormResponse = oneFormClient.getCityByCityListId(cityList);
							List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
									.getListData();
							if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
								MasterResponse masterResponse = MultipleJSONObjectHelper
										.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
								city = masterResponse.getValue();
							}
						} catch (Exception e) {
							logger.error(CommonUtils.EXCEPTION,e);
						}
					}

					directorDetail.setCountry(country);
					directorDetail.setStateCode(state);
					directorDetail.setCity(city);
					directorDetail.setPincode(detail.getPincode().toString());

					directorDetail.setIsMainDirector(detail.getIsMainDirector());

					ReportRequest reportRequest = new ReportRequest();
					reportRequest.setApplicationId(applicationId);
					reportRequest.setDirectorId(detail.getId());
					AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReport(reportRequest);

					if (analyzerResponse.getStatus() == HttpStatus.OK.value()) {
						logger.info(FETCHED_DIRECTORS_BACKGROUND_DETAILS_FOR_APPLICATION_ID_MSG + applicationId);
						Data data = MultipleJSONObjectHelper
								.getObjectFromMap((Map<String, Object>) analyzerResponse.getData(), Data.class);

						if (data != null && data.getSummaryInfo() != null) {
							directorDetail.setDirectorBankAccount(data.getSummaryInfo().getAccNo());
							directorDetail.setDirectorBankName(data.getSummaryInfo().getInstName());
						}
					}
					if (detail.getResidenceSinceYear() != null && detail.getResidenceSinceMonth() != null) {
						LocalDate now = LocalDate.now();
						LocalDate before = LocalDate.of(detail.getResidenceSinceYear(), detail.getResidenceSinceMonth(),
								1);
						Long timeAtAddress = ChronoUnit.MONTHS.between(before, now);
						directorDetail.setTimeAtAddress(new BigInteger(String.valueOf(timeAtAddress)));
					}

					response.addDirectorDetail(directorDetail);
				}
			}
			logger.info("Fetching Bank details for application Id : " + applicationId);

			logger.info("End getDataForHunter with Application ID : " + applicationId);
			return response;
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			throw new LoansException(e);
		}
	}

	@Override
	public SanctioningDetailResponse getDetailsForSanction(DisbursementRequest disbursementRequest) throws LoansException {
		try {
			logger.info(
					"Start getDetailsForSanction with data application Id : " + disbursementRequest.getApplicationId()
							+ " ProductMapping Id :" + disbursementRequest.getProductMappingId());
			SanctioningDetailResponse sanctioningDetailResponse = new SanctioningDetailResponse();

			logger.info("Fetching data from in-principle: ");
			ProposalMappingResponse response = proposalDetailsClient.getActivateProposalById(
					disbursementRequest.getProductMappingId(), disbursementRequest.getApplicationId());
			Map<String, Object> proposalresp = MultipleJSONObjectHelper
					.getObjectFromMap((Map<String, Object>) response.getData(), Map.class);
			if (proposalresp != null) {
				sanctioningDetailResponse.setSanctionAmount(
						proposalresp.get("elAmount") != null ? Double.valueOf(proposalresp.get("elAmount").toString())
								: 0.0);
				sanctioningDetailResponse.setTenure(
						proposalresp.get("elTenure") != null ? Double.valueOf(proposalresp.get("elTenure").toString())
								: 0.0);
				sanctioningDetailResponse.setRoi(
						proposalresp.get("elRoi") != null ? Double.valueOf(proposalresp.get("elRoi").toString()) : 0.0);
				sanctioningDetailResponse.setProcessingFee(proposalresp.get("processingFee") != null
						? Double.valueOf(proposalresp.get("processingFee").toString())
						: 0.0);
				sanctioningDetailResponse.setBranch(
						proposalresp.get("branchId") != null ? Long.valueOf(proposalresp.get("branchId").toString())
								: null);
				sanctioningDetailResponse.setUserOrgId(
						proposalresp.get("userOrgId") != null ? Long.valueOf(proposalresp.get("userOrgId").toString())
								: null);
			}

			logger.info("Fetching data for proposal: ");
			DisbursementRequest disbursementDetailsResponse = getDisbursementDetails(disbursementRequest);

			if (disbursementDetailsResponse != null) {
				BeanUtils.copyProperties(disbursementDetailsResponse, sanctioningDetailResponse, TENURE_LITERAL, "roi",
						"userId");
			}
			logger.info("End getDetailsForSanction with data application Id : " + disbursementRequest.getApplicationId()
					+ " ProductMapping Id :" + disbursementRequest.getProductMappingId());
			return sanctioningDetailResponse;
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			throw new LoansException(e);
		}
	}

	private String getQualificationForHunter(Integer qualificationId) {
		if (qualificationId != null) {
			if (EducationQualificationNTB.TECHNICAL.getId() == qualificationId) {
				return "ENGINEER";
			} else if (EducationQualificationNTB.IIT.getId() == qualificationId) {
				return "IT - TECH DIPLOMA";
			} else if (EducationQualificationNTB.IIM.getId() == qualificationId) {
				return "POST GRADUATE";
			} else if (EducationQualificationNTB.PROFESSIONAL.getId() == qualificationId) {
				return "PROFESSIONAL";
			} else if (EducationQualificationNTB.CA.getId() == qualificationId) {
				return "PROFESSIONAL";
			} else if (EducationQualificationNTB.OTHERS.getId() == qualificationId) {
				return OTHER_LITERAL;
			} else {
				return OTHER_LITERAL;
			}
		}
		return null;
	}

	private String getMaritalStatusForHunter(Integer maritakStatusId) {
		if (maritakStatusId != null) {
			if (MaritalStatus.MARRIED.getId() == maritakStatusId) {
				return "MARRIED";
			} else if (MaritalStatus.SINGLE.getId() == maritakStatusId) {
				return "SINGLE";
			} else if (MaritalStatus.DIVORCED.getId() == maritakStatusId) {
				return "DIVORCED";
			} else if (MaritalStatus.WIDOWED.getId() == maritakStatusId) {
				return "WIDOWED";
			}
		}
		return null;
	}

	private String getIndustryForHunter(Long industryId) {
		if (industryId != null) {
			Integer indId = Integer.valueOf(industryId.intValue());
			if (Industry.AGRICULTURE_ALLIED_ACTIVITIES.getId() == indId) {
				return "AGRICULTURE";
			} else if (Industry.DEFENCE.getId() == indId) {
				return "ARMED FORCES";
			}

			else if (Industry.CONSTRUCTION_MATERIAL.getId() == indId) {
				return "CONSTRUCTION";
			} else if (Industry.EDUCATION.getId() == indId) {
				return "EDUCATION";
			} else if (Industry.ENGINEERING_CAPITAL_GOODS.getId() == indId) {
				return "ENGINEERING";
			} else if (Industry.ENTERTAINMENT_MEDIA.getId() == indId) {
				return "ENTERTAINMENT";
			} else if (Industry.FINANCE_FINANCIAL_SERVICES.getId() == indId) {
				return "FINANCIAL SERVICES";
			} else if (Industry.FOOD_BEVERAGES.getId() == indId) {
				return "FOOD";
			} else if (Industry.HEALTHCARE.getId() == indId) {
				return "HEALTHCARE";
			} else if (Industry.TRAVEL_HOSPITALITY.getId() == indId) {
				return "HOSPITALITY AND TOURISM";
			} else if (Industry.IT_ITES.getId() == indId) {
				return "INFORMATION TECHNOLOGY";
			} else if (Industry.CONSUMER_DURABLES.getId() == indId) {
				return "MANAFACTURING";
			} else if (Industry.OILGAS.getId() == indId) {
				return "NATURAL RESOURCES";
			} else if (Industry.MINERALS_COMMODITIES.getId() == indId) {
				return "NATURAL RESOURCES";
			} else if (Industry.REAL_ESTATE.getId() == indId) {
				return "REAL ESTATE";
			} else if (Industry.RETAIL_ECOMMERCE.getId() == indId) {
				return "RETAIL";
			} else if (Industry.TELECOMMUNICATION.getId() == indId) {
				return "TELECOMMUNICATIONS";
			} else if (Industry.TEXTILES.getId() == indId) {
				return "TEXTILES";
			} else if (Industry.SHIPPING_LOGISTICS.getId() == indId) {
				return "TRANSPORT AND LOGISTICS";
			} else {
				return OTHER_LITERAL;
			}
		} else {
			return null;
		}
	}

	private String getConstitutionryForHunter(Integer constitutionId) {
		if (constitutionId != null) {
			if (Constitution.PRIVATE_LIMITED.getId() == constitutionId) {
				return "PRIVATE LIMITED CO";
			} else if (Constitution.PUBLIC_LISTED.getId() == constitutionId) {
				return "PUBLIC LIMITED CO";
			}

			else if (Constitution.PUBLIC_UNLISTED.getId() == constitutionId) {
				return "PUBLIC LIMITED CO";
			} else if (Constitution.FOREIGN_COMPANY.getId() == constitutionId) {
				return "MULTI NATIONAL";
			} else if (Constitution.SOLE_PROPRIETORSHIP.getId() == constitutionId) {
				return "PROPRIETORSHIP";
			} else if (Constitution.ONE_PERSON.getId() == constitutionId) {
				return "PROPRIETORSHIP";
			} else if (Constitution.PARTNERSHIP.getId() == constitutionId) {
				return "PARTNERSHIP";
			} else if (Constitution.GOVERNMENT_ENTITY.getId() == constitutionId) {
				return "STATE GOVERNMENT";
			} else {
				return "OTHERS";
			}
		} else {
			return null;
		}
	}

	@Override
	public String saveDetailedInfo(ProfileReqRes profileReqRes) throws LoansException {
		logger.info("================== Enter in saveDetailedInfo =============== ");
		Long applicationId = null;
		CorporateProfileRequest corporateProfileRequest = profileReqRes.getCorporateProfileRequest();

		if (!CommonUtils.isObjectListNull(corporateProfileRequest, corporateProfileRequest.getApplicationId(),
				corporateProfileRequest.getUdhyogAdhaar(), corporateProfileRequest.getAdministrativeAddress(),
				corporateProfileRequest.getAdministrativeAddress().getCityId(),
				corporateProfileRequest.getAdministrativeAddress().getStateId(),
				corporateProfileRequest.getAdministrativeAddress().getCountryId(),
				corporateProfileRequest.getAdministrativeAddress().getPincode(),
				corporateProfileRequest.getContLiabilityFyAmt(), corporateProfileRequest.getContLiabilitySyAmt(),
				corporateProfileRequest.getContLiabilityTyAmt())) {
			throw new LoansException(
					"Mandatory field must not be null (** applicationId , udhyogAddhar , city , state , country , pincode , ContLiabilityFyAmt ,  ContLiabilitySyAmt ,  ContLiabilityTyAmt ** ) ");
		}
		applicationId = saveCorporateProfile(profileReqRes.getCorporateProfileRequest());
		if (CommonUtils.isObjectListNull(applicationId)) {
			throw new LoansException("Invalid applicationId");
		}
		logger.info("Sucessfully save CorporateProfile =============> ");
		if (!CommonUtils.isListNullOrEmpty(profileReqRes.getAchievementList())) {
			saveAchivementDetail(applicationId, profileReqRes.getAchievementList());
			logger.info("Sucessfully save AchivementDetail =============> ");
		}

		if (!CommonUtils.isListNullOrEmpty(profileReqRes.getAssociateConcernList())) {
			saveAssociatedConcernDetail(applicationId, profileReqRes.getAssociateConcernList());
			logger.info("Sucessfully save AssociatedConcernDetailList =============> ");
		}

		if (!CommonUtils.isListNullOrEmpty(profileReqRes.getCreditRatingOrgList())) {
			saveCreditRatingOrganizationDetail(applicationId, profileReqRes.getCreditRatingOrgList());
			logger.info("Sucessfully save CreditRatingOrganizationDetailList =============> ");
		}

		if (!CommonUtils.isListNullOrEmpty(profileReqRes.getFinanceMeansDetailRequestsList())) {
			saveFinanceMeansDetail(applicationId, profileReqRes.getFinanceMeansDetailRequestsList());
			logger.info("Sucessfully save FinanceMeansDetailRequestsList =============> ");
		}
		if (!CommonUtils.isListNullOrEmpty(profileReqRes.getExPrList())) {
			if (!saveExistingProductDetail(applicationId, profileReqRes.getExPrList())) {
				throw new LoansException("Mandatory field must not be null ( ** Product and Application ** )");
			}
			logger.info("Sucessfully save ExistingProductDetailList =============> ");
		}
		if (!CommonUtils.isListNullOrEmpty(profileReqRes.getGuaCorpList())) {
			saveGuarantorsCorporateDetail(applicationId, profileReqRes.getGuaDetailList());
			logger.info("Sucessfully save GuarantorsCorporateDetailList  =============> ");
		}
		if (!CommonUtils.isListNullOrEmpty(profileReqRes.getMonTurnoverList())) {
			if (!saveMonthlyTurnoverDetail(applicationId, profileReqRes.getMonTurnoverList())) {
				throw new LoansException("Mandatory field must not be null (** MonthNamne and TurnOver ** )");
			}
			logger.info("Sucessfully save MonthlyTurnoverDetailList  =============> ");
		}
		/*
		 * if(!CommonUtils.isListNullOrEmpty(profileReqRes.getOwnerShipDetailsList())) {
		 * saveOwnershipDetail(applicationId, profileReqRes.getOwnerShipDetailsList());
		 * logger.info("Sucessfully save OwnershipDetailList  =============> "); }
		 */
		if (!CommonUtils.isListNullOrEmpty(profileReqRes.getPromotorBackgroundDetailRequestsList())) {
			if (!savePromotorBackgroundDetail(applicationId, profileReqRes.getPromotorBackgroundDetailRequestsList())) {
				throw new LoansException(
						"Mandsatory field must not be null ( ** salutationId , name , panNo , designation , address , mobileNO , dob , totalExperience and networth ** )");
			}
			logger.info("Sucessfully save PromotorBackgroundDetail =============> ");
		}
		if (!CommonUtils.isListNullOrEmpty(profileReqRes.getProposedProdList())) {
			if (!saveProposedProductDetail(applicationId, profileReqRes.getProposedProdList())) {
				throw new LoansException(
						"Mandsatory field must not be null ( ** product/proposed and application ** )");
			}
			logger.info("Sucessfully save ProposedProductDetailList  =============> ");
		}
		if (!CommonUtils.isListNullOrEmpty(profileReqRes.getCostOfProjectRequestsList())) {
			saveTotalCostOfProject(applicationId, profileReqRes.getCostOfProjectRequestsList());
			logger.info("Sucessfully save TotalCostOfProjectList  =============> ");
		}
		if (!CommonUtils.isListNullOrEmpty(profileReqRes.getSecurityCorporateDetailRequestsList())) {
			saveSecurityCorporateDetail(applicationId, profileReqRes.getSecurityCorporateDetailRequestsList());
			logger.info("Sucessfully save SecurityCorporateDetailList  =============> ");
		}
		logger.info("=============== Exit from saveDetailedInfo ============== ");

		return "Sucess";
	}

	public Long saveCorporateProfile(CorporateProfileRequest corporateProfileRequest) {

		CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
				.getByApplicationIdAndIsAtive(corporateProfileRequest.getApplicationId());
		if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
			corporateApplicantDetail.setAadhar(corporateProfileRequest.getUdhyogAdhaar());
			corporateApplicantDetail.setAboutUs(corporateProfileRequest.getAbout());

			corporateApplicantDetail.setAdministrativePremiseNumber(
					corporateProfileRequest.getAdministrativeAddress().getPremiseNumber());
			corporateApplicantDetail
					.setAdministrativeStreetName(corporateProfileRequest.getAdministrativeAddress().getStreetName());
			corporateApplicantDetail
					.setAdministrativeLandMark(corporateProfileRequest.getAdministrativeAddress().getLandMark());
			corporateApplicantDetail
					.setAdministrativeCityId(corporateProfileRequest.getAdministrativeAddress().getCityId());
			corporateApplicantDetail
					.setAdministrativeStateId(corporateProfileRequest.getAdministrativeAddress().getStateId());
			corporateApplicantDetail
					.setAdministrativeCountryId(corporateProfileRequest.getAdministrativeAddress().getCountryId());
			corporateApplicantDetail
					.setAdministrativePincode(corporateProfileRequest.getAdministrativeAddress().getPinCode());
			corporateApplicantDetail.setContLiabilityFyAmt(corporateProfileRequest.getContLiabilityFyAmt());
			corporateApplicantDetail.setContLiabilitySyAmt(corporateProfileRequest.getContLiabilitySyAmt());
			corporateApplicantDetail.setContLiabilityTyAmt(corporateProfileRequest.getContLiabilityTyAmt());

			corporateApplicantDetail.setModifiedBy(corporateProfileRequest.getApplicationId());
			corporateApplicantDetail.setModifiedDate(new Date());
			corporateApplicantDetail.setIsActive(true);
			return corporateApplicantDetailRepository.save(corporateApplicantDetail).getApplicationId().getId();
		}
		return null;

	}

	public Boolean saveAchivementDetail(Long applicationId, List<AchievementDetailRequest> achievementList) {
		AchievementDetail achievementDetail = null;
		try {
			achievementDetailsRepository.inActive(null, applicationId);
			for (AchievementDetailRequest achievementDetailRequest : achievementList) {
				achievementDetail = new AchievementDetail();

				achievementDetail.setMilestoneAchievedDetail(achievementDetailRequest.getMilestoneAchievedDetail());
				achievementDetail.setYear(achievementDetailRequest.getYear());

				achievementDetail.setCreatedBy(applicationId);
				achievementDetail.setCreatedDate(new Date());
				achievementDetail.setModifiedBy(applicationId);
				achievementDetail.setModifiedDate(new Date());
				achievementDetail.setIsActive(true);
				achievementDetailsRepository.save(achievementDetail);
			}
			return true;
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			return false;
		}
	}

	public Boolean saveAssociatedConcernDetail(Long applicationId,
			List<AssociatedConcernDetailRequest> associateConcernList) {
		AssociatedConcernDetail associatedConcernDetail = null;
		try {
			associatedConcernDetailRepository.inActive(null, applicationId);
			for (AssociatedConcernDetailRequest associatedConcernDetailRequest : associateConcernList) {
				associatedConcernDetail = new AssociatedConcernDetail();

				associatedConcernDetail.setName(associatedConcernDetailRequest.getName());
				associatedConcernDetail.setNatureAssociation(associatedConcernDetailRequest.getNatureAssociation());
				associatedConcernDetail.setNameOfDirector(associatedConcernDetailRequest.getNameOfDirector());
				associatedConcernDetail.setInvestedAmount(associatedConcernDetailRequest.getInvestedAmount());
				associatedConcernDetail.setNatureActivity(associatedConcernDetailRequest.getNatureActivity());
				associatedConcernDetail.setTurnOverFirstYear(associatedConcernDetailRequest.getTurnOverFirstYear());
				associatedConcernDetail.setTurnOverSecondYear(associatedConcernDetailRequest.getTurnOverSecondYear());
				associatedConcernDetail.setTurnOverThirdYear(associatedConcernDetailRequest.getTurnOverThirdYear());
				associatedConcernDetail.setProfitPastOneYear(associatedConcernDetailRequest.getProfitPastOneYear());
				associatedConcernDetail.setProfitPastTwoYear(associatedConcernDetailRequest.getProfitPastTwoYear());
				associatedConcernDetail.setProfitPastThreeYear(associatedConcernDetailRequest.getProfitPastThreeYear());
				associatedConcernDetail.setBriefDescription(associatedConcernDetailRequest.getBriefDescription());
				associatedConcernDetail.setApplicationId(new LoanApplicationMaster(applicationId));

				associatedConcernDetail.setCreatedBy(applicationId);
				associatedConcernDetail.setCreatedDate(new Date());
				associatedConcernDetail.setModifiedBy(applicationId);
				associatedConcernDetail.setModifiedDate(new Date());
				associatedConcernDetail.setIsActive(true);
				associatedConcernDetailRepository.save(associatedConcernDetail);
			}
			return true;
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			return false;
		}
	}

	public Boolean saveCreditRatingOrganizationDetail(Long applicationId,
			List<CreditRatingOrganizationDetailRequest> creditRatingOrgList) {
		CreditRatingOrganizationDetail creditRatingOrganizationDetail = null;
		try {
			creditRatingOrganizationDetailsRepository.inActive(null, applicationId);
			for (CreditRatingOrganizationDetailRequest creditRatingOrganizationDetailRequest : creditRatingOrgList) {
				creditRatingOrganizationDetail = new CreditRatingOrganizationDetail();

				creditRatingOrganizationDetail.setRatingDate(creditRatingOrganizationDetailRequest.getRatingDate());
				creditRatingOrganizationDetail
						.setRatingAgencyId(creditRatingOrganizationDetailRequest.getRatingAgencyId());
				creditRatingOrganizationDetail.setFacilityName(creditRatingOrganizationDetailRequest.getFacilityName());
				creditRatingOrganizationDetail
						.setCreditRatingFundId(creditRatingOrganizationDetailRequest.getCreditRatingFundId());
				creditRatingOrganizationDetail
						.setCreditRatingTermId(creditRatingOrganizationDetailRequest.getCreditRatingTermId());
				creditRatingOrganizationDetail.setAmount(creditRatingOrganizationDetailRequest.getAmount());
				creditRatingOrganizationDetail
						.setCreditRatingOptionId(creditRatingOrganizationDetailRequest.getCreditRatingOptionId());

				creditRatingOrganizationDetail.setCreatedBy(applicationId);
				creditRatingOrganizationDetail.setCreatedDate(new Date());
				creditRatingOrganizationDetail.setModifiedBy(applicationId);
				creditRatingOrganizationDetail.setModifiedDate(new Date());
				creditRatingOrganizationDetail.setIsActive(true);
				creditRatingOrganizationDetailsRepository.save(creditRatingOrganizationDetail);
			}
			return true;
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			return false;
		}
	}

	public Boolean saveExistingProductDetail(Long applicationId, List<ExistingProductDetailRequest> exPrList) {
		ExistingProductDetail existingProductDetail = null;
		if (CommonUtils.isObjectNullOrEmpty(exPrList.get(0))) {
			return false;
		}
		existingProductDetailsRepository.inActive(null, applicationId);
		for (ExistingProductDetailRequest existingProductDetailRequest : exPrList) {
			if (CommonUtils.isObjectListNull(existingProductDetailRequest.getProduct(),
					existingProductDetailRequest.getApplication())) {
				return false;
			}

			existingProductDetail = new ExistingProductDetail();

			existingProductDetail.setProduct(existingProductDetailRequest.getProduct());
			existingProductDetail.setApplication(existingProductDetailRequest.getApplication());
			existingProductDetail.setApplicationId(new LoanApplicationMaster(applicationId));

			existingProductDetail.setCreatedBy(applicationId);
			existingProductDetail.setCreatedDate(new Date());
			existingProductDetail.setModifiedBy(applicationId);
			existingProductDetail.setModifiedDate(new Date());
			existingProductDetail.setIsActive(true);
			existingProductDetailsRepository.save(existingProductDetail);
		}
		return true;
	}

	public Boolean saveFinanceMeansDetail(Long applicationId,
			List<FinanceMeansDetailRequest> financeMeansDetailRequestsList) {
		FinanceMeansDetail financeMeansDetail = null;
		try {
			financeMeansDetailRepository.inActive(null, applicationId);
			for (FinanceMeansDetailRequest financeMeansDetailRequest : financeMeansDetailRequestsList) {
				financeMeansDetail = new FinanceMeansDetail();

				financeMeansDetail.setFinanceMeansCategoryId(financeMeansDetailRequest.getFinanceMeansCategoryId());
				financeMeansDetail.setTotal(financeMeansDetailRequest.getTotal());
				financeMeansDetail.setAlreadyInfused(financeMeansDetailRequest.getAlreadyInfused());
				financeMeansDetail.setApplicationId(new LoanApplicationMaster(applicationId));

				financeMeansDetail.setCreatedBy(applicationId);
				financeMeansDetail.setCreatedDate(new Date());
				financeMeansDetail.setModifiedBy(applicationId);
				financeMeansDetail.setModifiedDate(new Date());
				financeMeansDetail.setIsActive(true);
				financeMeansDetailRepository.save(financeMeansDetail);
			}
			return true;
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			return false;
		}
	}

	public Boolean saveGuarantorsCorporateDetail(Long applicationId,
			List<GuarantorsCorporateDetailRequest> guaCorpList) {
		GuarantorsCorporateDetail guarantorsCorporateDetail = null;
		try {
			guarantorsCorporateDetailRepository.inActive(null, applicationId);
			for (GuarantorsCorporateDetailRequest guarantorsCorporateDetailRequest : guaCorpList) {
				guarantorsCorporateDetail = new GuarantorsCorporateDetail();

				guarantorsCorporateDetail.setName(guarantorsCorporateDetailRequest.getName());
				guarantorsCorporateDetail.setPropertiesOwned(guarantorsCorporateDetailRequest.getPropertiesOwned());
				guarantorsCorporateDetail.setPropertyType(guarantorsCorporateDetailRequest.getValue());
				guarantorsCorporateDetail.setAddress(guarantorsCorporateDetailRequest.getAddress());
				guarantorsCorporateDetail.setOccupation(guarantorsCorporateDetailRequest.getOccupation());
				guarantorsCorporateDetail.setApplicationId(new LoanApplicationMaster(applicationId));

				guarantorsCorporateDetail.setCreatedBy(applicationId);
				guarantorsCorporateDetail.setCreatedDate(new Date());
				guarantorsCorporateDetail.setModifiedBy(applicationId);
				guarantorsCorporateDetail.setModifiedDate(new Date());
				guarantorsCorporateDetail.setIsActive(true);
				guarantorsCorporateDetailRepository.save(guarantorsCorporateDetail);
			}
			return true;
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			return false;
		}
	}

	public Boolean saveMonthlyTurnoverDetail(Long applicationId, List<MonthlyTurnoverDetailRequest> monTurnoverList) {
		MonthlyTurnoverDetail monthlyTurnoverDetail = null;
		if (CommonUtils.isObjectNullOrEmpty(monTurnoverList.get(0))) {
			return false;
		}
		monthlyTurnoverDetailRepository.inActive(null, applicationId);
		for (MonthlyTurnoverDetailRequest monthlyTurnoverDetailRequest : monTurnoverList) {
			if (CommonUtils.isObjectListNull(monthlyTurnoverDetailRequest.getMonthName(),
					monthlyTurnoverDetailRequest.getAmount())) {
				return false;
			}
			monthlyTurnoverDetail = new MonthlyTurnoverDetail();

			monthlyTurnoverDetail.setMonthName(monthlyTurnoverDetailRequest.getMonthName());
			monthlyTurnoverDetail.setAmount(monthlyTurnoverDetailRequest.getAmount());
			monthlyTurnoverDetail.setApplicationId(new LoanApplicationMaster(applicationId));

			monthlyTurnoverDetail.setCreatedBy(applicationId);
			monthlyTurnoverDetail.setCreatedDate(new Date());
			monthlyTurnoverDetail.setModifiedBy(applicationId);
			monthlyTurnoverDetail.setModifiedDate(new Date());
			monthlyTurnoverDetail.setIsActive(true);
			monthlyTurnoverDetailRepository.save(monthlyTurnoverDetail);
		}
		return true;
	}
	/*
	 * public Boolean saveOwnershipDetail(Long applicationId
	 * ,List<OwnershipDetailRequest> ownerShipDetailsList) { OwnershipDetail
	 * ownershipDetail =null;
	 * if(CommonUtils.isObjectNullOrEmpty(ownerShipDetailsList.get(0))) { return
	 * false; } for (OwnershipDetailRequest ownershipDetailRequest :
	 * ownerShipDetailsList) { if(true);
	 * 
	 * ownershipDetail= new OwnershipDetail();
	 * 
	 * //ownershipDetail.setShareHoldingCategoryId(ownershipDetailRequest.
	 * getShareHoldingCategoryType());
	 * ownershipDetail.setStackPercentage(ownershipDetailRequest.getStackPercentage(
	 * )); ownershipDetail.setRemarks(ownershipDetailRequest.getRemarks());
	 * ownershipDetail.setApplicationId(new LoanApplicationMaster(applicationId));
	 * 
	 * ownershipDetail.setModifiedBy(ownershipDetailRequest.getApplicationId());
	 * ownershipDetail.setModifiedDate(new Date());
	 * ownershipDetail.setIsActive(true);
	 * ownershipDetailsRepository.save(ownershipDetail); } return true; }
	 */

	public Boolean savePromotorBackgroundDetail(Long applicationId,
			List<PromotorBackgroundDetailRequest> promotorBackgroundDetailRequestsList) {
		PromotorBackgroundDetail promotorBackgroundDetail = null;
		if (CommonUtils.isObjectNullOrEmpty(promotorBackgroundDetailRequestsList.get(0))) {
			return false;
		}
		promotorBackgroundDetailsRepository.inActive(null, applicationId);
		for (PromotorBackgroundDetailRequest promotorBackgroundDetailRequest : promotorBackgroundDetailRequestsList) {
			if (CommonUtils.isObjectListNull(promotorBackgroundDetailRequest,
					promotorBackgroundDetailRequest.getSalutationId(),
					promotorBackgroundDetailRequest.getPromotorsName(), promotorBackgroundDetailRequest.getPanNo(),
					promotorBackgroundDetailRequest.getDesignation(), promotorBackgroundDetailRequest.getAddress(),
					promotorBackgroundDetailRequest.getMobile(), promotorBackgroundDetailRequest.getDob(),
					promotorBackgroundDetailRequest.getTotalExperience(),
					promotorBackgroundDetailRequest.getNetworth())) {
				return false;
			}

			promotorBackgroundDetail = new PromotorBackgroundDetail();

			promotorBackgroundDetail.setSalutationId(promotorBackgroundDetailRequest.getSalutationId());
			promotorBackgroundDetail.setPromotorsName(promotorBackgroundDetailRequest.getPromotorsName());
			promotorBackgroundDetail.setPanNo(promotorBackgroundDetailRequest.getPanNo());
			promotorBackgroundDetail.setDin(promotorBackgroundDetailRequest.getDin());
			promotorBackgroundDetail.setDesignation(promotorBackgroundDetailRequest.getDesignation());
			promotorBackgroundDetail.setAddress(promotorBackgroundDetailRequest.getAddress());
			promotorBackgroundDetail.setMobile(promotorBackgroundDetailRequest.getMobile());
			promotorBackgroundDetail.setDob(promotorBackgroundDetailRequest.getDob());
			promotorBackgroundDetail.setTotalExperience(promotorBackgroundDetailRequest.getTotalExperience());
			promotorBackgroundDetail.setNetworth(promotorBackgroundDetailRequest.getNetworth());
			promotorBackgroundDetail.setApplicationId(new LoanApplicationMaster(applicationId));

			promotorBackgroundDetail.setCreatedBy(applicationId);
			promotorBackgroundDetail.setCreatedDate(new Date());
			promotorBackgroundDetail.setModifiedBy(promotorBackgroundDetailRequest.getApplicationId());
			promotorBackgroundDetail.setModifiedDate(new Date());
			promotorBackgroundDetail.setIsActive(true);
			promotorBackgroundDetailsRepository.save(promotorBackgroundDetail);
		}
		return true;

	}

	public Boolean saveProposedProductDetail(Long applicationId, List<ProposedProductDetailRequest> proposedProdList) {
		ProposedProductDetail proposedProductDetail = null;

		if (CommonUtils.isObjectNullOrEmpty(proposedProdList.get(0))) {
			return false;
		}
		proposedProductDetailsRepository.inActive(null, applicationId);
		for (ProposedProductDetailRequest proposedProductDetailRequest : proposedProdList) {
			if (CommonUtils.isObjectListNull(proposedProductDetailRequest.getProduct(),
					proposedProductDetailRequest.getApplication())) {
				return false;
			}

			proposedProductDetail = new ProposedProductDetail();

			proposedProductDetail.setApplication(proposedProductDetailRequest.getApplication());
			proposedProductDetail.setProduct(proposedProductDetailRequest.getProduct());
			proposedProductDetail.setApplicationId(new LoanApplicationMaster(applicationId));

			proposedProductDetail.setCreatedBy(applicationId);
			proposedProductDetail.setCreatedDate(new Date());
			proposedProductDetail.setModifiedBy(proposedProductDetailRequest.getApplicationId());
			proposedProductDetail.setModifiedDate(new Date());
			proposedProductDetail.setIsActive(true);
			proposedProductDetailsRepository.save(proposedProductDetail);
		}
		return true;
	}

	public Boolean saveTotalCostOfProject(Long applicationId,
			List<TotalCostOfProjectRequest> costOfProjectRequestsList) {
		TotalCostOfProject totalCostOfProject = null;
		try {
			totalCostOfProjectRepository.inActive(null, applicationId);
			for (TotalCostOfProjectRequest totalCostOfProjectRequest : costOfProjectRequestsList) {
				totalCostOfProject = new TotalCostOfProject();

				totalCostOfProject.setAlreadyIncurred(totalCostOfProjectRequest.getAlreadyIncurred());
				totalCostOfProject.setTotalCost(totalCostOfProjectRequest.getTotalCost());
				totalCostOfProject.setParticularsId(totalCostOfProject.getParticularsId());
				totalCostOfProject.setApplicationId(new LoanApplicationMaster(applicationId));

				totalCostOfProject.setCreatedBy(applicationId);
				totalCostOfProject.setCreatedDate(new Date());
				totalCostOfProject.setModifiedBy(applicationId);
				totalCostOfProject.setModifiedDate(new Date());
				totalCostOfProject.setIsActive(true);
				totalCostOfProjectRepository.save(totalCostOfProject);
			}
			return true;
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			return false;
		}
	}

	public Boolean saveSecurityCorporateDetail(Long applicationId,
			List<SecurityCorporateDetailRequest> securityCorporateDetailRequestsList) {
		SecurityCorporateDetail securityCorporateDetail = null;
		try {
			securityCorporateDetailsRepository.inActive(null, applicationId);
			for (SecurityCorporateDetailRequest securityCorporateDetailRequest : securityCorporateDetailRequestsList) {
				securityCorporateDetail = new SecurityCorporateDetail();

				securityCorporateDetail.setPrimarySecurityName(securityCorporateDetailRequest.getPrimarySecurityName());
				securityCorporateDetail.setAmount(securityCorporateDetailRequest.getAmount());
				securityCorporateDetail.setApplicationId(new LoanApplicationMaster(applicationId));

				securityCorporateDetail.setCreatedBy(applicationId);
				securityCorporateDetail.setCreatedDate(new Date());
				securityCorporateDetail.setModifiedBy(applicationId);
				securityCorporateDetail.setModifiedDate(new Date());
				securityCorporateDetail.setIsActive(true);
				securityCorporateDetailsRepository.save(securityCorporateDetail);
			}
			return true;
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			return false;
		}
	}

	/**
	 * @Override public Boolean updatePaymentStatusForMobile(PaymentRequest
	 * paymentRequest) {
	 * 
	 * Boolean updatePayment=false;
	 * 
	 * GatewayRequest gatewayRequest = new GatewayRequest();
	 * gatewayRequest.setApplicationId(paymentRequest.getApplicationId());
	 * gatewayRequest.setUserId(paymentRequest.getUserId());
	 * gatewayRequest.setStatus(paymentRequest.getStatus());
	 * gatewayRequest.setTxnId(paymentRequest.getTrxnId());
	 * 
	 * 
	 * try { updatePayment = gatewayClient.updatePayment(gatewayRequest); } catch
	 * (Exception e) {
	 * logger.error("THROW EXCEPTION WHILE UPDATE PAYMENT ON GATEWAY CLIENT : ",e);
	 *  }
	 * 
	 * return updatePayment; }
	 */


	public List<TotalCostOfProjectRequest> getTotalCostOfProjectRequestsList(Long applicationId, Long userId) {
		List<TotalCostOfProject> totalCostOfProjectsList = totalCostOfProjectRepository
				.listCostOfProjectFromAppId(applicationId, userId);

		if (CommonUtils.isListNullOrEmpty(totalCostOfProjectsList)) {
			logger.warn("No totalCostOfProjectsList Found for Application Id ==>{}", applicationId);
			return Collections.emptyList();
		} else {
			List<TotalCostOfProjectRequest> totalCostOfProjectRequestsList = new ArrayList<>(
					totalCostOfProjectsList.size());
			TotalCostOfProjectRequest target = null;
			for (TotalCostOfProject totalCostOfProject : totalCostOfProjectsList) {
				target = new TotalCostOfProjectRequest();
				target.setAlreadyIncurred(totalCostOfProject.getAlreadyIncurred());
				target.setApplicationId(applicationId);
				if (totalCostOfProject.getParticularsId() != null) {
					target.setParticulars(Particular.getById(totalCostOfProject.getParticularsId()).getValue());
				}
				target.setToBeIncurred(totalCostOfProject.getToBeIncurred());
				target.setTotalCost(totalCostOfProject.getTotalCost());
				target.setCreatedBy(userId);
				totalCostOfProjectRequestsList.add(target);
			}
			return totalCostOfProjectRequestsList;
		}
	}

	public List<FinanceMeansDetailRequest> getFinanceMeansDetailRequestList(Long applicationId, Long userId) {
		List<FinanceMeansDetail> financeMeansDetailsList = financeMeansDetailRepository
				.listFinanceMeansFromAppId(applicationId, userId);

		if (CommonUtils.isListNullOrEmpty(financeMeansDetailsList)) {
			logger.warn("No FinanceMeansDetailList Found for Application Id ==>{} ", applicationId + " userId==>{} ",
					userId);
			return Collections.emptyList();
		} else {
			List<FinanceMeansDetailRequest> financeMeansDetailRequestList = new ArrayList<>(
					financeMeansDetailsList.size());
			FinanceMeansDetailRequest target = null;
			for (FinanceMeansDetail financeMeansDetail : financeMeansDetailsList) {
				target = new FinanceMeansDetailRequest();
				target.setAlreadyInfused(financeMeansDetail.getAlreadyInfused());
				target.setApplicationId(applicationId);
				if (financeMeansDetail.getFinanceMeansCategoryId() != null) {
					target.setFinanceMeansCategory(FinanceCategory
							.getById(financeMeansDetail.getFinanceMeansCategoryId().intValue()).getValue());
				}
				target.setToBeIncurred(financeMeansDetail.getToBeIncurred());
				target.setTotal(financeMeansDetail.getTotal());
				target.setCreatedBy(userId);
				financeMeansDetailRequestList.add(target);
			}
			return financeMeansDetailRequestList;
		}
	}

	public List<SecurityCorporateDetailRequest> getSecurityCorporateDetailRequestList(Long applicationId, Long userId) {
		List<SecurityCorporateDetail> securityCorporateDetailList = securityCorporateDetailsRepository
				.listSecurityCorporateDetailFromAppId(applicationId, userId);

		if (CommonUtils.isListNullOrEmpty(securityCorporateDetailList)) {
			logger.warn("No SecurityCorporateDetailList Found for Application Id ==>{}", applicationId);
			return Collections.emptyList();
		} else {
			List<SecurityCorporateDetailRequest> securityCorporateDetailRequestList = new ArrayList<>(
					securityCorporateDetailList.size());
			SecurityCorporateDetailRequest target = null;
			for (SecurityCorporateDetail securityCorporateDetail : securityCorporateDetailList) {
				target = new SecurityCorporateDetailRequest();
				target.setAmount(securityCorporateDetail.getAmount());
				target.setPrimarySecurityName(securityCorporateDetail.getPrimarySecurityName());
				target.setApplicationId(applicationId);
				target.setCreatedBy(userId);
				securityCorporateDetailRequestList.add(target);
			}
			return securityCorporateDetailRequestList;
		}
	}



	@Override
	public ScoringModelReqRes getMinMaxMarginByApplicationId(Long applicationId, Integer businessTypeId) {

		try {
			ScoringModelReqRes scoringModelReqRes = new ScoringModelReqRes();

			List<BigInteger> fpProductList = null;
			if (CommonUtils.BusinessType.EXISTING_BUSINESS.getId() == businessTypeId) {
				fpProductList = loanApplicationRepository.getScoringIdListByApplicationIdOnOneForm();
			} else if (CommonUtils.BusinessType.NEW_TO_BUSINESS.getId() == businessTypeId) {
				fpProductList = loanApplicationRepository.getScoringIdListByApplicationIdAndStageId(applicationId, 105l);
			}

			List<Long> scoringLongList = new ArrayList<Long>();
			for (BigInteger i : fpProductList) {
				scoringLongList.add(i.longValue());
			}
			scoringModelReqRes.setScoringModelIdList(scoringLongList);
			return scoringClient.getMinMaxMargin(scoringModelReqRes);
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			return null;
		}
	}



	@Override
	public Boolean saveLoanWCRenewalType(Long applicationId, Integer wcRenewalType) {
		LoanApplicationMaster loanMaster = loanApplicationRepository.getById(applicationId);
		if (!CommonUtils.isObjectNullOrEmpty(loanMaster)) {
			loanMaster.setWcRenewalStatus(wcRenewalType);
			loanApplicationRepository.save(loanMaster);
			return true;
		}
		return false;
	}

	@Override
	public Integer getLoanWCRenewalType(Long applicationId) {
		LoanApplicationMaster loanMaster = loanApplicationRepository.getById(applicationId);
		if (!CommonUtils.isObjectNullOrEmpty(loanMaster)) {
			return loanMaster.getWcRenewalStatus();
		}
		logger.info("IN GET LOAN WC RENEWAL TYPE NOT FOUND BY APPLICATION ID ---------->" + applicationId);
		return null;
	}

	@Override
	public String getCommonPropertiesValue(String key) {
		return loanRepository.getCommonPropertiesValue(key);
	}

	@Override
	public LoanApplicationRequest getAllFlag(Long id, Long userId) throws LoansException {

		LoanApplicationRequest applicationRequest = null;
		LoanApplicationMaster applicationMaster = loanApplicationRepository.getById(id);
		if (applicationMaster != null) {
			applicationRequest = new LoanApplicationRequest();
			applicationRequest.setProfilePrimaryLocked(applicationMaster.getIsPrimaryLocked() != null ? applicationMaster.getIsPrimaryLocked() : false);
			applicationRequest.setFinalLocked(applicationMaster.getIsFinalLocked() != null ? applicationMaster.getIsFinalLocked() : false);
			applicationRequest.setIsMcqSkipped(applicationMaster.getIsMcqSkipped() != null ? applicationMaster.getIsMcqSkipped() : false);
			applicationRequest.setDdrStatusId(applicationMaster.getDdrStatusId());
		}

		return applicationRequest;
	}

	@Override
	public LoanApplicationRequest getAllFlagByProposalId(Long proposalId, Long userId) throws LoansException {

		LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
		ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.findByProposalIdAndIsActive(proposalId,true);
		if (applicationProposalMapping != null) {
			applicationRequest = new LoanApplicationRequest();
			applicationRequest.setProfilePrimaryLocked(applicationProposalMapping.getIsPrimaryLocked() != null ? applicationProposalMapping.getIsPrimaryLocked() : false);
			applicationRequest.setFinalLocked(applicationProposalMapping.getIsFinalLocked() != null ? applicationProposalMapping.getIsFinalLocked() : false);
			applicationRequest.setIsMcqSkipped(applicationProposalMapping.getIsMcqSkipped() != null ? applicationProposalMapping.getIsMcqSkipped() : false);
			applicationRequest.setDdrStatusId(applicationProposalMapping.getDdrStatusId());
		}

		return applicationRequest;
	}

	@Override
	public JSONObject isAllowToMoveAheadForMultiProposal(Long applicationId, Long proposalId, Long userId, Integer nextTabType,
														 Long coAppllicantOrGuarantorId) throws Exception {
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
		ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.getByProposalIdAndApplicationId(proposalId,applicationId);
		int userMainType = CommonUtils.getUserMainType(applicationProposalMapping.getProductId());
		if (CommonUtils.UserMainType.CORPORATE == userMainType) {
			return corporateValidating(applicationProposalMapping, nextTabType, coAppllicantOrGuarantorId);
		} else {
			//return retailValidating(loanApplicationMaster, nextTabType, coAppllicantOrGuarantorId);
			return retailValidating(applicationProposalMapping, nextTabType, coAppllicantOrGuarantorId);
		}
	}

	@SuppressWarnings("unchecked")
	private JSONObject corporateValidatingForMultiProposal(LoanApplicationMaster applicationMaster, Integer toTabType,
														   Long coAppllicantOrGuarantorId, Long proposalId) throws Exception {
		List<Long> coAppIds = null;

		ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.getOne(proposalId);

		Long coAppCount;

		int index = 0;
		final String INVALID_MSG = "Requested data is Invalid.";
		JSONObject response = new JSONObject();
		response.put("message", "NA");
		response.put("result", true);

		switch (toTabType) {

			case CommonUtils.TabType.PROFILE_CO_APPLICANT:
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
						|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
					response.put("message", "Please Fill PROFILE details to Move Next !");
					response.put("result", false);
					return response;
				}

				coAppIds = corporateCoApplicantService.getCoAppIds(applicationMaster.getId(),
						applicationMaster.getUserId());
				if (CommonUtils.isListNullOrEmpty(coAppIds)) {
					response.put("message", INVALID_MSG);
					response.put("result", false);
					return response;
				}

				index = coAppIds.indexOf(coAppllicantOrGuarantorId);
				if (index == -1) {
					response.put("message", INVALID_MSG);
					response.put("result", false);
					return response;
				}

				if (index == 1) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put("message", "Please CO-APPLICANT-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
				break;

			case CommonUtils.TabType.MATCHES:
				boolean isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
				if (!isPrimaryLocked) {
					response.put("message", "Please LOCK PRIMARY DETAILS to See the matches !");
					response.put("result", false);
					return response;
				}
				break;

			case CommonUtils.TabType.CONNECTIONS:
				isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
				if (!isPrimaryLocked) {
					response.put("message", "Please LOCK PRIMARY DETAILS to See the connections !");
					response.put("result", false);
					return response;
				}
				break;
			case CommonUtils.TabType.PRIMARY_INFORMATION:
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
						|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
					response.put("message", "Please Fill PROFILE details to Move Next !");
					response.put("result", false);
					return response;
				}

				// Co-Applicant Profile Checking

				coAppCount = null;

				coAppCount = corporateCoApplicantRepository.getCoAppCountByApplicationAndUserId(applicationMaster.getId(),
						applicationMaster.getUserId());
				if (!CommonUtils.isObjectNullOrEmpty(coAppCount) || coAppCount > 0) {
					if (coAppCount == 1) {
						if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
								|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
							response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
							response.put("result", false);
							return response;
						}
					}

					if (coAppCount == 2) {
						if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
								|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
							response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
							response.put("result", false);
							return response;
						}
						if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
								|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
							response.put("message", "Please Fill CO-APPLICANT-2 details to Move Next !");
							response.put("result", false);
							return response;
						}

					}
				}

				break;
			case CommonUtils.TabType.PRIMARY_UPLOAD:
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
						|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
					response.put("message", "Please Fill PROFILE details to Move Next !");
					response.put("result", false);
					return response;
				}
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
						|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
					response.put("message", "Please Fill PRIMARY INFORMATION details to Move Next !");
					response.put("result", false);
					return response;
				}
				break;
			case CommonUtils.TabType.FINAL_MCQ:

				// Co-Applicant Profile Checking
				coAppCount = null;
				coAppCount = corporateCoApplicantRepository.getCoAppCountByApplicationAndUserId(applicationMaster.getId(),
						applicationMaster.getUserId());
				if (!CommonUtils.isObjectNullOrEmpty(coAppCount) || coAppCount > 0) {
					if (coAppCount == 1) {
						if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
								|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
							response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
							response.put("result", false);
							return response;
						}
					}

					if (coAppCount == 2) {
						if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
								|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
							response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
							response.put("result", false);
							return response;
						}
						if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
								|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
							response.put("message", "Please Fill CO-APPLICANT-2 details to Move Next !");
							response.put("result", false);
							return response;
						}

					}
				}
				isPrimaryLocked = isPrimaryLockedByProposalId(proposalId, applicationMaster.getUserId());
				if (!isPrimaryLocked) {
					response.put("message", "Please LOCK PRIMARY DETAILS to Move next !");
					response.put("result", false);
					return response;
				}
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
						|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
					response.put("message", "Please Fill PROFILE details to Move Next !");
					response.put("result", false);
					return response;
				}
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
						|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
					response.put("message", "Please Fill PRIMARY INFORMATION details to Move Next !");
					response.put("result", false);
					return response;
				}
				/*
				 * if
				 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
				 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
				 * response.put("message",
				 * "Please Fill PRIMARY INFORMATION details to Move Next !");
				 * response.put("result", false); return response; }
				 */
				break;
			case CommonUtils.TabType.FINAL_INFORMATION:
				isPrimaryLocked = isPrimaryLockedByProposalId(proposalId, applicationMaster.getUserId());
				if (!isPrimaryLocked) {
					response.put("message", "Please LOCK PRIMARY DETAILS to Move next !");
					response.put("result", false);
					return response;
				}
				/*
				 * if (CommonUtils.isObjectNullOrEmpty(applicationMaster.
				 * getIsApplicantDetailsFilled()) ||
				 * !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				 * response.put("message", "Please Fill PROFILE details to Move Next !");
				 * response.put("result", false); return response; } if
				 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.
				 * getIsApplicantPrimaryFilled()) ||
				 * !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				 * response.put("message",
				 * "Please Fill PRIMARY INFORMATION details to Move Next !");
				 * response.put("result", false); return response; }
				 */
				// if
				// (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled())
				// || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
				// response.put("message", "Please Fill PRIMARY INFORMATION details to Move Next
				// !");
				// response.put("result", false);
				// return response;
				// }
				if (CommonUtils.BusinessType.EXISTING_BUSINESS.getId().equals(applicationMaster.getBusinessTypeId())) {
					if (CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getIsFinalMcqFilled())
							|| !applicationProposalMapping.getIsFinalMcqFilled().booleanValue()) {
						response.put("message", "Please Fill FINAL MCQ details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
				break;
			case CommonUtils.TabType.FINAL_DPR_UPLOAD:
				isPrimaryLocked = isPrimaryLockedByProposalId(proposalId, applicationMaster.getUserId());
				/*
				 * if (!isPrimaryLocked) { response.put("message",
				 * "Please LOCK PRIMARY DETAILS to Move next !"); response.put("result", false);
				 * return response; } if (CommonUtils.isObjectNullOrEmpty(applicationMaster.
				 * getIsApplicantDetailsFilled()) ||
				 * !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				 * response.put("message", "Please Fill PROFILE details to Move Next !");
				 * response.put("result", false); return response; }
				 */
				// if
				// (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
				// || !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				// response.put("message", "Please Fill PRIMARY INFORMATION details to Move Next
				// !");
				// response.put("result", false);
				// return response;
				// }
				/*
				 * if
				 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
				 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
				 * response.put("message",
				 * "Please Fill PRIMARY INFORMATION details to Move Next !");
				 * response.put("result", false); return response; }
				 */
				if (CommonUtils.BusinessType.EXISTING_BUSINESS.getId().equals(applicationMaster.getBusinessTypeId())) {
					if (CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getIsFinalMcqFilled())
							|| !applicationProposalMapping.getIsFinalMcqFilled().booleanValue()) {
						response.put("message", "Please Fill FINAL MCQ details to Move Next !");
						response.put("result", false);
						return response;
					}
				}

				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantFinalFilled())
						|| !applicationMaster.getIsApplicantFinalFilled().booleanValue()) {
					response.put("message", "Please Fill FINAL INFORMATION details to Move Next !");
					response.put("result", false);
					return response;
				}
				break;
			case CommonUtils.TabType.FINAL_UPLOAD:
				isPrimaryLocked = isPrimaryLockedByProposalId(applicationMaster.getId(), applicationMaster.getUserId());
				if (!isPrimaryLocked) {
					response.put("message", "Please LOCK PRIMARY DETAILS to Move next !");
					response.put("result", false);
					return response;
				}

				/*
				 * if (CommonUtils.isObjectNullOrEmpty(applicationMaster.
				 * getIsApplicantDetailsFilled()) ||
				 * !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				 * response.put("message", "Please Fill PROFILE details to Move Next !");
				 * response.put("result", false); return response; } if
				 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.
				 * getIsApplicantPrimaryFilled()) ||
				 * !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				 * response.put("message",
				 * "Please Fill PRIMARY INFORMATION details to Move Next !");
				 * response.put("result", false); return response; }
				 */
				/*
				 * if
				 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
				 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
				 * response.put("message",
				 * "Please Fill PRIMARY INFORMATION details to Move Next !");
				 * response.put("result", false); return response; }
				 */
				if (CommonUtils.BusinessType.EXISTING_BUSINESS.getId().equals(applicationMaster.getBusinessTypeId())) {
					if (CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getIsFinalMcqFilled())
							|| !applicationProposalMapping.getIsFinalMcqFilled().booleanValue()) {
						response.put("message", "Please Fill FINAL MCQ details to Move Next !");
						response.put("result", false);
						return response;
					}
				}

				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantFinalFilled())
						|| !applicationMaster.getIsApplicantFinalFilled().booleanValue()) {
					response.put("message", "Please Fill FINAL INFORMATION details to Move Next !");
					response.put("result", false);
					return response;
				}
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsFinalDprUploadFilled())
						|| !applicationMaster.getIsFinalDprUploadFilled().booleanValue()) {
					response.put("message", "Please Fill Financial Model details to Move Next !");
					response.put("result", false);
					return response;
				}

				/*
				 * if (applicationMaster.getProductId() == LoanType.TERM_LOAN.getValue()) { if
				 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsFinalDprUploadFilled(
				 * )) || !applicationMaster.getIsFinalDprUploadFilled().booleanValue()) {
				 * response.put("message", "Please Fill DPR details to Move Next !");
				 * response.put("result", false); return response; } }
				 */
				break;
			default:
				break;
		}
		return response;
	}

	@Override
	public Long getProposalId(Long applicationId, Long userOrgId){

		Long proposalId = proposalDetailsRepository.findByApplicationIdAndUserOrgId(userOrgId,applicationId);
		if (CommonUtils.isObjectNullOrEmpty(proposalId)) {
			logger.error("Failed to get proposal id from application id and user org id" + applicationId);
		}
		return proposalId;
	}

	@Override
	public List<FpProfileBasicDetailRequest> getFpNegativeListByProposalId(Long proposalId) {
		try {
			ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.findOne(proposalId);
			if (!CommonUtils.isObjectNullOrEmpty(applicationProposalMapping)) {
				List<Long> fpUserIdList = productMasterRepository
						.getUserIdListByProductId(applicationProposalMapping.getProductId());
				if (!CommonUtils.isListNullOrEmpty(fpUserIdList)) {
					// get fp name from user client

					UserResponse userResponse = userClient.getFPNameListByUserId(fpUserIdList);
					if (userResponse != null && userResponse.getData() != null) {
						return  (List<FpProfileBasicDetailRequest>) userResponse.getData();
					}

				}
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		return Collections.emptyList();
	}
	/**
	 * @author nilay.darji
	 * @return Json of userApplicationDetailList as per userId 
	 * 
	 */
	@Override
	public String getUserApplicationList(Long userId) {
		
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("loan_application.listUserApplication");
		storedProcedureQuery.registerStoredProcedureParameter(CommonUtils.USER_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("result",String.class, ParameterMode.OUT);
		storedProcedureQuery.setParameter(CommonUtils.USER_ID,userId);
		storedProcedureQuery.execute();
		return (String) storedProcedureQuery.getOutputParameterValue("result");
		
	}
	
	//to save logs from billdesk
	@Override
	public Boolean savePaymentGatewayAudit(PaymentRequest paymentRequest) throws LoansException {
		if(paymentRequest != null) {
			logger.info("Start Saving All Payment gateway error logs");
			PaymentGatewayAuditMaster paymentGatewayAuditMaster = new PaymentGatewayAuditMaster();
			paymentGatewayAuditMaster.setApplicationId(paymentRequest.getApplicationId());
			paymentGatewayAuditMaster.setUserId(paymentRequest.getUserId());
			paymentGatewayAuditMaster.setName(paymentRequest.getNameOfEntity());
			paymentGatewayAuditMaster.setEmail(paymentRequest.getEmailAddress());
			paymentGatewayAuditMaster.setMobile(paymentRequest.getMobileNumber());
			paymentGatewayAuditMaster.setCreatedDate(new Date());
			paymentGatewayAuditMaster.setResponseParam(paymentRequest.getResponseParams());
			paymentGatewayAuditMaster.setTxnId(paymentRequest.getTrxnId());
			paymentGatewayAuditMaster.setTransactionType(paymentRequest.getTxnType());
			paymentGatewayAuditMaster.setTransactionReferenceNumber(paymentRequest.getTxnReferenceNumber());
			paymentGatewayAuditMaster.setStatus(paymentRequest.getStatus());
			paymentGatewayAuditMaster.setErrorLog(paymentRequest.getErrorLog());
			paymentGatewayAuditMasterRepository.save(paymentGatewayAuditMaster);
			return true;
		}
		return false;
	}

	@Override
	public List<MinMaxProductDetailRequest> getMinMaxProductDetail(Long applicationId) {
		List<MinMaxProductDetailRequest> minMaxProductDetailRequestList=new ArrayList<MinMaxProductDetailRequest>();
		List<MinMaxProductDetail> minMaxProductDetailList=new ArrayList<MinMaxProductDetail>();
		LoanApplicationMaster loanApplicationMaster=loanApplicationRepository.findOne(applicationId);

		try {
			String campaignCode=null;
			Long fsOrgId=null;
			try {
				UserResponse userResponseObj = userClient.getCampaignCodesByUserId(loanApplicationMaster.getUserId());
				if (!CommonUtils.isObjectNullOrEmpty(userResponseObj) && !CommonUtils.isObjectNullOrEmpty(userResponseObj.getData())) {
					List<String> userCampaignDetailsList = (List<String>) userResponseObj.getData();
					if(!CommonUtils.isListNullOrEmpty(userCampaignDetailsList)){
						campaignCode = userCampaignDetailsList.get(userCampaignDetailsList.size()-1);
					}
					if(!CommonUtils.isObjectNullOrEmpty(campaignCode)){
						try {
							logger.info("Call Users Client for Get Code by CampaignCode ---------> " + campaignCode);
							UserResponse userResponse = userClient.getOrgIdByCode(campaignCode);
							if (!CommonUtils.isObjectNullOrEmpty(userResponse) && !CommonUtils.isObjectNullOrEmpty(userResponse.getData())) {
								Integer organizationId = (Integer) userResponse.getData();
								fsOrgId = Long.valueOf(organizationId);
								logger.info("organization Id ------->:" + fsOrgId);
							}
						} catch (Exception e) {
							logger.info("Error while calling user client getOrgIdByCode():");
							logger.error(CommonUtils.EXCEPTION+e.getMessage(), e);
						}
					}
				}
			} catch (Exception e) {
				logger.info("Error while calling user client getCampaignCodesByUserId():");
				logger.error(CommonUtils.EXCEPTION+e.getMessage(), e);
			}

			//User is Not from Campaign
			if(CommonUtils.isObjectNullOrEmpty(fsOrgId))
			{
				logger.info("User is not from Campaign. Application Id ==>"+applicationId);
				minMaxProductDetailList=minMaxProductDetailRepository.listMinMaxProductDetail();
			}
			else // User is from Campaign
			{
				logger.info("User is from Campaign. Application Id ==>"+applicationId + " Campaign Code ==>"+campaignCode);
				minMaxProductDetailList=minMaxProductDetailRepository.listMinMaxProductDetailByOrgId(fsOrgId);
			}

			for(MinMaxProductDetail minMaxProductDetail:minMaxProductDetailList)
			{
				MinMaxProductDetailRequest minMaxProductDetailRequest=new MinMaxProductDetailRequest();
				BeanUtils.copyProperties(minMaxProductDetail,minMaxProductDetailRequest);
				minMaxProductDetailRequestList.add(minMaxProductDetailRequest);
			}
		}
		catch (Exception e)
		{
			logger.error("Error while get min max product detail list");
			e.printStackTrace();
		}

		return minMaxProductDetailRequestList;
	}

	@Override
	public BasicDetailFS getBasicDetail(Long applicationId) {
		BasicDetailFS basicDetailFS=new BasicDetailFS();
		try {
			RetailApplicantDetail retailApplicantDetail=retailApplicantDetailRepository.findByApplicationId(applicationId);
			String fullName="";
			if(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName()))
			{
				fullName +=retailApplicantDetail.getFirstName();
			}
			if(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getLastName()))
			{
				fullName +=" " + retailApplicantDetail.getLastName();
			}

			basicDetailFS.setFullName(fullName);

			LoanApplicationMaster loanApplicationMaster=loanApplicationRepository.getById(applicationId);

			if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getProductId()))
			{
				basicDetailFS.setLoanTypeId(loanApplicationMaster.getProductId().longValue());
			}

		}
		catch (Exception e)
		{
			logger.error("Error while get Basic Detail FS");
			e.printStackTrace();
		}
		return  basicDetailFS;
	}

	@Override
	public Boolean updateLoanType(Long userId, Long applicationId, Long loanTypeId)
	{
		try {
			LoanApplicationMaster loanApplicationMaster=loanApplicationRepository.findOne(applicationId);
			loanApplicationMaster.setModifiedBy(userId);
			loanApplicationMaster.setModifiedDate(new Date());
			loanApplicationMaster.setProductId(loanTypeId.intValue());
			loanApplicationRepository.save(loanApplicationMaster);
			loanApplicationRepository.updateLoanType(applicationId,loanTypeId);
			logger.info("Loan Type Updated");
			fpAsyncComp.sendEmailToFsWhenSubProductOfRetailSelectedByUser(loanApplicationMaster);
			return  true;
		}
		catch (Exception e)
		{
			logger.error("Erroe while Loan Type Update");
			e.printStackTrace();
			return  false;
		}
	}
}