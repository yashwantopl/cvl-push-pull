package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class NSDLResponseDetails {

	@JsonProperty("Seq")
	private String seq;
	
	@JsonProperty("Source")
	private String source;
	
	@JsonProperty("PAN")
	private String pan;	
	
	@JsonProperty("PANMatchStatus")
	private String panMatchStatus;
	
	@JsonProperty("Reason")
	private List<ReasonType> reasons;
	
	@JsonProperty("FullName")
	private MatchResponseType fullName;
	
	
	@JsonProperty("LastUpdatedDate")
	private String lastUpdatedDate;


	public String getSeq() {
		return seq;
	}


	public void setSeq(String seq) {
		this.seq = seq;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public String getPan() {
		return pan;
	}


	public void setPan(String pan) {
		this.pan = pan;
	}


	public String getPanMatchStatus() {
		return panMatchStatus;
	}


	public void setPanMatchStatus(String panMatchStatus) {
		this.panMatchStatus = panMatchStatus;
	}


	public List<ReasonType> getReasons() {
		return reasons;
	}


	public void setReasons(List<ReasonType> reasons) {
		this.reasons = reasons;
	}


	public MatchResponseType getFullName() {
		return fullName;
	}


	public void setFullName(MatchResponseType fullName) {
		this.fullName = fullName;
	}


	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}


	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
	
}
