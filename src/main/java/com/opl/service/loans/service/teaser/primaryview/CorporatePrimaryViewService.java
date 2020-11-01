package com.opl.service.loans.service.teaser.primaryview;

import java.util.LinkedHashMap;
import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.teaser.primaryview.CorporatePrimaryViewResponse;
import com.opl.mudra.api.rating.model.FinancialInputRequest;

public interface CorporatePrimaryViewService {

    public CorporatePrimaryViewResponse getCorporatePrimaryViewDetails(Long applicationId,Long proposalId, Integer userType, Long fundProviderUserId);
    
    public LinkedHashMap<String,Object> gstVsItrVsBsComparision(Long applicationId,FinancialInputRequest financialInputRequest , Long gstMasterId , Long itrMasterId , Long bsMasterId);
    
    public com.opl.mudra.api.loans.model.LoansResponse getCubictreeReport(Long applicationId) throws LoansException;
    
    public List<Long> getAllStorageIds(Long profileId, Long applicationId);
}
