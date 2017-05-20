package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.AssociatedConcernDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;

/**
 * @author Sanket
 *
 */
public interface AssociatedConcernDetailService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception;

	public List<AssociatedConcernDetailRequest> getAssociatedConcernsDetailList(Long id,Long userId) throws Exception;

}
