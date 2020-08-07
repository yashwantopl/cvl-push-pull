/**
 * 
 */
package com.opl.mudra.api.fraudanalytics.model;

import java.util.Collections;
import java.util.List;

/**
 * @author sanket
 *
 */
public class AnalyticsResponse {

	/**
	 * @param status
	 * @param message
	 */
	public AnalyticsResponse(Integer status, String message) {
		this.status=status;
		this.message=message;
	}

	/**
	 * @param status
	 * @param message
	 * @param data
	 */
	public AnalyticsResponse(Integer status, String message, Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	/**
	 * @param status
	 * @param message
	 * @param listData
	 */
	public AnalyticsResponse(Integer status, String message,  List<?> listData) {
		super();
		this.status = status;
		this.message = message;
		this.listData = listData;
	}

	
	public AnalyticsResponse() {
		super();
	}
	private Long id;

	private Integer status;

	private String message;

	private Object data;

	private List<?> listData = Collections.emptyList();
	
	private String statusCd;
	
	private String username;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the listData
	 */
	public List<?> getListData() {
		return listData;
	}

	/**
	 * @param listData the listData to set
	 */
	public void setListData(List<?> listData) {
		this.listData = listData;
	}

	/**
	 * @return the statusCd
	 */
	public String getStatusCd() {
		return statusCd;
	}

	/**
	 * @param statusCd the statusCd to set
	 */
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AnalyticsResponse [id=" + id + ", status=" + status + ", message=" + message + ", data=" + data
				+ ", listData=" + listData + ", statusCd=" + statusCd + ", username=" + username + "]";
	}
	
	
	
}
