package com.opl.mudra.api.loans.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinancialArrangementsDetailRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long id;

	private Double amount;

	 private Integer facilityNatureId;

	private String financialInstitutionName;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date loanDate;
	
	private Date reportedDate;

	private String loanType;

	/* private Integer lenderType; */

	private Double outstandingAmount;

	private Integer relationshipSince;

	private String securityDetails;

	private String address;

	private Double emi;
	
	private Double collateralSecurityAmount;

	private Boolean isActive = true;
	
	private Long directorId;

	private String coAppName;
	/*
	 * SBI MSME Integration related fields
	 * By Ravina
	 * */
	private Integer lcBgStatus;
	private String othersBankName;
	
	private Boolean isManuallyAdded;
	
	private Double bureauOutstandingAmount;
	private Double bureauOrCalculatedEmi;
	private String entryNo;
	private Boolean isBureauEmi;
	private Double amountPastDue;
	private String writtenOffAndSettledStatus;
	private String suitFiledStatus;
	
	private String dpdDetails;
	
	private Integer loanOwnerShip;

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

	 public Integer getFacilityNatureId() { return facilityNatureId; }



	 public void setFacilityNatureId(Integer facilityNatureId) {
	 this.facilityNatureId = facilityNatureId; }

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

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	/*
	 * public Integer getLenderType() { return lenderType; }
	 * 
	 * 
	 * 
	 * public void setLenderType(Integer lenderType) { this.lenderType = lenderType;
	 * }
	 */

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}

	public Double getEmi() {
		return emi;
	}

	public void setEmi(Double emi) {
		this.emi = emi;
	}

	public Long getDirectorId() {
		return directorId;
	}

	public void setDirectorId(Long directorId) {
		this.directorId = directorId;
	}

	public Integer getLcBgStatus() {
		return lcBgStatus;
	}

	public void setLcBgStatus(Integer lcBgStatus) {
		this.lcBgStatus = lcBgStatus;
	}

	public String getOthersBankName() {
		return othersBankName;
	}

	public void setOthersBankName(String othersBankName) {
		this.othersBankName = othersBankName;
	}

	public Boolean getIsManuallyAdded() {
		return isManuallyAdded;
	}

	public void setIsManuallyAdded(Boolean isManuallyAdded) {
		this.isManuallyAdded = isManuallyAdded;
	}

	public Double getCollateralSecurityAmount() {
		return collateralSecurityAmount;
	}

	public void setCollateralSecurityAmount(Double collateralSecurityAmount) {
		this.collateralSecurityAmount = collateralSecurityAmount;
	}

	public Double getBureauOutstandingAmount() {
		return bureauOutstandingAmount;
	}

	public void setBureauOutstandingAmount(Double bureauOutstandingAmount) {
		this.bureauOutstandingAmount = bureauOutstandingAmount;
	}

	public Double getBureauOrCalculatedEmi() {
		return bureauOrCalculatedEmi;
	}

	public void setBureauOrCalculatedEmi(Double bureauOrCalculatedEmi) {
		this.bureauOrCalculatedEmi = bureauOrCalculatedEmi;
	}

	public String getCoAppName() {
		return coAppName;
	}

	public void setCoAppName(String coAppName) {
		this.coAppName = coAppName;
	}

	public Boolean getIsBureauEmi() {
		return isBureauEmi;
	}

	public void setIsBureauEmi(Boolean isBureauEmi) {
		this.isBureauEmi = isBureauEmi;
	}

	public String getEntryNo() {
		return entryNo;
	}

	public void setEntryNo(String entryNo) {
		this.entryNo = entryNo;
	}

	public String getDpdDetails() {
		return dpdDetails;
	}

	public void setDpdDetails(String dpdDetails) {
		this.dpdDetails = dpdDetails;
	}

	public Integer getLoanOwnerShip() {
		return loanOwnerShip;
	}

	public void setLoanOwnerShip(Integer loanOwnerShip) {
		this.loanOwnerShip = loanOwnerShip;
	}

	public Double getAmountPastDue() {
		return amountPastDue;
	}

	public void setAmountPastDue(Double amountPastDue) {
		this.amountPastDue = amountPastDue;
	}

	public String getWrittenOffAndSettledStatus() {
		return writtenOffAndSettledStatus;
	}

	public void setWrittenOffAndSettledStatus(String writtenOffAndSettledStatus) {
		this.writtenOffAndSettledStatus = writtenOffAndSettledStatus;
	}

	public String getSuitFiledStatus() {
		return suitFiledStatus;
	}

	public void setSuitFiledStatus(String suitFiledStatus) {
		this.suitFiledStatus = suitFiledStatus;
	}
	
}
