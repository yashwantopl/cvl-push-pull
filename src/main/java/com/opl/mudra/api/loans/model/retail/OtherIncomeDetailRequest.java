package com.opl.mudra.api.loans.model.retail;

import java.io.Serializable;

/**
 * @author Sanket
 *
 */
public class OtherIncomeDetailRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private Double grossIncome;

	private Integer incomeDetailsId;

	private String incomeHead;

	private Boolean isActive = true;

	private Double netIncome;
	
	private String incomeDetailsType;
	
	private String grossIncomeString;
	
	private String netIncomeString;

	public String getGrossIncomeString() {
		return grossIncomeString;
	}

	public void setGrossIncomeString(String grossIncomeString) {
		this.grossIncomeString = grossIncomeString;
	}

	public String getNetIncomeString() {
		return netIncomeString;
	}

	public void setNetIncomeString(String netIncomeString) {
		this.netIncomeString = netIncomeString;
	}

	public String getIncomeDetailsType() {
		return incomeDetailsType;
	}

	public void setIncomeDetailsType(String incomeDetailsType) {
		this.incomeDetailsType = incomeDetailsType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getGrossIncome() {
		return grossIncome;
	}

	public void setGrossIncome(Double grossIncome) {
		this.grossIncome = grossIncome;
	}

	public Integer getIncomeDetailsId() {
		return incomeDetailsId;
	}

	public void setIncomeDetailsId(Integer incomeDetailsId) {
		this.incomeDetailsId = incomeDetailsId;
	}

	public String getIncomeHead() {
		return incomeHead;
	}

	public void setIncomeHead(String incomeHead) {
		this.incomeHead = incomeHead;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Double getNetIncome() {
		return netIncome;
	}

	public void setNetIncome(Double netIncome) {
		this.netIncome = netIncome;
	}

	
	
}
