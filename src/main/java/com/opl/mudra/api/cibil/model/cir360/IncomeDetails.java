package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class IncomeDetails {

	@JsonProperty("occupation")
	private String occupation;

	@JsonProperty("monthlyIncome")
	private String monthlyIncome;

	@JsonProperty("monthlyExpense")
	private String monthlyExpense;

	@JsonProperty("povertyIndex")
	private String povertyIndex;

	@JsonProperty("assetOwnership")
	private String assetOwnership;

	@JsonProperty("seq")
	private Integer seq;

	@JsonProperty("reportedDate")
	private String reportedDate;

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getMonthlyExpense() {
		return monthlyExpense;
	}

	public void setMonthlyExpense(String monthlyExpense) {
		this.monthlyExpense = monthlyExpense;
	}

	public String getPovertyIndex() {
		return povertyIndex;
	}

	public void setPovertyIndex(String povertyIndex) {
		this.povertyIndex = povertyIndex;
	}

	public String getAssetOwnership() {
		return assetOwnership;
	}

	public void setAssetOwnership(String assetOwnership) {
		this.assetOwnership = assetOwnership;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}

}
