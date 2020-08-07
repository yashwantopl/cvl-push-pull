package com.opl.service.loans.service.fundseeker.retail;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.retail.OtherPropertyDetailsRequest;

/**
 * @author Sanket
 *
 */
public interface OtherPropertyDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest,int type) throws LoansException;

	public List<OtherPropertyDetailsRequest> getPropertyDetailListByProposalId(Long proposalId, int applicationType) throws LoansException;
}
