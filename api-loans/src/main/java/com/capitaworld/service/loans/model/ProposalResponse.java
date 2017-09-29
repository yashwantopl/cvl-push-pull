package com.capitaworld.service.loans.model;

import java.util.List;

public class ProposalResponse {
	
	List proposalByMatches;
	
	List proposalWithoutMatches;

	public List getProposalByMatches() {
		return proposalByMatches;
	}

	public void setProposalByMatches(List proposalByMatches) {
		this.proposalByMatches = proposalByMatches;
	}

	public List getProposalWithoutMatches() {
		return proposalWithoutMatches;
	}

	public void setProposalWithoutMatches(List proposalWithoutMatches) {
		this.proposalWithoutMatches = proposalWithoutMatches;
	}
	
	

}
