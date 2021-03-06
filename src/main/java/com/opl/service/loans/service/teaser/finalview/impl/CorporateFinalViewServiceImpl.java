package com.opl.service.loans.service.teaser.finalview.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.analyzer.model.common.AnalyzerResponse;
import com.opl.mudra.api.analyzer.model.common.Data;
import com.opl.mudra.api.analyzer.model.common.ReportRequest;
import com.opl.mudra.api.dms.exception.DocumentException;
import com.opl.mudra.api.dms.model.DocumentRequest;
import com.opl.mudra.api.dms.model.DocumentResponse;
import com.opl.mudra.api.dms.utils.DocumentAlias;
import com.opl.mudra.api.eligibility.model.EligibililityRequest;
import com.opl.mudra.api.eligibility.model.EligibilityResponse;
import com.opl.mudra.api.fraudanalytics.model.AnalyticsResponse;
import com.opl.mudra.api.gst.model.GstResponse;
import com.opl.mudra.api.gst.model.MomSales;
import com.opl.mudra.api.gst.model.model.CAMGSTData;
import com.opl.mudra.api.gst.model.yuva.request.GSTR1Request;
import com.opl.mudra.api.itr.model.ITRConnectionResponse;
import com.opl.mudra.api.loans.model.CreditRatingOrganizationDetailRequest;
import com.opl.mudra.api.loans.model.CreditRatingOrganizationDetailResponse;
import com.opl.mudra.api.loans.model.DirectorBackgroundDetailRequest;
import com.opl.mudra.api.loans.model.DirectorBackgroundDetailResponse;
import com.opl.mudra.api.loans.model.DirectorPersonalDetailResponse;
import com.opl.mudra.api.loans.model.FinanceMeansDetailRequest;
import com.opl.mudra.api.loans.model.FinanceMeansDetailResponse;
import com.opl.mudra.api.loans.model.FinancialArrangementsDetailRequest;
import com.opl.mudra.api.loans.model.FinancialArrangementsDetailResponse;
import com.opl.mudra.api.loans.model.OwnershipDetailRequest;
import com.opl.mudra.api.loans.model.OwnershipDetailResponse;
import com.opl.mudra.api.loans.model.PincodeDataResponse;
import com.opl.mudra.api.loans.model.PromotorBackgroundDetailRequest;
import com.opl.mudra.api.loans.model.PromotorBackgroundDetailResponse;
import com.opl.mudra.api.loans.model.TotalCostOfProjectResponse;
import com.opl.mudra.api.loans.model.corporate.CollateralSecurityDetailRequest;
import com.opl.mudra.api.loans.model.corporate.CorporateFinalInfoRequest;
import com.opl.mudra.api.loans.model.corporate.CorporateMcqRequest;
import com.opl.mudra.api.loans.model.corporate.TotalCostOfProjectRequest;
import com.opl.mudra.api.loans.model.teaser.finalview.CorporateFinalViewResponse;
import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper;
import com.opl.mudra.api.matchengine.exception.MatchException;
import com.opl.mudra.api.matchengine.model.DisbursementRequestModel;
import com.opl.mudra.api.matchengine.model.MatchDisplayResponse;
import com.opl.mudra.api.matchengine.model.MatchRequest;
import com.opl.mudra.api.matchengine.model.ProposalMappingResponse;
import com.opl.mudra.api.mca.McaResponse;
import com.opl.mudra.api.mca.verifyApi.VerifyAPIRequest;
import com.opl.mudra.api.oneform.enums.AbilityRaiseFunds;
import com.opl.mudra.api.oneform.enums.AccountingQuality;
import com.opl.mudra.api.oneform.enums.AssessedForITMst;
import com.opl.mudra.api.oneform.enums.AssessmentOptionForFS;
import com.opl.mudra.api.oneform.enums.BorrowerInvoked;
import com.opl.mudra.api.oneform.enums.BusinessCommitment;
import com.opl.mudra.api.oneform.enums.BusinessExperience;
import com.opl.mudra.api.oneform.enums.ChequesReturned;
import com.opl.mudra.api.oneform.enums.CompanyConflicts;
import com.opl.mudra.api.oneform.enums.CompetitionMst_SBI;
import com.opl.mudra.api.oneform.enums.ComplianceConditions;
import com.opl.mudra.api.oneform.enums.Constitution;
import com.opl.mudra.api.oneform.enums.ConstructionContract;
import com.opl.mudra.api.oneform.enums.CreditRatingFund;
import com.opl.mudra.api.oneform.enums.CreditRatingTerm;
import com.opl.mudra.api.oneform.enums.CreditRecord;
import com.opl.mudra.api.oneform.enums.CumulativeOverdrawn;
import com.opl.mudra.api.oneform.enums.Currency;
import com.opl.mudra.api.oneform.enums.CustomerQuality;
import com.opl.mudra.api.oneform.enums.DelayInstalments;
import com.opl.mudra.api.oneform.enums.DelaySubmission;
import com.opl.mudra.api.oneform.enums.Denomination;
import com.opl.mudra.api.oneform.enums.DirectorRelationshipType;
import com.opl.mudra.api.oneform.enums.EducationalStatusMst;
import com.opl.mudra.api.oneform.enums.EnvironmentalImpact;
import com.opl.mudra.api.oneform.enums.EstablishmentMonths;
import com.opl.mudra.api.oneform.enums.FactoryPremiseMst;
import com.opl.mudra.api.oneform.enums.FinanceCategory;
import com.opl.mudra.api.oneform.enums.FinancialRestructuring;
import com.opl.mudra.api.oneform.enums.FinancialSupport;
import com.opl.mudra.api.oneform.enums.Gender;
import com.opl.mudra.api.oneform.enums.HaveLIMst;
import com.opl.mudra.api.oneform.enums.IndustrialRelations;
import com.opl.mudra.api.oneform.enums.InfrastructureAvailability;
import com.opl.mudra.api.oneform.enums.Integrity;
import com.opl.mudra.api.oneform.enums.InternalControl;
import com.opl.mudra.api.oneform.enums.InternalReturn;
import com.opl.mudra.api.oneform.enums.KnowHowMst;
import com.opl.mudra.api.oneform.enums.LCBG_Status_SBI;
import com.opl.mudra.api.oneform.enums.LimitOverdrawn;
import com.opl.mudra.api.oneform.enums.LoanType;
import com.opl.mudra.api.oneform.enums.ManagementCompetence;
import com.opl.mudra.api.oneform.enums.MaritalStatusMst;
import com.opl.mudra.api.oneform.enums.OperatingMargins;
import com.opl.mudra.api.oneform.enums.OrderBook;
import com.opl.mudra.api.oneform.enums.OwningHouseMst;
import com.opl.mudra.api.oneform.enums.Particular;
import com.opl.mudra.api.oneform.enums.ProductSeasonality;
import com.opl.mudra.api.oneform.enums.ProjectedRatio;
import com.opl.mudra.api.oneform.enums.PurposeOfLoan;
import com.opl.mudra.api.oneform.enums.RatingAgency;
import com.opl.mudra.api.oneform.enums.ResidentStatusMst;
import com.opl.mudra.api.oneform.enums.SensititivityAnalysis;
import com.opl.mudra.api.oneform.enums.ShareHoldingCategory;
import com.opl.mudra.api.oneform.enums.SpouseDetailMst;
import com.opl.mudra.api.oneform.enums.StatusClearances;
import com.opl.mudra.api.oneform.enums.StatusFinancialClosure;
import com.opl.mudra.api.oneform.enums.SubmissionReports;
import com.opl.mudra.api.oneform.enums.SuccessionPlanning;
import com.opl.mudra.api.oneform.enums.SupplierQuality;
import com.opl.mudra.api.oneform.enums.SustainabilityProduct;
import com.opl.mudra.api.oneform.enums.TechnologyRisk;
import com.opl.mudra.api.oneform.enums.Title;
import com.opl.mudra.api.oneform.enums.UnhedgedCurrency;
import com.opl.mudra.api.oneform.enums.VarianceSales;
import com.opl.mudra.api.oneform.enums.VisuallyImpairedMst;
import com.opl.mudra.api.oneform.enums.WcRenewalType;
import com.opl.mudra.api.oneform.model.MasterResponse;
import com.opl.mudra.api.oneform.model.OneFormResponse;
import com.opl.mudra.api.oneform.model.SectorIndustryModel;
import com.opl.mudra.api.rating.model.FinancialInputRequest;
import com.opl.mudra.api.scoring.exception.ScoringException;
import com.opl.mudra.api.scoring.model.ProposalScoreResponse;
import com.opl.mudra.api.scoring.model.ScoringRequest;
import com.opl.mudra.api.scoring.model.ScoringResponse;
import com.opl.mudra.api.thirdparty.model.CGTMSEDataResponse;
import com.opl.mudra.api.user.model.UserResponse;
import com.opl.mudra.api.user.model.UsersRequest;
import com.opl.mudra.client.analyzer.AnalyzerClient;
import com.opl.mudra.client.cibil.CIBILClient;
import com.opl.mudra.client.dms.DMSClient;
import com.opl.mudra.client.eligibility.EligibilityClient;
import com.opl.mudra.client.fraudanalytics.FraudAnalyticsClient;
import com.opl.mudra.client.gst.GstClient;
import com.opl.mudra.client.itr.ITRClient;
import com.opl.mudra.client.matchengine.MatchEngineClient;
import com.opl.mudra.client.matchengine.ProposalDetailsClient;
import com.opl.mudra.client.mca.McaClient;
import com.opl.mudra.client.oneform.OneFormClient;
import com.opl.mudra.client.scoring.ScoringClient;
import com.opl.mudra.client.thirdparty.ThirdPartyClient;
import com.opl.mudra.client.users.UsersClient;
import com.opl.service.loans.domain.fundprovider.TermLoanParameter;
import com.opl.service.loans.domain.fundprovider.WcTlParameter;
import com.opl.service.loans.domain.fundprovider.WorkingCapitalParameter;
import com.opl.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.opl.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.opl.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.opl.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.opl.service.loans.repository.common.CommonRepository;
import com.opl.service.loans.repository.common.LoanRepository;
import com.opl.service.loans.repository.fundprovider.ProductMasterRepository;
import com.opl.service.loans.repository.fundprovider.TermLoanParameterRepository;
import com.opl.service.loans.repository.fundprovider.WcTlLoanParameterRepository;
import com.opl.service.loans.repository.fundprovider.WorkingCapitalParameterRepository;
import com.opl.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.opl.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.opl.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.opl.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.opl.service.loans.service.common.CommonService;
import com.opl.service.loans.service.common.PincodeDateService;
import com.opl.service.loans.service.fundseeker.corporate.AchievmentDetailsService;
import com.opl.service.loans.service.fundseeker.corporate.AssociatedConcernDetailService;
import com.opl.service.loans.service.fundseeker.corporate.CollateralSecurityDetailService;
import com.opl.service.loans.service.fundseeker.corporate.CorporateFinalInfoService;
import com.opl.service.loans.service.fundseeker.corporate.CorporateMcqService;
import com.opl.service.loans.service.fundseeker.corporate.CreditRatingOrganizationDetailsService;
import com.opl.service.loans.service.fundseeker.corporate.DirectorBackgroundDetailsService;
import com.opl.service.loans.service.fundseeker.corporate.ExistingProductDetailsService;
import com.opl.service.loans.service.fundseeker.corporate.FinanceMeansDetailsService;
import com.opl.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.opl.service.loans.service.fundseeker.corporate.GuarantorsCorporateDetailService;
import com.opl.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.opl.service.loans.service.fundseeker.corporate.MonthlyTurnoverDetailService;
import com.opl.service.loans.service.fundseeker.corporate.OwnershipDetailsService;
import com.opl.service.loans.service.fundseeker.corporate.PromotorBackgroundDetailsService;
import com.opl.service.loans.service.fundseeker.corporate.ProposedProductDetailsService;
import com.opl.service.loans.service.fundseeker.corporate.SecurityCorporateDetailsService;
import com.opl.service.loans.service.fundseeker.corporate.TotalCostOfProjectService;
import com.opl.service.loans.service.irr.IrrService;
import com.opl.service.loans.service.teaser.finalview.CorporateFinalViewService;
import com.opl.service.loans.service.teaser.primaryview.CorporatePrimaryViewService;
import com.opl.service.loans.service.teaser.primaryview.impl.CorporatePrimaryViewServiceImpl;

@Service
@Transactional
public class CorporateFinalViewServiceImpl implements CorporateFinalViewService {

	private static final Logger logger = LoggerFactory.getLogger(CorporatePrimaryViewServiceImpl.class);
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private PrimaryCorporateDetailRepository primaryCorporateRepository;

	@Autowired
	private FinancialArrangementDetailsService financialArrangementDetailsService;

	@Autowired
	private DirectorBackgroundDetailsService directorBackgroundDetailsService;

	@Autowired
	private OneFormClient oneFormClient;

	@Autowired
	private DMSClient dmsClient;

	@Autowired
	private MatchEngineClient matchEngineClient;

	@Autowired
	private UsersClient usersClient;

	@Autowired
	private IrrService irrService;

	@Autowired
	private CorporateFinalInfoService corporateFinalInfoService;

	@Autowired
	private AchievmentDetailsService achievmentDetailsService;

	@Autowired
	private CreditRatingOrganizationDetailsService creditRatingOrganizationDetailsService;

	@Autowired
	private ExistingProductDetailsService existingProductDetailsService;

	@Autowired
	private ProposedProductDetailsService proposedProductDetailsService;

	@Autowired
	private AssociatedConcernDetailService associatedConcernDetailService;

	@Autowired
	private PromotorBackgroundDetailsService promotorBackgroundDetailsService;

	@Autowired
	private CorporateMcqService corporateMcqService;

	@Autowired
	private OwnershipDetailsService ownerShipDetailsService;

	@Autowired
	private TotalCostOfProjectService costOfProjectService;

	@Autowired
	private FinanceMeansDetailsService financeMeansDetailsService;

	@Autowired
	private SecurityCorporateDetailsService securityCorporateDetailsService;

	@Autowired
	private GuarantorsCorporateDetailService guarantorsCorporateDetailService;

	@Autowired
	private MonthlyTurnoverDetailService monthlyTurnoverDetailService;

	@Autowired
	private ScoringClient scoringClient;

	@Autowired
	private AnalyzerClient analyzerClient;

	@Autowired
	private EligibilityClient eligibilityClient;

	@Autowired
	private ThirdPartyClient thirdPartyClient;

	@Autowired
	private TermLoanParameterRepository termLoanParameterRepository;

	@Autowired
	private WorkingCapitalParameterRepository workingCapitalRepository;

	@Autowired
	private WcTlLoanParameterRepository wctlrepo;

	@Autowired
	private ITRClient itrClient;

	@Autowired
	private McaClient mcaClient;

	@Autowired
	private GstClient gstClient;

	@Autowired
	private PincodeDateService pincodeDateService;

	@Autowired
	private FraudAnalyticsClient fraudAnalyticsClient;
	
	@Autowired
	private ProductMasterRepository productMasterRepository;
	
	@Autowired
	private ApplicationProposalMappingRepository applicationProposalMappingRepository;

	@Autowired
	private CommonService commonService;
	
	@Autowired
	CollateralSecurityDetailService collateralSecurityDetailService;
	
	@Autowired
	private CIBILClient cibilClient;
	
	@Autowired
	private CommonRepository commonRepository;
	
	@Autowired
	private LoanApplicationService loanApplicationService;
	
	@Autowired
	private CorporatePrimaryViewService primaryCorpService;
	
	@Autowired
	private LoanRepository loanRepository;
	
	@Value("${capitaworld.gstdata.enable}")
	private Boolean gstCompRelFlag;

	@Autowired
	private ProposalDetailsClient proposalDetailsClient;

	DecimalFormat decim = new DecimalFormat("#,###.00");

	@SuppressWarnings("unchecked")
	@Override
	public CorporateFinalViewResponse getCorporateFinalViewDetails(Long toapplicationId,Long proposalMapId, Integer userType,Long fundProviderUserId) {


		CorporateFinalViewResponse corporateFinalViewResponse = new CorporateFinalViewResponse();

		Object[] profileVersionDetails = loanRepository.getProfileVersionDetailsByApplicationId(toapplicationId);
		if(CommonUtils.isObjectNullOrEmpty(profileVersionDetails)) {
			logger.error("Profile not found for applicationId =======>"+toapplicationId);
			return corporateFinalViewResponse;
		}
		Long itrId = profileVersionDetails[0] != null ? Long.valueOf(profileVersionDetails[0].toString()) : null;
		Long gstId = profileVersionDetails[1] != null ? Long.valueOf(profileVersionDetails[1].toString()) : null;
		Long bsId =  profileVersionDetails[2] != null ? Long.valueOf(profileVersionDetails[2].toString()) : null;
		
		DisbursementRequestModel request = new DisbursementRequestModel();
		request.setApplicationId(toapplicationId);
		request.setProposalId(proposalMapId);
		request.setUserId(fundProviderUserId);
		try {
			ProposalMappingResponse s = proposalDetailsClient.getDisbursementRequestDetails(request);
			corporateFinalViewResponse.setIsNBFCApplication(false);
			if(s !=null && s.getData() !=null){
				corporateFinalViewResponse.setIsNBFCApplication(true);
			}
			corporateFinalViewResponse.setDisbursementRequestDetails(s.getData());
		} catch (MatchException e) {
			logger.error("Error while geting disbursedment details",e);
		}

		ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.findOne(proposalMapId);

		Long userId = applicationProposalMapping.getUserId();
		Long toApplicationId = applicationProposalMapping.getApplicationId();
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(toApplicationId);
		corporateFinalViewResponse.setApplicationType(loanApplicationMaster.getWcRenewalStatus() != null ? WcRenewalType.getById(loanApplicationMaster.getWcRenewalStatus()).getValue().toString() : "New" );
		corporateFinalViewResponse.setIsMcqSkipped(applicationProposalMapping.getIsMcqSkipped() != null ? applicationProposalMapping.getIsMcqSkipped() : false);
		corporateFinalViewResponse.setProductId(applicationProposalMapping.getProductId());
		corporateFinalViewResponse.setCurrencyDenomination(applicationProposalMapping.getCurrencyId() != null ? Currency.getById(applicationProposalMapping.getCurrencyId()).getValue().toString() : "-");
		// ===================== MATCHES DATA ======================//
		if (userType != null && CommonUtils.UserType.FUND_SEEKER != userType ) {
			    // TEASER VIEW FROM FP
				Long fpProductMappingId = null;
				try {
					UsersRequest usersRequest = new UsersRequest();
					usersRequest.setId(fundProviderUserId);
					UserResponse userResponse = usersClient.getLastAccessApplicant(usersRequest);
					fpProductMappingId = userResponse.getId();
				} catch (Exception e) {
					logger.error("error while fetching last access fp product id for fund provider while fetching matches in teaser view : ",e);
				}
				try {
					MatchRequest matchRequest = new MatchRequest();
					matchRequest.setApplicationId(toApplicationId);
					matchRequest.setProductId(fpProductMappingId);
					MatchDisplayResponse matchResponse = matchEngineClient.displayMatchesOfCorporate(matchRequest);
					corporateFinalViewResponse.setMatchesList(matchResponse.getMatchDisplayObjectList());
				} catch (Exception e) {
					logger.error("Error while getting matches data for final teaser view : ",e);
				}
		}
		
		
		// GET CORPORATE APPLICANT DETAILS
/*		CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
				.getByApplicationAndUserId(userId, toApplicationId);*/ //PREVIOUS

		CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
				.getByApplicationAndProposalIdAndUserId(userId, toApplicationId,proposalMapId); // NEW BASED ON PROPOSAL MAP ID

		//NOTE OF BORROWER FOR MSME
		try {
			String note = commonRepository.getNoteForHLCam(toApplicationId);
			corporateFinalViewResponse.setNoteOfBorrower(!CommonUtils.isObjectNullOrEmpty(note) ? note : null);
		}catch (Exception e) {
			logger.error("Error/Exception while getting note of borrower....Error==>{}", e);
		}
		 
		// SET VALUE TO RESPONSE
		if (corporateApplicantDetail != null) {
			BeanUtils.copyProperties(corporateApplicantDetail, corporateFinalViewResponse);
			corporateFinalViewResponse.setComercialPanNo(corporateApplicantDetail.getPanNo() != null ? corporateApplicantDetail.getPanNo() : "-");
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getConstitutionId()))
				corporateFinalViewResponse
						.setConstitution(Constitution.getById(corporateApplicantDetail.getConstitutionId()).getValue());
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getEstablishmentMonth()))
				corporateFinalViewResponse.setEstablishmentMonth(
						EstablishmentMonths.getById(corporateApplicantDetail.getEstablishmentMonth()).getValue());
			// ESTABLISHMENT YEAR
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getEstablishmentYear())) {
				try {
					OneFormResponse establishmentYearResponse = oneFormClient.getYearByYearId(
							CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getEstablishmentYear()) ? null
									: corporateApplicantDetail.getEstablishmentYear().longValue());
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) establishmentYearResponse
							.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper
								.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						corporateFinalViewResponse.setEstablishmentYear(masterResponse.getValue());
					} else {
						corporateFinalViewResponse.setEstablishmentYear("NA");
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
			}

			//Set Registered Data
			Long cityId = null ;
			Integer stateId = null;
			Integer countryId = null;
			String cityName = null;
			String stateName = null;
			String countryName = null;
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCityId()))
				cityId = corporateApplicantDetail.getRegisteredCityId();
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredStateId()))
				stateId = corporateApplicantDetail.getRegisteredStateId();
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCountryId()))
				countryId = corporateApplicantDetail.getRegisteredCountryId();
			
			if(cityId != null || stateId != null || countryId != null) {
				Map<String ,Object> mapData = commonService.getCityStateCountryNameFromOneForm(cityId, stateId, countryId);
				if(mapData != null) {
					cityName = mapData.get(CommonUtils.CITY_NAME).toString();
					stateName = mapData.get(CommonUtils.STATE_NAME).toString();
					countryName = mapData.get(CommonUtils.COUNTRY_NAME).toString();
					
					//set City
					corporateFinalViewResponse.setCity(cityName != null ? cityName : "NA");
					corporateFinalViewResponse.setRegOfficeCity(cityName);
					
					//set State
					corporateFinalViewResponse.setState(stateName != null ? stateName : "NA");
					corporateFinalViewResponse.setRegOfficestate(stateName);
					
					//set Country
					corporateFinalViewResponse.setCountry(countryName != null ? countryName : "NA");
					corporateFinalViewResponse.setRegOfficecountry(countryName);
				}
			}
			
			//set Administrative Data
			cityId = null;
			stateId = null;
			countryId = null;
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getAdministrativeCityId()))
				cityId = corporateApplicantDetail.getAdministrativeCityId();
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getAdministrativeStateId()))
				stateId = corporateApplicantDetail.getAdministrativeStateId();
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getAdministrativeCountryId()))
				countryId = corporateApplicantDetail.getAdministrativeCountryId();
			
			if(cityId != null || stateId != null || countryId != null) {
				Map<String ,Object> mapData = commonService.getCityStateCountryNameFromOneForm(cityId, stateId, countryId);
				if(mapData != null) {
					cityName = mapData.get(CommonUtils.CITY_NAME).toString();
					stateName = mapData.get(CommonUtils.STATE_NAME).toString();
					countryName = mapData.get(CommonUtils.COUNTRY_NAME).toString();
					
					//set City
					corporateFinalViewResponse.setAddOfficeCity(cityName != null ? cityName : "NA");
					
					//set State
					corporateFinalViewResponse.setAddOfficestate(stateName != null ? stateName : "NA");
					
					//set Country
					corporateFinalViewResponse.setAddOfficecountry(countryName != null ? countryName : "NA");
				}
			}
			
			/**
			// SET CITY
			List<Long> cityList = new ArrayList<>();
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCityId()))
				cityList.add(corporateApplicantDetail.getRegisteredCityId());
			if (!CommonUtils.isListNullOrEmpty(cityList)) {
				try {
					OneFormResponse oneFormResponse = oneFormClient.getCityByCityListId(cityList);
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
							.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper
								.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						corporateFinalViewResponse.setCity(masterResponse.getValue());
						corporateFinalViewResponse.setRegOfficeCity(masterResponse.getValue());
					} else {
						corporateFinalViewResponse.setCity("NA");
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
			}
			cityList.clear();
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getAdministrativeCityId()))
				cityList.add(corporateApplicantDetail.getAdministrativeCityId());
			if (!CommonUtils.isListNullOrEmpty(cityList)) {
				try {
					OneFormResponse oneFormResponse = oneFormClient.getCityByCityListId(cityList);
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
							.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper
								.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						corporateFinalViewResponse.setAddOfficeCity(masterResponse.getValue());

					} else {
						corporateFinalViewResponse.setCity("NA");
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
			}
			// SET STATE
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
						corporateFinalViewResponse.setState(masterResponse.getValue());
						corporateFinalViewResponse.setRegOfficestate(masterResponse.getValue());
					} else {
						corporateFinalViewResponse.setState("NA");
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
			}
			stateList.clear();
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getAdministrativeStateId()))
				stateList.add(Long.valueOf(corporateApplicantDetail.getAdministrativeStateId()));
			if (!CommonUtils.isListNullOrEmpty(stateList)) {
				try {
					OneFormResponse oneFormResponse = oneFormClient.getStateByStateListId(stateList);
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
							.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper
								.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						corporateFinalViewResponse.setAddOfficestate(masterResponse.getValue());
					} else {
						corporateFinalViewResponse.setState("NA");
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
			}
			// SET COUNTRY
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
						corporateFinalViewResponse.setCountry(masterResponse.getValue());
						corporateFinalViewResponse.setRegOfficecountry(masterResponse.getValue());
					} else {
						corporateFinalViewResponse.setCountry("NA");
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
			}
			countryList.clear();
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getAdministrativeCountryId()))
				countryList.add(Long.valueOf(corporateApplicantDetail.getAdministrativeCountryId()));
			if (!CommonUtils.isListNullOrEmpty(countryList)) {
				try {
					OneFormResponse oneFormResponse = oneFormClient.getCountryByCountryListId(countryList);
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
							.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper
								.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						corporateFinalViewResponse.setAddOfficecountry(masterResponse.getValue());
					} else {
						corporateFinalViewResponse.setCountry("NA");
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
			}*/
			
			// KEY VERTICAL FUNDING (INDUSTRY SECTOR SUBSECTOR DETAILS)
			List<Long> keyVerticalFundingId = new ArrayList<>();
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getKeyVericalFunding()))
				keyVerticalFundingId.add(corporateApplicantDetail.getKeyVericalFunding());
			if (!CommonUtils.isListNullOrEmpty(keyVerticalFundingId)) {
				try {
					OneFormResponse oneFormResponse = oneFormClient.getIndustryById(keyVerticalFundingId);
					List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
							.getListData();
					if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
						MasterResponse masterResponse = MultipleJSONObjectHelper
								.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
						corporateFinalViewResponse.setKeyVericalFunding(masterResponse.getValue());
					} else {
						corporateFinalViewResponse.setKeyVericalFunding("NA");
					}

				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
			}
			// KEY VERTICAL SECTOR
			List<Long> keyVerticalSectorId = new ArrayList<>();
			// GETTING SECTOR ID FROM MAPPING
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getKeyVerticalSector()))
				keyVerticalSectorId.add(corporateApplicantDetail.getKeyVerticalSector());
			try {
				OneFormResponse formResponse = oneFormClient
						.getIndustrySecByMappingId(corporateApplicantDetail.getKeyVerticalSector());
				// SectorIndustryModel oneResponseDataList = (SectorIndustryModel) formResponse
				// .getData();
				SectorIndustryModel sectorIndustryModel = MultipleJSONObjectHelper
						.getObjectFromMap((Map) formResponse.getData(), SectorIndustryModel.class);

				// GET KEY VERTICAL SECTOR VALUE
				OneFormResponse oneFormResponse = oneFormClient
						.getSectorById(Arrays.asList(sectorIndustryModel.getSectorId()));
				List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
						.getListData();
				if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
					MasterResponse masterResponse = MultipleJSONObjectHelper
							.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
					corporateFinalViewResponse.setKeyVericalSector(masterResponse.getValue());
				} else {
					corporateFinalViewResponse.setKeyVericalSector("NA");
				}
			} catch (Exception e) {
				logger.error("Error while getting key vertical sector data : ",e);
			}

			// KEY VERTICAL SUB-SECTOR
			try {
				if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getKeyVerticalSubsector())) {
					OneFormResponse oneFormResponse = oneFormClient
							.getSubSecNameByMappingId(corporateApplicantDetail.getKeyVerticalSubsector());
					corporateFinalViewResponse.setKeyVericalSubsector((String) oneFormResponse.getData());
				}
			} catch (Exception e) {
				logger.error("error while getting key vertical sub-sector : ",e);
			}
		}
		// PROFILE AND PRIMARY DETAILS
		PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateRepository
				.getByApplicationAndUserId(toApplicationId, userId);
		if (primaryCorporateDetail != null) {
			// set value to response
			BeanUtils.copyProperties(primaryCorporateDetail, corporateFinalViewResponse, "isMcqSkipped");
			if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getCurrencyId())
					&& !CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getDenominationId())) {
				corporateFinalViewResponse
						.setCurrencyDenomination(Currency.getById(primaryCorporateDetail.getCurrencyId()).getValue()
								+ " in " + Denomination.getById(primaryCorporateDetail.getDenominationId()).getValue());
			}
/*			corporateFinalViewResponse.setLoanType(primaryCorporateDetail.getProductId() != null
					? LoanType.getById(primaryCorporateDetail.getProductId()).getValue()
					: null);*/
			corporateFinalViewResponse.setLoanType(applicationProposalMapping.getProductId() != null
					? LoanType.getById(applicationProposalMapping.getProductId()).getValue()
					: null); // NEW

/*			corporateFinalViewResponse.setLoanAmount(
					primaryCorporateDetail.getAmount() != null ? String.valueOf(primaryCorporateDetail.getAmount())
							: null);*/
			corporateFinalViewResponse.setLoanAmount(
					applicationProposalMapping.getLoanAmount() != null ? String.valueOf(applicationProposalMapping.getLoanAmount())
							: null); // NEW

			corporateFinalViewResponse.setGstIn(
					corporateApplicantDetail.getGstIn() != null ? String.valueOf(corporateApplicantDetail.getGstIn())
							: null);
			if(primaryCorporateDetail.getAssessmentId()!=null) {
				corporateFinalViewResponse.setPurposeOfLoan(primaryCorporateDetail.getPurposeOfLoanId() != null && primaryCorporateDetail.getPurposeOfLoanId()==1 ? AssessmentOptionForFS.getById(primaryCorporateDetail.getAssessmentId()).getValue().toString() : PurposeOfLoan.getById(primaryCorporateDetail.getPurposeOfLoanId()).getValue().toString());	
			}else {
				
				logger.warn("assesment id is null so considered PurposeOfLoan enum.....");
				corporateFinalViewResponse.setPurposeOfLoan(
						CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getPurposeOfLoanId()) ? null
								: PurposeOfLoan.getById(primaryCorporateDetail.getPurposeOfLoanId()).getValue().toString());
			}
			
			List<CollateralSecurityDetailRequest> collateralSecurityDetails = collateralSecurityDetailService.getData(toapplicationId);
			corporateFinalViewResponse.setCollateralSecurityDetails(collateralSecurityDetails);
			
			corporateFinalViewResponse
					.setHaveCollateralSecurity(primaryCorporateDetail.getHaveCollateralSecurity() != null
							? String.valueOf(primaryCorporateDetail.getHaveCollateralSecurity())
							: null);
			corporateFinalViewResponse
					.setCollateralSecurityAmount(primaryCorporateDetail.getCollateralSecurityAmount() != null
							? String.valueOf(primaryCorporateDetail.getCollateralSecurityAmount())
							: null);
			corporateFinalViewResponse.setPromotersContribution(primaryCorporateDetail.getPromoterContribution());
			corporateFinalViewResponse
					.setPromotersContributionPer(primaryCorporateDetail.getTotalAmtPercentage() != null
							? " (" + convertValue(primaryCorporateDetail.getTotalAmtPercentage()) + "%)"
							: null);
			corporateFinalViewResponse.setNpOrgId(applicationProposalMapping.getOrgId());
			// workingCapitalPrimaryViewResponse.setSharePriceFace(primaryWorkingCapitalLoanDetail.getSharePriceFace());
			// workingCapitalPrimaryViewResponse.setSharePriceMarket(primaryWorkingCapitalLoanDetail.getSharePriceMarket());
			if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getModifiedDate()))
				corporateFinalViewResponse.setDateOfProposal(primaryCorporateDetail.getModifiedDate() != null
						? simpleDateFormat.format(primaryCorporateDetail.getModifiedDate())
						: null);
			
			// other Details
			
			corporateFinalViewResponse.setComercialOpDate(primaryCorporateDetail.getCommercialOperationDate());
			corporateFinalViewResponse.setFactoryPremise(primaryCorporateDetail.getFactoryPremise() != null ? FactoryPremiseMst.getById(primaryCorporateDetail.getFactoryPremise()).getValue().toString() : "-");
			corporateFinalViewResponse.setKnoHow(primaryCorporateDetail.getKnowHow() != null ? KnowHowMst.getById(primaryCorporateDetail.getKnowHow()).getValue().toString() : "-");
			corporateFinalViewResponse.setCompetition(primaryCorporateDetail.getCompetition()  != null ? CompetitionMst_SBI.getById(primaryCorporateDetail.getCompetition()).getValue().toString() : "-");
		
			// add additional Details 
			
			corporateFinalViewResponse.setCostOfMachinery(primaryCorporateDetail.getCostOfMachinery());
			corporateFinalViewResponse.setIncrementalTurnover(primaryCorporateDetail.getIncrementalTurnover());
			corporateFinalViewResponse.setIncrementalMargin(primaryCorporateDetail.getIncrementalMargin());
			corporateFinalViewResponse.setProductServiceDesc(primaryCorporateDetail.getProductServiceDescription());
						
		
		}

		
		
		// DIRECTORS BACKGROUND DETAILS
		
		try {
			List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestList = directorBackgroundDetailsService
					.getDirectorBackgroundDetailList(toApplicationId, userId);
			List<DirectorBackgroundDetailResponse> directorBackgroundDetailResponseList = new ArrayList<>();
			for (DirectorBackgroundDetailRequest directorBackgroundDetailRequest : directorBackgroundDetailRequestList) {
				DirectorBackgroundDetailResponse directorBackgroundDetailResponse = new DirectorBackgroundDetailResponse();
				// directorBackgroundDetailResponse.setAchivements(directorBackgroundDetailRequest.getAchivements());
				directorBackgroundDetailResponse.setAddress(directorBackgroundDetailRequest.getAddress());
				// directorBackgroundDetailResponse.setAge(directorBackgroundDetailRequest.getAge());
				// directorBackgroundDetailResponse.setPanNo(directorBackgroundDetailRequest.getPanNo());
				directorBackgroundDetailResponse
						.setDirectorsName((directorBackgroundDetailRequest.getSalutationId() != null
								? Title.getById(directorBackgroundDetailRequest.getSalutationId()).getValue()
								: null) + " " + directorBackgroundDetailRequest.getDirectorsName());
				if(directorBackgroundDetailRequest.getPanNo() != null) {
					directorBackgroundDetailResponse.setPanNo(directorBackgroundDetailRequest.getPanNo().toUpperCase());
				}
				
				String directorName = "";
				if (directorBackgroundDetailRequest.getSalutationId() != null) {
					directorName = Title.getById(directorBackgroundDetailRequest.getSalutationId()).getValue();
				}
				directorName += " " + directorBackgroundDetailRequest.getDirectorsName();
				directorBackgroundDetailResponse.setDirectorsName(directorName);
				// directorBackgroundDetailResponse.setQualification(directorBackgroundDetailRequest.getQualification());
				directorBackgroundDetailResponse
						.setTotalExperience(directorBackgroundDetailRequest.getTotalExperience().toString() + " Years");
				directorBackgroundDetailResponse.setNetworth(directorBackgroundDetailRequest.getNetworth().toString());
				directorBackgroundDetailResponse.setDesignation(directorBackgroundDetailRequest.getDesignation());
				directorBackgroundDetailResponse
						.setAppointmentDate(directorBackgroundDetailRequest.getAppointmentDate());
				directorBackgroundDetailResponse.setDin(directorBackgroundDetailRequest.getDin());
				directorBackgroundDetailResponse.setMobile(directorBackgroundDetailRequest.getMobile());
				directorBackgroundDetailResponse.setDob(directorBackgroundDetailRequest.getDob());
				directorBackgroundDetailResponse.setPincode(directorBackgroundDetailRequest.getPincode());
				directorBackgroundDetailResponse.setPersonalId(directorBackgroundDetailRequest.getPersonalId());
                                
                                //Loan Obligation Added
				Double loanObligation = financialArrangementDetailsService.getTotalOfEmiByApplicationIdAndDirectorId(toApplicationId,directorBackgroundDetailRequest.getId());
				directorBackgroundDetailResponse.setLoanObligation(!CommonUtils.isObjectNullOrEmpty(loanObligation) ? loanObligation : 0);
				try {
					if (!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getDistrictMappingId())) {
						directorBackgroundDetailResponse.setPinData(
								pincodeDateService.getById(directorBackgroundDetailRequest.getDistrictMappingId()));
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}

				directorBackgroundDetailResponse.setStateCode(directorBackgroundDetailRequest.getStateCode());
				directorBackgroundDetailResponse.setCity(directorBackgroundDetailRequest.getCity());
				directorBackgroundDetailResponse.setGender((directorBackgroundDetailRequest.getGender() != null
						? Gender.getById(directorBackgroundDetailRequest.getGender()).getValue()
						: " "));
				directorBackgroundDetailResponse
						.setRelationshipType((directorBackgroundDetailRequest.getRelationshipType() != null
								? DirectorRelationshipType
										.getById(directorBackgroundDetailRequest.getRelationshipType()).getValue()
								: " "));
				
				// additional Details added as per req
				directorBackgroundDetailResponse.setIsMainDirector(directorBackgroundDetailRequest.getIsMainDirector());
				directorBackgroundDetailResponse.setFatherName(directorBackgroundDetailRequest.getFatherName());
				directorBackgroundDetailResponse.setEducationalStatus(directorBackgroundDetailRequest.getEducationalStatus() != null ? EducationalStatusMst.getById(directorBackgroundDetailRequest.getEducationalStatus()).getValue().toString() : "-");
				directorBackgroundDetailResponse.setVisuallyImpaired(directorBackgroundDetailRequest.getVisuallyImpaired() != null ? VisuallyImpairedMst.getById(directorBackgroundDetailRequest.getVisuallyImpaired()).getValue().toString() : "-");
				directorBackgroundDetailResponse.setResidentStatus(directorBackgroundDetailRequest.getResidentStatus() != null ? ResidentStatusMst.getById(directorBackgroundDetailRequest.getResidentStatus()).getValue().toString() : "-");
				directorBackgroundDetailResponse.setDirectorPersonalInfo(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest() != null ? directorBackgroundDetailRequest.getDirectorPersonalDetailRequest() : " " );
				//nationality
				
				List<Long> countryList = new ArrayList<>();
				if (!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest.getNationality()))
					countryList.add(Long.valueOf(directorBackgroundDetailRequest.getNationality()));
				if (!CommonUtils.isListNullOrEmpty(countryList)) {
					try {
						OneFormResponse oneFormResponse = oneFormClient.getCountryByCountryListId(countryList);
						List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
								.getListData();
						if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
							MasterResponse masterResponse = MultipleJSONObjectHelper
									.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
							corporateFinalViewResponse.setCountry(masterResponse.getValue());
							corporateFinalViewResponse.setRegOfficecountry(masterResponse.getValue());
							
							directorBackgroundDetailResponse.setNationality(masterResponse.getValue());
							
						} else {
							directorBackgroundDetailResponse.setNationality("NA");
						}
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
				}
				
				directorBackgroundDetailResponseList.add(directorBackgroundDetailResponse);
				// is main director so fetch personal detail by personal id
				try {
					if(directorBackgroundDetailRequest.getIsMainDirector() != null && directorBackgroundDetailRequest.getIsMainDirector() == true){
						DirectorPersonalDetailResponse directorPersonalDetailResponse= new DirectorPersonalDetailResponse();
						if(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest() != null)
						{
							directorPersonalDetailResponse.setMaritalStatus(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getMaritalStatus() != null ? MaritalStatusMst.getById(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getMaritalStatus()).getValue().toString() : "-");
							directorPersonalDetailResponse.setSpouseName(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getSpouseName());
							directorPersonalDetailResponse.setSpouseDetail(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getSpouseDetail() != null ? SpouseDetailMst.getById(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getSpouseDetail()).getValue().toString() : "-");
							directorPersonalDetailResponse.setAssessedForIt(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getAssessedForIt() != null ? AssessedForITMst.getById(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getAssessedForIt()).getValue().toString() : "-");
							directorPersonalDetailResponse.setOwningHouse(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getOwningHouse() != null ? OwningHouseMst.getById(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getOwningHouse()).getValue().toString() : "-");
							directorPersonalDetailResponse.setNoOfChildren(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getNoOfChildren());
							directorPersonalDetailResponse.setHaveLiPolicy(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getHaveLiPolicy()!= null ? HaveLIMst.getById(directorBackgroundDetailRequest.getDirectorPersonalDetailRequest().getHaveLiPolicy()).getValue().toString() : "-");
						
							directorBackgroundDetailResponse.setDirectorPersonalInfo(directorPersonalDetailResponse);
						}
						else {
							logger.warn("directorBackgroundDetailRequest.getDirectorPersonalDetailRequest() is null....");
						}
					}else {
						logger.warn("----:::::: is main director is null ::::::-----For-----AppId==>{}",toApplicationId);
					}					
				} catch (Exception e) {
					logger.error("----:::::: error while get is main dir details ::::::-----For-----AppId==>{} ..Error==>{}",toApplicationId ,e);
				}
			}
			corporateFinalViewResponse.setDirectorBackgroundDetailResponses(directorBackgroundDetailResponseList);
		} catch (Exception e) {
			logger.error("Problem to get Data of Director's Background {}", e);
		}
		
		// address
		
		// FINANCIAL ARRANGEMENTS
		try {
			List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList = financialArrangementDetailsService
					.getFinancialArrangementDetailsList(toApplicationId, userId);
			List<FinancialArrangementsDetailResponse> financialArrangementsDetailResponseList = new ArrayList<>();
			for (FinancialArrangementsDetailRequest financialArrangementsDetailRequest : financialArrangementsDetailRequestList) {
				FinancialArrangementsDetailResponse financialArrangementsDetailResponse = new FinancialArrangementsDetailResponse();
				// financialArrangementsDetailResponse.setRelationshipSince(financialArrangementsDetailRequest.getRelationshipSince());
				financialArrangementsDetailResponse
						.setOutstandingAmount(financialArrangementsDetailRequest.getOutstandingAmount());
				financialArrangementsDetailResponse
						.setSecurityDetails(financialArrangementsDetailRequest.getSecurityDetails());
				financialArrangementsDetailResponse.setAmount(financialArrangementsDetailRequest.getAmount());
				// financialArrangementsDetailResponse.setLenderType(LenderType.getById(financialArrangementsDetailRequest.getLenderType()).getValue());
				financialArrangementsDetailResponse.setLoanDate(financialArrangementsDetailRequest.getLoanDate());
				financialArrangementsDetailResponse.setLoanType(financialArrangementsDetailRequest.getLoanType());
				financialArrangementsDetailResponse
						.setFinancialInstitutionName(financialArrangementsDetailRequest.getFinancialInstitutionName());
				// financialArrangementsDetailResponse.setFacilityNature(NatureFacility.getById(financialArrangementsDetailRequest.getFacilityNatureId()).getValue());
				// financialArrangementsDetailResponse.setAddress(financialArrangementsDetailRequest.getAddress());
				
				financialArrangementsDetailResponse.setBuerauOutStanding(financialArrangementsDetailRequest.getBureauOutstandingAmount());
				financialArrangementsDetailResponse.setLcbgStatus(financialArrangementsDetailRequest.getLcBgStatus() != null ? LCBG_Status_SBI.getById(financialArrangementsDetailRequest.getLcBgStatus()).getValue().toString() : "-");
				financialArrangementsDetailResponse.setEmi(financialArrangementsDetailRequest.getEmi());
				financialArrangementsDetailResponse.setCollateralAmt(financialArrangementsDetailRequest.getCollateralSecurityAmount());
				
				financialArrangementsDetailResponseList.add(financialArrangementsDetailResponse);
			}
			corporateFinalViewResponse
					.setFinancialArrangementsDetailResponseList(financialArrangementsDetailResponseList);

		} catch (Exception e) {
			logger.error("Problem to get Data of Financial Arrangements Details {}", e);
		}

		// FINANCIAL TAB IN TEASER VIEW
		// IRR MAPPING
		Long denomination = Denomination.getById(primaryCorporateDetail.getDenominationId()).getDigit();
		try {
			FinancialInputRequest financialInputRequest = irrService.cmaIrrMappingService(userId, toApplicationId, null,
					denomination,proposalMapId,null);

			logger.info("financialInputRequest.getYear()===>>>{}" , financialInputRequest.getYear());
			// Profit & Loss Statement
			financialInputRequest.setNetSaleFy(CommonUtils.substractNumbers(financialInputRequest.getGrossSalesFy(),
					financialInputRequest.getLessExciseDuityFy()));
			financialInputRequest.setNetSaleSy(CommonUtils.substractNumbers(financialInputRequest.getGrossSalesSy(),
					financialInputRequest.getLessExciseDuitySy()));
			financialInputRequest.setNetSaleTy(CommonUtils.substractNumbers(financialInputRequest.getGrossSalesTy(),
					financialInputRequest.getLessExciseDuityTy()));
			financialInputRequest.setTotalExpenditureFy(CommonUtils.substractNumbers(
					CommonUtils.addNumbers(financialInputRequest.getIncreaseDecreaseStockFy(),
							financialInputRequest.getRawMaterialConsumedFy(),
							financialInputRequest.getPowerAndFuelCostFy(), financialInputRequest.getEmployeeCostFy(),
							financialInputRequest.getGeneralAndAdminExpeFy(),
							financialInputRequest.getSellingAndDistriExpeFy(), financialInputRequest.getMiscelExpeFy()),
					financialInputRequest.getLessExpeCapitaFy()));
			financialInputRequest.setTotalExpenditureSy(CommonUtils.substractNumbers(
					CommonUtils.addNumbers(financialInputRequest.getIncreaseDecreaseStockSy(),
							financialInputRequest.getRawMaterialConsumedSy(),
							financialInputRequest.getPowerAndFuelCostSy(), financialInputRequest.getEmployeeCostSy(),
							financialInputRequest.getGeneralAndAdminExpeSy(),
							financialInputRequest.getSellingAndDistriExpeSy(), financialInputRequest.getMiscelExpeSy()),
					financialInputRequest.getLessExpeCapitaSy()));
			financialInputRequest.setTotalExpenditureTy(CommonUtils.substractNumbers(
					CommonUtils.addNumbers(financialInputRequest.getIncreaseDecreaseStockTy(),
							financialInputRequest.getRawMaterialConsumedTy(),
							financialInputRequest.getPowerAndFuelCostTy(), financialInputRequest.getEmployeeCostTy(),
							financialInputRequest.getGeneralAndAdminExpeTy(),
							financialInputRequest.getSellingAndDistriExpeTy(), financialInputRequest.getMiscelExpeTy()),
					financialInputRequest.getLessExpeCapitaTy()));
			financialInputRequest.setOperatingProfitExclOiFy(CommonUtils.substractNumbers(
					financialInputRequest.getNetSaleFy(), financialInputRequest.getTotalExpenditureFy()));
			financialInputRequest.setOperatingProfitExclOiSy(CommonUtils.substractNumbers(
					financialInputRequest.getNetSaleSy(), financialInputRequest.getTotalExpenditureSy()));
			financialInputRequest.setOperatingProfitExclOiTy(CommonUtils.substractNumbers(
					financialInputRequest.getNetSaleTy(), financialInputRequest.getTotalExpenditureTy()));
			financialInputRequest.setOperatingProfitEbitadOiFy(CommonUtils.addNumbers(
					financialInputRequest.getOperatingProfitExclOiFy(), financialInputRequest.getOtherIncomeFy()));
			financialInputRequest.setOperatingProfitEbitadOiSy(CommonUtils.addNumbers(
					financialInputRequest.getOperatingProfitExclOiSy(), financialInputRequest.getOtherIncomeSy()));
			financialInputRequest.setOperatingProfitEbitadOiTy(CommonUtils.addNumbers(
					financialInputRequest.getOperatingProfitExclOiTy(), financialInputRequest.getOtherIncomeTy()));
			financialInputRequest.setPbdtFy(CommonUtils.substractNumbers(
					financialInputRequest.getOperatingProfitEbitadOiFy(), financialInputRequest.getInterestFy()));
			financialInputRequest.setPbdtSy(CommonUtils.substractNumbers(
					financialInputRequest.getOperatingProfitEbitadOiSy(), financialInputRequest.getInterestSy()));
			financialInputRequest.setPbdtTy(CommonUtils.substractNumbers(
					financialInputRequest.getOperatingProfitEbitadOiTy(), financialInputRequest.getInterestTy()));
			financialInputRequest.setProfitBeforeTaxationFy(CommonUtils
					.substractNumbers(financialInputRequest.getPbdtFy(), financialInputRequest.getDepriciationFy()));
			financialInputRequest.setProfitBeforeTaxationSy(CommonUtils
					.substractNumbers(financialInputRequest.getPbdtSy(), financialInputRequest.getDepriciationSy()));
			financialInputRequest.setProfitBeforeTaxationTy(CommonUtils
					.substractNumbers(financialInputRequest.getPbdtTy(), financialInputRequest.getDepriciationTy()));
			financialInputRequest.setProfitBeforeTaxFy(CommonUtils.addNumbers(
					financialInputRequest.getProfitBeforeTaxationFy(), financialInputRequest.getExceptionalIncomeFy()));
			financialInputRequest.setProfitBeforeTaxSy(CommonUtils.addNumbers(
					financialInputRequest.getProfitBeforeTaxationSy(), financialInputRequest.getExceptionalIncomeSy()));
			financialInputRequest.setProfitBeforeTaxTy(CommonUtils.addNumbers(
					financialInputRequest.getProfitBeforeTaxationTy(), financialInputRequest.getExceptionalIncomeTy()));
			financialInputRequest
					.setProfitAfterTaxFy(CommonUtils.substractNumbers(financialInputRequest.getProfitBeforeTaxFy(),
							financialInputRequest.getProvisionForTaxFy())
							+ financialInputRequest.getOtherIncomeNeedTocCheckOpFy());
			financialInputRequest
					.setProfitAfterTaxSy(CommonUtils.substractNumbers(financialInputRequest.getProfitBeforeTaxSy(),
							financialInputRequest.getProvisionForTaxSy())
							+ financialInputRequest.getOtherIncomeNeedTocCheckOpSy());
			financialInputRequest
					.setProfitAfterTaxTy(CommonUtils.substractNumbers(financialInputRequest.getProfitBeforeTaxTy(),
							financialInputRequest.getProvisionForTaxTy())
							+ financialInputRequest.getOtherIncomeNeedTocCheckOpTy());
			if (financialInputRequest.getDividendPayOutFy() == 0)
				financialInputRequest.setEquityDividendFy(0.0);
			else
				financialInputRequest.setEquityDividendFy(financialInputRequest.getDividendPayOutFy()
						* financialInputRequest.getShareFaceValue() / financialInputRequest.getShareCapitalFy());

			if (financialInputRequest.getDividendPayOutSy() == 0)
				financialInputRequest.setEquityDividendSy(0.0);
			else
				financialInputRequest.setEquityDividendSy(financialInputRequest.getDividendPayOutSy()
						* financialInputRequest.getShareFaceValue() / financialInputRequest.getShareCapitalSy());

			if (financialInputRequest.getDividendPayOutTy() == 0)
				financialInputRequest.setEquityDividendTy(0.0);
			else
				financialInputRequest.setEquityDividendTy(financialInputRequest.getDividendPayOutTy()
						* financialInputRequest.getShareFaceValue() / financialInputRequest.getShareCapitalTy());

			financialInputRequest.setEarningPerShareFy((financialInputRequest.getProfitAfterTaxFy())
					* financialInputRequest.getShareFaceValue() / financialInputRequest.getShareCapitalFy());
			financialInputRequest.setEarningPerShareSy((financialInputRequest.getProfitAfterTaxSy())
					* financialInputRequest.getShareFaceValue() / financialInputRequest.getShareCapitalSy());
			financialInputRequest.setEarningPerShareTy((financialInputRequest.getProfitAfterTaxTy())
					* financialInputRequest.getShareFaceValue() / financialInputRequest.getShareCapitalTy());

			// Balance Sheet -Equities and Liabilities
			financialInputRequest.setShareHolderFundsFy(CommonUtils.addNumbers(
					financialInputRequest.getShareCapitalFy(), financialInputRequest.getShareWarrantOutstandingsFy(),
					financialInputRequest.getRevalationReserveFy(),
					financialInputRequest.getOtherReserveAndSurplusFy()));
			financialInputRequest.setShareHolderFundsSy(CommonUtils.addNumbers(
					financialInputRequest.getShareCapitalSy(), financialInputRequest.getShareWarrantOutstandingsSy(),
					financialInputRequest.getRevalationReserveSy(),
					financialInputRequest.getOtherReserveAndSurplusSy()));
			financialInputRequest.setShareHolderFundsTy(CommonUtils.addNumbers(
					financialInputRequest.getShareCapitalTy(), financialInputRequest.getShareWarrantOutstandingsTy(),
					financialInputRequest.getRevalationReserveTy(),
					financialInputRequest.getOtherReserveAndSurplusTy()));
			financialInputRequest.setTotalNonCurruntLiablitiesFy(CommonUtils.addNumbers(
					financialInputRequest.getMinorityInterestFy(), financialInputRequest.getSecuredLoansFy(),
					financialInputRequest.getUnsecuredLoansOthersFy(),
					financialInputRequest.getUnsecuredLoansPromotersFy(),
					financialInputRequest.getDeferredTaxLiablitiesFy(),
					financialInputRequest.getOtherLongTermLiablitiesFy(), financialInputRequest.getOtherBorrowingFy(),
					financialInputRequest.getLongTermProvisionFy()));
			financialInputRequest.setTotalNonCurruntLiablitiesSy(CommonUtils.addNumbers(
					financialInputRequest.getMinorityInterestSy(), financialInputRequest.getSecuredLoansSy(),
					financialInputRequest.getUnsecuredLoansOthersSy(),
					financialInputRequest.getUnsecuredLoansPromotersSy(),
					financialInputRequest.getDeferredTaxLiablitiesSy(),
					financialInputRequest.getOtherLongTermLiablitiesSy(), financialInputRequest.getOtherBorrowingSy(),
					financialInputRequest.getLongTermProvisionSy()));
			financialInputRequest.setTotalNonCurruntLiablitiesTy(CommonUtils.addNumbers(
					financialInputRequest.getMinorityInterestTy(), financialInputRequest.getSecuredLoansTy(),
					financialInputRequest.getUnsecuredLoansOthersTy(),
					financialInputRequest.getUnsecuredLoansPromotersTy(),
					financialInputRequest.getDeferredTaxLiablitiesTy(),
					financialInputRequest.getOtherLongTermLiablitiesTy(), financialInputRequest.getOtherBorrowingTy(),
					financialInputRequest.getLongTermProvisionTy()));
			financialInputRequest.setTotalCurruntLiablitiesFy(CommonUtils.addNumbers(
					financialInputRequest.getTradePayablesFy(), financialInputRequest.getOtherCurruntLiablitiesFy(),
					financialInputRequest.getShortTermProvisionFy()));
			financialInputRequest.setTotalCurruntLiablitiesSy(CommonUtils.addNumbers(
					financialInputRequest.getTradePayablesSy(), financialInputRequest.getOtherCurruntLiablitiesSy(),
					financialInputRequest.getShortTermProvisionSy()));
			financialInputRequest.setTotalCurruntLiablitiesTy(CommonUtils.addNumbers(
					financialInputRequest.getTradePayablesTy(), financialInputRequest.getOtherCurruntLiablitiesTy(),
					financialInputRequest.getShortTermProvisionTy()));
			financialInputRequest
					.setTotalLiablitiesFy(CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsFy(),
							financialInputRequest.getTotalNonCurruntLiablitiesFy(),
							financialInputRequest.getTotalCurruntLiablitiesFy(),
							financialInputRequest.getOtherIncomeNeedTocCheckLiaFy()));
			financialInputRequest
					.setTotalLiablitiesSy(CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsSy(),
							financialInputRequest.getTotalNonCurruntLiablitiesSy(),
							financialInputRequest.getTotalCurruntLiablitiesSy(),
							financialInputRequest.getOtherIncomeNeedTocCheckLiaSy()));
			financialInputRequest
					.setTotalLiablitiesTy(CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsTy(),
							financialInputRequest.getTotalNonCurruntLiablitiesTy(),
							financialInputRequest.getTotalCurruntLiablitiesTy(),
							financialInputRequest.getOtherIncomeNeedTocCheckLiaTy()));

			// Balance Sheet -ASSETS
			financialInputRequest.setNetBlockFy(CommonUtils.substractThreeNumbers(
					financialInputRequest.getGrossBlockFy(), financialInputRequest.getLessAccumulatedDepreFy(),
					financialInputRequest.getImpairmentofAssetFy()));
			financialInputRequest.setNetBlockSy(CommonUtils.substractThreeNumbers(
					financialInputRequest.getGrossBlockSy(), financialInputRequest.getLessAccumulatedDepreSy(),
					financialInputRequest.getImpairmentofAssetSy()));
			financialInputRequest.setNetBlockTy(CommonUtils.substractThreeNumbers(
					financialInputRequest.getGrossBlockTy(), financialInputRequest.getLessAccumulatedDepreTy(),
					financialInputRequest.getImpairmentofAssetTy()));
			financialInputRequest.setTotalNonCurruntAssetFy(CommonUtils.addNumbers(
					financialInputRequest.getCapitalWorkInProgressFy(), financialInputRequest.getIntengibleAssetsFy(),
					financialInputRequest.getPreOperativeExpeFy(), financialInputRequest.getAssetInTransitFy(),
					financialInputRequest.getInvestmentInSubsidiariesFy(), financialInputRequest.getOtherInvestmentFy(),
					financialInputRequest.getLongTermLoansAndAdvaFy(),
					financialInputRequest.getOtheNonCurruntAssetFy()));
			financialInputRequest.setTotalNonCurruntAssetSy(CommonUtils.addNumbers(
					financialInputRequest.getCapitalWorkInProgressSy(), financialInputRequest.getIntengibleAssetsSy(),
					financialInputRequest.getPreOperativeExpeSy(), financialInputRequest.getAssetInTransitSy(),
					financialInputRequest.getInvestmentInSubsidiariesSy(), financialInputRequest.getOtherInvestmentSy(),
					financialInputRequest.getLongTermLoansAndAdvaSy(),
					financialInputRequest.getOtheNonCurruntAssetSy()));
			financialInputRequest.setTotalNonCurruntAssetTy(CommonUtils.addNumbers(
					financialInputRequest.getCapitalWorkInProgressTy(), financialInputRequest.getIntengibleAssetsTy(),
					financialInputRequest.getPreOperativeExpeTy(), financialInputRequest.getAssetInTransitTy(),
					financialInputRequest.getInvestmentInSubsidiariesTy(), financialInputRequest.getOtherInvestmentTy(),
					financialInputRequest.getLongTermLoansAndAdvaTy(),
					financialInputRequest.getOtheNonCurruntAssetTy()));
			financialInputRequest.setTotalCurruntAssetFy(CommonUtils.addNumbers(
					financialInputRequest.getInventoriesFy(), financialInputRequest.getSundryDebtorsFy(),
					financialInputRequest.getCashAndBankFy(), financialInputRequest.getOtherCurruntAssetFy(),
					financialInputRequest.getShortTermLoansAdvancesFy()));
			financialInputRequest.setTotalCurruntAssetSy(CommonUtils.addNumbers(
					financialInputRequest.getInventoriesSy(), financialInputRequest.getSundryDebtorsSy(),
					financialInputRequest.getCashAndBankSy(), financialInputRequest.getOtherCurruntAssetSy(),
					financialInputRequest.getShortTermLoansAdvancesSy()));
			financialInputRequest.setTotalCurruntAssetTy(CommonUtils.addNumbers(
					financialInputRequest.getInventoriesTy(), financialInputRequest.getSundryDebtorsTy(),
					financialInputRequest.getCashAndBankTy(), financialInputRequest.getOtherCurruntAssetTy(),
					financialInputRequest.getShortTermLoansAdvancesTy()));
			financialInputRequest.setTotalAssetFy(CommonUtils.addNumbers(financialInputRequest.getNetBlockFy(),
					financialInputRequest.getTotalCurruntAssetFy(), financialInputRequest.getTotalNonCurruntAssetFy()));
			financialInputRequest.setTotalAssetSy(CommonUtils.addNumbers(financialInputRequest.getNetBlockSy(),
					financialInputRequest.getTotalCurruntAssetSy(), financialInputRequest.getTotalNonCurruntAssetSy()));
			financialInputRequest.setTotalAssetTy(CommonUtils.addNumbers(financialInputRequest.getNetBlockTy(),
					financialInputRequest.getTotalCurruntAssetTy(), financialInputRequest.getTotalNonCurruntAssetTy()));
			financialInputRequest.setBookValueFy(financialInputRequest.getShareHolderFundsFy()
					/ (financialInputRequest.getShareCapitalFy() / financialInputRequest.getShareFaceValue()));
			financialInputRequest.setBookValueSy(financialInputRequest.getShareHolderFundsSy()
					/ (financialInputRequest.getShareCapitalSy() / financialInputRequest.getShareFaceValue()));
			financialInputRequest.setBookValueTy(financialInputRequest.getShareHolderFundsTy()
					/ (financialInputRequest.getShareCapitalTy() / financialInputRequest.getShareFaceValue()));
			corporateFinalViewResponse.setFinancialInputRequest(financialInputRequest);

		} catch (Exception e) {
			logger.error("Error while getting irr mapping data : ",e);
		}

		Double loanObligation=financialArrangementDetailsService.getTotalEmiOfAllDirByApplicationId(toApplicationId);
		corporateFinalViewResponse.setLoanObligation(loanObligation!= null ? loanObligation : 0);

		// ============================================FINAL TEASER VIEW
		// DETAILS=============================================//

		try {
			/*CorporateFinalInfoRequest corporateFinalInfoRequest = corporateFinalInfoService.get(userId,
					toApplicationId);*/  //  PREVIOUS
			CorporateFinalInfoRequest corporateFinalInfoRequest = corporateFinalInfoService.getByProposalId(userId,
					proposalMapId); // BASED ON NEW PROPOSAL ID

			corporateFinalViewResponse
					.setAboutUs(!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getAboutUs())
							? corporateFinalInfoRequest.getAboutUs()
							: null);
			
			corporateFinalViewResponse
					.setUdhyogAadharNo(!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getAadhar())
							? corporateFinalInfoRequest.getAadhar()
							: null);
			corporateFinalViewResponse
					.setContigentLiablitiesFy(corporateFinalInfoRequest.getContLiabilityFyAmt() != null
							? String.valueOf(corporateFinalInfoRequest.getContLiabilityFyAmt())
							: null);
			corporateFinalViewResponse
					.setContigentLiablitiesSy(corporateFinalInfoRequest.getContLiabilitySyAmt() != null
							? String.valueOf(corporateFinalInfoRequest.getContLiabilitySyAmt())
							: null);
			corporateFinalViewResponse
					.setContigentLiablitiesTy(corporateFinalInfoRequest.getContLiabilityTyAmt() != null
							? String.valueOf(corporateFinalInfoRequest.getContLiabilityTyAmt())
							: null);
			corporateFinalViewResponse
					.setCreditRating(!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getCreditRatingId())
							? CreditRatingTerm.getById(corporateFinalInfoRequest.getCreditRatingId()).getValue()
							: " ");
		} catch (Exception e1) {
			logger.error("Error while getting corporate final information : ",e1);
		}

		// ACHIEVMENTS DETAILS
		try {
			/*corporateFinalViewResponse.setAchievementDetailList(
					achievmentDetailsService.getAchievementDetailList(toApplicationId, userId));*/ // PREVIOUS API
			corporateFinalViewResponse.setAchievementDetailList(
					achievmentDetailsService.getAchievementDetailListForMultipleBank(proposalMapId)); // NEW BASED ON PROPOSAL  MAPPING ID
		} catch (Exception e) {
			logger.error("Problem to get Data of Achievement Details {}", e);
		}
		// CREDIT RATING TABLE
		try {
/*			List<CreditRatingOrganizationDetailRequest> creditRatingOrganizationDetailRequestList = creditRatingOrganizationDetailsService
					.getcreditRatingOrganizationDetailsList(toApplicationId, userId);*/
			List<CreditRatingOrganizationDetailRequest> creditRatingOrganizationDetailRequestList = creditRatingOrganizationDetailsService
					.getCreditRatingOrganizationDetailsListFromProposalId(proposalMapId, userId);
			List<CreditRatingOrganizationDetailResponse> creditRatingOrganizationDetailResponseList = new ArrayList<>();
			for (CreditRatingOrganizationDetailRequest creditRatingOrganizationDetailRequest : creditRatingOrganizationDetailRequestList) {
				CreditRatingOrganizationDetailResponse creditRatingOrganizationDetailResponse = new CreditRatingOrganizationDetailResponse();
				creditRatingOrganizationDetailResponse.setAmount(creditRatingOrganizationDetailRequest.getAmount());
				creditRatingOrganizationDetailResponse.setCreditRatingFund(
						creditRatingOrganizationDetailRequest.getCreditRatingFundId() != null ? CreditRatingFund
								.getById(creditRatingOrganizationDetailRequest.getCreditRatingFundId()).getValue()
								: null);

				OneFormResponse oneFormResponse = oneFormClient.getRatingById(
						CommonUtils.isObjectNullOrEmpty(creditRatingOrganizationDetailRequest.getCreditRatingOptionId())
								? null
								: creditRatingOrganizationDetailRequest.getCreditRatingOptionId().longValue());
				MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) oneFormResponse.getData(), MasterResponse.class);
				if (masterResponse != null) {
					creditRatingOrganizationDetailResponse.setCreditRatingOption(masterResponse.getValue());
				} else {
					creditRatingOrganizationDetailResponse.setCreditRatingOption(CommonUtils.NOT_APPLICABLE);
				}
				if (creditRatingOrganizationDetailRequest.getCreditRatingTermId() != null)
					creditRatingOrganizationDetailResponse.setCreditRatingTerm(CreditRatingTerm
							.getById(creditRatingOrganizationDetailRequest.getCreditRatingTermId()).getValue());
				if (creditRatingOrganizationDetailRequest.getRatingAgencyId() != null)
					creditRatingOrganizationDetailResponse.setRatingAgency(
							RatingAgency.getById(creditRatingOrganizationDetailRequest.getRatingAgencyId()).getValue());
				creditRatingOrganizationDetailResponse
						.setFacilityName(creditRatingOrganizationDetailRequest.getFacilityName());
				creditRatingOrganizationDetailResponseList.add(creditRatingOrganizationDetailResponse);
				creditRatingOrganizationDetailResponse
						.setEntityName(creditRatingOrganizationDetailRequest.getEntityName());
				if (creditRatingOrganizationDetailRequest.getRatingDate() != null) {
					creditRatingOrganizationDetailResponse
							.setRatingDate(creditRatingOrganizationDetailRequest.getRatingDate());
				}
			}
			corporateFinalViewResponse
					.setCreditRatingOrganizationDetailResponse(creditRatingOrganizationDetailResponseList);
		} catch (Exception e) {
			logger.error("Problem to get Data of Credit Rating {}", e);
		}
		
		/*get cmr score cibil */	
		try {
			String cmrScore= cibilClient.getCMRScore(toApplicationId);
			if (cmrScore != null && cmrScore.contains("EXP")) {
				corporateFinalViewResponse.setMsmeRankTitle("Experian");
			}else if (cmrScore != null && cmrScore.contains("CIBIL")) {
				corporateFinalViewResponse.setMsmeRankTitle("Cibil");
			}
			corporateFinalViewResponse.setCibilCmrScore(cmrScore != null ? cmrScore : "Not Found");	
		} catch (Exception e) {
			logger.error("error while CIBIL CMR score : ",e);
		}
		
		// itr xml isUpload or Online check
		try {
			ITRConnectionResponse itrConnectionResponse = itrClient.getIsUploadAndYearDetails(toApplicationId);
			if (itrConnectionResponse != null) {
				corporateFinalViewResponse.setItrXmlIsUploaded(itrConnectionResponse.getData());
			} else {
				logger.info("itr Response is null");

			}

		} catch (Exception e) {
			logger.error("error while itr xml is uploaded or not check : ",e);
		}

		// EXISTING PRODUCT DETAILS
		try {
			/*corporateFinalViewResponse.setExistingProductDetailRequestList(
					existingProductDetailsService.getExistingProductDetailList(toApplicationId, userId));*/
			corporateFinalViewResponse.setExistingProductDetailRequestList(
					existingProductDetailsService.getExistingProductDetailListByProposalId(proposalMapId, userId)); // NEW
		} catch (Exception e) {
			logger.error("Problem to get Data of Existing Product {}", e);
		}
		// PROPOSED PRODUCT DETAILS
		try {
			/*corporateFinalViewResponse.setProposedProductDetailRequestList(
					proposedProductDetailsService.getProposedProductDetailList(toApplicationId, userId));*/
			corporateFinalViewResponse.setProposedProductDetailRequestList(
					proposedProductDetailsService.getProposedProductDetailListFromProposalId(proposalMapId, userId)); // new

		} catch (Exception e) {
			logger.error("Problem to get Data of Proposed Product {}", e);
		}

		// ASSOCIATED CONCERNS
		try {
			/*corporateFinalViewResponse.setAssociatedConcernDetailRequests(
					associatedConcernDetailService.getAssociatedConcernsDetailList(toApplicationId, userId));*/
			corporateFinalViewResponse.setAssociatedConcernDetailRequests(
					associatedConcernDetailService.getAssociatedConcernsDetailListByProposalId(proposalMapId,userId));

		} catch (Exception e) {
			logger.error("Problem to get Data of Associated Concerns {}", e);
		}

		// PROMOTOR BACKGROUND DETAILS
		try {
/*			List<PromotorBackgroundDetailRequest> promotorBackgroundDetailRequestList = promotorBackgroundDetailsService
					.getPromotorBackgroundDetailList(toApplicationId, userId);*/
			List<PromotorBackgroundDetailRequest> promotorBackgroundDetailRequestList = promotorBackgroundDetailsService
					.getPromotorBackgroundDetailListByProposalId(toApplicationId, proposalMapId,userId); // NEW BASED ON PROPOSAL MAPID

			List<PromotorBackgroundDetailResponse> promotorBackgroundDetailResponseList = new ArrayList<>();
			for (PromotorBackgroundDetailRequest promotorBackgroundDetailRequest : promotorBackgroundDetailRequestList) {
				PromotorBackgroundDetailResponse promotorBackgroundDetailResponse = new PromotorBackgroundDetailResponse();
				// promotorBackgroundDetailResponse.setAchievements(promotorBackgroundDetailRequest.getAchivements());
				String promotorName = "";
				if (promotorBackgroundDetailRequest.getSalutationId() != null) {
					promotorName = Title.getById(promotorBackgroundDetailRequest.getSalutationId()).getValue();
				}
				promotorName += promotorBackgroundDetailRequest.getPromotorsName();
				promotorBackgroundDetailResponse.setPromotorsName(promotorName);
				promotorBackgroundDetailResponse.setPanNo(promotorBackgroundDetailRequest.getPanNo().toUpperCase());
				promotorBackgroundDetailResponse.setAddress(promotorBackgroundDetailRequest.getAddress());
				promotorBackgroundDetailResponse.setGender((promotorBackgroundDetailRequest.getGender() != null
						? Gender.getById(promotorBackgroundDetailRequest.getGender()).getValue()
						: " "));
				promotorBackgroundDetailResponse
						.setDin(!CommonUtils.isObjectNullOrEmpty(promotorBackgroundDetailRequest.getDin())
								? promotorBackgroundDetailRequest.getDin().toString()
								: " ");
				promotorBackgroundDetailResponse
						.setTotalExperience(convertValue(promotorBackgroundDetailRequest.getTotalExperience()));
				promotorBackgroundDetailResponse
						.setNetworth(convertValue(promotorBackgroundDetailRequest.getNetworth()));
				promotorBackgroundDetailResponse
						.setAppointmentDate(promotorBackgroundDetailRequest.getAppointmentDate() != null
								? simpleDateFormat.format(promotorBackgroundDetailRequest.getAppointmentDate())
								: null);
				promotorBackgroundDetailResponse
						.setRelationshipType((promotorBackgroundDetailRequest.getRelationshipType() != null
								? DirectorRelationshipType
										.getById(promotorBackgroundDetailRequest.getRelationshipType()).getValue()
								: " "));
				promotorBackgroundDetailResponse.setDesignation(promotorBackgroundDetailRequest.getDesignation());
				promotorBackgroundDetailResponse.setMobile(promotorBackgroundDetailRequest.getMobile());
				promotorBackgroundDetailResponseList.add(promotorBackgroundDetailResponse);
			}
			corporateFinalViewResponse.setPromotorBackgroundDetailResponseList(promotorBackgroundDetailResponseList);
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}

		try {
			//CorporateMcqRequest corporateMcqRequest = corporateMcqService.get(toApplicationId);
			CorporateMcqRequest corporateMcqRequest = corporateMcqService.get(proposalMapId);

			corporateFinalViewResponse.setTechnologyRiskId(corporateMcqRequest.getTechnologyRiskId() != null
					? TechnologyRisk.getById(corporateMcqRequest.getTechnologyRiskId()).getValue()
					: null);
			corporateFinalViewResponse.setCustomerQuality(corporateMcqRequest.getCustomerQuality() != null
					? CustomerQuality.getById(corporateMcqRequest.getCustomerQuality()).getValue()
					: null);
			corporateFinalViewResponse.setSupplierQuality(corporateMcqRequest.getSupplierQuality() != null
					? SupplierQuality.getById(corporateMcqRequest.getSupplierQuality()).getValue()
					: null);
			corporateFinalViewResponse.setSustainabilityProduct(corporateMcqRequest.getSustainabilityProduct() != null
					? SustainabilityProduct.getById(corporateMcqRequest.getSustainabilityProduct()).getValue()
					: null);
			corporateFinalViewResponse.setEmployeeRelations(corporateMcqRequest.getEmployeeRelations() != null
					? IndustrialRelations.getById(corporateMcqRequest.getEmployeeRelations()).getValue()
					: null);
			corporateFinalViewResponse.setProductSeasonality(corporateMcqRequest.getProductSeasonality() != null
					? ProductSeasonality.getById(corporateMcqRequest.getProductSeasonality()).getValue()
					: null);
			corporateFinalViewResponse
					.setImpactOnOperatingMargins(corporateMcqRequest.getImpactOnOperatingMargins() != null
							? OperatingMargins.getById(corporateMcqRequest.getImpactOnOperatingMargins()).getValue()
							: null);
			corporateFinalViewResponse.setOrderBookPosition(corporateMcqRequest.getOrderBookPosition() != null
					? OrderBook.getById(corporateMcqRequest.getOrderBookPosition()).getValue()
					: null);
			corporateFinalViewResponse.setEnvironmentalImpact(corporateMcqRequest.getEnvironmentalImpact() != null
					? EnvironmentalImpact.getById(corporateMcqRequest.getEnvironmentalImpact()).getValue()
					: null);
			corporateFinalViewResponse.setAccountingQuality(corporateMcqRequest.getAccountingQuality() != null
					? AccountingQuality.getById(corporateMcqRequest.getAccountingQuality()).getValue()
					: null);
			corporateFinalViewResponse
					.setFinancialRestructuringHistory(corporateMcqRequest.getFinancialRestructuringHistory() != null
							? FinancialRestructuring.getById(corporateMcqRequest.getFinancialRestructuringHistory())
									.getValue()
							: null);
			corporateFinalViewResponse.setIntegrity(corporateMcqRequest.getIntegrity() != null
					? Integrity.getById(corporateMcqRequest.getIntegrity()).getValue()
					: null);
			corporateFinalViewResponse.setBusinessCommitment(corporateMcqRequest.getBusinessCommitment() != null
					? BusinessCommitment.getById(corporateMcqRequest.getBusinessCommitment()).getValue()
					: null);
			corporateFinalViewResponse.setManagementCompetence(corporateMcqRequest.getManagementCompetence() != null
					? ManagementCompetence.getById(corporateMcqRequest.getManagementCompetence()).getValue()
					: null);
			corporateFinalViewResponse.setBusinessExperience(corporateMcqRequest.getBusinessExperience() != null
					? BusinessExperience.getById(corporateMcqRequest.getBusinessExperience()).getValue()
					: null);
			corporateFinalViewResponse.setSuccessionPlanning(corporateMcqRequest.getSuccessionPlanning() != null
					? SuccessionPlanning.getById(corporateMcqRequest.getSuccessionPlanning()).getValue()
					: null);
			corporateFinalViewResponse.setFinancialStrength(corporateMcqRequest.getFinancialStrength() != null
					? FinancialSupport.getById(corporateMcqRequest.getFinancialStrength()).getValue()
					: null);
			corporateFinalViewResponse.setAbilityToRaiseFunds(corporateMcqRequest.getAbilityToRaiseFunds() != null
					? AbilityRaiseFunds.getById(corporateMcqRequest.getAbilityToRaiseFunds()).getValue()
					: null);
			corporateFinalViewResponse.setIntraCompany(corporateMcqRequest.getIntraCompany() != null
					? CompanyConflicts.getById(corporateMcqRequest.getIntraCompany()).getValue()
					: null);
			corporateFinalViewResponse.setInternalControl(corporateMcqRequest.getInternalControl() != null
					? InternalControl.getById(corporateMcqRequest.getInternalControl()).getValue()
					: null);
			corporateFinalViewResponse.setCreditTrackRecord(corporateMcqRequest.getCreditTrackRecord() != null
					? CreditRecord.getById(corporateMcqRequest.getCreditTrackRecord()).getValue()
					: null);
			corporateFinalViewResponse
					.setStatusOfProjectClearances(corporateMcqRequest.getStatusOfProjectClearances() != null
							? StatusClearances.getById(corporateMcqRequest.getStatusOfProjectClearances()).getValue()
							: null);
			corporateFinalViewResponse.setStatusOfFinancialClosure(
					corporateMcqRequest.getStatusOfFinancialClosure() != null
							? StatusFinancialClosure.getById(corporateMcqRequest.getStatusOfFinancialClosure())
									.getValue()
							: null);
			corporateFinalViewResponse
					.setInfrastructureAvailability(corporateMcqRequest.getInfrastructureAvailability() != null
							? InfrastructureAvailability.getById(corporateMcqRequest.getInfrastructureAvailability())
									.getValue()
							: null);
			corporateFinalViewResponse.setConstructionContract(corporateMcqRequest.getConstructionContract() != null
					? ConstructionContract.getById(corporateMcqRequest.getConstructionContract()).getValue()
					: null);
			corporateFinalViewResponse.setNumberOfCheques(corporateMcqRequest.getNumberOfCheques() != null
					? ChequesReturned.getById(corporateMcqRequest.getNumberOfCheques()).getValue()
					: null);
			corporateFinalViewResponse.setNumberOfTimesDp(corporateMcqRequest.getNumberOfTimesDp() != null
					? LimitOverdrawn.getById(corporateMcqRequest.getNumberOfTimesDp()).getValue()
					: null);
			corporateFinalViewResponse.setCumulativeNoOfDaysDp(corporateMcqRequest.getCumulativeNoOfDaysDp() != null
					? CumulativeOverdrawn.getById(corporateMcqRequest.getCumulativeNoOfDaysDp()).getValue()
					: null);
			corporateFinalViewResponse
					.setComplianceWithSanctioned(corporateMcqRequest.getComplianceWithSanctioned() != null
							? ComplianceConditions.getById(corporateMcqRequest.getComplianceWithSanctioned()).getValue()
							: null);
			corporateFinalViewResponse.setProgressReports(corporateMcqRequest.getProgressReports() != null
					? SubmissionReports.getById(corporateMcqRequest.getProgressReports()).getValue()
					: null);
			corporateFinalViewResponse.setDelayInReceipt(corporateMcqRequest.getDelayInReceipt() != null
					? DelayInstalments.getById(corporateMcqRequest.getDelayInReceipt()).getValue()
					: null);
			corporateFinalViewResponse.setDelayInSubmission(corporateMcqRequest.getDelayInSubmission() != null
					? DelaySubmission.getById(corporateMcqRequest.getDelayInSubmission()).getValue()
					: null);
			corporateFinalViewResponse.setNumberOfLc(corporateMcqRequest.getNumberOfLc() != null
					? BorrowerInvoked.getById(corporateMcqRequest.getNumberOfLc()).getValue()
					: null);
			corporateFinalViewResponse
					.setUnhedgedForeignCurrency(corporateMcqRequest.getUnhedgedForeignCurrency() != null
							? UnhedgedCurrency.getById(corporateMcqRequest.getUnhedgedForeignCurrency()).getValue()
							: null);
			corporateFinalViewResponse.setProjectedDebtService(corporateMcqRequest.getProjectedDebtService() != null
					? ProjectedRatio.getById(corporateMcqRequest.getProjectedDebtService()).getValue()
					: null);
			corporateFinalViewResponse.setInternalRateReturn(corporateMcqRequest.getInternalRateReturn() != null
					? InternalReturn.getById(corporateMcqRequest.getInternalRateReturn()).getValue()
					: null);
			corporateFinalViewResponse.setSensititivityAnalysis(corporateMcqRequest.getSensititivityAnalysis() != null
					? SensititivityAnalysis.getById(corporateMcqRequest.getSensititivityAnalysis()).getValue()
					: null);
			corporateFinalViewResponse
					.setVarianceInProjectedSales(corporateMcqRequest.getVarianceInProjectedSales() != null
							? VarianceSales.getById(corporateMcqRequest.getVarianceInProjectedSales()).getValue()
							: null);

		} catch (Exception e) {
			logger.error("Error while getting mcq : ",e);
		}
		// OwnerShipDetails
		try {
			List<OwnershipDetailRequest> ownershipDetailRequestsList = ownerShipDetailsService
					.getOwnershipDetailList(toApplicationId, userId,proposalMapId);
			List<OwnershipDetailResponse> ownershipDetailResponseList = new ArrayList<>();
			for (OwnershipDetailRequest ownershipDetailRequest : ownershipDetailRequestsList) {
				OwnershipDetailResponse ownershipDetailResponse = new OwnershipDetailResponse();
				ownershipDetailResponse.setRemarks(ownershipDetailRequest.getRemarks());
				ownershipDetailResponse.setStackPercentage(ownershipDetailRequest.getStackPercentage());
				ownershipDetailResponse.setShareHoldingCategory(
						ShareHoldingCategory.getById(ownershipDetailRequest.getShareHoldingCategoryId()).getValue());
				ownershipDetailResponseList.add(ownershipDetailResponse);
			}
			corporateFinalViewResponse.setOwnershipDetailResponseList(ownershipDetailResponseList);

		} catch (Exception e) {
			logger.error("Problem to get Data of Ownership Details {}", e);
		}

		// cost of project
		try {
		/*	List<TotalCostOfProjectRequest> costOfProjectsList = costOfProjectService
					.getCostOfProjectDetailList(toApplicationId, userId);*/

			List<TotalCostOfProjectRequest> costOfProjectsList = costOfProjectService
					.getCostOfProjectDetailListByProposalId(proposalMapId, userId);
			List<TotalCostOfProjectResponse> costOfProjectResponses = new ArrayList<TotalCostOfProjectResponse>();
			for (TotalCostOfProjectRequest costOfProjectRequest : costOfProjectsList) {
				TotalCostOfProjectResponse costOfProjectResponse = new TotalCostOfProjectResponse();
				BeanUtils.copyProperties(costOfProjectRequest, costOfProjectResponse);
				if (costOfProjectRequest.getParticularsId() != null)
					costOfProjectResponse.setParticulars(Particular
							.getById(Integer.parseInt(costOfProjectRequest.getParticularsId().toString())).getValue());
				costOfProjectResponses.add(costOfProjectResponse);
			}
			corporateFinalViewResponse.setTotalCostOfProjectResponseList(costOfProjectResponses);
		} catch (Exception e1) {
			logger.error("Problem to get Data of Total cost of project{}", e1);
		}

		// Means of finance

		try {
			/*List<FinanceMeansDetailRequest> financeMeansDetailRequestsList = financeMeansDetailsService
					.getMeansOfFinanceList(toApplicationId, userId);*/
			List<FinanceMeansDetailRequest> financeMeansDetailRequestsList = financeMeansDetailsService
					.getMeansOfFinanceListByProposalId(proposalMapId, userId);
			List<FinanceMeansDetailResponse> financeMeansDetailResponsesList = new ArrayList<FinanceMeansDetailResponse>();
			for (FinanceMeansDetailRequest financeMeansDetailRequest : financeMeansDetailRequestsList) {
				FinanceMeansDetailResponse detailResponse = new FinanceMeansDetailResponse();
				BeanUtils.copyProperties(financeMeansDetailRequest, detailResponse);

				if (financeMeansDetailRequest.getFinanceMeansCategoryId() != null)
					detailResponse.setFinanceMeansCategory(FinanceCategory
							.getById(Integer.parseInt(financeMeansDetailRequest.getFinanceMeansCategoryId().toString()))
							.getValue());
				financeMeansDetailResponsesList.add(detailResponse);
			}
			corporateFinalViewResponse.setFinanceMeansDetailResponseList(financeMeansDetailResponsesList);
		} catch (Exception e1) {
			logger.error("Problem to get Data of Finance Means Details {}", e1);
		}

		// Security

		try {
	/*		corporateFinalViewResponse.setSecurityCorporateDetailRequestList(
					securityCorporateDetailsService.getsecurityCorporateDetailsList(toApplicationId, userId));*/
			corporateFinalViewResponse.setSecurityCorporateDetailRequestList(
					securityCorporateDetailsService.getSecurityCorporateDetailsListFromProposalId(proposalMapId, userId));

		} catch (Exception e) {
			logger.error("Problem to get Data of Security Details {}", e);
		}

		// Share Price
		corporateFinalViewResponse.setSharePriceFace(corporateApplicantDetail.getSharePriceFace());
		corporateFinalViewResponse.setSharePriceMarket(corporateApplicantDetail.getSharePriceMarket());

		// get data of Details of Guarantors
		try {
/*			corporateFinalViewResponse.setGuarantorsCorporateDetailRequestList(
					guarantorsCorporateDetailService.getGuarantorsCorporateDetailList(toApplicationId, userId));*/
			corporateFinalViewResponse.setGuarantorsCorporateDetailRequestList(
					guarantorsCorporateDetailService.getGuarantorsCorporateDetailListByProposalId(proposalMapId, userId));
		} catch (Exception e) {
			logger.error("Problem to get Data of Details of Guarantor {}", e);
		}

		// get data of Monthly Turnover
		try {
			corporateFinalViewResponse.setMonthlyTurnoverDetailRequestList(
					monthlyTurnoverDetailService.getMonthlyTurnoverDetailListByProposalId(toApplicationId, userId,applicationProposalMapping.getProposalId()));
		} catch (Exception e) {
			logger.error("Problem to get Data of Monthly Turnover {}", e);
		}

		// bank statement data
		ReportRequest reportRequest = new ReportRequest();
		reportRequest.setApplicationId(toApplicationId);
		reportRequest.setUserId(userId);
		List<Data> datas = new ArrayList<>();
		try {
			AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportForCam(reportRequest);
			List<HashMap<String, Object>> hashMaps = (List<HashMap<String, Object>>) analyzerResponse.getData();
			if (!CommonUtils.isListNullOrEmpty(hashMaps)) {
				for (HashMap<String, Object> hashMap : hashMaps) {
					Data data = MultipleJSONObjectHelper.getObjectFromMap(hashMap, Data.class);
					datas.add(data);
				}
			}
			corporateFinalViewResponse.setBankData(datas);
			// Data data = MultipleJSONObjectHelper.getObjectFromMap((HashMap<String,
			// Object>) analyzerResponse.getData(), Data.class);
			// corporatePrimaryViewResponse.setMonthlyDetailList(data.getMonthlyDetailList());
			// corporatePrimaryViewResponse.setTop5FundReceivedList(data.getTop5FundReceivedList());
			// corporatePrimaryViewResponse.setTop5FundTransferedList(data.getTop5FundTransferedList());
		} catch (Exception e) {
			logger.error("Error while getting perfios data : ",e);
		}

		// scoring Data
		Long fpProductMappingId = null;
		try {
			UsersRequest usersRequest = new UsersRequest();
			usersRequest.setId(fundProviderUserId);
			UserResponse userResponse = usersClient.getLastAccessApplicant(usersRequest);
			fpProductMappingId = userResponse.getId();
			logger.info("fp product id=========================>>>>>{}" , fpProductMappingId);
		} catch (Exception e) {
			logger.error("Error while getting fpMappingId For Scoring : ",e);
		}
		ScoringRequest scoringRequest = new ScoringRequest();
		scoringRequest.setApplicationId(toApplicationId);
		scoringRequest.setFpProductId(fpProductMappingId);
		try {
			logger.info("Enter in get scoing data -----APPID-->{}  --FPProductId-->{}" , toApplicationId , fpProductMappingId);
			ScoringResponse scoringResponse = scoringClient.getScore(scoringRequest);
			if (!CommonUtils.isObjectNullOrEmpty(scoringResponse)
					&& !CommonUtils.isObjectNullOrEmpty(scoringResponse.getDataObject())) {
				logger.info("Find Data From Scoring ");
				ProposalScoreResponse proposalScoreResponse = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) scoringResponse.getDataObject(), ProposalScoreResponse.class);
				corporateFinalViewResponse.setDataList(scoringResponse.getDataList());
				corporateFinalViewResponse.setScoringModelName(proposalScoreResponse.getScoringModelName() != null ? proposalScoreResponse.getScoringModelName() : "-");
				corporateFinalViewResponse.setManagementRiskScore(
						CommonUtils.checkDoubleNull(proposalScoreResponse.getManagementRiskScore()));
				corporateFinalViewResponse.setFinancialRiskScore(
						CommonUtils.checkDoubleNull(proposalScoreResponse.getFinancialRiskScore()));
				corporateFinalViewResponse.setBuisnessRiskScore(
						CommonUtils.checkDoubleNull(proposalScoreResponse.getBusinessRiskScore()));
				corporateFinalViewResponse.setManagementRiskScoreWeight(
						CommonUtils.checkDoubleNull(proposalScoreResponse.getManagementRiskWeight()));
				corporateFinalViewResponse.setFinancialRiskScoreWeight(
						CommonUtils.checkDoubleNull(proposalScoreResponse.getFinancialRiskWeight()));
				corporateFinalViewResponse.setBuisnessRiskScoreWeight(
						CommonUtils.checkDoubleNull(proposalScoreResponse.getBusinessRiskWeight()));
				corporateFinalViewResponse.setScoreInterpretation(proposalScoreResponse.getInterpretation());
				corporateFinalViewResponse
						.setManagementRiskMaxTotalScore(proposalScoreResponse.getManagementRiskMaxTotalScore());
				corporateFinalViewResponse
						.setFinancialRiskMaxTotalScore(proposalScoreResponse.getFinancialRiskMaxTotalScore());
				corporateFinalViewResponse
						.setBusinessRiskMaxTotalScore(proposalScoreResponse.getBusinessRiskMaxTotalScore());
				corporateFinalViewResponse
						.setManagementRiskWeightOfScoring(proposalScoreResponse.getManagementRiskWeightOfScoring());
				corporateFinalViewResponse
						.setFinancialRiskWeightOfScoring(proposalScoreResponse.getFinancialRiskWeightOfScoring());
				corporateFinalViewResponse
						.setBusinessRiskWeightOfScoring(proposalScoreResponse.getBusinessRiskWeightOfScoring());

				corporateFinalViewResponse.setWeightConsider(proposalScoreResponse.getWeightConsider());
				corporateFinalViewResponse
						.setManagementRiskMaxTotalWeight(proposalScoreResponse.getManagementRiskMaxTotalWeight());
				corporateFinalViewResponse
						.setFinancialRiskMaxTotalWeight(proposalScoreResponse.getFinancialRiskMaxTotalWeight());
				corporateFinalViewResponse
						.setBusinessRiskMaxTotalWeight(proposalScoreResponse.getBusinessRiskMaxTotalWeight());

				// if ture so show two col
				corporateFinalViewResponse
						.setIsProportionateScoreConsider(proposalScoreResponse.getIsProportionateScoreConsider());// Score(out
																													// of),
																													// proportionateScoreFS
				corporateFinalViewResponse.setProportionateScore(proposalScoreResponse.getProportionateScore());
				corporateFinalViewResponse.setProportionateScoreFS(proposalScoreResponse.getProportionateScoreFS());
			} else {
				logger.info("SCORING OBJECT NULL OR EMPTY -------------------->");
			}
		} catch (ScoringException | IOException e1) {
			logger.error(CommonUtils.EXCEPTION,e1);
		}


		// Product Name
		if(fpProductMappingId != null) {
			String productName = productMasterRepository.getFpProductName(fpProductMappingId);
			if(productName != null) {
				corporateFinalViewResponse.setFpProductName(productName);
			}else {
				logger.info("product name is null..");
			}
		}else {
			logger.info("fpProductMapping id is null..");
		}


		// Eligibility Data

		//int loanId = primaryCorporateDetail.getProductId(); //previous
		int loanId = applicationProposalMapping.getProductId(); // NEW BASED ON PROPOAL MAPPING PRODUCT ID

		switch (loanId) {

		case 1:

			WorkingCapitalParameter workingCapitalPara = workingCapitalRepository.getByID(fpProductMappingId);
			if (workingCapitalPara.getAssessmentMethodId() != null) {
				Long assessmentId = workingCapitalPara.getAssessmentMethodId().longValue();
				corporateFinalViewResponse.setAssesmentId(assessmentId);
			} else {
				logger.info("assesment id is null in wc");
			}
			break;

		case 2:

			TermLoanParameter termLoanParameter = termLoanParameterRepository.getById(fpProductMappingId);
			if (termLoanParameter.getAssessmentMethodId() != null) {
				Long assessmentId = termLoanParameter.getAssessmentMethodId().longValue();
				corporateFinalViewResponse.setAssesmentId(assessmentId);
			} else {
				logger.info("assesment id is null tl");
			}
			break;

		case 16:

			WcTlParameter wctlPara = wctlrepo.getById(fpProductMappingId);
			if (wctlPara.getAssessmentMethodId() != null) {
				Long assessmentId = wctlPara.getAssessmentMethodId().longValue();
				corporateFinalViewResponse.setAssesmentId(assessmentId);
			} else {
				logger.info("assesment id is null in wctl");
			}
			break;

		default:
			logger.info("invalid loan id");
			break;
		}

		EligibililityRequest eligibilityReq = new EligibililityRequest();
		eligibilityReq.setApplicationId(toApplicationId);
		// eligibilityReq.set
		eligibilityReq.setFpProductId(fpProductMappingId);
		logger.info(" for eligibility appid============>>{}" , toApplicationId);

		try {
			EligibilityResponse eligibilityResp = eligibilityClient.corporateEligibilityData(eligibilityReq);
			corporateFinalViewResponse.setEligibilityDataObject(eligibilityResp.getData());
		} catch (Exception e1) {
			logger.error(CommonUtils.EXCEPTION,e1);
		}

		/*loan eligibility financial year*/
		corporateFinalViewResponse.setEligibilityFinancialYear(CommonUtils.getFinancialYear());

		// CGTMSE
		try {
			CGTMSEDataResponse cgtmseDataResp = thirdPartyClient.getCalulation(toApplicationId,fpProductMappingId);
			corporateFinalViewResponse.setCgtmseData(cgtmseDataResp);
		} catch (Exception e) {
			logger.error("Error while calling CGTMSE data : ",e);
		}

		// MCA DATA
		try {
			String companyId = loanApplicationMaster.getMcaCompanyId();
			corporateFinalViewResponse.setCompanyId(companyId);
			logger.info("mca comp id==>>{}" , companyId);
			VerifyAPIRequest verifyReq=new VerifyAPIRequest();
			verifyReq.setApplicationId(toApplicationId);
			McaResponse directorData = mcaClient.getVerifyApiData(verifyReq);
			if(directorData!= null) {
				corporateFinalViewResponse.setVerifyApiData(directorData.getData());
			}
			if (companyId != null) {
				corporateFinalViewResponse.setMcaNotApplicable(Boolean.FALSE);
				McaResponse mcaResponse = mcaClient.getCompanyDetailedData(companyId);
				McaResponse mcaStatusResponse = mcaClient.mcaStatusCheck(String.valueOf(toApplicationId), companyId);
				if (mcaStatusResponse != null) {
					corporateFinalViewResponse.setMcaCheckStatus(mcaStatusResponse.getData());
				} else {
					logger.warn("::::::=====MCA Check Status Data is Null====:::::::For:::::CompanyId==>{}" , companyId);
				}

				if (!CommonUtils.isObjectNullOrEmpty(mcaResponse)) {
					corporateFinalViewResponse.setMcaData(mcaResponse);
				} else {
					logger.warn("::::::=====MCA Data is Null====:::::::For:::::CompanyId==>{}" , companyId);
				}

				McaResponse mcaFinancialAndDetailsRes=mcaClient.getCompanyFinancialCalcAndDetails(toApplicationId, companyId);
				if(mcaFinancialAndDetailsRes.getData()!=null) {
					corporateFinalViewResponse.setMcaFinancialAndDetailsResponse(mcaFinancialAndDetailsRes);
				}else {
					logger.info("::::::=====MCA Financial Data is Null====:::::::For:::::CompanyId==>{}   AppId==>{}", companyId ,toApplicationId);
				}
			} else {
				corporateFinalViewResponse.setMcaNotApplicable(Boolean.TRUE);
				logger.warn("Mca Company Id is Null");
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}

		// Name As Per ITR
		try {
			ITRConnectionResponse resNameAsPerITR = itrClient.getITRBasicDetails(toApplicationId);
			if (resNameAsPerITR != null) {
				corporateFinalViewResponse
						.setNameAsPerItr(resNameAsPerITR.getData() != null ? resNameAsPerITR.getData() : "NA");
			} else {
				logger.warn("-----------:::::::::::::: ItrResponse is null ::::::::::::---------");
			}
		} catch (Exception e) {
			logger.error(":::::::::::---------Error while fetching name as per itr----------:::::::::::",e);
		}

		// Name as per Gst
		try {

			/*if(corporateApplicantDetail.getGstIn()!= null) {*/
			CAMGSTData resp =null;
				GSTR1Request req= new GSTR1Request();
				req.setApplicationId(toApplicationId);
				req.setUserId(userId);
				req.setGstin(corporateApplicantDetail.getGstIn());
				GstResponse response = gstClient.detailCalculation(req);
				if (response != null) {
					
					DecimalFormat df = new DecimalFormat(".##");
					if (!CommonUtils.isObjectNullOrEmpty(response)) {
						for (LinkedHashMap<String, Object> data : (List<LinkedHashMap<String, Object>>) response.getData()) {
							resp = MultipleJSONObjectHelper.getObjectFromMap(data,CAMGSTData.class);
							Double totalSales =0.0d;
							if(resp.getMomSales() != null) {
								List<MomSales> momSalesResp = resp.getMomSales();
								for (MomSales sales : momSalesResp) {
									
									totalSales += Double.valueOf(sales.getValue());
								}
								data.put("totalMomSales", df.format(totalSales));
//							resp.setTotalMomSales(totalSales);
							}
						}
						
					corporateFinalViewResponse.setGstData((List<LinkedHashMap<String, Object>>) response.getData());
				} else {

					logger.warn("----------:::::::: Gst Response is null :::::::---------");

				}
			/*}else {
				logger.warn("gstIn is Null for in corporate Applicant Details=>>>>>"+toApplicationId);
			}*/

				}
		} catch (Exception e) {
			logger.error(":::::::------Error while calling gstData---:::::::",e);
		}

		
		
		// Fraud Detection Data

		try {
			UserResponse campaignUser=usersClient.isExists(userId,null);
			corporateFinalViewResponse.setIsCampaignUser(campaignUser!= null && campaignUser.getData()!= null ? (Boolean) campaignUser.getData() : null);
			if(campaignUser!= null && campaignUser.getData().equals(false)) {
				AnalyticsResponse hunterResp = fraudAnalyticsClient.getRuleAnalysisData(toApplicationId);
				if (!CommonUtils.isObjectListNull(hunterResp, hunterResp.getData())) {
					corporateFinalViewResponse.setFraudDetectionData(hunterResp);
				}else {
					logger.info("application is bank specific so fraud detection is skipped for===>"+toapplicationId);
				}	
			}
			
		} catch (Exception e1) {
			logger.error("------:::::...Error while fetching Fraud Detection Details...For..::::::-----", toApplicationId + CommonUtils.EXCEPTION + e1);
		}
		
		
		
		//ADMIN OFFICE ADDRESS
		CorporateFinalInfoRequest corporateFinalInfoRequest;
		try {
	/*		corporateFinalInfoRequest = corporateFinalInfoService.get(userId,toApplicationId);*/
			corporateFinalInfoRequest = corporateFinalInfoService.getByProposalId(userId,proposalMapId); // NEW BASED ON PROPOSAL ID--->
			if(corporateFinalInfoRequest != null && corporateFinalInfoRequest.getSecondAddress() != null && corporateFinalInfoRequest.getSecondAddress().getDistrictMappingId() != null) {
				PincodeDataResponse pindata=pincodeDateService.getById(corporateFinalInfoRequest.getSecondAddress().getDistrictMappingId());
				corporateFinalViewResponse.setAdminAddDist(pindata.getDistrictName());
				corporateFinalViewResponse.setAdminAddTaluko(pindata.getTaluka());
				pindata.getTaluka();
			}
			
			if(!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress())){	
				corporateFinalViewResponse.setAdminAdd( (corporateFinalInfoRequest.getSecondAddress().getPremiseNumber()!=null ? (CommonUtils.commaReplace(corporateFinalInfoRequest.getSecondAddress().getPremiseNumber())) :"") + (corporateFinalInfoRequest.getSecondAddress().getStreetName() != null ? (CommonUtils.commaReplace(corporateFinalInfoRequest.getSecondAddress().getStreetName())) : "") + (corporateFinalInfoRequest.getSecondAddress().getLandMark() != null ? (CommonUtils.commaReplace(corporateFinalInfoRequest.getSecondAddress().getLandMark())) : "")+ (corporateFinalViewResponse.getAdminAddDist() != null ?(CommonUtils.commaReplace(corporateFinalViewResponse.getAdminAddDist())) :"")+ (corporateFinalViewResponse.getAdminAddTaluko() != null ? (CommonUtils.commaReplace(corporateFinalViewResponse.getAdminAddTaluko())) : "") + (corporateFinalInfoRequest.getSecondAddress().getPincode() != null ? (corporateFinalInfoRequest.getSecondAddress().getPincode()) : ""));
			}
		}
		catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}	

		//Reg OFFICE ADDRESS
		try {
			/*corporateFinalInfoRequest = corporateFinalInfoService.get(userId,toApplicationId);*/
			corporateFinalInfoRequest = corporateFinalInfoService.getByProposalId(userId,proposalMapId);
			
			if(corporateFinalInfoRequest != null && corporateFinalInfoRequest.getFirstAddress() != null && corporateFinalInfoRequest.getFirstAddress().getDistrictMappingId() != null) {
				PincodeDataResponse pindata=pincodeDateService.getById(corporateFinalInfoRequest.getFirstAddress().getDistrictMappingId());
				corporateFinalViewResponse.setRegAddDist(pindata.getDistrictName());
				corporateFinalViewResponse.setRegAddTaluko(pindata.getTaluka());
				pindata.getTaluka();
			}
			
			if(!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress())){		
				corporateFinalViewResponse.setRegAdd( (corporateFinalInfoRequest.getFirstAddress().getPremiseNumber()!=null ? (CommonUtils.commaReplace(corporateFinalInfoRequest.getFirstAddress().getPremiseNumber())) :"") + (corporateFinalInfoRequest.getFirstAddress().getStreetName() != null ? (CommonUtils.commaReplace(corporateFinalInfoRequest.getFirstAddress().getStreetName())) : "") + (corporateFinalInfoRequest.getFirstAddress().getLandMark() != null ? (CommonUtils.commaReplace(corporateFinalInfoRequest.getFirstAddress().getLandMark())) : "")+ (corporateFinalViewResponse.getRegAddDist() != null ?(CommonUtils.commaReplace(corporateFinalViewResponse.getRegAddDist())) :"")+ (corporateFinalViewResponse.getRegAddTaluko() != null ? (CommonUtils.commaReplace(corporateFinalViewResponse.getRegAddTaluko())) : "") + (corporateFinalInfoRequest.getFirstAddress().getPincode() != null ? (corporateFinalInfoRequest.getFirstAddress().getPincode()) : ""));
			}
		}
		catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			}	

		
		// GET DOCUMENTS
		DocumentRequest documentRequest = new DocumentRequest();
		documentRequest.setApplicationId(toApplicationId);
		documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
		documentRequest.setProductDocumentMappingId(DocumentAlias.CIBIL_REPORT_MSME_COMPANY);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporateFinalViewResponse.setCibilReport(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_BANK_STATEMENT);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporateFinalViewResponse.setBankStatement(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		documentRequest.setProductDocumentMappingId(DocumentAlias.CORPORATE_ITR_PDF);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporateFinalViewResponse.setIrtPdfReport(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		documentRequest.setProductDocumentMappingId(DocumentAlias.CIBIL_REPORT_MSME_CONSUMER);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporateFinalViewResponse.setCibilConsumerReport(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}

		/*documentRequest.setProductDocumentMappingId(DocumentAlias.NBFC_PAN_UPLOAD);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporateFinalViewResponse.setNbfcPANReport(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		documentRequest.setProductDocumentMappingId(DocumentAlias.NBFC_ADDRESS_PROOF);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporateFinalViewResponse.setNbfcAddressProofReport(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}*/
		documentRequest.setProductDocumentMappingId(DocumentAlias.NBFC_TRIPARTITE_AGREEMENT);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporateFinalViewResponse.setNbfcTripartiteAgreement(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		documentRequest.setProductDocumentMappingId(DocumentAlias.NBFC_SANCTION_LETTER);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporateFinalViewResponse.setNbfcSanctionLetterOfNBFC(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		documentRequest.setProductDocumentMappingId(DocumentAlias.NBFC_PROMISSONRY_NOTE);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporateFinalViewResponse.setNbfcDemandPromissonryNote(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		documentRequest.setProductDocumentMappingId(DocumentAlias.NBFC_lETTER_OF_INTENT);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporateFinalViewResponse.setNbfcLetterOfIntent(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		documentRequest.setProductDocumentMappingId(DocumentAlias.NBFC_OTHER);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporateFinalViewResponse.setNbfcOther(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		/*documentRequest.setProductDocumentMappingId(DocumentAlias.NBFC_PROJECTED_FINANCIALS);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporateFinalViewResponse.setNbfcProjectedFinancials(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}*/
		documentRequest.setProductDocumentMappingId(DocumentAlias.AGE_PROOF_DOCUMENT);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporateFinalViewResponse.setAgeProofDocument(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}

		documentRequest.setProductDocumentMappingId(DocumentAlias.ID_PROOF_DOCUMENT);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporateFinalViewResponse.setIdProofDocument(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}

		documentRequest.setProductDocumentMappingId(DocumentAlias.ADDRESS_PROOF_DOCUMENT);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporateFinalViewResponse.setAddressProofDocument(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}

		documentRequest.setProductDocumentMappingId(DocumentAlias.APPLICATION_FORM);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporateFinalViewResponse.setApplicationForm(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}

		documentRequest.setProductDocumentMappingId(DocumentAlias.DETAILED_ASSESSMENT_NOTE);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporateFinalViewResponse.setDetailedAssessmentNote(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}


		/*DocumentRequest documentRequestForMCAZip = new DocumentRequest();
		documentRequestForMCAZip.setApplicationId(Long.valueOf(loanApplicationMaster.getMcaCompanyId()));
		documentRequestForMCAZip.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
		documentRequestForMCAZip.setProductDocumentMappingId(DocumentAlias.MCA_CORP_ZIP);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequestForMCAZip);
			corporateFinalViewResponse.setMcaCorpZipFile(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);

		}*/

		if (applicationProposalMapping.getProductId() == 1) {
			documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_LAST_AUDITED_ANNUAL_REPORT);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setAuditedAnnualReport(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_LAST_IT_RETURN);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setItr(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_BANK_STATEMENT);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setBankStatementFinalView(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_SANCTION_LETTER_COPY);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setSanctionLetter(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_PROVISIONAL_FINANCIALS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setProvisionalFinancials(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_NET_WORTH_STATEMENT_OF_DIRECTORS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setNetWorthStatements(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_FINANCIALS_OF_SUBSIDIARIES);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setFinancialsOfHolding(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_ASSESSMENT_ORDERS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setAssessmentOrders(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WC_MOM_AOA);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setMomAndAoa(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WC_GST_APPLIED);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setGstCertificate(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_CERTIFICATE_OF_INCORPORATION);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setCertificateOfIncorporation(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_COPY_OF_PAN_CARD);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setCopyOfPanCard(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(
					DocumentAlias.WORKING_CAPITAL_PAN_OF_DIRECTORS_CERTIFICATE_OF_INCORPORATION);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setPanOfAllDirectors(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_PHOTO_OF_DIRECTORS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setPhotosOfDirectors(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_DIRECTOR_ADDRESS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setResidenceAddOfDirectors(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId((long) DocumentAlias.WC_CMA);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setCmaList(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}

		}
		if (applicationProposalMapping.getProductId() == 2) {
			documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_LAST_AUDITED_ANNUAL_REPORT);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setAuditedAnnualReport(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_LAST_IT_RETURN);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setItr(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_BANK_STATEMENT);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setBankStatementFinalView(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_SANCTION_LETTER_COPY);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setSanctionLetter(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_PROVISIONAL_FINANCIALS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setProvisionalFinancials(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_NET_WORTH_STATEMENT_OF_DIRECTORS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setNetWorthStatements(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_FINANCIALS_OF_SUBSIDIARIES);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setFinancialsOfHolding(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_ASSESSMENT_ORDERS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setAssessmentOrders(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.TL_MOM_AOA);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setMomAndAoa(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.TL_GST_APPLIED);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setGstCertificate(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_CERTIFICATE_OF_INCORPORATION);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setCertificateOfIncorporation(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_COPY_OF_PAN_CARD);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setCopyOfPanCard(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest
					.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_PAN_OF_DIRECTORS_CERTIFICATE_OF_INCORPORATION);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setPanOfAllDirectors(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_PHOTO_OF_DIRECTORS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setPhotosOfDirectors(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_DIRECTOR_ADDRESS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setResidenceAddOfDirectors(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId((long) DocumentAlias.TL_CMA);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setCmaList(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
		}
		if (applicationProposalMapping.getProductId() == 15) {
			documentRequest.setProductDocumentMappingId(DocumentAlias.UNSECURED_LOAN_LAST_AUDITED_ANNUAL_REPORT);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setAuditedAnnualReport(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.UNSECURED_LOAN_LAST_IT_RETURN);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setItr(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.UNSECURED_LOAN_BANK_STATEMENT);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setBankStatementFinalView(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.UNSECURED_LOAN_SANCTION_LETTER_COPY);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setSanctionLetter(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.UNSECURED_LOAN_PROVISIONAL_FINANCIALS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setProvisionalFinancials(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.UNSECURED_LOAN_NET_WORTH_STATEMENT_OF_DIRECTORS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setNetWorthStatements(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.UNSECURED_LOAN_FINANCIALS_OF_SUBSIDIARIES);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setFinancialsOfHolding(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.UNSECURED_LOAN_ASSESSMENT_ORDERS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setAssessmentOrders(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.USL_MOM_AOA);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setMomAndAoa(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.USL_GST_APPLIED);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setGstCertificate(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.UNSECURED_LOAN_CERTIFICATE_OF_INCORPORATION);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setCertificateOfIncorporation(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.UNSECURED_LOAN_COPY_OF_PAN_CARD);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setCopyOfPanCard(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(
					DocumentAlias.UNSECURED_LOAN_PAN_OF_DIRECTORS_CERTIFICATE_OF_INCORPORATION);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setPanOfAllDirectors(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.UNSECURED_LOAN_PHOTO_OF_DIRECTORS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setPhotosOfDirectors(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.UNSECURED_LOAN_DIRECTOR_ADDRESS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setResidenceAddOfDirectors(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId((long) DocumentAlias.USL_CMA);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setCmaList(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
		}

		if (applicationProposalMapping.getProductId() == 16) {
			documentRequest.setProductDocumentMappingId(DocumentAlias.WCTL_LOAN_LAST_AUDITED_ANNUAL_REPORT);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setAuditedAnnualReport(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WCTL_LOAN_LAST_IT_RETURN);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setItr(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WCTL_LOAN_BANK_STATEMENT);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setBankStatementFinalView(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WCTL_LOAN_SANCTION_LETTER_COPY);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setSanctionLetter(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WCTL_LOAN_PROVISIONAL_FINANCIALS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setProvisionalFinancials(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WCTL_LOAN_NET_WORTH_STATEMENT_OF_DIRECTORS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setNetWorthStatements(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WCTL_LOAN_FINANCIALS_OF_SUBSIDIARIES);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setFinancialsOfHolding(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WCTL_LOAN_ASSESSMENT_ORDERS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setAssessmentOrders(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			/*
			 * documentRequest.setProductDocumentMappingId(DocumentAlias.USL_MOM_AOA); try{
			 * DocumentResponse documentResponse =
			 * dmsClient.listProductDocument(documentRequest);
			 * corporateFinalViewResponse.setMomAndAoa(documentResponse.getDataList()); }
			 * catch (DocumentException e) { logger.error(CommonUtils.EXCEPTION,e); }
			 */
			/*
			 * documentRequest.setProductDocumentMappingId(DocumentAlias.USL_GST_APPLIED);
			 * try{ DocumentResponse documentResponse =
			 * dmsClient.listProductDocument(documentRequest);
			 * corporateFinalViewResponse.setGstCertificate(documentResponse.getDataList());
			 * } catch (DocumentException e) { logger.error(CommonUtils.EXCEPTION,e); }
			 */
			documentRequest.setProductDocumentMappingId(DocumentAlias.WCTL_LOAN_CERTIFICATE_OF_INCORPORATION);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setCertificateOfIncorporation(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WCTL_LOAN_COPY_OF_PAN_CARD);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setCopyOfPanCard(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest
					.setProductDocumentMappingId(DocumentAlias.WCTL_LOAN_PAN_OF_DIRECTORS_CERTIFICATE_OF_INCORPORATION);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setPanOfAllDirectors(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WCTL_LOAN_PHOTO_OF_DIRECTORS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setPhotosOfDirectors(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId(DocumentAlias.WCTL_LOAN_DIRECTOR_ADDRESS);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setResidenceAddOfDirectors(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			documentRequest.setProductDocumentMappingId((long) DocumentAlias.WCTL_CMA);
			try {
				DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
				corporateFinalViewResponse.setCmaList(documentResponse.getDataList());
			} catch (DocumentException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
		}
		
		if(gstCompRelFlag) {
			LinkedHashMap<String, Object> gstVsItrVsBsComparision =primaryCorpService.gstVsItrVsBsComparision(toapplicationId, (FinancialInputRequest) corporateFinalViewResponse.getFinancialInputRequest() , gstId , itrId ,bsId);
			corporateFinalViewResponse.setBankComparisionData(gstVsItrVsBsComparision);
				
			Map<String, Object> gstRelatedPartyDetails = loanApplicationService.getGstRelatedPartyDetails(toapplicationId);
			corporateFinalViewResponse.setGstRelatedParty(gstRelatedPartyDetails);
		}
		

		return corporateFinalViewResponse;
	}

	public String convertValue(Double value) {
		return !CommonUtils.isObjectNullOrEmpty(value) ? decim.format(value) : "0";
	}

}
