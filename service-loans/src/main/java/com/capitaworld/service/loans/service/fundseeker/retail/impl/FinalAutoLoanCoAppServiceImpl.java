package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.BankAccountHeldDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.CoApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.EmpAgriculturistType;
import com.capitaworld.service.loans.domain.fundseeker.retail.EmpSalariedType;
import com.capitaworld.service.loans.domain.fundseeker.retail.EmpSelfEmployedType;
import com.capitaworld.service.loans.domain.fundseeker.retail.FinalAutoLoanCoApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.FixedDepositsDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.OtherCurrentAssetDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.OtherIncomeDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.ReferencesRetailDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.retail.BankAccountHeldDetailsRequest;
import com.capitaworld.service.loans.model.retail.EmpAgriculturistTypeRequest;
import com.capitaworld.service.loans.model.retail.EmpSalariedTypeRequest;
import com.capitaworld.service.loans.model.retail.EmpSelfEmployedTypeRequest;
import com.capitaworld.service.loans.model.retail.FinalAutoLoanCoApplicantDetailRequest;
import com.capitaworld.service.loans.model.retail.FixedDepositsDetailsRequest;
import com.capitaworld.service.loans.model.retail.OtherCurrentAssetDetailRequest;
import com.capitaworld.service.loans.model.retail.OtherIncomeDetailRequest;
import com.capitaworld.service.loans.model.retail.ReferenceRetailDetailsRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.BankAccountHeldDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.CoApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.EmpAgriculturistTypeRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.EmpSalariedTypeRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.EmpSelfEmployedTypeRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.FinalAutoLoanCoAppDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.FinalHomeLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.FixedDepositsDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.OtherCurrentAssetDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.OtherIncomeDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.OtherPropertyDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PurchasePropertyDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.ReferenceRetailDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.retail.FinalAutoLoanCoAppService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.enums.EducationStatusRetailMst;
import com.capitaworld.service.oneform.enums.OccupationNature;
import com.capitaworld.service.oneform.enums.Title;

@Service
@Transactional
public class FinalAutoLoanCoAppServiceImpl implements FinalAutoLoanCoAppService {

    private static final Logger logger = LoggerFactory.getLogger(FinalHomeLoanCoAppServiceImpl.class);

    @Autowired
    private FinalHomeLoanDetailRepository finalHomeLoanDetailRepository;

    @Autowired
    private FinalAutoLoanCoAppDetailRepository finalAutoLoanCoAppDetailRepository;

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
    public boolean saveOrUpdate(FinalAutoLoanCoApplicantDetailRequest finalAutoLoanDetailRequest, Long userId) throws LoansException {
        try {
            Long finalUserId = (CommonUtils.isObjectNullOrEmpty(finalAutoLoanDetailRequest.getClientId()) ? userId : finalAutoLoanDetailRequest.getClientId());
            FinalAutoLoanCoApplicantDetail finalAutoLoanDetailTmp = finalAutoLoanCoAppDetailRepository.getByApplicationAndProposalIdAndCoAppId(finalAutoLoanDetailRequest.getApplicationId(), finalAutoLoanDetailRequest.getProposalId(),finalAutoLoanDetailRequest.getCoApplicantId());
            if (finalAutoLoanDetailTmp == null) {
                finalAutoLoanDetailTmp = new FinalAutoLoanCoApplicantDetail();
                finalAutoLoanDetailTmp.setCreatedBy(userId);
                finalAutoLoanDetailTmp.setCreatedDate(new Date());
                finalAutoLoanDetailTmp.setApplicationId(new LoanApplicationMaster(finalAutoLoanDetailRequest.getApplicationId()));
                finalAutoLoanDetailTmp.setProposalId(new ApplicationProposalMapping(finalAutoLoanDetailRequest.getProposalId()));
            } else {
                finalAutoLoanDetailTmp.setModifiedBy(userId);
                finalAutoLoanDetailTmp.setModifiedDate(new Date());
            }
            String[] corporate = new String[CommonUtils.IgnorableCopy.getCORPORATE().length + 1];
            corporate[CommonUtils.IgnorableCopy.getCORPORATE().length-1] = "isActive";
            corporate[CommonUtils.IgnorableCopy.getCORPORATE().length] = CommonUtils.IgnorableCopy.ID;
            BeanUtils.copyProperties(finalAutoLoanDetailRequest, finalAutoLoanDetailTmp, corporate);
            finalAutoLoanDetailTmp.setIsActive(true);
            Address permanentAddress = finalAutoLoanDetailRequest.getPermanentAddress();
            Address correspondenceAddress = finalAutoLoanDetailRequest.getCorrespondenceAddress();

            finalAutoLoanDetailTmp.setPermanentPremiseNo(permanentAddress.getPremiseNumber());
            finalAutoLoanDetailTmp.setPermanentStreetName(permanentAddress.getStreetName());
            finalAutoLoanDetailTmp.setPermanentCity(permanentAddress.getCityId().intValue());
            finalAutoLoanDetailTmp.setPermanentState(permanentAddress.getStateId());
            finalAutoLoanDetailTmp.setPermanentCountry(permanentAddress.getCountryId());
            finalAutoLoanDetailTmp.setPermanentLandmark(permanentAddress.getLandMark());
            finalAutoLoanDetailTmp.setPermanentPinCode(permanentAddress.getPincode().intValue());


            finalAutoLoanDetailTmp.setCorrespondencePremiseNo(correspondenceAddress.getPremiseNumber());
            finalAutoLoanDetailTmp.setCorrespondenceStreetName(correspondenceAddress.getStreetName());
            finalAutoLoanDetailTmp.setCorrespondenceCity(correspondenceAddress.getCityId().intValue());
            finalAutoLoanDetailTmp.setCorrespondenceState(correspondenceAddress.getStateId());
            finalAutoLoanDetailTmp.setCorrespondenceCountry(correspondenceAddress.getCountryId());
            finalAutoLoanDetailTmp.setCorrespondenceLandmark(correspondenceAddress.getLandMark());
            finalAutoLoanDetailTmp.setCorrespondencePinCode(correspondenceAddress.getPincode().intValue());
            finalAutoLoanDetailTmp = finalAutoLoanCoAppDetailRepository.save(finalAutoLoanDetailTmp);

            if (finalAutoLoanDetailTmp != null) {
                logger.info("finalAutoLoanDetailTmp is saved successfully");
            }

            //setting Flag to DB
            /*if(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailRequest.getIsFinalInformationFilled())){
//				we are reusing this method and also same column in loanApplication master. it is actually using Corporate. 
				loanApplicationRepository.setIsFinalMcqMandatoryFilled(finalHomeLoanDetailRequest.getApplicationId(), finalUserId, finalHomeLoanDetailRequest.getIsFinalInformationFilled());
			}*/

            //Update Bowl Count Flag
            loanApplicationRepository.setFinalFilledCount(finalAutoLoanDetailRequest.getApplicationId(), finalUserId, finalAutoLoanDetailRequest.getFinalFilledCount());
            return true;
        } catch (Exception e) {
            logger.error("Error while Saving Final Auto Loan Co-App Details:-", e);
            throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }

    private Object getDefaultOrEmpty(Object o) {
        return CommonUtils.isObjectNullOrEmpty(o) ? "" : o;
    }

    private void addOneformDetails(FinalAutoLoanCoApplicantDetailRequest finalAutoLoanDetailRequest) {

        CoApplicantDetail retailApplicantDetail = coApplicantDetailRepository.findByIdAndIsActive(finalAutoLoanDetailRequest.getCoApplicantId(),true);
        try {
            Title.getById(retailApplicantDetail.getTitleId()).getValue();
//            finalAutoLoanDetailRequest.setName(Title.getById(retailApplicantDetail.getTitleId()).getValue() + " " + retailApplicantDetail.getFirstName() + " " + retailApplicantDetail.getMiddleName() + " " + retailApplicantDetail.getFatherName());
            finalAutoLoanDetailRequest.setName(Title.getById(retailApplicantDetail.getTitleId()).getValue() +" " + retailApplicantDetail.getFirstName() + " " +   (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMiddleName()) ? retailApplicantDetail.getMiddleName() : "" ) + " " + (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getLastName()) ? retailApplicantDetail.getLastName() : "" ));
            finalAutoLoanDetailRequest.setFatherFullName(retailApplicantDetail.getFatherName());

            Address permanentAddress = new Address();
            permanentAddress.setPremiseNumber(String.valueOf(getDefaultOrEmpty(retailApplicantDetail.getAddressPremiseName())));
            permanentAddress.setStreetName(String.valueOf(getDefaultOrEmpty(retailApplicantDetail.getAddressStreetName())));
            permanentAddress.setCityId(Long.valueOf(String.valueOf(getDefaultOrEmpty(retailApplicantDetail.getAddressCity()))));
            permanentAddress.setStateId(Integer.valueOf(String.valueOf(getDefaultOrEmpty(retailApplicantDetail.getAddressState()))));
            permanentAddress.setCountryId(Integer.valueOf(String.valueOf(getDefaultOrEmpty(retailApplicantDetail.getAddressCountry()))));
            permanentAddress.setPincode(Long.valueOf(String.valueOf(getDefaultOrEmpty(retailApplicantDetail.getAddressPincode()))));
            permanentAddress.setLandMark(String.valueOf(getDefaultOrEmpty(retailApplicantDetail.getAddressLandmark())));

            finalAutoLoanDetailRequest.setPermanentAddress(permanentAddress);
            finalAutoLoanDetailRequest.setEducationalQualification(EducationStatusRetailMst.getById(retailApplicantDetail.getEducationQualification()).getValue());
            finalAutoLoanDetailRequest.setEmployeeType(retailApplicantDetail.getEmploymentType());
        }catch (Exception e){
            logger.error("Error while getting co-applicant details from one form, One or more fields are null....", e.getMessage());
        }
    }

    private void addEmployementDetails(FinalAutoLoanCoApplicantDetailRequest finalAutoLoanDetailRequest) {

        try{
        OccupationNature occupationNature = OccupationNature.getById(finalAutoLoanDetailRequest.getEmployeeType());
        switch (occupationNature) {

            case SALARIED:
                List<EmpSalariedTypeRequest> empSalariedTypeRequests = new ArrayList<>();
                List<EmpSalariedType> empSalariedTypes = empSalariedTypeRepository.getListByApplicationId(finalAutoLoanDetailRequest.getCoApplicantId());
                for (EmpSalariedType empSalariedType : empSalariedTypes) {
                    EmpSalariedTypeRequest empSalariedTypeRequest = new EmpSalariedTypeRequest();
                    BeanUtils.copyProperties(empSalariedType, empSalariedTypeRequest);
                    empSalariedTypeRequests.add(empSalariedTypeRequest);
                }
                finalAutoLoanDetailRequest.setEmpSalariedTypeList(empSalariedTypeRequests);
                break;

            case AGRICULTURIST:
                List<EmpAgriculturistTypeRequest> empAgriculturistTypes = new ArrayList<>();
                List<EmpAgriculturistType> agriculturistTypes = empAgriculturistTypeRepository.getListByApplicationId(finalAutoLoanDetailRequest.getCoApplicantId());
                for (EmpAgriculturistType empAgriculturistType : agriculturistTypes) {
                    EmpAgriculturistTypeRequest empAgriculturistTypeRequest = new EmpAgriculturistTypeRequest();
                    BeanUtils.copyProperties(empAgriculturistType, empAgriculturistTypeRequest);
                    empAgriculturistTypes.add(empAgriculturistTypeRequest);
                }
                finalAutoLoanDetailRequest.setEmpAgriculturistTypeList(empAgriculturistTypes);
                break;
            case BUSINESS:
            case SELF_EMPLOYED:
                List<EmpSelfEmployedTypeRequest> empSelfEmployedTypeRequests = new ArrayList<>();
                List<EmpSelfEmployedType> empSelfEmployedTypes = empSelfEmployedTypeRepository.getListByApplicationId(finalAutoLoanDetailRequest.getCoApplicantId());
                for (EmpSelfEmployedType empSelfEmployedType : empSelfEmployedTypes) {
                    EmpSelfEmployedTypeRequest empSelfEmployedTypeRequest = new EmpSelfEmployedTypeRequest();
                    BeanUtils.copyProperties(empSelfEmployedType, empSelfEmployedTypeRequest);
                    empSelfEmployedTypeRequests.add(empSelfEmployedTypeRequest);
                }
                finalAutoLoanDetailRequest.setEmpSelfEmployedTypeList(empSelfEmployedTypeRequests);
                break;
        }}catch (Exception e){
            logger.info("Error while getting co-applicant employee details one or more fields are null...", e.getMessage());
        }

    }

    private void addBankAccDetails(FinalAutoLoanCoApplicantDetailRequest finalHomeLoanDetailRequest) {

        List<BankAccountHeldDetailsRequest> bankAccountHeldDetailsRequests = new ArrayList<>();
        List<BankAccountHeldDetail> bankAccountHeldDetails = bankAccountHeldDetailRepository.listBankAccountHeldFromCoAppId(finalHomeLoanDetailRequest.getCoApplicantId());
        for (BankAccountHeldDetail bankAccountHeldDetail : bankAccountHeldDetails) {
            BankAccountHeldDetailsRequest accountHeldDetailsRequests = new BankAccountHeldDetailsRequest();
            BeanUtils.copyProperties(bankAccountHeldDetail, accountHeldDetailsRequests);
            bankAccountHeldDetailsRequests.add(accountHeldDetailsRequests);
        }
        finalHomeLoanDetailRequest.setBankAccountHeldDetailsList(bankAccountHeldDetailsRequests);
    }

    private void addOtherIncomeDetails(FinalAutoLoanCoApplicantDetailRequest finalHomeLoanDetailRequest) {

        List<OtherIncomeDetailRequest> otherIncomeDetailRequests = new ArrayList<>();
        List<OtherIncomeDetail> otherIncomeDetails = otherIncomeDetailRepository.listOtherIncomeFromCoAppId(finalHomeLoanDetailRequest.getCoApplicantId());

        for (OtherIncomeDetail otherIncomeDetail : otherIncomeDetails) {
            OtherIncomeDetailRequest otherIncomeDetailRequest = new OtherIncomeDetailRequest();
            BeanUtils.copyProperties(otherIncomeDetail, otherIncomeDetailRequest);
            otherIncomeDetailRequests.add(otherIncomeDetailRequest);
        }
        finalHomeLoanDetailRequest.setOtherIncomeDetailsList(otherIncomeDetailRequests);
    }

    private void addFixdepositeDetails(FinalAutoLoanCoApplicantDetailRequest finalHomeLoanDetailRequest) {
        List<FixedDepositsDetailsRequest> fixedDepositsDetailsRequests = new ArrayList<>();
        List<FixedDepositsDetail> fixedDepositsDetails = fixedDepositsDetailRepository.listFixedDepositsFromCoAppId(finalHomeLoanDetailRequest.getCoApplicantId());

        for (FixedDepositsDetail fixedDepositsDetail : fixedDepositsDetails) {
            FixedDepositsDetailsRequest fixedDepositsDetailsRequests1 = new FixedDepositsDetailsRequest();
            BeanUtils.copyProperties(fixedDepositsDetail, fixedDepositsDetailsRequests1);
            fixedDepositsDetailsRequests.add(fixedDepositsDetailsRequests1);
        }

        finalHomeLoanDetailRequest.setFixedDepositsDetailsList(fixedDepositsDetailsRequests);
    }

    private void addRefDetails(FinalAutoLoanCoApplicantDetailRequest finalHomeLoanDetailRequest) {
        List<ReferenceRetailDetailsRequest> referenceRetailDetailsRequests = new ArrayList<>();
        List<ReferencesRetailDetail> ReferencesRetailDetails = referenceRetailDetailsRepository.listReferencesRetailFromCoAppId(finalHomeLoanDetailRequest.getCoApplicantId());

        for (ReferencesRetailDetail referencesRetailDetail : ReferencesRetailDetails) {
            ReferenceRetailDetailsRequest referenceRetailDetailsRequest = new ReferenceRetailDetailsRequest();
            BeanUtils.copyProperties(referencesRetailDetail, referenceRetailDetailsRequest);
            referenceRetailDetailsRequests.add(referenceRetailDetailsRequest);
        }

        finalHomeLoanDetailRequest.setReferenceRetailDetailsList(referenceRetailDetailsRequests);
    }

    private void addCurrFinDetails(FinalAutoLoanCoApplicantDetailRequest finalHomeLoanDetailRequest) {

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
    public FinalAutoLoanCoApplicantDetailRequest get(Long coAppId, Long applicationId, Long userId, Long proposalId) throws LoansException {
        try {
            FinalAutoLoanCoApplicantDetailRequest finalAutoLoanDetailRequest = new FinalAutoLoanCoApplicantDetailRequest();
            finalAutoLoanDetailRequest.setApplicationId(applicationId);
            finalAutoLoanDetailRequest.setProposalId(proposalId);
            finalAutoLoanDetailRequest.setCoApplicantId(coAppId);
            FinalAutoLoanCoApplicantDetail finalAutoLoanDetail = finalAutoLoanCoAppDetailRepository.getByApplicationAndProposalIdAndCoAppId(applicationId, proposalId,coAppId);
            if (finalAutoLoanDetail == null) {
                finalAutoLoanDetail = new FinalAutoLoanCoApplicantDetail();
                addOneformDetails(finalAutoLoanDetailRequest);
            } else {
                if (finalAutoLoanDetail.getCorrespondencePremiseNo() != null) {
                    Address correspondenceAddress = new Address();
                    correspondenceAddress.setPremiseNumber(String.valueOf(finalAutoLoanDetail.getCorrespondencePremiseNo()));
                    correspondenceAddress.setStreetName(finalAutoLoanDetail.getCorrespondenceStreetName());
                    correspondenceAddress.setCityId(Long.valueOf(finalAutoLoanDetail.getCorrespondenceCity()));
                    correspondenceAddress.setStateId(finalAutoLoanDetail.getCorrespondenceState());
                    correspondenceAddress.setCountryId(finalAutoLoanDetail.getCorrespondenceCountry());
                    correspondenceAddress.setLandMark(finalAutoLoanDetail.getCorrespondenceLandmark());
                    correspondenceAddress.setPincode(Long.valueOf(finalAutoLoanDetail.getCorrespondencePinCode()));
                    finalAutoLoanDetailRequest.setCorrespondenceAddress(correspondenceAddress);
                }
                if(finalAutoLoanDetail.getPermanentPremiseNo() !=null){
                    Address permanentAddress = new Address();
                    permanentAddress.setPremiseNumber(String.valueOf(finalAutoLoanDetail.getPermanentPremiseNo()));
                    permanentAddress.setStreetName(finalAutoLoanDetail.getPermanentStreetName());
                    permanentAddress.setCityId(Long.valueOf(finalAutoLoanDetail.getCorrespondenceCity()));
                    permanentAddress.setStateId(finalAutoLoanDetail.getPermanentState());
                    permanentAddress.setCountryId(finalAutoLoanDetail.getPermanentCountry());
                    permanentAddress.setLandMark(finalAutoLoanDetail.getPermanentLandmark());
                    permanentAddress.setPincode(Long.valueOf(finalAutoLoanDetail.getPermanentPinCode()));
                    finalAutoLoanDetailRequest.setPermanentAddress(permanentAddress);
                }
                BeanUtils.copyProperties(finalAutoLoanDetail, finalAutoLoanDetailRequest);
            }
            addEmployementDetails(finalAutoLoanDetailRequest);
            addBankAccDetails(finalAutoLoanDetailRequest);
            addCurrFinDetails(finalAutoLoanDetailRequest);
            addRefDetails(finalAutoLoanDetailRequest);
            addFixdepositeDetails(finalAutoLoanDetailRequest);
            addOtherIncomeDetails(finalAutoLoanDetailRequest);
            
            Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, applicationId);
            finalAutoLoanDetailRequest.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
            if(!CommonUtils.isObjectNullOrEmpty(finalAutoLoanDetail) && !CommonUtils.isObjectNullOrEmpty(finalAutoLoanDetail.getApplicationId())) {
                finalAutoLoanDetailRequest.setFinalFilledCount(finalAutoLoanDetail.getApplicationId().getFinalFilledCount());
            }
            return finalAutoLoanDetailRequest;
        } catch (Exception e) {
            logger.error("Error while getting Final Auto Loan Co app Details:-", e);
            throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }
}
