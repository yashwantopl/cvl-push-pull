package com.capitaworld.service.loans.domain.fundseeker.mfi;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fs_mfi_expense_expected_income_details")
public class MfiExpenseExpectedIncomeDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "ship_shgi_installment")
    private Double shipShgiInstallment;

    @Column(name = "other_installment")
    private Double otherInstallment;

    @Column(name = "loan_installment")
    private Double loanInstallment;

    @Column(name = "education_expense")
    private Double educationExpense;

    @Column(name = "medical_expense")
    private Double medicalExpense;

    @Column(name = "food_expense")
    private Double foodExpense;

    @Column(name = "other_expense")
    private Double otherExpense;

    @Column(name = "house_hold_expense")
    private Double houseHoldExpense;

    @Column(name = "clothes_expense")
    private Double clothesExpense;

    @Column(name = "business_in_brief")
    private Integer businessInBrief;

    @Column(name = "monthly_cashflow")
    private Double monthlyCashflow;

    @Column(name = "monthly_expenditure")
    private Double monthlyExpenditure;

    @Column(name = "monthly_income")
    private Double monthlyIncome;

    @Column(name = "total_expense")
    private Double totalExpense;

    @Column(name = "net_saving")
    private Double netSaving;

    @Column(name = "cash_flow")
    private Double cashFlow;

    @Column(name = "total_monthly_income_for_family")
	private Double totalMonthlyIncomeForFamily;

    @Column(name = "is_active")
    private Boolean isActive;
    private Integer type;

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

    public Double getShipShgiInstallment() {
        return shipShgiInstallment;
    }

    public void setShipShgiInstallment(Double shipShgiInstallment) {
        this.shipShgiInstallment = shipShgiInstallment;
    }

    public Double getOtherInstallment() {
        return otherInstallment;
    }

    public void setOtherInstallment(Double otherInstallment) {
        this.otherInstallment = otherInstallment;
    }

    public Double getLoanInstallment() {
        return loanInstallment;
    }

    public void setLoanInstallment(Double loanInstallment) {
        this.loanInstallment = loanInstallment;
    }

    public Double getEducationExpense() {
        return educationExpense;
    }

    public void setEducationExpense(Double educationExpense) {
        this.educationExpense = educationExpense;
    }

    public Double getMedicalExpense() {
        return medicalExpense;
    }

    public void setMedicalExpense(Double medicalExpense) {
        this.medicalExpense = medicalExpense;
    }

    public Double getFoodExpense() {
        return foodExpense;
    }

    public void setFoodExpense(Double foodExpense) {
        this.foodExpense = foodExpense;
    }

    public Double getOtherExpense() {
        return otherExpense;
    }

    public void setOtherExpense(Double otherExpense) {
        this.otherExpense = otherExpense;
    }

    public Double getHouseHoldExpense() {
        return houseHoldExpense;
    }

    public void setHouseHoldExpense(Double houseHoldExpense) {
        this.houseHoldExpense = houseHoldExpense;
    }

    public Double getClothesExpense() {
        return clothesExpense;
    }

    public void setClothesExpense(Double clothesExpense) {
        this.clothesExpense = clothesExpense;
    }

    public Integer getBusinessInBrief() {
        return businessInBrief;
    }

    public void setBusinessInBrief(Integer businessInBrief) {
        this.businessInBrief = businessInBrief;
    }

    public Double getMonthlyCashflow() {
        return monthlyCashflow;
    }

    public void setMonthlyCashflow(Double monthlyCashflow) {
        this.monthlyCashflow = monthlyCashflow;
    }

    public Double getMonthlyExpenditure() {
        return monthlyExpenditure;
    }

    public void setMonthlyExpenditure(Double monthlyExpenditure) {
        this.monthlyExpenditure = monthlyExpenditure;
    }

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Double getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(Double totalExpense) {
        this.totalExpense = totalExpense;
    }

    public Double getNetSaving() {
        return netSaving;
    }

    public void setNetSaving(Double netSaving) {
        this.netSaving = netSaving;
    }

    public Double getCashFlow() {
        return cashFlow;
    }

    public void setCashFlow(Double cashFlow) {
        this.cashFlow = cashFlow;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Double getTotalMonthlyIncomeForFamily() {
        return totalMonthlyIncomeForFamily;
    }

    public void setTotalMonthlyIncomeForFamily(Double totalMonthlyIncomeForFamily) {
        this.totalMonthlyIncomeForFamily = totalMonthlyIncomeForFamily;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
