package com.opl.mudra.api.oneform.model;

import java.io.Serializable;

public class AmountCriteriaResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer bankId;

	private Integer propertyType;
	
	private Integer employmentType;
	
	private Integer loanType;
	
	private Long min;

	private Long max;

	private Float roi;
	
	private Boolean isActive;

	public AmountCriteriaResponse() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public Long getMin() {
		return min;
	}

	public void setMin(Long min) {
		this.min = min;
	}

	public Long getMax() {
		return max;
	}

	public void setMax(Long max) {
		this.max = max;
	}

	public Float getRoi() {
		return roi;
	}

	public void setRoi(Float roi) {
		this.roi = roi;
	}


	public Integer getLoanType() {
		return loanType;
	}

	public void setLoanType(Integer loanType) {
		this.loanType = loanType;
	}

	public Integer getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(Integer propertyType) {
		this.propertyType = propertyType;
	}

	public Integer getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(Integer employmentType) {
		this.employmentType = employmentType;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
}
