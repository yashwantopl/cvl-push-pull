package com.capitaworld.service.loans.model.retail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmpSalariedTypeRequest implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long applicationId;

    private Integer bonusPerAnnum;

    private Integer incentivePerAnnum;

    private Integer taxPaidLastYear;

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

    public Integer getBonusPerAnnum() {
        return bonusPerAnnum;
    }

    public void setBonusPerAnnum(Integer bonusPerAnnum) {
        this.bonusPerAnnum = bonusPerAnnum;
    }

    public Integer getIncentivePerAnnum() {
        return incentivePerAnnum;
    }

    public void setIncentivePerAnnum(Integer incentivePerAnnum) {
        this.incentivePerAnnum = incentivePerAnnum;
    }

    public Integer getTaxPaidLastYear() {
        return taxPaidLastYear;
    }

    public void setTaxPaidLastYear(Integer taxPaidLastYear) {
        this.taxPaidLastYear = taxPaidLastYear;
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
