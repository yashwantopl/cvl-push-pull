/**
 * 
 */
package com.opl.mudra.api.mca.verifyApi;

import java.util.List;

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
public class VerifyAPIPara {
	
	@JsonProperty("api_auth_token")
	private String apiAuthToken;
	
	@JsonProperty("user_id")
	private String userId;
	
	@JsonProperty("source_system")
	private String sourceSystem;
	
	@JsonProperty("din-pan")
	private List<VerifyAPIDINPAN> verifyAPIDINPANs;
	
	@JsonProperty("web-hooks")
	private List<VerifyAPIWebHookRequest> webHook;

	/**
	 * @return the apiAuthToken
	 */
	public String getApiAuthToken() {
		return apiAuthToken;
	}

	/**
	 * @param apiAuthToken the apiAuthToken to set
	 */
	public void setApiAuthToken(String apiAuthToken) {
		this.apiAuthToken = apiAuthToken;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the sourceSystem
	 */
	public String getSourceSystem() {
		return sourceSystem;
	}

	/**
	 * @param sourceSystem the sourceSystem to set
	 */
	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	/**
	 * @return the verifyAPIDINPANs
	 */
	public List<VerifyAPIDINPAN> getVerifyAPIDINPANs() {
		return verifyAPIDINPANs;
	}

	/**
	 * @param verifyAPIDINPANs the verifyAPIDINPANs to set
	 */
	public void setVerifyAPIDINPANs(List<VerifyAPIDINPAN> verifyAPIDINPANs) {
		this.verifyAPIDINPANs = verifyAPIDINPANs;
	}

	/**
	 * @return the webHook
	 */
	public List<VerifyAPIWebHookRequest> getWebHook() {
		return webHook;
	}

	/**
	 * @param webHook the webHook to set
	 */
	public void setWebHook(List<VerifyAPIWebHookRequest> webHook) {
		this.webHook = webHook;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VerifyAPIPara [apiAuthToken=" + apiAuthToken + ", userId=" + userId + ", sourceSystem=" + sourceSystem
				+ ", verifyAPIDINPANs=" + verifyAPIDINPANs + ", webHook=" + webHook + "]";
	}

		
}
