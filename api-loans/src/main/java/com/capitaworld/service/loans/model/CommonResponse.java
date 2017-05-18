package com.capitaworld.service.loans.model;

public class CommonResponse {
	private Long id;
	private String value;

	public CommonResponse() {
		super();
	}

	public CommonResponse(Long id, String value) {
		super();
		this.id = id;
		this.value = value;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
