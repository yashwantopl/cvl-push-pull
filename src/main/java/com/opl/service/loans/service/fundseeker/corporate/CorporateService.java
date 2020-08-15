package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;
import java.util.Map;

import com.opl.mudra.api.common.CommonResponse;

public interface CorporateService {
	
	public CommonResponse copyMsmeDataWithProposalId (Long applicationId, Long proposalId);
	
	public List<Map<String, Object>> getLoanTabsData(Long profileId, Long applicationId, Long userId) throws Exception;
	
	public CommonResponse isProfileUpdated(Long profileId, Long applicationId, boolean isLoanSaved, Long userId);

}
