package com.capitaworld.service.loans.service;

import java.util.List;

import com.capitaworld.service.loans.model.FundProviderProposalDetails;
import com.capitaworld.service.matchengine.model.ProposalCountResponse;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;

public interface ProposalService {

	public List<?>  fundproviderProposal(ProposalMappingRequest request);
	
	public List<FundProviderProposalDetails>  fundseekerProposal(ProposalMappingRequest request,Long userId);
	
	public ProposalCountResponse fundProviderProposalCount(ProposalMappingRequest request);
	
	public ProposalCountResponse fundSeekerProposalCount(ProposalMappingRequest request);
}
