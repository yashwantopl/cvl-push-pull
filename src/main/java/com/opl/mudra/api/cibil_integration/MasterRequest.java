package com.opl.mudra.api.cibil_integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
public class MasterRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long applicationId;

	private Long userId;

	private Integer fromBureauType;
	
	private Integer provider;
	
	private String pan;
	
	public MasterRequest() {
	}

	public MasterRequest(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getProvider() {
		return provider;
	}

	public void setProvider(Integer provider) {
		this.provider = provider;
	}

	public Integer getFromBureauType() {
		return fromBureauType;
	}

	public void setFromBureauType(Integer fromBureauType) {
		this.fromBureauType = fromBureauType;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}
}