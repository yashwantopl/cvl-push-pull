package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class SearchDirectorsRequest {
	
private String request;
	
	private McaDirectorRequestPara para;

	public String getRequest() {
		return request;
	}

	public McaDirectorRequestPara getPara() {
		return para;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public void setPara(McaDirectorRequestPara para) {
		this.para = para;
	}
	
	

}
