package com.opl.mudra.api.cibil_integration.utils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SBIBureauMSMEConfig implements Serializable {
	private static final long serialVersionUID = -4363151292856116804L;
	
	private Commercial commercial;
	private Consumer consumer;

	public Commercial getCommercial() {
		return commercial;
	}

	public void setCommercial(Commercial commercial) {
		this.commercial = commercial;
	}

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}
	

	public static class Commercial implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = -784525118727401078L;
		private Long id;
		private String applicationId;
		private Integer businessGroup;
		private Integer circle;
		private String productName;
		private String applicantType;
		private String loanAmount;
		private String creditEnquiryPurpose;
		private String commercialURL;
		private String commercialFinalURL;
		private Boolean isActive;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getApplicationId() {
			return applicationId;
		}
		public void setApplicationId(String applicationId) {
			this.applicationId = applicationId;
		}
		public Integer getBusinessGroup() {
			return businessGroup;
		}
		public void setBusinessGroup(Integer businessGroup) {
			this.businessGroup = businessGroup;
		}
		public Integer getCircle() {
			return circle;
		}
		public void setCircle(Integer circle) {
			this.circle = circle;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public String getApplicantType() {
			return applicantType;
		}
		public void setApplicantType(String applicantType) {
			this.applicantType = applicantType;
		}
		public String getLoanAmount() {
			return loanAmount;
		}
		public void setLoanAmount(String loanAmount) {
			this.loanAmount = loanAmount;
		}
		public String getCreditEnquiryPurpose() {
			return creditEnquiryPurpose;
		}
		public void setCreditEnquiryPurpose(String creditEnquiryPurpose) {
			this.creditEnquiryPurpose = creditEnquiryPurpose;
		}
		public String getCommercialURL() {
			return commercialURL;
		}
		public void setCommercialURL(String commercialURL) {
			this.commercialURL = commercialURL;
		}
		public String getCommercialFinalURL() {
			return commercialFinalURL;
		}
		public void setCommercialFinalURL(String commercialFinalURL) {
			this.commercialFinalURL = commercialFinalURL;
		}
		public Boolean getIsActive() {
			return isActive;
		}
		public void setIsActive(Boolean isActive) {
			this.isActive = isActive;
		}

	}

	public static class Consumer implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 309366239381908460L;
		private Long id;
		private String applicationId;
		private Integer businessGroup;
		private Integer circle;
		private String productName;
		private String applicantType;
		private String loanAmount;
		private String creditEnquiryPurpose;
		private String individualURL;
		private String individualFinalURL;
		private Boolean isActive;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getApplicationId() {
			return applicationId;
		}
		public void setApplicationId(String applicationId) {
			this.applicationId = applicationId;
		}
		public Integer getBusinessGroup() {
			return businessGroup;
		}
		public void setBusinessGroup(Integer businessGroup) {
			this.businessGroup = businessGroup;
		}
		public Integer getCircle() {
			return circle;
		}
		public void setCircle(Integer circle) {
			this.circle = circle;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public String getApplicantType() {
			return applicantType;
		}
		public void setApplicantType(String applicantType) {
			this.applicantType = applicantType;
		}
		public String getLoanAmount() {
			return loanAmount;
		}
		public void setLoanAmount(String loanAmount) {
			this.loanAmount = loanAmount;
		}
		public String getCreditEnquiryPurpose() {
			return creditEnquiryPurpose;
		}
		public void setCreditEnquiryPurpose(String creditEnquiryPurpose) {
			this.creditEnquiryPurpose = creditEnquiryPurpose;
		}
		public Boolean getIsActive() {
			return isActive;
		}
		public void setIsActive(Boolean isActive) {
			this.isActive = isActive;
		}
		public String getIndividualURL() {
			return individualURL;
		}
		public void setIndividualURL(String individualURL) {
			this.individualURL = individualURL;
		}
		public String getIndividualFinalURL() {
			return individualFinalURL;
		}
		public void setIndividualFinalURL(String individualFinalURL) {
			this.individualFinalURL = individualFinalURL;
		}
	}

}