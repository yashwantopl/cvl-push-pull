package com.opl.mudra.api.connect;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectCoAppRequest  implements Serializable {
	
	private static final long serialVersionUID = 3408967653526547573L;
	
	private String firstName;
	private String middleName;
	private String lastName;
	private Integer relationShipWith;
	private Long applicationId;
	private Long userId;
	private Boolean isIncomeConsider;
	private String pan;

	
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
	public Integer getRelationShipWith() {
		return relationShipWith;
	}
	public void setRelationShipWith(Integer relationShipWith) {
		this.relationShipWith = relationShipWith;
	}

	public Boolean getIsIncomeConsider() {
		return isIncomeConsider;
	}

	public void setIsIncomeConsider(Boolean isIncomeConsider) {
		this.isIncomeConsider = isIncomeConsider;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	
}
