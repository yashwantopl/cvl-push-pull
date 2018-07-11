package com.capitaworld.service.loans.model.corporate;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CorporateDirectorIncomeRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long applicationId;
	
	private Long directorId;
	
	private Long userId;
	
	private String year;
	
	private Double salary;
	
	private String houseProperty;
	
	private String PGBP;
	
	private String capitalGain;
	
	private String otherSource;
	
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

	public Long getDirectorId() {
		return directorId;
	}

	public void setDirectorId(Long directorId) {
		this.directorId = directorId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getHouseProperty() {
		return houseProperty;
	}

	public void setHouseProperty(String houseProperty) {
		this.houseProperty = houseProperty;
	}

	public String getPGBP() {
		return PGBP;
	}

	public void setPGBP(String pGBP) {
		PGBP = pGBP;
	}

	public String getCapitalGain() {
		return capitalGain;
	}

	public void setCapitalGain(String capitalGain) {
		this.capitalGain = capitalGain;
	}

	public String getOtherSource() {
		return otherSource;
	}

	public void setOtherSource(String otherSource) {
		this.otherSource = otherSource;
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

	@Override
	public String toString() {
		return "CorporateDirectorIncomeRequest [id=" + id + ", applicationId=" + applicationId + ", directorId="
				+ directorId + ", year=" + year + ", salary=" + salary + ", houseProperty=" + houseProperty + ", PGBP="
				+ PGBP + ", capitalGain=" + capitalGain + ", otherSource=" + otherSource + ", isActive=" + isActive + ", userId=" + userId 
				+ "]";
	}
	
	
	
	
	

}
