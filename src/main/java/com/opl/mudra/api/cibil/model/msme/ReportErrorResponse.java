package com.opl.mudra.api.cibil.model.msme;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportErrorResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6504551812114934733L;

	private Long code;
	private String message;
	private String descritption;

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescritption() {
		return descritption;
	}

	public void setDescritption(String descritption) {
		this.descritption = descritption;
	}

	@Override
	public String toString() {
		return "ReportErrorResponse [code=" + code + ", message=" + message + ", descritption=" + descritption + "]";
	}
}
