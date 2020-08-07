package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class McaRequestPara {
	
//	@JsonProperty("apikey")
	@JsonProperty("api_auth_token")
	private String apiKey;
	
	@JsonProperty("search-criteria")
	private SearchCriteria searchCriteria;
	
	@JsonProperty("user_id")
	private String userId;
	
	

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

	public String getApiKey() {
		return apiKey;
	}

	public SearchCriteria getSearchCriteria() {
		return searchCriteria;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public void setSearchCriteria(SearchCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	
	
}
