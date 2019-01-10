package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;

public interface ApplicationProposalMappingService {

    Boolean saveOrUpdate();
    
    ApplicationProposalMapping getApplicationProposalMappingByProposalId(Long proposalId);
    
}
