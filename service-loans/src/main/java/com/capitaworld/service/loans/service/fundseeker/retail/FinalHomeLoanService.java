package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.retail.FinalHomeLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.FinalHomeLoanDetailRequestOld;

public interface FinalHomeLoanService {

	public boolean saveOrUpdate(FinalHomeLoanDetailRequest finalHomeLoanDetailRequest, Long userId) throws LoansException;

	public boolean saveOrUpdateNew(FinalHomeLoanDetailRequest finalHomeLoanDetailRequest, Long userId) throws LoansException;

	public FinalHomeLoanDetailRequest get(Long id, Long userId, Long proposalId) throws LoansException;
}
