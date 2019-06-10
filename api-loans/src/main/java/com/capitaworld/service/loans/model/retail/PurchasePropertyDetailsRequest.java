package com.capitaworld.service.loans.model.retail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchasePropertyDetailsRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long applicationId;

    private Integer city;

    private Integer state;

    private Integer buildUpArea;

    private Integer superBuildUpArea;

    private Integer carpetArea;

    private Integer totalPriceOfProperty;

    private String propertyName;

    private Date createdDate;

    private Date modifiedDate;

    private Boolean isActive;
    
    private String cityName;
    
    private String stateName;

    public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getBuildUpArea() {
        return buildUpArea;
    }

    public void setBuildUpArea(Integer buildUpArea) {
        this.buildUpArea = buildUpArea;
    }

    public Integer getSuperBuildUpArea() {
        return superBuildUpArea;
    }

    public void setSuperBuildUpArea(Integer superBuildUpArea) {
        this.superBuildUpArea = superBuildUpArea;
    }

    public Integer getCarpetArea() {
        return carpetArea;
    }

    public void setCarpetArea(Integer carpetArea) {
        this.carpetArea = carpetArea;
    }

    public Integer getTotalPriceOfProperty() {
        return totalPriceOfProperty;
    }

    public void setTotalPriceOfProperty(Integer totalPriceOfProperty) {
        this.totalPriceOfProperty = totalPriceOfProperty;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }
}
