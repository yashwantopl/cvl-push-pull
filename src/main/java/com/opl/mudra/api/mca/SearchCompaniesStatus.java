package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class SearchCompaniesStatus {

	@JsonProperty("error-code")
	private String errorCode;
	
	@JsonProperty("error-status")
	private String errorStatus;
	
	@JsonProperty("error-description")
	private String errorDescription;

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorStatus() {
		return errorStatus;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
	
}
