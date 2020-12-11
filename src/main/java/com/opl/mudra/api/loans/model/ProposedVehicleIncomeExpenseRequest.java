package com.opl.mudra.api.loans.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by pooja.patel on 24-11-2020.
 */
public class ProposedVehicleIncomeExpenseRequest implements Serializable {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Long applicationId;
    private Integer noOfDaysVehicleOperated;
    private Integer kmCoveredByVehicleInDay;
    private Double ratePerKm;
    private Double avgMonthlyIncome;
    private Double otherMonthlyIncome;
    private Integer typeOfFuel;
    private Double fuelCostPerLtr;
    private Double avgKmsPerLtr;
    private Double fuelCostPerMnth;
    private Double incomeTaxPaid;
    private Date createdDate;
    private Long createdBy;
    private Date modifiedDate;
    private Long modifiedBy;
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

    public Integer getNoOfDaysVehicleOperated() {
        return noOfDaysVehicleOperated;
    }

    public void setNoOfDaysVehicleOperated(Integer noOfDaysVehicleOperated) {
        this.noOfDaysVehicleOperated = noOfDaysVehicleOperated;
    }

    public Integer getKmCoveredByVehicleInDay() {
        return kmCoveredByVehicleInDay;
    }

    public void setKmCoveredByVehicleInDay(Integer kmCoveredByVehicleInDay) {
        this.kmCoveredByVehicleInDay = kmCoveredByVehicleInDay;
    }

    public Double getRatePerKm() {
        return ratePerKm;
    }

    public void setRatePerKm(Double ratePerKm) {
        this.ratePerKm = ratePerKm;
    }

    public Double getAvgMonthlyIncome() {
        return avgMonthlyIncome;
    }

    public void setAvgMonthlyIncome(Double avgMonthlyIncome) {
        this.avgMonthlyIncome = avgMonthlyIncome;
    }

    public Double getOtherMonthlyIncome() {
        return otherMonthlyIncome;
    }

    public void setOtherMonthlyIncome(Double otherMonthlyIncome) {
        this.otherMonthlyIncome = otherMonthlyIncome;
    }

    public Double getFuelCostPerLtr() {
        return fuelCostPerLtr;
    }

    public void setFuelCostPerLtr(Double fuelCostPerLtr) {
        this.fuelCostPerLtr = fuelCostPerLtr;
    }

    public Double getIncomeTaxPaid() {
        return incomeTaxPaid;
    }

    public void setIncomeTaxPaid(Double incomeTaxPaid) {
        this.incomeTaxPaid = incomeTaxPaid;
    }

    public Integer getTypeOfFuel() {
        return typeOfFuel;
    }

    public void setTypeOfFuel(Integer typeOfFuel) {
        this.typeOfFuel = typeOfFuel;
    }

    public Double getAvgKmsPerLtr() {
        return avgKmsPerLtr;
    }

    public void setAvgKmsPerLtr(Double avgKmsPerLtr) {
        this.avgKmsPerLtr = avgKmsPerLtr;
    }

    public Double getFuelCostPerMnth() {
        return fuelCostPerMnth;
    }

    public void setFuelCostPerMnth(Double fuelCostPerMnth) {
        this.fuelCostPerMnth = fuelCostPerMnth;
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
