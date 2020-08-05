package com.opl.mudra.api.matchengine.model;

import java.util.Date;

public class DisbursementRequestModel {

	/*
	* Application Date,Application No.,Applicant NameApplicant Address,Bank Sanction Date,NBFC Sanction Date,Final Loan Amount,Bank Loan Amount
	* NBFC Loan Amount,Total Sanction Amount,Bank Sanctioned Amount,NBFC Sanctioned Amount,Instalment Amount,Bank Instalment Amount
	* NBFC Instalment Amount,Loan Purpose,Rate of Interest
	*
	* */
	private Long id;
	
	private Long userId;
	
	private Long proposalId;

	private Long nbfcProposalId;

	private Long bankProposalId;

	private Long applicationNo;

	private String applicationDate;

	private String nbfcSanctionDate;

	private String bankSanctionDate;
	
	private Double totalSanctionedAmount;

	private Double nfbcSanctionedAmount;

	private Double bankSanctionedAmount;

	private Double instalmentAmount;

	private Double nbfcInstalmentAmount;

	private Double bankInstalmentAmount;

	private Double nfbcLoanAmount;

	private Double bankLoanAmount;

	private Double finalLoanAmount;

	private Long loanAccountNumber;

	private String loanPurpose;
	
	private Double roi;
	
	private String applicantAddress;

	private String applicantName;

	private String remark;

	private Long applicationId;
	
	private Integer proposalStatus;
	
	private Long fpProductId;
	
	private Long orgId;

	private String reason;

	private Integer nbfcFlow;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getRoi() {
		return roi;
	}

	public void setRoi(Double roi) {
		this.roi = roi;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getProposalStatus() {
		return proposalStatus;
	}

	public void setProposalStatus(Integer proposalStatus) {
		this.proposalStatus = proposalStatus;
	}

	public Long getFpProductId() {
		return fpProductId;
	}

	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Integer getNbfcFlow() {
		return nbfcFlow;
	}

	public void setNbfcFlow(Integer nbfcFlow) {
		this.nbfcFlow = nbfcFlow;
	}

	public Long getProposalId() {
		return proposalId;
	}

	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}

	public Long getNbfcProposalId() {
		return nbfcProposalId;
	}

	public void setNbfcProposalId(Long nbfcProposalId) {
		this.nbfcProposalId = nbfcProposalId;
	}

	public Long getBankProposalId() {
		return bankProposalId;
	}

	public void setBankProposalId(Long bankProposalId) {
		this.bankProposalId = bankProposalId;
	}

	public Long getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(Long applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getNbfcSanctionDate() {
		return nbfcSanctionDate;
	}

	public void setNbfcSanctionDate(String nbfcSanctionDate) {
		this.nbfcSanctionDate = nbfcSanctionDate;
	}

	public String getBankSanctionDate() {
		return bankSanctionDate;
	}

	public void setBankSanctionDate(String bankSanctionDate) {
		this.bankSanctionDate = bankSanctionDate;
	}

	public Double getTotalSanctionedAmount() {
		return totalSanctionedAmount;
	}

	public void setTotalSanctionedAmount(Double totalSanctionedAmount) {
		this.totalSanctionedAmount = totalSanctionedAmount;
	}

	public Double getNfbcSanctionedAmount() {
		return nfbcSanctionedAmount;
	}

	public void setNfbcSanctionedAmount(Double nfbcSanctionedAmount) {
		this.nfbcSanctionedAmount = nfbcSanctionedAmount;
	}

	public Double getBankSanctionedAmount() {
		return bankSanctionedAmount;
	}

	public void setBankSanctionedAmount(Double bankSanctionedAmount) {
		this.bankSanctionedAmount = bankSanctionedAmount;
	}

	public Double getInstalmentAmount() {
		return instalmentAmount;
	}

	public void setInstalmentAmount(Double instalmentAmount) {
		this.instalmentAmount = instalmentAmount;
	}

	public Double getNbfcInstalmentAmount() {
		return nbfcInstalmentAmount;
	}

	public void setNbfcInstalmentAmount(Double nbfcInstalmentAmount) {
		this.nbfcInstalmentAmount = nbfcInstalmentAmount;
	}

	public Double getBankInstalmentAmount() {
		return bankInstalmentAmount;
	}

	public void setBankInstalmentAmount(Double bankInstalmentAmount) {
		this.bankInstalmentAmount = bankInstalmentAmount;
	}

	public Double getNfbcLoanAmount() {
		return nfbcLoanAmount;
	}

	public void setNfbcLoanAmount(Double nfbcLoanAmount) {
		this.nfbcLoanAmount = nfbcLoanAmount;
	}

	public Double getBankLoanAmount() {
		return bankLoanAmount;
	}

	public void setBankLoanAmount(Double bankLoanAmount) {
		this.bankLoanAmount = bankLoanAmount;
	}

	public Double getFinalLoanAmount() {
		return finalLoanAmount;
	}

	public void setFinalLoanAmount(Double finalLoanAmount) {
		this.finalLoanAmount = finalLoanAmount;
	}

	public String getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public String getApplicantAddress() {
		return applicantAddress;
	}

	public void setApplicantAddress(String applicantAddress) {
		this.applicantAddress = applicantAddress;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public Long getLoanAccountNumber() {
		return loanAccountNumber;
	}

	public void setLoanAccountNumber(Long loanAccountNumber) {
		this.loanAccountNumber = loanAccountNumber;
	}
}
