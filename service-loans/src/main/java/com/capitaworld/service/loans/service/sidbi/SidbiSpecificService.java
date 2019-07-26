package com.capitaworld.service.loans.service.sidbi;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.sidbi.SidbiBasicDetailRequest;
import com.capitaworld.service.oneform.enums.sidbi.SidbiCurrencyRate;

public interface SidbiSpecificService {
	
	boolean saveOrUpdateAdditionalData(SidbiBasicDetailRequest sidbiBasicDetailRequest, Long userId)
			throws LoansException;

	SidbiBasicDetailRequest getAdditionalData(Long applicationId, Long userId) throws LoansException;
	
	Double getLoanAmountByApplicationId(Long applicationId) throws LoansException;
	
	LoansResponse validateSidbiForm(Long applicationId, Long userId) throws LoansException;
	
	public SidbiCurrencyRate getValuesIn(Long applicationId) throws LoansException;
	
	SidbiBasicDetailRequest getSidbiBasicDetailByAppId(Long applicationId) throws LoansException;
}
