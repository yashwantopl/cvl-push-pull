package com.capitaworld.service.loans.model.retail;

public class CoApplicantScoreRequest {

	private Long id;

	private Boolean isIncomeConsider;

	public CoApplicantScoreRequest() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsIncomeConsider() {
		return isIncomeConsider;
	}

	public void setIsIncomeConsider(Boolean isIncomeConsider) {
		this.isIncomeConsider = isIncomeConsider;
	}

	@Override
	public String toString() {
		return "CoApplicantScoreRequest [id=" + id + ", isIncomeConsider=" + isIncomeConsider + "]";
	}
}
