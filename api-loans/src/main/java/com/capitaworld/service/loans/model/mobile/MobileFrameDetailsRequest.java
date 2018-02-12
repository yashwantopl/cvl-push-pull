package com.capitaworld.service.loans.model.mobile;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MobileFrameDetailsRequest  implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String categoryCode;

	private String name;

	private Double tenure;
	
	private Integer employmentType;
	
	private Integer propertyType;
	
	private Double income;
	
	private Double obligation;
	
	private Integer totalExperienceMonth;

	private Integer totalExperienceYear;
	
	private Integer currentJobMonth;

	private Integer currentJobYear;
	
	private String propertyLocation;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date dateOfBirth;
	
	private Double marketValue;
	
	

	
	//NEW PARAMETER
	
	private Long permanentPincode;
	
	private Integer occupationId;
	
	private Double monthlyIncome;
	
	private Double annualTurnover;
	
	private Double monthlyLoanObligation;

	private Double bonusPerAnnum;
	
	private Double incentivePerAnnum;
	
	private Double otherIncome;
	
	private Double otherInvestment;
	
	private Double taxPaidLastYear;
	
	private Integer employedWithId;

	//HOME LOAN
	
	private Integer loanType;
	
	private Double downPayment;
	
	private Double bunglowCost;
	
	private Double constructionCost;
	
	private Integer renovationType;
	
	private Boolean isLoanTaken;
	
	private Double marketValProp;
	
	private Double propertyPrice;
	
	
	
	public String getCategoryCode() {
		return categoryCode;
	}

	public String getName() {
		return name;
	}

	public Double getTenure() {
		return tenure;
	}

	public Integer getEmploymentType() {
		return employmentType;
	}

	public Integer getPropertyType() {
		return propertyType;
	}

	public Double getIncome() {
		return income;
	}

	public Double getObligation() {
		return obligation;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public Double getMarketValue() {
		return marketValue;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public void setName(String name) {
		this.name = name;
	}



	public void setTenure(Double tenure) {
		this.tenure = tenure;
	}

	public void setEmploymentType(Integer employmentType) {
		this.employmentType = employmentType;
	}

	public void setPropertyType(Integer propertyType) {
		this.propertyType = propertyType;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public void setObligation(Double obligation) {
		this.obligation = obligation;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setMarketValue(Double marketValue) {
		this.marketValue = marketValue;
	}

	public Integer getTotalExperienceMonth() {
		return totalExperienceMonth;
	}

	public Integer getTotalExperienceYear() {
		return totalExperienceYear;
	}

	public void setTotalExperienceMonth(Integer totalExperienceMonth) {
		this.totalExperienceMonth = totalExperienceMonth;
	}

	public void setTotalExperienceYear(Integer totalExperienceYear) {
		this.totalExperienceYear = totalExperienceYear;
	}

	public Integer getCurrentJobMonth() {
		return currentJobMonth;
	}

	public Integer getCurrentJobYear() {
		return currentJobYear;
	}

	public void setCurrentJobMonth(Integer currentJobMonth) {
		this.currentJobMonth = currentJobMonth;
	}

	public void setCurrentJobYear(Integer currentJobYear) {
		this.currentJobYear = currentJobYear;
	}

	public String getPropertyLocation() {
		return propertyLocation;
	}

	public void setPropertyLocation(String propertyLocation) {
		this.propertyLocation = propertyLocation;
	}

	public Integer getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(Integer occupationId) {
		this.occupationId = occupationId;
	}

	public Double getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(Double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public Double getAnnualTurnover() {
		return annualTurnover;
	}

	public void setAnnualTurnover(Double annualTurnover) {
		this.annualTurnover = annualTurnover;
	}

	public Double getMonthlyLoanObligation() {
		return monthlyLoanObligation;
	}

	public void setMonthlyLoanObligation(Double monthlyLoanObligation) {
		this.monthlyLoanObligation = monthlyLoanObligation;
	}

	public Integer getLoanType() {
		return loanType;
	}

	public void setLoanType(Integer loanType) {
		this.loanType = loanType;
	}

	public Double getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(Double downPayment) {
		this.downPayment = downPayment;
	}

	public Double getBunglowCost() {
		return bunglowCost;
	}

	public void setBunglowCost(Double bunglowCost) {
		this.bunglowCost = bunglowCost;
	}

	public Double getConstructionCost() {
		return constructionCost;
	}

	public void setConstructionCost(Double constructionCost) {
		this.constructionCost = constructionCost;
	}

	public Integer getRenovationType() {
		return renovationType;
	}

	public void setRenovationType(Integer renovationType) {
		this.renovationType = renovationType;
	}

	public Boolean getIsLoanTaken() {
		return isLoanTaken;
	}

	public void setIsLoanTaken(Boolean isLoanTaken) {
		this.isLoanTaken = isLoanTaken;
	}

	public Double getMarketValProp() {
		return marketValProp;
	}

	public void setMarketValProp(Double marketValProp) {
		this.marketValProp = marketValProp;
	}

	public Double getBonusPerAnnum() {
		return bonusPerAnnum;
	}

	public void setBonusPerAnnum(Double bonusPerAnnum) {
		this.bonusPerAnnum = bonusPerAnnum;
	}

	public Double getIncentivePerAnnum() {
		return incentivePerAnnum;
	}

	public void setIncentivePerAnnum(Double incentivePerAnnum) {
		this.incentivePerAnnum = incentivePerAnnum;
	}

	public Double getOtherIncome() {
		return otherIncome;
	}

	public void setOtherIncome(Double otherIncome) {
		this.otherIncome = otherIncome;
	}

	public Double getOtherInvestment() {
		return otherInvestment;
	}

	public void setOtherInvestment(Double otherInvestment) {
		this.otherInvestment = otherInvestment;
	}

	public Double getTaxPaidLastYear() {
		return taxPaidLastYear;
	}

	public void setTaxPaidLastYear(Double taxPaidLastYear) {
		this.taxPaidLastYear = taxPaidLastYear;
	}

	public Double getPropertyPrice() {
		return propertyPrice;
	}

	public void setPropertyPrice(Double propertyPrice) {
		this.propertyPrice = propertyPrice;
	}

	public Long getPermanentPincode() {
		return permanentPincode;
	}

	public void setPermanentPincode(Long permanentPincode) {
		this.permanentPincode = permanentPincode;
	}

	public Integer getEmployedWithId() {
		return employedWithId;
	}

	public void setEmployedWithId(Integer employedWithId) {
		this.employedWithId = employedWithId;
	}
	
	

}
