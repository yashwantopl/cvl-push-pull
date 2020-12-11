package com.opl.service.loans.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by pooja.patel on 24-11-2020.
 */

@Entity
@Table(name="proposed_vehicle_detail")
public class ProposedVehicleDetail {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="application_id")
    private Long applicationId;

    @Column(name="vehicle_type")
    private Integer vehicleType;

    @Column(name="vehicle_segment")
    private Integer vehicleSegment;

    @Column(name="manufacturer")
    private Integer manufacturer;

    @Column(name="manufacturing_year")
    private Integer manufacturingYear;

    @Column(name="vehicle_is")
    private Integer vehicleIs;

    @Column(name="created_date")
    private Date createdDate;

    @Column(name="created_by")
    private Long createdBy;

    @Column(name="modified_date")
    private Date modifiedDate;

    @Column(name="modified_by")
    private Long modifiedBy;

    @Column(name="is_active")
    private Boolean isActive;
    
    @Column(name="vehicle_age")
    private Integer vehicleAge;

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

    public Integer getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(Integer vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Integer getVehicleSegment() {
        return vehicleSegment;
    }

    public void setVehicleSegment(Integer vehicleSegment) {
        this.vehicleSegment = vehicleSegment;
    }

    public Integer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Integer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getManufacturingYear() {
        return manufacturingYear;
    }

    public void setManufacturingYear(Integer manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    public Integer getVehicleIs() {
        return vehicleIs;
    }

    public void setVehicleIs(Integer vehicleIs) {
        this.vehicleIs = vehicleIs;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

	public Integer getVehicleAge() {
		return vehicleAge;
	}

	public void setVehicleAge(Integer vehicleAge) {
		this.vehicleAge = vehicleAge;
	}
    
    
}
