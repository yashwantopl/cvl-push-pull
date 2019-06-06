package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.List;

public class ProductParameterRequest implements Serializable {

    private Long id;

    private Long productId;

    private Long parentId;

    private Long conditionId;

    private Long userId;

    private String conditionName;

    private Boolean isMandatory;

    private Boolean isAllMandatory;

    private List<ProductParameterRequest> group;

    private List<ProductParameterRequest> parameters;

    private Double compareValue;

    private Double minValue;

    private Double maxValue;

    private Long bodmasFormulaId;

    private Boolean isGroup;

    private Integer parameterOperator;

    private Integer logicalCondition;

    private Integer level;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<ProductParameterRequest> getGroup() {
        return group;
    }

    public void setGroup(List<ProductParameterRequest> group) {
        this.group = group;
    }

    public List<ProductParameterRequest> getParameters() {
        return parameters;
    }

    public void setParameters(List<ProductParameterRequest> parameters) {
        this.parameters = parameters;
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

    public Long getBodmasFormulaId() {
        return bodmasFormulaId;
    }

    public void setBodmasFormulaId(Long bodmasFormulaId) {
        this.bodmasFormulaId = bodmasFormulaId;
    }

    public Boolean getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(Boolean isGroup) {
        this.isGroup = isGroup;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getConditionId() {
        return conditionId;
    }

    public void setConditionId(Long conditionId) {
        this.conditionId = conditionId;
    }

    public Integer getParameterOperator() {
        return parameterOperator;
    }

    public void setParameterOperator(Integer parameterOperator) {
        this.parameterOperator = parameterOperator;
    }

    public Integer getLogicalCondition() {
        return logicalCondition;
    }

    public void setLogicalCondition(Integer logicalCondition) {
        this.logicalCondition = logicalCondition;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
