package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RequestBody {

	@JsonProperty("InquiryPurpose")
	private String inquiryPurpose;

	@JsonProperty("TransactionAmount")
	private String transactionAmount;

	@JsonProperty("FirstName")
	private String firstName;

	@JsonProperty("MiddleName")
	private String middleName;

	@JsonProperty("LastName")
	private String lastName;

	@JsonProperty("InquiryAddresses")
	private List<InquiryAddress> inquiryAddresses;

	@JsonProperty("InquiryPhones")
	private List<InquiryPhone> inquiryPhones;

	@JsonProperty("EmailAddresses")
	private List<EmailAddress> emailAddresses;

	@JsonProperty("IDDetails")
	private List<IDInfo> idDetails;

	@JsonProperty("DOB")
	private String dob;

	@JsonProperty("Gender")
	private String gender;

	@JsonProperty("InquiryCommonAccountDetails")
	private List<InquiryCommonAccountDetails> inquiryCommonAccountDetails;

	@JsonProperty("CustomFields")
	private List<CustomFieldsType> customFields;

	@JsonProperty("MFIDetails")
	private MFIDetails mfiDetails;

	public MFIDetails getMfiDetails() {
		return mfiDetails;
	}

	public void setMfiDetails(MFIDetails mfiDetails) {
		this.mfiDetails = mfiDetails;
	}

	public String getInquiryPurpose() {
		return inquiryPurpose;
	}

	public void setInquiryPurpose(String inquiryPurpose) {
		this.inquiryPurpose = inquiryPurpose;
	}

	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<InquiryAddress> getInquiryAddresses() {
		if (inquiryAddresses == null) {
			inquiryAddresses = new ArrayList<InquiryAddress>();
		}
		return inquiryAddresses;
	}

	public void setInquiryAddresses(List<InquiryAddress> inquiryAddresses) {
		this.inquiryAddresses = inquiryAddresses;
	}

	public List<InquiryPhone> getInquiryPhones() {
		return inquiryPhones;
	}

	public void setInquiryPhones(List<InquiryPhone> inquiryPhones) {
		this.inquiryPhones = inquiryPhones;
	}

	public List<EmailAddress> getEmailAddresses() {
		return emailAddresses;
	}

	public void setEmailAddresses(List<EmailAddress> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	public List<IDInfo> getIdDetails() {
		return idDetails;
	}

	public void setIdDetails(List<IDInfo> idDetails) {
		this.idDetails = idDetails;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dOB) {
		dob = dOB;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<InquiryCommonAccountDetails> getInquiryCommonAccountDetails() {
		return inquiryCommonAccountDetails;
	}

	public void setInquiryCommonAccountDetails(List<InquiryCommonAccountDetails> inquiryCommonAccountDetails) {
		this.inquiryCommonAccountDetails = inquiryCommonAccountDetails;
	}

	public List<CustomFieldsType> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(List<CustomFieldsType> customFields) {
		this.customFields = customFields;
	}

}
