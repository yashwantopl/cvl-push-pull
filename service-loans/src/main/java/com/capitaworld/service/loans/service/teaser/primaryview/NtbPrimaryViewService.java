/**
 * 
 */
package com.capitaworld.service.loans.service.teaser.primaryview;

import com.capitaworld.service.loans.model.teaser.primaryview.NtbPrimaryViewResponse;

/**
 * @author nilay
 *
 */
public interface NtbPrimaryViewService {
	
	 public NtbPrimaryViewResponse getNtbPrimaryViewDetails(Long toApplicationId, Integer userType, Long fundProviderUserId);

}
