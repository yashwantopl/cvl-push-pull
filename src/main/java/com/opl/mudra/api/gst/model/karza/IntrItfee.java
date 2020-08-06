package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class IntrItfee {
	
	@JsonProperty("itfree_details")
	private ItfreeDetails itfreeDetails;


	@JsonProperty("intr_details")
	private IntrDetails intrDetails;

	public ItfreeDetails getItfreeDetails() {
		return itfreeDetails;
	}

	public void setItfreeDetails(ItfreeDetails itfreeDetails) {
		this.itfreeDetails = itfreeDetails;
	}

	/**
	 * @return the intrDetails
	 */
	public IntrDetails getIntrDetails() {
		return intrDetails;
	}

	/**
	 * @param intrDetails the intrDetails to set
	 */
	public void setIntrDetails(IntrDetails intrDetails) {
		this.intrDetails = intrDetails;
	}
	
	

}
