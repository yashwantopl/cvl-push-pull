package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.retail.FinalAutoLoanDetailRequest;

public interface FinalAutoLoanService {
	
	public boolean saveOrUpdate(FinalAutoLoanDetailRequest finalAutoLoanDetailRequest, Long userId) throws LoansException;
	
	public FinalAutoLoanDetailRequest get(Long applicationId, Long userId , Long proposalId) throws LoansException;
}
