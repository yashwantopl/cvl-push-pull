package com.capitaworld.service.loans.service.fundseeker.microfinance;

import com.capitaworld.service.loans.model.ProposalRequestResponce;
import java.util.List;

import com.capitaworld.api.workflow.model.WorkflowRequest;
import com.capitaworld.service.loans.domain.fundseeker.mfi.MFIApplicantDetail;
import com.capitaworld.service.loans.model.WorkflowData;
import com.capitaworld.service.loans.model.micro_finance.*;

import java.util.List;

public interface MfiApplicationService {

    public AadharDetailsReq saveOrUpdateAadharDetails(AadharDetailsReq aadharDetailsReq);

	public AadharDetailsReq getAadharDetailsByAppId(Long applicationId);

	public Object saveOrUpdatePersonalDetails(PersonalDetailsReq personalDetailsReq);

	public PersonalDetailsReq getPersonalDetailsAppId(Long applicationId);

	public Object saveOrUpdateProjectDetails(ProjectDetailsReq projectDetailsReq);

	public Object saveOrUpdateBankDetails(MfiBankDetailsReq bankDetailsReq);

	public MfiBankDetailsReq fetchBankDetail(Long applicationId);

	public MfiApplicantDetailsReq getApplicantDetails(Long applicationId,Integer type);

	public ProjectDetailsReq getProjectDetailsAppId(Long applicationId);

	public Object saveOrUpdateIncomeExpenditureDetails(MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq);

	public MfiIncomeAndExpenditureReq getIncomeExpenditureDetailsAppId(Long applicationId);
	
	public boolean saveOrUpdateAssetsLiabilityDetails(MfiAssetsDetailsReq mfiAssetsDetailsReq);
	
	public MfiAssetsDetailsReq getAssetsLiabilityDetailsAppId(Long applicationId);

	public FlagCheckMFI findAllFlag(Long applicationId,Integer type);
	
	public Object saveOrUpdateLoanAssessmentDetails(MfiLoanAssessmentDetailsReq mfiLoanAssessmentDetailsReq);
	
	public MfiLoanAssessmentDetailsReq getLoanAssessmentDetailsAppId(Long applicationId);

	public MfiLoanAssessmentDetailsReq getCashFlowAssesmentByAppId(Long applicationId,Integer type);


	public ProposalRequestResponce getProposalDetails(ProposalRequestResponce proposalRequestResponce);

	public AadharDetailsReq getApplicationsByStatus(Long orgId, Long userId, Integer status);


	public Object getActiveButtons(WorkflowRequest workflowRequest);

	public boolean updateStaus(Long applicationId,Long status);

}
