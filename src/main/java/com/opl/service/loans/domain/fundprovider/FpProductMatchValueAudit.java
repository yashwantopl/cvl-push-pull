package com.opl.service.loans.domain.fundprovider;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fp_product_match_value_audit")
public class FpProductMatchValueAudit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "parameter_id")
    private Long parameterId;

    @Column(name = "bodmas_formula_id")
    private Long bodmasFormulaId;

    @Column(name = "formula_answer")
    private Double formulaAnswer;

    @Column(name = "max_val")
    private Double maxVal;

    @Column(name = "min_val")
    private Double minVal;

    @Column(name = "compare_val")
    private Double compareVal;

    @Column(name = "condition_id")
    private Integer conditionId;

    @Column(name = "is_match")
    private Boolean isMatch;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "is_active")
    private boolean isActive;


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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getParameterId() {
        return parameterId;
    }

    public void setParameterId(Long parameterId) {
        this.parameterId = parameterId;
    }

    public Long getBodmasFormulaId() {
        return bodmasFormulaId;
    }

    public void setBodmasFormulaId(Long bodmasFormulaId) {
        this.bodmasFormulaId = bodmasFormulaId;
    }

    public Double getFormulaAnswer() {
        return formulaAnswer;
    }

    public void setFormulaAnswer(Double formulaAnswer) {
        this.formulaAnswer = formulaAnswer;
    }

    public Double getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(Double maxVal) {
        this.maxVal = maxVal;
    }

    public Double getMinVal() {
        return minVal;
    }

    public void setMinVal(Double minVal) {
        this.minVal = minVal;
    }

    public Double getCompareVal() {
        return compareVal;
    }

    public void setCompareVal(Double compareVal) {
        this.compareVal = compareVal;
    }

    public Integer getConditionId() {
        return conditionId;
    }

    public void setConditionId(Integer conditionId) {
        this.conditionId = conditionId;
    }

    public Boolean getIsMatch() {
        return isMatch;
    }

    public void setIsMatch(Boolean isMatch) {
        this.isMatch = isMatch;
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

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "FpProductMatchValueAudit{" +
                "id=" + id +
                ", applicationId=" + applicationId +
                ", productId=" + productId +
                ", parameterId=" + parameterId +
                ", bodmasFormulaId=" + bodmasFormulaId +
                ", formulaAnswer=" + formulaAnswer +
                ", maxVal=" + maxVal +
                ", minVal=" + minVal +
                ", compareVal=" + compareVal +
                ", conditionId=" + conditionId +
                ", isMatch=" + isMatch +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", isActive=" + isActive +
                '}';
    }
}
