package com.capitaworld.service.loans.service.teaser.primaryview;

import com.capitaworld.service.loans.model.teaser.primaryview.CarLoanPrimaryViewResponse;

/**
 * Created by dhaval on 23-May-17.
 */

public interface CarLoanPrimaryViewService {
    public CarLoanPrimaryViewResponse getCarLoanPrimaryViewDetails(Long toApplicationId);
}
