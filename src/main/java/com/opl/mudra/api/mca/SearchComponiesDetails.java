package com.opl.mudra.api.mca;

import java.io.Serializable;

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
public class SearchComponiesDetails implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("company-id")
	private String companyId;
	
	@JsonProperty("company-name")
	private String companyName;

	private String cin;
	
	private String city;
	
	private String state; 
	
	@JsonProperty("company-status")
	private String companyStatus;
	
	@JsonProperty("last-refresh-time")
	private String lastRefreshTime;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCompanyId() {
		return companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getCin() {
		return cin;
	}

	public String getState() {
		return state;
	}

	public String getCompanyStatus() {
		return companyStatus;
	}

	public String getLastRefreshTime() {
		return lastRefreshTime;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
	}

	public void setLastRefreshTime(String lastRefreshTime) {
		this.lastRefreshTime = lastRefreshTime;
	}

	@Override
	public String toString() {
		return "SearchComponiesDetails [companyId=" + companyId + ", companyName=" + companyName + ", cin=" + cin
				+ ", city=" + city + ", state=" + state + ", companyStatus=" + companyStatus + ", lastRefreshTime="
				+ lastRefreshTime + "]";
	}
	
	
}
