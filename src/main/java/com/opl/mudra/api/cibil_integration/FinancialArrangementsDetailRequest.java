package com.opl.mudra.api.cibil_integration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

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
	private Double outstandingAmount;
	private Double emi;
	private Double collateralSecurityAmount;
	private Boolean isActive = true;
	private Long directorId;
	private Integer lcBgStatus;
	private String othersBankName;
	private Boolean isManuallyAdded;
	private Double bureauOutstandingAmount;
	private Double bureauOrCalculatedEmi;
	private String entryNo;
	private Boolean isBureauEmi;
	private String dpdDetails;
	private String securityDetails;
	
	
	public FinancialArrangementsDetailRequest() {
		super();
	}
	
	public FinancialArrangementsDetailRequest(Double amount, Date loanDate, String loanType, Double emi,
			Double outstandingAmount, Double collateralSecurityAmount) {
		super();
		this.amount = amount;
		this.loanDate = loanDate;
		this.loanType = loanType;
		this.emi = emi;
		this.outstandingAmount = outstandingAmount;
		this.collateralSecurityAmount = collateralSecurityAmount;
	}
	
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
	public Date getReportedDate() {
		return reportedDate;
	}
	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public Double getOutstandingAmount() {
		return outstandingAmount;
	}
	public void setOutstandingAmount(Double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}
	public Double getEmi() {
		return emi;
	}
	public void setEmi(Double emi) {
		this.emi = emi;
	}
	public Double getCollateralSecurityAmount() {
		return collateralSecurityAmount;
	}
	public void setCollateralSecurityAmount(Double collateralSecurityAmount) {
		this.collateralSecurityAmount = collateralSecurityAmount;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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
	public String getEntryNo() {
		return entryNo;
	}
	public void setEntryNo(String entryNo) {
		this.entryNo = entryNo;
	}
	public Boolean getIsBureauEmi() {
		return isBureauEmi;
	}
	public void setIsBureauEmi(Boolean isBureauEmi) {
		this.isBureauEmi = isBureauEmi;
	}
	public String getDpdDetails() {
		return dpdDetails;
	}
	public void setDpdDetails(String dpdDetails) {
		this.dpdDetails = dpdDetails;
	}
	public String getSecurityDetails() {
		return securityDetails;
	}
	public void setSecurityDetails(String securityDetails) {
		this.securityDetails = securityDetails;
	}
}





