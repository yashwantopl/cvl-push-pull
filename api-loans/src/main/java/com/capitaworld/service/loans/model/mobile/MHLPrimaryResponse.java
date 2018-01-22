package com.capitaworld.service.loans.model.mobile;


public class MHLPrimaryResponse extends MApplicantProfileResponse {

	
	private Double downPayment;

	private Double renovationCost;

	private Integer renovationType;

	private Integer loanType;

	private Integer oldPropMonth;
	
	private Integer oldPropYear;
	
	private Double originalValProp;
	
	private Double regValProp;
	
	private Double marketValProp;
	
	private Integer propertyUsed;
	
	private Double estimatedRentalIncome;

	public Double getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(Double downPayment) {
		this.downPayment = downPayment;
	}

	public Double getRenovationCost() {
		return renovationCost;
	}

	public void setRenovationCost(Double renovationCost) {
		this.renovationCost = renovationCost;
	}

	public Integer getRenovationType() {
		return renovationType;
	}

	public void setRenovationType(Integer renovationType) {
		this.renovationType = renovationType;
	}

	public Integer getLoanType() {
		return loanType;
	}

	public void setLoanType(Integer loanType) {
		this.loanType = loanType;
	}

	public Integer getOldPropMonth() {
		return oldPropMonth;
	}

	public void setOldPropMonth(Integer oldPropMonth) {
		this.oldPropMonth = oldPropMonth;
	}

	public Integer getOldPropYear() {
		return oldPropYear;
	}

	public void setOldPropYear(Integer oldPropYear) {
		this.oldPropYear = oldPropYear;
	}

	public Double getOriginalValProp() {
		return originalValProp;
	}

	public void setOriginalValProp(Double originalValProp) {
		this.originalValProp = originalValProp;
	}

	public Double getRegValProp() {
		return regValProp;
	}

	public void setRegValProp(Double regValProp) {
		this.regValProp = regValProp;
	}

	public Double getMarketValProp() {
		return marketValProp;
	}

	public void setMarketValProp(Double marketValProp) {
		this.marketValProp = marketValProp;
	}

	public Integer getPropertyUsed() {
		return propertyUsed;
	}

	public void setPropertyUsed(Integer propertyUsed) {
		this.propertyUsed = propertyUsed;
	}

	public Double getEstimatedRentalIncome() {
		return estimatedRentalIncome;
	}

	public void setEstimatedRentalIncome(Double estimatedRentalIncome) {
		this.estimatedRentalIncome = estimatedRentalIncome;
	}
	
	
	
	
}
