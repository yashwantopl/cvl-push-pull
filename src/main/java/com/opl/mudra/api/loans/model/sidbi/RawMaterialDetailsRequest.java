package com.opl.mudra.api.loans.model.sidbi;

/**
 * Created by pooja.patel on 19-06-2019.
 */
public class RawMaterialDetailsRequest {

    private Long id;
    private Long quantityRequired;
    private String importIndigenous;
    private String sourceSuppliers;
    private String paymentTerms;
    private Long quantityUnitCost;
    private Double leadProcureTime;
    private Boolean availability;
    private String nameOfRawMaterial;
    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getLeadProcureTime() {
        return leadProcureTime;
    }

    public void setLeadProcureTime(Double leadProcureTime) {
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

    public String getNameOfRawMaterial() {
        return nameOfRawMaterial;
    }

    public void setNameOfRawMaterial(String nameOfRawMaterial) {
        this.nameOfRawMaterial = nameOfRawMaterial;
    }
}
