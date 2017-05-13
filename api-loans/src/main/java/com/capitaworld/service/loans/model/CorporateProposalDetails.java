package com.capitaworld.service.loans.model;

public class CorporateProposalDetails {

	private String name;
	
	private String fsMainType;

	private String industry;
	
	private String amount;
	
	private String imagePath;
	
	private Long applicationId;
	
	private Long proposalMappingId;
	
	private int fsType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFsMainType() {
		return fsMainType;
	}

	public void setFsMainType(String fsMainType) {
		this.fsMainType = fsMainType;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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
