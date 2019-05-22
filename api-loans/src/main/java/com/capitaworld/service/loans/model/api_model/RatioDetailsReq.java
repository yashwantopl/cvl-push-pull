package com.capitaworld.service.loans.model.api_model;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author harshit Date : 22-Jun-2018
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RatioDetailsReq implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String year;

	private Long applicationId;

	private String currency;

	private Double ebitda;

	private Double patm;

	private Double roce;

	private Double assetTurnover;

	private Double inventoryTurnover;

	private Double debtorsTurnover;

	private Double creditorsTurnover;

	private Double salesAndWorkingCapital;

	private Double netSalesGrowth;

	private Double patGrowth;

	private Double adjustedTotalDebtAndEquity;

	private Double growthInDebtAndEquity;

	private Double currentRatio;

	private Double quickRatio;

	private Double cashInterestCover;

	private Double debtEbitda;

	private Double freeReservesEquity;

	private Double cfoMargin;

	private Double growthInCfoMargin;

	private Date createdDate;

	private Boolean isActive;

	private Double currentRatioAsPerCma;

	public RatioDetailsReq() {
		super();
		this.ebitda = 0.0;
		this.patm = 0.0;
		this.roce = 0.0;
		this.assetTurnover = 0.0;
		this.inventoryTurnover = 0.0;
		this.debtorsTurnover = 0.0;
		this.creditorsTurnover = 0.0;
		this.salesAndWorkingCapital = 0.0;
		this.netSalesGrowth = 0.0;
		this.patGrowth = 0.0;
		this.adjustedTotalDebtAndEquity = 0.0;
		this.growthInDebtAndEquity = 0.0;
		this.currentRatio = 0.0;
		this.quickRatio = 0.0;
		this.cashInterestCover = 0.0;
		this.debtEbitda = 0.0;
		this.freeReservesEquity = 0.0;
		this.cfoMargin = 0.0;
		this.growthInCfoMargin = 0.0;
	}

	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId the applicationId to set
	 */
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getEbitda() {
		return ebitda;
	}

	/**
	 * @param ebitda the ebitda to set
	 */
	public void setEbitda(Double ebitda) {
		this.ebitda = ebitda;
	}

	public Double getPatm() {
		return patm;
	}

	/**
	 * @param patm the patm to set
	 */
	public void setPatm(Double patm) {
		this.patm = patm;
	}

	public Double getRoce() {
		return roce;
	}

	/**
	 * @param roce the roce to set
	 */
	public void setRoce(Double roce) {
		this.roce = roce;
	}

	public Double getAssetTurnover() {
		return assetTurnover;
	}

	/**
	 * @param assetTurnover the assetTurnover to set
	 */
	public void setAssetTurnover(Double assetTurnover) {
		this.assetTurnover = assetTurnover;
	}

	public Double getInventoryTurnover() {
		return inventoryTurnover;
	}

	/**
	 * @param inventoryTurnover the inventoryTurnover to set
	 */
	public void setInventoryTurnover(Double inventoryTurnover) {
		this.inventoryTurnover = inventoryTurnover;
	}

	public Double getDebtorsTurnover() {
		return debtorsTurnover;
	}

	/**
	 * @param debtorsTurnover the debtorsTurnover to set
	 */
	public void setDebtorsTurnover(Double debtorsTurnover) {
		this.debtorsTurnover = debtorsTurnover;
	}

	public Double getCreditorsTurnover() {
		return creditorsTurnover;
	}

	/**
	 * @param creditorsTurnover the creditorsTurnover to set
	 */
	public void setCreditorsTurnover(Double creditorsTurnover) {
		this.creditorsTurnover = creditorsTurnover;
	}

	public Double getSalesAndWorkingCapital() {
		return salesAndWorkingCapital;
	}

	/**
	 * @param salesAndWorkingCapital the salesAndWorkingCapital to set
	 */
	public void setSalesAndWorkingCapital(Double salesAndWorkingCapital) {
		this.salesAndWorkingCapital = salesAndWorkingCapital;
	}

	public Double getNetSalesGrowth() {
		return netSalesGrowth;
	}

	/**
	 * @param netSalesGrowth the netSalesGrowth to set
	 */
	public void setNetSalesGrowth(Double netSalesGrowth) {
		this.netSalesGrowth = netSalesGrowth;
	}

	public Double getPatGrowth() {
		return patGrowth;
	}

	/**
	 * @param patGrowth the patGrowth to set
	 */
	public void setPatGrowth(Double patGrowth) {
		this.patGrowth = patGrowth;
	}

	public Double getAdjustedTotalDebtAndEquity() {
		return adjustedTotalDebtAndEquity;
	}

	/**
	 * @param adjustedTotalDebtAndEquity the adjustedTotalDebtAndEquity to set
	 */
	public void setAdjustedTotalDebtAndEquity(Double adjustedTotalDebtAndEquity) {
		this.adjustedTotalDebtAndEquity = adjustedTotalDebtAndEquity;
	}

	public Double getGrowthInDebtAndEquity() {
		return growthInDebtAndEquity;
	}

	/**
	 * @param growthInDebtAndEquity the growthInDebtAndEquity to set
	 */
	public void setGrowthInDebtAndEquity(Double growthInDebtAndEquity) {
		this.growthInDebtAndEquity = growthInDebtAndEquity;
	}

	public Double getCurrentRatio() {
		return currentRatio;
	}

	/**
	 * @param currentRatio the currentRatio to set
	 */
	public void setCurrentRatio(Double currentRatio) {
		this.currentRatio = currentRatio;
	}

	public Double getQuickRatio() {
		return quickRatio;
	}

	/**
	 * @param quickRatio the quickRatio to set
	 */
	public void setQuickRatio(Double quickRatio) {
		this.quickRatio = quickRatio;
	}

	public Double getCashInterestCover() {
		return cashInterestCover;
	}

	/**
	 * @param cashInterestCover the cashInterestCover to set
	 */
	public void setCashInterestCover(Double cashInterestCover) {
		this.cashInterestCover = cashInterestCover;
	}

	public Double getDebtEbitda() {
		return debtEbitda;
	}

	/**
	 * @param debtEbitda the debtEbitda to set
	 */
	public void setDebtEbitda(Double debtEbitda) {
		this.debtEbitda = debtEbitda;
	}

	public Double getFreeReservesEquity() {
		return freeReservesEquity;
	}

	/**
	 * @param freeReservesEquity the freeReservesEquity to set
	 */
	public void setFreeReservesEquity(Double freeReservesEquity) {
		this.freeReservesEquity = freeReservesEquity;
	}

	public Double getCfoMargin() {
		return cfoMargin;
	}

	/**
	 * @param cfoMargin the cfoMargin to set
	 */
	public void setCfoMargin(Double cfoMargin) {
		this.cfoMargin = cfoMargin;
	}

	public Double getGrowthInCfoMargin() {
		return growthInCfoMargin;
	}

	/**
	 * @param growthInCfoMargin the growthInCfoMargin to set
	 */
	public void setGrowthInCfoMargin(Double growthInCfoMargin) {
		this.growthInCfoMargin = growthInCfoMargin;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Double getCurrentRatioAsPerCma() {
		return currentRatioAsPerCma;
	}

	public void setCurrentRatioAsPerCma(Double currentRatioAsPerCma) {
		this.currentRatioAsPerCma = currentRatioAsPerCma;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RatioDetailsReq [id=" + id + ", year=" + year + ", applicationId=" + applicationId + ", currency="
				+ currency + ", ebitda=" + ebitda + ", patm=" + patm + ", roce=" + roce + ", assetTurnover="
				+ assetTurnover + ", inventoryTurnover=" + inventoryTurnover + ", debtorsTurnover=" + debtorsTurnover
				+ ", creditorsTurnover=" + creditorsTurnover + ", salesAndWorkingCapital=" + salesAndWorkingCapital
				+ ", netSalesGrowth=" + netSalesGrowth + ", patGrowth=" + patGrowth + ", adjustedTotalDebtAndEquity="
				+ adjustedTotalDebtAndEquity + ", growthInDebtAndEquity=" + growthInDebtAndEquity + ", currentRatio="
				+ currentRatio + ", quickRatio=" + quickRatio + ", cashInterestCover=" + cashInterestCover
				+ ", debtEbitda=" + debtEbitda + ", freeReservesEquity=" + freeReservesEquity + ", cfoMargin="
				+ cfoMargin + ", growthInCfoMargin=" + growthInCfoMargin + ", createdDate=" + createdDate
				+ ", isActive=" + isActive + "]";
	}

}
