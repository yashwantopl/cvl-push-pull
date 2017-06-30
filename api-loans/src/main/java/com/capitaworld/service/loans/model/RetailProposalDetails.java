package com.capitaworld.service.loans.model;

import java.util.Collections;
import java.util.List;

public class RetailProposalDetails {

	private String name;
	
	private String imagePath;
	
	private Long applicationId;
	
	private Long proposalMappingId;
	
	private int fsType;
	
	private String address;
	
	private String amount;


	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	private List<?> listMatches = Collections.emptyList();
	
	public List<?> getListMatches() {
		return listMatches;
	}

	public void setListMatches(List<?> listMatches) {
		this.listMatches = listMatches;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getProposalMappingId() {
		return proposalMappingId;
	}

	public void setProposalMappingId(Long proposalMappingId) {
		this.proposalMappingId = proposalMappingId;
	}

	public int getFsType() {
		return fsType;
	}

	public void setFsType(int fsType) {
		this.fsType = fsType;
	}
	
	
}
