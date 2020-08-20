package com.opl.mudra.api.user.model;

/**
 * Created by dhaval.panchal on 23-Nov-18.
 */
public class SanctionedResponse {

    private String name;
    private String pan;
    private String constitution;
    private String mobile;
    private String email;
    private String sanctionAmount;
    private String disburseAmount;
    private String message;

    public SanctionedResponse(String name, String pan, String constitution, String mobile, String email, String sanctionAmount, String disburseAmount, String message) {
        this.name = name;
        this.pan = pan;
        this.constitution = constitution;
        this.mobile = mobile;
        this.email = email;
        this.sanctionAmount = sanctionAmount;
        this.disburseAmount = disburseAmount;
        this.message = message;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
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

    public String getSanctionAmount() {
        return sanctionAmount;
    }

    public void setSanctionAmount(String sanctionAmount) {
        this.sanctionAmount = sanctionAmount;
    }

    public String getDisburseAmount() {
        return disburseAmount;
    }

    public void setDisburseAmount(String disburseAmount) {
        this.disburseAmount = disburseAmount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
