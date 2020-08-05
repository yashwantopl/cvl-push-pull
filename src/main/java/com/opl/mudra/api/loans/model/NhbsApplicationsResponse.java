package com.opl.mudra.api.loans.model;

import java.io.Serializable;
import java.util.Date;

public class NhbsApplicationsResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String clientName;
	
	private String clientProfilePic;
	
	private Integer applicationType;
	
	private String applicationCode;
	
	private String productName;
	
	private String makerName;

	private String checkerName;
	
	private String assigneeName;
	
	private String clientSource;
	
	private Date applicationDate;

	private Date proposalTakenDate;

	private Date assignedToCheckerDate;

	private Date receivedDate;

	private Date revertDate;

	private Date approvalDate;
	
	private Long userId;
	
	private Long applicationId;
	
	private String oneFormFilled;
	
	private String ddrStatus;
	
	private int ddrStatusId;
	
	private String mcaStatus;
	
	private Long fpProductId;

	private Boolean isFinalLocked;


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

	private String applicationWith;

	private Integer businessTypeId;
	
	private String agentName;

	private String orgName;
	
	
	public Long getProposalId() {
		return proposalId;
	}

	public void setProposalId(Long proposalId) {
		this.proposalId = proposalId;
	}

	private Long clientId;

	private Long proposalId;

	boolean isSanction;

	public Date getAssignedToCheckerDate() {
		return assignedToCheckerDate;
	}

	public void setAssignedToCheckerDate(Date assignedToCheckerDate) {
		this.assignedToCheckerDate = assignedToCheckerDate;
	}

	public String getApplicationWith() {
		return applicationWith;
	}

	public void setApplicationWith(String applicationWith) {
		this.applicationWith = applicationWith;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

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

	public Date getProposalTakenDate() {
		return proposalTakenDate;
	}

	public void setProposalTakenDate(Date proposalTakenDate) {
		this.proposalTakenDate = proposalTakenDate;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public Date getRevertDate() {
		return revertDate;
	}

	public void setRevertDate(Date revertDate) {
		this.revertDate = revertDate;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getMcaStatus() {
		return mcaStatus;
	}

	public void setMcaStatus(String mcaStatus) {
		this.mcaStatus = mcaStatus;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Boolean getIsFinalLocked() {
		return isFinalLocked;
	}

	public void setIsFinalLocked(Boolean isFinalLocked) {
		this.isFinalLocked = isFinalLocked;
	}

	public Long getFpProductId() {
		return fpProductId;
	}

	public void setFpProductId(Long fpProductId) {
		this.fpProductId = fpProductId;
	}



	public boolean isSanction() {
		return isSanction;
	}

	public void setSanction(boolean sanction) {
		isSanction = sanction;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	
	
	
}
