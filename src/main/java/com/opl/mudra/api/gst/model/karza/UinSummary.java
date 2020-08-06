package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class UinSummary {
	
	@JsonProperty("uin_summary")
	private Map<String, Object> uinSummary;

	public Map<String, Object> getUinSummary() {
		return uinSummary;
	}

	public void setUinSummary(Map<String, Object> uinSummary) {
		this.uinSummary = uinSummary;
	}

	

}
