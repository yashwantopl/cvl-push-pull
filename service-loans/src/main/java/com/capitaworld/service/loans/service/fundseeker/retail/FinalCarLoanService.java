package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.retail.FinalCarLoanDetailRequest;

public interface FinalCarLoanService {

	public boolean saveOrUpdate(FinalCarLoanDetailRequest finalCarLoanDetailRequest, Long userId) throws LoansException;

	public FinalCarLoanDetailRequest get(Long applicationId, Long userId) throws LoansException;
}
