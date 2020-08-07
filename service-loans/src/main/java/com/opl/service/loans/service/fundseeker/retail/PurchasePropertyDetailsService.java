package com.opl.service.loans.service.fundseeker.retail;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.retail.PurchasePropertyDetailsRequest;

/**
 * @author Sanket
 *
 */
public interface PurchasePropertyDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public List<PurchasePropertyDetailsRequest> getPropertyDetailListByProposalId(Long proposalId, int applicationType) throws LoansException;
}
