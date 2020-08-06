package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Details {
	
	private Boolean b2bLimitReached;
	
	private String chksum;
	
	@JsonProperty("ret_period")
	private String retPeriod;
	
	private String time;
	
	@JsonProperty("hsn_wise")
	private DetailsHsnWise[] detailsHsnWise;

	@JsonProperty("sec_sum")
	private SecSum[] secSum;

	public Boolean getB2bLimitReached() {
		return b2bLimitReached;
	}

	public void setB2bLimitReached(Boolean b2bLimitReached) {
		this.b2bLimitReached = b2bLimitReached;
	}

	public String getChksum() {
		return chksum;
	}

	public void setChksum(String chksum) {
		this.chksum = chksum;
	}

	public String getRetPeriod() {
		return retPeriod;
	}

	public void setRetPeriod(String retPeriod) {
		this.retPeriod = retPeriod;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public DetailsHsnWise[] getDetailsHsnWise() {
		return detailsHsnWise;
	}

	public void setDetailsHsnWise(DetailsHsnWise[] detailsHsnWise) {
		this.detailsHsnWise = detailsHsnWise;
	}

	public SecSum[] getSecSum() {
		return secSum;
	}

	public void setSecSum(SecSum[] secSum) {
		this.secSum = secSum;
	}

	
	
	
	
	

}
