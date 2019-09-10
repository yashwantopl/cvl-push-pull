package com.capitaworld.service.loans.service.teaser.primaryview;

import com.capitaworld.service.loans.model.teaser.primaryview.AlTeaserViewResponse;

/**
 * @author rohit.chaudhary
 *
 */
public interface AlTeaserViewService {
	
	public AlTeaserViewResponse getAlTeaserView(Long toApplicationId, Integer userType, Long userId, Long productMappingId, Boolean isFinal, Long proposalId);
}
