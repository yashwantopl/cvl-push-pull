package com.capitaworld.service.loans.model.micro_finance;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @author harsukh.ghumaliya
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ProjectDetailsReq {

	private Long id;
	private Long applicationId;
	private Integer loanType;
	private String loanPurpose;
	private Double loanAmountRequired;
	private Double costOfProject;
	private Double costOfEquipment;
	private Double workingCapOfEquipment;
	private Double totalCostEquipment;
	private Double promoterContribution;
	private Double loanRequiredFromSidbi;
	private Double totalMeanFinance;
	private Double totalCashFlow;
	private Integer repaymentFrequency;
	private Boolean insurenceRequired;
	private String insurenceCompanyName;
	private Double insurencePremium;
	private Boolean isProjectDetailsFilled;

	public Integer getLoanType() {
		return loanType;
	}

	public void setLoanType(Integer loanType) {
		this.loanType = loanType;
	}

	public String getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public Double getLoanAmountRequired() {
		return loanAmountRequired;
	}

	public void setLoanAmountRequired(Double loanAmountRequired) {
		this.loanAmountRequired = loanAmountRequired;
	}

	public Double getCostOfProject() {
		return costOfProject;
	}

	public void setCostOfProject(Double costOfProject) {
		this.costOfProject = costOfProject;
	}

	public Double getCostOfEquipment() {
		return costOfEquipment;
	}

	public void setCostOfEquipment(Double costOfEquipment) {
		this.costOfEquipment = costOfEquipment;
	}

	public Double getWorkingCapOfEquipment() {
		return workingCapOfEquipment;
	}

	public void setWorkingCapOfEquipment(Double workingCapOfEquipment) {
		this.workingCapOfEquipment = workingCapOfEquipment;
	}

	public Double getTotalCostEquipment() {
		return totalCostEquipment;
	}

	public void setTotalCostEquipment(Double totalCostEquipment) {
		this.totalCostEquipment = totalCostEquipment;
	}

	public Double getPromoterContribution() {
		return promoterContribution;
	}

	public void setPromoterContribution(Double promoterContribution) {
		this.promoterContribution = promoterContribution;
	}

	public Double getLoanRequiredFromSidbi() {
		return loanRequiredFromSidbi;
	}

	public void setLoanRequiredFromSidbi(Double loanRequiredFromSidbi) {
		this.loanRequiredFromSidbi = loanRequiredFromSidbi;
	}

	public Double getTotalMeanFinance() {
		return totalMeanFinance;
	}

	public void setTotalMeanFinance(Double totalMeanFinance) {
		this.totalMeanFinance = totalMeanFinance;
	}

	public Double getTotalCashFlow() {
		return totalCashFlow;
	}

	public void setTotalCashFlow(Double totalCashFlow) {
		this.totalCashFlow = totalCashFlow;
	}

	public Integer getRepaymentFrequency() {
		return repaymentFrequency;
	}

	public void setRepaymentFrequency(Integer repaymentFrequency) {
		this.repaymentFrequency = repaymentFrequency;
	}

	public Boolean getInsurenceRequired() {
		return insurenceRequired;
	}

	public void setInsurenceRequired(Boolean insurenceRequired) {
		this.insurenceRequired = insurenceRequired;
	}

	public String getInsurenceCompanyName() {
		return insurenceCompanyName;
	}

	public void setInsurenceCompanyName(String insurenceCompanyName) {
		this.insurenceCompanyName = insurenceCompanyName;
	}

	public Double getInsurencePremium() {
		return insurencePremium;
	}

	public void setInsurencePremium(Double insurencePremium) {
		this.insurencePremium = insurencePremium;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsProjectDetailsFilled() {
		return isProjectDetailsFilled;
	}

	public void setIsProjectDetailsFilled(Boolean isProjectDetailsFilled) {
		this.isProjectDetailsFilled = isProjectDetailsFilled;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	@Override
	public String toString() {
		return "ProjectDetailsReq [id=" + id + ", applicationId=" + applicationId + ", loanType=" + loanType
				+ ", loanPurpose=" + loanPurpose + ", loanAmountRequired=" + loanAmountRequired + ", costOfProject="
				+ costOfProject + ", costOfEquipment=" + costOfEquipment + ", workingCapOfEquipment="
				+ workingCapOfEquipment + ", totalCostEquipment=" + totalCostEquipment + ", promoterContribution="
				+ promoterContribution + ", loanRequiredFromSidbi=" + loanRequiredFromSidbi + ", totalMeanFinance="
				+ totalMeanFinance + ", totalCashFlow=" + totalCashFlow + ", repaymentFrequency=" + repaymentFrequency
				+ ", insurenceRequired=" + insurenceRequired + ", insurenceCompanyName=" + insurenceCompanyName
				+ ", insurencePremium=" + insurencePremium + ", isProjectDetailsFilled=" + isProjectDetailsFilled
				+ ", getLoanType()=" + getLoanType() + ", getLoanPurpose()=" + getLoanPurpose()
				+ ", getLoanAmountRequired()=" + getLoanAmountRequired() + ", getCostOfProject()=" + getCostOfProject()
				+ ", getCostOfEquipment()=" + getCostOfEquipment() + ", getWorkingCapOfEquipment()="
				+ getWorkingCapOfEquipment() + ", getTotalCostEquipment()=" + getTotalCostEquipment()
				+ ", getPromoterContribution()=" + getPromoterContribution() + ", getLoanRequiredFromSidbi()="
				+ getLoanRequiredFromSidbi() + ", getTotalMeanFinance()=" + getTotalMeanFinance()
				+ ", getTotalCashFlow()=" + getTotalCashFlow() + ", getRepaymentFrequency()=" + getRepaymentFrequency()
				+ ", getInsurenceRequired()=" + getInsurenceRequired() + ", getInsurenceCompanyName()="
				+ getInsurenceCompanyName() + ", getInsurencePremium()=" + getInsurencePremium() + ", getId()="
				+ getId() + ", getIsProjectDetailsFilled()=" + getIsProjectDetailsFilled() + ", getApplicationId()="
				+ getApplicationId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
