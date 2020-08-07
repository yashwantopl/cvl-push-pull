package com.opl.mudra.api.fraudanalytics.model;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opl.mudra.api.fraudanalytics.utils.FraudAnalyticsUtils;

public class RegisteredComapnyData {

	@JsonProperty("CORPORATE_IDENTIFICATION_NUMBER")
	private String cin;
	
	@JsonProperty("COMPANY_NAME")
	private String companyName;
	
	@JsonProperty("COMPANY_STATUS")
	private String companyStatus;
	
	@JsonProperty("COMPANY_CLASS")
	private String companyClass;
	
	@JsonProperty("COMPANY_CATEGORY")
	private String companyCategory;
	
	@JsonProperty("COMPANY_SUB_CATEGORY")
	private String companySubCategory;
	
	@JsonProperty("DATE_OF_REGISTRATION")
	private Date dateOfRegistration;
	
	@JsonProperty("REGISTERED_STATE")
	private String registeredState;
	
	@JsonProperty("AUTHORIZED_CAP")
	private Double authorizedCap;
	
	@JsonProperty("PAIDUP_CAPITAL")
	private Double paidupCapital;
	
	@JsonProperty("INDUSTRIAL_CLASS")
	private String industrialClass;
	
	@JsonProperty("PRINCIPAL_BUSINESS_ACTIVITY_AS_PER_CIN")
	private String principalBusinessActivity;
	
	@JsonProperty("REGISTERED_OFFICE_ADDRESS")
	private String registeredOfficeAddress;
	
	@JsonProperty("REGISTRAR_OF_COMPANIES")
	private String registrarOfCompanies;
	
	@JsonProperty("EMAIL_ADDR")
	private String emailAddr;
	
	@JsonProperty("LATEST_YEAR_ANNUAL_RETURN")
	private Date latestYearAnnualReturn;
	
	@JsonProperty("LATEST_YEAR_FINANCIAL_STATEMENT")
	private Date latestYearFinancialStmt;
	

	public String getCin() {
		return cin;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getCompanyStatus() {
		return companyStatus;
	}

	public String getCompanyClass() {
		return companyClass;
	}

	public String getCompanyCategory() {
		return companyCategory;
	}

	public String getCompanySubCategory() {
		return companySubCategory;
	}

	public Date getDateOfRegistration() {
		return dateOfRegistration;
	}

	public String getRegisteredState() {
		return registeredState;
	}

	public Double getAuthorizedCap() {
		return authorizedCap;
	}

	public Double getPaidupCapital() {
		return paidupCapital;
	}

	public String getIndustrialClass() {
		return industrialClass;
	}

	public String getPrincipalBusinessActivity() {
		return principalBusinessActivity;
	}

	public String getRegisteredOfficeAddress() {
		return registeredOfficeAddress;
	}

	public String getRegistrarOfCompanies() {
		return registrarOfCompanies;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public Date getLatestYearAnnualReturn() {
		return latestYearAnnualReturn;
	}

	public Date getLatestYearFinancialStmt() {
		return latestYearFinancialStmt;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
	}

	public void setCompanyClass(String companyClass) {
		this.companyClass = companyClass;
	}

	public void setCompanyCategory(String companyCategory) {
		this.companyCategory = companyCategory;
	}

	public void setCompanySubCategory(String companySubCategory) {
		this.companySubCategory = companySubCategory;
	}

	public void setDateOfRegistration(String dateOfRegistration) {
		this.dateOfRegistration = FraudAnalyticsUtils.toDate(dateOfRegistration);
	}

	public void setRegisteredState(String registeredState) {
		this.registeredState = registeredState;
	}

	public void setAuthorizedCap(String authorizedCap) {
		this.authorizedCap = FraudAnalyticsUtils.toDouble(authorizedCap);
	}

	public void setPaidupCapital(String paidupCapital) {
		this.paidupCapital = FraudAnalyticsUtils.toDouble(paidupCapital);
	}

	public void setIndustrialClass(String industrialClass) {
		this.industrialClass = industrialClass;
	}

	public void setPrincipalBusinessActivity(String principalBusinessActivity) {
		this.principalBusinessActivity = principalBusinessActivity;
	}

	public void setRegisteredOfficeAddress(String registeredOfficeAddress) {
		this.registeredOfficeAddress = registeredOfficeAddress;
	}

	public void setRegistrarOfCompanies(String registrarOfCompanies) {
		this.registrarOfCompanies = registrarOfCompanies;
	}

	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public void setLatestYearAnnualReturn(String latestYearAnnualReturn) {
		this.latestYearAnnualReturn = FraudAnalyticsUtils.toDate(latestYearAnnualReturn);
	}

	public void setLatestYearFinancialStmt(String latestYearFinancialStmt) {
		this.latestYearFinancialStmt = FraudAnalyticsUtils.toDate(latestYearFinancialStmt);
	}
	
	
	
}
