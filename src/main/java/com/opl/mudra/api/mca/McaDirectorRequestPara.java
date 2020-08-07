package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class McaDirectorRequestPara {
	

	@JsonProperty("api_auth_token")
	private String apiKey;
	
	@JsonProperty("search-criteria")
	private SearchDirectorCriteria searchCriteria;

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

	public SearchDirectorCriteria getSearchCriteria() {
		return searchCriteria;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public void setSearchCriteria(SearchDirectorCriteria searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	
	


}
