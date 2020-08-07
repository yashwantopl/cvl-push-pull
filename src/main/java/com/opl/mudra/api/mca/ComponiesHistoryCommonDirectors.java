/**
 * 
 */
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
public class ComponiesHistoryCommonDirectors {
	
	private String commonDirectors;
	
	@JsonProperty("company-id")
	private String companyId;
	
	@JsonProperty("as-on-date")
	private String asOnDate;
	
	private String cin;
	
	@JsonProperty("company-name")
	private String companyName;
	
	@JsonProperty("designation")
	private String designation;
	
	@JsonProperty("appointment-date")
	private String appointmentDate;
	
	@JsonProperty("date_cessation")
	private String cessationDate;
	
	@JsonProperty("current_status")
	private String currentStatus;
	
	@JsonProperty("defaulting_status")
	private String defaultingStatus;

	public String getCommonDirectors() {
		return commonDirectors;
	}

	public String getCompanyId() {
		return companyId;
	}

	public String getAsOnDate() {
		return asOnDate;
	}

	public String getCin() {
		return cin;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getDesignation() {
		return designation;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public String getCessationDate() {
		return cessationDate;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public String getDefaultingStatus() {
		return defaultingStatus;
	}

	public void setCommonDirectors(String commonDirectors) {
		this.commonDirectors = commonDirectors;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public void setCessationDate(String cessationDate) {
		this.cessationDate = cessationDate;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public void setDefaultingStatus(String defaultingStatus) {
		this.defaultingStatus = defaultingStatus;
	}

	@Override
	public String toString() {
		return "ComponiesHistoryCommonDirectors [commonDirectors=" + commonDirectors + ", companyId=" + companyId
				+ ", asOnDate=" + asOnDate + ", cin=" + cin + ", companyName=" + companyName + ", designation="
				+ designation + ", appointmentDate=" + appointmentDate + ", cessationDate=" + cessationDate
				+ ", currentStatus=" + currentStatus + ", defaultingStatus=" + defaultingStatus + "]";
	}
}
