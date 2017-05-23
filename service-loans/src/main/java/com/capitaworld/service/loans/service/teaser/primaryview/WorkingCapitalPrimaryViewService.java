package com.capitaworld.service.loans.service.teaser.primaryview;

import com.capitaworld.service.loans.model.teaser.primaryview.WorkingCapitalPrimaryViewResponse;

/**
 * Created by dhaval on 19-May-17.
 */
public interface WorkingCapitalPrimaryViewService {

    public WorkingCapitalPrimaryViewResponse getWorkingCapitalPrimaryViewDetails(Long toApplicationId,Long userId);

    public boolean validateWorkingCapitalPrimaryViewRequest(String toApplicationId);
}
