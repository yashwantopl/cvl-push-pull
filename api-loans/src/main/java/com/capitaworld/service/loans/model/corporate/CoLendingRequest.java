package com.capitaworld.service.loans.model.corporate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoLendingRequest {

	private Long id;
	
	private String name;
	
	private Double tenure;
	
	private Double nbfcRatio;
	
	private Double bankRatio;
	
	private Long userId;
	
	private Long clientId;
	
	private Long jobId;
	
	private Object workflowData;
	
	private Long bankId;
	
	private Long userOrgId;
	
	private Boolean isProposalActive;
	
	private String reason;

	//constructor
	public CoLendingRequest(Long bankId) {
		this.bankId = bankId;
	}

	public CoLendingRequest() {
	}

	//getter setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTenure() {
		return tenure;
	}

	public void setTenure(Double tenure) {
		this.tenure = tenure;
	}

	public Double getNbfcRatio() {
		return nbfcRatio;
	}

	public void setNbfcRatio(Double nbfcRatio) {
		this.nbfcRatio = nbfcRatio;
	}

	public Double getBankRatio() {
		return bankRatio;
	}

	public void setBankRatio(Double bankRatio) {
		this.bankRatio = bankRatio;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Object getWorkflowData() {
		return workflowData;
	}

	public void setWorkflowData(Object workflowData) {
		this.workflowData = workflowData;
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}

	public Boolean getIsProposalActive() {
		return isProposalActive;
	}

	public void setIsProposalActive(Boolean isProposalActive) {
		this.isProposalActive = isProposalActive;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
