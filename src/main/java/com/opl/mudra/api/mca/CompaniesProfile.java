package com.opl.mudra.api.mca;
/**
 * @author Sanket
 *
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class CompaniesProfile {
	
	@JsonProperty("authorised-capital")
	private String authorisedCapital; 
	
	@JsonProperty("authorised-capital-original-value")
	private String authorisedCapitalOriginalValue;
	
	private String cin;
	
	private String city;
	
	@JsonProperty("class-of-company")
	private String classOfCompany;
	
	@JsonProperty("company-category")
	private String companyCategory;
	
	@JsonProperty("company-name")
	private String companyName;
	
	@JsonProperty("company-sub-category")
	private String companySubCategory;
	
	@JsonProperty("company-type")
	private String companyType;
	
	@JsonProperty("date-of-balance-sheet")
	private String dateOfBalanceSheet;
	
	@JsonProperty("date-of-balance-sheet-original-value")
	private String dateOfBalanceSheetOriginalValue;
	
	@JsonProperty("date-of-incorporation")
	private String dateOfIncorporation;
	
	@JsonProperty("date-of-incorporation-original-value")
	private String dateOfIncorporationOriginalValue;
	
	@JsonProperty("date-of-last-agm")
	private String dateOfLastAgm;
	
	@JsonProperty("date-of-last-agm-original-value")
	private  String dateOfLastAgmOriginalValue;
	
	private String email;
	
	@JsonProperty("indian-or-foreign")
	private String indianOrForeign;
	
	@JsonProperty("in_db")
	private String inDb;
	
	@JsonProperty("listed-or-unlisted")
	private String listedOrUnlisted;
	
	@JsonProperty("paid-up-capital")
	private String paidUpCapital;
	
	@JsonProperty("paid-up-capital-original-value")
	private String paidUpCapitalOriginalValue;
	
	@JsonProperty("product-services")
	private String productServices;
	
	@JsonProperty("registered-address")
	private String registeredAddress;
	
	@JsonProperty("registered-address_map")
	private String registeredAddressMap;
	
	private String state;
	
	private String status;
	
	@JsonProperty("product-services-original")
	private CompaniesProductServicesOriginal[] productServicesOriginal;

	public CompaniesProductServicesOriginal[] getProductServicesOriginal() {
		return productServicesOriginal;
	}

	public void setProductServicesOriginal(CompaniesProductServicesOriginal[] productServicesOriginal) {
		this.productServicesOriginal = productServicesOriginal;
	}

	public String getAuthorisedCapital() {
		return authorisedCapital;
	}

	public String getAuthorisedCapitalOriginalValue() {
		return authorisedCapitalOriginalValue;
	}

	public String getCin() {
		return cin;
	}

	public String getCity() {
		return city;
	}

	public String getClassOfCompany() {
		return classOfCompany;
	}

	public String getCompanCategory() {
		return companyCategory;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getCompanySubCategory() {
		return companySubCategory;
	}

	public String getCompanyType() {
		return companyType;
	}

	public String getDateOfBalanceSheet() {
		return dateOfBalanceSheet;
	}

	public String getDateOfBalanceSheetOriginalValue() {
		return dateOfBalanceSheetOriginalValue;
	}

	public String getDateOfIncorporation() {
		return dateOfIncorporation;
	}

	public String getDateOfIncorporationOriginalValue() {
		return dateOfIncorporationOriginalValue;
	}

	public String getDateOfLastAgm() {
		return dateOfLastAgm;
	}

	public String getDateOfLastAgmOriginalValue() {
		return dateOfLastAgmOriginalValue;
	}

	public String getEmail() {
		return email;
	}

	public String getIndianOrForeign() {
		return indianOrForeign;
	}

	public String getInDb() {
		return inDb;
	}

	public String getListedOrUnlisted() {
		return listedOrUnlisted;
	}

	public String getPaidUpCapital() {
		return paidUpCapital;
	}

	public String getPaidUpCapitalOriginalValue() {
		return paidUpCapitalOriginalValue;
	}

	public String getProductServices() {
		return productServices;
	}

	public String getRegisteredAddress() {
		return registeredAddress;
	}

	public String getRegisteredAddressMap() {
		return registeredAddressMap;
	}

	public String getState() {
		return state;
	}

	public String getStatus() {
		return status;
	}

	public void setAuthorisedCapital(String authorisedCapital) {
		this.authorisedCapital = authorisedCapital;
	}

	public void setAuthorisedCapitalOriginalValue(String authorisedCapitalOriginalValue) {
		this.authorisedCapitalOriginalValue = authorisedCapitalOriginalValue;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setClassOfCompany(String classOfCompany) {
		this.classOfCompany = classOfCompany;
	}

	public void setCompanCategory(String companCategory) {
		this.companyCategory = companCategory;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setCompanySubCategory(String companySubCategory) {
		this.companySubCategory = companySubCategory;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public void setDateOfBalanceSheet(String dateOfBalanceSheet) {
		this.dateOfBalanceSheet = dateOfBalanceSheet;
	}

	public void setDateOfBalanceSheetOriginalValue(String dateOfBalanceSheetOriginalValue) {
		this.dateOfBalanceSheetOriginalValue = dateOfBalanceSheetOriginalValue;
	}

	public void setDateOfIncorporation(String dateOfIncorporation) {
		this.dateOfIncorporation = dateOfIncorporation;
	}

	public void setDateOfIncorporationOriginalValue(String dateOfIncorporationOriginalValue) {
		this.dateOfIncorporationOriginalValue = dateOfIncorporationOriginalValue;
	}

	public void setDateOfLastAgm(String dateOfLastAgm) {
		this.dateOfLastAgm = dateOfLastAgm;
	}

	public void setDateOfLastAgmOriginalValue(String dateOfLastAgmOriginalValue) {
		this.dateOfLastAgmOriginalValue = dateOfLastAgmOriginalValue;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIndianOrForeign(String indianOrForeign) {
		this.indianOrForeign = indianOrForeign;
	}

	public void setInDb(String inDb) {
		this.inDb = inDb;
	}

	public void setListedOrUnlisted(String listedOrUnlisted) {
		this.listedOrUnlisted = listedOrUnlisted;
	}

	public void setPaidUpCapital(String paidUpCapital) {
		this.paidUpCapital = paidUpCapital;
	}

	public void setPaidUpCapitalOriginalValue(String paidUpCapitalOriginalValue) {
		this.paidUpCapitalOriginalValue = paidUpCapitalOriginalValue;
	}

	public void setProductServices(String productServices) {
		this.productServices = productServices;
	}

	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	public void setRegisteredAddressMap(String registeredAddressMap) {
		this.registeredAddressMap = registeredAddressMap;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
