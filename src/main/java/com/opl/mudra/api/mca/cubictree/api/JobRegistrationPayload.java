package com.opl.mudra.api.mca.cubictree.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @Author : Maaz Shaikh
 * Time :  5:03:01 PM
 **/
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class JobRegistrationPayload {

	@JsonProperty(value="individual")
	private Boolean individual;
	
	@JsonProperty(value="keywords")
	private List<String> keywords;
	
	@JsonProperty(value="cin_number")
	private String cinNumber;
	
	@JsonProperty(value="application_customer_id")
	private String applicationCustomerId;
	
	@JsonProperty(value="file_format")
	private String fileFormat;
	
	@JsonProperty(value="area_locality")
	private String areaLocality;
	
	@JsonProperty(value="show_roc_details")
	private Boolean showRocDetails=Boolean.TRUE;
	
	@JsonProperty(value="filter")
	private Filter filter;
	
	@JsonProperty(value="match_table_individual")
	private MatchTableIndividual matchTableIndividual;
	
	@JsonProperty(value="formats")
	private String formats;

	public Boolean getIndividual() {
		return individual;
	}

	public void setIndividual(Boolean individual) {
		this.individual = individual;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public String getCinNumber() {
		return cinNumber;
	}

	public void setCinNumber(String cinNumber) {
		this.cinNumber = cinNumber;
	}

	public String getApplicationCustomerId() {
		return applicationCustomerId;
	}

	public void setApplicationCustomerId(String applicationCustomerId) {
		this.applicationCustomerId = applicationCustomerId;
	}

	public String getFileFormat() {
		return fileFormat;
	}

	public void setFileFormat(String fileFormat) {
		this.fileFormat = fileFormat;
	}

	public String getAreaLocality() {
		return areaLocality;
	}

	public void setAreaLocality(String areaLocality) {
		this.areaLocality = areaLocality;
	}

	public Boolean getShowRocDetails() {
		return showRocDetails;
	}

	public void setShowRocDetails(Boolean showRocDetails) {
		this.showRocDetails = showRocDetails;
	}

	public Filter getFilter() {
		return filter;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public MatchTableIndividual getMatchTableIndividual() {
		return matchTableIndividual;
	}

	public void setMatchTableIndividual(MatchTableIndividual matchTableIndividual) {
		this.matchTableIndividual = matchTableIndividual;
	}

	public String getFormats() {
		return formats;
	}

	public void setFormats(String formats) {
		this.formats = formats;
	}

	@Override
	public String toString() {
		return "CubicTreeJobRegistrationPayload [individual=" + individual + ", keywords=" + keywords + ", cinNumber="
				+ cinNumber + ", applicationCustomerId=" + applicationCustomerId + ", fileFormat=" + fileFormat
				+ ", areaLocality=" + areaLocality + ", showRocDetails=" + showRocDetails + ", filter=" + filter
				+ ", matchTableIndividual=" + matchTableIndividual + ", formats=" + formats + "]";
	}
	
	
	
}
