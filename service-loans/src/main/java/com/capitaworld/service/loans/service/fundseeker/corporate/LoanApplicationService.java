package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoanApplicationDetailsForSp;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.users.model.UserResponse;

public interface LoanApplicationService {

	public boolean saveOrUpdate(FrameRequest commonRequest, Long userId) throws Exception;

	public LoanApplicationRequest get(Long id, Long userId) throws Exception;

	public boolean inActive(Long id, Long userId) throws Exception;

	public List<LoanApplicationRequest> getList(Long userId) throws Exception;

	public List<LoanApplicationDetailsForSp> getLoanDetailsByUserIdList(Long userId);

	public boolean lockPrimary(Long applicationId, Long userId, Integer productId) throws Exception;

	public boolean lockFinal(Long applicationId, Long userId,  Integer productId) throws Exception;
	
	public UserResponse setLastAccessApplication(Long applicationId,Long userId) throws Exception;
	
	public Integer getProductIdByApplicationId(Long applicationId,Long userId) throws Exception;
}
