package com.opl.mudra.api.user.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class EclgsDpd {
	
	private String date;
	private String osAmount;
	private String dpd;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOsAmount() {
		return osAmount;
	}
	public void setOsAmount(String osAmount) {
		this.osAmount = osAmount;
	}
	public String getDpd() {
		return dpd;
	}
	public void setDpd(String dpd) {
		this.dpd = dpd;
	}
	
	

}
