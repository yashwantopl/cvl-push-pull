package com.capitaworld.service.loans.service.teaser.primaryview.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailResponse;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.CorporatePrimaryViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SectorIndustryMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SubSectorMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SubSectorRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.DirectorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.irr.IrrService;
import com.capitaworld.service.loans.service.teaser.primaryview.CorporatePrimaryViewService;
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
import com.capitaworld.service.oneform.enums.EstablishmentMonths;
import com.capitaworld.service.oneform.enums.Gender;
import com.capitaworld.service.oneform.enums.LoanType;
import com.capitaworld.service.oneform.enums.LoanTypeNatureFacility;
import com.capitaworld.service.oneform.enums.PurposeOfLoan;
import com.capitaworld.service.oneform.enums.Title;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.oneform.model.SectorIndustryModel;
import com.capitaworld.service.rating.model.FinancialInputRequest;
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

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    @Override
    public CorporatePrimaryViewResponse getCorporatePrimaryViewDetails(Long toApplicationId, Integer userType, Long fundProviderUserId) {

        CorporatePrimaryViewResponse corporatePrimaryViewResponse = new CorporatePrimaryViewResponse();
        LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(toApplicationId);
        Long userId = loanApplicationMaster.getUserId();


        /*========= Matches Data ==========*/
        if (userType != null) {
            if (!(CommonUtils.UserType.FUND_SEEKER == userType)) { // teaser
                // view
                // viwed by
                // fund
                // provider
                Long fpProductMappingId = null;
                try {

                    UsersRequest usersRequest = new UsersRequest();
                    usersRequest.setId(fundProviderUserId);
                    UserResponse userResponse= usersClient.getLastAccessApplicant(usersRequest);
                    fpProductMappingId=userResponse.getId();
                } catch (Exception e) {
                    logger.error("error while fetching last access fp rpduct id for fund provider while fetching matches in teaser view");
                    e.printStackTrace();
                }
                try {
                    MatchRequest matchRequest = new MatchRequest();
                    matchRequest.setApplicationId(toApplicationId);
                    matchRequest.setProductId(fpProductMappingId);
                    MatchDisplayResponse matchResponse = matchEngineClient.displayMatchesOfCorporate(matchRequest);
                    corporatePrimaryViewResponse.setMatchesList(matchResponse.getMatchDisplayObjectList());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }



        // get details of CorporateApplicantDetail
        CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.getByApplicationAndUserId(userId, toApplicationId);
        // set value to response
        if (corporateApplicantDetail != null) {
            BeanUtils.copyProperties(corporateApplicantDetail, corporatePrimaryViewResponse);
            if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getConstitutionId()))
                corporatePrimaryViewResponse.setConstitution(Constitution.getById(corporateApplicantDetail.getConstitutionId()).getValue());
            if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getEstablishmentMonth()))
                corporatePrimaryViewResponse.setEstablishmentMonth(EstablishmentMonths.getById(corporateApplicantDetail.getEstablishmentMonth()).getValue());
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
                    e.printStackTrace();
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
                        corporatePrimaryViewResponse.setAddOfficeCity(masterResponse.getValue());

                    } else {
                        corporatePrimaryViewResponse.setCity("NA");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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
                        corporatePrimaryViewResponse.setAddOfficestate(masterResponse.getValue());
                    } else {
                        corporatePrimaryViewResponse.setState("NA");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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
                        corporatePrimaryViewResponse.setAddOfficecountry(masterResponse.getValue());
                    } else {
                        corporatePrimaryViewResponse.setCountry("NA");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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
                    e.printStackTrace();
                }
            }

            //key vertical sector
           List<Long> keyVerticalSectorId = new ArrayList<>();
            //getting sector id from mapping
            if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getKeyVerticalSector()))
                keyVerticalSectorId.add(corporateApplicantDetail.getKeyVerticalSector());

            try
            {
            OneFormResponse formResponse=oneFormClient.getIndustrySecByMappingId(corporateApplicantDetail.getKeyVerticalSector());
           // SectorIndustryModel oneResponseDataList = (SectorIndustryModel) formResponse    .getData();
            
            SectorIndustryModel sectorIndustryModel= MultipleJSONObjectHelper
            .getObjectFromMap((Map)formResponse
                    .getData(), SectorIndustryModel.class);
            
            //get key vertical sector value
            OneFormResponse oneFormResponse = oneFormClient.getSectorById(Arrays.asList(sectorIndustryModel.getSectorId()));
            List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
                    .getListData();
            if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
                MasterResponse masterResponse = MultipleJSONObjectHelper
                        .getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
                corporatePrimaryViewResponse.setKeyVericalSector(masterResponse.getValue());
            } else {
                corporatePrimaryViewResponse.setKeyVericalSector("NA");
            }
            }
            catch (Exception e) {
            //System.o	
            	e.printStackTrace();
			}
            
            


            //key vertical Subsector
            try
            {
            if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getKeyVerticalSubsector()))
            {
            	OneFormResponse oneFormResponse=oneFormClient.getSubSecNameByMappingId(corporateApplicantDetail.getKeyVerticalSubsector());
            	corporatePrimaryViewResponse.setKeyVericalSubsector((String)oneFormResponse.getData());
            }
            }
            catch (Exception e) {
				// TODO: handle exception
            	logger.warn("error while getting key vertical sub-sector");
			}

           
           





        }
            // get value of working capital data
            PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateRepository.getByApplicationAndUserId(toApplicationId, userId);
            if (primaryCorporateDetail != null) {
                // set value to response
                BeanUtils.copyProperties(primaryCorporateDetail, corporatePrimaryViewResponse);
                if(!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getCurrencyId())&&!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getDenominationId())) {
                    corporatePrimaryViewResponse.setCurrencyDenomination(Currency.getById(primaryCorporateDetail.getCurrencyId()).getValue()
                            + " in "
                            + Denomination.getById(primaryCorporateDetail.getDenominationId()).getValue());
                }
                corporatePrimaryViewResponse.setLoanType(primaryCorporateDetail.getProductId() != null ? LoanType.getById(primaryCorporateDetail.getProductId()).getValue() : null);
                corporatePrimaryViewResponse.setLoanAmount(primaryCorporateDetail.getAmount() != null ? String.valueOf(primaryCorporateDetail.getAmount()) : null);
                corporatePrimaryViewResponse.setGstIn(corporateApplicantDetail.getGstIn() != null ? String.valueOf(corporateApplicantDetail.getGstIn()) : null);

                corporatePrimaryViewResponse.setPurposeOfLoan(CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getPurposeOfLoanId()) ? null : PurposeOfLoan.getById(primaryCorporateDetail.getPurposeOfLoanId()).getValue().toString());
                corporatePrimaryViewResponse.setBusinessAssetAmount(primaryCorporateDetail.getBusinessAssetAmount() != null ? String.valueOf(primaryCorporateDetail.getBusinessAssetAmount()) : null);
                corporatePrimaryViewResponse.setWcAmount(primaryCorporateDetail.getWcAmount() != null ? String.valueOf(primaryCorporateDetail.getWcAmount()) : null);
                corporatePrimaryViewResponse.setOtherAmt(primaryCorporateDetail.getOtherAmt() != null ? String.valueOf(primaryCorporateDetail.getOtherAmt()) : null);

                corporatePrimaryViewResponse.setHaveCollateralSecurity(primaryCorporateDetail.getHaveCollateralSecurity() != null ? String.valueOf(primaryCorporateDetail.getHaveCollateralSecurity()) : null);
                corporatePrimaryViewResponse.setCollateralSecurityAmount(primaryCorporateDetail.getCollateralSecurityAmount() != null ? String.valueOf(primaryCorporateDetail.getCollateralSecurityAmount()) : null);
                //workingCapitalPrimaryViewResponse.setSharePriceFace(primaryWorkingCapitalLoanDetail.getSharePriceFace());
                //workingCapitalPrimaryViewResponse.setSharePriceMarket(primaryWorkingCapitalLoanDetail.getSharePriceMarket());
                if (!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getModifiedDate()))
                    corporatePrimaryViewResponse.setDateOfProposal(primaryCorporateDetail.getModifiedDate() != null ? DATE_FORMAT.format(primaryCorporateDetail.getModifiedDate()) : null);
            }


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
                    directorBackgroundDetailResponse.setTotalExperience(directorBackgroundDetailRequest.getTotalExperience().toString());
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
//				financialArrangementsDetailResponse.setRelationshipSince(financialArrangementsDetailRequest.getRelationshipSince());
                    financialArrangementsDetailResponse.setOutstandingAmount(financialArrangementsDetailRequest.getOutstandingAmount());
                    financialArrangementsDetailResponse.setSecurityDetails(financialArrangementsDetailRequest.getSecurityDetails());
                    financialArrangementsDetailResponse.setAmount(financialArrangementsDetailRequest.getAmount());
                    //			financialArrangementsDetailResponse.setLenderType(LenderType.getById(financialArrangementsDetailRequest.getLenderType()).getValue());
                    financialArrangementsDetailResponse.setLoanDate(financialArrangementsDetailRequest.getLoanDate());
                    financialArrangementsDetailResponse.setLoanType(financialArrangementsDetailRequest.getLoanType());
                    financialArrangementsDetailResponse.setFinancialInstitutionName(financialArrangementsDetailRequest.getFinancialInstitutionName());
                    //			financialArrangementsDetailResponse.setFacilityNature(NatureFacility.getById(financialArrangementsDetailRequest.getFacilityNatureId()).getValue());
                    //financialArrangementsDetailResponse.setAddress(financialArrangementsDetailRequest.getAddress());
                    financialArrangementsDetailResponseList.add(financialArrangementsDetailResponse);
                }
                corporatePrimaryViewResponse.setFinancialArrangementsDetailResponseList(financialArrangementsDetailResponseList);

            } catch (Exception e) {
                logger.error("Problem to get Data of Financial Arrangements Details {}", e);
            }
         
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
	    		corporatePrimaryViewResponse.setFinancialInputRequest(financialInputRequest);
	    		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
    		
    	

            //GET DOCUMENTS
            DocumentRequest documentRequest = new DocumentRequest();
            documentRequest.setApplicationId(toApplicationId);
            documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
            documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_PROFIEL_PICTURE);
            try {
                DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
                corporatePrimaryViewResponse.setProfilePic(documentResponse.getDataList());
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_BANK_STATEMENT);
            try {
                DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
                corporatePrimaryViewResponse.setBankStatement(documentResponse.getDataList());
            } catch (DocumentException e) {
                e.printStackTrace();
            }
            documentRequest.setProductDocumentMappingId(DocumentAlias.CIBIL_REPORT_MSME_COMPANY);
            try {
                DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
                corporatePrimaryViewResponse.setCibilReport(documentResponse.getDataList());
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        return corporatePrimaryViewResponse;
    }
}
