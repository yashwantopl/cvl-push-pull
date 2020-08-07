/**
 * 
 */
package com.opl.mudra.api.mca.verifyApi;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sanket
 *
 */
public class VerifyAPIWebHookRequest {
	
	@JsonProperty("web-hook-url")
	private String webHookUrl;
	
	@JsonProperty("key-name")
	private String keyName;
	
	@JsonProperty("key-value")
	private String keyValue;

	/**
	 * @return the webHookUrl
	 */
	public String getWebHookUrl() {
		return webHookUrl;
	}

	/**
	 * @param webHookUrl the webHookUrl to set
	 */
	public void setWebHookUrl(String webHookUrl) {
		this.webHookUrl = webHookUrl;
	}

	/**
	 * @return the keyName
	 */
	public String getKeyName() {
		return keyName;
	}

	/**
	 * @param keyName the keyName to set
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	/**
	 * @return the keyValue
	 */
	public String getKeyValue() {
		return keyValue;
	}

	/**
	 * @param keyValue the keyValue to set
	 */
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	
	
	

}
