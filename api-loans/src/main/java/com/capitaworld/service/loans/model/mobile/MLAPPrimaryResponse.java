package com.capitaworld.service.loans.model.mobile;

public class MLAPPrimaryResponse{
	private static final long serialVersionUID = 1L;
	
	private Long loanPurpose;

	private String loanPurposeOther;
	
	private String ownerName;
	
	private Double tenure;
	
	private Double amount;
	
	private Double builtUpArea;
	
	private Double landArea;
	
	private Integer propertyAgeInMonth;

	private Integer propertyAgeInYear;
	
	private Integer occupationStatus;

	public Long getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(Long loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public String getLoanPurposeOther() {
		return loanPurposeOther;
	}

	public void setLoanPurposeOther(String loanPurposeOther) {
		this.loanPurposeOther = loanPurposeOther;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Double getTenure() {
		return tenure;
	}

	public void setTenure(Double tenure) {
		this.tenure = tenure;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getBuiltUpArea() {
		return builtUpArea;
	}

	public void setBuiltUpArea(Double builtUpArea) {
		this.builtUpArea = builtUpArea;
	}

	public Double getLandArea() {
		return landArea;
	}

	public void setLandArea(Double landArea) {
		this.landArea = landArea;
	}

	public Integer getPropertyAgeInMonth() {
		return propertyAgeInMonth;
	}

	public void setPropertyAgeInMonth(Integer propertyAgeInMonth) {
		this.propertyAgeInMonth = propertyAgeInMonth;
	}

	public Integer getPropertyAgeInYear() {
		return propertyAgeInYear;
	}

	public void setPropertyAgeInYear(Integer propertyAgeInYear) {
		this.propertyAgeInYear = propertyAgeInYear;
	}

	public Integer getOccupationStatus() {
		return occupationStatus;
	}

	public void setOccupationStatus(Integer occupationStatus) {
		this.occupationStatus = occupationStatus;
	}
	

}
