package com.capitaworld.service.loans.model;

import java.io.Serializable;

public class GenerateTokenRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8358683835700479809L;
	private Long applicationId;
	private String password;
	private String token;
	
	public Long getApplicationId() {
		return applicationId;
	}


	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "GenerateTokenRequest [applicationId=" + applicationId + ", password=" + password + ", token=" + token
				+ "]";
	}
	
	
}
