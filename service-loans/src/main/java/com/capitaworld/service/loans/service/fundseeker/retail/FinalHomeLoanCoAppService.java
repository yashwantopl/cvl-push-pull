package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.retail.FinalHomeLoanCoApplicantDetailRequest;
import com.capitaworld.service.loans.model.retail.FinalHomeLoanDetailRequest;

public interface FinalHomeLoanCoAppService {

	public boolean saveOrUpdate(FinalHomeLoanCoApplicantDetailRequest finalHomeLoanDetailRequest, Long userId) throws LoansException;

	public FinalHomeLoanCoApplicantDetailRequest get(Long id,Long applicationId, Long userId, Long proposalId) throws LoansException;
}
