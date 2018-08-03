/**
 * 
 */
package com.capitaworld.service.loans.service.teaser.finalview;

import com.capitaworld.service.loans.model.teaser.finalview.CorporateFinalViewResponse;
import com.capitaworld.service.loans.model.teaser.finalview.NtbFinalViewResponse;

/**
 * @author nilay
 *
 */
public interface NtbFinalViewService {
	
	public NtbFinalViewResponse getNtbFinalViewDetails(Long toApplicationId, Integer userType, Long fundProviderUserId);

}
