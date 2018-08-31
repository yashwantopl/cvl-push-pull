package com.capitaworld.service.loans.domain.fundseeker.retail;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.capitaworld.service.loans.domain.fundseeker.AuditActivity;

/**
 * @author harshit 30-08-2018
 *
 */
@Entity
@Table(name = "fs_retail_applicant_income_details")
public class RetailApplicantIncomeDetail extends AuditActivity implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "application_id")
	private Long applicationId;
	
	@Column(name = "year")
	private Integer year;
	
	@Column(name = "salary_income")
	private Double salaryIncome;
	
	@Column(name = "income_ratio")
	private Double incomeRatio;
	
	@Column(name = "house_property")
	private Double houseProperty;
	
	@Column(name = "pgbp")
	private Double pgbp;
	
	@Column(name = "capital_gain")
	private Double capitalGain;
	
	@Column(name = "other_source")
	private Double otherSource;
	
	
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


}
