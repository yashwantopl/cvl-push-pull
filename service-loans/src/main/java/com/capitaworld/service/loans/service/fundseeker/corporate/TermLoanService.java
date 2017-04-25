package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.TermLoanRequest;

public interface TermLoanService {

	public boolean saveOrUpdate(TermLoanRequest termLoanRequest);

	public TermLoanRequest getTermLoan(Long id);
}
