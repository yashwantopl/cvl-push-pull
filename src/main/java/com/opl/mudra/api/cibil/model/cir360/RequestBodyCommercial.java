package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RequestBodyCommercial {

	@JsonProperty("InquiryPurpose")
	private String inquiryPurpose;

	@JsonProperty("TransactionAmount")
	private String transactionAmount;

	@JsonProperty("BusinessName")
	private String businessName;

	@JsonProperty("InquiryAddresses")
	private List<InquiryAddress> inquiryAddresses;

	@JsonProperty("InquiryPhones")
	private List<InquiryPhone> inquiryPhones;

	@JsonProperty("EmailAddresses")
	private List<EmailAddress> emailAddresses;

	@JsonProperty("IDDetails")
	private List<IDInfo> idDetails;

	@JsonProperty("CustomFields")
	private List<CustomFieldsType> customFields;

	@JsonProperty("DateIncorporation")
	private String dateIncorporation;

	@JsonProperty("BusinessCategory")
	private String businessCategory;

	@JsonProperty("IndustryType")
	private String industryType;

	public String getDateIncorporation() {
		return dateIncorporation;
	}

	public void setDateIncorporation(String dateIncorporation) {
		this.dateIncorporation = dateIncorporation;
	}

	public String getBusinessCategory() {
		return businessCategory;
	}

	public void setBusinessCategory(String businessCategory) {
		this.businessCategory = businessCategory;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
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

	public List<CustomFieldsType> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(List<CustomFieldsType> customFields) {
		this.customFields = customFields;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

}
