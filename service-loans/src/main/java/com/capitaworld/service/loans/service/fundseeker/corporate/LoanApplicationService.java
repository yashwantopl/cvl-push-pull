package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.LoansResponse;

public interface LoanApplicationService {

	public boolean saveOrUpdate(FrameRequest commonRequest, Long userId) throws Exception;

	public LoanApplicationRequest get(Long id, Long userId) throws Exception;

	public boolean inActive(Long id, Long userId) throws Exception;

	public List<LoanApplicationRequest> getList(Long userId) throws Exception;

	public List<LoansResponse> getLoanDetailsByUserIdList(List<Long> list);

	public boolean lockPrimary(Long applicationId, Long userId, Integer productId) throws Exception;

	public boolean lockFinal(Long applicationId, Long userId,  Integer productId) throws Exception;
}
