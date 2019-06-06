package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;

public class BankRelationshipRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;
	private String bank;
	private Integer sinceYear;
	private Integer sinceMonth;
	private Integer from;
	private Boolean isActive;
	private Boolean isSalaryAccount;
	private String sinceWhen;
	private Long coApplicantId;
	
	public String getSinceWhen() {
		return sinceWhen;
	}
	public void setSinceWhen(String sinceWhen) {
		this.sinceWhen = sinceWhen;
	}
	public Long getId() {
		return id;
	}
	public String getBank() {
		return bank;
	}
	public Integer getSinceYear() {
		return sinceYear;
	}
	public Integer getSinceMonth() {
		return sinceMonth;
	}
	public Integer getFrom() {
		return from;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public void setSinceYear(Integer sinceYear) {
		this.sinceYear = sinceYear;
	}
	public void setSinceMonth(Integer sinceMonth) {
		this.sinceMonth = sinceMonth;
	}
	public void setFrom(Integer from) {
		this.from = from;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsSalaryAccount() {
		return isSalaryAccount;
	}
	public void setIsSalaryAccount(Boolean isSalaryAccount) {
		this.isSalaryAccount = isSalaryAccount;
	}
	public Long getCoApplicantId() {
		return coApplicantId;
	}

	public void setCoApplicantId(Long coApplicantId) {
		this.coApplicantId = coApplicantId;
	}
}
