package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.corporate.FinalTermLoanRequest;

public interface FinalTermLoanService {

	public boolean saveOrUpdate(FinalTermLoanRequest termLoanRequest, Long userId) throws Exception;

	public FinalTermLoanRequest get(Long userId, Long applicationId) throws Exception;
}
