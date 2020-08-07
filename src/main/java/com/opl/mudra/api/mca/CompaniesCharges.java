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
public class CompaniesCharges {
	
	private String amount;
	
	@JsonProperty("amount-original-value")
	private String amountOriginalValue;
	
	@JsonProperty("amount-formatted")
	private String amountFormatted;

	
	@JsonProperty("assets-under-charge")
	private String assetsUnderCharge;
	
	@JsonProperty("charge-id")
	private String chargeId;

	@JsonProperty("chargeholder-address")
	private String chargeholderAddress;

	@JsonProperty("chargeholder-name")
	private String chargeholderName;
	
	@JsonProperty("creation-date")
	private String creationDate;
	
	@JsonProperty("creation-date-original-value")
	private String creationDateOriginalValue;
	
	@JsonProperty("modification-date")
	private String modificationDate;
	
	@JsonProperty("satisfaction-date")
	private String satisfactionDate;
	
	@JsonProperty("show-red-line")
	private String showRedLine;
	
	private String year;

	public String getAmount() {
		return amount;
	}

	public String getAmountOriginalValue() {
		return amountOriginalValue;
	}

	public String getAssetsUnderCharge() {
		return assetsUnderCharge;
	}

	public String getChargeId() {
		return chargeId;
	}

	public String getChargeholderAddress() {
		return chargeholderAddress;
	}

	public String getChargeholderName() {
		return chargeholderName;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public String getCreationDateOriginalValue() {
		return creationDateOriginalValue;
	}

	public String getModificationDate() {
		return modificationDate;
	}

	public String getSatisfactionDate() {
		return satisfactionDate;
	}

	public String getShowRedLine() {
		return showRedLine;
	}

	public String getYear() {
		return year;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setAmountOriginalValue(String amountOriginalValue) {
		this.amountOriginalValue = amountOriginalValue;
	}

	public void setAssetsUnderCharge(String assetsUnderCharge) {
		this.assetsUnderCharge = assetsUnderCharge;
	}

	public void setChargeId(String chargeId) {
		this.chargeId = chargeId;
	}

	public void setChargeholderAddress(String chargeholderAddress) {
		this.chargeholderAddress = chargeholderAddress;
	}

	public void setChargeholderName(String chargeholderName) {
		this.chargeholderName = chargeholderName;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public void setCreationDateOriginalValue(String creationDateOriginalValue) {
		this.creationDateOriginalValue = creationDateOriginalValue;
	}

	public void setModificationDate(String modificationDate) {
		this.modificationDate = modificationDate;
	}

	public void setSatisfactionDate(String satisfactionDate) {
		this.satisfactionDate = satisfactionDate;
	}

	public void setShowRedLine(String showRedLine) {
		this.showRedLine = showRedLine;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getAmountFormatted() {
		return amountFormatted;
	}

	public void setAmountFormatted(String amountFormatted) {
		this.amountFormatted = amountFormatted;
	}
	
}
