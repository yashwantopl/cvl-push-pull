package com.capitaworld.service.loans.service.teaser.primaryview.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.capitaworld.service.loans.domain.colending.RecommendDetail;
import com.capitaworld.service.loans.repository.colending.RecommendDetailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.api.eligibility.model.EligibililityRequest;
import com.capitaworld.api.eligibility.model.EligibilityResponse;
import com.capitaworld.cibil.client.CIBILClient;
import com.capitaworld.client.eligibility.EligibilityClient;
import com.capitaworld.connect.client.ConnectClient;
import com.capitaworld.itr.api.model.ITRConnectionResponse;
import com.capitaworld.itr.client.ITRClient;
import com.capitaworld.service.analyzer.client.AnalyzerClient;
import com.capitaworld.service.analyzer.model.common.AnalyzerResponse;
import com.capitaworld.service.analyzer.model.common.Data;
import com.capitaworld.service.analyzer.model.common.ReportRequest;
import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.fraudanalytics.client.FraudAnalyticsClient;
import com.capitaworld.service.fraudanalytics.model.AnalyticsResponse;
import com.capitaworld.service.gst.GstResponse;
import com.capitaworld.service.gst.MomSales;
import com.capitaworld.service.gst.client.GstClient;
import com.capitaworld.service.gst.model.CAMGSTData;
import com.capitaworld.service.gst.util.DateComparator2;
import com.capitaworld.service.gst.yuva.request.GSTR1Request;
import com.capitaworld.service.loans.domain.fundprovider.NbfcProposalBlendedRate;
import com.capitaworld.service.loans.domain.fundprovider.ProposalDetails;
import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.WcTlParameter;
import com.capitaworld.service.loans.domain.fundprovider.WorkingCapitalParameter;
import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.DirectorPersonalDetailResponse;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailResponse;
import com.capitaworld.service.loans.model.PincodeDataResponse;
import com.capitaworld.service.loans.model.corporate.CollateralSecurityDetailRequest;
import com.capitaworld.service.loans.model.corporate.CorporateFinalInfoRequest;
import com.capitaworld.service.loans.model.teaser.primaryview.CorporatePrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.CorporatePrimaryViewResponseNbfc;
import com.capitaworld.service.loans.repository.fundprovider.NbfcProposalBlendedRateRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.fundprovider.TermLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.WcTlLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.WorkingCapitalParameterRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.capitaworld.service.loans.service.common.CommonService;
import com.capitaworld.service.loans.service.common.PincodeDateService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CollateralSecurityDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateFinalInfoService;
import com.capitaworld.service.loans.service.fundseeker.corporate.DirectorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.irr.IrrService;
import com.capitaworld.service.loans.service.teaser.primaryview.CorporatePrimaryViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.model.MatchDisplayResponse;
import com.capitaworld.service.matchengine.model.MatchRequest;
import com.capitaworld.service.mca.client.McaClient;
import com.capitaworld.service.mca.model.McaResponse;
import com.capitaworld.service.mca.model.verifyApi.VerifyAPIRequest;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.AssessedForITMst;
import com.capitaworld.service.oneform.enums.AssessmentOptionForFS;
import com.capitaworld.service.oneform.enums.CompetitionMst_SBI;
import com.capitaworld.service.oneform.enums.Constitution;
import com.capitaworld.service.oneform.enums.Currency;
import com.capitaworld.service.oneform.enums.Denomination;
import com.capitaworld.service.oneform.enums.DirectorRelationshipType;
import com.capitaworld.service.oneform.enums.EducationalStatusMst;
import com.capitaworld.service.oneform.enums.EstablishmentMonths;
import com.capitaworld.service.oneform.enums.FactoryPremiseMst;
import com.capitaworld.service.oneform.enums.Gender;
import com.capitaworld.service.oneform.enums.HaveLIMst;
import com.capitaworld.service.oneform.enums.KnowHowMst;
import com.capitaworld.service.oneform.enums.LCBG_Status_SBI;
import com.capitaworld.service.oneform.enums.LoanType;
import com.capitaworld.service.oneform.enums.MaritalStatusMst;
import com.capitaworld.service.oneform.enums.OwningHouseMst;
import com.capitaworld.service.oneform.enums.PurposeOfLoan;
import com.capitaworld.service.oneform.enums.ResidentStatusMst;
import com.capitaworld.service.oneform.enums.SpouseDetailMst;
import com.capitaworld.service.oneform.enums.Title;
import com.capitaworld.service.oneform.enums.VisuallyImpairedMst;
import com.capitaworld.service.oneform.enums.WcRenewalType;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.oneform.model.SectorIndustryModel;
import com.capitaworld.service.rating.model.FinancialInputRequest;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.scoring.exception.ScoringException;
import com.capitaworld.service.scoring.model.ProposalScoreResponse;
import com.capitaworld.service.scoring.model.ScoringRequest;
import com.capitaworld.service.scoring.model.ScoringResponse;
import com.capitaworld.service.thirdparty.model.CGTMSEDataResponse;
import com.capitaworld.service.thirdpaty.client.ThirdPartyClient;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;

@Service
@Transactional
public class CorporatePrimaryViewServiceImpl implements CorporatePrimaryViewService {

	private static final Logger logger = LoggerFactory.getLogger(CorporatePrimaryViewServiceImpl.class);

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
	private CorporateFinalInfoService corporateFinalInfoService;

	@Autowired
	private ProductMasterRepository productMasterRepository;

	@Autowired
	private ApplicationProposalMappingRepository applicationProposalMappingRepository;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private CollateralSecurityDetailService collateralSecurityDetailService;
	
	@Autowired
	private CIBILClient cibilClient;
	
	@Autowired
	private LoanApplicationService loanApplicationService;

	@Value("${capitaworld.gstdata.enable}")
	private Boolean gstCompRelFlag;
	
	@Autowired
	private ConnectClient connectClient;
	
    @Autowired
    ProposalDetailsRepository proposalDetailsRepository;
    
	@Autowired
	private NbfcProposalBlendedRateRepository nbfcProposalBlendedRateRepository;

	@Autowired
	private RecommendDetailRepository recommendDetailRepository;
    
	
	DecimalFormat decim = new DecimalFormat("#,###.00");

	@SuppressWarnings("unchecked")
	@Override
	public CorporatePrimaryViewResponse getCorporatePrimaryViewDetails(Long applicationId,Long proposalId, Integer userType,
			Long fundProviderUserId) {

	CorporatePrimaryViewResponse corporatePrimaryViewResponse = new CorporatePrimaryViewResponse();
	//for NBFC and Code
	   Boolean isNBFCFlow = applicationProposalMappingRepository.getNbfcUserValue(applicationId);
		if(isNBFCFlow) {
			CorporatePrimaryViewResponseNbfc nbfcData = getNbfcData(applicationId);
			corporatePrimaryViewResponse.setNbfcData(nbfcData);
			ProposalDetails proposalDetailsForBank = proposalDetailsRepository.findFirstByApplicationIdAndIsActiveAndNbfcFlowOrderByIdDesc(applicationId, true, 2);
			proposalId = proposalDetailsForBank.getId();

			RecommendDetail detail = recommendDetailRepository.getOneByApplicationId(applicationId);
			if (!CommonUtils.isObjectNullOrEmpty(detail)) {
				nbfcData.setRecommendedValue(detail.getValue());
				nbfcData.setRecommendedTenure(detail.getTenure());
				nbfcData.setRecommendedRoi(detail.getRoi());
				nbfcData.setRecommendedProcessingFee(detail.getProcessingFee());
				nbfcData.setRecommendedRemark(detail.getRemark());
			}

		}
	// END
		
		ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.findOne(proposalId); // NEW BASED ON PROPOSAL MAPPING ID
		logger.info("AppId===========>{}",applicationProposalMapping.getApplicationId());
		Long toApplicationId = applicationProposalMapping.getApplicationId(); // new
		Long toUserId = applicationProposalMapping.getUserId(); // new
		corporatePrimaryViewResponse.setCurrencyDenomination(applicationProposalMapping.getCurrencyId() != null ? Currency.getById(applicationProposalMapping.getCurrencyId()).getValue().toString() : "-");
		
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(toApplicationId);
		//Long userId = loanApplicationMaster.getUserId(); // previous

		//corporatePrimaryViewResponse.setProductId(loanApplicationMaster.getProductId()); //  previous
		corporatePrimaryViewResponse.setProductId(applicationProposalMapping.getProductId()); // new
		corporatePrimaryViewResponse.setApplicationType(loanApplicationMaster.getWcRenewalStatus() != null ? WcRenewalType.getById(loanApplicationMaster.getWcRenewalStatus()).getValue().toString() : "New" );
		/* ========= Matches Data ========== */
		if (userType != null && CommonUtils.UserType.FUND_SEEKER != userType ) {
			     // teaser
				// view
				// viwed by
				// fund
				// provider
				Long fpProductMappingId = null;
				try {

					UsersRequest usersRequest = new UsersRequest();
					usersRequest.setId(fundProviderUserId);
					UserResponse userResponse = usersClient.getLastAccessApplicant(usersRequest);
					fpProductMappingId = userResponse.getId();

				} catch (Exception e) {
					logger.error("error while fetching last access fp rpduct id for fund provider while fetching matches in teaser view : ",e);
				}
				try {
					MatchRequest matchRequest = new MatchRequest();
					matchRequest.setApplicationId(toApplicationId);
					matchRequest.setProductId(fpProductMappingId);
					MatchDisplayResponse matchResponse = matchEngineClient.displayMatchesOfCorporate(matchRequest);
					corporatePrimaryViewResponse.setMatchesList(matchResponse.getMatchDisplayObjectList());
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
		}

		// get details of CorporateApplicantDetail PREVIOUS
	/*	CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
				.getByApplicationAndUserId(userId, toApplicationId);*/ // PREVIOUS

		CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
				.getByApplicationAndProposalIdAndUserId(toUserId,toApplicationId,proposalId); //NEW BASED ON PROPOSAL MAPPING ID=======>
		
		 
		// set value to response
		if (corporateApplicantDetail != null) {
			BeanUtils.copyProperties(corporateApplicantDetail, corporatePrimaryViewResponse);
			corporatePrimaryViewResponse.setComercialPanNo(corporateApplicantDetail.getPanNo() != null ? corporateApplicantDetail.getPanNo() : "-");
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getConstitutionId()))
				corporatePrimaryViewResponse
						.setConstitution(Constitution.getById(corporateApplicantDetail.getConstitutionId()).getValue());
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getEstablishmentMonth()))
				corporatePrimaryViewResponse.setEstablishmentMonth(
						EstablishmentMonths.getById(corporateApplicantDetail.getEstablishmentMonth()).getValue());
			// set Establishment year
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
						corporatePrimaryViewResponse.setEstablishmentYear(masterResponse.getValue());
					} else {
						corporatePrimaryViewResponse.setEstablishmentYear("NA");
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
					corporatePrimaryViewResponse.setCity(cityName != null ? cityName : "NA");
					corporatePrimaryViewResponse.setRegOfficeCity(cityName);
					
					//set State
					corporatePrimaryViewResponse.setState(stateName != null ? stateName : "NA");
					corporatePrimaryViewResponse.setRegOfficestate(stateName);
					
					//set Country
					corporatePrimaryViewResponse.setCountry(countryName != null ? countryName : "NA");
					corporatePrimaryViewResponse.setRegOfficecountry(countryName);
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
					corporatePrimaryViewResponse.setAddOfficeCity(cityName != null ? cityName : "NA");
					
					//set State
					corporatePrimaryViewResponse.setAddOfficestate(stateName != null ? stateName : "NA");
					
					//set Country
					corporatePrimaryViewResponse.setAddOfficecountry(countryName != null ? countryName : "NA");
				}
			}
			
		

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
						corporatePrimaryViewResponse.setKeyVericalFunding(masterResponse.getValue());
					} else {
						corporatePrimaryViewResponse.setKeyVericalFunding("NA");
					}

				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
			}

			// key vertical sector
			List<Long> keyVerticalSectorId = new ArrayList<>();
			// getting sector id from mapping
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getKeyVerticalSector()))
				keyVerticalSectorId.add(corporateApplicantDetail.getKeyVerticalSector());

			try {
				OneFormResponse formResponse = oneFormClient
						.getIndustrySecByMappingId(corporateApplicantDetail.getKeyVerticalSector());
				// SectorIndustryModel oneResponseDataList = (SectorIndustryModel) formResponse
				// .getData();

				SectorIndustryModel sectorIndustryModel = MultipleJSONObjectHelper
						.getObjectFromMap((Map) formResponse.getData(), SectorIndustryModel.class);

				// get key vertical sector value
				OneFormResponse oneFormResponse = oneFormClient
						.getSectorById(Arrays.asList(sectorIndustryModel.getSectorId()));
				List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
						.getListData();
				if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
					MasterResponse masterResponse = MultipleJSONObjectHelper
							.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
					corporatePrimaryViewResponse.setKeyVericalSector(masterResponse.getValue());
				} else {
					corporatePrimaryViewResponse.setKeyVericalSector("NA");
				}
			} catch (Exception e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}

			// key vertical Subsector
			try {
				if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getKeyVerticalSubsector())) {
					OneFormResponse oneFormResponse = oneFormClient
							.getSubSecNameByMappingId(corporateApplicantDetail.getKeyVerticalSubsector());
					corporatePrimaryViewResponse.setKeyVericalSubsector((String) oneFormResponse.getData());
				}
			} catch (Exception e) {
				logger.error("error while getting key vertical sub-sector : ",e);
			}

		}
		// get value of working capital data
		PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateRepository
				.getByApplicationAndUserId(toApplicationId, toUserId);

		if (primaryCorporateDetail != null) {
			// set value to response
			BeanUtils.copyProperties(primaryCorporateDetail, corporatePrimaryViewResponse);
			if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getCurrencyId())
					&& !CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getDenominationId())) {
				corporatePrimaryViewResponse
						.setCurrencyDenomination(Currency.getById(primaryCorporateDetail.getCurrencyId()).getValue()
								+ " in " + Denomination.getById(primaryCorporateDetail.getDenominationId()).getValue());
			}
			/*corporatePrimaryViewResponse.setLoanType(primaryCorporateDetail.getProductId() != null
					? LoanType.getById(primaryCorporateDetail.getProductId()).getValue()
					: null);*/ // PREVIOUS

			corporatePrimaryViewResponse.setLoanType(applicationProposalMapping.getProductId() != null
					? LoanType.getById(applicationProposalMapping.getProductId()).getValue()
					: null); // NEW BASED ON PROPOSAL MAPPING DATABASE

			/*corporatePrimaryViewResponse.setLoanAmount(
					primaryCorporateDetail.getAmount() != null ? String.valueOf(primaryCorporateDetail.getAmount())
							: null);*/ //PREVIOUS

			corporatePrimaryViewResponse.setLoanAmount(
					applicationProposalMapping.getLoanAmount() != null ? String.valueOf(applicationProposalMapping.getLoanAmount())
							: null); // NEW PROPOSAL MAPPING ID BASED

			corporatePrimaryViewResponse.setGstIn(
					corporateApplicantDetail.getGstIn() != null ? String.valueOf(corporateApplicantDetail.getGstIn())
							: null);
			
			if(primaryCorporateDetail.getAssessmentId()!=null) {
				corporatePrimaryViewResponse.setPurposeOfLoan(primaryCorporateDetail.getPurposeOfLoanId() != null && primaryCorporateDetail.getPurposeOfLoanId()==1 ? AssessmentOptionForFS.getById(primaryCorporateDetail.getAssessmentId()).getValue().toString() : PurposeOfLoan.getById(primaryCorporateDetail.getPurposeOfLoanId()).getValue().toString());	
			}else {	
				logger.warn("assesment id is null so considered PurposeOfLoan enum.....");
				corporatePrimaryViewResponse.setPurposeOfLoan(
						CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getPurposeOfLoanId()) ? null
								: PurposeOfLoan.getById(primaryCorporateDetail.getPurposeOfLoanId()).getValue().toString());
			}
			
			corporatePrimaryViewResponse
					.setHaveCollateralSecurity(primaryCorporateDetail.getHaveCollateralSecurity() != null
							? String.valueOf(primaryCorporateDetail.getHaveCollateralSecurity())
							: null);
			corporatePrimaryViewResponse
					.setCollateralSecurityAmount(primaryCorporateDetail.getCollateralSecurityAmount() != null
							? String.valueOf(primaryCorporateDetail.getCollateralSecurityAmount())
							: null);
			corporatePrimaryViewResponse.setProductServiceDesc(primaryCorporateDetail.getProductServiceDescription());
			
			
			List<CollateralSecurityDetailRequest> collateralSecurityDetails = collateralSecurityDetailService.getData(applicationId);
			corporatePrimaryViewResponse.setCollateralSecurityDetails(collateralSecurityDetails);
			corporatePrimaryViewResponse.setPromotersContribution(primaryCorporateDetail.getPromoterContribution());
			
			// add additional Details 
			
			corporatePrimaryViewResponse.setCostOfMachinery(primaryCorporateDetail.getCostOfMachinery());
			corporatePrimaryViewResponse.setIncrementalTurnover(primaryCorporateDetail.getIncrementalTurnover());
			corporatePrimaryViewResponse.setIncrementalMargin(primaryCorporateDetail.getIncrementalMargin());
			
			corporatePrimaryViewResponse
					.setPromotersContributionPer(primaryCorporateDetail.getTotalAmtPercentage() != null
							? " (" + convertValue(primaryCorporateDetail.getTotalAmtPercentage()) + "%)"
							: null);

			corporatePrimaryViewResponse.setNpOrgId(loanApplicationMaster.getNpOrgId());
			// workingCapitalPrimaryViewResponse.setSharePriceFace(primaryWorkingCapitalLoanDetail.getSharePriceFace());
			// workingCapitalPrimaryViewResponse.setSharePriceMarket(primaryWorkingCapitalLoanDetail.getSharePriceMarket());
			if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getModifiedDate()))
				corporatePrimaryViewResponse.setDateOfProposal(primaryCorporateDetail.getModifiedDate() != null
						? CommonUtils.DATE_FORMAT.format(primaryCorporateDetail.getModifiedDate())
						: null);
			
			// other Details			
			corporatePrimaryViewResponse.setComercialOpDate(primaryCorporateDetail.getCommercialOperationDate());
			corporatePrimaryViewResponse.setFactoryPremise(primaryCorporateDetail.getFactoryPremise() != null ? FactoryPremiseMst.getById(primaryCorporateDetail.getFactoryPremise()).getValue().toString() : "-");
			corporatePrimaryViewResponse.setKnoHow(primaryCorporateDetail.getKnowHow() != null ? KnowHowMst.getById(primaryCorporateDetail.getKnowHow()).getValue().toString() : "-");
			corporatePrimaryViewResponse.setCompetition(primaryCorporateDetail.getCompetition()  != null ? CompetitionMst_SBI.getById(primaryCorporateDetail.getCompetition()).getValue().toString() : "-");
		}

		try {
			List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestList = directorBackgroundDetailsService
					.getDirectorBackgroundDetailList(toApplicationId, toUserId);
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
				directorBackgroundDetailResponse.setPanNo(directorBackgroundDetailRequest.getPanNo().toUpperCase());
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
							corporatePrimaryViewResponse.setCountry(masterResponse.getValue());
							corporatePrimaryViewResponse.setRegOfficecountry(masterResponse.getValue());
							
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
					logger.error("----:::::: error while get is main dir details ::::::-----For-----AppId==>{}  Error==>{}",toApplicationId , e);
				}
			}
			
			corporatePrimaryViewResponse.setDirectorBackgroundDetailResponses(directorBackgroundDetailResponseList);
		
		} catch (Exception e) {
			logger.error("Problem to get Data of Director's Background {}", e);
		}

		// get value of Financial Arrangements and set in response
		try {
			List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList = financialArrangementDetailsService
					.getFinancialArrangementDetailsList(toApplicationId, toUserId);
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
				financialArrangementsDetailResponse.setLcbgStatus(financialArrangementsDetailRequest.getLcBgStatus() != null ? LCBG_Status_SBI.getById(financialArrangementsDetailRequest.getLcBgStatus()).getValue().toString() : "-");
				// financialArrangementsDetailResponse.setFacilityNature(NatureFacility.getById(financialArrangementsDetailRequest.getFacilityNatureId()).getValue());
				// financialArrangementsDetailResponse.setAddress(financialArrangementsDetailRequest.getAddress());
				//financialArrangementsDetailResponse.setBuerauEmi(financialArrangementsDetailRequest.getBureauOrCalculatedEmi());
				financialArrangementsDetailResponse.setEmi(financialArrangementsDetailRequest.getEmi());
				financialArrangementsDetailResponse.setBuerauOutStanding(financialArrangementsDetailRequest.getBureauOutstandingAmount());
				financialArrangementsDetailResponse.setCollateralAmt(financialArrangementsDetailRequest.getCollateralSecurityAmount());
				
				financialArrangementsDetailResponseList.add(financialArrangementsDetailResponse);
			}
			corporatePrimaryViewResponse
					.setFinancialArrangementsDetailResponseList(financialArrangementsDetailResponseList);

		} catch (Exception e) {
			logger.error("Problem to get Data of Financial Arrangements Details {}", e);
		}

		/*get personal loan obligation od dir*/

		Double loanObligation=financialArrangementDetailsService.getTotalEmiOfAllDirByApplicationId(toApplicationId);
		corporatePrimaryViewResponse.setLoanObligation(loanObligation!= null ? loanObligation : 0);


		Long denomination = Denomination.getById(primaryCorporateDetail.getDenominationId()).getDigit();

		try {
		//	FinancialInputRequest financialInputRequest = irrService.cmaIrrMappingService(userId, toApplicationId, null,enomination); //=======>>>>PREVIOUS
			FinancialInputRequest financialInputRequest = irrService.cmaIrrMappingService(toUserId, toApplicationId, null,
					denomination,proposalId); // CHANGES PROPOSAL ID NEW

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
							+ (financialInputRequest.getOtherIncomeNeedTocCheckOpSy() != null ? financialInputRequest.getOtherIncomeNeedTocCheckOpSy() :0.0));
			financialInputRequest
					.setProfitAfterTaxTy(CommonUtils.substractNumbers(financialInputRequest.getProfitBeforeTaxTy(),
							financialInputRequest.getProvisionForTaxTy())
							+ (financialInputRequest.getOtherIncomeNeedTocCheckOpTy() != null ? financialInputRequest.getOtherIncomeNeedTocCheckOpTy() :0.0));
			if (financialInputRequest.getDividendPayOutFy() == 0)
				financialInputRequest.setEquityDividendFy(0.0);
			else
				financialInputRequest.setEquityDividendFy(financialInputRequest.getDividendPayOutFy()
						* financialInputRequest.getShareFaceValue() / financialInputRequest.getShareCapitalFy());
			if(financialInputRequest.getDividendPayOutSy()!=null) {
				if (financialInputRequest.getDividendPayOutSy() == 0)
					financialInputRequest.setEquityDividendSy(0.0);
				else
					financialInputRequest.setEquityDividendSy(financialInputRequest.getDividendPayOutSy()
							* financialInputRequest.getShareFaceValue() / financialInputRequest.getShareCapitalSy());	
			}else {
				financialInputRequest.setEquityDividendSy(0.0);
			}
			
			if( financialInputRequest.getDividendPayOutTy() !=null) {
			
				if (financialInputRequest.getDividendPayOutTy() == 0)
					financialInputRequest.setEquityDividendTy(0.0);
				else
					financialInputRequest.setEquityDividendTy(financialInputRequest.getDividendPayOutTy()
							* financialInputRequest.getShareFaceValue() / financialInputRequest.getShareCapitalTy());
				
			}else {
				financialInputRequest.setEquityDividendTy(0.0);	
			}
			

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
					financialInputRequest.getTotalCurruntAssetFy(), financialInputRequest.getTotalNonCurruntAssetFy(),
					financialInputRequest.getOtherIncomeNeedTocCheckAssetFy()));
			financialInputRequest.setTotalAssetSy(CommonUtils.addNumbers(financialInputRequest.getNetBlockSy(),
					financialInputRequest.getTotalCurruntAssetSy(), financialInputRequest.getTotalNonCurruntAssetSy(),
					financialInputRequest.getOtherIncomeNeedTocCheckAssetSy()));
			financialInputRequest.setTotalAssetTy(CommonUtils.addNumbers(financialInputRequest.getNetBlockTy(),
					financialInputRequest.getTotalCurruntAssetTy(), financialInputRequest.getTotalNonCurruntAssetTy(),
					financialInputRequest.getOtherIncomeNeedTocCheckAssetTy()));
			financialInputRequest.setBookValueFy(financialInputRequest.getShareHolderFundsFy()
					/ (financialInputRequest.getShareCapitalFy() / financialInputRequest.getShareFaceValue()));
			financialInputRequest.setBookValueSy(financialInputRequest.getShareHolderFundsSy()
					/ (financialInputRequest.getShareCapitalSy() / financialInputRequest.getShareFaceValue()));
			financialInputRequest.setBookValueTy(financialInputRequest.getShareHolderFundsTy()
					/ (financialInputRequest.getShareCapitalTy() / financialInputRequest.getShareFaceValue()));
			corporatePrimaryViewResponse.setFinancialInputRequest(financialInputRequest);

		} catch (Exception e) {
			logger.error("Error From Irr Side While Calculate Financial data .....",e);
		}
		
		/*get cmr score cibil */
		
		try {
				String cmrScore= cibilClient.getCMRScore(applicationId);
				if (cmrScore != null && cmrScore.contains("EXP")) {
					corporatePrimaryViewResponse.setMsmeRankTitle("Experian");
				}else if (cmrScore != null && cmrScore.contains("CIBIL")) {
					corporatePrimaryViewResponse.setMsmeRankTitle("Cibil");
				}else {
					corporatePrimaryViewResponse.setMsmeRankTitle("MSME Ranking");
				}
				corporatePrimaryViewResponse.setCibilCmrScore(cmrScore != null ? cmrScore : "Not Found");	
			
		} catch (Exception e) {
			logger.info("Exception while get CIBIL CMR Score {}",e);
		}
		

		// itr xml isUpload or Online check
		try {
			ITRConnectionResponse itrConnectionResponse = itrClient.getIsUploadAndYearDetails(toApplicationId);
			if (itrConnectionResponse != null) {
				corporatePrimaryViewResponse.setItrXmlIsUploaded(itrConnectionResponse.getData());
			} else {
				logger.info("itr Response is null");

			}

		} catch (Exception e) {
			logger.error("error while itr xml is uploaded or not check : ",e);
		}

		// bank statement data
		ReportRequest reportRequest = new ReportRequest();
		reportRequest.setApplicationId(toApplicationId);
		reportRequest.setUserId(toUserId);
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
			corporatePrimaryViewResponse.setBankData(datas);
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
			if(userResponse != null && userResponse.getId() != null) {

				fpProductMappingId = userResponse.getId();

			}else {

				logger.info("fpProduuct MappingId is Null..");
			}

		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		ScoringRequest scoringRequest = new ScoringRequest();
		scoringRequest.setApplicationId(toApplicationId);
		scoringRequest.setFpProductId(fpProductMappingId);
		try {
			logger.info("Enter in get scoing data -----APPID-->{}  --FPProductId-->{}", toApplicationId ,fpProductMappingId);
			ScoringResponse scoringResponse = scoringClient.getScore(scoringRequest);
			if (!CommonUtils.isObjectNullOrEmpty(scoringResponse)
					&& !CommonUtils.isObjectNullOrEmpty(scoringResponse.getDataObject())) {
				logger.info("Find Data From Scoring ");
				ProposalScoreResponse proposalScoreResponse = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) scoringResponse.getDataObject(), ProposalScoreResponse.class);
				corporatePrimaryViewResponse.setDataList(scoringResponse.getDataList());
				corporatePrimaryViewResponse.setScoringModelName(proposalScoreResponse.getScoringModelName() != null ? proposalScoreResponse.getScoringModelName() : "-");
				corporatePrimaryViewResponse.setManagementRiskScore(proposalScoreResponse.getManagementRiskScore());
				corporatePrimaryViewResponse.setFinancialRiskScore(proposalScoreResponse.getFinancialRiskScore());
				corporatePrimaryViewResponse.setBuisnessRiskScore(proposalScoreResponse.getBusinessRiskScore());
				corporatePrimaryViewResponse
						.setManagementRiskScoreWeight(proposalScoreResponse.getManagementRiskWeight());
				corporatePrimaryViewResponse
						.setFinancialRiskScoreWeight(proposalScoreResponse.getFinancialRiskWeight());
				corporatePrimaryViewResponse.setBuisnessRiskScoreWeight(proposalScoreResponse.getBusinessRiskWeight());
				corporatePrimaryViewResponse.setScoreInterpretation(proposalScoreResponse.getInterpretation());
				corporatePrimaryViewResponse
						.setManagementRiskMaxTotalScore(proposalScoreResponse.getManagementRiskMaxTotalScore());
				corporatePrimaryViewResponse
						.setFinancialRiskMaxTotalScore(proposalScoreResponse.getFinancialRiskMaxTotalScore());
				corporatePrimaryViewResponse
						.setBusinessRiskMaxTotalScore(proposalScoreResponse.getBusinessRiskMaxTotalScore());
				corporatePrimaryViewResponse
						.setManagementRiskWeightOfScoring(proposalScoreResponse.getManagementRiskWeightOfScoring());
				corporatePrimaryViewResponse
						.setFinancialRiskWeightOfScoring(proposalScoreResponse.getFinancialRiskWeightOfScoring());
				corporatePrimaryViewResponse
						.setBusinessRiskWeightOfScoring(proposalScoreResponse.getBusinessRiskWeightOfScoring());

				// if true so show 3 col

				corporatePrimaryViewResponse.setWeightConsider(proposalScoreResponse.getWeightConsider());
				corporatePrimaryViewResponse
						.setManagementRiskMaxTotalWeight(proposalScoreResponse.getManagementRiskMaxTotalWeight());
				corporatePrimaryViewResponse
						.setFinancialRiskMaxTotalWeight(proposalScoreResponse.getFinancialRiskMaxTotalWeight());
				corporatePrimaryViewResponse
						.setBusinessRiskMaxTotalWeight(proposalScoreResponse.getBusinessRiskMaxTotalWeight());

				// if ture so show two col
				corporatePrimaryViewResponse
						.setIsProportionateScoreConsider(proposalScoreResponse.getIsProportionateScoreConsider());// Score(out
																													// of),
																													// proportionateScoreFS
				corporatePrimaryViewResponse.setProportionateScore(proposalScoreResponse.getProportionateScore());
				corporatePrimaryViewResponse.setProportionateScoreFS(proposalScoreResponse.getProportionateScoreFS());

				logger.info("complete");
			} else {
				logger.info("SCORING OBJECT NULL OR EMPTY -------------------->");
			} 
		} catch (ScoringException | IOException e1) {
			logger.error(CommonUtils.EXCEPTION,e1);
		}

		// Eligibility Data


		try {

			//int loanId = primaryCorporateDetail.getProductId(); // previous
			int loanId = applicationProposalMapping.getProductId(); // NEW BASED ON PROPOAL MAPPING PRODUCT ID
			switch (loanId) {
			case 1:

				WorkingCapitalParameter workingCapitalPara = workingCapitalRepository.getByID(fpProductMappingId);
				if (workingCapitalPara.getAssessmentMethodId() != null) {
					Long assessmentId = workingCapitalPara.getAssessmentMethodId().longValue();
					corporatePrimaryViewResponse.setAssesmentId(assessmentId);
				} else {
					logger.info("assesment id is null in wc");
				}
				break;
			case 2:

				TermLoanParameter termLoanParameter = termLoanParameterRepository.getById(fpProductMappingId);
				if (termLoanParameter.getAssessmentMethodId() != null) {
					Long assessmentId = termLoanParameter.getAssessmentMethodId().longValue();
					corporatePrimaryViewResponse.setAssesmentId(assessmentId);
				} else {
					logger.info("assesment id is null tl");
				}
				break;

			case 16:

				WcTlParameter wctlPara = wctlrepo.getById(fpProductMappingId);
				if (wctlPara.getAssessmentMethodId() != null) {
					Long assessmentId = wctlPara.getAssessmentMethodId().longValue();
					corporatePrimaryViewResponse.setAssesmentId(assessmentId);
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
			eligibilityReq.setFpProductMappingId(fpProductMappingId);
			logger.info(" for eligibility appid============>>{}" , toApplicationId);

			try {

				EligibilityResponse eligibilityResp = eligibilityClient.corporateLoanData(eligibilityReq);
				corporatePrimaryViewResponse.setEligibilityDataObject(eligibilityResp.getData());

			} catch (Exception e1) {
				logger.error(CommonUtils.EXCEPTION,e1);
			}
			/*loan eligibility financial year  */
            corporatePrimaryViewResponse.setEligibilityFinancialYear(CommonUtils.getFinancialYear());

            // CGTMSE
			try {

				CGTMSEDataResponse cgtmseDataResp = thirdPartyClient.getCalulation(toApplicationId,fpProductMappingId);

				corporatePrimaryViewResponse.setCgtmseData(cgtmseDataResp);

			} catch (Exception e) {
				logger.error("Error while calling CGTMSE data : ",e);
			}

		} catch (Exception e) {
			logger.error("FpProductId is null : ",e);
		}



		// MCA DATA
		try {
			String companyId = loanApplicationMaster.getMcaCompanyId();
			logger.info("mca comp id==>>{}" , companyId);
			VerifyAPIRequest verifyReq=new VerifyAPIRequest();
			verifyReq.setApplicationId(toApplicationId);
			McaResponse directorData = mcaClient.getVerifyApiData(verifyReq);
			if(directorData!= null) {
				corporatePrimaryViewResponse.setVerifyApiData(directorData.getData());
			}
			if (companyId != null) {
				corporatePrimaryViewResponse.setMcaNotApplicable(Boolean.FALSE);
				McaResponse mcaResponse = mcaClient.getCompanyDetailedData(companyId);
				McaResponse mcaStatusResponse = mcaClient.mcaStatusCheck(String.valueOf(toApplicationId), companyId);
				if (mcaStatusResponse != null) {
					corporatePrimaryViewResponse.setMcaCheckStatus(mcaStatusResponse.getData());
				} else {

					logger.warn("::::::=====MCA Check Status Data is Null====:::::::For:::::CompanyId==>{}" , companyId);
				}

				if (!CommonUtils.isObjectNullOrEmpty(mcaResponse.getData())) {

					corporatePrimaryViewResponse.setMcaData(mcaResponse);

				} else {

					logger.warn("::::::=====MCA Data is Null====:::::::For:::::CompanyId==>{}" , companyId);
				}

				McaResponse mcaFinancialAndDetailsRes=mcaClient.getCompanyFinancialCalcAndDetails(toApplicationId, companyId);
				if(mcaFinancialAndDetailsRes.getData()!=null) {
					corporatePrimaryViewResponse.setMcaFinancialAndDetailsResponse(mcaFinancialAndDetailsRes);
				}else {
					logger.info("::::::=====MCA Financial Data is Null====:::::::For:::::CompanyId==>{}  AppId==>{}", companyId ,toApplicationId);
				}
			} else {
				corporatePrimaryViewResponse.setMcaNotApplicable(Boolean.TRUE);
				logger.warn("Mca Company Id is Null");

			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}

		// Name As Per

		try {
			ITRConnectionResponse resNameAsPerITR = itrClient.getITRBasicDetails(toApplicationId);
			if (resNameAsPerITR != null) {

				corporatePrimaryViewResponse
						.setNameAsPerItr(resNameAsPerITR.getData() != null ? resNameAsPerITR.getData() : "NA");
			} else {

				logger.warn("-----------:::::::::::::: ItrResponse is null ::::::::::::---------");
			}

		} catch (Exception e) {
			logger.error(":::::::::::---------Error while fetching name as per itr----------:::::::::::",e);
		}

		// Gst Data

		try {

			/*if(corporateApplicantDetail.getGstIn()!= null) {*/
			CAMGSTData resp =null;
				GSTR1Request req= new GSTR1Request();
				req.setApplicationId(toApplicationId);
				req.setUserId(toUserId);
				req.setGstin(corporateApplicantDetail.getGstIn());
				
				GstResponse response = gstClient.detailCalculation(req);
				/*GstResponse gstBankComp = gstClient.getGSTDataForBankStatmentComaparison(req);*/
				
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
						
					corporatePrimaryViewResponse.setGstData((List<LinkedHashMap<String, Object>>) response.getData());
				} else {

					logger.warn("----------:::::::: Gst Response is null :::::::---------");

				}
					/*if(gstBankComp!= null && gstBankComp.getData()!= null) {
						corporatePrimaryViewResponse.setGstBankComp(gstBankComp);
					}else {
						logger.info("gst bank comparison Data is null for====>>"+applicationId);
					}*/
			/*}else {
				logger.warn("gstIn is Null for in corporate Applicant Details=>>>>>"+toApplicationId);
			}*/

				}
		} catch (Exception e) {
			logger.error(":::::::------Error while calling gstData---:::::::",e);
		}

		// Fraud Detection Data

		try {
			UserResponse campaignUser=usersClient.isExists(toUserId,null);
			corporatePrimaryViewResponse.setIsCampaignUser(campaignUser!= null && campaignUser.getData()!= null ? (Boolean) campaignUser.getData() : null);
			if(campaignUser!= null && campaignUser.getData().equals(false)) {
				AnalyticsResponse hunterResp = fraudAnalyticsClient.getRuleAnalysisData(toApplicationId);
				if (!CommonUtils.isObjectListNull(hunterResp, hunterResp.getData())) {
					corporatePrimaryViewResponse.setFraudDetectionData(hunterResp);
				}else {
					logger.info("application is bank specific so fraud detection is skipped for===>"+applicationId);
				}	
			}
			
		} catch (Exception e1) {
			logger.error("------:::::...Error while fetching Fraud Detection Details...For..::::::-----" + toApplicationId + CommonUtils.EXCEPTION + e1);
		}


		// Product Name

		if(fpProductMappingId != null) {
			String productName = productMasterRepository.getFpProductName(fpProductMappingId);
			if(productName != null) {
				corporatePrimaryViewResponse.setFpProductName(productName);
			}else {
				logger.info("product name is null..");
			}
		}else {
			logger.info("fpProductMapping id is null..");
		}
		
		// address
		
		CorporateFinalInfoRequest corporateFinalInfoRequest;
		try {
			//corporateFinalInfoRequest = corporateFinalInfoService.get(userId,toApplicationId); // PREVIOUS CHANGES
			corporateFinalInfoRequest = corporateFinalInfoService.getByProposalId(toUserId,proposalId); //// NEW BASED ON PROPOSAL MAPPING ID

			//ADMIN OFFICE ADDRESS
			try {
				if(corporateFinalInfoRequest.getSecondAddress().getDistrictMappingId() != null) {
					
					PincodeDataResponse pindata=pincodeDateService.getById(corporateFinalInfoRequest.getSecondAddress().getDistrictMappingId());
					corporatePrimaryViewResponse.setAdminAddDist(pindata.getDistrictName());
					corporatePrimaryViewResponse.setAdminAddTaluko(pindata.getTaluka());
					pindata.getTaluka();
				}
			} catch (Exception e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			if(!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getSecondAddress())){
				
				corporatePrimaryViewResponse.setAdminAdd( (corporateFinalInfoRequest.getSecondAddress().getPremiseNumber()!=null ? (CommonUtils.commaReplace(corporateFinalInfoRequest.getSecondAddress().getPremiseNumber())) :"") + (corporateFinalInfoRequest.getSecondAddress().getStreetName() != null ? (CommonUtils.commaReplace(corporateFinalInfoRequest.getSecondAddress().getStreetName())) : "") + (corporateFinalInfoRequest.getSecondAddress().getLandMark() != null ? (CommonUtils.commaReplace(corporateFinalInfoRequest.getSecondAddress().getLandMark())) : "")+ (corporatePrimaryViewResponse.getAdminAddDist() != null ?(CommonUtils.commaReplace(corporatePrimaryViewResponse.getAdminAddDist())) :"")+ (corporatePrimaryViewResponse.getAdminAddTaluko() != null ? (CommonUtils.commaReplace(corporatePrimaryViewResponse.getAdminAddTaluko())) : "") + (corporateFinalInfoRequest.getSecondAddress().getPincode() != null ? (corporateFinalInfoRequest.getSecondAddress().getPincode()) : ""));
			}
		}
		catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			}	
			
		
		
		// address
		
				
				try {
					//corporateFinalInfoRequest = corporateFinalInfoService.get(userId,toApplicationId); // PREVIOUS
					corporateFinalInfoRequest = corporateFinalInfoService.getByProposalId(toUserId,proposalId); // NEW BASED ON PROPOSAL MAPPING ID
				//	logger.info("==================>"+corporateFinalInfoRequest.getFirstAddress());
					logger.info("==================>{}",corporateFinalInfoRequest.getSecondAddress().getDistrictMappingId());
					//Reg OFFICE ADDRESS
					try {
						if(corporateFinalInfoRequest.getSecondAddress().getDistrictMappingId() != null) {
							
							PincodeDataResponse pindata=pincodeDateService.getById(corporateFinalInfoRequest.getFirstAddress().getDistrictMappingId());
							corporatePrimaryViewResponse.setRegAddDist(pindata.getDistrictName());
							corporatePrimaryViewResponse.setRegAddTaluko(pindata.getTaluka());
							pindata.getTaluka();
						}
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
					if(!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getFirstAddress())){
						
						corporatePrimaryViewResponse.setRegAdd( (corporateFinalInfoRequest.getFirstAddress().getPremiseNumber()!=null ? (CommonUtils.commaReplace(corporateFinalInfoRequest.getFirstAddress().getPremiseNumber())) :"") + (corporateFinalInfoRequest.getFirstAddress().getStreetName() != null ? (CommonUtils.commaReplace(corporateFinalInfoRequest.getFirstAddress().getStreetName())) : "") + (corporateFinalInfoRequest.getFirstAddress().getLandMark() != null ? (CommonUtils.commaReplace(corporateFinalInfoRequest.getFirstAddress().getLandMark())) : "")+ (corporatePrimaryViewResponse.getRegAddDist() != null ?(CommonUtils.commaReplace(corporatePrimaryViewResponse.getRegAddDist())) :"")+ (corporatePrimaryViewResponse.getRegAddTaluko() != null ? (CommonUtils.commaReplace(corporatePrimaryViewResponse.getRegAddTaluko())) : "") + (corporateFinalInfoRequest.getFirstAddress().getPincode() != null ? (corporateFinalInfoRequest.getFirstAddress().getPincode()) : ""));
					}
				}
				catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
					}	
		

		// GET DOCUMENTS
		DocumentRequest documentRequest = new DocumentRequest();
		documentRequest.setApplicationId(toApplicationId);
		documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
		documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_PROFIEL_PICTURE);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporatePrimaryViewResponse.setProfilePic(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_BANK_STATEMENT);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporatePrimaryViewResponse.setBankStatement(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		documentRequest.setProductDocumentMappingId(DocumentAlias.CIBIL_REPORT_MSME_COMPANY);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporatePrimaryViewResponse.setCibilReport(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		documentRequest.setProductDocumentMappingId(DocumentAlias.CIBIL_REPORT_HUF_COMMERCIAL);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporatePrimaryViewResponse.setHufReport(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		documentRequest.setProductDocumentMappingId(DocumentAlias.CIBIL_REPORT_MSME_CONSUMER);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporatePrimaryViewResponse.setCibilConsumerReport(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		documentRequest.setProductDocumentMappingId(DocumentAlias.CORPORATE_ITR_PDF);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporatePrimaryViewResponse.setIrtPdfReport(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		
		if(gstCompRelFlag) {
			LinkedHashMap<String, Object> gstVsItrVsBsComparision = gstVsItrVsBsComparision(applicationId, (FinancialInputRequest) corporatePrimaryViewResponse.getFinancialInputRequest());
			corporatePrimaryViewResponse.setBankComparisionData(gstVsItrVsBsComparision);
				
			Map<String, Object> gstRelatedPartyDetails = loanApplicationService.getGstRelatedPartyDetails(applicationId);
			corporatePrimaryViewResponse.setGstRelatedParty(gstRelatedPartyDetails);
		}
		return corporatePrimaryViewResponse;
	}

//	STARTS HERE CO-ORIGIN CODE 
	private CorporatePrimaryViewResponseNbfc getNbfcData(Long applicationId)
	{
		CorporatePrimaryViewResponseNbfc corporatePrimaryView = new CorporatePrimaryViewResponseNbfc();
		    Boolean isNBFCFlow = applicationProposalMappingRepository.getNbfcUserValue(applicationId);
		    corporatePrimaryView.setCheckFlag(isNBFCFlow);
		    
		    NbfcProposalBlendedRate nbfcProposalBlendedRate = nbfcProposalBlendedRateRepository.getByApplicationIdOrderByIdDescLimit1(applicationId);
			if(!CommonUtils.isObjectNullOrEmpty(nbfcProposalBlendedRate)) {
				//Existing and Additional Loan Amount
				Long loanAmount = nbfcProposalBlendedRate.getElAmount() != null ? nbfcProposalBlendedRate.getElAmount().longValue() : null;
				Long existingLoanAmount = nbfcProposalBlendedRate.getExistingLoanAmount() != null ? nbfcProposalBlendedRate.getExistingLoanAmount().longValue() : 0l;
				Long additionalLoanAmount = nbfcProposalBlendedRate.getAdditionalLoanAmount() != null ? nbfcProposalBlendedRate.getAdditionalLoanAmount().longValue() : 0l;
				if(existingLoanAmount != 0 && additionalLoanAmount != 0) {
					loanAmount = existingLoanAmount + additionalLoanAmount;
				}
				corporatePrimaryView.setNbfcBlendedExistingLoanAmount(existingLoanAmount != 0 ? CommonUtils.convertValueWithoutDecimal(existingLoanAmount.doubleValue()) : "0");
				corporatePrimaryView.setNbfcBlendedAdditionalLoanAmount(additionalLoanAmount != 0 ? CommonUtils.convertValueWithoutDecimal(additionalLoanAmount.doubleValue()) : "0");
				corporatePrimaryView.setNbfcBlendedAmount(loanAmount != 0 ? CommonUtils.convertValueWithoutDecimal(loanAmount.doubleValue()) : "0");
				corporatePrimaryView.setNbfcBlendedRateOfInterest(nbfcProposalBlendedRate.getElRoi());
				corporatePrimaryView.setNbfcBlendedTenure(nbfcProposalBlendedRate.getElTenure());
				corporatePrimaryView.setNbfcBlendedEmiAmount(nbfcProposalBlendedRate.getEmi() != 0 ? CommonUtils.convertValueWithoutDecimal(nbfcProposalBlendedRate.getEmi().doubleValue()) : "0");
				corporatePrimaryView.setNbfcBlendedProcessingFees(nbfcProposalBlendedRate.getProcessingFee());
			}
			
			ProposalDetails proposalDetailsForNbfc = proposalDetailsRepository.findFirstByApplicationIdAndIsActiveAndNbfcFlowOrderByIdDesc(applicationId, true, 1);
			if(!CommonUtils.isObjectNullOrEmpty(proposalDetailsForNbfc)) {
 				Long loanAmount = proposalDetailsForNbfc.getElAmount() != null ? proposalDetailsForNbfc.getElAmount().longValue() : null;
				Long existingLoanAmount = proposalDetailsForNbfc.getExistingLoanAmount() != null ? proposalDetailsForNbfc.getExistingLoanAmount().longValue() : 0l;
				Long additionalLoanAmount = proposalDetailsForNbfc.getAdditionalLoanAmount() != null ? proposalDetailsForNbfc.getAdditionalLoanAmount().longValue() : 0l;
				if(existingLoanAmount != 0 && additionalLoanAmount != 0) {
					loanAmount = existingLoanAmount + additionalLoanAmount;
				}
				corporatePrimaryView.setNbfcExistingLoanAmount(existingLoanAmount != 0 ? CommonUtils.convertValueWithoutDecimal(existingLoanAmount.doubleValue()) : "0");
				corporatePrimaryView.setNbfcAdditionalLoanAmount(additionalLoanAmount != 0 ? CommonUtils.convertValueWithoutDecimal(additionalLoanAmount.doubleValue()) : "0");
				corporatePrimaryView.setNbfcAmount(loanAmount != 0 ? CommonUtils.convertValueWithoutDecimal(loanAmount.doubleValue()) : "0");
				corporatePrimaryView.setNbfcRateOfInterest(proposalDetailsForNbfc.getElRoi());
				corporatePrimaryView.setNbfcTenure(proposalDetailsForNbfc.getElTenure());
				corporatePrimaryView.setNbfcEmiAmount(proposalDetailsForNbfc.getEmi() != 0 ? CommonUtils.convertValueWithoutDecimal(proposalDetailsForNbfc.getEmi().doubleValue()) : "0");
				corporatePrimaryView.setNbfcProcessingFees(proposalDetailsForNbfc.getProcessingFee());
			}
			
			ProposalDetails proposalDetailsForBank = proposalDetailsRepository.findFirstByApplicationIdAndIsActiveAndNbfcFlowOrderByIdDesc(applicationId, true, 2);
			if(!CommonUtils.isObjectNullOrEmpty(proposalDetailsForBank)) {
				//Existing and Additional Loan Amount
				Long loanAmount = proposalDetailsForBank.getElAmount() != null ? proposalDetailsForBank.getElAmount().longValue() : null;
				Long existingLoanAmount = proposalDetailsForBank.getExistingLoanAmount() != null ? proposalDetailsForBank.getExistingLoanAmount().longValue() : 0l;
				Long additionalLoanAmount = proposalDetailsForBank.getAdditionalLoanAmount() != null ? proposalDetailsForBank.getAdditionalLoanAmount().longValue() : 0l;
				if(existingLoanAmount != 0 && additionalLoanAmount != 0) {
					loanAmount = existingLoanAmount + additionalLoanAmount;
				}
				corporatePrimaryView.setBankExistingLoanAmount(existingLoanAmount != 0 ? CommonUtils.convertValueWithoutDecimal(existingLoanAmount.doubleValue()) : "0");
				corporatePrimaryView.setBankAdditionalLoanAmount(additionalLoanAmount != 0 ? CommonUtils.convertValueWithoutDecimal(additionalLoanAmount.doubleValue()) : "0");
				corporatePrimaryView.setBankAmount(loanAmount != 0 ? CommonUtils.convertValueWithoutDecimal(loanAmount.doubleValue()) : "0");
				corporatePrimaryView.setBankRateOfInterest(proposalDetailsForBank.getElRoi());
				corporatePrimaryView.setBankTenure(proposalDetailsForBank.getElTenure());
				corporatePrimaryView.setBankEmiAmount(proposalDetailsForBank.getEmi() != 0 ? CommonUtils.convertValueWithoutDecimal(proposalDetailsForBank.getEmi().doubleValue()) : "0");
				corporatePrimaryView.setBankProcessingFees(proposalDetailsForBank.getProcessingFee() );
			}
			return corporatePrimaryView;
	}
//	ENDS HERE CO-ORIGIN CODE 


	@SuppressWarnings("unchecked")
	@Override
	public LinkedHashMap<String,Object> gstVsItrVsBsComparision(Long applicationId,FinancialInputRequest financialInputRequest) {
		LinkedHashMap<String,Object>comparisionData=new LinkedHashMap<>();
		GstResponse gstResp = null;
		Map<String,Object> bsMap=new HashMap<>();
		try {
			GSTR1Request request=new GSTR1Request();
			request.setApplicationId(applicationId);
			gstResp = gstClient.getbankComparisonData(request);
			ReportRequest requestReport = new ReportRequest(applicationId);
			bsMap  = (LinkedHashMap<String, Object>)analyzerClient.getDetailsByCategoryWise(requestReport).getData();
		}catch (Exception e) {
			logger.error("Exception in getting gst and BS data for teaserview {}",e);
		}
		SimpleDateFormat sdf=new SimpleDateFormat("MMyyyy");
		SimpleDateFormat sdf1=new SimpleDateFormat("MMMyy");
		SimpleDateFormat displayFormate=new SimpleDateFormat("MM-yyyy");
		// gst vs bank statement month wise purchase		
		if(bsMap != null && !bsMap.isEmpty() && gstResp != null && gstResp.getData() != null) {
			LinkedHashMap<String,Object> gstData= (LinkedHashMap<String, Object>) gstResp.getData();
			
			try {
					List<Map<String,Object>>bsData = new ArrayList<>();
					Double totalBsResipts = 0d;
					Double totalGstValue = 0d;
					Double bsValue= 0d;
					for (Map.Entry<String, Object> entry : bsMap.entrySet()) {
						Date parse = sdf1.parse(String.valueOf(entry.getKey()));
						HashMap<String,Object>gstSalesVsBankStatementMonthly = new HashMap();

						gstSalesVsBankStatementMonthly.put("month", displayFormate.format(parse));
						gstSalesVsBankStatementMonthly.put("gstValue", " - ");
						gstSalesVsBankStatementMonthly.put("bsDivededBygst", " - ");
						gstSalesVsBankStatementMonthly.put("bsValue", " - ");
						
						for (Map.Entry<String, Object> creditEntry : ((Map<String, Object>) entry.getValue()).entrySet()) {
							
							if(creditEntry != null && creditEntry.getValue() != null && creditEntry.getKey().equals("credit")) {
								 bsValue = Double.valueOf(String.valueOf(creditEntry.getValue()));
								 gstSalesVsBankStatementMonthly.put("bsValue", bsValue != null && bsValue != 0 ? CommonUtils.convertStringFormate(bsValue):"0");
								 totalBsResipts += bsValue;
								if(gstData != null && gstData.get("monthWiseTotalSales") != null) {
									for(Map.Entry<String, Object> monthWiseTotalSales: ((Map<String, Object>) gstData.get("monthWiseTotalSales")).entrySet()) {
										if(monthWiseTotalSales.getKey().equals(sdf.format(parse))) {
											 Double gstValue =Double.valueOf(String.valueOf(monthWiseTotalSales.getValue()));
											 gstSalesVsBankStatementMonthly.put("gstValue", monthWiseTotalSales.getValue() != null && gstValue  != 0 ? CommonUtils.convertValueIndianCurrency(gstValue):" - ");
											 totalGstValue += gstValue;
											 Double bsDividedByGst = gstValue != null && gstValue != 0 ?((bsValue/gstValue)*100):0;
											 gstSalesVsBankStatementMonthly.put("bsDivededBygst",bsDividedByGst != null && bsDividedByGst != 0 ?CommonUtils.convertValueIndianCurrency(bsDividedByGst).toString().concat(" %") : " - ");
										}
									}
								}
							}
						}
						bsData.add(gstSalesVsBankStatementMonthly);
					}
					comparisionData.put("gstVsBsSalesMonthly", !bsData.isEmpty()?bsData:null);
					comparisionData.put("totalGstValueForGstVsBsSalesMonthly", CommonUtils.convertValueIndianCurrency(totalGstValue));
					comparisionData.put("totalBsResiptsForGstVsBsSalesMonthly", CommonUtils.convertValueIndianCurrency(totalBsResipts));
					if(totalBsResipts != null && totalGstValue != 0 ) {
						Double totalGstVsBsTotalValues= totalBsResipts/totalGstValue*100;
						comparisionData.put("totalBsVsGstValueForGstVsBsSalesMonthly", totalGstVsBsTotalValues != null ?convertValue(totalGstVsBsTotalValues).concat(" %") : " - ");
					}else {
						comparisionData.put("totalBsVsGstValueForGstVsBsSalesMonthly", " - ");
					}
			}catch (Exception e) {
				logger.error("exception in getting the details of gstSalesVsBankStatementMonthly {}",e);
			}
		//	for monthly wise purchase gst vs bankstatement
			try {
				Double totalBsResipts = 0d;
				Double totalGstValue = 0d;
				List<LinkedHashMap<String,Object>>bsPurchaseData = new ArrayList<>();
				
				for (Map.Entry<String, Object> bsMapEntry : bsMap.entrySet()) {
					if(bsMapEntry != null) {
						Date parse = sdf1.parse(String.valueOf(bsMapEntry.getKey()));
						
						LinkedHashMap<String,Object>gstPurchaseVsBankStatementMonthly = new LinkedHashMap<>(); 
						
						gstPurchaseVsBankStatementMonthly.put("month", displayFormate.format(parse));
						gstPurchaseVsBankStatementMonthly.put("bsValue", " - ");
						for (Map.Entry<String, Object> debitEntry : ((Map<String, Object>) bsMapEntry.getValue()).entrySet()) {
							 if(debitEntry != null && debitEntry.getKey() != null  && debitEntry.getValue() != null && debitEntry.getKey().equals("debit")) {
								gstPurchaseVsBankStatementMonthly.put("bsDivededBygst", " - ");
								gstPurchaseVsBankStatementMonthly.put("gstValue", " - ");
		
								 Double bsValue = Double.valueOf(debitEntry.getValue().toString());
								 gstPurchaseVsBankStatementMonthly.put("bsValue", bsValue != null && bsValue != 0 ? CommonUtils.convertValueIndianCurrency(bsValue):"0");
								 totalBsResipts +=bsValue;
								 
								for (Map.Entry<String ,Object> entry: ((Map<String, Object>) gstData.get("monthWisePurchase")).entrySet()) {
									 if(bsMapEntry != null && entry.getKey().equals(sdf.format(parse))) {
										 Double gstValue =Double.valueOf(String.valueOf(entry.getValue()));
										gstPurchaseVsBankStatementMonthly.put("gstValue", gstValue != null && gstValue != 0 ? CommonUtils.convertValueIndianCurrency(gstValue):" - " );
										totalGstValue += gstValue;
								
										Double bsDividedByGst = gstValue != null && gstValue != 0 ?((bsValue/gstValue)*100):0;
										gstPurchaseVsBankStatementMonthly.put("bsDivededBygst",bsDividedByGst != 0 ?CommonUtils.convertValueIndianCurrency(bsDividedByGst).toString().concat(" %") : " - ");
									 }
								}
							 }
						 }
						bsPurchaseData.add(gstPurchaseVsBankStatementMonthly);
					}
				}
				comparisionData.put("gstVsBsPurchaseMonthly", !bsPurchaseData.isEmpty()?bsPurchaseData:null);
				comparisionData.put("gstVsBsPurchaseTotalGstValueMonthly", totalGstValue != null && totalGstValue !=0 ?CommonUtils.convertValueIndianCurrency(totalGstValue):" - ");
				comparisionData.put("gstVsBsPurchaseTotalBsResiptsMonthly", totalBsResipts != null && totalBsResipts != 0? CommonUtils.convertValueIndianCurrency(totalBsResipts) :" - ");
				if(totalGstValue != null && totalGstValue != 0) {
					Double total = totalBsResipts/totalGstValue*100;
					comparisionData.put("gstVsBsPurchaseTotalReciptsToSales", total != null && total != 0?CommonUtils.convertValueIndianCurrency(total).toString().concat(" %"):" - ");
				}else {
					comparisionData.put("gstVsBsPurchaseTotalReciptsToSales", " - ");
				}
			}catch (Exception e) {
				logger.error("Exception in getting gst vs bankstatement purchase : {}",e);
			}
			
		// GSTVsITR Yearly Sales 
			try {
				if(gstData != null && gstData.get("yearWiseTotalSales") != null) {
				List<LinkedHashMap<String,Object>>itrSalesData = new ArrayList<>();
				Double totalOfGstSalesTotal = 0d;
				Double totalOfITRSalesTotal = 0d;
				Double totalOfGstToItr = 0d;
				
				for (Map.Entry<String, Object> yearWiseSales: ((Map<String, Object>) gstData.get("yearWiseTotalSales")).entrySet()) {
					if(yearWiseSales != null) {
				
					for (Map<String, Object> itrSales:  financialInputRequest.getYearSalesPurchasList()) {
						if(itrSales != null &&  !String.valueOf(Integer.valueOf(itrSales.get("year").toString())-1).equals("2017") && String.valueOf(Integer.valueOf(itrSales.get("year").toString())-1).equals(yearWiseSales.getKey().split("-")[0])) {
						
							LinkedHashMap<String,Object>gstPurchaseVsBankStatementMonthly = new LinkedHashMap<>(); 
							gstPurchaseVsBankStatementMonthly.put("year", yearWiseSales.getKey() != null ? yearWiseSales.getKey() : " - ");
							gstPurchaseVsBankStatementMonthly.put("gstSalesTotal",CommonUtils.convertValueIndianCurrency(yearWiseSales.getValue()));
							gstPurchaseVsBankStatementMonthly.put("gstToItr"," - ");
							Double totalOfGst=Double.valueOf(String.valueOf(yearWiseSales.getValue()));
							gstPurchaseVsBankStatementMonthly.put("itrSales", itrSales.get("itrSales") != null && Double.valueOf(String.valueOf(itrSales.get("itrSales"))) != 0 && !itrSales.get("itrSales").equals(" - ")?CommonUtils.convertValueIndianCurrency(itrSales.get("itrSales")):" - ");
							Double gstToItr = 0d;
							if(itrSales.get("itrSales") != null && Double.valueOf(itrSales.get("itrSales").toString()) != 0) {
								gstToItr = totalOfGst/Double.valueOf(itrSales.get("itrSales").toString()) * 100;
							}
							gstPurchaseVsBankStatementMonthly.put("gstToItr",gstToItr != 0?convertValue(gstToItr) + " %":" - ");
							
							// calculating total
							totalOfGstSalesTotal += totalOfGst; 
							totalOfITRSalesTotal += itrSales.get("itrSales") != null && !itrSales.get("itrSales").toString().equals("0") && !itrSales.get("itrSales").toString().equals("-")?Double.valueOf(itrSales.get("itrSales").toString()):0;
	
							if(!gstPurchaseVsBankStatementMonthly.isEmpty()) {
								itrSalesData.add(gstPurchaseVsBankStatementMonthly);
							}
						}
					}
				}
			
				if(totalOfITRSalesTotal != 0) {
					totalOfGstToItr = totalOfGstSalesTotal/totalOfITRSalesTotal*100;
				}
			 }
				comparisionData.put("gstVsItrYearlySales", !itrSalesData.isEmpty()?itrSalesData:null);
				comparisionData.put("gstVsItrYearlySalesTotalOfGstSalesTotal", totalOfGstSalesTotal !=0 ?CommonUtils.convertValueIndianCurrency(totalOfGstSalesTotal): " - ");
				comparisionData.put("gstVsItrYearlySalesTotalOfITRSalesTotal", totalOfITRSalesTotal!=0?CommonUtils.convertValueIndianCurrency(totalOfITRSalesTotal): " - ");
				comparisionData.put("gstVsItrYearlySalesTotalOfGstToItr", totalOfGstToItr!=0?CommonUtils.convertValueIndianCurrency(totalOfGstToItr).toString().concat(" %"): " - ");
			}
			}catch (Exception e) {
				logger.error("Exception in getting value of gstVsItrYearlySales {}",e);
			}
			// GSTVsITR Yearly Purchase 				
				if(gstData != null && gstData.get("yearWisePurchase") != null) {
				try{
					Double totalOfGstPurchase = 0d;
					Double totalOfITRPurchase = 0d;
					Double totalOfGstToItr = 0d;
				List<LinkedHashMap<String,Object>>bsPurchaseData = new ArrayList<>();
				((Map<String, Object>) gstData.get("yearWisePurchase")).entrySet().stream().sorted(new DateComparator2());
				for(Map.Entry<String, Object> y:((Map<String, Object>) gstData.get("yearWisePurchase")).entrySet()) {
					
					if(financialInputRequest.getYearSalesPurchasList() !=null  && !financialInputRequest.getYearSalesPurchasList().isEmpty()) {
						financialInputRequest.getYearSalesPurchasList().stream().sorted(new DateComparator2());		
						for(Map<String, Object> fi: financialInputRequest.getYearSalesPurchasList()) {
							if(fi != null && !String.valueOf(Integer.valueOf(fi.get("year").toString())-1).equals("2017") && String.valueOf(Integer.valueOf(fi.get("year").toString())-1).split("-")[0].contains(String.valueOf(fi.get("year")))) {
								LinkedHashMap<String,Object>gstPurchaseVsBankStatementMonthly = new LinkedHashMap<>();
								gstPurchaseVsBankStatementMonthly.put("year", y.getKey() != null ?y.getKey().toString() : " - ");
								gstPurchaseVsBankStatementMonthly.put("gstPurchase", y.getValue()!= null && y.getValue().toString() != "0" ?CommonUtils.convertStringFormate(y.getValue()).toString() : " - ");
								totalOfGstPurchase += Double.valueOf(y.getValue().toString());
								
								gstPurchaseVsBankStatementMonthly.put("itrPurchase", fi.get("rowMaterialIndigenous") != null && Double.valueOf(fi.get("rowMaterialIndigenous").toString()) != 0?CommonUtils.convertStringFormate(fi.get("rowMaterialIndigenous").toString()):" - ");
								
								totalOfITRPurchase += fi.get("rowMaterialIndigenous") != null ?Double.valueOf(fi.get("rowMaterialIndigenous").toString()):0;
								Double gstToItr = 0d;
								if(fi.get("rowMaterialIndigenous") != null && y.getValue() != null && Double.valueOf(fi.get("rowMaterialIndigenous").toString()) != 0) {
									gstToItr = (Double.valueOf(fi.get("rowMaterialIndigenous").toString())/Double.valueOf(y.getValue().toString()) * 100);
								}
								gstPurchaseVsBankStatementMonthly.put("gstToItr",gstToItr != 0? convertValue(gstToItr) + " %":" - ");
								
								if(!gstPurchaseVsBankStatementMonthly.isEmpty()) {
									bsPurchaseData.add(gstPurchaseVsBankStatementMonthly);
								}
							}
						}
						
					}
					
				}
				if(totalOfITRPurchase != 0) {
					totalOfGstToItr = totalOfGstPurchase/totalOfITRPurchase*100;
				}
				comparisionData.put("gstVsItrYearlyPurchase", !bsPurchaseData.isEmpty()?bsPurchaseData:null);
				comparisionData.put("gstVsItrYearlyPurchaseTotalOfGstPurchase", totalOfGstPurchase!= 0?CommonUtils.convertValueIndianCurrency(totalOfGstPurchase): " - ");
				comparisionData.put("gstVsItrYearlyPurchaseTotalOfITRPurchase", totalOfITRPurchase !=0 ?CommonUtils.convertValueIndianCurrency(totalOfITRPurchase): " - ");
				comparisionData.put("gstVsItrYearlyPurchaseTotalOfGstToItr", totalOfGstToItr!=0?CommonUtils.convertValueIndianCurrency(totalOfGstToItr).toString().concat(" %"): " - ");
				}catch (Exception e) {
					logger.error("Exception in getting value of gstVsItrYearlyPurchase {}",e);
				}
			}
		}else {
			logger.error("Response not found in bankstatement  for bank comparision dispalay for :{}",applicationId);
		}
		
		return comparisionData;
	}

	public String convertValue(Double value) {
		return !CommonUtils.isObjectNullOrEmpty(value) ? decim.format(value) : "0";
	}
}
