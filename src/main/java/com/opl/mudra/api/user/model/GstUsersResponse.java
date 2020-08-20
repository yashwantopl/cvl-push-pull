/**
 * 
 */
package com.opl.mudra.api.user.model;

import java.util.Date;

/**
 * @author vijay.chauhan
 * @Date 01-Aug-2019
 */
public class GstUsersResponse {

	private Long id;    
    private String mobile;
    private String email;    
    private boolean isActive;
    private boolean isEmailSent;
    private Date emailSentDate;    
    private Long orgId;
    private Long fileId;    
    private Date createdDate;
    private Date modifiedDate;
    private String pan;
    private String remarks;
    private Long uploadedBy;
    private String uploadType;
    private Integer gstPanStatus;
    private Boolean isGstDataRetrieved;
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean isEmailSent() {
		return isEmailSent;
	}
	public void setEmailSent(boolean isEmailSent) {
		this.isEmailSent = isEmailSent;
	}
	public Date getEmailSentDate() {
		return emailSentDate;
	}
	public void setEmailSentDate(Date emailSentDate) {
		this.emailSentDate = emailSentDate;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}	
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Long getUploadedBy() {
		return uploadedBy;
	}
	public void setUploadedBy(Long uploadedBy) {
		this.uploadedBy = uploadedBy;
	}
	public String getUploadType() {
		return uploadType;
	}
	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}
	public Integer getGstPanStatus() {
		return gstPanStatus;
	}
	public void setGstPanStatus(Integer gstPanStatus) {
		this.gstPanStatus = gstPanStatus;
	}
	public Boolean getIsGstDataRetrieved() {
		return isGstDataRetrieved;
	}
	public void setIsGstDataRetrieved(Boolean isGstDataRetrieved) {
		this.isGstDataRetrieved = isGstDataRetrieved;
	}
	@Override
	public String toString() {
		return "GstUsersResponse [id=" + id + ", mobile=" + mobile + ", email=" + email + ", isActive=" + isActive
				+ ", isEmailSent=" + isEmailSent + ", emailSentDate=" + emailSentDate + ", orgId=" + orgId + ", fileId="
				+ fileId + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", pan=" + pan
				+ ", remarks=" + remarks + ", uploadedBy=" + uploadedBy + ", uploadType=" + uploadType
				+ ", gstPanStatus=" + gstPanStatus + ", isGstDataRetrieved=" + isGstDataRetrieved + "]";
	}
	
}
