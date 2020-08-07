package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EnquiryType {

	private String seq;

	@JsonProperty("Institution")
	private String institution;

	@JsonProperty("Date")
	private String date;

	@JsonProperty("Time")
	private String time;

	@JsonProperty("RequestPurpose")
	private String requestPurpose;

	@JsonProperty("Amount")
	private String amount;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getRequestPurpose() {
		return requestPurpose;
	}

	public void setRequestPurpose(String requestPurpose) {
		this.requestPurpose = requestPurpose;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
