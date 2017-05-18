package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.model.retail.FinalCarLoanDetailRequest;

public interface FinalCarLoanService {

	public boolean saveOrUpdate(FinalCarLoanDetailRequest finalCarLoanDetailRequest );

	public FinalCarLoanDetailRequest get(Long id);
}
