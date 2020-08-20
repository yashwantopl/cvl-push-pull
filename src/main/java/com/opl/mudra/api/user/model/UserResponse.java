package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String message;
	private Integer status;
	private Object data;
	private Boolean flag;
	private List<Long> branchList;
	private List<?> listData = Collections.emptyList();
	private Long lastAccessBusinessTypeId;
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public UserResponse(String message, int status) {
		this.message = message;
		this.status = status;
	}
	public UserResponse(Long id, String message, int status) {
		
		this.id = id;
		this.message = message;
		this.status = status;
	}
	public UserResponse(Long id, String message, int status,Long lastAccessBusinessTypeId) {
		
		this.id = id;
		this.message = message;
		this.status = status;
		this.lastAccessBusinessTypeId=lastAccessBusinessTypeId;
	}
	public UserResponse(Long userId, Object obj, String message, int status) {
		
		this.id = userId;
		this.message = message;
		this.status = status;
		this.data = obj;
	}

	public UserResponse(Object obj,String message, int status) {

		this.message = message;
		this.status = status;
		this.data = obj;
	}
	public UserResponse(Object obj,List<Long> listData,String message, int status) {
		
		this.message = message;
		this.status = status;
		this.data = obj;
		this.branchList=listData;
	}
	
	public UserResponse(Object obj,String message, int status,List<Object[]> listData) {
		this.message = message;
		this.status = status;
		this.data = obj;
		this.listData = listData;
	}
	@Override
	public String toString() {
		return "UserResponse [id=" + id + ", message=" + message + ", status="
				+ status + ", data=" + data + ", branchList=" + branchList
				+ ", listData=" + listData + ", lastAccessBusinessTypeId="
				+ lastAccessBusinessTypeId + "]";
	}
	
	
}
