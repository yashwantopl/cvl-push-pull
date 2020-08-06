package com.opl.mudra.api.common;

import java.io.Serializable;


/**
 *  USE FOR COMMON REPONSE IN ALL REPOSITORY
 * @author harshit
 *
 */
public class CommonResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;

	private Object data;

	private Integer status;

	private Boolean flag;

	public CommonResponse() {
		super();
	}

	public CommonResponse(String message, Integer status) {
		super();
		this.message = message;
		this.status = status;
	}

	public CommonResponse(String message, Object data, Integer status) {
		super();
		this.message = message;
		this.data = data;
		this.status = status;
	}

	public CommonResponse(String message, Object data, Integer status, Boolean flag) {
		super();
		this.message = message;
		this.data = data;
		this.status = status;
		this.flag = flag;
	}
	
	public CommonResponse(String message, Integer status, Boolean flag) {
		super();
		this.message = message;
		this.status = status;
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

}
