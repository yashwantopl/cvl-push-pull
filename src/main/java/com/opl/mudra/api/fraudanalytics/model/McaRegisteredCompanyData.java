package com.opl.mudra.api.fraudanalytics.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class McaRegisteredCompanyData {

	@JsonProperty("mca_master_india")
	private List<RegisteredComapnyData> registeredComapnyDatas;

	public List<RegisteredComapnyData> getRegisteredComapnyDatas() {
		return registeredComapnyDatas;
	}

	public void setRegisteredComapnyDatas(List<RegisteredComapnyData> registeredComapnyDatas) {
		this.registeredComapnyDatas = registeredComapnyDatas;
	}
	
}
