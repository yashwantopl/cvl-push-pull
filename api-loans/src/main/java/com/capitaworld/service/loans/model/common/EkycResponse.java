package com.capitaworld.service.loans.model.common;

import java.io.Serializable;

public class EkycResponse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String fullName;
	private String panNo;
	private String aadharNo;
	private String nameAsPerAadhar;
	private String dob;
	private String organizationName;
	
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	public String getNameAsPerAadhar() {
		return nameAsPerAadhar;
	}
	public void setNameAsPerAadhar(String nameAsPerAadhar) {
		this.nameAsPerAadhar = nameAsPerAadhar;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	
	@Override
	public String toString() {
		return "EkycResponse [fullName=" + fullName + ", panNo=" + panNo + ", aadharNo=" + aadharNo
				+ ", nameAsPerAadhar=" + nameAsPerAadhar + ", dob=" + dob + ", organizationName=" + organizationName
				+ "]";
	}
	public EkycResponse(String fullName, String panNo, String aadharNo, String nameAsPerAadhar, String dob,
			String organizationName, Long userId, Long applicationId, Long clientId, Long applicantType,
			Long applicantsId) {
		super();
		this.fullName = fullName;
		this.panNo = panNo;
		this.aadharNo = aadharNo;
		this.nameAsPerAadhar = nameAsPerAadhar;
		this.dob = dob;
		this.organizationName = organizationName;
		
	}
	
	public EkycResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
