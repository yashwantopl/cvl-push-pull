package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class NhbsApplicationsResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String clientName;
	
	private String clientProfilePic;
	
	private Integer applicationType;
	
	private String makerName;
	
	private String assigneeName;
	
	private String clientSource;
	
	private Date applicationDate;
	
	private Long userId;
	
	private Long applicationId;
	
	private String oneFormFilled;
	
	private String ddrStatus;
	
	private int ddrStatusId;
	
	public int getDdrStatusId() {
		return ddrStatusId;
	}

	public void setDdrStatusId(int ddrStatusId) {
		this.ddrStatusId = ddrStatusId;
	}

	private String paymentMode;
	
	private String isPaymentDone;
	
	private String city;
	
	private String state;
	
	private String country;
		
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getIsPaymentDone() {
		return isPaymentDone;
	}

	public void setIsPaymentDone(String isPaymentDone) {
		this.isPaymentDone = isPaymentDone;
	}

	public String getDdrStatus() {
		return ddrStatus;
	}

	public void setDdrStatus(String ddrStatus) {
		this.ddrStatus = ddrStatus;
	}

	
	public String getOneFormFilled() {
		return oneFormFilled;
	}

	public void setOneFormFilled(String oneFormFilled) {
		this.oneFormFilled = oneFormFilled;
	}

	public String getAssigneeName() {
		return assigneeName;
	}

	public void setAssigneeName(String assigneeName) {
		this.assigneeName = assigneeName;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Integer getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(Integer applicationType) {
		this.applicationType = applicationType;
	}

	public String getMakerName() {
		return makerName;
	}

	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}

	public String getClientSource() {
		return clientSource;
	}

	public void setClientSource(String clientSource) {
		this.clientSource = clientSource;
	}

	public String getClientProfilePic() {
		return clientProfilePic;
	}

	public void setClientProfilePic(String clientProfilePic) {
		this.clientProfilePic = clientProfilePic;
	}
	
	
	
}
