package com.opl.mudra.api.cibil.model.experian;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HTTPConnectionResponse implements Serializable {

	/**
	 * Auto Generated 
	 */
	private static final long serialVersionUID = 5399968166123993109L;
	
	private String output;
	private Integer statusCode;
	
	public HTTPConnectionResponse() {
	}
	
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	
}
