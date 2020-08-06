/*
* @author harshit
*/
/**
 * @author harshit
 */
package com.opl.mudra.api.itr.model.perfios;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * @author harshit
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ITRTrasactionStatusResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String clientTransactionId;
	private String errorCode;
	private String message;
	private String transactionId;
	private String transactionStatus;
	private List<ITRTrasFileStatusResponse> statementsStatus;
	
	
	
	public String getClientTransactionId() {
		return clientTransactionId;
	}
	public void setClientTransactionId(String clientTransactionId) {
		this.clientTransactionId = clientTransactionId;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public List<ITRTrasFileStatusResponse> getStatementsStatus() {
		return statementsStatus;
	}
	public void setStatementsStatus(List<ITRTrasFileStatusResponse> statementsStatus) {
		this.statementsStatus = statementsStatus;
	}
	@Override
	public String toString() {
		return "ITRTrasactionStatusResponse [clientTransactionId=" + clientTransactionId + ", errorCode=" + errorCode
				+ ", message=" + message + ", transactionId=" + transactionId + ", transactionStatus="
				+ transactionStatus + ", statementsStatus=" + statementsStatus.toString() + "]";
	}
	
	

}
