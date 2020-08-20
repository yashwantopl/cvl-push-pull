package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author rahul.meena
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserGrievanceRequest implements Serializable  {
	private static final long serialVersionUID = 1L;
	
// GRIEVANCE DETAILS	
    private String roportTo;
	private String customerId;
	private String applicationIdd;
	private String requestId;
	private String queryType;
	private String modeOfInteraction;
	private String teamAllocatedTo;
	private String pointOfContact;
	private Date dateOfRaising;
	private String status;
	
	private Long businessTypeId;
	private Long userOrgId;
	
	public Long getUserOrgId() {
		return userOrgId;
	}
	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}
	public Long getBusinessTypeId() {
		return businessTypeId;
	}
	public void setBusinessTypeId(Long businessTypeId) {
		this.businessTypeId = businessTypeId;
	}
	public String getRoportTo() {
		return roportTo;
	}
	public void setRoportTo(String roportTo) {
		this.roportTo = roportTo;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getApplicationId() {
		return applicationIdd;
	}
	public void setApplicationId(String applicationId) {
		this.applicationIdd = applicationId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getModeOfInteraction() {
		return modeOfInteraction;
	}
	public void setModeOfInteraction(String modeOfInteraction) {
		this.modeOfInteraction = modeOfInteraction;
	}
	public String getTeamAllocatedTo() {
		return teamAllocatedTo;
	}
	public void setTeamAllocatedTo(String teamAllocatedTo) {
		this.teamAllocatedTo = teamAllocatedTo;
	}
	public String getPointOfContact() {
		return pointOfContact;
	}
	public void setPointOfContact(String pointOfContact) {
		this.pointOfContact = pointOfContact;
	}
	public Date getDateOfRaising() {
		return dateOfRaising;
	}
	public void setDateOfRaising(Date dateOfRaising) {
		this.dateOfRaising = dateOfRaising;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getApplicationIdd() {
		return applicationIdd;
	}
	public void setApplicationIdd(String applicationIdd) {
		this.applicationIdd = applicationIdd;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserGrievanceRequest [roportTo=" + roportTo + ", customerId=" + customerId + ", applicationIdd="
				+ applicationIdd + ", requestId=" + requestId + ", queryType=" + queryType + ", modeOfInteraction="
				+ modeOfInteraction + ", teamAllocatedTo=" + teamAllocatedTo + ", pointOfContact=" + pointOfContact
				+ ", dateOfRaising=" + dateOfRaising + ", status=" + status + ", businessTypeId=" + businessTypeId
				+ ", userOrgId=" + userOrgId + "]";
	}
}
