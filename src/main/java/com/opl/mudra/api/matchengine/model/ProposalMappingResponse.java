package com.opl.mudra.api.matchengine.model;

import java.util.Collections;
import java.util.List;

public class ProposalMappingResponse {

	@Override
	public String toString() {
		return "ProposalMappingResponse [id=" + id + ", status=" + status + ", message=" + message + ", data=" + data
				+ ", dataList=" + dataList + ", userType=" + userType + ", flag=" + flag + "]";
	}

	private Long id;

	private Integer status;

	private String message;

	private Object data;
	
	private List<?> dataList = Collections.emptyList();
	
	private Long userType;
	
	private Boolean flag;
	
	

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public ProposalMappingResponse() {
		super();
	}

	public ProposalMappingResponse(String message,Integer status) {
		super();
		this.message = message;
		this.status = status;
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

}
