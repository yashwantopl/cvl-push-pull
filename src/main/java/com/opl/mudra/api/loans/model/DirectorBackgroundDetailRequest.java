package com.opl.mudra.api.loans.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opl.mudra.api.loans.exception.LoansException;


/**
 * The persistent class for the fs_corporate_promotor_background_details database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectorBackgroundDetailRequest implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private String address;
	
	private Double networth;

	private Long applicationId;

	private Integer salutationId;
	
	private Double din;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date appointmentDate;
	
	private String designation;

	private String panNo;

	private String directorsName;

	private Double totalExperience;
	
	private Boolean isActive = true;
	
	private String pincode;
	
	private String stateCode;
	
	private String city;

	/*@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")*/
	private Date dob;
	private String dobString;

	private String mobile;

	private Integer gender;

	private Integer relationshipType;

	private String firstName;

	private String lastName;

	private String middleName;

	private String title;

	private Double shareholding;
	
	private Boolean isItrCompleted;
	
	private Boolean isCibilCompleted;
	
	private Boolean isBankStatementCompleted;
	
	private Boolean isOneFormCompleted;

	private String aadhar;

	private Integer maritalStatus;

	private Integer noOfDependent;

	private Integer residenceType;

    private Integer residenceSinceMonth;
    private Integer residenceSinceYear;


	private Boolean isFamilyMemberInBusiness;

    private EmploymentDetailRequest employmentDetailRequest;

	private Integer countryId;

	private String premiseNumber;

	private String streetName;

	private String landmark;

	private Integer stateId;
	private Integer cityId;

	private Boolean isMainDirector;

	private Integer qualificationId;
	
	private String amount;

	/*
	* SBI Msme Integration fields
	* By Ravina
	* */
	private String fatherName;
	private Integer educationalStatus;
	private Integer nationality;
	private Integer visuallyImpaired;
	/*
	* Co-origination field added
	* */
	private Integer physicallyHandicapped;
	private Integer residentStatus;
	private Boolean isGuarantor;
	private DirectorPersonalDetailRequest directorPersonalDetailRequest;

	private String district;
	private String subDistrict;
	private String village;
	private String xmlResponseExternal;
	private Long districtMappingId;
	
	private Long personalId;
	private Double loanObligation;
	
	

	public Double getLoanObligation() {
		return loanObligation;
	}

	public void setLoanObligation(Double loanObligation) {
		this.loanObligation = loanObligation;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public DirectorBackgroundDetailRequest() {
		// Do nothing because of X and Y.
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



	public Long getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(Long applicationId) {
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

	public void setFamilyMemberInBusiness(Boolean familyMemberInBusiness) {
		isFamilyMemberInBusiness = familyMemberInBusiness;
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

	public void setItrCompleted(Boolean itrCompleted) {
		isItrCompleted = itrCompleted;
	}

	public void setCibilCompleted(Boolean cibilCompleted) {
		isCibilCompleted = cibilCompleted;
	}

	public void setBankStatementCompleted(Boolean bankStatementCompleted) {
		isBankStatementCompleted = bankStatementCompleted;
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



	public EmploymentDetailRequest getEmploymentDetailRequest() {
		return employmentDetailRequest;
	}

	public void setEmploymentDetailRequest(EmploymentDetailRequest employmentDetailRequest) {
		this.employmentDetailRequest = employmentDetailRequest;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
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

	public Integer getVisuallyImpaired() {
		return visuallyImpaired;
	}

	public void setVisuallyImpaired(Integer visuallyImpaired) {
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

	public DirectorPersonalDetailRequest getDirectorPersonalDetailRequest() {
		return directorPersonalDetailRequest;
	}

	public void setDirectorPersonalDetailRequest(DirectorPersonalDetailRequest directorPersonalDetailRequest) {
		this.directorPersonalDetailRequest = directorPersonalDetailRequest;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getSubDistrict() {
		return subDistrict;
	}

	public void setSubDistrict(String subDistrict) {
		this.subDistrict = subDistrict;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public Long getDistrictMappingId() {
		return districtMappingId;
	}

	public void setDistrictMappingId(Long districtMappingId) {
		this.districtMappingId = districtMappingId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public String getDobString() {
		return dobString;
	}

	public void setDobString(String dobString) {
		this.dobString = dobString;
	}

	
	
	public Long getPersonalId() {
		return personalId;
	}

	public void setPersonalId(Long personalId) {
		this.personalId = personalId;
	}

	public Boolean getIsFamilyMemberInBusiness() {
		return isFamilyMemberInBusiness;
	}

	public void setIsFamilyMemberInBusiness(Boolean isFamilyMemberInBusiness) {
		this.isFamilyMemberInBusiness = isFamilyMemberInBusiness;
	}

	public String getXmlResponseExternal() {
		return xmlResponseExternal;
	}

	public void setXmlResponseExternal(String xmlResponseExternal) {
		this.xmlResponseExternal = xmlResponseExternal;
	}

	public Integer getPhysicallyHandicapped() {
		return physicallyHandicapped;
	}

	public void setPhysicallyHandicapped(Integer physicallyHandicapped) {
		this.physicallyHandicapped = physicallyHandicapped;
	}
	


	@Override
	public String toString() {
		return "DirectorBackgroundDetailRequest [id=" + id + ", address=" + address + ", networth=" + networth
				+ ", applicationId=" + applicationId + ", salutationId=" + salutationId + ", din=" + din
				+ ", appointmentDate=" + appointmentDate + ", designation=" + designation + ", directorsName="
				+ directorsName + ", totalExperience=" + totalExperience + ", isActive=" + isActive + ", pincode="
				+ pincode + ", stateCode=" + stateCode + ", city=" + city + ", dob=" + dob + ", dobString=" + dobString
				+ ", mobile=" + mobile + ", gender=" + gender + ", relationshipType=" + relationshipType
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName + ", title="
				+ title + ", shareholding=" + shareholding + ", isItrCompleted=" + isItrCompleted
				+ ", isCibilCompleted=" + isCibilCompleted + ", isBankStatementCompleted=" + isBankStatementCompleted
				+ ", isOneFormCompleted=" + isOneFormCompleted + ", aadhar=" + aadhar + ", maritalStatus="
				+ maritalStatus + ", noOfDependent=" + noOfDependent + ", residenceType=" + residenceType
				+ ", residenceSinceMonth=" + residenceSinceMonth + ", residenceSinceYear=" + residenceSinceYear
				+ ", isFamilyMemberInBusiness=" + isFamilyMemberInBusiness + ", employmentDetailRequest="
				+ employmentDetailRequest + ", countryId=" + countryId + ", premiseNumber=" + premiseNumber
				+ ", streetName=" + streetName + ", landmark=" + landmark + ", stateId=" + stateId + ", cityId="
				+ cityId + ", isMainDirector=" + isMainDirector + ", qualificationId=" + qualificationId + ", amount="
				+ amount + ", fatherName=" + fatherName + ", educationalStatus=" + educationalStatus + ", nationality="
				+ nationality + ", visuallyImpaired=" + visuallyImpaired + ", residentStatus=" + residentStatus
				+ ", isGuarantor=" + isGuarantor + ", directorPersonalDetailRequest=" + directorPersonalDetailRequest
				+ ", district=" + district + ", subDistrict=" + subDistrict + ", village=" + village
				+ ", districtMappingId=" + districtMappingId + ", personalId=" + personalId + "]";
	}

	public static void printFields(Object obj) throws LoansException {
		try {
			Field[] fields = DirectorBackgroundDetailRequest.class.getDeclaredFields();

			for(Field field : fields) {
				Object value = field.get(obj);
				if(value instanceof String){
					String a = value.toString().replaceAll("&", "&amp;");
					value = a;
					field.set(obj, value);
				}
			}
		}
		catch (Exception e){
			throw new LoansException(e);
		}

    }
	

}