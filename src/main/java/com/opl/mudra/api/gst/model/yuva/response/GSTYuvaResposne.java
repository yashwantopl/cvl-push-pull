package com.opl.mudra.api.gst.model.yuva.response;

import com.opl.mudra.api.gst.model.karza.Result;

public class GSTYuvaResposne {

	private String statusCode;
	
	private String requestId;
	
	private Result result;
	

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}


	public GSTYuvaResposne(String statusCode, String requestId, Result result) {
		this.statusCode = statusCode;
		this.requestId = requestId;
		this.result = result;
	}
}
