package com.opl.mudra.api.connect;

import java.io.Serializable;

public class TeleCallCenterResponse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Object data;
	private Integer status;
	private String message;

	public TeleCallCenterResponse() {
		super();
	}

	public TeleCallCenterResponse(Object data) {
		super();
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String toString(){
		return "test";
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
