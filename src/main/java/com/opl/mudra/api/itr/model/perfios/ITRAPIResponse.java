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
public class ITRAPIResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	private String success;
	private String transactionId;
	private String errorCode;
	private String errorMessage;
	private String message;
	private String code;
	private List<ITRTrasFileStatusResponse> statementsStatus;


	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public List<ITRTrasFileStatusResponse> getStatementsStatus() {
		return statementsStatus;
	}
	public void setStatementsStatus(List<ITRTrasFileStatusResponse> statementsStatus) {
		this.statementsStatus = statementsStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
