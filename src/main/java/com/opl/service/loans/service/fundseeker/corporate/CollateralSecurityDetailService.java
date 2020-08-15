package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.opl.mudra.api.loans.model.corporate.CollateralSecurityDetailRequest;

public interface CollateralSecurityDetailService {
	
	public void saveData(List<CollateralSecurityDetailRequest> collateralSecurityDetailRequests, Long applicationId, Long userId) throws Exception;
	
	public List<CollateralSecurityDetailRequest> getData(Long applicationId);

}
