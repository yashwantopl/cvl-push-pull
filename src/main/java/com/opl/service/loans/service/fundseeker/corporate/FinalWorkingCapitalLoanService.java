package com.opl.service.loans.service.fundseeker.corporate;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.corporate.FinalWorkingCapitalLoanRequest;

public interface FinalWorkingCapitalLoanService {

	public boolean saveOrUpdate(FinalWorkingCapitalLoanRequest capitalLoanRequest,Long userId) throws LoansException;
	
	public FinalWorkingCapitalLoanRequest get(Long id,Long applicationId) throws LoansException;
	
	
}
