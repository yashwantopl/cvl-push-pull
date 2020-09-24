package com.opl.mudra.api.analyzer.model.common;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author jitesh.savaliya
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportRequest implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1372696341293083083L;
private Long userId;
private Long applicationId;
//private Long appId;
private Long coApplicantId;
private Long storageId;
private Long directorId;
private Long bsProfileId;
private Long bsMasterId;
private Long profileId;
private String name;
private String userToken;
private Boolean isFromSbi;
private Long[] coApplicantIds;
private List<Long> appIds;
private Boolean isFromRetail;
private List<Integer> ids;
private Boolean isFromCoOrigination;
private Boolean isRenewalAffected;
private Integer stageId;
private String noBankStatementDetail;
private Boolean isFromFederal;
private ManualBsReportRequest manualBsReportRequest;

private List<Long> applicationid;

public List<Long> getApplicationid() {
	return applicationid;
}
public void setApplicationid(List<Long> applicationid) {
	this.applicationid = applicationid;
}
public Long getUserId() {
	return userId;
}
public void setUserId(Long userId) {
	this.userId = userId;
}
public Long getApplicationId() {
	return applicationId;
}
public void setApplicationId(Long applicationId) {
	this.applicationId = applicationId;
}
public ReportRequest(Long bsMasterId) {
	super();
	this.bsMasterId = bsMasterId;
}

public ReportRequest(Long bsMasterId,Boolean isFromSbi) {
	super();
	this.bsMasterId = bsMasterId;
	this.isFromSbi = isFromSbi;
}


public ReportRequest() {
	super();
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Long getDirectorId() {
	return directorId;
}
public void setDirectorId(Long directorId) {
	this.directorId = directorId;
}
public String getUserToken() {
	return userToken;
}
public void setUserToken(String userToken) {
	this.userToken = userToken;
}
public Long getStorageId() {
	return storageId;
}
public void setStorageId(Long storageId) {
	this.storageId = storageId;
}
public ReportRequest(Long userId, Long applicationId, Long storageId, Long directorId, String name, String userToken, Long coApplicantId) {
	super();
	this.userId = userId;
	this.applicationId = applicationId;
	this.storageId = storageId;
	this.directorId = directorId;
	this.name = name;
	this.userToken = userToken;
	this.coApplicantId = coApplicantId;
}
public Boolean getIsFromSbi() {
	return isFromSbi;
}
public void setIsFromSbi(Boolean isFromSbi) {
	this.isFromSbi = isFromSbi;
}
public Long getCoApplicantId() {
	return coApplicantId;
}
public void setCoApplicantId(Long coApplicantId) {
	this.coApplicantId = coApplicantId;
}
public Long[] getCoApplicantIds() {
	return coApplicantIds;
}
public void setCoApplicantIds(Long[] coApplicantIds) {
	this.coApplicantIds = coApplicantIds;
}
public List<Long> getAppIds() {
	return appIds;
}
public void setAppIds(List<Long> appIds) {
	this.appIds = appIds;
}
public Boolean getIsFromRetail() {
	return isFromRetail;
}
public void setIsFromRetail(Boolean isFromRetail) {
	this.isFromRetail = isFromRetail;
}
public List<Integer> getIds() {
	return ids;
}
public void setIds(List<Integer> ids) {
	this.ids = ids;
}
public Boolean getIsFromCoOrigination() {
	return isFromCoOrigination;
}
public void setIsFromCoOrigination(Boolean isFromCoOrigination) {
	this.isFromCoOrigination = isFromCoOrigination;
}
public Boolean getIsRenewalAffected() {
	return isRenewalAffected;
}
public void setIsRenewalAffected(Boolean isRenewalAffected) {
	this.isRenewalAffected = isRenewalAffected;
}
public Integer getStageId() {
	return stageId;
}
public void setStageId(Integer stageId) {
	this.stageId = stageId;
}

	public Long getBsProfileId() {
		return bsProfileId;
	}

	public void setBsProfileId(Long bsProfileId) {
		this.bsProfileId = bsProfileId;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public Long getBsMasterId() {
		return bsMasterId;
	}

	public void setBsMasterId(Long bsMasterId) {
		this.bsMasterId = bsMasterId;
	}
	
	public String getNoBankStatementDetail() {
		return noBankStatementDetail;
	}
	
	public void setNoBankStatementDetail(String noBankStatementDetail) {
		this.noBankStatementDetail = noBankStatementDetail;
	}
	public Boolean getIsFromFederal() {
		return isFromFederal;
	}
	public void setIsFromFederal(Boolean isFromFederal) {
		this.isFromFederal = isFromFederal;
	}
	public ManualBsReportRequest getManualBsReportRequest() {
		return manualBsReportRequest;
	}
	public void setManualBsReportRequest(ManualBsReportRequest manualBsReportRequest) {
		this.manualBsReportRequest = manualBsReportRequest;
	}
//	public Long getAppId() {
//		return appId;
//	}
//	public void setAppId(Long appId) {
//		this.appId = appId;
//	}
}

