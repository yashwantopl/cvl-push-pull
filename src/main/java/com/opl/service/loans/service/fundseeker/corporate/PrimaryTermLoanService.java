package com.opl.service.loans.service.fundseeker.corporate;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.corporate.PrimaryTermLoanRequest;

public interface PrimaryTermLoanService {

	public boolean saveOrUpdate(PrimaryTermLoanRequest termLoanRequest, Long userId) throws LoansException;

	public PrimaryTermLoanRequest get(Long applicationId, Long userId) throws LoansException;
	
}
