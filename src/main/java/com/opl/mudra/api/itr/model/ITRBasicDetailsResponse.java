package com.opl.mudra.api.itr.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author harshit
 * Date : 21-May-2018
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ITRBasicDetailsResponse implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private Long applicationId;
	private Long coAppId;
	private String itrForm;
	private String year;
	private String name;
	private String pan;
	private String dob;
	private Boolean isUpload;
	private String email;
	private String mobile;
	private Boolean isMoveAhead;
	private String message;
	private Integer itrFinancialType;
	private Integer itrCount;
	private Boolean isFilledManual;
	private String fileName;
	private Boolean isSkip;
	private Boolean isITR4Agree;
	/**
	 * @return the itrForm
	 */
	public String getItrForm() {
		return itrForm;
	}
	/**
	 * @param itrForm the itrForm to set
	 */
	public void setItrForm(String itrForm) {
		this.itrForm = itrForm;
	}
	/**
	 * @return the year
	 */
	public String getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the pan
	 */
	public String getPan() {
		return pan;
	}
	/**
	 * @param pan the pan to set
	 */
	public void setPan(String pan) {
		this.pan = pan;
	}
	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}
	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}
	public Boolean getIsUpload() {
		return isUpload;
	}
	public void setIsUpload(Boolean isUpload) {
		this.isUpload = isUpload;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	

	public Boolean getIsMoveAhead() {
		return isMoveAhead;
	}
	public void setIsMoveAhead(Boolean isMoveAhead) {
		this.isMoveAhead = isMoveAhead;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getIsFilledManual() {
		return isFilledManual;
	}
	public void setIsFilledManual(Boolean isFilledManual) {
		this.isFilledManual = isFilledManual;
	}
	public Integer getItrCount() {
		return itrCount;
	}
	public void setItrCount(Integer itrCount) {
		this.itrCount = itrCount;
	}
	public Integer getItrFinancialType() {
		return itrFinancialType;
	}
	public void setItrFinancialType(Integer itrFinancialType) {
		this.itrFinancialType = itrFinancialType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public Long getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public Long getCoAppId() {
		return coAppId;
	}
	public void setCoAppId(Long coAppId) {
		this.coAppId = coAppId;
	}
	
	public Boolean getIsSkip() {
		return isSkip;
	}
	public void setIsSkip(Boolean isSkip) {
		this.isSkip = isSkip;
	}
	
	public Boolean getIsITR4Agree() {
		return isITR4Agree;
	}
	public void setIsITR4Agree(Boolean isITR4Agree) {
		this.isITR4Agree = isITR4Agree;
	}
	@Override
	public String toString() {
		return "ITRBasicDetailsResponse [itrForm=" + itrForm + ", year=" + year + ", name=" + name + ", pan=" + pan
				+ ", dob=" + dob + ", isUpload=" + isUpload + ", email=" + email + ", mobile=" + mobile
				+ ", isMoveAhead=" + isMoveAhead + ", message=" + message + ", itrFinancialType=" + itrFinancialType
				+ ", itrCount=" + itrCount + ", isFilledManual=" + isFilledManual + ", fileName=" + fileName + "]";
	}
	
	
	

}
