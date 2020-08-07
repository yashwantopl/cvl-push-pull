/*
* @author harshit
*/
/**
 * @author harshit
 */
package com.opl.mudra.api.itr.model.perfios;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author harshit
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ITRAPIRequest implements Serializable{

	private static final long serialVersionUID = 1L;

	private String clientTransactionId;
	private String transactionCompleteUrl;
	private String type;
	
	public ITRAPIRequest() {}
	
	public ITRAPIRequest(String transactionCompleteUrl, String type) {
		super();
		this.transactionCompleteUrl = transactionCompleteUrl;
		this.type = type;
	}
	
	public ITRAPIRequest(String clientTransactionId, String transactionCompleteUrl, String type) {
		super();
		this.clientTransactionId = clientTransactionId;
		this.transactionCompleteUrl = transactionCompleteUrl;
		this.type = type;
	}
	
	public String getClientTransactionId() {
		return clientTransactionId;
	}
	public void setClientTransactionId(String clientTransactionId) {
		this.clientTransactionId = clientTransactionId;
	}
	public String getTransactionCompleteUrl() {
		return transactionCompleteUrl;
	}
	public void setTransactionCompleteUrl(String transactionCompleteUrl) {
		this.transactionCompleteUrl = transactionCompleteUrl;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	
	
}
