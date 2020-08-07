package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class SearchDirectorsDetails {
	
	private String din;
	
	@JsonProperty("director-name")
	private String directorName;
	
	private String city;
	
	private String state;
	
	private String age;
	
	@JsonProperty("last-refresh-time")
	private String lastRefreshTime;

	public String getDin() {
		return din;
	}

	public String getDirectorName() {
		return directorName;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getAge() {
		return age;
	}

	public String getLastRefreshTime() {
		return lastRefreshTime;
	}

	public void setDin(String din) {
		this.din = din;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setLastRefreshTime(String lastRefreshTime) {
		this.lastRefreshTime = lastRefreshTime;
	}
	
	

}
