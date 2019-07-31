package com.capitaworld.service.loans.service.fundseeker.microfinance;

import com.capitaworld.service.loans.model.ProposalRequestResponce;
import com.capitaworld.service.loans.model.micro_finance.*;

import java.util.List;

public interface MfiApplicationService {

    public AadharDetailsReq saveOrUpdateAadharDetails(AadharDetailsReq aadharDetailsReq);

	public AadharDetailsReq getAadharDetailsByAppId(Long applicationId);

	public boolean saveOrUpdatePersonalDetails(PersonalDetailsReq personalDetailsReq);

	public PersonalDetailsReq getPersonalDetailsAppId(Long applicationId);

	public boolean saveOrUpdateProjectDetails(ProjectDetailsReq projectDetailsReq);

	public List<MfiReqResponse> getMfiApplicantDetails(Long applicationId);

	public boolean saveOrUpdateBankDetails(MfiBankDetailsReq bankDetailsReq);

	public MfiBankDetailsReq fetchBankDetail(Long applicationId);

	public List<MfiApplicantDetailsReq> getAllApplicantDetails(Long applicationId);

	public ProjectDetailsReq getProjectDetailsAppId(Long applicationId);

	public boolean saveOrUpdateIncomeExpenditureDetails(MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq);

	public MfiIncomeAndExpenditureReq getIncomeExpenditureDetailsAppId(Long applicationId);
	
	public boolean saveOrUpdateAssetsLiabilityDetails(MfiAssetsDetailsReq mfiAssetsDetailsReq);
	
	public MfiAssetsDetailsReq getAssetsLiabilityDetailsAppId(Long applicationId);

	public FlagCheckMFI findAllFlag(Long applicationId,Integer type);
	
	public boolean saveOrUpdateLoanAssessmentDetails(MfiLoanAssessmentDetailsReq mfiLoanAssessmentDetailsReq);
	
	public MfiLoanAssessmentDetailsReq getLoanAssessmentDetailsAppId(Long applicationId);

	public MfiLoanAssessmentDetailsReq getCashFlowAssesmentByAppId(Long applicationId,Integer type);

	public ProposalRequestResponce getProposalDetails(ProposalRequestResponce proposalRequestResponce);

}
