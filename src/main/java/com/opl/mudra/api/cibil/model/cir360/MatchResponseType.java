package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MatchResponseType {

	@JsonProperty("InquiryValue")
	private String inquiryValue;
	
	@JsonProperty("ResponseValue")
	private String responseValue;
	
	@JsonProperty("MatchPercent")
	private String matchPercent;
	
	@JsonProperty("MatchBand")
	private String matchBand;
	
	@JsonProperty("Reason")
	private List<ReasonType> reason;

	public String getInquiryValue() {
		return inquiryValue;
	}

	public void setInquiryValue(String inquiryValue) {
		this.inquiryValue = inquiryValue;
	}

	public String getResponseValue() {
		return responseValue;
	}

	public void setResponseValue(String responseValue) {
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
		if(reason==null){
			reason = new ArrayList<ReasonType>();
		}
		return reason;
	}

	public void setReason(List<ReasonType> reason) {
		this.reason = reason;
	}



}
