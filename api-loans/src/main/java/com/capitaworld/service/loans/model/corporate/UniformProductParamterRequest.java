package com.capitaworld.service.loans.model.corporate;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.capitaworld.service.loans.model.ProductMasterRequest;
import com.capitaworld.service.loans.model.WorkflowData;

public class UniformProductParamterRequest extends ProductMasterRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Double minAmount;
	private Double maxAmount;
	private Double roi;
	private Double pf;
	private Double minCibilScore;
	private Integer minCmr;
	private Integer maxCmr;
	
	private Integer currency;

	private Boolean isEdited;
	
	private Integer dpd;
	
	private Integer denomination;
	
	private Integer assessmentMethod;
	
	private Integer version;
	
	private Double promotorContribution;
	
	private Double wcRequirement;
	
	private Double maxGrowth;
	
	private Object workflowData;
	
	private WorkflowData workflowRequest; 
	
	private List<Long> roleIds;
	
	
	//Audit Fields
	private String minAmountAudit;
	
	private String maxAmountAudit;

	private String roiAudit;
	
	private String pfAudit;
	
	private String minCibilScoreAudit;

	private String minCmrAudit;
	
	private String maxCmrAudit;
	
	private String currencyAudit;

	private String denominationAudit;
	
	private String assessmentMethodAudit;
	
	private String promotorContributionAudit;
	
	private String wcRequirementAudit;
	
	private String maxGrowthAudit;
	
	private String versionAudit;
	
	private Date createdDate;

	public UniformProductParamterRequest(){
		super();
	}
	
	public UniformProductParamterRequest(Long id){
		super(id);
	}
	public Double getRoi() {
		return roi;
	}

	public void setRoi(Double roi) {
		this.roi = roi;
	}

	public Double getPf() {
		return pf;
	}

	public void setPf(Double pf) {
		this.pf = pf;
	}

	public Double getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Double minAmount) {
		this.minAmount = minAmount;
	}

	public Double getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Double maxAmount) {
		this.maxAmount = maxAmount;
	}

	public Double getMinCibilScore() {
		return minCibilScore;
	}

	public void setMinCibilScore(Double minCibilScore) {
		this.minCibilScore = minCibilScore;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public Integer getDenomination() {
		return denomination;
	}

	public void setDenomination(Integer denomination) {
		this.denomination = denomination;
	}

	public Integer getAssessmentMethod() {
		return assessmentMethod;
	}

	public void setAssessmentMethod(Integer assessmentMethod) {
		this.assessmentMethod = assessmentMethod;
	}

	public Double getPromotorContribution() {
		return promotorContribution;
	}

	public void setPromotorContribution(Double promotorContribution) {
		this.promotorContribution = promotorContribution;
	}

	public Double getWcRequirement() {
		return wcRequirement;
	}

	public void setWcRequirement(Double wcRequirement) {
		this.wcRequirement = wcRequirement;
	}

	public Double getMaxGrowth() {
		return maxGrowth;
	}

	public void setMaxGrowth(Double maxGrowth) {
		this.maxGrowth = maxGrowth;
	}

	public Integer getMinCmr() {
		return minCmr;
	}

	public void setMinCmr(Integer minCmr) {
		this.minCmr = minCmr;
	}

	public Integer getMaxCmr() {
		return maxCmr;
	}

	public void setMaxCmr(Integer maxCmr) {
		this.maxCmr = maxCmr;
	}

	public Object getWorkflowData() {
		return workflowData;
	}

	public void setWorkflowData(Object workflowData) {
		this.workflowData = workflowData;
	}

	public List<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(List<Long> roleIds) {
		this.roleIds = roleIds;
	}

	public WorkflowData getWorkflowRequest() {
		return workflowRequest;
	}

	public void setWorkflowRequest(WorkflowData workflowRequest) {
		this.workflowRequest = workflowRequest;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getMinAmountAudit() {
		return minAmountAudit;
	}

	public void setMinAmountAudit(String minAmountAudit) {
		this.minAmountAudit = minAmountAudit;
	}

	public String getMaxAmountAudit() {
		return maxAmountAudit;
	}

	public void setMaxAmountAudit(String maxAmountAudit) {
		this.maxAmountAudit = maxAmountAudit;
	}

	public String getRoiAudit() {
		return roiAudit;
	}

	public void setRoiAudit(String roiAudit) {
		this.roiAudit = roiAudit;
	}

	public String getPfAudit() {
		return pfAudit;
	}

	public void setPfAudit(String pfAudit) {
		this.pfAudit = pfAudit;
	}

	public String getMinCibilScoreAudit() {
		return minCibilScoreAudit;
	}

	public void setMinCibilScoreAudit(String minCibilScoreAudit) {
		this.minCibilScoreAudit = minCibilScoreAudit;
	}

	public String getMinCmrAudit() {
		return minCmrAudit;
	}

	public void setMinCmrAudit(String minCmrAudit) {
		this.minCmrAudit = minCmrAudit;
	}

	public String getMaxCmrAudit() {
		return maxCmrAudit;
	}

	public void setMaxCmrAudit(String maxCmrAudit) {
		this.maxCmrAudit = maxCmrAudit;
	}

	public String getCurrencyAudit() {
		return currencyAudit;
	}

	public void setCurrencyAudit(String currencyAudit) {
		this.currencyAudit = currencyAudit;
	}

	public String getDenominationAudit() {
		return denominationAudit;
	}

	public void setDenominationAudit(String denominationAudit) {
		this.denominationAudit = denominationAudit;
	}

	public String getAssessmentMethodAudit() {
		return assessmentMethodAudit;
	}

	public void setAssessmentMethodAudit(String assessmentMethodAudit) {
		this.assessmentMethodAudit = assessmentMethodAudit;
	}

	public String getPromotorContributionAudit() {
		return promotorContributionAudit;
	}

	public void setPromotorContributionAudit(String promotorContributionAudit) {
		this.promotorContributionAudit = promotorContributionAudit;
	}

	public String getWcRequirementAudit() {
		return wcRequirementAudit;
	}

	public void setWcRequirementAudit(String wcRequirementAudit) {
		this.wcRequirementAudit = wcRequirementAudit;
	}

	public String getMaxGrowthAudit() {
		return maxGrowthAudit;
	}

	public void setMaxGrowthAudit(String maxGrowthAudit) {
		this.maxGrowthAudit = maxGrowthAudit;
	}

	public String getVersionAudit() {
		return versionAudit;
	}

	public void setVersionAudit(String versionAudit) {
		this.versionAudit = versionAudit;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getDpd() {
		return dpd;
	}

	public void setDpd(Integer dpd) {
		this.dpd = dpd;
	}

	public Boolean getIsEdited() {
		return isEdited;
	}

	public void setIsEdited(Boolean isEdited) {
		this.isEdited = isEdited;
	}
}