package com.opl.mudra.api.mca;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Sanket
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class CompaniesHistoryCompanyProfilePaid {
    @JsonProperty("authorisedCapitalFormatted")
    private String authorisedCapitalFormatted;
    @JsonProperty("paidUpCapitalFormatted")
    private String paidUpCapitalFormatted;
    @JsonProperty("company-name")
    private String companyName;
    @JsonProperty("cin")
    private String cin;
    @JsonProperty("pan")
    private String pan;
    @JsonProperty("telephone-number-with-std-code")
    private String telephoneNumberWithStdCode;
    @JsonProperty("website")
    private String website;
    @JsonProperty("registered-address")
    private String registeredAddress;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("registered-address_map")
    private String registeredAddressMap;
    @JsonProperty("email")
    private String email;
    @JsonProperty("class-of-company")
    private String classOfCompany;
    @JsonProperty("listed-or-unlisted")
    private String listedOrUnlisted;
    @JsonProperty("company-category")
    private String companyCategory;
    @JsonProperty("company-sub-category")
    private String companySubCategory;
    @JsonProperty("authorised-capital")
    private String authorisedCapital;
    @JsonProperty("paid-up-capital")
    private String paidUpCapital;
    @JsonProperty("date-of-incorporation")
    private String dateOfIncorporation;
    @JsonProperty("indian-or-foreign")
    private String indianOrForeign;
    @JsonProperty("date-of-last-agm")
    private String dateOfLastAgm;
    @JsonProperty("date-of-balance-sheet")
    private String dateOfBalanceSheet;
    @JsonProperty("status")
    private String status;
    @JsonProperty("product-services")
    private List<Object> productServices = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	public String getAuthorisedCapitalFormatted() {
		return authorisedCapitalFormatted;
	}
	public String getPaidUpCapitalFormatted() {
		return paidUpCapitalFormatted;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String getCin() {
		return cin;
	}
	public String getPan() {
		return pan;
	}
	public String getTelephoneNumberWithStdCode() {
		return telephoneNumberWithStdCode;
	}
	public String getWebsite() {
		return website;
	}
	public String getRegisteredAddress() {
		return registeredAddress;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getRegisteredAddressMap() {
		return registeredAddressMap;
	}
	public String getEmail() {
		return email;
	}
	public String getClassOfCompany() {
		return classOfCompany;
	}
	public String getListedOrUnlisted() {
		return listedOrUnlisted;
	}
	public String getCompanyCategory() {
		return companyCategory;
	}
	public String getCompanySubCategory() {
		return companySubCategory;
	}
	public String getAuthorisedCapital() {
		return authorisedCapital;
	}
	public String getPaidUpCapital() {
		return paidUpCapital;
	}
	public String getDateOfIncorporation() {
		return dateOfIncorporation;
	}
	public String getIndianOrForeign() {
		return indianOrForeign;
	}
	public String getDateOfLastAgm() {
		return dateOfLastAgm;
	}
	public String getDateOfBalanceSheet() {
		return dateOfBalanceSheet;
	}
	public String getStatus() {
		return status;
	}
	public List<Object> getProductServices() {
		return productServices;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAuthorisedCapitalFormatted(String authorisedCapitalFormatted) {
		this.authorisedCapitalFormatted = authorisedCapitalFormatted;
	}
	public void setPaidUpCapitalFormatted(String paidUpCapitalFormatted) {
		this.paidUpCapitalFormatted = paidUpCapitalFormatted;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public void setTelephoneNumberWithStdCode(String telephoneNumberWithStdCode) {
		this.telephoneNumberWithStdCode = telephoneNumberWithStdCode;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setRegisteredAddressMap(String registeredAddressMap) {
		this.registeredAddressMap = registeredAddressMap;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setClassOfCompany(String classOfCompany) {
		this.classOfCompany = classOfCompany;
	}
	public void setListedOrUnlisted(String listedOrUnlisted) {
		this.listedOrUnlisted = listedOrUnlisted;
	}
	public void setCompanyCategory(String companyCategory) {
		this.companyCategory = companyCategory;
	}
	public void setCompanySubCategory(String companySubCategory) {
		this.companySubCategory = companySubCategory;
	}
	public void setAuthorisedCapital(String authorisedCapital) {
		this.authorisedCapital = authorisedCapital;
	}
	public void setPaidUpCapital(String paidUpCapital) {
		this.paidUpCapital = paidUpCapital;
	}
	public void setDateOfIncorporation(String dateOfIncorporation) {
		this.dateOfIncorporation = dateOfIncorporation;
	}
	public void setIndianOrForeign(String indianOrForeign) {
		this.indianOrForeign = indianOrForeign;
	}
	public void setDateOfLastAgm(String dateOfLastAgm) {
		this.dateOfLastAgm = dateOfLastAgm;
	}
	public void setDateOfBalanceSheet(String dateOfBalanceSheet) {
		this.dateOfBalanceSheet = dateOfBalanceSheet;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setProductServices(List<Object> productServices) {
		this.productServices = productServices;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
    
    
}
