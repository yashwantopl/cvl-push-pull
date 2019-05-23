package com.capitaworld.service.loans.model.api_model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDRFamilyDirectorsDetailsRequest implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private Long applicationId;
	
	private Long backgroundId;
	
	private String name;
	
	private String maritalStatus;
	
	private String maritalStatusName;
	
	private String isHouseOwned;
	
	private String addressOfOtherProperty;
	
	private String nameOccupationOfSpouse;
	
	private Long createdBy;
	
	private Date createdDate;
	
	private Long modifyBy;
	
	private Date modifyDate;

	private Boolean isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}


	public Long getBackgroundId() {
		return backgroundId;
	}

	public void setBackgroundId(Long backgroundId) {
		this.backgroundId = backgroundId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getIsHouseOwned() {
		return isHouseOwned;
	}

	public void setIsHouseOwned(String isHouseOwned) {
		this.isHouseOwned = isHouseOwned;
	}

	public String getAddressOfOtherProperty() {
		return addressOfOtherProperty;
	}

	public void setAddressOfOtherProperty(String addressOfOtherProperty) {
		this.addressOfOtherProperty = addressOfOtherProperty;
	}

	public String getNameOccupationOfSpouse() {
		return nameOccupationOfSpouse;
	}

	public void setNameOccupationOfSpouse(String nameOccupationOfSpouse) {
		this.nameOccupationOfSpouse = nameOccupationOfSpouse;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	
	public String getMaritalStatusName() {
		return maritalStatusName;
	}

	public void setMaritalStatusName(String maritalStatusName) {
		this.maritalStatusName = maritalStatusName;
	}

	@Override
	public String toString() {
		return "DDRFamilyDirectorsDetailsRequest [id=" + id + ", applicationId=" + applicationId + ", backgroundId="
				+ backgroundId + ", name=" + name + ", maritalStatus=" + maritalStatus + ", isHouseOwned="
				+ isHouseOwned + ", addressOfOtherProperty=" + addressOfOtherProperty + ", nameOccupationOfSpouse="
				+ nameOccupationOfSpouse + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", modifyBy="
				+ modifyBy + ", modifyDate=" + modifyDate + ", isActive=" + isActive + "]";
	}
}
