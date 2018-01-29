package com.capitaworld.service.loans.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinancialArrangementsDetailRequest {
	
	private Long id;

	private Double amount;

	private Integer facilityNatureId;

	private String financialInstitutionName;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date loanDate;
	
	private Integer loanType;
	
	private Integer lenderType;
	
	private Double outstandingAmount;
	
	private Integer relationshipSince;
	
	private String securityDetails;
	
	

	private Boolean isActive = true;



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Double getAmount() {
		return amount;
	}



	public void setAmount(Double amount) {
		this.amount = amount;
	}



	public Integer getFacilityNatureId() {
		return facilityNatureId;
	}



	public void setFacilityNatureId(Integer facilityNatureId) {
		this.facilityNatureId = facilityNatureId;
	}



	public String getFinancialInstitutionName() {
		return financialInstitutionName;
	}



	public void setFinancialInstitutionName(String financialInstitutionName) {
		this.financialInstitutionName = financialInstitutionName;
	}



	public Date getLoanDate() {
		return loanDate;
	}



	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}



	public Integer getLoanType() {
		return loanType;
	}



	public void setLoanType(Integer loanType) {
		this.loanType = loanType;
	}



	public Integer getLenderType() {
		return lenderType;
	}



	public void setLenderType(Integer lenderType) {
		this.lenderType = lenderType;
	}



	public Double getOutstandingAmount() {
		return outstandingAmount;
	}



	public void setOutstandingAmount(Double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}



	public Integer getRelationshipSince() {
		return relationshipSince;
	}



	public void setRelationshipSince(Integer relationshipSince) {
		this.relationshipSince = relationshipSince;
	}



	public String getSecurityDetails() {
		return securityDetails;
	}



	public void setSecurityDetails(String securityDetails) {
		this.securityDetails = securityDetails;
	}



	public Boolean getIsActive() {
		return isActive;
	}



	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	

}
