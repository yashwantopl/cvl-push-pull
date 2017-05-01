package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.model.PrimaryPersonalLoanRequest;

public interface PrimaryPersonalLoanService {

	public boolean saveOrUpdate(PrimaryPersonalLoanRequest primaryPersonalLoanRequest);
	
	public PrimaryPersonalLoanRequest get(Long id);
	
	
}
