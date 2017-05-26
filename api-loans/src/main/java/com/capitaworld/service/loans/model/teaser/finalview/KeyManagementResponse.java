package com.capitaworld.service.loans.model.teaser.finalview;

import java.io.Serializable;

/**
 * Created by dhaval on 25-May-17.
 */
public class KeyManagementResponse implements Serializable{
    private static final long serialVersionUID = 1L;

    private String anySpecialAchievement;

    private String designation;

    private String experience;

    private String functionalDuties;

    private String name;

    private String qualification;

    public KeyManagementResponse(String anySpecialAchievement, String designation, String experience, String functionalDuties, String name, String qualification) {
        this.anySpecialAchievement = anySpecialAchievement;
        this.designation = designation;
        this.experience = experience;
        this.functionalDuties = functionalDuties;
        this.name = name;
        this.qualification = qualification;
    }

    public KeyManagementResponse() {
    }

    public String getAnySpecialAchievement() {
        return anySpecialAchievement;
    }

    public void setAnySpecialAchievement(String anySpecialAchievement) {
        this.anySpecialAchievement = anySpecialAchievement;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getFunctionalDuties() {
        return functionalDuties;
    }

    public void setFunctionalDuties(String functionalDuties) {
        this.functionalDuties = functionalDuties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}
