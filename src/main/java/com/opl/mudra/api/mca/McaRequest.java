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
public class McaRequest extends CommonRequest{
	
	private LoginRequest loginRequest;
	
	private SearchCompaniesRequest searchCompanies;
	
	private CompaniesHistoryRequest companiesHistory;
	
	private SearchDirectorsRequest searchDirectors;
	
	private DirectorsDetailRequest directorDetails;
	
	private PlaceOrderRequest placeOrder;
	
//	@JsonProperty("status")
//	private PlacedOrderNotification orderNotification;
	
	@JsonProperty("webhook_token")
	private String webhookToken;
	
	private Long applicationId;
	
	private String applicationType;
	
	@JsonProperty("reference-id")
	private String referenceId;
	
	private String requestKey;
	
//	private String status;
	
	
	
	
	
	@JsonProperty("webhook_key")
	private String webhookKey;
	
	private String status;
	
	private String message;
	
	
	private Boolean isLoansSkip =false;
	
	
	

	public Boolean getIsLoansSkip() {
		return isLoansSkip;
	}

	public void setIsLoansSkip(Boolean isLoansSkip) {
		this.isLoansSkip = isLoansSkip;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the webhookKey
	 */
	public String getWebhookKey() {
		return webhookKey;
	}

	/**
	 * @param webhookKey the webhookKey to set
	 */
	public void setWebhookKey(String webhookKey) {
		this.webhookKey = webhookKey;
	}

//	/**
//	 * @return the status
//	 */
//	public String getStatus() {
//		return status;
//	}
//
//	/**
//	 * @param status the status to set
//	 */
//	public void setStatus(String status) {
//		this.status = status;
//	}
	
	
	

	public String getWebhookToken() {
		return webhookToken;
	}

	

	public void setWebhookToken(String webhookToken) {
		this.webhookToken = webhookToken;
	}

	public String getRequestKey() {
		return requestKey;
	}

	public void setRequestKey(String requestKey) {
		this.requestKey = requestKey;
	}

/*	public PlacedOrderNotification getOrderNotification() {
		return orderNotification;
	}

	public void setOrderNotification(PlacedOrderNotification orderNotification) {
		this.orderNotification = orderNotification;
	}*/


	public PlaceOrderRequest getPlaceOrder() {
		return placeOrder;
	}

	public void setPlaceOrder(PlaceOrderRequest placeOrder) {
		this.placeOrder = placeOrder;
	}


	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	public Long getApplicationId() {
		return applicationId;
	}


	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}


	public DirectorsDetailRequest getDirectorDetails() {
		return directorDetails;
	}

	public void setDirectorDetails(DirectorsDetailRequest directorDetails) {
		this.directorDetails = directorDetails;
	}

	public SearchDirectorsRequest getSearchDirectors() {
		return searchDirectors;
	}

	public void setSearchDirectors(SearchDirectorsRequest searchDirectors) {
		this.searchDirectors = searchDirectors;
	}

	public CompaniesHistoryRequest getCompaniesHistory() {
		return companiesHistory;
	}

	public void setCompaniesHistory(CompaniesHistoryRequest companiesHistory) {
		this.companiesHistory = companiesHistory;
	}

	public LoginRequest getLoginRequest() {
		return loginRequest;
	}

	public void setLoginRequest(LoginRequest loginRequest) {
		this.loginRequest = loginRequest;
	}

	public SearchCompaniesRequest getSearchCompanies() {
		return searchCompanies;
	}

	public void setSearchCompanies(SearchCompaniesRequest searchCompanies) {
		this.searchCompanies = searchCompanies;
	}

	/**
	 * @return the referenceId
	 */
	public String getReferenceId() {
		return referenceId;
	}

	/**
	 * @param referenceId the referenceId to set
	 */
	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "McaRequest [loginRequest=" + loginRequest + ", searchCompanies=" + searchCompanies
				+ ", companiesHistory=" + companiesHistory + ", searchDirectors=" + searchDirectors
				+ ", directorDetails=" + directorDetails + ", placeOrder=" + placeOrder + ", webhookToken="
				+ webhookToken + ", applicationId=" + applicationId + ", applicationType=" + applicationType
				+ ", referenceId=" + referenceId + ", requestKey=" + requestKey + ", webhookKey=" + webhookKey
				+ ", status=" + status + ", message=" + message + "]";
	}

	
	
	


}
