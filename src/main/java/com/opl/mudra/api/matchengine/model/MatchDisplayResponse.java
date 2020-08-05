package com.opl.mudra.api.matchengine.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchDisplayResponse {
	

	private Long id;

	private Integer status;

	private String message;
	
	private List<MatchDisplayObject> matchDisplayObjectList = Collections.emptyList();

	private Map<String,MatchDisplayObject> matchDisplayObjectMap =new HashMap<String, MatchDisplayObject>();

	public MatchDisplayResponse() {
		super();
	}

	public MatchDisplayResponse(String message,Integer status) {
		super();
		this.message = message;
		this.status = status;
	}
	
	public MatchDisplayResponse(String message,Integer status,List matchDisplayObjectList) {
		super();
		this.message = message;
		this.status = status;
		this.matchDisplayObjectList=matchDisplayObjectList;
	}

	public MatchDisplayResponse(String message, Integer status, Map<String,MatchDisplayObject> matchDisplayObjectMap) {
		super();
		this.message = message;
		this.status = status;
		this.matchDisplayObjectMap=matchDisplayObjectMap;
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

	public List<MatchDisplayObject> getMatchDisplayObjectList() {
		return matchDisplayObjectList;
	}

	public void setMatchDisplayObjectList(List<MatchDisplayObject> matchDisplayObjectList) {
		this.matchDisplayObjectList = matchDisplayObjectList;
	}

	public Map<String, MatchDisplayObject> getMatchDisplayObjectMap() {
		return matchDisplayObjectMap;
	}

	public void setMatchDisplayObjectMap(Map<String, MatchDisplayObject> matchDisplayObjectMap) {
		this.matchDisplayObjectMap = matchDisplayObjectMap;
	}
}
