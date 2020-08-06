package com.opl.mudra.api.loans.model.micro_finance;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author harsukh.ghumaliya
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class ProjectDetailsReq {

	private Long id;
	private Long applicationId;
	//1. Loan Applied/ Cost of Project/ Means of Finance
	private Integer loanType;
	private Integer purposeOfLoan;
	private Double loanAmountRequired;
	private Double costOfEquipment;
	private Double workingCapOfEquipment;
	private Double promoterContribution;
	private Double loanRequiredFromSidbi;
	private Integer loanTenure;

	private Double totalCostEquipment;
	private Double totalMeanFinance;
	private Double totalCashFlow;

	//2. Repayment and Insurance Details
	private Integer repaymentFrequency;
	private Boolean insurenceRequired;
	private String insurenceCompanyName;
	private Double insurencePremium;

	//3. Operational & Business Assessment
	private Integer clientType;
	private Integer repaymentTrack;
	private Integer competition;

	//4. Expected Increase in Income
	private Integer businessInBrief;
	private Double monthlyCashflow;
	private Double monthlyExpenditure;
	private Double monthlyIncome;

	private Boolean isProjectDetailsFilled;
	private Boolean isRepaymentDetailsFilled;

	public ProjectDetailsReq() {
	}

	public ProjectDetailsReq(Long applicationId, Integer loanType, Integer purposeOfLoan, Double loanAmountRequired,
			Double costOfEquipment, Double workingCapOfEquipment, Double totalCostEquipment,
			Double promoterContribution, Double loanRequiredFromSidbi, Double totalMeanFinance, Double totalCashFlow,
			Integer repaymentFrequency, Boolean insurenceRequired, String insurenceCompanyName, Double insurencePremium,
			Boolean isProjectDetailsFilled,Integer businessInBrief, Double monthlyCashflow, Double monthlyExpenditure,Double monthlyIncome) {
		super();
		this.applicationId = applicationId;
		this.loanType = loanType;
		this.purposeOfLoan = purposeOfLoan;
		this.loanAmountRequired = loanAmountRequired;
		this.costOfEquipment = costOfEquipment;
		this.workingCapOfEquipment = workingCapOfEquipment;
		this.totalCostEquipment = totalCostEquipment;
		this.promoterContribution = promoterContribution;
		this.loanRequiredFromSidbi = loanRequiredFromSidbi;
		this.totalMeanFinance = totalMeanFinance;
		this.totalCashFlow = totalCashFlow;
		this.repaymentFrequency = repaymentFrequency;
		this.insurenceRequired = insurenceRequired;
		this.insurenceCompanyName = insurenceCompanyName;
		this.insurencePremium = insurencePremium;
		this.isProjectDetailsFilled = isProjectDetailsFilled;
		this.businessInBrief = businessInBrief;
		this.monthlyCashflow = monthlyCashflow;
		this.monthlyExpenditure = monthlyExpenditure;
		this.monthlyIncome = monthlyIncome;
	}

	public Integer getLoanType() {
		return loanType;
	}

	public void setLoanType(Integer loanType) {
		this.loanType = loanType;
	}

	public Double getLoanAmountRequired() {
		return loanAmountRequired;
	}

	public void setLoanAmountRequired(Double loanAmountRequired) {
		this.loanAmountRequired = loanAmountRequired;
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

	public Integer getPurposeOfLoan() {
		return purposeOfLoan;
	}

	public void setPurposeOfLoan(Integer purposeOfLoan) {
		this.purposeOfLoan = purposeOfLoan;
	}
	

	public Boolean getIsRepaymentDetailsFilled() {
		return isRepaymentDetailsFilled;
	}

	public void setIsRepaymentDetailsFilled(Boolean isRepaymentDetailsFilled) {
		this.isRepaymentDetailsFilled = isRepaymentDetailsFilled;
	}

	public Integer getLoanTenure() {
		return loanTenure;
	}

	public void setLoanTenure(Integer loanTenure) {
		this.loanTenure = loanTenure;
	}

	public Integer getClientType() {
		return clientType;
	}

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public Integer getRepaymentTrack() {
		return repaymentTrack;
	}

	public void setRepaymentTrack(Integer repaymentTrack) {
		this.repaymentTrack = repaymentTrack;
	}

	public Integer getCompetition() {
		return competition;
	}

	public void setCompetition(Integer competition) {
		this.competition = competition;
	}

	public Integer getBusinessInBrief() {
		return businessInBrief;
	}

	public void setBusinessInBrief(Integer businessInBrief) {
		this.businessInBrief = businessInBrief;
	}

	public Double getMonthlyCashflow() {
		return monthlyCashflow;
	}

	public void setMonthlyCashflow(Double monthlyCashflow) {
		this.monthlyCashflow = monthlyCashflow;
	}

	public Double getMonthlyExpenditure() {
		return monthlyExpenditure;
	}

	public void setMonthlyExpenditure(Double monthlyExpenditure) {
		this.monthlyExpenditure = monthlyExpenditure;
	}

	public Double getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(Double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	@Override
	public String toString() {
		return "ProjectDetailsReq{" +
				"id=" + id +
				", applicationId=" + applicationId +
				", loanType=" + loanType +
				", purposeOfLoan=" + purposeOfLoan +
				", loanAmountRequired=" + loanAmountRequired +
				", costOfEquipment=" + costOfEquipment +
				", workingCapOfEquipment=" + workingCapOfEquipment +
				", promoterContribution=" + promoterContribution +
				", loanRequiredFromSidbi=" + loanRequiredFromSidbi +
				", loanTenure=" + loanTenure +
				", totalCostEquipment=" + totalCostEquipment +
				", totalMeanFinance=" + totalMeanFinance +
				", totalCashFlow=" + totalCashFlow +
				", repaymentFrequency=" + repaymentFrequency +
				", insurenceRequired=" + insurenceRequired +
				", insurenceCompanyName='" + insurenceCompanyName + '\'' +
				", insurencePremium=" + insurencePremium +
				", clientType=" + clientType +
				", repaymentTrack=" + repaymentTrack +
				", competition=" + competition +
				", businessInBrief=" + businessInBrief +
				", monthlyCashflow=" + monthlyCashflow +
				", monthlyExpenditure=" + monthlyExpenditure +
				", monthlyIncome=" + monthlyIncome +
				", isProjectDetailsFilled=" + isProjectDetailsFilled +
				", isRepaymentDetailsFilled=" + isRepaymentDetailsFilled +
				'}';
	}
}
