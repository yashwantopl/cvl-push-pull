package com.opl.mudra.api.cibil.model.cir360;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PlaceOfBirthInfo {

	@JsonProperty("CityOfBirth")
	private String cityofBirth;
	
	@JsonProperty("StateOfBirth")
	private String stateOfbirth;
	
	@JsonProperty("ReportedDate")
	private String reportedDate;

	public String getCityofBirth() {
		return cityofBirth;
	}

	public void setCityofBirth(String cityofBirth) {
		this.cityofBirth = cityofBirth;
	}

	public String getStateOfbirth() {
		return stateOfbirth;
	}

	public void setStateOfbirth(String stateOfbirth) {
		this.stateOfbirth = stateOfbirth;
	}

	public String getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}
	
	
}
