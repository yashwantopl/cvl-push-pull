package com.opl.mudra.api.scoring.model;

import java.util.HashMap;
import java.util.Map;

public class FundSeekerInputRequest {

	private Long fieldId;
	
	private String name;
	
	private Integer type;
	
	private Map<String,Object> map=new HashMap<String,Object>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getFieldId() {
		return fieldId;
	}

	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}
}
