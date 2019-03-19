package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.exceptions.LoansException;
import org.json.simple.JSONObject;

public interface ApplicationProposalMappingService {

    Boolean saveOrUpdate();
    
    ApplicationProposalMapping getApplicationProposalMappingByProposalId(Long proposalId);

    public JSONObject getCurrencyAndDenomination(Long applicationId, Long proposalId, Long userId) throws LoansException;

    public JSONObject getCurrencyAndDenomination(Long proposalId) throws LoansException;
}
