package com.opl.mudra.api.oneform.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OneFormResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer status;

	private String message;

	private Object data;
	
	private List<?> listData = Collections.emptyList();

	public OneFormResponse() {
		super();
	}

	public OneFormResponse(String message, Integer status) {
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

	public List<?> getListData() {
		return listData;
	}

	public void setListData(List<?> listData) {
		this.listData = listData;
	}
	
}
