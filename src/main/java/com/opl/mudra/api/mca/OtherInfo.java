package com.opl.mudra.api.mca;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author rahul.meena
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class OtherInfo {
	

	private List<HighCourtWebBeanList> highCourtWebBeanList;

	@JsonProperty("cibil-defaulter")
	private List<Map<String, Object>> cibil_defaulter;

	@JsonProperty("defaulter-list")
	private List<Map<String, Object>> defaulter_list;

	@JsonProperty("high-court")
	private List<HighCourtWebBeanList> high_court;

	private List<Map<String, Object>> bifr;

	private List<Map<String, Object>> nclt;

	private List<Map<String, Object>> sat;

	@JsonProperty("district-court-cases")
	private List<DistrictCourtCases> district_court_cases;

	@JsonProperty("as-on-date-cibil")
	private String as_on_date_cibil;

	@JsonProperty("as-on-date-defaulter")
	private String as_on_date_defaulter;

	@JsonProperty("as-on-date-high-court")
	private String as_on_date_high_court;

	@JsonProperty("as-on-date-bifr")
	private String as_on_date_bifr;

	@JsonProperty("as-on-date-nclt")
	private String as_on_date_nclt;

	@JsonProperty("as-on-date-sat")
	private String as_on_date_sat;

	@JsonProperty("as-on-date-district-courts")
	private String as_on_date_district_courts;

	public List<HighCourtWebBeanList> getHighCourtWebBeanList() {
		return highCourtWebBeanList;
	}

	public List<Map<String, Object>> getCibil_defaulter() {
		return cibil_defaulter;
	}

	public List<Map<String, Object>> getDefaulter_list() {
		return defaulter_list;
	}

	public List<HighCourtWebBeanList> getHigh_court() {
		return high_court;
	}

	public List<Map<String, Object>> getBifr() {
		return bifr;
	}

	public List<Map<String, Object>> getNclt() {
		return nclt;
	}

	public List<Map<String, Object>> getSat() {
		return sat;
	}

	public List<DistrictCourtCases> getDistrict_court_cases() {
		return district_court_cases;
	}

	public String getAs_on_date_cibil() {
		return as_on_date_cibil;
	}

	public String getAs_on_date_defaulter() {
		return as_on_date_defaulter;
	}

	public String getAs_on_date_high_court() {
		return as_on_date_high_court;
	}

	public String getAs_on_date_bifr() {
		return as_on_date_bifr;
	}

	public String getAs_on_date_nclt() {
		return as_on_date_nclt;
	}

	public String getAs_on_date_sat() {
		return as_on_date_sat;
	}

	public String getAs_on_date_district_courts() {
		return as_on_date_district_courts;
	}

	public void setHighCourtWebBeanList(List<HighCourtWebBeanList> highCourtWebBeanList) {
		this.highCourtWebBeanList = highCourtWebBeanList;
	}

	public void setCibil_defaulter(List<Map<String, Object>> cibil_defaulter) {
		this.cibil_defaulter = cibil_defaulter;
	}

	public void setDefaulter_list(List<Map<String, Object>> defaulter_list) {
		this.defaulter_list = defaulter_list;
	}

	public void setHigh_court(List<HighCourtWebBeanList> high_court) {
		this.high_court = high_court;
	}

	public void setBifr(List<Map<String, Object>> bifr) {
		this.bifr = bifr;
	}

	public void setNclt(List<Map<String, Object>> nclt) {
		this.nclt = nclt;
	}

	public void setSat(List<Map<String, Object>> sat) {
		this.sat = sat;
	}

	public void setDistrict_court_cases(List<DistrictCourtCases> district_court_cases) {
		this.district_court_cases = district_court_cases;
	}

	public void setAs_on_date_cibil(String as_on_date_cibil) {
		this.as_on_date_cibil = as_on_date_cibil;
	}

	public void setAs_on_date_defaulter(String as_on_date_defaulter) {
		this.as_on_date_defaulter = as_on_date_defaulter;
	}

	public void setAs_on_date_high_court(String as_on_date_high_court) {
		this.as_on_date_high_court = as_on_date_high_court;
	}

	public void setAs_on_date_bifr(String as_on_date_bifr) {
		this.as_on_date_bifr = as_on_date_bifr;
	}

	public void setAs_on_date_nclt(String as_on_date_nclt) {
		this.as_on_date_nclt = as_on_date_nclt;
	}

	public void setAs_on_date_sat(String as_on_date_sat) {
		this.as_on_date_sat = as_on_date_sat;
	}

	public void setAs_on_date_district_courts(String as_on_date_district_courts) {
		this.as_on_date_district_courts = as_on_date_district_courts;
	}

}
