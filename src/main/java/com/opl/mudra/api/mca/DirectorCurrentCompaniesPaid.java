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
public class DirectorCurrentCompaniesPaid {
	@JsonProperty("appointment-current-designation-date")
	private String appointmentCurrentDesignationDate;

	@JsonProperty("appointment-date")
	private String appointmentDate;

	@JsonProperty("appointment-original-date")
	private String appointmentOriginalDate;

	@JsonProperty("as-on-date")
	private String asOnDate;

	@JsonProperty("cessation-date")
	private String cessationDate;

	private String cin;

	@JsonProperty("company-id")
	private String companyId;

	@JsonProperty("company-name")
	private String companyName;

	@JsonProperty("current-status")
	private String currentStatus;

	private String dbcompany;

	private String dbview;

	@JsonProperty("defaulting-status")
	private String defaultingStatus;

	private String designation;

	private String email;

	@JsonProperty("no-of-equity-share-holding")
	private String noOfEquityShareHolding;

	@JsonProperty("paidup-capital")
	private String paidupCapital;

	private String paidview;

	@JsonProperty("personal-detail")
	private String personalDetail;

	private String processview;

	private String profileview;

	@JsonProperty("purchase_status")
	private String purchaseStatus;

	@JsonProperty("share-holding-percent")
	private String shareHoldingPercent;

	@JsonProperty("share-holding-percent-str")
	private String shareHoldingPercentStr;

	@JsonProperty("whether-accused")
	private String whetherAccused;

	/**
	 * @return the appointmentCurrentDesignationDate
	 */
	public String getAppointmentCurrentDesignationDate() {
		return appointmentCurrentDesignationDate;
	}

	/**
	 * @param appointmentCurrentDesignationDate the appointmentCurrentDesignationDate to set
	 */
	public void setAppointmentCurrentDesignationDate(String appointmentCurrentDesignationDate) {
		this.appointmentCurrentDesignationDate = appointmentCurrentDesignationDate;
	}

	/**
	 * @return the appointmentDate
	 */
	public String getAppointmentDate() {
		return appointmentDate;
	}

	/**
	 * @param appointmentDate the appointmentDate to set
	 */
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	/**
	 * @return the appointmentOriginalDate
	 */
	public String getAppointmentOriginalDate() {
		return appointmentOriginalDate;
	}

	/**
	 * @param appointmentOriginalDate the appointmentOriginalDate to set
	 */
	public void setAppointmentOriginalDate(String appointmentOriginalDate) {
		this.appointmentOriginalDate = appointmentOriginalDate;
	}

	/**
	 * @return the asOnDate
	 */
	public String getAsOnDate() {
		return asOnDate;
	}

	/**
	 * @param asOnDate the asOnDate to set
	 */
	public void setAsOnDate(String asOnDate) {
		this.asOnDate = asOnDate;
	}

	/**
	 * @return the cessationDate
	 */
	public String getCessationDate() {
		return cessationDate;
	}

	/**
	 * @param cessationDate the cessationDate to set
	 */
	public void setCessationDate(String cessationDate) {
		this.cessationDate = cessationDate;
	}

	/**
	 * @return the cin
	 */
	public String getCin() {
		return cin;
	}

	/**
	 * @param cin the cin to set
	 */
	public void setCin(String cin) {
		this.cin = cin;
	}

	/**
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the currentStatus
	 */
	public String getCurrentStatus() {
		return currentStatus;
	}

	/**
	 * @param currentStatus the currentStatus to set
	 */
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	/**
	 * @return the dbcompany
	 */
	public String getDbcompany() {
		return dbcompany;
	}

	/**
	 * @param dbcompany the dbcompany to set
	 */
	public void setDbcompany(String dbcompany) {
		this.dbcompany = dbcompany;
	}

	/**
	 * @return the dbview
	 */
	public String getDbview() {
		return dbview;
	}

	/**
	 * @param dbview the dbview to set
	 */
	public void setDbview(String dbview) {
		this.dbview = dbview;
	}

	/**
	 * @return the defaultingStatus
	 */
	public String getDefaultingStatus() {
		return defaultingStatus;
	}

	/**
	 * @param defaultingStatus the defaultingStatus to set
	 */
	public void setDefaultingStatus(String defaultingStatus) {
		this.defaultingStatus = defaultingStatus;
	}

	/**
	 * @return the designation
	 */
	public String getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the noOfEquityShareHolding
	 */
	public String getNoOfEquityShareHolding() {
		return noOfEquityShareHolding;
	}

	/**
	 * @param noOfEquityShareHolding the noOfEquityShareHolding to set
	 */
	public void setNoOfEquityShareHolding(String noOfEquityShareHolding) {
		this.noOfEquityShareHolding = noOfEquityShareHolding;
	}

	/**
	 * @return the paidupCapital
	 */
	public String getPaidupCapital() {
		return paidupCapital;
	}

	/**
	 * @param paidupCapital the paidupCapital to set
	 */
	public void setPaidupCapital(String paidupCapital) {
		this.paidupCapital = paidupCapital;
	}

	/**
	 * @return the paidview
	 */
	public String getPaidview() {
		return paidview;
	}

	/**
	 * @param paidview the paidview to set
	 */
	public void setPaidview(String paidview) {
		this.paidview = paidview;
	}

	/**
	 * @return the personalDetail
	 */
	public String getPersonalDetail() {
		return personalDetail;
	}

	/**
	 * @param personalDetail the personalDetail to set
	 */
	public void setPersonalDetail(String personalDetail) {
		this.personalDetail = personalDetail;
	}

	/**
	 * @return the processview
	 */
	public String getProcessview() {
		return processview;
	}

	/**
	 * @param processview the processview to set
	 */
	public void setProcessview(String processview) {
		this.processview = processview;
	}

	/**
	 * @return the profileview
	 */
	public String getProfileview() {
		return profileview;
	}

	/**
	 * @param profileview the profileview to set
	 */
	public void setProfileview(String profileview) {
		this.profileview = profileview;
	}

	/**
	 * @return the purchaseStatus
	 */
	public String getPurchaseStatus() {
		return purchaseStatus;
	}

	/**
	 * @param purchaseStatus the purchaseStatus to set
	 */
	public void setPurchaseStatus(String purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	/**
	 * @return the shareHoldingPercent
	 */
	public String getShareHoldingPercent() {
		return shareHoldingPercent;
	}

	/**
	 * @param shareHoldingPercent the shareHoldingPercent to set
	 */
	public void setShareHoldingPercent(String shareHoldingPercent) {
		this.shareHoldingPercent = shareHoldingPercent;
	}

	/**
	 * @return the shareHoldingPercentStr
	 */
	public String getShareHoldingPercentStr() {
		return shareHoldingPercentStr;
	}

	/**
	 * @param shareHoldingPercentStr the shareHoldingPercentStr to set
	 */
	public void setShareHoldingPercentStr(String shareHoldingPercentStr) {
		this.shareHoldingPercentStr = shareHoldingPercentStr;
	}

	/**
	 * @return the whetherAccused
	 */
	public String getWhetherAccused() {
		return whetherAccused;
	}

	/**
	 * @param whetherAccused the whetherAccused to set
	 */
	public void setWhetherAccused(String whetherAccused) {
		this.whetherAccused = whetherAccused;
	}
	
	
	
}
