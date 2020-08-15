package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.ProposedProductDetailRequest;

/**
 * @author Sanket
 *
 */
public interface ProposedProductDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public List<ProposedProductDetailRequest> getProposedProductDetailList(Long proposalId,Long userId) throws Exception;

	public List<ProposedProductDetailRequest> getProposedProductDetailListFromProposalId(Long proposalId,Long userId) throws LoansException;

}
