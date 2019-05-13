package com.capitaworld.service.loans.model.score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoringRequestLoans {

    /*private Long scoringModelId;*/

    private Long fsDigit;

    private Long fpProductId;
    
    private Long loanPurposeModelId;
    
    private Long orgId;

    /*private Long applicationId;*/

    private Long scoringModelId;
    private Long scoringModelCoAppId;
	private Long applicationId;

	private Integer financialTypeIdProduct;

	private Double emi;
	
	private Double roi;

	private Long userId;
	
	private Double tenureFS;
	private Double elAmountBasedOnIncome;
	private Double elAmountOnAverageScoring;
	private Double tenureScoring;
	private Double ageFS;
	private Double tenureFP;
	private Integer incomeType;
	private Double nmi;
	private Double gmi;
	private Double foir;
	
	private List<Double> coAppIncomeArray;
	private Double coApp1Income;
	private Double coApp2Income;
	private Double coApp3Income;
	private Boolean isConsiderCoApp;
	private Boolean isSetGrossNetIncome;
	private Integer employmentType;
	private Double marketValue;
	private Double existingObligation;
	private Double purCunsExpRepAndRenovCost;
	private Double ltvScoring;
	private Double loanAmountBorrower;
	private Double maximumLoanAmountLender;
	private Double eligibleTenure;
	private Double timesMultiplier;
	private Double monthlyObligation;
	
	private Integer noOfCoApplicantFP;
	private Integer noOfCoApplicantFS;
	private Integer incomeTypeMultiplier;
	//Boolean Flags
	private Boolean isSetTimesMultiplierIncome;
	private Boolean isSetLTV;
	private Boolean isSetPurCunsExpRepAndRenovCost;
	private Boolean isSetMarketValue;
	
	private ScoreParameterRequestLoans scoreParameterRequestLoans;

	private ScoreParameterNTBRequest scoreParameterNTBRequest;

	private ScoreParameterRetailRequest scoreParameterRetailRequest;

	private Double eligibleLoanAmountCircular;

	public Double getEligibleLoanAmountCircular() {
		return eligibleLoanAmountCircular;
	}

	public void setEligibleLoanAmountCircular(Double eligibleLoanAmountCircular) {
		this.eligibleLoanAmountCircular = eligibleLoanAmountCircular;
	}

	public ScoreParameterRetailRequest getScoreParameterRetailRequest() {
		return scoreParameterRetailRequest;
	}

	public void setScoreParameterRetailRequest(ScoreParameterRetailRequest scoreParameterRetailRequest) {
		this.scoreParameterRetailRequest = scoreParameterRetailRequest;
	}

	public Integer getFinancialTypeIdProduct() {
		return financialTypeIdProduct;
	}

	public void setFinancialTypeIdProduct(Integer financialTypeIdProduct) {
		this.financialTypeIdProduct = financialTypeIdProduct;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getFsDigit() {
		return fsDigit;
	}
	public void setFsDigit(Long fsDigit) {
		this.fsDigit = fsDigit;
	}
	public Long getFpProductId() {
		return fpProductId;
	}
	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
	}
	public Long getScoringModelId() {
		return scoringModelId;
	}
	public void setScoringModelId(Long scoringModelId) {
		this.scoringModelId = scoringModelId;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public ScoreParameterRequestLoans getScoreParameterRequestLoans() {
		return scoreParameterRequestLoans;
	}

	public void setScoreParameterRequestLoans(ScoreParameterRequestLoans scoreParameterRequestLoans) {
		this.scoreParameterRequestLoans = scoreParameterRequestLoans;
	}


	public ScoreParameterNTBRequest getScoreParameterNTBRequest() {
		return scoreParameterNTBRequest;
	}
	public void setScoreParameterNTBRequest(ScoreParameterNTBRequest scoreParameterNTBRequest) {
		this.scoreParameterNTBRequest = scoreParameterNTBRequest;
	}

	public Double getEmi() {
		return emi;
	}

	public void setEmi(Double emi) {
		this.emi = emi;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Double getTenureFS() {
		return tenureFS;
	}

	public void setTenureFS(Double tenureFS) {
		this.tenureFS = tenureFS;
	}

	public Double getTenureScoring() {
		return tenureScoring;
	}

	public void setTenureScoring(Double tenureScoring) {
		this.tenureScoring = tenureScoring;
	}

	public Double getAgeFS() {
		return ageFS;
	}

	public void setAgeFS(Double ageFS) {
		this.ageFS = ageFS;
	}

	public Double getTenureFP() {
		return tenureFP;
	}

	public void setTenureFP(Double tenureFP) {
		this.tenureFP = tenureFP;
	}

	public Double getRoi() {
		return roi;
	}

	public void setRoi(Double roi) {
		this.roi = roi;
	}

	public List<Double> getCoAppIncomeArray() {
		if(coAppIncomeArray == null) {
			coAppIncomeArray = new ArrayList<Double>();
		}
		Collections.sort(coAppIncomeArray,Collections.reverseOrder());
		return coAppIncomeArray;
	}

	public Double getCoApp1Income() {
		return coApp1Income;
	}

	public void setCoApp1Income(Double coApp1Income) {
		if(coApp1Income != null) {
			getCoAppIncomeArray().add(coApp1Income);
		}
		this.coApp1Income = coApp1Income;
	}

	public Double getCoApp2Income() {
		return coApp2Income;
	}

	public void setCoApp2Income(Double coApp2Income) {
		if(coApp2Income != null) {
			getCoAppIncomeArray().add(coApp2Income);
		}
		this.coApp2Income = coApp2Income;
	}

	public Double getCoApp3Income() {
		return coApp3Income;
	}

	public void setCoApp3Income(Double coApp3Income) {
		if(coApp3Income != null) {
			getCoAppIncomeArray().add(coApp3Income);
		}
		this.coApp3Income = coApp3Income;
	}

	public Boolean getIsConsiderCoApp() {
		return isConsiderCoApp;
	}

	public void setIsConsiderCoApp(Boolean isConsiderCoApp) {
		this.isConsiderCoApp = isConsiderCoApp;
	}

	public Integer getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(Integer incomeType) {
		this.incomeType = incomeType;
	}

	public Double getNmi() {
		return nmi;
	}

	public void setNmi(Double nmi) {
		this.nmi = nmi;
	}

	public Double getGmi() {
		return gmi;
	}

	public void setGmi(Double gmi) {
		this.gmi = gmi;
	}

	public void setCoAppIncomeArray(List<Double> coAppIncomeArray) {
		this.coAppIncomeArray = coAppIncomeArray;
	}

	public Boolean getIsSetGrossNetIncome() {
		return isSetGrossNetIncome;
	}

	public void setIsSetGrossNetIncome(Boolean isSetGrossNetIncome) {
		this.isSetGrossNetIncome = isSetGrossNetIncome;
	}

	public Long getLoanPurposeModelId() {
		return loanPurposeModelId;
	}

	public void setLoanPurposeModelId(Long loanPurposeModelId) {
		this.loanPurposeModelId = loanPurposeModelId;
	}

	public Long getScoringModelCoAppId() {
		return scoringModelCoAppId;
	}

	public void setScoringModelCoAppId(Long scoringModelCoAppId) {
		this.scoringModelCoAppId = scoringModelCoAppId;
	}

	public Double getFoir() {
		return foir;
	}

	public void setFoir(Double foir) {
		this.foir = foir;
	}

	public Integer getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(Integer employmentType) {
		this.employmentType = employmentType;
	}

	public Double getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(Double marketValue) {
		this.marketValue = marketValue;
	}

	public Double getExistingObligation() {
		return existingObligation;
	}

	public void setExistingObligation(Double existingObligation) {
		this.existingObligation = existingObligation;
	}

	public Double getPurCunsExpRepAndRenovCost() {
		return purCunsExpRepAndRenovCost;
	}

	public void setPurCunsExpRepAndRenovCost(Double purCunsExpRepAndRenovCost) {
		this.purCunsExpRepAndRenovCost = purCunsExpRepAndRenovCost;
	}

	public Double getLtvScoring() {
		return ltvScoring;
	}

	public void setLtvScoring(Double ltvScoring) {
		this.ltvScoring = ltvScoring;
	}

	public Double getLoanAmountBorrower() {
		return loanAmountBorrower;
	}

	public void setLoanAmountBorrower(Double loanAmountBorrower) {
		this.loanAmountBorrower = loanAmountBorrower;
	}

	public Double getMaximumLoanAmountLender() {
		return maximumLoanAmountLender;
	}

	public void setMaximumLoanAmountLender(Double maximumLoanAmountLender) {
		this.maximumLoanAmountLender = maximumLoanAmountLender;
	}

	public Double getEligibleTenure() {
		return eligibleTenure;
	}

	public void setEligibleTenure(Double eligibleTenure) {
		this.eligibleTenure = eligibleTenure;
	}

	public Double getTimesMultiplier() {
		return timesMultiplier;
	}

	public void setTimesMultiplier(Double timesMultiplier) {
		this.timesMultiplier = timesMultiplier;
	}

	public Double getMonthlyObligation() {
		return monthlyObligation;
	}

	public void setMonthlyObligation(Double monthlyObligation) {
		this.monthlyObligation = monthlyObligation;
	}

	public Integer getNoOfCoApplicantFP() {
		return noOfCoApplicantFP;
	}

	public void setNoOfCoApplicantFP(Integer noOfCoApplicantFP) {
		this.noOfCoApplicantFP = noOfCoApplicantFP;
	}

	public Integer getNoOfCoApplicantFS() {
		return noOfCoApplicantFS;
	}

	public void setNoOfCoApplicantFS(Integer noOfCoApplicantFS) {
		this.noOfCoApplicantFS = noOfCoApplicantFS;
	}

	public Integer getIncomeTypeMultiplier() {
		return incomeTypeMultiplier;
	}

	public void setIncomeTypeMultiplier(Integer incomeTypeMultiplier) {
		this.incomeTypeMultiplier = incomeTypeMultiplier;
	}

	public Boolean getIsSetTimesMultiplierIncome() {
		return isSetTimesMultiplierIncome;
	}

	public void setIsSetTimesMultiplierIncome(Boolean isSetTimesMultiplierIncome) {
		this.isSetTimesMultiplierIncome = isSetTimesMultiplierIncome;
	}

	public Boolean getIsSetLTV() {
		return isSetLTV;
	}

	public void setIsSetLTV(Boolean isSetLTV) {
		this.isSetLTV = isSetLTV;
	}

	public Boolean getIsSetPurCunsExpRepAndRenovCost() {
		return isSetPurCunsExpRepAndRenovCost;
	}

	public void setIsSetPurCunsExpRepAndRenovCost(Boolean isSetPurCunsExpRepAndRenovCost) {
		this.isSetPurCunsExpRepAndRenovCost = isSetPurCunsExpRepAndRenovCost;
	}

	public Boolean getIsSetMarketValue() {
		return isSetMarketValue;
	}

	public void setIsSetMarketValue(Boolean isSetMarketValue) {
		this.isSetMarketValue = isSetMarketValue;
	}

	public Double getElAmountBasedOnIncome() {
		return elAmountBasedOnIncome;
	}

	public void setElAmountBasedOnIncome(Double elAmountBasedOnIncome) {
		this.elAmountBasedOnIncome = elAmountBasedOnIncome;
	}

	public Double getElAmountOnAverageScoring() {
		return elAmountOnAverageScoring;
	}

	public void setElAmountOnAverageScoring(Double elAmountOnAverageScoring) {
		this.elAmountOnAverageScoring = elAmountOnAverageScoring;
	}

	@Override
	public String toString() {
		return "ScoringRequestLoans{" +
				"fsDigit=" + fsDigit +
				", fpProductId=" + fpProductId +
				", scoringModelId=" + scoringModelId +
				", applicationId=" + applicationId +
				", financialTypeIdProduct=" + financialTypeIdProduct +
				", emi=" + emi +
				", userId=" + userId +
				", scoreParameterRequestLoans=" + scoreParameterRequestLoans +
				", scoreParameterNTBRequest=" + scoreParameterNTBRequest +
				", scoreParameterRetailRequest=" + scoreParameterRetailRequest +
				", eligibleLoanAmountCircular=" + eligibleLoanAmountCircular +
				'}';
	}
}
