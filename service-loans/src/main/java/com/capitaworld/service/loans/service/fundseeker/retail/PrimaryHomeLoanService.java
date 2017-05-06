package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.model.retail.PrimaryHomeLoanDetailRequest;

public interface PrimaryHomeLoanService {

	public boolean saveOrUpdate(PrimaryHomeLoanDetailRequest  primaryHomeLoanDetailRequest);
	
	public PrimaryHomeLoanDetailRequest get(Long id);
	
	
}
