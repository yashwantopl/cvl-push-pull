package com.opl.service.loans.service.fundseeker.corporate;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.corporate.PrimaryUnsecureLoanRequest;

public interface PrimaryUnsecureLoanService {

	public boolean saveOrUpdate(PrimaryUnsecureLoanRequest primaryUnsecureLoanRequest, Long userId) throws LoansException;

	public PrimaryUnsecureLoanRequest get(Long applicationId, Long userId) throws LoansException;
}
