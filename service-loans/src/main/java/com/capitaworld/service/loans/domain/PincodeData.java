package com.capitaworld.service.loans.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pincode_data")
public class PincodeData implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="office_name")
    private String officeName;

    @Column(name="pincode")
    private String pincode;

    @Column(name="office_type")
    private String officeType;

    @Column(name="delivery_status")
    private String deliveryStatus;

    @Column(name="division_name")
    private String divisionName;

    @Column(name="region_name")
    private String regionName;

    @Column(name="circle_name")
    private String circleName;

    @Column(name="taluka")
    private String taluka;

    @Column(name="district_name")
    private String districtName;

    @Column(name="state_name")
    private String stateName;

    //constructor
    public PincodeData() {

    }

    //getter setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getOfficeType() {
        return officeType;
    }

    public void setOfficeType(String officeType) {
        this.officeType = officeType;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCircleName() {
        return circleName;
    }

    public void setCircleName(String circleName) {
        this.circleName = circleName;
    }

    public String getTaluka() {
        return taluka;
    }

    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
