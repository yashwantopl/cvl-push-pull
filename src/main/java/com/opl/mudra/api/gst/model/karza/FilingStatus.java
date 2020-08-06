package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class FilingStatus {
	
	@JsonProperty("ret_period")
	private String retPeriod;
	
	private Status[] status;


	public String getRetPeriod() {
		return retPeriod;
	}

	public void setRetPeriod(String retPeriod) {
		this.retPeriod = retPeriod;
	}

	public Status[] getStatus() {
		return status;
	}

	public void setStatus(Status[] status) {
		this.status = status;
	}
	
	

}
