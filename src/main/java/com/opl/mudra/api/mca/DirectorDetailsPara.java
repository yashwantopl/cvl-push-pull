package com.opl.mudra.api.mca;

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
public class DirectorDetailsPara {

//	@JsonProperty("apikey")
	@JsonProperty("api_auth_token")
	private String apiAuthToken;
	
	@JsonProperty("user_id")
	private String userId;
	
	@JsonProperty("din")
	private String[] din;

	public String getApiAuthToken() {
		return apiAuthToken;
	}

	public String getUserId() {
		return userId;
	}


	public void setApiAuthToken(String apiAuthToken) {
		this.apiAuthToken = apiAuthToken;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String[] getDin() {
		return din;
	}

	public void setDin(String[] din) {
		this.din = din;
	}

	

	

	
}
