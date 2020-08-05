package com.opl.mudra.api.connect;

import java.io.Serializable;

public class EmpMasterResponse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Integer status;
	private String message;
	private Object data;


	public EmpMasterResponse() {
		super();
	}

	public EmpMasterResponse(Integer status, String message,Object data) {
		super();
		this.message = message;
		this.status = status;
		this.data= data;
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
