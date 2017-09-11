package com.capitaworld.service.loans.model.retail;

import com.capitaworld.service.loans.model.Address;

public class CoApplicantRequest {

	private Long id;
	private Long clientId;
	private Long applicationId;
	private String firstName;
	private Integer relationshipWithApplicant;
	private Boolean isActive;
	private Integer titleId;
	private String middleName;
	private String lastName;
	private Integer statusId;
	private Integer occupationId;
	private String pan;
	private String aadharNumber;
	private String nameAsPerAadharCard;
	private Double monthlyIncome;
	private Integer currencyId;
	private Address firstAddress;
	private Address secondAddress;
	private Boolean addressSameAs;
	private String contactNo;
	private String companyName;
	private Integer employedWithId;
	private String employedWithOther;
	private String entityName;
	private Integer industryTypeId;
	private String industryTypeOther;
	private Integer selfEmployedOccupationId;
	private String selfEmployedOccupationOther;
	private Double landSize;
	private Integer alliedActivityId;

	private Integer genderId;
	private Integer date;
	private Integer month;
	private Integer year;
	
	private Boolean isCoApp1DetailsFilled;
	private Boolean isCoApp2DetailsFilled;
	
	private String detailsFilledCount;

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Integer getRelationshipWithApplicant() {
		return relationshipWithApplicant;
	}

	public void setRelationshipWithApplicant(Integer relationshipWithApplicant) {
		this.relationshipWithApplicant = relationshipWithApplicant;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public Integer getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(Integer occupationId) {
		this.occupationId = occupationId;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getNameAsPerAadharCard() {
		return nameAsPerAadharCard;
	}

	public void setNameAsPerAadharCard(String nameAsPerAadharCard) {
		this.nameAsPerAadharCard = nameAsPerAadharCard;
	}

	public Double getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(Double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

	public Address getFirstAddress() {
		return firstAddress;
	}

	public void setFirstAddress(Address firstAddress) {
		this.firstAddress = firstAddress;
	}

	public Address getSecondAddress() {
		return secondAddress;
	}

	public void setSecondAddress(Address secondAddress) {
		this.secondAddress = secondAddress;
	}

	public Boolean getAddressSameAs() {
		return addressSameAs;
	}

	public void setAddressSameAs(Boolean addressSameAs) {
		this.addressSameAs = addressSameAs;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getEmployedWithId() {
		return employedWithId;
	}

	public void setEmployedWithId(Integer employedWithId) {
		this.employedWithId = employedWithId;
	}

	public String getEmployedWithOther() {
		return employedWithOther;
	}

	public void setEmployedWithOther(String employedWithOther) {
		this.employedWithOther = employedWithOther;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public Integer getIndustryTypeId() {
		return industryTypeId;
	}

	public void setIndustryTypeId(Integer industryTypeId) {
		this.industryTypeId = industryTypeId;
	}

	public String getIndustryTypeOther() {
		return industryTypeOther;
	}

	public void setIndustryTypeOther(String industryTypeOther) {
		this.industryTypeOther = industryTypeOther;
	}

	public Integer getSelfEmployedOccupationId() {
		return selfEmployedOccupationId;
	}

	public void setSelfEmployedOccupationId(Integer selfEmployedOccupationId) {
		this.selfEmployedOccupationId = selfEmployedOccupationId;
	}

	public String getSelfEmployedOccupationOther() {
		return selfEmployedOccupationOther;
	}

	public void setSelfEmployedOccupationOther(String selfEmployedOccupationOther) {
		this.selfEmployedOccupationOther = selfEmployedOccupationOther;
	}

	public Double getLandSize() {
		return landSize;
	}

	public void setLandSize(Double landSize) {
		this.landSize = landSize;
	}

	public Integer getAlliedActivityId() {
		return alliedActivityId;
	}

	public void setAlliedActivityId(Integer alliedActivityId) {
		this.alliedActivityId = alliedActivityId;
	}

	public Integer getGenderId() {
		return genderId;
	}

	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Boolean getIsCoApp1DetailsFilled() {
		return isCoApp1DetailsFilled;
	}

	public void setIsCoApp1DetailsFilled(Boolean isCoApp1DetailsFilled) {
		this.isCoApp1DetailsFilled = isCoApp1DetailsFilled;
	}

	public Boolean getIsCoApp2DetailsFilled() {
		return isCoApp2DetailsFilled;
	}

	public void setIsCoApp2DetailsFilled(Boolean isCoApp2DetailsFilled) {
		this.isCoApp2DetailsFilled = isCoApp2DetailsFilled;
	}

	public String getDetailsFilledCount() {
		return detailsFilledCount;
	}

	public void setDetailsFilledCount(String detailsFilledCount) {
		this.detailsFilledCount = detailsFilledCount;
	}

}
