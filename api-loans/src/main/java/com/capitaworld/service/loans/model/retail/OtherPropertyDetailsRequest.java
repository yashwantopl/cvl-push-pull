package com.capitaworld.service.loans.model.retail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OtherPropertyDetailsRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long applicationId;

    private Integer propertyType;

    private Integer totalCostOfLand;

    private Integer totalCostOfConstruction;

    private Integer timeForCompletion;

    private Date createdDate;

    private Date modifiedDate;

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

    public Integer getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(Integer propertyType) {
        this.propertyType = propertyType;
    }

    public Integer getTotalCostOfLand() {
        return totalCostOfLand;
    }

    public void setTotalCostOfLand(Integer totalCostOfLand) {
        this.totalCostOfLand = totalCostOfLand;
    }

    public Integer getTotalCostOfConstruction() {
        return totalCostOfConstruction;
    }

    public void setTotalCostOfConstruction(Integer totalCostOfConstruction) {
        this.totalCostOfConstruction = totalCostOfConstruction;
    }

    public Integer getTimeForCompletion() {
        return timeForCompletion;
    }

    public void setTimeForCompletion(Integer timeForCompletion) {
        this.timeForCompletion = timeForCompletion;
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
}
