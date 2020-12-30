package com.opl.mudra.api.loans.model;

import java.io.Serializable;

/**
 * Created by pooja.patel on 24-11-2020.
 */
public class ProposedVehicleRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Long applicationId;
    private Integer vehicleType;
    private Integer vehicleSegment;
    private Integer manufacturer;
    private Integer manufacturingYear;
    private Integer vehicleIs;
    private Integer vehicleAge;
    private String typeOfVehicleObt;
    private String vehicleSeg;
    private String vehicleMake;
    private String vehicleBuild;
    
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

	public Integer getVehicleAge() {
		return vehicleAge;
	}

	public void setVehicleAge(Integer vehicleAge) {
		this.vehicleAge = vehicleAge;
	}

    public String getTypeOfVehicleObt() {
        return typeOfVehicleObt;
    }

    public void setTypeOfVehicleObt(String typeOfVehicleObt) {
        this.typeOfVehicleObt = typeOfVehicleObt;
    }

    public String getVehicleSeg() {
        return vehicleSeg;
    }

    public void setVehicleSeg(String vehicleSeg) {
        this.vehicleSeg = vehicleSeg;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getVehicleBuild() {
        return vehicleBuild;
    }

    public void setVehicleBuild(String vehicleBuild) {
        this.vehicleBuild = vehicleBuild;
    }
}
