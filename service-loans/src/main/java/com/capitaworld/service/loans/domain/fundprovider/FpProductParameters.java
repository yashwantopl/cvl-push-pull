package com.capitaworld.service.loans.domain.fundprovider;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "fp_product_parameters")
public class FpProductParameters implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "condition_id")
    private Long conditionId;

    @Column(name = "bodmas_formula_id")
    private Long bodmasFormulaId;

    @Column(name = "compare_value")
    private Double compareValue;

    @Column(name = "min_value")
    private Double minValue;

    @Column(name = "max_value")
    private Double maxValue;

    @Column(name = "parameter_operator")
    private Integer parameterOperator;

    @Column(name = "logical_condition")
    private Integer logicalCondition;

    private Integer level;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "is_group")
    private Boolean isGroup;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "is_active")
    private Boolean isActive;

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

    @Override
    public String toString() {
        return "FpProductParameters{" +
                "id=" + id +
                ", productId=" + productId +
                ", conditionId=" + conditionId +
                ", bodmasFormulaId=" + bodmasFormulaId +
                ", compareValue=" + compareValue +
                ", minValue=" + minValue +
                ", maxValue=" + maxValue +
                ", parameterOperator=" + parameterOperator +
                ", logicalCondition=" + logicalCondition +
                ", parentId=" + parentId +
                ", isGroup=" + isGroup +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", updatedBy=" + updatedBy +
                ", updatedDate=" + updatedDate +
                ", isActive=" + isActive +
                '}';
    }
}
