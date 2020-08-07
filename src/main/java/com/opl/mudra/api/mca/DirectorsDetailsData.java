package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class DirectorsDetailsData {
	
	private String din;
	
	@JsonProperty("last-refresh-time")
	private String lastRefreshTime;
	
	@JsonProperty("director-profile")
	private DirectorProfile directorProfile;
	
	@JsonProperty("director-current-companies")
	private DirectorCurrentCompanies[] directorCurrentCompanies;
	
	@JsonProperty("director-past-companies")
	private DirectorsPastCompanies[] directorsPastCompanies;
	
	@JsonProperty("director-past-companies-original")
	private DirectorsPastCompaniesOriginal[] directorsPastCompaniesOriginal;

	@JsonProperty("director-current-companies-paid")
	private DirectorCurrentCompaniesPaid[] directorCurrentCompaniesPaid;

	
	
	/**
	 * @return the directorCurrentCompaniesPaid
	 */
	public DirectorCurrentCompaniesPaid[] getDirectorCurrentCompaniesPaid() {
		return directorCurrentCompaniesPaid;
	}

	/**
	 * @param directorCurrentCompaniesPaid the directorCurrentCompaniesPaid to set
	 */
	public void setDirectorCurrentCompaniesPaid(DirectorCurrentCompaniesPaid[] directorCurrentCompaniesPaid) {
		this.directorCurrentCompaniesPaid = directorCurrentCompaniesPaid;
	}

	public String getDin() {
		return din;
	}

	public String getLastRefreshTime() {
		return lastRefreshTime;
	}


	public void setDin(String din) {
		this.din = din;
	}

	public void setLastRefreshTime(String lastRefreshTime) {
		this.lastRefreshTime = lastRefreshTime;
	}

	public DirectorProfile getDirectorProfile() {
		return directorProfile;
	}

	public DirectorCurrentCompanies[] getDirectorCurrentCompanies() {
		return directorCurrentCompanies;
	}

	public DirectorsPastCompanies[] getDirectorsPastCompanies() {
		return directorsPastCompanies;
	}

	public DirectorsPastCompaniesOriginal[] getDirectorsPastCompaniesOriginal() {
		return directorsPastCompaniesOriginal;
	}

	public void setDirectorProfile(DirectorProfile directorProfile) {
		this.directorProfile = directorProfile;
	}

	public void setDirectorCurrentCompanies(DirectorCurrentCompanies[] directorCurrentCompanies) {
		this.directorCurrentCompanies = directorCurrentCompanies;
	}

	public void setDirectorsPastCompanies(DirectorsPastCompanies[] directorsPastCompanies) {
		this.directorsPastCompanies = directorsPastCompanies;
	}

	public void setDirectorsPastCompaniesOriginal(DirectorsPastCompaniesOriginal[] directorsPastCompaniesOriginal) {
		this.directorsPastCompaniesOriginal = directorsPastCompaniesOriginal;
	}

	
	
	

}
