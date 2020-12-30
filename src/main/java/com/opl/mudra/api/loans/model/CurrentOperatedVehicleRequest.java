package com.opl.mudra.api.loans.model;

import java.io.Serializable;

/**
 * Created by pooja.patel on 24-11-2020.
 */
public class CurrentOperatedVehicleRequest implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
    private Long applicationId;
    private Integer vehicleType;
    private Integer mfgYear;
    private Double vehicleCost;
    private Double vehicleIncome;
    private String typeOfVehicle;
    

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

    public Integer getMfgYear() {
        return mfgYear;
    }

    public void setMfgYear(Integer mfgYear) {
        this.mfgYear = mfgYear;
    }

    public Double getVehicleCost() {
        return vehicleCost;
    }

    public void setVehicleCost(Double vehicleCost) {
        this.vehicleCost = vehicleCost;
    }

    public Double getVehicleIncome() {
        return vehicleIncome;
    }

    public void setVehicleIncome(Double vehicleIncome) {
        this.vehicleIncome = vehicleIncome;
    }

    public String getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public void setTypeOfVehicle(String typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }
}
