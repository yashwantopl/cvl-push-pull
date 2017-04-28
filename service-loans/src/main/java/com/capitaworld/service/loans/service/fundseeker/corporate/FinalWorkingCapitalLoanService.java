package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.FinalWorkingCapitalLoanRequest;

public interface FinalWorkingCapitalLoanService {

	public boolean saveOrUpdate(FinalWorkingCapitalLoanRequest capitalLoanRequest);
	
	public FinalWorkingCapitalLoanRequest get(Long id);
	
	
}
