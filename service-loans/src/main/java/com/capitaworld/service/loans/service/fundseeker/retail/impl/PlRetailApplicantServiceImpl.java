package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.api.ekyc.model.epf.request.EmployerDefaulterRequest;
import com.capitaworld.api.ekyc.model.epf.request.EmployerRequest;
import com.capitaworld.api.ekyc.model.epf.request.EmployerVerificationRequest;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinancialArrangementsDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.BankingRelation;
import com.capitaworld.service.loans.domain.fundseeker.retail.CoApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.CreditCardsDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantIncomeDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.retail.BankRelationshipRequest;
import com.capitaworld.service.loans.model.retail.CreditCardsDetailRequest;
import com.capitaworld.service.loans.model.retail.PLRetailApplicantRequest;
import com.capitaworld.service.loans.model.retail.RetailApplicantIncomeRequest;
import com.capitaworld.service.loans.model.retail.RetailFinalInfoRequest;
import com.capitaworld.service.loans.model.retail.RetailOnformBasicInfoReq;
import com.capitaworld.service.loans.model.retail.RetailOnformContactInfoReq;
import com.capitaworld.service.loans.model.retail.RetailOnformEmploymentInfoReq;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinancialArrangementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.BankingRelationlRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.CoApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.CreditCardsDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantIncomeRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.PlRetailApplicantService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.EPFOAsyncComponent;
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
    private LoanApplicationRepository loanApplicationRepository;
    
    @Autowired
    private ApplicationProposalMappingRepository applicationProposalMappingRepository;

    @Autowired
    private RetailApplicantIncomeRepository retailApplicantIncomeRepository;

    @Autowired
    private FinancialArrangementDetailsRepository financialArrangementDetailsRepository;

    @Autowired
    private CreditCardsDetailRepository creditCardsDetailRepository;

    @Autowired
    private BankingRelationlRepository bankingRelationlRepository;
    
    @Autowired
    private EPFOAsyncComponent epfoAsyncComponent;
    
    @Autowired
    private CoApplicantDetailRepository coApplicantDetailRepository;


    @Override
    public boolean saveProfile(PLRetailApplicantRequest plRetailApplicantRequest, Long userId) throws LoansException {
        try {
        	Long finalUserId = (CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getClientId()) ? userId : plRetailApplicantRequest.getClientId());
            if(plRetailApplicantRequest.getCoAppId() == null){
            	RetailApplicantDetail applicantDetail = null;
                
                if(plRetailApplicantRequest.getProposalId() != null) {
                	applicantDetail = applicantRepository.findByProposalId(plRetailApplicantRequest.getApplicationId(), plRetailApplicantRequest.getProposalId());
                }else {
                	applicantDetail = applicantRepository.getByApplicationAndUserId(finalUserId, plRetailApplicantRequest.getApplicationId());
                }
                
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
                
                if(plRetailApplicantRequest.getType() != null && plRetailApplicantRequest.getType() == CommonUtils.RetailOneformType.BASIC_INFO) {
                    applicantDetail.setTitleId(plRetailApplicantRequest.getTitleId());
                    applicantDetail.setFirstName(plRetailApplicantRequest.getFirstName());
                    applicantDetail.setMiddleName(plRetailApplicantRequest.getMiddleName());
                    applicantDetail.setLastName(plRetailApplicantRequest.getLastName());
                    applicantDetail.setGenderId(plRetailApplicantRequest.getGenderId());
                    applicantDetail.setPan(plRetailApplicantRequest.getPan());
                    applicantDetail.setAadharNumber(plRetailApplicantRequest.getAadharNumber());
                    applicantDetail.setMobile(plRetailApplicantRequest.getMobile());
                    applicantDetail.setEducationQualification(plRetailApplicantRequest.getEducationQualification());
                    applicantDetail.setStatusId(plRetailApplicantRequest.getStatusId());
                    applicantDetail.setResidenceType(plRetailApplicantRequest.getResidenceType());
                    applicantDetail.setResidentialStatus(plRetailApplicantRequest.getResidentialStatus());
                    applicantDetail.setBirthDate(plRetailApplicantRequest.getBirthDate());
                    applicantDetail.setContactNo(plRetailApplicantRequest.getContactNo());
                    applicantDetail.setEmail(plRetailApplicantRequest.getEmail());
                    applicantDetail.setCompanyName(plRetailApplicantRequest.getCompanyName());
                    applicantDetail.setSpouseEmployment(plRetailApplicantRequest.getSpouseEmployment());
                    applicantDetail.setNoOfDependent(plRetailApplicantRequest.getNoOfDependent());
                    applicantDetail.setNoDependent(plRetailApplicantRequest.getNoOfDependent());
                    applicantDetail.setCategory(plRetailApplicantRequest.getCategory());
                    applicantDetail.setFatherName(plRetailApplicantRequest.getFatherName());
                    applicantDetail.setAnnualIncomeOfSpouse(plRetailApplicantRequest.getAnnualIncomeOfSpouse());
                    applicantDetail.setNationality(plRetailApplicantRequest.getNationality());
                    applicantDetail.setNetworth(plRetailApplicantRequest.getNetworth());
                    applicantDetail.setIsBasicInfoFilled(plRetailApplicantRequest.getIsBasicInfoFilled());
                } else if(plRetailApplicantRequest.getType() != null && plRetailApplicantRequest.getType() == CommonUtils.RetailOneformType.CONTACT_INFO) {
                	applicantDetail.setIsContactInfoFilled(plRetailApplicantRequest.getIsContactInfoFilled());
                	applicantDetail.setResidenceType(plRetailApplicantRequest.getResidenceType());
                	applicantDetail.setResidenceSinceYear(plRetailApplicantRequest.getResidenceSinceYear());
                	applicantDetail.setResidenceSinceMonth(plRetailApplicantRequest.getResidenceSinceMonth());
                	copyAddressFromRequestToDomain(plRetailApplicantRequest, applicantDetail);	
                } else if(plRetailApplicantRequest.getType() != null && plRetailApplicantRequest.getType() == CommonUtils.RetailOneformType.EMPLOYMENT_INFO) {
                	applicantDetail.setIsEmploymentInfoFilled(plRetailApplicantRequest.getIsEmploymentInfoFilled());
                	applicantDetail.setEmploymentType(plRetailApplicantRequest.getEmploymentType());
                	applicantDetail.setEmploymentWith(plRetailApplicantRequest.getEmploymentWith());
                	applicantDetail.setCentralGovId(plRetailApplicantRequest.getCentralGovId());
                	applicantDetail.setStateGovId(plRetailApplicantRequest.getStateGovId());
                	applicantDetail.setPsuId(plRetailApplicantRequest.getPsuId());
                	applicantDetail.setCorporateId(plRetailApplicantRequest.getCorporateId());
                	applicantDetail.setEduInstId(plRetailApplicantRequest.getEduInstId());
                	applicantDetail.setBankNameId(plRetailApplicantRequest.getBankNameId());
                	applicantDetail.setInsuranceNameId(plRetailApplicantRequest.getInsuranceNameId());
                	applicantDetail.setNameOfEmployer(plRetailApplicantRequest.getNameOfEmployer());
                	applicantDetail.setEmploymentStatus(plRetailApplicantRequest.getEmploymentStatus());
                	applicantDetail.setCompanyName(plRetailApplicantRequest.getCompanyName());
                	applicantDetail.setDesignation(plRetailApplicantRequest.getDesignation());
                	applicantDetail.setGrossMonthlyIncome(plRetailApplicantRequest.getGrossMonthlyIncome());
                	applicantDetail.setMonthlyIncome(plRetailApplicantRequest.getMonthlyIncome());
                	applicantDetail.setLoanPurposeOther(plRetailApplicantRequest.getLoanPurposeOther());
                	applicantDetail.setKeyVerticalFunding(plRetailApplicantRequest.getKeyVerticalFunding());
                	applicantDetail.setKeyVerticalSector(plRetailApplicantRequest.getKeyVerticalSector());
                	applicantDetail.setKeyVerticalSubSector(plRetailApplicantRequest.getKeyVerticalSubSector());
                	if(!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getBusinessStartMonth()) && !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getBusinessStartYear())) {
    					Calendar cal = Calendar.getInstance();
    					cal.set(plRetailApplicantRequest.getBusinessStartYear(), plRetailApplicantRequest.getBusinessStartMonth(), 01);
    					applicantDetail.setBusinessStartDate(cal.getTime());
    				}
                	applicantDetail.setSalaryMode(plRetailApplicantRequest.getSalaryMode());
                	applicantDetail.setCurrentJobMonth(plRetailApplicantRequest.getCurrentJobMonth());
                	applicantDetail.setCurrentJobYear(plRetailApplicantRequest.getCurrentJobYear());
                	applicantDetail.setTotalExperienceMonth(plRetailApplicantRequest.getTotalExperienceMonth());
                	applicantDetail.setTotalExperienceYear(plRetailApplicantRequest.getTotalExperienceYear());
                	applicantDetail.setEmployedWithOther(plRetailApplicantRequest.getEmployedWithOther());
                	applicantDetail.setBirthDate(plRetailApplicantRequest.getBirthDate());
                	updateEkycIdForApplicant(plRetailApplicantRequest.getApplicationId(), plRetailApplicantRequest.getKid(), applicantDetail);
                }  else if(plRetailApplicantRequest.getType() != null && plRetailApplicantRequest.getType() == CommonUtils.RetailOneformType.CREDIT_INFO) {
                	applicantDetail.setIsCreditInfoFilled(plRetailApplicantRequest.getIsCreditInfoFilled());
                	saveFinancialArrangementDetails(plRetailApplicantRequest, finalUserId);
                } else {
                	BeanUtils.copyProperties(plRetailApplicantRequest, applicantDetail, CommonUtils.IgnorableCopy.getRetailPrimary());
                	copyAddressFromRequestToDomain(plRetailApplicantRequest, applicantDetail);	
                	if(!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getBusinessStartMonth()) && !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getBusinessStartYear())) {
    					Calendar cal = Calendar.getInstance();
    					cal.set(plRetailApplicantRequest.getBusinessStartYear(), plRetailApplicantRequest.getBusinessStartMonth(), 01);
    					applicantDetail.setBusinessStartDate(cal.getTime());
    				}
                	updateEkycIdForApplicant(plRetailApplicantRequest.getApplicationId(), plRetailApplicantRequest.getKid(), applicantDetail);
                }
                applicantRepository.save(applicantDetail);
                
                // Updating Flag
                if(plRetailApplicantRequest.getProposalId() != null) {
                	applicationProposalMappingRepository.setIsApplicantProfileMandatoryFilled(plRetailApplicantRequest.getProposalId(),
                            finalUserId, plRetailApplicantRequest.getIsApplicantDetailsFilled());
                }else {
                	loanApplicationRepository.setIsApplicantProfileMandatoryFilled(plRetailApplicantRequest.getApplicationId(),
                            finalUserId, plRetailApplicantRequest.getIsApplicantDetailsFilled());
                }
            } else {
            	CoApplicantDetail coApplicantDetail = coApplicantDetailRepository.findByIdAndIsActive(plRetailApplicantRequest.getCoAppId(), true);
    			if(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail)) {
    				if(plRetailApplicantRequest.getType() != null && plRetailApplicantRequest.getType() == CommonUtils.RetailOneformType.BASIC_INFO) {
                        coApplicantDetail.setTitleId(plRetailApplicantRequest.getTitleId());
                        coApplicantDetail.setFirstName(plRetailApplicantRequest.getFirstName());
                        coApplicantDetail.setMiddleName(plRetailApplicantRequest.getMiddleName());
                        coApplicantDetail.setLastName(plRetailApplicantRequest.getLastName());
                        coApplicantDetail.setGenderId(plRetailApplicantRequest.getGenderId());
                        coApplicantDetail.setPan(plRetailApplicantRequest.getPan());
                        coApplicantDetail.setAadharNumber(plRetailApplicantRequest.getAadharNumber());
                        coApplicantDetail.setMobile(plRetailApplicantRequest.getMobile());
                        coApplicantDetail.setEducationQualification(plRetailApplicantRequest.getEducationQualification());
                        coApplicantDetail.setStatusId(plRetailApplicantRequest.getStatusId());
                        coApplicantDetail.setResidenceType(plRetailApplicantRequest.getResidenceType());
                        coApplicantDetail.setBirthDate(plRetailApplicantRequest.getBirthDate());
                        coApplicantDetail.setContactNo(plRetailApplicantRequest.getContactNo());
                        coApplicantDetail.setEmail(plRetailApplicantRequest.getEmail());
                        coApplicantDetail.setCompanyName(plRetailApplicantRequest.getCompanyName());
                        coApplicantDetail.setSpouseEmployment(plRetailApplicantRequest.getSpouseEmployment());
                        coApplicantDetail.setNoDependent(plRetailApplicantRequest.getNoOfDependent());
                        coApplicantDetail.setCategory(plRetailApplicantRequest.getCategory());
                        coApplicantDetail.setFatherName(plRetailApplicantRequest.getFatherName());
                        coApplicantDetail.setAnnualIncomeOfSpouse(plRetailApplicantRequest.getAnnualIncomeOfSpouse());
                        coApplicantDetail.setNetworth(plRetailApplicantRequest.getNetworth());
                        coApplicantDetail.setIsBasicInfoFilled(plRetailApplicantRequest.getIsBasicInfoFilled());
                        coApplicantDetail.setNationality(plRetailApplicantRequest.getResidentialStatus());
                        
                    } else if(plRetailApplicantRequest.getType() != null && plRetailApplicantRequest.getType() == CommonUtils.RetailOneformType.CONTACT_INFO) {
                    	coApplicantDetail.setIsContactInfoFilled(plRetailApplicantRequest.getIsContactInfoFilled());
                    	coApplicantDetail.setResidenceType(plRetailApplicantRequest.getResidenceType());
                    	coApplicantDetail.setResidenceSinceYear(plRetailApplicantRequest.getResidenceSinceYear());
                    	coApplicantDetail.setResidenceSinceMonth(plRetailApplicantRequest.getResidenceSinceMonth());
                    	if (plRetailApplicantRequest.getContactAddress() != null) {
        					coApplicantDetail.setAddressPremiseName(plRetailApplicantRequest.getContactAddress().getPremiseNumber());
        					coApplicantDetail.setAddressStreetName(plRetailApplicantRequest.getContactAddress().getStreetName());
        					coApplicantDetail.setAddressLandmark(plRetailApplicantRequest.getContactAddress().getLandMark());
        					if(!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getContactAddress().getCityId())) {
        						coApplicantDetail.setAddressCity(plRetailApplicantRequest.getContactAddress().getCityId().intValue());	
        					}
        					if(!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getContactAddress().getStateId())) {
        						coApplicantDetail.setAddressState(plRetailApplicantRequest.getContactAddress().getStateId().intValue());	
        					}
        					coApplicantDetail.setAddressCountry(plRetailApplicantRequest.getContactAddress().getCountryId());
        					coApplicantDetail.setAddressDistrictMappingId(plRetailApplicantRequest.getContactAddress().getDistrictMappingId());
        					if(!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getContactAddress().getPincode())) {
        						coApplicantDetail.setAddressPincode(BigInteger.valueOf(plRetailApplicantRequest.getContactAddress().getPincode()));	
        					}
        					
        		        }
                    } else if(plRetailApplicantRequest.getType() != null && plRetailApplicantRequest.getType() == CommonUtils.RetailOneformType.EMPLOYMENT_INFO) {
                    	coApplicantDetail.setIsEmploymentInfoFilled(plRetailApplicantRequest.getIsEmploymentInfoFilled());
                    	coApplicantDetail.setEmploymentType(plRetailApplicantRequest.getEmploymentType());
                    	coApplicantDetail.setEmploymentWith(plRetailApplicantRequest.getEmploymentWith());
                    	coApplicantDetail.setCentralGovId(plRetailApplicantRequest.getCentralGovId());
                    	coApplicantDetail.setStateGovId(plRetailApplicantRequest.getStateGovId());
                    	coApplicantDetail.setPsuId(plRetailApplicantRequest.getPsuId());
                    	//coApplicantDetail.setCorporateId(plRetailApplicantRequest.getCorporateId());
                    	coApplicantDetail.setEduInstId(plRetailApplicantRequest.getEduInstId());
                    	coApplicantDetail.setBankNameId(plRetailApplicantRequest.getBankNameId());
                    	coApplicantDetail.setInsuranceNameId(plRetailApplicantRequest.getInsuranceNameId());
                    	coApplicantDetail.setNameOfEmployer(plRetailApplicantRequest.getNameOfEmployer());
                    	coApplicantDetail.setEmploymentStatus(plRetailApplicantRequest.getEmploymentStatus());
                    	coApplicantDetail.setCompanyName(plRetailApplicantRequest.getCompanyName());
                    	coApplicantDetail.setDesignation(plRetailApplicantRequest.getDesignation());
                    	coApplicantDetail.setGrossMonthlyIncome(plRetailApplicantRequest.getGrossMonthlyIncome());
                    	coApplicantDetail.setMonthlyIncome(plRetailApplicantRequest.getMonthlyIncome());
                    	if(!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getBusinessStartMonth()) && !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getBusinessStartYear())) {
        					Calendar cal = Calendar.getInstance();
        					cal.set(plRetailApplicantRequest.getBusinessStartYear(), plRetailApplicantRequest.getBusinessStartMonth(), 01);
        					coApplicantDetail.setBusinessStartDate(cal.getTime());
        				}
                    	coApplicantDetail.setModeOfReceipt(plRetailApplicantRequest.getSalaryMode());
                    	coApplicantDetail.setCurrentJobMonth(plRetailApplicantRequest.getCurrentJobMonth());
                    	coApplicantDetail.setCurrentJobYear(plRetailApplicantRequest.getCurrentJobYear());
                    	coApplicantDetail.setTotalExperienceMonth(plRetailApplicantRequest.getTotalExperienceMonth());
                    	coApplicantDetail.setTotalExperienceYear(plRetailApplicantRequest.getTotalExperienceYear());
                    	coApplicantDetail.setEmployedWithOther(plRetailApplicantRequest.getEmployedWithOther());
                    	coApplicantDetail.setBirthDate(plRetailApplicantRequest.getBirthDate());
                    	updateEkycIdForCoApplicant(plRetailApplicantRequest.getApplicationId(), plRetailApplicantRequest.getKid(), coApplicantDetail);
                    }  else if(plRetailApplicantRequest.getType() != null && plRetailApplicantRequest.getType() == CommonUtils.RetailOneformType.CREDIT_INFO) {
                    	saveFinancialArrangementDetails(plRetailApplicantRequest, finalUserId);
                    } else {
                    	BeanUtils.copyProperties(plRetailApplicantRequest, coApplicantDetail,"applicationId","userId","id","createdDate","createdBy","applicationProposalMapping");
                    	if(!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getBusinessStartMonth()) && !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getBusinessStartYear())) {
        					Calendar cal = Calendar.getInstance();
        					cal.set(plRetailApplicantRequest.getBusinessStartYear(), plRetailApplicantRequest.getBusinessStartMonth(), 01);
        					coApplicantDetail.setBusinessStartDate(cal.getTime());
        				}
                    	if (plRetailApplicantRequest.getContactAddress() != null) {
        					coApplicantDetail.setAddressPremiseName(plRetailApplicantRequest.getContactAddress().getPremiseNumber());
        					coApplicantDetail.setAddressStreetName(plRetailApplicantRequest.getContactAddress().getStreetName());
        					coApplicantDetail.setAddressLandmark(plRetailApplicantRequest.getContactAddress().getLandMark());
        					if(!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getContactAddress().getCityId())) {
        						coApplicantDetail.setAddressCity(plRetailApplicantRequest.getContactAddress().getCityId().intValue());	
        					}
        					if(!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getContactAddress().getStateId())) {
        						coApplicantDetail.setAddressState(plRetailApplicantRequest.getContactAddress().getStateId().intValue());	
        					}
        					coApplicantDetail.setAddressCountry(plRetailApplicantRequest.getContactAddress().getCountryId());
        					coApplicantDetail.setAddressDistrictMappingId(plRetailApplicantRequest.getContactAddress().getDistrictMappingId());
        					if(!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getContactAddress().getPincode())) {
        						coApplicantDetail.setAddressPincode(BigInteger.valueOf(plRetailApplicantRequest.getContactAddress().getPincode()));	
        					}
        					
        		        }
                    	updateEkycIdForCoApplicant(plRetailApplicantRequest.getApplicationId(), plRetailApplicantRequest.getKid(), coApplicantDetail);
                    }
    				coApplicantDetail.setModifiedBy(plRetailApplicantRequest.getUserId());
    				coApplicantDetail.setModifiedDate(new Date());
    				coApplicantDetail.setIsOneFormCompleted(plRetailApplicantRequest.getIsOneFormCompleted());
    				coApplicantDetailRepository.save(coApplicantDetail);
    			}
            }
            return true;

        } catch (Exception e) {
            logger.error("Error while Saving Retail Profile :- ",e);
            throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }
    
    private void updateEkycIdForApplicant(Long applicationId,String kid,RetailApplicantDetail applicantDetail) {
    	EmployerRequest req = new EmployerRequest();
        req.setApplicationId(applicationId);
        String middleName = !CommonUtils.isObjectNullOrEmpty(applicantDetail.getMiddleName())? applicantDetail.getMiddleName():"";
        String name=applicantDetail.getFirstName()+ " "+ middleName  +" "+ applicantDetail.getLastName();
        if(kid != null) {
        	req.setEmployerDefaulterRequest(new EmployerDefaulterRequest(kid));
        	req.setEmployerVerificationRequest(new EmployerVerificationRequest(kid, applicantDetail.getCompanyName(),name , applicantDetail.getMobile(), applicantDetail.getEmail()));
        	epfoAsyncComponent.callAPI(req);
        }
    }

    private void updateEkycIdForCoApplicant(Long applicationId,String kid,CoApplicantDetail coApplicantDetail) {
    	EmployerRequest req = new EmployerRequest();
        req.setApplicationId(applicationId);
        req.setCoAppId(coApplicantDetail.getId());
        String middleName = !CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getMiddleName())? coApplicantDetail.getMiddleName():"";
        String name=coApplicantDetail.getFirstName()+ " "+ middleName  +" "+ coApplicantDetail.getLastName();
        if(kid != null) {
        	req.setEmployerDefaulterRequest(new EmployerDefaulterRequest(kid));
        	req.setEmployerVerificationRequest(new EmployerVerificationRequest(kid, coApplicantDetail.getCompanyName(),name , coApplicantDetail.getMobile(), coApplicantDetail.getEmail()));
        	epfoAsyncComponent.callAPI(req);
        }
    }
    
    private void saveFinancialArrangementDetails(PLRetailApplicantRequest plRetailApplicantRequest, Long userId) {
    	List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestsList = plRetailApplicantRequest.getFinancialArrangementsDetailRequestsList();
        if(!CommonUtils.isListNullOrEmpty(financialArrangementsDetailRequestsList)) {
            logger.info("Financial Arrangements Detail List Null Or Empty ------------->");
            for (FinancialArrangementsDetailRequest reqObj : financialArrangementsDetailRequestsList) {
            	
                FinancialArrangementsDetail saveFinObj = null;
                if (!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
                    saveFinObj = financialArrangementDetailsRepository.findByIdAndIsActive(reqObj.getId(), true);
                }
                if (saveFinObj == null || CommonUtils.isObjectNullOrEmpty(saveFinObj)) {
                    saveFinObj = new FinancialArrangementsDetail();
                    BeanUtils.copyProperties(reqObj, saveFinObj, "id", CommonUtils.CREATED_BY, CommonUtils.CREATED_DATE, CommonUtils.MODIFIED_BY, CommonUtils.MODIFIED_DATE, "isActive");
                    if(plRetailApplicantRequest.getCoAppId() != null){
                    	saveFinObj.setApplicationId(new LoanApplicationMaster(plRetailApplicantRequest.getApplicationId()));
                    	saveFinObj.setDirectorBackgroundDetail(plRetailApplicantRequest.getCoAppId());
                    } else {
                    	saveFinObj.setApplicationId(new LoanApplicationMaster(plRetailApplicantRequest.getApplicationId()));
                    }
                    saveFinObj.setCreatedBy(userId);
                    saveFinObj.setCreatedDate(new Date());
                    saveFinObj.setIsActive(true);
                } else {
                    BeanUtils.copyProperties(reqObj, saveFinObj, "id", CommonUtils.CREATED_BY, CommonUtils.CREATED_DATE, CommonUtils.MODIFIED_BY, CommonUtils.MODIFIED_DATE);
                    saveFinObj.setModifiedBy(userId);
                    saveFinObj.setModifiedDate(new Date());
                }
                
                if(reqObj.getLoanType() != null && reqObj.getLoanType().equals("Credit Card")) {
                	if(reqObj.getIsManuallyAdded() != null && reqObj.getIsManuallyAdded() == false) {
                		creditCardsDetailRepository.inactive(plRetailApplicantRequest.getApplicationId());
                		saveFinObj.setIsManuallyAdded(false);
                	}
                	saveFinObj.setAmount(null);
                	saveFinObj.setEmi(null);
                }
                
                financialArrangementDetailsRepository.save(saveFinObj);
            }
        }
    }
    
    @Override
    public PLRetailApplicantRequest getCoAppProfile(Long coAppId) {
    	CoApplicantDetail coApplicantDetail = coApplicantDetailRepository.findByIdAndIsActive(coAppId, true);
    	if(coApplicantDetail == null) {
    		return null;
    	}
    	PLRetailApplicantRequest res = new PLRetailApplicantRequest();
    	BeanUtils.copyProperties(coApplicantDetail,res);
        res.setResidentialStatus(coApplicantDetail.getNationality());
        res.setNoOfDependent(coApplicantDetail.getNoDependent());
        res.setSalaryMode(coApplicantDetail.getModeOfReceipt());
    	Address address = new Address();
        address.setPremiseNumber(coApplicantDetail.getAddressPremiseName());
        address.setLandMark(coApplicantDetail.getAddressLandmark());
        address.setStreetName(coApplicantDetail.getAddressStreetName());
        if(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getAddressCity())) {
        	address.setCityId(coApplicantDetail.getAddressCity().longValue());	 
        }
        address.setStateId(CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getAddressState()) ? null : coApplicantDetail.getAddressState().intValue());
        address.setCountryId(coApplicantDetail.getAddressCountry());
        if(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getAddressPincode())) {
        	address.setPincode(coApplicantDetail.getAddressPincode().longValue());	 
        }
        address.setDistrictMappingId(coApplicantDetail.getAddressDistrictMappingId());
        res.setContactAddress(address);
        if(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBusinessStartDate())) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(coApplicantDetail.getBusinessStartDate());
			res.setBusinessStartMonth(cal.get(Calendar.MONTH));
			res.setBusinessStartYear(cal.get(Calendar.YEAR));
		}
        try {
        	List<FinancialArrangementsDetail> retailFinancialDetailsList = financialArrangementDetailsRepository.findByDirectorBackgroundDetailAndApplicationIdIdAndIsActive(coAppId, coApplicantDetail.getApplicationId().getId(), true);
            if(retailFinancialDetailsList != null) {
            	List<FinancialArrangementsDetailRequest> retailFinancialDetailsReq= new ArrayList<FinancialArrangementsDetailRequest>(retailFinancialDetailsList.size());
                FinancialArrangementsDetailRequest retailFinReq=null;
                for(FinancialArrangementsDetail finArDetails : retailFinancialDetailsList) {
                	retailFinReq =new FinancialArrangementsDetailRequest();
                	BeanUtils.copyProperties(finArDetails, retailFinReq);
                	retailFinancialDetailsReq.add(retailFinReq);
                }
                res.setFinancialArrangementsDetailRequestsList(retailFinancialDetailsReq);
            	
            }else {
				logger.warn("FinancialArrangementData is Null while get for COapplicants");
			}
		} catch (Exception e) {
			logger.error("=======>>>>> Error while fetching FinancialArrangementDetails while coapplicant <<<<<<<=========",e);
		}
    	return res;
    	
    }

    @Override
    public PLRetailApplicantRequest getProfile(Long userId, Long applicationId) throws LoansException {
        try {
            RetailApplicantDetail applicantDetail = applicantRepository.findByApplicationId(applicationId);
            if (applicantDetail == null) {
                PLRetailApplicantRequest request = new PLRetailApplicantRequest();
                LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId,
                        userId);
                if (applicationMaster != null){
                    logger.info("getByIdAndUserId called successfully ");
                }
                return request;
            }
            PLRetailApplicantRequest applicantRequest = new PLRetailApplicantRequest();
            BeanUtils.copyProperties(applicantDetail, applicantRequest);
            copyAddressFromDomainToRequest(applicantDetail, applicantRequest);
            
            if(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getBusinessStartDate())) {
    			Calendar cal = Calendar.getInstance();
    			cal.setTime(applicantDetail.getBusinessStartDate());
    			applicantRequest.setBusinessStartMonth(cal.get(Calendar.MONTH));
    			applicantRequest.setBusinessStartYear(cal.get(Calendar.YEAR));
    		}
            /*UserResponse userResponse = usersClient.getEmailMobile(userId);
            LinkedHashMap<String, Object> lm = (LinkedHashMap<String, Object>)userResponse.getData();
            UsersRequest request = MultipleJSONObjectHelper.getObjectFromMap(lm,UsersRequest.class);
            applicantRequest.setMobile(request.getMobile());*/

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
                    	if(creditCard.getCreditCardTypesId() != 0) {
                    		creditCardDetailReq.setCardTypeString(!CommonUtils.isObjectNullOrEmpty(creditCard.getCreditCardTypesId()) ? CreditCardTypesRetail.getById(creditCard.getCreditCardTypesId()).getValue() : "");
                    	}
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

            List<BankRelationshipRequest> bankRelationshipRequests = new ArrayList<>();
            List<BankingRelation> bankingRelations = bankingRelationlRepository.listBankRelationAppId(applicationId);
            BankRelationshipRequest bankRelationshipRequest = null;
            for(BankingRelation bankingRelation : bankingRelations) {
            	bankRelationshipRequest = new BankRelationshipRequest();
            	BeanUtils.copyProperties(bankingRelation, bankRelationshipRequest);

            	if(bankingRelation.getSinceYear() !=null && bankingRelation.getSinceMonth() != null) {

					LocalDate since = LocalDate.of(bankingRelation.getSinceYear(), bankingRelation.getSinceMonth(), 1);
			        LocalDate today = LocalDate.now();

			        Period age = Period.between(since, today);
			        int years = age.getYears();
			        int months = age.getMonths();

			        bankRelationshipRequest.setSinceYear(years);
			        bankRelationshipRequest.setSinceMonth(months);
				}
            	bankRelationshipRequests.add(bankRelationshipRequest);
            }
            applicantRequest.setBankingRelationshipList(bankRelationshipRequests);

            

            return applicantRequest;
        } catch (Exception e) {
            logger.error("Error while getting Retail Profile :- ",e);
            throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }

    public PLRetailApplicantRequest getProfileByProposalId(Long userId, Long applicationId, Long proposalId) throws LoansException {
        try {
        	RetailApplicantDetail applicantDetail = null;
        	if(proposalId != null) {
        		applicantDetail = applicantRepository.findByProposalId(applicationId, proposalId);
        	}else {
        		applicantDetail = applicantRepository.findByApplicationId(applicationId);
        	}
            
            PLRetailApplicantRequest applicantRequest = new PLRetailApplicantRequest();
            if (applicantDetail == null) {
                
                
//                ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.findByProposalIdAndIsActive(proposalId, true);
                
//                if (applicationProposalMapping != null){
//                    logger.info("getByproposalId called successfully ");
//                }
                return applicantRequest;
            }
            BeanUtils.copyProperties(applicantDetail, applicantRequest);
            applicantRequest.setNetworth(applicantDetail.getNetworth());
            copyAddressFromDomainToRequest(applicantDetail, applicantRequest);

            if(applicantRequest.getSalaryBankYear() !=null && applicantRequest.getSalaryBankMonth()!= null) {

				LocalDate since = LocalDate.of(applicantRequest.getSalaryBankYear(), applicantRequest.getSalaryBankMonth(), 1);
		        LocalDate today = LocalDate.now();

		        Period age = Period.between(since, today);
		        int years = age.getYears();
		        int months = age.getMonths();

				applicantRequest.setSalaryBankYear(years);
				applicantRequest.setSalaryBankMonth(months);
			}
            /*UserResponse userResponse = usersClient.getEmailMobile(userId);
            LinkedHashMap<String, Object> lm = (LinkedHashMap<String, Object>)userResponse.getData();
            UsersRequest request = MultipleJSONObjectHelper.getObjectFromMap(lm,UsersRequest.class);
            applicantRequest.setMobile(request.getMobile());*/

            List<RetailApplicantIncomeDetail> retailApplicantIncomeDetailList = null;
            if(proposalId != null) {
            	retailApplicantIncomeDetailList = retailApplicantIncomeRepository.findByProposalIdAndIsActive(proposalId, true);
            }else {
            	retailApplicantIncomeDetailList = retailApplicantIncomeRepository.findByApplicationId(applicationId);
            }
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
            	
            	List<FinancialArrangementsDetail> retailFinancialDetailsList = financialArrangementDetailsRepository.listSecurityCorporateDetailFromAppId(applicationId ,userId);
                
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
                    	if(creditCard.getCreditCardTypesId() != 0) {
                    		creditCardDetailReq.setCardTypeString(!CommonUtils.isObjectNullOrEmpty(creditCard.getCreditCardTypesId()) ? CreditCardTypesRetail.getById(creditCard.getCreditCardTypesId()).getValue() : "");
                    	}
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

            List<BankRelationshipRequest> bankRelationshipRequests = new ArrayList<>();
            List<BankingRelation> bankingRelations = bankingRelationlRepository.listBankRelationAppId(applicationId);
            BankRelationshipRequest bankRelationshipRequest = null;
            for(BankingRelation bankingRelation : bankingRelations) {
            	bankRelationshipRequest = new BankRelationshipRequest();
            	BeanUtils.copyProperties(bankingRelation, bankRelationshipRequest);

            	if(bankingRelation.getSinceYear() !=null && bankingRelation.getSinceMonth() != null) {

					LocalDate since = LocalDate.of(bankingRelation.getSinceYear(), bankingRelation.getSinceMonth(), 1);
			        LocalDate today = LocalDate.now();

			        Period age = Period.between(since, today);
			        int years = age.getYears();
			        int months = age.getMonths();

			        bankRelationshipRequest.setSinceYear(years);
			        bankRelationshipRequest.setSinceMonth(months);
				}
            	bankRelationshipRequests.add(bankRelationshipRequest);
            }
            applicantRequest.setBankingRelationshipList(bankRelationshipRequests);

            

            return applicantRequest;
        } catch (Exception e) {
            logger.error("Error while getting Retail Profile :- ",e);
            throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public boolean savePrimary(PLRetailApplicantRequest plRetailApplicantRequest, Long userId) throws LoansException {
        try {
            Long finalUserId = (CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getClientId()) ? userId : plRetailApplicantRequest.getClientId());
            RetailApplicantDetail applicantDetail = null;
            if(plRetailApplicantRequest.getProposalId() != null) {
            	applicantDetail = applicantRepository.findByProposalId(plRetailApplicantRequest.getApplicationId(), plRetailApplicantRequest.getProposalId());
            }else {
            	applicantDetail = applicantRepository.getByApplicationAndUserId(finalUserId, plRetailApplicantRequest.getApplicationId());
            }
            
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

            BeanUtils.copyProperties(plRetailApplicantRequest, applicantDetail, CommonUtils.IgnorableCopy.getPlRetailProfile());

            List<BankingRelation> bankingRelations = new ArrayList<>();
            BankingRelation bankingRelation = null;

            if(!CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest) && !CommonUtils.isObjectNullOrEmpty(plRetailApplicantRequest.getBankingRelationshipList()))
            {
                for(BankRelationshipRequest bankRelationshipRequest : plRetailApplicantRequest.getBankingRelationshipList()) {
                    bankingRelation = new BankingRelation();
                    BeanUtils.copyProperties(bankRelationshipRequest, bankingRelation);
                    bankingRelation.setApplicationId(plRetailApplicantRequest.getApplicationId());
                    if(bankRelationshipRequest.getId() == null) {
                        bankingRelation.setCreatedBy(userId);
                        bankingRelation.setCreatedDate(new Date());
                        bankingRelation.setIsActive(true);
                    }
                    bankingRelation.setModifiedBy(userId);
                    bankingRelation.setModifiedDate(new Date());
                    bankingRelations.add(bankingRelation);
                }
            }

            bankingRelationlRepository.save(bankingRelations);

            applicantRepository.save(applicantDetail);

            List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestsList = plRetailApplicantRequest.getFinancialArrangementsDetailRequestsList();
            if(!CommonUtils.isListNullOrEmpty(financialArrangementsDetailRequestsList)) {
                logger.info("Financial Arrangements Detail List Null Or Empty ------------->");
                for (FinancialArrangementsDetailRequest reqObj : financialArrangementsDetailRequestsList) {
                    FinancialArrangementsDetail saveFinObj = null;
                    if (!CommonUtils.isObjectNullOrEmpty(reqObj.getId())) {
                        saveFinObj = financialArrangementDetailsRepository.findByIdAndIsActive(reqObj.getId(), true);
                    }
                    if (saveFinObj == null || CommonUtils.isObjectNullOrEmpty(saveFinObj)) {
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
                    if (saveObj == null || CommonUtils.isObjectNullOrEmpty(saveObj)) {
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
            if(plRetailApplicantRequest.getProposalId() != null) {
            	applicationProposalMappingRepository.setIsApplicantProfileMandatoryFilled(plRetailApplicantRequest.getProposalId(), finalUserId,
            			plRetailApplicantRequest.getIsApplicantDetailsFilled());
            	applicationProposalMappingRepository.setIsPrimaryLocked(plRetailApplicantRequest.getProposalId(), finalUserId, true);
            	
            }else {
            	loanApplicationRepository.setIsApplicantProfileMandatoryFilled(plRetailApplicantRequest.getApplicationId(), finalUserId,
            			plRetailApplicantRequest.getIsApplicantDetailsFilled());
            	loanApplicationRepository.setIsPrimaryLocked(plRetailApplicantRequest.getApplicationId(), finalUserId,true);
            }
            
            return true;

        } catch (Exception e) {
            logger.error("Error while Saving Retail Primary :- ",e);
            throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }
    
    @Override
    public Boolean saveBankRelation(Long userId, Long applicationId, BankRelationshipRequest request) {
    	
    	BankingRelation bankingRelations = new BankingRelation();
    	bankingRelations.setCreatedBy(userId);
    	bankingRelations.setCreatedDate(new Date());
    	bankingRelations.setIsActive(Boolean.TRUE);
    	bankingRelations.setApplicationId(applicationId);
    	bankingRelations.setBank(request.getBank());
    	bankingRelations.setIsSalaryAccount(request.getIsSalaryAccount());
    	bankingRelations.setModifiedBy(userId);
    	bankingRelations.setModifiedDate(new Date());
    	bankingRelations.setSinceMonth(request.getSinceMonth());
    	bankingRelations.setSinceYear(request.getSinceYear());
    	bankingRelations.setCoApplicantId(request.getCoApplicantId());
    	
    	bankingRelationlRepository.save(bankingRelations);
    	return Boolean.TRUE;
    }
    
    @Override
    public Boolean inactivateBankRelation(Long id, Long userId) {
    	BankingRelation bankingRelations = bankingRelationlRepository.findOne(id);
    	bankingRelations.setIsActive(Boolean.FALSE);
    	bankingRelations.setModifiedBy(userId);
    	bankingRelations.setModifiedDate(new Date());
    	bankingRelationlRepository.save(bankingRelations);
    	return Boolean.TRUE;
    }
    
    @Override
    public List<BankRelationshipRequest> getBankRelations(Long applicationId, Long coApplicantId) {
    	List<BankRelationshipRequest> bankRelationshipRequests = new ArrayList<>();
        List<BankingRelation> bankingRelations = new ArrayList<>();
    	if(coApplicantId != null) {
            bankingRelations = bankingRelationlRepository.listBankRelationAppId(applicationId, coApplicantId);
        } else{
            bankingRelations = bankingRelationlRepository.listBankRelationAppId(applicationId);
        }
        BankRelationshipRequest bankRelationshipRequest = null;
        for(BankingRelation bankingRelation : bankingRelations) {
        	bankRelationshipRequest = new BankRelationshipRequest();
        	BeanUtils.copyProperties(bankingRelation, bankRelationshipRequest);
        	bankRelationshipRequests.add(bankRelationshipRequest);
        }
    	return bankRelationshipRequests;
    }

    @Override
    public PLRetailApplicantRequest getPrimary(Long userId, Long applicationId) throws LoansException {
        try {
            RetailApplicantDetail applicantDetail = applicantRepository.findByApplicationId(applicationId);
            
            if (applicantDetail == null) {
                PLRetailApplicantRequest request = new PLRetailApplicantRequest();
                LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
                if (applicationMaster != null){
                    logger.info("getByIdAndUserId called successfully");
                    request.setLoanTypeId(applicationMaster.getProductId());
                }
                return request;
            }
            PLRetailApplicantRequest applicantRequest = new PLRetailApplicantRequest();
            applicantRequest.setLoanAmountRequiredString(CommonUtils.convertValueWithoutDecimal(applicantDetail.getLoanAmountRequired()));
            applicantRequest.setMonthlyIncomeString(CommonUtils.convertValueWithoutDecimal(applicantDetail.getMonthlyIncome()));
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

            List<BankRelationshipRequest> bankRelationshipRequests = new ArrayList<>();
            List<BankingRelation> bankingRelations = bankingRelationlRepository.listBankRelationAppId(applicationId);
            BankRelationshipRequest bankRelationshipRequest = null;
            for(BankingRelation bankingRelation : bankingRelations) {
            	bankRelationshipRequest = new BankRelationshipRequest();
            	BeanUtils.copyProperties(bankingRelation, bankRelationshipRequest);
            	bankRelationshipRequests.add(bankRelationshipRequest);
            }
            applicantRequest.setBankingRelationshipList(bankRelationshipRequests);

            return applicantRequest;
        } catch (Exception e) {
            logger.error("Error while getting Retail Primary :- ",e);
            throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public boolean saveFinal(RetailFinalInfoRequest applicantRequest, Long userId) throws LoansException {
        try {
            if (applicantRequest.getApplicationId() == null) {
                throw new NullPointerException("Application Id and ID(Primary Key) must not be null=>Application ID==>"
                        + applicantRequest.getApplicationId() + " User Id (Primary Key)==>" + userId);
            }
            Long finaluserId = (CommonUtils.isObjectNullOrEmpty(applicantRequest.getClientId()) ? userId
                    : applicantRequest.getClientId());
            
            RetailApplicantDetail applicantDetail = null;
            if(applicantRequest.getProposalId() != null) {
            	applicantDetail = applicantRepository.findByProposalId(applicantRequest.getApplicationId(), 
            			applicantRequest.getProposalId());
            }else {
            	applicantDetail = applicantRepository.getByApplicationAndUserId(finaluserId,
                        applicantRequest.getApplicationId());
            }
            
            if (applicantDetail == null) {
                throw new NullPointerException(
                        "Applicant ID and ID(Primary Key) does not match with the database==> Applicant ID==>"
                                + applicantRequest.getApplicationId() + "User ID==>" + userId);
            }
            applicantDetail.setModifiedBy(userId);
            applicantDetail.setModifiedDate(new Date());
            BeanUtils.copyProperties(applicantRequest, applicantDetail, CommonUtils.IgnorableCopy.getRetailPlProfile());
            copyAddressFromRequestToDomainForFinal(applicantRequest, applicantDetail, PERMANENT_LITERAL);
            copyAddressFromRequestToDomainForFinal(applicantRequest, applicantDetail, OFFICE_LITERAL);
            applicantRepository.save(applicantDetail);
            // Updating Final Flag
            
            if(applicantRequest.getProposalId() != null) {
            	applicationProposalMappingRepository.setIsApplicantFinalMandatoryFilled(applicantRequest.getProposalId(),
            			applicantRequest.getApplicationId(), finaluserId, applicantRequest.getIsApplicantFinalFilled());
            }else {
            	loanApplicationRepository.setIsApplicantFinalMandatoryFilled(applicantRequest.getApplicationId(),
                        finaluserId, applicantRequest.getIsApplicantFinalFilled());
            }
            
            // Updating Final Count

            return true;
        } catch (Exception e) {
            logger.error("Error while Saving Retail Final :- ",e);
            throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public RetailFinalInfoRequest getFinal(Long userId, Long applicationId) throws LoansException {
        try {
            RetailApplicantDetail applicantDetail = applicantRepository.findByApplicationId(applicationId);
            if (applicantDetail == null) {
                throw new NullPointerException("RetailApplicantDetail Record of Final Portion not exists in DB of ID : "
                        + userId + "  ApplicationId==>" + applicationId);
            }
            RetailFinalInfoRequest applicantRequest = new RetailFinalInfoRequest();
            BeanUtils.copyProperties(applicantDetail, applicantRequest, CommonUtils.IgnorableCopy.getRetailPlProfile());
            copyAddressFromDomainToRequestForFinal(applicantDetail, applicantRequest, "contact");
            copyAddressFromDomainToRequestForFinal(applicantDetail, applicantRequest, PERMANENT_LITERAL);
            copyAddressFromDomainToRequestForFinal(applicantDetail, applicantRequest, OFFICE_LITERAL);
            return applicantRequest;
        } catch (Exception e) {
            logger.error("Error while getting Retail Final :- ",e);
            throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
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
        
        Address officeAddress = new Address();
        officeAddress.setPremiseNumber(from.getOfficePremiseNumberName());
        officeAddress.setLandMark(from.getOfficeLandMark());
        officeAddress.setStreetName(from.getOfficeStreetName());
        officeAddress.setCityId(from.getOfficeCityId());
        officeAddress.setStateId(from.getOfficeStateId());
        officeAddress.setCountryId(from.getOfficeCountryId());
        officeAddress.setPincode(from.getOfficePincode());
        officeAddress.setDistrictMappingId(from.getOfficeDistrictMappingId());
        to.setOfficeAddress(officeAddress);

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

	@Override
	public PLRetailApplicantRequest getPrimaryByProposalId(Long userId, Long applicationId, Long proposalId)
			throws LoansException {
		try {
            RetailApplicantDetail applicantDetail = applicantRepository.findByProposalId(applicationId, proposalId);
            PLRetailApplicantRequest applicantRequest = new PLRetailApplicantRequest();
            if (applicantDetail == null) {
                
//                LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId,
//                        userId);
//                ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.findByProposalIdAndIsActive(proposalId, true);
//                if (applicationProposalMapping != null){
//                    logger.info("getByIdAndUserId called successfully");
//                }
                return applicantRequest;
            }
            
            applicantRequest.setLoanAmountRequiredString(CommonUtils.convertValueWithoutDecimal(applicantDetail.getLoanAmountRequired()));
            applicantRequest.setMonthlyIncomeString(CommonUtils.convertValueWithoutDecimal(applicantDetail.getMonthlyIncome()));
            BeanUtils.copyProperties(applicantDetail, applicantRequest);

            List<FinancialArrangementsDetail> financialArrangementsDetailList= financialArrangementDetailsRepository.listSecurityCorporateDetailByAppId(applicationId);
            List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList= new ArrayList<FinancialArrangementsDetailRequest>(financialArrangementsDetailList.size());
            List<BankRelationshipRequest> bankRelationshipRequests = new ArrayList<>();
            List<BankingRelation> bankingRelations = bankingRelationlRepository.listBankRelationAppId(applicationId);

            FinancialArrangementsDetailRequest financialRequest = null;
            for(FinancialArrangementsDetail financialDetail : financialArrangementsDetailList){
                financialRequest = new FinancialArrangementsDetailRequest();
                BeanUtils.copyProperties(financialDetail, financialRequest);
                financialArrangementsDetailRequestList.add(financialRequest);
            }
            applicantRequest.setFinancialArrangementsDetailRequestsList(financialArrangementsDetailRequestList);

            BankRelationshipRequest bankRelationshipRequest = null;
            for(BankingRelation bankingRelation : bankingRelations) {
            	bankRelationshipRequest = new BankRelationshipRequest();
            	BeanUtils.copyProperties(bankingRelation, bankRelationshipRequest);
            	if(bankingRelation.getSinceYear() !=null && bankingRelation.getSinceMonth() != null) {
            		LocalDate since = LocalDate.of(bankingRelation.getSinceYear(), bankingRelation.getSinceMonth(), 1);
            		LocalDate today = LocalDate.now();
            		Period age = Period.between(since, today);
            		int years = age.getYears();
            		int months = age.getMonths();
            		bankRelationshipRequest.setSinceYear(years);
            		bankRelationshipRequest.setSinceMonth(months);
            		bankRelationshipRequest.setSinceWhen((bankRelationshipRequest.getSinceYear() != null ? bankRelationshipRequest.getSinceYear() +" year" : "") + " " +(bankRelationshipRequest.getSinceMonth() != null ? bankRelationshipRequest.getSinceMonth()+" months" :  "" ));
            	}
            	bankRelationshipRequests.add(bankRelationshipRequest);
            }
            applicantRequest.setBankingRelationshipList(bankRelationshipRequests);

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
            throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
        }
	}

	@Override
	public RetailFinalInfoRequest getFinalByProposalId(Long userId, Long applicationId, Long proposalId)
			throws LoansException {
		
		try {
            RetailApplicantDetail applicantDetail = applicantRepository.findByProposalId(applicationId, proposalId);
            if (applicantDetail == null) {
                throw new NullPointerException("RetailApplicantDetail Record of Final Portion not exists in DB of ID : "
                        + userId + "  ApplicationId==>" + applicationId+" proposalID ==> "+proposalId);
            }
            RetailFinalInfoRequest applicantRequest = new RetailFinalInfoRequest();
            if(applicantDetail.getApplicationProposalMapping() != null) {
            	applicantRequest.setApplicationStatus(applicantDetail.getApplicationProposalMapping().getApplicationStatusMaster() != null ? applicantDetail.getApplicationProposalMapping().getApplicationStatusMaster().getId() : null);
            }
            
            BeanUtils.copyProperties(applicantDetail, applicantRequest, CommonUtils.IgnorableCopy.getRetailPlProfile());
            copyAddressFromDomainToRequestForFinal(applicantDetail, applicantRequest, "contact");
            copyAddressFromDomainToRequestForFinal(applicantDetail, applicantRequest, PERMANENT_LITERAL);
            copyAddressFromDomainToRequestForFinal(applicantDetail, applicantRequest, OFFICE_LITERAL);
            
            if(applicantDetail.getPreviousJobYear() != null && applicantDetail.getPreviousJobMonth() != null) {
            	LocalDate since = LocalDate.of(applicantDetail.getPreviousJobYear(), applicantDetail.getPreviousJobMonth(), 1);
        		LocalDate today = LocalDate.now();
        		Period age = Period.between(since, today);
        		int years = age.getYears();
        		int months = age.getMonths();
        		applicantRequest.setPreviousJobYear(years);
        		applicantRequest.setPreviousJobMonth(months);
            }else {
            	applicantRequest.setPreviousJobYear(null);
            }
            
            return applicantRequest;
        } catch (Exception e) {
            logger.error("Error while getting Retail Final :- ",e);
            throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
        }
	}
	
	public PLRetailApplicantRequest getRetailBasicDetail(Long userId, Long applicationId) throws LoansException {
        try {
            RetailApplicantDetail applicantDetail = applicantRepository.findByApplicationId(applicationId);
            PLRetailApplicantRequest applicantRequest = new PLRetailApplicantRequest();
            if (applicantDetail == null) {
                return applicantRequest;
            }
            BeanUtils.copyProperties(applicantDetail, applicantRequest);
            copyAddressFromDomainToRequest(applicantDetail, applicantRequest);

            if(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getBusinessStartDate())) {
    			Calendar cal = Calendar.getInstance();
    			cal.setTime(applicantDetail.getBusinessStartDate());
    			applicantRequest.setBusinessStartMonth(cal.get(Calendar.MONTH));
    			applicantRequest.setBusinessStartYear(cal.get(Calendar.YEAR));
    		}
            
            if(applicantRequest.getSalaryBankYear() !=null && applicantRequest.getSalaryBankMonth()!= null) {

				LocalDate since = LocalDate.of(applicantRequest.getSalaryBankYear(), applicantRequest.getSalaryBankMonth(), 1);
		        LocalDate today = LocalDate.now();

		        Period age = Period.between(since, today);
		        int years = age.getYears();
		        int months = age.getMonths();

				applicantRequest.setSalaryBankYear(years);
				applicantRequest.setSalaryBankMonth(months);
			}

            
            // financialArrangement data fetched and copy in beanUtil 

            List<FinancialArrangementsDetail> financialArrangementsDetailList= financialArrangementDetailsRepository.listSecurityCorporateDetailByAppId(applicationId);
            List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList= new ArrayList<FinancialArrangementsDetailRequest>(financialArrangementsDetailList.size());
            FinancialArrangementsDetailRequest financialRequest = null;
            for(FinancialArrangementsDetail financialDetail : financialArrangementsDetailList){
                financialRequest = new FinancialArrangementsDetailRequest();
                BeanUtils.copyProperties(financialDetail, financialRequest);
                financialArrangementsDetailRequestList.add(financialRequest);
            }
            
            List<CreditCardsDetail> creditCardsDetailList= creditCardsDetailRepository.listCreditCardsFromAppId(applicationId);
            for(CreditCardsDetail creditCardsDetail: creditCardsDetailList){
            	financialRequest = new FinancialArrangementsDetailRequest();
                financialRequest.setFinancialInstitutionName(creditCardsDetail.getIssuerName());
                financialRequest.setOutstandingAmount(creditCardsDetail.getOutstandingBalance());
                financialRequest.setLoanType("Credit Card");
                financialRequest.setIsManuallyAdded(false);
                financialArrangementsDetailRequestList.add(financialRequest);
            }
            
            applicantRequest.setFinancialArrangementsDetailRequestsList(financialArrangementsDetailRequestList);
            
            return applicantRequest;
        } catch (Exception e) {
            logger.error("Error while getting Retail Profile :- ",e);
            throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }
	
	@Override
	public boolean checkCoAppProfileBeforeSelectHL(Long applicationId) {
		List<CoApplicantDetail> coAppIdList = coApplicantDetailRepository.getAllByApplicationId(applicationId);
		if(!coAppIdList.isEmpty()) {
			for(CoApplicantDetail coApp : coAppIdList) {
				if((CommonUtils.isObjectNullOrEmpty(coApp.getIsBankStatementCompleted()) || !coApp.getIsBankStatementCompleted())
						|| (CommonUtils.isObjectNullOrEmpty(coApp.getIsItrCompleted()) || !coApp.getIsItrCompleted())
						|| (CommonUtils.isObjectNullOrEmpty(coApp.getIsCibilCompleted()) || !coApp.getIsCibilCompleted())
						|| (CommonUtils.isObjectNullOrEmpty(coApp.getIsBasicInfoFilled()) || !coApp.getIsBasicInfoFilled())
						|| (CommonUtils.isObjectNullOrEmpty(coApp.getIsEmploymentInfoFilled()) || !coApp.getIsEmploymentInfoFilled())
						|| (CommonUtils.isObjectNullOrEmpty(coApp.getIsContactInfoFilled()) || !coApp.getIsContactInfoFilled())
						|| (CommonUtils.isObjectNullOrEmpty(coApp.getIsOneFormCompleted()) || !coApp.getIsOneFormCompleted())) {
					return false;
				}
			}
		}
		return true;
	}
	
	public RetailOnformBasicInfoReq getOneformBasicInfo(Long applicationId, Long coAppId) {
		if(!CommonUtils.isObjectNullOrEmpty(coAppId)) {
			CoApplicantDetail coApplicantDetail = coApplicantDetailRepository.findByIdAndIsActive(coAppId, true);
			if(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail)) {
				RetailOnformBasicInfoReq res = new RetailOnformBasicInfoReq();
				BeanUtils.copyProperties(coApplicantDetail, res);
				res.setIsItrSkip(coApplicantDetail.getIsItrSkip());
				res.setResidentialStatus(coApplicantDetail.getNationality());
				res.setNoOfDependent(coApplicantDetail.getNoDependent());
				return res;
			}
		} else {
			RetailApplicantDetail applicantDetail = applicantRepository.findByApplicationId(applicationId);
			if(!CommonUtils.isObjectNullOrEmpty(applicantDetail)) {
				RetailOnformBasicInfoReq res = new RetailOnformBasicInfoReq();
				BeanUtils.copyProperties(applicantDetail, res );
				return res;	
			}
		}
		return null;
	}
	
	public RetailOnformEmploymentInfoReq getOneformEmploymentInfo(Long applicationId, Long coAppId) {
		if(!CommonUtils.isObjectNullOrEmpty(coAppId)) {
			CoApplicantDetail coApplicantDetail = coApplicantDetailRepository.findByIdAndIsActive(coAppId, true);
			if(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail)) {
				RetailOnformEmploymentInfoReq res = new RetailOnformEmploymentInfoReq();
				BeanUtils.copyProperties(coApplicantDetail, res);
				if(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBusinessStartDate())) {
	    			Calendar cal = Calendar.getInstance();
	    			cal.setTime(coApplicantDetail.getBusinessStartDate());
	    			res.setBusinessStartMonth(cal.get(Calendar.MONTH));
	    			res.setBusinessStartYear(cal.get(Calendar.YEAR));
	    		}
				res.setBirthDate(coApplicantDetail.getBirthDate());
				res.setSalaryMode(coApplicantDetail.getModeOfReceipt());
				return res;
			}
		} else {
			RetailApplicantDetail applicantDetail = applicantRepository.findByApplicationId(applicationId);
			if(!CommonUtils.isObjectNullOrEmpty(applicantDetail)) {
				RetailOnformEmploymentInfoReq res = new RetailOnformEmploymentInfoReq();
				BeanUtils.copyProperties(applicantDetail, res);
				if(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getBusinessStartDate())) {
	    			Calendar cal = Calendar.getInstance();
	    			cal.setTime(applicantDetail.getBusinessStartDate());
	    			res.setBusinessStartMonth(cal.get(Calendar.MONTH));
	    			res.setBusinessStartYear(cal.get(Calendar.YEAR));
	    		}
				res.setBirthDate(applicantDetail.getBirthDate());
				return res;	
			}
		}
		return null;
	}

	public RetailOnformContactInfoReq getOneformContactInfo(Long applicationId, Long coAppId) {
		if(!CommonUtils.isObjectNullOrEmpty(coAppId)) {
			CoApplicantDetail coApplicantDetail = coApplicantDetailRepository.findByIdAndIsActive(coAppId, true);
			if(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail)) {
				RetailOnformContactInfoReq res = new RetailOnformContactInfoReq();
				BeanUtils.copyProperties(coApplicantDetail, res);
				Address address = new Address();
		        address.setPremiseNumber(coApplicantDetail.getAddressPremiseName());
		        address.setLandMark(coApplicantDetail.getAddressLandmark());
		        address.setStreetName(coApplicantDetail.getAddressStreetName());
		        if(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getAddressCity())) {
		        	address.setCityId(coApplicantDetail.getAddressCity().longValue());	 
		        }
		        address.setStateId(CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getAddressState()) ? null : coApplicantDetail.getAddressState().intValue());
		        address.setCountryId(coApplicantDetail.getAddressCountry());
		        if(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getAddressPincode())) {
		        	address.setPincode(coApplicantDetail.getAddressPincode().longValue());	 
		        }
		        address.setDistrictMappingId(coApplicantDetail.getAddressDistrictMappingId());
		        res.setContactAddress(address);
		        res.setPan(coApplicantDetail.getPan());
		        res.setBirthDate(coApplicantDetail.getBirthDate());
				return res;
			}
		} else {
			RetailApplicantDetail applicantDetail = applicantRepository.findByApplicationId(applicationId);
			if(!CommonUtils.isObjectNullOrEmpty(applicantDetail)) {
				RetailOnformContactInfoReq res = new RetailOnformContactInfoReq();
				BeanUtils.copyProperties(applicantDetail, res);
				Address address = new Address();
		        address.setPremiseNumber(applicantDetail.getAddressPremiseName());
		        address.setLandMark(applicantDetail.getAddressLandmark());
		        address.setStreetName(applicantDetail.getAddressStreetName());
		        if(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getAddressCity())) {
		        	address.setCityId(applicantDetail.getAddressCity().longValue());	 
		        }
		        address.setStateId(CommonUtils.isObjectNullOrEmpty(applicantDetail.getAddressState()) ? null : applicantDetail.getAddressState().intValue());
		        address.setCountryId(applicantDetail.getAddressCountry());
		        if(!CommonUtils.isObjectNullOrEmpty(applicantDetail.getAddressPincode())) {
		        	address.setPincode(applicantDetail.getAddressPincode().longValue());	 
		        }
		        address.setDistrictMappingId(applicantDetail.getAddressDistrictMappingId());
		        res.setContactAddress(address);
		        res.setPan(applicantDetail.getPan());
		        res.setBirthDate(applicantDetail.getBirthDate());
				return res;	
			}
		}
		return null;
	}
	
	public List<FinancialArrangementsDetailRequest> getOneformCreditInfo(Long applicationId, Long coAppId) {
		if(!CommonUtils.isObjectNullOrEmpty(coAppId)) {
			try {
	        	List<FinancialArrangementsDetail> retailFinancialDetailsList = financialArrangementDetailsRepository.findByDirectorBackgroundDetailAndApplicationIdIdAndIsActive(coAppId, applicationId, true);
	            if(retailFinancialDetailsList != null) {
	            	List<FinancialArrangementsDetailRequest> retailFinancialDetailsReq= new ArrayList<FinancialArrangementsDetailRequest>(retailFinancialDetailsList.size());
	                FinancialArrangementsDetailRequest retailFinReq=null;
	                for(FinancialArrangementsDetail finArDetails : retailFinancialDetailsList) {
	                	retailFinReq =new FinancialArrangementsDetailRequest();
	                	BeanUtils.copyProperties(finArDetails, retailFinReq);
	                	retailFinancialDetailsReq.add(retailFinReq);
	                }
	                return retailFinancialDetailsReq;
	            }
			} catch (Exception e) {
				logger.error("=======>>>>> Error while fetching FinancialArrangementDetails while coapplicant <<<<<<<=========",e);
			}	
		} else {
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
                    return retailFinancialDetailsReq;
                }
			} catch (Exception e) {
				logger.error("=======>>>>> Error while fetching FinancialArrangementDetails <<<<<<<=========",e);
			}
		}
		return Collections.emptyList();
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getApplicantAndCoAppOneFormInfo(Long applicationId, Long coAppId) {
		if(!CommonUtils.isObjectNullOrEmpty(coAppId)) {
			CoApplicantDetail coApplicantDetail = coApplicantDetailRepository.findByIdAndIsActive(coAppId, true);
			if(!CommonUtils.isObjectNullOrEmpty(coApplicantDetail)) {
				JSONObject json = new JSONObject();
				json.put("isBasicInfoFilled", coApplicantDetail.getIsBasicInfoFilled());
				json.put("isEmploymentInfoFilled",coApplicantDetail.getIsEmploymentInfoFilled());
				json.put("isCreditInfoFilled",coApplicantDetail.getIsCreditInfoFilled());
				json.put("isContactInfoFilled",coApplicantDetail.getIsContactInfoFilled());
				json.put("isCibilCompleted",coApplicantDetail.getIsCibilCompleted());
				json.put("isOneFormCompleted",coApplicantDetail.getIsOneFormCompleted());
				return json;
			}
		} else {
			RetailApplicantDetail applicantDetail = applicantRepository.findByApplicationId(applicationId);
			if(!CommonUtils.isObjectNullOrEmpty(applicantDetail)) {
				RetailOnformContactInfoReq res = new RetailOnformContactInfoReq();
				JSONObject json = new JSONObject();
				json.put("isBasicInfoFilled",applicantDetail.getIsBasicInfoFilled());
				json.put("isEmploymentInfoFilled",applicantDetail.getIsEmploymentInfoFilled());
				json.put("isCreditInfoFilled",applicantDetail.getIsCreditInfoFilled());
				json.put("isContactInfoFilled",applicantDetail.getIsContactInfoFilled());
				json.put("isCibilCompleted",applicantDetail.getIsCibilCompleted());
				json.put("isOneFormCompleted",applicantDetail.getIsOneFormCompleted());
				return json;
			}
		}
		return null;
	}
}
