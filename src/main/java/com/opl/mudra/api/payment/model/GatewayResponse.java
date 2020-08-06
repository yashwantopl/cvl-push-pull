package com.opl.mudra.api.payment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GatewayResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer status;

	private String message;

	private Object data;

	private List<?> listData = Collections.emptyList();

	private Map<String, Object> map;
	
	//LoansResponse
	private Map<String ,Map<String, Object>> mapData;
	
	public Map<String, Map<String, Object>> getMapData() {
		return mapData;
	}

	public void setMapData(Map<String, Map<String, Object>> mapData) {
		this.mapData = mapData;
	}

	public byte[] getContentInBytes() {
		return contentInBytes;
	}

	public void setContentInBytes(byte[] contentInBytes) {
		this.contentInBytes = contentInBytes;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public Long getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Long responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseCodeMessage() {
		return responseCodeMessage;
	}

	public void setResponseCodeMessage(String responseCodeMessage) {
		this.responseCodeMessage = responseCodeMessage;
	}

	private byte[] contentInBytes;
	
	private Boolean flag;
	
	private String success;
	
	private Long responseCode;
	
	private String responseCodeMessage;

	public GatewayResponse() {
		super();
	}

	//LoansResponse
	public GatewayResponse(Long id,String message, Integer status) {
		super();
		this.id = id;
		this.status = status;
		this.message = message;
	}
	
	public GatewayResponse(String message) {
		super();
		this.message = message;
	}
	
	public GatewayResponse(String message, Integer status, byte[] contentInBytes) {
		super();
		this.message = message;
		this.status = status;
		this.contentInBytes = contentInBytes;
	}
	
	public GatewayResponse(String success, Object data, Long responseCode, String responseCodeMessage, String message) {
		super();
		this.success = success;
		this.data = data;
		this.responseCode = responseCode;
		this.message = message;
		this.responseCodeMessage = responseCodeMessage;
	}
	
	public GatewayResponse(String success, Long responseCode, String responseCodeMessage, String message) {
		super();
		this.success = success;
		this.responseCode = responseCode;
		this.responseCodeMessage = responseCodeMessage;
		this.message = message;
	}

	public GatewayResponse(Integer status, String message, Object data, Map<String, Object> map) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		this.map = map;
	}
	
	//GatewayResponse
	public GatewayResponse(String message, Object data, Integer status) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public GatewayResponse(String message, Integer status) {
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

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "GatewayResponse [id=" + id + ", status=" + status + ", message=" + message + ", data=" + data
				+ ", listData=" + listData + ", mapData=" + mapData + ", map=" + map + 
				", contentInBytes=" + Arrays.toString(contentInBytes)+ ", flag=" + flag + ", success=" + success +
				", responseCode=" + responseCode+ ", responseCodeMessage=" + responseCodeMessage +"]";
	}

}
