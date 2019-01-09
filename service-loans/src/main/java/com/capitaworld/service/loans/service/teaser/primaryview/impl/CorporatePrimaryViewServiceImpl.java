package com.capitaworld.service.loans.service.teaser.primaryview.impl;

import java.io.IOException;
import java.text.DecimalFormat;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.api.eligibility.model.EligibililityRequest;
import com.capitaworld.api.eligibility.model.EligibilityResponse;
import com.capitaworld.client.eligibility.EligibilityClient;
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
import com.capitaworld.service.gst.GstResponse;
import com.capitaworld.service.gst.client.GstClient;
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
import com.capitaworld.service.loans.model.corporate.CorporateFinalInfoRequest;
import com.capitaworld.service.loans.model.teaser.primaryview.CorporatePrimaryViewResponse;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.repository.fundprovider.TermLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.WcTlLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.WorkingCapitalParameterRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.DirectorPersonalDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.capitaworld.service.loans.service.common.PincodeDateService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateFinalInfoService;
import com.capitaworld.service.loans.service.fundseeker.corporate.DirectorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.irr.IrrService;
import com.capitaworld.service.loans.service.teaser.primaryview.CorporatePrimaryViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.model.MatchDisplayResponse;
import com.capitaworld.service.matchengine.model.MatchRequest;
import com.capitaworld.service.mca.client.McaClient;
import com.capitaworld.service.mca.model.McaResponse;
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
	private DirectorPersonalDetailRepository dirPerRep;

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
	private ApplicationProposalMappingRepository applicationProposalMappingRepository;


	@Autowired
	private ProductMasterRepository productMasterRepository;

	DecimalFormat decim = new DecimalFormat("#,###.00");

	@Override
	public CorporatePrimaryViewResponse getCorporatePrimaryViewDetails(Long applicationId,Long proposalId, Integer userType,
			Long fundProviderUserId) {

		CorporatePrimaryViewResponse corporatePrimaryViewResponse = new CorporatePrimaryViewResponse();
		
		ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.findOne(proposalId); // NEW BASED ON PROPOSAL MAPPING ID 
		logger.info("===============>"+applicationProposalMapping.getApplicationId());
		Long toApplicationId = applicationProposalMapping.getApplicationId();

		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(toApplicationId);
		Long userId = loanApplicationMaster.getUserId();

		corporatePrimaryViewResponse.setProductId(loanApplicationMaster.getProductId());
		corporatePrimaryViewResponse.setApplicationType(loanApplicationMaster.getWcRenewalStatus() != null ? WcRenewalType.getById(loanApplicationMaster.getWcRenewalStatus()).getValue().toString() : "New" );
		/* ========= Matches Data ========== */
		if (userType != null && !(CommonUtils.UserType.FUND_SEEKER == userType) ) {
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
				.getByApplicationAndProposalIdAndUserId(userId,toApplicationId,proposalId); //NEW BASED ON PROPOSAL MAPPING ID=======> 
		
		// set value to response
		if (corporateApplicantDetail != null) {
			BeanUtils.copyProperties(corporateApplicantDetail, corporatePrimaryViewResponse);
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

			// set city
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
						corporatePrimaryViewResponse.setCity(masterResponse.getValue());
						corporatePrimaryViewResponse.setRegOfficeCity(masterResponse.getValue());
					} else {
						corporatePrimaryViewResponse.setCity("NA");
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
						corporatePrimaryViewResponse.setAddOfficeCity(masterResponse.getValue());

					} else {
						corporatePrimaryViewResponse.setCity("NA");
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
			}

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
						corporatePrimaryViewResponse.setState(masterResponse.getValue());
						corporatePrimaryViewResponse.setRegOfficestate(masterResponse.getValue());
					} else {
						corporatePrimaryViewResponse.setState("NA");
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
						corporatePrimaryViewResponse.setAddOfficestate(masterResponse.getValue());
					} else {
						corporatePrimaryViewResponse.setState("NA");
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
				}
			}
			// set country
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
						corporatePrimaryViewResponse.setCountry(masterResponse.getValue());
						corporatePrimaryViewResponse.setRegOfficecountry(masterResponse.getValue());
					} else {
						corporatePrimaryViewResponse.setCountry("NA");
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
						corporatePrimaryViewResponse.setAddOfficecountry(masterResponse.getValue());
					} else {
						corporatePrimaryViewResponse.setCountry("NA");
					}
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
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
				.getByApplicationAndUserId(toApplicationId, userId);
		if (primaryCorporateDetail != null) {
			// set value to response
			BeanUtils.copyProperties(primaryCorporateDetail, corporatePrimaryViewResponse);
			if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getCurrencyId())
					&& !CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getDenominationId())) {
				corporatePrimaryViewResponse
						.setCurrencyDenomination(Currency.getById(primaryCorporateDetail.getCurrencyId()).getValue()
								+ " in " + Denomination.getById(primaryCorporateDetail.getDenominationId()).getValue());
			}
			corporatePrimaryViewResponse.setLoanType(primaryCorporateDetail.getProductId() != null
					? LoanType.getById(primaryCorporateDetail.getProductId()).getValue()
					: null);
			corporatePrimaryViewResponse.setLoanAmount(
					primaryCorporateDetail.getAmount() != null ? String.valueOf(primaryCorporateDetail.getAmount())
							: null);
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
						logger.warn("----:::::: is main director is null ::::::-----For-----",toApplicationId);
					}
					
				} catch (Exception e) {
					logger.error("----:::::: error while get is main dir details ::::::-----For-----",toApplicationId + CommonUtils.EXCEPTION + e);
				}
			}
			
			corporatePrimaryViewResponse.setDirectorBackgroundDetailResponses(directorBackgroundDetailResponseList);
		
		} catch (Exception e) {
			logger.error("Problem to get Data of Director's Background {}", e);
		}

		// get value of Financial Arrangements and set in response
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
				financialArrangementsDetailResponse.setLcbgStatus(financialArrangementsDetailRequest.getLcBgStatus() != null ? LCBG_Status_SBI.getById(financialArrangementsDetailRequest.getLcBgStatus()).getValue().toString() : "-");
				// financialArrangementsDetailResponse.setFacilityNature(NatureFacility.getById(financialArrangementsDetailRequest.getFacilityNatureId()).getValue());
				// financialArrangementsDetailResponse.setAddress(financialArrangementsDetailRequest.getAddress());
				financialArrangementsDetailResponseList.add(financialArrangementsDetailResponse);
			}
			corporatePrimaryViewResponse
					.setFinancialArrangementsDetailResponseList(financialArrangementsDetailResponseList);

		} catch (Exception e) {
			logger.error("Problem to get Data of Financial Arrangements Details {}", e);
		}

		Long denomination = Denomination.getById(primaryCorporateDetail.getDenominationId()).getDigit();

		try {
		//	FinancialInputRequest financialInputRequest = irrService.cmaIrrMappingService(userId, toApplicationId, null,enomination); //=======>>>>PREVIOUS  
			FinancialInputRequest financialInputRequest = irrService.cmaIrrMappingService(userId, toApplicationId, null,
					denomination,proposalId); // CHANGES PROPOSAL ID NEW 

			logger.info("financialInputRequest.getYear()===>>>" + financialInputRequest.getYear());
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
			logger.info("Enter in get scoing data -----APPID-->" + toApplicationId + "-----FPProductId------->"
					+ fpProductMappingId);
			ScoringResponse scoringResponse = scoringClient.getScore(scoringRequest);
			if (!CommonUtils.isObjectNullOrEmpty(scoringResponse)
					&& !CommonUtils.isObjectNullOrEmpty(scoringResponse.getDataObject())) {
				logger.info("Find Data From Scoring ");
				ProposalScoreResponse proposalScoreResponse = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) scoringResponse.getDataObject(), ProposalScoreResponse.class);
				corporatePrimaryViewResponse.setDataList(scoringResponse.getDataList());
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

			int loanId = primaryCorporateDetail.getProductId();
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
			logger.info(" for eligibility appid============>>" + toApplicationId);

			try {

				EligibilityResponse eligibilityResp = eligibilityClient.corporateLoanData(eligibilityReq);
				corporatePrimaryViewResponse.setEligibilityDataObject(eligibilityResp.getData());

			} catch (Exception e1) {
				logger.error(CommonUtils.EXCEPTION,e1);
			}
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
			logger.info("mca comp id==>>" + companyId);

			if (companyId != null) {

				McaResponse mcaResponse = mcaClient.getCompanyDetailedData(companyId);
				McaResponse mcaStatusResponse = mcaClient.mcaStatusCheck(String.valueOf(toApplicationId), companyId);
				if (mcaStatusResponse != null) {
					corporatePrimaryViewResponse.setMcaCheckStatus(mcaStatusResponse.getData());
				} else {

					logger.warn("::::::=====MCA Check Status Data is Null====:::::::For:::::==>" + companyId);
				}

				if (!CommonUtils.isObjectNullOrEmpty(mcaResponse.getData())) {

					corporatePrimaryViewResponse.setMcaData(mcaResponse);

				} else {

					logger.warn("::::::=====MCA Data is Null====:::::::For:::::==>" + companyId);
				}

				/*McaResponse mcaFinancialAndDetailsRes=mcaClient.getCompanyFinancialCalcAndDetails(toApplicationId, companyId);
				if(mcaFinancialAndDetailsRes.getData()!=null) {
					corporatePrimaryViewResponse.setMcaFinancialAndDetailsResponse(mcaFinancialAndDetailsRes);
				}else {
					logger.info("::::::=====MCA Financial Data is Null====:::::::For:::::==>"+ companyId + " appId==>"+toApplicationId);
				}*/
			} else {
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
			GstResponse response = gstClient.detailCalculation(corporateApplicantDetail.getGstIn());
			if (response != null) {
				corporatePrimaryViewResponse.setGstData(response);
			} else {

				logger.warn("----------:::::::: Gst Response is null :::::::---------");

			}

		} catch (Exception e) {
			logger.error(":::::::------Error while calling gstData---:::::::",e);
		}

		// Fraud Detection Data

		/*try {
			AnalyticsResponse hunterResp = fraudAnalyticsClient.getRuleAnalysisData(toApplicationId);

			if (!CommonUtils.isObjectListNull(hunterResp, hunterResp.getData())) {

				corporatePrimaryViewResponse.setFraudDetectionData(hunterResp);

			}
		} catch (Exception e1) {
			logger.error("------:::::...Error while fetching Fraud Detection Details...For..::::::-----" + toApplicationId + CommonUtils.EXCEPTION + e1);
		}*/


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
			corporateFinalInfoRequest = corporateFinalInfoService.getByProposalId(userId,proposalId); //// NEW BASED ON PROPOSAL MAPPING ID 
			
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
					corporateFinalInfoRequest = corporateFinalInfoService.getByProposalId(userId,proposalId); // NEW BASED ON PROPOSAL MAPPING ID 
				//	logger.info("==================>"+corporateFinalInfoRequest.getFirstAddress());
					logger.info("==================>"+corporateFinalInfoRequest.getSecondAddress().getDistrictMappingId());
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
		documentRequest.setProductDocumentMappingId(DocumentAlias.CORPORATE_ITR_PDF);
		try {
			DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
			corporatePrimaryViewResponse.setIrtPdfReport(documentResponse.getDataList());
		} catch (DocumentException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		// documentRequest.setProductDocumentMappingId(DocumentAlias.ZIP_TEASER_VIEW);
		// try {
		// DocumentResponse documentResponse =
		// dmsClient.listProductDocument(documentRequest);
		// corporatePrimaryViewResponse.setZipBytes(documentResponse.getDataList());
		// } catch (DocumentException e) {
		// logger.error(CommonUtils.EXCEPTION,e);
		// }
		// List<Long> ids=new ArrayList<>();
		// ids.add(354l);
		// ids.add(358l);
		// ids.add(365l);
		// ids.add(406l);
		// ZipRequest zipRequest=new ZipRequest();
		// zipRequest.setApplicationId(toApplicationId);
		// zipRequest.setProductDocumentMappingIds(ids);
		// try {
		// DocumentResponse documentResponse=dmsClient.getGenerateZip(zipRequest);
		// corporatePrimaryViewResponse.setZipBytes(documentResponse.getData());
		// } catch (DocumentException e) {
		// logger.error(CommonUtils.EXCEPTION,e);
		// }

		return corporatePrimaryViewResponse;
	}

	public String convertValue(Double value) {
		return !CommonUtils.isObjectNullOrEmpty(value) ? decim.format(value) : "0";
	}
	
}
