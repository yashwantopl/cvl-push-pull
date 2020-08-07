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
public class DirectorsDetailRequest {

	private String request;
	
	private DirectorDetailsPara para;

	public String getRequest() {
		return request;
	}

	public DirectorDetailsPara getPara() {
		return para;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public void setPara(DirectorDetailsPara para) {
		this.para = para;
	}
}
