package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectorBackgroundDetailResponse implements Serializable{
	
	 private static final long serialVersionUID = 1L;

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

		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
		private Date dob;

		private String mobile;
		
		private String pincode;
		
		private String stateCode;
		
		private String city;
		
		private String gender;

		private String relationshipType;
		
		private String cibilScore;


		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}



		public Double getNetworth() {
			return networth;
		}

		public void setNetworth(Double networth) {
			this.networth = networth;
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



		public Double getTotalExperience() {
			return totalExperience;
		}

		public void setTotalExperience(Double totalExperience) {
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

	public String getCibilScore() {
		return cibilScore;
	}

	public void setCibilScore(String cibilScore) {
		this.cibilScore = cibilScore;
	}
	
	
}
