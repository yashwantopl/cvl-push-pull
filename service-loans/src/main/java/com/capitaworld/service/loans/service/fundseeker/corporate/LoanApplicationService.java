package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;

import com.capitaworld.service.auth.model.MobilelAppLoginResponse;
import com.capitaworld.service.loans.controller.mobile.MobileLoanController;
import com.capitaworld.service.loans.model.AdminPanelLoanDetailsResponse;
import com.capitaworld.service.loans.model.CommonResponse;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoanApplicationDetailsForSp;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.common.ChatDetails;
import com.capitaworld.service.loans.model.common.ProposalList;
import com.capitaworld.service.users.model.FpProfileBasicDetailRequest;
import com.capitaworld.service.loans.model.mobile.MLoanDetailsResponse;
import com.capitaworld.service.users.model.RegisteredUserResponse;
import com.capitaworld.service.users.model.UserResponse;

public interface LoanApplicationService {

	public boolean saveOrUpdate(FrameRequest commonRequest, Long userId) throws Exception;
	
	public boolean saveOrUpdateFromLoanEligibilty(FrameRequest commonRequest, Long userId) throws Exception;

	public LoanApplicationRequest get(Long id, Long userId) throws Exception;

	public LoanApplicationRequest inActive(Long id, Long userId) throws Exception;

	public List<LoanApplicationRequest> getList(Long userId) throws Exception;

	public List<LoanApplicationDetailsForSp> getLoanDetailsByUserIdList(Long userId);

	public boolean lockPrimary(Long applicationId, Long userId,boolean flag) throws Exception;

	public boolean lockFinal(Long applicationId, Long userId,boolean flag) throws Exception;
	
	public UserResponse setLastAccessApplication(Long applicationId,Long userId) throws Exception;
	
	public Integer getProductIdByApplicationId(Long applicationId,Long userId) throws Exception;
	
	public Object[] getApplicationDetailsById(Long applicationId) throws Exception;
	
	public String getFsApplicantName(Long applicationId) throws Exception;
	
	public void updateFinalCommonInformation(Long applicationId, Long userId, Boolean flag,String finalFilledCount) throws Exception;
	
	public Boolean isProfileAndPrimaryDetailFilled(Long applicationId,Long userId) throws Exception;
	
	public Boolean isPrimaryLocked(Long applicationId, Long userId) throws Exception;
	
	public Boolean isApplicationIdActive(Long applicationId) throws Exception;
	
	public Boolean isFinalDetailFilled(Long applicationId, Long userId) throws Exception;
	
	public Boolean isFinalLocked(Long applicationId, Long userId) throws Exception;
	
	public JSONObject getSelfViewAndPrimaryLocked(Long applicationId, Long userId) throws Exception;
	
	public Integer getCurrencyId(Long applicationId, Long userId) throws Exception;

	public JSONObject getCurrencyAndDenomination(Long applicationId, Long userId) throws Exception;
	
	public JSONObject isAllowToMoveAhead(Long applicationId, Long userId, Integer nextTabType,Long coAppllicantOrGuarantorId) throws Exception;
	
	public boolean hasAlreadyApplied(Long userId, Long applicationId, Integer productId);
	
	public JSONObject getBowlCount(Long applicationId, Long userId);
	
	public List<RegisteredUserResponse> getUsersRegisteredLoanDetails(Long userType);
	
	public List<AdminPanelLoanDetailsResponse> getLoanDetailsForAdminPanel(Integer type) throws IOException, Exception;
	
	public List<ChatDetails> getChatListByApplicationId(Long fpMappingId);
	
	public List<FpProfileBasicDetailRequest> getFpNegativeList(Long applicationId);
	
	public boolean isFsProceed( Long applicationId);
	
	public void saveSuggestionList(ProposalList  proposalList);	
	public List<MLoanDetailsResponse> getLoanListForMobile(Long userId);
	
}
