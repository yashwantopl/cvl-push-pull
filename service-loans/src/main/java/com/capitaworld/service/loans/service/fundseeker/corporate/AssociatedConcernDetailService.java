package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.AssociatedConcernDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;

/**
 * @author Sanket
 *
 */
public interface AssociatedConcernDetailService {

	public Boolean saveOrUpdate(FrameRequest frameRequest);

	public List<AssociatedConcernDetailRequest> getAssociatedConcernsDetailList(Long id);

}
