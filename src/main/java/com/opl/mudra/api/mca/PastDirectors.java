/**
 * 
 */
package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author nilay
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class PastDirectors {

	@JsonProperty("din")
	private String din;

	@JsonProperty("director-name")
	private String directorName;

	@JsonProperty("designation")
	private String designation;

	@JsonProperty("appointment-current-designation-date")
	private String appointmentCurrentDesignationDate;

	@JsonProperty("appointment-original-date")
	private String appointmentOriginalDate;

	@JsonProperty("cessation-date")
	private String cessationDate;

	@JsonProperty("defaulting-status")
	private String DefaultingStatus;

	@JsonProperty("email")
	private String email;

	@JsonProperty("share-holding-percent-str")
	private String shareHoldingPercentStr;

	@JsonProperty("residential-address")
	private String residentialAddress;

	@JsonProperty("remarks")
	private String remarks;

	/**
	 * @return the din
	 */
	public String getDin() {
		return din;
	}

	/**
	 * @param din
	 *            the din to set
	 */
	public void setDin(String din) {
		this.din = din;
	}

	/**
	 * @return the directorName
	 */
	public String getDirectorName() {
		return directorName;
	}

	/**
	 * @param directorName
	 *            the directorName to set
	 */
	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation
	 *            the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the appointmentCurrentDesignationDate
	 */
	public String getAppointmentCurrentDesignationDate() {
		return appointmentCurrentDesignationDate;
	}

	/**
	 * @param appointmentCurrentDesignationDate
	 *            the appointmentCurrentDesignationDate to set
	 */
	public void setAppointmentCurrentDesignationDate(String appointmentCurrentDesignationDate) {
		this.appointmentCurrentDesignationDate = appointmentCurrentDesignationDate;
	}

	/**
	 * @return the appointmentOriginalDate
	 */
	public String getAppointmentOriginalDate() {
		return appointmentOriginalDate;
	}

	/**
	 * @param appointmentOriginalDate
	 *            the appointmentOriginalDate to set
	 */
	public void setAppointmentOriginalDate(String appointmentOriginalDate) {
		this.appointmentOriginalDate = appointmentOriginalDate;
	}

	/**
	 * @return the cessationDate
	 */
	public String getCessationDate() {
		return cessationDate;
	}

	/**
	 * @param cessationDate
	 *            the cessationDate to set
	 */
	public void setCessationDate(String cessationDate) {
		this.cessationDate = cessationDate;
	}

	/**
	 * @return the defaultingStatus
	 */
	public String getDefaultingStatus() {
		return DefaultingStatus;
	}

	/**
	 * @param defaultingStatus
	 *            the defaultingStatus to set
	 */
	public void setDefaultingStatus(String defaultingStatus) {
		DefaultingStatus = defaultingStatus;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the shareHoldingPercentStr
	 */
	public String getShareHoldingPercentStr() {
		return shareHoldingPercentStr;
	}

	/**
	 * @param shareHoldingPercentStr
	 *            the shareHoldingPercentStr to set
	 */
	public void setShareHoldingPercentStr(String shareHoldingPercentStr) {
		this.shareHoldingPercentStr = shareHoldingPercentStr;
	}

	/**
	 * @return the residentialAddress
	 */
	public String getResidentialAddress() {
		return residentialAddress;
	}

	/**
	 * @param residentialAddress
	 *            the residentialAddress to set
	 */
	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks
	 *            the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
