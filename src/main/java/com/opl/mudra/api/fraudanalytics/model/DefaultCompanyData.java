package com.opl.mudra.api.fraudanalytics.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DefaultCompanyData {

	
	@JsonProperty("CIN")
	private String cin;
	
	@JsonProperty("Name")
	private String name;

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "DefaultCompanyData [cin=" + cin + ", name=" + name + "]";
	}
	
	
}
