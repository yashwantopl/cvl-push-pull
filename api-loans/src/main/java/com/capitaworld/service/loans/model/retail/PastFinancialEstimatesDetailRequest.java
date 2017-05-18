package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the fs_corporate_past_financial_estimates_details
 * database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PastFinancialEstimatesDetailRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long applicationId;

	private Double currentAssets;

	private Double currentLiabilities;

	private Double debt;

	private Double ebitda;

	private String financialYear;

	private Double fixedAssets;

	private Double netWorth;

	private Double pat;

	private Double sales;

	private Boolean isActive = true;

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public PastFinancialEstimatesDetailRequest() {
	}

	public PastFinancialEstimatesDetailRequest(String financialYear) {
		this.financialYear = financialYear;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Double getCurrentAssets() {
		return this.currentAssets;
	}

	public void setCurrentAssets(Double currentAssets) {
		this.currentAssets = currentAssets;
	}

	public Double getCurrentLiabilities() {
		return this.currentLiabilities;
	}

	public void setCurrentLiabilities(Double currentLiabilities) {
		this.currentLiabilities = currentLiabilities;
	}

	public Double getDebt() {
		return this.debt;
	}

	public void setDebt(Double debt) {
		this.debt = debt;
	}

	public Double getEbitda() {
		return this.ebitda;
	}

	public void setEbitda(Double ebitda) {
		this.ebitda = ebitda;
	}

	public String getFinancialYear() {
		return this.financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public Double getFixedAssets() {
		return this.fixedAssets;
	}

	public void setFixedAssets(Double fixedAssets) {
		this.fixedAssets = fixedAssets;
	}

	public Double getNetWorth() {
		return this.netWorth;
	}

	public void setNetWorth(Double netWorth) {
		this.netWorth = netWorth;
	}

	public Double getPat() {
		return this.pat;
	}

	public void setPat(Double pat) {
		this.pat = pat;
	}

	public Double getSales() {
		return this.sales;
	}

	public void setSales(Double sales) {
		this.sales = sales;
	}

}