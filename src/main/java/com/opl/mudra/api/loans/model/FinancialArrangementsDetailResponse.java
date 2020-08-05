package com.opl.mudra.api.loans.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opl.mudra.api.loans.exception.LoansException;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by dhaval on 21-May-17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FinancialArrangementsDetailResponse implements Serializable{

    private static final long serialVersionUID = 1L;

    private Double amount;

    private String facilityNature;

    private String financialInstitutionName;
    
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date loanDate;
    
   /* private String lenderType;*/
    
    private Double outstandingAmount;
    
    private String securityDetails;
    
    private Integer relationshipSince;
    
    private String relationshipSinceInYear;
    
    private String loanType;
    
    private String address;
    
    private String directorName;
    
    private Object lcbgStatus;
    
    private String amountStr;
    
    private String outstandingAmountStr;
    
    private Double emi;
    
    private Double buerauEmi;
    private Double buerauOutStanding;
    private Double collateralAmt;
    
   /* public String getFacilityNature() {
        return facilityNature;
    }

    public void setFacilityNature(String facilityNature) {
        this.facilityNature = facilityNature;
    }*/

	public String getFinancialInstitutionName() {
        return financialInstitutionName;
    }

    public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(Double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
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

	/*public String getLenderType() {
		return lenderType;
	}

	public void setLenderType(String lenderType) {
		this.lenderType = lenderType;
	}*/

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

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public String getRelationshipSinceInYear() {
		return relationshipSinceInYear;
	}

	public void setRelationshipSinceInYear(String relationshipSinceInYear) {
		this.relationshipSinceInYear = relationshipSinceInYear;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}
	public Object getLcbgStatus() {
		return lcbgStatus;
	}

	public void setLcbgStatus(Object lcbgStatus) {
		this.lcbgStatus = lcbgStatus;
	}
	
	public String getAmountStr() {
		return amountStr;
	}

	public void setAmountStr(String amountStr) {
		this.amountStr = amountStr;
	}

	public String getOutstandingAmountStr() {
		return outstandingAmountStr;
	}

	public void setOutstandingAmountStr(String outstandingAmountStr) {
		this.outstandingAmountStr = outstandingAmountStr;
	}

	public static void printFields(Object obj) throws LoansException {
		try{
			Field[] fields = FinancialArrangementsDetailResponse.class.getDeclaredFields();

			for(Field field : fields) {
				Object value = field.get(obj);
				if(value instanceof String){
					String a = value.toString().replaceAll("&", "&amp;");
					value = a;
					field.set(obj, value);
				}
			}
		}
		catch (Exception e){
			throw new LoansException(e);
		}

    }

	public Double getEmi() {
		return emi;
	}

	public void setEmi(Double emi) {
		this.emi = emi;
	}

	public String getFacilityNature() {
		return facilityNature;
	}

	public void setFacilityNature(String facilityNature) {
		this.facilityNature = facilityNature;
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

	
	
    
}
