package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author harshit 30-08-2018
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RetailApplicantIncomeRequest implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long applicationId;
	private Integer year;
	private Double salaryIncome;
	private Double incomeRatio;
	private Double houseProperty;
	private Double pgbp;
	private Double capitalGain;
	private Double otherSource;
	private Long createdBy;
    private Date createdDate;
    private Boolean isActive;
    private Long userId;
	
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
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Double getSalaryIncome() {
		return salaryIncome;
	}
	public void setSalaryIncome(Double salaryIncome) {
		this.salaryIncome = salaryIncome;
	}
	public Double getIncomeRatio() {
		return incomeRatio;
	}
	public void setIncomeRatio(Double incomeRatio) {
		this.incomeRatio = incomeRatio;
	}
	public Double getHouseProperty() {
		return houseProperty;
	}
	public void setHouseProperty(Double houseProperty) {
		this.houseProperty = houseProperty;
	}
	public Double getPgbp() {
		return pgbp;
	}
	public void setPgbp(Double pgbp) {
		this.pgbp = pgbp;
	}
	public Double getCapitalGain() {
		return capitalGain;
	}
	public void setCapitalGain(Double capitalGain) {
		this.capitalGain = capitalGain;
	}
	public Double getOtherSource() {
		return otherSource;
	}
	public void setOtherSource(Double otherSource) {
		this.otherSource = otherSource;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}


    
	
}
