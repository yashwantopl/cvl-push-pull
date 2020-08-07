package com.opl.mudra.api.fraudanalytics.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class McaDefaultCompanyData {
	
	@JsonProperty("defaultCompanydata")
	private List<DefaultCompanyData> defaultCompanyData;

	public List<DefaultCompanyData> getDefaultCompanyData() {
		return defaultCompanyData;
	}

	public void setDefaultCompanyData(List<DefaultCompanyData> defaultCompanyData) {
		this.defaultCompanyData = defaultCompanyData;
	}
	
	

}
