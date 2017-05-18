package com.capitaworld.service.loans.domain.fundseeker.retail;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;


/**
 * The persistent class for the fs_retail_primary_home_loan_details database table.
 * 
 */
@Entity
@Table(name="fs_retail_primary_home_loan_details")
public class PrimaryHomeLoanDetail extends LoanApplicationMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "application_id")
	private LoanApplicationMaster applicationId;

	private Double area;

	@Column(name="bunglow_cost")
	private Double bunglowCost;

	@Column(name="completion_time_in_month")
	private Integer completionTimeInMonth;

	@Column(name="completion_time_in_year")
	private Integer completionTimeInYear;

	@Column(name="construction_completion_time_in_month")
	private Integer constructionCompletionTimeInMonth;

	@Column(name="construction_completion_time_in_year")
	private Integer constructionCompletionTimeInYear;

	@Column(name="construction_cost")
	private Double constructionCost;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_of_loan_taken")
	private Date dateOfLoanTaken;

	@Column(name="down_payment")
	private Double downPayment;

	@Column(name="is_construction_completed")
	private Boolean isConstructionCompleted;

	@Column(name="is_loan_taken")
	private Boolean isLoanTaken;

	@Column(name="land_area")
	private Double landArea;

	@Column(name="land_plot_cost")
	private Double landPlotCost;

	@Column(name="other_renovation_type")
	private String otherRenovationType;

	@Column(name="project_city")
	private String projectCity;

	@Column(name="project_name")
	private String projectName;

	@Column(name="property_price")
	private Double propertyPrice;

	@Column(name="property_type")
	private Integer propertyType;

	@Column(name="property_used_type")
	private Integer propertyUsedType;

	@Column(name="renovation_completion_time_in_month")
	private Integer renovationCompletionTimeInMonth;

	@Column(name="renovation_completion_time_in_year")
	private Integer renovationCompletionTimeInYear;

	@Column(name="renovation_cost")
	private Double renovationCost;

	@Column(name="renovation_type")
	private Integer renovationType;

	public LoanApplicationMaster getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
	}

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

	

}