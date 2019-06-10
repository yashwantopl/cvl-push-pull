package com.capitaworld.service.loans.model;

import java.util.Date;
import java.util.List;

public class ProductParameterResponse {
    private Long id;

    private Long productId;

    private Long conditionId;

    private Long bodmasFormulaId;

    private Double compareValue;

    private Double minValue;

    private Double maxValue;

    private Integer parameterOperator;

    private Integer logicalCondition;

    private Integer level;

    private Long parentId;

    private Boolean isGroup;

    private Long createdBy;

    private Date createdDate;

    private Long updatedBy;

    private Date updatedDate;

    private Boolean isActive;

    private String formulaName;

    private List<ProductParameterResponse> parameters;

    public ProductParameterResponse() {
    }

    public ProductParameterResponse(Long id, Long conditionId, Long bodmasFormulaId, Double compareValue, Double minValue, Double maxValue, Integer logicalCondition, Integer parameterOperator, Long parentId, Boolean isGroup, Integer level, Boolean isActive) {
        this.id = id;
        this.conditionId = conditionId;
        this.bodmasFormulaId = bodmasFormulaId;
        this.compareValue = compareValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.parameterOperator = parameterOperator;
        this.logicalCondition = logicalCondition;
        this.level = level;
        this.parentId = parentId;
        this.isGroup = isGroup;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getConditionId() {
        return conditionId;
    }

    public void setConditionId(Long conditionId) {
        this.conditionId = conditionId;
    }

    public Long getBodmasFormulaId() {
        return bodmasFormulaId;
    }

    public void setBodmasFormulaId(Long bodmasFormulaId) {
        this.bodmasFormulaId = bodmasFormulaId;
    }

    public Double getCompareValue() {
        return compareValue;
    }

    public void setCompareValue(Double compareValue) {
        this.compareValue = compareValue;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public Integer getParameterOperator() {
        return parameterOperator;
    }

    public void setParameterOperator(Integer parameterOperator) {
        this.parameterOperator = parameterOperator;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public Boolean getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(Boolean isGroup) {
        this.isGroup = isGroup;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getLogicalCondition() {
        return logicalCondition;
    }

    public void setLogicalCondition(Integer logicalCondition) {
        this.logicalCondition = logicalCondition;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public List<ProductParameterResponse> getParameters() {
        return parameters;
    }

    public void setParameters(List<ProductParameterResponse> parameters) {
        this.parameters = parameters;
    }
}
