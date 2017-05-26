package com.capitaworld.service.loans.service.teaser.primaryview;

import com.capitaworld.service.loans.model.teaser.primaryview.WorkingCapitalPrimaryViewResponse;

/**
 * Created by dhaval on 25-May-17.
 */
public interface WorkingCapitalFinalService {
    public WorkingCapitalPrimaryViewResponse getWorkingCapitalFinalViewDetails(Long toApplicationId,Long userId);
}
