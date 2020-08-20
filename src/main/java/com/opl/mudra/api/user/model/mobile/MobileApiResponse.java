package com.opl.mudra.api.user.model.mobile;

import java.io.Serializable;

public class MobileApiResponse implements Serializable{

private static final long serialVersionUID = 1L;
	
	private String success;
	private Object data;
	private Long responseCode;
	private String responseCodeMessage;
	private String message;	
	private Integer status;
	
	
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Long getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(Long responseCode) {
		this.responseCode = responseCode;
	}
	
	public String getResponseCodeMessage() {
		return responseCodeMessage;
	}
	public void setResponseCodeMessage(String responseCodeMessage) {
		this.responseCodeMessage = responseCodeMessage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public MobileApiResponse(String success, Object data, Long responseCode, String responseCodeMessage, String message) {
		super();
		this.success = success;
		this.data = data;
		this.responseCode = responseCode;
		this.message = message;
		this.responseCodeMessage = responseCodeMessage;
	}
	public MobileApiResponse(String success, Object data, Integer status, String responseCodeMessage, String message) {
		super();
		this.success = success;
		this.data = data;
		this.status = status;
		this.message = message;
		this.responseCodeMessage = responseCodeMessage;
	}
	public MobileApiResponse(String success, Long responseCode, String responseCodeMessage, String message) {
		super();
		this.success = success;
		this.responseCode = responseCode;
		this.responseCodeMessage = responseCodeMessage;
		this.message = message;
	}
	public MobileApiResponse(String success, Integer status, String responseCodeMessage, String message) {
		super();
		this.success = success;
		this.status = status;
		this.responseCodeMessage = responseCodeMessage;
		this.message = message;
	}
	
	public MobileApiResponse() {
		super();
	}
}
