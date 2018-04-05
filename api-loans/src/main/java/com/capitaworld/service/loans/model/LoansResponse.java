package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class LoansResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String message;

	private Object data;
	
	private List<?> listData = Collections.emptyList();
	
	private Map<String ,Map<String, Object>> mapData;
	
	private Map map;
	
	private byte[] contentInBytes;
	
	private Boolean flag;
	
	private String success;
	
	private Long response_code;
	
	private String response_code_message;
	
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public Long getResponse_code() {
		return response_code;
	}

	public void setResponse_code(Long response_code) {
		this.response_code = response_code;
	}

	public String getResponse_code_message() {
		return response_code_message;
	}

	public void setResponse_code_message(String response_code_message) {
		this.response_code_message = response_code_message;
	}

	public byte[] getContentInBytes() {
		return contentInBytes;
	}

	public void setContentInBytes(byte[] contentInBytes) {
		this.contentInBytes = contentInBytes;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Map<String, Map<String, Object>> getMapData() {
		return mapData;
	}

	public void setMapData(Map<String, Map<String, Object>> mapData) {
		this.mapData = mapData;
	}

	public LoansResponse() {
		super();
	}

	public LoansResponse(String message) {
		super();
		this.message = message;
	}
	
	public LoansResponse(String message, Integer status) {
		super();
		this.message = message;
		this.status = status;
	}
	
	public LoansResponse(String message, Integer status, Object data) {
		super();
		this.message = message;
		this.status = status;
		this.data = data;
	}
	
	public LoansResponse(String message, Integer status, byte[] contentInBytes) {
		super();
		this.message = message;
		this.status = status;
		this.contentInBytes = contentInBytes;
	}
	
	public LoansResponse(String success, Object data, Long response_code, String response_code_message, String message) {
		super();
		this.success = success;
		this.data = data;
		this.response_code = response_code;
		this.message = message;
		this.response_code_message = response_code_message;
	}
	public LoansResponse(String success, Long response_code, String response_code_message, String message) {
		super();
		this.success = success;
		this.response_code = response_code;
		this.response_code_message = response_code_message;
		this.message = message;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	
	
	
}
