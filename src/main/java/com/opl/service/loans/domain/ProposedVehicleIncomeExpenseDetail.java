package com.opl.service.loans.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pooja.patel on 24-11-2020.
 */

@Entity
@Table(name="proposed_vehicle_income_expense_detail")
public class ProposedVehicleIncomeExpenseDetail {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="application_id")
    private Long applicationId;

    @Column(name="no_of_days_vehicle_operated")
    private Integer noOfDaysVehicleOperated;

    @Column(name="km_covered_by_vehicle_in_day")
    private Integer kmCoveredByVehicleInDay;

    @Column(name="rate_per_km")
    private Double ratePerKm;

    @Column(name="avg_monthly_income")
    private Double avgMonthlyIncome;

    @Column(name="other_monthly_income")
    private Double otherMonthlyIncome;

    @Column(name="type_of_fuel")
    private Integer typeOfFuel;

    @Column(name="fuel_cost_per_ltr")
    private Double fuelCostPerLtr;

    @Column(name="avg_kms_per_ltr")
    private Double avgKmsPerLtr;

    @Column(name="fuel_cost_per_mnth")
    private Double fuelCostPerMnth;

    @Column(name="income_tax_paid")
    private Double incomeTaxPaid;

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
