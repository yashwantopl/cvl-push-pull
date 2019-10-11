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

import com.capitaworld.api.workflow.model.WorkflowRequest;
import com.capitaworld.api.workflow.model.WorkflowResponse;
import com.capitaworld.api.workflow.utility.WorkflowUtils;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.BankAccountHeldDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.EmpAgriculturistType;
import com.capitaworld.service.loans.domain.fundseeker.retail.EmpSalariedType;
import com.capitaworld.service.loans.domain.fundseeker.retail.EmpSelfEmployedType;
import com.capitaworld.service.loans.domain.fundseeker.retail.FinalAutoLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.FixedDepositsDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.OtherCurrentAssetDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.OtherIncomeDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PreSanctionVisit;
import com.capitaworld.service.loans.domain.fundseeker.retail.ReferencesRetailDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.retail.BankAccountHeldDetailsRequest;
import com.capitaworld.service.loans.model.retail.EmpAgriculturistTypeRequest;
import com.capitaworld.service.loans.model.retail.EmpSalariedTypeRequest;
import com.capitaworld.service.loans.model.retail.EmpSelfEmployedTypeRequest;
import com.capitaworld.service.loans.model.retail.FinalAutoLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.FinalHomeLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.FixedDepositsDetailsRequest;
import com.capitaworld.service.loans.model.retail.OtherCurrentAssetDetailRequest;
import com.capitaworld.service.loans.model.retail.OtherIncomeDetailRequest;
import com.capitaworld.service.loans.model.retail.PreSanctionVisitRequest;
import com.capitaworld.service.loans.model.retail.ReferenceRetailDetailsRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.BankAccountHeldDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.EmpAgriculturistTypeRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.EmpSalariedTypeRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.EmpSelfEmployedTypeRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.FinalAutoLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.FixedDepositsDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.OtherCurrentAssetDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.OtherIncomeDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PreSanctionVisitRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.ReferenceRetailDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.retail.FinalAutoLoanService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.enums.EducationStatusRetailMst;
import com.capitaworld.service.oneform.enums.OccupationNature;
import com.capitaworld.service.oneform.enums.Title;

@Service
@Transactional
public class FinalAutoLoanServiceImpl implements FinalAutoLoanService {

	private static final Logger logger = LoggerFactory.getLogger(FinalAutoLoanServiceImpl.class);

	@Autowired
	private FinalAutoLoanDetailRepository finalAutoLoanDetailRepository;
	
	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private LoanApplicationService loanApplicationService;
	
	@Autowired
	private ApplicationProposalMappingRepository appProposalMappingRepo;
	
	@Autowired
	private WorkflowClient workflowClient;
	
	@Autowired
	private EmpSalariedTypeRepository empSalariedTypeRepository;
	
	@Autowired
	private EmpAgriculturistTypeRepository empAgriculturistTypeRepository;
	
	@Autowired
	private EmpSelfEmployedTypeRepository empSelfEmployedTypeRepository;
	
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
	private PreSanctionVisitRepository preSanctionVisitRepository;


	@Override
	public boolean saveOrUpdate(FinalAutoLoanDetailRequest finalAutoLoanDetailRequest, Long userId) throws LoansException {
		try {
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(finalAutoLoanDetailRequest.getClientId()) ? userId : finalAutoLoanDetailRequest.getClientId());
			FinalAutoLoanDetail finalAutoLoanDetailTmp = finalAutoLoanDetailRepository.getByApplicationAndProposalId(finalAutoLoanDetailRequest.getApplicationId(), finalAutoLoanDetailRequest.getProposalId());
			if (finalAutoLoanDetailTmp == null) {
				finalAutoLoanDetailTmp = new FinalAutoLoanDetail();
				finalAutoLoanDetailTmp.setCreatedBy(userId);
				finalAutoLoanDetailTmp.setCreatedDate(new Date());
				finalAutoLoanDetailTmp.setApplicationId(new LoanApplicationMaster(finalAutoLoanDetailRequest.getApplicationId()));
				finalAutoLoanDetailTmp.setProposalId(new ApplicationProposalMapping(finalAutoLoanDetailRequest.getProposalId()));
			} else {
				finalAutoLoanDetailTmp.setModifiedBy(userId);
				finalAutoLoanDetailTmp.setModifiedDate(new Date());
			}
			String[] corporate = new String[CommonUtils.IgnorableCopy.getCORPORATE().length + 1];
			corporate[CommonUtils.IgnorableCopy.getCORPORATE().length] = CommonUtils.IgnorableCopy.ID;
			corporate[CommonUtils.IgnorableCopy.getCORPORATE().length -1] = "is_active";
			BeanUtils.copyProperties(finalAutoLoanDetailRequest, finalAutoLoanDetailTmp,corporate);
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
            
            //Save presanction visit
            if (!CommonUtils.isObjectNullOrEmpty(finalAutoLoanDetailRequest.getPreSanctionVisit())) {
            	
            	PreSanctionVisitRequest preVisitRequest = finalAutoLoanDetailRequest.getPreSanctionVisit(); 
            	PreSanctionVisit preVisit = preSanctionVisitRepository.getByApplicationIdAndApplicationProposalMapping(finalAutoLoanDetailRequest.getApplicationId(), finalAutoLoanDetailRequest.getProposalId());
            	
            	if (CommonUtils.isObjectNullOrEmpty(preVisit)) {
            		preVisit = new PreSanctionVisit(); 
            		preVisit.setCreatedBy(finalUserId);
                	preVisit.setCreatedDate(new Date());
                	preVisit.setApplicationId(new LoanApplicationMaster(finalAutoLoanDetailRequest.getApplicationId()));
                	preVisit.setApplicationProposalMapping(new ApplicationProposalMapping(finalAutoLoanDetailRequest.getProposalId()));	
				}else {
					preVisit.setModifiedBy(userId);
					preVisit.setModifiedDate(new Date());
				}
            	
            	System.out.println("preVisitRequest---::" + preVisitRequest.getCountryId());
            	
            	BeanUtils.copyProperties(preVisitRequest, preVisit);
            	System.out.println("preVisit---::" + preVisit.getCountryId());
            	
            	preSanctionVisitRepository.save(preVisit);
			}
            
            
            //Creating Workflow Job
			if(finalAutoLoanDetailTmp.getJobId() == null){
				WorkflowResponse workflowResponse = null;
				try{
					WorkflowRequest workflowRequest = new WorkflowRequest();
					workflowRequest.setApplicationId(finalAutoLoanDetailRequest.getApplicationId());
					workflowRequest.setUserId(userId);
					workflowRequest.setActionId(WorkflowUtils.Action.ASSIGN_TO_MAKER_ON_SAVE);
					workflowRequest.setWorkflowId(WorkflowUtils.Workflow.PL_PROCESS);
					workflowResponse = workflowClient.createJob(workflowRequest);
					Long jobId = null;
					if (!CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
						jobId = Long.valueOf(workflowResponse.getData().toString());
					}
					finalAutoLoanDetailTmp.setJobId(jobId);
				}catch (Exception e){
					logger.error("Error while Creating Workflow Process for AL = >{}",e);
					return false;
				}
			}
			finalAutoLoanDetailTmp = finalAutoLoanDetailRepository.save(finalAutoLoanDetailTmp);

			if (finalAutoLoanDetailTmp != null){
				logger.info("finalAutoLoanDetailTmp is saved successfully");
			}

			//setting Flag to DB
			/*if(!CommonUtils.isObjectNullOrEmpty(finalHomeLoanDetailRequest.getIsFinalInformationFilled())){
//				we are reusing this method and also same column in loanApplication master. it is actually using Corporate. 
				loanApplicationRepository.setIsFinalMcqMandatoryFilled(finalHomeLoanDetailRequest.getApplicationId(), finalUserId, finalHomeLoanDetailRequest.getIsFinalInformationFilled());
			}*/

			//Update Bowl Count Flag
			loanApplicationRepository.setFinalFilledCount(finalAutoLoanDetailRequest.getApplicationId(), finalUserId, finalAutoLoanDetailRequest.getFinalFilledCount());
			/* Update Applicant Finally Filled */
			appProposalMappingRepo.setIsApplicantFinalFilled(finalAutoLoanDetailRequest.getProposalId(), userId);
			
			return true;
		} catch (Exception e) {
			logger.error("Error while Saving Final Auto Loan Details:-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public FinalAutoLoanDetailRequest get(Long applicationId, Long userId, Long proposalId) throws LoansException {
		try {
			FinalAutoLoanDetail finalAutoLoanDetail = finalAutoLoanDetailRepository.getByApplicationAndProposalId(applicationId, proposalId);
			FinalAutoLoanDetailRequest finalAutoLoanDetailRequest = new FinalAutoLoanDetailRequest();
			finalAutoLoanDetailRequest.setApplicationId(applicationId);
			finalAutoLoanDetailRequest.setProposalId(proposalId);
            if (finalAutoLoanDetail == null) {
                finalAutoLoanDetail = new FinalAutoLoanDetail();
				addOneformDetails(finalAutoLoanDetailRequest);
            } else {
				if (finalAutoLoanDetail.getPermanentPremiseNo() != null) {
					Address permanentAddress = new Address();
					permanentAddress.setPremiseNumber(String.valueOf(finalAutoLoanDetail.getPermanentPremiseNo()));
					permanentAddress.setStreetName(finalAutoLoanDetail.getPermanentStreetName());
					permanentAddress.setCityId(Long.valueOf(finalAutoLoanDetail.getPermanentCity()));
					permanentAddress.setStateId(finalAutoLoanDetail.getPermanentState());
					permanentAddress.setCountryId(finalAutoLoanDetail.getPermanentCountry());
					permanentAddress.setPincode(Long.valueOf(finalAutoLoanDetail.getPermanentPinCode()));
					permanentAddress.setLandMark(finalAutoLoanDetail.getPermanentLandmark());
					finalAutoLoanDetailRequest.setPermanentAddress(permanentAddress);
				}
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
				finalAutoLoanDetailRequest.setEmployeeType(finalAutoLoanDetail.getEmployeeType());
				BeanUtils.copyProperties(finalAutoLoanDetail, finalAutoLoanDetailRequest);
			}
            
            // Get pre sanction details
            PreSanctionVisit preSanctionVisit = preSanctionVisitRepository.getByApplicationIdAndApplicationProposalMapping(applicationId , proposalId);
            if (!CommonUtils.isObjectNullOrEmpty(preSanctionVisit)) {				
            	PreSanctionVisitRequest preSanctionVisitRequest = new PreSanctionVisitRequest(); 
            	BeanUtils.copyProperties(preSanctionVisit, preSanctionVisitRequest);
            	finalAutoLoanDetailRequest.setPreSanctionVisit(preSanctionVisitRequest);
            }

            if (!CommonUtils.isObjectNullOrEmpty(finalAutoLoanDetailRequest.getEmployeeType())) {				
            	addEmployementDetails(finalAutoLoanDetailRequest);
			}
            addBankAccDetails(finalAutoLoanDetailRequest);
            addCurrFinDetails(finalAutoLoanDetailRequest);
            addRefDetails(finalAutoLoanDetailRequest);
            addFixdepositeDetails(finalAutoLoanDetailRequest);
            addOtherIncomeDetails(finalAutoLoanDetailRequest);
			
			Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, applicationId);
			finalAutoLoanDetailRequest.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
			return finalAutoLoanDetailRequest;
		} catch (Exception e) {
			logger.error("Error while getting Final Auto Loan Details:-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
	
	
	/**
	 * Add one form details
	 * @param finalAutoLoanDetailRequest
	 */
	private void addOneformDetails(FinalAutoLoanDetailRequest finalAutoLoanDetailRequest){

		RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findByProposalId(finalAutoLoanDetailRequest.getApplicationId(),finalAutoLoanDetailRequest.getProposalId());
		Title.getById(retailApplicantDetail.getTitleId()).getValue();
		finalAutoLoanDetailRequest.setName(Title.getById(retailApplicantDetail.getTitleId()).getValue() +" " + retailApplicantDetail.getFirstName() + " " +   (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMiddleName()) ? retailApplicantDetail.getMiddleName() : "" ) + " " + (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getLastName()) ? retailApplicantDetail.getLastName() : "" ));
		finalAutoLoanDetailRequest.setFatherFullName(retailApplicantDetail.getFatherName());

		Address permanentAddress = new Address();
		permanentAddress.setPremiseNumber(retailApplicantDetail.getAddressPremiseName());
		permanentAddress.setStreetName(retailApplicantDetail.getAddressStreetName());
		permanentAddress.setCityId(retailApplicantDetail.getAddressCity());
		permanentAddress.setStateId(Integer.valueOf(String.valueOf(retailApplicantDetail.getAddressState())));
		permanentAddress.setCountryId(retailApplicantDetail.getAddressCountry());
		permanentAddress.setPincode(retailApplicantDetail.getAddressPincode());
		permanentAddress.setLandMark(retailApplicantDetail.getAddressLandmark());

		finalAutoLoanDetailRequest.setPermanentAddress(permanentAddress);
		finalAutoLoanDetailRequest.setEducationalQualification(EducationStatusRetailMst.getById(retailApplicantDetail.getEducationQualification()).getValue());
		finalAutoLoanDetailRequest.setEmployeeType(retailApplicantDetail.getEmploymentType());
		finalAutoLoanDetailRequest.setStatusId(retailApplicantDetail.getStatusId());
	}
	
	
	/**
	 * Add Employement Details 
	 * @param finalAutoLoanDetailRequest
	 */
	 private void addEmployementDetails(FinalAutoLoanDetailRequest finalAutoLoanDetailRequest) {
		 	
		 OccupationNature occupationNature = OccupationNature.getById(finalAutoLoanDetailRequest.getEmployeeType());
	        switch (occupationNature) {
	            case SALARIED:
	                List<EmpSalariedTypeRequest> empSalariedTypeRequests = new ArrayList<>();
	                List<EmpSalariedType> empSalariedTypes = empSalariedTypeRepository.getListByApplicationId(finalAutoLoanDetailRequest.getApplicationId());
	                for (EmpSalariedType empSalariedType : empSalariedTypes) {
	                    EmpSalariedTypeRequest empSalariedTypeRequest = new EmpSalariedTypeRequest();
	                    BeanUtils.copyProperties(empSalariedType, empSalariedTypeRequest);
	                    empSalariedTypeRequests.add(empSalariedTypeRequest);
	                }
	                finalAutoLoanDetailRequest.setEmpSalariedTypeList(empSalariedTypeRequests);
	                break;

	            case AGRICULTURIST:
	                List<EmpAgriculturistTypeRequest> empAgriculturistTypes = new ArrayList<>();
	                List<EmpAgriculturistType> agriculturistTypes = empAgriculturistTypeRepository.getListByApplicationId(finalAutoLoanDetailRequest.getApplicationId());
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
	                List<EmpSelfEmployedType> empSelfEmployedTypes = empSelfEmployedTypeRepository.getListByApplicationId(finalAutoLoanDetailRequest.getApplicationId());
	                for (EmpSelfEmployedType empSelfEmployedType : empSelfEmployedTypes) {
	                    EmpSelfEmployedTypeRequest empSelfEmployedTypeRequest = new EmpSelfEmployedTypeRequest();
	                    BeanUtils.copyProperties(empSelfEmployedType, empSelfEmployedTypeRequest);
	                    empSelfEmployedTypeRequests.add(empSelfEmployedTypeRequest);
	                }
	                finalAutoLoanDetailRequest.setEmpSelfEmployedTypeList(empSelfEmployedTypeRequests);
	                break;
	        }
	    }
	 
	 /**
	  * Add BankAcc Details 
	  * @param finalAutoLoanDetailRequest
	  */
	 private void addBankAccDetails(FinalAutoLoanDetailRequest finalAutoLoanDetailRequest){

	        List<BankAccountHeldDetailsRequest> bankAccountHeldDetailsRequests = new ArrayList<>();
	        List<BankAccountHeldDetail> bankAccountHeldDetails = bankAccountHeldDetailRepository.listBankAccountHeldFromAppId(finalAutoLoanDetailRequest.getApplicationId());
	        for(BankAccountHeldDetail bankAccountHeldDetail : bankAccountHeldDetails){
	            BankAccountHeldDetailsRequest accountHeldDetailsRequests = new BankAccountHeldDetailsRequest();
	            BeanUtils.copyProperties(bankAccountHeldDetail,accountHeldDetailsRequests);
	            bankAccountHeldDetailsRequests.add(accountHeldDetailsRequests);
	        }
	        finalAutoLoanDetailRequest.setBankAccountHeldDetailsList(bankAccountHeldDetailsRequests);
	    }

	 /**
	  * Add Other Income Details
	  * @param finalAutoLoanDetailRequest
	  */
	    private void addOtherIncomeDetails(FinalAutoLoanDetailRequest finalAutoLoanDetailRequest){

	        List<OtherIncomeDetailRequest> otherIncomeDetailRequests = new ArrayList<>();
	        List<OtherIncomeDetail> otherIncomeDetails = otherIncomeDetailRepository.listOtherIncomeFromAppId(finalAutoLoanDetailRequest.getApplicationId(), finalAutoLoanDetailRequest.getProposalId());

	        for(OtherIncomeDetail otherIncomeDetail : otherIncomeDetails){
	            OtherIncomeDetailRequest otherIncomeDetailRequest = new OtherIncomeDetailRequest();
	            BeanUtils.copyProperties(otherIncomeDetail,otherIncomeDetailRequest);
	            otherIncomeDetailRequests.add(otherIncomeDetailRequest);
	        }
	        finalAutoLoanDetailRequest.setOtherIncomeDetailsList(otherIncomeDetailRequests);
	    }

	    /**
	     * Add Fixdeposite Details
	     * @param finalAutoLoanDetailRequest
	     */
	    private void addFixdepositeDetails(FinalAutoLoanDetailRequest finalAutoLoanDetailRequest){
	        List<FixedDepositsDetailsRequest> fixedDepositsDetailsRequests = new ArrayList<>();
	        List<FixedDepositsDetail> fixedDepositsDetails = fixedDepositsDetailRepository.listFixedDepositsFromProposalId(finalAutoLoanDetailRequest.getProposalId());

	        for(FixedDepositsDetail fixedDepositsDetail : fixedDepositsDetails){
	            FixedDepositsDetailsRequest fixedDepositsDetailsRequests1 = new FixedDepositsDetailsRequest();
	            BeanUtils.copyProperties(fixedDepositsDetail,fixedDepositsDetailsRequests1);
	            fixedDepositsDetailsRequests.add(fixedDepositsDetailsRequests1);
	        }
	        finalAutoLoanDetailRequest.setFixedDepositsDetailsList(fixedDepositsDetailsRequests);
	    }

	    /**
	     * Add Ref Details
	     * @param finalAutoLoanDetailRequest
	     */
	    private void addRefDetails(FinalAutoLoanDetailRequest finalAutoLoanDetailRequest){
	        List<ReferenceRetailDetailsRequest> referenceRetailDetailsRequests = new ArrayList<>();
	        List<ReferencesRetailDetail> ReferencesRetailDetails  = referenceRetailDetailsRepository.listReferencesRetailFromPropsalId(finalAutoLoanDetailRequest.getProposalId());

	        for(ReferencesRetailDetail referencesRetailDetail : ReferencesRetailDetails){
	            ReferenceRetailDetailsRequest referenceRetailDetailsRequest = new ReferenceRetailDetailsRequest();
	            BeanUtils.copyProperties(referencesRetailDetail,referenceRetailDetailsRequest);
	            referenceRetailDetailsRequests.add(referenceRetailDetailsRequest);
	        }

	        finalAutoLoanDetailRequest.setReferenceRetailDetailsList(referenceRetailDetailsRequests);
	    }

	    /**
	     * 
	     * @param finalAutoLoanDetailRequest
	     */
	    private void addCurrFinDetails(FinalAutoLoanDetailRequest finalAutoLoanDetailRequest){

	        List<OtherCurrentAssetDetailRequest> otherCurrentAssetDetailRequests = new ArrayList<>();
	        List<OtherCurrentAssetDetail> otherCurrentAssetDetails = otherCurrentAssetDetailRepository.listOtherCurrentAssetFromProposalId(finalAutoLoanDetailRequest.getProposalId());

	        for(OtherCurrentAssetDetail  currentAssetDetail : otherCurrentAssetDetails){
	            OtherCurrentAssetDetailRequest  otherCurrentAssetDetailRequest = new OtherCurrentAssetDetailRequest();
	            BeanUtils.copyProperties(currentAssetDetail,otherCurrentAssetDetailRequest);
	            otherCurrentAssetDetailRequests.add(otherCurrentAssetDetailRequest);
	        }
	        finalAutoLoanDetailRequest.setAssetDetailList(otherCurrentAssetDetailRequests);
	    }
	

}
