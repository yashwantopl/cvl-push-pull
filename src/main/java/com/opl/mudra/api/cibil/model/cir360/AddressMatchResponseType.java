package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddressMatchResponseType {

	private String seq;
	
	@JsonProperty("Type")
	private List<String> type;
	
	@JsonProperty("InquiryValue")
	private BasicAddressType inquiryValue;
	
	@JsonProperty("ResponseValue")
	private EnhancedAddressType responseValue;
	
	@JsonProperty("MatchPercent")
	private String matchPercent;
	
	@JsonProperty("MatchBand")
	private String matchBand;
	
	@JsonProperty("Reason")
	private List<ReasonType> reason;

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}

	public BasicAddressType getInquiryValue() {
		return inquiryValue;
	}

	public void setInquiryValue(BasicAddressType inquiryValue) {
		this.inquiryValue = inquiryValue;
	}

	public EnhancedAddressType getResponseValue() {
		return responseValue;
	}

	public void setResponseValue(EnhancedAddressType responseValue) {
		this.responseValue = responseValue;
	}

	public String getMatchPercent() {
		return matchPercent;
	}

	public void setMatchPercent(String matchPercent) {
		this.matchPercent = matchPercent;
	}

	public String getMatchBand() {
		return matchBand;
	}

	public void setMatchBand(String matchBand) {
		this.matchBand = matchBand;
	}

	public List<ReasonType> getReason() {
		return reason;
	}

	public void setReason(List<ReasonType> reason) {
		this.reason = reason;
	}
	
	
}



