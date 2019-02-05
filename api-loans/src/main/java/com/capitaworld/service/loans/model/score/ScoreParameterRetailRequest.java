package com.capitaworld.service.loans.model.score;

public class ScoreParameterRetailRequest {

    private Boolean isWorkingExperience_p = false;
    private Boolean isCibilScore_p = false;
    private Boolean isAge_p = false;
    private Boolean isEducationQualifaction_p = false;
    private Boolean isEmployementType_p = false;
    private Boolean isHouseOwnership_p = false;
    private Boolean isMaritalStatus_p = false;
    private Boolean isCategoryInfo_p = false;
    private Boolean isFixedObligationRatio_p = false;
    private Boolean isChequeBounce_p = false;
    private Boolean isDPD_p = false;
    private Boolean isNetAnnualIncome_p = false;
    private Boolean isEmiNmi_p = false;
    private Boolean isNoOfYearCurrentLocation_p = false;

    private Double workingExperience;
    private Double cibilScore;
    private Double age;
    private Long educationQualification;
    private Long employmentType;
    private Long houseOwnership;
    private Long maritalStatus;
    private Long categoryInfo;
    private Double fixedObligationRatio;
    private Double chequeBounce;
    private Double dpd;
    private Double netAnnualIncome;
    private Double eminmi;
    private Double noOfYearCurrentLocation;

    @Override
    public String toString() {
        return "ScoreParameterRetailRequest{" +
                "isWorkingExperience_p=" + isWorkingExperience_p +
                ", isCibilScore_p=" + isCibilScore_p +
                ", isAge_p=" + isAge_p +
                ", isEducationQualifaction_p=" + isEducationQualifaction_p +
                ", isEmployementType_p=" + isEmployementType_p +
                ", isHouseOwnership_p=" + isHouseOwnership_p +
                ", isMaritalStatus_p=" + isMaritalStatus_p +
                ", isCategoryInfo_p=" + isCategoryInfo_p +
                ", isFixedObligationRatio_p=" + isFixedObligationRatio_p +
                ", isChequeBounce_p=" + isChequeBounce_p +
                ", isDPD_p=" + isDPD_p +
                ", isNetAnnualIncome_p=" + isNetAnnualIncome_p +
                ", isEmiNmi_p=" + isEmiNmi_p +
                ", isNoOfYearCurrentLocation_p=" + isNoOfYearCurrentLocation_p +
                ", workingExperience=" + workingExperience +
                ", cibilScore=" + cibilScore +
                ", age=" + age +
                ", educationQualification=" + educationQualification +
                ", employmentType=" + employmentType +
                ", houseOwnership=" + houseOwnership +
                ", maritalStatus=" + maritalStatus +
                ", categoryInfo=" + categoryInfo +
                ", fixedObligationRatio=" + fixedObligationRatio +
                ", chequeBounce=" + chequeBounce +
                ", dpd=" + dpd +
                ", netAnnualIncome=" + netAnnualIncome +
                ", eminmi=" + eminmi +
                ", noOfYearCurrentLocation=" + noOfYearCurrentLocation +
                '}';
    }

    public Boolean getWorkingExperience_p() {
        return isWorkingExperience_p;
    }

    public void setWorkingExperience_p(Boolean workingExperience_p) {
        isWorkingExperience_p = workingExperience_p;
    }

    public Boolean getCibilScore_p() {
        return isCibilScore_p;
    }

    public void setCibilScore_p(Boolean cibilScore_p) {
        isCibilScore_p = cibilScore_p;
    }

    public Boolean getAge_p() {
        return isAge_p;
    }

    public void setAge_p(Boolean age_p) {
        isAge_p = age_p;
    }

    public Boolean getEducationQualifaction_p() {
        return isEducationQualifaction_p;
    }

    public void setEducationQualifaction_p(Boolean educationQualifaction_p) {
        isEducationQualifaction_p = educationQualifaction_p;
    }

    public Boolean getEmployementType_p() {
        return isEmployementType_p;
    }

    public void setEmployementType_p(Boolean employementType_p) {
        isEmployementType_p = employementType_p;
    }

    public Boolean getHouseOwnership_p() {
        return isHouseOwnership_p;
    }

    public void setHouseOwnership_p(Boolean houseOwnership_p) {
        isHouseOwnership_p = houseOwnership_p;
    }

    public Boolean getMaritalStatus_p() {
        return isMaritalStatus_p;
    }

    public void setMaritalStatus_p(Boolean maritalStatus_p) {
        isMaritalStatus_p = maritalStatus_p;
    }

    public Boolean getCategoryInfo_p() {
        return isCategoryInfo_p;
    }

    public void setCategoryInfo_p(Boolean categoryInfo_p) {
        isCategoryInfo_p = categoryInfo_p;
    }

    public Boolean getFixedObligationRatio_p() {
        return isFixedObligationRatio_p;
    }

    public void setFixedObligationRatio_p(Boolean fixedObligationRatio_p) {
        isFixedObligationRatio_p = fixedObligationRatio_p;
    }

    public Boolean getChequeBounce_p() {
        return isChequeBounce_p;
    }

    public void setChequeBounce_p(Boolean chequeBounce_p) {
        isChequeBounce_p = chequeBounce_p;
    }

    public Boolean getDPD_p() {
        return isDPD_p;
    }

    public void setDPD_p(Boolean DPD_p) {
        isDPD_p = DPD_p;
    }

    public Boolean getNetAnnualIncome_p() {
        return isNetAnnualIncome_p;
    }

    public void setNetAnnualIncome_p(Boolean netAnnualIncome_p) {
        isNetAnnualIncome_p = netAnnualIncome_p;
    }

    public Boolean getEmiNmi_p() {
        return isEmiNmi_p;
    }

    public void setEmiNmi_p(Boolean emiNmi_p) {
        isEmiNmi_p = emiNmi_p;
    }

    public Double getWorkingExperience() {
        return workingExperience;
    }

    public void setWorkingExperience(Double workingExperience) {
        this.workingExperience = workingExperience;
    }

    public Double getCibilScore() {
        return cibilScore;
    }

    public void setCibilScore(Double cibilScore) {
        this.cibilScore = cibilScore;
    }

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Long getEducationQualification() {
        return educationQualification;
    }

    public void setEducationQualification(Long educationQualification) {
        this.educationQualification = educationQualification;
    }

    public Long getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(Long employmentType) {
        this.employmentType = employmentType;
    }

    public Long getHouseOwnership() {
        return houseOwnership;
    }

    public void setHouseOwnership(Long houseOwnership) {
        this.houseOwnership = houseOwnership;
    }

    public Long getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Long maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Long getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(Long categoryInfo) {
        this.categoryInfo = categoryInfo;
    }

    public Double getFixedObligationRatio() {
        return fixedObligationRatio;
    }

    public void setFixedObligationRatio(Double fixedObligationRatio) {
        this.fixedObligationRatio = fixedObligationRatio;
    }

    public Double getChequeBounce() {
        return chequeBounce;
    }

    public void setChequeBounce(Double chequeBounce) {
        this.chequeBounce = chequeBounce;
    }

    public Double getDpd() {
        return dpd;
    }

    public void setDpd(Double dpd) {
        this.dpd = dpd;
    }

    public Double getNetAnnualIncome() {
        return netAnnualIncome;
    }

    public void setNetAnnualIncome(Double netAnnualIncome) {
        this.netAnnualIncome = netAnnualIncome;
    }

    public Double getEminmi() {
        return eminmi;
    }

    public void setEminmi(Double eminmi) {
        this.eminmi = eminmi;
    }

    public Boolean getIsNoOfYearCurrentLocation_p() {
        return isNoOfYearCurrentLocation_p;
    }

    public void setIsNoOfYearCurrentLocation_p(Boolean noOfYearCurrentLocation_p) {
        isNoOfYearCurrentLocation_p = noOfYearCurrentLocation_p;
    }

    public Double getNoOfYearCurrentLocation() {
        return noOfYearCurrentLocation;
    }

    public void setNoOfYearCurrentLocation(Double noOfYearCurrentLocation) {
        this.noOfYearCurrentLocation = noOfYearCurrentLocation;
    }
}
