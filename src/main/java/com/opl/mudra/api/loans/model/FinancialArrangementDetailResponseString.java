package com.opl.mudra.api.loans.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class FinancialArrangementDetailResponseString implements Serializable{
	
	private static final long serialVersionUID = 1L;

    private String amount;

    /*private String facilityNature;*/

    private String financialInstitutionName;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date loanDate;
    
   /* private String lenderType;*/
    
    private String outstandingAmount;
    
    private String securityDetails;
    
    private Integer relationshipSince;
    
    private String relationshipSinceInYear;
    
    private String loanType;
    
    private String address;

	private Date reportedDate;
	
	private Object lcbgStatus;
	
	private String emi;
	
    private Double buerauEmi;
    private Double buerauOutStanding;
    private Double collateralAmt;
    
    private String buerauOutStandingStr;
    private String collateralAmtStr;
    private String bureauOrCalculatedEmi;
    
    public String getBureauOrCalculatedEmi() {
 		return bureauOrCalculatedEmi;
 	}

 	public void setBureauOrCalculatedEmi(String bureauOrCalculatedEmi) {
 		this.bureauOrCalculatedEmi = bureauOrCalculatedEmi;
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

	public String getSecurityDetails() {
		return securityDetails;
	}

	public void setSecurityDetails(String securityDetails) {
		this.securityDetails = securityDetails;
	}

	public Integer getRelationshipSince() {
		return relationshipSince;
	}

	public void setRelationshipSince(Integer relationshipSince) {
		this.relationshipSince = relationshipSince;
	}

	public String getRelationshipSinceInYear() {
		return relationshipSinceInYear;
	}

	public void setRelationshipSinceInYear(String relationshipSinceInYear) {
		this.relationshipSinceInYear = relationshipSinceInYear;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(String outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public Date getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(Date reportedDate) {
		this.reportedDate = reportedDate;
	}

	public Object getLcbgStatus() {
		return lcbgStatus;
	}

	public void setLcbgStatus(Object lcbgStatus) {
		this.lcbgStatus = lcbgStatus;
	}

	public String getEmi() {
		return emi;
	}

	public void setEmi(String emi) {
		this.emi = emi;
	}

	public Double getBuerauEmi() {
		return buerauEmi;
	}

	public void setBuerauEmi(Double buerauEmi) {
		this.buerauEmi = buerauEmi;
	}

	public Double getBuerauOutStanding() {
		return buerauOutStanding;
	}

	public void setBuerauOutStanding(Double buerauOutStanding) {
		this.buerauOutStanding = buerauOutStanding;
	}

	public Double getCollateralAmt() {
		return collateralAmt;
	}

	public void setCollateralAmt(Double collateralAmt) {
		this.collateralAmt = collateralAmt;
	}

	public String getBuerauOutStandingStr() {
		return buerauOutStandingStr;
	}

	public void setBuerauOutStandingStr(String buerauOutStandingStr) {
		this.buerauOutStandingStr = buerauOutStandingStr;
	}

	public String getCollateralAmtStr() {
		return collateralAmtStr;
	}

	public void setCollateralAmtStr(String collateralAmtStr) {
		this.collateralAmtStr = collateralAmtStr;
	}
	
	
	
}
