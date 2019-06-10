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

    private Integer totalCostOfLand;

    private Integer totalCostOfConstruction;

    private Integer timeForCompletionConstruction;

    private Integer timeForCompletionRenovation;

    private Date createdDate;

    private Date modifiedDate;

    private Boolean isActive;

    private Integer totalCostOfRenovation;

    private Integer typeOfRepairRenovation;

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

    public Integer getTotalCostOfRenovation() {
        return totalCostOfRenovation;
    }

    public void setTotalCostOfRenovation(Integer totalCostOfRenovation) {
        this.totalCostOfRenovation = totalCostOfRenovation;
    }

    public Integer getTypeOfRepairRenovation() {
        return typeOfRepairRenovation;
    }

    public void setTypeOfRepairRenovation(Integer typeOfRepairRenovation) {
        this.typeOfRepairRenovation = typeOfRepairRenovation;
    }

    public Integer getTimeForCompletionConstruction() {
        return timeForCompletionConstruction;
    }

    public void setTimeForCompletionConstruction(Integer timeForCompletionConstruction) {
        this.timeForCompletionConstruction = timeForCompletionConstruction;
    }

    public Integer getTimeForCompletionRenovation() {
        return timeForCompletionRenovation;
    }

    public void setTimeForCompletionRenovation(Integer timeForCompletionRenovation) {
        this.timeForCompletionRenovation = timeForCompletionRenovation;
    }
}
