package com.opl.mudra.api.connect;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The persistent class for the connect_log_audit database table.
 * 
 */
/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConnectLogAuditRequest extends AuditLogRequest {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long applicationId;

	private Long connectLogId;

	private String requestId;

	private Integer stageId;

	private Integer stepId;

	private Long userId;

	private Integer status;

	private String gstin;
	
	private String email;

	private String mobile;
	
	private String userName;

	private String organization;

	private String stageName;

	private String reason;

	private String code;

	private String jsonData;
	
	private Integer businessTypeId;

	public ConnectLogAuditRequest(Long applicationId, Integer stageId, Long userId, String reason, String code,
			Integer businessTypeId) {
		super();
		this.applicationId = applicationId;
		this.stageId = stageId;
		this.userId = userId;
		this.reason = reason;
		this.code = code;
		this.businessTypeId = businessTypeId;
	}
	
	public ConnectLogAuditRequest(Long applicationId, Integer stageId, Long userId, String reason, String code,
			Integer businessTypeId,String jsonData) {
		super();
		this.applicationId = applicationId;
		this.stageId = stageId;
		this.userId = userId;
		this.reason = reason;
		this.code = code;
		this.businessTypeId = businessTypeId;
		this.jsonData = jsonData;
	}

	public ConnectLogAuditRequest() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getConnectLogId() {
		return connectLogId;
	}

	public void setConnectLogId(Long connectLogId) {
		this.connectLogId = connectLogId;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public Integer getStageId() {
		return stageId;
	}

	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}

	public Integer getStepId() {
		return stepId;
	}

	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	@Override
	public String toString() {
		return "ConnectLogAuditRequest [id=" + id + ", applicationId=" + applicationId + ", connectLogId="
				+ connectLogId + ", requestId=" + requestId + ", stageId=" + stageId + ", stepId=" + stepId
				+ ", userId=" + userId + ", status=" + status + ", gstin=" + gstin + ", email=" + email + ", mobile="
				+ mobile + ", userName=" + userName + ", organization=" + organization + ", stageName=" + stageName
				+ ", reason=" + reason + ", code=" + code + ", jsonData=" + jsonData + ", businessTypeId="
				+ businessTypeId + "]";
	}
}