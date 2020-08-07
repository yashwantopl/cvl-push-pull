/**
 * 
 */
package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sanket.devare
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class CheckFinancialDataPara {
	
	@JsonProperty("api_auth_token")
	private String apiAuthToken;
	
	@JsonProperty("user_id")
	private String userId;
	
	@JsonProperty("reference_ids")
	private String[] referenceIds;
	
	@JsonProperty("source_system")
	private String sourceSystem;

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
	 * @return the referenceIds
	 */
	public String[] getReferenceIds() {
		return referenceIds;
	}

	/**
	 * @param referenceIds the referenceIds to set
	 */
	public void setReferenceIds(String[] referenceIds) {
		this.referenceIds = referenceIds;
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
	
	
	

}
