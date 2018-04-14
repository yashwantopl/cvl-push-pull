package com.capitaworld.service.loans.service.teaser.primaryview;

import com.capitaworld.service.loans.model.teaser.primaryview.CorporatePrimaryViewResponse;

public interface CorporatePrimaryViewService {

    public CorporatePrimaryViewResponse getCorporatePrimaryViewDetails(Long toApplicationId, Integer userType, Long fundProviderUserId);

}
