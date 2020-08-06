package com.opl.mudra.api.gst.model.yuva.response.gstr3bsummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class InterSup {
	
	@JsonProperty("uin_details")
	private UinDetails[] uinDetails;
	
	@JsonProperty("comp_summary")
	private CompSummary[] compSummary;

	@JsonProperty("comp_details")
	private CompDetails[] compDetails;
	
	@JsonProperty("uin_summary")
	private UinSummary[] uinSummary;
	
	@JsonProperty("unreg_details")
	private UnregDetails[] unregDetails;

	public UinDetails[] getUinDetails() {
		return uinDetails;
	}

	public void setUinDetails(UinDetails[] uinDetails) {
		this.uinDetails = uinDetails;
	}

	public CompSummary[] getCompSummary() {
		return compSummary;
	}

	public void setCompSummary(CompSummary[] compSummary) {
		this.compSummary = compSummary;
	}

	public CompDetails[] getCompDetails() {
		return compDetails;
	}

	public void setCompDetails(CompDetails[] compDetails) {
		this.compDetails = compDetails;
	}

	public UinSummary[] getUinSummary() {
		return uinSummary;
	}

	public void setUinSummary(UinSummary[] uinSummary) {
		this.uinSummary = uinSummary;
	}

	public UnregDetails[] getUnregDetails() {
		return unregDetails;
	}

	public void setUnregDetails(UnregDetails[] unregDetails) {
		this.unregDetails = unregDetails;
	}


	public InterSup() {
		this.uinDetails = new UinDetails[0];
		this.compSummary = new CompSummary[0];
		this.compDetails = new CompDetails[0];
		this.uinSummary = new UinSummary[0];
		this.unregDetails = new UnregDetails[0];
	}
}
