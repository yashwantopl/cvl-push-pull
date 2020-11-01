package com.opl.mudra.api.dms.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;


/**
 * Created by dhaval on 17-Apr-17.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class DocumentRequest implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private Long applicationId;
	private Long proposalMappingId;
    private Long directorId;
    private Long userId;
    private Long documentId;
    private Long companyId;
    private Long productDocumentMappingId;
    private Long userDocumentMappingId;
    private Long coApplicantId;
    private Long guarantorId;
    private String originalFileName;
    private String userType;
    //14 Aug For DDR Uploads(harshit)
    private Integer isUploadFrom;
    private List<Long> proMapIds;
    private Long profileId;
    private Long moduleMasterId;
    private List<String> bsStorageIds;
    private List<Long> storageIds;
    
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

    public Long getProductDocumentMappingId() {
        return productDocumentMappingId;
    }

    public void setProductDocumentMappingId(Long productDocumentMappingId) {
        this.productDocumentMappingId = productDocumentMappingId;
    }

    public Long getCoApplicantId() {
        return coApplicantId;
    }

    public void setCoApplicantId(Long coApplicantId) {
        this.coApplicantId = coApplicantId;
    }

    public Long getGuarantorId() {
        return guarantorId;
    }

    public void setGuarantorId(Long guarantorId) {
        this.guarantorId = guarantorId;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Long getUserDocumentMappingId() {
        return userDocumentMappingId;
    }

    public void setUserDocumentMappingId(Long userDocumentMappingId) {
        this.userDocumentMappingId = userDocumentMappingId;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    public Integer getIsUploadFrom() {
		return isUploadFrom;
	}

	public void setIsUploadFrom(Integer isUploadFrom) {
		this.isUploadFrom = isUploadFrom;
	}

	public Long getDirectorId() {
		return directorId;
	}
	public void setDirectorId(Long directorId) {
		this.directorId = directorId;
	}
	
	public List<Long> getProMapIds() {
		return proMapIds;
	}

	public void setProMapIds(List<Long> proMapIds) {
		this.proMapIds = proMapIds;
	}
	@Override
	public String toString() {
		return "DocumentRequest [applicationId=" + applicationId + ", directorId=" + directorId + ", userId=" + userId
				+ ", documentId=" + documentId + ", companyId=" + companyId + ", productDocumentMappingId="
				+ productDocumentMappingId + ", userDocumentMappingId=" + userDocumentMappingId + ", coApplicantId="
				+ coApplicantId + ", guarantorId=" + guarantorId + ", originalFileName=" + originalFileName
				+ ", userType=" + userType + ", isUploadFrom=" + isUploadFrom + ", proMapIds=" + proMapIds + "]";
	}

    public Long getProposalMappingId() {
        return proposalMappingId;
    }

    public void setProposalMappingId(Long proposalMappingId) {
        this.proposalMappingId = proposalMappingId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getModuleMasterId() {
        return moduleMasterId;
    }

    public void setModuleMasterId(Long moduleMasterId) {
        this.moduleMasterId = moduleMasterId;
    }

	public List<String> getBsStorageIds() {
		return bsStorageIds;
	}

	public void setBsStorageIds(List<String> bsStorageIds) {
		this.bsStorageIds = bsStorageIds;
	}

	public List<Long> getStorageIds() {
		return storageIds;
	}

	public void setStorageIds(List<Long> storageIds) {
		this.storageIds = storageIds;
	}
}
