package com.opl.mudra.api.matchengine.model;

import java.util.Collections;
import java.util.List;

public class ProposalMappingResponse {

	private Long id;

	private Integer status;

	private String message;

	private Object data;
	
	private List<?> dataList = Collections.emptyList();
	
	private Long userType;
	
	private Boolean flag;
	
	public ProposalMappingResponse(String message,Integer status) {
		super();
		this.message = message;
		this.status = status;
	}
	public ProposalMappingResponse(String message,Integer status,Long id) {
		super();
		this.message = message;
		this.status = status;
		this.id = id;
	}

	public ProposalMappingResponse(String message, Integer status, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public ProposalMappingResponse() {
		super();
	}

	public List<?> getDataList() {
		return dataList;
	}

	public Long getUserType() {
		return userType;
	}

	public void setUserType(Long userType) {
		this.userType = userType;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
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

	@Override
	public String toString() {
		return "ProposalMappingResponse [id=" + id + ", status=" + status + ", message=" + message + ", data=" + data
				+ ", dataList=" + dataList + ", userType=" + userType + ", flag=" + flag + "]";
	}
}
