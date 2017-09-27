package com.capitaworld.service.loans.model.retail;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the fs_retail_primary_home_loan_details database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PrimaryHomeLoanDetailRequest extends LoanApplicationRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Double area;

	private Double bunglowCost;

	private Integer completionTimeInMonth;

	private Integer completionTimeInYear;

	private Integer constructionCompletionTimeInMonth;

	private Integer constructionCompletionTimeInYear;

	private Double constructionCost;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date dateOfLoanTaken;

	private Double downPayment;

	private Boolean isConstructionCompleted=false;

	private Boolean isLoanTaken=false;

	private Double landArea;

	private Double landPlotCost;

	private String otherRenovationType;

	private String projectCity;

	private String projectName;

	private Double propertyPrice;

	private Integer propertyType;

	private Integer propertyUsedType;

	private Integer renovationCompletionTimeInMonth;

	private Integer renovationCompletionTimeInYear;

	private Double renovationCost;

	private Integer renovationType;

	private List<Long> negativeList=Collections.emptyList();
	
	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public Double getBunglowCost() {
		return bunglowCost;
	}

	public void setBunglowCost(Double bunglowCost) {
		this.bunglowCost = bunglowCost;
	}

	public Integer getCompletionTimeInMonth() {
		return completionTimeInMonth;
	}

	public void setCompletionTimeInMonth(Integer completionTimeInMonth) {
		this.completionTimeInMonth = completionTimeInMonth;
	}

	public Integer getCompletionTimeInYear() {
		return completionTimeInYear;
	}

	public void setCompletionTimeInYear(Integer completionTimeInYear) {
		this.completionTimeInYear = completionTimeInYear;
	}

	public Integer getConstructionCompletionTimeInMonth() {
		return constructionCompletionTimeInMonth;
	}

	public void setConstructionCompletionTimeInMonth(Integer constructionCompletionTimeInMonth) {
		this.constructionCompletionTimeInMonth = constructionCompletionTimeInMonth;
	}

	public Integer getConstructionCompletionTimeInYear() {
		return constructionCompletionTimeInYear;
	}

	public void setConstructionCompletionTimeInYear(Integer constructionCompletionTimeInYear) {
		this.constructionCompletionTimeInYear = constructionCompletionTimeInYear;
	}

	public Double getConstructionCost() {
		return constructionCost;
	}

	public void setConstructionCost(Double constructionCost) {
		this.constructionCost = constructionCost;
	}

	public Date getDateOfLoanTaken() {
		return dateOfLoanTaken;
	}

	public void setDateOfLoanTaken(Date dateOfLoanTaken) {
		this.dateOfLoanTaken = dateOfLoanTaken;
	}

	public Double getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(Double downPayment) {
		this.downPayment = downPayment;
	}

	public Boolean getIsConstructionCompleted() {
		return isConstructionCompleted;
	}

	public void setIsConstructionCompleted(Boolean isConstructionCompleted) {
		this.isConstructionCompleted = isConstructionCompleted;
	}

	public Boolean getIsLoanTaken() {
		return isLoanTaken;
	}

	public void setIsLoanTaken(Boolean isLoanTaken) {
		this.isLoanTaken = isLoanTaken;
	}

	public Double getLandArea() {
		return landArea;
	}

	public void setLandArea(Double landArea) {
		this.landArea = landArea;
	}

	public Double getLandPlotCost() {
		return landPlotCost;
	}

	public void setLandPlotCost(Double landPlotCost) {
		this.landPlotCost = landPlotCost;
	}

	public String getOtherRenovationType() {
		return otherRenovationType;
	}

	public void setOtherRenovationType(String otherRenovationType) {
		this.otherRenovationType = otherRenovationType;
	}

	public String getProjectCity() {
		return projectCity;
	}

	public void setProjectCity(String projectCity) {
		this.projectCity = projectCity;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Double getPropertyPrice() {
		return propertyPrice;
	}

	public void setPropertyPrice(Double propertyPrice) {
		this.propertyPrice = propertyPrice;
	}

	public Integer getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(Integer propertyType) {
		this.propertyType = propertyType;
	}

	public Integer getPropertyUsedType() {
		return propertyUsedType;
	}

	public void setPropertyUsedType(Integer propertyUsedType) {
		this.propertyUsedType = propertyUsedType;
	}

	public Integer getRenovationCompletionTimeInMonth() {
		return renovationCompletionTimeInMonth;
	}

	public void setRenovationCompletionTimeInMonth(Integer renovationCompletionTimeInMonth) {
		this.renovationCompletionTimeInMonth = renovationCompletionTimeInMonth;
	}

	public Integer getRenovationCompletionTimeInYear() {
		return renovationCompletionTimeInYear;
	}

	public void setRenovationCompletionTimeInYear(Integer renovationCompletionTimeInYear) {
		this.renovationCompletionTimeInYear = renovationCompletionTimeInYear;
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

	public List<Long> getNegativeList() {
		return negativeList;
	}

	public void setNegativeList(List<Long> negativeList) {
		this.negativeList = negativeList;
	}

	

}