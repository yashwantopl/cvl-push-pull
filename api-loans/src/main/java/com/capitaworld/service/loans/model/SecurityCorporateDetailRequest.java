package com.capitaworld.service.loans.model;

import java.io.Serializable;


/**
 * The persistent class for the fs_corporate_security_corporate_details database table.
 * 
 */
public class SecurityCorporateDetailRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Double amount;

	private Long applicationId;

	private String primarySecurityName;

	public SecurityCorporateDetailRequest() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Long getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}


	public String getPrimarySecurityName() {
		return this.primarySecurityName;
	}

	public void setPrimarySecurityName(String primarySecurityName) {
		this.primarySecurityName = primarySecurityName;
	}

}