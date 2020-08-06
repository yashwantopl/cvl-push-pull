package com.opl.mudra.api.cibil_integration.experian;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HTTPConnectionResponse implements Serializable {

	/**
	 * Auto Generated 
	 */
	private static final long serialVersionUID = 5399968166123993109L;
	
	private String output;
	private String input;
	private Integer statusCode;
	
	public HTTPConnectionResponse() {
	}
	
	public HTTPConnectionResponse(Integer statusCode, String output) {
		this.output = output;
		this.statusCode = statusCode;
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
	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
}
