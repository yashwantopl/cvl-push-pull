package com.opl.mudra.api.matchengine.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProposalMappingRequest implements Serializable {

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

	private Double existingLoanAmount;

	private Double additionalLoanAmount;

	private Double elAmount;

	private Double elTenure;

	private Double elRoi;

	private Integer pageIndex;
	private Integer size;

	private Long branchId;

	private List<Long> branchIds;

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

	private Long userRoleId;

	private Long workflowId;
	
	private Long modifiedBy;
	
	private Date modifiedDate;
	
	//By Akshay Ends

	private Double emi;

	private Double processingFee;

	private Long typeId;

	private String reason;
	
	private Integer version;

	private  Boolean isButtonDisplay;

	private  String messageOfButton;

	private Boolean isSanctionByOtherBankReq;

	private Double minPf;

	private Double maxPf;
	
	private Long proposalId;

	private Double spreadRoi;

	private Double mclrRoi;

	private Double consessionRoi;
	
	private String concessionBasedOnType;

	private Integer scoringModelBasedOn;

	private Integer nbfcFlow;

	private Long ratioId;
	
	private Double pfConcession;

	private String pfConcessionText;

	private Integer connectFlowTypeId;
	
	private String gstin;

	private Integer businessTypeId;

	private Boolean isOffline;

	private String addiFields;

	private String reopenReason;

	private Boolean isSanctioned;

	private Boolean isDisbursed;


	public String getConcessionBasedOnType() {
		return concessionBasedOnType;
	}

	public void setConcessionBasedOnType(String concessionBasedOnType) {
		this.concessionBasedOnType = concessionBasedOnType;
	}

	public Long getProposalId() {
		return proposalId;
	}

	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}

	public Double getMinPf() {
		return minPf;
	}

	public void setMinPf(Double minPf) {
		this.minPf = minPf;
	}

	public Double getMaxPf() {
		return maxPf;
	}

	public void setMaxPf(Double maxPf) {
		this.maxPf = maxPf;
	}

	public Boolean getIsButtonDisplay() {
		return isButtonDisplay;
	}

	public void setIsButtonDisplay(Boolean buttonDisplay) {
		isButtonDisplay = buttonDisplay;
	}

	public String getMessageOfButton() {
		return messageOfButton;
	}

	public void setMessageOfButton(String messageOfButton) {
		this.messageOfButton = messageOfButton;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public Long getUserOrgId() {
		return userOrgId;
	}

	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}

	public String getFpProductName() {
		return fpProductName;
	}

	public void setFpProductName(String fpProductName) {
		this.fpProductName = fpProductName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public Long getUserType() {
		return userType;
	}

	public void setUserType(Long userType) {
		this.userType = userType;
	}

	public Long getLastActionPerformedBy() {
		return lastActionPerformedBy;
	}

	public void setLastActionPerformedBy(Long lastActionPerformedBy) {
		this.lastActionPerformedBy = lastActionPerformedBy;
	}

	public Long getInitiatedBy() {
		return initiatedBy;
	}

	public void setInitiatedBy(Long initiatedBy) {
		this.initiatedBy = initiatedBy;
	}

	public Long getProposalStageId() {
		return proposalStageId;
	}

	public void setProposalStageId(Long proposalStageId) {
		this.proposalStageId = proposalStageId;
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

	public Boolean getIsProposalAuto() {
		return isProposalAuto;
	}

	public void setIsProposalAuto(Boolean isProposalAuto) {
		this.isProposalAuto = isProposalAuto;
	}

	public Double getUserScore() {
		return userScore;
	}

	public void setUserScore(Double userScore) {
		this.userScore = userScore;
	}

	public Double getElAmount() {
		return elAmount;
	}

	public void setElAmount(Double elAmount) {
		this.elAmount = elAmount;
	}

	public Double getElTenure() {
		return elTenure;
	}

	public void setElTenure(Double elTenure) {
		this.elTenure = elTenure;
	}

	public Double getElRoi() {
		return elRoi;
	}

	public void setElRoi(Double elRoi) {
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

	// By Akshay Starts
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

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
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

	
	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public List<Long> getBranchIds() {
		return branchIds;
	}

	public void setBranchIds(List<Long> branchIds) {
		this.branchIds = branchIds;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Double getExistingLoanAmount() {
		return existingLoanAmount;
	}

	public void setExistingLoanAmount(Double existingLoanAmount) {
		this.existingLoanAmount = existingLoanAmount;
	}

	public Double getAdditionalLoanAmount() {
		return additionalLoanAmount;
	}

	public void setAdditionalLoanAmount(Double additionalLoanAmount) {
		this.additionalLoanAmount = additionalLoanAmount;
	}

	public Boolean getIsSanctionByOtherBankReq() {
		return isSanctionByOtherBankReq;
	}

	public void setIsSanctionByOtherBankReq(Boolean sanctionByOtherBankReq) {
		isSanctionByOtherBankReq = sanctionByOtherBankReq;
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

	public Double getConsessionRoi() {
		return consessionRoi;
	}

	public void setConsessionRoi(Double consessionRoi) {
		this.consessionRoi = consessionRoi;
	}

	public Integer getScoringModelBasedOn() {
		return scoringModelBasedOn;
	}

	public void setScoringModelBasedOn(Integer scoringModelBasedOn) {
		this.scoringModelBasedOn = scoringModelBasedOn;
	}

	public Integer getNbfcFlow() {
		return nbfcFlow;
	}

	public void setNbfcFlow(Integer nbfcFlow) {
		this.nbfcFlow = nbfcFlow;
	}

	public Long getRatioId() {
		return ratioId;
	}

	public void setRatioId(Long ratioId) {
		this.ratioId = ratioId;
	}
	

	public Double getPfConcession() {
		return pfConcession;
	}

	public void setPfConcession(Double pfConcession) {
		this.pfConcession = pfConcession;
	}

	public String getPfConcessionText() {
		return pfConcessionText;
	}

	public void setPfConcessionText(String pfConcessionText) {
		this.pfConcessionText = pfConcessionText;
	}

	public Integer getConnectFlowTypeId() {
		return connectFlowTypeId;
	}

	public void setConnectFlowTypeId(Integer connectFlowTypeId) {
		this.connectFlowTypeId = connectFlowTypeId;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public Boolean getIsOffline() {
		return isOffline;
	}

	public void setIsOffline(Boolean isOffline) {
		this.isOffline = isOffline;
	}

	public String getAddiFields() {
		return addiFields;
	}

	public void setAddiFields(String addiFields) {
		this.addiFields = addiFields;
	}

	public String getReopenReason() {
		return reopenReason;
	}

	public void setReopenReason(String reopenReason) {
		this.reopenReason = reopenReason;
	}

	public Boolean getIsSanctioned() {
		return isSanctioned;
	}

	public void setIsSanctioned(Boolean isSanctioned) {
		this.isSanctioned = isSanctioned;
	}

	public Boolean getIsDisbursed() {
		return isDisbursed;
	}

	public void setIsDisbursed(Boolean isDisbursed) {
		this.isDisbursed = isDisbursed;
	}

	@Override
	public String toString() {
		return "ProposalMappingRequest [id=" + id + ", fpProductId=" + fpProductId + ", fpProductName=" + fpProductName
				+ ", applicationId=" + applicationId + ", proposalStatusId=" + proposalStatusId + ", proposalStageId="
				+ proposalStageId + ", isProposalAuto=" + isProposalAuto + ", initiatedBy=" + initiatedBy
				+ ", lastActionPerformedBy=" + lastActionPerformedBy + ", userId=" + userId + ", userType=" + userType
				+ ", applicantName=" + applicantName + ", userOrgId=" + userOrgId + ", userScore=" + userScore
				+ ", existingLoanAmount=" + existingLoanAmount + ", additionalLoanAmount=" + additionalLoanAmount
				+ ", elAmount=" + elAmount + ", elTenure=" + elTenure + ", elRoi=" + elRoi + ", pageIndex=" + pageIndex
				+ ", size=" + size + ", branchId=" + branchId + ", branchIds=" + branchIds + ", assignBy=" + assignBy
				+ ", assignBranchTo=" + assignBranchTo + ", assignDate=" + assignDate + ", applicationLoanAmount="
				+ applicationLoanAmount + ", dateTypeMasterId=" + dateTypeMasterId + ", currentStep=" + currentStep
				+ ", toStep=" + toStep + ", jobId=" + jobId + ", clientId=" + clientId + ", remarks=" + remarks
				+ ", actionId=" + actionId + ", roleIds=" + roleIds + ", userRoleId=" + userRoleId + ", workflowId="
				+ workflowId + ", modifiedBy=" + modifiedBy + ", modifiedDate=" + modifiedDate + ", emi=" + emi
				+ ", processingFee=" + processingFee + ", typeId=" + typeId + ", reason=" + reason + ", version="
				+ version + ", isButtonDisplay=" + isButtonDisplay + ", messageOfButton=" + messageOfButton
				+ ", isSanctionByOtherBankReq=" + isSanctionByOtherBankReq + ", minPf=" + minPf + ", maxPf=" + maxPf
				+ ", proposalId=" + proposalId + ", spreadRoi=" + spreadRoi + ", mclrRoi=" + mclrRoi
				+ ", consessionRoi=" + consessionRoi + ", concessionBasedOnType=" + concessionBasedOnType + ", scoringModelBasedOn=" + scoringModelBasedOn +"]";
	}
	
}
