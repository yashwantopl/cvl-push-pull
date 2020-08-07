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
public class LoginData {
	
	@JsonProperty("api_auth_token")
	private String apiAuthToken;
	
	@JsonProperty("email_id")
	private String emailId;
	
	@JsonProperty("first_name")
	private String firstName;
	
	@JsonProperty("unread_notification_count")
	private String unreadNotificationCount;
	
	@JsonProperty("user_id")
	private String userId;
	
	@JsonProperty("available_tabs")
	private String[] availableTabs;

	public String getApiAuthToken() {
		return apiAuthToken;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getUnreadNotificationCount() {
		return unreadNotificationCount;
	}

	public String getUserId() {
		return userId;
	}

	public String[] getAvailableTabs() {
		return availableTabs;
	}

	public void setApiAuthToken(String apiAuthToken) {
		this.apiAuthToken = apiAuthToken;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setUnreadNotificationCount(String unreadNotificationCount) {
		this.unreadNotificationCount = unreadNotificationCount;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setAvailableTabs(String[] availableTabs) {
		this.availableTabs = availableTabs;
	}


	

}
