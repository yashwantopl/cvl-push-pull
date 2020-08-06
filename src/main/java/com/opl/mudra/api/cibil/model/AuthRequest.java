package com.opl.mudra.api.cibil.model;

import java.io.Serializable;

public class AuthRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long applicationId;
	private String userName;
	private String pass;
	private Long bankId;
	
	public AuthRequest() {
		super();
	}
	
	public AuthRequest(Long applicationId) {
		super();
		this.applicationId = applicationId;
	}
	
	public AuthRequest(Long applicationId, String userName, String pass) {
		super();
		this.applicationId = applicationId;
		this.userName = userName;
		this.pass = pass;
	}
	
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Long getBankId() {
		return bankId;
	}
	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}
	
	
	
	
}
