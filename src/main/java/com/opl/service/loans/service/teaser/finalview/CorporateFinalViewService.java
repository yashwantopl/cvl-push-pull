package com.opl.service.loans.service.teaser.finalview;

import com.opl.mudra.api.loans.model.teaser.finalview.CorporateFinalViewResponse;

public interface CorporateFinalViewService {
	
	public CorporateFinalViewResponse getCorporateFinalViewDetails(Long toApplicationId,Long proposalMapId, Integer userType, Long fundProviderUserId);
}
