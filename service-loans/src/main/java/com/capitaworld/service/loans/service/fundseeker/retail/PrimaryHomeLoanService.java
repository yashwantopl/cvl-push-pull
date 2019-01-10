package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.retail.PrimaryHomeLoanDetailRequest;

public interface PrimaryHomeLoanService {

	public boolean saveOrUpdate(PrimaryHomeLoanDetailRequest homeLoanDetailRequest,Long userId) throws LoansException;
	
	public PrimaryHomeLoanDetailRequest get(Long id,Long userId) throws LoansException;
	
	
}
