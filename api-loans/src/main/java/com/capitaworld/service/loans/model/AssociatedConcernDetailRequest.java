package com.capitaworld.service.loans.model;
/**
 * @author Sanket
 *
 */

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	private String pan;

	private String natureActivity;

	private String natureAssociation;

	private Double profitPastOneYear;

	private Double profitPastThreeYear;

	private Double profitPastTwoYear;

	private Double turnOverFirstYear;

	private Double turnOverSecondYear;

	private Double turnOverThirdYear;
	
	private String profitPastOneYearString;

	private String profitPastThreeYearString;

	private String profitPastTwoYearString;

	private String turnOverFirstYearString;

	private String turnOverSecondYearString;

	private String turnOverThirdYearString;
	
	private String nameOfDirector;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date dateOfIncorporation;
	
	private String financialInstitutionName;
	
	private Double limitAvailed;

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
	
	public String getProfitPastOneYearString() {
		return profitPastOneYearString;
	}

	public void setProfitPastOneYearString(String profitPastOneYearString) {
		this.profitPastOneYearString = profitPastOneYearString;
	}

	public String getProfitPastThreeYearString() {
		return profitPastThreeYearString;
	}

	public void setProfitPastThreeYearString(String profitPastThreeYearString) {
		this.profitPastThreeYearString = profitPastThreeYearString;
	}

	public String getProfitPastTwoYearString() {
		return profitPastTwoYearString;
	}

	public void setProfitPastTwoYearString(String profitPastTwoYearString) {
		this.profitPastTwoYearString = profitPastTwoYearString;
	}

	public String getTurnOverFirstYearString() {
		return turnOverFirstYearString;
	}

	public void setTurnOverFirstYearString(String turnOverFirstYearString) {
		this.turnOverFirstYearString = turnOverFirstYearString;
	}

	public String getTurnOverSecondYearString() {
		return turnOverSecondYearString;
	}

	public void setTurnOverSecondYearString(String turnOverSecondYearString) {
		this.turnOverSecondYearString = turnOverSecondYearString;
	}

	public String getTurnOverThirdYearString() {
		return turnOverThirdYearString;
	}

	public void setTurnOverThirdYearString(String turnOverThirdYearString) {
		this.turnOverThirdYearString = turnOverThirdYearString;
	}

	public Date getDateOfIncorporation() {
		return dateOfIncorporation;
	}

	public void setDateOfIncorporation(Date dateOfIncorporation) {
		this.dateOfIncorporation = dateOfIncorporation;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getFinancialInstitutionName() {
		return financialInstitutionName;
	}

	public void setFinancialInstitutionName(String financialInstitutionName) {
		this.financialInstitutionName = financialInstitutionName;
	}

	public Double getLimitAvailed() {
		return limitAvailed;
	}

	public void setLimitAvailed(Double limitAvailed) {
		this.limitAvailed = limitAvailed;
	}

	public static void printFields(Object obj) throws Exception {
        Field[] fields = AssociatedConcernDetailRequest.class.getDeclaredFields();
        
        for(Field field : fields) {
            Object value = field.get(obj);
            if(value instanceof String){
             String a = value.toString().replaceAll("&", "&amp;");
             value = a;
             field.set(obj, value);
            }
        }
    }
	
	
}
