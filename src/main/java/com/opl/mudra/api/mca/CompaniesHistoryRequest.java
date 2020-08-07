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
public class CompaniesHistoryRequest {
	
	private String request;
	
	private CompaniesHistoryPara para;

	public String getRequest() {
		return request;
	}

	public CompaniesHistoryPara getPara() {
		return para;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public void setPara(CompaniesHistoryPara para) {
		this.para = para;
	}
	
	
	
	

}
