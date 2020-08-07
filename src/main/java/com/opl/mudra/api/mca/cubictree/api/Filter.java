package com.opl.mudra.api.mca.cubictree.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author : Maaz Shaikh
 * Time :  5:03:01 PM
 **/
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Filter {

	@JsonProperty(value="district")
	private String district;
	
	@JsonProperty(value="state")
	private String state;
	
	@JsonProperty(value="city")
	private String city;
	
	@JsonProperty(value="from_date")
	private String fromDate;
	
	@JsonProperty(value="to_date")
	private String toDate;

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "filter [district=" + district + ", state=" + state + ", city=" + city + ", fromDate=" + fromDate
				+ ", toDate=" + toDate + "]";
	}
	
	
}
