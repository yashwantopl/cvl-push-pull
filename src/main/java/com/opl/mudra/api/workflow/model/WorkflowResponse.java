package com.opl.mudra.api.workflow.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class WorkflowResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer status;

	private String message;

	private Object data;
	
	private List<?> listData = Collections.emptyList();

	public WorkflowResponse() {
		super();
	}

	public WorkflowResponse(String message) {
		super();
		this.message = message;
	}
	
	public WorkflowResponse(String message, Integer status) {
		super();
		this.message = message;
		this.status = status;
	}
	
	public WorkflowResponse(String message, Integer status, Object data) {
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
	
}
