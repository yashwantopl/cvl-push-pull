package com.opl.service.loans.service.teaser.primaryview;

import com.opl.mudra.api.loans.model.teaser.primaryview.TermLoanPrimaryViewResponse;


public interface TermLoanPrimaryViewService {

    public TermLoanPrimaryViewResponse getTermLoanPrimaryViewDetails(Long toApplicationId,Integer userType,Long fundProviderUserId);

   
}
