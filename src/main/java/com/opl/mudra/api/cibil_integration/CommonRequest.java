package com.opl.mudra.api.cibil_integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonRequest extends AuthRequest {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Object data;
	private String value;
	
	public CommonRequest() {
		super();
	}
	
	public CommonRequest(Object data) {
		super();
		this.data = data;
	}
	
	public CommonRequest(Object data, Long applicationId, String userName, String pass) {
		super(applicationId,userName,pass);
		this.data = data;
	}

	public CommonRequest(Long id, Object data, String value) {
		super();
		this.id = id;
		this.data = data;
		this.value = value;
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
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	
	

}
