package com.opl.service.loans.service.fundseeker.corporate;

import org.json.simple.JSONObject;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.service.loans.domain.fundseeker.ApplicationProposalMapping;

public interface ApplicationProposalMappingService {

    Boolean saveOrUpdate();
    
    ApplicationProposalMapping getApplicationProposalMappingByProposalId(Long proposalId);
    
    Boolean isFinalLocked(Long proposalId) throws Exception;
    
    JSONObject getSelfViewAndPrimaryLocked(Long proposalId, Long userId) throws Exception;
    
    Boolean isPrimaryLockedByProposalId(Long proposalId, Long userId) throws Exception;
    
    public JSONObject getCurrencyAndDenomination(Long applicationId, Long proposalId, Long userId) throws LoansException;

    public JSONObject getCurrencyAndDenomination(Long proposalId) throws LoansException;

}
