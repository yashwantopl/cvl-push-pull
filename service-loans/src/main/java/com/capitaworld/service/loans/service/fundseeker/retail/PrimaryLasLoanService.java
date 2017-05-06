package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.model.retail.PrimaryLasLoanDetailRequest;

public interface PrimaryLasLoanService {

	public boolean saveOrUpdate(PrimaryLasLoanDetailRequest lasLoanDetailRequest);
	
	public PrimaryLasLoanDetailRequest get(Long id);
	
	
}
