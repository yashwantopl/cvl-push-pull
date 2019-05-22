package com.capitaworld.service.loans.model.api_model;

import java.io.Serializable;

public class GuarantorsCorporateDetailRequest extends AuditorRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String propertiesOwned;

	private String occupation;

	private String value;

	private String address;

	private Long applicationId;

	public GuarantorsCorporateDetailRequest() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPropertiesOwned() {
		return propertiesOwned;
	}

	public void setPropertiesOwned(String propertiesOwned) {
		this.propertiesOwned = propertiesOwned;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "GuarantorsCorporateDetail [id=" + id + ", name=" + name + ", propertiesOwned=" + propertiesOwned
				+ ", occupation=" + occupation + ", value=" + value + ", applicationId=" + applicationId + "]";
	}

}