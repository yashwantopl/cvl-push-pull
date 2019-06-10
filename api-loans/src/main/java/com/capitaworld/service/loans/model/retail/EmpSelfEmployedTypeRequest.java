package com.capitaworld.service.loans.model.retail;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmpSelfEmployedTypeRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long applicationId;

    private Integer typeOfOwnership;

    private Integer numberOfDirPartner;

    private Integer shareHolding;

    private String nameOfDirPartner;

    private String tradeLicenseNo;

    private String nameOfEntity;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date tradeLicenseExpDate;

    private Date createdDate;

    private Date modifiedDate;

    private Boolean isActive;

    private String nameOfPOAHolder;

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

    public Integer getTypeOfOwnership() {
        return typeOfOwnership;
    }

    public void setTypeOfOwnership(Integer typeOfOwnership) {
        this.typeOfOwnership = typeOfOwnership;
    }

    public Integer getNumberOfDirPartner() {
        return numberOfDirPartner;
    }

    public void setNumberOfDirPartner(Integer numberOfDirPartner) {
        this.numberOfDirPartner = numberOfDirPartner;
    }

    public Integer getShareHolding() {
        return shareHolding;
    }

    public void setShareHolding(Integer shareHolding) {
        this.shareHolding = shareHolding;
    }

    public String getNameOfDirPartner() {
        return nameOfDirPartner;
    }

    public void setNameOfDirPartner(String nameOfDirPartner) {
        this.nameOfDirPartner = nameOfDirPartner;
    }

    public String getTradeLicenseNo() {
        return tradeLicenseNo;
    }

    public void setTradeLicenseNo(String tradeLicenseNo) {
        this.tradeLicenseNo = tradeLicenseNo;
    }

    public String getNameOfEntity() {
        return nameOfEntity;
    }

    public void setNameOfEntity(String nameOfEntity) {
        this.nameOfEntity = nameOfEntity;
    }

    public Date getTradeLicenseExpDate() {
        return tradeLicenseExpDate;
    }

    public void setTradeLicenseExpDate(Date tradeLicenseExpDate) {
        this.tradeLicenseExpDate = tradeLicenseExpDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public String getNameOfPOAHolder() {
        return nameOfPOAHolder;
    }

    public void setNameOfPOAHolder(String nameOfPOAHolder) {
        this.nameOfPOAHolder = nameOfPOAHolder;
    }
}
