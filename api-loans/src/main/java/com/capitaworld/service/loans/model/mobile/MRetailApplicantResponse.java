package com.capitaworld.service.loans.model.mobile;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MRetailApplicantResponse extends MobileLoanRequest implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Integer titleId;
	private String firstName;
	private String middleName;
	private String lastName;
	private Integer genderId;
	private Integer statusId;
	private String pan;
	private String aadharNumber;
	
	
	private Integer occupationId;
	
	//salaried
	private String companyName;
	private Long employedWithId;
	
	//business
	private String entityName;
	private Integer industryTypeId;
	private String industryTypeOther;
	
	//self employeed professional
	private Integer selfEmployedOccupationId;
	private String selfEmployedOccupationOther;
	
	//agriculture
	private Double landSize;
	private Integer alliedActivityId;
	
	private Object data;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getTitleId() {
		return titleId;
	}
	public void setTitleId(Integer titleId) {
		this.titleId = titleId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getGenderId() {
		return genderId;
	}
	public void setGenderId(Integer genderId) {
		this.genderId = genderId;
	}
	public Long getEmployedWithId() {
		return employedWithId;
	}
	public void setEmployedWithId(Long employedWithId) {
		this.employedWithId = employedWithId;
	}
	
	public Integer getOccupationId() {
		return occupationId;
	}
	public void setOccupationId(Integer occupationId) {
		this.occupationId = occupationId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public Integer getIndustryTypeId() {
		return industryTypeId;
	}
	public void setIndustryTypeId(Integer industryTypeId) {
		this.industryTypeId = industryTypeId;
	}
	public String getIndustryTypeOther() {
		return industryTypeOther;
	}
	public void setIndustryTypeOther(String industryTypeOther) {
		this.industryTypeOther = industryTypeOther;
	}
	public Integer getSelfEmployedOccupationId() {
		return selfEmployedOccupationId;
	}
	public void setSelfEmployedOccupationId(Integer selfEmployedOccupationId) {
		this.selfEmployedOccupationId = selfEmployedOccupationId;
	}
	public String getSelfEmployedOccupationOther() {
		return selfEmployedOccupationOther;
	}
	public void setSelfEmployedOccupationOther(String selfEmployedOccupationOther) {
		this.selfEmployedOccupationOther = selfEmployedOccupationOther;
	}
	public Double getLandSize() {
		return landSize;
	}
	public void setLandSize(Double landSize) {
		this.landSize = landSize;
	}
	public Integer getAlliedActivityId() {
		return alliedActivityId;
	}
	public void setAlliedActivityId(Integer alliedActivityId) {
		this.alliedActivityId = alliedActivityId;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	
	
	
	
	
}
