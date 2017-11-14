package com.capitaworld.service.loans.model.teaser.finalview;

public class GuarantorsCorporateDetailResponse {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Boolean isActive = true;

	private String name;

	private String occupation;

	private String propertiesOwned;

	private String propertyType;
	
	private String industrylist;

	private String sectorlist;
	
	private String constitutionId;
	
	private String panNo;
	
	private String profitAfterTax;
	
	private String address;
	
	private String contactNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getPropertiesOwned() {
		return propertiesOwned;
	}

	public void setPropertiesOwned(String propertiesOwned) {
		this.propertiesOwned = propertiesOwned;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getIndustrylist() {
		return industrylist;
	}

	public void setIndustrylist(String industrylist) {
		this.industrylist = industrylist;
	}

	public String getSectorlist() {
		return sectorlist;
	}

	public void setSectorlist(String sectorlist) {
		this.sectorlist = sectorlist;
	}

	public String getConstitutionId() {
		return constitutionId;
	}

	public void setConstitutionId(String constitutionId) {
		this.constitutionId = constitutionId;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getProfitAfterTax() {
		return profitAfterTax;
	}

	public void setProfitAfterTax(String profitAfterTax) {
		this.profitAfterTax = profitAfterTax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	

}
