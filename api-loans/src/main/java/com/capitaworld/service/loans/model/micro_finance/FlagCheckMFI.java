package com.capitaworld.service.loans.model.micro_finance;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class FlagCheckMFI {

    private Boolean isPersonalDetailsFilled;

    private Boolean isFamilyDetailsFilled;

    private Boolean isNomineeDetailsFilled;

    private Boolean isAcadamicDetailsFilled;

    private Boolean isBankDetailsFilled;

    private Boolean isAccountDetailsFilled;

    private Boolean isExistingLoanDetailsFilled;

    private Boolean isIncomeDetailsFilled;

    private Boolean isFamilyIncomeFilled;

    private Boolean isFamilyExpenseFilled;

    private Boolean isExpectedIncomeFilled;

    private Boolean isPPIFilled;

    private Boolean isProjectDetailsFilled;

    private Boolean isApplyLoanFilled;

    private Boolean isCostProjectFilled;

    private Boolean isMeanFinanceFilled;

    private Boolean isCashFlowDetailsFilled;

    private Boolean isAssetsDetailsFilled;

    private Boolean isCurrentAssetsFilled;

    private Boolean isFixedAssetsFilled;

    private Boolean isCurrntLiabilityFilled;

    private Boolean isRepaymentDetailsFilled;

    private Boolean isConsentFormFilled;

    public FlagCheckMFI() {
    }

    public Boolean getIsPersonalDetailsFilled() {
        return isPersonalDetailsFilled;
    }

    public void setIsPersonalDetailsFilled(Boolean isPersonalDetailsFilled) {
        this.isPersonalDetailsFilled = isPersonalDetailsFilled;
    }

    public Boolean getIsFamilyDetailsFilled() {
        return isFamilyDetailsFilled;
    }

    public void setIsFamilyDetailsFilled(Boolean isFamilyDetailsFilled) {
        this.isFamilyDetailsFilled = isFamilyDetailsFilled;
    }

    public Boolean getIsNomineeDetailsFilled() {
        return isNomineeDetailsFilled;
    }

    public void setIsNomineeDetailsFilled(Boolean isNomineeDetailsFilled) {
        this.isNomineeDetailsFilled = isNomineeDetailsFilled;
    }

    public Boolean getIsAcadamicDetailsFilled() {
        return isAcadamicDetailsFilled;
    }

    public void setIsAcadamicDetailsFilled(Boolean isAcadamicDetailsFilled) {
        this.isAcadamicDetailsFilled = isAcadamicDetailsFilled;
    }

    public Boolean getIsBankDetailsFilled() {
        return isBankDetailsFilled;
    }

    public void setIsBankDetailsFilled(Boolean isBankDetailsFilled) {
        this.isBankDetailsFilled = isBankDetailsFilled;
    }

    public Boolean getIsAccountDetailsFilled() {
        return isAccountDetailsFilled;
    }

    public void setIsAccountDetailsFilled(Boolean isAccountDetailsFilled) {
        this.isAccountDetailsFilled = isAccountDetailsFilled;
    }

    public Boolean getIsExistingLoanDetailsFilled() {
        return isExistingLoanDetailsFilled;
    }

    public void setIsExistingLoanDetailsFilled(Boolean isExistingLoanDetailsFilled) {
        this.isExistingLoanDetailsFilled = isExistingLoanDetailsFilled;
    }

    public Boolean getIsIncomeDetailsFilled() {
        return isIncomeDetailsFilled;
    }

    public void setIsIncomeDetailsFilled(Boolean isIncomeDetailsFilled) {
        this.isIncomeDetailsFilled = isIncomeDetailsFilled;
    }

    public Boolean getIsFamilyIncomeFilled() {
        return isFamilyIncomeFilled;
    }

    public void setIsFamilyIncomeFilled(Boolean isFamilyIncomeFilled) {
        this.isFamilyIncomeFilled = isFamilyIncomeFilled;
    }

    public Boolean getIsFamilyExpenseFilled() {
        return isFamilyExpenseFilled;
    }

    public void setIsFamilyExpenseFilled(Boolean isFamilyExpenseFilled) {
        this.isFamilyExpenseFilled = isFamilyExpenseFilled;
    }

    public Boolean getIsExpectedIncomeFilled() {
        return isExpectedIncomeFilled;
    }

    public void setIsExpectedIncomeFilled(Boolean isExpectedIncomeFilled) {
        this.isExpectedIncomeFilled = isExpectedIncomeFilled;
    }

    public Boolean getIsPPIFilled() {
        return isPPIFilled;
    }

    public void setIsPPIFilled(Boolean isPPIFilled) {
        this.isPPIFilled = isPPIFilled;
    }

    public Boolean getIsProjectDetailsFilled() {
        return isProjectDetailsFilled;
    }

    public void setIsProjectDetailsFilled(Boolean isProjectDetailsFilled) {
        this.isProjectDetailsFilled = isProjectDetailsFilled;
    }

    public Boolean getIsApplyLoanFilled() {
        return isApplyLoanFilled;
    }

    public void setIsApplyLoanFilled(Boolean isApplyLoanFilled) {
        this.isApplyLoanFilled = isApplyLoanFilled;
    }

    public Boolean getIsCostProjectFilled() {
        return isCostProjectFilled;
    }

    public void setIsCostProjectFilled(Boolean isCostProjectFilled) {
        this.isCostProjectFilled = isCostProjectFilled;
    }

    public Boolean getIsMeanFinanceFilled() {
        return isMeanFinanceFilled;
    }

    public void setIsMeanFinanceFilled(Boolean isMeanFinanceFilled) {
        this.isMeanFinanceFilled = isMeanFinanceFilled;
    }

    public Boolean getIsCashFlowDetailsFilled() {
        return isCashFlowDetailsFilled;
    }

    public void setIsCashFlowDetailsFilled(Boolean isCashFlowDetailsFilled) {
        this.isCashFlowDetailsFilled = isCashFlowDetailsFilled;
    }

    public Boolean getIsAssetsDetailsFilled() {
        return isAssetsDetailsFilled;
    }

    public void setIsAssetsDetailsFilled(Boolean isAssetsDetailsFilled) {
        this.isAssetsDetailsFilled = isAssetsDetailsFilled;
    }

    public Boolean getIsCurrentAssetsFilled() {
        return isCurrentAssetsFilled;
    }

    public void setIsCurrentAssetsFilled(Boolean isCurrentAssetsFilled) {
        this.isCurrentAssetsFilled = isCurrentAssetsFilled;
    }

    public Boolean getIsFixedAssetsFilled() {
        return isFixedAssetsFilled;
    }

    public void setIsFixedAssetsFilled(Boolean isFixedAssetsFilled) {
        this.isFixedAssetsFilled = isFixedAssetsFilled;
    }

    public Boolean getIsCurrntLiabilityFilled() {
        return isCurrntLiabilityFilled;
    }

    public void setIsCurrntLiabilityFilled(Boolean isCurrntLiabilityFilled) {
        this.isCurrntLiabilityFilled = isCurrntLiabilityFilled;
    }

    public Boolean getIsRepaymentDetailsFilled() {
        return isRepaymentDetailsFilled;
    }

    public void setIsRepaymentDetailsFilled(Boolean isRepaymentDetailsFilled) {
        this.isRepaymentDetailsFilled = isRepaymentDetailsFilled;
    }

    public Boolean getIsConsentFormFilled() {
        return isConsentFormFilled;
    }

    public void setIsConsentFormFilled(Boolean isConsentFormFilled) {
        this.isConsentFormFilled = isConsentFormFilled;
    }
}
