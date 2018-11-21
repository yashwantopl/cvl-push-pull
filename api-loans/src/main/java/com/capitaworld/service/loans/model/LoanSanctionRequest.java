package com.capitaworld.service.loans.model;

import java.io.Serializable;

import java.util.Date;

import com.capitaworld.service.loans.model.common.AuditActivityRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoanSanctionRequest extends AuditActivityRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String accountNo;
	private Double sanctionAmount;
	private String transactionNo;
	private Double roi;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date sanctionDate;
	
	private Long branch;
	private Long applicationId;
	private Long referenceNo;
	private Double tenure;
	private String sanctionAuthority;
	private String remark;
	private String userName;
	private String password;
	private String actionBy;
	private Double processingFee;
	private Long orgId;

	private Long proposalId;
	private Long proposalStatusId;
	private Boolean isSaved = false;
	private String reason;
	private String statusCode;
	private Boolean isIneligibleProposal;
	private Long isSanctionedFrom;
	private Boolean isPartiallyDisbursedOffline;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Double getSanctionAmount() {
		return sanctionAmount;
	}

	public void setSanctionAmount(Double sanctionAmount) {
		this.sanctionAmount = sanctionAmount;
	}

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}

	public Double getRoi() {
		return roi;
	}

	public void setRoi(Double roi) {
		this.roi = roi;
	}

	public Date getSanctionDate() {
		return sanctionDate;
	}

	public void setSanctionDate(Date sanctionDate) {
		this.sanctionDate = sanctionDate;
	}

	public Long getBranch() {
		return branch;
	}

	public void setBranch(Long branch) {
		this.branch = branch;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(Long referenceNo) {
		this.referenceNo = referenceNo;
	}

	public Double getTenure() {
		return tenure;
	}

	public void setTenure(Double tenure) {
		this.tenure = tenure;
	}

	public String getSanctionAuthority() {
		return sanctionAuthority;
	}

	public void setSanctionAuthority(String sanctionAuthority) {
		this.sanctionAuthority = sanctionAuthority;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remarks) {
		this.remark = remarks;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getActionBy() {
		return actionBy;
	}

	public void setActionBy(String actionBy) {
		this.actionBy = actionBy;
	}

	public Double getProcessingFee() {
		return processingFee;
	}

	public void setProcessingFee(Double processingFee) {
		this.processingFee = processingFee;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Long getProposalId() {
		return proposalId;
	}

	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}

	public Long getProposalStatusId() {
		return proposalStatusId;
	}

	public void setProposalStatusId(Long proposalStatusId) {
		this.proposalStatusId = proposalStatusId;
	}

	public Boolean getIsSaved() {
		return isSaved;
	}

	public void setIsSaved(Boolean isSaved) {
		this.isSaved = isSaved;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	public Boolean getIsIneligibleProposal() {
		return isIneligibleProposal;
	}

	public void setIsIneligibleProposal(Boolean isIneligibleProposal) {
		this.isIneligibleProposal = isIneligibleProposal;
	}
	
	public Long getIsSanctionedFrom() {
		return isSanctionedFrom;
	}

	public void setIsSanctionedFrom(Long isSanctionedFrom) {
		this.isSanctionedFrom = isSanctionedFrom;
	}
	
	public Boolean getIsPartiallyDisbursedOffline() {
		return isPartiallyDisbursedOffline;
	}

	public void setIsPartiallyDisbursedOffline(Boolean isPartiallyDisbursedOffline) {
		this.isPartiallyDisbursedOffline = isPartiallyDisbursedOffline;
	}

	@Override
	public String toString() {
		return "LoanSanctionRequest [id=" + id + ", accountNo=" + accountNo + ", sanctionAmount=" + sanctionAmount
				+ ", transactionNo=" + transactionNo + ", roi=" + roi + ", sanctionDate=" + sanctionDate + ", branch="
				+ branch + ", applicationId=" + applicationId + ", referenceNo=" + referenceNo + ", tenure=" + tenure
				+ ", sanctionAuthority=" + sanctionAuthority + ", remark=" + remark + ", userName=" + userName
				+ ", actionBy=" + actionBy + ", processingFee=" + processingFee + ", orgId="
				+ orgId + ", proposalId=" + proposalId + ", proposalStatusId=" + proposalStatusId + ", isSaved="
				+ isSaved + ", reason=" + reason + ", statusCode=" + statusCode + ", isIneligibleProposal="
				+ isIneligibleProposal + ", isSanctionedFrom=" + isSanctionedFrom + "]";
	}

	
	
}
