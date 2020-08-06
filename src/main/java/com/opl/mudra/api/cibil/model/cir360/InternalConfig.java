package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class InternalConfig {

	@JsonProperty("configDetails")
	private Map<String, String> configDetails ;

	public Map<String, String> getConfigDetails() {
		return configDetails;
	}

	public void setConfigDetails(Map<String, String> configDetails) {
		this.configDetails = configDetails;
	}

	

}
