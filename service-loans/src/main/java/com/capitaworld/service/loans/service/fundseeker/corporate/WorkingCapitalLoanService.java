package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.FinalWorkingCapitalLoanRequest;

public interface WorkingCapitalLoanService {

	public boolean saveOrUpdateFinalDetails(FinalWorkingCapitalLoanRequest capitalLoanRequest);
	
	public FinalWorkingCapitalLoanRequest getFinalWorkingCapitalLoan(Long id);
	
	
}
