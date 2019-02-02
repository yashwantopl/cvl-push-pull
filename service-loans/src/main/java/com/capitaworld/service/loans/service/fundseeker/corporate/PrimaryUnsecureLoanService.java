package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.corporate.PrimaryUnsecureLoanRequest;

public interface PrimaryUnsecureLoanService {

	public boolean saveOrUpdate(PrimaryUnsecureLoanRequest primaryUnsecureLoanRequest, Long userId) throws LoansException;

	public PrimaryUnsecureLoanRequest get(Long applicationId, Long userId) throws LoansException;
}
