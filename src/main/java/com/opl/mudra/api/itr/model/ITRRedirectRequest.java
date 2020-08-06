package com.opl.mudra.api.itr.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ITRRedirectRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String uuid;
	private String status;
	private String errorCode;
	private String error;
	private String clientTransactionId;
	private String txnId;
	private String message;
	private Long userId;
	
	public ITRRedirectRequest() {
		super();
	}
	
	public ITRRedirectRequest(String uuid, String status, String errorCode, String error, String clientTransactionId,
			String txnId, String message, Long userId) {
		super();
		this.uuid = uuid;
		this.status = status;
		this.errorCode = errorCode;
		this.error = error;
		this.clientTransactionId = clientTransactionId;
		this.txnId = txnId;
		this.message = message;
		this.userId = userId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getClientTransactionId() {
		return clientTransactionId;
	}
	public void setClientTransactionId(String clientTransactionId) {
		this.clientTransactionId = clientTransactionId;
	}
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "ITRRedirectRequest [uuid=" + uuid + ", status=" + status + ", errorCode=" + errorCode + ", error="
				+ error + ", clientTransactionId=" + clientTransactionId + ", txnId=" + txnId + ", message=" + message
				+ "]";
	}
	
	
	
	
	
}
