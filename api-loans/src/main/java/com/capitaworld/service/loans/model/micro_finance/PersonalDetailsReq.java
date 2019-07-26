package com.capitaworld.service.loans.model.micro_finance;

import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author harsukh.ghumaliya
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class PersonalDetailsReq {

	private Long id;
	private Long applicationId;

	private String fatherName;
	private String motherName;
	private String spouseName;
	private Date spouseBirthDate;
	private Integer noDependent;
	private String spouseMobile;

	private String nomineeName;
	private Date nomineeBirthDate;
	private Integer relationWithNomineeId;
	private String nomineeAddress;
	private String nomineePincode;

	private Integer educationQualification;
	private Integer religion;
//	private String cast;
	private Double landHolding;
	private Integer houseType;

	private String nameOfFirm;
	private Integer businessType;
//	private Boolean lifeInsurance;
//	private String sumInsured;

	private String nomineeState;
	private String nomineeCity;
	private String nomineeDistrict;
	private String nomineeLocation;
	private String nomineeHouseNo;
	private String nomineeLandmark;
	private String academicReligion;
	private String academicCaste;
	private Boolean isAcademicLifeInsurance;
	private String houseOwnership;
	private String areaType;
	private String businessPremises;
	private Integer expInSameLine;
	private String academicSumInsured;
	private Boolean isPersonalDetailsFilled;

	public PersonalDetailsReq() {
	}


	public PersonalDetailsReq(Long applicationId, String fatherName, String motherName, String spouseName,
			Date spouseBirthDate, Integer noDependent, String spouseMobile, String nomineeName, Date nomineeBirthDate,
			Integer relationWithNomineeId, String nomineeAddress, String nomineePincode, Integer educationQualification,
			Integer religion, Double landHolding, Integer houseType, String nameOfFirm, Integer businessType,
			String nomineeState, String nomineeCity, String nomineeDistrict, String nomineeLocation,
			String nomineeHouseNo, String nomineeLandmark, String academicReligion, String academicCaste,
			Boolean isAcademicLifeInsurance, String houseOwnership, String areaType, String businessPremises,
			Integer expInSameLine, String academicSumInsured, Boolean isPersonalDetailsFilled) {
		super();
		this.applicationId = applicationId;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.spouseName = spouseName;
		this.spouseBirthDate = spouseBirthDate;
		this.noDependent = noDependent;
		this.spouseMobile = spouseMobile;
		this.nomineeName = nomineeName;
		this.nomineeBirthDate = nomineeBirthDate;
		this.relationWithNomineeId = relationWithNomineeId;
		this.nomineeAddress = nomineeAddress;
		this.nomineePincode = nomineePincode;
		this.educationQualification = educationQualification;
		this.religion = religion;
		this.landHolding = landHolding;
		this.houseType = houseType;
		this.nameOfFirm = nameOfFirm;
		this.businessType = businessType;
		this.nomineeState = nomineeState;
		this.nomineeCity = nomineeCity;
		this.nomineeDistrict = nomineeDistrict;
		this.nomineeLocation = nomineeLocation;
		this.nomineeHouseNo = nomineeHouseNo;
		this.nomineeLandmark = nomineeLandmark;
		this.academicReligion = academicReligion;
		this.academicCaste = academicCaste;
		this.isAcademicLifeInsurance = isAcademicLifeInsurance;
		this.houseOwnership = houseOwnership;
		this.areaType = areaType;
		this.businessPremises = businessPremises;
		this.expInSameLine = expInSameLine;
		this.academicSumInsured = academicSumInsured;
		this.isPersonalDetailsFilled = isPersonalDetailsFilled;
	}






	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public Date getSpouseBirthDate() {
		return spouseBirthDate;
	}

	public void setSpouseBirthDate(Date spouseBirthDate) {
		this.spouseBirthDate = spouseBirthDate;
	}

	public String getSpouseMobile() {
		return spouseMobile;
	}

	public void setSpouseMobile(String spouseMobile) {
		this.spouseMobile = spouseMobile;
	}

	public Integer getNoDependent() {
		return noDependent;
	}

	public void setNoDependent(Integer noDependent) {
		this.noDependent = noDependent;
	}

	public Integer getRelationWithNomineeId() {
		return relationWithNomineeId;
	}

	public void setRelationWithNomineeId(Integer relationWithNomineeId) {
		this.relationWithNomineeId = relationWithNomineeId;
	}

	public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	public String getNomineeAddress() {
		return nomineeAddress;
	}

	public void setNomineeAddress(String nomineeAddress) {
		this.nomineeAddress = nomineeAddress;
	}

	public String getNomineePincode() {
		return nomineePincode;
	}

	public void setNomineePincode(String nomineePincode) {
		this.nomineePincode = nomineePincode;
	}

	public Integer getEducationQualification() {
		return educationQualification;
	}

	public void setEducationQualification(Integer educationQualification) {
		this.educationQualification = educationQualification;
	}


	public Integer getReligion() {
		return religion;
	}

	public void setReligion(Integer religion) {
		this.religion = religion;
	}


	public Double getLandHolding() {
		return landHolding;
	}

	public void setLandHolding(Double landHolding) {
		this.landHolding = landHolding;
	}

	public String getHouseOwnership() {
		return houseOwnership;
	}

	public void setHouseOwnership(String houseOwnership) {
		this.houseOwnership = houseOwnership;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getNameOfFirm() {
		return nameOfFirm;
	}

	public void setNameOfFirm(String nameOfFirm) {
		this.nameOfFirm = nameOfFirm;
	}

	public Integer getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Integer businessType) {
		this.businessType = businessType;
	}

	public String getBusinessPremises() {
		return businessPremises;
	}

	public void setBusinessPremises(String businessPremises) {
		this.businessPremises = businessPremises;
	}

//	public Boolean getLifeInsurance() {
//		return lifeInsurance;
//	}
//
//	public void setLifeInsurance(Boolean lifeInsurance) {
//		this.lifeInsurance = lifeInsurance;
//	}

	public Date getNomineeBirthDate() {
		return nomineeBirthDate;
	}

	public void setNomineeBirthDate(Date nomineeBirthDate) {
		this.nomineeBirthDate = nomineeBirthDate;
	}

	public String getNomineeState() {
		return nomineeState;
	}

	public void setNomineeState(String nomineeState) {
		this.nomineeState = nomineeState;
	}

	public String getNomineeCity() {
		return nomineeCity;
	}

	public void setNomineeCity(String nomineeCity) {
		this.nomineeCity = nomineeCity;
	}

	public String getNomineeDistrict() {
		return nomineeDistrict;
	}

	public void setNomineeDistrict(String nomineeDistrict) {
		this.nomineeDistrict = nomineeDistrict;
	}

	public String getNomineeLocation() {
		return nomineeLocation;
	}

	public void setNomineeLocation(String nomineeLocation) {
		this.nomineeLocation = nomineeLocation;
	}

	public String getNomineeHouseNo() {
		return nomineeHouseNo;
	}

	public void setNomineeHouseNo(String nomineeHouseNo) {
		this.nomineeHouseNo = nomineeHouseNo;
	}

	public String getNomineeLandmark() {
		return nomineeLandmark;
	}

	public void setNomineeLandmark(String nomineeLandmark) {
		this.nomineeLandmark = nomineeLandmark;
	}

	public String getAcademicReligion() {
		return academicReligion;
	}

	public void setAcademicReligion(String academicReligion) {
		this.academicReligion = academicReligion;
	}

	public String getAcademicCaste() {
		return academicCaste;
	}

	public void setAcademicCaste(String academicCaste) {
		this.academicCaste = academicCaste;
	}

	public Boolean getIsAcademicLifeInsurance() {
		return isAcademicLifeInsurance;
	}

	public void setIsAcademicLifeInsurance(Boolean isAcademicLifeInsurance) {
		this.isAcademicLifeInsurance = isAcademicLifeInsurance;
	}

	public Integer getExpInSameLine() {
		return expInSameLine;
	}

	public void setExpInSameLine(Integer expInSameLine) {
		this.expInSameLine = expInSameLine;
	}

	public String getAcademicSumInsured() {
		return academicSumInsured;
	}

	public void setAcademicSumInsured(String academicSumInsured) {
		this.academicSumInsured = academicSumInsured;
	}

	public Integer getHouseType() {
		return houseType;
	}

	public void setHouseType(Integer houseType) {
		this.houseType = houseType;
	}

	public Boolean getIsPersonalDetailsFilled() {
		return isPersonalDetailsFilled;
	}

	public void setIsPersonalDetailsFilled(Boolean isPersonalDetailsFilled) {
		this.isPersonalDetailsFilled = isPersonalDetailsFilled;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

}
