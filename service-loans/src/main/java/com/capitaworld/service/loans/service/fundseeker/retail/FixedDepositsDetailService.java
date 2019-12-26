package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.FixedDepositsDetailsRequest;

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
