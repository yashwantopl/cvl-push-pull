package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

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

	public List<Long> getBranchList() {
		return branchList;
	}

	public void setBranchList(List<Long> branchList) {
		this.branchList = branchList;
	}

	public List<?> getListData() {
		return listData;
	}

	public void setListData(List<?> listData) {
		this.listData = listData;
	}

	public UserResponse() {
		super();
		
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the lastAccessBusinessTypeId
	 */
	public Long getLastAccessBusinessTypeId() {
		return lastAccessBusinessTypeId;
	}

	/**
	 * @param lastAccessBusinessTypeId the lastAccessBusinessTypeId to set
	 */
	public void setLastAccessBusinessTypeId(Long lastAccessBusinessTypeId) {
		this.lastAccessBusinessTypeId = lastAccessBusinessTypeId;
	}
	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "UserResponse [id=" + id + ", message=" + message + ", status="
				+ status + ", data=" + data + ", branchList=" + branchList
				+ ", listData=" + listData + ", lastAccessBusinessTypeId="
				+ lastAccessBusinessTypeId + "]";
	}
	
	
}
