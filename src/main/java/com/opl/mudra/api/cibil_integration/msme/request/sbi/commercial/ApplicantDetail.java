package com.opl.mudra.api.cibil_integration.msme.request.sbi.commercial;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"firstName",
	"middleName",
	"lastName",
	"gender",
	"dateOfBirth",
	"relations",
	"identificationDetails",
	"phoneNumberDetails",
	"emailDetails",
	"addressDetails"
})
public class ApplicantDetail {

	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("dateOfBirth")
	private String dateOfBirth;
	@JsonProperty("relations")
	private List<Relation> relations = null;
	@JsonProperty("identificationDetails")
	private List<IdentificationDetail_> identificationDetails = null;
	@JsonProperty("phoneNumberDetails")
	private List<PhoneNumberDetail_> phoneNumberDetails = null;
	@JsonProperty("emailDetails")
	private List<EmailDetail_> emailDetails = null;
	@JsonProperty("addressDetails")
	private List<AddressDetail_> addressDetails = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("middleName")
	public String getMiddleName() {
		return middleName;
	}

	@JsonProperty("middleName")
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("gender")
	public String getGender() {
		return gender;
	}

	@JsonProperty("gender")
	public void setGender(String gender) {
		this.gender = gender;
	}

	@JsonProperty("dateOfBirth")
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	@JsonProperty("dateOfBirth")
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@JsonProperty("relations")
	public List<Relation> getRelations() {
		return relations;
	}

	@JsonProperty("relations")
	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}

	@JsonProperty("identificationDetails")
	public List<IdentificationDetail_> getIdentificationDetails() {
		return identificationDetails;
	}

	@JsonProperty("identificationDetails")
	public void setIdentificationDetails(List<IdentificationDetail_> identificationDetails) {
		this.identificationDetails = identificationDetails;
	}

	@JsonProperty("phoneNumberDetails")
	public List<PhoneNumberDetail_> getPhoneNumberDetails() {
		return phoneNumberDetails;
	}

	@JsonProperty("phoneNumberDetails")
	public void setPhoneNumberDetails(List<PhoneNumberDetail_> phoneNumberDetails) {
		this.phoneNumberDetails = phoneNumberDetails;
	}

	@JsonProperty("emailDetails")
	public List<EmailDetail_> getEmailDetails() {
		return emailDetails;
	}

	@JsonProperty("emailDetails")
	public void setEmailDetails(List<EmailDetail_> emailDetails) {
		this.emailDetails = emailDetails;
	}

	@JsonProperty("addressDetails")
	public List<AddressDetail_> getAddressDetails() {
		return addressDetails;
	}

	@JsonProperty("addressDetails")
	public void setAddressDetails(List<AddressDetail_> addressDetails) {
		this.addressDetails = addressDetails;
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

