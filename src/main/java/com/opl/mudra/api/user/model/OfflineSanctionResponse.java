package com.opl.mudra.api.user.model;

import java.util.Date;

/**
 * Created by dhaval.panchal on 25-Dec-18.
 */
public class OfflineSanctionResponse {
    private Long id;
    private String originalFileName;
    private String filePath;
    private Integer isUploadFrom;
    private Long productMappingId;
    private Date createdDate;
    private boolean isActive;
    private Long uploadedCount;

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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getUploadedCount() {
        return uploadedCount;
    }

    public void setUploadedCount(Long uploadedCount) {
        this.uploadedCount = uploadedCount;
    }
}
