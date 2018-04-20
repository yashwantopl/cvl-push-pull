package com.capitaworld.service.loans.service.teaser.finalview.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.loans.model.CreditRatingOrganizationDetailRequest;
import com.capitaworld.service.loans.model.CreditRatingOrganizationDetailResponse;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailResponse;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.corporate.CorporateFinalInfoRequest;
import com.capitaworld.service.loans.model.corporate.CorporateMcqRequest;
import com.capitaworld.service.loans.model.teaser.finalview.CorporateFinalViewResponse;
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
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.PromotorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ProposedProductDetailsService;
import com.capitaworld.service.loans.service.irr.IrrService;
import com.capitaworld.service.loans.service.teaser.finalview.CorporateFinalViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.impl.CorporatePrimaryViewServiceImpl;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.model.MatchDisplayResponse;
import com.capitaworld.service.matchengine.model.MatchRequest;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.AbilityRaiseFunds;
import com.capitaworld.service.oneform.enums.AccountingQuality;
import com.capitaworld.service.oneform.enums.BorrowerInvoked;
import com.capitaworld.service.oneform.enums.BusinessCommitment;
import com.capitaworld.service.oneform.enums.BusinessExperience;
import com.capitaworld.service.oneform.enums.ChequesReturned;
import com.capitaworld.service.oneform.enums.CompanyConflicts;
import com.capitaworld.service.oneform.enums.ComplianceConditions;
import com.capitaworld.service.oneform.enums.Constitution;
import com.capitaworld.service.oneform.enums.ConstructionContract;
import com.capitaworld.service.oneform.enums.CreditRatingFund;
import com.capitaworld.service.oneform.enums.CreditRatingTerm;
import com.capitaworld.service.oneform.enums.CreditRecord;
import com.capitaworld.service.oneform.enums.CumulativeOverdrawn;
import com.capitaworld.service.oneform.enums.Currency;
import com.capitaworld.service.oneform.enums.CustomerQuality;
import com.capitaworld.service.oneform.enums.DelayInstalments;
import com.capitaworld.service.oneform.enums.DelaySubmission;
import com.capitaworld.service.oneform.enums.Denomination;
import com.capitaworld.service.oneform.enums.DirectorRelationshipType;
import com.capitaworld.service.oneform.enums.EnvironmentalImpact;
import com.capitaworld.service.oneform.enums.EstablishmentMonths;
import com.capitaworld.service.oneform.enums.FinancialRestructuring;
import com.capitaworld.service.oneform.enums.FinancialSupport;
import com.capitaworld.service.oneform.enums.Gender;
import com.capitaworld.service.oneform.enums.IndustrialRelations;
import com.capitaworld.service.oneform.enums.InfrastructureAvailability;
import com.capitaworld.service.oneform.enums.Integrity;
import com.capitaworld.service.oneform.enums.InternalControl;
import com.capitaworld.service.oneform.enums.InternalReturn;
import com.capitaworld.service.oneform.enums.LimitOverdrawn;
import com.capitaworld.service.oneform.enums.LoanType;
import com.capitaworld.service.oneform.enums.LoanTypeNatureFacility;
import com.capitaworld.service.oneform.enums.ManagementCompetence;
import com.capitaworld.service.oneform.enums.OperatingMargins;
import com.capitaworld.service.oneform.enums.OrderBook;
import com.capitaworld.service.oneform.enums.ProductSeasonality;
import com.capitaworld.service.oneform.enums.ProjectedRatio;
import com.capitaworld.service.oneform.enums.PurposeOfLoan;
import com.capitaworld.service.oneform.enums.RatingAgency;
import com.capitaworld.service.oneform.enums.SensititivityAnalysis;
import com.capitaworld.service.oneform.enums.StatusClearances;
import com.capitaworld.service.oneform.enums.StatusFinancialClosure;
import com.capitaworld.service.oneform.enums.SubmissionReports;
import com.capitaworld.service.oneform.enums.SuccessionPlanning;
import com.capitaworld.service.oneform.enums.SupplierQuality;
import com.capitaworld.service.oneform.enums.SustainabilityProduct;
import com.capitaworld.service.oneform.enums.TechnologyRisk;
import com.capitaworld.service.oneform.enums.Title;
import com.capitaworld.service.oneform.enums.UnhedgedCurrency;
import com.capitaworld.service.oneform.enums.VarianceSales;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.oneform.model.SectorIndustryModel;
import com.capitaworld.service.rating.model.FinancialInputRequest;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;

@Service
@Transactional
public class CorporateFinalViewServiceImpl implements CorporateFinalViewService{
	
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

    
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
	@Override
	public CorporateFinalViewResponse getCorporateFinalViewDetails(Long toApplicationId, Integer userType, Long fundProviderUserId) {
		
		CorporateFinalViewResponse corporateFinalViewResponse = new CorporateFinalViewResponse();
        LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(toApplicationId);
        Long userId = loanApplicationMaster.getUserId();
        
        //===================== MATCHES DATA ======================//
        if (userType != null) {
            if (!(CommonUtils.UserType.FUND_SEEKER == userType)) { // TEASER VIEW FROM FP
                Long fpProductMappingId = null;
                try {
                    UsersRequest usersRequest = new UsersRequest();
                    usersRequest.setId(fundProviderUserId);
                    UserResponse userResponse= usersClient.getLastAccessApplicant(usersRequest);
                    fpProductMappingId=userResponse.getId();
                } catch (Exception e) {
                    logger.error("error while fetching last access fp product id for fund provider while fetching matches in teaser view");
                    e.printStackTrace();
                }
                try {
                    MatchRequest matchRequest = new MatchRequest();
                    matchRequest.setApplicationId(toApplicationId);
                    matchRequest.setProductId(fpProductMappingId);
                    MatchDisplayResponse matchResponse = matchEngineClient.displayMatchesOfCorporate(matchRequest);
                    corporateFinalViewResponse.setMatchesList(matchResponse.getMatchDisplayObjectList());
                } catch (Exception e) {
                	logger.error("Error while getting matches data for final teaser view");
                    e.printStackTrace();
                }
            }
        }
        //GET CORPORATE APPLICANT DETAILS
        CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.getByApplicationAndUserId(userId, toApplicationId);
        //SET VALUE TO RESPONSE
        if (corporateApplicantDetail != null) {
            BeanUtils.copyProperties(corporateApplicantDetail, corporateFinalViewResponse);
            if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getConstitutionId()))
                corporateFinalViewResponse.setConstitution(Constitution.getById(corporateApplicantDetail.getConstitutionId()).getValue());
            if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getEstablishmentMonth()))
                corporateFinalViewResponse.setEstablishmentMonth(EstablishmentMonths.getById(corporateApplicantDetail.getEstablishmentMonth()).getValue());
            //ESTABLISHMENT YEAR
            if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getEstablishmentYear())) {
                try {
                    OneFormResponse establishmentYearResponse = oneFormClient.getYearByYearId(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getEstablishmentYear()) ? null: corporateApplicantDetail.getEstablishmentYear().longValue());
                    List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) establishmentYearResponse.getListData();
                    if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
                        MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
                        corporateFinalViewResponse.setEstablishmentYear(masterResponse.getValue());
                    } else {
                        corporateFinalViewResponse.setEstablishmentYear("NA");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //SET CITY
            List<Long> cityList = new ArrayList<>();
            if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCityId()))
                cityList.add(corporateApplicantDetail.getRegisteredCityId());
            if (!CommonUtils.isListNullOrEmpty(cityList)) {
                try {
                    OneFormResponse oneFormResponse = oneFormClient.getCityByCityListId(cityList);
                    List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
                    if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
                        MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
                        corporateFinalViewResponse.setCity(masterResponse.getValue());
                        corporateFinalViewResponse.setRegOfficeCity(masterResponse.getValue());
                    } else {
                        corporateFinalViewResponse.setCity("NA");
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
                    List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
                    if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
                        MasterResponse masterResponse = MultipleJSONObjectHelper
                                .getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
                        corporateFinalViewResponse.setAddOfficeCity(masterResponse.getValue());

                    } else {
                        corporateFinalViewResponse.setCity("NA");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //SET STATE
            List<Long> stateList = new ArrayList<>();
            if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredStateId()))
                stateList.add(Long.valueOf(corporateApplicantDetail.getRegisteredStateId()));
            if (!CommonUtils.isListNullOrEmpty(stateList)) {
                try {
                    OneFormResponse oneFormResponse = oneFormClient.getStateByStateListId(stateList);
                    List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
                    if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
                        MasterResponse masterResponse = MultipleJSONObjectHelper
                                .getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
                        corporateFinalViewResponse.setState(masterResponse.getValue());
                        corporateFinalViewResponse.setRegOfficestate(masterResponse.getValue());
                    } else {
                        corporateFinalViewResponse.setState("NA");
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
                    List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
                    if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
                        MasterResponse masterResponse = MultipleJSONObjectHelper
                                .getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
                        corporateFinalViewResponse.setAddOfficestate(masterResponse.getValue());
                    } else {
                        corporateFinalViewResponse.setState("NA");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //SET COUNTRY
            List<Long> countryList = new ArrayList<>();
            if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCountryId()))
                countryList.add(Long.valueOf(corporateApplicantDetail.getRegisteredCountryId()));
            if (!CommonUtils.isListNullOrEmpty(countryList)) {
                try {
                    OneFormResponse oneFormResponse = oneFormClient.getCountryByCountryListId(countryList);
                    List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
                    if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
                        MasterResponse masterResponse = MultipleJSONObjectHelper
                                .getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
                        corporateFinalViewResponse.setCountry(masterResponse.getValue());
                        corporateFinalViewResponse.setRegOfficecountry(masterResponse.getValue());
                    } else {
                        corporateFinalViewResponse.setCountry("NA");
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
                    List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
                    if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
                        MasterResponse masterResponse = MultipleJSONObjectHelper
                                .getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
                        corporateFinalViewResponse.setAddOfficecountry(masterResponse.getValue());
                    } else {
                        corporateFinalViewResponse.setCountry("NA");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //KEY VERTICAL FUNDING (INDUSTRY SECTOR SUBSECTOR DETAILS)
            List<Long> keyVerticalFundingId = new ArrayList<>();
            if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getKeyVericalFunding()))
                keyVerticalFundingId.add(corporateApplicantDetail.getKeyVericalFunding());
            if (!CommonUtils.isListNullOrEmpty(keyVerticalFundingId)) {
                try {
                    OneFormResponse oneFormResponse = oneFormClient.getIndustryById(keyVerticalFundingId);
                    List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
                    if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
                        MasterResponse masterResponse = MultipleJSONObjectHelper
                                .getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
                        corporateFinalViewResponse.setKeyVericalFunding(masterResponse.getValue());
                    } else {
                        corporateFinalViewResponse.setKeyVericalFunding("NA");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //KEY VERTICAL SECTOR
           List<Long> keyVerticalSectorId = new ArrayList<>();
            //GETTING SECTOR ID FROM MAPPING
            if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getKeyVerticalSector()))
                keyVerticalSectorId.add(corporateApplicantDetail.getKeyVerticalSector());
            try
            {
            OneFormResponse formResponse=oneFormClient.getIndustrySecByMappingId(corporateApplicantDetail.getKeyVerticalSector());
            // SectorIndustryModel oneResponseDataList = (SectorIndustryModel) formResponse    .getData();
            SectorIndustryModel sectorIndustryModel= MultipleJSONObjectHelper.getObjectFromMap((Map)formResponse.getData(), SectorIndustryModel.class);
            
            //GET KEY VERTICAL SECTOR VALUE
            OneFormResponse oneFormResponse = oneFormClient.getSectorById(Arrays.asList(sectorIndustryModel.getSectorId()));
            List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
            if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
                MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
                corporateFinalViewResponse.setKeyVericalSector(masterResponse.getValue());
            } else {
                corporateFinalViewResponse.setKeyVericalSector("NA");
            	}
            }
            catch (Exception e) {
            	logger.info("Error while getting key vertical sector data");
            	e.printStackTrace();
			}

            //KEY VERTICAL SUB-SECTOR
            try
            {
            if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getKeyVerticalSubsector()))
            	{
            	OneFormResponse oneFormResponse=oneFormClient.getSubSecNameByMappingId(corporateApplicantDetail.getKeyVerticalSubsector());
            	corporateFinalViewResponse.setKeyVericalSubsector((String)oneFormResponse.getData());
            	}
            }
            catch (Exception e) {
				// TODO: handle exception
            	logger.warn("error while getting key vertical sub-sector");
			}
        }
            //PROFILE AND PRIMARY DETAILS
            PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateRepository.getByApplicationAndUserId(toApplicationId, userId);
            if (primaryCorporateDetail != null) {
                // set value to response
                BeanUtils.copyProperties(primaryCorporateDetail, corporateFinalViewResponse);
                if(!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getCurrencyId())&&!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getDenominationId())) {
                    corporateFinalViewResponse.setCurrencyDenomination(Currency.getById(primaryCorporateDetail.getCurrencyId()).getValue()
                            + " in "
                            + Denomination.getById(primaryCorporateDetail.getDenominationId()).getValue());
                }
                corporateFinalViewResponse.setLoanType(primaryCorporateDetail.getProductId() != null ? LoanType.getById(primaryCorporateDetail.getProductId()).getValue() : null);
                corporateFinalViewResponse.setLoanAmount(primaryCorporateDetail.getAmount() != null ? String.valueOf(primaryCorporateDetail.getAmount()) : null);
                corporateFinalViewResponse.setGstIn(corporateApplicantDetail.getGstIn() != null ? String.valueOf(corporateApplicantDetail.getGstIn()) : null);
                corporateFinalViewResponse.setPurposeOfLoan(CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getPurposeOfLoanId()) ? null : PurposeOfLoan.getById(primaryCorporateDetail.getPurposeOfLoanId()).toString());
                corporateFinalViewResponse.setBusinessAssetAmount(primaryCorporateDetail.getBusinessAssetAmount() != null ? String.valueOf(primaryCorporateDetail.getBusinessAssetAmount()) : null);
                corporateFinalViewResponse.setWcAmount(primaryCorporateDetail.getWcAmount() != null ? String.valueOf(primaryCorporateDetail.getWcAmount()) : null);
                corporateFinalViewResponse.setOtherAmt(primaryCorporateDetail.getOtherAmt() != null ? String.valueOf(primaryCorporateDetail.getOtherAmt()) : null);
                corporateFinalViewResponse.setHaveCollateralSecurity(primaryCorporateDetail.getHaveCollateralSecurity() != null ? String.valueOf(primaryCorporateDetail.getHaveCollateralSecurity()) : null);
                corporateFinalViewResponse.setCollateralSecurityAmount(primaryCorporateDetail.getCollateralSecurityAmount() != null ? String.valueOf(primaryCorporateDetail.getCollateralSecurityAmount()) : null);
                //workingCapitalPrimaryViewResponse.setSharePriceFace(primaryWorkingCapitalLoanDetail.getSharePriceFace());
                //workingCapitalPrimaryViewResponse.setSharePriceMarket(primaryWorkingCapitalLoanDetail.getSharePriceMarket());
                if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getModifiedDate()))
                    corporateFinalViewResponse.setDateOfProposal(primaryCorporateDetail.getModifiedDate() != null ? DATE_FORMAT.format(primaryCorporateDetail.getModifiedDate()) : null);
            }
            //DIRECTORS BACKGROUND DETAILS
            try {
                List<DirectorBackgroundDetailRequest> directorBackgroundDetailRequestList = directorBackgroundDetailsService.getDirectorBackgroundDetailList(toApplicationId, userId);
                List<DirectorBackgroundDetailResponse> directorBackgroundDetailResponseList = new ArrayList<>();
                for (DirectorBackgroundDetailRequest directorBackgroundDetailRequest : directorBackgroundDetailRequestList) {
                    DirectorBackgroundDetailResponse directorBackgroundDetailResponse = new DirectorBackgroundDetailResponse();
                    //directorBackgroundDetailResponse.setAchivements(directorBackgroundDetailRequest.getAchivements());
                    directorBackgroundDetailResponse.setAddress(directorBackgroundDetailRequest.getAddress());
                    //directorBackgroundDetailResponse.setAge(directorBackgroundDetailRequest.getAge());
                    //directorBackgroundDetailResponse.setPanNo(directorBackgroundDetailRequest.getPanNo());
                    directorBackgroundDetailResponse.setDirectorsName((directorBackgroundDetailRequest.getSalutationId() != null ? Title.getById(directorBackgroundDetailRequest.getSalutationId()).getValue() : null )+ " " + directorBackgroundDetailRequest.getDirectorsName());
                    directorBackgroundDetailResponse.setPanNo(directorBackgroundDetailRequest.getPanNo().toUpperCase());
                    String directorName = "";
                    if (directorBackgroundDetailRequest.getSalutationId() != null){
                        directorName = Title.getById(directorBackgroundDetailRequest.getSalutationId()).getValue();
                    }
                    directorName += " "+directorBackgroundDetailRequest.getDirectorsName();
                    directorBackgroundDetailResponse.setDirectorsName(directorName);
                    //directorBackgroundDetailResponse.setQualification(directorBackgroundDetailRequest.getQualification());
                    directorBackgroundDetailResponse.setTotalExperience(directorBackgroundDetailRequest.getTotalExperience());
                    directorBackgroundDetailResponse.setNetworth(directorBackgroundDetailRequest.getNetworth());
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
                corporateFinalViewResponse.setDirectorBackgroundDetailResponses(directorBackgroundDetailResponseList);
            } catch (Exception e) {
                logger.error("Problem to get Data of Director's Background {}", e);
            }
            //FINANCIAL ARRANGEMENTS
            try {
                List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList = financialArrangementDetailsService
                        .getFinancialArrangementDetailsList(toApplicationId, userId);
                List<FinancialArrangementsDetailResponse> financialArrangementsDetailResponseList = new ArrayList<>();
                for (FinancialArrangementsDetailRequest financialArrangementsDetailRequest : financialArrangementsDetailRequestList) {
                    FinancialArrangementsDetailResponse financialArrangementsDetailResponse = new FinancialArrangementsDetailResponse();
//				financialArrangementsDetailResponse.setRelationshipSince(financialArrangementsDetailRequest.getRelationshipSince());
                    financialArrangementsDetailResponse.setOutstandingAmount(financialArrangementsDetailRequest.getOutstandingAmount());
                    financialArrangementsDetailResponse.setSecurityDetails(financialArrangementsDetailRequest.getSecurityDetails());
                    financialArrangementsDetailResponse.setAmount(financialArrangementsDetailRequest.getAmount());
                    //			financialArrangementsDetailResponse.setLenderType(LenderType.getById(financialArrangementsDetailRequest.getLenderType()).getValue());
                    financialArrangementsDetailResponse.setLoanDate(financialArrangementsDetailRequest.getLoanDate());
                    financialArrangementsDetailResponse.setLoanType(LoanTypeNatureFacility.getById(financialArrangementsDetailRequest.getLoanType()).getValue());
                    financialArrangementsDetailResponse.setFinancialInstitutionName(financialArrangementsDetailRequest.getFinancialInstitutionName());
                    //			financialArrangementsDetailResponse.setFacilityNature(NatureFacility.getById(financialArrangementsDetailRequest.getFacilityNatureId()).getValue());
                    //financialArrangementsDetailResponse.setAddress(financialArrangementsDetailRequest.getAddress());
                    financialArrangementsDetailResponseList.add(financialArrangementsDetailResponse);
                }
                corporateFinalViewResponse.setFinancialArrangementsDetailResponseList(financialArrangementsDetailResponseList);

            } catch (Exception e) {
                logger.error("Problem to get Data of Financial Arrangements Details {}", e);
            }
         
            //FINANCIAL TAB IN TEASER VIEW
            //IRR MAPPING
            Long denomination = Denomination.getById(primaryCorporateDetail.getDenominationId()).getDigit();
			try {
				FinancialInputRequest financialInputRequest = irrService.cmaIrrMappingService(userId, toApplicationId, null, denomination);
				//Profit & Loss Statement
	    		financialInputRequest.setNetSaleFy(CommonUtils.substractNumbers(financialInputRequest.getGrossSalesFy(), financialInputRequest.getLessExciseDuityFy()));
	    		financialInputRequest.setNetSaleSy(CommonUtils.substractNumbers(financialInputRequest.getGrossSalesSy(), financialInputRequest.getLessExciseDuitySy()));
	    		financialInputRequest.setNetSaleTy(CommonUtils.substractNumbers(financialInputRequest.getGrossSalesTy(), financialInputRequest.getLessExciseDuityTy()));
	    		financialInputRequest.setTotalExpenditureFy(CommonUtils.substractNumbers(CommonUtils.addNumbers(financialInputRequest.getIncreaseDecreaseStockFy(),financialInputRequest.getRawMaterialConsumedFy(),financialInputRequest.getPowerAndFuelCostFy(),financialInputRequest.getEmployeeCostFy(), financialInputRequest.getGeneralAndAdminExpeFy(),financialInputRequest.getSellingAndDistriExpeFy(),financialInputRequest.getMiscelExpeFy()), financialInputRequest.getLessExpeCapitaFy()));
	    		financialInputRequest.setTotalExpenditureSy(CommonUtils.substractNumbers(CommonUtils.addNumbers(financialInputRequest.getIncreaseDecreaseStockSy(),financialInputRequest.getRawMaterialConsumedSy(),financialInputRequest.getPowerAndFuelCostSy(),financialInputRequest.getEmployeeCostSy(), financialInputRequest.getGeneralAndAdminExpeSy(),financialInputRequest.getSellingAndDistriExpeSy(),financialInputRequest.getMiscelExpeSy()), financialInputRequest.getLessExpeCapitaSy()));
	    		financialInputRequest.setTotalExpenditureTy(CommonUtils.substractNumbers(CommonUtils.addNumbers(financialInputRequest.getIncreaseDecreaseStockTy(),financialInputRequest.getRawMaterialConsumedTy(),financialInputRequest.getPowerAndFuelCostTy(),financialInputRequest.getEmployeeCostTy(), financialInputRequest.getGeneralAndAdminExpeTy(),financialInputRequest.getSellingAndDistriExpeTy(),financialInputRequest.getMiscelExpeTy()), financialInputRequest.getLessExpeCapitaTy()));
	    		financialInputRequest.setOperatingProfitExclOiFy(CommonUtils.substractNumbers(financialInputRequest.getNetSaleFy(),financialInputRequest.getTotalExpenditureFy()));
	    		financialInputRequest.setOperatingProfitExclOiSy(CommonUtils.substractNumbers(financialInputRequest.getNetSaleSy(),financialInputRequest.getTotalExpenditureSy()));
	    		financialInputRequest.setOperatingProfitExclOiTy(CommonUtils.substractNumbers(financialInputRequest.getNetSaleTy(),financialInputRequest.getTotalExpenditureTy()));
	    		financialInputRequest.setOperatingProfitEbitadOiFy(CommonUtils.addNumbers(financialInputRequest.getOperatingProfitExclOiFy(),financialInputRequest.getOtherIncomeFy()));
	    		financialInputRequest.setOperatingProfitEbitadOiSy(CommonUtils.addNumbers(financialInputRequest.getOperatingProfitExclOiSy(),financialInputRequest.getOtherIncomeSy()));
	    		financialInputRequest.setOperatingProfitEbitadOiTy(CommonUtils.addNumbers(financialInputRequest.getOperatingProfitExclOiTy(),financialInputRequest.getOtherIncomeTy()));
	    		financialInputRequest.setPbdtFy(CommonUtils.substractNumbers(financialInputRequest.getOperatingProfitEbitadOiFy(), financialInputRequest.getInterestFy()));
	    		financialInputRequest.setPbdtSy(CommonUtils.substractNumbers(financialInputRequest.getOperatingProfitEbitadOiSy(), financialInputRequest.getInterestSy()));
	    		financialInputRequest.setPbdtTy(CommonUtils.substractNumbers(financialInputRequest.getOperatingProfitEbitadOiTy(), financialInputRequest.getInterestTy()));
	    		financialInputRequest.setProfitBeforeTaxationFy(CommonUtils.substractNumbers(financialInputRequest.getPbdtFy(), financialInputRequest.getDepriciationFy()));
	    		financialInputRequest.setProfitBeforeTaxationSy(CommonUtils.substractNumbers(financialInputRequest.getPbdtSy(), financialInputRequest.getDepriciationSy()));
	    		financialInputRequest.setProfitBeforeTaxationTy(CommonUtils.substractNumbers(financialInputRequest.getPbdtTy(), financialInputRequest.getDepriciationTy()));
	    		financialInputRequest.setProfitBeforeTaxFy(CommonUtils.addNumbers(financialInputRequest.getProfitBeforeTaxationFy(), financialInputRequest.getExceptionalIncomeFy()));
	    		financialInputRequest.setProfitBeforeTaxSy(CommonUtils.addNumbers(financialInputRequest.getProfitBeforeTaxationSy(), financialInputRequest.getExceptionalIncomeSy()));
	    		financialInputRequest.setProfitBeforeTaxTy(CommonUtils.addNumbers(financialInputRequest.getProfitBeforeTaxationTy(), financialInputRequest.getExceptionalIncomeTy()));
	    		financialInputRequest.setProfitAfterTaxFy(CommonUtils.substractNumbers(financialInputRequest.getProfitBeforeTaxFy(), financialInputRequest.getProvisionForTaxFy()));
	    		financialInputRequest.setProfitAfterTaxSy(CommonUtils.substractNumbers(financialInputRequest.getProfitBeforeTaxSy(), financialInputRequest.getProvisionForTaxSy()));
	    		financialInputRequest.setProfitAfterTaxTy(CommonUtils.substractNumbers(financialInputRequest.getProfitBeforeTaxTy(), financialInputRequest.getProvisionForTaxTy()));
	    		if(financialInputRequest.getDividendPayOutFy() == 0)
	    			financialInputRequest.setEquityDividendFy(0.0);
	    		else
	    			financialInputRequest.setEquityDividendFy(financialInputRequest.getDividendPayOutFy()*financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalFy());
	    		
	    		if(financialInputRequest.getDividendPayOutSy() == 0)
	    			financialInputRequest.setEquityDividendSy(0.0);
	    		else
	    			financialInputRequest.setEquityDividendSy(financialInputRequest.getDividendPayOutSy()*financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalSy());
	    		
	    		if(financialInputRequest.getDividendPayOutTy() == 0)
	    			financialInputRequest.setEquityDividendTy(0.0);
	    		else
	    			financialInputRequest.setEquityDividendTy(financialInputRequest.getDividendPayOutTy()*financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalTy());
	    		
	    		financialInputRequest.setEarningPerShareFy((financialInputRequest.getProfitAfterTaxFy())*financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalFy());
	    		financialInputRequest.setEarningPerShareSy((financialInputRequest.getProfitAfterTaxSy())*financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalSy());
	    		financialInputRequest.setEarningPerShareTy((financialInputRequest.getProfitAfterTaxTy())*financialInputRequest.getShareFaceValue()/financialInputRequest.getShareCapitalTy());
	    		
	    		//Balance Sheet -Equities and Liabilities
	    		financialInputRequest.setShareHolderFundsFy(CommonUtils.addNumbers(financialInputRequest.getShareCapitalFy(),financialInputRequest.getShareWarrantOutstandingsFy(),financialInputRequest.getRevalationReserveFy(),financialInputRequest.getOtherReserveAndSurplusFy()));
	    		financialInputRequest.setShareHolderFundsSy(CommonUtils.addNumbers(financialInputRequest.getShareCapitalSy(),financialInputRequest.getShareWarrantOutstandingsSy(),financialInputRequest.getRevalationReserveSy(),financialInputRequest.getOtherReserveAndSurplusSy()));
	    		financialInputRequest.setShareHolderFundsTy(CommonUtils.addNumbers(financialInputRequest.getShareCapitalTy(),financialInputRequest.getShareWarrantOutstandingsTy(),financialInputRequest.getRevalationReserveTy(),financialInputRequest.getOtherReserveAndSurplusTy()));
	    		financialInputRequest.setTotalNonCurruntLiablitiesFy(CommonUtils.addNumbers(financialInputRequest.getMinorityInterestFy(), financialInputRequest.getSecuredLoansFy(), financialInputRequest.getUnsecuredLoansOthersFy(),financialInputRequest.getUnsecuredLoansPromotersFy(),financialInputRequest.getDeferredTaxLiablitiesFy(),financialInputRequest.getOtherLongTermLiablitiesFy(),financialInputRequest.getOtherBorrowingFy(),financialInputRequest.getLongTermProvisionFy()));
	    		financialInputRequest.setTotalNonCurruntLiablitiesSy(CommonUtils.addNumbers(financialInputRequest.getMinorityInterestSy(), financialInputRequest.getSecuredLoansSy(), financialInputRequest.getUnsecuredLoansOthersSy(),financialInputRequest.getUnsecuredLoansPromotersSy(),financialInputRequest.getDeferredTaxLiablitiesSy(),financialInputRequest.getOtherLongTermLiablitiesSy(),financialInputRequest.getOtherBorrowingSy(),financialInputRequest.getLongTermProvisionSy()));
	    		financialInputRequest.setTotalNonCurruntLiablitiesTy(CommonUtils.addNumbers(financialInputRequest.getMinorityInterestTy(), financialInputRequest.getSecuredLoansTy(), financialInputRequest.getUnsecuredLoansOthersTy(),financialInputRequest.getUnsecuredLoansPromotersTy(),financialInputRequest.getDeferredTaxLiablitiesTy(),financialInputRequest.getOtherLongTermLiablitiesTy(),financialInputRequest.getOtherBorrowingTy(),financialInputRequest.getLongTermProvisionTy()));
	    		financialInputRequest.setTotalCurruntLiablitiesFy(CommonUtils.addNumbers(financialInputRequest.getTradePayablesFy(), financialInputRequest.getOtherCurruntLiablitiesFy(), financialInputRequest.getShortTermProvisionFy()));
	    		financialInputRequest.setTotalCurruntLiablitiesSy(CommonUtils.addNumbers(financialInputRequest.getTradePayablesSy(), financialInputRequest.getOtherCurruntLiablitiesSy(), financialInputRequest.getShortTermProvisionSy()));
	    		financialInputRequest.setTotalCurruntLiablitiesTy(CommonUtils.addNumbers(financialInputRequest.getTradePayablesTy(), financialInputRequest.getOtherCurruntLiablitiesTy(), financialInputRequest.getShortTermProvisionTy()));
	    		financialInputRequest.setTotalLiablitiesFy(CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsFy(), financialInputRequest.getTotalNonCurruntLiablitiesFy(), financialInputRequest.getTotalCurruntLiablitiesFy()));
	    		financialInputRequest.setTotalLiablitiesSy(CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsSy(), financialInputRequest.getTotalNonCurruntLiablitiesSy(), financialInputRequest.getTotalCurruntLiablitiesSy()));
	    		financialInputRequest.setTotalLiablitiesTy(CommonUtils.addNumbers(financialInputRequest.getShareHolderFundsTy(), financialInputRequest.getTotalNonCurruntLiablitiesTy(), financialInputRequest.getTotalCurruntLiablitiesTy()));
	    		
	    		//Balance Sheet -ASSETS
	    		financialInputRequest.setNetBlockFy(CommonUtils.addNumbers(financialInputRequest.getGrossBlockFy(), financialInputRequest.getLessAccumulatedDepreFy(),financialInputRequest.getImpairmentofAssetFy()));
	    		financialInputRequest.setNetBlockSy(CommonUtils.addNumbers(financialInputRequest.getGrossBlockSy(), financialInputRequest.getLessAccumulatedDepreSy(),financialInputRequest.getImpairmentofAssetSy()));
	    		financialInputRequest.setNetBlockTy(CommonUtils.addNumbers(financialInputRequest.getGrossBlockTy(), financialInputRequest.getLessAccumulatedDepreTy(),financialInputRequest.getImpairmentofAssetTy()));
	    		financialInputRequest.setTotalNonCurruntAssetFy(CommonUtils.addNumbers(financialInputRequest.getCapitalWorkInProgressFy(), financialInputRequest.getIntengibleAssetsFy(), financialInputRequest.getPreOperativeExpeFy(), financialInputRequest.getAssetInTransitFy(), financialInputRequest.getInvestmentInSubsidiariesFy(), financialInputRequest.getOtherInvestmentFy(), financialInputRequest.getLongTermLoansAndAdvaFy(), financialInputRequest.getOtheNonCurruntAssetFy()));
	    		financialInputRequest.setTotalNonCurruntAssetSy(CommonUtils.addNumbers(financialInputRequest.getCapitalWorkInProgressSy(), financialInputRequest.getIntengibleAssetsSy(), financialInputRequest.getPreOperativeExpeSy(), financialInputRequest.getAssetInTransitSy(), financialInputRequest.getInvestmentInSubsidiariesSy(), financialInputRequest.getOtherInvestmentSy(), financialInputRequest.getLongTermLoansAndAdvaSy(), financialInputRequest.getOtheNonCurruntAssetSy()));
	    		financialInputRequest.setTotalNonCurruntAssetTy(CommonUtils.addNumbers(financialInputRequest.getCapitalWorkInProgressTy(), financialInputRequest.getIntengibleAssetsTy(), financialInputRequest.getPreOperativeExpeTy(), financialInputRequest.getAssetInTransitTy(), financialInputRequest.getInvestmentInSubsidiariesTy(), financialInputRequest.getOtherInvestmentTy(), financialInputRequest.getLongTermLoansAndAdvaTy(), financialInputRequest.getOtheNonCurruntAssetTy()));
	    		financialInputRequest.setTotalCurruntAssetFy(CommonUtils.addNumbers(financialInputRequest.getInventoriesFy(), financialInputRequest.getSundryDebtorsFy(), financialInputRequest.getCashAndBankFy(), financialInputRequest.getOtherCurruntAssetFy(), financialInputRequest.getShortTermLoansAdvancesFy()));
	    		financialInputRequest.setTotalCurruntAssetSy(CommonUtils.addNumbers(financialInputRequest.getInventoriesSy(), financialInputRequest.getSundryDebtorsSy(), financialInputRequest.getCashAndBankSy(), financialInputRequest.getOtherCurruntAssetSy(), financialInputRequest.getShortTermLoansAdvancesSy()));
	    		financialInputRequest.setTotalCurruntAssetTy(CommonUtils.addNumbers(financialInputRequest.getInventoriesTy(), financialInputRequest.getSundryDebtorsTy(), financialInputRequest.getCashAndBankTy(), financialInputRequest.getOtherCurruntAssetTy(), financialInputRequest.getShortTermLoansAdvancesTy()));
	    		financialInputRequest.setTotalAssetFy(CommonUtils.addNumbers(financialInputRequest.getNetBlockFy(), financialInputRequest.getTotalCurruntAssetFy(), financialInputRequest.getTotalNonCurruntAssetFy()));
	    		financialInputRequest.setTotalAssetSy(CommonUtils.addNumbers(financialInputRequest.getNetBlockSy(), financialInputRequest.getTotalCurruntAssetSy(), financialInputRequest.getTotalNonCurruntAssetSy()));
	    		financialInputRequest.setTotalAssetTy(CommonUtils.addNumbers(financialInputRequest.getNetBlockTy(), financialInputRequest.getTotalCurruntAssetTy(), financialInputRequest.getTotalNonCurruntAssetTy()));
	    		financialInputRequest.setBookValueFy(financialInputRequest.getShareHolderFundsFy()/(financialInputRequest.getShareCapitalFy()/financialInputRequest.getShareFaceValue()));
	    		financialInputRequest.setBookValueSy(financialInputRequest.getShareHolderFundsSy()/(financialInputRequest.getShareCapitalSy()/financialInputRequest.getShareFaceValue()));
	    		financialInputRequest.setBookValueTy(financialInputRequest.getShareHolderFundsTy()/(financialInputRequest.getShareCapitalTy()/financialInputRequest.getShareFaceValue()));
	    		corporateFinalViewResponse.setFinancialInputRequest(financialInputRequest);
	    		
			} catch (Exception e) {
				logger.info("Error while getting irr mapping data");
				e.printStackTrace();
			}
			
			
			//============================================FINAL TEASER VIEW DETAILS=============================================//
			 
			try {
					CorporateFinalInfoRequest corporateFinalInfoRequest = corporateFinalInfoService.get(userId, toApplicationId);
					corporateFinalViewResponse.setAboutUs(!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getAboutUs()) ? corporateFinalInfoRequest.getAboutUs() : null);
					corporateFinalViewResponse.setUdhyogAadharNo(!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getAadhar()) ? corporateFinalInfoRequest.getAadhar() : null);
					corporateFinalViewResponse.setContigentLiablitiesFy(corporateFinalInfoRequest.getContLiabilityFyAmt() != null ? String.valueOf(corporateFinalInfoRequest.getContLiabilityFyAmt()) : null);
					corporateFinalViewResponse.setContigentLiablitiesSy(corporateFinalInfoRequest.getContLiabilitySyAmt() != null ? String.valueOf(corporateFinalInfoRequest.getContLiabilitySyAmt()) : null);
					corporateFinalViewResponse.setContigentLiablitiesTy(corporateFinalInfoRequest.getContLiabilityTyAmt() != null ? String.valueOf(corporateFinalInfoRequest.getContLiabilityTyAmt()) : null);
					corporateFinalViewResponse.setCreditRating(!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getCreditRatingId()) ? CreditRatingTerm.getById(corporateFinalInfoRequest.getCreditRatingId()).getValue() : " ");
			} catch (Exception e1) {
					logger.info("Error while getting corporate final information");
					e1.printStackTrace();
			}
			 
			 //ACHIEVMENTS DETAILS
			 try {
				 corporateFinalViewResponse.setAchievementDetailList(achievmentDetailsService.getAchievementDetailList(toApplicationId, userId));
			} catch (Exception e) {
					logger.error("Problem to get Data of Achievement Details {}", e);
			}
			 //CREDIT RATING TABLE
			 try {
				List<CreditRatingOrganizationDetailRequest> creditRatingOrganizationDetailRequestList = creditRatingOrganizationDetailsService.getcreditRatingOrganizationDetailsList(toApplicationId, userId);
					List<CreditRatingOrganizationDetailResponse> creditRatingOrganizationDetailResponseList = new ArrayList<>();
					for (CreditRatingOrganizationDetailRequest creditRatingOrganizationDetailRequest : creditRatingOrganizationDetailRequestList) {
						CreditRatingOrganizationDetailResponse creditRatingOrganizationDetailResponse = new CreditRatingOrganizationDetailResponse();
						creditRatingOrganizationDetailResponse.setAmount(creditRatingOrganizationDetailRequest.getAmount());
						creditRatingOrganizationDetailResponse.setCreditRatingFund(creditRatingOrganizationDetailRequest.getCreditRatingFundId() != null ? CreditRatingFund.getById(creditRatingOrganizationDetailRequest.getCreditRatingFundId()).getValue() : null);
		
						OneFormResponse oneFormResponse = oneFormClient.getRatingById(CommonUtils.isObjectNullOrEmpty(creditRatingOrganizationDetailRequest.getCreditRatingOptionId()) ? null : creditRatingOrganizationDetailRequest.getCreditRatingOptionId().longValue());
						MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) oneFormResponse.getData(), MasterResponse.class);
						if (masterResponse != null) {
							creditRatingOrganizationDetailResponse.setCreditRatingOption(masterResponse.getValue());
						} else {
							creditRatingOrganizationDetailResponse.setCreditRatingOption(CommonUtils.NOT_APPLICABLE);
						}
						if (creditRatingOrganizationDetailRequest.getCreditRatingTermId() != null)
							creditRatingOrganizationDetailResponse.setCreditRatingTerm(CreditRatingTerm.getById(creditRatingOrganizationDetailRequest.getCreditRatingTermId()).getValue());
						if (creditRatingOrganizationDetailRequest.getRatingAgencyId() != null)
							creditRatingOrganizationDetailResponse.setRatingAgency(RatingAgency.getById(creditRatingOrganizationDetailRequest.getRatingAgencyId()).getValue());
							creditRatingOrganizationDetailResponse.setFacilityName(creditRatingOrganizationDetailRequest.getFacilityName());
							creditRatingOrganizationDetailResponseList.add(creditRatingOrganizationDetailResponse);
							creditRatingOrganizationDetailResponse.setEntityName(creditRatingOrganizationDetailRequest.getEntityName());
						if (creditRatingOrganizationDetailRequest.getRatingDate() != null){
							creditRatingOrganizationDetailResponse.setRatingDate(creditRatingOrganizationDetailRequest.getRatingDate());
						}
					}
					corporateFinalViewResponse.setCreditRatingOrganizationDetailResponse(creditRatingOrganizationDetailResponseList);
				} catch (Exception e) {
					logger.error("Problem to get Data of Credit Rating {}", e);
				}
			 
			 //EXISTING PRODUCT DETAILS
			try {
				 corporateFinalViewResponse.setExistingProductDetailRequestList(existingProductDetailsService.getExistingProductDetailList(toApplicationId, userId));
			} catch (Exception e) {
					logger.error("Problem to get Data of Existing Product {}", e);
			}
			 //PROPOSED PRODUCT DETAILS
			 try {
				 corporateFinalViewResponse.setProposedProductDetailRequestList(proposedProductDetailsService.getProposedProductDetailList(toApplicationId, userId));
			} catch (Exception e) {
					logger.error("Problem to get Data of Proposed Product {}", e);
			}
			
			 //ASSOCIATED CONCERNS
			try {
				 corporateFinalViewResponse.setAssociatedConcernDetailRequests(associatedConcernDetailService.getAssociatedConcernsDetailList(toApplicationId, userId));
			} catch (Exception e) {
					logger.error("Problem to get Data of Associated Concerns {}", e);
			}
			
			//PROMOTOR BACKGROUND DETAILS
			try {
				List<PromotorBackgroundDetailRequest> promotorBackgroundDetailRequestList = promotorBackgroundDetailsService.getPromotorBackgroundDetailList(toApplicationId, userId);
				List<PromotorBackgroundDetailResponse> promotorBackgroundDetailResponseList = new ArrayList<>();
				for (PromotorBackgroundDetailRequest promotorBackgroundDetailRequest : promotorBackgroundDetailRequestList) {
					PromotorBackgroundDetailResponse promotorBackgroundDetailResponse = new PromotorBackgroundDetailResponse();
					promotorBackgroundDetailResponse.setAchievements(promotorBackgroundDetailRequest.getAchivements());
					promotorBackgroundDetailResponse.setAddress(promotorBackgroundDetailRequest.getAddress());
					promotorBackgroundDetailResponse.setAge(promotorBackgroundDetailRequest.getAge());
	                promotorBackgroundDetailResponse.setPanNo(promotorBackgroundDetailRequest.getPanNo().toUpperCase());
					String promotorName = "";
					if (promotorBackgroundDetailRequest.getSalutationId() != null){
						promotorName = Title.getById(promotorBackgroundDetailRequest.getSalutationId()).getValue();
					}
					promotorName += promotorBackgroundDetailRequest.getPromotorsName();
					promotorBackgroundDetailResponse.setPromotorsName(promotorName);
					promotorBackgroundDetailResponse.setQualification(promotorBackgroundDetailRequest.getQualification());
					promotorBackgroundDetailResponse.setTotalExperience(promotorBackgroundDetailRequest.getTotalExperience());
					promotorBackgroundDetailResponseList.add(promotorBackgroundDetailResponse);
				}
				corporateFinalViewResponse.setPromotorBackgroundDetailResponseList(promotorBackgroundDetailResponseList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				CorporateMcqRequest corporateMcqRequest = corporateMcqService.get(userId, toApplicationId);
				
				corporateFinalViewResponse.setTechnologyRiskId(corporateMcqRequest.getTechnologyRiskId() != null ? TechnologyRisk.getById(corporateMcqRequest.getTechnologyRiskId()).getValue() : null);
				corporateFinalViewResponse.setCustomerQuality(corporateMcqRequest.getCustomerQuality() != null ? CustomerQuality.getById(corporateMcqRequest.getCustomerQuality()).getValue() : null);
				corporateFinalViewResponse.setSupplierQuality(corporateMcqRequest.getSupplierQuality() != null ? SupplierQuality.getById(corporateMcqRequest.getSupplierQuality()).getValue() : null);
				corporateFinalViewResponse.setSustainabilityProduct(corporateMcqRequest.getSustainabilityProduct() != null ? SustainabilityProduct.getById(corporateMcqRequest.getSustainabilityProduct()).getValue() : null);
				corporateFinalViewResponse.setEmployeeRelations(corporateMcqRequest.getEmployeeRelations() != null ? IndustrialRelations.getById(corporateMcqRequest.getEmployeeRelations()).getValue() : null);
				corporateFinalViewResponse.setProductSeasonality(corporateMcqRequest.getProductSeasonality() != null ? ProductSeasonality.getById(corporateMcqRequest.getProductSeasonality()).getValue() : null);
				corporateFinalViewResponse.setImpactOnOperatingMargins(corporateMcqRequest.getImpactOnOperatingMargins() != null ? OperatingMargins.getById(corporateMcqRequest.getImpactOnOperatingMargins()).getValue() : null);
				corporateFinalViewResponse.setOrderBookPosition(corporateMcqRequest.getOrderBookPosition() != null ? OrderBook.getById(corporateMcqRequest.getOrderBookPosition()).getValue() : null);
				corporateFinalViewResponse.setEnvironmentalImpact(corporateMcqRequest.getEnvironmentalImpact() != null ? EnvironmentalImpact.getById(corporateMcqRequest.getEnvironmentalImpact()).getValue() : null);
				corporateFinalViewResponse.setAccountingQuality(corporateMcqRequest.getAccountingQuality() != null ? AccountingQuality.getById(corporateMcqRequest.getAccountingQuality()).getValue() : null);
				corporateFinalViewResponse.setFinancialRestructuringHistory(corporateMcqRequest.getFinancialRestructuringHistory() != null ? FinancialRestructuring.getById(corporateMcqRequest.getFinancialRestructuringHistory()).getValue() : null);
				corporateFinalViewResponse.setIntegrity(corporateMcqRequest.getIntegrity() != null ? Integrity.getById(corporateMcqRequest.getIntegrity()).getValue() : null);
				corporateFinalViewResponse.setBusinessCommitment(corporateMcqRequest.getBusinessCommitment() != null ? BusinessCommitment.getById(corporateMcqRequest.getBusinessCommitment()).getValue() : null);
				corporateFinalViewResponse.setManagementCompetence(corporateMcqRequest.getManagementCompetence() != null ? ManagementCompetence.getById(corporateMcqRequest.getManagementCompetence()).getValue() : null);
				corporateFinalViewResponse.setBusinessExperience(corporateMcqRequest.getBusinessExperience() != null ? BusinessExperience.getById(corporateMcqRequest.getBusinessExperience()).getValue() : null);
				corporateFinalViewResponse.setSuccessionPlanning(corporateMcqRequest.getSuccessionPlanning() != null ? SuccessionPlanning.getById(corporateMcqRequest.getSuccessionPlanning()).getValue() : null);
				corporateFinalViewResponse.setFinancialStrength(corporateMcqRequest.getFinancialStrength() != null ? FinancialSupport.getById(corporateMcqRequest.getFinancialStrength()).getValue() : null);
				corporateFinalViewResponse.setAbilityToRaiseFunds(corporateMcqRequest.getAbilityToRaiseFunds() != null ? AbilityRaiseFunds.getById(corporateMcqRequest.getAbilityToRaiseFunds()).getValue() : null);
				corporateFinalViewResponse.setIntraCompany(corporateMcqRequest.getIntraCompany() != null ? CompanyConflicts.getById(corporateMcqRequest.getIntraCompany()).getValue() : null);
				corporateFinalViewResponse.setInternalControl(corporateMcqRequest.getInternalControl() != null ? InternalControl.getById(corporateMcqRequest.getInternalControl()).getValue() : null);
				corporateFinalViewResponse.setCreditTrackRecord(corporateMcqRequest.getCreditTrackRecord() != null ? CreditRecord.getById(corporateMcqRequest.getCreditTrackRecord()).getValue() : null);
				corporateFinalViewResponse.setStatusOfProjectClearances(corporateMcqRequest.getStatusOfProjectClearances() != null ? StatusClearances.getById(corporateMcqRequest.getStatusOfProjectClearances()).getValue() : null);
				corporateFinalViewResponse.setStatusOfFinancialClosure(corporateMcqRequest.getStatusOfFinancialClosure() != null ? StatusFinancialClosure.getById(corporateMcqRequest.getStatusOfFinancialClosure()).getValue() : null);
				corporateFinalViewResponse.setInfrastructureAvailability(corporateMcqRequest.getInfrastructureAvailability() != null ? InfrastructureAvailability.getById(corporateMcqRequest.getInfrastructureAvailability()).getValue() : null);
				corporateFinalViewResponse.setConstructionContract(corporateMcqRequest.getConstructionContract() != null ? ConstructionContract.getById(corporateMcqRequest.getConstructionContract()).getValue() : null);
				corporateFinalViewResponse.setNumberOfCheques(corporateMcqRequest.getNumberOfCheques() != null ? ChequesReturned.getById(corporateMcqRequest.getNumberOfCheques()).getValue() : null);
				corporateFinalViewResponse.setNumberOfTimesDp(corporateMcqRequest.getNumberOfTimesDp() != null ? LimitOverdrawn.getById(corporateMcqRequest.getNumberOfTimesDp()).getValue() : null);
				corporateFinalViewResponse.setCumulativeNoOfDaysDp(corporateMcqRequest.getCumulativeNoOfDaysDp() != null ? CumulativeOverdrawn.getById(corporateMcqRequest.getCumulativeNoOfDaysDp()).getValue() : null);
				corporateFinalViewResponse.setComplianceWithSanctioned(corporateMcqRequest.getComplianceWithSanctioned() != null ? ComplianceConditions.getById(corporateMcqRequest.getComplianceWithSanctioned()).getValue() : null);
				corporateFinalViewResponse.setProgressReports(corporateMcqRequest.getProgressReports() != null ? SubmissionReports.getById(corporateMcqRequest.getProgressReports()).getValue() : null);
				corporateFinalViewResponse.setDelayInReceipt(corporateMcqRequest.getDelayInReceipt() != null ? DelayInstalments.getById(corporateMcqRequest.getDelayInReceipt()).getValue() : null);
				corporateFinalViewResponse.setDelayInSubmission(corporateMcqRequest.getDelayInSubmission() != null ? DelaySubmission.getById(corporateMcqRequest.getDelayInSubmission()).getValue() : null);
				corporateFinalViewResponse.setNumberOfLc(corporateMcqRequest.getNumberOfLc() != null ? BorrowerInvoked.getById(corporateMcqRequest.getNumberOfLc()).getValue() : null);
				corporateFinalViewResponse.setUnhedgedForeignCurrency(corporateMcqRequest.getUnhedgedForeignCurrency() != null ? UnhedgedCurrency.getById(corporateMcqRequest.getUnhedgedForeignCurrency()).getValue() : null);
				corporateFinalViewResponse.setProjectedDebtService(corporateMcqRequest.getProjectedDebtService() != null ? ProjectedRatio.getById(corporateMcqRequest.getProjectedDebtService()).getValue() : null);
				corporateFinalViewResponse.setInternalRateReturn(corporateMcqRequest.getInternalRateReturn() != null ? InternalReturn.getById(corporateMcqRequest.getInternalRateReturn()).getValue() : null);
				corporateFinalViewResponse.setSensititivityAnalysis(corporateMcqRequest.getSensititivityAnalysis() != null ? SensititivityAnalysis.getById(corporateMcqRequest.getSensititivityAnalysis()).getValue() : null);
				corporateFinalViewResponse.setVarianceInProjectedSales(corporateMcqRequest.getVarianceInProjectedSales() != null ? VarianceSales.getById(corporateMcqRequest.getVarianceInProjectedSales()).getValue() : null);
				
			} catch (Exception e) {
				logger.info("Error while getting mcq");
				e.printStackTrace();
			}
				
			
        return corporateFinalViewResponse;
	}

}
