/**
 * 
 */
package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.Date;


/**
 * @author sanket.devare
 *
 */
public class MCAUsersInsertedResponse  implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String mobile;
    private String email;
    private String pan;
    private Boolean isEmailSent;
    private Date emailSentDate;
    private String borrowerName;
    private String remarks;
    private Boolean isLinkExpired;
    private Long uploadedBy;
    private String uploadType;
    private Boolean isMcaDataRetrived;
    private Long fileId;
    private String cin;
    private String companyName;
    
    private Boolean isMcaComplete;
    
    private String companyId;
    
    private String filename;
    
    
    			
    
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public Boolean getIsMcaComplete() {
		return isMcaComplete;
	}
	public void setIsMcaComplete(Boolean isMcaComplete) {
		this.isMcaComplete = isMcaComplete;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public MCAUsersInsertedResponse(Long id, String mobile, String email, Boolean isEmailSent, Date emailSentDate,String pan,String borrowerName,String remarks,Long uploadedBy,String uploadType,Boolean isMcaDataRetrived,Long fileId,String cin,String companyName,String companyId) {
		super();
		this.id = id;
		this.mobile = mobile;
		this.email = email;
		this.pan = pan;
		this.isEmailSent = isEmailSent;
		this.emailSentDate = emailSentDate;
		this.borrowerName=borrowerName;
		this.remarks=remarks;
		this.uploadedBy=uploadedBy;
		this.uploadType = uploadType;
		this.isMcaDataRetrived =isMcaDataRetrived;
		this.fileId = fileId;
		this.cin=cin;
		this.companyName=companyName;
		this.companyId = companyId;
	}
	public MCAUsersInsertedResponse(Long id, String mobile, String email, Boolean isEmailSent, Date emailSentDate,String pan,String remarks,Long uploadedBy,String uploadType,Boolean isMcaDataRetrived,Long fileId,String cin,String companyName, String companyId) {
		super();
		this.id = id;
		this.mobile = mobile;
		this.email = email;
		this.pan = pan;
		this.isEmailSent = isEmailSent;
		this.emailSentDate = emailSentDate;
		this.remarks=remarks;
		this.uploadedBy=uploadedBy;
		this.uploadType = uploadType;
		this.isMcaDataRetrived =isMcaDataRetrived;
		this.fileId = fileId;
		this.cin=cin;
		this.companyName=companyName;
		this.companyId = companyId;
	}
    
	public MCAUsersInsertedResponse() {
		
	}

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

	public Boolean getIsEmailSent() {
		return isEmailSent;
	}

	public void setIsEmailSent(Boolean isEmailSent) {
		this.isEmailSent = isEmailSent;
	}

	public Date getEmailSentDate() {
		return emailSentDate;
	}

	public void setEmailSentDate(Date emailSentDate) {
		this.emailSentDate = emailSentDate;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getBorrowerName() {
		return borrowerName;
	}

	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Boolean getIsLinkExpired() {
		return isLinkExpired;
	}
	public void setIsLinkExpired(Boolean isLinkExpired) {
		this.isLinkExpired = isLinkExpired;
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
	public Boolean getIsMcaDataRetrived() {
		return isMcaDataRetrived;
	}
	public void setIsMcaDataRetrived(Boolean isMcaDataRetrived) {
		this.isMcaDataRetrived = isMcaDataRetrived;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	
}
