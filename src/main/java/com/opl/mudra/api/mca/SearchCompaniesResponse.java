package com.opl.mudra.api.mca;

import java.io.Serializable;
import java.util.Arrays;

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
public class SearchCompaniesResponse implements Serializable{
	
//	private SearchCompaniesStatus status;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String status;
	
	private String message;
	
	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@JsonProperty("search-summary")
	private SearchCompaniesSummary searchSummary;
	
	private SearchComponiesDetails[] companies;
	

	

	public SearchComponiesDetails[] getCompanies() {
		return companies;
	}

	public void setCompanies(SearchComponiesDetails[] companies) {
		this.companies = companies;
	}

//	public SearchCompaniesStatus getStatus() {
//		return status;
//	}

	public SearchCompaniesSummary getSearchSummary() {
		return searchSummary;
	}

//	public void setStatus(SearchCompaniesStatus status) {
//		this.status = status;
//	}

	public void setSearchSummary(SearchCompaniesSummary searchSummary) {
		this.searchSummary = searchSummary;
	}

	@Override
	public String toString() {
		return "SearchCompaniesResponse [status=" + status + ", message=" + message + ", searchSummary=" + searchSummary
				+ ", companies=" + Arrays.toString(companies) + "]";
	}
	
	

}
