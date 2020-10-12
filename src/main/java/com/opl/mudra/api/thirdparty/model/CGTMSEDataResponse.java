/**
 * 
 */
package com.opl.mudra.api.thirdparty.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class CGTMSEDataResponse implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String ssiUnitName;

	
	private String memBankName;
	
	
	private String cgpan;
	
	
	private String gurAmt;
	
	
	private String ssiItPan;

	private String udyogAadhar;

	
	private Boolean status;
	
	private String errorCode;
	private String errorMessage;
	
	private Double colleteralCoverage;
	
	private Double totalColleteralAmount;
	
	private Double assetAqusition;
	
	private Boolean isPurchaseOfEqup;
	
	private Double amountOfColleteral;

	private Double cgtmseCoverageAmount;


	private Double gurAmtCarld;

	private Double gutAmt;

	private Double identityAmount;


	private Boolean isEligibleBorrower;

	private Boolean isNorthEastRegion;

	private Boolean isShareHolding50;

	private Double loanAmount;

	private Double maxCgtmseCoverageAmount;

	private String natureOfEntity;

	private Double percTerms;

	private String statusOfBorrower;
	
	private CGTMSEResponse cgtmseResponse;
	
	Map<Long, Double> productWithColleteralCovereage;
	
	private List<CGSTMSEMappingData> cgtmseMappingData;

	private Double turnOver;




	/**
	 * @return the cgtmseMappingData
	 */
	public List<CGSTMSEMappingData> getCgtmseMappingData() {
		return cgtmseMappingData;
	}


	/**
	 * @param cgtmseMappingData the cgtmseMappingData to set
	 */
	public void setCgtmseMappingData(List<CGSTMSEMappingData> cgtmseMappingData) {
		this.cgtmseMappingData = cgtmseMappingData;
	}


	public Double getAssetAqusition() {
		return assetAqusition;
	}


	public void setAssetAqusition(Double assetAqusition) {
		this.assetAqusition = assetAqusition;
	}


	public Boolean getIsPurchaseOfEqup() {
		return isPurchaseOfEqup;
	}


	public void setIsPurchaseOfEqup(Boolean isPurchaseOfEqup) {
		this.isPurchaseOfEqup = isPurchaseOfEqup;
	}


	/**
	 * @return the totalColleteralAmount
	 */
	public Double getTotalColleteralAmount() {
		return totalColleteralAmount;
	}


	/**
	 * @param totalColleteralAmount the totalColleteralAmount to set
	 */
	public void setTotalColleteralAmount(Double totalColleteralAmount) {
		this.totalColleteralAmount = totalColleteralAmount;
	}



	
	
	


	

	/**
	 * @return the cgtmseResponse
	 */
	public CGTMSEResponse getCgtmseResponse() {
		return cgtmseResponse;
	}


	/**
	 * @param cgtmseResponse the cgtmseResponse to set
	 */
	public void setCgtmseResponse(CGTMSEResponse cgtmseResponse) {
		this.cgtmseResponse = cgtmseResponse;
	}


	/**
	 * @return the amountOfColleteral
	 */
	public Double getAmountOfColleteral() {
		return amountOfColleteral;
	}


	/**
	 * @param amountOfColleteral the amountOfColleteral to set
	 */
	public void setAmountOfColleteral(Double amountOfColleteral) {
		this.amountOfColleteral = amountOfColleteral;
	}


	/**
	 * @return the cgtmseCoverageAmount
	 */
	public Double getCgtmseCoverageAmount() {
		return cgtmseCoverageAmount;
	}


	/**
	 * @param cgtmseCoverageAmount the cgtmseCoverageAmount to set
	 */
	public void setCgtmseCoverageAmount(Double cgtmseCoverageAmount) {
		this.cgtmseCoverageAmount = cgtmseCoverageAmount;
	}


	/**
	 * @return the gurAmtCarld
	 */
	public Double getGurAmtCarld() {
		return gurAmtCarld;
	}


	/**
	 * @param gurAmtCarld the gurAmtCarld to set
	 */
	public void setGurAmtCarld(Double gurAmtCarld) {
		this.gurAmtCarld = gurAmtCarld;
	}


	/**
	 * @return the gutAmt
	 */
	public Double getGutAmt() {
		return gutAmt;
	}


	/**
	 * @param gutAmt the gutAmt to set
	 */
	public void setGutAmt(Double gutAmt) {
		this.gutAmt = gutAmt;
	}


	/**
	 * @return the identityAmount
	 */
	public Double getIdentityAmount() {
		return identityAmount;
	}


	/**
	 * @param identityAmount the identityAmount to set
	 */
	public void setIdentityAmount(Double identityAmount) {
		this.identityAmount = identityAmount;
	}


	/**
	 * @return the isEligibleBorrower
	 */
	public Boolean getIsEligibleBorrower() {
		return isEligibleBorrower;
	}


	/**
	 * @param isEligibleBorrower the isEligibleBorrower to set
	 */
	public void setIsEligibleBorrower(Boolean isEligibleBorrower) {
		this.isEligibleBorrower = isEligibleBorrower;
	}


	/**
	 * @return the isNorthEastRegion
	 */
	public Boolean getIsNorthEastRegion() {
		return isNorthEastRegion;
	}


	/**
	 * @param isNorthEastRegion the isNorthEastRegion to set
	 */
	public void setIsNorthEastRegion(Boolean isNorthEastRegion) {
		this.isNorthEastRegion = isNorthEastRegion;
	}


	/**
	 * @return the isShareHolding50
	 */
	public Boolean getIsShareHolding50() {
		return isShareHolding50;
	}


	/**
	 * @param isShareHolding50 the isShareHolding50 to set
	 */
	public void setIsShareHolding50(Boolean isShareHolding50) {
		this.isShareHolding50 = isShareHolding50;
	}


	/**
	 * @return the loanAmount
	 */
	public Double getLoanAmount() {
		return loanAmount;
	}


	/**
	 * @param loanAmount the loanAmount to set
	 */
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}


	/**
	 * @return the maxCgtmseCoverageAmount
	 */
	public Double getMaxCgtmseCoverageAmount() {
		return maxCgtmseCoverageAmount;
	}


	/**
	 * @param maxCgtmseCoverageAmount the maxCgtmseCoverageAmount to set
	 */
	public void setMaxCgtmseCoverageAmount(Double maxCgtmseCoverageAmount) {
		this.maxCgtmseCoverageAmount = maxCgtmseCoverageAmount;
	}


	/**
	 * @return the natureOfEntity
	 */
	public String getNatureOfEntity() {
		return natureOfEntity;
	}


	/**
	 * @param natureOfEntity the natureOfEntity to set
	 */
	public void setNatureOfEntity(String natureOfEntity) {
		this.natureOfEntity = natureOfEntity;
	}


	/**
	 * @return the percTerms
	 */
	public Double getPercTerms() {
		return percTerms;
	}


	/**
	 * @param percTerms the percTerms to set
	 */
	public void setPercTerms(Double percTerms) {
		this.percTerms = percTerms;
	}


	/**
	 * @return the statusOfBorrower
	 */
	public String getStatusOfBorrower() {
		return statusOfBorrower;
	}


	/**
	 * @param statusOfBorrower the statusOfBorrower to set
	 */
	public void setStatusOfBorrower(String statusOfBorrower) {
		this.statusOfBorrower = statusOfBorrower;
	}



	/**
	 * @return the colleteralCoverage
	 */
	public Double getColleteralCoverage() {
		return colleteralCoverage;
	}


	/**
	 * @param colleteralCoverage the colleteralCoverage to set
	 */
	public void setColleteralCoverage(Double colleteralCoverage) {
		this.colleteralCoverage = colleteralCoverage;
	}


	/**
	 * @param errorCode
	 * @param errorMessage
	 */
	public CGTMSEDataResponse(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}


	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}


	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}


	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


	/**
	 * @param ssiUnitName
	 * @param memBankName
	 * @param cgpan
	 * @param gurAmt
	 * @param ssiItPan
	 * @param udyogAadhar
	 * @param status
	 */
	public CGTMSEDataResponse(String ssiUnitName, String memBankName, String cgpan, String gurAmt, String ssiItPan,
			String udyogAadhar, Boolean status, Double colleteralCoverage) {
		super();
		this.ssiUnitName = ssiUnitName;
		this.memBankName = memBankName;
		this.cgpan = cgpan;
		this.gurAmt = gurAmt;
		this.ssiItPan = ssiItPan;
		this.udyogAadhar = udyogAadhar;
		this.status = status;
		this.colleteralCoverage = colleteralCoverage;
	}
	
	
	public CGTMSEDataResponse(CGTMSEResponse cgtmseResponse, Double colleteralCoverage) {
		super();
		this.cgtmseResponse = cgtmseResponse;
		this.colleteralCoverage = colleteralCoverage;
	}
	
	public CGTMSEDataResponse(CGTMSEResponse cgtmseResponse, Map<Long, Double> productWithColleteralCovereage) {
		super();
		this.cgtmseResponse = cgtmseResponse;
		this.productWithColleteralCovereage = productWithColleteralCovereage;
	}
	
	
	
	public CGTMSEDataResponse(CGTMSEResponse cgtmseResponse, List<CGSTMSEMappingData> cgtmseMappingData) {
		super();
		this.cgtmseResponse = cgtmseResponse;
		this.cgtmseMappingData = cgtmseMappingData;
	}

	/**
	 * @param status
	 */
	public CGTMSEDataResponse(Boolean status) {
		super();
		this.status = status;
	}


	/**
	 * 
	 */
	public CGTMSEDataResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the ssiUnitName
	 */
	public String getSsiUnitName() {
		return ssiUnitName;
	}


	/**
	 * @param ssiUnitName the ssiUnitName to set
	 */
	public void setSsiUnitName(String ssiUnitName) {
		this.ssiUnitName = ssiUnitName;
	}


	/**
	 * @return the memBankName
	 */
	public String getMemBankName() {
		return memBankName;
	}


	/**
	 * @param memBankName the memBankName to set
	 */
	public void setMemBankName(String memBankName) {
		this.memBankName = memBankName;
	}


	/**
	 * @return the cgpan
	 */
	public String getCgpan() {
		return cgpan;
	}


	/**
	 * @param cgpan the cgpan to set
	 */
	public void setCgpan(String cgpan) {
		this.cgpan = cgpan;
	}


	/**
	 * @return the gurAmt
	 */
	public String getGurAmt() {
		return gurAmt;
	}


	/**
	 * @param gurAmt the gurAmt to set
	 */
	public void setGurAmt(String gurAmt) {
		this.gurAmt = gurAmt;
	}


	/**
	 * @return the ssiItPan
	 */
	public String getSsiItPan() {
		return ssiItPan;
	}


	/**
	 * @param ssiItPan the ssiItPan to set
	 */
	public void setSsiItPan(String ssiItPan) {
		this.ssiItPan = ssiItPan;
	}


	/**
	 * @return the udyogAadhar
	 */
	public String getUdyogAadhar() {
		return udyogAadhar;
	}


	/**
	 * @param udyogAadhar the udyogAadhar to set
	 */
	public void setUdyogAadhar(String udyogAadhar) {
		this.udyogAadhar = udyogAadhar;
	}


	/**
	 * @return the status
	 */
	public Boolean getStatus() {
		return status;
	}


	/**
	 * @param status the status to set
	 */
	public void setStatus(Boolean status) {
		this.status = status;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CGTMSEDataResponse [ssiUnitName=" + ssiUnitName + ", memBankName=" + memBankName + ", cgpan=" + cgpan
				+ ", gurAmt=" + gurAmt + ", ssiItPan=" + ssiItPan + ", udyogAadhar=" + udyogAadhar + ", status="
				+ status + ", errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", colleteralCoverage="
				+ colleteralCoverage + "]";
	}



	/**
	 * @param colleteralCoverage
	 */
	public CGTMSEDataResponse(Double colleteralCoverage) {
		super();
		this.colleteralCoverage = colleteralCoverage;
	}


	public Map<Long, Double> getProductWithColleteralCovereage() {
		return productWithColleteralCovereage;
	}


	public void setProductWithColleteralCovereage(Map<Long, Double> productWithColleteralCovereage) {
		this.productWithColleteralCovereage = productWithColleteralCovereage;
	}


	public Double getTurnOver() {
		return turnOver;
	}


	public void setTurnOver(Double turnOver) {
		this.turnOver = turnOver;
	}


}
