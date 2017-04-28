package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.FinalTermLoanRequest;

public interface FinalTermLoanService {

	public boolean saveOrUpdate(FinalTermLoanRequest termLoanRequest);

	public FinalTermLoanRequest get(Long id);
}
