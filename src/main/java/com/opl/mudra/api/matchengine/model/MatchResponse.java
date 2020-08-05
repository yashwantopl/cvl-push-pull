package com.opl.mudra.api.matchengine.model;

import java.util.Collections;
import java.util.List;

public class MatchResponse {

	private Long id;

	private Integer status;

	private String message;

	private Object data;
	
	private Boolean isUBIMatched;
	
	private List<?> dataList = Collections.emptyList();

	private Boolean multiBankFlow;

	public Boolean getIsMultiBankFlow() {
		return multiBankFlow;
	}

	public void setIsMultiBankFlow(Boolean multiBankFlow) {
		this.multiBankFlow = multiBankFlow;
	}

	public MatchResponse() {
		super();
	}

	public MatchResponse(String message,Integer status) {
		super();
		this.message = message;
		this.status = status;
	}
	
	public MatchResponse(String message,Integer status,Object data) {
		super();
		this.message = message;
		this.status = status;
		this.data = data;
	}

	
	public List<?> getDataList() {
		return dataList;
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

	public Boolean getIsUBIMatched() {
		return isUBIMatched;
	}

	public void setIsUBIMatched(Boolean isUBIMatched) {
		this.isUBIMatched = isUBIMatched;
	}

	@Override
	public String toString() {
		return "MatchResponse{" +
				"id=" + id +
				", status=" + status +
				", message='" + message + '\'' +
				", data=" + data +
				", isUBIMatched=" + isUBIMatched +
				", dataList=" + dataList +
				'}';
	}
}
