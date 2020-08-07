package com.opl.mudra.api.itr.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ITRRequest implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private String pan;
	private Date dob;
	private Long applicationId;
	private Long userId;
	private Long directorId;
	private String password;
	private String captcha;
	private String type;
	private String txnNo;
	private String companyName;
	private Integer businessTypeId;
	private Long coAppId;
	private Long profileId;
	private Boolean value;
	private Boolean isAlreadyRead;
	private Boolean isUpload;
	private Integer typeFromId;
	
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
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
	public Long getDirectorId() {
		return directorId;
	}
	public void setDirectorId(Long directorId) {
		this.directorId = directorId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCaptcha() {
		return captcha;
	}
	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTxnNo() {
		return txnNo;
	}
	public void setTxnNo(String txnNo) {
		this.txnNo = txnNo;
	}
	
	public Integer getBusinessTypeId() {
		return businessTypeId;
	}
	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}
	
	public Long getCoAppId() {
		return coAppId;
	}
	public void setCoAppId(Long coAppId) {
		this.coAppId = coAppId;
	}
	
	public Boolean getIsAlreadyRead() {
		return isAlreadyRead;
	}
	public void setIsAlreadyRead(Boolean isAlreadyRead) {
		this.isAlreadyRead = isAlreadyRead;
	}
	public Boolean getValue() {
		return value;
	}
	public void setValue(Boolean value) {
		this.value = value;
	}
	public Boolean getIsUpload() {
		return isUpload;
	}
	public void setIsUpload(Boolean isUpload) {
		this.isUpload = isUpload;
	}
	public Integer getTypeFromId() {
		return typeFromId;
	}
	public void setTypeFromId(Integer typeFromId) {
		this.typeFromId = typeFromId;
	}
	@Override
	public String toString() {
		return "ITRRequest [pan=" + pan + ", dob=" + dob + ", applicationId=" + applicationId + ", userId=" + userId
				+ ", directorId=" + directorId + ", password=" + password + ", captcha=" + captcha + ", type=" + type
				+ ", txnNo=" + txnNo + ", businessTypeId=" + businessTypeId + ", coAppId=" + coAppId + ", value="
				+ value + ", isAlreadyRead=" + isAlreadyRead + ", isUpload=" + isUpload + "]";
	}


	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
