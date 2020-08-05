package com.opl.mudra.api.scoring.model;

public class ScoreParameterRetailRequest {

    private Boolean isWorkingExperience_p = false;
    private Boolean isWorkingExperienceCurrent_p = false;
    private Boolean isCibilScore_p = false;
    private Boolean isAge_p = false;
    private Boolean isEducationQualifaction_p = false;
    private Boolean isEmployementType_p = false;
    private Boolean isEmployementJobCat_p = false;
    private Boolean isEmployementTypeSelfEmpBus_p = false;
    private Boolean isHouseOwnership_p = false;
    private Boolean isMaritalStatus_p = false;
    private Boolean isCurrentEmploymentStatus_p = false;
    private Boolean isCategoryInfo_p = false;
    private Boolean isFixedObligationRatio_p = false;
    private Boolean isChequeBounce_p = false;
    private Boolean isChequeBounceLast1Month_p = false;
    private Boolean isDPD_p = false;
    private Boolean isNetAnnualIncome_p = false;
    private Boolean isAvailableIncome_p = false;
    private Boolean isEmiNmi_p = false;
    private Boolean isNoOfYearCurrentLocation_p = false;
    private Boolean isSpouseEmploymentDetails_p= false;
    private Boolean isNumberOfDependents_p= false;
    private Boolean isDesignation_p= false;
    private Boolean isLoanToIncomeRatio_p= false;
    private Boolean isTotalBusProfExperiance_p = false;
    private Boolean isToir_p = false;
    private Boolean isResidenceType_p = false;
    private Boolean isMinBankingRelationship_p = false;
    private Boolean isNoOfApplicantsType_p = false;
    private Boolean isSpouseIncome_p = false;
    private Boolean isMonIncomePerDep_p = false;
    private Boolean isEligibleTenure_p = false;
    private Boolean isAgeOfProperty_p = false;
    private Boolean isAvgOfTotalCheDepsitLast6Month_p = false;
    private Boolean isMarketValueOfProp_p = false;
    private Boolean isLTV_p = false;
    private Boolean isNetWorth_p = false;
    private Boolean isRepaymentPeriod_p = false;
    private Boolean isLoanPurpose_p = false;
    private Boolean isIncomeFromItr_p = false;
    private Boolean isNMI_p = false;
    private Boolean isGMI_p = false;
    private Boolean isAvgEodBalToToalDep_p = false;
    private Boolean isNetWrothToLoanAmount_p= false;
    private Boolean isAvgEODBalToTotalDeposite_p = false;
    private Boolean isTenureOfTheLoan_p = false;
    private Boolean isAnnualIncome_p = false;
    private Boolean isIncomeProof_p = false;
    private Boolean isEmiNmiRatio_p = false;
    private Boolean isRepaymentMode_p = false;
    private Boolean isCarSegment_p = false;
    private Boolean isSecurityCoverage_p = false;
    private Boolean isTakeHomePay_p = false;
    private Boolean isPersonalRelationShipWithBank_p = false;
    private Boolean isBorrowerMargin_p = false;
    private Boolean isAdhaarCard_p = false;
    private Boolean isVehicleAge_p = false;
    private Double workingExperience;
    private Double elAmountOnAverageScoring;
    private Double avgEodBalToToalDep;
    private Double workingExperienceCurrent;
    private Double emiNmiRatio;
    private Double securityCoverage;
    private Double takeHomePay;
    private Double borrowerMargin;
    private Double vechileAge;
    private Integer vechileType;
    private Integer personalBankingRelationShip;
    private Integer totalBusProfExperiance;
    private Integer minBankingRelationship;
    private Integer carSegment;
    private Integer incomeProofId;
    private Integer adhaarCardValue;
    private Double cibilScore;
    private Double toir;
    private Double foir;
    private Double age;
    private Double roi;
    private Long educationQualification;
    private Long employmentType;
    private Integer employmentTypeCatJob;
    private Long employmentTypeSelfEmpBus;
    private Long houseOwnership;
    private Integer loanPurpose;
    private Integer loanPurposeQueType;
    private String loanPurposeQueValue;
    private Long maritalStatus;
    private Long currentEmploymentStatus;
    private Long categoryInfo;
    private Double lastYearTotalIncomeFromITR;
    private Double incomeFromItr;
    private Double emiAmountFromCIBIL;
    private Double chequeBounce;
    private Double chequeBouncelast1Month;
    private Double avgOfTotalCheDepsitLast6Month;
    private Double dpd;
    private Double netAnnualIncome;
    private Integer noOfDependants;
    private Double emi;
    private Double nmi;
    private Double gmi;
    private Double ltv;
    private Double netWorth;
    private Double noOfYearCurrentLocation;
    private Double marketValueOfProp;
    private Long spouseEmploymentDetails;
    private Integer numberOfDependents;
    private Integer ageOfProperty;
    private Integer noOfApplicantsType;
    private Integer residenceType;
    private Integer repaymentPeriod;
    private Long designation;
    private Double loanToIncomeRatio;
    private Double loanAmtProposed;
    private Double grossAnnualIncome;
    private Double availableIncome;
    private Double spouseIncome;
    private Double eligibleTenure;
    private Long loanPurposeModelId;
    private Double netWrothToLoanAmount;
    private Double avgEODBal;
    private Double deposite;
    private Double eligibleLoanAmountCircular;;
    private Double exitingLoanObligation;
    private Double tenureOfTheLoan;
    private Double exShowRoomPrice;
    private Double onRoadPrice;
    private Double agreedIDV;
    private Integer repaymentMode;


    //Loan Purpose Model Fields.
	private Boolean isPurReadyBuiltHouse = false;
	private Boolean isPurReadyBuiltIndependentHouse = false;
	private Boolean isPurResidetialFlat	 = false;
	private Boolean isPurResidetialFlatAllotee = false;
	private Boolean isPurResidetialSite = false;
	private Boolean isConstruResidetialBuid = false;
	private Boolean isConstruExpaResBuild = false;
	private Boolean isConstruPurResSite = false;
	private Boolean isRepPurReadyBuiltIndependant = false;
	private Boolean isRepRenImpFlatHouse = false;
	private Boolean isOthRefExcessMarginPaid = false;
	private Boolean isOthLoanReimbursementFlat = false;
	private Boolean isConsiderCoAppIncome = false;

	// LOGIC FOR CONCESSION RATE OF INTEREST
    private Boolean isBorrowersHavingAccounts;
    private Boolean isBorrowersAvailingLoans;
    private Boolean isBorrowersHavingSalaryAccounts;
    private Boolean isBorrowersAvailingCreaditCards;
    private Boolean isWomenApplicant;
    
    // LOGIC FOR CHECK OFF RELATED ISSUE
    private Boolean isCheckOffDirectPayEmi;
    private Boolean isCheckOffAgreetoPayOutstanding;
    private Boolean isCheckOffShiftSalAcc;
    private Boolean isCheckOffPayOutstndAmount;
    private Boolean isCheckOffNotChangeSalAcc;
    // ENDS HERE CHECK OFF
    
	// CIBIL BASED CONCESSION RATE  OF INTEREST
	private Boolean isCreaditHisotryGreaterSixMonths;  
	private Boolean isCreaditHisotryLessThenSixMonths;
	private Boolean isNoCreaditHistory;
	private Double cibilActualScore;
	private Double cibilActualScoreVersion2;
	// ENDS HERE
    
	
    public Boolean getIsBorrowersAvailingCreaditCards() {
		return isBorrowersAvailingCreaditCards;
	}

	public Boolean getIsPersonalRelationShipWithBank_p() {
		return isPersonalRelationShipWithBank_p;
	}

	public void setIsPersonalRelationShipWithBank_p(Boolean isPersonalRelationShipWithBank_p) {
		this.isPersonalRelationShipWithBank_p = isPersonalRelationShipWithBank_p;
	}

	public Double getCibilActualScore() {
		return cibilActualScore;
	}

	public void setCibilActualScore(Double cibilActualScore) {
		this.cibilActualScore = cibilActualScore;
	}

	public Boolean getIsCreaditHisotryGreaterSixMonths() {
		return isCreaditHisotryGreaterSixMonths;
	}

	public void setIsCreaditHisotryGreaterSixMonths(Boolean isCreaditHisotryGreaterSixMonths) {
		this.isCreaditHisotryGreaterSixMonths = isCreaditHisotryGreaterSixMonths;
	}

	public Boolean getIsCreaditHisotryLessThenSixMonths() {
		return isCreaditHisotryLessThenSixMonths;
	}

	public void setIsCreaditHisotryLessThenSixMonths(Boolean isCreaditHisotryLessThenSixMonths) {
		this.isCreaditHisotryLessThenSixMonths = isCreaditHisotryLessThenSixMonths;
	}

	public Boolean getIsNoCreaditHistory() {
		return isNoCreaditHistory;
	}

	public void setIsNoCreaditHistory(Boolean isNoCreaditHistory) {
		this.isNoCreaditHistory = isNoCreaditHistory;
	}

	public Boolean getIsCheckOffDirectPayEmi() {
		return isCheckOffDirectPayEmi;
	}

	public void setIsCheckOffDirectPayEmi(Boolean isCheckOffDirectPayEmi) {
		this.isCheckOffDirectPayEmi = isCheckOffDirectPayEmi;
	}

	public Boolean getIsCheckOffAgreetoPayOutstanding() {
		return isCheckOffAgreetoPayOutstanding;
	}

	public void setIsCheckOffAgreetoPayOutstanding(Boolean isCheckOffAgreetoPayOutstanding) {
		this.isCheckOffAgreetoPayOutstanding = isCheckOffAgreetoPayOutstanding;
	}

	public Boolean getIsCheckOffShiftSalAcc() {
		return isCheckOffShiftSalAcc;
	}

	public void setIsCheckOffShiftSalAcc(Boolean isCheckOffShiftSalAcc) {
		this.isCheckOffShiftSalAcc = isCheckOffShiftSalAcc;
	}

	public Boolean getIsCheckOffPayOutstndAmount() {
		return isCheckOffPayOutstndAmount;
	}

	public void setIsCheckOffPayOutstndAmount(Boolean isCheckOffPayOutstndAmount) {
		this.isCheckOffPayOutstndAmount = isCheckOffPayOutstndAmount;
	}

	public Boolean getIsCheckOffNotChangeSalAcc() {
		return isCheckOffNotChangeSalAcc;
	}

	public void setIsCheckOffNotChangeSalAcc(Boolean isCheckOffNotChangeSalAcc) {
		this.isCheckOffNotChangeSalAcc = isCheckOffNotChangeSalAcc;
	}

	public Boolean getIsBorrowersHavingAccounts() {
        return isBorrowersHavingAccounts;
    }

    public void setIsBorrowersHavingAccounts(Boolean borrowersHavingAccounts) {
        isBorrowersHavingAccounts = borrowersHavingAccounts;
    }

    public Boolean getIsBorrowersAvailingLoans() {
        return isBorrowersAvailingLoans;
    }

    public void setIsBorrowersAvailingLoans(Boolean borrowersAvailingLoans) {
        isBorrowersAvailingLoans = borrowersAvailingLoans;
    }

    public Boolean getIsBorrowersHavingSalaryAccounts() {
        return isBorrowersHavingSalaryAccounts;
    }

    public void setIsBorrowersHavingSalaryAccounts(Boolean borrowersHavingSalaryAccounts) {
        isBorrowersHavingSalaryAccounts = borrowersHavingSalaryAccounts;
    }

    public Boolean getBorrowersAvailingCreaditCards() {
        return isBorrowersAvailingCreaditCards;
    }

    public void setIsBorrowersAvailingCreaditCards(Boolean borrowersAvailingCreaditCards) {
        isBorrowersAvailingCreaditCards = borrowersAvailingCreaditCards;
    }

    public Double getLastYearTotalIncomeFromITR() {
        return this.lastYearTotalIncomeFromITR;
    }

    public void setLastYearTotalIncomeFromITR(Double lastYearTotalIncomeFromITR) {
        this.lastYearTotalIncomeFromITR = lastYearTotalIncomeFromITR;
    }

    public Double getEmiAmountFromCIBIL() {
        return this.emiAmountFromCIBIL;
    }

    public void setEmiAmountFromCIBIL(Double emiAmountFromCIBIL) {
        this.emiAmountFromCIBIL = emiAmountFromCIBIL;
    }

    public Boolean getWorkingExperience_p() {
        return this.isWorkingExperience_p;
    }

    public void setWorkingExperience_p(Boolean workingExperience_p) {
    	this.isWorkingExperience_p = workingExperience_p;
    }

    public Boolean getCibilScore_p() {
        return this.isCibilScore_p;
    }

    public void setCibilScore_p(Boolean cibilScore_p) {
    	this.isCibilScore_p = cibilScore_p;
    }

    public Boolean getAge_p() {
        return isAge_p;
    }

    public void setAge_p(Boolean age_p) {
    	this.isAge_p = age_p;
    }

    public Boolean getEducationQualifaction_p() {
        return this.isEducationQualifaction_p;
    }

    public void setEducationQualifaction_p(Boolean educationQualifaction_p) {
    	this.isEducationQualifaction_p = educationQualifaction_p;
    }

    public Boolean getEmployementType_p() {
        return this.isEmployementType_p;
    }

    public void setEmployementType_p(Boolean employementType_p) {
    	this.isEmployementType_p = employementType_p;
    }

    public Boolean getHouseOwnership_p() {
        return this.isHouseOwnership_p;
    }

    public void setHouseOwnership_p(Boolean houseOwnership_p) {
    	this.isHouseOwnership_p = houseOwnership_p;
    }

    public Boolean getMaritalStatus_p() {
        return this.isMaritalStatus_p;
    }

    public void setMaritalStatus_p(Boolean maritalStatus_p) {
    	this.isMaritalStatus_p = maritalStatus_p;
    }

    public Boolean getCategoryInfo_p() {
        return this.isCategoryInfo_p;
    }

    public void setCategoryInfo_p(Boolean categoryInfo_p) {
    	this.isCategoryInfo_p = categoryInfo_p;
    }

    public Boolean getFixedObligationRatio_p() {
        return this.isFixedObligationRatio_p;
    }

    public void setFixedObligationRatio_p(Boolean fixedObligationRatio_p) {
    	this.isFixedObligationRatio_p = fixedObligationRatio_p;
    }

    public Boolean getChequeBounce_p() {
        return this.isChequeBounce_p;
    }

    public void setChequeBounce_p(Boolean chequeBounce_p) {
    	this.isChequeBounce_p = chequeBounce_p;
    }

    public Boolean getDPD_p() {
        return this.isDPD_p;
    }

    public void setDPD_p(Boolean DPD_p) {
    	this.isDPD_p = DPD_p;
    }

    public Boolean getNetAnnualIncome_p() {
        return this.isNetAnnualIncome_p;
    }

    public void setNetAnnualIncome_p(Boolean netAnnualIncome_p) {
    	this.isNetAnnualIncome_p = netAnnualIncome_p;
    }

    public Boolean getEmiNmi_p() {
        return isEmiNmi_p;
    }

    public void setEmiNmi_p(Boolean emiNmi_p) {
    	this.isEmiNmi_p = emiNmi_p;
    }

    public Double getWorkingExperience() {
        return this.workingExperience;
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
        return this.educationQualification;
    }

    public void setEducationQualification(Long educationQualification) {
        this.educationQualification = educationQualification;
    }

    public Long getEmploymentType() {
        return this.employmentType;
    }

    public void setEmploymentType(Long employmentType) {
        this.employmentType = employmentType;
    }

    public Long getHouseOwnership() {
        return this.houseOwnership;
    }

    public void setHouseOwnership(Long houseOwnership) {
        this.houseOwnership = houseOwnership;
    }

    public Long getMaritalStatus() {
        return this.maritalStatus;
    }

    public void setMaritalStatus(Long maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Long getCategoryInfo() {
        return this.categoryInfo;
    }

    public void setCategoryInfo(Long categoryInfo) {
        this.categoryInfo = categoryInfo;
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

    public Double getNoOfYearCurrentLocation() {
        return noOfYearCurrentLocation;
    }

    public void setNoOfYearCurrentLocation(Double noOfYearCurrentLocation) {
        this.noOfYearCurrentLocation = noOfYearCurrentLocation;
    }

    public Boolean getIsNoOfYearCurrentLocation_p() {
        return isNoOfYearCurrentLocation_p;
    }

    public void setIsNoOfYearCurrentLocation_p(Boolean noOfYearCurrentLocation_p) {
    	this.isNoOfYearCurrentLocation_p = noOfYearCurrentLocation_p;
    }

    public Boolean getNoOfYearCurrentLocation_p() {
        return this.isNoOfYearCurrentLocation_p;
    }

    public void setNoOfYearCurrentLocation_p(Boolean noOfYearCurrentLocation_p) {
    	this.isNoOfYearCurrentLocation_p = noOfYearCurrentLocation_p;
    }

    public Boolean getSpouseEmploymentDetails_p() {
        return this.isSpouseEmploymentDetails_p;
    }

    public void setSpouseEmploymentDetails_p(Boolean spouseEmploymentDetails_p) {
    	this.isSpouseEmploymentDetails_p = spouseEmploymentDetails_p;
    }

    public Boolean getNumberOfDependents_p() {
        return this.isNumberOfDependents_p;
    }

    public void setNumberOfDependents_p(Boolean numberOfDependents_p) {
    	this.isNumberOfDependents_p = numberOfDependents_p;
    }

    public Boolean getDesignation_p() {
        return this.isDesignation_p;
    }

    public void setDesignation_p(Boolean designation_p) {
    	this.isDesignation_p = designation_p;
    }

    public Long getSpouseEmploymentDetails() {
        return spouseEmploymentDetails;
    }

    public void setSpouseEmploymentDetails(Long spouseEmploymentDetails) {
        this.spouseEmploymentDetails = spouseEmploymentDetails;
    }

    public Integer getNumberOfDependents() {
        return this.numberOfDependents;
    }

    public void setNumberOfDependents(Integer numberOfDependents) {
        this.numberOfDependents = numberOfDependents;
    }

    public Long getDesignation() {
        return designation;
    }

    public void setDesignation(Long designation) {
        this.designation = designation;
    }

    public Boolean getLoanToIncomeRatio_p() {
        return isLoanToIncomeRatio_p;
    }

    public void setLoanToIncomeRatio_p(Boolean loanToIncomeRatio_p) {
        this.isLoanToIncomeRatio_p = loanToIncomeRatio_p;
    }

    public Double getLoanToIncomeRatio() {
        return loanToIncomeRatio;
    }

    public void setLoanToIncomeRatio(Double loanToIncomeRatio) {
        this.loanToIncomeRatio = loanToIncomeRatio;
    }

    public Double getLoanAmtProposed() {
        return loanAmtProposed;
    }

    public void setLoanAmtProposed(Double loanAmtProposed) {
        this.loanAmtProposed = loanAmtProposed;
    }

    public Double getGrossAnnualIncome() {
        return grossAnnualIncome;
    }

    public void setGrossAnnualIncome(Double grossAnnualIncome) {
        this.grossAnnualIncome = grossAnnualIncome;
    }

    public Double getEmi() {
        return emi;
    }

    public void setEmi(Double emi) {
        this.emi = emi;
    }

    public Double getNmi() {
        return nmi;
    }

    public void setNmi(Double nmi) {
        this.nmi = nmi;
    }

    public Boolean getIsWorkingExperienceCurrent_p() {
		return isWorkingExperienceCurrent_p;
	}

	public void setIsWorkingExperienceCurrent_p(Boolean isWorkingExperienceCurrent_p) {
		this.isWorkingExperienceCurrent_p = isWorkingExperienceCurrent_p;
	}

	public Double getWorkingExperienceCurrent() {
		return workingExperienceCurrent;
	}

	public void setWorkingExperienceCurrent(Double workingExperienceCurrent) {
		this.workingExperienceCurrent = workingExperienceCurrent;
	}

	public Boolean getIsTotalBusProfExperiance_p() {
		return isTotalBusProfExperiance_p;
	}

	public void setIsTotalBusProfExperiance_p(Boolean isTotalBusProfExperiance_p) {
		this.isTotalBusProfExperiance_p = isTotalBusProfExperiance_p;
	}

	public Integer getTotalBusProfExperiance() {
		return totalBusProfExperiance;
	}

	public void setTotalBusProfExperiance(Integer totalBusProfExperiance) {
		this.totalBusProfExperiance = totalBusProfExperiance;
	}
	
	public Boolean getIsResidenceType_p() {
		return isResidenceType_p;
	}

	public void setIsResidenceType_p(Boolean isResidenceType_p) {
		this.isResidenceType_p = isResidenceType_p;
	}

	public Integer getResidenceType() {
		return residenceType;
	}

	public void setResidenceType(Integer residenceType) {
		this.residenceType = residenceType;
	}

	public Boolean getIsEmployementTypeSelfEmpBus_p() {
		return isEmployementTypeSelfEmpBus_p;
	}

	public void setIsEmployementTypeSelfEmpBus_p(Boolean isEmployementTypeSelfEmpBus_p) {
		this.isEmployementTypeSelfEmpBus_p = isEmployementTypeSelfEmpBus_p;
	}

	public Long getEmploymentTypeSelfEmpBus() {
		return employmentTypeSelfEmpBus;
	}

	public void setEmploymentTypeSelfEmpBus(Long employmentTypeSelfEmpBus) {
		this.employmentTypeSelfEmpBus = employmentTypeSelfEmpBus;
	}

	public Boolean getIsCurrentEmploymentStatus_p() {
		return isCurrentEmploymentStatus_p;
	}

	public void setIsCurrentEmploymentStatus_p(Boolean isCurrentEmploymentStatus_p) {
		this.isCurrentEmploymentStatus_p = isCurrentEmploymentStatus_p;
	}

	public Long getCurrentEmploymentStatus() {
		return currentEmploymentStatus;
	}

	public void setCurrentEmploymentStatus(Long currentEmploymentStatus) {
		this.currentEmploymentStatus = currentEmploymentStatus;
	}

	public Boolean getIsMinBankingRelationship_p() {
		return isMinBankingRelationship_p;
	}

	public void setIsMinBankingRelationship_p(Boolean isMinBankingRelationship_p) {
		this.isMinBankingRelationship_p = isMinBankingRelationship_p;
	}

	public Integer getMinBankingRelationship() {
		return minBankingRelationship;
	}

	public void setMinBankingRelationship(Integer minBankingRelationship) {
		this.minBankingRelationship = minBankingRelationship;
	}

	public Boolean getIsNoOfApplicantsType_p() {
		return isNoOfApplicantsType_p;
	}

	public void setIsNoOfApplicantsType_p(Boolean isNoOfApplicantsType_p) {
		this.isNoOfApplicantsType_p = isNoOfApplicantsType_p;
	}

	public Integer getNoOfApplicantsType() {
		return noOfApplicantsType;
	}

	public void setNoOfApplicantsType(Integer noOfApplicantsType) {
		this.noOfApplicantsType = noOfApplicantsType;
	}

	public Boolean getIsChequeBounceLast1Month_p() {
		return isChequeBounceLast1Month_p;
	}

	public void setIsChequeBounceLast1Month_p(Boolean isChequeBounceLast1Month_p) {
		this.isChequeBounceLast1Month_p = isChequeBounceLast1Month_p;
	}

	public Double getChequeBouncelast1Month() {
		return chequeBouncelast1Month;
	}

	public void setChequeBouncelast1Month(Double chequeBouncelast1Month) {
		this.chequeBouncelast1Month = chequeBouncelast1Month;
	}

	public Boolean getIsAvailableIncome_p() {
		return isAvailableIncome_p;
	}

	public void setIsAvailableIncome_p(Boolean isAvailableIncome_p) {
		this.isAvailableIncome_p = isAvailableIncome_p;
	}

	public Double getAvailableIncome() {
		return availableIncome;
	}

	public void setAvailableIncome(Double availableIncome) {
		this.availableIncome = availableIncome;
	}

	public Boolean getIsSpouseIncome_p() {
		return isSpouseIncome_p;
	}

	public void setIsSpouseIncome_p(Boolean isSpouseIncome_p) {
		this.isSpouseIncome_p = isSpouseIncome_p;
	}

	public Double getSpouseIncome() {
		return spouseIncome;
	}

	public void setSpouseIncome(Double spouseIncome) {
		this.spouseIncome = spouseIncome;
	}

	public Boolean getIsMonIncomePerDep_p() {
		return isMonIncomePerDep_p;
	}

	public void setIsMonIncomePerDep_p(Boolean isMonIncomePerDep_p) {
		this.isMonIncomePerDep_p = isMonIncomePerDep_p;
	}


	public Integer getNoOfDependants() {
		return noOfDependants;
	}

	public void setNoOfDependants(Integer noOfDependants) {
		this.noOfDependants = noOfDependants;
	}

	public Boolean getIsEligibleTenure_p() {
		return isEligibleTenure_p;
	}

	public void setIsEligibleTenure_p(Boolean isEligibleTenure_p) {
		this.isEligibleTenure_p = isEligibleTenure_p;
	}

	public Double getEligibleTenure() {
		return eligibleTenure;
	}

	public void setEligibleTenure(Double eligibleTenure) {
		this.eligibleTenure = eligibleTenure;
	}

	public Integer getAgeOfProperty() {
		return ageOfProperty;
	}

	public void setAgeOfProperty(Integer ageOfProperty) {
		this.ageOfProperty = ageOfProperty;
	}
	

	public Boolean getIsAgeOfProperty_p() {
		return isAgeOfProperty_p;
	}

	public void setIsAgeOfProperty_p(Boolean isAgeOfProperty_p) {
		this.isAgeOfProperty_p = isAgeOfProperty_p;
	}

	public Boolean getIsAvgOfTotalCheDepsitLast6Month_p() {
		return isAvgOfTotalCheDepsitLast6Month_p;
	}

	public void setIsAvgOfTotalCheDepsitLast6Month_p(Boolean isAvgOfTotalCheDepsitLast6Month_p) {
		this.isAvgOfTotalCheDepsitLast6Month_p = isAvgOfTotalCheDepsitLast6Month_p;
	}

	public Double getAvgOfTotalCheDepsitLast6Month() {
		return avgOfTotalCheDepsitLast6Month;
	}

	public void setAvgOfTotalCheDepsitLast6Month(Double avgOfTotalCheDepsitLast6Month) {
		this.avgOfTotalCheDepsitLast6Month = avgOfTotalCheDepsitLast6Month;
	}

	public Boolean getIsMarketValueOfProp_p() {
		return isMarketValueOfProp_p;
	}

	public void setIsMarketValueOfProp_p(Boolean isMarketValueOfProp_p) {
		this.isMarketValueOfProp_p = isMarketValueOfProp_p;
	}

	public Double getMarketValueOfProp() {
		return marketValueOfProp;
	}

	public void setMarketValueOfProp(Double marketValueOfProp) {
		this.marketValueOfProp = marketValueOfProp;
	}

	public Boolean getIsLTV_p() {
		return isLTV_p;
	}

	public void setIsLTV_p(Boolean isLTV_p) {
		this.isLTV_p = isLTV_p;
	}

	public Double getLtv() {
		return ltv;
	}

	public void setLtv(Double ltv) {
		this.ltv = ltv;
	}

	public Boolean getIsNetWorth_p() {
		return isNetWorth_p;
	}

	public void setIsNetWorth_p(Boolean isNetWorth_p) {
		this.isNetWorth_p = isNetWorth_p;
	}

	public Double getNetWorth() {
		return netWorth;
	}

	public void setNetWorth(Double netWorth) {
		this.netWorth = netWorth;
	}

	public Boolean getIsRepaymentPeriod_p() {
		return isRepaymentPeriod_p;
	}

	public void setIsRepaymentPeriod_p(Boolean isRepaymentPeriod_p) {
		this.isRepaymentPeriod_p = isRepaymentPeriod_p;
	}

	public Integer getRepaymentPeriod() {
		return repaymentPeriod;
	}

	public void setRepaymentPeriod(Integer repaymentPeriod) {
		this.repaymentPeriod = repaymentPeriod;
	}

	public Boolean getIsEmployementJobCat_p() {
		return isEmployementJobCat_p;
	}

	public void setIsEmployementJobCat_p(Boolean isEmployementJobCat_p) {
		this.isEmployementJobCat_p = isEmployementJobCat_p;
	}

	public Integer getEmploymentTypeCatJob() {
		return employmentTypeCatJob;
	}

	public void setEmploymentTypeCatJob(Integer employmentTypeCatJob) {
		this.employmentTypeCatJob = employmentTypeCatJob;
	}

	public Double getEmiNmiRatio() {
		return emiNmiRatio;
	}

	public void setEmiNmiRatio(Double emiNmiRatio) {
		this.emiNmiRatio = emiNmiRatio;
	}

	public Boolean getIsLoanPurpose_p() {
		return isLoanPurpose_p;
	}

	public void setIsLoanPurpose_p(Boolean isLoanPurpose_p) {
		this.isLoanPurpose_p = isLoanPurpose_p;
	}

	public Integer getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(Integer loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public Long getLoanPurposeModelId() {
		return loanPurposeModelId;
	}

	public void setLoanPurposeModelId(Long loanPurposeModelId) {
		this.loanPurposeModelId = loanPurposeModelId;
	}

	public Boolean getIsPurReadyBuiltHouse() {
		return isPurReadyBuiltHouse;
	}

	public void setIsPurReadyBuiltHouse(Boolean isPurReadyBuiltHouse) {
		this.isPurReadyBuiltHouse = isPurReadyBuiltHouse;
	}

	public Boolean getIsPurReadyBuiltIndependentHouse() {
		return isPurReadyBuiltIndependentHouse;
	}

	public void setIsPurReadyBuiltIndependentHouse(Boolean isPurReadyBuiltIndependentHouse) {
		this.isPurReadyBuiltIndependentHouse = isPurReadyBuiltIndependentHouse;
	}

	public Boolean getIsPurResidetialFlat() {
		return isPurResidetialFlat;
	}

	public void setIsPurResidetialFlat(Boolean isPurResidetialFlat) {
		this.isPurResidetialFlat = isPurResidetialFlat;
	}

	public Boolean getIsPurResidetialFlatAllotee() {
		return isPurResidetialFlatAllotee;
	}

	public void setIsPurResidetialFlatAllotee(Boolean isPurResidetialFlatAllotee) {
		this.isPurResidetialFlatAllotee = isPurResidetialFlatAllotee;
	}

	public Boolean getIsPurResidetialSite() {
		return isPurResidetialSite;
	}

	public void setIsPurResidetialSite(Boolean isPurResidetialSite) {
		this.isPurResidetialSite = isPurResidetialSite;
	}

	public Boolean getIsConstruResidetialBuid() {
		return isConstruResidetialBuid;
	}

	public void setIsConstruResidetialBuid(Boolean isConstruResidetialBuid) {
		this.isConstruResidetialBuid = isConstruResidetialBuid;
	}

	public Boolean getIsConstruExpaResBuild() {
		return isConstruExpaResBuild;
	}

	public void setIsConstruExpaResBuild(Boolean isConstruExpaResBuild) {
		this.isConstruExpaResBuild = isConstruExpaResBuild;
	}

	public Boolean getIsConstruPurResSite() {
		return isConstruPurResSite;
	}

	public void setIsConstruPurResSite(Boolean isConstruPurResSite) {
		this.isConstruPurResSite = isConstruPurResSite;
	}

	public Boolean getIsRepPurReadyBuiltIndependant() {
		return isRepPurReadyBuiltIndependant;
	}

	public void setIsRepPurReadyBuiltIndependant(Boolean isRepPurReadyBuiltIndependant) {
		this.isRepPurReadyBuiltIndependant = isRepPurReadyBuiltIndependant;
	}

	public Boolean getIsRepRenImpFlatHouse() {
		return isRepRenImpFlatHouse;
	}

	public void setIsRepRenImpFlatHouse(Boolean isRepRenImpFlatHouse) {
		this.isRepRenImpFlatHouse = isRepRenImpFlatHouse;
	}

	public Boolean getIsOthRefExcessMarginPaid() {
		return isOthRefExcessMarginPaid;
	}

	public void setIsOthRefExcessMarginPaid(Boolean isOthRefExcessMarginPaid) {
		this.isOthRefExcessMarginPaid = isOthRefExcessMarginPaid;
	}

	public Boolean getIsOthLoanReimbursementFlat() {
		return isOthLoanReimbursementFlat;
	}

	public void setIsOthLoanReimbursementFlat(Boolean isOthLoanReimbursementFlat) {
		this.isOthLoanReimbursementFlat = isOthLoanReimbursementFlat;
	}

	public Integer getLoanPurposeQueType() {
		return loanPurposeQueType;
	}

	public void setLoanPurposeQueType(Integer loanPurposeQueType) {
		this.loanPurposeQueType = loanPurposeQueType;
	}

	public String getLoanPurposeQueValue() {
		return loanPurposeQueValue;
	}

	public void setLoanPurposeQueValue(String loanPurposeQueValue) {
		this.loanPurposeQueValue = loanPurposeQueValue;
	}

	public Boolean getIsIncomeFromItr_p() {
		return isIncomeFromItr_p;
	}

	public void setIsIncomeFromItr_p(Boolean isIncomeFromItr_p) {
		this.isIncomeFromItr_p = isIncomeFromItr_p;
	}

	public Double getIncomeFromItr() {
		return incomeFromItr;
	}

	public void setIncomeFromItr(Double incomeFromItr) {
		this.incomeFromItr = incomeFromItr;
	}

	public Boolean getIsToir_p() {
		return isToir_p;
	}

	public void setIsToir_p(Boolean isToir_p) {
		this.isToir_p = isToir_p;
	}

	public Double getToir() {
		return toir;
	}

	public void setToir(Double toir) {
		this.toir = toir;
	}

	public Boolean getIsNMI_p() {
		return isNMI_p;
	}

	public void setIsNMI_p(Boolean isNMI_p) {
		this.isNMI_p = isNMI_p;
	}

	public Boolean getIsGMI_p() {
		return isGMI_p;
	}

	public void setIsGMI_p(Boolean isGMI_p) {
		this.isGMI_p = isGMI_p;
	}

    public Double getFoir() {
		return foir;
	}

	public void setFoir(Double foir) {
		this.foir = foir;
	}

	public Double getGmi() {
		return gmi;
	}

	public void setGmi(Double gmi) {
		this.gmi = gmi;
	}

	public Boolean getIsAvgEodBalToToalDep_p() {
		return isAvgEodBalToToalDep_p;
	}

	public void setIsAvgEodBalToToalDep_p(Boolean isAvgEodBalToToalDep_p) {
		this.isAvgEodBalToToalDep_p = isAvgEodBalToToalDep_p;
	}

	public Double getAvgEodBalToToalDep() {
		return avgEodBalToToalDep;
	}

	public void setAvgEodBalToToalDep(Double avgEodBalToToalDep) {
		this.avgEodBalToToalDep = avgEodBalToToalDep;
	}

	public Integer getIncomeProofId() {
		return incomeProofId;
	}

	public void setIncomeProofId(Integer incomeProofId) {
		this.incomeProofId = incomeProofId;
	}
//
	public Double getElAmountOnAverageScoring() {
		return elAmountOnAverageScoring;
	}

	public void setElAmountOnAverageScoring(Double elAmountOnAverageScoring) {
		this.elAmountOnAverageScoring = elAmountOnAverageScoring;
	}

	public Boolean getIsWomenApplicant() {
		return isWomenApplicant;
	}

	public void setIsWomenApplicant(Boolean isWomenApplicant) {
		this.isWomenApplicant = isWomenApplicant;
	}

    public Boolean getIsNetWrothToLoanAmount_p() {
        return isNetWrothToLoanAmount_p;
    }

    public void setIsNetWrothToLoanAmount_p(Boolean netWrothToLoanAmount_p) {
        isNetWrothToLoanAmount_p = netWrothToLoanAmount_p;
    }

    public Boolean getIsAvgEODBalToTotalDeposite_p() {
        return isAvgEODBalToTotalDeposite_p;
    }

    public void setIsAvgEODBalToTotalDeposite_p(Boolean avgEODBalToTotalDeposite_p) {
        isAvgEODBalToTotalDeposite_p = avgEODBalToTotalDeposite_p;
    }

    public Boolean getTenureOfTheLoan_p() {
        return isTenureOfTheLoan_p;
    }

    public void setTenureOfTheLoan_p(Boolean tenureOfTheLoan_p) {
        isTenureOfTheLoan_p = tenureOfTheLoan_p;
    }

    public Double getNetWrothToLoanAmount() {
        return netWrothToLoanAmount;
    }

    public void setNetWrothToLoanAmount(Double netWrothToLoanAmount) {
        this.netWrothToLoanAmount = netWrothToLoanAmount;
    }

    public Double getAvgEODBal() {
        return avgEODBal;
    }

    public void setAvgEODBal(Double avgEODBal) {
        this.avgEODBal = avgEODBal;
    }

    public Double getTenureOfTheLoan() {
        return tenureOfTheLoan;
    }

    public void setTenureOfTheLoan(Double tenureOfTheLoan) {
        this.tenureOfTheLoan = tenureOfTheLoan;
    }

    public Double getEligibleLoanAmountCircular() {
        return eligibleLoanAmountCircular;
    }

    public void setEligibleLoanAmountCircular(Double eligibleLoanAmountCircular) {
        this.eligibleLoanAmountCircular = eligibleLoanAmountCircular;
    }

    public Double getExitingLoanObligation() {
        return exitingLoanObligation;
    }

    public void setExitingLoanObligation(Double exitingLoanObligation) {
        this.exitingLoanObligation = exitingLoanObligation;
    }

    public Double getDeposite() {
        return deposite;
    }

    public void setDeposite(Double deposite) {
        this.deposite = deposite;
    }

    public Boolean getIsConsiderCoAppIncome() {
		return isConsiderCoAppIncome;
	}

	public void setIsConsiderCoAppIncome(Boolean isConsiderCoAppIncome) {
		this.isConsiderCoAppIncome = isConsiderCoAppIncome;
	}

    public Boolean getIncomeProof_p() {
        return isIncomeProof_p;
    }

    public void setIncomeProof_p(Boolean incomeProof_p) {
        isIncomeProof_p = incomeProof_p;
    }

    public Boolean getEmiNmiRatio_p() {
        return isEmiNmiRatio_p;
    }

    public void setEmiNmiRatio_p(Boolean emiNmiRatio_p) {
        isEmiNmiRatio_p = emiNmiRatio_p;
    }

    public Boolean getAnnualIncome_p() {
        return isAnnualIncome_p;
    }

    public void setAnnualIncome_p(Boolean annualIncome_p) {
        isAnnualIncome_p = annualIncome_p;
    }

    public Boolean getRepaymentMode_p() {
        return isRepaymentMode_p;
    }

    public void setRepaymentMode_p(Boolean repaymentMode_p) {
        isRepaymentMode_p = repaymentMode_p;
    }

    public Integer getRepaymentMode() {
        return repaymentMode;
    }

    public void setRepaymentMode(Integer repaymentMode) {
        this.repaymentMode = repaymentMode;
    }
    
	public Boolean getIsCarSegment_p() {
		return isCarSegment_p;
	}

	public void setIsCarSegment_p(Boolean isCarSegment_p) {
		this.isCarSegment_p = isCarSegment_p;
	}

	public Integer getCarSegment() {
		return carSegment;
	}

	public void setCarSegment(Integer carSegment) {
		this.carSegment = carSegment;
	}

	public Boolean getIsSecurityCoverage_p() {
		return isSecurityCoverage_p;
	}

	public void setIsSecurityCoverage_p(Boolean isSecurityCoverage_p) {
		this.isSecurityCoverage_p = isSecurityCoverage_p;
	}

	public Boolean getIsTakeHomePay_p() {
		return isTakeHomePay_p;
	}

	public void setIsTakeHomePay_p(Boolean isTakeHomePay_p) {
		this.isTakeHomePay_p = isTakeHomePay_p;
	}

	public Boolean getIsBorrowerMargin_p() {
		return isBorrowerMargin_p;
	}

	public void setIsBorrowerMargin_p(Boolean isBorrowerMargin_p) {
		this.isBorrowerMargin_p = isBorrowerMargin_p;
	}

	public Double getSecurityCoverage() {
		return securityCoverage;
	}

	public void setSecurityCoverage(Double securityCoverage) {
		this.securityCoverage = securityCoverage;
	}

	public Double getTakeHomePay() {
		return takeHomePay;
	}

	public void setTakeHomePay(Double takeHomePay) {
		this.takeHomePay = takeHomePay;
	}

	public Double getBorrowerMargin() {
		return borrowerMargin;
	}

	public void setBorrowerMargin(Double borrowerMargin) {
		this.borrowerMargin = borrowerMargin;
	}

	public Double getRoi() {
		return roi;
	}

	public void setRoi(Double roi) {
		this.roi = roi;
	}

	public Double getExShowRoomPrice() {
		return exShowRoomPrice;
	}

	public void setExShowRoomPrice(Double exShowRoomPrice) {
		this.exShowRoomPrice = exShowRoomPrice;
	}

	public Boolean getIsVehicleAge_p() {
		return isVehicleAge_p;
	}

	public void setIsVehicleAge_p(Boolean isVehicleAgeFourWheeler_p) {
		this.isVehicleAge_p = isVehicleAgeFourWheeler_p;
	}

	public Double getOnRoadPrice() {
		return onRoadPrice;
	}

	public void setOnRoadPrice(Double onRoadPrice) {
		this.onRoadPrice = onRoadPrice;
	}

	public Double getAgreedIDV() {
		return agreedIDV;
	}

	public void setAgreedIDV(Double agreedIDV) {
		this.agreedIDV = agreedIDV;
	}
	
	public Integer getVechileType() {
		return vechileType;
	}

	public void setVechileType(Integer vechileType) {
		this.vechileType = vechileType;
	}
	

	public Double getVechileAge() {
		return vechileAge;
	}

	public void setVechileAge(Double vechileAge) {
		this.vechileAge = vechileAge;
	}

	public Integer getPersonalBankingRelationShip() {
		return personalBankingRelationShip;
	}

	public void setPersonalBankingRelationShip(Integer personalBankingRelationShip) {
		this.personalBankingRelationShip = personalBankingRelationShip;
	}

	public Boolean getIsAdhaarCard_p() {
		return isAdhaarCard_p;
	}

	public void setIsAdhaarCard_p(Boolean isAdhaarCard) {
		this.isAdhaarCard_p = isAdhaarCard;
	}

	public Integer getAdhaarCardValue() {
		return adhaarCardValue;
	}

	public void setAdhaarCardValue(Integer adhaarCardValue) {
		this.adhaarCardValue = adhaarCardValue;
	}

	public Double getCibilActualScoreVersion2() {
		return cibilActualScoreVersion2;
	}

	public void setCibilActualScoreVersion2(Double cibilActualScoreVersion2) {
		this.cibilActualScoreVersion2 = cibilActualScoreVersion2;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ScoreParameterRetailRequest [isWorkingExperience_p=" + isWorkingExperience_p
				+ ", isWorkingExperienceCurrent_p=" + isWorkingExperienceCurrent_p + ", isCibilScore_p="
				+ isCibilScore_p + ", isAge_p=" + isAge_p + ", isEducationQualifaction_p=" + isEducationQualifaction_p
				+ ", isEmployementType_p=" + isEmployementType_p + ", isEmployementJobCat_p=" + isEmployementJobCat_p
				+ ", isEmployementTypeSelfEmpBus_p=" + isEmployementTypeSelfEmpBus_p + ", isHouseOwnership_p="
				+ isHouseOwnership_p + ", isMaritalStatus_p=" + isMaritalStatus_p + ", isCurrentEmploymentStatus_p="
				+ isCurrentEmploymentStatus_p + ", isCategoryInfo_p=" + isCategoryInfo_p + ", isFixedObligationRatio_p="
				+ isFixedObligationRatio_p + ", isChequeBounce_p=" + isChequeBounce_p + ", isChequeBounceLast1Month_p="
				+ isChequeBounceLast1Month_p + ", isDPD_p=" + isDPD_p + ", isNetAnnualIncome_p=" + isNetAnnualIncome_p
				+ ", isAvailableIncome_p=" + isAvailableIncome_p + ", isEmiNmi_p=" + isEmiNmi_p
				+ ", isNoOfYearCurrentLocation_p=" + isNoOfYearCurrentLocation_p + ", isSpouseEmploymentDetails_p="
				+ isSpouseEmploymentDetails_p + ", isNumberOfDependents_p=" + isNumberOfDependents_p
				+ ", isDesignation_p=" + isDesignation_p + ", isLoanToIncomeRatio_p=" + isLoanToIncomeRatio_p
				+ ", isTotalBusProfExperiance_p=" + isTotalBusProfExperiance_p + ", isToir_p=" + isToir_p
				+ ", isResidenceType_p=" + isResidenceType_p + ", isMinBankingRelationship_p="
				+ isMinBankingRelationship_p + ", isNoOfApplicantsType_p=" + isNoOfApplicantsType_p
				+ ", isSpouseIncome_p=" + isSpouseIncome_p + ", isMonIncomePerDep_p=" + isMonIncomePerDep_p
				+ ", isEligibleTenure_p=" + isEligibleTenure_p + ", isAgeOfProperty_p=" + isAgeOfProperty_p
				+ ", isAvgOfTotalCheDepsitLast6Month_p=" + isAvgOfTotalCheDepsitLast6Month_p
				+ ", isMarketValueOfProp_p=" + isMarketValueOfProp_p + ", isLTV_p=" + isLTV_p + ", isNetWorth_p="
				+ isNetWorth_p + ", isRepaymentPeriod_p=" + isRepaymentPeriod_p + ", isLoanPurpose_p=" + isLoanPurpose_p
				+ ", isIncomeFromItr_p=" + isIncomeFromItr_p + ", isNMI_p=" + isNMI_p + ", isGMI_p=" + isGMI_p
				+ ", isAvgEodBalToToalDep_p=" + isAvgEodBalToToalDep_p + ", isNetWrothToLoanAmount_p="
				+ isNetWrothToLoanAmount_p + ", isAvgEODBalToTotalDeposite_p=" + isAvgEODBalToTotalDeposite_p
				+ ", isTenureOfTheLoan_p=" + isTenureOfTheLoan_p + ", isAnnualIncome_p=" + isAnnualIncome_p
				+ ", isIncomeProof_p=" + isIncomeProof_p + ", isEmiNmiRatio_p=" + isEmiNmiRatio_p
				+ ", isRepaymentMode_p=" + isRepaymentMode_p + ", isCarSegment_p=" + isCarSegment_p
				+ ", isSecurityCoverage_p=" + isSecurityCoverage_p + ", isTakeHomePay_p=" + isTakeHomePay_p
				+ ", isPersonalRelationShipWithBank_p=" + isPersonalRelationShipWithBank_p + ", isBorrowerMargin_p="
				+ isBorrowerMargin_p + ", isVehicleAge_p=" + isVehicleAge_p + ", workingExperience=" + workingExperience
				+ ", elAmountOnAverageScoring=" + elAmountOnAverageScoring + ", avgEodBalToToalDep="
				+ avgEodBalToToalDep + ", personalRelationShipWithBank=, workingExperienceCurrent=" + workingExperienceCurrent + ", emiNmiRatio=" + emiNmiRatio
				+ ", securityCoverage=" + securityCoverage + ", takeHomePay=" + takeHomePay + ", borrowerMargin="
				+ borrowerMargin + ", vechileAge=" + vechileAge + ", vechileType=" + vechileType
				+ ", totalBusProfExperiance=" + totalBusProfExperiance + ", minBankingRelationship="
				+ minBankingRelationship + ", carSegment=" + carSegment + ", incomeProofId=" + incomeProofId
				+ ", cibilScore=" + cibilScore + ", toir=" + toir + ", foir=" + foir + ", age=" + age + ", roi=" + roi
				+ ", educationQualification=" + educationQualification + ", employmentType=" + employmentType
				+ ", employmentTypeCatJob=" + employmentTypeCatJob + ", employmentTypeSelfEmpBus="
				+ employmentTypeSelfEmpBus + ", houseOwnership=" + houseOwnership + ", loanPurpose=" + loanPurpose
				+ ", loanPurposeQueType=" + loanPurposeQueType + ", loanPurposeQueValue=" + loanPurposeQueValue
				+ ", maritalStatus=" + maritalStatus + ", currentEmploymentStatus=" + currentEmploymentStatus
				+ ", categoryInfo=" + categoryInfo + ", lastYearTotalIncomeFromITR=" + lastYearTotalIncomeFromITR
				+ ", incomeFromItr=" + incomeFromItr + ", emiAmountFromCIBIL=" + emiAmountFromCIBIL + ", chequeBounce="
				+ chequeBounce + ", chequeBouncelast1Month=" + chequeBouncelast1Month
				+ ", avgOfTotalCheDepsitLast6Month=" + avgOfTotalCheDepsitLast6Month + ", dpd=" + dpd
				+ ", netAnnualIncome=" + netAnnualIncome + ", noOfDependants=" + noOfDependants + ", emi=" + emi
				+ ", nmi=" + nmi + ", gmi=" + gmi + ", ltv=" + ltv + ", netWorth=" + netWorth
				+ ", noOfYearCurrentLocation=" + noOfYearCurrentLocation + ", marketValueOfProp=" + marketValueOfProp
				+ ", spouseEmploymentDetails=" + spouseEmploymentDetails + ", numberOfDependents=" + numberOfDependents
				+ ", ageOfProperty=" + ageOfProperty + ", noOfApplicantsType=" + noOfApplicantsType + ", residenceType="
				+ residenceType + ", repaymentPeriod=" + repaymentPeriod + ", designation=" + designation
				+ ", loanToIncomeRatio=" + loanToIncomeRatio + ", loanAmtProposed=" + loanAmtProposed
				+ ", grossAnnualIncome=" + grossAnnualIncome + ", availableIncome=" + availableIncome
				+ ", spouseIncome=" + spouseIncome + ", eligibleTenure=" + eligibleTenure + ", loanPurposeModelId="
				+ loanPurposeModelId + ", netWrothToLoanAmount=" + netWrothToLoanAmount + ", avgEODBal=" + avgEODBal
				+ ", deposite=" + deposite + ", eligibleLoanAmountCircular=" + eligibleLoanAmountCircular
				+ ", exitingLoanObligation=" + exitingLoanObligation + ", tenureOfTheLoan=" + tenureOfTheLoan
				+ ", exShowRoomPrice=" + exShowRoomPrice + ", onRoadPrice=" + onRoadPrice + ", agreedIDV=" + agreedIDV
				+ ", repaymentMode=" + repaymentMode + ", isPurReadyBuiltHouse=" + isPurReadyBuiltHouse
				+ ", isPurReadyBuiltIndependentHouse=" + isPurReadyBuiltIndependentHouse + ", isPurResidetialFlat="
				+ isPurResidetialFlat + ", isPurResidetialFlatAllotee=" + isPurResidetialFlatAllotee
				+ ", isPurResidetialSite=" + isPurResidetialSite + ", isConstruResidetialBuid="
				+ isConstruResidetialBuid + ", isConstruExpaResBuild=" + isConstruExpaResBuild
				+ ", isConstruPurResSite=" + isConstruPurResSite + ", isRepPurReadyBuiltIndependant="
				+ isRepPurReadyBuiltIndependant + ", isRepRenImpFlatHouse=" + isRepRenImpFlatHouse
				+ ", isOthRefExcessMarginPaid=" + isOthRefExcessMarginPaid + ", isOthLoanReimbursementFlat="
				+ isOthLoanReimbursementFlat + ", isConsiderCoAppIncome=" + isConsiderCoAppIncome
				+ ", isBorrowersHavingAccounts=" + isBorrowersHavingAccounts + ", isBorrowersAvailingLoans="
				+ isBorrowersAvailingLoans + ", isBorrowersHavingSalaryAccounts=" + isBorrowersHavingSalaryAccounts
				+ ", isBorrowersAvailingCreaditCards=" + isBorrowersAvailingCreaditCards + ", isWomenApplicant="
				+ isWomenApplicant + ", isCheckOffDirectPayEmi=" + isCheckOffDirectPayEmi
				+ ", isCheckOffAgreetoPayOutstanding=" + isCheckOffAgreetoPayOutstanding + ", isCheckOffShiftSalAcc="
				+ isCheckOffShiftSalAcc + ", isCheckOffPayOutstndAmount=" + isCheckOffPayOutstndAmount
				+ ", isCheckOffNotChangeSalAcc=" + isCheckOffNotChangeSalAcc + ", isCreaditHisotryGreaterSixMonths="
				+ isCreaditHisotryGreaterSixMonths + ", isCreaditHisotryLessThenSixMonths="
				+ isCreaditHisotryLessThenSixMonths + ", isNoCreaditHistory=" + isNoCreaditHistory
				+ ", cibilActualScore=" + cibilActualScore + "]";
	}
}

