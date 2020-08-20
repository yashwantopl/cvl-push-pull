/**
 * 
 */
package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author nilay.darji
 * 
 */
public class EclgsGstUsersInsertedResponse  implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String mobile;
    private String email;
    private String pan;
    private Boolean isEmailSent;
    private Date emailSentDate;
    private String borrowerName;
    private String circle;
    private String module;
    private String branchCode;
    private String branchEmailId;
    private String cif;
    private String accountNo;
    private String facility;
    private String explosureRsInCR;
    private String gstPanStatus;
    private String remarks;
    private Boolean isGstDataRetrieved;
    private Boolean isLinkExpired;
    private Long uploadedBy;
    private Integer amountOfLoan;
    
	public EclgsGstUsersInsertedResponse(Long id, String mobile, String email, Boolean isEmailSent, Date emailSentDate,String pan,String borrowerName,String circle,String module,String branchCode,String branchEmailId,String cif,String accountNo,String facility,String explosureRsInCR,String gstPanStatus,String remarks,Boolean isGstDataRetrieved,Long uploadedBy,Integer amountOfLoan) {
		super();
		this.id = id;
		this.mobile = mobile;
		this.email = email;
		this.pan = pan;
		this.isEmailSent = isEmailSent;
		this.emailSentDate = emailSentDate;
		this.borrowerName=borrowerName;
		this.circle=circle;
		this.module = module;
		this.branchCode = branchCode;
		this.branchEmailId = branchEmailId;
		this.cif = cif;
		this.accountNo = accountNo;
		this.facility = facility;
		this.explosureRsInCR = explosureRsInCR;
		this.gstPanStatus=gstPanStatus;
		this.remarks=remarks;
		this.isGstDataRetrieved=isGstDataRetrieved;
		this.uploadedBy=uploadedBy;
		this.amountOfLoan=amountOfLoan;
	}
	public EclgsGstUsersInsertedResponse(Long id, String mobile, String email, Boolean isEmailSent, Date emailSentDate,String pan,String gstPanStatus,String remarks,Boolean isGstDataRetrieved,Long uploadedBy,Integer amountOfLoan) {
		super();
		this.id = id;
		this.mobile = mobile;
		this.email = email;
		this.pan = pan;
		this.isEmailSent = isEmailSent;
		this.emailSentDate = emailSentDate;
		this.gstPanStatus=gstPanStatus;
		this.remarks=remarks;
		this.isGstDataRetrieved=isGstDataRetrieved;
		this.uploadedBy=uploadedBy;
		this.amountOfLoan=amountOfLoan;
	}
    
	public EclgsGstUsersInsertedResponse() {
		
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

	public String getCircle() {
		return circle;
	}

	public void setCircle(String circle) {
		this.circle = circle;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}


	public String getBranchEmailId() {
		return branchEmailId;
	}
	public void setBranchEmailId(String branchEmailId) {
		this.branchEmailId = branchEmailId;
	}
	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public String getExplosureRsInCR() {
		return explosureRsInCR;
	}

	public void setExplosureRsInCR(String explosureRsInCR) {
		this.explosureRsInCR = explosureRsInCR;
	}
	public String getGstPanStatus() {
		return gstPanStatus;
	}
	public void setGstPanStatus(String gstPanStatus) {
		this.gstPanStatus = gstPanStatus;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Boolean getIsGstDataRetrieved() {
		return isGstDataRetrieved;
	}
	public void setIsGstDataRetrieved(Boolean isGstDataRetrieved) {
		this.isGstDataRetrieved = isGstDataRetrieved;
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
	public Integer getAmountOfLoan() {
		return amountOfLoan;
	}
	public void setAmountOfLoan(Integer amountOfLoan) {
		this.amountOfLoan = amountOfLoan;
	}    
}
