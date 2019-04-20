package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.OwnershipDetailRequest;

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
