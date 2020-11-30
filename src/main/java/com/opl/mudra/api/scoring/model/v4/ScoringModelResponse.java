package com.opl.mudra.api.scoring.model.v4;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class ScoringModelResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer status;

	private String message;
	
	private Object data;
	
	private List<?> dataList = Collections.emptyList();
	
	private Map<String, Object> map;
	
	private List<ScoringVersionWithProduct> scoringVersionWithProducts;


	public ScoringModelResponse() {
		
	}

	public ScoringModelResponse(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public ScoringModelResponse(Integer status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public ScoringModelResponse(Integer status, String message, Object data, Map<String, Object> map) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		this.map = map;
	}
	
	public ScoringModelResponse(Integer status, String message, List dataList) {
		super();
		this.status = status;
		this.message = message;
		this.data = dataList;
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

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public List<ScoringVersionWithProduct> getScoringVersionWithProducts() {
		return scoringVersionWithProducts;
	}

	public void setScoringVersionWithProducts(List<ScoringVersionWithProduct> scoringVersionWithProducts) {
		this.scoringVersionWithProducts = scoringVersionWithProducts;
	}
	
	

	
}