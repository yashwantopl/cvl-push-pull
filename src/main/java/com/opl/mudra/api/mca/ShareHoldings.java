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
public class ShareHoldings {

	@JsonProperty("File-id")
	private String fileId;

	@JsonProperty("financial-year-from-date")
	private String financialYearFromDate;

	@JsonProperty("financial-year-to-date")
	private String financialYearToDate;

	@JsonProperty("type-of-share")
	private String typeOfShare;

	@JsonProperty("class-name")
	private String className;

	@JsonProperty("total-no-of-shares-allotted")
	private String totalNoOfSharesAlloted;

	@JsonProperty("total-no-of-shares")
	private String totalNoOfShares;

	@JsonProperty("nominal-amount-per-share")
	private String nominalAmountPerShare;

	@JsonProperty("total-nominal-amount")
	private String totalNominalAmount;

	@JsonProperty("nominal-amount-paid-per-share")
	private String nominalAmountPaidPerShare;

	@JsonProperty("total-nominal-amount-paid")
	private String totalNominalAmountPaid;

	@JsonProperty("details")
	private Details[] details;

	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * @param fileId
	 *            the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	/**
	 * @return the financialYearFromDate
	 */
	public String getFinancialYearFromDate() {
		return financialYearFromDate;
	}

	/**
	 * @param financialYearFromDate
	 *            the financialYearFromDate to set
	 */
	public void setFinancialYearFromDate(String financialYearFromDate) {
		this.financialYearFromDate = financialYearFromDate;
	}

	/**
	 * @return the financialYearToDate
	 */
	public String getFinancialYearToDate() {
		return financialYearToDate;
	}

	/**
	 * @param financialYearToDate
	 *            the financialYearToDate to set
	 */
	public void setFinancialYearToDate(String financialYearToDate) {
		this.financialYearToDate = financialYearToDate;
	}

	/**
	 * @return the typeOfShare
	 */
	public String getTypeOfShare() {
		return typeOfShare;
	}

	/**
	 * @param typeOfShare
	 *            the typeOfShare to set
	 */
	public void setTypeOfShare(String typeOfShare) {
		this.typeOfShare = typeOfShare;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className
	 *            the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}

	/**
	 * @return the totalNoOfSharesAlloted
	 */
	public String getTotalNoOfSharesAlloted() {
		return totalNoOfSharesAlloted;
	}

	/**
	 * @param totalNoOfSharesAlloted
	 *            the totalNoOfSharesAlloted to set
	 */
	public void setTotalNoOfSharesAlloted(String totalNoOfSharesAlloted) {
		this.totalNoOfSharesAlloted = totalNoOfSharesAlloted;
	}

	/**
	 * @return the totalNoOfShares
	 */
	public String getTotalNoOfShares() {
		return totalNoOfShares;
	}

	/**
	 * @param totalNoOfShares
	 *            the totalNoOfShares to set
	 */
	public void setTotalNoOfShares(String totalNoOfShares) {
		this.totalNoOfShares = totalNoOfShares;
	}

	/**
	 * @return the nominalAmountPerShare
	 */
	public String getNominalAmountPerShare() {
		return nominalAmountPerShare;
	}

	/**
	 * @param nominalAmountPerShare
	 *            the nominalAmountPerShare to set
	 */
	public void setNominalAmountPerShare(String nominalAmountPerShare) {
		this.nominalAmountPerShare = nominalAmountPerShare;
	}

	/**
	 * @return the totalNominalAmount
	 */
	public String getTotalNominalAmount() {
		return totalNominalAmount;
	}

	/**
	 * @param totalNominalAmount
	 *            the totalNominalAmount to set
	 */
	public void setTotalNominalAmount(String totalNominalAmount) {
		this.totalNominalAmount = totalNominalAmount;
	}

	/**
	 * @return the nominalAmountPaidPerShare
	 */
	public String getNominalAmountPaidPerShare() {
		return nominalAmountPaidPerShare;
	}

	/**
	 * @param nominalAmountPaidPerShare
	 *            the nominalAmountPaidPerShare to set
	 */
	public void setNominalAmountPaidPerShare(String nominalAmountPaidPerShare) {
		this.nominalAmountPaidPerShare = nominalAmountPaidPerShare;
	}

	/**
	 * @return the totalNominalAmountPaid
	 */
	public String getTotalNominalAmountPaid() {
		return totalNominalAmountPaid;
	}

	/**
	 * @param totalNominalAmountPaid
	 *            the totalNominalAmountPaid to set
	 */
	public void setTotalNominalAmountPaid(String totalNominalAmountPaid) {
		this.totalNominalAmountPaid = totalNominalAmountPaid;
	}

	/**
	 * @return the details
	 */
	public Details[] getDetails() {
		return details;
	}

	/**
	 * @param details
	 *            the details to set
	 */
	public void setDetails(Details[] details) {
		this.details = details;
	}

}
