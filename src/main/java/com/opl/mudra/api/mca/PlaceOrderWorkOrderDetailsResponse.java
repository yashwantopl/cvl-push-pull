package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sanket
 *
 */
public class PlaceOrderWorkOrderDetailsResponse {
	
	@JsonProperty("company-id")
	private String companyId;
	
	@JsonProperty("reference-id")
	private String referenceId;

	public String getCompanyId() {
		return companyId;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}
	
	

}
