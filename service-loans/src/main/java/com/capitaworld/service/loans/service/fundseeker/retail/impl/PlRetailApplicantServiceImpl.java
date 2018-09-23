package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinancialArrangementsDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.CreditCardsDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantIncomeDetail;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.retail.CreditCardsDetailRequest;
import com.capitaworld.service.loans.model.retail.PLRetailApplicantRequest;
import com.capitaworld.service.loans.model.retail.RetailApplicantIncomeRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinancialArrangementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.CreditCardsDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantIncomeRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.PlRetailApplicantService;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PlRetailApplicantServiceImpl implements PlRetailApplicantService {
    private static final Logger logger = LoggerFactory.getLogger(PlRetailApplicantServiceImpl.class.getName());

    @Autowired
    private RetailApplicantDetailRepository applicantRepository;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private RetailApplicantIncomeRepository retailApplicantIncomeRepository;

    @Autowired
    private FinancialArrangementDetailsRepository financialArrangementDetailsRepository;

    @Autowired
    private CreditCardsDetailRepository creditCardsDetailRepository;

    @Override
    public boolean saveProfile(PLRetailApplicantRequest plRetailApplicantRequest, Long userId) throws Exception {
        try {
            Long finalUserId = (CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getClientId()) ? userId : plRetailApplicantRequest.getClientId());
            RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(finalUserId, plRetailApplicantRequest.getApplicationId());
            if (applicantDetail != null) {
                applicantDetail.setModifiedBy(userId);
                applicantDetail.setModifiedDate(new Date());
            } else {
                applicantDetail = new RetailApplicantDetail();
                applicantDetail.setCreatedBy(userId);
                applicantDetail.setCreatedDate(new Date());
                applicantDetail.setIsActive(true);
                applicantDetail.setApplicationId(new LoanApplicationMaster(plRetailApplicantRequest.getApplicationId()));
            }

            BeanUtils.copyProperties(plRetailApplicantRequest, applicantDetail, CommonUtils.IgnorableCopy.PL_RETAIL_PRIMARY);
            copyAddressFromRequestToDomain(plRetailApplicantRequest, applicantDetail);

            applicantRepository.save(applicantDetail);

            // Updating Flag
            loanApplicationRepository.setIsApplicantProfileMandatoryFilled(plRetailApplicantRequest.getApplicationId(),
                    finalUserId, plRetailApplicantRequest.getIsApplicantDetailsFilled());

            return true;

        } catch (Exception e) {
            logger.error("Error while Saving Retail Profile:-");
            e.printStackTrace();
            throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public PLRetailApplicantRequest getProfile(Long userId, Long applicationId) throws Exception {
        try {
            RetailApplicantDetail applicantDetail = applicantRepository.findOneByApplicationIdId(applicationId);
            if (applicantDetail == null) {
                PLRetailApplicantRequest request = new PLRetailApplicantRequest();
                LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId,
                        userId);
                return request;
            }
            PLRetailApplicantRequest applicantRequest = new PLRetailApplicantRequest();
            BeanUtils.copyProperties(applicantDetail, applicantRequest);
            copyAddressFromDomainToRequest(applicantDetail, applicantRequest);

            List<RetailApplicantIncomeDetail> retailApplicantIncomeDetailList= retailApplicantIncomeRepository.findByApplicationIdAndIsActive(applicationId, true);
            List<RetailApplicantIncomeRequest> retailApplicantIncomeRequestList = new ArrayList<RetailApplicantIncomeRequest>(retailApplicantIncomeDetailList.size());

            RetailApplicantIncomeRequest incomeRequest = null;
            for(RetailApplicantIncomeDetail incomeDetail : retailApplicantIncomeDetailList){
                incomeRequest = new RetailApplicantIncomeRequest();
                BeanUtils.copyProperties(incomeDetail, incomeRequest);
                retailApplicantIncomeRequestList.add(incomeRequest);
            }
            applicantRequest.setRetailApplicantIncomeRequestList(retailApplicantIncomeRequestList);

            return applicantRequest;
        } catch (Exception e) {
            logger.error("Error while getting Retail Profile:-");
            e.printStackTrace();
            throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public boolean savePrimary(PLRetailApplicantRequest plRetailApplicantRequest, Long userId) throws Exception {
        try {
            Long finalUserId = (CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getClientId()) ? userId : plRetailApplicantRequest.getClientId());
            RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(finalUserId, plRetailApplicantRequest.getApplicationId());
            if (applicantDetail != null) {
                applicantDetail.setModifiedBy(userId);
                applicantDetail.setModifiedDate(new Date());
            } else {
                applicantDetail = new RetailApplicantDetail();
                applicantDetail.setCreatedBy(userId);
                applicantDetail.setCreatedDate(new Date());
                applicantDetail.setIsActive(true);
                applicantDetail.setApplicationId(new LoanApplicationMaster(plRetailApplicantRequest.getApplicationId()));
            }

            BeanUtils.copyProperties(plRetailApplicantRequest, applicantDetail, CommonUtils.IgnorableCopy.PL_RETAIL_PROFILE);

            applicantRepository.save(applicantDetail);

            List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestsList = plRetailApplicantRequest.getFinancialArrangementsDetailRequestsList();
            if(!CommonUtils.isListNullOrEmpty(financialArrangementsDetailRequestsList)) {
                logger.info("Financial Arrangements Detail List Null Or Empty ------------->");
                for (FinancialArrangementsDetailRequest reqObj : financialArrangementsDetailRequestsList) {
                    FinancialArrangementsDetail saveFinObj = null;
                    if (!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
                        saveFinObj = financialArrangementDetailsRepository.findByIdAndIsActive(reqObj.getId(), true);
                    }
                    if (CommonUtils.isObjectNullOrEmpty(saveFinObj)) {
                        saveFinObj = new FinancialArrangementsDetail();
                        BeanUtils.copyProperties(reqObj, saveFinObj, "id", "createdBy", "createdDate", "modifiedBy",
                                "modifiedDate", "isActive");

                        saveFinObj.setApplicationId(new LoanApplicationMaster(plRetailApplicantRequest.getApplicationId()));
                        saveFinObj.setCreatedBy(userId);
                        saveFinObj.setCreatedDate(new Date());
                        saveFinObj.setIsActive(true);
                    } else {
                        BeanUtils.copyProperties(reqObj, saveFinObj, "id", "createdBy", "createdDate", "modifiedBy",
                                "modifiedDate");
                        saveFinObj.setModifiedBy(userId);
                        saveFinObj.setModifiedDate(new Date());
                    }
                    financialArrangementDetailsRepository.save(saveFinObj);
                }
            }

            List<CreditCardsDetailRequest> creditCardsDetailRequestList = plRetailApplicantRequest.getCreditCardsDetailRequestList();
            if(!CommonUtils.isListNullOrEmpty(creditCardsDetailRequestList)) {
                logger.info("CreditCards Detail List Null Or Empty ------------->");
                for (CreditCardsDetailRequest reqObj : creditCardsDetailRequestList) {
                    CreditCardsDetail saveObj = null;
                    if (!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
                        saveObj = creditCardsDetailRepository.findOne(reqObj.getId());
                    }
                    if (CommonUtils.isObjectNullOrEmpty(saveObj)) {
                        saveObj = new CreditCardsDetail();
                        BeanUtils.copyProperties(reqObj, saveObj, "id", "createdBy", "createdDate", "modifiedBy",
                                "modifiedDate", "isActive");

                        saveObj.setApplicantionId(new LoanApplicationMaster(plRetailApplicantRequest.getApplicationId()));
                        saveObj.setCreatedBy(userId);
                        saveObj.setCreatedDate(new Date());
                        saveObj.setIsActive(true);
                    } else {
                        BeanUtils.copyProperties(reqObj, saveObj, "id", "createdBy", "createdDate", "modifiedBy",
                                "modifiedDate");
                        saveObj.setModifiedBy(userId);
                        saveObj.setModifiedDate(new Date());
                    }
                    creditCardsDetailRepository.save(saveObj);
                }
            }

            // Updating Flag
            loanApplicationRepository.setIsApplicantProfileMandatoryFilled(plRetailApplicantRequest.getApplicationId(),
                    finalUserId, plRetailApplicantRequest.getIsApplicantDetailsFilled());

            return true;

        } catch (Exception e) {
            logger.error("Error while Saving Retail Primary:-");
            e.printStackTrace();
            throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public PLRetailApplicantRequest getPrimary(Long userId, Long applicationId) throws Exception {
        try {
            RetailApplicantDetail applicantDetail = applicantRepository.findOneByApplicationIdId(applicationId);
            if (applicantDetail == null) {
                PLRetailApplicantRequest request = new PLRetailApplicantRequest();
                LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId,
                        userId);
                return request;
            }
            PLRetailApplicantRequest applicantRequest = new PLRetailApplicantRequest();
            BeanUtils.copyProperties(applicantDetail, applicantRequest);

            List<FinancialArrangementsDetail> financialArrangementsDetailList= financialArrangementDetailsRepository.listSecurityCorporateDetailByAppId(applicationId);
            List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList= new ArrayList<FinancialArrangementsDetailRequest>(financialArrangementsDetailList.size());

            FinancialArrangementsDetailRequest financialRequest = null;
            for(FinancialArrangementsDetail financialDetail : financialArrangementsDetailList){
                financialRequest = new FinancialArrangementsDetailRequest();
                BeanUtils.copyProperties(financialDetail, financialRequest);
                financialArrangementsDetailRequestList.add(financialRequest);
            }
            applicantRequest.setFinancialArrangementsDetailRequestsList(financialArrangementsDetailRequestList);


            List<CreditCardsDetail> creditCardsDetailList= creditCardsDetailRepository.listCreditCardsFromAppId(applicationId);
            List<CreditCardsDetailRequest> creditCardsDetailRequestList= new ArrayList<CreditCardsDetailRequest>(creditCardsDetailList.size());

            CreditCardsDetailRequest creditCardRequest = null;
            for(CreditCardsDetail creditCardsDetail: creditCardsDetailList){
                creditCardRequest = new CreditCardsDetailRequest();
                BeanUtils.copyProperties(creditCardsDetail, creditCardRequest);
                creditCardsDetailRequestList.add(creditCardRequest);
            }
            applicantRequest.setCreditCardsDetailRequestList(creditCardsDetailRequestList);

            return applicantRequest;
        } catch (Exception e) {
            logger.error("Error while getting Retail Primary:-");
            e.printStackTrace();
            throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }


    public static void copyAddressFromRequestToDomain(PLRetailApplicantRequest from, RetailApplicantDetail to) {
        if (from.getContactAddress() != null) {
            to.setAddressPremiseName(from.getContactAddress().getPremiseNumber());
            to.setAddressStreetName(from.getContactAddress().getStreetName());
            to.setAddressLandmark(from.getContactAddress().getLandMark());
            to.setAddressCity(from.getContactAddress().getCityId());
            to.setAddressState(from.getContactAddress().getStateId().longValue());
            to.setAddressCountry(from.getContactAddress().getCountryId());
            to.setAddressDistrictMappingId(from.getContactAddress().getDistrictMappingId());
            to.setAddressPincode(from.getContactAddress().getPincode());
        }
    }

    public static void copyAddressFromDomainToRequest(RetailApplicantDetail from, PLRetailApplicantRequest to) {
        Address address = new Address();
        address.setPremiseNumber(from.getAddressPremiseName());
        address.setLandMark(from.getAddressLandmark());
        address.setStreetName(from.getAddressStreetName());
        address.setCityId(from.getAddressCity());
        address.setStateId(from.getAddressState().intValue());
        address.setCountryId(from.getAddressCountry());
        address.setPincode(from.getAddressPincode());
        address.setDistrictMappingId(from.getAddressDistrictMappingId());
        to.setContactAddress(address);

    }
}
