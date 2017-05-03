package com.capitaworld.service.loans.model;

import java.io.Serializable;

/**
 * @author Sanket
 *
 */
public class GuarantorsCorporateDetailRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Boolean isActive = true;

	private String name;

	private String occupation;

	private String propertiesOwned;

	private String propertyType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getPropertiesOwned() {
		return propertiesOwned;
	}

	public void setPropertiesOwned(String propertiesOwned) {
		this.propertiesOwned = propertiesOwned;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	
	

}
