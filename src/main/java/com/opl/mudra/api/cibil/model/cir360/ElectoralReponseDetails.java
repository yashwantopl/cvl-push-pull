package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ElectoralReponseDetails {

	@JsonProperty("Seq")
	private String seq;
	
	@JsonProperty("Source")
	private String source;
	
	@JsonProperty("VoterID")
	private String voterID;	
	
	@JsonProperty("VoterMatchStatus")
	private String voterMatchStatus;
	
	@JsonProperty("Reason")
	private List<ReasonType> reasons;
	
	@JsonProperty("FullName")
	private MatchResponseType fullName;
		
	@JsonProperty("Gender")
	private MatchResponseType gender;
	
	@JsonProperty("Age")
	private MatchResponseType age;
		
	@JsonProperty("AddressDetails")
	private List<AddressMatchResponseType> addressDetails;
	
	@JsonProperty("GuardianDetails")
	private GuardianResponseType guardianDetails;

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

	public String getVoterID() {
		return voterID;
	}

	public void setVoterID(String voterID) {
		this.voterID = voterID;
	}

	public String getVoterMatchStatus() {
		return voterMatchStatus;
	}

	public void setVoterMatchStatus(String voterMatchStatus) {
		this.voterMatchStatus = voterMatchStatus;
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

	public List<AddressMatchResponseType> getAddressDetails() {
		if(addressDetails==null){
			addressDetails = new ArrayList<AddressMatchResponseType>();
		}
		return addressDetails;
	}

	public void setAddressDetails(List<AddressMatchResponseType> addressDetails) {
		this.addressDetails = addressDetails;
	}

	public GuardianResponseType getGuardianDetails() {
		return guardianDetails;
	}

	public void setGuardianDetails(GuardianResponseType guardianDetails) {
		this.guardianDetails = guardianDetails;
	}
	
	
	
	
}
