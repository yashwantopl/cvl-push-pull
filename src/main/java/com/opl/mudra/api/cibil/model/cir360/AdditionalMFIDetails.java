package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AdditionalMFIDetails {

	@JsonProperty("MFIClientFullname")
	private String MFIClientFullname;
	
	@JsonProperty("MFIDOB")
	private String MFIDOB;
	@JsonProperty("MFIGender")
	private String MFIGender;
	@JsonProperty("MemberId")
	private String MemberId;
	
	@JsonProperty("MFIIdentification")
	private IdentityInfo mfiIdentification;

	@JsonProperty("MFIAddress")
	private List<AddressInfo> mfiAddress;
	
	@JsonProperty("MFIPhones")
	private List<PhoneInfo> mfiPhones;
	
	@JsonProperty("MFIClientFullname")
	public String getMFIClientFullname() {
		return MFIClientFullname;
	}
	@JsonProperty("MFIClientFullname")
	public void setMFIClientFullname(String mFIClientFullname) {
		MFIClientFullname = mFIClientFullname;
	}

	@JsonProperty("MFIDOB")
	public String getMFIDOB() {
		return MFIDOB;
	}
	@JsonProperty("MFIDOB")
	public void setMFIDOB(String mFIDOB) {
		MFIDOB = mFIDOB;
	}
	@JsonProperty("MFIGender")
	public String getMFIGender() {
		return MFIGender;
	}
	@JsonProperty("MFIGender")
	public void setMFIGender(String mFIGender) {
		MFIGender = mFIGender;
	}
	@JsonProperty("MemberId")
	public String getMemberId() {
		return MemberId;
	}
	@JsonProperty("MemberId")
	public void setMemberId(String memberId) {
		MemberId = memberId;
	}
	@JsonProperty("MFIIdentification")
	public IdentityInfo getMfiIdentification() {
		return mfiIdentification;
	}
	@JsonProperty("MFIIdentification")
	public void setMfiIdentification(IdentityInfo mfiIdentification) {
		this.mfiIdentification = mfiIdentification;
	}
	@JsonProperty("MFIAddress")
	public List<AddressInfo> getMfiAddress() {
		return mfiAddress;
	}
	@JsonProperty("MFIAddress")
	public void setMfiAddress(List<AddressInfo> mfiAddress) {
		this.mfiAddress = mfiAddress;
	}
	@JsonProperty("MFIPhones")
	public List<PhoneInfo> getMfiPhones() {
		return mfiPhones;
	}
	@JsonProperty("MFIPhones")
	public void setMfiPhones(List<PhoneInfo> mfiPhones) {
		this.mfiPhones = mfiPhones;
	}
	
}
