package com.opl.mudra.api.fraudanalytics.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DefaultDirectorData {

	@JsonProperty("Signatory ID")
	private Long signatoryId;
	
	@JsonProperty("Name")
	private String name;
	
	@JsonProperty("CIN")
	private String cin;
	
	@JsonProperty("Company Name")
	private String companyName;
	
	@JsonProperty("Defaulting Year")
	private String defaultingYear;
	
	

	public Long getSignatoryId() {
		return signatoryId;
	}

	public void setSignatoryId(Long signatoryId) {
		this.signatoryId = signatoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDefaultingYear() {
		return defaultingYear;
	}

	public void setDefaultingYear(String defaultingYear) {
		this.defaultingYear = defaultingYear;
	}

	@Override
	public String toString() {
		return "DefaultDirectorData [signatoryId=" + signatoryId + ", name=" + name + ", cin=" + cin + ", companyName="
				+ companyName + ", defaultingYear=" + defaultingYear + "]";
	}
	
	
}
