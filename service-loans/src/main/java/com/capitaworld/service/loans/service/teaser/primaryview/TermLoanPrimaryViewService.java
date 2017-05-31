package com.capitaworld.service.loans.service.teaser.primaryview;

import com.capitaworld.service.loans.model.teaser.primaryview.TermLoanPrimaryViewResponse;


public interface TermLoanPrimaryViewService {

    public TermLoanPrimaryViewResponse getTermLoanPrimaryViewDetails(Long toApplicationId,Integer userType,Long fundProviderUserId);

   
}
