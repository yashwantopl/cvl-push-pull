/**
 * 
 */
package com.opl.mudra.api.user.model;

import java.util.Date;

/**
 * @author vijay.chauhan
 *
 * May 7, 2020
 */

public class StadaloneApplicationAuditLogReq {
    private String fileType;
    private Long fpUserId;
    private Long fsUserId;
    private String stage;
    private Integer stageId;
    private Date createdDate;
    private String message;
    
    public StadaloneApplicationAuditLogReq() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StadaloneApplicationAuditLogReq(String fileType, Long fpUserId, Long fsUserId, String stage) {
		super();
		this.fileType = fileType;
		this.fpUserId = fpUserId;
		this.fsUserId = fsUserId;
		this.stage = stage;
	}
	public String getAuditDateTime() {
		return auditDateTime;
	}
	public void setAuditDateTime(String auditDateTime) {
		this.auditDateTime = auditDateTime;
	}
	private String auditDateTime;
	
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Long getFpUserId() {
		return fpUserId;
	}
	public void setFpUserId(Long fpUserId) {
		this.fpUserId = fpUserId;
	}
	public Long getFsUserId() {
		return fsUserId;
	}
	public void setFsUserId(Long fsUserId) {
		this.fsUserId = fsUserId;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Integer getStageId() {
		return stageId;
	}
	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}
	@Override
	public String toString() {
		return "StadaloneApplicationAuditLogReq [fileType=" + fileType + ", fpUserId=" + fpUserId + ", fsUserId="
				+ fsUserId + ", stage=" + stage + ", stageId=" + stageId + ", createdDate=" + createdDate + ", message="
				+ message + ", auditDateTime=" + auditDateTime + "]";
	}

	
}
