package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.corporate.FinalWorkingCapitalLoanRequest;

public interface FinalWorkingCapitalLoanService {

	public boolean saveOrUpdate(FinalWorkingCapitalLoanRequest capitalLoanRequest,Long userId) throws Exception;
	
	public FinalWorkingCapitalLoanRequest get(Long id,Long applicationId) throws Exception;
	
	
}
