/**
 * 
 */
package com.opl.mudra.api.gst.model;

import java.io.Serializable;

/**
 * @author sanket
 *
 */
public class GSTMobileResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String success;
	private Integer status;
	private Object data;
	private String error;
	private String statusCd;
	
	public GSTMobileResponse() {
		
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
	 * @return the error
	 */
	public String getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}
	/**
	 * @return the success
	 */
	public String getSuccess() {
		return success;
	}
	/**
	 * @param success the success to set
	 */
	public void setSuccess(String success) {
		this.success = success;
	}
	/**
	 * @param message
	 * @param success
	 * @param mobileResponse
	 */
	public GSTMobileResponse(String message, String success, MobileResponse mobileResponse) {
		super();
		this.message = message;
		this.success = success;
		this.status = mobileResponse.getStatusCode();
		this.error = mobileResponse.getDescription();
	}
	/**
	 * @param message
	 * @param success
	 * @param mobileResponse
	 * @param data
	 */
	public GSTMobileResponse(String message, String success, MobileResponse mobileResponse, Object data) {
		super();
		this.message = message;
		this.success = success;
		this.data = data;
		this.status = mobileResponse.getStatusCode();
		this.error = mobileResponse.getDescription();
	}
	
	
	public GSTMobileResponse(String message, String success, MobileResponse mobileResponse, Object data, String statusCd) {
		super();
		this.message = message;
		this.success = success;
		this.data = data;
		this.statusCd = statusCd;
		this.status = mobileResponse.getStatusCode();
		this.error = mobileResponse.getDescription();
	}

	public String getStatusCd() {
		return statusCd;
	}

	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}


	@Override
	public String toString() {
		return "GSTMobileResponse [message=" + message + ", success=" + success + ", status=" + status + ", data="
				+ data + ", error=" + error + ", statusCd=" + statusCd + "]";
	}
	
	
}
