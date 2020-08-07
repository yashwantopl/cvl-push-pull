package com.opl.service.loans.service.teaser.primaryview;

import java.util.LinkedHashMap;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.teaser.primaryview.CorporatePrimaryViewResponse;
import com.opl.mudra.api.rating.model.FinancialInputRequest;

public interface CorporatePrimaryViewService {

    public CorporatePrimaryViewResponse getCorporatePrimaryViewDetails(Long applicationId,Long proposalId, Integer userType, Long fundProviderUserId);
    
    public LinkedHashMap<String,Object> gstVsItrVsBsComparision(Long applicationId,FinancialInputRequest financialInputRequest);
    
    public com.opl.mudra.api.loans.model.LoansResponse getCubictreeReport(Long applicationId) throws LoansException;
}
