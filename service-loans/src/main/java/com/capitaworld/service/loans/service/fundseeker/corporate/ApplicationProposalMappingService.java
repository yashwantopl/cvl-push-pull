package com.capitaworld.service.loans.service.fundseeker.corporate;

import org.json.simple.JSONObject;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;

public interface ApplicationProposalMappingService {

    Boolean saveOrUpdate();
    
    ApplicationProposalMapping getApplicationProposalMappingByProposalId(Long proposalId);
    
    Boolean isFinalLocked(Long proposalId) throws Exception;
    
    JSONObject getSelfViewAndPrimaryLocked(Long proposalId, Long userId) throws Exception;
    
    Boolean isPrimaryLockedByProposalId(Long proposalId, Long userId) throws Exception;
    
}
