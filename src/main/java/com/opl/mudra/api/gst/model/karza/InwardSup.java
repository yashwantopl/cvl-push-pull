package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class InwardSup {

	@JsonProperty("isup_details")
	private IsupDetails[] isupDetails;

	public IsupDetails[] getIsupDetails() {
		return isupDetails;
	}

	public void setIsupDetails(IsupDetails[] isupDetails) {
		this.isupDetails = isupDetails;
	}
	
	
}
