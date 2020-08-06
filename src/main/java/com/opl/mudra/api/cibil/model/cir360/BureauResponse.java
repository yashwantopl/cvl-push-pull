package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BureauResponse {

	@JsonProperty("FullName")
	private MatchResponseType fullName;
	
	@JsonProperty("DOB")
	private MatchResponseType dob;
	
	@JsonProperty("Gender")
	private MatchResponseType gender;
	
	@JsonProperty("Age")
	private MatchResponseType age;
	
	@JsonProperty("IDDetails")
	private List<IDMatchResponseType> idDetails;
	
	@JsonProperty("PhoneDetails")
	private List<OtherMatchResponseType> phoneDetails;
	
	@JsonProperty("AddressDetails")
	private List<AddressMatchResponseType> addressDetails;
	
	@JsonProperty("EmailDetails")
	private List<OtherMatchResponseType> emailDetails;

	public MatchResponseType getFullName() {
		return fullName;
	}

	public void setFullName(MatchResponseType fullName) {
		this.fullName = fullName;
	}

	public MatchResponseType getDob() {
		return dob;
	}

	public void setDob(MatchResponseType dob) {
		this.dob = dob;
	}

	public MatchResponseType getGender() {
		return gender;
	}

	public void setGender(MatchResponseType gender) {
		this.gender = gender;
	}

	public MatchResponseType getAge() {
		return age;
	}

	public void setAge(MatchResponseType age) {
		this.age = age;
	}

	public List<IDMatchResponseType> getIdDetails() {
		return idDetails;
	}

	public void setIdDetails(List<IDMatchResponseType> idDetails) {
		this.idDetails = idDetails;
	}

	public List<OtherMatchResponseType> getPhoneDetails() {
		if(phoneDetails==null){
			phoneDetails = new ArrayList<OtherMatchResponseType>();
		}
		return phoneDetails;
	}

	public void setPhoneDetails(List<OtherMatchResponseType> phoneDetails) {
		this.phoneDetails = phoneDetails;
	}

	public List<AddressMatchResponseType> getAddressDetails() {
		if(addressDetails==null){
			addressDetails = new ArrayList<AddressMatchResponseType>();
		}
		return addressDetails;
	}

	public void setAddressDetails(List<AddressMatchResponseType> addressDetails) {
		this.addressDetails = addressDetails;
	}

	public List<OtherMatchResponseType> getEmailDetails() {
		if(emailDetails==null){
			emailDetails = new ArrayList<OtherMatchResponseType>();
		}
		return emailDetails;
	}

	public void setEmailDetails(List<OtherMatchResponseType> emailDetails) {
		this.emailDetails = emailDetails;
	}
	
	
}
