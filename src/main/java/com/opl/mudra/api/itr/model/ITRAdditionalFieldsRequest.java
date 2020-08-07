/*
* @author harshit
*/
/**
 * @author harshit
 */
package com.opl.mudra.api.itr.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author harshit
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ITRAdditionalFieldsRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer year;
	
	private Long applicationId;

	private String formType;

	private Double dividendsFromInvestments;
	
	private Double profitLossOnSaleOfFixedAssets;
	
	private Double profitLossOnForexFluctuations;
	
	private Double badDebtsWrittenOff;
	
	private Date createdDate;
	
	private Date modifiedDate;
	
	private Boolean isActive;
	
	private Boolean isLastYear;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public Double getDividendsFromInvestments() {
		return dividendsFromInvestments;
	}

	public void setDividendsFromInvestments(Double dividendsFromInvestments) {
		this.dividendsFromInvestments = dividendsFromInvestments;
	}

	public Double getProfitLossOnSaleOfFixedAssets() {
		return profitLossOnSaleOfFixedAssets;
	}

	public void setProfitLossOnSaleOfFixedAssets(Double profitLossOnSaleOfFixedAssets) {
		this.profitLossOnSaleOfFixedAssets = profitLossOnSaleOfFixedAssets;
	}

	public Double getProfitLossOnForexFluctuations() {
		return profitLossOnForexFluctuations;
	}

	public void setProfitLossOnForexFluctuations(Double profitLossOnForexFluctuations) {
		this.profitLossOnForexFluctuations = profitLossOnForexFluctuations;
	}

	public Double getBadDebtsWrittenOff() {
		return badDebtsWrittenOff;
	}

	public void setBadDebtsWrittenOff(Double badDebtsWrittenOff) {
		this.badDebtsWrittenOff = badDebtsWrittenOff;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsLastYear() {
		return isLastYear;
	}

	public void setIsLastYear(Boolean isLastYear) {
		this.isLastYear = isLastYear;
	}
	
	
	
	
}
