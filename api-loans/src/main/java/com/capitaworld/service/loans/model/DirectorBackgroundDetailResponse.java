package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DirectorBackgroundDetailResponse implements Serializable{
	
	 private static final long serialVersionUID = 1L;
	 
	    private String achivements;

		private String address;

		private Double age;
		
		private Double networth;

		private Long applicationId;

		private Integer salutationId;
		
		private Double din;
		
		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
		private Date appointmentDate;
		
		private String designation;

		private String panNo;

		private String directorsName;

		private String qualification;

		private Double totalExperience;

		public String getAchivements() {
			return achivements;
		}

		public void setAchivements(String achivements) {
			this.achivements = achivements;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Double getAge() {
			return age;
		}

		public void setAge(Double age) {
			this.age = age;
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

		public String getQualification() {
			return qualification;
		}

		public void setQualification(String qualification) {
			this.qualification = qualification;
		}

		public Double getTotalExperience() {
			return totalExperience;
		}

		public void setTotalExperience(Double totalExperience) {
			this.totalExperience = totalExperience;
		}
		
		

}
