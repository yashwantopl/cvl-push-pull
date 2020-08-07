/**
 * 
 */
package com.opl.mudra.api.thirdparty.model;

import java.util.Date;

/**
 * @author sanket
 *
 */
public class CGSTMSEMappingData {
	

	private static final long serialVersionUID = 1L;

	private Long id;

	private Double amountOfColleteral;

	private Long applicationId;

	private Double cgtmseCoverageAmount;

	private Double colleteralCoverage;

	private Long createdBy;

	private Date createdDate;

	private Double gurAmtCarld;

	private Double gutAmt;

	private Double identityAmount;

	private Boolean isActive;

	private Boolean isEligibleBorrower;

	private Boolean isNorthEastRegion;

	private Boolean isShareHolding50;

	private Double loanAmount;

	private Double maxCgtmseCoverageAmount;

	private Long modifiedBy;

	private Date modifiedDate;

	private String natureOfEntity;

	private Double percTerms;

	private Boolean status;

	private String statusOfBorrower;

	private Double totalColleteralAmount;

	private Double assetAqusition;
	
	private Boolean isPurchaseOfEqup;
	
	private Long productId;
	
	private Boolean isAverageCall;
	
	
	
	
	public Boolean getIsAverageCall() {
		return isAverageCall;
	}

	public void setIsAverageCall(Boolean isAverageCall) {
		this.isAverageCall = isAverageCall;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

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

	public CGSTMSEMappingData() {
		// Do nothing because of X and Y.
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getAmountOfColleteral() {
		return this.amountOfColleteral;
	}

	public void setAmountOfColleteral(Double amountOfColleteral) {
		this.amountOfColleteral = amountOfColleteral;
	}

	public Long getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Double getCgtmseCoverageAmount() {
		return this.cgtmseCoverageAmount;
	}

	public void setCgtmseCoverageAmount(Double cgtmseCoverageAmount) {
		this.cgtmseCoverageAmount = cgtmseCoverageAmount;
	}

	public Double getColleteralCoverage() {
		return this.colleteralCoverage;
	}

	public void setColleteralCoverage(Double colleteralCoverage) {
		this.colleteralCoverage = colleteralCoverage;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Double getGurAmtCarld() {
		return this.gurAmtCarld;
	}

	public void setGurAmtCarld(Double gurAmtCarld) {
		this.gurAmtCarld = gurAmtCarld;
	}

	public Double getGutAmt() {
		return this.gutAmt;
	}

	public void setGutAmt(Double gutAmt) {
		this.gutAmt = gutAmt;
	}

	public Double getIdentityAmount() {
		return this.identityAmount;
	}

	public void setIdentityAmount(Double identityAmount) {
		this.identityAmount = identityAmount;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getIsEligibleBorrower() {
		return this.isEligibleBorrower;
	}

	public void setIsEligibleBorrower(Boolean isEligibleBorrower) {
		this.isEligibleBorrower = isEligibleBorrower;
	}

	public Boolean getIsNorthEastRegion() {
		return this.isNorthEastRegion;
	}

	public void setIsNorthEastRegion(Boolean isNorthEastRegion) {
		this.isNorthEastRegion = isNorthEastRegion;
	}

	public Boolean getIsShareHolding50() {
		return this.isShareHolding50;
	}

	public void setIsShareHolding50(Boolean isShareHolding50) {
		this.isShareHolding50 = isShareHolding50;
	}

	public Double getLoanAmount() {
		return this.loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Double getMaxCgtmseCoverageAmount() {
		return this.maxCgtmseCoverageAmount;
	}

	public void setMaxCgtmseCoverageAmount(Double maxCgtmseCoverageAmount) {
		this.maxCgtmseCoverageAmount = maxCgtmseCoverageAmount;
	}

	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getNatureOfEntity() {
		return this.natureOfEntity;
	}

	public void setNatureOfEntity(String natureOfEntity) {
		this.natureOfEntity = natureOfEntity;
	}

	public Double getPercTerms() {
		return this.percTerms;
	}

	public void setPercTerms(Double percTerms) {
		this.percTerms = percTerms;
	}

	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getStatusOfBorrower() {
		return this.statusOfBorrower;
	}

	public void setStatusOfBorrower(String statusOfBorrower) {
		this.statusOfBorrower = statusOfBorrower;
	}

	public Double getTotalColleteralAmount() {
		return this.totalColleteralAmount;
	}

	public void setTotalColleteralAmount(Double totalColleteralAmount) {
		this.totalColleteralAmount = totalColleteralAmount;
	}



}
