package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class SearchDirectorResponse {

	private String status;

	private String message;

	@JsonProperty("search-summary")
	private SearchDirectorsSummary searchSummary;
	
	@JsonProperty("Directors")
	private SearchDirectorsDetails[] directors;

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public SearchDirectorsSummary getSearchSummary() {
		return searchSummary;
	}

	public SearchDirectorsDetails[] getDirectors() {
		return directors;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setSearchSummary(SearchDirectorsSummary searchSummary) {
		this.searchSummary = searchSummary;
	}

	public void setDirectors(SearchDirectorsDetails[] directors) {
		this.directors = directors;
	}
	
	
}
