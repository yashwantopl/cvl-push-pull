package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.retail.PrimaryLapLoanDetailRequest;

public interface PrimaryLapLoanService {

	public boolean saveOrUpdate(PrimaryLapLoanDetailRequest lapLoanDetailRequest,Long userId) throws LoansException;
	
	public PrimaryLapLoanDetailRequest get(Long id,Long userId) throws LoansException;
	
	
}
