/**
 * 
 */
package com.capitaworld.service.loans.service.teaser.primaryview;

import com.capitaworld.service.loans.model.teaser.primaryview.NtbPrimaryViewResponse;

/**
 * @author nilay
 *
 */
public interface NtbTeaserViewService {
	
	 public NtbPrimaryViewResponse getNtbTeaserViewDetails(Long toApplicationId, Integer userType, Long userId, Long productMappingId, Boolean isFinal);

}
