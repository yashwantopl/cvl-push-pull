package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.OwnershipDetailRequest;

/**
 * @author Sanket
 *
 */
public interface OwnershipDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public List<OwnershipDetailRequest> getOwnershipDetailList(Long applicationId,Long userId) throws LoansException;
	public List<OwnershipDetailRequest> getOwnershipDetailList(Long applicationId,Long userId,Long proposalId) throws Exception;

	/*multiple bank*/
	public List<OwnershipDetailRequest> getOwnershipDetailListForMultipleBank(Long proposalId) throws Exception;
}
