package com.capitaworld.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.capitaworld.service.loans.domain.fundseeker.AuditActivity;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;


/**
 * The persistent class for the fs_corporate_promotor_background_details database table.
 * 
 */
@Entity
@Table(name="fs_corporate_director_background_details")
public class DirectorBackgroundDetail extends AuditActivity implements Serializable {
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

	@Column(name="residence_since_month")
	private Integer residenceSinceMonth;

	@Column(name="residence_since_year")
	private Integer residenceSinceYear;

	@Column(name="is_family_member_in_business")
	private Boolean isFamilyMemberInBusiness;

	@Column(name = "country_id")
	private Integer countryId;

	@Column(name = "state_id") // FOR NTB
	private Integer stateId;

	@Column(name = "city_id") // FOR NTB
	private Integer cityId;

	@Column(name = "premise_number")
	private String premiseNumber;

	@Column(name = "street_name")
	private String streetName;

	@Column(name = "landmark")
	private String landmark;

	@Column(name="qualification_id")
	private Integer qualificationId;

	@OneToOne
	@JoinColumn(name="emp_detail_id")
	private EmploymentDetail employmentDetail;

	@Column(name="is_main_director")
	private Boolean isMainDirector;

	@Column(name="father_or_spouse_name")
	private String fatherOrSpouseName;

	@Column(name="educational_status")
	private Integer educationalStatus;

	@Column(name="nationality")
	private Integer nationality;

	@Column(name="visually_impaired")
	private Boolean visuallyImpaired;

	@Column(name="resident_status")
	private Integer residentStatus;

	@Column(name="is_guarantor")
	private Boolean isGuarantor;

	@OneToOne
	@JoinColumn(name="personal_detail_id")
	private DirectorPersonalDetail directorPersonalDetail;

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

    public Integer getResidenceSinceMonth() {
        return residenceSinceMonth;
    }

    public void setResidenceSinceMonth(Integer residenceSinceMonth) {
        this.residenceSinceMonth = residenceSinceMonth;
    }

    public Integer getResidenceSinceYear() {
        return residenceSinceYear;
    }

    public void setResidenceSinceYear(Integer residenceSinceYear) {
        this.residenceSinceYear = residenceSinceYear;
    }

    public Boolean getFamilyMemberInBusiness() {
		return isFamilyMemberInBusiness;
	}

	public void setFamilyMemberInBusiness(Boolean familyMemberInBusiness) {
		isFamilyMemberInBusiness = familyMemberInBusiness;
	}

	public EmploymentDetail getEmploymentDetail() {
		return employmentDetail;
	}

	public void setEmploymentDetail(EmploymentDetail employmentDetail) {
		this.employmentDetail = employmentDetail;
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

	public Boolean getIsMainDirector() {
		return isMainDirector;
	}

	public void setIsMainDirector(Boolean isMainDirector) {
		this.isMainDirector = isMainDirector;
	}

	public Integer getQualificationId() {
		return qualificationId;
	}

	public void setQualificationId(Integer qualificationId) {
		this.qualificationId = qualificationId;
	}

	/**
	 * for NTB State city
	 * @Param State city Id
	 * @return state city ID
	 */
	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	/**
	 * for NTB State city
	 * @Param State city Id
	 * @return state city ID
	 */
	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getFatherOrSpouseName() {
		return fatherOrSpouseName;
	}

	public void setFatherOrSpouseName(String fatherOrSpouseName) {
		this.fatherOrSpouseName = fatherOrSpouseName;
	}

	public Integer getEducationalStatus() {
		return educationalStatus;
	}

	public void setEducationalStatus(Integer educationalStatus) {
		this.educationalStatus = educationalStatus;
	}

	public Integer getNationality() {
		return nationality;
	}

	public void setNationality(Integer nationality) {
		this.nationality = nationality;
	}

	public Boolean getVisuallyImpaired() {
		return visuallyImpaired;
	}

	public void setVisuallyImpaired(Boolean visuallyImpaired) {
		this.visuallyImpaired = visuallyImpaired;
	}

	public Integer getResidentStatus() {
		return residentStatus;
	}

	public void setResidentStatus(Integer residentStatus) {
		this.residentStatus = residentStatus;
	}

	public Boolean getIsGuarantor() {
		return isGuarantor;
	}

	public void setIsGuarantor(Boolean isGuarantor) {
		this.isGuarantor = isGuarantor;
	}

	public DirectorPersonalDetail getDirectorPersonalDetail() {
		return directorPersonalDetail;
	}

	public void setDirectorPersonalDetail(DirectorPersonalDetail directorPersonalDetail) {
		this.directorPersonalDetail = directorPersonalDetail;
	}
}