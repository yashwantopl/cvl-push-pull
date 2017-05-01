package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.model.PrimaryLapLoanDetailRequest;

public interface PrimaryLapLoanService {

	public boolean saveOrUpdate(PrimaryLapLoanDetailRequest lapLoanDetailRequest);
	
	public PrimaryLapLoanDetailRequest get(Long id);
	
	
}
