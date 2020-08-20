package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.AssociatedConcernDetailRequest;
import com.opl.mudra.api.loans.model.FrameRequest;

/**
 * @author Sanket
 *
 */
public interface AssociatedConcernDetailService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public List<AssociatedConcernDetailRequest> getAssociatedConcernsDetailList(Long id,Long userId) throws LoansException;

	public Boolean saveOrUpdate(List<AssociatedConcernDetailRequest> requests,Long applicationId,Long userId);

	public List<AssociatedConcernDetailRequest> getAssociatedConcernsDetailListByProposalId(Long id,Long userId) throws Exception;

}
