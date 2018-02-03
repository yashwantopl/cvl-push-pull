package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import javax.persistence.*;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

import java.util.Date;


/**
 * The persistent class for the fs_corporate_associated_concern_details database table.
 * 
 */
@Entity
@Table(name="fs_corporate_associated_concern_details")
public class AssociatedConcernDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="application_id")
	private LoanApplicationMaster applicationId;

	@Lob
	@Column(name="brief_description")
	private String briefDescription;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	private Integer currentYear;

	@Column(name="invested_amount")
	private Double investedAmount;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	private String name;

	@Column(name="nature_activity")
	private String natureActivity;

	@Column(name="nature_association")
	private String natureAssociation;

	@Column(name="profit_past_one_year")
	private Double profitPastOneYear;

	@Column(name="profit_past_three_year")
	private Double profitPastThreeYear;

	@Column(name="profit_past_two_year")
	private Double profitPastTwoYear;

	@Column(name="turn_over_first_year")
	private Double turnOverFirstYear;

	@Column(name="turn_over_second_year")
	private Double turnOverSecondYear;

	@Column(name="turn_over_third_year")
	private Double turnOverThirdYear;
	
	@Column(name="name_of_director")
	private String nameOfDirector;

	public AssociatedConcernDetail() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LoanApplicationMaster getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
	}

	public String getBriefDescription() {
		return this.briefDescription;
	}

	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
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

	public Integer getCurrentYear() {
		return this.currentYear;
	}

	public void setCurrentYear(Integer currentYear) {
		this.currentYear = currentYear;
	}

	public Double getInvestedAmount() {
		return this.investedAmount;
	}

	public void setInvestedAmount(Double investedAmount) {
		this.investedAmount = investedAmount;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNatureActivity() {
		return this.natureActivity;
	}

	public void setNatureActivity(String natureActivity) {
		this.natureActivity = natureActivity;
	}

	public String getNatureAssociation() {
		return this.natureAssociation;
	}

	public void setNatureAssociation(String natureAssociation) {
		this.natureAssociation = natureAssociation;
	}

	public Double getProfitPastOneYear() {
		return this.profitPastOneYear;
	}

	public void setProfitPastOneYear(Double profitPastOneYear) {
		this.profitPastOneYear = profitPastOneYear;
	}

	public Double getProfitPastThreeYear() {
		return this.profitPastThreeYear;
	}

	public void setProfitPastThreeYear(Double profitPastThreeYear) {
		this.profitPastThreeYear = profitPastThreeYear;
	}

	public Double getProfitPastTwoYear() {
		return this.profitPastTwoYear;
	}

	public void setProfitPastTwoYear(Double profitPastTwoYear) {
		this.profitPastTwoYear = profitPastTwoYear;
	}

	public Double getTurnOverFirstYear() {
		return this.turnOverFirstYear;
	}

	public void setTurnOverFirstYear(Double turnOverFirstYear) {
		this.turnOverFirstYear = turnOverFirstYear;
	}

	public Double getTurnOverSecondYear() {
		return this.turnOverSecondYear;
	}

	public void setTurnOverSecondYear(Double turnOverSecondYear) {
		this.turnOverSecondYear = turnOverSecondYear;
	}

	public Double getTurnOverThirdYear() {
		return this.turnOverThirdYear;
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