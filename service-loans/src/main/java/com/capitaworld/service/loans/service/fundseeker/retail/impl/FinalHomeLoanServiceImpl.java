package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.retail.*;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.retail.*;
import com.capitaworld.service.loans.repository.fundseeker.retail.*;
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

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.retail.FinalHomeLoanService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class FinalHomeLoanServiceImpl implements FinalHomeLoanService {

	private static final Logger logger = LoggerFactory.getLogger(FinalHomeLoanServiceImpl.class);

	@Autowired
	private FinalHomeLoanDetailOldRepository finalHomeLoanDetailOldRepository;

	@Autowired
	private FinalHomeLoanDetailRepository finalHomeLoanDetailRepository;

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




	@Override
	public boolean saveOrUpdate(FinalHomeLoanDetailRequest finalHomeLoanDetailRequest, Long userId) throws LoansException {
		try {
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailRequest.getClientId()) ? userId : finalHomeLoanDetailRequest.getClientId());
			FinalHomeLoanDetail finalHomeLoanDetailTmp = finalHomeLoanDetailRepository
					.getByApplicationAndProposalId(finalHomeLoanDetailRequest.getApplicationId(), finalHomeLoanDetailRequest.getProposalId());
			if (finalHomeLoanDetailTmp == null) {
				finalHomeLoanDetailTmp = new FinalHomeLoanDetail();
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
			corporate[CommonUtils.IgnorableCopy.getCORPORATE().length] = CommonUtils.IgnorableCopy.ID;
			BeanUtils.copyProperties(finalHomeLoanDetailRequest, finalHomeLoanDetailTmp,corporate);
			Address permanentAddress = finalHomeLoanDetailRequest.getPermanentAddress();
			Address correspondenceAddress = finalHomeLoanDetailRequest.getCorrespondenceAddress();

            finalHomeLoanDetailTmp.setPermanentPremiseNo(Integer.parseInt(permanentAddress.getPremiseNumber()));
            finalHomeLoanDetailTmp.setPermanentStreetName(permanentAddress.getStreetName());
            finalHomeLoanDetailTmp.setPermanentCity(permanentAddress.getCityId().intValue());
            finalHomeLoanDetailTmp.setPermanentState(permanentAddress.getStateId());
            finalHomeLoanDetailTmp.setPermanentCountry(permanentAddress.getCountryId());
            finalHomeLoanDetailTmp.setPermanentLandmark(permanentAddress.getLandMark());
            finalHomeLoanDetailTmp.setPermanentPinCode(permanentAddress.getPincode().intValue());


            finalHomeLoanDetailTmp.setCorrespondencePremiseNo(Integer.parseInt(correspondenceAddress.getPremiseNumber()));
            finalHomeLoanDetailTmp.setCorrespondenceStreetName(correspondenceAddress.getStreetName());
            finalHomeLoanDetailTmp.setCorrespondenceCity(correspondenceAddress.getCityId().intValue());
            finalHomeLoanDetailTmp.setCorrespondenceState(correspondenceAddress.getStateId());
            finalHomeLoanDetailTmp.setPermanentCountry(correspondenceAddress.getCountryId());
            finalHomeLoanDetailTmp.setCorrespondenceLandmark(correspondenceAddress.getLandMark());
            finalHomeLoanDetailTmp.setCorrespondencePinCode(correspondenceAddress.getPincode().intValue());
			finalHomeLoanDetailTmp = finalHomeLoanDetailRepository.save(finalHomeLoanDetailTmp);

			if (finalHomeLoanDetailTmp != null){
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
			logger.error("Error while Saving Final Home Loan Details:-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	private void addOneformDetails(FinalHomeLoanDetailRequest finalHomeLoanDetailRequest){

		RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findByProposalId(finalHomeLoanDetailRequest.getApplicationId(),finalHomeLoanDetailRequest.getProposalId());
		Title.getById(retailApplicantDetail.getTitleId()).getValue();
		finalHomeLoanDetailRequest.setName(Title.getById(retailApplicantDetail.getTitleId()).getValue() +" " + retailApplicantDetail.getFirstName() + " " + retailApplicantDetail.getMiddleName() + " " + retailApplicantDetail.getFatherName());
		finalHomeLoanDetailRequest.setFatherFullName(retailApplicantDetail.getFatherName());

		Address permanentAddress = new Address();
		permanentAddress.setPremiseNumber(retailApplicantDetail.getPermanentPremiseNumberName());
		permanentAddress.setStreetName(retailApplicantDetail.getPermanentStreetName());
		permanentAddress.setCityId(retailApplicantDetail.getPermanentCityId());
		permanentAddress.setStateId(retailApplicantDetail.getPermanentStateId());
		permanentAddress.setCountryId(retailApplicantDetail.getPermanentCountryId());
		permanentAddress.setPincode(retailApplicantDetail.getPermanentPincode());
		permanentAddress.setLandMark(retailApplicantDetail.getPermanentLandMark());

		finalHomeLoanDetailRequest.setPermanentAddress(permanentAddress);
		finalHomeLoanDetailRequest.setEducationalQualification(EducationStatusRetailMst.getById(retailApplicantDetail.getEducationQualification()).getValue());
		finalHomeLoanDetailRequest.setEmployeeType(retailApplicantDetail.getEmploymentType());
		finalHomeLoanDetailRequest.setStatusId(retailApplicantDetail.getStatusId());
	}

    private void addEmployementDetails(FinalHomeLoanDetailRequest finalHomeLoanDetailRequest) {

        OccupationNature occupationNature = OccupationNature.getById(finalHomeLoanDetailRequest.getEmployeeType());
        switch (occupationNature) {

            case SALARIED:
                List<EmpSalariedTypeRequest> empSalariedTypeRequests = new ArrayList<>();
                List<EmpSalariedType> empSalariedTypes = empSalariedTypeRepository.getListByApplicationId(finalHomeLoanDetailRequest.getApplicationId());
                for (EmpSalariedType empSalariedType : empSalariedTypes) {
                    EmpSalariedTypeRequest empSalariedTypeRequest = new EmpSalariedTypeRequest();
                    BeanUtils.copyProperties(empSalariedType, empSalariedTypeRequest);
                    empSalariedTypeRequests.add(empSalariedTypeRequest);
                }
                finalHomeLoanDetailRequest.setEmpSalariedTypeList(empSalariedTypeRequests);
                break;

            case AGRICULTURIST:
                List<EmpAgriculturistTypeRequest> empAgriculturistTypes = new ArrayList<>();
                List<EmpAgriculturistType> agriculturistTypes = empAgriculturistTypeRepository.getListByApplicationId(finalHomeLoanDetailRequest.getApplicationId());
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
                List<EmpSelfEmployedType> empSelfEmployedTypes = empSelfEmployedTypeRepository.getListByApplicationId(finalHomeLoanDetailRequest.getApplicationId());
                for (EmpSelfEmployedType empSelfEmployedType : empSelfEmployedTypes) {
                    EmpSelfEmployedTypeRequest empSelfEmployedTypeRequest = new EmpSelfEmployedTypeRequest();
                    BeanUtils.copyProperties(empSelfEmployedType, empSelfEmployedTypeRequest);
                    empSelfEmployedTypeRequests.add(empSelfEmployedTypeRequest);
                }
                finalHomeLoanDetailRequest.setEmpSelfEmployedTypeList(empSelfEmployedTypeRequests);
                break;
        }
    }

    private void addPropertyDetails(FinalHomeLoanDetailRequest finalHomeLoanDetailRequest){

        List<OtherPropertyDetailsRequest> otherPropertyDetailsRequests = new ArrayList<>();
        List<OtherPropertyDetails> otherPropertyDetails = otherPropertyDetailsRepository.getListByApplicationId(finalHomeLoanDetailRequest.getApplicationId());
        for(OtherPropertyDetails propertyDetails : otherPropertyDetails){
            OtherPropertyDetailsRequest otherPropertyDetailsRequest = new OtherPropertyDetailsRequest();
            BeanUtils.copyProperties(propertyDetails,otherPropertyDetailsRequest);
            otherPropertyDetailsRequests.add(otherPropertyDetailsRequest);
        }

        List<PurchasePropertyDetailsRequest> purchasePropertyDetailsRequests = new ArrayList<>();
        List<PurchasePropertyDetails> purchasePropertyDetails = purchasePropertyDetailsRepository.getListByApplicationId(finalHomeLoanDetailRequest.getApplicationId());
        for(PurchasePropertyDetails propertyDetails : purchasePropertyDetails){
            PurchasePropertyDetailsRequest empSalariedTypeRequest = new PurchasePropertyDetailsRequest();
            BeanUtils.copyProperties(propertyDetails,empSalariedTypeRequest);
            purchasePropertyDetailsRequests.add(empSalariedTypeRequest);
        }

        finalHomeLoanDetailRequest.setOtherPropertyDetailsList(otherPropertyDetailsRequests);
        finalHomeLoanDetailRequest.setPurchasePropertyDetailsList(purchasePropertyDetailsRequests);
    }

    private void addBankAccDetails(FinalHomeLoanDetailRequest finalHomeLoanDetailRequest){

        List<BankAccountHeldDetailsRequest> bankAccountHeldDetailsRequests = new ArrayList<>();
        List<BankAccountHeldDetail> bankAccountHeldDetails = bankAccountHeldDetailRepository.listBankAccountHeldFromAppId(finalHomeLoanDetailRequest.getApplicationId());
        for(BankAccountHeldDetail bankAccountHeldDetail : bankAccountHeldDetails){
            BankAccountHeldDetailsRequest accountHeldDetailsRequests = new BankAccountHeldDetailsRequest();
            BeanUtils.copyProperties(bankAccountHeldDetail,accountHeldDetailsRequests);
            bankAccountHeldDetailsRequests.add(accountHeldDetailsRequests);
        }
        finalHomeLoanDetailRequest.setBankAccountHeldDetailsList(bankAccountHeldDetailsRequests);
    }

    private void addOtherIncomeDetails(FinalHomeLoanDetailRequest finalHomeLoanDetailRequest){

        List<OtherIncomeDetailRequest> otherIncomeDetailRequests = new ArrayList<>();
        List<OtherIncomeDetail> otherIncomeDetails = otherIncomeDetailRepository.listOtherIncomeFromAppId(finalHomeLoanDetailRequest.getApplicationId(), finalHomeLoanDetailRequest.getProposalId());

        for(OtherIncomeDetail otherIncomeDetail : otherIncomeDetails){
            OtherIncomeDetailRequest otherIncomeDetailRequest = new OtherIncomeDetailRequest();
            BeanUtils.copyProperties(otherIncomeDetail,otherIncomeDetailRequest);
            otherIncomeDetailRequests.add(otherIncomeDetailRequest);
        }
        finalHomeLoanDetailRequest.setOtherIncomeDetailsList(otherIncomeDetailRequests);
    }

    private void addFixdepositeDetails(FinalHomeLoanDetailRequest finalHomeLoanDetailRequest){
        List<FixedDepositsDetailsRequest> fixedDepositsDetailsRequests = new ArrayList<>();
        List<FixedDepositsDetail> fixedDepositsDetails = fixedDepositsDetailRepository.listFixedDepositsFromProposalId(finalHomeLoanDetailRequest.getProposalId());

        for(FixedDepositsDetail fixedDepositsDetail : fixedDepositsDetails){
            FixedDepositsDetailsRequest fixedDepositsDetailsRequests1 = new FixedDepositsDetailsRequest();
            BeanUtils.copyProperties(fixedDepositsDetail,fixedDepositsDetailsRequests1);
            fixedDepositsDetailsRequests.add(fixedDepositsDetailsRequests1);
        }

        finalHomeLoanDetailRequest.setFixedDepositsDetailsList(fixedDepositsDetailsRequests);
    }

    private void addRefDetails(FinalHomeLoanDetailRequest finalHomeLoanDetailRequest){
        List<ReferenceRetailDetailsRequest> referenceRetailDetailsRequests = new ArrayList<>();
        List<ReferencesRetailDetail> ReferencesRetailDetails  = referenceRetailDetailsRepository.listReferencesRetailFromPropsalId(finalHomeLoanDetailRequest.getProposalId());

        for(ReferencesRetailDetail referencesRetailDetail : ReferencesRetailDetails){
            ReferenceRetailDetailsRequest referenceRetailDetailsRequest = new ReferenceRetailDetailsRequest();
            BeanUtils.copyProperties(referencesRetailDetail,referenceRetailDetailsRequest);
            referenceRetailDetailsRequests.add(referenceRetailDetailsRequest);
        }

        finalHomeLoanDetailRequest.setReferenceRetailDetailsList(referenceRetailDetailsRequests);
    }

    private void addCurrFinDetails(FinalHomeLoanDetailRequest finalHomeLoanDetailRequest){

        List<OtherCurrentAssetDetailRequest> otherCurrentAssetDetailRequests = new ArrayList<>();
        List<OtherCurrentAssetDetail> otherCurrentAssetDetails = otherCurrentAssetDetailRepository.listOtherCurrentAssetFromProposalId(finalHomeLoanDetailRequest.getProposalId());

        for(OtherCurrentAssetDetail  currentAssetDetail : otherCurrentAssetDetails){
            OtherCurrentAssetDetailRequest  otherCurrentAssetDetailRequest = new OtherCurrentAssetDetailRequest();
            BeanUtils.copyProperties(currentAssetDetail,otherCurrentAssetDetailRequest);
            otherCurrentAssetDetailRequests.add(otherCurrentAssetDetailRequest);
        }

        finalHomeLoanDetailRequest.setAssetDetailList(otherCurrentAssetDetailRequests);
    }

	@Override
	public FinalHomeLoanDetailRequest get(Long applicationId, Long userId, Long proposalId) throws LoansException {
		try {
			FinalHomeLoanDetail finalHomeLoanDetail = finalHomeLoanDetailRepository.getByApplicationAndProposalId(applicationId,
					proposalId);
			FinalHomeLoanDetailRequest finalHomeLoanDetailRequest = new FinalHomeLoanDetailRequest();
			finalHomeLoanDetailRequest.setApplicationId(applicationId);
			finalHomeLoanDetailRequest.setProposalId(proposalId);
            if (finalHomeLoanDetail == null) {
                finalHomeLoanDetail = new FinalHomeLoanDetail();
				addOneformDetails(finalHomeLoanDetailRequest);
            }else{
				if (finalHomeLoanDetail.getPermanentPremiseNo() != null) {
					Address permanentAddress = new Address();
					permanentAddress.setPremiseNumber(String.valueOf(finalHomeLoanDetail.getPermanentPremiseNo()));
					permanentAddress.setStreetName(finalHomeLoanDetail.getPermanentStreetName());
					permanentAddress.setCityId(Long.valueOf(finalHomeLoanDetail.getPermanentCity()));
					permanentAddress.setStateId(finalHomeLoanDetail.getPermanentState());
					permanentAddress.setCountryId(finalHomeLoanDetail.getPermanentCountry());
					permanentAddress.setPincode(Long.valueOf(finalHomeLoanDetail.getPermanentPinCode()));
					permanentAddress.setLandMark(finalHomeLoanDetail.getPermanentLandmark());
					finalHomeLoanDetailRequest.setPermanentAddress(permanentAddress);
				}

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
				finalHomeLoanDetailRequest.setEmployeeType(finalHomeLoanDetail.getEmployeeType());
			}
			//finalHomeLoanDetailRequest.setEmployeeType(finalHomeLoanDetailRequest.getEmployeeType());
			addEmployementDetails(finalHomeLoanDetailRequest);
            addPropertyDetails(finalHomeLoanDetailRequest);
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
				if(!CommonUtils.isObjectNullOrEmpty(bowlCount.get("finalFilledCount"))){
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
			logger.error("Error while getting Final Home Loan Details:-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public boolean saveOrUpdateNew(FinalHomeLoanDetailRequest finalHomeLoanDetailRequest, Long userId) throws LoansException {
		try {
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailRequest.getClientId()) ? userId : finalHomeLoanDetailRequest.getClientId());
			FinalHomeLoanDetail finalHomeLoanDetail = finalHomeLoanDetailRepository
					.getByApplicationAndProposalId(finalHomeLoanDetailRequest.getApplicationId(), finalUserId);
			OtherPropertyDetails otherPropertyDetails = null;
			if (finalHomeLoanDetail == null) {
				finalHomeLoanDetail = new FinalHomeLoanDetail();
				otherPropertyDetails = new OtherPropertyDetails();
				finalHomeLoanDetail.setCreatedBy(userId);
				finalHomeLoanDetail.setCreatedDate(new Date());
				finalHomeLoanDetail.setIsActive(true);
				finalHomeLoanDetail
						.setApplicationId(new LoanApplicationMaster(finalHomeLoanDetailRequest.getApplicationId()));
			} else {
				finalHomeLoanDetail.setModifiedBy(userId);
				finalHomeLoanDetail.setModifiedDate(new Date());
			}
			String[] corporate = new String[CommonUtils.IgnorableCopy.getCORPORATE().length + 1];
			corporate[CommonUtils.IgnorableCopy.getCORPORATE().length] = CommonUtils.IgnorableCopy.ID;
			BeanUtils.copyProperties(finalHomeLoanDetailRequest, finalHomeLoanDetail,corporate);
			finalHomeLoanDetail = finalHomeLoanDetailRepository.save(finalHomeLoanDetail);

			if (finalHomeLoanDetail != null){
				logger.info("finalHomeLoanDetail is saved successfully");
			}

		/*	//setting Flag to DB
			if(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailRequest.getIsFinalInformationFilled())){
//				we are reusing this method and also same column in loanApplication master. it is actually using Corporate.
				loanApplicationRepository.setIsFinalMcqMandatoryFilled(finalHomeLoanDetailRequest.getApplicationId(), finalUserId, finalHomeLoanDetailRequest.getIsFinalInformationFilled());
			}

			//Update Bowl Count Flag
			loanApplicationRepository.setFinalFilledCount(finalHomeLoanDetailRequest.getApplicationId(), finalUserId,finalHomeLoanDetailRequest.getFinalFilledCount());*/
			return true;
		} catch (Exception e) {
			logger.error("Error while Saving Final Home Loan Details:-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}
}
