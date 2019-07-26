package com.capitaworld.service.loans.domain.fundseeker.mfi;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "fs_mfi_income_details")
public class MfiIncomeDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "application_id")
    private Long applicationId;
    @Column(name = "relation_id")
    private Integer relationId;

    @Column(name = "occupation")
    private Integer occupation;

    @Column(name = "net_income")
    private Double netIncome;

    @Column(name = "frequency_income")
    private Integer frequencyIncome;

    @Column(name = "monthly_income")
    private Double monthlyIncome;

    @Column(name = "yearly_income")
    private Double yearlyIncome;

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

    public Integer getOccupation() {
        return occupation;
    }

    public void setOccupation(Integer occupation) {
        this.occupation = occupation;
    }

    public Double getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(Double netIncome) {
        this.netIncome = netIncome;
    }

    public Integer getFrequencyIncome() {
        return frequencyIncome;
    }

    public void setFrequencyIncome(Integer frequencyIncome) {
        this.frequencyIncome = frequencyIncome;
    }

    public Double getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(Double monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public Double getYearlyIncome() {
        return yearlyIncome;
    }

    public void setYearlyIncome(Double yearlyIncome) {
        this.yearlyIncome = yearlyIncome;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }
}
