package com.capitaworld.service.loans.service.teaser.finalview;

        import com.capitaworld.service.loans.model.teaser.finalview.WorkingCapitalFinalViewResponse;

/**
 * Created by dhaval on 25-May-17.
 */
public interface WorkingCapitalFinalService {
    public WorkingCapitalFinalViewResponse getWorkingCapitalFinalViewDetails(Long toApplicationId);
}
