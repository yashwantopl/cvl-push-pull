package com.opl.mudra.api.dms.model;

import java.util.Date;

/**
 * Created by dhaval on 25-Apr-17.
 */
public class StorageDetailsResponse {


	private Long id;
    private String originalFileName;
    private String filePath;
    private String message;
    private Integer isUploadFrom;
    private Integer status;
    private Long productMappingId;
    private Long proposalMappingId;
    private Date createdDate;
    private boolean isActive;

    public StorageDetailsResponse(Integer status,String originalFileName,String message) {
		super();
		this.status = status;
		this.originalFileName = originalFileName;
		this.message = message;
	}

	public StorageDetailsResponse(Long id, String originalFileName, String filePath) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.filePath = filePath;
    }

    public StorageDetailsResponse(Long id, String originalFileName, String filePath,Integer isUploadFrom) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.filePath = filePath;
        this.isUploadFrom = isUploadFrom;
    }

    public StorageDetailsResponse(Long id, String originalFileName, String filePath, Date createdDate, boolean isActive) {
        this.id = id;
        this.originalFileName = originalFileName;
        this.filePath = filePath;
        this.createdDate = createdDate;
        this.isActive = isActive;
    }

    public StorageDetailsResponse(Long id,Long proposalMappingId, String originalFileName, String filePath,Integer isUploadFrom,Boolean isActive, Date createdDate) {
        this.id = id;
        this.proposalMappingId = proposalMappingId;
        this.originalFileName = originalFileName;
        this.filePath = filePath;
        this.isUploadFrom = isUploadFrom;
        this.isActive = isActive;
        this.createdDate=createdDate;
    }

    public StorageDetailsResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    
    public Integer getIsUploadFrom() {
		return isUploadFrom;
	}

	public void setIsUploadFrom(Integer isUploadFrom) {
		this.isUploadFrom = isUploadFrom;
	}
	public Long getProductMappingId() {
		return productMappingId;
	}

	public void setProductMappingId(Long productMappingId) {
		this.productMappingId = productMappingId;
	}

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public Long getProposalMappingId() {
        return proposalMappingId;
    }

    public void setProposalMappingId(Long proposalMappingId) {
        this.proposalMappingId = proposalMappingId;
    }

    public Integer getStatus() {
		return status;
	}

	public StorageDetailsResponse setStatus(Integer status) {
		this.status = status;
		return this;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
    public String toString() {
        return "StorageDetailsResponse{" +
                "id=" + id +
                ", originalFileName='" + originalFileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", isUploadFrom=" + isUploadFrom +
                ", productMappingId=" + productMappingId +
                ", proposalMappingId=" + proposalMappingId +
                ", createdDate=" + createdDate +
                ", isActive=" + isActive +
                '}';
    }
}
