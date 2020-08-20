package com.opl.service.loans.service.teaser.finalview;

import com.opl.mudra.api.loans.model.teaser.finalview.TermLoanFinalViewResponse;

/**
 * Created by dhaval on 27-May-17.
 */
public interface TermLoanFinalViewService {
    public TermLoanFinalViewResponse getTermLoanFinalViewDetails(Long toApplicationId);
}
