package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.retail.FinalAutoLoanCoApplicantDetailRequest;

public interface FinalAutoLoanCoAppService {

	public boolean saveOrUpdate(FinalAutoLoanCoApplicantDetailRequest finalAutoLoanDetailRequest, Long userId) throws LoansException;

	public FinalAutoLoanCoApplicantDetailRequest get(Long id,Long applicationId, Long userId, Long proposalId) throws LoansException;
	
}
