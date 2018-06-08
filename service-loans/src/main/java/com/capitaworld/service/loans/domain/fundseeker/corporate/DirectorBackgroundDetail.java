package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;


/**
 * The persistent class for the fs_corporate_promotor_background_details database table.
 * 
 */
@Entity
@Table(name="fs_corporate_director_background_details")
public class DirectorBackgroundDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	/*@Lob
	private String achivements;*/

	@Lob
	private String address;
	
	private String pincode;
	
	@Column(name="state_code")
	private String stateCode;
	
	private String city;

	/*private Double age;*/
	
	private Double din;


	private Double networth;
	
	@ManyToOne
	@JoinColumn(name="application_id")
	private LoanApplicationMaster applicationId;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="appointment_date")
	private Date appointmentDate;

	@Column(name="salutation_id")
	private Integer salutationId;

	@Column(name="pan_no")
	private String panNo;
	
	@Column(name="designation")
	private String designation;

	@Column(name="directors_name")
	private String directorsName;

	/*@Lob
	private String qualification;*/

	@Column(name="total_experience")
	private Double totalExperience;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dob")
	private Date dob;

	private String mobile;

	@Column(name="gender")
	private Integer gender;

	@Column(name="relationship_type")
	private Integer relationshipType;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="middle_name")
	private String middleName;

	@Column(name="title")
	private String title;

	private Double shareholding;
	
	@Column(name="is_itr_completed")
	private Boolean isItrCompleted;
	
	@Column(name="is_cibil_completed")
	private Boolean isCibilCompleted;
	
	@Column(name="is_bank_state_completed")
	private Boolean isBankStatementCompleted;
	
	@Column(name="is_one_form_completed")
	private Boolean isOneFormCompleted;

	@Column(name="aadhar")
	private String aadhar;

	@Column(name="marital_status")
	private Integer maritalStatus;

	@Column(name="no_of_dependent")
	private Integer noOfDependent;

	@Column(name="residence_type")
	private Integer residenceType;

	@Column(name="residence_since")
	private Integer residenceSince;

	@Column(name="is_family_member_in_business")
	private Boolean isFamilyMemberInBusiness;

	@Column(name = "country_id")
	private Integer countryId;

	@Column(name = "premise_number")
	private String premiseNumber;

	@Column(name = "street_name")
	private String streetName;

	@Column(name = "landmark")
	private String landmark;

	@OneToOne
	@JoinColumn(name="emp_detail_id")
	private EmploymentDetail empDetailId;

	public DirectorBackgroundDetail() {
	}
	
	public DirectorBackgroundDetail(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}



	public LoanApplicationMaster getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
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

	public Integer getSalutationId() {
		return salutationId;
	}

	public void setSalutationId(Integer salutationId) {
		this.salutationId = salutationId;
	}

	public String getPanNo() {
		return this.panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}


	public String getDirectorsName() {
		return directorsName;
	}

	public void setDirectorsName(String directorsName) {
		this.directorsName = directorsName;
	}


	public Double getTotalExperience() {
		return this.totalExperience;
	}

	public void setTotalExperience(Double totalExperience) {
		this.totalExperience = totalExperience;
	}

	public Double getNetworth() {
		return networth;
	}

	public void setNetworth(Double networth) {
		this.networth = networth;
	}

	public Double getDin() {
		return din;
	}

	public void setDin(Double din) {
		this.din = din;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getRelationshipType() {
		return relationshipType;
	}

	public void setRelationshipType(Integer relationshipType) {
		this.relationshipType = relationshipType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getShareholding() {
		return shareholding;
	}

	public void setShareholding(Double shareholding) {
		this.shareholding = shareholding;
	}

	public Boolean getIsItrCompleted() {
		return isItrCompleted;
	}

	public void setIsItrCompleted(Boolean isItrCompleted) {
		this.isItrCompleted = isItrCompleted;
	}

	public Boolean getIsCibilCompleted() {
		return isCibilCompleted;
	}

	public void setIsCibilCompleted(Boolean isCibilCompleted) {
		this.isCibilCompleted = isCibilCompleted;
	}

	public Boolean getIsBankStatementCompleted() {
		return isBankStatementCompleted;
	}

	public void setIsBankStatementCompleted(Boolean isBankStatementCompleted) {
		this.isBankStatementCompleted = isBankStatementCompleted;
	}

	public Boolean getIsOneFormCompleted() {
		return isOneFormCompleted;
	}

	public void setIsOneFormCompleted(Boolean isOneFormCompleted) {
		this.isOneFormCompleted = isOneFormCompleted;
	}

	public Boolean getItrCompleted() {
		return isItrCompleted;
	}

	public void setItrCompleted(Boolean itrCompleted) {
		isItrCompleted = itrCompleted;
	}

	public Boolean getCibilCompleted() {
		return isCibilCompleted;
	}

	public void setCibilCompleted(Boolean cibilCompleted) {
		isCibilCompleted = cibilCompleted;
	}

	public Boolean getBankStatementCompleted() {
		return isBankStatementCompleted;
	}

	public void setBankStatementCompleted(Boolean bankStatementCompleted) {
		isBankStatementCompleted = bankStatementCompleted;
	}

	public Boolean getOneFormCompleted() {
		return isOneFormCompleted;
	}

	public void setOneFormCompleted(Boolean oneFormCompleted) {
		isOneFormCompleted = oneFormCompleted;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public Integer getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Integer getNoOfDependent() {
		return noOfDependent;
	}

	public void setNoOfDependent(Integer noOfDependent) {
		this.noOfDependent = noOfDependent;
	}

	public Integer getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(Integer residenceType) {
		this.residenceType = residenceType;
	}

	public Integer getResidenceSince() {
		return residenceSince;
	}

	public void setResidenceSince(Integer residenceSince) {
		this.residenceSince = residenceSince;
	}

	public Boolean getFamilyMemberInBusiness() {
		return isFamilyMemberInBusiness;
	}

	public void setFamilyMemberInBusiness(Boolean familyMemberInBusiness) {
		isFamilyMemberInBusiness = familyMemberInBusiness;
	}

	public EmploymentDetail getEmpDetailId() {
		return empDetailId;
	}

	public void setEmpDetailId(EmploymentDetail empDetailId) {
		this.empDetailId = empDetailId;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getPremiseNumber() {
		return premiseNumber;
	}

	public void setPremiseNumber(String premiseNumber) {
		this.premiseNumber = premiseNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
}