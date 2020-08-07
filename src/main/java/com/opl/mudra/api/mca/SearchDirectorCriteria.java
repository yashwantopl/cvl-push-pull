package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class SearchDirectorCriteria {
	

	
	@JsonProperty("company-ids")
	private String[] companyIds;
	
	@JsonProperty("director-names")
	private String[] directorNames;

	@JsonProperty("director-name-partials")
	private String[] directorNamePartials;
	
	private String[] dins;
	
	@JsonProperty("director-state")
	private String[] directorState;
	
	@JsonProperty("director-city")
	private String[] directorCity;
	
	@JsonProperty("director-start-age")
	private String directorStartAge;
	
	@JsonProperty("director-end-age")
	private String directorEndAge;
	
	@JsonProperty(value = "partials-search-type")
	private String partialsSearchType;
	
	@JsonProperty(value = "offset-start")
	private String offsetStart;
	
	@JsonProperty(value = "offset-end")
	private String offsetEnd;

	public String[] getCompanyIds() {
		return companyIds;
	}

	public String[] getDirectorNames() {
		return directorNames;
	}

	public String[] getDirectorNamePartials() {
		return directorNamePartials;
	}

	public String[] getDins() {
		return dins;
	}

	public String[] getDirectorState() {
		return directorState;
	}

	public String[] getDirectorCity() {
		return directorCity;
	}

	public String getDirectorStartAge() {
		return directorStartAge;
	}

	public String getDirectorEndAge() {
		return directorEndAge;
	}

	public String getPartialsSearchType() {
		return partialsSearchType;
	}

	public String getOffsetStart() {
		return offsetStart;
	}

	public String getOffsetEnd() {
		return offsetEnd;
	}

	public void setCompanyIds(String[] companyIds) {
		this.companyIds = companyIds;
	}

	public void setDirectorNames(String[] directorNames) {
		this.directorNames = directorNames;
	}

	public void setDirectorNamePartials(String[] directorNamePartials) {
		this.directorNamePartials = directorNamePartials;
	}

	public void setDins(String[] dins) {
		this.dins = dins;
	}

	public void setDirectorState(String[] directorState) {
		this.directorState = directorState;
	}

	public void setDirectorCity(String[] directorCity) {
		this.directorCity = directorCity;
	}

	public void setDirectorStartAge(String directorStartAge) {
		this.directorStartAge = directorStartAge;
	}

	public void setDirectorEndAge(String directorEndAge) {
		this.directorEndAge = directorEndAge;
	}

	public void setPartialsSearchType(String partialsSearchType) {
		this.partialsSearchType = partialsSearchType;
	}

	public void setOffsetStart(String offsetStart) {
		this.offsetStart = offsetStart;
	}

	public void setOffsetEnd(String offsetEnd) {
		this.offsetEnd = offsetEnd;
	}
	
	

	}
