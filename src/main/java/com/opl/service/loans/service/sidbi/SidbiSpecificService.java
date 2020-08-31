package com.opl.service.loans.service.sidbi;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.LoansResponse;
import com.opl.mudra.api.loans.model.sidbi.SidbiBasicDetailRequest;
import com.opl.mudra.api.sidbi.enums.SidbiCurrencyRate;

public interface SidbiSpecificService {
	
	boolean saveOrUpdateAdditionalData(SidbiBasicDetailRequest sidbiBasicDetailRequest, Long userId)
			throws LoansException;

	SidbiBasicDetailRequest getAdditionalData(Long applicationId, Long userId) throws LoansException;
	
	Double getLoanAmountByApplicationId(Long applicationId) throws LoansException;
	
	LoansResponse validateSidbiForm(Long applicationId, Long userId) throws LoansException;
	
	public SidbiCurrencyRate getValuesIn(Long applicationId) throws LoansException;
	
	SidbiBasicDetailRequest getSidbiBasicDetailByAppId(Long applicationId) throws LoansException;
}
