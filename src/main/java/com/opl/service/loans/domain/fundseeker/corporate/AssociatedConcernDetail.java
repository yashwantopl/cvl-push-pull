package com.opl.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.opl.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.opl.service.loans.domain.fundseeker.LoanApplicationMaster;


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


	@ManyToOne
	@JoinColumn(name="proposal_mapping_id")
	private ApplicationProposalMapping applicationProposalMapping;

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

	private String pan;
	
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

	@Column(name="date_of_incorporation")
	private Date dateOfIncorporation;
	
	@Column(name = "financial_institution_name")
	private String financialInstitutionName;
	
	@Column(name = "limit_availed")
	private Double limitAvailed;
	
	@Column(name = "extent_of_interest")
	private String extentOfInterest;
	
	@Column(name = "premise_number")
	private String premiseNumber;
	
	@Column(name = "land_mark")
	private String landMark;
	
	@Column(name = "country_id")
	private Integer countryId;
	
	@Column(name = "city_id")
	private Long cityId;
	
	@Column(name = "state_id")
	private Integer stateId;
	
	@Column(name = "sub_district")
	private String subDistrict;
	
	@Column(name = "district")
	private String district;
	
	@Column(name = "district_mapping_id")
	private Long districtMappingId; 
	
	@Column(name = "pincode")
	private Long pincode;
	

	public AssociatedConcernDetail() {
		// Do nothing because of X and Y.
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

	public ApplicationProposalMapping getApplicationProposalMapping() {
		return applicationProposalMapping;
	}

	public void setApplicationProposalMapping(ApplicationProposalMapping applicationProposalMapping) {
		this.applicationProposalMapping = applicationProposalMapping;
	}

	public String getExtentOfInterest() {
		return extentOfInterest;
	}

	public void setExtentOfInterest(String extentOfInterest) {
		this.extentOfInterest = extentOfInterest;
	}

	public String getPremiseNumber() {
		return premiseNumber;
	}

	public void setPremiseNumber(String premiseNumber) {
		this.premiseNumber = premiseNumber;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getSubDistrict() {
		return subDistrict;
	}

	public void setSubDistrict(String subDistrict) {
		this.subDistrict = subDistrict;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Long getDistrictMappingId() {
		return districtMappingId;
	}

	public void setDistrictMappingId(Long districtMappingId) {
		this.districtMappingId = districtMappingId;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}
	
}