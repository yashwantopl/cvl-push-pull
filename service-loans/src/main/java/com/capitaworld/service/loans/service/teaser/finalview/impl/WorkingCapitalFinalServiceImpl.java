package com.capitaworld.service.loans.service.teaser.finalview.impl;


import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryWorkingCapitalLoanDetail;
import com.capitaworld.service.loans.model.*;
import com.capitaworld.service.loans.model.corporate.FinalWorkingCapitalLoanRequest;
import com.capitaworld.service.loans.model.teaser.finalview.*;
import com.capitaworld.service.loans.repository.fundseeker.corporate.*;
import com.capitaworld.service.loans.service.common.DocumentManagementService;
import com.capitaworld.service.loans.service.fundseeker.corporate.*;
import com.capitaworld.service.loans.service.teaser.finalview.WorkingCapitalFinalService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.client.*;
import com.capitaworld.service.oneform.enums.*;
import com.capitaworld.service.oneform.model.IndustrySectorSubSectorTeaserRequest;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dhaval on 25-May-17.
 */
@Service
@Transactional
public class WorkingCapitalFinalServiceImpl implements WorkingCapitalFinalService {

    @Autowired
    private BoardOfDirectorsDetailRepository boardOfDirectorsDetailRepository;

    @Autowired
    private StrategicAlliancesDetailRepository strategicAlliancesDetailRepository;

    @Autowired
    private KeyManagementDetailRepository keyManagementDetailRepository;

    @Autowired
    private EmployeesCategoryBreaksDetailRepository employeesCategoryBreaksDetailRepository;

    @Autowired
    private TechnologyPositioningDetailRepository technologyPositioningDetailRepository;

    @Autowired
    private RevenueAndOrderBookDetailRepository revenueAndOrderBookDetailRepository;

    @Autowired
    private CapacityDetailRepository capacityDetailRepository;

    @Autowired
    private AvailabilityProposedPlantDetailRepository availabilityProposedPlantDetailRepository;

    @Autowired
    private RequirementsAndAvailabilityRawMaterialsDetailRepository requirementsAndAvailabilityRawMaterialsDetailRepository;

    @Autowired
    private ScotAnalysisDetailRepository scotAnalysisDetailRepository;

    @Autowired
    private DprUserDataDetailRepository dprUserDataDetailRepository;

    @Autowired
    private DocumentManagementService documentManagementService;

    @Autowired
    private DriverForFutureGrowthDetailRepository driverForFutureGrowthDetailRepository;

    @Autowired
    private ProjectImplementationScheduleDetailRepository projectImplementationScheduleDetailRepository;

    @Autowired
    private CorporateApplicantDetailRepository corporateApplicantDetailRepository;

    @Autowired
    private PrimaryWorkingCapitalLoanDetailRepository primaryWorkingCapitalLoanDetailRepository;

    @Autowired
    private ProposedProductDetailsService proposedProductDetailsService;

    @Autowired
    private AchievmentDetailsService achievmentDetailsService;

    @Autowired
    private CreditRatingOrganizationDetailsService creditRatingOrganizationDetailsService;

    @Autowired
    private OwnershipDetailsService ownershipDetailsService;

    @Autowired
    private PromotorBackgroundDetailsService promotorBackgroundDetailsService;

    @Autowired
    private PastFinancialEstiamateDetailsService pastFinancialEstiamateDetailsService;

    @Autowired
    private FutureFinancialEstimatesDetailsService futureFinancialEstimatesDetailsService;

    @Autowired
    private ExistingProductDetailsService existingProductDetailsService;

    @Autowired
    private SecurityCorporateDetailsService securityCorporateDetailsService;

    @Autowired
    private FinancialArrangementDetailsService financialArrangementDetailsService;

    @Autowired
    private Environment environment;

    @Autowired
    private IndustrySectorRepository industrySectorRepository;

    @Autowired
    private SubSectorRepository subSectorRepository;

    @Autowired
    private AssociatedConcernDetailService associatedConcernDetailService;

    @Autowired
    private GuarantorsCorporateDetailService guarantorsCorporateDetailService;

    @Autowired
    private MonthlyTurnoverDetailService monthlyTurnoverDetailService;

    @Autowired
    private FinalWorkingCapitalLoanService finalWorkingCapitalLoanService;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    protected static final String ONE_FORM_URL = "oneForm";
    protected static final String USERS_URL = "userURL";

    private static final Logger logger = LoggerFactory.getLogger(WorkingCapitalFinalServiceImpl.class);

    @Override
    public WorkingCapitalFinalViewResponse getWorkingCapitalFinalViewDetails(Long toApplicationId) {
        LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(toApplicationId);
        Long userId = applicationMaster.getUserId();
        //create response object
        WorkingCapitalFinalViewResponse response = new WorkingCapitalFinalViewResponse();

        List<Object> dprList = new ArrayList<Object>();
        //get list of uploads of final and profile picture
        try {
            response.setProfilePic(documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_PROFIEL_PICTURE));
            response.setLastAuditedAnnualReportList(documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_LAST_AUDITED_ANNUAL_REPORT));
            response.setSanctionLetterCopyList(documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_SANCTION_LETTER_COPY));
            response.setLastITReturnList(documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_LAST_IT_RETURN));
            response.setNetWorthStatementOfdirectorsList(documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_NET_WORTH_STATEMENT_OF_DIRECTORS));
            response.setProvisionalFinancialsList(documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_PROVISIONAL_FINANCIALS));
            response.setPanOfDirectorsList(documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_PAN_OF_DIRECTORS_CERTIFICATE_OF_INCORPORATION));
            response.setDetailedListOfShareholdersList(documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_DETAILED_LIST_OF_SHAREHOLDERS));
            response.setPhotoOfDirectorsList(documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_PHOTO_OF_DIRECTORS));
            dprList = documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WC_DPR_OUR_FORMAT));
            response.setDprList(dprList);
            response.setCmaList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WC_CMA)));
            response.setBsFormatList(documentManagementService.getDocumentDetails(toApplicationId,DocumentAlias.UERT_TYPE_APPLICANT, Long.valueOf(DocumentAlias.WC_COMPANY_ACT)));
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        //if DPR our format not upload no need get data of DPR
        if (dprList.size()>0) {
            response.setIsDprUploaded(true);
            List<BoardOfDirectorsResponse> boardOfDirectorsResponseList = boardOfDirectorsDetailRepository.listByApplicationId(toApplicationId);
            List<StrategicAlliancesResponse> strategicAlliancesResponseList = strategicAlliancesDetailRepository.listByApplicationId(toApplicationId);
            List<KeyManagementResponse> keyManagementResponseList = keyManagementDetailRepository.listByApplicationId(toApplicationId);
            List<EmployeesCategoryBreaksResponse> employeesCategoryBreaksResponseList = employeesCategoryBreaksDetailRepository.listByApplicationId(toApplicationId);
            List<TechnologyPositioningResponse> technologyPositioningResponseList = technologyPositioningDetailRepository.listByApplicationId(toApplicationId);
            List<RevenueAndOrderBookResponse> revenueAndOrderBookResponseList = revenueAndOrderBookDetailRepository.listByApplicationId(toApplicationId);
            DriverForFutureGrowthResponse driverForFutureGrowthResponse = driverForFutureGrowthDetailRepository.listByApplicationId(toApplicationId).size() > 0 ? driverForFutureGrowthDetailRepository.listByApplicationId(toApplicationId).get(0) : null;
            List<ProjectImplementationScheduleResponse> projectImplementationScheduleResponseList = projectImplementationScheduleDetailRepository.listByApplicationId(toApplicationId);
            List<CapacityDetailResponse> capacityDetailResponses = capacityDetailRepository.listByApplicationId(toApplicationId);
            List<AvailabilityProposedPlantDetailResponse> availabilityProposedPlantDetailResponses = availabilityProposedPlantDetailRepository.listByApplicationId(toApplicationId);
            List<RequirementsAndAvailabilityRawMaterialsDetailResponse> requirementsAndAvailabilityRawMaterialsDetailResponses = requirementsAndAvailabilityRawMaterialsDetailRepository.listByApplicationId(toApplicationId);
            ScotAnalysisDetailResponse scotAnalysisDetailResponses = scotAnalysisDetailRepository.listByApplicationId(toApplicationId).size() > 0 ? scotAnalysisDetailRepository.listByApplicationId(toApplicationId).get(0) : null;
            DprUserDataDetailResponse dprUserDataDetailResponses = dprUserDataDetailRepository.listByApplicationId(toApplicationId).size() > 0 ? dprUserDataDetailRepository.listByApplicationId(toApplicationId).get(0) : null;

            response.setAvailabilityProposedPlantDetailResponse(availabilityProposedPlantDetailResponses);
            response.setBoardOfDirectorsResponseList(boardOfDirectorsResponseList);
            response.setCapacityDetailResponses(capacityDetailResponses);
            response.setDprUserDataDetailResponses(dprUserDataDetailResponses);
            response.setEmployeesCategoryBreaksResponseList(employeesCategoryBreaksResponseList);
            response.setKeyManagementResponseList(keyManagementResponseList);
            response.setRequirementsAndAvailabilityRawMaterialsDetailResponse(requirementsAndAvailabilityRawMaterialsDetailResponses);
            response.setRevenueAndOrderBookResponseList(revenueAndOrderBookResponseList);
            response.setScotAnalysisDetailResponses(scotAnalysisDetailResponses);
            response.setStrategicAlliancesResponseList(strategicAlliancesResponseList);
            response.setTechnologyPositioningResponseList(technologyPositioningResponseList);
            response.setProjectImplementationScheduleResponseList(projectImplementationScheduleResponseList);
            response.setDriverForFutureGrowthResponse(driverForFutureGrowthResponse);

            //set final working capital information
            try {
                FinalWorkingCapitalLoanRequest finalWorkingCapitalLoanRequest = finalWorkingCapitalLoanService.get(userId, toApplicationId);
                response.setTechnologyType(finalWorkingCapitalLoanRequest.getTechnologyTypeId() != null ? TypeTechnology.getById(finalWorkingCapitalLoanRequest.getTechnologyTypeId()).getValue() : null);
                response.setTechnologyPatented(finalWorkingCapitalLoanRequest.getTechnologyPatentedId() != null ? TechnologyPatented.getById(finalWorkingCapitalLoanRequest.getTechnologyPatentedId()).getValue() : null);
                response.setTechnologyRequiresUpgradation(finalWorkingCapitalLoanRequest.getTechnologyRequiresUpgradationId() != null ? TechnologyRequiresUpgradation.getById(finalWorkingCapitalLoanRequest.getTechnologyRequiresUpgradationId()).getValue() : null);
                response.setMarketPosition(finalWorkingCapitalLoanRequest.getMarketingPositioningId() != null ? MarketPosition.getById(finalWorkingCapitalLoanRequest.getMarketingPositioningId()).getValue() : null);
                response.setMarketPositioningTop(MarketPositioningTop.getById(finalWorkingCapitalLoanRequest.getMarketPositioningTopId()).getValue());
                response.setMarketShareTurnover(finalWorkingCapitalLoanRequest.getMarketShareTurnoverId() != null ? MarketShareTurnover.getById(finalWorkingCapitalLoanRequest.getMarketShareTurnoverId()).getValue() : null);
                response.setIndiaDistributionNetwork(finalWorkingCapitalLoanRequest.getIndiaDistributionNetworkId() != null ? IndiaDistributionNetwork.getById(finalWorkingCapitalLoanRequest.getIndiaDistributionNetworkId()).getValue() : null);
                response.setEnvironmentCertification(finalWorkingCapitalLoanRequest.getEnvironmentCertificationId() != null ? EnvironmentCertification.getById(finalWorkingCapitalLoanRequest.getEnvironmentCertificationId()).getValue() : null);
                response.setAccountingSystems(finalWorkingCapitalLoanRequest.getAccountingSystemsId() != null ? AccountingSystems.getById(finalWorkingCapitalLoanRequest.getAccountingSystemsId()).getValue() : null);
                response.setInternalAudit(finalWorkingCapitalLoanRequest.getInternalAuditId() != null ? InternalAudit.getById(finalWorkingCapitalLoanRequest.getInternalAuditId()).getValue() : null);
                response.setCompetence(finalWorkingCapitalLoanRequest.getCompetenceId() != null ? Competence.getById(finalWorkingCapitalLoanRequest.getCompetenceId()).getValue() : null);
                if (finalWorkingCapitalLoanRequest.getIsIsoCertified()){
                    response.setIsIsoCertified("Yes");
                }else{
                    response.setIsIsoCertified("No");
                }
                if (finalWorkingCapitalLoanRequest.getWhetherTechnologyIsTied()){
                    response.setWhetherTechnologyIsTied("Yes");
                }else{
                    response.setWhetherTechnologyIsTied("No");
                }
                //set overseas
                List<Integer> overseasIds = finalWorkingCapitalLoanRequest.getOverseasNetworkIds();
                String overseasString ="";
                for (int id:overseasIds){
                   overseasString+=OverseasNetwork.getById(id).getValue()+",";
                }
                response.setOverseasNetwork(overseasString);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{
            response.setIsDprUploaded(false);
        }


        //set registered email address and registered contact number
        UsersClient usersClient = new UsersClient(environment.getProperty(USERS_URL));
        UserResponse userResponse = usersClient.getEmailMobile(userId);
        try {
            UsersRequest usersRequest = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) userResponse.getData(), UsersRequest.class);
            if (usersRequest!=null) {
                response.setRegisteredEmailAddress(usersRequest.getEmail());
                response.setRegisteredContactNumber(usersRequest.getMobile());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        //get details of CorporateApplicantDetail
        CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.getByApplicationAndUserId(userId, toApplicationId);
        //set value to response
        if (corporateApplicantDetail != null) {
            BeanUtils.copyProperties(corporateApplicantDetail, response);
            response.setConstitution(Constitution.getById(corporateApplicantDetail.getConstitutionId()).getValue());
            response.setEstablishmentMonth(EstablishmentMonths.getById(corporateApplicantDetail.getEstablishmentMonth()).getValue());

            //set Establishment year
            EstablistmentYearClient establistmentYearClient = new EstablistmentYearClient(environment.getProperty(ONE_FORM_URL));
            OneFormResponse establishmentYearResponse = null;
            try {
                establishmentYearResponse = establistmentYearClient.send(Long.valueOf(corporateApplicantDetail.getEstablishmentYear()));
                List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) establishmentYearResponse.getListData();
                if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
                    MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
                    response.setEstablishmentYear(masterResponse.getValue());
                } else {
                    response.setEstablishmentYear("NA");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //set city
            List<Long> cityList = new ArrayList<>();
            cityList.add(corporateApplicantDetail.getRegisteredCityId());
            CityByCityListIdClient cityByCityListIdClient = new CityByCityListIdClient(environment.getProperty(ONE_FORM_URL));
            try {
                OneFormResponse oneFormResponse = cityByCityListIdClient.send(cityList);
                List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
                if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
                    MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
                    response.setCity(masterResponse.getValue());
                } else {
                    response.setCity("NA");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //set state
            List<Long> stateList = new ArrayList<>();
            stateList.add(Long.valueOf(corporateApplicantDetail.getRegisteredStateId()));
            StateListByStateListIdClient stateListByStateListIdClient = new StateListByStateListIdClient(environment.getProperty(ONE_FORM_URL));
            try {
                OneFormResponse oneFormResponse = stateListByStateListIdClient.send(stateList);
                List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
                if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
                    MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
                    response.setState(masterResponse.getValue());
                } else {
                    response.setState("NA");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //set country
            List<Long> countryList = new ArrayList<>();
            countryList.add(Long.valueOf(corporateApplicantDetail.getRegisteredCountryId()));
            CountryByCountryListIdClient countryByCountryListIdClient = new CountryByCountryListIdClient(environment.getProperty(ONE_FORM_URL));
            try {
                OneFormResponse oneFormResponse = countryByCountryListIdClient.send(countryList);
                List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
                if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
                    MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
                    response.setCountry(masterResponse.getValue());
                } else {
                    response.setCountry("NA");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            IndustryClient industryClient = new IndustryClient(environment.getProperty(ONE_FORM_URL));
            List<Long> keyVerticalFundingId = new ArrayList<>();
            keyVerticalFundingId.add(corporateApplicantDetail.getKeyVericalFunding());
            try {
                OneFormResponse oneFormResponse = industryClient.send(keyVerticalFundingId);
                List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
                if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
                    MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
                    response.setKeyVericalFunding(masterResponse.getValue());
                } else {
                    response.setKeyVericalFunding("NA");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        List<Long> industryList = industrySectorRepository.getIndustryByApplicationId(toApplicationId);
        List<Long> sectorList = industrySectorRepository.getSectorByApplicationId(toApplicationId);
        List<Long> subSectorList = subSectorRepository.getSubSectorByApplicationId(toApplicationId);


        IndustrySectorSubSectorTeaser industrySectorSubSectorTeaser = new IndustrySectorSubSectorTeaser (environment.getProperty(ONE_FORM_URL));
        IndustrySectorSubSectorTeaserRequest industrySectorSubSectorTeaserRequest=new IndustrySectorSubSectorTeaserRequest();
        industrySectorSubSectorTeaserRequest.setIndustryList(industryList);
        industrySectorSubSectorTeaserRequest.setSectorList(sectorList);
        industrySectorSubSectorTeaserRequest.setSubSectorList(subSectorList);
        try {
            OneFormResponse oneFormResponse=industrySectorSubSectorTeaser.send(industrySectorSubSectorTeaserRequest);
            response.setIndustrySector(oneFormResponse.getListData());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //get value of working capital data
        PrimaryWorkingCapitalLoanDetail primaryWorkingCapitalLoanDetail = primaryWorkingCapitalLoanDetailRepository.getByApplicationAndUserId(toApplicationId, userId);
        if(primaryWorkingCapitalLoanDetail!=null) {
            //set value to response
            BeanUtils.copyProperties(primaryWorkingCapitalLoanDetail, response);
            response.setCurrencyDenomination(Currency.getById(primaryWorkingCapitalLoanDetail.getCurrencyId()).getValue() + " in " + Denomination.getById(primaryWorkingCapitalLoanDetail.getDenominationId()).getValue());
            response.setLoanType(primaryWorkingCapitalLoanDetail.getName());
            response.setLoanAmount(String.valueOf(primaryWorkingCapitalLoanDetail.getAmount()));
            response.setDateOfProposal(DATE_FORMAT.format(primaryWorkingCapitalLoanDetail.getModifiedDate()));
            response.setProjectBrief(primaryWorkingCapitalLoanDetail.getProjectBrief());
        }

        //get value of proposed product and set in response
        try {
            response.setProposedProductDetailRequestList(proposedProductDetailsService.getProposedProductDetailList(toApplicationId, userId));
        } catch (Exception e) {
            logger.error("Problem to get Data of Proposed Product {}", e);
        }

        //get value of Existing product and set in response
        try {
            response.setExistingProductDetailRequestList(existingProductDetailsService.getExistingProductDetailList(toApplicationId, userId));
        } catch (Exception e) {
            logger.error("Problem to get Data of Existing Product {}", e);
        }

        //get value of achievement details and set in response
        try {
            response.setAchievementDetailList(achievmentDetailsService.getAchievementDetailList(toApplicationId, userId));
        } catch (Exception e) {
            logger.error("Problem to get Data of Achievement Details {}", e);
        }

        //get value of Credit Rating and set in response
        try {
            List<CreditRatingOrganizationDetailRequest> creditRatingOrganizationDetailRequestList = creditRatingOrganizationDetailsService.getcreditRatingOrganizationDetailsList(toApplicationId, userId);
            List<CreditRatingOrganizationDetailResponse> creditRatingOrganizationDetailResponseList = new ArrayList<>();
            for (CreditRatingOrganizationDetailRequest creditRatingOrganizationDetailRequest:creditRatingOrganizationDetailRequestList){
                CreditRatingOrganizationDetailResponse creditRatingOrganizationDetailResponse = new CreditRatingOrganizationDetailResponse();
                creditRatingOrganizationDetailResponse.setAmount(creditRatingOrganizationDetailRequest.getAmount());
                creditRatingOrganizationDetailResponse.setCreditRatingFund(CreditRatingFund.getById(creditRatingOrganizationDetailRequest.getCreditRatingFundId()).getValue());
                RatingByRatingIdClient ratingOptionClient = new RatingByRatingIdClient(environment.getProperty(ONE_FORM_URL));
                OneFormResponse oneFormResponse = ratingOptionClient.send(Long.valueOf(creditRatingOrganizationDetailRequest.getCreditRatingOptionId()));
                MasterResponse masterResponse= MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>)oneFormResponse.getData(),MasterResponse.class);
                if (masterResponse!=null ) {
                    creditRatingOrganizationDetailResponse.setCreditRatingOption(masterResponse.getValue());
                }else{
                    response.setKeyVericalFunding("NA");
                }
                creditRatingOrganizationDetailResponse.setCreditRatingTerm(CreditRatingTerm.getById(creditRatingOrganizationDetailRequest.getCreditRatingTermId()).getValue());
                creditRatingOrganizationDetailResponse.setRatingAgency(RatingAgency.getById(creditRatingOrganizationDetailRequest.getRatingAgencyId()).getValue());
                creditRatingOrganizationDetailResponse.setFacilityName(creditRatingOrganizationDetailRequest.getFacilityName());
                creditRatingOrganizationDetailResponseList.add(creditRatingOrganizationDetailResponse);
            }
            response.setCreditRatingOrganizationDetailResponse(creditRatingOrganizationDetailResponseList);
        } catch (Exception e) {
            logger.error("Problem to get Data of Credit Rating {}", e);
        }

        // set short term rating option
        try {
            List<String> shortTermValueList = new ArrayList<String>();
            List<Integer> shortTermIdList = creditRatingOrganizationDetailsService.getShortTermCreditRatingForTeaser(toApplicationId, userId);
            for (Integer shortTermId : shortTermIdList) {
                RatingByRatingIdClient ratingOptionClient = new RatingByRatingIdClient(environment.getProperty(ONE_FORM_URL));
                OneFormResponse oneFormResponse = ratingOptionClient.send(Long.valueOf(shortTermId.toString()));
                MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) oneFormResponse.getData(), MasterResponse.class);
                if (masterResponse != null) {
                    shortTermValueList.add(masterResponse.getValue());
                } else {
                    shortTermValueList.add(CommonUtils.NOT_APPLICABLE);
                }
                response.setShortTermRating(shortTermValueList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // set long term rating option
        try {
            List<String> longTermValueList = new ArrayList<String>();
            List<Integer> longTermIdList = creditRatingOrganizationDetailsService.getLongTermCreditRatingForTeaser(toApplicationId, userId);
            for (Integer shortTermId : longTermIdList) {
                RatingByRatingIdClient ratingOptionClient = new RatingByRatingIdClient(environment.getProperty(ONE_FORM_URL));
                OneFormResponse oneFormResponse = ratingOptionClient.send(Long.valueOf(shortTermId.toString()));
                MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) oneFormResponse.getData(), MasterResponse.class);
                if (masterResponse != null) {
                    longTermValueList.add(masterResponse.getValue());
                } else {
                    longTermValueList.add(CommonUtils.NOT_APPLICABLE);
                }
            }
            response.setLongTermRating(longTermValueList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //get value of Ownership Details and set in response
        try {
            List<OwnershipDetailRequest> ownershipDetailRequestsList = ownershipDetailsService.getOwnershipDetailList(toApplicationId, userId);
            List<OwnershipDetailResponse> ownershipDetailResponseList = new ArrayList<>();
            for (OwnershipDetailRequest ownershipDetailRequest:ownershipDetailRequestsList){
                OwnershipDetailResponse ownershipDetailResponse = new OwnershipDetailResponse();
                ownershipDetailResponse.setRemarks(ownershipDetailRequest.getRemarks());
                ownershipDetailResponse.setStackPercentage(ownershipDetailRequest.getStackPercentage());
                ownershipDetailResponse.setShareHoldingCategory(ShareHoldingCategory.getById(ownershipDetailRequest.getShareHoldingCategoryId()).getValue());
                ownershipDetailResponseList.add(ownershipDetailResponse);
            }
            response.setOwnershipDetailResponseList(ownershipDetailResponseList);

        } catch (Exception e) {
            logger.error("Problem to get Data of Ownership Details {}", e);
        }

        //get value of Promotor Background and set in response
        try {
            List<PromotorBackgroundDetailRequest>  promotorBackgroundDetailRequestList = promotorBackgroundDetailsService.getPromotorBackgroundDetailList(toApplicationId, userId);
            List<PromotorBackgroundDetailResponse> promotorBackgroundDetailResponseList = new ArrayList<>();
            for (PromotorBackgroundDetailRequest promotorBackgroundDetailRequest:promotorBackgroundDetailRequestList){
                PromotorBackgroundDetailResponse promotorBackgroundDetailResponse = new PromotorBackgroundDetailResponse();
                promotorBackgroundDetailResponse.setAchievements(promotorBackgroundDetailRequest.getAchivements());
                promotorBackgroundDetailResponse.setAddress(promotorBackgroundDetailRequest.getAddress());
                promotorBackgroundDetailResponse.setAge(promotorBackgroundDetailRequest.getAge());
                promotorBackgroundDetailResponse.setPanNo(promotorBackgroundDetailRequest.getPanNo());
                promotorBackgroundDetailResponse.setPromotorsName(Title.getById(promotorBackgroundDetailRequest.getSalutationId()).getValue() + " " + promotorBackgroundDetailRequest.getPromotorsName());
                promotorBackgroundDetailResponse.setQualification(promotorBackgroundDetailRequest.getQualification());
                promotorBackgroundDetailResponse.setTotalExperience(promotorBackgroundDetailRequest.getTotalExperience());
                promotorBackgroundDetailResponseList.add(promotorBackgroundDetailResponse);
            }
            response.setPromotorBackgroundDetailResponseList(promotorBackgroundDetailResponseList);
        } catch (Exception e) {
            logger.error("Problem to get Data of Promotor Background {}", e);
        }

        //get value of Past Financial and set in response
        try {
            response.setPastFinancialEstimatesDetailRequestList(pastFinancialEstiamateDetailsService.getFinancialListData(userId, toApplicationId));
        } catch (Exception e) {
            logger.error("Problem to get Data of Past Financial {}", e);
        }

        //get value of Future Projection and set in response
        try {
            response.setFutureFinancialEstimatesDetailRequestList(futureFinancialEstimatesDetailsService.getFutureFinancialEstimateDetailsList(toApplicationId, userId));
        } catch (Exception e) {
            logger.error("Problem to get Data of Future Projection {}", e);
        }

        //get value of Security and set in response
        try {
            response.setSecurityCorporateDetailRequestList(securityCorporateDetailsService.getsecurityCorporateDetailsList(toApplicationId, userId));
        } catch (Exception e) {
            logger.error("Problem to get Data of Security Details {}", e);
        }

        //get value of Financial Arrangements and set in response
        try {
            List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList = financialArrangementDetailsService.getFinancialArrangementDetailsList(toApplicationId, userId);
            List<FinancialArrangementsDetailResponse> financialArrangementsDetailResponseList = new ArrayList<>();
            for (FinancialArrangementsDetailRequest financialArrangementsDetailRequest:financialArrangementsDetailRequestList){
                FinancialArrangementsDetailResponse financialArrangementsDetailResponse = new FinancialArrangementsDetailResponse();
                financialArrangementsDetailResponse.setAmount(financialArrangementsDetailRequest.getAmount());
                financialArrangementsDetailResponse.setFinancialInstitutionName(financialArrangementsDetailRequest.getFinancialInstitutionName());
                financialArrangementsDetailResponse.setFacilityNature(NatureFacility.getById(financialArrangementsDetailRequest.getFacilityNatureId()).getValue());
                financialArrangementsDetailResponseList.add(financialArrangementsDetailResponse);
            }
            response.setFinancialArrangementsDetailResponseList(financialArrangementsDetailResponseList);
        } catch (Exception e) {
            logger.error("Problem to get Data of Financial Arrangements Details {}", e);
        }

        //get data of Associated Concern
        try {
            response.setAssociatedConcernDetailRequests(associatedConcernDetailService.getAssociatedConcernsDetailList(toApplicationId, userId));
        } catch (Exception e) {
            logger.error("Problem to get Data of Associated Concerns {}",e);
        }

        //get data of Details of Guarantors
        try {
            response.setGuarantorsCorporateDetailRequestList(guarantorsCorporateDetailService.getGuarantorsCorporateDetailList(toApplicationId, userId));
        } catch (Exception e) {
            logger.error("Problem to get Data of Details of Guarantor {}",e);
        }

        //get data of Monthly Turnover
        try {
            response.setMonthlyTurnoverDetailRequestList(monthlyTurnoverDetailService.getMonthlyTurnoverDetailList(toApplicationId,userId));
        } catch (Exception e) {
            logger.error("Problem to get Data of Monthly Turnover {}", e);
        }
        return response;
    }
}
