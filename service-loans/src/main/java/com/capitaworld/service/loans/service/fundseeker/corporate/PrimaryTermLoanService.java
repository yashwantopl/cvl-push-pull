package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.PrimaryTermLoanRequest;

public interface PrimaryTermLoanService {

	public boolean saveOrUpdate(PrimaryTermLoanRequest termLoanRequest) throws Exception;

	public PrimaryTermLoanRequest get(Long id) throws Exception;
}
