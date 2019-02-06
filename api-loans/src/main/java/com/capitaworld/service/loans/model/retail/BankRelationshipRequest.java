package com.capitaworld.service.loans.model.retail;

public class BankRelationshipRequest {

	private Long id;
	private String bank;
	private Integer sinceYear;
	private Integer sinceMonth;
	private Integer from;
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
	
	
	
}
