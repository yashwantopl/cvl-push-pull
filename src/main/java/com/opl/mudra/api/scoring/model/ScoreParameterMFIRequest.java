package com.opl.mudra.api.scoring.model;

public class ScoreParameterMFIRequest {

    private Boolean isAgeOfBorrower_p = false;
    private Boolean isAcademicQualification_p = false;
    private Boolean isDependents_p = false;
    private Boolean isOwnerShipOfHouse_p = false;
    private Boolean isAnnualIncome_p = false;
    private Boolean isWorkingExperience_p = false;
    private Boolean isLoanPurpose_p = false;

    private Long ageOfBorrower;
    private Long academicQualification;
    private Integer dependents;
    private Long ownershipOfHouse;
    private Long annualIncome;
    private Double workingExperience;
    private Integer loanPurpose;
    private Integer mfiMakerRecommendedTenure;
    private Double loanAmountRecommanded;
    private Double cibilScore;


    public Boolean getAgeOfBorrower_p() {
        return isAgeOfBorrower_p;
    }

    public void setAgeOfBorrower_p(Boolean ageOfBorrower_p) {
        isAgeOfBorrower_p = ageOfBorrower_p;
    }

    public Boolean getAcademicQualification_p() {
        return isAcademicQualification_p;
    }

    public void setAcademicQualification_p(Boolean academicQualification_p) {
        isAcademicQualification_p = academicQualification_p;
    }

    public Boolean getDependents_p() {
        return isDependents_p;
    }

    public void setDependents_p(Boolean dependents_p) {
        isDependents_p = dependents_p;
    }

    public Boolean getOwnerShipOfHouse_p() {
        return isOwnerShipOfHouse_p;
    }

    public void setOwnerShipOfHouse_p(Boolean ownerShipOfHouse_p) {
        isOwnerShipOfHouse_p = ownerShipOfHouse_p;
    }

    public Boolean getAnnualIncome_p() {
        return isAnnualIncome_p;
    }

    public void setAnnualIncome_p(Boolean annualIncome_p) {
        isAnnualIncome_p = annualIncome_p;
    }

    public Boolean getWorkingExperience_p() {
        return isWorkingExperience_p;
    }

    public void setWorkingExperience_p(Boolean workingExperience_p) {
        isWorkingExperience_p = workingExperience_p;
    }

    public Boolean getLoanPurpose_p() {
        return isLoanPurpose_p;
    }

    public void setLoanPurpose_p(Boolean loanPurpose_p) {
        isLoanPurpose_p = loanPurpose_p;
    }

    public Long getAgeOfBorrower() {
        return ageOfBorrower;
    }

    public void setAgeOfBorrower(Long ageOfBorrower) {
        this.ageOfBorrower = ageOfBorrower;
    }

    public Long getAcademicQualification() {
        return academicQualification;
    }

    public void setAcademicQualification(Long academicQualification) {
        this.academicQualification = academicQualification;
    }

    public Integer getDependents() {
        return dependents;
    }

    public void setDependents(Integer dependents) {
        this.dependents = dependents;
    }

    public Long getOwnershipOfHouse() {
        return ownershipOfHouse;
    }

    public void setOwnershipOfHouse(Long ownershipOfHouse) {
        this.ownershipOfHouse = ownershipOfHouse;
    }

    public Long getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(Long annualIncome) {
        this.annualIncome = annualIncome;
    }

    public Double getWorkingExperience() {
        return workingExperience;
    }

    public void setWorkingExperience(Double workingExperience) {
        this.workingExperience = workingExperience;
    }

    public Integer getLoanPurpose() {
        return loanPurpose;
    }

    public void setLoanPurpose(Integer loanPurpose) {
        this.loanPurpose = loanPurpose;
    }

    public Integer getMfiMakerRecommendedTenure() {
        return mfiMakerRecommendedTenure;
    }

    public void setMfiMakerRecommendedTenure(Integer mfiMakerRecommendedTenure) {
        this.mfiMakerRecommendedTenure = mfiMakerRecommendedTenure;
    }

    public Double getLoanAmountRecommanded() {
        return loanAmountRecommanded;
    }

    public void setLoanAmountRecommanded(Double loanAmountRecommanded) {
        this.loanAmountRecommanded = loanAmountRecommanded;
    }

    public Double getCibilScore() {
        return cibilScore;
    }

    public void setCibilScore(Double cibilScore) {
        this.cibilScore = cibilScore;
    }

    @Override
    public String toString() {
        return "ScoreParameterMFIRequest{" +
                "isAgeOfBorrower_p=" + isAgeOfBorrower_p +
                ", isAcademicQualification_p=" + isAcademicQualification_p +
                ", isDependents_p=" + isDependents_p +
                ", isOwnerShipOfHouse_p=" + isOwnerShipOfHouse_p +
                ", isAnnualIncome_p=" + isAnnualIncome_p +
                ", isWorkingExperience_p=" + isWorkingExperience_p +
                ", isLoanPurpose_p=" + isLoanPurpose_p +
                ", ageOfBorrower=" + ageOfBorrower +
                ", academicQualification=" + academicQualification +
                ", dependents=" + dependents +
                ", ownershipOfHouse=" + ownershipOfHouse +
                ", annualIncome=" + annualIncome +
                ", workingExperience=" + workingExperience +
                ", loanPurpose=" + loanPurpose +
                ", mfiMakerRecommendedTenure=" + mfiMakerRecommendedTenure +
                ", loanAmountRecommanded=" + loanAmountRecommanded +
                ", cibilScore=" + cibilScore +
                '}';
    }
}

