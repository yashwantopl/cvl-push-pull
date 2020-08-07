package com.opl.mudra.api.itr.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonRes implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Object data;
	private Boolean result;
	private String msg;
	private String errorCode;
	
	public CommonRes() {
		super();
	}
	
	public CommonRes(Long id, Object data, Boolean result, String msg, String errorCode) {
		super();
		this.id = id;
		this.data = data;
		this.result = result;
		this.msg = msg;
		this.errorCode = errorCode;
	}
	
	public CommonRes(Boolean result, String msg, String errorCode) {
		super();
		this.result = result;
		this.msg = msg;
		this.errorCode = errorCode;
	}
	
	public CommonRes(Boolean result, String msg) {
		super();
		this.result = result;
		this.msg = msg;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Boolean getResult() {
		return result;
	}
	public void setResult(Boolean result) {
		this.result = result;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	
	

}
