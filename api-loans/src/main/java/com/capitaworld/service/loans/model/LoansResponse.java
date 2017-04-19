package com.capitaworld.service.loans.model;

public class LoansResponse {

	private Long id;

	private Integer status;

	private String message;

	private Object data;

	public LoansResponse() {
		super();
	}

	public LoansResponse(String message,Integer status) {
		super();
		this.message = message;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
