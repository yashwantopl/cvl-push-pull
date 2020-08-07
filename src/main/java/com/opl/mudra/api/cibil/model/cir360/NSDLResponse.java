package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class NSDLResponse {
	
	@JsonProperty("Status")
	private String status;
	
	@JsonProperty("Error")
	private ErrorDetails error;
	
	@JsonProperty("NSDLResponseDetails")
	private List<NSDLResponseDetails> nsdlResponseDetails;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ErrorDetails getError() {
		return error;
	}

	public void setError(ErrorDetails error) {
		this.error = error;
	}

	public List<NSDLResponseDetails> getNsdlResponseDetails() {
		return nsdlResponseDetails;
	}

	public void setNsdlResponseDetails(List<NSDLResponseDetails> nsdlResponseDetails) {
		this.nsdlResponseDetails = nsdlResponseDetails;
	}

}
