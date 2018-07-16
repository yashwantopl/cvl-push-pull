/**
 * 
 */
package com.capitaworld.service.loans.service.teaser.finalview.impl;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.api.eligibility.model.EligibililityRequest;
import com.capitaworld.api.eligibility.model.EligibilityResponse;
import com.capitaworld.client.eligibility.EligibilityClient;
import com.capitaworld.service.analyzer.client.AnalyzerClient;
import com.capitaworld.service.analyzer.model.common.AnalyzerResponse;
import com.capitaworld.service.analyzer.model.common.Data;
import com.capitaworld.service.analyzer.model.common.ReportRequest;
import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailResponse;
import com.capitaworld.service.loans.model.teaser.finalview.NtbFinalViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SectorIndustryMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SubSectorMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SubSectorRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.AchievmentDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.AssociatedConcernDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateFinalInfoService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateMcqService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CreditRatingOrganizationDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.DirectorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ExistingProductDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinanceMeansDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.GuarantorsCorporateDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.MonthlyTurnoverDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.OwnershipDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.PromotorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ProposedProductDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.SecurityCorporateDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.TotalCostOfProjectService;
import com.capitaworld.service.loans.service.irr.IrrService;
import com.capitaworld.service.loans.service.teaser.finalview.NtbFinalViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.impl.CorporatePrimaryViewServiceImpl;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.model.MatchDisplayResponse;
import com.capitaworld.service.matchengine.model.MatchRequest;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.Constitution;
import com.capitaworld.service.oneform.enums.Currency;
import com.capitaworld.service.oneform.enums.Denomination;
import com.capitaworld.service.oneform.enums.DirectorRelationshipType;
import com.capitaworld.service.oneform.enums.EducationQualificationNTB;
import com.capitaworld.service.oneform.enums.EstablishmentMonths;
import com.capitaworld.service.oneform.enums.Gender;
import com.capitaworld.service.oneform.enums.LoanType;
import com.capitaworld.service.oneform.enums.MaritalStatus;
import com.capitaworld.service.oneform.enums.PurposeOfLoan;
import com.capitaworld.service.oneform.enums.Title;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.oneform.model.SectorIndustryModel;
import com.capitaworld.service.scoring.ScoringClient;
import com.capitaworld.service.scoring.exception.ScoringException;
import com.capitaworld.service.scoring.model.ProposalScoreResponse;
import com.capitaworld.service.scoring.model.ScoringRequest;
import com.capitaworld.service.scoring.model.ScoringResponse;
import com.capitaworld.service.thirdpaty.client.ThirdPartyClient;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;

/**
 * @author nilay
 *
 */
@Service
@Transactional
public class NtbFinalViewServiceImpl implements NtbFinalViewService{

	
	private static final Logger logger = LoggerFactory.getLogger(CorporatePrimaryViewServiceImpl.class);

	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private PrimaryCorporateDetailRepository primaryCorporateRepository;

	@Autowired
	private IndustrySectorRepository industrySectorRepository;

	@Autowired
	private SubSectorRepository subSectorRepository;

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
	private SectorIndustryMappingRepository sectorIndustryMappingRepository;

	@Autowired
	private SubSectorMappingRepository subSectorMappingRepository;

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

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	DecimalFormat decim = new DecimalFormat("#,###.00");
	
	@Override
	public NtbFinalViewResponse getNtbFinalViewDetails(Long toApplicationId, Integer userType,
			Long fundProviderUserId) {
		
		NtbFinalViewResponse ntbFinalViewResponse = new NtbFinalViewResponse();
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(toApplicationId);
		Long userId = loanApplicationMaster.getUserId();

		ntbFinalViewResponse.setProductId(loanApplicationMaster.getProductId());
		// ===================== MATCHES DATA ======================//
		if (userType != null) {
			if (!(CommonUtils.UserType.FUND_SEEKER == userType)) { // TEASER VIEW FROM FP
				Long fpProductMappingId = null;
				try {
					UsersRequest usersRequest = new UsersRequest();
					usersRequest.setId(fundProviderUserId);
					UserResponse userResponse = usersClient.getLastAccessApplicant(usersRequest);
					fpProductMappingId = userResponse.getId();
				} catch (Exception e) {
					logger.error(
							"error while fetching last access fp product id for fund provider while fetching matches in teaser view");
					e.printStackTrace();
				}
				try {
					MatchRequest matchRequest = new MatchRequest();
					matchRequest.setApplicationId(toApplicationId);
					matchRequest.setProductId(fpProductMappingId);
					MatchDisplayResponse matchResponse = matchEngineClient.displayMatchesOfCorporate(matchRequest);
					ntbFinalViewResponse.setMatchesList(matchResponse.getMatchDisplayObjectList());
				} catch (Exception e) {
					logger.error("Error while getting matches data for final teaser view");
					e.printStackTrace();
				}
			}
		}
		// GET CORPORATE APPLICANT DETAILS
		CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
				.getByApplicationAndUserId(userId, toApplicationId);
		// SET VALUE TO RESPONSE
		if (corporateApplicantDetail != null) {
			BeanUtils.copyProperties(corporateApplicantDetail, ntbFinalViewResponse);
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getConstitutionId()))
				ntbFinalViewResponse
						.setConstitution(Constitution.getById(corporateApplicantDetail.getConstitutionId()).getValue());
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getEstablishmentMonth()))
				ntbFinalViewResponse.setEstablishmentMonth(
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
						ntbFinalViewResponse.setEstablishmentYear(masterResponse.getValue());
					} else {
						ntbFinalViewResponse.setEstablishmentYear("NA");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

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
						ntbFinalViewResponse.setCity(masterResponse.getValue());
						ntbFinalViewResponse.setRegOfficeCity(masterResponse.getValue());
					} else {
						ntbFinalViewResponse.setCity("NA");
					}
				} catch (Exception e) {
					e.printStackTrace();
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
						ntbFinalViewResponse.setAddOfficeCity(masterResponse.getValue());

					} else {
						ntbFinalViewResponse.setCity("NA");
					}
				} catch (Exception e) {
					e.printStackTrace();
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
						ntbFinalViewResponse.setState(masterResponse.getValue());
						ntbFinalViewResponse.setRegOfficestate(masterResponse.getValue());
					} else {
						ntbFinalViewResponse.setState("NA");
					}
				} catch (Exception e) {
					e.printStackTrace();
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
						ntbFinalViewResponse.setAddOfficestate(masterResponse.getValue());
					} else {
						ntbFinalViewResponse.setState("NA");
					}
				} catch (Exception e) {
					e.printStackTrace();
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
						ntbFinalViewResponse.setCountry(masterResponse.getValue());
						ntbFinalViewResponse.setRegOfficecountry(masterResponse.getValue());
					} else {
						ntbFinalViewResponse.setCountry("NA");
					}
				} catch (Exception e) {
					e.printStackTrace();
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
						ntbFinalViewResponse.setAddOfficecountry(masterResponse.getValue());
					} else {
						ntbFinalViewResponse.setCountry("NA");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
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
						ntbFinalViewResponse.setKeyVericalFunding(masterResponse.getValue());
					} else {
						ntbFinalViewResponse.setKeyVericalFunding("NA");
					}

				} catch (Exception e) {
					e.printStackTrace();
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
					ntbFinalViewResponse.setKeyVericalSector(masterResponse.getValue());
				} else {
					ntbFinalViewResponse.setKeyVericalSector("NA");
				}
			} catch (Exception e) {
				logger.info("Error while getting key vertical sector data");
				e.printStackTrace();
			}

			// KEY VERTICAL SUB-SECTOR
			try {
				if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getKeyVerticalSubsector())) {
					OneFormResponse oneFormResponse = oneFormClient
							.getSubSecNameByMappingId(corporateApplicantDetail.getKeyVerticalSubsector());
					ntbFinalViewResponse.setKeyVericalSubsector((String) oneFormResponse.getData());
				}
			} catch (Exception e) {
				// TODO: handle exception
				logger.warn("error while getting key vertical sub-sector");
			}
		}
		// PROFILE AND PRIMARY DETAILS
		PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateRepository
				.getByApplicationAndUserId(toApplicationId, userId);
		if (primaryCorporateDetail != null) {
			// set value to response
			BeanUtils.copyProperties(primaryCorporateDetail, ntbFinalViewResponse);
			if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getCurrencyId())
					&& !CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getDenominationId())) {
				ntbFinalViewResponse
						.setCurrencyDenomination(Currency.getById(primaryCorporateDetail.getCurrencyId()).getValue()
								+ " in " + Denomination.getById(primaryCorporateDetail.getDenominationId()).getValue());
			}
			ntbFinalViewResponse.setLoanType(primaryCorporateDetail.getProductId() != null
					? LoanType.getById(primaryCorporateDetail.getProductId()).getValue()
					: null);
			ntbFinalViewResponse.setLoanAmount(
					primaryCorporateDetail.getAmount() != null ? String.valueOf(primaryCorporateDetail.getAmount())
							: null);
			ntbFinalViewResponse.setGstIn(
					corporateApplicantDetail.getGstIn() != null ? String.valueOf(corporateApplicantDetail.getGstIn())
							: null);
			ntbFinalViewResponse.setPurposeOfLoan(
					CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getPurposeOfLoanId()) ? null
							: PurposeOfLoan.getById(primaryCorporateDetail.getPurposeOfLoanId()).getValue().toString());
			ntbFinalViewResponse.setBusinessAssetAmount(primaryCorporateDetail.getBusinessAssetAmount() != null
					? String.valueOf(primaryCorporateDetail.getBusinessAssetAmount())
					: null);
			ntbFinalViewResponse.setWcAmount(
					primaryCorporateDetail.getWcAmount() != null ? String.valueOf(primaryCorporateDetail.getWcAmount())
							: null);
			ntbFinalViewResponse.setOtherAmt(
					primaryCorporateDetail.getOtherAmt() != null ? String.valueOf(primaryCorporateDetail.getOtherAmt())
							: null);
			ntbFinalViewResponse
					.setHaveCollateralSecurity(primaryCorporateDetail.getHaveCollateralSecurity() != null
							? String.valueOf(primaryCorporateDetail.getHaveCollateralSecurity())
							: null);
			ntbFinalViewResponse
					.setCollateralSecurityAmount(primaryCorporateDetail.getCollateralSecurityAmount() != null
							? String.valueOf(primaryCorporateDetail.getCollateralSecurityAmount())
							: null);
			ntbFinalViewResponse.setNpOrgId(loanApplicationMaster.getNpOrgId());
			// workingCapitalPrimaryViewResponse.setSharePriceFace(primaryWorkingCapitalLoanDetail.getSharePriceFace());
			// workingCapitalPrimaryViewResponse.setSharePriceMarket(primaryWorkingCapitalLoanDetail.getSharePriceMarket());
			if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getModifiedDate()))
				ntbFinalViewResponse.setDateOfProposal(primaryCorporateDetail.getModifiedDate() != null
						? DATE_FORMAT.format(primaryCorporateDetail.getModifiedDate())
						: null);
		}
		// DIRECTORS BACKGROUND DETAILS
		  try {
              List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestList = directorBackgroundDetailsService.getDirectorBackgroundDetailList(toApplicationId, userId);
              List<DirectorBackgroundDetailResponse> directorBackgroundDetailResponseList = new ArrayList<>();
              for (DirectorBackgroundDetailRequest directorBackgroundDetailRequest : directorBackgroundDetailRequestList) {
                  DirectorBackgroundDetailResponse directorBackgroundDetailResponse = new DirectorBackgroundDetailResponse();
                  directorBackgroundDetailResponse.setAddress(directorBackgroundDetailRequest.getAddress());
                  directorBackgroundDetailResponse.setDirectorsName((directorBackgroundDetailRequest.getSalutationId() != null ? Title.getById(directorBackgroundDetailRequest.getSalutationId()).getValue() : null )+ " " + directorBackgroundDetailRequest.getDirectorsName());
                  directorBackgroundDetailResponse.setPanNo(directorBackgroundDetailRequest.getPanNo().toUpperCase());
                  directorBackgroundDetailResponse.setAadhar(directorBackgroundDetailRequest.getAadhar());
                  String directorName = "";
                  if (directorBackgroundDetailRequest.getSalutationId() != null){
                      directorName = Title.getById(directorBackgroundDetailRequest.getSalutationId()).getValue();
                  }
                  directorName += " "+directorBackgroundDetailRequest.getDirectorsName();
                  directorBackgroundDetailResponse.setDirectorsName(directorName);
                  directorBackgroundDetailResponse.setQualification(directorBackgroundDetailRequest.getQualificationId()!= null ? EducationQualificationNTB.getById(directorBackgroundDetailRequest.getQualificationId()).getValue() : null);
                  directorBackgroundDetailResponse.setMaritalStatus(directorBackgroundDetailRequest.getMaritalStatus()!= null ? MaritalStatus.getById(directorBackgroundDetailRequest.getMaritalStatus()).getValue() : null);
                  directorBackgroundDetailResponse.setNoOfDependent(directorBackgroundDetailRequest.getNoOfDependent().toString());
                //  directorBackgroundDetailResponse.setResidenceType(directorBackgroundDetailRequest.getResidenceType());
//                 directorBackgroundDetailResponse.setResidenceSince(directorBackgroundDetailRequest.getResidenceSinceYear()!= null ? reside);
                  directorBackgroundDetailResponse.setTotalExperience(directorBackgroundDetailRequest.getTotalExperience().toString()+" Years");
                  directorBackgroundDetailResponse.setNetworth(directorBackgroundDetailRequest.getNetworth().toString());
                  directorBackgroundDetailResponse.setDesignation(directorBackgroundDetailRequest.getDesignation());
                  directorBackgroundDetailResponse.setAppointmentDate(directorBackgroundDetailRequest.getAppointmentDate());
                  directorBackgroundDetailResponse.setDin(directorBackgroundDetailRequest.getDin());
                  directorBackgroundDetailResponse.setMobile(directorBackgroundDetailRequest.getMobile());
                  directorBackgroundDetailResponse.setDob(directorBackgroundDetailRequest.getDob());
                  directorBackgroundDetailResponse.setPincode(directorBackgroundDetailRequest.getPincode());
                  directorBackgroundDetailResponse.setStateCode(directorBackgroundDetailRequest.getStateCode());
                  directorBackgroundDetailResponse.setCity(directorBackgroundDetailRequest.getCity());
                  directorBackgroundDetailResponse.setGender((directorBackgroundDetailRequest.getGender() != null ? Gender.getById(directorBackgroundDetailRequest.getGender()).getValue() : " " ));
                  directorBackgroundDetailResponse.setRelationshipType((directorBackgroundDetailRequest.getRelationshipType() != null ? DirectorRelationshipType.getById(directorBackgroundDetailRequest.getRelationshipType()).getValue() : " " ));
                  directorBackgroundDetailResponseList.add(directorBackgroundDetailResponse);
              }
              ntbFinalViewResponse.setDirectorBackgroundDetailResponses(directorBackgroundDetailResponseList);
          } catch (Exception e) {
              logger.error("Problem to get Data of Director's Background {}", e);
          }
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
				financialArrangementsDetailResponseList.add(financialArrangementsDetailResponse);
			}
			ntbFinalViewResponse
					.setFinancialArrangementsDetailResponseList(financialArrangementsDetailResponseList);

		} catch (Exception e) {
			logger.error("Problem to get Data of Financial Arrangements Details {}", e);
		}
		
		
		
		//bank statement data
				ReportRequest reportRequest = new ReportRequest();
				reportRequest.setApplicationId(toApplicationId);
				reportRequest.setUserId(userId);
				List<Data> datas=new ArrayList<>();
				try {
					AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportForCam(reportRequest);
					List<HashMap<String, Object>> hashMaps=(List<HashMap<String, Object>>) analyzerResponse.getData();
					if(!CommonUtils.isListNullOrEmpty(hashMaps))
					{
					for(HashMap<String,Object> hashMap:hashMaps)
					{
						Data data = MultipleJSONObjectHelper.getObjectFromMap(hashMap, Data.class);
						datas.add(data);
					}
					}
					ntbFinalViewResponse.setBankData(datas);
//					Data data = MultipleJSONObjectHelper.getObjectFromMap((HashMap<String, Object>) analyzerResponse.getData(), Data.class);
//					corporatePrimaryViewResponse.setMonthlyDetailList(data.getMonthlyDetailList());
//					corporatePrimaryViewResponse.setTop5FundReceivedList(data.getTop5FundReceivedList());
//					corporatePrimaryViewResponse.setTop5FundTransferedList(data.getTop5FundTransferedList());
				}catch (Exception e) {
					e.printStackTrace();
					logger.info("Error while getting perfios data");
				}

				// scoring Data
				Long fpProductMappingId = null;
				try {

					UsersRequest usersRequest = new UsersRequest();
					usersRequest.setId(fundProviderUserId);
					UserResponse userResponse = usersClient.getLastAccessApplicant(usersRequest);
					fpProductMappingId = userResponse.getId();
					
					System.out.println("fp product id=========================>>>>>"+fpProductMappingId);
				} catch (Exception e) {
					e.printStackTrace();
					logger.info("Error while getting fpMappingId For Scoring");
				}
				ScoringRequest scoringRequest = new ScoringRequest();
				scoringRequest.setApplicationId(toApplicationId);
				scoringRequest.setFpProductId(fpProductMappingId);
				try {
					ScoringResponse scoringResponse = scoringClient.getScore(scoringRequest);
					ProposalScoreResponse proposalScoreResponse = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)scoringResponse.getDataObject(),ProposalScoreResponse.class);
					ntbFinalViewResponse.setDataList(scoringResponse.getDataList());
					ntbFinalViewResponse.setManagementRiskScore(proposalScoreResponse.getManagementRiskScore());
					ntbFinalViewResponse.setFinancialRiskScore(proposalScoreResponse.getFinancialRiskScore());
					ntbFinalViewResponse.setBuisnessRiskScore(proposalScoreResponse.getBusinessRiskScore());
					ntbFinalViewResponse.setManagementRiskScoreWeight(proposalScoreResponse.getManagementRiskWeight());
					ntbFinalViewResponse.setFinancialRiskScoreWeight(proposalScoreResponse.getFinancialRiskWeight());
					ntbFinalViewResponse.setBuisnessRiskScoreWeight(proposalScoreResponse.getBusinessRiskWeight());
					ntbFinalViewResponse.setScoreInterpretation(proposalScoreResponse.getInterpretation());

				} catch (ScoringException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					logger.info("Error while getting Scoring data");
				}

				//Eligibility Data
				EligibililityRequest eligibilityReq=new EligibililityRequest();
				eligibilityReq.setApplicationId(toApplicationId);
				eligibilityReq.setProductId(!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getProductId()) ? Long.valueOf(primaryCorporateDetail.getProductId()) : null);
				System.out.println(" for eligibility appid============>>"+toApplicationId);
				
				try {
					EligibilityResponse eligibilityResp= eligibilityClient.corporateLoanData(eligibilityReq);
//					CLEligibilityRequest cLEligibilityRequest= MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>), CLEligibilityRequest.class);
					ntbFinalViewResponse.setEligibilityDataObject(eligibilityResp.getData());
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					logger.info("Error while getting Loan Eligibility data");
				}
		
		
		return ntbFinalViewResponse;
	}
	
	

}
