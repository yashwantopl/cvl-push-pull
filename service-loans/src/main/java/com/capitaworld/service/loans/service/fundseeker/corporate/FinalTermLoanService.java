package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.FinalTermLoanRequest;

public interface FinalTermLoanService {

	public boolean saveOrUpdate(FinalTermLoanRequest termLoanRequest) throws Exception;

	public FinalTermLoanRequest get(Long id,Long applicationId) throws Exception;
}
