/**
 * 
 */
package com.capitaworld.service.loans.model.micro_finance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author harsukh.ghumaliya
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class MfiLoanAssessmentDetailsReq {
	
	private Long id;
	private Long applicationId;
	private Long userId;
	private Integer purposeOfLoan;
	private Integer clientType;
	private Boolean isBusinessPremiseVisited;
	private Integer repaymentTrack;
	private Integer creaditWorthiness;
	private String loanLiabilityRatio;
	private Integer competition;
	private Double loanAmountRecomandation;
	private Integer tenureRecomandation;
	private Integer moratoriumRecomandation;
	private Double interestRateRecomandation;
	private Integer installmentRecomandation;
	private Boolean isLoanassessmentDetailsFilled;

	//For Cash Flow Assessment
	private Double incomeFromOccupation;
	private Double existingExpensesOfFamily;
	private Double netSaving;
	private Double expectedIncome;
	private Double totalCashFlow;

	public MfiLoanAssessmentDetailsReq() {
	}

	public MfiLoanAssessmentDetailsReq(Long applicationId, Double incomeFromOccupation, Double existingExpensesOfFamily, Double netSaving, Double expectedIncome, Double totalCashFlow) {
		this.applicationId = applicationId;
		this.incomeFromOccupation = incomeFromOccupation;
		this.existingExpensesOfFamily = existingExpensesOfFamily;
		this.netSaving = netSaving;
		this.expectedIncome = expectedIncome;
		this.totalCashFlow = totalCashFlow;
	}

	public MfiLoanAssessmentDetailsReq(Long applicationId, Integer purposeOfLoan, Integer clientType,
									   Boolean isBusinessPremiseVisited, Integer repaymentTrack, Integer creaditWorthiness,
									   String loanLiabilityRatio, Integer competition, Double loanAmountRecomandation, Integer tenureRecomandation,
									   Integer moratoriumRecomandation, Double interestRateRecomandation, Integer installmentRecomandation) {
		super();
		this.applicationId = applicationId;
		this.purposeOfLoan = purposeOfLoan;
		this.clientType = clientType;
		this.isBusinessPremiseVisited = isBusinessPremiseVisited;
		this.repaymentTrack = repaymentTrack;
		this.creaditWorthiness = creaditWorthiness;
		this.loanLiabilityRatio = loanLiabilityRatio;
		this.competition = competition;
		this.loanAmountRecomandation = loanAmountRecomandation;
		this.tenureRecomandation = tenureRecomandation;
		this.moratoriumRecomandation = moratoriumRecomandation;
		this.interestRateRecomandation = interestRateRecomandation;
		this.installmentRecomandation = installmentRecomandation;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Integer getClientType() {
		return clientType;
	}
	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}
	public Boolean getIsBusinessPremiseVisited() {
		return isBusinessPremiseVisited;
	}
	public void setIsBusinessPremiseVisited(Boolean isBusinessPremiseVisited) {
		this.isBusinessPremiseVisited = isBusinessPremiseVisited;
	}
	public Integer getRepaymentTrack() {
		return repaymentTrack;
	}
	public void setRepaymentTrack(Integer repaymentTrack) {
		this.repaymentTrack = repaymentTrack;
	}
	public Integer getCreaditWorthiness() {
		return creaditWorthiness;
	}
	public void setCreaditWorthiness(Integer creaditWorthiness) {
		this.creaditWorthiness = creaditWorthiness;
	}
	public String getLoanLiabilityRatio() {
		return loanLiabilityRatio;
	}
	public void setLoanLiabilityRatio(String loanLiabilityRatio) {
		this.loanLiabilityRatio = loanLiabilityRatio;
	}
	public Integer getCompetition() {
		return competition;
	}
	public void setCompetition(Integer competition) {
		this.competition = competition;
	}
	public Double getLoanAmountRecomandation() {
		return loanAmountRecomandation;
	}
	public void setLoanAmountRecomandation(Double loanAmountRecomandation) {
		this.loanAmountRecomandation = loanAmountRecomandation;
	}
	public Integer getTenureRecomandation() {
		return tenureRecomandation;
	}
	public void setTenureRecomandation(Integer tenureRecomandation) {
		this.tenureRecomandation = tenureRecomandation;
	}
	public Integer getMoratoriumRecomandation() {
		return moratoriumRecomandation;
	}
	public void setMoratoriumRecomandation(Integer moratoriumRecomandation) {
		this.moratoriumRecomandation = moratoriumRecomandation;
	}
	public Double getInterestRateRecomandation() {
		return interestRateRecomandation;
	}
	public void setInterestRateRecomandation(Double interestRateRecomandation) {
		this.interestRateRecomandation = interestRateRecomandation;
	}
	public Integer getInstallmentRecomandation() {
		return installmentRecomandation;
	}
	public void setInstallmentRecomandation(Integer installmentRecomandation) {
		this.installmentRecomandation = installmentRecomandation;
	}
	public Boolean getIsLoanassessmentDetailsFilled() {
		return isLoanassessmentDetailsFilled;
	}
	public void setIsLoanassessmentDetailsFilled(Boolean isLoanassessmentDetailsFilled) {
		this.isLoanassessmentDetailsFilled = isLoanassessmentDetailsFilled;
	}

	public Double getIncomeFromOccupation() {
		return incomeFromOccupation;
	}

	public void setIncomeFromOccupation(Double incomeFromOccupation) {
		this.incomeFromOccupation = incomeFromOccupation;
	}

	public Double getExistingExpensesOfFamily() {
		return existingExpensesOfFamily;
	}

	public void setExistingExpensesOfFamily(Double existingExpensesOfFamily) {
		this.existingExpensesOfFamily = existingExpensesOfFamily;
	}

	public Double getNetSaving() {
		return netSaving;
	}

	public void setNetSaving(Double netSaving) {
		this.netSaving = netSaving;
	}

	public Double getExpectedIncome() {
		return expectedIncome;
	}

	public void setExpectedIncome(Double expectedIncome) {
		this.expectedIncome = expectedIncome;
	}

	public Double getTotalCashFlow() {
		return totalCashFlow;
	}

	public void setTotalCashFlow(Double totalCashFlow) {
		this.totalCashFlow = totalCashFlow;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
