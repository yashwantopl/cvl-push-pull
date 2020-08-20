package com.opl.service.loans.service.fundseeker.retail;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.retail.FixedDepositsDetailsRequest;

/**
 * @author Sanket
 *
 */
public interface FixedDepositsDetailService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public Boolean saveOrUpdateCoApplicant(FrameRequest frameRequest) throws LoansException;

	public List<FixedDepositsDetailsRequest> getFixedDepositsDetailList(Long id, int applicationType) throws LoansException;
	
	public List<FixedDepositsDetailsRequest> getFixedDepositsDetailByProposalId(Long proposalId, int applicationType) throws LoansException;

	public List<FixedDepositsDetailsRequest> getFixedDepositsDetailByProposalIdAndCoAppId(Long proposalId, Long coAppId) throws LoansException;

}
