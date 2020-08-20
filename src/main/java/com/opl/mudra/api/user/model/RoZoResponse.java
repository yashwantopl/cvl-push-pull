package com.opl.mudra.api.user.model;

import java.io.Serializable;

/**
 * 
 * @author vijay.chauhan
 *
 */
public class RoZoResponse  implements Serializable{

	private static final long serialVersionUID = 1L;
	private String message;
	private Integer status;
	private Object data;
	private RoResponse roRequest;
	private ZoResponse zoRequest;
	
	public RoZoResponse () {
		
	}
	public RoZoResponse(String message, int status) {
		this.message = message;
		this.status = status;
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
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public RoResponse getRoRequest() {
		return roRequest;
	}
	public void setRoRequest(RoResponse roRequest) {
		this.roRequest = roRequest;
	}
	public ZoResponse getZoRequest() {
		return zoRequest;
	}
	public void setZoRequest(ZoResponse zoRequest) {
		this.zoRequest = zoRequest;
	}
	
}