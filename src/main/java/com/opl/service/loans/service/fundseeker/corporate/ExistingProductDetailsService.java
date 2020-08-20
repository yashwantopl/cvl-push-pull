package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.ExistingProductDetailRequest;
import com.opl.mudra.api.loans.model.FrameRequest;

/**
 * @author Sanket
 *
 */
public interface ExistingProductDetailsService {

	Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;
	
	List<ExistingProductDetailRequest> getExistingProductDetailList(Long applicationId, Long userId) throws LoansException;

	List<ExistingProductDetailRequest> getExistingProductDetailListByProposalId(Long proposalId, Long userId) throws LoansException;

}
