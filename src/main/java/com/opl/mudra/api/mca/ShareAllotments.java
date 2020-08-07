/**
 * 
 */
package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author nilay
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ShareAllotments {

	@JsonProperty("file-id")
	private String fileId;

	@JsonProperty("date-of-allotment")
	private String dateOfAllotment;

	@JsonProperty("type-of-share")
	private String typeOfShare;

	@JsonProperty("mode-of-payment")
	private String modeOfPayment;

	@JsonProperty("total-no-of-shares-allotted")
	private String totalNoOfSharesAlloted;

	@JsonProperty("nominal-amount-per-share")
	private String nominalAmountPerShare;

	@JsonProperty("total-nominal-amount")
	private String totalNominalAmount;

	@JsonProperty("nominal-amount-paid-per-share")
	private String nominalAmountPaidPerShare;

	@JsonProperty("share-price")
	private String sharePrice;

	@JsonProperty("share-allotment-percent")
	private String shareAllotedPercent;

	@JsonProperty("total-nominal-amount-paid")
	private String totalNominalAmountPaid;

	@JsonProperty("premium-per-share")
	private String primiumPerShare;

	@JsonProperty("total-premium-amount")
	private String totalPremiumAmount;

	@JsonProperty("discount-per-security")
	private String discountPerSecurity;

	@JsonProperty("total-discount")
	private String totalDiscount;

	@JsonProperty("total-amount-per-security")
	private String totalAmountPerSecurity;

	@JsonProperty("total-amount")
	private String totalAmount;

	@JsonProperty("total-number-of-paid-up-equity-shares-as-on-date")
	private String totalNumberOfPaidUpEquitySharesAsOnDate;

	@JsonProperty("total-number-of-paid-up-preference-shares-as-on-date")
	private String totalNumberOfPaidPpPreferenceSharesAsOnDate;

	@JsonProperty("details")
	private Details[] details;

	/**
	 * @return the fileId
	 */
	public String getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	/**
	 * @return the dateOfAllotment
	 */
	public String getDateOfAllotment() {
		return dateOfAllotment;
	}

	/**
	 * @param dateOfAllotment the dateOfAllotment to set
	 */
	public void setDateOfAllotment(String dateOfAllotment) {
		this.dateOfAllotment = dateOfAllotment;
	}

	/**
	 * @return the typeOfShare
	 */
	public String getTypeOfShare() {
		return typeOfShare;
	}

	/**
	 * @param typeOfShare the typeOfShare to set
	 */
	public void setTypeOfShare(String typeOfShare) {
		this.typeOfShare = typeOfShare;
	}

	/**
	 * @return the modeOfPayment
	 */
	public String getModeOfPayment() {
		return modeOfPayment;
	}

	/**
	 * @param modeOfPayment the modeOfPayment to set
	 */
	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	/**
	 * @return the totalNoOfSharesAlloted
	 */
	public String getTotalNoOfSharesAlloted() {
		return totalNoOfSharesAlloted;
	}

	/**
	 * @param totalNoOfSharesAlloted the totalNoOfSharesAlloted to set
	 */
	public void setTotalNoOfSharesAlloted(String totalNoOfSharesAlloted) {
		this.totalNoOfSharesAlloted = totalNoOfSharesAlloted;
	}

	/**
	 * @return the nominalAmountPerShare
	 */
	public String getNominalAmountPerShare() {
		return nominalAmountPerShare;
	}

	/**
	 * @param nominalAmountPerShare the nominalAmountPerShare to set
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
	 * @param totalNominalAmount the totalNominalAmount to set
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
	 * @param nominalAmountPaidPerShare the nominalAmountPaidPerShare to set
	 */
	public void setNominalAmountPaidPerShare(String nominalAmountPaidPerShare) {
		this.nominalAmountPaidPerShare = nominalAmountPaidPerShare;
	}

	/**
	 * @return the sharePrice
	 */
	public String getSharePrice() {
		return sharePrice;
	}

	/**
	 * @param sharePrice the sharePrice to set
	 */
	public void setSharePrice(String sharePrice) {
		this.sharePrice = sharePrice;
	}

	/**
	 * @return the shareAllotedPercent
	 */
	public String getShareAllotedPercent() {
		return shareAllotedPercent;
	}

	/**
	 * @param shareAllotedPercent the shareAllotedPercent to set
	 */
	public void setShareAllotedPercent(String shareAllotedPercent) {
		this.shareAllotedPercent = shareAllotedPercent;
	}

	/**
	 * @return the totalNominalAmountPaid
	 */
	public String getTotalNominalAmountPaid() {
		return totalNominalAmountPaid;
	}

	/**
	 * @param totalNominalAmountPaid the totalNominalAmountPaid to set
	 */
	public void setTotalNominalAmountPaid(String totalNominalAmountPaid) {
		this.totalNominalAmountPaid = totalNominalAmountPaid;
	}

	/**
	 * @return the primiumPerShare
	 */
	public String getPrimiumPerShare() {
		return primiumPerShare;
	}

	/**
	 * @param primiumPerShare the primiumPerShare to set
	 */
	public void setPrimiumPerShare(String primiumPerShare) {
		this.primiumPerShare = primiumPerShare;
	}

	/**
	 * @return the totalPremiumAmount
	 */
	public String getTotalPremiumAmount() {
		return totalPremiumAmount;
	}

	/**
	 * @param totalPremiumAmount the totalPremiumAmount to set
	 */
	public void setTotalPremiumAmount(String totalPremiumAmount) {
		this.totalPremiumAmount = totalPremiumAmount;
	}

	/**
	 * @return the discountPerSecurity
	 */
	public String getDiscountPerSecurity() {
		return discountPerSecurity;
	}

	/**
	 * @param discountPerSecurity the discountPerSecurity to set
	 */
	public void setDiscountPerSecurity(String discountPerSecurity) {
		this.discountPerSecurity = discountPerSecurity;
	}

	/**
	 * @return the totalDiscount
	 */
	public String getTotalDiscount() {
		return totalDiscount;
	}

	/**
	 * @param totalDiscount the totalDiscount to set
	 */
	public void setTotalDiscount(String totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	/**
	 * @return the totalAmountPerSecurity
	 */
	public String getTotalAmountPerSecurity() {
		return totalAmountPerSecurity;
	}

	/**
	 * @param totalAmountPerSecurity the totalAmountPerSecurity to set
	 */
	public void setTotalAmountPerSecurity(String totalAmountPerSecurity) {
		this.totalAmountPerSecurity = totalAmountPerSecurity;
	}

	/**
	 * @return the totalAmount
	 */
	public String getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the totalNumberOfPaidUpEquitySharesAsOnDate
	 */
	public String getTotalNumberOfPaidUpEquitySharesAsOnDate() {
		return totalNumberOfPaidUpEquitySharesAsOnDate;
	}

	/**
	 * @param totalNumberOfPaidUpEquitySharesAsOnDate the totalNumberOfPaidUpEquitySharesAsOnDate to set
	 */
	public void setTotalNumberOfPaidUpEquitySharesAsOnDate(String totalNumberOfPaidUpEquitySharesAsOnDate) {
		this.totalNumberOfPaidUpEquitySharesAsOnDate = totalNumberOfPaidUpEquitySharesAsOnDate;
	}

	/**
	 * @return the totalNumberOfPaidPpPreferenceSharesAsOnDate
	 */
	public String getTotalNumberOfPaidPpPreferenceSharesAsOnDate() {
		return totalNumberOfPaidPpPreferenceSharesAsOnDate;
	}

	/**
	 * @param totalNumberOfPaidPpPreferenceSharesAsOnDate the totalNumberOfPaidPpPreferenceSharesAsOnDate to set
	 */
	public void setTotalNumberOfPaidPpPreferenceSharesAsOnDate(String totalNumberOfPaidPpPreferenceSharesAsOnDate) {
		this.totalNumberOfPaidPpPreferenceSharesAsOnDate = totalNumberOfPaidPpPreferenceSharesAsOnDate;
	}

	/**
	 * @return the details
	 */
	public Details[] getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(Details[] details) {
		this.details = details;
	}
	
	
}
