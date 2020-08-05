package com.opl.mudra.api.matchengine.model;

import java.util.List;

public class ProposalStatusList {

	private Long status;
	
	private List<Long> proposalIds;

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public List<Long> getProposalIds() {
		return proposalIds;
	}

	public void setProposalIds(List<Long> proposalIds) {
		this.proposalIds = proposalIds;
	}
	
	
}
