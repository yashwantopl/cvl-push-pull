package com.opl.mudra.api.analyzer.model.common;

public class NetBankingStartResponse {

	private String payload;
	private String signature;
	public String getPayload() {
		return payload;
	}
	public void setPayload(String payload) {
		this.payload = payload;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	@Override
	public String toString() {
		return "NetBankingStartResponse [payload=" + payload + ", signature=" + signature + "]";
	}
	
	
	
	
}
