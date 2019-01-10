package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.corporate.PrimaryTermLoanRequest;

public interface PrimaryTermLoanService {

	public boolean saveOrUpdate(PrimaryTermLoanRequest termLoanRequest, Long userId) throws LoansException;

	public PrimaryTermLoanRequest get(Long applicationId, Long userId) throws LoansException;
	
}
