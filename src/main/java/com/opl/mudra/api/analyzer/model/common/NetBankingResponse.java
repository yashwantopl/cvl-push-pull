package com.opl.mudra.api.analyzer.model.common;

public class NetBankingResponse {

	String  perfiosTransactionId;
	String clientTransactionId;
	String status;
	public String getPerfiosTransactionId() {
		return perfiosTransactionId;
	}
	public void setPerfiosTransactionId(String perfiosTransactionId) {
		this.perfiosTransactionId = perfiosTransactionId;
	}
	public String getClientTransactionId() {
		return clientTransactionId;
	}
	public void setClientTransactionId(String clientTransactionId) {
		this.clientTransactionId = clientTransactionId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
