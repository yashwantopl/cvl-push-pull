package com.opl.mudra.api.loans.model;

import java.io.Serializable;

/**
 * Created by pooja.patel on 24-11-2020.
 */
public class OperatorRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private Long applicationId;
    private String drivingLicence;
    private Integer noOfDependent;
    private Double monthlyHouseholdExpense;
    private Double monthlyIncome;
    

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

    
}
