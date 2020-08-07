package com.opl.service.loans.service.fundseeker.corporate;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.corporate.FinalUnsecuredLoanRequest;

public interface FinalUnsecuredLoanService {

	public boolean saveOrUpdate(FinalUnsecuredLoanRequest unsecuredLoanRequest, Long userId) throws LoansException;

	public FinalUnsecuredLoanRequest get(Long userId, Long applicationId) throws LoansException;
}
