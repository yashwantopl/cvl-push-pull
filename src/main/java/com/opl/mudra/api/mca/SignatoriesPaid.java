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
public class SignatoriesPaid {

	@JsonProperty("din")
	private String din;

	@JsonProperty("director-name")
	private String direcorName;

	@JsonProperty("residential-address")
	private String residentialAddress;

	@JsonProperty("residential-address-map")
	private String residentialAddressMap;

	@JsonProperty("designation")
	private String designation;

	@JsonProperty("appointment-date")
	private String appointmentDate;

	@JsonProperty("cessation-date")
	private String cessationDate;

	@JsonProperty("whether-accused")
	private String whetherAccused;

	@JsonProperty("no-of-equity-share-holding")
	private String noOfQuityShareHolding;

	@JsonProperty("share-holding-percent")
	private String shareHoldingPercentage;

	@JsonProperty("no-of-equity-share-holding-str")
	private String noOfQuityShareHoldingStr;

	@JsonProperty("share-holding-percent-str")
	private String shareHoldingPercentStr;

	@JsonProperty("date-of-birth")
	private String dateOfBirth;

	@JsonProperty("director-age")
	private String directorAge;

	@JsonProperty("director-type")
	private String directorType;

	@JsonProperty("as-on-date")
	private String asOnDate;

	@JsonProperty("remarks")
	private String remarks;

	@JsonProperty("email")
	private String email;

	@JsonProperty("dsc-registered")
	private String dscRegistered;

	@JsonProperty("dsc-expiry-date")
	private String dscExpiryDate;

	@JsonProperty("surrendered-din")
	private String surrenderedDin;

	@JsonProperty("director-contribution-percent")
	private String directorContributionPercent;

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
	 * @return the direcorName
	 */
	public String getDirecorName() {
		return direcorName;
	}

	/**
	 * @param direcorName
	 *            the direcorName to set
	 */
	public void setDirecorName(String direcorName) {
		this.direcorName = direcorName;
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
	 * @return the residentialAddressMap
	 */
	public String getResidentialAddressMap() {
		return residentialAddressMap;
	}

	/**
	 * @param residentialAddressMap
	 *            the residentialAddressMap to set
	 */
	public void setResidentialAddressMap(String residentialAddressMap) {
		this.residentialAddressMap = residentialAddressMap;
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
	 * @return the appointmentDate
	 */
	public String getAppointmentDate() {
		return appointmentDate;
	}

	/**
	 * @param appointmentDate
	 *            the appointmentDate to set
	 */
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
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
	 * @return the whetherAccused
	 */
	public String getWhetherAccused() {
		return whetherAccused;
	}

	/**
	 * @param whetherAccused
	 *            the whetherAccused to set
	 */
	public void setWhetherAccused(String whetherAccused) {
		this.whetherAccused = whetherAccused;
	}

	/**
	 * @return the noOfQuityShareHolding
	 */
	public String getNoOfQuityShareHolding() {
		return noOfQuityShareHolding;
	}

	/**
	 * @param noOfQuityShareHolding
	 *            the noOfQuityShareHolding to set
	 */
	public void setNoOfQuityShareHolding(String noOfQuityShareHolding) {
		this.noOfQuityShareHolding = noOfQuityShareHolding;
	}

	/**
	 * @return the shareHoldingPercentage
	 */
	public String getShareHoldingPercentage() {
		return shareHoldingPercentage;
	}

	/**
	 * @param shareHoldingPercentage
	 *            the shareHoldingPercentage to set
	 */
	public void setShareHoldingPercentage(String shareHoldingPercentage) {
		this.shareHoldingPercentage = shareHoldingPercentage;
	}

	/**
	 * @return the noOfQuityShareHoldingStr
	 */
	public String getNoOfQuityShareHoldingStr() {
		return noOfQuityShareHoldingStr;
	}

	/**
	 * @param noOfQuityShareHoldingStr
	 *            the noOfQuityShareHoldingStr to set
	 */
	public void setNoOfQuityShareHoldingStr(String noOfQuityShareHoldingStr) {
		this.noOfQuityShareHoldingStr = noOfQuityShareHoldingStr;
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
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @return the directorAge
	 */
	public String getDirectorAge() {
		return directorAge;
	}

	/**
	 * @param directorAge
	 *            the directorAge to set
	 */
	public void setDirectorAge(String directorAge) {
		this.directorAge = directorAge;
	}

	/**
	 * @return the directorType
	 */
	public String getDirectorType() {
		return directorType;
	}

	/**
	 * @param directorType
	 *            the directorType to set
	 */
	public void setDirectorType(String directorType) {
		this.directorType = directorType;
	}

	/**
	 * @return the asOnDate
	 */
	public String getAsOnDate() {
		return asOnDate;
	}

	/**
	 * @param asOnDate
	 *            the asOnDate to set
	 */
	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
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
	 * @return the dscRegistered
	 */
	public String getDscRegistered() {
		return dscRegistered;
	}

	/**
	 * @param dscRegistered
	 *            the dscRegistered to set
	 */
	public void setDscRegistered(String dscRegistered) {
		this.dscRegistered = dscRegistered;
	}

	/**
	 * @return the dscExpiryDate
	 */
	public String getDscExpiryDate() {
		return dscExpiryDate;
	}

	/**
	 * @param dscExpiryDate
	 *            the dscExpiryDate to set
	 */
	public void setDscExpiryDate(String dscExpiryDate) {
		this.dscExpiryDate = dscExpiryDate;
	}

	/**
	 * @return the surrenderedDin
	 */
	public String getSurrenderedDin() {
		return surrenderedDin;
	}

	/**
	 * @param surrenderedDin
	 *            the surrenderedDin to set
	 */
	public void setSurrenderedDin(String surrenderedDin) {
		this.surrenderedDin = surrenderedDin;
	}

	/**
	 * @return the directorContributionPercent
	 */
	public String getDirectorContributionPercent() {
		return directorContributionPercent;
	}

	/**
	 * @param directorContributionPercent
	 *            the directorContributionPercent to set
	 */
	public void setDirectorContributionPercent(String directorContributionPercent) {
		this.directorContributionPercent = directorContributionPercent;
	}

}
