package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.ExistingProductDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;

/**
 * @author Sanket
 *
 */
public interface ExistingProductDetailsService {

	Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;
	
	List<ExistingProductDetailRequest> getExistingProductDetailList(Long applicationId, Long userId) throws LoansException;

	List<ExistingProductDetailRequest> getExistingProductDetailListByProposalId(Long proposalId, Long userId) throws Exception;

}
