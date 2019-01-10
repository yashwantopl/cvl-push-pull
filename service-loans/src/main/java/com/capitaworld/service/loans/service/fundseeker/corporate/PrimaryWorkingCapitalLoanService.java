package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.corporate.PrimaryWorkingCapitalLoanRequest;

public interface PrimaryWorkingCapitalLoanService {

	public boolean saveOrUpdate(PrimaryWorkingCapitalLoanRequest capitalLoanRequest,Long userId) throws LoansException;
	
	public PrimaryWorkingCapitalLoanRequest get(Long id,Long userId) throws LoansException;
	
	
}
