package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.model.retail.PrimaryPersonalLoanRequest;

public interface PrimaryPersonalLoanService {

	public boolean saveOrUpdate(PrimaryPersonalLoanRequest primaryPersonalLoanRequest,Long userId) throws Exception;
	
	public PrimaryPersonalLoanRequest get(Long applicationId,Long userId) throws Exception;
	
	
}
