package com.capitaworld.service.loans.domain.sidbi;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by pooja.patel on 19-06-2019.
 */

@Entity
@Table(name="fs_sidbi_raw_material_details")
public class RawMaterialDetails implements Serializable{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="application_id")
    private Long applicationId;

    @Column(name="quantity_required")
    private Long quantityRequired;

    @Column(name="import_indigenous")
    private String importIndigenous;

    @Column(name="source_suppliers")
    private String sourceSuppliers;

    @Column(name="payment_terms")
    private String paymentTerms;

    @Column(name="quantity_unit_cost")
    private Long quantityUnitCost;

    @Column(name="lead_procure_time")
    private Integer leadProcureTime;

    @Column(name="availability")
    private Boolean availability;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="created_by")
    private Long createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date")
    private Date createdDate;

    @Column(name="modified_by")
    private Long modifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_date")
    private Date modifiedDate;

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

    public Long getQuantityRequired() {
        return quantityRequired;
    }

    public void setQuantityRequired(Long quantityRequired) {
        this.quantityRequired = quantityRequired;
    }

    public String getImportIndigenous() {
        return importIndigenous;
    }

    public void setImportIndigenous(String importIndigenous) {
        this.importIndigenous = importIndigenous;
    }

    public String getSourceSuppliers() {
        return sourceSuppliers;
    }

    public void setSourceSuppliers(String sourceSuppliers) {
        this.sourceSuppliers = sourceSuppliers;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public Long getQuantityUnitCost() {
        return quantityUnitCost;
    }

    public void setQuantityUnitCost(Long quantityUnitCost) {
        this.quantityUnitCost = quantityUnitCost;
    }

    public Integer getLeadProcureTime() {
        return leadProcureTime;
    }

    public void setLeadProcureTime(Integer leadProcureTime) {
        this.leadProcureTime = leadProcureTime;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }


    public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
