package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.capitaworld.cibil.api.utility.MultipleJSONObjectHelper;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.capitaworld.service.loans.model.retail.RetailFinalInfoRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinancialArrangementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.CreditCardsDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantIncomeRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.PlRetailApplicantService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.enums.CreditCardTypesRetail;

@Service
@Transactional
public class PlRetailApplicantServiceImpl implements PlRetailApplicantService {
    private static final Logger logger = LoggerFactory.getLogger(PlRetailApplicantServiceImpl.class.getName());

    private static final String PERMANENT_LITERAL = "permanent";
    private static final String OFFICE_LITERAL = "office";

    @Autowired
    private RetailApplicantDetailRepository applicantRepository;

    @Autowired
    private UsersClient usersClient;

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
            logger.error("Error while Saving Retail Profile :- ",e);
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

            UserResponse userResponse = usersClient.getEmailMobile(userId);
            LinkedHashMap<String, Object> lm = (LinkedHashMap<String, Object>)userResponse.getData();
            UsersRequest request = MultipleJSONObjectHelper.getObjectFromMap(lm,UsersRequest.class);
            applicantRequest.setMobile(request.getMobile());

            List<RetailApplicantIncomeDetail> retailApplicantIncomeDetailList= retailApplicantIncomeRepository.findByApplicationIdAndIsActive(applicationId, true);
            List<RetailApplicantIncomeRequest> retailApplicantIncomeRequestList = new ArrayList<RetailApplicantIncomeRequest>(retailApplicantIncomeDetailList.size());

            RetailApplicantIncomeRequest incomeRequest = null;
            for(RetailApplicantIncomeDetail incomeDetail : retailApplicantIncomeDetailList){
                incomeRequest = new RetailApplicantIncomeRequest();
                BeanUtils.copyProperties(incomeDetail, incomeRequest);
                retailApplicantIncomeRequestList.add(incomeRequest);
            }
            applicantRequest.setRetailApplicantIncomeRequestList(retailApplicantIncomeRequestList);
            
            
            // financialArrangement data fetched and copy in beanUtil 

            try {
            	
            	List<FinancialArrangementsDetail> retailFinancialDetailsList = financialArrangementDetailsRepository.listSecurityCorporateDetailFromAppId(applicationId);
                
                if(retailFinancialDetailsList != null) {
                
                	List<FinancialArrangementsDetailRequest> retailFinancialDetailsReq= new ArrayList<FinancialArrangementsDetailRequest>(retailFinancialDetailsList.size());
                    
                    FinancialArrangementsDetailRequest retailFinReq=null;
                    
                    for(FinancialArrangementsDetail finArDetails : retailFinancialDetailsList) {
                    	 
                    	retailFinReq =new FinancialArrangementsDetailRequest();
                    	BeanUtils.copyProperties(finArDetails, retailFinReq);
                    	retailFinancialDetailsReq.add(retailFinReq);
                    }
                    applicantRequest.setFinancialArrangementsDetailRequestsList(retailFinancialDetailsReq);
                	
                }else {
					logger.warn("FinancialArrangementData is Null");
				}
                
			} catch (Exception e) {
				logger.error("=======>>>>> Error while fetching FinancialArrangementDetails <<<<<<<=========",e);
			}
            
            
            //CraditCard Details Fetching
            
            try {
            	
            	List<CreditCardsDetail> creditCardDetails= creditCardsDetailRepository.listCreditCardsFromAppId(applicationId);
            	
            	if(creditCardDetails != null) {
            	
            		List<CreditCardsDetailRequest> creditCardDetailsRequest=new ArrayList<CreditCardsDetailRequest>(creditCardDetails.size());
                    
                    CreditCardsDetailRequest creditCardDetailReq=null;

                    for(CreditCardsDetail creditCard :creditCardDetails) {
                    	creditCardDetailReq= new CreditCardsDetailRequest();
                    	BeanUtils.copyProperties(creditCard, creditCardDetailReq);
                    	creditCardDetailReq.setCardTypeString(!CommonUtils.isObjectNullOrEmpty(creditCard.getCreditCardTypesId()) ? CreditCardTypesRetail.getById(creditCard.getCreditCardTypesId()).getValue() : "");
                    	creditCardDetailReq.setOutstandingBalanceString(!CommonUtils.isObjectNullOrEmpty(creditCard.getOutstandingBalance()) ? CommonUtils.convertValue(creditCard.getOutstandingBalance()) : "");
                    	creditCardDetailsRequest.add(creditCardDetailReq);
                    }
                    applicantRequest.setCreditCardsDetailRequestList(creditCardDetailsRequest);
            	}else {
					logger.warn("CreditCard Details is Null");
				}
			} catch (Exception e) {
				logger.error("==========>>>>>>> Error while Fetching CreditCardDetails <<<<<<<============",e);
			}
            
            

            return applicantRequest;
        } catch (Exception e) {
            logger.error("Error while getting Retail Profile :- ",e);
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
                        BeanUtils.copyProperties(reqObj, saveFinObj, "id", CommonUtils.CREATED_BY, CommonUtils.CREATED_DATE, CommonUtils.MODIFIED_BY,
                                CommonUtils.MODIFIED_DATE, "isActive");

                        saveFinObj.setApplicationId(new LoanApplicationMaster(plRetailApplicantRequest.getApplicationId()));
                        saveFinObj.setCreatedBy(userId);
                        saveFinObj.setCreatedDate(new Date());
                        saveFinObj.setIsActive(true);
                    } else {
                        BeanUtils.copyProperties(reqObj, saveFinObj, "id", CommonUtils.CREATED_BY, CommonUtils.CREATED_DATE, CommonUtils.MODIFIED_BY,
                                CommonUtils.MODIFIED_DATE);
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
                        BeanUtils.copyProperties(reqObj, saveObj, "id", CommonUtils.CREATED_BY, CommonUtils.CREATED_DATE, CommonUtils.MODIFIED_BY,
                                CommonUtils.MODIFIED_DATE, "isActive");

                        saveObj.setApplicantionId(new LoanApplicationMaster(plRetailApplicantRequest.getApplicationId()));
                        saveObj.setCreatedBy(userId);
                        saveObj.setCreatedDate(new Date());
                        saveObj.setIsActive(true);
                    } else {
                        BeanUtils.copyProperties(reqObj, saveObj, "id", CommonUtils.CREATED_BY, CommonUtils.CREATED_DATE, CommonUtils.MODIFIED_BY,
                                CommonUtils.MODIFIED_DATE);
                        saveObj.setModifiedBy(userId);
                        saveObj.setModifiedDate(new Date());
                    }
                    creditCardsDetailRepository.save(saveObj);
                }
            }

            // Updating Flag
            LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(plRetailApplicantRequest.getApplicationId(), userId);

            loanApplicationRepository.setIsApplicantProfileMandatoryFilled(plRetailApplicantRequest.getApplicationId(),
                    finalUserId, plRetailApplicantRequest.getIsApplicantDetailsFilled());
            applicationMaster.setIsPrimaryLocked(true);
            loanApplicationRepository.save(applicationMaster);

            return true;

        } catch (Exception e) {
            logger.error("Error while Saving Retail Primary :- ",e);
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
            applicantRequest.setLoanAmountRequiredString(CommonUtils.convertValue(applicantDetail.getLoanAmountRequired()));
            applicantRequest.setMonthlyIncomeString(CommonUtils.convertValue(applicantDetail.getMonthlyIncome()));
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
                //creditCardRequest.setOutstandingBalanceString(CommonUtils.convertValue(creditCardsDetail.getOutstandingBalance()));
                //creditCardRequest.setCardTypeString(!CommonUtils.isObjectNullOrEmpty(creditCardsDetail.getCreditCardTypesId()) ? CreditCardTypesRetail.getById(creditCardsDetail.getCreditCardTypesId()).getValue() : "");
                BeanUtils.copyProperties(creditCardsDetail, creditCardRequest);
                creditCardsDetailRequestList.add(creditCardRequest);
            }
            applicantRequest.setCreditCardsDetailRequestList(creditCardsDetailRequestList);

            return applicantRequest;
        } catch (Exception e) {
            logger.error("Error while getting Retail Primary :- ",e);
            throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public boolean saveFinal(RetailFinalInfoRequest applicantRequest, Long userId) throws Exception {
        try {
            if (applicantRequest.getApplicationId() == null) {
                throw new NullPointerException("Application Id and ID(Primary Key) must not be null=>Application ID==>"
                        + applicantRequest.getApplicationId() + " User Id (Primary Key)==>" + userId);
            }
            Long finaluserId = (CommonUtils.isObjectNullOrEmpty(applicantRequest.getClientId()) ? userId
                    : applicantRequest.getClientId());
            RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(finaluserId,
                    applicantRequest.getApplicationId());
            if (applicantDetail == null) {
                throw new NullPointerException(
                        "Applicant ID and ID(Primary Key) does not match with the database==> Applicant ID==>"
                                + applicantRequest.getApplicationId() + "User ID==>" + userId);
            }
            applicantDetail.setModifiedBy(userId);
            applicantDetail.setModifiedDate(new Date());
            BeanUtils.copyProperties(applicantRequest, applicantDetail, CommonUtils.IgnorableCopy.RETAIL_PL_PROFILE);
            copyAddressFromRequestToDomainForFinal(applicantRequest, applicantDetail, PERMANENT_LITERAL);
            copyAddressFromRequestToDomainForFinal(applicantRequest, applicantDetail, OFFICE_LITERAL);
            applicantRepository.save(applicantDetail);
            // Updating Final Flag
            loanApplicationRepository.setIsApplicantFinalMandatoryFilled(applicantRequest.getApplicationId(),
                    finaluserId, applicantRequest.getIsApplicantFinalFilled());
            // Updating Final Count

            return true;
        } catch (Exception e) {
            logger.error("Error while Saving Retail Final :- ",e);
            throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public RetailFinalInfoRequest getFinal(Long userId, Long applicationId) throws Exception {
        try {
            RetailApplicantDetail applicantDetail = applicantRepository.findOneByApplicationIdIdAndIsActive(applicationId, true);
            if (applicantDetail == null) {
                throw new NullPointerException("RetailApplicantDetail Record of Final Portion not exists in DB of ID : "
                        + userId + "  ApplicationId==>" + applicationId);
            }
            RetailFinalInfoRequest applicantRequest = new RetailFinalInfoRequest();
            BeanUtils.copyProperties(applicantDetail, applicantRequest, CommonUtils.IgnorableCopy.RETAIL_PL_PROFILE);
            copyAddressFromDomainToRequestForFinal(applicantDetail, applicantRequest, "contact");
            copyAddressFromDomainToRequestForFinal(applicantDetail, applicantRequest, PERMANENT_LITERAL);
            copyAddressFromDomainToRequestForFinal(applicantDetail, applicantRequest, OFFICE_LITERAL);
            return applicantRequest;
        } catch (Exception e) {
            logger.error("Error while getting Retail Final :- ",e);
            throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }


    public static void copyAddressFromRequestToDomain(PLRetailApplicantRequest from, RetailApplicantDetail to) {
        if (from.getContactAddress() != null) {
            to.setAddressPremiseName(from.getContactAddress().getPremiseNumber());
            to.setAddressStreetName(from.getContactAddress().getStreetName());
            to.setAddressLandmark(from.getContactAddress().getLandMark());
            to.setAddressCity(from.getContactAddress().getCityId());
            to.setAddressState(CommonUtils.isObjectNullOrEmpty(from.getContactAddress().getStateId()) ? null : from.getContactAddress().getStateId().longValue());
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
        address.setStateId(CommonUtils.isObjectNullOrEmpty(from.getAddressState()) ? null : from.getAddressState().intValue());
        address.setCountryId(from.getAddressCountry());
        address.setPincode(from.getAddressPincode());
        address.setDistrictMappingId(from.getAddressDistrictMappingId());
        to.setContactAddress(address);

    }

    public static void copyAddressFromDomainToRequestForFinal(RetailApplicantDetail from, RetailFinalInfoRequest to, String type){
        if(type.equalsIgnoreCase("contact")){
            Address address = new Address();
            address.setPremiseNumber(from.getAddressPremiseName());
            address.setLandMark(from.getAddressLandmark());
            address.setStreetName(from.getAddressStreetName());
            address.setCityId(from.getAddressCity());
            address.setStateId(CommonUtils.isObjectNullOrEmpty(from.getAddressState()) ? null : from.getAddressState().intValue());
            address.setCountryId(from.getAddressCountry());
            address.setPincode(from.getAddressPincode());
            address.setDistrictMappingId(from.getAddressDistrictMappingId());
            to.setContactAddress(address);
        }
        if(type.equalsIgnoreCase(PERMANENT_LITERAL)){
            Address address = new Address();
            address.setPremiseNumber(from.getPermanentPremiseNumberName());
            address.setLandMark(from.getPermanentLandMark());
            address.setStreetName(from.getPermanentStreetName());
            address.setCityId(from.getPermanentCityId());
            address.setStateId(from.getPermanentStateId());
            address.setCountryId(from.getPermanentCountryId());
            address.setPincode(from.getPermanentPincode());
            address.setDistrictMappingId(from.getPermanentdistrictMappingId());
            to.setPermanentAddress(address);
        }
        if(type.equalsIgnoreCase(OFFICE_LITERAL)){
            Address address = new Address();
            address.setPremiseNumber(from.getOfficePremiseNumberName());
            address.setLandMark(from.getOfficeLandMark());
            address.setStreetName(from.getOfficeStreetName());
            address.setCityId(from.getOfficeCityId());
            address.setStateId(from.getOfficeStateId());
            address.setCountryId(from.getOfficeCountryId());
            address.setPincode(from.getOfficePincode());
            address.setDistrictMappingId(from.getOfficeDistrictMappingId());
            to.setOfficeAddress(address);
        }
    }

    public static void copyAddressFromRequestToDomainForFinal(RetailFinalInfoRequest from, RetailApplicantDetail to, String type){
        if(type.equalsIgnoreCase(PERMANENT_LITERAL) && from.getPermanentAddress() != null ){
                to.setPermanentPremiseNumberName(from.getPermanentAddress().getPremiseNumber());
                to.setPermanentStreetName(from.getPermanentAddress().getStreetName());
                to.setPermanentLandMark(from.getPermanentAddress().getLandMark());
                to.setPermanentCityId(from.getPermanentAddress().getCityId());
                to.setPermanentStateId(from.getPermanentAddress().getStateId());
                to.setPermanentCountryId(from.getPermanentAddress().getCountryId());
                to.setPermanentdistrictMappingId(from.getPermanentAddress().getDistrictMappingId());
                to.setPermanentPincode(from.getPermanentAddress().getPincode());
        }
        if(type.equalsIgnoreCase(OFFICE_LITERAL) && from.getOfficeAddress() != null ){
                to.setOfficePremiseNumberName(from.getOfficeAddress().getPremiseNumber());
                to.setOfficeStreetName(from.getOfficeAddress().getStreetName());
                to.setOfficeLandMark(from.getOfficeAddress().getLandMark());
                to.setOfficeCityId(from.getOfficeAddress().getCityId());
                to.setOfficeStateId(from.getOfficeAddress().getStateId());
                to.setOfficeCountryId(from.getOfficeAddress().getCountryId());
                to.setOfficeDistrictMappingId(from.getOfficeAddress().getDistrictMappingId());
                to.setOfficePincode(from.getOfficeAddress().getPincode());
        }
    }
}
