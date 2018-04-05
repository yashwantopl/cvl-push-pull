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
}
