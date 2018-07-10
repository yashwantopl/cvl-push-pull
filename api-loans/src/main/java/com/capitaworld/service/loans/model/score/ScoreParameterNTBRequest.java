package com.capitaworld.service.loans.model.score;

import java.io.Serializable;

public class ScoreParameterNTBRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5151726053737490490L;

	private Boolean isWorkingExperience = false;
	private Boolean isFamilyMemberInLineOfBusiness = false;
	private Boolean isCibilTransunionScore = false;
	private Boolean isAgeOfPromotor = false;
	private Boolean isEducationQualification = false;
	private Boolean isEmploymentType = false;
	private Boolean isHouseOwnership = false;
	private Boolean isMaritialStatus = false;
	private Boolean isCWN = false;
	private Boolean isItrSalaryIncome = false;
	private Boolean isConstitutionOfBorrower = false;
	private Boolean isFixedObligationRatio = false;
	private Boolean isChequeBounces = false;
	private Boolean isDPD = false;
	private Boolean isAssetCoverageRatio = false;
	private Boolean isUnitFactoryPremises = false;
	private Boolean isBalanceGestationPeriod = false;
	private Boolean isEnvironmentCategory = false;

	private Double totalworkingExperience;
	private Long familyMemberInLineOfBusiness;
	private Double cibilTransunionScore;
	private Double ageOfPromotor;
	private Long educationQualification;
	private Long employeeType;
	private Long houseOwnerShip;
	private Integer maritialStatus;
	private Double networth;
	private Double loanAmount;
	private Double itrSalaryIncome;
	private Double itrPromotorContribution;
	private Long constitutionOfBorrowe;
	private Double fixedObligationIncomeRatio;
	private Double chequeBouncesPastSixMonths;
	private Double dpdOnPersonalLoan;
	private Double assetCoverageRatio;
	private Long unitFactoryPremisesDetails;
	private Long balanceGestationPeriod;
	private Long environmentCategory;

	public Boolean getIsWorkingExperience() {
		return isWorkingExperience;
	}

	public void setIsWorkingExperience(Boolean isWorkingExperience) {
		this.isWorkingExperience = isWorkingExperience;
	}

	public Boolean getIsFamilyMemberInLineOfBusiness() {
		return isFamilyMemberInLineOfBusiness;
	}

	public void setIsFamilyMemberInLineOfBusiness(Boolean isFamilyMemberInLineOfBusiness) {
		this.isFamilyMemberInLineOfBusiness = isFamilyMemberInLineOfBusiness;
	}

	public Boolean getIsCibilTransunionScore() {
		return isCibilTransunionScore;
	}

	public void setIsCibilTransunionScore(Boolean isCibilTransunionScore) {
		this.isCibilTransunionScore = isCibilTransunionScore;
	}

	public Boolean getIsAgeOfPromotor() {
		return isAgeOfPromotor;
	}

	public void setIsAgeOfPromotor(Boolean isAgeOfPromotor) {
		this.isAgeOfPromotor = isAgeOfPromotor;
	}

	public Boolean getIsEducationQualification() {
		return isEducationQualification;
	}

	public void setIsEducationQualification(Boolean isEducationQualification) {
		this.isEducationQualification = isEducationQualification;
	}

	public Boolean getIsEmploymentType() {
		return isEmploymentType;
	}

	public void setIsEmploymentType(Boolean isEmploymentType) {
		this.isEmploymentType = isEmploymentType;
	}

	public Boolean getIsHouseOwnership() {
		return isHouseOwnership;
	}

	public void setIsHouseOwnership(Boolean isHouseOwnership) {
		this.isHouseOwnership = isHouseOwnership;
	}

	public Boolean getIsMaritialStatus() {
		return isMaritialStatus;
	}

	public void setIsMaritialStatus(Boolean isMaritialStatus) {
		this.isMaritialStatus = isMaritialStatus;
	}

	public Boolean getIsCWN() {
		return isCWN;
	}

	public void setIsCWN(Boolean isCWN) {
		this.isCWN = isCWN;
	}

	public Boolean getIsItrSalaryIncome() {
		return isItrSalaryIncome;
	}

	public void setIsItrSalaryIncome(Boolean isItrSalaryIncome) {
		this.isItrSalaryIncome = isItrSalaryIncome;
	}

	public Boolean getIsConstitutionOfBorrower() {
		return isConstitutionOfBorrower;
	}

	public void setIsConstitutionOfBorrower(Boolean isConstitutionOfBorrower) {
		this.isConstitutionOfBorrower = isConstitutionOfBorrower;
	}

	public Boolean getIsFixedObligationRatio() {
		return isFixedObligationRatio;
	}

	public void setIsFixedObligationRatio(Boolean isFixedObligationRatio) {
		this.isFixedObligationRatio = isFixedObligationRatio;
	}

	public Boolean getIsChequeBounces() {
		return isChequeBounces;
	}

	public void setIsChequeBounces(Boolean isChequeBounces) {
		this.isChequeBounces = isChequeBounces;
	}

	public Boolean getIsDPD() {
		return isDPD;
	}

	public void setIsDPD(Boolean isDPD) {
		this.isDPD = isDPD;
	}

	public Boolean getIsAssetCoverageRatio() {
		return isAssetCoverageRatio;
	}

	public void setIsAssetCoverageRatio(Boolean isAssetCoverageRatio) {
		this.isAssetCoverageRatio = isAssetCoverageRatio;
	}

	public Boolean getIsUnitFactoryPremises() {
		return isUnitFactoryPremises;
	}

	public void setIsUnitFactoryPremises(Boolean isUnitFactoryPremises) {
		this.isUnitFactoryPremises = isUnitFactoryPremises;
	}

	public Boolean getIsBalanceGestationPeriod() {
		return isBalanceGestationPeriod;
	}

	public void setIsBalanceGestationPeriod(Boolean isBalanceGestationPeriod) {
		this.isBalanceGestationPeriod = isBalanceGestationPeriod;
	}

	public Boolean getIsEnvironmentCategory() {
		return isEnvironmentCategory;
	}

	public void setIsEnvironmentCategory(Boolean isEnvironmentCategory) {
		this.isEnvironmentCategory = isEnvironmentCategory;
	}

	public Double getTotalworkingExperience() {
		return totalworkingExperience;
	}

	public void setTotalworkingExperience(Double totalworkingExperience) {
		this.totalworkingExperience = totalworkingExperience;
	}

	public Long getFamilyMemberInLineOfBusiness() {
		return familyMemberInLineOfBusiness;
	}

	public void setFamilyMemberInLineOfBusiness(Long familyMemberInLineOfBusiness) {
		this.familyMemberInLineOfBusiness = familyMemberInLineOfBusiness;
	}

	public Double getCibilTransunionScore() {
		return cibilTransunionScore;
	}

	public void setCibilTransunionScore(Double cibilTransunionScore) {
		this.cibilTransunionScore = cibilTransunionScore;
	}

	public Double getAgeOfPromotor() {
		return ageOfPromotor;
	}

	public void setAgeOfPromotor(Double ageOfPromotor) {
		this.ageOfPromotor = ageOfPromotor;
	}

	public Long getEducationQualification() {
		return educationQualification;
	}

	public void setEducationQualification(Long educationQualification) {
		this.educationQualification = educationQualification;
	}

	public Long getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(Long employeeType) {
		this.employeeType = employeeType;
	}

	public Long getHouseOwnerShip() {
		return houseOwnerShip;
	}

	public void setHouseOwnerShip(Long houseOwnerShip) {
		this.houseOwnerShip = houseOwnerShip;
	}

	public Integer getMaritialStatus() {
		return maritialStatus;
	}

	public void setMaritialStatus(Integer maritialStatus) {
		this.maritialStatus = maritialStatus;
	}

	public Double getNetworth() {
		return networth;
	}

	public void setNetworth(Double networth) {
		this.networth = networth;
	}

	public Double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Double getItrSalaryIncome() {
		return itrSalaryIncome;
	}

	public void setItrSalaryIncome(Double itrSalaryIncome) {
		this.itrSalaryIncome = itrSalaryIncome;
	}

	public Double getItrPromotorContribution() {
		return itrPromotorContribution;
	}

	public void setItrPromotorContribution(Double itrPromotorContribution) {
		this.itrPromotorContribution = itrPromotorContribution;
	}

	public Long getConstitutionOfBorrowe() {
		return constitutionOfBorrowe;
	}

	public void setConstitutionOfBorrowe(Long constitutionOfBorrowe) {
		this.constitutionOfBorrowe = constitutionOfBorrowe;
	}

	public Double getFixedObligationIncomeRatio() {
		return fixedObligationIncomeRatio;
	}

	public void setFixedObligationIncomeRatio(Double fixedObligationIncomeRatio) {
		this.fixedObligationIncomeRatio = fixedObligationIncomeRatio;
	}

	public Double getChequeBouncesPastSixMonths() {
		return chequeBouncesPastSixMonths;
	}

	public void setChequeBouncesPastSixMonths(Double chequeBouncesPastSixMonths) {
		this.chequeBouncesPastSixMonths = chequeBouncesPastSixMonths;
	}

	public Double getDpdOnPersonalLoan() {
		return dpdOnPersonalLoan;
	}

	public void setDpdOnPersonalLoan(Double dpdOnPersonalLoan) {
		this.dpdOnPersonalLoan = dpdOnPersonalLoan;
	}

	public Double getAssetCoverageRatio() {
		return assetCoverageRatio;
	}

	public void setAssetCoverageRatio(Double assetCoverageRatio) {
		this.assetCoverageRatio = assetCoverageRatio;
	}

	public Long getUnitFactoryPremisesDetails() {
		return unitFactoryPremisesDetails;
	}

	public void setUnitFactoryPremisesDetails(Long unitFactoryPremisesDetails) {
		this.unitFactoryPremisesDetails = unitFactoryPremisesDetails;
	}

	public Long getBalanceGestationPeriod() {
		return balanceGestationPeriod;
	}

	public void setBalanceGestationPeriod(Long balanceGestationPeriod) {
		this.balanceGestationPeriod = balanceGestationPeriod;
	}

	public Long getEnvironmentCategory() {
		return environmentCategory;
	}

	public void setEnvironmentCategory(Long environmentCategory) {
		this.environmentCategory = environmentCategory;
	}

	@Override
	public String toString() {
		return "ScoreParameterNTBRequest [isWorkingExperience=" + isWorkingExperience
				+ ", isFamilyMemberInLineOfBusiness=" + isFamilyMemberInLineOfBusiness + ", isCibilTransunionScore="
				+ isCibilTransunionScore + ", isAgeOfPromotor=" + isAgeOfPromotor + ", isEducationQualification="
				+ isEducationQualification + ", isEmploymentType=" + isEmploymentType + ", isHouseOwnership="
				+ isHouseOwnership + ", isMaritialStatus=" + isMaritialStatus + ", isCWN=" + isCWN
				+ ", isItrSalaryIncome=" + isItrSalaryIncome + ", isConstitutionOfBorrower=" + isConstitutionOfBorrower
				+ ", isFixedObligationRatio=" + isFixedObligationRatio + ", isChequeBounces=" + isChequeBounces
				+ ", isDPD=" + isDPD + ", isAssetCoverageRatio=" + isAssetCoverageRatio + ", isUnitFactoryPremises="
				+ isUnitFactoryPremises + ", isBalanceGestationPeriod=" + isBalanceGestationPeriod
				+ ", isEnvironmentCategory=" + isEnvironmentCategory + ", totalworkingExperience="
				+ totalworkingExperience + ", familyMemberInLineOfBusiness=" + familyMemberInLineOfBusiness
				+ ", cibilTransunionScore=" + cibilTransunionScore + ", ageOfPromotor=" + ageOfPromotor
				+ ", educationQualification=" + educationQualification + ", employeeType=" + employeeType
				+ ", houseOwnerShip=" + houseOwnerShip + ", maritialStatus=" + maritialStatus + ", networth=" + networth
				+ ", loanAmount=" + loanAmount + ", itrSalaryIncome=" + itrSalaryIncome + ", itrPromotorContribution="
				+ itrPromotorContribution + ", constitutionOfBorrowe=" + constitutionOfBorrowe
				+ ", fixedObligationIncomeRatio=" + fixedObligationIncomeRatio + ", chequeBouncesPastSixMonths="
				+ chequeBouncesPastSixMonths + ", dpdOnPersonalLoan=" + dpdOnPersonalLoan + ", assetCoverageRatio="
				+ assetCoverageRatio + ", unitFactoryPremisesDetails=" + unitFactoryPremisesDetails
				+ ", balanceGestationPeriod=" + balanceGestationPeriod + ", environmentCategory=" + environmentCategory
				+ "]";
	}

}
