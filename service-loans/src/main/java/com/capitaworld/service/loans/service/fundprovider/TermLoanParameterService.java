package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.model.TermLoanParameterRequest;

public interface TermLoanParameterService {
	public boolean saveOrUpdate(TermLoanParameterRequest termLoanParameterRequest);
	
	public TermLoanParameterRequest getTermLoanParameterRequest(Long id);
}
