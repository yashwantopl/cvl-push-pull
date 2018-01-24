package com.capitaworld.service.loans.model.mobile;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MRetailCoAppGuarResponse extends MApplicantProfileResponse{

	private static final long serialVersionUID = 1L;
	
	private Date dateOfBirth;
	private String contactNo;
	private Integer relationshipWithApplicant;
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public Integer getRelationshipWithApplicant() {
		return relationshipWithApplicant;
	}
	public void setRelationshipWithApplicant(Integer relationshipWithApplicant) {
		this.relationshipWithApplicant = relationshipWithApplicant;
	}
	
	
}
