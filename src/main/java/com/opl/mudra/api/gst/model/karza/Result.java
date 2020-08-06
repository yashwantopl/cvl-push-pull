package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Result {

	@JsonProperty("gstin")
	private String gstin;
	
	@JsonProperty("current")
	private Current current;

	@JsonProperty("previous")
	private Previous previous;

	@JsonProperty("profile")
	private Profile profile;


	/**
	 * @return the gstin
	 */
	public String getGstin() {
		return gstin;
	}


	/**
	 * @param gstin the gstin to set
	 */
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}


	/**
	 * @return the current
	 */
	public Current getCurrent() {
		return current;
	}


	/**
	 * @param current the current to set
	 */
	public void setCurrent(Current current) {
		this.current = current;
	}


	/**
	 * @return the previous
	 */
	public Previous getPrevious() {
		return previous;
	}


	/**
	 * @param previous the previous to set
	 */
	public void setPrevious(Previous previous) {
		this.previous = previous;
	}


	/**
	 * @return the profile
	 */
	public Profile getProfile() {
		return profile;
	}


	/**
	 * @param profile the profile to set
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	
	
	 
}
