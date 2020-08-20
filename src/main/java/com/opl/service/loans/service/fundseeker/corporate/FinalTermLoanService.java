package com.opl.service.loans.service.fundseeker.corporate;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.corporate.FinalTermLoanRequest;

public interface FinalTermLoanService {

	public boolean saveOrUpdate(FinalTermLoanRequest termLoanRequest, Long userId) throws LoansException;

	public FinalTermLoanRequest get(Long userId, Long applicationId) throws LoansException;
}
