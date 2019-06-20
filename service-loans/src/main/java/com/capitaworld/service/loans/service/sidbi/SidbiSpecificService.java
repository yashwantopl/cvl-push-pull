package com.capitaworld.service.loans.service.sidbi;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.sidbi.SidbiBasicDetailRequest;

public interface SidbiSpecificService {
	
	boolean saveOrUpdateAdditionalData(SidbiBasicDetailRequest corporateAdditionalRequest, Long userId)
			throws LoansException;

}
