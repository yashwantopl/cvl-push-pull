package com.opl.mudra.api.cibil_integration.msme.request.sbi.commercial;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"name",
	"shortName",
	"legalEntity",
	"incorporationDate",
	"industryType",
	"identificationDetails",
	"phoneNumberDetails",
	"emailDetails",
	"addressDetails",
	"applicantDetails"
})
public class BorrowerDetails {

	@JsonProperty("name")
	private String name;
	@JsonProperty("shortName")
	private String shortName;
	@JsonProperty("legalEntity")
	private String legalEntity;
	@JsonProperty("incorporationDate")
	private String incorporationDate;
	@JsonProperty("industryType")
	private String industryType;
	@JsonProperty("identificationDetails")
	private List<IdentificationDetail> identificationDetails = null;
	@JsonProperty("phoneNumberDetails")
	private List<PhoneNumberDetail> phoneNumberDetails = null;
	@JsonProperty("emailDetails")
	private List<EmailDetail> emailDetails = null;
	@JsonProperty("addressDetails")
	private List<AddressDetail> addressDetails = null;
	@JsonProperty("applicantDetails")
	private List<ApplicantDetail> applicantDetails = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("shortName")
	public String getShortName() {
		return shortName;
	}

	@JsonProperty("shortName")
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@JsonProperty("legalEntity")
	public String getLegalEntity() {
		return legalEntity;
	}

	@JsonProperty("legalEntity")
	public void setLegalEntity(String legalEntity) {
		this.legalEntity = legalEntity;
	}

	@JsonProperty("incorporationDate")
	public String getIncorporationDate() {
		return incorporationDate;
	}

	@JsonProperty("incorporationDate")
	public void setIncorporationDate(String incorporationDate) {
		this.incorporationDate = incorporationDate;
	}

	@JsonProperty("industryType")
	public String getIndustryType() {
		return industryType;
	}

	@JsonProperty("industryType")
	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	@JsonProperty("identificationDetails")
	public List<IdentificationDetail> getIdentificationDetails() {
		return identificationDetails;
	}

	@JsonProperty("identificationDetails")
	public void setIdentificationDetails(List<IdentificationDetail> identificationDetails) {
		this.identificationDetails = identificationDetails;
	}

	@JsonProperty("phoneNumberDetails")
	public List<PhoneNumberDetail> getPhoneNumberDetails() {
		return phoneNumberDetails;
	}

	@JsonProperty("phoneNumberDetails")
	public void setPhoneNumberDetails(List<PhoneNumberDetail> phoneNumberDetails) {
		this.phoneNumberDetails = phoneNumberDetails;
	}

	@JsonProperty("emailDetails")
	public List<EmailDetail> getEmailDetails() {
		return emailDetails;
	}

	@JsonProperty("emailDetails")
	public void setEmailDetails(List<EmailDetail> emailDetails) {
		this.emailDetails = emailDetails;
	}

	@JsonProperty("addressDetails")
	public List<AddressDetail> getAddressDetails() {
		return addressDetails;
	}

	@JsonProperty("addressDetails")
	public void setAddressDetails(List<AddressDetail> addressDetails) {
		this.addressDetails = addressDetails;
	}

	@JsonProperty("applicantDetails")
	public List<ApplicantDetail> getApplicantDetails() {
		return applicantDetails;
	}

	@JsonProperty("applicantDetails")
	public void setApplicantDetails(List<ApplicantDetail> applicantDetails) {
		this.applicantDetails = applicantDetails;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}

