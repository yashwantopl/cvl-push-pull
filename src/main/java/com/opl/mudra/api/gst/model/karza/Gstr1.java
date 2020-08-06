package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Gstr1 {

	@JsonProperty("total")
	private Total total;
	
	@JsonProperty("details")
	private Details[] details;

	public Total getTotal() {
		return total;
	}

	public void setTotal(Total total) {
		this.total = total;
	}

	public Details[] getDetails() {
		return details;
	}

	public void setDetails(Details[] details) {
		this.details = details;
	}
	
	
}
