package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.PrimaryWorkingCapitalLoanRequest;

public interface PrimaryWorkingCapitalLoanService {

	public boolean saveOrUpdate(PrimaryWorkingCapitalLoanRequest capitalLoanRequest);
	
	public PrimaryWorkingCapitalLoanRequest get(Long id);
	
	
}
