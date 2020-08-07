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
public class CompaniesSignatories {

	@JsonProperty("appointment-date")
	private String appointmentDate;
	
	@JsonProperty("appointment-date-original-value")
	private String appointmentDateOriginalValue;
	
	private String designation;
	
	private String din;
	
	@JsonProperty("director-name")
	private String directorName;
	
	@JsonProperty("residential-address")
	private String residentialAddress;

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public String getAppointmentDateOriginalValue() {
		return appointmentDateOriginalValue;
	}

	public String getDesignation() {
		return designation;
	}

	public String getDin() {
		return din;
	}

	public String getDirectorName() {
		return directorName;
	}

	public String getResidentialAddress() {
		return residentialAddress;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public void setAppointmentDateOriginalValue(String appointmentDateOriginalValue) {
		this.appointmentDateOriginalValue = appointmentDateOriginalValue;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public void setDin(String din) {
		this.din = din;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}
	
	
	
}
