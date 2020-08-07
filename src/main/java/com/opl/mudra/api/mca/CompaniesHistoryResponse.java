package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class CompaniesHistoryResponse {
	
	private String status;
	
	private String message;
	
	private CompaniesHistoryDetails[] companies;

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public CompaniesHistoryDetails[] getCompanies() {
		return companies;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setCompanies(CompaniesHistoryDetails[] companies) {
		this.companies = companies;
	}
	
	

}
