package com.opl.mudra.api.loans.model;

import java.io.Serializable;
import java.util.Arrays;
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
	
	public LoansResponse(Long id,String message, Integer status) {
		super();
		this.id = id;
		this.status = status;
		this.message = message;
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
	

	public LoansResponse(Integer status, String message, Object data, Map<String, Object> map) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		this.map = map;
	}
	
	public LoansResponse(Integer status, String message, Map<String, Object> map) {
		super();
		this.status = status;
		this.message = message;
		this.map = map;
	}
	public LoansResponse(Integer status, String message,List listData) {
		super();
		this.status = status;
		this.message = message;
		this.listData = listData;
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

	@Override
	public String toString() {
		return "LoansResponse [id=" + id + ", message=" + message + ", data=" + data + ", listData=" + listData
				+ ", mapData=" + mapData + ", map=" + map + ", contentInBytes=" + Arrays.toString(contentInBytes)
				+ ", flag=" + flag + ", success=" + success + ", response_code=" + response_code
				+ ", response_code_message=" + response_code_message + ", status=" + status + "]";
	}
	
	
	
	
	
}
