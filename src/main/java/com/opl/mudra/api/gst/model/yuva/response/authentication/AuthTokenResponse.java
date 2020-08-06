/**
 * 
 */
package com.opl.mudra.api.gst.model.yuva.response.authentication;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class AuthTokenResponse extends OTPResponse {

	@JsonProperty("auth_token")
	private String authToken;
	
	private String expiry;
	
	private String sek;

	/**
	 * @return the authToken
	 */
	public String getAuthToken() {
		return authToken;
	}

	/**
	 * @param authToken the authToken to set
	 */
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	/**
	 * @return the expiry
	 */
	public String getExpiry() {
		return expiry;
	}

	/**
	 * @param expiry the expiry to set
	 */
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	/**
	 * @return the sek
	 */
	public String getSek() {
		return sek;
	}

	/**
	 * @param sek the sek to set
	 */
	public void setSek(String sek) {
		this.sek = sek;
	}
	
	 
	
}
