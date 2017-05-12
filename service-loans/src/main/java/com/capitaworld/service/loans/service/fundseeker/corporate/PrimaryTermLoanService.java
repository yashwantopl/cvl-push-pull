package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.corporate.PrimaryTermLoanRequest;

public interface PrimaryTermLoanService {

	public boolean saveOrUpdate(PrimaryTermLoanRequest termLoanRequest, Long userId) throws Exception;

	public PrimaryTermLoanRequest get(Long id) throws Exception;
}
