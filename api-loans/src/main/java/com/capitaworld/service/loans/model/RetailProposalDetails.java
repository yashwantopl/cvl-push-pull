package com.capitaworld.service.loans.model;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author nilay.darji
 *
 */
public class RetailProposalDetails {

	private String name;
	
	private String imagePath;
	
	private Long applicationId;
	
	private Long proposalMappingId;
	
	private int fsType;
	
	private String address;
	
	private String amount;
	
	private String cibilSCore;

	private String branchLocationName;
	private String branchCity;
	private String branchState;
	private Integer businessTypeId;
	
	private Long fpProductid;
	
	private Integer productId;
	
	private Date lastStatusActionDate;
	
	private Long proposalStatus;
	 

	public String getBranchLocationName() {
		return branchLocationName;
	}

	public void setBranchLocationName(String branchLocationName) {
		this.branchLocationName = branchLocationName;
	}

	public String getBranchCity() {
		return branchCity;
	}

	public void setBranchCity(String branchCity) {
		this.branchCity = branchCity;
	}

	public String getBranchState() {
		return branchState;
	}

	public void setBranchState(String branchState) {
		this.branchState = branchState;
	}

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

	public String getCibilSCore() {
		return cibilSCore;
	}

	public void setCibilSCore(String cibilSCore) {
		this.cibilSCore = cibilSCore;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public Long getFpProductid() {
		return fpProductid;
	}

	public void setFpProductid(Long fpProductid) {
		this.fpProductid = fpProductid;
	}

	public Date getLastStatusActionDate() {
		return lastStatusActionDate;
	}

	public void setLastStatusActionDate(Date lastStatusActionDate) {
		this.lastStatusActionDate = lastStatusActionDate;
	}

	public Long getProposalStatus() {
		return proposalStatus;
	}

	public void setProposalStatus(Long proposalStatus) {
		this.proposalStatus = proposalStatus;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	  
	
}
