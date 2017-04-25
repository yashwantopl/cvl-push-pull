package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.WorkingCapitalLoanRequest;

public interface WorkingCapitalLoanService {

	public boolean saveOrUpdate(WorkingCapitalLoanRequest capitalLoanRequest);
	
	public WorkingCapitalLoanRequest getWorkingCapitalLoan(Long id);
	
	
}
