package com.opl.mudra.api.loans.model.common;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportRequest implements Serializable {

	private static final long serialVersionUID = 6232712419796541868L;
	
	private Date fromDate;
	private Date toDate;
	private Long id;
	private Integer number;
	private String value;
	private Long businessTypeId;
	private Integer connectFlowTypeId;
	private Long profileId;
	private Long applicationId;
	
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the businessTypeId
	 */
	public Long getBusinessTypeId() {
		return businessTypeId;
	}
	/**
	 * @param businessTypeId the businessTypeId to set
	 */
	public void setBusinessTypeId(Long businessTypeId) {
		this.businessTypeId = businessTypeId;
	}
	public Integer getConnectFlowTypeId() {
		return connectFlowTypeId;
	}
	public void setConnectFlowTypeId(Integer connectFlowTypeId) {
		this.connectFlowTypeId = connectFlowTypeId;
	}
	public Long getProfileId() {
		return profileId;
	}
	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	

	
}
