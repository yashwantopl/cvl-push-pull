package com.capitaworld.service.loans.model.common;

import java.util.Date;

public class LoanEligibilility {

	private Long income;
	private Long obligation;
	private Date dateOfBirth;
	private Integer tenure;
	
	public LoanEligibilility(){
		super();
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
	
}
