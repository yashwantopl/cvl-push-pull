/**
 * 
 */
package com.capitaworld.service.loans.service.teaser.primaryview;

import com.capitaworld.service.loans.model.teaser.primaryview.HlTeaserViewResponse;

/**
 * @author nilay.darji
 *
 */
public interface HlTeaserViewService {
	
	public HlTeaserViewResponse getHlTeaserView(Long toApplicationId, Integer userType, Long userId, Long productMappingId, Boolean isFinal, Long proposalId);

}
