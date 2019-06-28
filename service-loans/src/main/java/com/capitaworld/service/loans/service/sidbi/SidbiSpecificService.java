package com.capitaworld.service.loans.service.sidbi;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.sidbi.SidbiBasicDetailRequest;

public interface SidbiSpecificService {
	
	boolean saveOrUpdateAdditionalData(SidbiBasicDetailRequest sidbiBasicDetailRequest, Long userId)
			throws LoansException;

	SidbiBasicDetailRequest getAdditionalData(Long applicationId, Long userId) throws LoansException;
	
	Double getLoanAmountByApplicationId(Long applicationId) throws LoansException;
}
