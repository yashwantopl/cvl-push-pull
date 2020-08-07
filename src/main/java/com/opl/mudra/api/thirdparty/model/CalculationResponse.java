/**
 * 
 */
package com.opl.mudra.api.thirdparty.model;

/**
 * @author sanket
 *
 */
public class CalculationResponse {
	
	private Double totalColleteralAmt;
	
	private Double colleteralCoverage;
	
	private String natureOfEntity;
	
	private Double identifyAmount;
	
	private String statusOfBrrower;
	
	/**
	 * @param totalColleteralAmt
	 * @param colleteralCoverage
	 * @param natureOfEntity
	 * @param identifyAmount
	 * @param statusOfBrrower
	 * @param isEligibleBorrower
	 * @param isShareHoldingGT50
	 * @param isNorthEastRegion
	 * @param status
	 * @param gurAmt
	 * @param maxCGTMSECovergeAvail
	 * @param loanAmount
	 * @param cgtmseCoverageAmount
	 * @param amountOfColleteral
	 * @param percTerms
	 * @param gurAmtCalctd
	 */
	public CalculationResponse(Double totalColleteralAmt, Double colleteralCoverage, String natureOfEntity,
			Double identifyAmount, String statusOfBrrower, Boolean isEligibleBorrower, Boolean isShareHoldingGT50,
			Boolean isNorthEastRegion, Boolean status, Double gurAmt, Double maxCGTMSECovergeAvail, Double loanAmount,
			Double cgtmseCoverageAmount, Double amountOfColleteral, Double percTerms, Double gurAmtCalctd,Double assetAqusition, Boolean isPurchaseOfEqup) {
		super();
		this.totalColleteralAmt = totalColleteralAmt;
		this.colleteralCoverage = colleteralCoverage;
		this.natureOfEntity = natureOfEntity;
		this.identifyAmount = identifyAmount;
		this.statusOfBrrower = statusOfBrrower;
		this.isEligibleBorrower = isEligibleBorrower;
		this.isShareHoldingGT50 = isShareHoldingGT50;
		this.isNorthEastRegion = isNorthEastRegion;
		this.status = status;
		this.gurAmt = gurAmt;
		this.maxCGTMSECovergeAvail = maxCGTMSECovergeAvail;
		this.loanAmount = loanAmount;
		this.cgtmseCoverageAmount = cgtmseCoverageAmount;
		this.amountOfColleteral = amountOfColleteral;
		this.percTerms = percTerms;
		this.gurAmtCalctd = gurAmtCalctd;
		this.assetAqusition = assetAqusition;
		this.isPurchaseOfEqup = isPurchaseOfEqup;
	}

	private Boolean isEligibleBorrower;
	
	private Boolean isShareHoldingGT50;
	
	private Boolean isNorthEastRegion;
	
	private Boolean status;
	
	private Double gurAmt;
	
	private Double maxCGTMSECovergeAvail;
	
	private Double loanAmount;
	
	private Double cgtmseCoverageAmount;
	
	private Double amountOfColleteral;
	
	private Double percTerms;
	
	private Double gurAmtCalctd;
	
	private Double assetAqusition;
	
	private Boolean isPurchaseOfEqup;
	
	
	
	
	public Boolean getIsPurchaseOfEqup() {
		return isPurchaseOfEqup;
	}

	public void setIsPurchaseOfEqup(Boolean isPurchaseOfEqup) {
		this.isPurchaseOfEqup = isPurchaseOfEqup;
	}

	/**
	 * @return the assetAqusition
	 */
	public Double getAssetAqusition() {
		return assetAqusition;
	}

	/**
	 * @param assetAqusition the assetAqusition to set
	 */
	public void setAssetAqusition(Double assetAqusition) {
		this.assetAqusition = assetAqusition;
	}

	/**
	 * @param totalColleteralAmt
	 * @param colleteralCoverage
	 */
	public CalculationResponse(Double totalColleteralAmt, Double colleteralCoverage) {
		super();
		this.totalColleteralAmt = totalColleteralAmt;
		this.colleteralCoverage = colleteralCoverage;
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
	 * @return the identifyAmount
	 */
	public Double getIdentifyAmount() {
		return identifyAmount;
	}

	/**
	 * @param identifyAmount the identifyAmount to set
	 */
	public void setIdentifyAmount(Double identifyAmount) {
		this.identifyAmount = identifyAmount;
	}

	/**
	 * @return the statusOfBrrower
	 */
	public String getStatusOfBrrower() {
		return statusOfBrrower;
	}

	/**
	 * @param statusOfBrrower the statusOfBrrower to set
	 */
	public void setStatusOfBrrower(String statusOfBrrower) {
		this.statusOfBrrower = statusOfBrrower;
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
	 * @return the isShareHoldingGT50
	 */
	public Boolean getIsShareHoldingGT50() {
		return isShareHoldingGT50;
	}

	/**
	 * @param isShareHoldingGT50 the isShareHoldingGT50 to set
	 */
	public void setIsShareHoldingGT50(Boolean isShareHoldingGT50) {
		this.isShareHoldingGT50 = isShareHoldingGT50;
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

	/**
	 * @return the gurAmt
	 */
	public Double getGurAmt() {
		return gurAmt;
	}

	/**
	 * @param gurAmt the gurAmt to set
	 */
	public void setGurAmt(Double gurAmt) {
		this.gurAmt = gurAmt;
	}

	/**
	 * @return the maxCGTMSECovergeAvail
	 */
	public Double getMaxCGTMSECovergeAvail() {
		return maxCGTMSECovergeAvail;
	}

	/**
	 * @param maxCGTMSECovergeAvail the maxCGTMSECovergeAvail to set
	 */
	public void setMaxCGTMSECovergeAvail(Double maxCGTMSECovergeAvail) {
		this.maxCGTMSECovergeAvail = maxCGTMSECovergeAvail;
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
	 * @return the gurAmtCalctd
	 */
	public Double getGurAmtCalctd() {
		return gurAmtCalctd;
	}

	/**
	 * @param gurAmtCalctd the gurAmtCalctd to set
	 */
	public void setGurAmtCalctd(Double gurAmtCalctd) {
		this.gurAmtCalctd = gurAmtCalctd;
	}

	/**
	 * 
	 */
	public CalculationResponse() {
		super();
	}

	/**
	 * @return the totalColleteralAmt
	 */
	public Double getTotalColleteralAmt() {
		return totalColleteralAmt;
	}

	/**
	 * @param totalColleteralAmt the totalColleteralAmt to set
	 */
	public void setTotalColleteralAmt(Double totalColleteralAmt) {
		this.totalColleteralAmt = totalColleteralAmt;
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
	
	
	

}
