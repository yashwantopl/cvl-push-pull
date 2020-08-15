package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.PromotorBackgroundDetailRequest;

/**
 * @author Sanket
 *
 */
public interface PromotorBackgroundDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	List<PromotorBackgroundDetailRequest> getPromotorBackgroundDetailList(Long applicationId,Long userId) throws LoansException;

	public List<PromotorBackgroundDetailRequest> getPromotorBackgroundDetailListByProposalId(Long applicationId,Long proposalId,Long userId) throws Exception;

}
