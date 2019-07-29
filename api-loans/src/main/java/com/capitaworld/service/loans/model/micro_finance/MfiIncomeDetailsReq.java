package com.capitaworld.service.loans.model.micro_finance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class MfiIncomeDetailsReq implements Serializable {

    private Long id;

    private Long applicationId;

    private Integer occupation;

    private Double netIncome;

    private Integer frequencyIncome;

    private Double monthlyIncome;

    private Double yearlyIncome;

    private Integer relationId;

    public MfiIncomeDetailsReq() {
    }

    public MfiIncomeDetailsReq(Long applicationId, Integer occupation, Double netIncome, Integer frequencyIncome,
                               Double monthlyIncome, Double yearlyIncome, Integer relationId) {
		this.applicationId = applicationId;
		this.occupation = occupation;
		this.netIncome = netIncome;
		this.frequencyIncome = frequencyIncome;
		this.monthlyIncome = monthlyIncome;
		this.yearlyIncome = yearlyIncome;
		this.relationId = relationId;
	}

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
