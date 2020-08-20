package com.opl.mudra.api.user.model;

import java.io.Serializable;

/**
 * Created by dhaval.panchal on 18-Oct-18.
 */
public class BankUserInsertedResponse implements Serializable {

    private String nameOfEntity;
    private String panOfEntity;
    private String constitution;
    private String mobile;
    private String email;
    private String facityType;
    private String sanctionedAmount;
    private String renewalAmount;

    /*constructors*/
    public BankUserInsertedResponse(String nameOfEntity, String panOfEntity, String constitution, String mobile, String email, String facityType, String sanctionedAmount) {
        this.nameOfEntity = nameOfEntity;
        this.panOfEntity = panOfEntity;
        this.constitution = constitution;
        this.mobile = mobile;
        this.email = email;
        this.facityType = facityType;
        this.sanctionedAmount = sanctionedAmount;
    }

    public BankUserInsertedResponse(String nameOfEntity, String panOfEntity, String constitution, String mobile, String email, String facityType, String sanctionedAmount, String renewalAmount) {
        this.nameOfEntity = nameOfEntity;
        this.panOfEntity = panOfEntity;
        this.constitution = constitution;
        this.mobile = mobile;
        this.email = email;
        this.facityType = facityType;
        this.sanctionedAmount = sanctionedAmount;
        this.renewalAmount = renewalAmount;
    }

    public BankUserInsertedResponse() {

    }
    /*constructors*/

    /*getter setter*/
    public String getNameOfEntity() {
        return nameOfEntity;
    }

    public void setNameOfEntity(String nameOfEntity) {
        this.nameOfEntity = nameOfEntity;
    }

    public String getPanOfEntity() {
        return panOfEntity;
    }

    public void setPanOfEntity(String panOfEntity) {
        this.panOfEntity = panOfEntity;
    }

    public String getConstitution() {
        return constitution;
    }

    public void setConstitution(String constitution) {
        this.constitution = constitution;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacityType() {
        return facityType;
    }

    public void setFacityType(String facityType) {
        this.facityType = facityType;
    }

    public String getSanctionedAmount() {
        return sanctionedAmount;
    }

    public void setSanctionedAmount(String sanctionedAmount) {
        this.sanctionedAmount = sanctionedAmount;
    }

    public String getRenewalAmount() {
        return renewalAmount;
    }

    public void setRenewalAmount(String renewalAmount) {
        this.renewalAmount = renewalAmount;
    }
    /*getter setter*/
}
