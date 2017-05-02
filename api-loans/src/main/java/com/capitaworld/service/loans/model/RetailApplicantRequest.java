package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the fs_retail_applicant_details database table.
 * 
 */
public class RetailApplicantRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	// Common Fields
	private Long id;
	private Long applicationId;

	// Primary Fields
	private Integer titleId;
	private String firstName;
	private String middleName;
	private String lastName;
	private Integer statusId;
	private Integer occupationId;
	private String pan;
	private String aadharNumber;
	private Double monthlyIncome;
	private Integer currencyId;
	private Address firstAddress;
	private Address secondAddress;
	private boolean addressSameAs;
	private String contactNo;
	private List<CoApplicantRequest> coApplicants = Collections.emptyList();
	private List<GuarantorRequest> guarantors = Collections.emptyList();
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

	// Final common Retail Fields
	private Integer castId;
	private String castOther;
	private Integer religion;
	private String religionOther;
	private String birthPlace;
	private String fatherName;
	private String motherName;
	private String spouseName;
	private Boolean isSpouseEmployed;
	private Integer noChildren;
	private Integer noDependent;
	private Integer highestQualification;
	private String highestQualificationOther;
	private Date qualifyingYear;
	private String institute;
	private Integer residenceType;
	private Double annualRent;
	private Double annualTurnover;
	private Integer noPartners;

	// private Boolean addressSameAs;

	// private Date birthDate;

	// private Date businessStartDate;

	// private String currentDepartment;
	// private String currentDesignation;
	// private String currentIndustry;
	// private Integer currentJobMonth;
	// private Integer currentJobYear;

	// private Integer employmentStatus;

	//

	// private Integer interestRate;

	//
	//
	//

	// private String nameOfEntity;

	//
	// private Integer officeType;
	// private Integer ownershipType;
	//
	// private String partnersName;
	// private String poaHolderName;
	// private String presentlyIrrigated;
	// private String previousEmployersAddress;
	// private String previousEmployersName;
	// private Integer previousJobMonth;
	// private Integer previousJobYear;

	// private String rainFed;

	// private Integer repaymentCycle;
	// private Integer repaymentMode;

	// private Double residingMonth;
	// private Double residingYear;
	// private String seasonalIrrigated;

	// private String shareholding;

	//
	// private Integer totalExperienceMonth;
	// private Integer totalExperienceYear;
	// private Double totalLandOwned;
	// private Date tradeLicenseExpiryDate;
	// private String tradeLicenseNumber;
	// private String unattended;
	// private String websiteAddress;
	private Long userId;

	public RetailApplicantRequest() {
	}

	public RetailApplicantRequest(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getTitleId() {
		return titleId;
	}

	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public boolean isAddressSameAs() {
		return addressSameAs;
	}

	public void setAddressSameAs(boolean addressSameAs) {
		this.addressSameAs = addressSameAs;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public List<CoApplicantRequest> getCoApplicants() {
		return coApplicants;
	}

	public void setCoApplicants(List<CoApplicantRequest> coApplicants) {
		this.coApplicants = coApplicants;
	}

	public List<GuarantorRequest> getGuarantors() {
		return guarantors;
	}

	public void setGuarantors(List<GuarantorRequest> guarantors) {
		this.guarantors = guarantors;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

}