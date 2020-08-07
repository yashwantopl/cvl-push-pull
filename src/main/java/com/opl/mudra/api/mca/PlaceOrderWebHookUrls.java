package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sanket
 *
 */
public class PlaceOrderWebHookUrls {
	
	@JsonProperty("web-hook-url")
	private String webHookUrl;
	
	@JsonProperty("key-name")
	private String keyName;
	
	@JsonProperty("key-value")
	private String keyValue;

	public String getWebHookUrl() {
		return webHookUrl;
	}

	public String getKeyName() {
		return keyName;
	}

	public String getKeyValue() {
		return keyValue;
	}

	public void setWebHookUrl(String webHookUrl) {
		this.webHookUrl = webHookUrl;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	
	

}
