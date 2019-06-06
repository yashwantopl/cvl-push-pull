package com.capitaworld.service.loans.domain.fundprovider;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "fp_product_conditions")
public class FpProductConditions implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fp_product_id")
    private Long fpProductId;

    @Column(name = "condition_name")
    private String conditionName;

    @Column(name = "is_mandatory")
    private Boolean isMandatory;

    @Column(name = "is_all_mandatory")
    private Boolean isAllMandatory;

    @Column(name = "all_logical_condition")
    private Integer allLogicalCondition;

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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "FpProductConditions{" +
                "id=" + id +
                ", fpProductId=" + fpProductId +
                ", conditionName='" + conditionName + '\'' +
                ", isMandatory=" + isMandatory +
                ", isAllMandatory=" + isAllMandatory +
                ", logicalCondition=" + allLogicalCondition +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", updatedBy=" + updatedBy +
                ", updatedDate=" + updatedDate +
                ", isActive=" + isActive +
                '}';
    }
}
