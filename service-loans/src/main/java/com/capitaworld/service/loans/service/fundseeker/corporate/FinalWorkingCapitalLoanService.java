package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.FinalWorkingCapitalLoanRequest;

public interface FinalWorkingCapitalLoanService {

	public boolean saveOrUpdate(FinalWorkingCapitalLoanRequest capitalLoanRequest) throws Exception;
	
	public FinalWorkingCapitalLoanRequest get(Long id,Long applicationId) throws Exception;
	
	
}
