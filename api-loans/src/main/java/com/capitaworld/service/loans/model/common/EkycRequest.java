package com.capitaworld.service.loans.model.common;

import java.io.Serializable;

public class EkycRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long applicationId;
	private Long applicantType;
	private Long applicantsId;
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Long getApplicantType() {
		return applicantType;
	}
	public void setApplicantType(Long applicantType) {
		this.applicantType = applicantType;
	}
	public Long getApplicantsId() {
		return applicantsId;
	}
	public void setApplicantsId(Long applicantsId) {
		this.applicantsId = applicantsId;
	}
	
	@Override
	public String toString() {
		return "EkycRequest [applicationId=" + applicationId + ", applicantType=" + applicantType + ", applicantsId="
				+ applicantsId + "]";
	}
	public EkycRequest(Long applicationId, Long applicantType, Long applicantsId) {
		super();
		this.applicationId = applicationId;
		this.applicantType = applicantType;
		this.applicantsId = applicantsId;
	}
	
	public EkycRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
