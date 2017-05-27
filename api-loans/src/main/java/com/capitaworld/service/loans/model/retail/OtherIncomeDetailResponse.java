package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;

public class OtherIncomeDetailResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String grossIncome;
	private String incomeDetails;
	private String incomeHead;
	private String netIncome;
	public String getGrossIncome() {
		return grossIncome;
	}
	public void setGrossIncome(String grossIncome) {
		this.grossIncome = grossIncome;
	}
	public String getIncomeDetails() {
		return incomeDetails;
	}
	public void setIncomeDetails(String incomeDetails) {
		this.incomeDetails = incomeDetails;
	}
	public String getIncomeHead() {
		return incomeHead;
	}
	public void setIncomeHead(String incomeHead) {
		this.incomeHead = incomeHead;
	}
	public String getNetIncome() {
		return netIncome;
	}
	public void setNetIncome(String netIncome) {
		this.netIncome = netIncome;
	}
	
	
}
