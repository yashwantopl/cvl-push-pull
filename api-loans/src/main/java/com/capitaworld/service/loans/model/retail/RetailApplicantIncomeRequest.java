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
	private Long coAppId;
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
	private Double salaryIncomeGross;
	private Double housePropertyGross;
	private Double pgbpGross;
	private Double capitalGainGross;
	private Double otherSourceGross;
	private String salaryMode;
	private String housePropertyMode;
	private String pgbpMode;
	private String capitalGainMode;
	private String otherSourceMode;

	//FOR PL CAM REPORT
	private String salaryIncomeString;
	private String incomeRatioString;
	private String housePropertyString;
	private String pgbpString;
	private String capitalGainString;
	private String otherSourceString;
	
	private String salaryIncomeGrossString;
	private String housePropertyGrossString;
	private String pgbpGrossString;
	private String capitalGainGrossString;
	private String otherSourceGrossString;
	
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

	public Double getSalaryIncomeGross() {
		return salaryIncomeGross;
	}

	public void setSalaryIncomeGross(Double salaryIncomeGross) {
		this.salaryIncomeGross = salaryIncomeGross;
	}

	public Double getHousePropertyGross() {
		return housePropertyGross;
	}

	public void setHousePropertyGross(Double housePropertyGross) {
		this.housePropertyGross = housePropertyGross;
	}

	public Double getPgbpGross() {
		return pgbpGross;
	}

	public void setPgbpGross(Double pgbpGross) {
		this.pgbpGross = pgbpGross;
	}

	public Double getCapitalGainGross() {
		return capitalGainGross;
	}

	public void setCapitalGainGross(Double capitalGainGross) {
		this.capitalGainGross = capitalGainGross;
	}

	public Double getOtherSourceGross() {
		return otherSourceGross;
	}

	public void setOtherSourceGross(Double otherSourceGross) {
		this.otherSourceGross = otherSourceGross;
	}

	public String getSalaryMode() {
		return salaryMode;
	}

	public void setSalaryMode(String salaryMode) {
		this.salaryMode = salaryMode;
	}

	public String getHousePropertyMode() {
		return housePropertyMode;
	}

	public void setHousePropertyMode(String housePropertyMode) {
		this.housePropertyMode = housePropertyMode;
	}

	public String getPgbpMode() {
		return pgbpMode;
	}

	public void setPgbpMode(String pgbpMode) {
		this.pgbpMode = pgbpMode;
	}

	public String getCapitalGainMode() {
		return capitalGainMode;
	}

	public void setCapitalGainMode(String capitalGainMode) {
		this.capitalGainMode = capitalGainMode;
	}

	public String getOtherSourceMode() {
		return otherSourceMode;
	}

	public void setOtherSourceMode(String otherSourceMode) {
		this.otherSourceMode = otherSourceMode;
	}
	public String getSalaryIncomeString() {
		return salaryIncomeString;
	}
	public void setSalaryIncomeString(String salaryIncomeString) {
		this.salaryIncomeString = salaryIncomeString;
	}
	public String getIncomeRatioString() {
		return incomeRatioString;
	}
	public void setIncomeRatioString(String incomeRatioString) {
		this.incomeRatioString = incomeRatioString;
	}
	public String getHousePropertyString() {
		return housePropertyString;
	}
	public void setHousePropertyString(String housePropertyString) {
		this.housePropertyString = housePropertyString;
	}
	public String getPgbpString() {
		return pgbpString;
	}
	public void setPgbpString(String pgbpString) {
		this.pgbpString = pgbpString;
	}
	public String getCapitalGainString() {
		return capitalGainString;
	}
	public void setCapitalGainString(String capitalGainString) {
		this.capitalGainString = capitalGainString;
	}
	public String getOtherSourceString() {
		return otherSourceString;
	}
	public void setOtherSourceString(String otherSourceString) {
		this.otherSourceString = otherSourceString;
	}
	public String getSalaryIncomeGrossString() {
		return salaryIncomeGrossString;
	}
	public void setSalaryIncomeGrossString(String salaryIncomeGrossString) {
		this.salaryIncomeGrossString = salaryIncomeGrossString;
	}
	public String getHousePropertyGrossString() {
		return housePropertyGrossString;
	}
	public void setHousePropertyGrossString(String housePropertyGrossString) {
		this.housePropertyGrossString = housePropertyGrossString;
	}
	public String getPgbpGrossString() {
		return pgbpGrossString;
	}
	public void setPgbpGrossString(String pgbpGrossString) {
		this.pgbpGrossString = pgbpGrossString;
	}
	public String getCapitalGainGrossString() {
		return capitalGainGrossString;
	}
	public void setCapitalGainGrossString(String capitalGainGrossString) {
		this.capitalGainGrossString = capitalGainGrossString;
	}
	public String getOtherSourceGrossString() {
		return otherSourceGrossString;
	}
	public void setOtherSourceGrossString(String otherSourceGrossString) {
		this.otherSourceGrossString = otherSourceGrossString;
	}
	public Long getCoAppId() {
		return coAppId;
	}
	public void setCoAppId(Long coAppId) {
		this.coAppId = coAppId;
	}
	
}
