package com.opl.service.loans.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pooja.patel on 24-11-2020.
 */

@Entity
@Table(name="operator_detail")
public class OperatorDetail {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="application_id")
    private Long applicationId;

    @Column(name="driving_licence")
    private String drivingLicence;

    @Column(name="no_of_dependent")
    private Integer noOfDependent;

    @Column(name="monthly_household_expense")
    private Double monthlyHouseholdExpense;

    @Column(name="monthly_income")
    private Double monthlyIncome;

    @Column(name="created_date")
    private Date createdDate;

    @Column(name="created_by")
    private Long createdBy;

    @Column(name="modified_date")
    private Date modifiedDate;

    @Column(name="modified_by")
    private Long modifiedBy;

    @Column(name="is_active")
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

    public String getDrivingLicence() {
        return drivingLicence;
    }

    public void setDrivingLicence(String drivingLicence) {
        this.drivingLicence = drivingLicence;
    }

    public Integer getNoOfDependent() {
        return noOfDependent;
    }

    public void setNoOfDependent(Integer noOfDependent) {
        this.noOfDependent = noOfDependent;
    }

    public Double getMonthlyHouseholdExpense() {
        return monthlyHouseholdExpense;
    }

    public void setMonthlyHouseholdExpense(Double monthlyHouseholdExpense) {
        this.monthlyHouseholdExpense = monthlyHouseholdExpense;
    }

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
