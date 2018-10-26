/**
 * 
 */
package com.capitaworld.service.loans.service.teaser.primaryview;

import com.capitaworld.service.loans.model.teaser.primaryview.PlTeaserViewResponse;

/**
 * @author nilay.darji
 *
 */
public interface PlTeaserViewService {
	
	public PlTeaserViewResponse getPlPrimaryViewDetails(Long toApplicationId, Integer userType, Long userId, Long productMappingId, Boolean isFinal);

}
