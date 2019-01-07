
package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.io.IOException;

import java.util.List;
import java.util.Map;

import com.capitaworld.service.loans.model.common.*;
import com.capitaworld.service.scoring.model.scoringmodel.ScoringModelReqRes;
import org.json.simple.JSONObject;

import com.capitaworld.service.gateway.model.GatewayRequest;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.AdminPanelLoanDetailsResponse;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoanApplicationDetailsForSp;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.PaymentRequest;

import com.capitaworld.service.loans.model.common.CGTMSECalcDataResponse;
import com.capitaworld.service.loans.model.common.ChatDetails;
import com.capitaworld.service.loans.model.common.DisbursementRequest;
import com.capitaworld.service.loans.model.common.EkycRequest;
import com.capitaworld.service.loans.model.common.EkycResponse;
import com.capitaworld.service.loans.model.common.HunterRequestDataResponse;
import com.capitaworld.service.loans.model.common.ProposalList;
import com.capitaworld.service.loans.model.corporate.CorporateProduct;
import com.capitaworld.service.loans.model.mobile.MLoanDetailsResponse;
import com.capitaworld.service.loans.model.mobile.MobileLoanRequest;
import com.capitaworld.service.users.model.FpProfileBasicDetailRequest;
import com.capitaworld.service.users.model.RegisteredUserResponse;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.sidbi.integration.model.ProfileReqRes;

public interface LoanApplicationService {

	public boolean saveOrUpdate(FrameRequest commonRequest, Long userId) throws LoansException;
	
	public boolean saveOrUpdateFromLoanEligibilty(FrameRequest commonRequest, Long userId) throws LoansException;

	public LoanApplicationRequest get(Long id, Long userId) throws LoansException;
	
	public Long getIrrByApplicationId(Long id) throws LoansException;

	public LoanApplicationRequest inActive(Long id, Long userId) throws LoansException;
	
	public int inActiveApplication(Long id, Long userId);

	public List<LoanApplicationRequest> getList(Long userId) throws LoansException;

	public List<LoanApplicationDetailsForSp> getLoanDetailsByUserIdList(Long userId);

	public boolean lockPrimary(Long applicationId, Long userId,boolean flag) throws LoansException;

	public LoanApplicationRequest lockFinal(Long applicationId, Long userId,boolean flag) throws LoansException;
	
	public UserResponse setLastAccessApplication(Long applicationId,Long userId) throws LoansException;
	
	public Integer getProductIdByApplicationId(Long applicationId,Long userId) throws LoansException;
	
	public Object[] getApplicationDetailsById(Long applicationId) throws LoansException;
	
	public String getFsApplicantName(Long applicationId) throws LoansException;
	
	public void updateFinalCommonInformation(Long applicationId, Long userId, Boolean flag,String finalFilledCount) throws LoansException;
	
	public Boolean isProfileAndPrimaryDetailFilled(Long applicationId,Long userId) throws LoansException;
	
	public Boolean isPrimaryLocked(Long applicationId, Long userId) throws LoansException;
	
	public Boolean isApplicationIdActive(Long applicationId) throws LoansException;
	
	public Boolean isFinalDetailFilled(Long applicationId, Long userId) throws LoansException;
	
	public Boolean isFinalLocked(Long applicationId, Long userId) throws LoansException;
	
	public JSONObject getSelfViewAndPrimaryLocked(Long applicationId, Long userId) throws LoansException;
	
	public Integer getCurrencyId(Long applicationId, Long userId) throws LoansException;

	public JSONObject getCurrencyAndDenomination(Long applicationId, Long userId) throws LoansException;
	
	public JSONObject isAllowToMoveAhead(Long applicationId, Long userId, Integer nextTabType,Long coAppllicantOrGuarantorId) throws LoansException;
	
	public boolean hasAlreadyApplied(Long userId, Long applicationId, Integer productId);
	
	public JSONObject getBowlCount(Long applicationId, Long userId);
	
	public List<RegisteredUserResponse> getUsersRegisteredLoanDetails(MobileLoanRequest loanRequest);
	
	public List<AdminPanelLoanDetailsResponse> getLoanDetailsForAdminPanel(Integer type,MobileLoanRequest loanRequest) throws IOException, Exception;

	public List<AdminPanelLoanDetailsResponse> getPostLoginForAdminPanel(MobileLoanRequest loanRequest) throws IOException, LoansException;

	public List<AdminPanelLoanDetailsResponse> getPostLoginForAdminPanelOfEligibility(MobileLoanRequest loanRequest) throws IOException, LoansException;

	public List<AdminPanelLoanDetailsResponse> getPostLoginForAdminPanelOfNotEligibility(MobileLoanRequest loanRequest) throws IOException, LoansException;

	public List<AdminPanelLoanDetailsResponse> getPostLoginForAdminPanelOfFinalLockedRejectedByUbi(MobileLoanRequest loanRequest) throws IOException, LoansException;

	public List<AdminPanelLoanDetailsResponse> getPostLoginForAdminPanelOfApprovedByUbi(MobileLoanRequest loanRequest) throws IOException, LoansException;

	public List<ChatDetails> getChatListByApplicationId(Long fpMappingId);

	public String getMcaCompanyId(Long applicationId, Long userId);
	
	public List<FpProfileBasicDetailRequest> getFpNegativeList(Long applicationId);
	
	public void saveSuggestionList(ProposalList  proposalList);	
	
	public List<MLoanDetailsResponse> getLoanListForMobile(Long userId);

	public void updateLoanApplication(LoanApplicationRequest loanRequest);
	
	public EkycResponse getDetailsForEkycAuthentication(EkycRequest ekycRequest);

	public Boolean isMca(Long applicationId, Long userId);
	
	public LoanApplicationRequest getLoanBasicDetails(Long id, Long userId);
	
	public Long getTotalUserApplication(Long userId);
	
	public Long getUserIdByApplicationId(Long applicationId);
	
	public LoanApplicationRequest saveFromCampaign(Long userId, Long clientId, String campaignCode) throws LoansException;
	
	public boolean isCampaignCodeExist(Long userId, Long clientId, String code) throws LoansException;
	
	public String getCampaignCodeByApplicationId(Long applicationId) throws LoansException;
	
	public Boolean isTermLoanLessThanLimit(Long applicationId);
	
	public Integer getIndustryIrrByApplication(Long applicationId);
	
	public Integer setEligibleLoanAmount(LoanApplicationRequest applicationRequest) throws LoansException;
	
	public void updateFlow(Long applicationId,Long clientId,Long userId) throws LoansException ;
	
	public Object updateLoanApplicationMaster(PaymentRequest paymentRequest, Long userId) throws LoansException;
	
	public void updateSkipPayment(Long userId, Long applicationId, Long orgId,Long fprProductId) throws LoansException;
	
	public void updateSkipPaymentWhiteLabel(Long userId, Long applicationId, Integer businessTypeId, Long orgId,Long fprProductId) throws Exception;
	
	public void sendInPrincipleForPersonalLoan(Long userId, Long applicationId, Integer businessTypeId, Long orgId,Long fprProductId) throws Exception;
	
	public LoanApplicationRequest updateLoanApplicationMasterPaymentStatus(PaymentRequest paymentRequest, Long userId)throws LoansException;
	
	public GatewayRequest getPaymentStatus(PaymentRequest paymentRequest, Long userId, Long ClientId) throws LoansException;
	
	public Long getDDRStatusId(Long applicationId);
	
	public Boolean updateDDRStatus(Long applicationId, Long userId , Long clientId, Long statusId) throws LoansException;
	
	public LoanApplicationRequest getFromClient(Long id) throws LoansException;

	public Boolean isApplicationEligibleForIrr(Long applicationId) throws LoansException;
	
	public DisbursementRequest getDisbursementDetails(DisbursementRequest disbursementRequest);
	
	public Long createMsmeLoan(Long userId,Boolean isActive,Integer businessTypeId);

	public Long createRetailLoan(Long userId, Boolean isActive, Integer businessTypeId);

	public boolean updateProductDetails(LoanApplicationRequest loanApplicationRequest);
	
	public boolean savePhese1DataToSidbi(Long applicationId,Long userId,Long organizationId,Long fpProductMappingId);
	
	public boolean savePhese2DataToSidbi(Long applicationId,Long userId,Long organizationId,Long fpProductMappingId);
	
	public Map<String, Object> getFpDetailsByFpProductId(Long fpProductId) throws LoansException;
	
	public CorporateProduct getFpDetailsByFpProductMappingId(Long fpProductId) throws LoansException;
	
	public LoanApplicationRequest getLoanApplicationDetails(Long userId, Long applicationId);

	public ScoringModelReqRes getMinMaxMarginByApplicationId(Long applicationId,Integer businessTypeId);

	/**
	 * @param applicationId
	 * @return
	 */
	public CGTMSECalcDataResponse getDataForCGTMSE(Long applicationId) throws LoansException;

	public LoanApplicationRequest getProposalDataFromApplicationId(Long applicationId);

	/**
	 * @param applicationId
	 * @return
	 */
	public HunterRequestDataResponse getDataForHunter(Long applicationId) throws Exception;


	public SanctioningDetailResponse getDetailsForSanction(DisbursementRequest disbursementRequest) throws Exception;

	
	public String saveDetailedInfo(ProfileReqRes profileReqRes) throws LoansException;
	
	/*//Update Payment Status after redirection through Gateway for Mobile
	
	public Boolean updatePaymentStatusForMobile(PaymentRequest paymentRequest);
*/
	
	public String getMCACompanyIdById(Long applicationId);

	/**
	 * @param applicationId
	 * @return
	 * @throws Exception
	 */
	public HunterRequestDataResponse getDataForHunterForNTB(Long applicationId) throws Exception;
	
	public Boolean saveLoanWCRenewalType(Long applicationId,Integer wcRenewalType);
	public Integer getLoanWCRenewalType(Long applicationId);

	Boolean isMcqSkipped(Long applicationId) throws LoansException;
	
	public String getCommonPropertiesValue(String key);
}


