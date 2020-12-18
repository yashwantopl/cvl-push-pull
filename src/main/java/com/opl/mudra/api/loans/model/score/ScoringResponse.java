package com.opl.mudra.api.loans.model.score;

import java.util.List;

public class ScoringResponse {

	
	private Long id;

	private Integer status;

	private String message;
	
	private Object dataObject;
	
	private List dataList;

	private List<ScoringResponse> scoringResponseList;

	public List<ScoringResponse> getScoringResponseList() {
		return scoringResponseList;
	}

	public void setScoringResponseList(List<ScoringResponse> scoringResponseList) {
		this.scoringResponseList = scoringResponseList;
	}

	public ScoringResponse() {
		
	}

	public ScoringResponse(String message,Integer status) {
		super();
		this.status = status;
		this.message = message;
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

	public Object getDataObject() {
		return dataObject;
	}

	public void setDataObject(Object dataObject) {
		this.dataObject = dataObject;
	}

	public List getDataList() {
		return dataList;
	}

	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
}
