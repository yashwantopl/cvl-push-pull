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

    private Integer incomeDays;

    private Integer frequencyIncome;

    private Double monthlyIncome;

    private Double yearlyIncome;

    private Integer relationId;

    private Integer type;

    public MfiIncomeDetailsReq() {
    }

    public MfiIncomeDetailsReq(Long id, Long applicationId, Integer occupation, Double netIncome, Integer frequencyIncome,
                               Double monthlyIncome, Double yearlyIncome, Integer relationId,Integer type,Integer incomeDays) {
    	this.id = id;
		this.applicationId = applicationId;
		this.occupation = occupation;
		this.netIncome = netIncome;
		this.frequencyIncome = frequencyIncome;
		this.monthlyIncome = monthlyIncome;
		this.yearlyIncome = yearlyIncome;
		this.relationId = relationId;
		this.type = type;
		this.incomeDays = incomeDays;
	}

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

	@Override
	public String toString() {
		return "MfiIncomeDetailsReq [id=" + id + ", applicationId=" + applicationId + ", occupation=" + occupation
				+ ", netIncome=" + netIncome + ", frequencyIncome=" + frequencyIncome + ", monthlyIncome="
				+ monthlyIncome + ", yearlyIncome=" + yearlyIncome + ", relationId=" + relationId + "]";
	}

    public Integer getIncomeDays() {
        return incomeDays;
    }

    public void setIncomeDays(Integer incomeDays) {
        this.incomeDays = incomeDays;
    }
}
