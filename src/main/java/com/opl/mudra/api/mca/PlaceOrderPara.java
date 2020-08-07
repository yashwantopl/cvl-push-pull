package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sanket
 *
 */
public class PlaceOrderPara {
	
	@JsonProperty("api_auth_token")
	private String apiAuthToken;
	
	@JsonProperty("secret_key")
	private String secretKey;
	
	@JsonProperty("user_id")
	private String userId;
	
	@JsonProperty("coupon_code")
	private String couponCode;
	
	private String amount="500";
	
	private String param1;
	
	private String param2;

	@JsonProperty("source_system")
	private String sourceSystem;
	
	@JsonProperty("webhook_urls")	
	private PlaceOrderWebHookUrls[] webHookUrls;
	
	@JsonProperty("company_data")
	private PlaceOrderCompanyData[] companydata;

	public String getApiAuthToken() {
		return apiAuthToken;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public String getUserId() {
		return userId;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public String getAmount() {
		return amount;
	}

	public String getParam1() {
		return param1;
	}

	public String getParam2() {
		return param2;
	}

	public String getSourceSystem() {
		return sourceSystem;
	}

	public void setApiAuthToken(String apiAuthToken) {
		this.apiAuthToken = apiAuthToken;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public PlaceOrderWebHookUrls[] getWebHookUrls() {
		return webHookUrls;
	}

	public PlaceOrderCompanyData[] getCompanydata() {
		return companydata;
	}

	public void setWebHookUrls(PlaceOrderWebHookUrls[] webHookUrls) {
		this.webHookUrls = webHookUrls;
	}

	public void setCompanydata(PlaceOrderCompanyData[] companydata) {
		this.companydata = companydata;
	}
	
	

}
