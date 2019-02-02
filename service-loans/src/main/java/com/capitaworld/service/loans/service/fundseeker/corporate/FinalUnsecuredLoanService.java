package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.corporate.FinalUnsecuredLoanRequest;

public interface FinalUnsecuredLoanService {

	public boolean saveOrUpdate(FinalUnsecuredLoanRequest unsecuredLoanRequest, Long userId) throws LoansException;

	public FinalUnsecuredLoanRequest get(Long userId, Long applicationId) throws LoansException;
}
