package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectorBackgroundDetailResponse implements Serializable{
	
	 private static final long serialVersionUID = 1L;

		private String address;

		private String networth;

		private Long applicationId;

		private Integer salutationId;
		
		private Double din;
		
		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
		private Date appointmentDate;
		
		private String designation;

		private String panNo;

		private String directorsName;

		private String totalExperience;

		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
		private Date dob;

		private String mobile;
		
		private String pincode;
		
		private String stateCode;
		
		private String city;
		
		private String gender;

		private String relationshipType;
		
		private Double cibilScore;

		private Double shareholding;

		private String aadhar;

		private String maritalStatus;

		private String noOfDependent;

		private String residenceType;

		private String residenceSince;

		private String isFamilyMemberInBusiness;

		private Long empDetailId;

		private String country;

		private String premiseNumber;

		private String streetName;

		private String landmark;

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}
		
		public Long getApplicationId() {
			return applicationId;
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

		public String getPanNo() {
			return panNo;
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

	public String getNetworth() {
			return networth;
		}

		public void setNetworth(String networth) {
			this.networth = networth;
		}

		public String getTotalExperience() {
			return totalExperience;
		}

		public void setTotalExperience(String totalExperience) {
			this.totalExperience = totalExperience;
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

	/**
	 * @return the pincode
	 */
	public String getPincode() {
		return pincode;
	}

	/**
	 * @param pincode the pincode to set
	 */
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the gender
	 */
	

	/**
	 * @return the relationshipType
	 */
	

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @param relationshipType the relationshipType to set
	 */
	

	/**
	 * @return the stateCode
	 */
	public String getStateCode() {
		return stateCode;
	}

	/**
	 * @return the relationshipType
	 */
	public String getRelationshipType() {
		return relationshipType;
	}

	/**
	 * @param relationshipType the relationshipType to set
	 */
	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}

	/**
	 * @param stateCode the stateCode to set
	 */
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public Double getCibilScore() {
		return cibilScore;
	}

	public void setCibilScore(Double cibilScore) {
		this.cibilScore = cibilScore;
	}

	public Double getShareholding() {
		return shareholding;
	}

	public void setShareholding(Double shareholding) {
		this.shareholding = shareholding;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getNoOfDependent() {
		return noOfDependent;
	}

	public void setNoOfDependent(String noOfDependent) {
		this.noOfDependent = noOfDependent;
	}

	public String getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(String residenceType) {
		this.residenceType = residenceType;
	}

	public String getResidenceSince() {
		return residenceSince;
	}

	public void setResidenceSince(String residenceSince) {
		this.residenceSince = residenceSince;
	}

	public String getIsFamilyMemberInBusiness() {
		return isFamilyMemberInBusiness;
	}

	public void setIsFamilyMemberInBusiness(String isFamilyMemberInBusiness) {
		this.isFamilyMemberInBusiness = isFamilyMemberInBusiness;
	}

	public Long getEmpDetailId() {
		return empDetailId;
	}

	public void setEmpDetailId(Long empDetailId) {
		this.empDetailId = empDetailId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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
