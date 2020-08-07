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
public class DirectorsPastCompanies {
	
	@JsonProperty("company-id")
	private String companyId;
	
	@JsonProperty("company-name")
	private String companyName;
	
	private String cin;
	
	private String designation;
	
	@JsonProperty("appointment-current-designation-date")
	private String appointmentCurrentDesignationDate;
	
	@JsonProperty("appointment_original_date")
	private String appointmentOriginalDate;
	
	@JsonProperty("date_cessation")
	private String dateCessation;
	
	@JsonProperty("current_status")
	private String currentStatus;
	
	@JsonProperty("defaulting_status")
	private String defaultingStatus;
	
	private String subscription;
	
	@JsonProperty("is_showdata_in")
	private String isShowDataIn;
	
	@JsonProperty("appointment-current-designation-date-original-value")
	private String appointmentCurrentDesignationDateOriginalValue;
	
	@JsonProperty("appointment_original_date-original-value")
	private String appointmentOriginalDateOriginalValue;

	public String getCompanyId() {
		return companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getCin() {
		return cin;
	}

	public String getDesignation() {
		return designation;
	}

	public String getAppointmentCurrentDesignationDate() {
		return appointmentCurrentDesignationDate;
	}

	public String getAppointmentOriginalDate() {
		return appointmentOriginalDate;
	}

	public String getDateCessation() {
		return dateCessation;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public String getDefaultingStatus() {
		return defaultingStatus;
	}

	public String getSubscription() {
		return subscription;
	}

	public String getIsShowDataIn() {
		return isShowDataIn;
	}

	public String getAppointmentCurrentDesignationDateOriginalValue() {
		return appointmentCurrentDesignationDateOriginalValue;
	}

	public String getAppointmentOriginalDateOriginalValue() {
		return appointmentOriginalDateOriginalValue;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setAppointmentCurrentDesignationDate(String appointmentCurrentDesignationDate) {
		this.appointmentCurrentDesignationDate = appointmentCurrentDesignationDate;
	}

	public void setAppointmentOriginalDate(String appointmentOriginalDate) {
		this.appointmentOriginalDate = appointmentOriginalDate;
	}

	public void setDateCessation(String dateCessation) {
		this.dateCessation = dateCessation;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public void setDefaultingStatus(String defaultingStatus) {
		this.defaultingStatus = defaultingStatus;
	}

	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}

	public void setIsShowDataIn(String isShowDataIn) {
		this.isShowDataIn = isShowDataIn;
	}

	public void setAppointmentCurrentDesignationDateOriginalValue(String appointmentCurrentDesignationDateOriginalValue) {
		this.appointmentCurrentDesignationDateOriginalValue = appointmentCurrentDesignationDateOriginalValue;
	}

	public void setAppointmentOriginalDateOriginalValue(String appointmentOriginalDateOriginalValue) {
		this.appointmentOriginalDateOriginalValue = appointmentOriginalDateOriginalValue;
	}
	
	
	

}
