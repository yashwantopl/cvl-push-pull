
package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.capitaworld.service.loans.model.common.*;
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

import com.capitaworld.api.eligibility.model.CLEligibilityRequest;
import com.capitaworld.api.eligibility.model.EligibililityRequest;
import com.capitaworld.api.eligibility.model.EligibilityResponse;
import com.capitaworld.client.eligibility.EligibilityClient;
import com.capitaworld.connect.api.ConnectResponse;
import com.capitaworld.connect.client.ConnectClient;
import com.capitaworld.service.analyzer.client.AnalyzerClient;
import com.capitaworld.service.analyzer.model.common.AnalyzerResponse;
import com.capitaworld.service.analyzer.model.common.BouncedOrPenalXn;
import com.capitaworld.service.analyzer.model.common.Data;
import com.capitaworld.service.analyzer.model.common.Item;
import com.capitaworld.service.analyzer.model.common.MonthlyDetail;
import com.capitaworld.service.analyzer.model.common.ReportRequest;
import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.model.StorageDetailsResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.gateway.client.GatewayClient;
import com.capitaworld.service.gateway.model.GatewayRequest;
import com.capitaworld.service.loans.config.AuditComponent;
import com.capitaworld.service.loans.config.MCAAsyncComponent;
import com.capitaworld.service.loans.domain.fundprovider.ProductMaster;
import com.capitaworld.service.loans.domain.fundseeker.ApplicationStatusMaster;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.AchievementDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.AssociatedConcernDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateCoApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CreditRatingOrganizationDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.DirectorBackgroundDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.ExistingProductDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinanceMeansDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinancialArrangementsDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.GuarantorsCorporateDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.MonthlyTurnoverDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OwnershipDetail;
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
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.AdminPanelLoanDetailsResponse;
import com.capitaworld.service.loans.model.CommonResponse;
import com.capitaworld.service.loans.model.DashboardProfileResponse;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoanApplicationDetailsForSp;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.LoanEligibilityRequest;
import com.capitaworld.service.loans.model.PaymentRequest;
import com.capitaworld.service.loans.model.ReportResponse;
import com.capitaworld.service.loans.model.common.CGTMSECalcDataResponse;
import com.capitaworld.service.loans.model.common.ChatDetails;
import com.capitaworld.service.loans.model.common.DisbursementRequest;
import com.capitaworld.service.loans.model.common.EkycRequest;
import com.capitaworld.service.loans.model.common.EkycResponse;
import com.capitaworld.service.loans.model.common.HunterRequestDataResponse;
import com.capitaworld.service.loans.model.common.ProposalList;
import com.capitaworld.service.loans.model.mobile.MLoanDetailsResponse;
import com.capitaworld.service.loans.model.mobile.MobileLoanRequest;
import com.capitaworld.service.loans.repository.common.LogDetailsRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AchievementDetailsRepository;
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
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.MonthlyTurnoverDetailRepository;
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
import com.capitaworld.service.loans.service.ProposalService;
import com.capitaworld.service.loans.service.common.ApplicationSequenceService;
import com.capitaworld.service.loans.service.common.DashboardService;
import com.capitaworld.service.loans.service.common.LogService;
import com.capitaworld.service.loans.service.fundprovider.OrganizationReportsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateCoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateUploadService;
import com.capitaworld.service.loans.service.fundseeker.corporate.DDRFormService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.irr.IrrService;
import com.capitaworld.service.loans.service.networkpartner.NetworkPartnerService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.LoanType;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.matchengine.exception.MatchException;
import com.capitaworld.service.matchengine.model.MatchDisplayObject;
import com.capitaworld.service.matchengine.model.MatchDisplayResponse;
import com.capitaworld.service.matchengine.model.MatchRequest;
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
import com.capitaworld.service.oneform.enums.CampaignCode;
import com.capitaworld.service.oneform.enums.Constitution;
import com.capitaworld.service.oneform.enums.CreditRatingFund;
import com.capitaworld.service.oneform.enums.CreditRatingTerm;
import com.capitaworld.service.oneform.enums.Currency;
import com.capitaworld.service.oneform.enums.Denomination;
import com.capitaworld.service.oneform.enums.DirectorRelationshipType;
import com.capitaworld.service.oneform.enums.FinanceCategory;
import com.capitaworld.service.oneform.enums.Gender;
import com.capitaworld.service.oneform.enums.Industry;
import com.capitaworld.service.oneform.enums.LogDateTypeMaster;
import com.capitaworld.service.oneform.enums.OccupationNature;
import com.capitaworld.service.oneform.enums.Particular;
import com.capitaworld.service.oneform.enums.PurposeOfLoan;
import com.capitaworld.service.oneform.enums.RatingAgency;
import com.capitaworld.service.oneform.enums.ShareHoldingCategory;
import com.capitaworld.service.oneform.enums.Title;
import com.capitaworld.service.oneform.model.IrrBySectorAndSubSector;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.rating.RatingClient;
import com.capitaworld.service.rating.exception.RatingException;
import com.capitaworld.service.rating.model.IndustryResponse;
import com.capitaworld.service.rating.model.IrrRequest;
import com.capitaworld.service.rating.model.RatingResponse;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.scoring.exception.ScoringException;
import com.capitaworld.service.scoring.model.ScoreParameterResult;
import com.capitaworld.service.scoring.model.ScoringRequest;
import com.capitaworld.service.scoring.model.ScoringResponse;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.FpProfileBasicDetailRequest;
import com.capitaworld.service.users.model.FundProviderDetailsRequest;
import com.capitaworld.service.users.model.NetworkPartnerDetailsRequest;
import com.capitaworld.service.users.model.RegisteredUserResponse;
import com.capitaworld.service.users.model.UserOrganisationRequest;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;
import com.capitaworld.service.users.model.mobile.MobileUserRequest;
import com.capitaworld.sidbi.integration.client.SidbiIntegrationClient;
import com.capitaworld.sidbi.integration.model.AchievementDetailRequest;
import com.capitaworld.sidbi.integration.model.AddressRequest;
import com.capitaworld.sidbi.integration.model.AssociatedConcernDetailRequest;
import com.capitaworld.sidbi.integration.model.CorporateProfileRequest;
import com.capitaworld.sidbi.integration.model.CreditRatingOrganizationDetailRequest;
import com.capitaworld.sidbi.integration.model.CurrentFinancialArrangementsDetailRequest;
import com.capitaworld.sidbi.integration.model.DirectorBackgroundDetailRequest;
import com.capitaworld.sidbi.integration.model.ExistingProductDetailRequest;
import com.capitaworld.sidbi.integration.model.FinanceMeansDetailRequest;
import com.capitaworld.sidbi.integration.model.GenerateTokenRequest;
import com.capitaworld.sidbi.integration.model.GuarantorsCorporateDetailRequest;
import com.capitaworld.sidbi.integration.model.LoanMasterRequest;
import com.capitaworld.sidbi.integration.model.MonthlyTurnoverDetailRequest;
import com.capitaworld.sidbi.integration.model.OwnershipDetailRequest;
import com.capitaworld.sidbi.integration.model.ProfileReqRes;
import com.capitaworld.sidbi.integration.model.PromotorBackgroundDetailRequest;
import com.capitaworld.sidbi.integration.model.ProposedProductDetailRequest;
import com.capitaworld.sidbi.integration.model.SecurityCorporateDetailRequest;
import com.capitaworld.sidbi.integration.model.TotalCostOfProjectRequest;
import com.capitaworld.sidbi.integration.model.ddr.DDRFormDetailsRequest;
import com.capitaworld.sidbi.integration.model.eligibility.EligibilityDetailRequest;
import com.capitaworld.sidbi.integration.model.irr.IRROutputManufacturingRequest;
import com.capitaworld.sidbi.integration.model.irr.IRROutputServiceRequest;
import com.capitaworld.sidbi.integration.model.irr.IRROutputTradingRequest;
import com.capitaworld.sidbi.integration.model.matches.MatchesParameterRequest;
import com.capitaworld.sidbi.integration.model.scoring.ScoreParameterDetailsRequest;

@Service
@Transactional
public class LoanApplicationServiceImpl implements LoanApplicationService {

	private static final Logger logger = LoggerFactory.getLogger(LoanApplicationServiceImpl.class.getName());

	@Autowired
	private DMSClient dmsClient;

	@Autowired
	private Environment environment;
	
	@Autowired
	private SidbiIntegrationClient  sidbiIntegrationClient;  

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

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
	private GatewayClient gatewayClient;
	
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
	private AuditComponent auditComponent;
	
	@Autowired
	private AssetsDetailsRepository assetsDetailsRepository;
	
	@Autowired
	private MCAAsyncComponent mcaAsyncComponent; 
	
	@Autowired
	private FinanceMeansDetailRepository financeMeansDetailRepository;
	
	@Autowired 
	private TotalCostOfProjectRepository totalCostOfProjectRepository ; 
	
	@Autowired
	private SecurityCorporateDetailsRepository securityCorporateDetailsRepository ;
	
	@Autowired
	private PromotorBackgroundDetailsRepository promotorBackgroundDetailsRepository;
	@Override
	public boolean saveOrUpdate(FrameRequest commonRequest, Long userId) throws Exception {
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
				e.printStackTrace();
				logger.error("Error while Set Campaign Code to LoanApplication Master");
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
						.setApplicationCode(applicationSequenceService.getApplicationSequenceNumber(type.getValue()));
				loanApplicationRepository.save(applicationMaster);
			}

			try {
				// Inactivating Campaign Codes
				if (codeExist) {
					inactiveCampaignDetails(finalUserId, CommonUtils.CampaignCodes.ALL1MSME.getValue());
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error while inactivating campaign details");
			}
			return true;
		} catch (Exception e) {
			logger.error("Error while Saving Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
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
//			e.printStackTrace();
//			logger.error("Error while Getting Campaign Codes using Users Client");
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
			e.printStackTrace();
			logger.error("Error while Getting Campaign Codes using Users Client");
		}
	}

	@Override
	public LoanApplicationRequest saveFromCampaign(Long userId, Long clientId, String campaignCode) throws Exception {
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
				applicationMaster.setApplicationStatusMaster(new ApplicationStatusMaster(CommonUtils.ApplicationStatus.OPEN));
			}
			applicationMaster.setDdrStatusId(CommonUtils.DdrStatus.OPEN);
			applicationMaster.setCategoryCode(loanCode.toLowerCase());
			applicationMaster.setCampaignCode(campaignCode);
			applicationMaster.setApplicationCode(applicationSequenceService.getApplicationSequenceNumber(type.getValue()));
			applicationMaster.setIsActive(true);
			applicationMaster = loanApplicationRepository.save(applicationMaster);
			BeanUtils.copyProperties(applicationMaster, request);
			UsersRequest usersRequest = new UsersRequest();
			usersRequest.setLastAccessApplicantId(applicationMaster.getId());
			usersRequest.setId(finalUserId);
			userClient.setLastAccessApplicant(usersRequest);
			return request;
		} catch (Exception e) {
			logger.error("Error while Saving Loan Details From Campaign:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public boolean saveOrUpdateFromLoanEligibilty(FrameRequest commonRequest, Long userId) throws Exception {
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
						.setApplicationCode(applicationSequenceService.getApplicationSequenceNumber(type.getValue()));
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
			logger.error("Error while Saving Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
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
			logger.error("Error while Saving RetailApplicantDetailFromLoanEligibility:-");
			e.printStackTrace();
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
		if(CommonUtils.isObjectNullOrEmpty(applicationMaster.getProductId())){
			userMainType = CommonUtils.UserMainType.CORPORATE;
		}else{
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
	public LoanApplicationRequest get(Long id, Long userId) throws Exception {
		try {
			LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
			LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(id, userId);
			if (applicationMaster == null) {
				throw new NullPointerException("Invalid Loan Application ID==>" + id + " of User ID==>" + userId);
			}
			BeanUtils.copyProperties(applicationMaster, applicationRequest, "name");
			if(CommonUtils.isObjectNullOrEmpty(applicationMaster.getProductId())) {
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
						e.printStackTrace();
					}
				}
				applicationRequest.setCurrencyValue(currencyAndDenomination);
				applicationRequest.setLoanTypeSub(CommonUtils.getCorporateLoanType(applicationMaster.getProductId()));

				if (!CommonUtils.isObjectNullOrEmpty(applicationRequest.getTypeOfPayment())
						&& applicationRequest.getTypeOfPayment().equals(CommonUtils.PaymentMode.ONLINE)) {
					GatewayRequest gatewayRequest = networkPartnerService
							.getPaymentStatuOfApplication(applicationRequest.getId());
					if (!CommonUtils.isObjectNullOrEmpty(gatewayRequest)) {
						if (gatewayRequest.getStatus()
								.equals(com.capitaworld.service.gateway.utils.CommonUtils.PaymentStatus.SUCCESS)) {
							applicationRequest.setPaymentStatus(gatewayRequest.getStatus());
						} else {
							applicationRequest.setPaymentStatus(gatewayRequest.getStatus());
						}
					}
				}
			} else {
				applicationRequest.setLoanTypeMain(CommonUtils.RETAIL);
				Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, applicationMaster.getId());
				applicationRequest.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
				applicationRequest.setLoanTypeSub("DEBT");
			}
			applicationRequest.setProfilePrimaryLocked(applicationMaster.getIsPrimaryLocked());
			applicationRequest.setFinalLocked(applicationMaster.getIsFinalLocked());
			try {
				ProposalMappingResponse response = proposalDetailsClient
						.getFundSeekerApplicationStatus(applicationMaster.getId());
				applicationRequest.setStatus(
						CommonUtils.isObjectNullOrEmpty(response.getData()) ? null : (Integer) response.getData());
				applicationRequest.setName(LoanType.getType(applicationMaster.getProductId()).getName());
				return applicationRequest;
			} catch (Exception e) {
				logger.error("Error while getting Status From Proposal Client");
				e.printStackTrace();
				return applicationRequest;
			}
		} catch (Exception e) {
			logger.error("Error while getting Individual Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public LoanApplicationRequest inActive(Long id, Long userId) throws Exception {
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
	public List<LoanApplicationRequest> getList(Long userId) throws Exception {
		try {
			List<LoanApplicationMaster> results = loanApplicationRepository.getUserLoans(userId);
			List<LoanApplicationRequest> requests = new ArrayList<>(results.size());
			for (LoanApplicationMaster master : results) {
				LoanApplicationRequest request = new LoanApplicationRequest();
				BeanUtils.copyProperties(master, request, "name");
				if(CommonUtils.isObjectNullOrEmpty(master.getProductId())) {
					request.setLoanTypeMain(CommonUtils.CORPORATE);
					request.setLoanTypeSub("DEBT");
					requests.add(request);
					continue;
				}
				request.setHasAlreadyApplied(hasAlreadyApplied(userId, master.getId(), master.getProductId()));
				int userMainType = CommonUtils.getUserMainType(master.getProductId());
				if (userMainType == CommonUtils.UserMainType.CORPORATE) {
					request.setLoanTypeMain(CommonUtils.CORPORATE);
					String currencyAndDenomination = "NA";
					if (!CommonUtils.isObjectNullOrEmpty(master.getCurrencyId())
							&& !CommonUtils.isObjectNullOrEmpty(master.getDenominationId())) {
						try {
							currencyAndDenomination = CommonDocumentUtils.getCurrency(master.getCurrencyId());
							currencyAndDenomination = currencyAndDenomination
									.concat(" in " + CommonDocumentUtils.getDenomination(master.getDenominationId()));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					request.setCurrencyValue(currencyAndDenomination);
					request.setLoanTypeSub(CommonUtils.getCorporateLoanType(master.getProductId()));
				} else {
					request.setLoanTypeMain(CommonUtils.RETAIL);
					Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, master.getId());
					request.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
					request.setLoanTypeSub("DEBT");
				}
				request.setProfilePrimaryLocked(master.getIsPrimaryLocked());
				request.setFinalLocked(master.getIsFinalLocked());
				try {
					if (!CommonUtils.isObjectNullOrEmpty(master.getApplicationStatusMaster())) {
						request.setStatus(Integer.valueOf(master.getApplicationStatusMaster().getId().toString()));
						request.setIsNhbsApplication(true);
						request.setDdrStatusId(CommonUtils.isObjectListNull(master.getDdrStatusId()) ? null
								: Integer.valueOf(master.getDdrStatusId().toString()));
					} else {
						ProposalMappingResponse response = proposalDetailsClient
								.getFundSeekerApplicationStatus(master.getId());
						request.setStatus(CommonUtils.isObjectNullOrEmpty(response.getData()) ? null
								: (Integer) response.getData());
						request.setIsNhbsApplication(false);
					}
					request.setName(LoanType.getType(master.getProductId()).getName());
					requests.add(request);
				} catch (Exception e) {
					logger.error(
							"Error while Getting Loan Status from Proposal Client or Proposal Service is not available:-");
					e.printStackTrace();
					// throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
				}
			}
			return requests;
		} catch (Exception e) {
			logger.error("Error while Getting Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
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
						e.printStackTrace();
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
	public boolean lockPrimary(Long applicationId, Long userId, boolean flag) throws Exception {
		try {
			LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
			if (applicationMaster == null) {
				throw new Exception(
						"LoanapplicationMaster object Must not be null while locking the Profile And Primary Details==>"
								+ applicationMaster);
			}

			applicationMaster.setIsPrimaryLocked(flag);
			loanApplicationRepository.save(applicationMaster);
			// create log when teaser submit
			logService.saveFsLog(applicationId, LogDateTypeMaster.TEASER_SUBMIT.getId());

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Locking Profile and Primary Information");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public LoanApplicationRequest lockFinal(Long applicationId, Long userId, boolean flag) throws Exception {
		try {

			LoanApplicationRequest loanApplicationRequest = new LoanApplicationRequest();
			loanApplicationRequest.setIsMailSent(false);
			LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
			if (applicationMaster == null) {
				throw new Exception(
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

				// FundProviderDetailsRequest fundProviderDetailsRequest =
				// MultipleJSONObjectHelper.getObjectFromMap(
				// (LinkedHashMap<String, Object>) userResponse.getData(),
				// FundProviderDetailsRequest.class);
				// fundProviderDetailsRequest.get
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
				// // TODO: handle exception
				// e.printStackTrace();
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
				// // TODO: handle exception
				// e.printStackTrace();
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

			//SMS
			
			UsersRequest resp = getEmailMobile(applicationMaster.getNpAssigneeId());
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("url", "https://bit.ly/2IGwvBF");
			parameters.put("maker_name", getNPName(applicationMaster.getNpUserId()));
			parameters.put("checker_name", getNPName(applicationMaster.getNpAssigneeId()));
			sendSMSNotification(applicationMaster.getNpAssigneeId().toString(), parameters, NotificationAlias.SMS_MAKER_LOCKS_ONEFORM, resp.getMobile());
			// create log when teaser submit
			logService.saveFsLog(applicationId, LogDateTypeMaster.FINAL_SUBMIT.getId());
			return loanApplicationRequest;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Locking Final Information");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);

		}
	}
	
	private void sendSMSNotification(String userId, Map<String, Object> parameters, Long templateId, String... to) throws NotificationException  {
//		String to[] = {toNo};
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
			logger.warn("emailMobile or Data in emailMobile must not be null===>{}", emailMobile);
			return null;
		}

		UsersRequest request = MultipleJSONObjectHelper
				.getObjectFromMap((LinkedHashMap<String, Object>) emailMobile.getData(), UsersRequest.class);
		return request;
	}
	private Object[] getFPName(Long fpMappingId) {
		List<Object[]> pm = productMasterRepository.findById(fpMappingId);
		CommonDocumentUtils.endHook(logger, "getUserDetailsByPrductId");
		return (pm != null && !pm.isEmpty()) ? pm.get(0) : null;
	}

	private String getApplicantName(long applicationId) throws Exception {
		// TODO Auto-generated method stub
		try {
			String applicantName = getFsApplicantName(applicationId);
			return applicantName;
		} catch (LoansException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
	public UserResponse setLastAccessApplication(Long applicationId, Long userId) throws Exception {
		try {
			UsersRequest usersRequest = new UsersRequest();
			usersRequest.setLastAccessApplicantId(applicationId);
			usersRequest.setId(userId);
			UsersClient client = new UsersClient(environment.getRequiredProperty(CommonUtils.USER_CLIENT_URL));
			return client.setLastAccessApplicant(usersRequest);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);

		}

	}

	@Override
	public boolean hasAlreadyApplied(Long userId, Long applicationId, Integer productId) {
		if (CommonUtils.UserMainType.CORPORATE == CommonUtils.getUserMainType(productId)) {
			return (corporateApplicantDetailRepository.hasAlreadyApplied(userId, applicationId) > 0 ? true : false);
		} else {
			return (retailApplicantDetailRepository.hasAlreadyApplied(userId, applicationId) > 0 ? true : false);
		}
	}

	@Override
	public Integer getProductIdByApplicationId(Long applicationId, Long userId) throws Exception {
		try {
			return loanApplicationRepository.getProductIdByApplicationId(applicationId, userId);
		} catch (Exception e) {
			logger.error("Error while getting Product Id by Application Id");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Object[] getApplicationDetailsById(Long applicationId) {
		List<Object[]> data = loanApplicationRepository.getUserDetailsByApplicationId(applicationId);
		return (!CommonUtils.isListNullOrEmpty(data)) ? data.get(0) : null;
	}

	@Override
	public void updateFinalCommonInformation(Long applicationId, Long userId, Boolean flag, String finalFilledCount)
			throws Exception {
		try {
			loanApplicationRepository.setIsApplicantFinalMandatoryFilled(applicationId, userId, flag);
			loanApplicationRepository.setFinalFilledCount(applicationId, userId, finalFilledCount);
		} catch (Exception e) {
			logger.error("Error while updating final information flag");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Boolean isProfileAndPrimaryDetailFilled(Long applicationId, Long userId) throws Exception {
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
				} else if (coApps == 1) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue())
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
				} else if (guarantors == 1) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue())
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
			logger.error("Error while getting isProfileAndPrimaryDetailFilled ?");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Boolean isPrimaryLocked(Long applicationId, Long userId) throws Exception {
		try {
			Long count = loanApplicationRepository.checkPrimaryDetailIsLocked(applicationId);
			return (count != null ? count > 0 : false);
		} catch (Exception e) {
			logger.error("Error while getting isPrimaryLocked ?");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Boolean isApplicationIdActive(Long applicationId) throws Exception {
		try {
			Long count = loanApplicationRepository.checkApplicationIdActive(applicationId);
			return (count != null ? count > 0 : false);
		} catch (Exception e) {
			logger.error("Error while getting isApplicationIdActive ?");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Boolean isFinalDetailFilled(Long applicationId, Long userId) throws Exception {
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
				} else if (coApps == 1) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1FinalFilled())
							|| !applicationMaster.getIsCoApp1FinalFilled().booleanValue())
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
				} else if (guarantors == 1) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1FinalFilled())
							|| !applicationMaster.getIsGuarantor1FinalFilled().booleanValue())
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

				if ((loanType.getId() == CommonUtils.LoanType.HOME_LOAN.getValue()
						|| loanType.getId() == CommonUtils.LoanType.CAR_LOAN.getValue())) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsFinalMcqFilled())
							|| !applicationMaster.getIsFinalMcqFilled().booleanValue()) {
						return false;
					}
				}
				return true;
			}
		} catch (Exception e) {
			logger.error("Error while getting isFinalDetailFilled ?");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Boolean isFinalLocked(Long applicationId, Long userId) throws Exception {
		try {
			Long count = loanApplicationRepository.checkFinalDetailIsLocked(applicationId);
			return (count != null ? count > 0 : false);
		} catch (Exception e) {
			logger.error("Error while getting isFinalLocked ?");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getSelfViewAndPrimaryLocked(Long applicationId, Long userId) throws Exception {
		try {
			JSONObject json = new JSONObject();
			Long selfViewCount = loanApplicationRepository.isSelfApplicantView(applicationId, userId);
			json.put("isSelfView", (!CommonUtils.isObjectNullOrEmpty(selfViewCount) && selfViewCount > 0));
			json.put("isPrimaryLocked", isPrimaryLocked(applicationId, userId));
			return json;
		} catch (Exception e) {
			logger.error("Error while getting isFinalLocked ?");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Integer getCurrencyId(Long applicationId, Long userId) throws Exception {
		return loanApplicationRepository.getCurrencyId(applicationId, userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getCurrencyAndDenomination(Long applicationId, Long userId) throws Exception {
		try {
			Integer currencyId = loanApplicationRepository.getCurrencyId(applicationId, userId);
			Integer denominationId = loanApplicationRepository.getDenominationId(applicationId, userId);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("currency", CommonDocumentUtils.getCurrency(currencyId));
			jsonObject.put("denomination", CommonDocumentUtils.getDenomination(denominationId));
			return jsonObject;
		} catch (Exception e) {
			logger.error("Error while getting Currency and Denomination Value");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public JSONObject isAllowToMoveAhead(Long applicationId, Long userId, Integer nextTabType,
			Long coAppllicantOrGuarantorId) throws Exception {
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
	private JSONObject corporateValidating(LoanApplicationMaster applicationMaster, Integer toTabType,
			Long coAppllicantOrGuarantorId) throws Exception {
		List<Long> coAppIds = null;

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
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
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
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put("message", "Please LOCK PRIMARY DETAILS to Move next !");
				response.put("result", false);
				return response;
			}
			/*if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
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
			}*/
			// if
			// (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled())
			// || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			// response.put("message", "Please Fill PRIMARY INFORMATION details to Move Next
			// !");
			// response.put("result", false);
			// return response;
			// }
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsFinalMcqFilled())
					|| !applicationMaster.getIsFinalMcqFilled().booleanValue()) {
				response.put("message", "Please Fill FINAL MCQ details to Move Next !");
				response.put("result", false);
				return response;
			}
			break;
		case CommonUtils.TabType.FINAL_DPR_UPLOAD:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			/*if (!isPrimaryLocked) {
				response.put("message", "Please LOCK PRIMARY DETAILS to Move next !");
				response.put("result", false);
				return response;
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put("message", "Please Fill PROFILE details to Move Next !");
				response.put("result", false);
				return response;
			}*/
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
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsFinalMcqFilled())
					|| !applicationMaster.getIsFinalMcqFilled().booleanValue()) {
				response.put("message", "Please Fill FINAL MCQ details to Move Next !");
				response.put("result", false);
				return response;
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantFinalFilled())
					|| !applicationMaster.getIsApplicantFinalFilled().booleanValue()) {
				response.put("message", "Please Fill FINAL INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}
			break;
		case CommonUtils.TabType.FINAL_UPLOAD:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put("message", "Please LOCK PRIMARY DETAILS to Move next !");
				response.put("result", false);
				return response;
			}

			/*if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
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
			}*/
			/*
			 * if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
			 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			 * response.put("message",
			 * "Please Fill PRIMARY INFORMATION details to Move Next !");
			 * response.put("result", false); return response; }
			 */
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsFinalMcqFilled())
					|| !applicationMaster.getIsFinalMcqFilled().booleanValue()) {
				response.put("message", "Please Fill FINAL MCQ details to Move Next !");
				response.put("result", false);
				return response;
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

			/*if (applicationMaster.getProductId() == LoanType.TERM_LOAN.getValue()) {
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsFinalDprUploadFilled())
						|| !applicationMaster.getIsFinalDprUploadFilled().booleanValue()) {
					response.put("message", "Please Fill DPR details to Move Next !");
					response.put("result", false);
					return response;
				}
			}*/
			break;
		default:
			break;
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	private JSONObject retailValidating(LoanApplicationMaster applicationMaster, Integer toTabType,
			Long coAppllicantOrGuarantorId) throws Exception {
		List<Long> coAppIds = null;
		List<Long> guaIds = null;
		Long coAppCount = null;
		Long guarantorCount = null;
		int index = 0;
		final String INVALID_MSG = "Requested data is Invalid.";

		JSONObject response = new JSONObject();
		response.put("message", "NA");
		response.put("result", true);
		switch (toTabType) {
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
		case CommonUtils.TabType.PROFILE_CO_APPLICANT:
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put("message", "Please Fill PROFILE details to Move Next !");
				response.put("result", false);
				return response;
			}

			coAppIds = coApplicantDetailRepository.getCoAppIds(applicationMaster.getId(),
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
		case CommonUtils.TabType.PROFILE_GUARANTOR:
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put("message", "Please Fill PROFILE details to Move Next !");
				response.put("result", false);
				return response;
			}

			coAppCount = coApplicantDetailRepository.getCoAppCountByApplicationAndUserId(applicationMaster.getId(),
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

			guaIds = guarantorDetailsRepository.getGuarantorIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (CommonUtils.isListNullOrEmpty(guaIds)) {
				response.put("message", INVALID_MSG);
				response.put("result", false);
				return response;
			}

			index = guaIds.indexOf(coAppllicantOrGuarantorId);
			if (index == -1) {
				response.put("message", INVALID_MSG);
				response.put("result", false);
				return response;
			}

			if (index == 1) {
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
						|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
					response.put("message", "Please GUARANTOR-1 details to Move Next !");
					response.put("result", false);
					return response;
				}
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
			coAppCount = coApplicantDetailRepository.getCoAppCountByApplicationAndUserId(applicationMaster.getId(),
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
			// Guarantor Profile Checking
			guarantorCount = guarantorDetailsRepository
					.getGuarantorCountByApplicationAndUserId(applicationMaster.getId(), applicationMaster.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(guarantorCount) || guarantorCount > 0) {
				if (guarantorCount == 1) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}

				if (guarantorCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put("message", "Please GUARANTOR-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue()) {
						response.put("message", "Please GUARANTOR-2 details to Move Next !");
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

			// Co-Applicant Profile Checking
			coAppCount = coApplicantDetailRepository.getCoAppCountByApplicationAndUserId(applicationMaster.getId(),
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
			// Guarantor Profile Checking
			guarantorCount = guarantorDetailsRepository
					.getGuarantorCountByApplicationAndUserId(applicationMaster.getId(), applicationMaster.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(guarantorCount) || guarantorCount > 0) {
				if (guarantorCount == 1) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}

				if (guarantorCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put("message", "Please GUARANTOR-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue()) {
						response.put("message", "Please GUARANTOR-2 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}

			// Primary Information Tab Validating
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
					|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put("message", "Please Fill PRIMARY INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}
			break;
		case CommonUtils.TabType.FINAL_INFORMATION:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
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

			// Co-Applicant Profile Checking
			coAppCount = coApplicantDetailRepository.getCoAppCountByApplicationAndUserId(applicationMaster.getId(),
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
			// Guarantor Profile Checking
			guarantorCount = guarantorDetailsRepository
					.getGuarantorCountByApplicationAndUserId(applicationMaster.getId(), applicationMaster.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(guarantorCount) || guarantorCount > 0) {
				if (guarantorCount == 1) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}

				if (guarantorCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put("message", "Please GUARANTOR-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue()) {
						response.put("message", "Please GUARANTOR-2 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}

			// Primary Information Tab Validating
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
					|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put("message", "Please Fill PRIMARY INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}

			// Primary Upload Tab Validating
			/*
			 * if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
			 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			 * response.put("message", "Please Fill PRIMARY UPLOAD details to Move Next !");
			 * response.put("result", false); return response; }
			 */
			break;
		case CommonUtils.TabType.FINAL_CO_APPLICANT:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
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

			// Co-Applicant Profile Checking
			coAppIds = coApplicantDetailRepository.getCoAppIds(applicationMaster.getId(),
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

			// CO-APPLICANT Profile Check
			for (int i = 0; i < coAppIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-2 details to Move Next !");
						response.put("result", false);
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
						response.put("message", "Please Fill GUARANTOR-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-2 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
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
			 * response.put("message", "Please Fill PRIMARY UPLOAD details to Move Next !");
			 * response.put("result", false); return response; }
			 */
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantFinalFilled())
					|| !applicationMaster.getIsApplicantFinalFilled().booleanValue()) {
				response.put("message", "Please Fill FINAL INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}

			// CO-APPLICANT Final Check
			if (index == 1) {
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1FinalFilled())
						|| !applicationMaster.getIsCoApp1FinalFilled().booleanValue()) {
					response.put("message", "Please Fill CO-APPLICANT-1 Final Details to Move Next !");
					response.put("result", false);
					return response;
				}
			}

			break;
		case CommonUtils.TabType.FINAL_GUARANTOR:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
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

			// CO-APPLICANT Profile Check
			coAppIds = coApplicantDetailRepository.getCoAppIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < coAppIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-2 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}

			// Guarantor Profile Check
			guaIds = guarantorDetailsRepository.getGuarantorIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (CommonUtils.isListNullOrEmpty(guaIds)) {
				response.put("message", INVALID_MSG);
				response.put("result", false);
				return response;
			}

			index = guaIds.indexOf(coAppllicantOrGuarantorId);
			if (index == -1) {
				response.put("message", INVALID_MSG);
				response.put("result", false);
				return response;
			}

			for (int i = 0; i < guaIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-2 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
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
			 * response.put("message", "Please Fill PRIMARY UPLOAD details to Move Next !");
			 * response.put("result", false); return response; }
			 */
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantFinalFilled())
					|| !applicationMaster.getIsApplicantFinalFilled().booleanValue()) {
				response.put("message", "Please Fill FINAL INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}

			// CO-APPLICANT FINAL Check
			coAppIds = coApplicantDetailRepository.getCoAppIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < coAppIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1FinalFilled())
							|| !applicationMaster.getIsCoApp1FinalFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 FINAL details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2FinalFilled())
							|| !applicationMaster.getIsCoApp2FinalFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-2 FINAL details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}

			// FOR FINAL GUARANTOR
			if (index == 1) {
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1FinalFilled())
						|| !applicationMaster.getIsGuarantor1FinalFilled().booleanValue()) {
					response.put("message", "Please Fill GUARANTOR-1 Final Details to Move Next !");
					response.put("result", false);
					return response;
				}
			}

			break;
		// for Final HomeLoan and CarLoan
		case CommonUtils.TabType.FINAL_MCQ:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
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

			// CO-APPLICANT Profile Check
			coAppIds = coApplicantDetailRepository.getCoAppIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < coAppIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-2 details to Move Next !");
						response.put("result", false);
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
						response.put("message", "Please Fill GUARANTOR-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-2 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
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
			 * response.put("message", "Please Fill PRIMARY UPLOAD details to Move Next !");
			 * response.put("result", false); return response; }
			 */
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantFinalFilled())
					|| !applicationMaster.getIsApplicantFinalFilled().booleanValue()) {
				response.put("message", "Please Fill FINAL INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}

			// CO-APPLICANT FINAL Check
			for (int i = 0; i < coAppIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1FinalFilled())
							|| !applicationMaster.getIsCoApp1FinalFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 FINAL details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2FinalFilled())
							|| !applicationMaster.getIsCoApp2FinalFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-2 FINAL details to Move Next !");
						response.put("result", false);
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
						response.put("message", "Please Fill GUARANTOR-1 FINAL details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2FinalFilled())
							|| !applicationMaster.getIsGuarantor2FinalFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-2 FINAL details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}

			break;

		case CommonUtils.TabType.FINAL_UPLOAD:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
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

			// CO-APPLICANT Profile Check
			coAppIds = coApplicantDetailRepository.getCoAppIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < coAppIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-2 details to Move Next !");
						response.put("result", false);
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
						response.put("message", "Please Fill GUARANTOR-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-2 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
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
			 * response.put("message", "Please Fill PRIMARY UPLOAD details to Move Next !");
			 * response.put("result", false); return response; }
			 */
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantFinalFilled())
					|| !applicationMaster.getIsApplicantFinalFilled().booleanValue()) {
				response.put("message", "Please Fill FINAL INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}

			// CO-APPLICANT FINAL Check
			for (int i = 0; i < coAppIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1FinalFilled())
							|| !applicationMaster.getIsCoApp1FinalFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 FINAL details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2FinalFilled())
							|| !applicationMaster.getIsCoApp2FinalFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-2 FINAL details to Move Next !");
						response.put("result", false);
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
						response.put("message", "Please Fill GUARANTOR-1 FINAL details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2FinalFilled())
							|| !applicationMaster.getIsGuarantor2FinalFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-2 FINAL details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}

			com.capitaworld.service.oneform.enums.LoanType loanType = com.capitaworld.service.oneform.enums.LoanType
					.getById(applicationMaster.getProductId());
			if (!CommonUtils.isObjectNullOrEmpty(loanType)
					&& (loanType.getId() == CommonUtils.LoanType.HOME_LOAN.getValue()
							|| loanType.getId() == CommonUtils.LoanType.CAR_LOAN.getValue())) {
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsFinalMcqFilled())
						|| !applicationMaster.getIsFinalMcqFilled().booleanValue()) {
					if (loanType.getId() == CommonUtils.LoanType.CAR_LOAN.getValue()) {
						response.put("message", "Please Fill CAR-LOAN FINAL details to Move Next !");
					} else {
						response.put("message", "Please Fill HOME-LOAN FINAL details to Move Next !");
					}
					response.put("result", false);
					return response;
				}
			}
			break;
		default:
			break;
		}
		return response;
	}

	@Override
	public String getFsApplicantName(Long applicationId) throws Exception {
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicationId);
		if (CommonUtils.isObjectNullOrEmpty(applicationMaster))
			return null;

		if(applicationMaster.getProductId() != null) {
			if (CommonUtils.getUserMainType(applicationMaster.getProductId()) == CommonUtils.UserMainType.RETAIL) {
				RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository
						.findOneByApplicationIdId(applicationId);
				return retailApplicantDetail.getFirstName() + " " + retailApplicantDetail.getLastName();
			} else if (CommonUtils
					.getUserMainType(applicationMaster.getProductId()) == CommonUtils.UserMainType.CORPORATE) {
				CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
						.findOneByApplicationIdId(applicationId);
				return corporateApplicantDetail.getOrganisationName();
			}			
		}else {
			CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
					.findOneByApplicationIdId(applicationId);
			return corporateApplicantDetail.getOrganisationName();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
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
					obj.put("amount",
							!CommonUtils.isObjectListNull(loanMstr.getAmount())
									? decimalFormat.format(loanMstr.getAmount())
									: 0);
					obj.put("currency", currency);
					obj.put("tenure", loanMstr.getTenure() != null ? String.valueOf(loanMstr.getTenure() / 12) : null);
					ProposalMappingRequest proposalMappingRequest = new ProposalMappingRequest();
					proposalMappingRequest.setApplicationId(loanMstr.getId());
					ProposalCountResponse proposalCountResponse = null;
					try {
						proposalCountResponse = proposalDetailsClient.proposalCountOfFundSeeker(proposalMappingRequest);
					} catch (Exception e) {
						e.printStackTrace();
						logger.warn(
								"Throw Exception while get matches count for registration user details------------->"
										+ loanMstr.getId());
					}
					if (!CommonUtils.isObjectNullOrEmpty(proposalCountResponse)) {
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
			throws Exception {

		List<AdminPanelLoanDetailsResponse> responseList = new ArrayList<>();
		UserResponse userResponse = userClient.getFsIsSelfActiveUserId();
		if (userResponse.getStatus() != HttpStatus.OK.value()) {
			return null;
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
		logger.info("GetLoanDetailsForAdminPanel, from and todate for admin panel --------> " + cal.getTime());
		loanRequest.setToDate(cal.getTime());

		List<LoanApplicationMaster> loanApplicationList = loanApplicationRepository.getLoanDetailsForAdminPanel(userIds,
				loanRequest.getFromDate(), loanRequest.getToDate());

		SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
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
				if (!CommonUtils.isObjectNullOrEmpty(currencyId)) {
					if (currencyId.equals(Currency.RUPEES.getId())) {
						response.setAmounInRuppes(true);
					}
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
			throws IOException, Exception {
		List<AdminPanelLoanDetailsResponse> responseList = new ArrayList<>();
		UserResponse userResponse = userClient.getFsIsSelfActiveForAdminPanel();
		if (userResponse.getStatus() != HttpStatus.OK.value()) {
			return null;
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
		logger.info("GetLoanDetailsForAdminPanel, from and todate for admin panel --------> " + cal.getTime());
		loanRequest.setToDate(cal.getTime());

		List<LoanApplicationMaster> loanApplicationList = loanApplicationRepository.getLoanDetailsForAdminPanel(userIds,
				loanRequest.getFromDate(), loanRequest.getToDate());
		SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
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
					: "Direct");
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
				if (corporateApplicantDetail != null) {
					if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredPincode())) {
						response.setPincode(corporateApplicantDetail.getRegisteredPincode().toString());
					}
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
		System.out.println(responseList);
		return responseList;
	}

	@Override
	public List<AdminPanelLoanDetailsResponse> getPostLoginForAdminPanelOfNotEligibility(MobileLoanRequest loanRequest)
			throws Exception {
		List<AdminPanelLoanDetailsResponse> responseList = new ArrayList<>();
		UserResponse userResponse = userClient.getFsIsSelfActiveForAdminPanel();
		if (userResponse.getStatus() != HttpStatus.OK.value()) {
			return null;
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
		logger.info("GetLoanDetailsForAdminPanel, from and todate for admin panel --------> " + cal.getTime());
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
						: "Direct");
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
					if (corporateApplicantDetail != null) {
						if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredPincode())) {
							response.setPincode(corporateApplicantDetail.getRegisteredPincode().toString());
						}
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
		System.out.println(responseList);
		return responseList;
	}

	@Override
	public List<AdminPanelLoanDetailsResponse> getPostLoginForAdminPanelOfEligibility(MobileLoanRequest loanRequest)
			throws Exception {
		List<AdminPanelLoanDetailsResponse> responseList = new ArrayList<>();
		UserResponse userResponse = userClient.getFsIsSelfActiveForAdminPanel();
		if (userResponse.getStatus() != HttpStatus.OK.value()) {
			return null;
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
		logger.info("GetLoanDetailsForAdminPanel, from and todate for admin panel --------> " + cal.getTime());
		loanRequest.setToDate(cal.getTime());

		List<LoanApplicationMaster> loanApplicationList = loanApplicationRepository.getLoanDetailsForAdminPanel(userIds,
				loanRequest.getFromDate(), loanRequest.getToDate());
		SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		for (LoanApplicationMaster loanApplicationMaster : loanApplicationList) {
			// code for got eligibility
			if (loanApplicationMaster.getEligibleAmnt() != null) {
				if (CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getIsFinalLocked())) {
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
							: "Direct");
					response.setLastLoginDate(!CommonUtils.isObjectNullOrEmpty(usersRequest.getSignUpDate())
							? usersRequest.getSignUpDate()
							: null);
					response.setProductName(CommonUtils.LoanType.getType(loanApplicationMaster.getProductId()).getName());

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
						if (corporateApplicantDetail != null) {
							if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredPincode())) {
								response.setPincode(corporateApplicantDetail.getRegisteredPincode().toString());
							}
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
		}
		System.out.println(responseList);
		return responseList;
	}

	@Override
	public List<AdminPanelLoanDetailsResponse> getPostLoginForAdminPanelOfFinalLockedRejectedByUbi(
			MobileLoanRequest loanRequest) throws IOException {

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
			return null;
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
		logger.info("GetLoanDetailsForAdminPanel, from and todate for admin panel --------> " + cal.getTime());
		loanRequest.setToDate(cal.getTime());

		List<LoanApplicationMaster> loanApplicationList = loanApplicationRepository.getLoanDetailsForAdminPanelUbi(
				userId, applicationId, loanRequest.getFromDate(), loanRequest.getToDate());
		SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
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
					: "Direct");
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
				if (corporateApplicantDetail != null) {
					if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredPincode())) {
						response.setPincode(corporateApplicantDetail.getRegisteredPincode().toString());
					}
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
		System.out.println(responseList);
		return responseList;
	}

	@Override
	public List<AdminPanelLoanDetailsResponse> getPostLoginForAdminPanelOfApprovedByUbi(MobileLoanRequest loanRequest)
			throws IOException, Exception {
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
			return null;
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
		logger.info("GetLoanDetailsForAdminPanel, from and todate for admin panel --------> " + cal.getTime());
		loanRequest.setToDate(cal.getTime());

		List<LoanApplicationMaster> loanApplicationList = loanApplicationRepository.getLoanDetailsForAdminPanelUbi(
				userId, applicationId, loanRequest.getFromDate(), loanRequest.getToDate());
		SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
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
					: "Direct");
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
				if (corporateApplicantDetail != null) {
					if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredPincode())) {
						response.setPincode(corporateApplicantDetail.getRegisteredPincode().toString());
					}
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
		System.out.println(responseList);
		return responseList;
	}

	@Override
	public List<ChatDetails> getChatListByApplicationId(Long applicationId) {
		// TODO Auto-generated method stub
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return chatDetailList;
			}
		} catch (MatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
		// TODO Auto-generated method stub
		try {
			LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicationId);
			if (!CommonUtils.isObjectNullOrEmpty(applicationMaster)) {
				List<Long> fpUserIdList = productMasterRepository
						.getUserIdListByProductId(applicationMaster.getProductId());
				if (!CommonUtils.isListNullOrEmpty(fpUserIdList)) {
					CommonResponse response = new CommonResponse();
					// get fp name from user client

					UserResponse userResponse = userClient.getFPNameListByUserId(fpUserIdList);
					if (userResponse != null && userResponse.getData() != null) {
						List<FpProfileBasicDetailRequest> basicDetailRequests = (List<FpProfileBasicDetailRequest>) userResponse
								.getData();
						return basicDetailRequests;
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveSuggestionList(ProposalList proposalList) {
		// TODO Auto-generated method stub
		try {

			// change proposal status
			if (!CommonUtils.isListNullOrEmpty(proposalList.getSuggetionIds())) {
				proposalDetailsClient.saveSuggestionList(proposalList.getSuggetionIds());
			}

		} catch (Exception e) {
			e.printStackTrace();
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
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
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
	public boolean isCampaignCodeExist(Long userId, Long clientId, String code) throws Exception {
		try {
			Long finalUserId = CommonUtils.isObjectNullOrEmpty(clientId) ? userId : clientId;
			Long long1 = loanApplicationRepository.getApplicantCountByCode(finalUserId, code);
			return long1 > 0;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Checking Code is Exists or not");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public String getCampaignCodeByApplicationId(Long applicationId) throws Exception {
		try {
			return loanApplicationRepository.getCampaignCodeByApplicationId(applicationId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while getting Code by Application Id");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
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
		// TODO Auto-generated method stub
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicationId);
		if (CommonUtils.isObjectNullOrEmpty(applicationMaster)) {
			return null;
		} else {
			return CommonUtils.isTermLoanLessThanLimit(applicationMaster.getDenominationId(),
					applicationMaster.getAmount());
		}

	}

	@Override
	public Integer setEligibleLoanAmount(LoanApplicationRequest applicationRequest) throws Exception {
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
			e.printStackTrace();
			logger.error("Error while updating Eligibility Amount");
			logger.info("Exit from setEligibleLoanAmount()");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public void updateFlow(Long applicationId, Long clientId, Long userId) throws Exception {
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
			e.printStackTrace();
			logger.error("Error while Coverting UBI flow to Normal");
			logger.info("Exit from updateFlow()");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public Long getIrrByApplicationId(Long id) throws Exception {
		// TODO Auto-generated method stub
		try {

			CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
					.findOneByApplicationIdId(id);
			if (corporateApplicantDetail == null) {
				throw new NullPointerException("Invalid Loan Application ID==>" + id);
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
				logger.error("Error while getting Status From Proposal Client,getKeyVerticalSector can not be null");
				e.printStackTrace();
				return null;
			}
		} catch (Exception e) {
			logger.error("Error while getting Individual Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
		return null;
	}

	@Override
	public Object updateLoanApplicationMaster(PaymentRequest paymentRequest, Long userId) throws Exception {
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

			if ("SIDBI_FEES".equalsIgnoreCase(paymentRequest.getPurposeCode())) {

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
					System.out.println("Response for gateway is:- " + values);
					logger.info("End updateLoanApplicationMaster when Payment Mode in ONLINE() in NHBS");
					return values;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Error while Saving Payment History to Patyment Module when Payment Mode is ONLINE");
					throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
				}
			} else if (CommonUtils.PaymentMode.ONLINE.equalsIgnoreCase(paymentRequest.getTypeOfPayment())
					&& paymentRequest.getPurposeCode().equals("SIDBI_FEES")) {

				logger.info("Start updateLoanApplicationMaster when Payment Mode in ONLINE() in SIDBI");

				
				GatewayRequest gatewayRequest = new GatewayRequest();
				
				UsersRequest usersRequest = null;
				try {
					UserResponse emailMobile = userClient.getEmailMobile(userId);
					usersRequest = MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) emailMobile.getData(), UsersRequest.class);
				} catch (Exception e) {
					logger.info("Throw Exception While Get User Email and Mobile");
					e.printStackTrace();
				} 
				
				if(!CommonUtils.isObjectNullOrEmpty(usersRequest)) {
					gatewayRequest.setEmail(usersRequest.getEmail());
					gatewayRequest.setPhone(usersRequest.getMobile());					
				} else {
					gatewayRequest.setEmail("hakimuddin@capitaworld.com");
					gatewayRequest.setPhone("7869585058");
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

				Object values = gatewayClient.payout(gatewayRequest);

				logger.info("Response for gateway is:- " + values);
				logger.info("End updateLoanApplicationMaster when Payment Mode in ONLINE() in SIDBI");
				return values;
			}
		} catch (Exception e) {
			logger.error("Error while Saving payment information in Loan");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
		return paymentRequest.getTypeOfPayment();
	}

	
	@Override
	public void updateSkipPayment(Long userId, Long applicationId, Long orgId, Long fpProductId) throws Exception {
		
		logger.info("Enter in Update Skip Payment Details !!");
		
		//UPDATE PAYMENT STATE IN LOAN MASTER
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);
		
		if (loanApplicationMaster == null) {
			throw new NullPointerException("Invalid Loan Application ID==>" + applicationId);
		}
		/*LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
		BeanUtils.copyProperties(loanApplicationMaster, applicationRequest);*/
		loanApplicationMaster.setPaymentStatus(com.capitaworld.service.gateway.utils.CommonUtils.PaymentStatus.BYPASS);
		loanApplicationRepository.save(loanApplicationMaster);
		
		//UPDATE CONNECT POST PAYMENT
		try {
			ConnectResponse connectResponse = connectClient.postPayment(applicationId, userId,loanApplicationMaster.getBusinessTypeId());
			
			if (!CommonUtils.isObjectListNull(connectResponse)) {
				logger.info("Connector Response ----------------------------->" + connectResponse.toString());
				logger.info("Before Start Saving Phase 1 Sidbi API ------------------->" + orgId);
				if(orgId==10L) {
					logger.info("Start Saving Phase 1 sidbi API -------------------->" + loanApplicationMaster.getId());
					Long fpMappingId = null;
					try {
					}catch(Exception e) {
						e.printStackTrace();
					}
					savePhese1DataToSidbi(loanApplicationMaster.getId(), userId,orgId,fpProductId);
				}

				if(connectResponse.getProceed()) {
					if(loanApplicationMaster.getCompanyCinNumber()!=null) {
						mcaAsyncComponent.callMCA(loanApplicationMaster.getCompanyCinNumber(),loanApplicationMaster.getId(),loanApplicationMaster.getUserId());
					}
				}
			} else {
				logger.info("Connector Response null or empty");
				throw new Exception("Something went wrong while call connect client for " + applicationId);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Something went wrong while call connect client for " + applicationId);
		}
		
		//TRUE MATCHES PROPOSAL
		try {
			ProposalMappingResponse proposalMappingResponse = proposalDetailsClient.activateProposalOnPayment(applicationId);
			if(!CommonUtils.isObjectNullOrEmpty(proposalMappingResponse)) {
				logger.info("Proposal Mapping Response---------------> "+proposalMappingResponse.toString());
				if(proposalMappingResponse.getStatus() != HttpStatus.OK.value()) {
					throw new Exception(proposalMappingResponse.getMessage());	
				}
			} else {
				logger.info("Proposal Mapping Response Null or Empty---------------> ");
				throw new Exception("Something went wrong while call proposal client for " + applicationId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Something went wrong while call proposal client for " + applicationId);
		}
		
		logger.info("Exit on Update Skip Payment Details ");		
	}
	
	
	@Override
	public LoanApplicationRequest updateLoanApplicationMasterPaymentStatus(PaymentRequest paymentRequest, Long userId) throws Exception {
		logger.info("start updateLoanApplicationMasterPaymentStatus()");
		try {
			
			
			GatewayRequest gatewayRequest = new GatewayRequest();
			gatewayRequest.setApplicationId(paymentRequest.getApplicationId());
			gatewayRequest.setUserId(userId);
			gatewayRequest.setClientId(userId);
			gatewayRequest.setStatus(paymentRequest.getStatus());
			gatewayRequest.setTxnId(paymentRequest.getTrxnId());

			Boolean updatePayment = false;
			ProposalMappingResponse respProp = null;
			if ("SIDBI_FEES".equals(paymentRequest.getPurposeCode())) {
				if ("Success".equals(paymentRequest.getStatus())) {
					try {
						logger.info("Start update true proposal-------------------------->" + paymentRequest.getApplicationId());
						respProp = proposalDetailsClient.activateProposalOnPayment(paymentRequest.getApplicationId());	
					} catch (Exception e) {
						logger.info("Throw Exception WHile Activate Proposals");
						e.printStackTrace();
					}
					
				}
			}
			try {
				updatePayment = gatewayClient.updatePayment(gatewayRequest);
			} catch (Exception e) {
				logger.info("THROW EXCEPTION WHILE UPDATE PAYMENT ON GATEWAY CLIENT");
				e.printStackTrace();
			}
			

			if ("SIDBI_FEES".equals(paymentRequest.getPurposeCode())) {
				Long orgId = null;
				LoanApplicationMaster loanApplicationMaster = loanApplicationRepository
						.findOne(paymentRequest.getApplicationId());
				
				if (loanApplicationMaster == null) {
					throw new NullPointerException(
							"Invalid Loan Application ID==>" + paymentRequest.getApplicationId());
				}
				orgId = loanApplicationMaster.getNpOrgId();
				LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
				BeanUtils.copyProperties(loanApplicationMaster, applicationRequest);
				loanApplicationMaster.setPaymentStatus(paymentRequest.getStatus());
				logger.info("Business Type Id===============>"+loanApplicationMaster.getBusinessTypeId());
				loanApplicationRepository.save(loanApplicationMaster);
				try {
					
					if ("Success".equals(paymentRequest.getStatus())) {
						
					Long fpProductId = null;
					if(respProp != null && respProp.getData() != null) {
						ProposalMappingRequest mappingRequest = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)respProp.getData(), ProposalMappingRequest.class);
						fpProductId = mappingRequest.getFpProductId();
					}
					logger.info("Call Connector client for update payment status");
					ConnectResponse connectResponse = connectClient.postPayment(paymentRequest.getApplicationId(),
							userId,loanApplicationMaster.getBusinessTypeId());
					
					if (!CommonUtils.isObjectListNull(connectResponse)) {
						logger.info("Connector Response ----------------------------->" + connectResponse.toString());
						logger.info("Before Start Saving Phase 1 Sidbi API ------------------->" + orgId);
//						if(orgId==10L) {
							logger.info("Start Saving Phase 1 sidbi API -------------------->" + loanApplicationMaster.getId());
							savePhese1DataToSidbi(loanApplicationMaster.getId(), userId,orgId,fpProductId);
//						}
						
						if(connectResponse.getProceed()) {
							if(loanApplicationMaster.getCompanyCinNumber()!=null) {
								mcaAsyncComponent.callMCA(loanApplicationMaster.getCompanyCinNumber(),loanApplicationMaster.getId(),loanApplicationMaster.getUserId());
							}
						}
						
					} else {
						logger.info("Connector Response null or empty");
					}
					
					ProposalMappingResponse response = proposalDetailsClient.getInPricipleById(paymentRequest.getApplicationId());
					if(response!=null && response.getData()!=null) {
					logger.info("Inside Congratulations");
					
					Map<String, Object> proposalresp = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) response.getData(), Map.class);
//					ProposalMappingResponse resp = proposalDetailsClient.getActivateProposalById(Long.valueOf(proposalresp.get("fp_product_id").toString()), paymentRequest.getApplicationId());
//					ProposalMappingRequest proposalMappingRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) resp.getData(), ProposalMappingRequest.class);
					
					if(proposalresp!=null) {
					applicationRequest.setLoanAmount(proposalresp.get("amount")!=null?Double.valueOf(proposalresp.get("amount").toString() ):0.0 );
					applicationRequest.setTenure(proposalresp.get("tenure")!=null?Double.valueOf(proposalresp.get("tenure").toString() ):0.0 );
					applicationRequest.setEmiAmount(proposalresp.get("emi_amount")!=null?Double.valueOf(proposalresp.get("emi_amount").toString() ):0.0 );
					applicationRequest.setTypeOfLoan(CommonUtils.LoanType.getType(applicationRequest.getProductId()).toString());
					applicationRequest.setInterestRate(proposalresp.get("rate_interest")!=null?Double.valueOf(proposalresp.get("rate_interest").toString() ):0.0 );
					applicationRequest.setOnlinePaymentSuccess(updatePayment);
					applicationRequest.setNameOfEntity(paymentRequest.getNameOfEntity());
					orgId =proposalresp.get("org_id")!=null ? Long.valueOf(proposalresp.get("org_id").toString()): null;
					
					applicationRequest.setFundProvider(orgId!=null ? CommonUtils.getOrganizationName(orgId) : null);
					

			  }
					
				}else {
						throw new NullPointerException("Invalid user");
					}
				}
					else {
						logger.info("Payment Failed");
					}
				} catch (Exception e) {
					logger.info("THROW EXCEPTION WHILE CALLING PROPOSAL DETAILS FROM MATCHE ENGINE");
					e.printStackTrace();
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
				logger.warn("Invalid Application Id in Updating Payment Status====>{}",
						paymentRequest.getApplicationId());
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
					logger.warn("emailMobile or Data in emailMobile must not be null===>{}", emailMobile);
					return loanRequest;
				} else {
					UsersRequest userEmailMobile = MultipleJSONObjectHelper.getObjectFromMap(
							(LinkedHashMap<String, Object>) emailMobile.getData(), UsersRequest.class);
					loanRequest.setEmail(userEmailMobile.getEmail());
					loanRequest.setMobile(userEmailMobile.getMobile());
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error while Getting Client Details from Users");
			}
			logger.info("End updateLoanApplicationMasterPaymentStatus() with success");
			return loanRequest;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("End updateLoanApplicationMasterPaymentStatus() with Exception");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}



	@Override
	public GatewayRequest getPaymentStatus(PaymentRequest paymentRequest, Long userId, Long ClientId) throws Exception {
		logger.info("start getPaymentStatus()");
		try {
			GatewayRequest gatewayRequest = new GatewayRequest();
			gatewayRequest.setApplicationId(paymentRequest.getApplicationId());
			gatewayRequest.setStatus(com.capitaworld.service.gateway.utils.CommonUtils.PaymentStatus.SUCCESS);
			gatewayRequest.setUserId(userId);
			gatewayRequest.setClientId(ClientId);
			logger.info("End getPaymentStatus() with success");
			GatewayRequest paymentStatus = gatewayClient.getPaymentStatus(gatewayRequest);
			return paymentStatus;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("End updateLoanApplicationMasterPaymentStatus() with Exception");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Integer getIndustryIrrByApplication(Long applicationId) {
		// TODO Auto-generated method stub
		IrrRequest irrIndustryRequest = new IrrRequest();

		Long irrId = null;
		try {
			irrId = getIrrByApplicationId(applicationId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		irrIndustryRequest.setIrrIndustryId(irrId);
		try {
			irrIndustryRequest = ratingClient.getIrrIndustry(irrIndustryRequest);
		} catch (RatingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IndustryResponse industryResponse = irrIndustryRequest.getIndustryResponse();
		return !CommonUtils.isObjectNullOrEmpty(industryResponse) ? industryResponse.getBusinessTypeId() : null;			
	}

	@Override
	public Boolean updateDDRStatus(Long applicationId, Long userId, Long clientId, Long statusId) throws Exception {
		logger.info("start getPaymentStatus()");
		try {
			LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
			if (applicationMaster == null) {
				throw new Exception("LoanapplicationMaster object Must not be null while Updating DDR Status==>"
						+ applicationMaster);
			}

			if (statusId.equals(CommonUtils.DdrStatus.APPROVED)) {
				applicationMaster.setApprovedDate(new Date());
			}

			applicationMaster.setDdrStatusId(statusId);
			applicationMaster.setModifiedBy(userId);
			applicationMaster.setModifiedDate(new Date());
			loanApplicationRepository.save(applicationMaster);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Updating DDR Status");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public LoanApplicationRequest getFromClient(Long id) throws Exception {
		try {
			LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(id);
			if (applicationMaster == null) {
				throw new NullPointerException("Invalid Loan Application ID==>" + id);
			}
			LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
			BeanUtils.copyProperties(applicationMaster, applicationRequest);
			applicationRequest.setProfilePrimaryLocked(applicationMaster.getIsPrimaryLocked());
			applicationRequest.setFinalLocked(applicationMaster.getIsFinalLocked());
			applicationRequest.setUserName(getFsApplicantName(id));

			UserResponse emailMobile = userClient.getEmailMobile(applicationRequest.getUserId());
			if (CommonUtils.isObjectListNull(emailMobile, emailMobile.getData())) {
				logger.warn("emailMobile or Data in emailMobile must not be null===>{}", emailMobile);
				return applicationRequest;
			} else {
				UsersRequest userEmailMobile = MultipleJSONObjectHelper
						.getObjectFromMap((LinkedHashMap<String, Object>) emailMobile.getData(), UsersRequest.class);
				applicationRequest.setEmail(userEmailMobile.getEmail());
				applicationRequest.setMobile(userEmailMobile.getMobile());
			}
			// SETTING ADDRESS
			String address = null;
			if(!CommonUtils.isObjectNullOrEmpty(applicationMaster.getProductId())) {
				int mainType = CommonUtils.getUserMainType(applicationMaster.getProductId().intValue());
				if (CommonUtils.UserMainType.CORPORATE == mainType) {
					CorporateApplicantDetail applicantDetail = corporateApplicantDetailRepository
							.findOneByApplicationIdId(id);
					if (!CommonUtils.isObjectNullOrEmpty(applicantDetail)) {
						address = CommonDocumentUtils.getAdministrativeOfficeAddress(applicantDetail, oneFormClient);
					}
				} else {
					RetailApplicantDetail applicantDetail = retailApplicantDetailRepository.findOneByApplicationIdId(id);
					if (!CommonUtils.isObjectNullOrEmpty(applicantDetail)) {
						address = CommonDocumentUtils.getPermenantAddress(applicantDetail, oneFormClient);
					}
				}
				applicationRequest.setAddress(!CommonUtils.isObjectNullOrEmpty(address) ? address : "NA");
			}else {
				logger.info("No ProductId Found========>");	
			}
			return applicationRequest;
		} catch (Exception e) {
			logger.error("Error while getting Individual Loan Details For Client:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Boolean isApplicationEligibleForIrr(Long applicationId) throws Exception {
		// TODO Auto-generated method stub
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicationId);
		if (CommonUtils.isObjectNullOrEmpty(applicationMaster)) {
			return false;
		} else {
			if (!CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryLocked())
					&& !CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryLocked())) {
				try {

					CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
							.findOneByApplicationIdId(applicationId);
					if (corporateApplicantDetail == null) {
						throw new NullPointerException("Invalid Loan Application ID==>" + applicationId);
					}

					try {
						if (corporateApplicantDetail.getKeyVerticalSector() != null
								&& corporateApplicantDetail.getKeyVerticalSubsector() != null) {
							return true;
						} else {
							return false;
						}
					} catch (Exception e) {
						logger.error("Error while getting Status From isApplicationEligibleForIrr");
						e.printStackTrace();
						return null;
					}
				} catch (Exception e) {
					logger.error("Error while getting Individual Loan Details:-");
					e.printStackTrace();
					throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
				}
			}
		}
		return false;
	}

	@Override
	public DisbursementRequest getDisbursementDetails(DisbursementRequest disbursementRequest) {
		// TODO Auto-generated method stub

		try {
			// set fs details
			LoanApplicationMaster loanApplicationMaster = loanApplicationRepository
					.findOne(disbursementRequest.getApplicationId());
			disbursementRequest.setFsName(getFsApplicantName(disbursementRequest.getApplicationId()));
			disbursementRequest.setFsAddress(getAddressByApplicationId(disbursementRequest.getApplicationId()));
			// fs image
			DocumentRequest documentRequest = new DocumentRequest();
			documentRequest.setApplicationId(disbursementRequest.getApplicationId());
			documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
			documentRequest.setProductDocumentMappingId(
					CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));

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
			disbursementRequest
					.setFpName(productMasterRepository.findOne(disbursementRequest.getProductMappingId()).getName());
			ProductMaster productMaster = productMasterRepository.findOne(disbursementRequest.getProductMappingId());

			UsersRequest request = new UsersRequest();
			request.setId(productMaster.getUserId());
			UserResponse userResponse = userClient.getFPDetails(request);

			FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
					(LinkedHashMap<String, Object>) userResponse.getData(), FundProviderDetailsRequest.class);

			//disbursementRequest.setFpName(fundProviderDetailsRequest.getOrganizationName());
			String fpAddress = "";
			
			
			List<Long> stateList = new ArrayList<>();
			if (!CommonUtils.isObjectNullOrEmpty(fundProviderDetailsRequest.getStateId()))
				stateList.add(Long.valueOf(fundProviderDetailsRequest.getStateId()));
			if (!CommonUtils.isListNullOrEmpty(stateList)) {
				try {
					OneFormResponse oneFormResponse = oneFormClient.getStateByStateListId(stateList);
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
							.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper
								.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						fpAddress = masterResponse.getValue() + ",";
					} else {

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			disbursementRequest.setFpOrganisationName(fundProviderDetailsRequest.getOrganizationName());

			List<Long> countryList = new ArrayList<>();
			if (!CommonUtils.isObjectNullOrEmpty(fundProviderDetailsRequest.getCountryId()))
				countryList.add(Long.valueOf(fundProviderDetailsRequest.getCountryId()));
			if (!CommonUtils.isListNullOrEmpty(countryList)) {
				try {
					OneFormResponse oneFormResponse = oneFormClient.getCountryByCountryListId(countryList);
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
							.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper
								.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						fpAddress += masterResponse.getValue();
					} else {
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			
			disbursementRequest.setFpAddress(fpAddress);

			disbursementRequest.setLoanName(LoanType.getType(loanApplicationMaster.getProductId()).getName());
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
		} catch (Exception e) {
			logger.warn("error while getting details of disbursement", e);
		}

		return disbursementRequest;
	}

	private String getAddressByApplicationId(Long applicationId) {
		// TODO Auto-generated method stub
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
					} else {

					}
				} catch (Exception e) {
					e.printStackTrace();
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
					} else {
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {

		}
		return address;
	}

	@Override
	public Long createMsmeLoan(Long userId,Boolean isActive,Integer businessTypeId) {
		logger.info("IsActive======================>{}",isActive);
		
		if(isActive != null && isActive) {
			int inActiveCount = loanApplicationRepository.inActiveCorporateLoan(userId);
			logger.info("Inactivated Application Count of Users are ====== {} ",inActiveCount);			
		}
		logger.info("Entry in createMsmeLoan--------------------------->" + userId);
		LoanApplicationMaster corporateLoan = loanApplicationRepository.getCorporateLoan(userId,businessTypeId);
		if (!CommonUtils.isObjectNullOrEmpty(corporateLoan)) {
			logger.info("Corporate Application Id is Already Exists===>{}", corporateLoan.getId());
			return corporateLoan.getId();
		}
		logger.info("Successfully get result");
		corporateLoan = new PrimaryCorporateDetail();
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
		logger.info("Setting Last Application is as Last access Id in User Table---->" +corporateLoan.getIsActive());
		UsersRequest usersRequest = new UsersRequest();
		usersRequest.setLastAccessApplicantId(corporateLoan.getId());
		usersRequest.setId(userId);
		userClient.setLastAccessApplicant(usersRequest);
		logger.info("Exit in createMsmeLoan");
		return corporateLoan.getId();
	}

	@Override
	public boolean updateProductDetails(LoanApplicationRequest loanApplicationRequest) {
		logger.info("Application id -------------------------------->"+loanApplicationRequest.getId());
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.getById(loanApplicationRequest.getId());
		if (CommonUtils.isObjectNullOrEmpty(loanApplicationMaster)) {
			logger.info("Loan master no found-------------------------------->"+loanApplicationRequest.getId());
			return false;
		}
		loanApplicationMaster.setAmount(loanApplicationRequest.getAmount());
		loanApplicationMaster.setTenure(loanApplicationRequest.getTenure());
		loanApplicationMaster.setProductId(loanApplicationRequest.getProductId());
		loanApplicationMaster.setIsApplicantDetailsFilled(true);
		loanApplicationMaster.setIsApplicantPrimaryFilled(true);
		loanApplicationMaster.setIsPrimaryLocked(true);
		if(!CommonUtils.isObjectNullOrEmpty(loanApplicationRequest.getNpOrgId())) {
			loanApplicationMaster.setNpOrgId(loanApplicationRequest.getNpOrgId());	
		}
		
		LoanType type = CommonUtils.LoanType.getType(loanApplicationRequest.getProductId());
		if (!CommonUtils.isObjectNullOrEmpty(type)) {
			loanApplicationMaster
					.setApplicationCode(applicationSequenceService.getApplicationSequenceNumber(type.getValue()));
		}
		loanApplicationRepository.save(loanApplicationMaster);
		
		if(CommonUtils.LoanType.WORKING_CAPITAL.getValue() == loanApplicationRequest.getProductId()) {
			PrimaryWorkingCapitalLoanDetail wcLoan = primaryWorkingCapitalLoanDetailRepository.findByApplicationIdIdAndIsActive(loanApplicationMaster.getId(), true);
			if(CommonUtils.isObjectNullOrEmpty(wcLoan)) {
				wcLoan = new PrimaryWorkingCapitalLoanDetail();
				wcLoan.setId(loanApplicationMaster.getId());
				wcLoan.setApplicationId(loanApplicationMaster);
				primaryWorkingCapitalLoanDetailRepository.save(wcLoan);
			}
		} else if (CommonUtils.LoanType.TERM_LOAN.getValue() == loanApplicationRequest.getProductId()) {
			PrimaryTermLoanDetail tlLoan = primaryTermLoanDetailRepository
					.findByApplicationIdIdAndIsActive(loanApplicationMaster.getId(), true);
			if (CommonUtils.isObjectNullOrEmpty(tlLoan)) {
				tlLoan = new PrimaryTermLoanDetail();
				tlLoan.setId(loanApplicationMaster.getId());
				tlLoan.setApplicationId(loanApplicationMaster);
				primaryTermLoanDetailRepository.save(tlLoan);
			}
		} else if (CommonUtils.LoanType.UNSECURED_LOAN.getValue() == loanApplicationRequest.getProductId()) {
			PrimaryUnsecuredLoanDetail unsLoan = primaryUnsecuredLoanDetailRepository
					.findByApplicationIdIdAndIsActive(loanApplicationMaster.getId(), true);
			if(CommonUtils.isObjectNullOrEmpty(unsLoan)) {
				unsLoan = new PrimaryUnsecuredLoanDetail();
				unsLoan.setId(loanApplicationMaster.getId());
				unsLoan.setApplicationId(loanApplicationMaster);
				primaryUnsecuredLoanDetailRepository.save(unsLoan);
			}
		}
		
		try {
			logger.info("Call Post Matche -------------------------------------->");
			ConnectResponse postMatches = connectClient.postMatches(loanApplicationMaster.getId(), loanApplicationMaster.getUserId(), !CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getBusinessTypeId()) ? loanApplicationMaster.getBusinessTypeId() : CommonUtils.BusinessType.EXISTING_BUSINESS.getId());
			if(!CommonUtils.isObjectNullOrEmpty(postMatches)) {
				logger.info("Response form Connect lient ---------------->" + postMatches.toString());
				logger.info("Successfully update loan data-------------------------------->"+loanApplicationRequest.getId());
				return postMatches.getProceed();
			} else {
				logger.info("Response form Connect lient ---------------->" + null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public int inActiveApplication(Long id, Long userId) {
		logger.info("Entry in inActiveApplication");
		int inActiveApplication = loanApplicationRepository.inActiveApplication(id, userId);
		logger.info("Inactivated Count==================>{}",inActiveApplication);
		logger.info("Exit in inActiveApplication");
		return 0;
	}

	@Override
	public boolean savePhese1DataToSidbi(Long applicationId, Long userId,Long organizationId,Long fpProductMappingId) {
		GenerateTokenRequest generateTokenRequest =null;
		try {
			generateTokenRequest = setUrlAndTokenInSidbiClient(organizationId , applicationId);
			if(CommonUtils.isObjectNullOrEmpty(generateTokenRequest)) {
				logger.warn("Something went wrong while setting URL and Token in savePhese1DataToSidbi()");
				return false;
			}
		}catch(Exception e) {
			logger.error("Something goes wrong while setUrlAndTokenInSidbiClient savePhese1DataToSidbi() ");
			e.printStackTrace();
			logger.error("Exception while getting token from SidbiIntegrationClient -------------- applicationId " +applicationId );
			return false;
		}
		
		Boolean savePrelimInfo = false;
		Boolean scoringDetails = false;
		Boolean matchesParameters = false;
		Boolean eligibilityParameters = false;
		Boolean bankStatement = false;
		try {
			
			//Create Prelim Sheet Object
			ProfileReqRes prelimData = getPrelimData(applicationId,userId);
			if(prelimData == null) {
				logger.error("ProfileReqRes ==> Prelim Sheet Object is Null in savePhese1DataToSidbi() ");
				auditComponent.updateAudit(AuditComponent.PRELIM_INFO, applicationId, userId, "ProfileReqRes ==> Prelim Sheet Object is Null ProfileReqRes prelimData  ==> " + prelimData,  savePrelimInfo);
				setTokenAsExpired(generateTokenRequest);
				return false;
			}
			try {
				logger.error("Start Saving ProfileReqRes in savePhese1DataToSidbi() ");
				savePrelimInfo = sidbiIntegrationClient.savePrelimInfo(prelimData);
				logger.error("Sucessfull Saved ProfileReqRes in savePhese1DataToSidbi() ");
				auditComponent.updateAudit(AuditComponent.PRELIM_INFO, applicationId, userId, null,  savePrelimInfo);
			}catch(Exception e) {
				logger.info("Exception while saving ProfileReqRes in savePhese1DataToSidbi() ==> for ApplicationId  ====>{}FpProductId====>{}",applicationId,fpProductMappingId +" Mgs " +e.getMessage());
				auditComponent.updateAudit(AuditComponent.PRELIM_INFO, applicationId, userId,  "Exception while saving ProfileReqRes in savePhese1DataToSidbi() ==> for ApplicationId  ====>{}}"+applicationId +" Mgs " +e.getMessage() ,savePrelimInfo);
				e.printStackTrace();
				setTokenAsExpired(generateTokenRequest);
			}
			//Set Match Parameters Starts
			try {
				MatchesParameterRequest parameterRequest = createMatchesParameterRequest(applicationId, fpProductMappingId);
				if(parameterRequest == null) {
					logger.info("MatchesParameterRequest Not Found in savePhese1DataToSidbi() ==> for ApplicationId ====>{}FpProductId====>{}",applicationId,fpProductMappingId);
					auditComponent.updateAudit(AuditComponent.MATCHES_PARAMETER, applicationId, userId, "MatchesParameterRequest Not Found for ApplicationId ====>{} "+applicationId+" FpProductId====>{} "+fpProductMappingId , matchesParameters);
					setTokenAsExpired(generateTokenRequest);
					return false;
				}else {
					logger.error("Start Saving MatchesParameterRequest in savePhese1DataToSidbi() ");
					matchesParameters = sidbiIntegrationClient.saveMatchesParameter(parameterRequest);
					logger.info("Sucessfully save MatchesParameterRequest in savePhese1DataToSidbi()  ====>{}FpProductId====>{}",applicationId,fpProductMappingId);
					auditComponent.updateAudit(AuditComponent.MATCHES_PARAMETER, applicationId, userId,null , matchesParameters);					
				}
			}catch(Exception e) {
				e.printStackTrace();
				logger.info("Exception in  MatchesParameterRequest in savePhese1DataToSidbi() ==> for ApplicationId  ====>{}FpProductId====>{}",applicationId,fpProductMappingId +" Mgs " +e.getMessage());
				auditComponent.updateAudit(AuditComponent.MATCHES_PARAMETER, applicationId, userId, "Exception in  MatchesParameterRequest in savePhese1DataToSidbi()  ====>{}applicationId "+applicationId+" Msg ==> "+e.getMessage(),  matchesParameters);
				setTokenAsExpired(generateTokenRequest);
			}
			
			//Set Match Parameters Ends
			
			
			//Set Bank Statement Starts
			try {
				com.capitaworld.sidbi.integration.model.bankstatement.Data data = createBankStatementRequest(applicationId);
				if(data == null) {
					logger.info("Bank Statement data Request Not Found  in savePhese1DataToSidbi()   for ApplicationId ====>{}FpProductId====>{}",applicationId,fpProductMappingId);
					auditComponent.updateAudit(AuditComponent.BANK_STATEMENT, applicationId, userId, "\"Bank Statement data Request Not Found for ApplicationId ====>{} "+applicationId + "FpProductId====>{}"+fpProductMappingId,  bankStatement);
					setTokenAsExpired(generateTokenRequest);
					return false;
				}else {
					logger.error("Start Saving BankStatemetnRequest in savePhese1DataToSidbi() ");
					bankStatement = sidbiIntegrationClient.saveBankStatement(data);
					logger.info("Sucessfully save BankStatemetnRequest in savePhese1DataToSidbi()  ====>{}FpProductId====>{}",applicationId,fpProductMappingId);
					auditComponent.updateAudit(AuditComponent.BANK_STATEMENT, applicationId, userId, null, bankStatement);					
				}
			}catch(Exception e) {
				logger.info("Exception in  BankStatementRequest in savePhese1DataToSidbi() ==> for ApplicationId  ====>{}FpProductId====>{}",applicationId,fpProductMappingId +" Mgs " +e.getMessage());
				e.printStackTrace();
				auditComponent.updateAudit(AuditComponent.BANK_STATEMENT, applicationId, userId, "Exception in  BankStatementRequest in savePhese1DataToSidbi() ==> for applicationId====>{} "+applicationId+" Msg ==> "+e.getMessage() ,bankStatement);
				setTokenAsExpired(generateTokenRequest);
			}
			
			//Set Bank Statement Ends
			
			//Set Bank Statement Starts
			try {
				 EligibilityDetailRequest eligibilityRequest = createEligibilityRequest(applicationId);
				if(eligibilityRequest == null) {
					logger.info("Eligibiity data Request Not Found  in savePhese1DataToSidbi()  for ApplicationId ====>{}FpProductId====>{}",applicationId,fpProductMappingId);
					auditComponent.updateAudit(AuditComponent.ELIGIBILITY, applicationId, userId, "Eligibiity data Request Not Found for ApplicationId ====>{} "+applicationId+"FpProductId====>{}"+fpProductMappingId, eligibilityParameters);
					setTokenAsExpired(generateTokenRequest);
					return false;
				}else {
					logger.error("Start Saving EligibilityDetailRequest in savePhese1DataToSidbi() ");
					eligibilityParameters = sidbiIntegrationClient.saveEligibilityDetails(eligibilityRequest);
					logger.info("Sucessfully save EligibilityDetailRequest in savePhese1DataToSidbi() for  ApplicationId ====>{}FpProductId====>{}",applicationId,fpProductMappingId);
					auditComponent.updateAudit(AuditComponent.ELIGIBILITY, applicationId, userId, null, eligibilityParameters);
				}
			}catch(Exception e) {
				logger.info("Exception in  EligibilityDetailRequest in savePhese1DataToSidbi() ==> for ApplicationId  ====>{}FpProductId====>{}",applicationId,fpProductMappingId +" Mgs " +e.getMessage());
				e.printStackTrace();
				auditComponent.updateAudit(AuditComponent.ELIGIBILITY, applicationId, userId, "Exception in  EligibilityDetailRequest in savePhese1DataToSidbi() ==> for ApplicationId  ====>{} " +applicationId +" Msg ==> "+ e.getMessage() , eligibilityParameters);
				setTokenAsExpired(generateTokenRequest);
			}
			
			//Set Eligibility Ends
			
			// TODO Auto-generated method stub
	        ProposalMappingRequest proposalMappingRequest = new ProposalMappingRequest();
	        proposalMappingRequest.setApplicationId(applicationId);
	        ProposalMappingResponse proposalMappingResponse = proposalService.listOfFundSeekerProposal(proposalMappingRequest);
	        if(!CommonUtils.isObjectListNull(proposalMappingResponse) && !CommonUtils.isObjectListNull(proposalMappingResponse.getDataList())){
				Long productId=null;
	        	List<Map<String, Object>> proposalMappingResponseDataList = (List<Map<String, Object>>) proposalMappingResponse.getDataList();
				try {
					ProposalMappingRequest proposalMappingRequest1 = MultipleJSONObjectHelper.getObjectFromMap(proposalMappingResponseDataList.get(0),
	                        ProposalMappingRequest.class);
					productId = proposalMappingRequest1.getFpProductId();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ScoringRequest scoringRequest = new ScoringRequest();
	            scoringRequest.setApplicationId(applicationId);
	            scoringRequest.setFpProductId(productId);
				try {
					ScoringResponse scoringResponse = scoringClient.getScoreResult(scoringRequest);
					if(!CommonUtils.isObjectListNull(scoringResponse.getDataObject())){
						try {
							ScoreParameterResult scoreParameterResult = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) scoringResponse.getDataObject(),
	                                ScoreParameterResult.class);
							if(scoreParameterResult == null) {
								logger.info("scoreParameterResult  data Request Not Found  in savePhese1DataToSidbi()  for ApplicationId ====>{} FpProductId====>{}",applicationId,fpProductMappingId);
								auditComponent.updateAudit(AuditComponent.SCORING_DETAILS, applicationId, userId, "Eligibiity data Request Not Found for ApplicationId ====>{} "+applicationId+"FpProductId====>{}"+fpProductMappingId, eligibilityParameters);
								setTokenAsExpired(generateTokenRequest);
								return false;
							}
							ScoreParameterDetailsRequest scoreParameterDetailsRequest = new ScoreParameterDetailsRequest();
							BeanUtils.copyProperties(scoreParameterResult,scoreParameterDetailsRequest);
							try {
								logger.error("Start Saving ScoreParameterDetailsRequest in savePhese1DataToSidbi() ");
								scoringDetails = sidbiIntegrationClient.saveScoringDetails(scoreParameterDetailsRequest);
								logger.info("Sucessfully save ScoreParameterDetailsRequest in savePhese1DataToSidbi() for  ApplicationId ====>{}FpProductId====>{}",applicationId,fpProductMappingId);
								auditComponent.updateAudit(AuditComponent.SCORING_DETAILS, applicationId, userId,null , scoringDetails);
							} catch (Exception e) {
								logger.info("Exception in  ScoreParameterDetailsRequest in savePhese1DataToSidbi() ==> for ApplicationId  ====>{}FpProductId====>{}",applicationId,fpProductMappingId +" Mgs " +e.getMessage());
								auditComponent.updateAudit(AuditComponent.SCORING_DETAILS, applicationId, userId,"Exception in  EligibilityDetailRequest in savePhese1DataToSidbi() ==> for ApplicationId  ====>{} "+applicationId+" Mgs " +e.getMessage() ,scoringDetails);
								e.printStackTrace();
								setTokenAsExpired(generateTokenRequest);
							}
						} catch (IOException e) {
							logger.info("Exception while getting Object from Map in savePhese1DataToSidbi() ==> for ApplicationId  ====>{}FpProductId====>{}",applicationId,fpProductMappingId +" Mgs " +e.getMessage());
							e.printStackTrace();
							setTokenAsExpired(generateTokenRequest);
						}
					}else {
						//setTokenAsExpired(generateTokenRequest);
					}
				} catch (ScoringException e) {
					logger.info("Exception while getting ScoringResponse from ScoringClient in savePhese1DataToSidbi() ==> for ApplicationId  ====>{}FpProductId====>{}",applicationId,fpProductMappingId +" Mgs " +e.getMessage());
					auditComponent.updateAudit(AuditComponent.SCORING_DETAILS, applicationId, userId, "Exception while getting ScoringResponse from ScoringClient in savePhese1DataToSidbi() ==> for ApplicationId  ====>{} "+applicationId+" Mgs " +e.getMessage(), false);
					e.printStackTrace();
					setTokenAsExpired(generateTokenRequest);
				}
			}
		
		} catch (Exception e) {
			logger.info("Exception while Saving Requests  in savePhese1DataToSidbi() ==> for ApplicationId  ====>{}FpProductId====>{}",applicationId,fpProductMappingId +" Mgs " +e.getMessage());
			auditComponent.updateAudit(AuditComponent.SCORING_DETAILS, applicationId, userId,"Exception while saving ScoringResponse  in savePhese1DataToSidbi() ==> for ApplicationId  ====>{} "+applicationId+" Mgs " +e.getMessage() ,false);
			auditComponent.updateAudit(AuditComponent.PRELIM_INFO, applicationId, userId, "Exception while saving ProfileReqRes ==> Prelim Sheet Object in savePhese1DataToSidbi() ==> for ApplicationId  ====>{} "+applicationId+" Mgs " +e.getMessage(), false);
			auditComponent.updateAudit(AuditComponent.BANK_STATEMENT, applicationId, userId, "Exception while saving BankStatemnt in savePhese1DataToSidbi() ==> for ApplicationId  ====>{} "+applicationId+" Mgs " +e.getMessage(), false);
			auditComponent.updateAudit(AuditComponent.MATCHES_PARAMETER, applicationId, userId, "Exception while saving MatchesparameterRequest in savePhese1DataToSidbi() ==> for ApplicationId  ====>{} "+applicationId+" Mgs " +e.getMessage(),false);
			auditComponent.updateAudit(AuditComponent.ELIGIBILITY, applicationId, userId, "Exception while saving EligibiliyDetsilRequest in savePhese1DataToSidbi() ==> for ApplicationId  ====>{} "+applicationId+" Mgs " +e.getMessage() ,false);
			logger.info("Throw Exception While Saving Phase one For SIDBI");
			e.printStackTrace();
			setTokenAsExpired(generateTokenRequest);
		}
		setTokenAsExpired(generateTokenRequest);
		return (savePrelimInfo && scoringDetails && matchesParameters && bankStatement);
	}
		

	@Override
	public boolean savePhese2DataToSidbi(Long applicationId, Long userId,Long organizationId,Long fpProductMappingId) {
		GenerateTokenRequest generateTokenRequest=null;
		try {
			generateTokenRequest = setUrlAndTokenInSidbiClient(organizationId, applicationId);
			if(CommonUtils.isObjectNullOrEmpty(generateTokenRequest)) {
				logger.warn("Something went wrong while setting URL and Token in savePhese2DataToSidbi()");
				return false;
			}
		}catch(Exception e) {
			logger.error("Something goes wrong while setUrlAndTokenInSidbiClient in savePhese2DataToSidbi() ");
			e.printStackTrace();
			logger.error("Exception while getting token from SidbiIntegrationClient -------------- applicationId " +applicationId );
			return false;
		}
		
		Boolean saveDetailsInfo = false;
		Boolean saveDDRInfo = false;
		Boolean saveIRRInfo = false;
		try {
			logger.info("Start savePhese2DataToSidbi()==>");
			PrimaryCorporateDetail applicationMaster = primaryCorporateRepository.findOneByApplicationIdId(applicationId);
			if(applicationMaster == null) {
				logger.info("Loan Application Found Null====>{}",applicationId);
				auditComponent.updateAudit(AuditComponent.DETAILED_INFO, applicationId, applicationMaster !=null ? applicationMaster.getUserId() : null,"Loan Application Found Null====>{} " +applicationId  , saveDetailsInfo);
				setTokenAsExpired(generateTokenRequest);
				return false;
			}
			userId = applicationMaster.getUserId();
			ProfileReqRes profileReqRes = new  ProfileReqRes();
			//Create Corporate Profile Object
			CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId);
			if(corporateApplicantDetail != null) {
				profileReqRes.setCorporateProfileRequest(createProfileObj(corporateApplicantDetail,applicationMaster.getUserId()));
				
				//Save Achievement
				profileReqRes.setAchievementList(getAchievementDetaisForSidbi(applicationId));
				profileReqRes.setExPrList(getExistingProductForSidbi(applicationId));
				profileReqRes.setProposedProdList(getProposedProductForSidbi(applicationId));
				profileReqRes.setOwnerShipDetailsList(getOwnershipDetailForSidbi(applicationId));
				profileReqRes.setCreditRatingOrgList(getCreditRatingDetailForSidbi(applicationId));
				profileReqRes.setGuaDetailList(getGuarantorDetailsForSidbi(applicationId));
				profileReqRes.setAssociateConcernList(getAssociatedConcernForSidbi(applicationId));
				profileReqRes.setMonTurnoverList(getMonthlyTurnOverForSidbi(applicationId));
				profileReqRes.setLoanMasterRequest(createObj(applicationMaster));
				profileReqRes.setCostOfProjectRequestsList(getTotalCostOfProjectRequestsList(applicationId, userId));
				profileReqRes.setFinanceMeansDetailRequestsList(getFinanceMeansDetailRequestList(applicationId, userId));
				profileReqRes.setSecurityCorporateDetailRequestsList(getSecurityCorporateDetailRequestList(applicationId, userId));
				try {
					logger.info("Going to Save Detailed Infor==>");
					saveDetailsInfo = sidbiIntegrationClient.saveDetailedInfo(profileReqRes);	
					auditComponent.updateAudit(AuditComponent.DETAILED_INFO, applicationId, applicationMaster.getUserId(), null, saveDetailsInfo);
				}catch(Exception e) {
					logger.info("Exception while Saving profileReqRes by sidbiIntegrationClient   in savePhese2DataToSidbi() ==> for ApplicationId  ====>{}FpProductId====>{}",applicationId,fpProductMappingId +" Mgs " +e.getMessage());
					auditComponent.updateAudit(AuditComponent.DETAILED_INFO, applicationId, applicationMaster.getUserId(),"Exception while Saving profileReqRes from SidbiIntegrationClient  in savePhese2DataToSidbi() ==> for ApplicationId  ====>{} "+applicationId +" Msg ==> "+e.getMessage() ,  false);
					e.printStackTrace();
					logger.info("Error while Calling Client====>");
					setTokenAsExpired(generateTokenRequest);
				}
				//Setting DDR
				logger.info("Going to Save DDR Form Data===>");
				
				DDRFormDetailsRequest sidbiDetails = dDRFormService.getSIDBIDetails(applicationId,applicationMaster.getUserId());
				try {
					saveDDRInfo = sidbiIntegrationClient.saveDDRFormDetails(sidbiDetails);
					auditComponent.updateAudit(AuditComponent.DDR_DETAILS, applicationId, applicationMaster.getUserId(), null ,saveDDRInfo);
					logger.info("ddr saved==========>{}",saveDDRInfo);
				}catch(Exception e) {
					logger.info("Exception while Saving DDRFormDetailsRequest by sidbiIntegrationClient   in savePhese2DataToSidbi() ==> for ApplicationId  ====>{}FpProductId====>{}",applicationId,fpProductMappingId +" Mgs " +e.getMessage());
					e.printStackTrace();
					auditComponent.updateAudit(AuditComponent.DDR_DETAILS , applicationId, applicationMaster.getUserId(), "Exception while Saving DDRFormDetailsRequest by sidbiIntegrationClient   in savePhese2DataToSidbi() ==> for ApplicationId  ====>{}"+ applicationId+" Mgs " +e.getMessage(),  false);
					logger.error("Error while calling DDRForm Details==>");
					setTokenAsExpired(generateTokenRequest);
				}
			}

			//To save irr details
			RatingResponse rtResponse = irrService.calculateIrrRating(applicationId,applicationMaster.getUserId()).getBody();
			RatingResponse ratingResponse = (RatingResponse)rtResponse.getData();
			com.capitaworld.sidbi.integration.model.irr.IrrRequest irrRequest = new com.capitaworld.sidbi.integration.model.irr.IrrRequest();
			//logger.info("Before -----------------data->"+ratingResponse.getData());
			IrrRequest irrReq = MultipleJSONObjectHelper.getObjectFromMap((Map<String,Object>) ratingResponse.getData(),IrrRequest.class);
			/*logger.info("After -----------------data->"+ irrReq.toString());
			BeanUtils.copyProperties(irrReq,irrRequest);*/
			if(com.capitaworld.service.rating.utils.CommonUtils.BusinessType.MANUFACTURING == ratingResponse.getBusinessTypeId()){
				IRROutputManufacturingRequest irrOutputManufacturingRequest = new IRROutputManufacturingRequest();
				IRROutputManufacturingRequest irrOutputManufacturingRequest1 = MultipleJSONObjectHelper.getObjectFromMap((Map<String,Object>) ratingResponse.getData(),IRROutputManufacturingRequest.class);
				BeanUtils.copyProperties(irrOutputManufacturingRequest1,irrOutputManufacturingRequest);
				irrOutputManufacturingRequest.setApplicationId(applicationId);
				irrOutputManufacturingRequest.setUserId(applicationMaster.getUserId());
				irrRequest.setIrrOutputManufacturingRequest(irrOutputManufacturingRequest);
				//logger.info("After Copy Response :::::::: " +irrOutputManufacturingRequest.toString());
			}else if(com.capitaworld.service.rating.utils.CommonUtils.BusinessType.SERVICE == ratingResponse.getBusinessTypeId()){
				IRROutputServiceRequest irrOutputServiceRequest = new IRROutputServiceRequest();
				IRROutputServiceRequest irrOutputServiceRequest1 = MultipleJSONObjectHelper.getObjectFromMap((Map<String,Object>) ratingResponse.getData(),IRROutputServiceRequest.class);
				BeanUtils.copyProperties(irrOutputServiceRequest1,irrOutputServiceRequest);
				irrOutputServiceRequest.setApplicationId(applicationId);
				irrOutputServiceRequest.setUserId(applicationMaster.getUserId());
				irrRequest.setIrrOutputServiceRequest(irrOutputServiceRequest);
                //logger.info("After Copy Response :::::::: " +irrOutputServiceRequest.toString());
			}else if(com.capitaworld.service.rating.utils.CommonUtils.BusinessType.TRADING == ratingResponse.getBusinessTypeId()){
				IRROutputTradingRequest irrOutputTradingRequest = new IRROutputTradingRequest();
				IRROutputTradingRequest irrOutputTradingRequest1 = MultipleJSONObjectHelper.getObjectFromMap((Map<String,Object>) ratingResponse.getData(),IRROutputTradingRequest.class);
				BeanUtils.copyProperties(irrOutputTradingRequest1,irrOutputTradingRequest);
				irrOutputTradingRequest.setApplicationId(applicationId);
				irrOutputTradingRequest.setUserId(applicationMaster.getUserId());
				irrRequest.setIrrOutputTradingRequest(irrOutputTradingRequest);
                //logger.info("After Copy Response :::::::: " +irrOutputTradingRequest.toString());
			}
            irrRequest.setApplicationId(applicationId.intValue());
			irrRequest.setBusinessTypeId(ratingResponse.getBusinessTypeId());
			try {
				saveIRRInfo = sidbiIntegrationClient.saveIrrDetails(irrRequest);
				auditComponent.updateAudit(AuditComponent.IRR_DETAILS, applicationId, applicationMaster.getUserId(), null ,saveIRRInfo);
			} catch (Exception e) {
				logger.info("Exception while Saving saveIRRInfo   by sidbiIntegrationClient   in savePhese2DataToSidbi() ==> for ApplicationId  ====>{}FpProductId====>{}",applicationId,fpProductMappingId +" Mgs " +e.getMessage());
				auditComponent.updateAudit(AuditComponent.IRR_DETAILS, applicationId, applicationMaster.getUserId(),"Exception while Saving saveIRRInfo   by sidbiIntegrationClient   in savePhese2DataToSidbi() ==> for ApplicationId  ====>{} "+applicationId+" Mgs " +e.getMessage()  ,false);
				e.printStackTrace();
				setTokenAsExpired(generateTokenRequest);
			}

			logger.info("End savePhese2DataToSidbi()==>");
		} catch (Exception e) {
			logger.info("Exception while Saving Requests by sidbiIntegrationClient   in savePhese2DataToSidbi() ==> for ApplicationId  ====>{}FpProductId====>{}",applicationId,fpProductMappingId +" Mgs " +e.getMessage());
			auditComponent.updateAudit(AuditComponent.DETAILED_INFO, applicationId, userId, "Exception while Saving DETAILED_INFO by sidbiIntegrationClient   in savePhese2DataToSidbi() ==> for ApplicationId  ====>{} "+applicationId +" Mgs " +e.getMessage(),false);
			auditComponent.updateAudit(AuditComponent.DDR_DETAILS , applicationId, userId, "Exception while Saving DDR_DETAILS by sidbiIntegrationClient   in savePhese2DataToSidbi() ==> for ApplicationId  ====>{} " +applicationId+" Mgs " +e.getMessage(), false);
			auditComponent.updateAudit(AuditComponent.IRR_DETAILS, applicationId, userId, "Exception while Saving  IRR_DETAILS by sidbiIntegrationClient   in savePhese2DataToSidbi() ==> for ApplicationId  ====>{} "+applicationId+" Mgs " +e.getMessage() , false);
			e.printStackTrace();
			setTokenAsExpired(generateTokenRequest);
		}
		setTokenAsExpired(generateTokenRequest);
		if(saveDetailsInfo && saveDDRInfo && saveIRRInfo) {
			return true;
		}
		return false;
	}
	
	private ProfileReqRes getPrelimData(Long applicationId, Long userId) {
		//Get and Create Loan Master
		PrimaryCorporateDetail applicationMaster = primaryCorporateRepository.findOneByApplicationIdId(applicationId);
		if(applicationMaster == null) {
			logger.info("Loan Application Found Null====>{}",applicationId);
			return null;
		}
		ProfileReqRes profileReqRes = new  ProfileReqRes();
		profileReqRes.setLoanMasterRequest(createObj(applicationMaster));
		//Create Corporate Profile Object
		CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId);
		if(corporateApplicantDetail != null) {
			profileReqRes.setCorporateProfileRequest(createProfileObj(corporateApplicantDetail,applicationMaster.getUserId()));
			//Setting Director Details
			profileReqRes.setDirBackList(getDirectorListForSidbi(applicationId));
			//Setting Current Financial Details
			profileReqRes.setCurrentFinArrList(getCurrentFinancialDetaisForSidbi(applicationId));
			return profileReqRes;
			
		}else {
			logger.warn("No Corporate Profile Found For Application Id==>{}",applicationId);
		}
		
		return null;
	}
	
	private MatchesParameterRequest createMatchesParameterRequest(Long applicationId,Long fpProductId) {
		MatchRequest request = new MatchRequest();
		request.setApplicationId(applicationId);
		request.setProductId(fpProductId);
		MatchDisplayResponse response = null;
		try {
			response = matchEngineClient.displayMatchesOfCorporate(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(response == null || CommonUtils.isListNullOrEmpty(response.getMatchDisplayObjectList())) {
			return null;
		}
		MatchesParameterRequest res = new MatchesParameterRequest();
		res.setApplicationId(applicationId);
		for(int i = 0; i < response.getMatchDisplayObjectList().size(); i++) {
			MatchDisplayObject displayObject = response.getMatchDisplayObjectList().get(i);
			switch(i) {
				case 0:
					res.setIndustryFp(checkIsNull(displayObject.getFpValue()));
					res.setIndustryFs(checkIsNull(displayObject.getValue()));
					res.setIndustryFlag(displayObject.getIsMatched());
					break;
				case 1:
					res.setInvestmentSizeFp(checkIsNull(displayObject.getFpValue()));
					res.setInvestmentSizeFs(checkIsNull(displayObject.getValue()));
					res.setInvestmentSizeFlag(displayObject.getIsMatched());
					break;
				case 2:
					res.setGeoMarketFocusFP(checkIsNull(displayObject.getFpValue()));
					res.setGeoMarketFocusFs(checkIsNull(displayObject.getValue()));
					res.setGeoMarketFocusFlag(displayObject.getIsMatched());
					break;
				case 3:
					res.setAssetCoverageFp(checkIsNull(displayObject.getFpValue()));
					res.setAssetCoverageFs(checkIsNull(displayObject.getValue()));
					res.setAssetCoverageFlag(displayObject.getIsMatched());
					break;
				case 4:
					res.setDebEqRatioFp(checkIsNull(displayObject.getFpValue()));
					res.setDebEqRatioFs(checkIsNull(displayObject.getValue()));
					res.setDebEqRatioFlag(displayObject.getIsMatched());
					break;
				case 5:
					res.setCurrentRatioFp(checkIsNull(displayObject.getFpValue()));
					res.setCurrentRatioFs(checkIsNull(displayObject.getValue()));
					res.setIndustryFlag(displayObject.getIsMatched());
					break;
				case 6:
					res.setInterestCovRatioFp(checkIsNull(displayObject.getFpValue()));
					res.setInterestCovRatioFs(checkIsNull(displayObject.getValue()));
					res.setInterestCovRatioFlag(displayObject.getIsMatched());
					break;
				case 7:
					res.setTolTnwFp(checkIsNull(displayObject.getFpValue()));
					res.setTolTnwFs(checkIsNull(displayObject.getValue()));
					res.setTolTnwFlag(displayObject.getIsMatched());
					break;
				case 8:
					res.setCustomerConFp(checkIsNull(displayObject.getFpValue()));
					res.setCustomerConFs(checkIsNull(displayObject.getValue()));
					res.setIndustryFlag(displayObject.getIsMatched());
					break;
				case 9:
					res.setNoOfCheckLastOneFp(checkIsNull(displayObject.getFpValue()));
					res.setNoOfCheckLastOneFs(checkIsNull(displayObject.getValue()));
					res.setNoOfCheckLastOneFlag(displayObject.getIsMatched());
					break;
				case 10:
					res.setNoOfMonthLastSixFp(checkIsNull(displayObject.getFpValue()));
					res.setNoOfMonthLastSixFs(checkIsNull(displayObject.getValue()));
					res.setNoOfMonthLastSixFlag(displayObject.getIsMatched());
					break;
				case 11:
					res.setRiskModelScoreFp(checkIsNull(displayObject.getFpValue()));
					res.setRiskModelScoreFs(checkIsNull(displayObject.getValue()));
					res.setRiskModelScoreFlag(displayObject.getIsMatched());
					break;
				case 13:
					res.setAgeEstaFp(checkIsNull(displayObject.getFpValue()));
					res.setAgeEstaFs(checkIsNull(displayObject.getValue()));
					res.setAgeEstaFlag(displayObject.getIsMatched());
					break;
				case 14:
					res.setPositiveProfFp(checkIsNull(displayObject.getFpValue()));
					res.setPositiveProfFs(checkIsNull(displayObject.getValue()));
					res.setPositiveProfFlag(displayObject.getIsMatched());
					break;
				case 15:
					res.setPastTernOverFp(checkIsNull(displayObject.getFpValue()));
					res.setPastTernOverFs(checkIsNull(displayObject.getValue()));
					res.setPastTernOverFlag(displayObject.getIsMatched());
					break;
				case 16:
					res.setPositiveNetFp(checkIsNull(displayObject.getFpValue()));
					res.setPositiveNetFs(checkIsNull(displayObject.getValue()));
					res.setPositiveNetFlag(displayObject.getIsMatched());
					break;
				case 17:
					res.setTurnOverToLoanFp(checkIsNull(displayObject.getFpValue()));
					res.setTurnOverToLoanFs(checkIsNull(displayObject.getValue()));
					res.setTurnOverToLoanFlag(displayObject.getIsMatched());
					break;
				case 18:
					res.setGrossCashAccuralFp(checkIsNull(displayObject.getFpValue()));
					res.setGrossCashAccuralFs(checkIsNull(displayObject.getValue()));
					res.setGrossCashAccuralFlag(displayObject.getIsMatched());
					break;
				case 19:
					res.setMinimumCibilFp(checkIsNull(displayObject.getFpValue()));
					res.setMinimumCibilFs(checkIsNull(displayObject.getValue()));
					res.setMinimumCibilFlag(displayObject.getIsMatched());
					break;
				case 20:
					res.setCommercialCibilFp(checkIsNull(displayObject.getFpValue()));
					res.setCommercialCibilFs(checkIsNull(displayObject.getValue()));
					res.setCommercialCibilFlag(displayObject.getIsMatched());
					break;
			}
		}
		return res;
	}
	
	
	private EligibilityDetailRequest createEligibilityRequest(Long applicationId) {
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicationId);
		if(CommonUtils.isObjectNullOrEmpty(applicationMaster)) {
			logger.warn("ApplicationMaster Object Found NUll ForApplication==========>{}",applicationId);
			return null;
		}
		EligibililityRequest eligibililityRequest = new  EligibililityRequest();
		eligibililityRequest.setApplicationId(applicationMaster.getId());
		eligibililityRequest.setProductId(applicationMaster.getProductId().longValue());
		
		try {
			EligibilityResponse eligibilityResponse = eligibilityClient.corporateLoanData(eligibililityRequest);
			if(eligibilityResponse == null || eligibilityResponse.getData() == null) {
				logger.warn("EligibilityResponse Found NUll for ApplicationId===>{}",applicationId);
				return null;
			}
			CLEligibilityRequest clEligibilityRequest = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)eligibilityResponse.getData(), CLEligibilityRequest.class);
				if(clEligibilityRequest == null) {
					logger.warn("Corporate Eligibility Request Found Null====>");
					return null;
				}
				EligibilityDetailRequest target = new EligibilityDetailRequest();
				BeanUtils.copyProperties(clEligibilityRequest, target);
				target.setApplicationId(applicationId);
				return target;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private com.capitaworld.sidbi.integration.model.bankstatement.Data createBankStatementRequest(Long applicationId) {
		ReportRequest reportRequest = new ReportRequest();
		reportRequest.setApplicationId(applicationId);
		try {
			AnalyzerResponse detailsFromReport = analyzerClient.getDetailsFromReport(reportRequest);
			if(detailsFromReport == null || detailsFromReport.getData() == null) {
				return null;
			}
			Data dataResponse =  (Data)MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)detailsFromReport.getData(), Data.class);
			com.capitaworld.sidbi.integration.model.bankstatement.Data dataRequest = new com.capitaworld.sidbi.integration.model.bankstatement.Data();
			if(!CommonUtils.isObjectNullOrEmpty(dataResponse)) {
				BeanUtils.copyProperties(dataResponse, dataRequest);
				//Set Customer Info
				if(!CommonUtils.isObjectNullOrEmpty(dataResponse.getCustomerInfo())) {
					com.capitaworld.sidbi.integration.model.bankstatement.CustomerInfo customerInfo = new com.capitaworld.sidbi.integration.model.bankstatement.CustomerInfo();
					BeanUtils.copyProperties(dataResponse.getCustomerInfo(), customerInfo);
					dataRequest.setCustomerInfo(customerInfo);
				}
				//Set Monthly Details
				if(!CommonUtils.isObjectNullOrEmpty(dataResponse.getMonthlyDetailList())) {
					if(!CommonUtils.isListNullOrEmpty(dataResponse.getMonthlyDetailList().getMonthlyDetails())) {
						List<com.capitaworld.sidbi.integration.model.bankstatement.MonthlyDetail> monthlyDetailList = new ArrayList<>(dataResponse.getMonthlyDetailList().getMonthlyDetails().size());
						for(MonthlyDetail monResponse :  dataResponse.getMonthlyDetailList().getMonthlyDetails()) {
							com.capitaworld.sidbi.integration.model.bankstatement.MonthlyDetail moRequest = new com.capitaworld.sidbi.integration.model.bankstatement.MonthlyDetail();
							BeanUtils.copyProperties(monResponse, moRequest);
							monthlyDetailList.add(moRequest);
						}
						dataRequest.setMonthlyDetails(monthlyDetailList);
					}	
				}
				
				//Set Summary Info
				
				if(!CommonUtils.isObjectNullOrEmpty(dataRequest.getSummaryInfo())) {
					com.capitaworld.sidbi.integration.model.bankstatement.SummaryInfo summaryInfo = new com.capitaworld.sidbi.integration.model.bankstatement.SummaryInfo();
					BeanUtils.copyProperties(dataResponse.getSummaryInfo(), summaryInfo);
					
					//Set SummaryInfoTotalDetail
					if(!CommonUtils.isObjectNullOrEmpty(dataResponse.getSummaryInfo().getSummaryInfoTotalDetails())) {
						com.capitaworld.sidbi.integration.model.bankstatement.SummaryInfoTotalDetails totalDetails = new  com.capitaworld.sidbi.integration.model.bankstatement.SummaryInfoTotalDetails();
						BeanUtils.copyProperties(dataResponse.getSummaryInfo().getSummaryInfoTotalDetails(), totalDetails);
						summaryInfo.setSummaryInfoTotalDetails(totalDetails);
					}
					
					//Set SummaryInfoAverageDetails
					if(!CommonUtils.isObjectNullOrEmpty(dataResponse.getSummaryInfo().getSummaryInfoAverageDetails())) {
						com.capitaworld.sidbi.integration.model.bankstatement.SummaryInfoAverageDetails averageDetails = new  com.capitaworld.sidbi.integration.model.bankstatement.SummaryInfoAverageDetails();
						BeanUtils.copyProperties(dataResponse.getSummaryInfo().getSummaryInfoAverageDetails(), averageDetails);
						summaryInfo.setSummaryInfoAverageDetails(averageDetails);
					}
					dataRequest.setSummaryInfo(summaryInfo);
				}
				
				//Set Top5 Fund Received List
				if(!CommonUtils.isObjectNullOrEmpty(dataResponse.getTop5FundReceivedList())) {
					if(!CommonUtils.isListNullOrEmpty(dataResponse.getTop5FundReceivedList().getItem())) {
						List<com.capitaworld.sidbi.integration.model.bankstatement.Item> top5FundReceivedList = new ArrayList<>(dataResponse.getTop5FundReceivedList().getItem().size());
						for(Item itemResponse :  dataResponse.getTop5FundReceivedList().getItem()) {
							com.capitaworld.sidbi.integration.model.bankstatement.Item itemRequest = new com.capitaworld.sidbi.integration.model.bankstatement.Item();
							BeanUtils.copyProperties(itemResponse, itemRequest);
							top5FundReceivedList.add(itemRequest);
						}
						dataRequest.setTop5FundReceivedList(top5FundReceivedList);
					}	
				}
				
				//Set Top5 Fund Transfered List
				if(!CommonUtils.isObjectNullOrEmpty(dataResponse.getTop5FundTransferedList())) {
					if(!CommonUtils.isListNullOrEmpty(dataResponse.getTop5FundTransferedList().getItem())) {
						List<com.capitaworld.sidbi.integration.model.bankstatement.Item> top5FundTransferedList = new ArrayList<>(dataResponse.getTop5FundTransferedList().getItem().size());
						for(Item itemResponse :  dataResponse.getTop5FundTransferedList().getItem()) {
							com.capitaworld.sidbi.integration.model.bankstatement.Item itemRequest = new com.capitaworld.sidbi.integration.model.bankstatement.Item();
							BeanUtils.copyProperties(itemResponse, itemRequest);
							top5FundTransferedList.add(itemRequest);
						}
						dataRequest.setTop5FundTransferedList(top5FundTransferedList);
					}	
				}
				
				//Set BouncedOrPenalXnList
				if(!CommonUtils.isObjectNullOrEmpty(dataResponse.getBouncedOrPenalXnList())) {
					if(!CommonUtils.isListNullOrEmpty(dataResponse.getBouncedOrPenalXnList().getBouncedOrPenalXns())) {
						List<com.capitaworld.sidbi.integration.model.bankstatement.BouncedOrPenalXn> bouncedOrPanelXnList = new ArrayList<>(dataResponse.getBouncedOrPenalXnList().getBouncedOrPenalXns().size());
						for(BouncedOrPenalXn bounceResponse :  dataResponse.getBouncedOrPenalXnList().getBouncedOrPenalXns()) {
							com.capitaworld.sidbi.integration.model.bankstatement.BouncedOrPenalXn bounceRequest = new com.capitaworld.sidbi.integration.model.bankstatement.BouncedOrPenalXn();
							BeanUtils.copyProperties(bounceResponse, bounceRequest);
							bouncedOrPanelXnList.add(bounceRequest);
						}
						dataRequest.setBouncedOrPenalXnList(bouncedOrPanelXnList);
					}	
				}
				
				//Set Panel List
				if(!CommonUtils.isObjectNullOrEmpty(dataResponse.getPenalList())) {
					if(!CommonUtils.isListNullOrEmpty(dataResponse.getPenalList().getBouncedOrPenalXns())) {
						List<com.capitaworld.sidbi.integration.model.bankstatement.BouncedOrPenalXn> panelXnList = new ArrayList<>(dataResponse.getPenalList().getBouncedOrPenalXns().size());
						for(BouncedOrPenalXn bounceResponse : dataResponse.getPenalList().getBouncedOrPenalXns()) {
							com.capitaworld.sidbi.integration.model.bankstatement.BouncedOrPenalXn bounceRequest = new com.capitaworld.sidbi.integration.model.bankstatement.BouncedOrPenalXn();
							BeanUtils.copyProperties(bounceResponse, bounceRequest);
							panelXnList.add(bounceRequest);
						}
						dataRequest.setPenalList(panelXnList);
					}	
				}
				
				//Set Xns
				
				if(!CommonUtils.isObjectNullOrEmpty(dataResponse.getXns())) {
					com.capitaworld.sidbi.integration.model.bankstatement.Xns xns = new com.capitaworld.sidbi.integration.model.bankstatement.Xns();
					BeanUtils.copyProperties(dataResponse.getXns(), xns);
					if(!CommonUtils.isObjectNullOrEmpty(dataResponse.getXns().getXn())) {
						com.capitaworld.sidbi.integration.model.bankstatement.Xn xn = new com.capitaworld.sidbi.integration.model.bankstatement.Xn();
						BeanUtils.copyProperties(dataResponse.getXns().getXn(), xn);
					}
					dataRequest.setXns(xns);
				}
				dataRequest.setApplicationId(applicationId);
				return dataRequest;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	
	
	
	private String checkIsNull(Object value) {
		return CommonUtils.isObjectNullOrEmpty(value) ? null : value.toString();
	}
	
	private List<DirectorBackgroundDetailRequest> getDirectorListForSidbi(Long applicationId){
		List<DirectorBackgroundDetail> direcotors = directorBackgroundDetailsRepository.listPromotorBackgroundFromAppId(applicationId);
		if(CommonUtils.isListNullOrEmpty(direcotors)) {
			logger.warn("No Directors Found for Application Id ==>{}",applicationId);
			return Collections.emptyList();
		}else {
			List<DirectorBackgroundDetailRequest> listData = new ArrayList<>(direcotors.size());
			for(DirectorBackgroundDetail source : direcotors) {
				DirectorBackgroundDetailRequest target = new DirectorBackgroundDetailRequest();
				target.setName(source.getDirectorsName());
				if(source.getGender() != null) {
					target.setGender(Gender.getById(source.getGender()).getValue());						
				}
				target.setAddress(source.getAddress());
				target.setPanNo(source.getPanNo());
				if(source.getRelationshipType() != null) {
					target.setRelationshipType(DirectorRelationshipType.getById(source.getRelationshipType()).getValue());						
				}
				target.setMobile(source.getMobile());
				target.setDob(source.getDob());
				target.setTotalExperience(source.getTotalExperience());
				target.setNetworth(source.getNetworth());
				if(!CommonUtils.isObjectNullOrEmpty(source.getDin()))
				    target.setDin(source.getDin().toString());
				if(source.getSalutationId() != null) {
					target.setTitle(Title.getById(source.getSalutationId()).getValue());					
				}
				target.setApplicationId(applicationId);
				listData.add(target);
			}
			return listData;
		}
	}
	
	private List<CurrentFinancialArrangementsDetailRequest> getCurrentFinancialDetaisForSidbi(Long applicationId){
		List<FinancialArrangementsDetail> financialDetails = financialArrangementDetailsRepository.listSecurityCorporateDetailByAppId(applicationId);
		if(CommonUtils.isListNullOrEmpty(financialDetails)) {
			logger.warn("No Current Financial Details Found for Application Id ==>{}",applicationId);
			return Collections.emptyList();
		}else {
			List<CurrentFinancialArrangementsDetailRequest> listData = new ArrayList<>(financialDetails.size());
			for(FinancialArrangementsDetail source : financialDetails) {
				CurrentFinancialArrangementsDetailRequest target = new CurrentFinancialArrangementsDetailRequest();
				target.setLenderName(source.getFinancialInstitutionName());
				target.setSanctionedAmount(source.getAmount());
				target.setAmount(source.getOutstandingAmount());
				target.setApplicationId(applicationId);
				listData.add(target);
			}
			return listData;
		}
	}
	
	private List<AchievementDetailRequest> getAchievementDetaisForSidbi(Long applicationId){
		List<AchievementDetail> achievementDetails = achievementDetailsRepository.listAchievementFromAppId(applicationId);
		if(CommonUtils.isListNullOrEmpty(achievementDetails)) {
			logger.warn("No achievementDetails Found for Application Id ==>{}",applicationId);
			return Collections.emptyList();
		}else {
			List<AchievementDetailRequest> listData = new ArrayList<>(achievementDetails.size());
			for(AchievementDetail source : achievementDetails) {
				AchievementDetailRequest target = new AchievementDetailRequest();
				target.setYear(source.getYear());
				target.setMilestoneAchievedDetail(source.getMilestoneAchievedDetail());
				target.setApplicationId(applicationId);
				listData.add(target);
			}
			return listData;
		}
	}
	
	
	private List<ExistingProductDetailRequest> getExistingProductForSidbi(Long applicationId){
		List<ExistingProductDetail> existingDetails = existingProductDetailsRepository.listExistingProductFromAppId(applicationId);
		if(CommonUtils.isListNullOrEmpty(existingDetails)) {
			logger.warn("No existingDetails Found for Application Id ==>{}",applicationId);
			return Collections.emptyList();
		}else {
			List<ExistingProductDetailRequest> listData = new ArrayList<>(existingDetails.size());
			for(ExistingProductDetail source : existingDetails) {
				ExistingProductDetailRequest target = new ExistingProductDetailRequest();
				target.setProduct(source.getProduct());
				target.setApplication(source.getApplication());
				target.setApplicationId(applicationId);
				listData.add(target);
			}
			return listData;
		}
	}
	
	private List<ProposedProductDetailRequest> getProposedProductForSidbi(Long applicationId){
		List<ProposedProductDetail> proposedDetails = proposedProductDetailsRepository.findByApplicationIdIdAndIsActive(applicationId, true);
		if(CommonUtils.isListNullOrEmpty(proposedDetails)) {
			logger.warn("No proposedDetails Found for Application Id ==>{}",applicationId);
			return Collections.emptyList();
		}else {
			List<ProposedProductDetailRequest> listData = new ArrayList<>(proposedDetails.size());
			for(ProposedProductDetail source : proposedDetails) {
				ProposedProductDetailRequest target = new ProposedProductDetailRequest();
				target.setProduct(source.getProduct());
				target.setApplication(source.getApplication());
				target.setApplicationId(applicationId);
				listData.add(target);
			}
			return listData;
		}
	}
	
	private List<OwnershipDetailRequest> getOwnershipDetailForSidbi(Long applicationId){
		List<OwnershipDetail> ownershipDetails = ownershipDetailsRepository.listOwnershipFromAppId(applicationId);
		if(CommonUtils.isListNullOrEmpty(ownershipDetails)) {
			logger.warn("No ownershipDetails Found for Application Id ==>{}",applicationId);
			return Collections.emptyList();
		}else {
			List<OwnershipDetailRequest> listData = new ArrayList<>(ownershipDetails.size());
			for(OwnershipDetail source : ownershipDetails) {
				OwnershipDetailRequest target = new OwnershipDetailRequest();
				if(source.getShareHoldingCategoryId() != null) {
					target.setShareHoldingCategoryType(ShareHoldingCategory.getById(source.getShareHoldingCategoryId()).getValue());					
				}
				target.setStackPercentage(source.getStackPercentage());
				target.setRemarks(source.getRemarks());
				target.setApplicationId(applicationId);
				listData.add(target);
			}
			return listData;
		}
	}
	
	
	private List<CreditRatingOrganizationDetailRequest> getCreditRatingDetailForSidbi(Long applicationId){
		List<CreditRatingOrganizationDetail> creditRatingDetails = creditRatingOrganizationDetailsRepository.listCreditRatingOrganizationDetailsFromAppId(applicationId);
		if(CommonUtils.isListNullOrEmpty(creditRatingDetails)) {
			logger.warn("No creditRatingDetails Found for Application Id ==>{}",applicationId);
			return Collections.emptyList();
		}else {
			List<CreditRatingOrganizationDetailRequest> listData = new ArrayList<>(creditRatingDetails.size());
			for(CreditRatingOrganizationDetail source : creditRatingDetails) {
				CreditRatingOrganizationDetailRequest target = new CreditRatingOrganizationDetailRequest();
				target.setRatingDate(source.getRatingDate());
				if(source.getRatingAgencyId() != null) {
					target.setRatingAgency(RatingAgency.getById(source.getRatingAgencyId()).getValue());					
				}
				target.setFacilityName(source.getEntityName());
				if(source.getCreditRatingFundId() != null) {
					target.setType(CreditRatingFund.getById(source.getCreditRatingFundId()).getValue());					
				}
				if(source.getCreditRatingTermId() != null) {
					target.setCreditRatingTerm(CreditRatingTerm.getById(source.getCreditRatingTermId()).getValue());					
				}
				target.setAmount(source.getAmount());
				if(source.getCreditRatingOptionId() != null) {
					try {
						OneFormResponse oneFormResponse = oneFormClient.getRatingById(source.getCreditRatingOptionId().longValue());
						if(oneFormResponse !=null && oneFormResponse.getData() != null) {
							MasterResponse masterResponse =  (MasterResponse)MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)oneFormResponse.getData(), MasterResponse.class);
							if(masterResponse != null) {
								target.setRating(masterResponse.getValue());
							}
						}
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				target.setApplicationId(applicationId);
				listData.add(target);
			}
			return listData;
		}
	}
	
	private List<GuarantorsCorporateDetailRequest> getGuarantorDetailsForSidbi(Long applicationId){
		List<GuarantorsCorporateDetail> guarantorDetails = guarantorsCorporateDetailRepository.listGuarantorsCorporateFromAppId(applicationId);
		if(CommonUtils.isListNullOrEmpty(guarantorDetails)) {
			logger.warn("No guarantorDetails Found for Application Id ==>{}",applicationId);
			return Collections.emptyList();
		}else {
			List<GuarantorsCorporateDetailRequest> listData = new ArrayList<>(guarantorDetails.size());
			for(GuarantorsCorporateDetail source : guarantorDetails) {
				GuarantorsCorporateDetailRequest target = new GuarantorsCorporateDetailRequest();
				target.setName(source.getName());
				target.setPropertiesOwned(source.getPropertiesOwned());
				target.setValue(source.getPropertyType());
				target.setAddress(source.getAddress());
				target.setOccupation(source.getOccupation());
				target.setApplicationId(applicationId);
				listData.add(target);
			}
			return listData;
		}
	}
	
	private List<MonthlyTurnoverDetailRequest> getMonthlyTurnOverForSidbi(Long applicationId){
		List<MonthlyTurnoverDetail> monthlyTurnOver = monthlyTurnoverDetailRepository.listMonthlyTurnoverFromAppId(applicationId);
		if(CommonUtils.isListNullOrEmpty(monthlyTurnOver)) {
			logger.warn("No monthlyTurnOver Found for Application Id ==>{}",applicationId);
			return Collections.emptyList();
		}else {
			List<MonthlyTurnoverDetailRequest> listData = new ArrayList<>(monthlyTurnOver.size());
			for(MonthlyTurnoverDetail source : monthlyTurnOver) {
				MonthlyTurnoverDetailRequest target = new MonthlyTurnoverDetailRequest();
				target.setMonthName(source.getMonthName());
				target.setAmount(source.getAmount());
				target.setApplicationId(applicationId);
				listData.add(target);
			}
			return listData;
		}
	}
	
	private List<AssociatedConcernDetailRequest> getAssociatedConcernForSidbi(Long applicationId){
		List<AssociatedConcernDetail> associatedConcernDetails = associatedConcernDetailRepository.listAssociatedConcernFromAppId(applicationId);
		if(CommonUtils.isListNullOrEmpty(associatedConcernDetails)) {
			logger.warn("No associatedConcernDetails Found for Application Id ==>{}",applicationId);
			return Collections.emptyList();
		}else {
			List<AssociatedConcernDetailRequest> listData = new ArrayList<>(associatedConcernDetails.size());
			for(AssociatedConcernDetail source : associatedConcernDetails) {
				AssociatedConcernDetailRequest target = new AssociatedConcernDetailRequest();
				BeanUtils.copyProperties(source, target);
				target.setApplicationId(applicationId);
//				target.setName(source.getName());
//				target.setNatureAssociation(source.getNatureAssociation());
//				target.setNameOfDirector(source.getNameOfDirector());
//				target.setInvestedAmount(source.getInvestedAmount());
//				target.setNatureActivity(source.getNatureActivity());
//				target.setTurnOverFirstYear(source.getTurnOverFirstYear());
//				target.setTurnOverSecondYear(source.getTurnOverSecondYear());
//				target.setTurnOverThirdYear(source.getTurnOverThirdYear());
//				target.setProfitPastOneYear(source.getProfitPastOneYear());
//				target.setProfitPastTwoYear(source.getProfitPastTwoYear());
//				target.setProfitPastThreeYear(source.getProfitPastThreeYear());
//				target.setBriefDescription(source.getBriefDescription());
				listData.add(target);
			}
			return listData;
		}
	}
	
	
	private LoanMasterRequest createObj(PrimaryCorporateDetail applicationMaster) {
		LoanMasterRequest loanMasterRequest = new LoanMasterRequest();
		loanMasterRequest.setTenure(applicationMaster.getTenure());
		loanMasterRequest.setProductName(CommonUtils.LoanType.getType(applicationMaster.getProductId()).getName());
		if(applicationMaster.getApplicationStatusMaster() != null) {
			loanMasterRequest.setStatus(applicationMaster.getApplicationStatusMaster().getStatus());			
		}
        loanMasterRequest.setBusinessTypeId(getIndustryIrrByApplication(applicationMaster.getId()));
		loanMasterRequest.setAmount(applicationMaster.getAmount());
		loanMasterRequest.setHaveCollateralSecurities(applicationMaster.getHaveCollateralSecurity());
		loanMasterRequest.setCollateralSecuritiesValue(applicationMaster.getCollateralSecurityAmount());
		loanMasterRequest.setApplicationId(applicationMaster.getId());
		loanMasterRequest.setApplicationCode(applicationMaster.getApplicationCode());
		if(applicationMaster.getPurposeOfLoanId() != null) {
			loanMasterRequest.setLoanPurpose(PurposeOfLoan.getById(applicationMaster.getPurposeOfLoanId()).getValue());			
		}
		loanMasterRequest.setApplicationDate(applicationMaster.getCreatedDate());

		ProposalMappingRequest proposalMappingRequest = new ProposalMappingRequest();
		proposalMappingRequest.setApplicationId(applicationMaster.getId());
		Long fpProductId=null;
		ProposalMappingResponse proposalMappingResponse = proposalService.listOfFundSeekerProposal(proposalMappingRequest);
		if(!CommonUtils.isObjectListNull(proposalMappingResponse) && !CommonUtils.isObjectListNull(proposalMappingResponse.getDataList())) {
			List<Map<String, Object>> proposalMappingResponseDataList = (List<Map<String, Object>>) proposalMappingResponse.getDataList();

			try {
				ProposalMappingRequest proposalMappingRequest1 = MultipleJSONObjectHelper.getObjectFromMap(proposalMappingResponseDataList.get(0),
						ProposalMappingRequest.class);
				fpProductId = proposalMappingRequest1.getFpProductId();
				loanMasterRequest.setFpProductId(proposalMappingRequest1.getFpProductId());
				loanMasterRequest.setRoi(proposalMappingRequest1.getElRoi());
				loanMasterRequest.setProcessingFee(proposalMappingRequest1.getProcessingFee());
                loanMasterRequest.setEmi(proposalMappingRequest1.getEmi());
                ProductMaster productMstr = productMasterRepository.findOne(proposalMappingRequest1.getFpProductId());
                if(productMstr != null) {
                	loanMasterRequest.setFpProductName(productMstr.getName());                	
                }
			} catch (IOException e) {
				logger.error("error while setting details from proposal details");
				e.printStackTrace();
			}
		}
		if(!CommonUtils.isObjectNullOrEmpty(fpProductId)){
			ProductMaster master = productMasterRepository.findOne(fpProductId);
			if (master.getIsActive()) {
				UsersRequest request = new UsersRequest();
				request.setId(master.getUserId());
				UserResponse userResponse = userClient.getFPDetails(request);
				FundProviderDetailsRequest fundProviderDetailsRequest = null;
				try {
					fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
                            (LinkedHashMap<String, Object>) userResponse.getData(), FundProviderDetailsRequest.class);
				} catch (IOException e) {
					logger.error("error while setting users details from proposal details");
					e.printStackTrace();
				}
				loanMasterRequest.setBankName(fundProviderDetailsRequest.getOrganizationName());
			}
		}
		return loanMasterRequest;
	}
	
	private CorporateProfileRequest createProfileObj(CorporateApplicantDetail corporateApplicantDetail,Long userId) {
		CorporateProfileRequest corporateProfileRequest = new CorporateProfileRequest();
		corporateProfileRequest.setOrganisationName(corporateApplicantDetail.getOrganisationName());
		corporateProfileRequest.setGstin(corporateApplicantDetail.getGstIn());
		if(corporateApplicantDetail.getConstitutionId() != null) {
			corporateProfileRequest.setConstitution(Constitution.getById(corporateApplicantDetail.getConstitutionId()).getValue());				
		}
		corporateProfileRequest.setEstablishmentYear(corporateApplicantDetail.getEstablishmentYear());
		corporateProfileRequest.setPan(corporateApplicantDetail.getPanNo());
		UsersRequest usersRequest = null;
		try {
			UserResponse emailMobile = userClient.getEmailMobile(userId);
			usersRequest = MultipleJSONObjectHelper
					.getObjectFromMap((LinkedHashMap<String, Object>) emailMobile.getData(), UsersRequest.class);
			corporateProfileRequest.setContactNo(usersRequest.getMobile());
			corporateProfileRequest.setEmail(usersRequest.getEmail());
		} catch (Exception e) {
			logger.info("Throw Exception While Get User Email and Mobile");
			e.printStackTrace();
		}
		
		//setting Registered Address
		AddressRequest addressRequest = new AddressRequest();
		addressRequest.setPremiseNumber(corporateApplicantDetail.getRegisteredPremiseNumber());
		addressRequest.setStreetName(corporateApplicantDetail.getRegisteredStreetName());
		addressRequest.setLandMark(corporateApplicantDetail.getRegisteredLandMark());
		if(corporateApplicantDetail.getRegisteredPincode() != null) {
			addressRequest.setPincode(corporateApplicantDetail.getRegisteredPincode().toString());				
		}
		try {
			addressRequest.setCity(CommonDocumentUtils.getCity(corporateApplicantDetail.getRegisteredCityId(), oneFormClient));
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			addressRequest.setState(CommonDocumentUtils.getState(corporateApplicantDetail.getRegisteredStateId().longValue(), oneFormClient));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			addressRequest.setCountry(CommonDocumentUtils.getCountry(corporateApplicantDetail.getRegisteredCountryId().longValue(), oneFormClient));
		}catch(Exception e) {
			e.printStackTrace();
		}
		corporateProfileRequest.setRegisteredAddress(addressRequest);
		
		//setting Registered Address
		AddressRequest administrativeRequest = new AddressRequest();
		if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getAdministrativePremiseNumber()))
		    administrativeRequest.setPremiseNumber(corporateApplicantDetail.getAdministrativePremiseNumber());
        if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getAdministrativeStreetName()))
		    administrativeRequest.setStreetName(corporateApplicantDetail.getAdministrativeStreetName());
        if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getAdministrativeLandMark()))
            administrativeRequest.setLandMark(corporateApplicantDetail.getAdministrativeLandMark());
		if(corporateApplicantDetail.getAdministrativePincode() != null) {
			administrativeRequest.setPincode(corporateApplicantDetail.getAdministrativePincode().toString());				
		}
		try {
            if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getAdministrativeCityId()))
			    administrativeRequest.setCity(CommonDocumentUtils.getCity(corporateApplicantDetail.getAdministrativeCityId(), oneFormClient));
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
            if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getAdministrativeStateId()))
			    administrativeRequest.setState(CommonDocumentUtils.getState(corporateApplicantDetail.getAdministrativeStateId().longValue(), oneFormClient));
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
            if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getAdministrativeCountryId()))
			    administrativeRequest.setCountry(CommonDocumentUtils.getCountry(corporateApplicantDetail.getAdministrativeCountryId().longValue(), oneFormClient));
		}catch(Exception e) {
			e.printStackTrace();
		}
		corporateProfileRequest.setAdministrativeAddress(administrativeRequest);
		if(corporateApplicantDetail.getKeyVericalFunding() != null) {
			corporateProfileRequest.setIndustry(Industry.getById(corporateApplicantDetail.getKeyVericalFunding().intValue()).getValue());				
		}
		
		if(corporateApplicantDetail.getKeyVerticalSector() != null) {
//			corporateProfileRequest.setIndustry(Sector.getById(corporateApplicantDetail.getKeyVerticalSector().intValue()).getValue());				
		}
		
		if(corporateApplicantDetail.getKeyVerticalSubsector() != null) {
			//key vertical Subsector
			try{
				OneFormResponse oneFormResponse=oneFormClient.getSubSecNameByMappingId(corporateApplicantDetail.getKeyVerticalSubsector());
				if(!CommonUtils.isObjectNullOrEmpty(oneFormResponse.getData()))
					corporateProfileRequest.setSubSector((String)oneFormResponse.getData());
			}
			catch (Exception e) {
				// TODO: handle exception
				logger.warn("error while getting key vertical sub-sector");
			}

		}
		corporateProfileRequest.setUdhyogAdhaar(corporateApplicantDetail.getAadhar());
		corporateProfileRequest.setAbout(corporateApplicantDetail.getAboutUs());
		corporateProfileRequest.setContLiabilityFyAmt(corporateApplicantDetail.getContLiabilityFyAmt());
		corporateProfileRequest.setContLiabilitySyAmt(corporateApplicantDetail.getContLiabilitySyAmt());
		corporateProfileRequest.setContLiabilityTyAmt(corporateApplicantDetail.getContLiabilityTyAmt());
		corporateProfileRequest.setApplicationId(corporateApplicantDetail.getApplicationId().getId());
		
		return corporateProfileRequest;
	}

	public Map<String, Object> getFpDetailsByFpProductId(Long fpProductId) throws Exception{
		 Map<String, Object> map= null;
		try {
			 map = new HashMap<String, Object>();
		ProductMaster productMaster = productMasterRepository.findOne(fpProductId);
		if(productMaster!=null) {
		UsersRequest request = new UsersRequest();
		request.setId(productMaster.getUserId());
		UserResponse resp = userClient.getEmailMobile(productMaster.getUserId());
		UsersRequest applicantRequest = MultipleJSONObjectHelper.getObjectFromMap(
				(Map<String, Object>) resp.getData(), UsersRequest.class);
		map.put("fpName", applicantRequest.getName());
		map.put("mobileNo", applicantRequest.getMobile());
		map.put("fpUserId", productMaster.getUserId());
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	
	public LoanApplicationRequest getLoanApplicationDetails(Long userId, Long applicationId) {
		
		LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
		
		
		LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
		
		BeanUtils.copyProperties(applicationMaster, applicationRequest);
		
		return applicationRequest;
	}

	/* (non-Javadoc)
	 * @see com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService#getDataForCGTMSE(java.lang.Long)
	 */
	@Override
	public CGTMSECalcDataResponse getDataForCGTMSE(Long applicationId) throws Exception {
		try {
			
			logger.info("In getDataForCGTMSE");
		CGTMSECalcDataResponse response = new CGTMSECalcDataResponse();
		CorporateApplicantDetail applicantDetail =	corporateApplicantDetailRepository.findByApplicationIdIdAndIsActive(applicationId, true);
		if(applicantDetail!=null) {
			//key vertical Subsector
	        try
	        {
	        if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getKeyVerticalSubsector()))
	        {
	        	OneFormResponse oneFormResponse=oneFormClient.getSubSecNameByMappingId(applicantDetail.getKeyVerticalSubsector());
	        	response.setSubSector((String)oneFormResponse.getData());
	        }
	        }
	        catch (Exception e) {
				// TODO: handle exception
	        	logger.warn("error while getting key vertical sub-sector");
			}
	        
	        response.setStateId(applicantDetail.getRegisteredStateId()!=null ? Long.valueOf(applicantDetail.getRegisteredStateId().toString()) : null);
			response.setColleteralValue(applicantDetail.getTotalCollateralDetails());
		}
		
		LoanApplicationMaster loan = loanApplicationRepository.getById(applicationId);
		
		if(loan!=null) {
			response.setLoanAmount(loan.getAmount());
		}
		
		List<DirectorBackgroundDetail> directorList = directorBackgroundDetailsRepository.listPromotorBackgroundFromAppId(applicationId);
		
		response.setDirectorRespo(new ArrayList<DirectorBackgroundDetailResponse>());
		if(directorList!=null && !directorList.isEmpty()) {
			for(DirectorBackgroundDetail detail : directorList) {
				if(detail.getGender()==Gender.FEMALE.getId()) {
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
		String year = String.valueOf(yearInt);
		AssetsDetails assetsDetails = null; 
		assetsDetails = assetsDetailsRepository.getAssetsDetails(applicationId, year);
		if(assetsDetails == null) {
			year = year+".0";
			assetsDetails = assetsDetailsRepository.getAssetsDetails(applicationId, year);
		}
		
		if(assetsDetails!=null) {
			response.setGrossBlock(assetsDetails.getGrossBlock());
		}
		
		return response;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public LoanApplicationRequest getProposalDataFromApplicationId(Long applicationId) {
		
		try {
			logger.info("ENter in get Proposal Data From ApplicationId ------------------->" + applicationId);
			LoanApplicationRequest applicationRequest= new LoanApplicationRequest();
			LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);
			
			if(CommonUtils.isObjectNullOrEmpty(loanApplicationMaster)) {
				logger.info("Application MAster response null or empty by above applicaiton iD");
				return null;
			}
			BeanUtils.copyProperties(loanApplicationMaster, applicationRequest);
			ProposalMappingResponse response = proposalDetailsClient.getInPricipleById(applicationId);
			if (response != null && response.getData() != null) {
				Map<String, Object> proposalresp = null;
				try {
					proposalresp = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) response.getData(), Map.class);
				} catch (IOException e) {
					logger.info("could not extract data");
					e.printStackTrace();
				}
				 
				if (!CommonUtils.isObjectNullOrEmpty(proposalresp)) {
					applicationRequest.setLoanAmount(proposalresp.get("amount") != null ? Double.valueOf(proposalresp.get("amount").toString()) : 0.0);
					applicationRequest.setTenure(proposalresp.get("tenure") != null ? Double.valueOf(proposalresp.get("tenure").toString()) : 0.0);
					applicationRequest.setEmiAmount(proposalresp.get("emi_amount") != null ? Double.valueOf(proposalresp.get("emi_amount").toString()) : 0.0);
					applicationRequest.setTypeOfLoan(CommonUtils.LoanType.getType(applicationRequest.getProductId()).toString());
					applicationRequest.setInterestRate(proposalresp.get("rate_interest") != null ? Double.valueOf(proposalresp.get("rate_interest").toString()) : 0.0);
					applicationRequest.setOnlinePaymentSuccess(true);

					CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId);
					if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)){
						applicationRequest.setNameOfEntity(corporateApplicantDetail.getOrganisationName());
					}
					Object orgObject = proposalresp.get("org_id");
					if(!CommonUtils.isObjectNullOrEmpty(orgObject)) {
						Integer orgObjectInt = (Integer) orgObject;
						applicationRequest.setFundProvider(CommonUtils.getOrganizationName(orgObjectInt.longValue()));

					}
					return applicationRequest;
				} else{
					logger.info("Proposal Map is null or empty !!");
					return null;
				}
			} else {
				logger.info("Proposal Response is null or empty !!");
			}
		} catch (Exception e) {
			logger.info("Throw Exception WHile Get Proposal Detaisl By APplicationId");
			e.printStackTrace();
		}
		return null;
	}
	
	private UserOrganisationRequest getOrganizationDetails(Long organizationId) {
		// Get Organization Data by OrganizationId
		try {
			return userClient.getByOrgId(organizationId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public GenerateTokenRequest setUrlAndTokenInSidbiClient(Long organizationId, Long applicationId) throws Exception {
		UserOrganisationRequest request = getOrganizationDetails(organizationId);
		if(request == null) {
			logger.warn("Something is Wrong as Organization Data not found for Organization id ==>{}",organizationId);
			return null;
		}
		if(CommonUtils.isObjectNullOrEmpty(isProduction)) {
			logger.warn("Please Set 'capitaworld.sidbi.integration.is_production' key value to Set URL");
			return null;
		}
		if(Boolean.valueOf(isProduction)) {
			sidbiIntegrationClient.setIntegrationBaseUrl(request.getProductionUrl());
		}else {
			sidbiIntegrationClient.setIntegrationBaseUrl(request.getUatUrl());
		}
		
		logger.warn("Getting token from SidbiIntegrationClient --------------" +applicationId);
		GenerateTokenRequest generateTokenRequest = new GenerateTokenRequest();
		generateTokenRequest.setApplicationId(applicationId);
		generateTokenRequest.setPassword(request.getPassword());
		
		String token=null;
			token = sidbiIntegrationClient.getToken(generateTokenRequest);
			sidbiIntegrationClient.setToken(token);
			logger.warn("Successfully  set token from SidbiIntegrationClient -------------- applicationId " +applicationId);
			/*Start Save Token in loan DB */
			/*TokenDetail tokenDetail =new TokenDetail();
			tokenDetail.setApplicationId(applicationId);
			tokenDetail.setCreatedDate(new Date());
			tokenDetail.setIsActive(true);
			tokenDetail.setToken(token);*/
			/*End  */
		return generateTokenRequest ;
	}
	
	
	@Override
	public HunterRequestDataResponse getDataForHunter(Long applicationId) throws Exception {
		try {
			
			logger.info("In getDataForHunter with Application ID : "+applicationId);
			HunterRequestDataResponse response = new HunterRequestDataResponse();
			logger.info("Fetching Corporate Applicant Details for application Id : "+applicationId);
		CorporateApplicantDetail applicantDetail =	corporateApplicantDetailRepository.findByApplicationIdIdAndIsActive(applicationId, true);
		if(applicantDetail!=null) {
			logger.info("FetchedS Corporate Applicant Details for application Id : "+applicationId);
			//key vertical Subsector
			
			String state= null;
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
						state = masterResponse.getValue() ;
					} else {

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			String city= null;
			List<Long> cityList = new ArrayList<>();
			if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredStateId()))
				cityList.add(Long.valueOf(applicantDetail.getRegisteredStateId()));
			if (!CommonUtils.isListNullOrEmpty(cityList)) {
				try {
					OneFormResponse oneFormResponse = oneFormClient.getCityByCityListId(cityList);
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
							.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper
								.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						city = masterResponse.getValue();
					} else {

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			String country= null;
			List<Long> countryList = new ArrayList<>();
			if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredStateId()))
				countryList.add(Long.valueOf(applicantDetail.getRegisteredStateId()));
			if (!CommonUtils.isListNullOrEmpty(countryList)) {
				try {
					OneFormResponse oneFormResponse = oneFormClient.getCountryByCountryListId(countryList);
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
							.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper
								.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						country = masterResponse.getValue() ;
					} else {

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			response.setOrganisationName(applicantDetail.getOrganisationName());
	        response.setCompanyCity(city);
	        response.setCompanyState(state);
	        response.setCompanyCountry(country);
	        response.setCompanyPincode(applicantDetail.getRegisteredPincode()!=null ? String.valueOf(applicantDetail.getRegisteredPincode()): null);
			response.setColleteralValue(applicantDetail.getTotalCollateralDetails());
			response.setIndustry(getIndustryForHunter(applicantDetail.getKeyVericalFunding()));
			response.setCompanyTelephone(applicantDetail.getLandlineNo());
			response.setConstitution(getConstitutionryForHunter(applicantDetail.getConstitutionId()));
			
			response.setEstablishmentDate(applicantDetail.getEstablishmentYear()+"-"+applicantDetail.getEstablishmentMonth()+"-"+"01");
			
			response.setCompanyAddress(applicantDetail.getRegisteredPremiseNumber()+", "+applicantDetail.getRegisteredStreetName()+", "+applicantDetail.getRegisteredLandMark());
			response.setCompanyEmail(applicantDetail.getEmail());
		}
		logger.info("Fetching Loan APplication Master for application Id : "+applicationId);
		LoanApplicationMaster loan = loanApplicationRepository.getById(applicationId);
		
		if(loan!=null) {
			logger.info("Fetched Loan APplication Master for application Id : "+applicationId);
			response.setLoanAmount(loan.getAmount());
			response.setLoanApplicationId(loan.getApplicationCode());
			
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			response.setDateOfApplication(dateFormat.format(loan.getCreatedDate()));
			response.setDateOfSubmission(dateFormat.format(new Date()));
		}
		PrimaryCorporateDetail primaryCorporate = primaryCorporateRepository.findOneByApplicationIdId(applicationId);
		if(primaryCorporate!=null) {
			response.setLoanType(String.valueOf(primaryCorporate.getPurposeOfLoanId()));
		}
		
		logger.info("Fetching Director's background details for application Id : "+applicationId);
		List<DirectorBackgroundDetail> directorList = directorBackgroundDetailsRepository.listPromotorBackgroundFromAppId(applicationId);
		
		response.setDirectorRespo(new ArrayList<DirectorBackgroundDetailResponse>());
		if(directorList!=null && !directorList.isEmpty()) {
			logger.info("Fetched Director's background details for application Id : "+applicationId);
			for(DirectorBackgroundDetail detail : directorList) {
				DirectorBackgroundDetailResponse directorDetail = new DirectorBackgroundDetailResponse();
				BeanUtils.copyProperties(detail, directorDetail);
				String gender = null;
				if(Gender.MALE.getId() == detail.getGender()) {
					gender = "MALE";
				}
				else if(Gender.FEMALE.getId() == detail.getGender()) {
					gender = "FEMALE";
				}
				else if(Gender.THIRD_GENDER.getId() == detail.getGender()) {
					gender = "OTHER";
				}
				else {
					gender = "OTHER";
				}
				directorDetail.setGender(gender);
				directorDetail.setShareholding(detail.getShareholding());
				
				String state= null;
				List<Long> stateList = new ArrayList<>();
				if (!CommonUtils.isObjectNullOrEmpty(detail.getStateCode()))
					stateList.add(Long.valueOf(detail.getStateCode()));
				if (!CommonUtils.isListNullOrEmpty(stateList)) {
					try {
						OneFormResponse oneFormResponse = oneFormClient.getStateByStateListId(stateList);
						List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
								.getListData();
						if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
							MasterResponse masterResponse = MultipleJSONObjectHelper
									.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
							state = masterResponse.getValue();
						} else {

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				directorDetail.setStateCode(state);
				
				String country= null;
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
						} else {

						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				directorDetail.setCountry(country);
				directorDetail.setPincode(detail.getPincode().toString());
				directorDetail.setIsMainDirector(detail.getMainDirector());
				response.addDirectorDetail(directorDetail);
			}
		}
		logger.info("Fetching Bank details for application Id : "+applicationId);
		ReportRequest reportRequest = new ReportRequest();
		reportRequest.setApplicationId(applicationId);
		AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReport(reportRequest);
		
		if(analyzerResponse.getStatus() == HttpStatus.OK.value()) {
			logger.info("Fetched Director's background details for application Id : "+applicationId);
			Data data = MultipleJSONObjectHelper
					.getObjectFromMap((Map<String, Object>) analyzerResponse.getData(), Data.class);
			
			if(data!=null && data.getSummaryInfo()!=null) {
				response.setCompanyBankAccount(data.getSummaryInfo().getAccNo());
				response.setCompanyBankName(data.getSummaryInfo().getInstName());
			}
		}
		logger.info("End getDataForHunter with Application ID : "+applicationId);
		return response;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public SanctioningDetailResponse getDetailsForSanction(DisbursementRequest disbursementRequest) throws Exception {
		try{
			logger.info("Start getDetailsForSanction with data application Id : "+ disbursementRequest.getApplicationId() + " ProductMapping Id :" + disbursementRequest.getProductMappingId());
			SanctioningDetailResponse sanctioningDetailResponse = new SanctioningDetailResponse();

			logger.info("Fetching data from in-principle: ");
			ProposalMappingResponse response = proposalDetailsClient.getActivateProposalById(disbursementRequest.getProductMappingId(), disbursementRequest.getApplicationId());
			Map<String, Object> proposalresp = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>) response.getData(), Map.class);
			if(proposalresp!=null){
				sanctioningDetailResponse.setSanctionAmount(proposalresp.get("elAmount") != null ? Double.valueOf(proposalresp.get("elAmount").toString()) : 0.0);
				sanctioningDetailResponse.setTenure(proposalresp.get("elTenure") != null ? Double.valueOf(proposalresp.get("elTenure").toString()) : 0.0);
				sanctioningDetailResponse.setRoi(proposalresp.get("elRoi") != null ? Double.valueOf(proposalresp.get("elRoi").toString()) : 0.0);
				sanctioningDetailResponse.setProcessingFee(proposalresp.get("processingFee") != null ? Double.valueOf(proposalresp.get("processingFee").toString()) : 0.0);
				sanctioningDetailResponse.setBranch(proposalresp.get("branchId") != null ? Long.valueOf(proposalresp.get("branchId").toString()) : null);
				sanctioningDetailResponse.setUserOrgId(proposalresp.get("userOrgId") != null ? Long.valueOf(proposalresp.get("userOrgId").toString()) : null);
			}

			logger.info("Fetching data for proposal: ");
			DisbursementRequest disbursementDetailsResponse =getDisbursementDetails(disbursementRequest);

			if(disbursementDetailsResponse != null){
				BeanUtils.copyProperties(disbursementDetailsResponse,sanctioningDetailResponse);
			}
			logger.info("End getDetailsForSanction with data application Id : "+ disbursementRequest.getApplicationId() + " ProductMapping Id :" + disbursementRequest.getProductMappingId());
			return sanctioningDetailResponse;
		}catch (Exception e){
			e.printStackTrace();
			throw e;
		}
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
				return "OTHER";
			}
		} else {
			return null;
		}
	}
	
	
	private String getConstitutionryForHunter(Integer constitutionId) {
		if (constitutionId != null) {
			if (Constitution.PRIVATE_LIMITED.getId() == constitutionId) {
				return "Private Limited Co";
			} else if (Constitution.PUBLIC_LISTED.getId() == constitutionId) {
				return "Public Limited Co";
			}

			else if (Constitution.PUBLIC_UNLISTED.getId() == constitutionId) {
				return "Public Limited Co";
			} else if (Constitution.FOREIGN_COMPANY.getId() == constitutionId) {
				return "Multi National";
			} else if (Constitution.SOLE_PROPRIETORSHIP.getId() == constitutionId) {
				return "Proprietorship";
			} else if (Constitution.ONE_PERSON.getId() == constitutionId) {
				return "Proprietorship";
			} else if (Constitution.PARTNERSHIP.getId() == constitutionId) {
				return "Partnership";
			} else if (Constitution.GOVERNMENT_ENTITY.getId() == constitutionId) {
				return "State Government";
			} else {
				return "Others";
			}
		} else {
			return null;
		}
	}
	
	@Override
	public String saveDetailedInfo(ProfileReqRes profileReqRes) throws LoansException , Exception {
	      logger.info("================== Enter in saveDetailedInfo =============== ");
	      Long applicationId=null;
	      CorporateProfileRequest corporateProfileRequest =profileReqRes.getCorporateProfileRequest();
	       
	      if(!CommonUtils.isObjectListNull(corporateProfileRequest , corporateProfileRequest.getApplicationId() , corporateProfileRequest.getUdhyogAdhaar(),  corporateProfileRequest.getAdministrativeAddress() , corporateProfileRequest.getAdministrativeAddress().getCityId() , corporateProfileRequest.getAdministrativeAddress().getStateId(), corporateProfileRequest.getAdministrativeAddress().getCountryId() , corporateProfileRequest.getAdministrativeAddress().getPincode() , corporateProfileRequest.getContLiabilityFyAmt() ,corporateProfileRequest.getContLiabilitySyAmt() ,corporateProfileRequest.getContLiabilityTyAmt() )) {
	    	  throw new Exception("Mandatory field must not be null (** applicationId , udhyogAddhar , city , state , country , pincode , ContLiabilityFyAmt ,  ContLiabilitySyAmt ,  ContLiabilityTyAmt ** ) " );
	      }
				applicationId = saveCorporateProfile(profileReqRes.getCorporateProfileRequest());
				if(CommonUtils.isObjectListNull(applicationId)) {
					throw new 	LoansException("Invalid applicationId");
				}
				logger.info("Sucessfully save CorporateProfile =============> ");
				if(!CommonUtils.isListNullOrEmpty(profileReqRes.getAchievementList())) {
					saveAchivementDetail(applicationId, profileReqRes.getAchievementList());
					logger.info("Sucessfully save AchivementDetail =============> ");
				}
				
				if(!CommonUtils.isListNullOrEmpty(profileReqRes.getAssociateConcernList())) {
		    	  	saveAssociatedConcernDetail(applicationId ,profileReqRes.getAssociateConcernList());
					logger.info("Sucessfully save AssociatedConcernDetailList =============> ");
				}
			
				if(!CommonUtils.isListNullOrEmpty(profileReqRes.getCreditRatingOrgList())) {
					saveCreditRatingOrganizationDetail(applicationId ,profileReqRes.getCreditRatingOrgList());
					logger.info("Sucessfully save CreditRatingOrganizationDetailList =============> ");
				}
				
				if(!CommonUtils.isListNullOrEmpty(profileReqRes.getFinanceMeansDetailRequestsList())) {
					saveFinanceMeansDetail(applicationId , profileReqRes.getFinanceMeansDetailRequestsList());
					logger.info("Sucessfully save FinanceMeansDetailRequestsList =============> ");
				}	
				if(!CommonUtils.isListNullOrEmpty(profileReqRes.getExPrList())) {
					if(!saveExistingProductDetail(applicationId ,profileReqRes.getExPrList())) {
						throw new LoansException("Mandatory field must not be null ( ** Product and Application ** )"); 
					}
					logger.info("Sucessfully save ExistingProductDetailList =============> ");
				}
				if(!CommonUtils.isListNullOrEmpty(profileReqRes.getGuaCorpList())) {
					saveGuarantorsCorporateDetail(applicationId ,  profileReqRes.getGuaDetailList());
					logger.info("Sucessfully save GuarantorsCorporateDetailList  =============> ");
				}	
				if(!CommonUtils.isListNullOrEmpty(profileReqRes.getMonTurnoverList())) {
					if(!saveMonthlyTurnoverDetail(applicationId, profileReqRes.getMonTurnoverList())) {
						throw new LoansException("Mandatory field must not be null (** MonthNamne and TurnOver ** )");
					}
					logger.info("Sucessfully save MonthlyTurnoverDetailList  =============> ");
				}
				/*if(!CommonUtils.isListNullOrEmpty(profileReqRes.getOwnerShipDetailsList())) {
					saveOwnershipDetail(applicationId, profileReqRes.getOwnerShipDetailsList());
					logger.info("Sucessfully save OwnershipDetailList  =============> ");
				}*/
				if(!CommonUtils.isListNullOrEmpty(profileReqRes.getPromotorBackgroundDetailRequestsList())) {
					if(! savePromotorBackgroundDetail(applicationId,profileReqRes.getPromotorBackgroundDetailRequestsList())){
						throw new LoansException("Mandsatory field must not be null ( ** salutationId , name , panNo , designation , address , mobileNO , dob , totalExperience and networth ** )"); 
					}
					logger.info("Sucessfully save PromotorBackgroundDetail =============> ");
				}
				if(!CommonUtils.isListNullOrEmpty(profileReqRes.getProposedProdList())) {
					if(!saveProposedProductDetail(applicationId, profileReqRes.getProposedProdList())) {
						throw new LoansException("Mandsatory field must not be null ( ** product/proposed and application ** )"); 
					}
					logger.info("Sucessfully save ProposedProductDetailList  =============> ");
				}
				if(!CommonUtils.isListNullOrEmpty(profileReqRes.getCostOfProjectRequestsList())) {
					saveTotalCostOfProject(applicationId ,profileReqRes.getCostOfProjectRequestsList());
					logger.info("Sucessfully save TotalCostOfProjectList  =============> ");
				}
				if(!CommonUtils.isListNullOrEmpty(profileReqRes.getSecurityCorporateDetailRequestsList())) {
					saveSecurityCorporateDetail(applicationId ,profileReqRes.getSecurityCorporateDetailRequestsList());
					logger.info("Sucessfully save SecurityCorporateDetailList  =============> ");
				}
		logger.info("=============== Exit from saveDetailedInfo ============== ");
		
		return "Sucess";
	}
	
	public Long saveCorporateProfile(CorporateProfileRequest corporateProfileRequest) {
		
		CorporateApplicantDetail  corporateApplicantDetail = corporateApplicantDetailRepository.getByApplicationIdAndIsAtive(corporateProfileRequest.getApplicationId());
		if(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail )){
			corporateApplicantDetail.setAadhar(corporateProfileRequest.getUdhyogAdhaar());
			corporateApplicantDetail.setAboutUs(corporateProfileRequest.getAbout());
			
			corporateApplicantDetail.setAdministrativePremiseNumber(corporateProfileRequest.getAdministrativeAddress().getPremiseNumber());
			corporateApplicantDetail.setAdministrativeStreetName(corporateProfileRequest.getAdministrativeAddress().getStreetName());
			corporateApplicantDetail.setAdministrativeLandMark(corporateProfileRequest.getAdministrativeAddress().getLandMark());
			corporateApplicantDetail.setAdministrativeCityId(corporateProfileRequest.getAdministrativeAddress().getCityId() );
			corporateApplicantDetail.setAdministrativeStateId(corporateProfileRequest.getAdministrativeAddress().getStateId());
			corporateApplicantDetail.setAdministrativeCountryId(corporateProfileRequest.getAdministrativeAddress().getCountryId());
			corporateApplicantDetail.setAdministrativePincode(corporateProfileRequest.getAdministrativeAddress().getPinCode());
			corporateApplicantDetail.setContLiabilityFyAmt(corporateProfileRequest.getContLiabilityFyAmt());
			corporateApplicantDetail.setContLiabilitySyAmt(corporateProfileRequest.getContLiabilitySyAmt());
			corporateApplicantDetail.setContLiabilityTyAmt(corporateProfileRequest.getContLiabilityTyAmt());
			
			corporateApplicantDetail.setModifiedBy(corporateProfileRequest.getApplicationId());
			corporateApplicantDetail.setModifiedDate( new Date());
			corporateApplicantDetail.setIsActive(true);
			return corporateApplicantDetailRepository.save(corporateApplicantDetail).getApplicationId().getId();
		}
		return null;
		
	}
	
	public Boolean saveAchivementDetail(Long applicationId,List<AchievementDetailRequest> achievementList) {
		AchievementDetail achievementDetail =null;
		try {
			achievementDetailsRepository.inActive(null, applicationId);
		for (AchievementDetailRequest achievementDetailRequest : achievementList) {
			achievementDetail= new AchievementDetail();
			
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
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Boolean saveAssociatedConcernDetail(Long applicationId,List<AssociatedConcernDetailRequest> associateConcernList) {
		AssociatedConcernDetail associatedConcernDetail=null;
		try {
			associatedConcernDetailRepository.inActive(null, applicationId);
			for (AssociatedConcernDetailRequest associatedConcernDetailRequest : associateConcernList) {
				associatedConcernDetail =new AssociatedConcernDetail();
				
				associatedConcernDetail.setName(associatedConcernDetailRequest.getName());
				associatedConcernDetail.setNatureAssociation(associatedConcernDetailRequest.getNatureAssociation());
				associatedConcernDetail.setNameOfDirector(associatedConcernDetailRequest.getNameOfDirector() );
				associatedConcernDetail.setInvestedAmount(associatedConcernDetailRequest.getInvestedAmount());
				associatedConcernDetail.setNatureActivity(associatedConcernDetailRequest.getNatureActivity() );
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
				associatedConcernDetail.setModifiedDate( new Date());
				associatedConcernDetail.setIsActive(true);
				associatedConcernDetailRepository.save(associatedConcernDetail);
			}
			return true;
		}catch (Exception e) {
		e.printStackTrace();
		return false;
		}
	}

	public Boolean saveCreditRatingOrganizationDetail(Long applicationId, List<CreditRatingOrganizationDetailRequest> creditRatingOrgList) {
		CreditRatingOrganizationDetail  creditRatingOrganizationDetail = null; 
		try {
			creditRatingOrganizationDetailsRepository.inActive(null, applicationId);
		for (CreditRatingOrganizationDetailRequest creditRatingOrganizationDetailRequest : creditRatingOrgList) {
			creditRatingOrganizationDetail =new CreditRatingOrganizationDetail();
			
			creditRatingOrganizationDetail.setRatingDate(creditRatingOrganizationDetailRequest.getRatingDate());
			creditRatingOrganizationDetail.setRatingAgencyId(creditRatingOrganizationDetailRequest.getRatingAgencyId());
			creditRatingOrganizationDetail.setFacilityName(creditRatingOrganizationDetailRequest.getFacilityName());
			creditRatingOrganizationDetail.setCreditRatingFundId(creditRatingOrganizationDetailRequest.getCreditRatingFundId()); 
			creditRatingOrganizationDetail.setCreditRatingTermId(creditRatingOrganizationDetailRequest.getCreditRatingTermId()); 
			creditRatingOrganizationDetail.setAmount(creditRatingOrganizationDetailRequest.getAmount());
			creditRatingOrganizationDetail.setCreditRatingOptionId(creditRatingOrganizationDetailRequest.getCreditRatingOptionId()); 
			
			creditRatingOrganizationDetail.setCreatedBy(applicationId);
			creditRatingOrganizationDetail.setCreatedDate(new Date());
			creditRatingOrganizationDetail.setModifiedBy(applicationId);
			creditRatingOrganizationDetail.setModifiedDate(new Date());
			creditRatingOrganizationDetail.setIsActive(true);
			creditRatingOrganizationDetailsRepository.save(creditRatingOrganizationDetail);
		}
		return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Boolean saveExistingProductDetail(Long applicationId,List<ExistingProductDetailRequest> exPrList) {
		ExistingProductDetail existingProductDetail =null; 
		if(CommonUtils.isObjectNullOrEmpty(exPrList.get(0))){
			return false;
		}
		existingProductDetailsRepository.inActive(null, applicationId);
		for (ExistingProductDetailRequest existingProductDetailRequest : exPrList) {
			if(CommonUtils.isObjectListNull(existingProductDetailRequest.getProduct() , existingProductDetailRequest.getApplication())) {
				return false;
			}

			existingProductDetail =new ExistingProductDetail();
			
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
	
	public Boolean saveFinanceMeansDetail(Long applicationId , List<FinanceMeansDetailRequest> financeMeansDetailRequestsList) {
		FinanceMeansDetail financeMeansDetail =null; 
		try {
			financeMeansDetailRepository.inActive(null, applicationId);
		for (FinanceMeansDetailRequest financeMeansDetailRequest : financeMeansDetailRequestsList) {
			financeMeansDetail =new FinanceMeansDetail();
			
			financeMeansDetail.setFinanceMeansCategoryId(financeMeansDetailRequest.getFinanceMeansCategoryId());
			financeMeansDetail.setTotal(financeMeansDetailRequest.getTotal());
			financeMeansDetail.setAlreadyInfused(financeMeansDetailRequest.getAlreadyInfused());
			financeMeansDetail.setApplicationId(new LoanApplicationMaster(applicationId));
			
			financeMeansDetail.setCreatedBy(applicationId);
			financeMeansDetail.setCreatedDate(new Date());
			financeMeansDetail.setModifiedBy(applicationId );
			financeMeansDetail.setModifiedDate(new Date());
			financeMeansDetail.setIsActive(true);
			financeMeansDetailRepository.save(financeMeansDetail);
		}
		return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Boolean saveGuarantorsCorporateDetail(Long applicationId , List<GuarantorsCorporateDetailRequest> guaCorpList) {
		GuarantorsCorporateDetail guarantorsCorporateDetail= null;
		try {
			guarantorsCorporateDetailRepository.inActive(null, applicationId); 
		for (GuarantorsCorporateDetailRequest guarantorsCorporateDetailRequest : guaCorpList) {
			guarantorsCorporateDetail =new GuarantorsCorporateDetail();
			
			guarantorsCorporateDetail.setName(guarantorsCorporateDetailRequest.getName());
			guarantorsCorporateDetail.setPropertiesOwned(guarantorsCorporateDetailRequest.getPropertiesOwned());
			guarantorsCorporateDetail.setPropertyType(guarantorsCorporateDetailRequest.getValue());;
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
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Boolean saveMonthlyTurnoverDetail(Long applicationId , List<MonthlyTurnoverDetailRequest> monTurnoverList) {
		MonthlyTurnoverDetail  monthlyTurnoverDetail =null;
		if(CommonUtils.isObjectNullOrEmpty(monTurnoverList.get(0))) {
			return false;
		}
		monthlyTurnoverDetailRepository.inActive(null, applicationId);
		for (MonthlyTurnoverDetailRequest monthlyTurnoverDetailRequest : monTurnoverList) {
			if(CommonUtils.isObjectListNull(monthlyTurnoverDetailRequest.getMonthName() , monthlyTurnoverDetailRequest.getAmount())) {
				return false;
			}
			monthlyTurnoverDetail =new MonthlyTurnoverDetail();
			
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
	/*public Boolean saveOwnershipDetail(Long applicationId ,List<OwnershipDetailRequest> ownerShipDetailsList) {
		OwnershipDetail ownershipDetail =null;
		if(CommonUtils.isObjectNullOrEmpty(ownerShipDetailsList.get(0))) {
			return false;
		}
		for (OwnershipDetailRequest ownershipDetailRequest : ownerShipDetailsList) {
			if(true);
			
			ownershipDetail= new  OwnershipDetail();
			
			//ownershipDetail.setShareHoldingCategoryId(ownershipDetailRequest.getShareHoldingCategoryType());
			ownershipDetail.setStackPercentage(ownershipDetailRequest.getStackPercentage());
			ownershipDetail.setRemarks(ownershipDetailRequest.getRemarks());
			ownershipDetail.setApplicationId(new LoanApplicationMaster(applicationId));
			
			ownershipDetail.setModifiedBy(ownershipDetailRequest.getApplicationId());
			ownershipDetail.setModifiedDate(new Date());
			ownershipDetail.setIsActive(true);
			ownershipDetailsRepository.save(ownershipDetail);
		}
		return true;
	}*/
	
	public Boolean savePromotorBackgroundDetail(Long applicationId, List<PromotorBackgroundDetailRequest> promotorBackgroundDetailRequestsList) {
		PromotorBackgroundDetail  promotorBackgroundDetail=null;
		if(CommonUtils.isObjectNullOrEmpty(promotorBackgroundDetailRequestsList.get(0))) {
			return false;
		}
		promotorBackgroundDetailsRepository.inActive(null, applicationId);
		for (PromotorBackgroundDetailRequest promotorBackgroundDetailRequest : promotorBackgroundDetailRequestsList) {
			if(CommonUtils.isObjectListNull(promotorBackgroundDetailRequest, promotorBackgroundDetailRequest.getSalutationId() , promotorBackgroundDetailRequest.getPromotorsName() , promotorBackgroundDetailRequest.getPanNo() , promotorBackgroundDetailRequest.getDesignation() ,promotorBackgroundDetailRequest.getAddress() , promotorBackgroundDetailRequest.getMobile() , promotorBackgroundDetailRequest.getDob() , promotorBackgroundDetailRequest.getTotalExperience() , promotorBackgroundDetailRequest.getNetworth())) {
				return false;
			}
			
			promotorBackgroundDetail =new PromotorBackgroundDetail();
			
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
	
	public Boolean saveProposedProductDetail(Long applicationId , List<ProposedProductDetailRequest> proposedProdList) {
		ProposedProductDetail  proposedProductDetail= null;
	
		if(CommonUtils.isObjectNullOrEmpty(proposedProdList.get(0))) {
			return false;
		}	
		proposedProductDetailsRepository.inActive(null, applicationId); 
		for (ProposedProductDetailRequest proposedProductDetailRequest : proposedProdList) {
			if(CommonUtils.isObjectListNull(proposedProductDetailRequest.getProduct(), proposedProductDetailRequest.getApplication())) {
				return false;
			}
			
			proposedProductDetail =new ProposedProductDetail();
			
			proposedProductDetail.setApplication(proposedProductDetailRequest.getApplication());
			proposedProductDetail.setProduct(proposedProductDetailRequest.getProduct());
			proposedProductDetail.setApplicationId(new LoanApplicationMaster(applicationId));
			
			proposedProductDetail.setCreatedBy(applicationId);
			proposedProductDetail.setCreatedDate(new Date());
			proposedProductDetail.setModifiedBy(proposedProductDetailRequest.getApplicationId());
			proposedProductDetail.setModifiedDate(new Date());
			proposedProductDetail.setIsActive(true);
			proposedProductDetailsRepository.save(proposedProductDetail );
		}
		return true;
	}
	
	public Boolean saveTotalCostOfProject(Long applicationId , List<TotalCostOfProjectRequest> costOfProjectRequestsList) {
		TotalCostOfProject totalCostOfProject =null;
		try {
			totalCostOfProjectRepository.inActive(null, applicationId);
		for (TotalCostOfProjectRequest totalCostOfProjectRequest : costOfProjectRequestsList) {
			totalCostOfProject =new TotalCostOfProject(); 
		
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
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Boolean saveSecurityCorporateDetail(Long applicationId, List<SecurityCorporateDetailRequest> securityCorporateDetailRequestsList) {
		SecurityCorporateDetail  securityCorporateDetail =null;
		try {
			securityCorporateDetailsRepository.inActive(null, applicationId);
		for (SecurityCorporateDetailRequest securityCorporateDetailRequest : securityCorporateDetailRequestsList) {
			securityCorporateDetail= new SecurityCorporateDetail();

			securityCorporateDetail.setPrimarySecurityName(securityCorporateDetailRequest.getPrimarySecurityName());
			securityCorporateDetail.setAmount(securityCorporateDetailRequest.getAmount());
			securityCorporateDetail.setApplicationId( new LoanApplicationMaster(applicationId));
			
			securityCorporateDetail.setCreatedBy(applicationId);
			securityCorporateDetail.setCreatedDate(new Date());
			securityCorporateDetail.setModifiedBy(applicationId);
			securityCorporateDetail.setModifiedDate(new Date());
			securityCorporateDetail.setIsActive(true);
			securityCorporateDetailsRepository.save(securityCorporateDetail);
		}
		return true;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/*@Override
	public Boolean updatePaymentStatusForMobile(PaymentRequest paymentRequest) {
		
		Boolean updatePayment=false;
		
		GatewayRequest gatewayRequest = new GatewayRequest();
		gatewayRequest.setApplicationId(paymentRequest.getApplicationId());
		gatewayRequest.setUserId(paymentRequest.getUserId());
		gatewayRequest.setStatus(paymentRequest.getStatus());
		gatewayRequest.setTxnId(paymentRequest.getTrxnId());

		
		try {
			updatePayment = gatewayClient.updatePayment(gatewayRequest);
		} catch (Exception e) {
			logger.info("THROW EXCEPTION WHILE UPDATE PAYMENT ON GATEWAY CLIENT");
			e.printStackTrace();
		}
		
		return updatePayment;
	}*/

	public void setTokenAsExpired(GenerateTokenRequest generateTokenRequest) {
		logger.info("Start expiring Token in setTokenAsExpired(){} ------------- generateTokenRequest "+ generateTokenRequest ); 
		try {
			sidbiIntegrationClient.setTokenAsExpired(generateTokenRequest);
		} catch (Exception e) {
			logger.info("Exception while set token as  expiring Token ------------- Msg "+e.getMessage());
			e.printStackTrace();
			
		}
		logger.info("End expiring Token setTokenAsExpired(){} -------------");
		
	}
	
	public List<TotalCostOfProjectRequest> getTotalCostOfProjectRequestsList(Long applicationId, Long userId){
		List<TotalCostOfProject> totalCostOfProjectsList= totalCostOfProjectRepository.listCostOfProjectFromAppId(applicationId, userId);
				
		if(CommonUtils.isListNullOrEmpty(totalCostOfProjectsList)) {
			logger.warn("No totalCostOfProjectsList Found for Application Id ==>{}",applicationId);
			return Collections.emptyList();
		}else {
			List<TotalCostOfProjectRequest> totalCostOfProjectRequestsList = new ArrayList<>(totalCostOfProjectsList.size());
			TotalCostOfProjectRequest target =null;
			for(TotalCostOfProject totalCostOfProject : totalCostOfProjectsList) {
				target = new TotalCostOfProjectRequest();
				target.setAlreadyIncurred(totalCostOfProject.getAlreadyIncurred());
				target.setApplicationId(applicationId);
				if(totalCostOfProject.getParticularsId()!= null) {
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
	
	public List<FinanceMeansDetailRequest> getFinanceMeansDetailRequestList(Long applicationId, Long userId){
		List<FinanceMeansDetail> financeMeansDetailsList=  financeMeansDetailRepository.listFinanceMeansFromAppId(applicationId, userId);
				
		if(CommonUtils.isListNullOrEmpty(financeMeansDetailsList)) {
			logger.warn("No FinanceMeansDetailList Found for Application Id ==>{} ",applicationId +  " userId==>{} ",userId);
			return Collections.emptyList();
		}else {
			List<FinanceMeansDetailRequest> financeMeansDetailRequestList = new ArrayList<>(financeMeansDetailsList.size());
			FinanceMeansDetailRequest target =null;
			for(FinanceMeansDetail financeMeansDetail : financeMeansDetailsList) {
				target = new FinanceMeansDetailRequest();
				target.setAlreadyInfused(financeMeansDetail.getAlreadyInfused());
				target.setApplicationId(applicationId);
				if(financeMeansDetail.getFinanceMeansCategoryId()!=null) {
					target.setFinanceMeansCategory(FinanceCategory.getById(financeMeansDetail.getFinanceMeansCategoryId().intValue()).getValue());
				}
				target.setToBeIncurred(financeMeansDetail.getToBeIncurred());
				target.setTotal(financeMeansDetail.getTotal());
				target.setCreatedBy(userId);
				financeMeansDetailRequestList.add(target);
			}
			return financeMeansDetailRequestList;
		}	
	}
	
	public List<SecurityCorporateDetailRequest> getSecurityCorporateDetailRequestList(Long applicationId, Long userId){
		List<SecurityCorporateDetail> securityCorporateDetailList= securityCorporateDetailsRepository.listSecurityCorporateDetailFromAppId(applicationId, userId);
				
		if(CommonUtils.isListNullOrEmpty(securityCorporateDetailList)) {
			logger.warn("No SecurityCorporateDetailList Found for Application Id ==>{}",applicationId);
			return Collections.emptyList();
		}else {
			List<SecurityCorporateDetailRequest> securityCorporateDetailRequestList = new ArrayList<>(securityCorporateDetailList.size());
			SecurityCorporateDetailRequest target =null;
			for(SecurityCorporateDetail securityCorporateDetail : securityCorporateDetailList) {
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
}
