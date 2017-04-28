package com.capitaworld.service.loans.model;

import java.io.Serializable;


/**
 * The persistent class for the fs_corporate_future_financial_estimates_details database table.
 * 
 */
public class FutureFinancialEstimatesDetailRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long applicationId;

	private Double currentAssets;

	private Double currentLiabilities;

	private Double ebitda;

	private String financialYear;

	private Double fixedAssets;

	private Boolean isActive = true;

	private Double longTermDebt;

	private Double netWorth;

	private Double pat;

	private Double sales;
	
	

	public FutureFinancialEstimatesDetailRequest() {
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

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Double getLongTermDebt() {
		return this.longTermDebt;
	}

	public void setLongTermDebt(Double longTermDebt) {
		this.longTermDebt = longTermDebt;
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