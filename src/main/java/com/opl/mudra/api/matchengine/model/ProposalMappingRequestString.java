package com.opl.mudra.api.matchengine.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProposalMappingRequestString implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long fpProductId;

	private String fpProductName;

	private Long applicationId;

	private Long proposalStatusId;

	private Long proposalStageId = null;

	private Boolean isProposalAuto = null;

	private Long initiatedBy = null;

	private Long lastActionPerformedBy = null;

	private Long userId;

	private Long userType;

	private String applicantName;

	private Long userOrgId;

	private Double userScore;

	private String elAmount;

	private String elTenure;

	private String elRoi;

	private Integer pageIndex;
	private Integer size;

	private Long branchId;

	private Long assignBy;

	private Long assignBranchTo;

	private Date assignDate;

	private Double applicationLoanAmount;
	
	private Integer dateTypeMasterId;

	//By Akshay Starts
	private Long currentStep;

	private Long toStep;

	private Long jobId;
	
	private Long clientId;

	private String remarks;

	private Long actionId;

	private List<Long> roleIds;

	private Long workflowId;
	
	private Long modifiedBy;
	
	private Date modifiedDate;
	
	//By Akshay Ends

	private Double emi;

	private Double processingFee;

	private Long typeId;
	
	private String reason;
	
	private String additionalLoanAmount;
	
	private Double consessionRoi;

	private Double spreadRoi;
	
	private Double mclrRoi;
	
	private String concessionBasedOnType;
	
	private Integer scoringModelBasedOn;

	public Integer getScoringModelBasedOn() {
		return scoringModelBasedOn;
	}

	public void setScoringModelBasedOn(Integer scoringModelBasedOn) {
		this.scoringModelBasedOn = scoringModelBasedOn;
	}

	public String getConcessionBasedOnType() {
		return concessionBasedOnType;
	}

	public void setConcessionBasedOnType(String concessionBasedOnType) {
		this.concessionBasedOnType = concessionBasedOnType;
	}

	public Double getConsessionRoi() {
		return consessionRoi;
	}

	public void setConsessionRoi(Double consessionRoi) {
		this.consessionRoi = consessionRoi;
	}

	public Double getSpreadRoi() {
		return spreadRoi;
	}

	public void setSpreadRoi(Double spreadRoi) {
		this.spreadRoi = spreadRoi;
	}

	public Double getMclrRoi() {
		return mclrRoi;
	}

	public void setMclrRoi(Double mclrRoi) {
		this.mclrRoi = mclrRoi;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getFpProductId() {
		return fpProductId;
	}

	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
	}

	public String getFpProductName() {
		return fpProductName;
	}

	public void setFpProductName(String fpProductName) {
		this.fpProductName = fpProductName;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getProposalStatusId() {
		return proposalStatusId;
	}

	public void setProposalStatusId(Long proposalStatusId) {
		this.proposalStatusId = proposalStatusId;
	}

	public Long getProposalStageId() {
		return proposalStageId;
	}

	public void setProposalStageId(Long proposalStageId) {
		this.proposalStageId = proposalStageId;
	}

	public Boolean getIsProposalAuto() {
		return isProposalAuto;
	}

	public void setIsProposalAuto(Boolean isProposalAuto) {
		this.isProposalAuto = isProposalAuto;
	}

	public Long getInitiatedBy() {
		return initiatedBy;
	}

	public void setInitiatedBy(Long initiatedBy) {
		this.initiatedBy = initiatedBy;
	}

	public Long getLastActionPerformedBy() {
		return lastActionPerformedBy;
	}

	public void setLastActionPerformedBy(Long lastActionPerformedBy) {
		this.lastActionPerformedBy = lastActionPerformedBy;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserType() {
		return userType;
	}

	public void setUserType(Long userType) {
		this.userType = userType;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}

	public Double getUserScore() {
		return userScore;
	}

	public void setUserScore(Double userScore) {
		this.userScore = userScore;
	}

	public String getElAmount() {
		return elAmount;
	}

	public void setElAmount(String elAmount) {
		this.elAmount = elAmount;
	}

	public String getElTenure() {
		return elTenure;
	}

	public void setElTenure(String elTenure) {
		this.elTenure = elTenure;
	}

	public String getElRoi() {
		return elRoi;
	}

	public void setElRoi(String elRoi) {
		this.elRoi = elRoi;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Long getAssignBy() {
		return assignBy;
	}

	public void setAssignBy(Long assignBy) {
		this.assignBy = assignBy;
	}

	public Long getAssignBranchTo() {
		return assignBranchTo;
	}

	public void setAssignBranchTo(Long assignBranchTo) {
		this.assignBranchTo = assignBranchTo;
	}

	public Date getAssignDate() {
		return assignDate;
	}

	public void setAssignDate(Date assignDate) {
		this.assignDate = assignDate;
	}

	public Double getApplicationLoanAmount() {
		return applicationLoanAmount;
	}

	public void setApplicationLoanAmount(Double applicationLoanAmount) {
		this.applicationLoanAmount = applicationLoanAmount;
	}

	public Integer getDateTypeMasterId() {
		return dateTypeMasterId;
	}

	public void setDateTypeMasterId(Integer dateTypeMasterId) {
		this.dateTypeMasterId = dateTypeMasterId;
	}

	public Long getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(Long currentStep) {
		this.currentStep = currentStep;
	}

	public Long getToStep() {
		return toStep;
	}

	public void setToStep(Long toStep) {
		this.toStep = toStep;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getActionId() {
		return actionId;
	}

	public void setActionId(Long actionId) {
		this.actionId = actionId;
	}

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public Long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Double getEmi() {
		return emi;
	}

	public void setEmi(Double emi) {
		this.emi = emi;
	}

	public Double getProcessingFee() {
		return processingFee;
	}

	public void setProcessingFee(Double processingFee) {
		this.processingFee = processingFee;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}



	public String getAdditionalLoanAmount() {
		return additionalLoanAmount;
	}

	public void setAdditionalLoanAmount(String additionalLoanAmount) {
		this.additionalLoanAmount = additionalLoanAmount;
	}
	
	
}
