package com.capitaworld.service.loans.service.fundseeker.microfinance.impl;

import java.io.IOException;
import java.util.*;

import com.capitaworld.service.loans.domain.fundprovider.ProposalDetails;
import com.capitaworld.service.loans.domain.fundseeker.ApplicationStatusMaster;
import com.capitaworld.service.loans.domain.fundseeker.mfi.*;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.ProposalRequestResponce;
import com.capitaworld.api.workflow.model.WorkflowJobsTrackerRequest;
import com.capitaworld.api.workflow.model.WorkflowRequest;
import com.capitaworld.api.workflow.model.WorkflowResponse;
import com.capitaworld.api.workflow.utility.WorkflowUtils;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.service.loans.model.micro_finance.*;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.Mfi.*;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.scoring.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.users.client.UsersClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.model.micro_finance.AadharDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiApplicantDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiAssetsDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiBankDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiIncomeAndExpenditureReq;
import com.capitaworld.service.loans.model.micro_finance.MfiIncomeDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiLoanAssessmentDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.PersonalDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.ProjectDetailsReq;
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

    @Autowired
    private ProposalDetailsRepository proposalDetailsRepository;

    @Autowired
    private MfiExpenseExpectedIncomeDetailRepository expectedIncomeDetailRepository;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Autowired
    private WorkflowClient workflowClient;


    @Override
    public AadharDetailsReq saveOrUpdateAadharDetails(AadharDetailsReq aadharDetailsReq) {
        MFIApplicantDetail mfiApplicationDetail;
        String serverSideValidation = serverSideValidation(CommonUtils.BASIC_DETAILS, aadharDetailsReq);
        if (!CommonUtils.isObjectNullOrEmpty(serverSideValidation)) {
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
                mfiApplicationDetail.setType(aadharDetailsReq.getType());
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

        if (!CommonUtils.isObjectNullOrEmpty(serverSideValidation)) {
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

        if (!CommonUtils.isObjectNullOrEmpty(serverSideValidation)) {
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
    public Object saveOrUpdateBankDetails(MfiBankDetailsReq bankDetailsReq) {
        //for server side validation
        String serverSideValidation = serverSideValidation(CommonUtils.BANK_DETAILS, bankDetailsReq);

        if (!CommonUtils.isObjectNullOrEmpty(serverSideValidation)) {
            //return if required fields empty
            return serverSideValidation;
        }
        if (bankDetailsReq.getApplicationId() != null) {
            //save bank details in bank details table
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
    public MfiApplicantDetailsReq getApplicantDetails(Long applicationId,Integer type) {
            MFIApplicantDetail mfiApplicantDetail = detailsRepository.findByApplicationIdAndAndTypeIsActive(applicationId,type);

            MfiApplicantDetailsReq detailsReq = new MfiApplicantDetailsReq();
            BeanUtils.copyProperties(mfiApplicantDetail, detailsReq);
            //for bank details
            MfiBankDetails byApplicationId = bankDetailsRepository.findByApplicationId(applicationId);
            if (byApplicationId != null) {
                BeanUtils.copyProperties(byApplicationId, detailsReq);
                detailsReq.setAcHolderName(byApplicationId.getAccountHolderName());
            }
            //for assets and liability
            detailsReq.setAssetsDetails(MfiAssetsDetailsRepository.findAssetsDetailsByAppId(applicationId));
            detailsReq.setLiabilityDetails(MfiAssetsDetailsRepository.findLiabilityDetailsByAppId(applicationId));
            //for Income
            List<MfiIncomeDetailsReq> incomeDetails = MfiIncomeDetailsRepository.findIncomeDetailsByAppId(applicationId, 1);
            detailsReq.setIncomeDetailsReqList(incomeDetails);

            List<MfiIncomeDetailsReq> incomeDetailsEditable = MfiIncomeDetailsRepository.findIncomeDetailsByAppId(applicationId, 2);
            detailsReq.setIncomeDetailsTypeTwoList(incomeDetailsEditable);

            // FOR MFI MAKER MfiIncomeAndExpenditureReq
            MfiExpenseExpectedIncomeDetails mfiIncomeAndExpendMFIMaker = expectedIncomeDetailRepository.findByApplicationIdAndType(applicationId,1); 
            
            MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq = detailsRepository.findIncomeAndExpenditureDetailsByAppId(applicationId,1);
            BeanUtils.copyProperties(mfiIncomeAndExpendMFIMaker, mfiIncomeAndExpenditureReq);
            
            detailsReq.setMfiIncomeAndExpenditureReqMFIMaker(mfiIncomeAndExpenditureReq);

            // FOR MFI CHECKER MfiIncomeAndExpenditureReq
            MfiExpenseExpectedIncomeDetails mfiIncomeAndExpendMFIChecker = expectedIncomeDetailRepository.findByApplicationIdAndType(applicationId,2); 
            MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq2 = new MfiIncomeAndExpenditureReq();
            BeanUtils.copyProperties(mfiIncomeAndExpendMFIChecker, mfiIncomeAndExpenditureReq2);
            detailsReq.setMfiIncomeAndExpenditureReqMFIChecker(mfiIncomeAndExpenditureReq2);
            
            

        return detailsReq;

    }

    @Override
    public ProjectDetailsReq getProjectDetailsAppId(Long applicationId) {
        List<ProjectDetailsReq> detailsReq = detailsRepository.findProjectDetailsByAppId(applicationId);
        return !CommonUtils.isListNullOrEmpty(detailsReq) ? detailsReq.get(0) : null;
    }

    @Override
    public Object saveOrUpdateIncomeExpenditureDetails(MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq) {
        Double totalIncome = 0.0;
        //check server side validation
        String serverSideValidation = serverSideValidation(CommonUtils.INCOME_EXPENDITURE, mfiIncomeAndExpenditureReq);

        if (!CommonUtils.isObjectNullOrEmpty(serverSideValidation)) {
            //return if required fields not available
            return serverSideValidation;
        }
        if (null != mfiIncomeAndExpenditureReq.getId()) {
            //save data in expense and expected income details for agent type 1 for agent details
            saveIncomeAndExpenditureWithCopy(mfiIncomeAndExpenditureReq, totalIncome, 1);
            //save data in expense and expected income details for agent type 1 for checker details
            saveIncomeAndExpenditureWithCopy(mfiIncomeAndExpenditureReq, totalIncome, 2);

            //save PPI and is income filled true
            MFIApplicantDetail mfiApplicationDetail = detailsRepository.findOne(mfiIncomeAndExpenditureReq.getId());
            Integer type = mfiApplicationDetail.getType();
            BeanUtils.copyProperties(mfiIncomeAndExpenditureReq, mfiApplicationDetail);
            mfiApplicationDetail.setIsIncomeDetailsFilled(true);
            mfiApplicationDetail.setType(type);
            detailsRepository.save(mfiApplicationDetail);
            return true;
        }
        return false;

    }

    /**
     * this method for copy code of maker and agent
     * @param mfiIncomeAndExpenditureReq
     * @param totalIncome
     * @param type
     * @return
     */
    private boolean saveIncomeAndExpenditureWithCopy(MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq, Double totalIncome,Integer type){
        Double totalExpense = 0.0;
        if (!CommonUtils.isListNullOrEmpty(mfiIncomeAndExpenditureReq.getIncomeDetailsReqList())) {
            //for MFI Agent data from users
            List<MfiIncomeDetailsReq> incomeDetailsByAppId = MfiIncomeDetailsRepository.findIncomeDetailsByAppId(mfiIncomeAndExpenditureReq.getApplicationId(), 1);
            for (MfiIncomeDetailsReq mfiIncomeDetailsReq : incomeDetailsByAppId) {
                MfiIncomeDetails mfiIncomeDetails = MfiIncomeDetailsRepository.findOne(mfiIncomeDetailsReq.getId());
                mfiIncomeDetails.setIsActive(false);
                MfiIncomeDetailsRepository.save(mfiIncomeDetails);
            }
            for (MfiIncomeDetailsReq mfiIncomeDetailsReq : mfiIncomeAndExpenditureReq.getIncomeDetailsReqList()) {
                MfiIncomeDetails mfiIncomeDetails = new MfiIncomeDetails();
                BeanUtils.copyProperties(mfiIncomeDetailsReq, mfiIncomeDetails);
                totalIncome = totalIncome + mfiIncomeDetails.getMonthlyIncome();
                mfiIncomeDetails.setType(type);
                mfiIncomeDetails.setIsActive(true);
                MfiIncomeDetailsRepository.save(mfiIncomeDetails);
            }
        }
        MfiExpenseExpectedIncomeDetails expectedIncomeDetails = expectedIncomeDetailRepository.findByApplicationIdAndType(mfiIncomeAndExpenditureReq.getApplicationId(),1);
        expectedIncomeDetails.setApplicationId(mfiIncomeAndExpenditureReq.getApplicationId());
        BeanUtils.copyProperties(mfiIncomeAndExpenditureReq, expectedIncomeDetails);
        //below code for calculate total expense
        totalExpense = expectedIncomeDetails.getEducationExpense() + expectedIncomeDetails.getMedicalExpense() + expectedIncomeDetails.getHouseHoldExpense()
                + expectedIncomeDetails.getFoodExpense() + expectedIncomeDetails.getOtherExpense() + expectedIncomeDetails.getClothesExpense() + expectedIncomeDetails.getLoanInstallment()
                + expectedIncomeDetails.getShipShgiInstallment() + expectedIncomeDetails.getOtherInstallment();
        //  Existing Expenses from Business/Family
        expectedIncomeDetails.setTotalExpense(CommonUtils.isObjectNullOrEmpty(totalExpense) ? 0.0 : totalExpense);
        //  Income from Occupation/Business is Monthly
        expectedIncomeDetails.setTotalMonthlyIncomeForFamily(CommonUtils.isObjectNullOrEmpty(totalIncome) ? 0.0 : totalIncome);
        //  Net Savings
        expectedIncomeDetails.setNetSaving(expectedIncomeDetails.getTotalMonthlyIncomeForFamily() - expectedIncomeDetails.getTotalExpense());
        //  Expected Increase in Income out of Loan ---- Monthly Income Column use
        //  Total Cash Flow
        expectedIncomeDetails.setCashFlow(expectedIncomeDetails.getMonthlyIncome() + expectedIncomeDetails.getNetSaving());
        expectedIncomeDetails.setIsActive(true);
        expectedIncomeDetails.setType(type);
        expectedIncomeDetailRepository.save(expectedIncomeDetails);
        return true;
    }

    @Override
    public MfiIncomeAndExpenditureReq getIncomeExpenditureDetailsAppId(Long applicationId) {
            MfiIncomeAndExpenditureReq detailsReq = detailsRepository.findIncomeAndExpenditureDetailsByAppId(applicationId,1);
            List<MfiIncomeDetailsReq> incomeDetails = MfiIncomeDetailsRepository.findIncomeDetailsByAppId(applicationId,1);
            detailsReq.setIncomeDetailsReqList(!CommonUtils.isListNullOrEmpty(incomeDetails) ? incomeDetails : Collections.emptyList());
            return detailsReq;
    }

    /**
     * @param applicationId
     * @param type          1 for Applicant 2 for co-applicant
     * @return
     */
    @Override
    public FlagCheckMFI findAllFlag(Long applicationId, Integer type) {
        return detailsRepository.getFlagDetailByApplicationId(applicationId, type);

    }

    @Override
    public LoansResponse saveOrUpdateAssetsLiabilityDetails(MfiAssetsDetailsReq mfiAssetsDetailsReq) {
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
            //set flag filled assets
            MFIApplicantDetail mfiApplicationDetail = detailsRepository.findOne(mfiAssetsDetailsReq.getId());
            mfiApplicationDetail.setIsAssetsDetailsFilled(true);
            detailsRepository.save(mfiApplicationDetail);
            //for status change to 10 display in Checker this code for submit application or add in consent form
            LoanApplicationMaster corporateLoan = loanApplicationRepository.getById(mfiAssetsDetailsReq.getApplicationId());
            corporateLoan.setApplicationStatusMaster(new ApplicationStatusMaster(CommonUtils.ApplicationStatus.MFI_PENDING));
            loanApplicationRepository.save(corporateLoan);
//            WorkflowRequest  request = new WorkflowRequest();
//            if(!CommonUtils.isObjectNullOrEmpty(mfiApplicationDetail.getJobId())){
//                request.setJobId(mfiApplicationDetail.getJobId());
//            }
//            request.setApplicationId(mfiAssetsDetailsReq.getApplicationId());
//            request.setUserId(mfiAssetsDetailsReq.getUserId());
//            List<Long> roles = new ArrayList<>();
//            roles.add(17l);
//            request.setRoleIds(roles);
//            Object activeButtons = getActiveButtons(request);
//            WorkflowJobsTrackerRequest objectFromMap = (WorkflowJobsTrackerRequest) activeButtons;
            LoansResponse loansResponse = new LoansResponse();
            loansResponse.setMessage("Successfully Saved.");
            loansResponse.setStatus(HttpStatus.OK.value());
            return loansResponse;
        }
        return null;
    }

    @Override
    public MfiAssetsDetailsReq getAssetsLiabilityDetailsAppId(Long applicationId) {
        MfiAssetsDetailsReq mfiAssetsDetailsReq = new MfiAssetsDetailsReq();
        mfiAssetsDetailsReq.setAssetsDetails(MfiAssetsDetailsRepository.findAssetsDetailsByAppId(applicationId));
        mfiAssetsDetailsReq.setLiabilityDetails(MfiAssetsDetailsRepository.findLiabilityDetailsByAppId(applicationId));
        return mfiAssetsDetailsReq;
    }

    @Override
    public Object saveOrUpdateLoanAssessmentDetails(MfiLoanAssessmentDetailsReq mfiLoanAssessmentDetailsReq) {
        String serverSideValidation = serverSideValidation(CommonUtils.LOAN_ASSESMENT, mfiLoanAssessmentDetailsReq);
        if (!CommonUtils.isObjectNullOrEmpty(serverSideValidation)) {
            return serverSideValidation;
        }
        if (null != mfiLoanAssessmentDetailsReq.getId()) {
            MFIApplicantDetail mfiApplicationDetail = detailsRepository.findOne(mfiLoanAssessmentDetailsReq.getId());
            BeanUtils.copyProperties(mfiLoanAssessmentDetailsReq, mfiApplicationDetail);
            mfiApplicationDetail.setIsLoanassessmentDetailsFilled(true);
            detailsRepository.save(mfiApplicationDetail);
            return true;
        }
        return false;

    }

    @Override
    public Object saveOrUpdateLoanRecommandationDetails(MfiLoanRecomandationReq loanRecomandationReq) {
        LoansResponse loansResponse = new LoansResponse();
        String serverSideValidation = serverSideValidation(CommonUtils.LOAN_RECOMANDATION, loanRecomandationReq);
        if (!CommonUtils.isObjectNullOrEmpty(serverSideValidation)) {
            loansResponse.setData(serverSideValidation);
            loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return loansResponse;
        }
        if (null != loanRecomandationReq.getId()) {
            MFIApplicantDetail mfiApplicationDetail = detailsRepository.findOne(loanRecomandationReq.getId());
            BeanUtils.copyProperties(loanRecomandationReq, mfiApplicationDetail);
            detailsRepository.save(mfiApplicationDetail);
            WorkflowRequest  request = new WorkflowRequest();
            if(!CommonUtils.isObjectNullOrEmpty(mfiApplicationDetail.getJobId())){
                request.setJobId(mfiApplicationDetail.getJobId());
            }
            request.setApplicationId(loanRecomandationReq.getApplicationId());
            request.setUserId(loanRecomandationReq.getUserId());
            List<Long> roles = new ArrayList<>();
            roles.add(17l);
            request.setRoleIds(roles);
            Object activeButtons = getActiveButtons(request);
            WorkflowJobsTrackerRequest objectFromMap = (WorkflowJobsTrackerRequest) activeButtons;
            loansResponse.setData(objectFromMap.getStep().getStepActions());
            loansResponse.setId(objectFromMap.getJob().getId());
            loansResponse.setMessage("Successfully Saved.");
            loansResponse.setStatus(HttpStatus.OK.value());
            return loansResponse;
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
        return expectedIncomeDetailRepository.findCashFlowAssessment(applicationId,type);
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
        } else if (type == CommonUtils.PERSONAL_DETAILS) {
            PersonalDetailsReq personalDetailsReq = (PersonalDetailsReq) validationJson;
            if (CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getFatherName()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getSpouseName()) ||
                    CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getNoDependent()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getSpouseBirthDate())) {
                return "Some required fields in Family Details are Missing Personal Detail section";
            } else if (CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getNomineeName()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getRelationWithNomineeId()) ||
                    CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getNomineeBirthDate()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getNomineePincode()) ||
                    CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getNomineeDistrict()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getNomineeCity()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getNomineeState())) {
                return "Some required fields in Nominee's details are missing Personal Detail section";
            } else if (CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getEducationQualification()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getAcademicReligion()) ||
                    CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getAcademicCaste())) {
                return "Some required fields in Acadamic and other details are missing Personal Detail section";
            } else if (CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getLandHolding()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getAreaType()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getBusinessType()) ||
                    CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getHouseOwnership()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getNameOfFirm()) || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getBusinessPremises())
                    || CommonUtils.isObjectNullOrEmpty(personalDetailsReq.getExpInSameLine())) {
                return "Some required fields in Business and Other details are missing Personal Detail section";
            }
        } else if (type == CommonUtils.BANK_DETAILS) {
            MfiBankDetailsReq mfiBankDetailsReq = (MfiBankDetailsReq) validationJson;
            if (CommonUtils.isObjectNullOrEmpty(mfiBankDetailsReq.getBankId()) || CommonUtils.isObjectNullOrEmpty(mfiBankDetailsReq.getAccountNo()) || CommonUtils.isObjectNullOrEmpty(mfiBankDetailsReq.getAccountType())
                    || CommonUtils.isObjectNullOrEmpty(mfiBankDetailsReq.getBranchName()) || CommonUtils.isObjectNullOrEmpty(mfiBankDetailsReq.getIfscCode())) {
                return "Some required fields in Bank details are missing";
            }
        } else if (type == CommonUtils.INCOME_EXPENDITURE) {
            MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq = (MfiIncomeAndExpenditureReq) validationJson;
            if (CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getOtherExpense()) || CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getOtherInstallment()) || CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getMedicalExpense())
                    || CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getEducationExpense()) || CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getFoodExpense()) || CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getLoanInstallment())
                    || CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getShipShgiInstallment()) || CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getHouseHoldExpense()) || CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getClothesExpense())) {
                return "Some required fields in family monthly Expenses are missing in Income and Expenditure section";
            } else if (CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getBusinessInBrief()) || CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getMonthlyCashflow()) || CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getMonthlyExpenditure())
                    || CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getMonthlyIncome())) {
                return "Some required fields in expected increase income are missing in Income and Expenditure section";
            } else if (CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getPpiNoFamilyMember()) || CommonUtils.isObjectNullOrEmpty(mfiIncomeAndExpenditureReq.getPpiAcadamicHeadFamily())) {
                return "Some required fields in Progress out of poverty index are missing in Income and Expenditure section";
            }
        } else if (type == CommonUtils.PROJECT_DETAILS) {
            ProjectDetailsReq projectDetailsReq = (ProjectDetailsReq) validationJson;
            if (CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getLoanType()) || CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getLoanAmountRequired()) || CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getPurposeOfLoan())) {
                return "Some required fields in Loan Applied are missing In project detail section";
            } else if (CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getRepaymentFrequency())) {
                return "Some required fields in Repayment & insurence are missing In project detail section";
            } else if (CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getCostOfEquipment()) || CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getWorkingCapOfEquipment())) {
                return "Some required fields in cost of finance are missing In project detail section";
            } else if (CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getPromoterContribution()) || CommonUtils.isObjectNullOrEmpty(projectDetailsReq.getLoanRequiredFromSidbi())) {
                return "Some required fields in mean of finance are missing In project detail section";
            }
        } else if (type == CommonUtils.LOAN_ASSESMENT) {
            MfiLoanAssessmentDetailsReq assessmentDetailsReq = (MfiLoanAssessmentDetailsReq) validationJson;
            if (CommonUtils.isObjectNullOrEmpty(assessmentDetailsReq.getClientType()) || CommonUtils.isObjectNullOrEmpty(assessmentDetailsReq.getRepaymentTrack()) || CommonUtils.isObjectNullOrEmpty(assessmentDetailsReq.getCreaditWorthiness())
                    || CommonUtils.isObjectNullOrEmpty(assessmentDetailsReq.getLoanLiabilityRatio()) || CommonUtils.isObjectNullOrEmpty(assessmentDetailsReq.getCompetition())) {
                return "Some required fields in mean of missing in Loan Assesment detail section";
            }
        } else if (type == CommonUtils.LOAN_RECOMANDATION) {
            MfiLoanRecomandationReq assessmentDetailsReq = (MfiLoanRecomandationReq) validationJson;
            if (CommonUtils.isObjectNullOrEmpty(assessmentDetailsReq.getLoanAmountRecomandation()) || CommonUtils.isObjectNullOrEmpty(assessmentDetailsReq.getTenureRecomandation()) ||
                    CommonUtils.isObjectNullOrEmpty(assessmentDetailsReq.getMoratoriumRecomandation()) || CommonUtils.isObjectNullOrEmpty(assessmentDetailsReq.getInstallmentRecomandation()) ||
                    CommonUtils.isObjectNullOrEmpty(assessmentDetailsReq.getInterestRateRecomandation())) {
                return "Some required fields in mean of missing in Loan Reccommendation detail section";
            }
        }
        return null;
    }


    @Override
    public ProposalRequestResponce getProposalDetails(ProposalRequestResponce proposalRequestResponce) {

        ProposalRequestResponce proposalRequestResponceNew = new ProposalRequestResponce();
        // get Proposal Details

        ProposalDetails proposalDetails = proposalDetailsRepository.getByApplicationIdAndFPProductId(proposalRequestResponce.getApplicationId());
        if (!CommonUtils.isObjectNullOrEmpty(proposalDetails)) {
            proposalRequestResponceNew.setFpProductId(proposalDetails.getFpProductId());
            proposalRequestResponceNew.setProposalStatusId(proposalDetails.getProposalStatusId().getId());
            proposalRequestResponceNew.setProposalMappingId(proposalDetails.getId());
            proposalRequestResponceNew.setApplicationId(proposalDetails.getApplicationId());
        }

        return proposalRequestResponceNew;

    }


    @Override
    public AadharDetailsReq getApplicationsByStatus(Long orgId, Long userId, Integer status) {
        if (status == 1) {
            return detailsRepository.getPendingApplications(userId, orgId);
        } else {
            return detailsRepository.getApprovedApplications(userId, orgId);
        }
    }

	@Override
	public Boolean saveOrUpdateApplicantDetail(MfiApplicantDetailsReq mfiApplicantDetailsReq) {
		Boolean result = false;
		try {
			List<MfiIncomeDetails> mfiIncomeDetails = new ArrayList<>();
			if(mfiApplicantDetailsReq != null) {
				for(MfiIncomeDetailsReq mfiIncomeDetailsReq : mfiApplicantDetailsReq.getIncomeDetailsTypeTwoList()) {
					MfiIncomeDetails mfiIncomeDetail =  new MfiIncomeDetails(); // MfiIncomeDetailsRepository.findOne(mfiApplicantDetailsReq.getId());
					BeanUtils.copyProperties(mfiIncomeDetailsReq, mfiIncomeDetail);
					mfiIncomeDetail.setIsActive(true);
					mfiIncomeDetail.setType(2);
					mfiIncomeDetails.add(mfiIncomeDetail);
				}
				MfiIncomeDetailsRepository.save(mfiIncomeDetails);
				
				MfiIncomeAndExpenditureReq mfiIncomeAndExpendMFIChecker = mfiApplicantDetailsReq.getMfiIncomeAndExpenditureReqMFIChecker(); 
				if(mfiIncomeAndExpendMFIChecker != null) {
					MfiExpenseExpectedIncomeDetails mfiExpenseExpectedIncomeDetails = expectedIncomeDetailRepository.findOne(mfiIncomeAndExpendMFIChecker.getId());
					mfiExpenseExpectedIncomeDetails.setShipShgiInstallment(mfiIncomeAndExpendMFIChecker.getShipShgiInstallment());
					mfiExpenseExpectedIncomeDetails.setOtherInstallment(mfiIncomeAndExpendMFIChecker.getOtherInstallment());
					mfiExpenseExpectedIncomeDetails.setLoanInstallment(mfiIncomeAndExpendMFIChecker.getLoanInstallment());
					mfiExpenseExpectedIncomeDetails.setEducationExpense(mfiIncomeAndExpendMFIChecker.getEducationExpense());
					mfiExpenseExpectedIncomeDetails.setMedicalExpense(mfiIncomeAndExpendMFIChecker.getMedicalExpense());
					mfiExpenseExpectedIncomeDetails.setFoodExpense(mfiIncomeAndExpendMFIChecker.getFoodExpense());
					mfiExpenseExpectedIncomeDetails.setClothesExpense(mfiIncomeAndExpendMFIChecker.getClothesExpense());
					mfiExpenseExpectedIncomeDetails.setOtherInstallment(mfiIncomeAndExpendMFIChecker.getOtherInstallment());
					
					expectedIncomeDetailRepository.save(mfiExpenseExpectedIncomeDetails);
				}
				
				MFIApplicantDetail mfiApplicantDetail = detailsRepository.findByApplicationIdAndAndTypeIsActive(mfiApplicantDetailsReq.getApplicationId(), mfiApplicantDetailsReq.getType());
				if(mfiApplicantDetail != null) {
					mfiApplicantDetail.setLoanAmountBankMaker(mfiApplicantDetailsReq.getLoanAmountBankMaker());
					mfiApplicantDetail.setLoanAmountMFIChecker(mfiApplicantDetailsReq.getLoanAmountMFIChecker());
					detailsRepository.save(mfiApplicantDetail);
				}
				
				result =true;
				
		}

		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("Exception : "+e.getMessage());
		}
		return result;
	}


	public Object getActiveButtons(WorkflowRequest workflowRequest) {

		Long jobId = workflowRequest.getJobId();
		if (CommonUtils.isObjectNullOrEmpty(jobId)) {
			WorkflowResponse workflowResponse = workflowClient.createJobForMasters(
					WorkflowUtils.Workflow.MFI_PROCESS, WorkflowUtils.Action.ASSIGN_TO_MAKER_ON_SAVE,
					workflowRequest.getUserId());
			if (!com.capitaworld.service.scoring.utils.CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
				jobId = Long.valueOf(workflowResponse.getData().toString());
                MFIApplicantDetail mfiApplicationDetail = detailsRepository.findByAppIdAndType(workflowRequest.getApplicationId(),1);
                mfiApplicationDetail.setJobId(jobId);
                detailsRepository.save(mfiApplicationDetail);
			}
		}
        try {
            WorkflowResponse workflowResponse = workflowClient.getActiveStepForMaster(jobId,
				workflowRequest.getRoleIds(), workflowRequest.getUserId());
		if (!com.capitaworld.service.scoring.utils.CommonUtils.isObjectNullOrEmpty(workflowResponse)
				&& !com.capitaworld.service.scoring.utils.CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {

				WorkflowJobsTrackerRequest workflowJobsTrackerRequest = MultipleJSONObjectHelper
						.getObjectFromMap((LinkedHashMap<String, Object>) workflowResponse.getData(),
								WorkflowJobsTrackerRequest.class);
				if (!com.capitaworld.service.scoring.utils.CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep()) && !com.capitaworld.service.scoring.utils.CommonUtils
						.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep().getStepActions())) {
					return workflowJobsTrackerRequest;
				}

		}
        } catch (IOException e) {
            logger.error("Error While getting data from workflow {}", e);
        }
		return null;
	}

    @Override
    public boolean updateStaus(Long applicationId, Long status) {
        return loanApplicationRepository.updateStatus(applicationId, status) > 0;
    }

}
