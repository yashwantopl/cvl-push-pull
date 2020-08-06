package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AUAResponseDetails {

	@JsonProperty("Seq")
	private String seq;
	
	@JsonProperty("Source")
	private String source;
	
	@JsonProperty("NationalID")
	private String nationalID;
	
	
	@JsonProperty("NationalIDMatchStatus")
	private String nationalIDMatchStatus;
	
	@JsonProperty("Reason")
	private List<ReasonType> reasons;
	
	@JsonProperty("FullName")
	private MatchResponseType fullName;
	
	@JsonProperty("DOB")
	private MatchResponseType dob;
	
	@JsonProperty("Gender")
	private MatchResponseType gender;
	
	@JsonProperty("Age")
	private MatchResponseType age;
		
	@JsonProperty("PhoneDetails")
	private List<OtherMatchResponseType> phoneDetails;
	
	@JsonProperty("AddressDetails")
	private List<AddressMatchResponseType> addressDetails;
	
	@JsonProperty("EmailDetails")
	private List<OtherMatchResponseType> emailDetails;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getNationalID() {
		return nationalID;
	}

	public void setNationalID(String nationalID) {
		this.nationalID = nationalID;
	}

	public String getNationalIDMatchStatus() {
		return nationalIDMatchStatus;
	}

	public void setNationalIDMatchStatus(String nationalIDMatchStatus) {
		this.nationalIDMatchStatus = nationalIDMatchStatus;
	}

	public List<ReasonType> getReasons() {
		if(reasons==null){
			reasons = new ArrayList<ReasonType>();
		}
		return reasons;
	}

	public void setReasons(List<ReasonType> reasons) {
		this.reasons = reasons;
	}

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

	public List<OtherMatchResponseType> getPhoneDetails() {
		return phoneDetails;
	}

	public void setPhoneDetails(List<OtherMatchResponseType> phoneDetails) {
		this.phoneDetails = phoneDetails;
	}

	public List<AddressMatchResponseType> getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(List<AddressMatchResponseType> addressDetails) {
		this.addressDetails = addressDetails;
	}

	public List<OtherMatchResponseType> getEmailDetails() {
		return emailDetails;
	}

	public void setEmailDetails(List<OtherMatchResponseType> emailDetails) {
		this.emailDetails = emailDetails;
	}
	
	
}
