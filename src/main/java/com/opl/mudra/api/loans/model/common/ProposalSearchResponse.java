package com.opl.mudra.api.loans.model.common;

import java.util.Date;

public class ProposalSearchResponse {

	private Long applicationId;
	private Long proposalId;
	private Long fpProductId;
	private String applicationCode;
	private String orgName;
	private Double elAmount;
	private String productName;
	private Date createdDate;
	private String branchName;
	private String branchCode;
	private Integer businessTypeId;
	private Long proposalStatusId;
	private Integer productId;
	private String applicantName;
	private Boolean isSactionedFromOther;

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Long getProposalId() {
		return proposalId;
	}
	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}
	public Long getFpProductId() {
		return fpProductId;
	}
	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
	}
	public String getApplicationCode() {
		return applicationCode;
	}
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public Double getElAmount() {
		return elAmount;
	}
	public void setElAmount(Double elAmount) {
		this.elAmount = elAmount;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public Integer getBusinessTypeId() {
		return businessTypeId;
	}
	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}
	public Long getProposalStatusId() {
		return proposalStatusId;
	}
	public void setProposalStatusId(Long proposalStatusId) {
		this.proposalStatusId = proposalStatusId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Boolean getIsSactionedFromOther() {
		return isSactionedFromOther;
	}

	public void setIsSactionedFromOther(Boolean isSactionedFromOther) {
		this.isSactionedFromOther = isSactionedFromOther;
	}
	
	
}
