package com.capitaworld.service.loans.service.teaser.finalview;

import com.capitaworld.service.loans.model.teaser.finalview.CorporateFinalViewResponse;

public interface CorporateFinalViewService {
	
	public CorporateFinalViewResponse getCorporateFinalViewDetails(Long toApplicationId,Long proposalMapId, Integer userType, Long fundProviderUserId);
}
