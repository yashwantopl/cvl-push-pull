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
public class CompaniesHistoryPara {
	
	@JsonProperty("api_auth_token")
	private String apiKey;
	
	@JsonProperty("user_id")
	private String userId;
	
	@JsonProperty("company-ids")
	private String[] companyIds;
	
	@JsonProperty("include-cards")
	private String[] includeTabs;
	
	
	public String[] getIncludeTabs() {
		return includeTabs;
	}

	public void setIncludeTabs(String[] includeTabs) {
		this.includeTabs = includeTabs;
	}

	public String getApiKey() {
		return apiKey;
	}

	public String getUserId() {
		return userId;
	}

	public String[] getCompanyIds() {
		return companyIds;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setCompanyIds(String[] companyIds) {
		this.companyIds = companyIds;
	}
	
	

}
