package com.opl.mudra.api.cibil.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CibilResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer status;

	private String message;

	private Object data;

	private List<?> listData = Collections.emptyList();
	
	private Integer provider;

	public CibilResponse() {
		super();
	}

	public CibilResponse(String message) {
		super();
		this.message = message;
	}

	public CibilResponse(String message, Integer status) {
		super();
		this.message = message;
		this.status = status;
	}

	public CibilResponse(String message, Integer status, Object data) {
		super();
		this.message = message;
		this.status = status;
		this.data = data;
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

	public List<?> getListData() {
		return listData;
	}

	public void setListData(List<?> listData) {
		this.listData = listData;
	}

	public Integer getProvider() {
		return provider;
	}

	public void setProvider(Integer provider) {
		this.provider = provider;
	}

	@Override
	public String toString() {
		return "CibilResponse [id=" + id + ", status=" + status + ", message=" + message + ", data=" + (data != null ? data.toString() : null)
				+ ", listData=" + (listData != null ? listData.toString() : null) + "]";
	}

}
