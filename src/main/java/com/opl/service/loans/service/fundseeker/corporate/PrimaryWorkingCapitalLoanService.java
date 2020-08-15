package com.opl.service.loans.service.fundseeker.corporate;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.corporate.PrimaryWorkingCapitalLoanRequest;

public interface PrimaryWorkingCapitalLoanService {

	public boolean saveOrUpdate(PrimaryWorkingCapitalLoanRequest capitalLoanRequest,Long userId) throws LoansException;
	
	public PrimaryWorkingCapitalLoanRequest get(Long id,Long userId) throws LoansException;
	
	
}
