package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DishonouredChequeDetails {

	@JsonProperty("seq")
	private String seq;

	@JsonProperty("CreditFacilityReference")
	private String creditFacilityReference;

	@JsonProperty("Amount")
	private String amount;

	@JsonProperty("ReasonForDishonour")
	private String reasonForDishonour;

	@JsonProperty("NumberOfTimesDishonoured")
	private String numberOfTimesDishonoured;

	@JsonProperty("DateOfDishonour")
	private String dateOfDishonour;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getCreditFacilityReference() {
		return creditFacilityReference;
	}

	public void setCreditFacilityReference(String creditFacilityReference) {
		this.creditFacilityReference = creditFacilityReference;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getReasonForDishonour() {
		return reasonForDishonour;
	}

	public void setReasonForDishonour(String reasonForDishonour) {
		this.reasonForDishonour = reasonForDishonour;
	}

	public String getNumberOfTimesDishonoured() {
		return numberOfTimesDishonoured;
	}

	public void setNumberOfTimesDishonoured(String numberOfTimesDishonoured) {
		this.numberOfTimesDishonoured = numberOfTimesDishonoured;
	}

	public String getDateOfDishonour() {
		return dateOfDishonour;
	}

	public void setDateOfDishonour(String dateOfDishonour) {
		this.dateOfDishonour = dateOfDishonour;
	}

}
