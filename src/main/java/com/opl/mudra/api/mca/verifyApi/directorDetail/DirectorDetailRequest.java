package com.opl.mudra.api.mca.verifyApi.directorDetail;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opl.mudra.api.mca.McaCommonUtils;

/**
  * @auther : Maaz Shaikh
  * @Time : 02-Aug-2019 - 6:16:37 PM
  */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DirectorDetailRequest {
	
	@JsonProperty("request")
	private String request;
	
	@JsonProperty("para")
	private DirectorDetailPara para;

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public DirectorDetailPara getPara() {
		return para;
	}

	public void setPara(DirectorDetailPara para) {
		this.para = para;
	}

	@Override
	public String toString() {
		return McaCommonUtils.convertObjectToString(this);
	}
	
	
}
