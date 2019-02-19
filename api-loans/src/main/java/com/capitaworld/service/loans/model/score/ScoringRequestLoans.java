package com.capitaworld.service.loans.model.score;

public class ScoringRequestLoans {

    /*private Long scoringModelId;*/

    private Long fsDigit;

    private Long fpProductId;

    /*private Long applicationId;*/

    private Long scoringModelId;
	private Long applicationId;

	private Integer financialTypeIdProduct;

	private Double emi;

	private Long userId;

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
