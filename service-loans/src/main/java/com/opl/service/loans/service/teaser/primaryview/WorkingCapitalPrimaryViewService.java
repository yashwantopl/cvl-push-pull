package com.opl.service.loans.service.teaser.primaryview;

import com.opl.mudra.api.loans.model.teaser.primaryview.WorkingCapitalPrimaryViewResponse;

/**
 * Created by dhaval on 19-May-17.
 */
public interface WorkingCapitalPrimaryViewService {
    public WorkingCapitalPrimaryViewResponse getWorkingCapitalPrimaryViewDetails(Long toApplicationId,Integer userType,Long fundProviderUserId);
}
