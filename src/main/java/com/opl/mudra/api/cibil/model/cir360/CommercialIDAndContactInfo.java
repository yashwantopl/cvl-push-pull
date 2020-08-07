package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CommercialIDAndContactInfo {

	@JsonProperty("CommercialPersonalInfo")
	private CommercialPersonalInfo personalInfo;
	
	@JsonProperty("CommercialIdentityInfo")
	private  CommercialIdentityInfo identityInfo;
	
	@JsonProperty("CommercialAddressInfo")
	private List<AddressInfo> addressInfoList;
	
	@JsonProperty("CommercialPhoneInfo")
	private List<PhoneInfo> phoneInfoList;
	
	@JsonProperty("CommercialEmailAddressInfo")
	private List<EmailAddressInfo> emailAddressInfoList;

	public CommercialPersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(CommercialPersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	public CommercialIdentityInfo getIdentityInfo() {
		return identityInfo;
	}

	public void setIdentityInfo(CommercialIdentityInfo identityInfo) {
		this.identityInfo = identityInfo;
	}

	public List<AddressInfo> getAddressInfoList() {
		if(addressInfoList==null){
			addressInfoList = new ArrayList<AddressInfo>();
		}
		return addressInfoList;
	}

	public void setAddressInfoList(List<AddressInfo> addressInfoList) {
		this.addressInfoList = addressInfoList;
	}

	public List<PhoneInfo> getPhoneInfoList() {
		if(phoneInfoList==null){
			phoneInfoList = new ArrayList<PhoneInfo>();
		}
		return phoneInfoList;
	}

	public void setPhoneInfoList(List<PhoneInfo> phoneInfoList) {
		this.phoneInfoList = phoneInfoList;
	}

	public List<EmailAddressInfo> getEmailAddressInfoList() {
		if(emailAddressInfoList==null){
			emailAddressInfoList = new ArrayList<EmailAddressInfo>();
		}
		return emailAddressInfoList;
	}

	public void setEmailAddressInfoList(List<EmailAddressInfo> emailAddressInfoList) {
		this.emailAddressInfoList = emailAddressInfoList;
	}
	
	
	
}
