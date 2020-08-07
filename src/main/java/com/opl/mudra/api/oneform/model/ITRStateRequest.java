package com.opl.mudra.api.oneform.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ITRStateRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String pinCityName;
	private String pinStateName;
	private String docCityName;
	private String docStateName;
	private Long stateId;
	private Long cityId;
	private Long countryId;
	public String getPinCityName() {
		return pinCityName;
	}
	public void setPinCityName(String pinCityName) {
		this.pinCityName = pinCityName;
	}
	public String getPinStateName() {
		return pinStateName;
	}
	public void setPinStateName(String pinStateName) {
		this.pinStateName = pinStateName;
	}
	public String getDocCityName() {
		return docCityName;
	}
	public void setDocCityName(String docCityName) {
		this.docCityName = docCityName;
	}
	public String getDocStateName() {
		return docStateName;
	}
	public void setDocStateName(String docStateName) {
		this.docStateName = docStateName;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public Long getCityId() {
		return cityId;
	}
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	
	public Long getCountryId() {
		return countryId;
	}
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}
	@Override
	public String toString() {
		return "ITRStateRequest [pinCityName=" + pinCityName + ", pinStateName=" + pinStateName + ", docCityName="
				+ docCityName + ", docStateName=" + docStateName + ", stateId=" + stateId + ", cityId=" + cityId
				+ ", countryId=" + countryId + "]";
	}
	
	
	
	

}
