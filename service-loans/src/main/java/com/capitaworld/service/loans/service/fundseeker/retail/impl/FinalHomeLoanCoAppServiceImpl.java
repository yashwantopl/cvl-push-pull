package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.*;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.retail.*;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.*;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.retail.FinalHomeLoanCoAppService;
import com.capitaworld.service.loans.service.fundseeker.retail.FinalHomeLoanService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.enums.EducationStatusRetailMst;
import com.capitaworld.service.oneform.enums.OccupationNature;
import com.capitaworld.service.oneform.enums.Title;
import org.json.simple.JSONObject;
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
public class FinalHomeLoanCoAppServiceImpl implements FinalHomeLoanCoAppService {

    private static final Logger logger = LoggerFactory.getLogger(FinalHomeLoanCoAppServiceImpl.class);

    @Autowired
    private FinalHomeLoanDetailRepository finalHomeLoanDetailRepository;

    @Autowired
    private FinalHomeLoanCoAppDetailRepository finalHomeLoanCoAppDetailRepository;

    @Autowired
    private RetailApplicantDetailRepository retailApplicantDetailRepository;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private LoanApplicationService loanApplicationService;

    @Autowired
    private PurchasePropertyDetailsRepository purchasePropertyDetailsRepository;

    @Autowired
    private OtherPropertyDetailsRepository otherPropertyDetailsRepository;

    @Autowired
    private EmpSalariedTypeRepository empSalariedTypeRepository;

    @Autowired
    private EmpSelfEmployedTypeRepository empSelfEmployedTypeRepository;

    @Autowired
    private EmpAgriculturistTypeRepository empAgriculturistTypeRepository;

    @Autowired
    private BankAccountHeldDetailRepository bankAccountHeldDetailRepository;

    @Autowired
    private OtherIncomeDetailRepository otherIncomeDetailRepository;

    @Autowired
    private OtherCurrentAssetDetailRepository otherCurrentAssetDetailRepository;

    @Autowired
    private FixedDepositsDetailRepository fixedDepositsDetailRepository;

    @Autowired
    private ReferenceRetailDetailsRepository referenceRetailDetailsRepository;

    @Autowired
    private CoApplicantDetailRepository coApplicantDetailRepository;

    @Override
    public boolean saveOrUpdate(FinalHomeLoanCoApplicantDetailRequest finalHomeLoanDetailRequest, Long userId) throws LoansException {
        try {
            Long finalUserId = (CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailRequest.getClientId()) ? userId : finalHomeLoanDetailRequest.getClientId());
            FinalHomeLoanCoApplicantDetail finalHomeLoanDetailTmp = finalHomeLoanCoAppDetailRepository
                    .getByApplicationAndProposalIdAndCoAppId(finalHomeLoanDetailRequest.getApplicationId(), finalHomeLoanDetailRequest.getProposalId(),finalHomeLoanDetailRequest.getCoApplicantId());
            if (finalHomeLoanDetailTmp == null) {
                finalHomeLoanDetailTmp = new FinalHomeLoanCoApplicantDetail();
                finalHomeLoanDetailTmp.setCreatedBy(userId);
                finalHomeLoanDetailTmp.setCreatedDate(new Date());
                finalHomeLoanDetailTmp.setIsActive(true);
                finalHomeLoanDetailTmp
                        .setApplicationId(new LoanApplicationMaster(finalHomeLoanDetailRequest.getApplicationId()));
                finalHomeLoanDetailTmp.setProposalId(new ApplicationProposalMapping(finalHomeLoanDetailRequest.getProposalId()));
            } else {
                finalHomeLoanDetailTmp.setModifiedBy(userId);
                finalHomeLoanDetailTmp.setModifiedDate(new Date());
            }
            String[] corporate = new String[CommonUtils.IgnorableCopy.getCORPORATE().length + 1];
            corporate[CommonUtils.IgnorableCopy.getCORPORATE().length-1] = "isActive";
            corporate[CommonUtils.IgnorableCopy.getCORPORATE().length] = CommonUtils.IgnorableCopy.ID;
            BeanUtils.copyProperties(finalHomeLoanDetailRequest, finalHomeLoanDetailTmp, corporate);
            Address permanentAddress = finalHomeLoanDetailRequest.getPermanentAddress();
            Address correspondenceAddress = finalHomeLoanDetailRequest.getCorrespondenceAddress();

            finalHomeLoanDetailTmp.setPermanentPremiseNo(permanentAddress.getPremiseNumber());
            finalHomeLoanDetailTmp.setPermanentStreetName(permanentAddress.getStreetName());
            finalHomeLoanDetailTmp.setPermanentCity(permanentAddress.getCityId().intValue());
            finalHomeLoanDetailTmp.setPermanentState(permanentAddress.getStateId());
            finalHomeLoanDetailTmp.setPermanentCountry(permanentAddress.getCountryId());
            finalHomeLoanDetailTmp.setPermanentLandmark(permanentAddress.getLandMark());
            finalHomeLoanDetailTmp.setPermanentPinCode(permanentAddress.getPincode().intValue());


            finalHomeLoanDetailTmp.setCorrespondencePremiseNo(correspondenceAddress.getPremiseNumber());
            finalHomeLoanDetailTmp.setCorrespondenceStreetName(correspondenceAddress.getStreetName());
            finalHomeLoanDetailTmp.setCorrespondenceCity(correspondenceAddress.getCityId().intValue());
            finalHomeLoanDetailTmp.setCorrespondenceState(correspondenceAddress.getStateId());
            finalHomeLoanDetailTmp.setPermanentCountry(correspondenceAddress.getCountryId());
            finalHomeLoanDetailTmp.setCorrespondenceLandmark(correspondenceAddress.getLandMark());
            finalHomeLoanDetailTmp.setCorrespondencePinCode(correspondenceAddress.getPincode().intValue());
            finalHomeLoanDetailTmp = finalHomeLoanCoAppDetailRepository.save(finalHomeLoanDetailTmp);

            if (finalHomeLoanDetailTmp != null) {
                logger.info("finalHomeLoanDetailTmp is saved successfully");
            }

            //setting Flag to DB
            /*if(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailRequest.getIsFinalInformationFilled())){
//				we are reusing this method and also same column in loanApplication master. it is actually using Corporate. 
				loanApplicationRepository.setIsFinalMcqMandatoryFilled(finalHomeLoanDetailRequest.getApplicationId(), finalUserId, finalHomeLoanDetailRequest.getIsFinalInformationFilled());
			}*/

            //Update Bowl Count Flag
            loanApplicationRepository.setFinalFilledCount(finalHomeLoanDetailRequest.getApplicationId(), finalUserId, finalHomeLoanDetailRequest.getFinalFilledCount());
            return true;
        } catch (Exception e) {
            logger.error("Error while Saving Final Home Loan Details:-", e);
            throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }

    private void addOneformDetails(FinalHomeLoanCoApplicantDetailRequest finalHomeLoanDetailRequest) {

        CoApplicantDetail retailApplicantDetail = coApplicantDetailRepository.findByIdAndIsActive(finalHomeLoanDetailRequest.getCoApplicantId(),true);
        try {
            Title.getById(retailApplicantDetail.getTitleId()).getValue();
            finalHomeLoanDetailRequest.setName(Title.getById(retailApplicantDetail.getTitleId()).getValue() + " " + retailApplicantDetail.getFirstName() + " " + retailApplicantDetail.getMiddleName() + " " + retailApplicantDetail.getFatherName());
            finalHomeLoanDetailRequest.setFatherFullName(retailApplicantDetail.getFatherName());

            Address permanentAddress = new Address();
            permanentAddress.setPremiseNumber(retailApplicantDetail.getAddressPremiseName());
            permanentAddress.setStreetName(retailApplicantDetail.getAddressStreetName());
            permanentAddress.setCityId(Long.valueOf(retailApplicantDetail.getAddressCity()));
            permanentAddress.setStateId(retailApplicantDetail.getAddressState());
            permanentAddress.setCountryId(retailApplicantDetail.getAddressCountry());
            permanentAddress.setPincode(Long.valueOf(String.valueOf(retailApplicantDetail.getAddressPincode())));
            permanentAddress.setLandMark(retailApplicantDetail.getAddressLandmark());

            finalHomeLoanDetailRequest.setPermanentAddress(permanentAddress);
            finalHomeLoanDetailRequest.setEducationalQualification(EducationStatusRetailMst.getById(retailApplicantDetail.getEducationQualification()).getValue());
            finalHomeLoanDetailRequest.setEmployeeType(retailApplicantDetail.getEmploymentType());
        }catch (Exception e){
            logger.error("Error while getting co-applicant details from one form, One or more fields are null....", e.getMessage());
        }
    }

    private void addEmployementDetails(FinalHomeLoanCoApplicantDetailRequest finalHomeLoanDetailRequest) {

        try{
        OccupationNature occupationNature = OccupationNature.getById(finalHomeLoanDetailRequest.getEmployeeType());
        switch (occupationNature) {

            case SALARIED:
                List<EmpSalariedTypeRequest> empSalariedTypeRequests = new ArrayList<>();
                List<EmpSalariedType> empSalariedTypes = empSalariedTypeRepository.getListByApplicationId(finalHomeLoanDetailRequest.getCoApplicantId());
                for (EmpSalariedType empSalariedType : empSalariedTypes) {
                    EmpSalariedTypeRequest empSalariedTypeRequest = new EmpSalariedTypeRequest();
                    BeanUtils.copyProperties(empSalariedType, empSalariedTypeRequest);
                    empSalariedTypeRequests.add(empSalariedTypeRequest);
                }
                finalHomeLoanDetailRequest.setEmpSalariedTypeList(empSalariedTypeRequests);
                break;

            case AGRICULTURIST:
                List<EmpAgriculturistTypeRequest> empAgriculturistTypes = new ArrayList<>();
                List<EmpAgriculturistType> agriculturistTypes = empAgriculturistTypeRepository.getListByApplicationId(finalHomeLoanDetailRequest.getCoApplicantId());
                for (EmpAgriculturistType empAgriculturistType : agriculturistTypes) {
                    EmpAgriculturistTypeRequest empAgriculturistTypeRequest = new EmpAgriculturistTypeRequest();
                    BeanUtils.copyProperties(empAgriculturistType, empAgriculturistTypeRequest);
                    empAgriculturistTypes.add(empAgriculturistTypeRequest);
                }
                finalHomeLoanDetailRequest.setEmpAgriculturistTypeList(empAgriculturistTypes);
                break;
            case BUSINESS:
            case SELF_EMPLOYED:
                List<EmpSelfEmployedTypeRequest> empSelfEmployedTypeRequests = new ArrayList<>();
                List<EmpSelfEmployedType> empSelfEmployedTypes = empSelfEmployedTypeRepository.getListByApplicationId(finalHomeLoanDetailRequest.getCoApplicantId());
                for (EmpSelfEmployedType empSelfEmployedType : empSelfEmployedTypes) {
                    EmpSelfEmployedTypeRequest empSelfEmployedTypeRequest = new EmpSelfEmployedTypeRequest();
                    BeanUtils.copyProperties(empSelfEmployedType, empSelfEmployedTypeRequest);
                    empSelfEmployedTypeRequests.add(empSelfEmployedTypeRequest);
                }
                finalHomeLoanDetailRequest.setEmpSelfEmployedTypeList(empSelfEmployedTypeRequests);
                break;
        }}catch (Exception e){
            logger.info("Error while getting co-applicant employee details one or more fields are null...", e.getMessage());
        }

    }

    private void addBankAccDetails(FinalHomeLoanCoApplicantDetailRequest finalHomeLoanDetailRequest) {

        List<BankAccountHeldDetailsRequest> bankAccountHeldDetailsRequests = new ArrayList<>();
        List<BankAccountHeldDetail> bankAccountHeldDetails = bankAccountHeldDetailRepository.listBankAccountHeldFromCoAppId(finalHomeLoanDetailRequest.getCoApplicantId());
        for (BankAccountHeldDetail bankAccountHeldDetail : bankAccountHeldDetails) {
            BankAccountHeldDetailsRequest accountHeldDetailsRequests = new BankAccountHeldDetailsRequest();
            BeanUtils.copyProperties(bankAccountHeldDetail, accountHeldDetailsRequests);
            bankAccountHeldDetailsRequests.add(accountHeldDetailsRequests);
        }
        finalHomeLoanDetailRequest.setBankAccountHeldDetailsList(bankAccountHeldDetailsRequests);
    }

    private void addOtherIncomeDetails(FinalHomeLoanCoApplicantDetailRequest finalHomeLoanDetailRequest) {

        List<OtherIncomeDetailRequest> otherIncomeDetailRequests = new ArrayList<>();
        List<OtherIncomeDetail> otherIncomeDetails = otherIncomeDetailRepository.listOtherIncomeFromCoAppId(finalHomeLoanDetailRequest.getCoApplicantId());

        for (OtherIncomeDetail otherIncomeDetail : otherIncomeDetails) {
            OtherIncomeDetailRequest otherIncomeDetailRequest = new OtherIncomeDetailRequest();
            BeanUtils.copyProperties(otherIncomeDetail, otherIncomeDetailRequest);
            otherIncomeDetailRequests.add(otherIncomeDetailRequest);
        }
        finalHomeLoanDetailRequest.setOtherIncomeDetailsList(otherIncomeDetailRequests);
    }

    private void addFixdepositeDetails(FinalHomeLoanCoApplicantDetailRequest finalHomeLoanDetailRequest) {
        List<FixedDepositsDetailsRequest> fixedDepositsDetailsRequests = new ArrayList<>();
        List<FixedDepositsDetail> fixedDepositsDetails = fixedDepositsDetailRepository.listFixedDepositsFromCoAppId(finalHomeLoanDetailRequest.getCoApplicantId());

        for (FixedDepositsDetail fixedDepositsDetail : fixedDepositsDetails) {
            FixedDepositsDetailsRequest fixedDepositsDetailsRequests1 = new FixedDepositsDetailsRequest();
            BeanUtils.copyProperties(fixedDepositsDetail, fixedDepositsDetailsRequests1);
            fixedDepositsDetailsRequests.add(fixedDepositsDetailsRequests1);
        }

        finalHomeLoanDetailRequest.setFixedDepositsDetailsList(fixedDepositsDetailsRequests);
    }

    private void addRefDetails(FinalHomeLoanCoApplicantDetailRequest finalHomeLoanDetailRequest) {
        List<ReferenceRetailDetailsRequest> referenceRetailDetailsRequests = new ArrayList<>();
        List<ReferencesRetailDetail> ReferencesRetailDetails = referenceRetailDetailsRepository.listReferencesRetailFromCoAppId(finalHomeLoanDetailRequest.getCoApplicantId());

        for (ReferencesRetailDetail referencesRetailDetail : ReferencesRetailDetails) {
            ReferenceRetailDetailsRequest referenceRetailDetailsRequest = new ReferenceRetailDetailsRequest();
            BeanUtils.copyProperties(referencesRetailDetail, referenceRetailDetailsRequest);
            referenceRetailDetailsRequests.add(referenceRetailDetailsRequest);
        }

        finalHomeLoanDetailRequest.setReferenceRetailDetailsList(referenceRetailDetailsRequests);
    }

    private void addCurrFinDetails(FinalHomeLoanCoApplicantDetailRequest finalHomeLoanDetailRequest) {

        List<OtherCurrentAssetDetailRequest> otherCurrentAssetDetailRequests = new ArrayList<>();
        List<OtherCurrentAssetDetail> otherCurrentAssetDetails = otherCurrentAssetDetailRepository.listOtherCurrentAssetFromCoAppId(finalHomeLoanDetailRequest.getCoApplicantId());

        for (OtherCurrentAssetDetail currentAssetDetail : otherCurrentAssetDetails) {
            OtherCurrentAssetDetailRequest otherCurrentAssetDetailRequest = new OtherCurrentAssetDetailRequest();
            BeanUtils.copyProperties(currentAssetDetail, otherCurrentAssetDetailRequest);
            otherCurrentAssetDetailRequests.add(otherCurrentAssetDetailRequest);
        }

        finalHomeLoanDetailRequest.setAssetDetailList(otherCurrentAssetDetailRequests);
    }

    @Override
    public FinalHomeLoanCoApplicantDetailRequest get(Long coAppId, Long applicationId, Long userId, Long proposalId) throws LoansException {
        try {
            FinalHomeLoanCoApplicantDetailRequest finalHomeLoanDetailRequest = new FinalHomeLoanCoApplicantDetailRequest();
            finalHomeLoanDetailRequest.setApplicationId(applicationId);
            finalHomeLoanDetailRequest.setProposalId(proposalId);
            finalHomeLoanDetailRequest.setCoApplicantId(coAppId);
            FinalHomeLoanCoApplicantDetail finalHomeLoanDetail = finalHomeLoanCoAppDetailRepository.getByApplicationAndProposalIdAndCoAppId(applicationId,
                    proposalId,coAppId);
            if (finalHomeLoanDetail == null) {
                finalHomeLoanDetail = new FinalHomeLoanCoApplicantDetail();
                addOneformDetails(finalHomeLoanDetailRequest);
            }else{
                if (finalHomeLoanDetail.getCorrespondencePremiseNo() != null) {
                    Address correspondenceAddress = new Address();
                    correspondenceAddress.setPremiseNumber(String.valueOf(finalHomeLoanDetail.getCorrespondencePremiseNo()));
                    correspondenceAddress.setStreetName(finalHomeLoanDetail.getCorrespondenceStreetName());
                    correspondenceAddress.setCityId(Long.valueOf(finalHomeLoanDetail.getCorrespondenceCity()));
                    correspondenceAddress.setStateId(finalHomeLoanDetail.getCorrespondenceState());
                    correspondenceAddress.setCountryId(finalHomeLoanDetail.getCorrespondenceCity());
                    correspondenceAddress.setLandMark(finalHomeLoanDetail.getCorrespondenceLandmark());
                    correspondenceAddress.setPincode(Long.valueOf(finalHomeLoanDetail.getCorrespondencePinCode()));
                    finalHomeLoanDetailRequest.setCorrespondenceAddress(correspondenceAddress);
                }
                if(finalHomeLoanDetail.getPermanentPremiseNo() !=null){
                    Address permanentAddress = new Address();
                    permanentAddress.setPremiseNumber(String.valueOf(finalHomeLoanDetail.getPermanentPremiseNo()));
                    permanentAddress.setStreetName(finalHomeLoanDetail.getPermanentStreetName());
                    permanentAddress.setCityId(Long.valueOf(finalHomeLoanDetail.getCorrespondenceCity()));
                    permanentAddress.setStateId(finalHomeLoanDetail.getPermanentState());
                    permanentAddress.setCountryId(finalHomeLoanDetail.getPermanentCountry());
                    permanentAddress.setLandMark(finalHomeLoanDetail.getPermanentLandmark());
                    permanentAddress.setPincode(Long.valueOf(finalHomeLoanDetail.getPermanentPinCode()));
                    finalHomeLoanDetailRequest.setPermanentAddress(permanentAddress);
                }
            }
            addEmployementDetails(finalHomeLoanDetailRequest);
            addBankAccDetails(finalHomeLoanDetailRequest);
            addCurrFinDetails(finalHomeLoanDetailRequest);
            addRefDetails(finalHomeLoanDetailRequest);
            addFixdepositeDetails(finalHomeLoanDetailRequest);
            addOtherIncomeDetails(finalHomeLoanDetailRequest);

            //finalHomeLoanDetailRequest.setYear(retailApplicantDetail.getQualifyingYear());
            if (finalHomeLoanDetail == null) {
                Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, applicationId);
                JSONObject bowlCount = loanApplicationService.getBowlCount(applicationId, userId);
                finalHomeLoanDetailRequest.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
                if (!CommonUtils.isObjectNullOrEmpty(bowlCount.get("finalFilledCount"))) {
                    finalHomeLoanDetailRequest.setFinalFilledCount(bowlCount.get("finalFilledCount").toString());
                }
                return finalHomeLoanDetailRequest;
            }
            BeanUtils.copyProperties(finalHomeLoanDetail, finalHomeLoanDetailRequest);
            Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, applicationId);
            finalHomeLoanDetailRequest.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
            finalHomeLoanDetailRequest.setFinalFilledCount(finalHomeLoanDetail.getApplicationId().getFinalFilledCount());
            return finalHomeLoanDetailRequest;
        } catch (Exception e) {
            logger.error("Error while getting Final Home Loan Details:-", e);
            throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }
}
