package com.opl.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the fs_corporate_finance_means_details database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FsPastPerformanceDetailsRequest implements Serializable {
	private static final Long serialVersionUID = 1L;

	private Long id;

	private Long applicationId;

	private Boolean isActive;

	private Double netSalesPastYear1;

	private Double netSalesPastYear2;

	private Double netSalesPresentYear;

	private Double netSalesNextYear;

	private Double netProfitPastYear1;

	private Double netProfitPastYear2;

	private Double netProfitPresentYear;

	private Double netProfitNextYear;

	private Double compNetWorthPastYear1;

	private Double compNetWorthPastYear2;

	private Double compNetWorthPresentYear;

	private Double compNetWorthNextYear;

	private String contingentLiabilities;


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

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}

	public Double getNetSalesPastYear1() {
		return netSalesPastYear1;
	}

	public void setNetSalesPastYear1(Double netSalesPastYear1) {
		this.netSalesPastYear1 = netSalesPastYear1;
	}

	public Double getNetSalesPastYear2() {
		return netSalesPastYear2;
	}

	public void setNetSalesPastYear2(Double netSalesPastYear2) {
		this.netSalesPastYear2 = netSalesPastYear2;
	}

	public Double getNetSalesPresentYear() {
		return netSalesPresentYear;
	}

	public void setNetSalesPresentYear(Double netSalesPresentYear) {
		this.netSalesPresentYear = netSalesPresentYear;
	}

	public Double getNetSalesNextYear() {
		return netSalesNextYear;
	}

	public void setNetSalesNextYear(Double netSalesNextYear) {
		this.netSalesNextYear = netSalesNextYear;
	}

	public Double getNetProfitPastYear1() {
		return netProfitPastYear1;
	}

	public void setNetProfitPastYear1(Double netProfitPastYear1) {
		this.netProfitPastYear1 = netProfitPastYear1;
	}

	public Double getNetProfitPastYear2() {
		return netProfitPastYear2;
	}

	public void setNetProfitPastYear2(Double netProfitPastYear2) {
		this.netProfitPastYear2 = netProfitPastYear2;
	}

	public Double getNetProfitPresentYear() {
		return netProfitPresentYear;
	}

	public void setNetProfitPresentYear(Double netProfitPresentYear) {
		this.netProfitPresentYear = netProfitPresentYear;
	}

	public Double getNetProfitNextYear() {
		return netProfitNextYear;
	}

	public void setNetProfitNextYear(Double netProfitNextYear) {
		this.netProfitNextYear = netProfitNextYear;
	}

	public Double getCompNetWorthPastYear1() {
		return compNetWorthPastYear1;
	}

	public void setCompNetWorthPastYear1(Double compNetWorthPastYear1) {
		this.compNetWorthPastYear1 = compNetWorthPastYear1;
	}

	public Double getCompNetWorthPastYear2() {
		return compNetWorthPastYear2;
	}

	public void setCompNetWorthPastYear2(Double compNetWorthPastYear2) {
		this.compNetWorthPastYear2 = compNetWorthPastYear2;
	}

	public Double getCompNetWorthPresentYear() {
		return compNetWorthPresentYear;
	}

	public void setCompNetWorthPresentYear(Double compNetWorthPresentYear) {
		this.compNetWorthPresentYear = compNetWorthPresentYear;
	}

	public Double getCompNetWorthNextYear() {
		return compNetWorthNextYear;
	}

	public void setCompNetWorthNextYear(Double compNetWorthNextYear) {
		this.compNetWorthNextYear = compNetWorthNextYear;
	}

	public String getContingentLiabilities() {
		return contingentLiabilities;
	}

	public void setContingentLiabilities(String contingentLiabilities) {
		this.contingentLiabilities = contingentLiabilities;
	}
}