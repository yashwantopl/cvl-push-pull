package com.capitaworld.service.loans.model.retail;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmpAgriculturistTypeRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;


    private Long applicationId;

    private Integer totalLandOwnedAndInPossesion;

    private Integer presentlyIrrigated;

    private Integer seasonalIrrigated;

    private Integer rainFed;

    private Integer unAttended;

    private Date createdDate;

    private Date modifiedDate;

    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTotalLandOwnedAndInPossesion() {
        return totalLandOwnedAndInPossesion;
    }

    public void setTotalLandOwnedAndInPossesion(Integer totalLandOwnedAndInPossesion) {
        this.totalLandOwnedAndInPossesion = totalLandOwnedAndInPossesion;
    }

    public Integer getPresentlyIrrigated() {
        return presentlyIrrigated;
    }

    public void setPresentlyIrrigated(Integer presentlyIrrigated) {
        this.presentlyIrrigated = presentlyIrrigated;
    }

    public Integer getSeasonalIrrigated() {
        return seasonalIrrigated;
    }

    public void setSeasonalIrrigated(Integer seasonalIrrigated) {
        this.seasonalIrrigated = seasonalIrrigated;
    }

    public Integer getRainFed() {
        return rainFed;
    }

    public void setRainFed(Integer rainFed) {
        this.rainFed = rainFed;
    }

    public Integer getUnAttended() {
        return unAttended;
    }

    public void setUnAttended(Integer unAttended) {
        this.unAttended = unAttended;
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
