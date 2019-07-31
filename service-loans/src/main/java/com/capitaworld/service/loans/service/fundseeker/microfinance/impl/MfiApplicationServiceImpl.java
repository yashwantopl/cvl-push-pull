package com.capitaworld.service.loans.service.fundseeker.microfinance.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.capitaworld.service.loans.model.micro_finance.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.mfi.MFIApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiAssetsLiabilityDetails;
import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiBankDetails;
import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiIncomeDetails;
import com.capitaworld.service.loans.model.micro_finance.AadharDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiApplicantDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiAssetsDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiBankDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiIncomeAndExpenditureReq;
import com.capitaworld.service.loans.model.micro_finance.MfiIncomeDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiLoanAssessmentDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiReqResponse;
import com.capitaworld.service.loans.model.micro_finance.PersonalDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.ProjectDetailsReq;
import com.capitaworld.service.loans.repository.fundseeker.Mfi.MfiApplicationDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.Mfi.MfiAssetsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.Mfi.MfiBankDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.Mfi.MfiIncomeDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.microfinance.MfiApplicationService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class MfiApplicationServiceImpl implements MfiApplicationService {

	private static final Logger logger = LoggerFactory.getLogger(MfiApplicationServiceImpl.class.getName());
	private static final Integer ASSETS = 1;
	private static final Integer LIABILITY = 2;

	@Autowired
	private MfiApplicationDetailsRepository detailsRepository;

	@Autowired
	private LoanApplicationService applicationService;

	@Autowired
	private MfiBankDetailsRepository bankDetailsRepository;

	@Autowired
	private MfiIncomeDetailsRepository MfiIncomeDetailsRepository;

	@Autowired
	private MfiAssetsDetailsRepository MfiAssetsDetailsRepository;

	@Override
	public AadharDetailsReq saveOrUpdateAadharDetails(AadharDetailsReq aadharDetailsReq) {
		MFIApplicantDetail mfiApplicationDetail;
        String serverSideValidation = serverSideValidation(CommonUtils.BASIC_DETAILS, aadharDetailsReq);
        if(!CommonUtils.isObjectNullOrEmpty(serverSideValidation)){
			AadharDetailsReq detailsReq = new AadharDetailsReq();
			detailsReq.setMessage(serverSideValidation);
            return detailsReq;
        }

        if (aadharDetailsReq.getId() == null) {
			Long applicationId = applicationService.createMfiLoan(aadharDetailsReq.getUserId(), true,
					aadharDetailsReq.getBusinessTypeId(), aadharDetailsReq.getOrgId());
			if (applicationId != null) {
				mfiApplicationDetail = new MFIApplicantDetail();
				BeanUtils.copyProperties(aadharDetailsReq, mfiApplicationDetail);
				mfiApplicationDetail.setApplicationId(new LoanApplicationMaster(applicationId));
				mfiApplicationDetail.setStatus(CommonUtils.PENDING);
				mfiApplicationDetail.setIsActive(true);
				mfiApplicationDetail.setCreatedBy(aadharDetailsReq.getUserId());
				mfiApplicationDetail.setCreatedDate(new Date());
				detailsRepository.save(mfiApplicationDetail);
				aadharDetailsReq.setId(mfiApplicationDetail.getId());
				aadharDetailsReq.setApplicationId(mfiApplicationDetail.getApplicationId().getId());
			}
		} else {
			mfiApplicationDetail = detailsRepository.findOne(aadharDetailsReq.getId());
			BeanUtils.copyProperties(aadharDetailsReq, mfiApplicationDetail);
			mfiApplicationDetail.setApplicationId(new LoanApplicationMaster(aadharDetailsReq.getApplicationId()));
			mfiApplicationDetail.setStatus(CommonUtils.PENDING);
			mfiApplicationDetail.setModifiedBy(aadharDetailsReq.getUserId());
			mfiApplicationDetail.setModifiedDate(new Date());
			detailsRepository.save(mfiApplicationDetail);
		}
		AadharDetailsReq detailsReq = new AadharDetailsReq();
		detailsReq.setId(aadharDetailsReq.getId());
		detailsReq.setApplicationId(aadharDetailsReq.getApplicationId());
		detailsReq.setMessage("Successfully Saved");
		return detailsReq;
	}

	@Override
	public AadharDetailsReq getAadharDetailsByAppId(Long applicationId) {
		List<AadharDetailsReq> detailsReq = detailsRepository.findAadharDetailsByAppId(applicationId);
		return !CommonUtils.isListNullOrEmpty(detailsReq) ? detailsReq.get(0) : null;
	}

	@Override
	public Object saveOrUpdatePersonalDetails(PersonalDetailsReq personalDetailsReq) {
		String serverSideValidation = serverSideValidation(CommonUtils.PERSONAL_DETAILS, personalDetailsReq);

		if (!CommonUtils.isObjectNullOrEmpty(serverSideValidation)){
			return serverSideValidation;
		}
		MFIApplicantDetail mfiApplicationDetail;
		if (null != personalDetailsReq.getId()) {
			mfiApplicationDetail = detailsRepository.findOne(personalDetailsReq.getId());
			BeanUtils.copyProperties(personalDetailsReq, mfiApplicationDetail);
			mfiApplicationDetail.setIsPersonalDetailsFilled(true);
			detailsRepository.save(mfiApplicationDetail);
			return true;
		}
		return false;

	}

	@Override
	public PersonalDetailsReq getPersonalDetailsAppId(Long applicationId) {
		List<PersonalDetailsReq> detailsReq = detailsRepository.findPersonalDetailsByAppId(applicationId);
		return !CommonUtils.isListNullOrEmpty(detailsReq) ? detailsReq.get(0) : null;
	}

	@Override
	public Object saveOrUpdateProjectDetails(ProjectDetailsReq projectDetailsReq) {
		MFIApplicantDetail mfiApplicationDetail;
		String serverSideValidation = serverSideValidation(CommonUtils.PROJECT_DETAILS, projectDetailsReq);

		if (!CommonUtils.isObjectNullOrEmpty(serverSideValidation)){
			return serverSideValidation;
		}
		if (null != projectDetailsReq.getId()) {
			mfiApplicationDetail = detailsRepository.findOne(projectDetailsReq.getId());
			projectDetailsReq.setTotalCostEquipment(projectDetailsReq.getCostOfEquipment() + projectDetailsReq.getWorkingCapOfEquipment());
			projectDetailsReq.setTotalMeanFinance(projectDetailsReq.getPromoterContribution() + projectDetailsReq.getLoanRequiredFromSidbi());
			BeanUtils.copyProperties(projectDetailsReq, mfiApplicationDetail);
			mfiApplicationDetail.setIsProjectDetailsFilled(true);
			detailsRepository.save(mfiApplicationDetail);
			return true;
		}
		return false;
	}

	@Override
	public List<MfiReqResponse> getMfiApplicantDetails(Long applicationId) {
		logger.info("ENTER HERE MFI DETAILS===== BY APPLICATION ==={}====={}===>" + applicationId);
		List<MFIApplicantDetail> appDetailList = detailsRepository.findByApplicationIdAndIsActive(applicationId);
		List<MfiReqResponse> appResponseList = new ArrayList<>(appDetailList.size());
		MfiReqResponse appIncomeReq = null;

		for (MFIApplicantDetail appIncomeDetail : appDetailList) {
			appIncomeReq = new MfiReqResponse();
			BeanUtils.copyProperties(appIncomeDetail, appIncomeReq);
			appResponseList.add(appIncomeReq);
		}
		return appResponseList;
	}

	@Override
	public Object saveOrUpdateBankDetails(MfiBankDetailsReq bankDetailsReq) {
		String serverSideValidation = serverSideValidation(CommonUtils.BANK_DETAILS, bankDetailsReq);

		if (!CommonUtils.isObjectNullOrEmpty(serverSideValidation)){
			return serverSideValidation;
		}
		if(bankDetailsReq.getApplicationId() != null){
			MfiBankDetails mfiBankDetails = new MfiBankDetails();
			BeanUtils.copyProperties(bankDetailsReq, mfiBankDetails);
			bankDetailsRepository.save(mfiBankDetails);
			detailsRepository.updateBankFilledFlag(bankDetailsReq.getApplicationId());
			return true;
		}
		return false;
	}

	@Override
	public MfiBankDetailsReq fetchBankDetail(Long applicationId) {
		MfiBankDetailsReq mfiBankDetailsReq = new MfiBankDetailsReq();
		MfiBankDetails byApplicationId = bankDetailsRepository.findByApplicationId(applicationId);
		BeanUtils.copyProperties(byApplicationId, mfiBankDetailsReq);
		return mfiBankDetailsReq;
	}


	@Override
	public List<MfiApplicantDetailsReq> getAllApplicantDetails(Long applicationId) {
		List<MfiApplicantDetailsReq> mfiApplicantDetailsReqs = new ArrayList<>();
		List<MFIApplicantDetail> all = detailsRepository.findByApplicationIdAndIsActive(applicationId);

		for (MFIApplicantDetail applicantDetail : all) {

			MfiApplicantDetailsReq detailsReq = new MfiApplicantDetailsReq();
			BeanUtils.copyProperties(applicantDetail, detailsReq);
			//for bank details
			MfiBankDetails byApplicationId = bankDetailsRepository.findByApplicationId(applicationId);
			if(byApplicationId != null) {
				BeanUtils.copyProperties(byApplicationId, detailsReq);
			}
			//for assets and liability
			detailsReq.setAssetsDetails(MfiAssetsDetailsRepository.findAssetsDetailsByAppId(applicationId));
			detailsReq.setLiabilityDetails(MfiAssetsDetailsRepository.findLiabilityDetailsByAppId(applicationId));
			//for Income
			List<MfiIncomeDetailsReq> incomeDetails = MfiIncomeDetailsRepository.findIncomeDetailsByAppId(applicationId);
			detailsReq.setIncomeDetailsReqList(incomeDetails);

			// FOR PARENT(MfiIncomeAndExpenditureReq)
			List<MfiIncomeAndExpenditureReq> MfiIncomeAndExpend = detailsRepository.findIncomeAndExpenditureDetailsByAppId(applicationId);
			BeanUtils.copyProperties(MfiIncomeAndExpend, detailsReq);
			
			mfiApplicantDetailsReqs.add(detailsReq);
			
		}

		return mfiApplicantDetailsReqs;

	}

	@Override
	public ProjectDetailsReq getProjectDetailsAppId(Long applicationId) {
		List<ProjectDetailsReq> detailsReq = detailsRepository.findProjectDetailsByAppId(applicationId);
		return !CommonUtils.isListNullOrEmpty(detailsReq) ? detailsReq.get(0) : null;
	}

	@Override
	public Object saveOrUpdateIncomeExpenditureDetails(MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq) {
		Double totalIncome = 0.0, totalExpense = 0.0;
		String serverSideValidation = serverSideValidation(CommonUtils.INCOME_EXPENDITURE, mfiIncomeAndExpenditureReq);

		if (!CommonUtils.isObjectNullOrEmpty(serverSideValidation)){
			return serverSideValidation;
		}
		if (null != mfiIncomeAndExpenditureReq.getId()) {
			List<MfiIncomeDetailsReq> mfiIncomeDetailsReqs = mfiIncomeAndExpenditureReq.getIncomeDetailsReqList();
			if (!CommonUtils.isListNullOrEmpty(mfiIncomeDetailsReqs)) {
				for (MfiIncomeDetailsReq mfiIncomeDetailsReq : mfiIncomeDetailsReqs) {
					MfiIncomeDetails mfiIncomeDetails = new MfiIncomeDetails();
					BeanUtils.copyProperties(mfiIncomeDetailsReq, mfiIncomeDetails);
					totalIncome = totalIncome + mfiIncomeDetails.getMonthlyIncome();
					MfiIncomeDetailsRepository.save(mfiIncomeDetails);
				}
			}

			MFIApplicantDetail mfiApplicationDetail = detailsRepository.findOne(mfiIncomeAndExpenditureReq.getId());
			BeanUtils.copyProperties(mfiIncomeAndExpenditureReq, mfiApplicationDetail);
			mfiApplicationDetail.setIsIncomeDetailsFilled(true);
			mfiApplicationDetail.setTotalMonthlyIncomeForFamily(CommonUtils.isObjectNullOrEmpty(totalIncome) ? 0.0 : totalIncome);
			totalExpense = mfiApplicationDetail.getEducationExpense() + mfiApplicationDetail.getMedicalExpense() + mfiApplicationDetail.getHouseHoldExpense()
					+ mfiApplicationDetail.getFoodExpense() + mfiApplicationDetail.getOtherExpense() + mfiApplicationDetail.getClothesExpense();
			mfiApplicationDetail.setTotalExpense(CommonUtils.isObjectNullOrEmpty(totalExpense) ? 0.0 : totalExpense);
			detailsRepository.save(mfiApplicationDetail);
			return true;
		}
		return false;

	}

	@Override
	public MfiIncomeAndExpenditureReq getIncomeExpenditureDetailsAppId(Long applicationId) {
		List<MfiIncomeAndExpenditureReq> detailsReq = detailsRepository.findIncomeAndExpenditureDetailsByAppId(applicationId);
		if (!CommonUtils.isListNullOrEmpty(detailsReq)) {
			MfiIncomeAndExpenditureReq expenditureReq = detailsReq.get(0);
			List<MfiIncomeDetailsReq> incomeDetails = MfiIncomeDetailsRepository.findIncomeDetailsByAppId(applicationId);
			expenditureReq.setIncomeDetailsReqList(!CommonUtils.isListNullOrEmpty(incomeDetails) ? incomeDetails : Collections.emptyList());
			return expenditureReq;
		}
		return null;
	}

	/**
	 *
	 * @param applicationId
	 * @param type 1 for Applicant 2 for co-applicant
	 * @return
	 */
	@Override
	public FlagCheckMFI findAllFlag(Long applicationId,Integer type) {
		return detailsRepository.getFlagDetailByApplicationId(applicationId,type);

	}

	@Override
	public boolean saveOrUpdateAssetsLiabilityDetails(MfiAssetsDetailsReq mfiAssetsDetailsReq) {
		MfiAssetsLiabilityDetails mfiAssetsLiabilityDetails = null;
		if (!CommonUtils.isListNullOrEmpty(mfiAssetsDetailsReq.getAssetsDetails()) || !CommonUtils.isListNullOrEmpty(mfiAssetsDetailsReq.getLiabilityDetails())) { // to save assets details
			if (!CommonUtils.isListNullOrEmpty(mfiAssetsDetailsReq.getAssetsDetails())) { // to save assets details
				for (MfiAssetsDetailsReq mfiassetsDetailsReq : mfiAssetsDetailsReq.getAssetsDetails()) {
					mfiAssetsLiabilityDetails = new MfiAssetsLiabilityDetails();
					BeanUtils.copyProperties(mfiassetsDetailsReq, mfiAssetsLiabilityDetails);
					mfiAssetsLiabilityDetails.setApplicationId(mfiassetsDetailsReq.getApplicationId());
					mfiAssetsLiabilityDetails.setType(ASSETS);
					MfiAssetsDetailsRepository.save(mfiAssetsLiabilityDetails);
				}
			}
			if (!CommonUtils.isListNullOrEmpty(mfiAssetsDetailsReq.getLiabilityDetails())) { // to save liability details
				for (MfiAssetsDetailsReq mfiDetailsReq : mfiAssetsDetailsReq.getLiabilityDetails()) {
					mfiAssetsLiabilityDetails = new MfiAssetsLiabilityDetails();
					BeanUtils.copyProperties(mfiDetailsReq, mfiAssetsLiabilityDetails);
					mfiAssetsLiabilityDetails.setApplicationId(mfiDetailsReq.getApplicationId());
					mfiAssetsLiabilityDetails.setType(LIABILITY);
					MfiAssetsDetailsRepository.save(mfiAssetsLiabilityDetails);
				}
			}

			MFIApplicantDetail mfiApplicationDetail = detailsRepository.findOne(mfiAssetsDetailsReq.getId());
			mfiApplicationDetail.setIsAssetsDetailsFilled(true);
			detailsRepository.save(mfiApplicationDetail);
			return true;
		}
		return false;
	}

	@Override
	public MfiAssetsDetailsReq getAssetsLiabilityDetailsAppId(Long applicationId) {
		MfiAssetsDetailsReq mfiAssetsDetailsReq = new MfiAssetsDetailsReq();
		mfiAssetsDetailsReq.setAssetsDetails(MfiAssetsDetailsRepository.findAssetsDetailsByAppId(applicationId));
		mfiAssetsDetailsReq.setLiabilityDetails(MfiAssetsDetailsRepository.findLiabilityDetailsByAppId(applicationId));
		return mfiAssetsDetailsReq;
	}

	@Override
	public boolean saveOrUpdateLoanAssessmentDetails(MfiLoanAssessmentDetailsReq mfiLoanAssessmentDetailsReq) {

		if (null != mfiLoanAssessmentDetailsReq.getId()) {
//            serverSideValidation(1,mfiLoanAssessmentDetailsReq);
			MFIApplicantDetail mfiApplicationDetail = detailsRepository.findOne(mfiLoanAssessmentDetailsReq.getId());
			BeanUtils.copyProperties(mfiLoanAssessmentDetailsReq, mfiApplicationDetail);
			mfiApplicationDetail.setIsLoanassessmentDetailsFilled(true);
			detailsRepository.save(mfiApplicationDetail);
			return true;
		}
		return false;

	}

	@Override
	public MfiLoanAssessmentDetailsReq getLoanAssessmentDetailsAppId(Long applicationId) {
		List<MfiLoanAssessmentDetailsReq> detailsReq = detailsRepository.findLoanAssessmentDetailsByAppId(applicationId);
		return !CommonUtils.isListNullOrEmpty(detailsReq) ? detailsReq.get(0) : null;
	}

	@Override
	public MfiLoanAssessmentDetailsReq getCashFlowAssesmentByAppId(Long applicationId,Integer type) {
		return detailsRepository.getCashFlowAssesmentByAppId(applicationId, type);
	}


    private String serverSideValidation(Integer type, Object validationJson) {

        if (type == CommonUtils.BASIC_DETAILS) {
            AadharDetailsReq aadharDetailsReq = (AadharDetailsReq) validationJson;
            if (CommonUtils.isObjectNullOrEmpty(aadharDetailsReq.getFirstName()) || CommonUtils.isObjectNullOrEmpty(aadharDetailsReq.getLastName())) {
                return "First Name or last name is empty";
            } else if (CommonUtils.isObjectNullOrEmpty(aadharDetailsReq.getGenderId())) {
                return "Gender is empty";
            } else if (CommonUtils.isObjectNullOrEmpty(aadharDetailsReq.getMaritalStatusId())) {
                return "Marital status is empty";
            } else if (CommonUtils.isObjectNullOrEmpty(aadharDetailsReq.getBirthDate())) {
                return "Date of birth is empty";
            } else if (CommonUtils.isObjectNullOrEmpty(aadharDetailsReq.getAddressProfType())) {
                return "Address proof type is empty";
            } else if (CommonUtils.isObjectNullOrEmpty(aadharDetailsReq.getAddressProofNo())) {
                return "Address proof number is empty";
            } else if (CommonUtils.isObjectNullOrEmpty(aadharDetailsReq.getAddressPincode())) {
                return "pincode is empty";
            }
        } else if(type == CommonUtils.PERSONAL_DETAILS){
            PersonalDetailsReq personalDetailsReq = (PersonalDetailsReq) validationJson;
            if(CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getFatherName()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getSpouseName()) ||
                    CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getNoDependent()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getSpouseBirthDate())){
                return "Some required fields in Family Details are Missing Personal Detail section";
            } else if(CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getNomineeName()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getRelationWithNomineeId()) ||
                    CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getNomineeBirthDate()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getNomineePincode()) ||
                    CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getNomineeDistrict()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getNomineeCity()) ||
                    CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getNomineeState()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getNomineeAddress())){
                return "Some required fields in Nominee's details are missing Personal Detail section";
            } else if(CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getEducationQualification()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getReligion()) ||
                    CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getAcademicCaste())){
                return "Some required fields in Acadamic and other details are missing Personal Detail section";
            } else if(CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getLandHolding()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getAreaType()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getBusinessType()) ||
                    CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getHouseOwnership()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getNameOfFirm()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getBusinessPremises())
            || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getExpInSameLine())){
                return "Some required fields in Business and Other details are missing Personal Detail section";
            }
        } else if(type == CommonUtils.BANK_DETAILS){
            MfiBankDetailsReq mfiBankDetailsReq = (MfiBankDetailsReq) validationJson;
            if(CommonUtils.isObjectNullOrEmpty(mfiBankDetailsReq.getBankId()) || CommonUtils.isObjectNullOrEmpty(mfiBankDetailsReq.getAccountNo()) || CommonUtils.isObjectNullOrEmpty(mfiBankDetailsReq.getAccountType())
					|| CommonUtils.isObjectNullOrEmpty(mfiBankDetailsReq.getBranchName()) || CommonUtils.isObjectNullOrEmpty(mfiBankDetailsReq.getIfscCode())){
				return "Some required fields in Bank details are missing";
			}
		} else if(type == CommonUtils.INCOME_EXPENDITURE){
        	MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq = (MfiIncomeAndExpenditureReq) validationJson;
        	if(CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getOtherExpense()) || CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getOtherInstallment()) || CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getMedicalExpense())
					|| CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getEducationExpense()) || CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getFoodExpense()) || CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getLoanInstallment())
					|| CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getShipShgiInstallment()) || CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getHouseHoldExpense()) || CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getClothesExpense())) {
				return "Some required fields in family monthly Expenses are missing in Income and Expenditure section";
			} else if(CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getBusinessInBrief()) || CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getMonthlyCashflow()) || CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getMonthlyExpenditure())
					|| CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getMonthlyIncome())){
				return "Some required fields in expected increase income are missing in Income and Expenditure section";
			} else if(CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getPpiNoFamilyMember()) || CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getPpiAcadamicHeadFamily()) ){
				return "Some required fields in Progress out of poverty index are missing in Income and Expenditure section";
			}
		} else if(type == CommonUtils.PROJECT_DETAILS){
        	ProjectDetailsReq projectDetailsReq = (ProjectDetailsReq) validationJson;
        	if(CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getLoanType()) || CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getLoanAmountRequired()) || CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getPurposeOfLoan())){
				return "Some required fields in Loan Applied are missing In project detail section";
			}else if(CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getRepaymentFrequency())){
				return "Some required fields in Repayment & insurence are missing In project detail section";
        	}else if(CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getCostOfEquipment()) || CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getWorkingCapOfEquipment())){
				return "Some required fields in cost of finance are missing In project detail section";
        	}else if(CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getPromoterContribution()) ||CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getLoanRequiredFromSidbi())){
				return "Some required fields in mean of finance are missing In project detail section";
			}
		}
        return null;
    }

}
