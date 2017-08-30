package com.capitaworld.service.loans.model.common;

import java.io.Serializable;

public class ChatDetails implements Serializable{
	
	Long proposalId;
	
	Long appAndFpMappingId;
	
	String name;
	
	String profile;

	public Long getProposalId() {
		return proposalId;
	}

	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}

	public Long getAppAndFpMappingId() {
		return appAndFpMappingId;
	}

	public void setAppAndFpMappingId(Long appAndFpMappingId) {
		this.appAndFpMappingId = appAndFpMappingId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	

}
