package com.capitaworld.service.loans.model.mobile;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MobileFrameDetailsRequest  implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String categoryCode;

	private String name;

	private Double tenure;
	
	private Integer employmentType;
	
	private Integer propertyType;
	
	private Double income;
	
	private Double obligation;
	
	private Integer totalExperienceMonth;

	private Integer totalExperienceYear;
	
	private Integer currentJobMonth;

	private Integer currentJobYear;
	
	private String propertyLocation;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date dateOfBirth;
	
	private Double marketValue;

	public String getCategoryCode() {
		return categoryCode;
	}

	public String getName() {
		return name;
	}



	public Double getTenure() {
		return tenure;
	}

	public Integer getEmploymentType() {
		return employmentType;
	}

	public Integer getPropertyType() {
		return propertyType;
	}

	public Double getIncome() {
		return income;
	}

	public Double getObligation() {
		return obligation;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public Double getMarketValue() {
		return marketValue;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public void setName(String name) {
		this.name = name;
	}



	public void setTenure(Double tenure) {
		this.tenure = tenure;
	}

	public void setEmploymentType(Integer employmentType) {
		this.employmentType = employmentType;
	}

	public void setPropertyType(Integer propertyType) {
		this.propertyType = propertyType;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public void setObligation(Double obligation) {
		this.obligation = obligation;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setMarketValue(Double marketValue) {
		this.marketValue = marketValue;
	}

	public Integer getTotalExperienceMonth() {
		return totalExperienceMonth;
	}

	public Integer getTotalExperienceYear() {
		return totalExperienceYear;
	}

	public void setTotalExperienceMonth(Integer totalExperienceMonth) {
		this.totalExperienceMonth = totalExperienceMonth;
	}

	public void setTotalExperienceYear(Integer totalExperienceYear) {
		this.totalExperienceYear = totalExperienceYear;
	}

	public Integer getCurrentJobMonth() {
		return currentJobMonth;
	}

	public Integer getCurrentJobYear() {
		return currentJobYear;
	}

	public void setCurrentJobMonth(Integer currentJobMonth) {
		this.currentJobMonth = currentJobMonth;
	}

	public void setCurrentJobYear(Integer currentJobYear) {
		this.currentJobYear = currentJobYear;
	}

	public String getPropertyLocation() {
		return propertyLocation;
	}

	public void setPropertyLocation(String propertyLocation) {
		this.propertyLocation = propertyLocation;
	}
	
	

}
