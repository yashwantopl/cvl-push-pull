package com.opl.mudra.api.mca;

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
public class SearchCriteria {
	
	@JsonProperty("company-ids")
	private String[] companyIds;
	
	@JsonProperty("company-names")
	private String[] companyNames= {};

	@JsonProperty("company-name-partials")
	private String[] companyNamePartials;
	
	private String[] cins;
	
	@JsonProperty("cin-partials")
	private String[] cinPartials;
	
	@JsonProperty(value = "partials-search-type")
	private String partialsSearchType;
	
	@JsonProperty(value = "offset-start")
	private String offsetStart;
	
	@JsonProperty(value = "offset-end")
	private String offsetEnd;

	public String[] getCompanyIds() {
		return companyIds;
	}

	public String[] getCompanyNames() {
		return companyNames;
	}

	public String[] getCompanyNamePartials() {
		return companyNamePartials;
	}

	public String[] getCins() {
		return cins;
	}

	public String[] getCinPartials() {
		return cinPartials;
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

	public void setCompanyNames(String[] companyNames) {
		this.companyNames = companyNames;
	}

	public void setCompanyNamePartials(String[] companyNamePartials) {
		this.companyNamePartials = companyNamePartials;
	}

	public void setCins(String[] cins) {
		this.cins = cins;
	}

	public void setCinPartials(String[] cinPartials) {
		this.cinPartials = cinPartials;
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
