package com.opl.mudra.api.itr.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ITRConnectionResponse implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String transactionId;
	private String pan;
	private String url;
	private String message;
	private String errorCode;
	private String success;
	private Integer status;
	private Object data;
	private String error;
	private Boolean isEligible;
	private Boolean isResFromScrap;
	private List<byte[]> byteArray;
	private Boolean isPanAlreadyFetched;
	
	public ITRConnectionResponse() {
		super();
		byteArray = new ArrayList<byte[]>();
	}
	
	public ITRConnectionResponse(Object data, String message, Integer status) {
		super();
		this.message = message;
		this.status = status;
		this.data = data;
	}



	public ITRConnectionResponse(String message,String errorCode , String success) {
		super();
		this.message = message;
		this.success = success;
		this.errorCode = errorCode;
	}
	
	public ITRConnectionResponse(String message, String errorCode, String success, Object data) {
		super();
		this.message = message;
		this.success = success;
		this.data = data;
		this.errorCode = errorCode;
	}
	
	public ITRConnectionResponse(String message, String errorCode, String success,Integer status) {
		super();
		this.message = message;
		this.success = success;
		this.status = status;
		this.errorCode = errorCode;
	}
	
	public ITRConnectionResponse(String message, String errorCode, String success,Integer status,Object data) {
		super();
		this.message = message;
		this.success = success;
		this.status = status;
		this.data = data;
		this.errorCode = errorCode;
	}
	
	public ITRConnectionResponse(String message,String errorCode, String success,Integer status,Boolean isResFromScrap) {
		super();
		this.message = message;
		this.success = success;
		this.status = status;
		this.isResFromScrap = isResFromScrap;
		this.errorCode = errorCode;
	}
	
	public ITRConnectionResponse(String message, String errorCode, String success,Integer status,String error) {
		super();
		this.message = message;
		this.success = success;
		this.status = status;
		this.error = error;
		this.errorCode = errorCode;
	}
	
	
	//------------------------------------ START------------------------------------------------------------------------
	
	public ITRConnectionResponse(String message , String success) {
		super();
		this.message = message;
		this.success = success;
	}
	
	public ITRConnectionResponse(String message, String success, Object data) {
		super();
		this.message = message;
		this.success = success;
		this.data = data;
	}
	
	public ITRConnectionResponse(String message, String success,Integer status) {
		super();
		this.message = message;
		this.success = success;
		this.status = status;
	}
	
	public ITRConnectionResponse(String message, String success,Integer status,Object data) {
		super();
		this.message = message;
		this.success = success;
		this.status = status;
		this.data = data;
	}
	
	public ITRConnectionResponse(String message, String success,Integer status,Boolean isResFromScrap) {
		super();
		this.message = message;
		this.success = success;
		this.status = status;
		this.isResFromScrap = isResFromScrap;
	}
	
	public ITRConnectionResponse(String message, String success,Integer status,String error) {
		super();
		this.message = message;
		this.success = success;
		this.status = status;
		this.error = error;
	}
	//------------------------------------------END -------------------------------------------
	
	
	public ITRConnectionResponse(String message, String success,MobileResponse mobileResponse) {
		super();
		this.message = message;
		this.success = success;
		this.status = mobileResponse.getStatusCode();
		this.error = mobileResponse.getDescription();
	}
	
	
	/**
	 * @return the byteArray
	 */
	public List<byte[]> getByteArray() {
		return byteArray;
	}

	/**
	 * @param byteArray the byteArray to set
	 */
	public void setByteArray(List<byte[]> byteArray) {
		this.byteArray = byteArray;
	}

	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
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
	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	public Boolean getIsResFromScrap() {
		return isResFromScrap;
	}

	public void setIsResFromScrap(Boolean isResFromScrap) {
		this.isResFromScrap = isResFromScrap;
	}


	public Boolean getIsEligible() {
		return isEligible;
	}
	public void setIsEligible(Boolean isEligible) {
		this.isEligible = isEligible;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public Boolean getIsPanAlreadyFetched() {
		return isPanAlreadyFetched;
	}

	public void setIsPanAlreadyFetched(Boolean isPanAlreadyFetched) {
		this.isPanAlreadyFetched = isPanAlreadyFetched;
	}
	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	@Override
	public String toString() {
		return "ITRConnectionResponse [transactionId=" + transactionId + ", url=" + url + ", message=" + message
				+ ", errorCode=" + errorCode + ", success=" + success + ", status=" + status + ", data=" + data
				+ ", error=" + error + ", isEligible=" + isEligible + ", isResFromScrap=" + isResFromScrap
				+ ", byteArray=" + byteArray + "]";
	}


	
	
	
	
	
}
