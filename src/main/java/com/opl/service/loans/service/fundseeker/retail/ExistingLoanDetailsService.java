package com.opl.service.loans.service.fundseeker.retail;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.retail.ExistingLoanDetailRequest;

/**
 * @author Sanket
 *
 */
public interface ExistingLoanDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public List<ExistingLoanDetailRequest> getExistingLoanDetailList(Long id, int applicationType) throws LoansException;
	
	public Boolean saveOrUpdateFromCibil(List<ExistingLoanDetailRequest> existingLoanDetail,Long applicationId,Long userId,int applicantType) throws LoansException;

}
