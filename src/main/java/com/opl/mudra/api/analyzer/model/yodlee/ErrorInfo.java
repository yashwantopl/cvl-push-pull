package com.opl.mudra.api.analyzer.model.yodlee;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class ErrorInfo {
	private String message;
	
	private String description;
	
	private String errorCode;
	private String errorMessage;
	private String referenceCode;

	public ErrorInfo(String message, String description) {
		this.message = message;
		this.description = description;
	}
	
	public ErrorInfo()
	{
		
	}

	public String getMessage() {
		return message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getReferenceCode() {
		return referenceCode;
	}

	public void setReferenceCode(String referenceCode) {
		this.referenceCode = referenceCode;
	}
	
	
}
