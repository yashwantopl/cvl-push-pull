package com.opl.mudra.api.fraudanalytics.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class McaDefaultDirectorData {

	@JsonProperty("defaultDirectordata")
	private List<DefaultDirectorData> defaultDirectorData;

	public List<DefaultDirectorData> getDefaultDirectorData() {
		return defaultDirectorData;
	}

	public void setDefaultDirectorData(List<DefaultDirectorData> defaultDirectorData) {
		this.defaultDirectorData = defaultDirectorData;
	}
	
	
}
