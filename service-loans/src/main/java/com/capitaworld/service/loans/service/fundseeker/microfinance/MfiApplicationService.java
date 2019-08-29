package com.capitaworld.service.loans.service.fundseeker.microfinance;

import java.util.List;
import java.util.Map;

import com.capitaworld.api.workflow.model.WorkflowRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.ProposalRequestResponce;
import com.capitaworld.service.loans.model.mfi.MFIFinancialArrangementRequest;
import com.capitaworld.service.loans.model.micro_finance.AadharDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.FlagCheckMFI;
import com.capitaworld.service.loans.model.micro_finance.MFIConversationReq;
import com.capitaworld.service.loans.model.micro_finance.MfiApplicantDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiAssetsDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiBankDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiIncomeAndExpenditureReq;
import com.capitaworld.service.loans.model.micro_finance.MfiLoanAssessmentDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiLoanRecomandationReq;
import com.capitaworld.service.loans.model.micro_finance.PersonalDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.ProjectDetailsReq;
import org.springframework.web.multipart.MultipartFile;

public interface MfiApplicationService {

    public AadharDetailsReq saveOrUpdateAadharDetails(MultipartFile uploadingFile, MultipartFile[] addressProofFile, AadharDetailsReq aadharDetailsReq);

	public AadharDetailsReq getAadharDetailsByAppId(Long applicationId,Integer type);

	public Object saveOrUpdatePersonalDetails(PersonalDetailsReq personalDetailsReq);

	public PersonalDetailsReq getPersonalDetailsAppId(Long applicationId,Integer type);

	public Object saveOrUpdateProjectDetails(ProjectDetailsReq projectDetailsReq);

	public Object saveOrUpdateBankDetails(MultipartFile uploadingFile, MfiBankDetailsReq bankDetailsReq);

	public MfiBankDetailsReq fetchBankDetail(Long applicationId);

	public MfiApplicantDetailsReq getApplicantDetails(Long applicationId,Integer type);

	public ProjectDetailsReq getProjectDetailsAppId(Long applicationId,Integer type);

	public Object saveOrUpdateIncomeExpenditureDetails(MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq);

	public MfiIncomeAndExpenditureReq getIncomeExpenditureDetailsAppId(Long applicationId);
	
	public LoansResponse saveOrUpdateAssetsLiabilityDetails(MfiAssetsDetailsReq mfiAssetsDetailsReq);
	
	public MfiAssetsDetailsReq getAssetsLiabilityDetailsAppId(Long applicationId);

	public FlagCheckMFI findAllFlag(Long applicationId,Integer type);
	
	public Object saveOrUpdateLoanAssessmentDetails(MfiLoanAssessmentDetailsReq mfiLoanAssessmentDetailsReq);

	public Object saveOrUpdateLoanRecommandationDetails(MfiLoanRecomandationReq recomandationReq);

	public MfiLoanAssessmentDetailsReq getLoanAssessmentDetailsAppId(Long applicationId,Integer type);

	public MfiLoanAssessmentDetailsReq getCashFlowAssesmentByAppId(Long applicationId,Integer type);


	public ProposalRequestResponce getProposalDetails(ProposalRequestResponce proposalRequestResponce);

	public AadharDetailsReq getApplicationsByStatus(Long orgId, Long userId, Integer status);
	
	public Boolean saveOrUpdateApplicantDetail(MfiApplicantDetailsReq mfiApplicantDetailsReq);


	public Object getActiveButtons(WorkflowRequest workflowRequest);

	public boolean updateStaus(Long applicationId,Long status);

	public Object getMfiConversation(MFIConversationReq mfiConversationReq);

	public Object saveOrUpdateMfiConversation(MFIConversationReq mfiConversationReq);

	public LoansResponse callBureauGetFinancialDetails(Long applicationId,Long applicantId, Long userId,Integer type);
	
	public Boolean saveFinancialDetails(List<MFIFinancialArrangementRequest> financialDataList, Long applicationId, Long createdBy, Long applicantId);

	public Boolean saveFinancialData(MFIFinancialArrangementRequest financialData, Long createdBy);

	public Boolean proceedFinancialFinalData(Long applicationId, Long createdBy, Integer creditWorthiness);

	public List<MFIFinancialArrangementRequest> getFinancialDetailsAppId(Long applicationId,Long applicantId);

	public boolean saveConsentFormImage(MultipartFile[] uploadingFile,AadharDetailsReq aadharDetailsReq);
	
	public boolean uploadDocuments(MultipartFile[] uploadingFile, MfiApplicantDetailsReq mfiApplicantDetailsReq);
	
	public Object saveOrUpdateAllApplicantsDetails(MultipartFile uploadingFile,MultipartFile addressProof,MultipartFile consentformImg,MultipartFile aadharImg,MfiApplicantDetailsReq mfiApplicantDetailsReq,Long userId,Long orgId);

	public Map<String, Object> getReportDetails(Long applicationId);
	
	public Map<String, Object> getApplicantDetails1(Long applicationId, Integer type);

}
