package com.capitaworld.service.loans.service.teaser.primaryview.impl;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.util.CommonUtil;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryWorkingCapitalLoanDetail;
import com.capitaworld.service.loans.model.*;
import com.capitaworld.service.loans.model.teaser.primaryview.WorkingCapitalPrimaryViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryWorkingCapitalLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SubSectorRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.*;
import com.capitaworld.service.loans.service.teaser.primaryview.WorkingCapitalPrimaryViewService;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.client.IndustryClient;
import com.capitaworld.service.oneform.enums.*;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dhaval on 19-May-17.
 */
@Service
@Transactional
public class WorkingCapitalPrimaryViewServiceImpl implements WorkingCapitalPrimaryViewService {

    private static final Logger logger = LoggerFactory.getLogger(WorkingCapitalPrimaryViewServiceImpl.class);

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

    protected static final String DMS_URL = "dmsURL";
    protected static final String ONE_FORM_URL = "oneForm";

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/mm/yyyy");

    @Override
    public WorkingCapitalPrimaryViewResponse getWorkingCapitalPrimaryViewDetails(Long toApplicationId,Long userId) {
        WorkingCapitalPrimaryViewResponse workingCapitalPrimaryViewResponse = new WorkingCapitalPrimaryViewResponse();
        //get details of CorporateApplicantDetail
        CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.getByApplicationAndUserId(userId, toApplicationId);
        //set value to response

        BeanUtils.copyProperties(corporateApplicantDetail, workingCapitalPrimaryViewResponse);
        workingCapitalPrimaryViewResponse.setConstitution(Constitution.getById(corporateApplicantDetail.getConstitutionId()).getValue());
        workingCapitalPrimaryViewResponse.setEstablishmentMonth(EstablishmentMonths.getById(corporateApplicantDetail.getEstablishmentMonth()).getValue());

        List<Long> industryList = industrySectorRepository.getIndustryByApplicationId(toApplicationId);
        List<Long> sectorList = industrySectorRepository.getSectorByApplicationId(toApplicationId);
        List<Long> subSectorList = subSectorRepository.getSubSectorByApplicationId(toApplicationId);

        List<List<Long>> list = new ArrayList<>();
        list.add(industryList);
        list.add(sectorList);
        list.add(subSectorList);

        System.out.println(list);


        IndustryClient industryClient = new IndustryClient(environment.getProperty(ONE_FORM_URL));
        List<Long> keyVerticalFundingId = new ArrayList<>();
        keyVerticalFundingId.add(corporateApplicantDetail.getKeyVericalFunding());
        try {
            OneFormResponse oneFormResponse = industryClient.send(keyVerticalFundingId);
            List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
            if (oneResponseDataList!=null && !oneResponseDataList.isEmpty()) {
                MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
                workingCapitalPrimaryViewResponse.setKeyVericalFunding(masterResponse.getValue());
            }else{
                workingCapitalPrimaryViewResponse.setKeyVericalFunding("NA");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //get value of working capital data
        PrimaryWorkingCapitalLoanDetail primaryWorkingCapitalLoanDetail = primaryWorkingCapitalLoanDetailRepository.getByApplicationAndUserId(toApplicationId, userId);
        //set value to response
        BeanUtils.copyProperties(primaryWorkingCapitalLoanDetail, workingCapitalPrimaryViewResponse);
        workingCapitalPrimaryViewResponse.setCurrencyDenomination(Currency.getById(primaryWorkingCapitalLoanDetail.getCurrencyId()).getValue() + " in " + Denomination.getById(primaryWorkingCapitalLoanDetail.getDenominationId()).getValue());
        workingCapitalPrimaryViewResponse.setLoanType(primaryWorkingCapitalLoanDetail.getName());
        workingCapitalPrimaryViewResponse.setDateOfProposal(DATE_FORMAT.format(primaryWorkingCapitalLoanDetail.getModifiedDate()));
        //get value of proposed product and set in response
        try {
            workingCapitalPrimaryViewResponse.setProposedProductDetailRequestList(proposedProductDetailsService.getProposedProductDetailList(toApplicationId,userId));
        } catch (Exception e) {
            logger.error("Problem to get Data of Proposed Product {}", e);
        }

        //get value of Existing product and set in response
        try {
            workingCapitalPrimaryViewResponse.setExistingProductDetailRequestList(existingProductDetailsService.getExistingProductDetailList(toApplicationId,userId));
        } catch (Exception e) {
            logger.error("Problem to get Data of Proposed Product {}", e);
        }

        //get value of achievement details and set in response
        try {
            workingCapitalPrimaryViewResponse.setAchievementDetailList(achievmentDetailsService.getAchievementDetailList(toApplicationId, userId));
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

               /* List<Long> ratingoptionId = new ArrayList<>();
                ratingoptionId.add(Long.valueOf(creditRatingOrganizationDetailRequest.getCreditRatingOptionId()));
                RatingOptionClient ratingOptionClient = new RatingOptionClient(environment.getProperty(ONE_FORM_URL));
                OneFormResponse oneFormResponse = ratingOptionClient.send(ratingoptionId);
                oneFormResponse.getListData();*/

                //creditRatingOrganizationDetailResponse.setCreditRatingOption(Credi creditRatingOrganizationDetailRequest.getCreditRatingOptionId());
                creditRatingOrganizationDetailResponse.setCreditRatingTerm(CreditRatingTerm.getById(creditRatingOrganizationDetailRequest.getCreditRatingTermId()).getValue());
                creditRatingOrganizationDetailResponse.setRatingAgency(RatingAgency.getById(creditRatingOrganizationDetailRequest.getRatingAgencyId()).getValue());
                creditRatingOrganizationDetailResponse.setFacilityName(creditRatingOrganizationDetailRequest.getFacilityName());
                creditRatingOrganizationDetailResponseList.add(creditRatingOrganizationDetailResponse);
            }
            workingCapitalPrimaryViewResponse.setCreditRatingOrganizationDetailResponse(creditRatingOrganizationDetailResponseList);
        } catch (Exception e) {
            logger.error("Problem to get Data of Achievement Details {}", e);
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
            workingCapitalPrimaryViewResponse.setOwnershipDetailResponseList(ownershipDetailResponseList);

        } catch (Exception e) {
            logger.error("Problem to get Data of Achievement Details {}", e);
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
            workingCapitalPrimaryViewResponse.setPromotorBackgroundDetailResponseList(promotorBackgroundDetailResponseList);
        } catch (Exception e) {
            logger.error("Problem to get Data of Achievement Details {}", e);
        }

        //get value of Past Financial and set in response
        try {
            workingCapitalPrimaryViewResponse.setPastFinancialEstimatesDetailRequestList(pastFinancialEstiamateDetailsService.getFinancialListData(userId, toApplicationId));
        } catch (Exception e) {
            logger.error("Problem to get Data of Achievement Details {}", e);
        }

        //get value of Future Projection and set in response
        try {
            workingCapitalPrimaryViewResponse.setFutureFinancialEstimatesDetailRequestList(futureFinancialEstimatesDetailsService.getFutureFinancialEstimateDetailsList(toApplicationId,userId));
        } catch (Exception e) {
            logger.error("Problem to get Data of Achievement Details {}", e);
        }

        //get value of Security and set in response
        try {
            workingCapitalPrimaryViewResponse.setSecurityCorporateDetailRequestList(securityCorporateDetailsService.getsecurityCorporateDetailsList(toApplicationId,userId));
        } catch (Exception e) {
            logger.error("Problem to get Data of Achievement Details {}", e);
        }

        //get value of Security and set in response
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
            workingCapitalPrimaryViewResponse.setFinancialArrangementsDetailResponseList(financialArrangementsDetailResponseList);

        } catch (Exception e) {
            logger.error("Problem to get Data of Achievement Details {}", e);
        }

        DMSClient dmsClient = new DMSClient(environment.getProperty(DMS_URL));
        DocumentRequest documentRequest = new DocumentRequest();
        documentRequest.setApplicationId(toApplicationId);
        documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
        documentRequest.setProductDocumentMappingId(1l);
        try {
            DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
            workingCapitalPrimaryViewResponse.setBrochureList(documentResponse.getDataList());
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        documentRequest.setApplicationId(toApplicationId);
        documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
        documentRequest.setProductDocumentMappingId(2l);
        try {
            DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
            workingCapitalPrimaryViewResponse.setCertificateList(documentResponse.getDataList());
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        documentRequest.setApplicationId(toApplicationId);
        documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
        documentRequest.setProductDocumentMappingId(3l);
        try {
            DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
            workingCapitalPrimaryViewResponse.setPanCardList(documentResponse.getDataList());
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        documentRequest.setApplicationId(toApplicationId);
        documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
        documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_PROFIEL_PICTURE);
        try {
            DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
            workingCapitalPrimaryViewResponse.setProfilePic(documentResponse.getDataList());
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return workingCapitalPrimaryViewResponse;
    }

    @Override
    public boolean validateWorkingCapitalPrimaryViewRequest(String toApplicationId) {
        if(!CommonUtil.isObjectNullOrEmpty(toApplicationId)){
            try {
                Long.parseLong(toApplicationId);
                return false;
            } catch (NumberFormatException e) {
                return true;
            }
        }else{
            logger.warn("Invalid Request {}", toApplicationId);
            return true;
        }
    }
}
