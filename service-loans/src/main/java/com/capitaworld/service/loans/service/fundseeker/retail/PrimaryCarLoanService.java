package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.model.retail.PrimaryCarLoanDetailRequest;

public interface PrimaryCarLoanService {

	public boolean saveOrUpdate(PrimaryCarLoanDetailRequest carLoanDetailRequest, Long userId) throws Exception;

	public PrimaryCarLoanDetailRequest get(Long id, Long userId) throws Exception;

}
