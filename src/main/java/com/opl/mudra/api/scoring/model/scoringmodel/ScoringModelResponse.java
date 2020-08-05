package com.opl.mudra.api.scoring.model.scoringmodel;

public class ScoringModelResponse {

	private Long id;

	private String name;

	private Long orgId;

	private Double totalScore;
	
	private Double totalCoAppScore;

	private Boolean isProportionateScoreConsider;

	private Double proportionateScore;

	private Integer financialTypeId;
	
	private Long  loanPurposeModelId;
	
	private Integer employmentTypeId;

	public Boolean getIsProportionateScoreConsider() {
		return isProportionateScoreConsider;
	}

	public void setIsProportionateScoreConsider(Boolean proportionateScoreConsider) {
		isProportionateScoreConsider = proportionateScoreConsider;
	}

	public Double getProportionateScore() {
		return proportionateScore;
	}

	public void setProportionateScore(Double proportionateScore) {
		this.proportionateScore = proportionateScore;
	}

	public Double getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Integer getFinancialTypeId() {
		return financialTypeId;
	}

	public void setFinancialTypeId(Integer financialTypeId) {
		this.financialTypeId = financialTypeId;
	}

	public Long getLoanPurposeModelId() {
		return loanPurposeModelId;
	}

	public void setLoanPurposeModelId(Long loanPurposeModelId) {
		this.loanPurposeModelId = loanPurposeModelId;
	}

	public Double getTotalCoAppScore() {
		return totalCoAppScore;
	}

	public void setTotalCoAppScore(Double totalCoAppScore) {
		this.totalCoAppScore = totalCoAppScore;
	}
	
	public Integer getEmploymentTypeId() {
		return employmentTypeId;
	}

	public void setEmploymentTypeId(Integer employmentTypeId) {
		this.employmentTypeId = employmentTypeId;
	}

	@Override
	public String toString() {
		return "ScoringModelResponse [id=" + id + ", name=" + name + ", orgId=" + orgId + ", totalScore=" + totalScore
				+ ", isProportionateScoreConsider=" + isProportionateScoreConsider + ", proportionateScore="
				+ proportionateScore + ", financialTypeId=" + financialTypeId + "]";
	}

}
