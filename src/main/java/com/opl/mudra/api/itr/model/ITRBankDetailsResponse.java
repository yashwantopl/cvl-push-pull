package com.opl.mudra.api.itr.model;


public class ITRBankDetailsResponse {

	private Long id;

	private String ifscCode;

	private String bankName;

	private String accountNumber;

	private String bankAccountType;

	private Long applicationId;

	private Long directorId;

	private String year;
	
	private String formName;

	private Integer businessTypeId;

	private Boolean isLastYear;

	private Long bankMasterId;

	private String micrCode;

	private String branch;

	private String address;

	private String contact;

	private String city;

	private String district;

	private String state;
	
	private Long analyzerId;
	
	
	public ITRBankDetailsResponse() {
		super();
	}

	public ITRBankDetailsResponse(String ifscCode, String bankName, String accountNumber, String year, String micrCode,
			String branch, String address, String contact, String city, String district, String state,
			String formName, Long analyzerId) {
		super();
		this.ifscCode = ifscCode;
		this.bankName = bankName;
		this.accountNumber = accountNumber;
		this.year = year;
		this.micrCode = micrCode;
		this.branch = branch;
		this.address = address;
		this.contact = contact;
		this.city = city;
		this.district = district;
		this.state = state;
		this.formName = formName;
		this.analyzerId = analyzerId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankAccountType() {
		return bankAccountType;
	}

	public void setBankAccountType(String bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getDirectorId() {
		return directorId;
	}

	public void setDirectorId(Long directorId) {
		this.directorId = directorId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public Boolean getIsLastYear() {
		return isLastYear;
	}

	public void setIsLastYear(Boolean isLastYear) {
		this.isLastYear = isLastYear;
	}

	public Long getBankMasterId() {
		return bankMasterId;
	}

	public void setBankMasterId(Long bankMasterId) {
		this.bankMasterId = bankMasterId;
	}

	public String getMicrCode() {
		return micrCode;
	}

	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getAnalyzerId() {
		return analyzerId;
	}

	public void setAnalyzerId(Long analyzerId) {
		this.analyzerId = analyzerId;
	}
	
	
	
	
}
