package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MonthlyDetailType {
	
	private String key;
	
	@JsonProperty("PaymentStatus")
	private String paymentStatus;
	
	@JsonProperty("SuitFiledStatus")
	private String suitFiledStatus;
	
	@JsonProperty("AssetClassificationStatus")
	private String assetClassificationStatus;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getSuitFiledStatus() {
		return suitFiledStatus;
	}

	public void setSuitFiledStatus(String suitFiledStatus) {
		this.suitFiledStatus = suitFiledStatus;
	}

	public String getAssetClassificationStatus() {
		return assetClassificationStatus;
	}

	public void setAssetClassificationStatus(String assetClassificationStatus) {
		this.assetClassificationStatus = assetClassificationStatus;
	}
	
	
}
