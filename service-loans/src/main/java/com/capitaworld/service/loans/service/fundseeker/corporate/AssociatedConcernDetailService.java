package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.AssociatedConcernDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;

/**
 * @author Sanket
 *
 */
public interface AssociatedConcernDetailService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public List<AssociatedConcernDetailRequest> getAssociatedConcernsDetailList(Long id,Long userId) throws LoansException;
	
	public Boolean saveOrUpdate(List<AssociatedConcernDetailRequest> requests,Long applicationId,Long userId);

}
