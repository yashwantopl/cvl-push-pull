package com.capitaworld.service.loans.model.common;

import java.io.Serializable;
import java.util.Date;

public class HomeLoanEligibilityRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer employmentType;
	private Long income;
	private Long obligation;
	private Date dateOfBirth;
	private Integer tenure;
	private Long stampValue;
	private Long marketValue;
	
	public HomeLoanEligibilityRequest(){
		super();
	}
	public Integer getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(Integer employmentType) {
		this.employmentType = employmentType;
	}
	public Long getIncome() {
		return income;
	}
	public void setIncome(Long income) {
		this.income = income;
	}
	public Long getObligation() {
		return obligation;
	}
	public void setObligation(Long obligation) {
		this.obligation = obligation;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Integer getTenure() {
		return tenure;
	}
	public void setTenure(Integer tenure) {
		this.tenure = tenure;
	}
	public Long getStampValue() {
		return stampValue;
	}
	public void setStampValue(Long stampValue) {
		this.stampValue = stampValue;
	}
	public Long getMarketValue() {
		return marketValue;
	}
	public void setMarketValue(Long marketValue) {
		this.marketValue = marketValue;
	}
	
}
