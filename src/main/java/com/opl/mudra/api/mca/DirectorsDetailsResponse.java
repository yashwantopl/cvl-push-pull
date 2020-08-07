package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class DirectorsDetailsResponse {
	
	private String status;
	
	private String message;
	
	@JsonProperty("directors")
	private DirectorsDetailsData[] data;

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public DirectorsDetailsData[] getData() {
		return data;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(DirectorsDetailsData[] data) {
		this.data = data;
	}
	
	
	
	

}
