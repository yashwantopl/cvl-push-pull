package com.opl.mudra.api.analyzer.model.common;

import java.io.Serializable;


public class AnalyzerMobileResponse implements Serializable{

	/**
	 * 
	 */
	private String transactionId;
	private String url;
	private String message;
	private String success;
	private Integer status;
	private Object data;
	private String error;

	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public AnalyzerMobileResponse() {
		super();
}
	public AnalyzerMobileResponse(String message, String success) {
		super();
		this.message = message;
		this.success = success;
	}
	
	public AnalyzerMobileResponse(String message, String success,Integer status) {
		super();
		this.message = message;
		this.success = success;
		this.status = status;
	}
	
	public AnalyzerMobileResponse(String message, String success,Integer status,String error) {
		super();
		this.message = message;
		this.success = success;
		this.status = status;
		this.error = error;
	}
	
	public AnalyzerMobileResponse(String message, String success,MobileResponse mobileResponse) {
		super();
		this.message = message;
		this.success = success;
		this.status = mobileResponse.getStatusCode();
		this.error = mobileResponse.getDescription();
}

	@Override
	public String toString() {
		return "AnalyzerMobileResponse [transactionId=" + transactionId + ", url=" + url + ", message=" + message
				+ ", success=" + success + ", status=" + status + ", data=" + data + ", error=" + error + "]";
	}


	
	
	
}
