package com.opl.service.loans.service.fundseeker.retail;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.retail.BankAccountHeldDetailsRequest;

/**
 * @author Sanket
 *
 */
public interface BankAccountHeldDetailService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public Boolean saveOrUpdateCoAppDetails(FrameRequest frameRequest) throws LoansException;

	public List<BankAccountHeldDetailsRequest> getExistingLoanDetailList(Long id, int applicationType) throws LoansException;
	
	public List<BankAccountHeldDetailsRequest> getExistingLoanDetailListByProposalId(Long proposalId, int applicationType) throws LoansException;

	public List<BankAccountHeldDetailsRequest> getExistingLoanDetailListByProposalIdCoAppId(Long proposalId, Long coAppId) throws LoansException;

}
