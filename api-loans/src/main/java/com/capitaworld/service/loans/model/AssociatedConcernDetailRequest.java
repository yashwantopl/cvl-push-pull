package com.capitaworld.service.loans.model;
/**
 * @author Sanket
 *
 */

import java.io.Serializable;

public class AssociatedConcernDetailRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String briefDescription;

	private Integer currentYear;

	private Double investedAmount;

	private Boolean isActive =true;

	private String name;

	private String natureActivity;

	private String natureAssociation;

	private Double profitPastOneYear;

	private Double profitPastThreeYear;

	private Double profitPastTwoYear;

	private Double turnOverFirstYear;

	private Double turnOverSecondYear;

	private Double turnOverThirdYear;
	
	private String nameOfDirector;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBriefDescription() {
		return briefDescription;
	}

	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}


	public Integer getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(Integer currentYear) {
		this.currentYear = currentYear;
	}

	public Double getInvestedAmount() {
		return investedAmount;
	}

	public void setInvestedAmount(Double investedAmount) {
		this.investedAmount = investedAmount;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNatureActivity() {
		return natureActivity;
	}

	public void setNatureActivity(String natureActivity) {
		this.natureActivity = natureActivity;
	}

	public String getNatureAssociation() {
		return natureAssociation;
	}

	public void setNatureAssociation(String natureAssociation) {
		this.natureAssociation = natureAssociation;
	}

	public Double getProfitPastOneYear() {
		return profitPastOneYear;
	}

	public void setProfitPastOneYear(Double profitPastOneYear) {
		this.profitPastOneYear = profitPastOneYear;
	}

	public Double getProfitPastThreeYear() {
		return profitPastThreeYear;
	}

	public void setProfitPastThreeYear(Double profitPastThreeYear) {
		this.profitPastThreeYear = profitPastThreeYear;
	}

	public Double getProfitPastTwoYear() {
		return profitPastTwoYear;
	}

	public void setProfitPastTwoYear(Double profitPastTwoYear) {
		this.profitPastTwoYear = profitPastTwoYear;
	}

	public Double getTurnOverFirstYear() {
		return turnOverFirstYear;
	}

	public void setTurnOverFirstYear(Double turnOverFirstYear) {
		this.turnOverFirstYear = turnOverFirstYear;
	}

	public Double getTurnOverSecondYear() {
		return turnOverSecondYear;
	}

	public void setTurnOverSecondYear(Double turnOverSecondYear) {
		this.turnOverSecondYear = turnOverSecondYear;
	}

	public Double getTurnOverThirdYear() {
		return turnOverThirdYear;
	}

	public void setTurnOverThirdYear(Double turnOverThirdYear) {
		this.turnOverThirdYear = turnOverThirdYear;
	}

	public String getNameOfDirector() {
		return nameOfDirector;
	}

	public void setNameOfDirector(String nameOfDirector) {
		this.nameOfDirector = nameOfDirector;
	}
	
	
	
}
