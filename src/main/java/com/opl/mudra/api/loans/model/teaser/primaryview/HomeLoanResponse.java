package com.opl.mudra.api.loans.model.teaser.primaryview;

import java.io.Serializable;
import java.util.List;

import com.opl.mudra.api.loans.model.AddressResponse;

/**
 * @author kushal
 *
 */
public class HomeLoanResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String propertyType; 
	
	private String propertyUsedType;
	private String propertyUsedTypeId;
	private String constructionCompleted;
	private String constructionCompletionTimeInMonth;
	private String constructionCompletionTimeInYear;
	private String projectName;
	private String projectCity;
	private String area;
	private String propertyPrice;
	
	private String bunglowCost;
	private String constructionCost;
	private String completionTimeInMonth;
	private String completionTimeInYear;

	private String renovationType;
	private String renovationTypeOther;
	private String renovationCost;
	private String renovationCompletionTimeInMonth;
	private String renovationCompletionTimeInYear;
	private String loanTaken;
	private String dateOfLoanTaken;
	
	private String landPlotCost;
	private String landArea;
	
	private String downPayment;
	private String currency;
	private String tenure;
	
	private AddressResponse permanentAddress;
    private AddressResponse officeAddress;
    private String dateOfProposal;
    private String loanType;
    private String loanAmount;
    
    private String propertyUse;
	private String rentalIncome;

	private String oldPropMonth;
	private String oldPropYear;
	private String originalValProp;
	private String marketValProp;
	private String regValProp;

    private List<Object> profileImage;
    
	public List<Object> getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(List<Object> profileImage) {
		this.profileImage = profileImage;
	}
	
	public String getPropertyUsedTypeId() {
		return propertyUsedTypeId;
	}
	public void setPropertyUsedTypeId(String propertyUsedTypeId) {
		this.propertyUsedTypeId = propertyUsedTypeId;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public AddressResponse getPermanentAddress() {
		return permanentAddress;
	}
	public void setPermanentAddress(AddressResponse permanentAddress) {
		this.permanentAddress = permanentAddress;
	}
	public AddressResponse getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(AddressResponse officeAddress) {
		this.officeAddress = officeAddress;
	}
	public String getDateOfProposal() {
		return dateOfProposal;
	}
	public void setDateOfProposal(String dateOfProposal) {
		this.dateOfProposal = dateOfProposal;
	}
	public String getPropertyType() {
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public String getPropertyUsedType() {
		return propertyUsedType;
	}
	public void setPropertyUsedType(String propertyUsedType) {
		this.propertyUsedType = propertyUsedType;
	}
	public String getConstructionCompleted() {
		return constructionCompleted;
	}
	public void setConstructionCompleted(String constructionCompleted) {
		this.constructionCompleted = constructionCompleted;
	}
	public String getConstructionCompletionTimeInMonth() {
		return constructionCompletionTimeInMonth;
	}
	public void setConstructionCompletionTimeInMonth(String constructionCompletionTimeInMonth) {
		this.constructionCompletionTimeInMonth = constructionCompletionTimeInMonth;
	}
	public String getConstructionCompletionTimeInYear() {
		return constructionCompletionTimeInYear;
	}
	public void setConstructionCompletionTimeInYear(String constructionCompletionTimeInYear) {
		this.constructionCompletionTimeInYear = constructionCompletionTimeInYear;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectCity() {
		return projectCity;
	}
	public void setProjectCity(String projectCity) {
		this.projectCity = projectCity;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getPropertyPrice() {
		return propertyPrice;
	}
	public void setPropertyPrice(String propertyPrice) {
		this.propertyPrice = propertyPrice;
	}
	public String getBunglowCost() {
		return bunglowCost;
	}
	public void setBunglowCost(String bunglowCost) {
		this.bunglowCost = bunglowCost;
	}
	public String getConstructionCost() {
		return constructionCost;
	}
	public void setConstructionCost(String constructionCost) {
		this.constructionCost = constructionCost;
	}
	public String getCompletionTimeInMonth() {
		return completionTimeInMonth;
	}
	public void setCompletionTimeInMonth(String completionTimeInMonth) {
		this.completionTimeInMonth = completionTimeInMonth;
	}
	public String getCompletionTimeInYear() {
		return completionTimeInYear;
	}
	public void setCompletionTimeInYear(String completionTimeInYear) {
		this.completionTimeInYear = completionTimeInYear;
	}
	public String getRenovationType() {
		return renovationType;
	}
	public void setRenovationType(String renovationType) {
		this.renovationType = renovationType;
	}
	public String getRenovationTypeOther() {
		return renovationTypeOther;
	}
	public void setRenovationTypeOther(String renovationTypeOther) {
		this.renovationTypeOther = renovationTypeOther;
	}
	public String getRenovationCost() {
		return renovationCost;
	}
	public void setRenovationCost(String renovationCost) {
		this.renovationCost = renovationCost;
	}
	public String getRenovationCompletionTimeInMonth() {
		return renovationCompletionTimeInMonth;
	}
	public void setRenovationCompletionTimeInMonth(String renovationCompletionTimeInMonth) {
		this.renovationCompletionTimeInMonth = renovationCompletionTimeInMonth;
	}
	public String getRenovationCompletionTimeInYear() {
		return renovationCompletionTimeInYear;
	}
	public void setRenovationCompletionTimeInYear(String renovationCompletionTimeInYear) {
		this.renovationCompletionTimeInYear = renovationCompletionTimeInYear;
	}
	public String getLoanTaken() {
		return loanTaken;
	}
	public void setLoanTaken(String loanTaken) {
		this.loanTaken = loanTaken;
	}
	public String getDateOfLoanTaken() {
		return dateOfLoanTaken;
	}
	public void setDateOfLoanTaken(String dateOfLoanTaken) {
		this.dateOfLoanTaken = dateOfLoanTaken;
	}
	public String getLandPlotCost() {
		return landPlotCost;
	}
	public void setLandPlotCost(String landPlotCost) {
		this.landPlotCost = landPlotCost;
	}
	public String getLandArea() {
		return landArea;
	}
	public void setLandArea(String landArea) {
		this.landArea = landArea;
	}
	public String getDownPayment() {
		return downPayment;
	}
	public void setDownPayment(String downPayment) {
		this.downPayment = downPayment;
	}
	public String getTenure() {
		return tenure;
	}
	public void setTenure(String tenure) {
		this.tenure = tenure;
	}
	public String getPropertyUse() {
		return propertyUse;
	}
	public void setPropertyUse(String propertyUse) {
		this.propertyUse = propertyUse;
	}
	public String getRentalIncome() {
		return rentalIncome;
	}
	public void setRentalIncome(String rentalIncome) {
		this.rentalIncome = rentalIncome;
	}

	public String getOldPropMonth() {
		return oldPropMonth;
	}

	public void setOldPropMonth(String oldPropMonth) {
		this.oldPropMonth = oldPropMonth;
	}

	public String getOldPropYear() {
		return oldPropYear;
	}

	public void setOldPropYear(String oldPropYear) {
		this.oldPropYear = oldPropYear;
	}

	public String getOriginalValProp() {
		return originalValProp;
	}

	public void setOriginalValProp(String originalValProp) {
		this.originalValProp = originalValProp;
	}

	public String getMarketValProp() {
		return marketValProp;
	}

	public void setMarketValProp(String marketValProp) {
		this.marketValProp = marketValProp;
	}

	public String getRegValProp() {
		return regValProp;
	}

	public void setRegValProp(String regValProp) {
		this.regValProp = regValProp;
	}
}
