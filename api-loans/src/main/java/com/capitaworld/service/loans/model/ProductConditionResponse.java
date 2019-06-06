package com.capitaworld.service.loans.model;

import java.util.Date;
import java.util.List;

public class ProductConditionResponse {

    private Long id;

    private Long fpProductId;

    private String conditionName;

    private Boolean isMandatory;

    private Boolean isAllMandatory;

    private Integer allLogicalCondition;

    private Long createdBy;

    private Date createdDate;

    private Long updatedBy;

    private Date updatedDate;

    private Boolean isActive;

    private Integer productType;

    List<ProductParameterResponse> parameters;

    public ProductConditionResponse() {
    }

    public ProductConditionResponse(Long id, Long fpProductId, String conditionName, Boolean isMandatory, Boolean isAllMandatory, Integer allLogicalCondition, Boolean isActive, Integer productType) {
        this.id = id;
        this.fpProductId = fpProductId;
        this.conditionName = conditionName;
        this.isMandatory = isMandatory;
        this.isAllMandatory = isAllMandatory;
        this.allLogicalCondition = allLogicalCondition;
        this.isActive = isActive;
        this.productType = productType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFpProductId() {
        return fpProductId;
    }

    public void setFpProductId(Long fpProductId) {
        this.fpProductId = fpProductId;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public Boolean getIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(Boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public Boolean getIsAllMandatory() {
        return isAllMandatory;
    }

    public void setIsAllMandatory(Boolean isAllMandatory) {
        this.isAllMandatory = isAllMandatory;
    }

    public Integer getAllLogicalCondition() {
        return allLogicalCondition;
    }

    public void setAllLogicalCondition(Integer allLogicalCondition) {
        this.allLogicalCondition = allLogicalCondition;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public List<ProductParameterResponse> getParameters() {
        return parameters;
    }

    public void setParameters(List<ProductParameterResponse> parameters) {
        this.parameters = parameters;
    }

    public Integer getProductType() {
        return productType;
    }

    public void setProductType(Integer productType) {
        this.productType = productType;
    }
}
