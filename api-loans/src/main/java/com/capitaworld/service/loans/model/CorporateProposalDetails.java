package com.capitaworld.service.loans.model;

import java.util.Date;

public class CorporateProposalDetails {

	private String name;
	
	private String fsMainType;

	private String industry;
	
	private String amount;
	
	private String imagePath;
	
	private Long applicationId;
	
	private Long proposalMappingId;
	
	private int fsType;
	
	private String address;
	
	private String assignBy;
	
	private String assignbranch;
	
	private Date assignDate;
	
	private Date lastStatusActionDate;
	
	private Boolean isAssignedToBranch;
	

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

	public String getAssignBy() {
		return assignBy;
	}

	public void setAssignBy(String assignBy) {
		this.assignBy = assignBy;
	}

	public String getAssignbranch() {
		return assignbranch;
	}

	public void setAssignbranch(String assignbranch) {
		this.assignbranch = assignbranch;
	}

	public Date getAssignDate() {
		return assignDate;
	}

	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}

	public Boolean getIsAssignedToBranch() {
		return isAssignedToBranch;
	}

	public void setIsAssignedToBranch(Boolean isAssignedToBranch) {
		this.isAssignedToBranch = isAssignedToBranch;
	}

	public Date getLastStatusActionDate() {
		return lastStatusActionDate;
	}

	public void setLastStatusActionDate(Date lastStatusActionDate) {
		this.lastStatusActionDate = lastStatusActionDate;
	}
	
	
	
		
}
