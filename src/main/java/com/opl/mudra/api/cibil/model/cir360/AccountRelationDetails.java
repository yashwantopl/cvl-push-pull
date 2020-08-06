package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AccountRelationDetails {

	@JsonProperty("seq")
	private String seq;

	@JsonProperty("CreditFacilityReference")
	private String creditFacilityReference;

	@JsonProperty("dt_reported_lst")
	private String dt_reported_lst;

	@JsonProperty("Type")
	private String type;

	// JsonProperty("Name")
	// private String name;

	@JsonProperty("full_name")
	private String full_name;

	@JsonProperty("business_entity_name")
	private String business_entity_name;

	@JsonProperty("Relationship")
	private String relationship;

	@JsonProperty("Gender")
	private String gender;

	@JsonProperty("Dob")
	private String dob;

	@JsonProperty("Age")
	private String age;

	@JsonProperty("date_of_incorporation")
	private String date_of_incorporation;

	@JsonProperty("Business_category")
	private String business_category;

	@JsonProperty("Business_industry_type")
	private String business_industry_type;

	@JsonProperty("Percentage_control")
	private String percentage_control;

	@JsonProperty("IdentityInfo")
	private CommercialIdentityInfo identityInfoList;

	@JsonProperty("CommercialAddressInfo")
	private List<AddressInfo> addressInfoList;

	@JsonProperty("PhoneInfo")
	private List<PhoneInfo> phoneInfoList;

	public String getPercentage_control() {
		return percentage_control;
	}

	public void setPercentage_control(String percentage_control) {
		this.percentage_control = percentage_control;
	}

	public List<AddressInfo> getAddressInfoList() {
		return addressInfoList;
	}

	public void setAddressInfoList(List<AddressInfo> addressInfoList) {
		this.addressInfoList = addressInfoList;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getCreditFacilityReference() {
		return creditFacilityReference;
	}

	public void setCreditFacilityReference(String creditFacilityReference) {
		this.creditFacilityReference = creditFacilityReference;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CommercialIdentityInfo getIdentityInfoList() {
		return identityInfoList;
	}

	public void setIdentityInfoList(CommercialIdentityInfo identityInfoList) {
		this.identityInfoList = identityInfoList;
	}

	public List<PhoneInfo> getPhoneInfoList() {
		if (phoneInfoList == null) {
			phoneInfoList = new ArrayList<PhoneInfo>();
		}
		return phoneInfoList;
	}

	public void setPhoneInfoList(List<PhoneInfo> phoneInfoList) {
		this.phoneInfoList = phoneInfoList;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getDate_of_incorporation() {
		return date_of_incorporation;
	}

	public void setDate_of_incorporation(String date_of_incorporation) {
		this.date_of_incorporation = date_of_incorporation;
	}

	public String getBusiness_category() {
		return business_category;
	}

	public void setBusiness_category(String business_category) {
		this.business_category = business_category;
	}

	public String getBusiness_industry_type() {
		return business_industry_type;
	}

	public void setBusiness_industry_type(String business_industry_type) {
		this.business_industry_type = business_industry_type;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getBusiness_entity_name() {
		return business_entity_name;
	}

	public void setBusiness_entity_name(String business_entity_name) {
		this.business_entity_name = business_entity_name;
	}

	public String getDt_reported_lst() {
		return dt_reported_lst;
	}

	public void setDt_reported_lst(String dt_reported_lst) {
		this.dt_reported_lst = dt_reported_lst;
	}

}