package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.corporate.PrimaryWorkingCapitalLoanRequest;

public interface PrimaryWorkingCapitalLoanService {

	public boolean saveOrUpdate(PrimaryWorkingCapitalLoanRequest capitalLoanRequest) throws Exception;
	
	public PrimaryWorkingCapitalLoanRequest get(Long id) throws Exception;
	
	
}
