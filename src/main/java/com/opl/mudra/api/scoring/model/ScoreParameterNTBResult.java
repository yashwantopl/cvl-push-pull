package com.opl.mudra.api.scoring.model;

public class ScoreParameterNTBResult {

    private Long applicationId;
    private Long fpProductId;
    private Long directorId;

    private Long scoringModelId;

    // txt

    private String workingExperienceInBusinessTxt;
    private String anyImmediatFamilyMemberInProposedLineBusinessTxt;
    private String cibilTransunionScoreTxt;
    private String ageOfPromoterTxt;
    private String academicQUalificationTxt;
    private String employmentTypeTxt;
    private String owningHouseTxt;
    private String maritalStatusTxt;
    private String combinedNetworth_text;
    private String itrSalaryIncomePast3PersentOfPromoterContributionTxt;
    private String constitutionOfBorrowerTxt;
    private String fixedObligationIncomeRatioTxt;
    private String chequeBouncesPastSixMonthsTxt;
    private String dayPastDueOnPersonalLoanTxt;
    private String assetCoverageRatioTxt;
    private String unitFactoryPremisesDetailsTxt;
    private String balanceGestationPeriodTxt;
    private String environmentCategoryTxt;

    // obt score

    private String workingExperienceInBusinessObtainedScore;
    private String anyImmediatFamilyMemberInProposedLineBusinessObtainedScore;
    private String cibilTransunionScoreObtainedScore;
    private String ageOfPromoterObtainedScore;
    private String academicQUalificationObtainedScore;
    private String employmentTypeObtainedScore;
    private String owningHouseObtainedScore;
    private String maritalStatusObtainedScore;
    private String combinedNetworthObtainedScore;
    private String itrSalaryIncomePast3PersentOfPromoterContributionObtainedScore;
    private String constitutionOfBorrowerObtainedScore;
    private String fixedObligationIncomeRatioObtainedScore;
    private String chequeBouncesPastSixMonthsObtainedScore;
    private String dayPastDueOnPersonalLoanObtainedScore;
    private String assetCoverageRatioObtainedScore;
    private String unitFactoryPremisesDetailsObtainedScore;
    private String balanceGestationPeriodObtainedScore;
    private String environmentCategoryObtainedScore;

    // max score
    private String workingExperienceInBusinessMaxScore;
    private String anyImmediatFamilyMemberInProposedLineBusinessMaxScore;
    private String cibilTransunionScoreMaxScore;
    private String ageOfPromoterMaxScore;
    private String academicQUalificationMaxScore;
    private String employmentTypeMaxScore;
    private String owningHouseMaxScore;
    private String maritalStatusMaxScore;
    private String combinedNetworthMaxScore;
    private String itrSalaryIncomePast3PersentOfPromoterContributionMaxScore;
    private String constitutionOfBorrowerMaxScore;
    private String fixedObligationIncomeRatioMaxScore;
    private String chequeBouncesPastSixMonthsMaxScore;
    private String dayPastDueOnPersonalLoanMaxScore;
    private String assetCoverageRatioMaxScore;
    private String unitFactoryPremisesDetailsMaxScore;
    private String balanceGestationPeriodMaxScore;
    private String environmentCategoryMaxScore;

    // code
    private String workingExperienceInBusinessCode;
    private String anyImmediatFamilyMemberInProposedLineBusinessCode;
    private String cibilTransunionScoreCode;
    private String ageOfPromoterCode;
    private String academicQUalificationCode;
    private String employmentTypeCode;
    private String owningHouseCode;
    private String maritalStatusCode;
    private String combinedNetworthCode;
    private String itrSalaryIncomePast3PersentOfPromoterContributionCode;
    private String constitutionOfBorrowerCode;
    private String fixedObligationIncomeRatioCode;
    private String chequeBouncesPastSixMonthsCode;
    private String dayPastDueOnPersonalLoanCode;
    private String assetCoverageRatioCode;
    private String unitFactoryPremisesDetailsCode;
    private String balanceGestationPeriodCode;
    private String environmentCategoryCode;


    private Double managementScoreWithRiskWeight;
    private Double financialScoreWithRiskWeight;
    private Double businessScoreWithRiskWeight;

    private Double managementRiskWeight;
    private Double financialRiskWeight;
    private Double businessRiskWeight;


    private Double managementRiskObtainScore;
    private Double financialRiskObtainScore;
    private Double businessRiskObtainScore;

    private Double totalScore;
    private String interpretation;

    public Long getDirectorId() {
        return directorId;
    }

    public void setDirectorId(Long directorId) {
        this.directorId = directorId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public Long getFpProductId() {
        return fpProductId;
    }

    public void setFpProductId(Long fpProductId) {
        this.fpProductId = fpProductId;
    }

    public Long getScoringModelId() {
        return scoringModelId;
    }

    public void setScoringModelId(Long scoringModelId) {
        this.scoringModelId = scoringModelId;
    }

    public String getWorkingExperienceInBusinessTxt() {
        return workingExperienceInBusinessTxt;
    }

    public void setWorkingExperienceInBusinessTxt(String workingExperienceInBusinessTxt) {
        this.workingExperienceInBusinessTxt = workingExperienceInBusinessTxt;
    }

    public String getAnyImmediatFamilyMemberInProposedLineBusinessTxt() {
        return anyImmediatFamilyMemberInProposedLineBusinessTxt;
    }

    public void setAnyImmediatFamilyMemberInProposedLineBusinessTxt(String anyImmediatFamilyMemberInProposedLineBusinessTxt) {
        this.anyImmediatFamilyMemberInProposedLineBusinessTxt = anyImmediatFamilyMemberInProposedLineBusinessTxt;
    }

    public String getCibilTransunionScoreTxt() {
        return cibilTransunionScoreTxt;
    }

    public void setCibilTransunionScoreTxt(String cibilTransunionScoreTxt) {
        this.cibilTransunionScoreTxt = cibilTransunionScoreTxt;
    }

    public String getAgeOfPromoterTxt() {
        return ageOfPromoterTxt;
    }

    public void setAgeOfPromoterTxt(String ageOfPromoterTxt) {
        this.ageOfPromoterTxt = ageOfPromoterTxt;
    }

    public String getAcademicQUalificationTxt() {
        return academicQUalificationTxt;
    }

    public void setAcademicQUalificationTxt(String academicQUalificationTxt) {
        this.academicQUalificationTxt = academicQUalificationTxt;
    }

    public String getEmploymentTypeTxt() {
        return employmentTypeTxt;
    }

    public void setEmploymentTypeTxt(String employmentTypeTxt) {
        this.employmentTypeTxt = employmentTypeTxt;
    }

    public String getOwningHouseTxt() {
        return owningHouseTxt;
    }

    public void setOwningHouseTxt(String owningHouseTxt) {
        this.owningHouseTxt = owningHouseTxt;
    }

    public String getMaritalStatusTxt() {
        return maritalStatusTxt;
    }

    public void setMaritalStatusTxt(String maritalStatusTxt) {
        this.maritalStatusTxt = maritalStatusTxt;
    }

    public String getCombinedNetworth_text() {
        return combinedNetworth_text;
    }

    public void setCombinedNetworth_text(String combinedNetworth_text) {
        this.combinedNetworth_text = combinedNetworth_text;
    }

    public String getItrSalaryIncomePast3PersentOfPromoterContributionTxt() {
        return itrSalaryIncomePast3PersentOfPromoterContributionTxt;
    }

    public void setItrSalaryIncomePast3PersentOfPromoterContributionTxt(String itrSalaryIncomePast3PersentOfPromoterContributionTxt) {
        this.itrSalaryIncomePast3PersentOfPromoterContributionTxt = itrSalaryIncomePast3PersentOfPromoterContributionTxt;
    }

    public String getConstitutionOfBorrowerTxt() {
        return constitutionOfBorrowerTxt;
    }

    public void setConstitutionOfBorrowerTxt(String constitutionOfBorrowerTxt) {
        this.constitutionOfBorrowerTxt = constitutionOfBorrowerTxt;
    }

    public String getFixedObligationIncomeRatioTxt() {
        return fixedObligationIncomeRatioTxt;
    }

    public void setFixedObligationIncomeRatioTxt(String fixedObligationIncomeRatioTxt) {
        this.fixedObligationIncomeRatioTxt = fixedObligationIncomeRatioTxt;
    }

    public String getChequeBouncesPastSixMonthsTxt() {
        return chequeBouncesPastSixMonthsTxt;
    }

    public void setChequeBouncesPastSixMonthsTxt(String chequeBouncesPastSixMonthsTxt) {
        this.chequeBouncesPastSixMonthsTxt = chequeBouncesPastSixMonthsTxt;
    }

    public String getDayPastDueOnPersonalLoanTxt() {
        return dayPastDueOnPersonalLoanTxt;
    }

    public void setDayPastDueOnPersonalLoanTxt(String dayPastDueOnPersonalLoanTxt) {
        this.dayPastDueOnPersonalLoanTxt = dayPastDueOnPersonalLoanTxt;
    }

    public String getAssetCoverageRatioTxt() {
        return assetCoverageRatioTxt;
    }

    public void setAssetCoverageRatioTxt(String assetCoverageRatioTxt) {
        this.assetCoverageRatioTxt = assetCoverageRatioTxt;
    }

    public String getUnitFactoryPremisesDetailsTxt() {
        return unitFactoryPremisesDetailsTxt;
    }

    public void setUnitFactoryPremisesDetailsTxt(String unitFactoryPremisesDetailsTxt) {
        this.unitFactoryPremisesDetailsTxt = unitFactoryPremisesDetailsTxt;
    }

    public String getBalanceGestationPeriodTxt() {
        return balanceGestationPeriodTxt;
    }

    public void setBalanceGestationPeriodTxt(String balanceGestationPeriodTxt) {
        this.balanceGestationPeriodTxt = balanceGestationPeriodTxt;
    }

    public String getEnvironmentCategoryTxt() {
        return environmentCategoryTxt;
    }

    public void setEnvironmentCategoryTxt(String environmentCategoryTxt) {
        this.environmentCategoryTxt = environmentCategoryTxt;
    }

    public String getWorkingExperienceInBusinessObtainedScore() {
        return workingExperienceInBusinessObtainedScore;
    }

    public void setWorkingExperienceInBusinessObtainedScore(String workingExperienceInBusinessObtainedScore) {
        this.workingExperienceInBusinessObtainedScore = workingExperienceInBusinessObtainedScore;
    }

    public String getAnyImmediatFamilyMemberInProposedLineBusinessObtainedScore() {
        return anyImmediatFamilyMemberInProposedLineBusinessObtainedScore;
    }

    public void setAnyImmediatFamilyMemberInProposedLineBusinessObtainedScore(String anyImmediatFamilyMemberInProposedLineBusinessObtainedScore) {
        this.anyImmediatFamilyMemberInProposedLineBusinessObtainedScore = anyImmediatFamilyMemberInProposedLineBusinessObtainedScore;
    }

    public String getCibilTransunionScoreObtainedScore() {
        return cibilTransunionScoreObtainedScore;
    }

    public void setCibilTransunionScoreObtainedScore(String cibilTransunionScoreObtainedScore) {
        this.cibilTransunionScoreObtainedScore = cibilTransunionScoreObtainedScore;
    }

    public String getAgeOfPromoterObtainedScore() {
        return ageOfPromoterObtainedScore;
    }

    public void setAgeOfPromoterObtainedScore(String ageOfPromoterObtainedScore) {
        this.ageOfPromoterObtainedScore = ageOfPromoterObtainedScore;
    }

    public String getAcademicQUalificationObtainedScore() {
        return academicQUalificationObtainedScore;
    }

    public void setAcademicQUalificationObtainedScore(String academicQUalificationObtainedScore) {
        this.academicQUalificationObtainedScore = academicQUalificationObtainedScore;
    }

    public String getEmploymentTypeObtainedScore() {
        return employmentTypeObtainedScore;
    }

    public void setEmploymentTypeObtainedScore(String employmentTypeObtainedScore) {
        this.employmentTypeObtainedScore = employmentTypeObtainedScore;
    }

    public String getOwningHouseObtainedScore() {
        return owningHouseObtainedScore;
    }

    public void setOwningHouseObtainedScore(String owningHouseObtainedScore) {
        this.owningHouseObtainedScore = owningHouseObtainedScore;
    }

    public String getMaritalStatusObtainedScore() {
        return maritalStatusObtainedScore;
    }

    public void setMaritalStatusObtainedScore(String maritalStatusObtainedScore) {
        this.maritalStatusObtainedScore = maritalStatusObtainedScore;
    }

    public String getCombinedNetworthObtainedScore() {
        return combinedNetworthObtainedScore;
    }

    public void setCombinedNetworthObtainedScore(String combinedNetworthObtainedScore) {
        this.combinedNetworthObtainedScore = combinedNetworthObtainedScore;
    }

    public String getItrSalaryIncomePast3PersentOfPromoterContributionObtainedScore() {
        return itrSalaryIncomePast3PersentOfPromoterContributionObtainedScore;
    }

    public void setItrSalaryIncomePast3PersentOfPromoterContributionObtainedScore(String itrSalaryIncomePast3PersentOfPromoterContributionObtainedScore) {
        this.itrSalaryIncomePast3PersentOfPromoterContributionObtainedScore = itrSalaryIncomePast3PersentOfPromoterContributionObtainedScore;
    }

    public String getConstitutionOfBorrowerObtainedScore() {
        return constitutionOfBorrowerObtainedScore;
    }

    public void setConstitutionOfBorrowerObtainedScore(String constitutionOfBorrowerObtainedScore) {
        this.constitutionOfBorrowerObtainedScore = constitutionOfBorrowerObtainedScore;
    }

    public String getFixedObligationIncomeRatioObtainedScore() {
        return fixedObligationIncomeRatioObtainedScore;
    }

    public void setFixedObligationIncomeRatioObtainedScore(String fixedObligationIncomeRatioObtainedScore) {
        this.fixedObligationIncomeRatioObtainedScore = fixedObligationIncomeRatioObtainedScore;
    }

    public String getChequeBouncesPastSixMonthsObtainedScore() {
        return chequeBouncesPastSixMonthsObtainedScore;
    }

    public void setChequeBouncesPastSixMonthsObtainedScore(String chequeBouncesPastSixMonthsObtainedScore) {
        this.chequeBouncesPastSixMonthsObtainedScore = chequeBouncesPastSixMonthsObtainedScore;
    }

    public String getDayPastDueOnPersonalLoanObtainedScore() {
        return dayPastDueOnPersonalLoanObtainedScore;
    }

    public void setDayPastDueOnPersonalLoanObtainedScore(String dayPastDueOnPersonalLoanObtainedScore) {
        this.dayPastDueOnPersonalLoanObtainedScore = dayPastDueOnPersonalLoanObtainedScore;
    }

    public String getAssetCoverageRatioObtainedScore() {
        return assetCoverageRatioObtainedScore;
    }

    public void setAssetCoverageRatioObtainedScore(String assetCoverageRatioObtainedScore) {
        this.assetCoverageRatioObtainedScore = assetCoverageRatioObtainedScore;
    }

    public String getUnitFactoryPremisesDetailsObtainedScore() {
        return unitFactoryPremisesDetailsObtainedScore;
    }

    public void setUnitFactoryPremisesDetailsObtainedScore(String unitFactoryPremisesDetailsObtainedScore) {
        this.unitFactoryPremisesDetailsObtainedScore = unitFactoryPremisesDetailsObtainedScore;
    }

    public String getBalanceGestationPeriodObtainedScore() {
        return balanceGestationPeriodObtainedScore;
    }

    public void setBalanceGestationPeriodObtainedScore(String balanceGestationPeriodObtainedScore) {
        this.balanceGestationPeriodObtainedScore = balanceGestationPeriodObtainedScore;
    }

    public String getEnvironmentCategoryObtainedScore() {
        return environmentCategoryObtainedScore;
    }

    public void setEnvironmentCategoryObtainedScore(String environmentCategoryObtainedScore) {
        this.environmentCategoryObtainedScore = environmentCategoryObtainedScore;
    }

    public String getWorkingExperienceInBusinessMaxScore() {
        return workingExperienceInBusinessMaxScore;
    }

    public void setWorkingExperienceInBusinessMaxScore(String workingExperienceInBusinessMaxScore) {
        this.workingExperienceInBusinessMaxScore = workingExperienceInBusinessMaxScore;
    }

    public String getAnyImmediatFamilyMemberInProposedLineBusinessMaxScore() {
        return anyImmediatFamilyMemberInProposedLineBusinessMaxScore;
    }

    public void setAnyImmediatFamilyMemberInProposedLineBusinessMaxScore(String anyImmediatFamilyMemberInProposedLineBusinessMaxScore) {
        this.anyImmediatFamilyMemberInProposedLineBusinessMaxScore = anyImmediatFamilyMemberInProposedLineBusinessMaxScore;
    }

    public String getCibilTransunionScoreMaxScore() {
        return cibilTransunionScoreMaxScore;
    }

    public void setCibilTransunionScoreMaxScore(String cibilTransunionScoreMaxScore) {
        this.cibilTransunionScoreMaxScore = cibilTransunionScoreMaxScore;
    }

    public String getAgeOfPromoterMaxScore() {
        return ageOfPromoterMaxScore;
    }

    public void setAgeOfPromoterMaxScore(String ageOfPromoterMaxScore) {
        this.ageOfPromoterMaxScore = ageOfPromoterMaxScore;
    }

    public String getAcademicQUalificationMaxScore() {
        return academicQUalificationMaxScore;
    }

    public void setAcademicQUalificationMaxScore(String academicQUalificationMaxScore) {
        this.academicQUalificationMaxScore = academicQUalificationMaxScore;
    }

    public String getEmploymentTypeMaxScore() {
        return employmentTypeMaxScore;
    }

    public void setEmploymentTypeMaxScore(String employmentTypeMaxScore) {
        this.employmentTypeMaxScore = employmentTypeMaxScore;
    }

    public String getOwningHouseMaxScore() {
        return owningHouseMaxScore;
    }

    public void setOwningHouseMaxScore(String owningHouseMaxScore) {
        this.owningHouseMaxScore = owningHouseMaxScore;
    }

    public String getMaritalStatusMaxScore() {
        return maritalStatusMaxScore;
    }

    public void setMaritalStatusMaxScore(String maritalStatusMaxScore) {
        this.maritalStatusMaxScore = maritalStatusMaxScore;
    }

    public String getCombinedNetworthMaxScore() {
        return combinedNetworthMaxScore;
    }

    public void setCombinedNetworthMaxScore(String combinedNetworthMaxScore) {
        this.combinedNetworthMaxScore = combinedNetworthMaxScore;
    }

    public String getItrSalaryIncomePast3PersentOfPromoterContributionMaxScore() {
        return itrSalaryIncomePast3PersentOfPromoterContributionMaxScore;
    }

    public void setItrSalaryIncomePast3PersentOfPromoterContributionMaxScore(String itrSalaryIncomePast3PersentOfPromoterContributionMaxScore) {
        this.itrSalaryIncomePast3PersentOfPromoterContributionMaxScore = itrSalaryIncomePast3PersentOfPromoterContributionMaxScore;
    }

    public String getConstitutionOfBorrowerMaxScore() {
        return constitutionOfBorrowerMaxScore;
    }

    public void setConstitutionOfBorrowerMaxScore(String constitutionOfBorrowerMaxScore) {
        this.constitutionOfBorrowerMaxScore = constitutionOfBorrowerMaxScore;
    }

    public String getFixedObligationIncomeRatioMaxScore() {
        return fixedObligationIncomeRatioMaxScore;
    }

    public void setFixedObligationIncomeRatioMaxScore(String fixedObligationIncomeRatioMaxScore) {
        this.fixedObligationIncomeRatioMaxScore = fixedObligationIncomeRatioMaxScore;
    }

    public String getChequeBouncesPastSixMonthsMaxScore() {
        return chequeBouncesPastSixMonthsMaxScore;
    }

    public void setChequeBouncesPastSixMonthsMaxScore(String chequeBouncesPastSixMonthsMaxScore) {
        this.chequeBouncesPastSixMonthsMaxScore = chequeBouncesPastSixMonthsMaxScore;
    }

    public String getDayPastDueOnPersonalLoanMaxScore() {
        return dayPastDueOnPersonalLoanMaxScore;
    }

    public void setDayPastDueOnPersonalLoanMaxScore(String dayPastDueOnPersonalLoanMaxScore) {
        this.dayPastDueOnPersonalLoanMaxScore = dayPastDueOnPersonalLoanMaxScore;
    }

    public String getAssetCoverageRatioMaxScore() {
        return assetCoverageRatioMaxScore;
    }

    public void setAssetCoverageRatioMaxScore(String assetCoverageRatioMaxScore) {
        this.assetCoverageRatioMaxScore = assetCoverageRatioMaxScore;
    }

    public String getUnitFactoryPremisesDetailsMaxScore() {
        return unitFactoryPremisesDetailsMaxScore;
    }

    public void setUnitFactoryPremisesDetailsMaxScore(String unitFactoryPremisesDetailsMaxScore) {
        this.unitFactoryPremisesDetailsMaxScore = unitFactoryPremisesDetailsMaxScore;
    }

    public String getBalanceGestationPeriodMaxScore() {
        return balanceGestationPeriodMaxScore;
    }

    public void setBalanceGestationPeriodMaxScore(String balanceGestationPeriodMaxScore) {
        this.balanceGestationPeriodMaxScore = balanceGestationPeriodMaxScore;
    }

    public String getEnvironmentCategoryMaxScore() {
        return environmentCategoryMaxScore;
    }

    public void setEnvironmentCategoryMaxScore(String environmentCategoryMaxScore) {
        this.environmentCategoryMaxScore = environmentCategoryMaxScore;
    }

    public String getWorkingExperienceInBusinessCode() {
        return workingExperienceInBusinessCode;
    }

    public void setWorkingExperienceInBusinessCode(String workingExperienceInBusinessCode) {
        this.workingExperienceInBusinessCode = workingExperienceInBusinessCode;
    }

    public String getAnyImmediatFamilyMemberInProposedLineBusinessCode() {
        return anyImmediatFamilyMemberInProposedLineBusinessCode;
    }

    public void setAnyImmediatFamilyMemberInProposedLineBusinessCode(String anyImmediatFamilyMemberInProposedLineBusinessCode) {
        this.anyImmediatFamilyMemberInProposedLineBusinessCode = anyImmediatFamilyMemberInProposedLineBusinessCode;
    }

    public String getCibilTransunionScoreCode() {
        return cibilTransunionScoreCode;
    }

    public void setCibilTransunionScoreCode(String cibilTransunionScoreCode) {
        this.cibilTransunionScoreCode = cibilTransunionScoreCode;
    }

    public String getAgeOfPromoterCode() {
        return ageOfPromoterCode;
    }

    public void setAgeOfPromoterCode(String ageOfPromoterCode) {
        this.ageOfPromoterCode = ageOfPromoterCode;
    }

    public String getAcademicQUalificationCode() {
        return academicQUalificationCode;
    }

    public void setAcademicQUalificationCode(String academicQUalificationCode) {
        this.academicQUalificationCode = academicQUalificationCode;
    }

    public String getEmploymentTypeCode() {
        return employmentTypeCode;
    }

    public void setEmploymentTypeCode(String employmentTypeCode) {
        this.employmentTypeCode = employmentTypeCode;
    }

    public String getOwningHouseCode() {
        return owningHouseCode;
    }

    public void setOwningHouseCode(String owningHouseCode) {
        this.owningHouseCode = owningHouseCode;
    }

    public String getMaritalStatusCode() {
        return maritalStatusCode;
    }

    public void setMaritalStatusCode(String maritalStatusCode) {
        this.maritalStatusCode = maritalStatusCode;
    }

    public String getCombinedNetworthCode() {
        return combinedNetworthCode;
    }

    public void setCombinedNetworthCode(String combinedNetworthCode) {
        this.combinedNetworthCode = combinedNetworthCode;
    }

    public String getItrSalaryIncomePast3PersentOfPromoterContributionCode() {
        return itrSalaryIncomePast3PersentOfPromoterContributionCode;
    }

    public void setItrSalaryIncomePast3PersentOfPromoterContributionCode(String itrSalaryIncomePast3PersentOfPromoterContributionCode) {
        this.itrSalaryIncomePast3PersentOfPromoterContributionCode = itrSalaryIncomePast3PersentOfPromoterContributionCode;
    }

    public String getConstitutionOfBorrowerCode() {
        return constitutionOfBorrowerCode;
    }

    public void setConstitutionOfBorrowerCode(String constitutionOfBorrowerCode) {
        this.constitutionOfBorrowerCode = constitutionOfBorrowerCode;
    }

    public String getFixedObligationIncomeRatioCode() {
        return fixedObligationIncomeRatioCode;
    }

    public void setFixedObligationIncomeRatioCode(String fixedObligationIncomeRatioCode) {
        this.fixedObligationIncomeRatioCode = fixedObligationIncomeRatioCode;
    }

    public String getChequeBouncesPastSixMonthsCode() {
        return chequeBouncesPastSixMonthsCode;
    }

    public void setChequeBouncesPastSixMonthsCode(String chequeBouncesPastSixMonthsCode) {
        this.chequeBouncesPastSixMonthsCode = chequeBouncesPastSixMonthsCode;
    }

    public String getDayPastDueOnPersonalLoanCode() {
        return dayPastDueOnPersonalLoanCode;
    }

    public void setDayPastDueOnPersonalLoanCode(String dayPastDueOnPersonalLoanCode) {
        this.dayPastDueOnPersonalLoanCode = dayPastDueOnPersonalLoanCode;
    }

    public String getAssetCoverageRatioCode() {
        return assetCoverageRatioCode;
    }

    public void setAssetCoverageRatioCode(String assetCoverageRatioCode) {
        this.assetCoverageRatioCode = assetCoverageRatioCode;
    }

    public String getUnitFactoryPremisesDetailsCode() {
        return unitFactoryPremisesDetailsCode;
    }

    public void setUnitFactoryPremisesDetailsCode(String unitFactoryPremisesDetailsCode) {
        this.unitFactoryPremisesDetailsCode = unitFactoryPremisesDetailsCode;
    }

    public String getBalanceGestationPeriodCode() {
        return balanceGestationPeriodCode;
    }

    public void setBalanceGestationPeriodCode(String balanceGestationPeriodCode) {
        this.balanceGestationPeriodCode = balanceGestationPeriodCode;
    }

    public String getEnvironmentCategoryCode() {
        return environmentCategoryCode;
    }

    public void setEnvironmentCategoryCode(String environmentCategoryCode) {
        this.environmentCategoryCode = environmentCategoryCode;
    }

    public Double getManagementScoreWithRiskWeight() {
        return managementScoreWithRiskWeight;
    }

    public void setManagementScoreWithRiskWeight(Double managementScoreWithRiskWeight) {
        this.managementScoreWithRiskWeight = managementScoreWithRiskWeight;
    }

    public Double getFinancialScoreWithRiskWeight() {
        return financialScoreWithRiskWeight;
    }

    public void setFinancialScoreWithRiskWeight(Double financialScoreWithRiskWeight) {
        this.financialScoreWithRiskWeight = financialScoreWithRiskWeight;
    }

    public Double getBusinessScoreWithRiskWeight() {
        return businessScoreWithRiskWeight;
    }

    public void setBusinessScoreWithRiskWeight(Double businessScoreWithRiskWeight) {
        this.businessScoreWithRiskWeight = businessScoreWithRiskWeight;
    }

    public Double getManagementRiskWeight() {
        return managementRiskWeight;
    }

    public void setManagementRiskWeight(Double managementRiskWeight) {
        this.managementRiskWeight = managementRiskWeight;
    }

    public Double getFinancialRiskWeight() {
        return financialRiskWeight;
    }

    public void setFinancialRiskWeight(Double financialRiskWeight) {
        this.financialRiskWeight = financialRiskWeight;
    }

    public Double getBusinessRiskWeight() {
        return businessRiskWeight;
    }

    public void setBusinessRiskWeight(Double businessRiskWeight) {
        this.businessRiskWeight = businessRiskWeight;
    }

    public Double getManagementRiskObtainScore() {
        return managementRiskObtainScore;
    }

    public void setManagementRiskObtainScore(Double managementRiskObtainScore) {
        this.managementRiskObtainScore = managementRiskObtainScore;
    }

    public Double getFinancialRiskObtainScore() {
        return financialRiskObtainScore;
    }

    public void setFinancialRiskObtainScore(Double financialRiskObtainScore) {
        this.financialRiskObtainScore = financialRiskObtainScore;
    }

    public Double getBusinessRiskObtainScore() {
        return businessRiskObtainScore;
    }

    public void setBusinessRiskObtainScore(Double businessRiskObtainScore) {
        this.businessRiskObtainScore = businessRiskObtainScore;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }
}
