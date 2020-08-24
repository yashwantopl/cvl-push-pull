package com.opl.mudra.api.scoring.model.v4;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScoringResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer status;

	private String message;
	
	private Object data;
	
	private List<?> dataList = Collections.emptyList();
	
	private Map<String, Object> map;
	
	private List<ScoringVersionWithProduct> scoringVersionWithProducts;


	public ScoringResponse(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public ScoringResponse(Integer status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	public ScoringResponse(Integer status, String message, Object data, Map<String, Object> map) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		this.map = map;
	}
	
	public ScoringResponse(String message,Integer status, List<?> dataList) {
		super();
		this.status = status;
		this.message = message;
		this.data = dataList;
	}
}
