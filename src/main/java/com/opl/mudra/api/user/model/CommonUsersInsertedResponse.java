/**
 * 
 */
package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * @author nilay.darji
 *
 */
public class CommonUsersInsertedResponse  implements Serializable{

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
    private Boolean isItrDataRetrived;
    private Long fileId;    
    private Boolean isGstDataRetrieved;
    private Double loanAmount;
    private Boolean isBsDataRetrived;
    private Boolean isMcaDataRetrived;
    private String cin;
    private String companyName;
    private Boolean isMcaComplete;
    private String companyId;
    private String filename;
    private String circle;
    private String module;
    private String branchCode;
    private String branch;
    private String branchEmailId;
    private String cif;
    private String accountNo;
    private String facility;
    private String explosureRsInCR;
    private String gstPanStatus;    	
    private Date createdDate;
    private Boolean isTick = false;
    private Integer stage;
    private Integer status;
    private Integer flowType;
    
    //itr
	public CommonUsersInsertedResponse(Long id, String mobile, String email, Boolean isEmailSent, Date emailSentDate,String pan,String borrowerName,String remarks,Long uploadedBy,String uploadType,Boolean isItrDataRetrived,Long fileId,Date createdDate) {
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
		this.isItrDataRetrived =isItrDataRetrived;
		this.fileId = fileId;
		this.createdDate = createdDate;
	}
	public CommonUsersInsertedResponse(Long id, String mobile, String email, Boolean isEmailSent, Date emailSentDate,String pan,String remarks,Long uploadedBy,String uploadType,Boolean isItrDataRetrived,Long fileId) {
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
		this.isItrDataRetrived =isItrDataRetrived;
		this.fileId = fileId;
	}
	//gst
	public CommonUsersInsertedResponse(Long id, String mobile, String email, Boolean isEmailSent, Date emailSentDate,String pan,String borrowerName,String circle,String module,String branchCode,String branch,String cif,String accountNo,String facility,String explosureRsInCR,String gstPanStatus,String remarks,Boolean isGstDataRetrieved,Long uploadedBy,Double loanAmount,Date createdDate) {
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
		this.branch = branch;
		this.cif = cif;
		this.accountNo = accountNo;
		this.facility = facility;
		this.explosureRsInCR = explosureRsInCR;
		this.gstPanStatus=gstPanStatus;
		this.remarks=remarks;
		this.isGstDataRetrieved=isGstDataRetrieved;
		this.uploadedBy=uploadedBy;
		this.loanAmount=loanAmount;
		this.createdDate = createdDate;
	}
	public CommonUsersInsertedResponse(Long id, String mobile, String email, Boolean isEmailSent, Date emailSentDate,String pan,String gstPanStatus,String remarks,Boolean isGstDataRetrieved,Long uploadedBy,Double loanAmount) {
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
		this.loanAmount=loanAmount;
	}
	
	//Eclgs
	
	public CommonUsersInsertedResponse(Long id, String mobile, String email, Boolean isEmailSent, Date emailSentDate,String pan,String borrowerName,String circle,String branchCode,String branchEmailId,String cif,String accountNo,String facility,String explosureRsInCR,String gstPanStatus,String remarks,Boolean isGstDataRetrieved,Long uploadedBy,Double loanAmount,Date createdDate,Integer stage,Integer status,Integer flowType) {
		super();
		this.id = id;
		this.mobile = mobile;
		this.email = email;
		this.pan = pan;
		this.isEmailSent = isEmailSent;
		this.emailSentDate = emailSentDate;
		this.borrowerName=borrowerName;
		this.circle=circle;
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
		this.loanAmount=loanAmount;
		this.createdDate = createdDate;
		this.stage = stage;
		this.status= status;
		this.flowType = flowType;
	}
	
	//bs
	
	public CommonUsersInsertedResponse(Long id, String mobile, String email, Boolean isEmailSent, Date emailSentDate,String pan,String borrowerName,String remarks,Long uploadedBy,String uploadType,Boolean isBsDataRetrived, Date createdDate) {
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
		this.isBsDataRetrived=isBsDataRetrived;
		this.createdDate = createdDate;
	}
	public CommonUsersInsertedResponse(Long id, String mobile, String email, Boolean isEmailSent,Date emailSentDate,String pan,String remarks,Long uploadedBy,String uploadType,Boolean isBsDataRetrived) {
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
		this.isBsDataRetrived=isBsDataRetrived;
	}
    
	// mca
	
	public CommonUsersInsertedResponse(Long id, String mobile, String email, Boolean isEmailSent, Date emailSentDate,String pan,String borrowerName,String remarks,Long uploadedBy,String uploadType,Boolean isMcaDataRetrived,Long fileId,String cin,String companyName,String companyId,Date createdDate) {
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
		this.createdDate = createdDate;
	}
	public CommonUsersInsertedResponse(Long id, String mobile, String email, Boolean isEmailSent, Date emailSentDate,String pan,String remarks,Long uploadedBy,String uploadType,Boolean isMcaDataRetrived,Long fileId,String cin,String companyName, String companyId) {
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
	public CommonUsersInsertedResponse() {
		
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
	public Boolean getIsItrDataRetrived() {
		return isItrDataRetrived;
	}
	public void setIsItrDataRetrived(Boolean isItrDataRetrived) {
		this.isItrDataRetrived = isItrDataRetrived;
	}
	public Long getFileId() {
		return fileId;
	}
	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}
	public Boolean getIsGstDataRetrieved() {
		return isGstDataRetrieved;
	}
	public void setIsGstDataRetrieved(Boolean isGstDataRetrieved) {
		this.isGstDataRetrieved = isGstDataRetrieved;
	}
	
	public Double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public Boolean getIsBsDataRetrived() {
		return isBsDataRetrived;
	}
	public void setIsBsDataRetrived(Boolean isBsDataRetrived) {
		this.isBsDataRetrived = isBsDataRetrived;
	}
	public Boolean getIsMcaDataRetrived() {
		return isMcaDataRetrived;
	}
	public void setIsMcaDataRetrived(Boolean isMcaDataRetrived) {
		this.isMcaDataRetrived = isMcaDataRetrived;
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
	public Boolean getIsMcaComplete() {
		return isMcaComplete;
	}
	public void setIsMcaComplete(Boolean isMcaComplete) {
		this.isMcaComplete = isMcaComplete;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
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
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
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
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Boolean getIsTick() {
		return isTick;
	}
	public void setIsTick(Boolean isTick) {
		this.isTick = isTick;
	}
	public String getBranchEmailId() {
		return branchEmailId;
	}
	public void setBranchEmailId(String branchEmailId) {
		this.branchEmailId = branchEmailId;
	}
	public Integer getStage() {
		return stage;
	}
	public void setStage(Integer stage) {
		this.stage = stage;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getFlowType() {
		return flowType;
	}
	public void setFlowType(Integer flowType) {
		this.flowType = flowType;
	}	
}
