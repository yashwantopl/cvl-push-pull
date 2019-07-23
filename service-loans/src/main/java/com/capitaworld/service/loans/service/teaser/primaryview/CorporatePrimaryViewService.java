package com.capitaworld.service.loans.service.teaser.primaryview;

import java.util.LinkedHashMap;

import com.capitaworld.service.loans.model.teaser.primaryview.CorporatePrimaryViewResponse;
import com.capitaworld.service.rating.model.FinancialInputRequest;

public interface CorporatePrimaryViewService {

    public CorporatePrimaryViewResponse getCorporatePrimaryViewDetails(Long applicationId,Long proposalId, Integer userType, Long fundProviderUserId);
    
    //public LinkedHashMap<String,Object> gstVsItrVsBsComparision(Long applicationId,FinancialInputRequest financialInputRequest);

}
