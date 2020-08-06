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
public class OTPResponse{
	

	/**
	 * @param statusCd
	 * @param message
	 */
	public OTPResponse() {
		super();
	}



	@JsonProperty("status_cd")
	private String statusCd;
	
	private String appKey;
	
	private String encrpytAppKey;
	
	private Error error;
	
	

	/**
	 * @return the encrpytAppKey
	 */
	public String getEncrpytAppKey() {
		return encrpytAppKey;
	}

	/**
	 * @param encrpytAppKey the encrpytAppKey to set
	 */
	public void setEncrpytAppKey(String encrpytAppKey) {
		this.encrpytAppKey = encrpytAppKey;
	}

	/**
	 * @return the error
	 */
	public Error getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(Error error) {
		this.error = error;
	}


	/**
	 * @return the appKey
	 */
	public String getAppKey() {
		return appKey;
	}

	/**
	 * @param appKey the appKey to set
	 */
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	/**
	 * @return the statusCd
	 */
	public String getStatusCd() {
		return statusCd;
	}

	/**
	 * @param statusCd the statusCd to set
	 */
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	
	

}
