package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.model.PrimaryCarLoanDetailRequest;

public interface PrimaryCarLoanService {

	public boolean saveOrUpdate(PrimaryCarLoanDetailRequest carLoanDetailRequest);
	
	public PrimaryCarLoanDetailRequest get(Long id);
	
	
}
