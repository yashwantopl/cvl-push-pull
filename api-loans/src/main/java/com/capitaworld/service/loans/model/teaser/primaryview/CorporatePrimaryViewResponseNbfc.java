package com.capitaworld.service.loans.model.teaser.primaryview;

/**
 * @author yashwant.menaria
 */
public class CorporatePrimaryViewResponseNbfc {

    private String nbfcBlendedExistingLoanAmount;
    private String nbfcBlendedAdditionalLoanAmount;
    private String nbfcBlendedAmount;
    private double nbfcBlendedRateOfInterest;
    private double nbfcBlendedTenure;
    private String nbfcBlendedEmiAmount;
    private double nbfcBlendedProcessingFees;

    private String bankExistingLoanAmount;
    private String bankAdditionalLoanAmount;
    private String bankAmount;
    private double bankRateOfInterest;
    private double bankTenure;
    private String bankEmiAmount;
    private double bankProcessingFees;

    private String nbfcExistingLoanAmount;
    private String nbfcAdditionalLoanAmount;
    private String nbfcAmount;
    private double nbfcRateOfInterest;
    private double nbfcTenure;
    private String nbfcEmiAmount;
    private double nbfcProcessingFees;

    private boolean checkFlag;

    //recommended fields
    private Double recommendedValue;
    private Double recommendedTenure;
    private Double recommendedRoi;
    private Double recommendedProcessingFee;
    private String recommendedRemark;

    //sanction fields
    private Double bankSanctionAmount;
    private Double bankSanctionRoi;
    private Double bankSanctionTenure;

    private Double nbfcSanctionAmount;
    private Double nbfcSanctionRoi;
    private Double nbfcSanctionTenure;

    //disbursement fields
    private Double bankDisbursedAmount;
    private String bankTransactionNo;
    private String bankRemark;

    private Double nbfcDisbursedAmount;
    private String nbfcTransactionNo;
    private String nbfcRemark;

    public String getNbfcBlendedExistingLoanAmount() {
        return nbfcBlendedExistingLoanAmount;
    }

    public void setNbfcBlendedExistingLoanAmount(String nbfcBlendedExistingLoanAmount) {
        this.nbfcBlendedExistingLoanAmount = nbfcBlendedExistingLoanAmount;
    }

    public String getNbfcBlendedAdditionalLoanAmount() {
        return nbfcBlendedAdditionalLoanAmount;
    }

    public void setNbfcBlendedAdditionalLoanAmount(String nbfcBlendedAdditionalLoanAmount) {
        this.nbfcBlendedAdditionalLoanAmount = nbfcBlendedAdditionalLoanAmount;
    }

    public String getNbfcBlendedAmount() {
        return nbfcBlendedAmount;
    }

    public void setNbfcBlendedAmount(String nbfcBlendedAmount) {
        this.nbfcBlendedAmount = nbfcBlendedAmount;
    }

    public double getNbfcBlendedRateOfInterest() {
        return nbfcBlendedRateOfInterest;
    }

    public void setNbfcBlendedRateOfInterest(double nbfcBlendedRateOfInterest) {
        this.nbfcBlendedRateOfInterest = nbfcBlendedRateOfInterest;
    }

    public double getNbfcBlendedTenure() {
        return nbfcBlendedTenure;
    }

    public void setNbfcBlendedTenure(double nbfcBlendedTenure) {
        this.nbfcBlendedTenure = nbfcBlendedTenure;
    }

    public String getNbfcBlendedEmiAmount() {
        return nbfcBlendedEmiAmount;
    }

    public void setNbfcBlendedEmiAmount(String nbfcBlendedEmiAmount) {
        this.nbfcBlendedEmiAmount = nbfcBlendedEmiAmount;
    }

    public double getNbfcBlendedProcessingFees() {
        return nbfcBlendedProcessingFees;
    }

    public void setNbfcBlendedProcessingFees(double nbfcBlendedProcessingFees) {
        this.nbfcBlendedProcessingFees = nbfcBlendedProcessingFees;
    }

    public String getBankExistingLoanAmount() {
        return bankExistingLoanAmount;
    }

    public void setBankExistingLoanAmount(String bankExistingLoanAmount) {
        this.bankExistingLoanAmount = bankExistingLoanAmount;
    }

    public String getBankAdditionalLoanAmount() {
        return bankAdditionalLoanAmount;
    }

    public void setBankAdditionalLoanAmount(String bankAdditionalLoanAmount) {
        this.bankAdditionalLoanAmount = bankAdditionalLoanAmount;
    }

    public String getBankAmount() {
        return bankAmount;
    }

    public void setBankAmount(String bankAmount) {
        this.bankAmount = bankAmount;
    }

    public double getBankRateOfInterest() {
        return bankRateOfInterest;
    }

    public void setBankRateOfInterest(double bankRateOfInterest) {
        this.bankRateOfInterest = bankRateOfInterest;
    }

    public double getBankTenure() {
        return bankTenure;
    }

    public void setBankTenure(double bankTenure) {
        this.bankTenure = bankTenure;
    }

    public String getBankEmiAmount() {
        return bankEmiAmount;
    }

    public void setBankEmiAmount(String bankEmiAmount) {
        this.bankEmiAmount = bankEmiAmount;
    }

    public double getBankProcessingFees() {
        return bankProcessingFees;
    }

    public void setBankProcessingFees(double bankProcessingFees) {
        this.bankProcessingFees = bankProcessingFees;
    }

    public String getNbfcExistingLoanAmount() {
        return nbfcExistingLoanAmount;
    }

    public void setNbfcExistingLoanAmount(String nbfcExistingLoanAmount) {
        this.nbfcExistingLoanAmount = nbfcExistingLoanAmount;
    }

    public String getNbfcAdditionalLoanAmount() {
        return nbfcAdditionalLoanAmount;
    }

    public void setNbfcAdditionalLoanAmount(String nbfcAdditionalLoanAmount) {
        this.nbfcAdditionalLoanAmount = nbfcAdditionalLoanAmount;
    }

    public String getNbfcAmount() {
        return nbfcAmount;
    }

    public void setNbfcAmount(String nbfcAmount) {
        this.nbfcAmount = nbfcAmount;
    }

    public double getNbfcRateOfInterest() {
        return nbfcRateOfInterest;
    }

    public void setNbfcRateOfInterest(double nbfcRateOfInterest) {
        this.nbfcRateOfInterest = nbfcRateOfInterest;
    }

    public double getNbfcTenure() {
        return nbfcTenure;
    }

    public void setNbfcTenure(double nbfcTenure) {
        this.nbfcTenure = nbfcTenure;
    }

    public String getNbfcEmiAmount() {
        return nbfcEmiAmount;
    }

    public void setNbfcEmiAmount(String nbfcEmiAmount) {
        this.nbfcEmiAmount = nbfcEmiAmount;
    }

    public double getNbfcProcessingFees() {
        return nbfcProcessingFees;
    }

    public void setNbfcProcessingFees(double nbfcProcessingFees) {
        this.nbfcProcessingFees = nbfcProcessingFees;
    }

    public boolean isCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(boolean checkFlag) {
        this.checkFlag = checkFlag;
    }

    public Double getRecommendedValue() {
        return recommendedValue;
    }

    public void setRecommendedValue(Double recommendedValue) {
        this.recommendedValue = recommendedValue;
    }

    public Double getRecommendedTenure() {
        return recommendedTenure;
    }

    public void setRecommendedTenure(Double recommendedTenure) {
        this.recommendedTenure = recommendedTenure;
    }

    public Double getRecommendedRoi() {
        return recommendedRoi;
    }

    public void setRecommendedRoi(Double recommendedRoi) {
        this.recommendedRoi = recommendedRoi;
    }

    public Double getRecommendedProcessingFee() {
        return recommendedProcessingFee;
    }

    public void setRecommendedProcessingFee(Double recommendedProcessingFee) {
        this.recommendedProcessingFee = recommendedProcessingFee;
    }

    public String getRecommendedRemark() {
        return recommendedRemark;
    }

    public void setRecommendedRemark(String recommendedRemark) {
        this.recommendedRemark = recommendedRemark;
    }

    public Double getBankSanctionAmount() {
        return bankSanctionAmount;
    }

    public void setBankSanctionAmount(Double bankSanctionAmount) {
        this.bankSanctionAmount = bankSanctionAmount;
    }

    public Double getBankSanctionRoi() {
        return bankSanctionRoi;
    }

    public void setBankSanctionRoi(Double bankSanctionRoi) {
        this.bankSanctionRoi = bankSanctionRoi;
    }

    public Double getBankSanctionTenure() {
        return bankSanctionTenure;
    }

    public void setBankSanctionTenure(Double bankSanctionTenure) {
        this.bankSanctionTenure = bankSanctionTenure;
    }

    public Double getNbfcSanctionAmount() {
        return nbfcSanctionAmount;
    }

    public void setNbfcSanctionAmount(Double nbfcSanctionAmount) {
        this.nbfcSanctionAmount = nbfcSanctionAmount;
    }

    public Double getNbfcSanctionRoi() {
        return nbfcSanctionRoi;
    }

    public void setNbfcSanctionRoi(Double nbfcSanctionRoi) {
        this.nbfcSanctionRoi = nbfcSanctionRoi;
    }

    public Double getNbfcSanctionTenure() {
        return nbfcSanctionTenure;
    }

    public void setNbfcSanctionTenure(Double nbfcSanctionTenure) {
        this.nbfcSanctionTenure = nbfcSanctionTenure;
    }

    public Double getBankDisbursedAmount() {
        return bankDisbursedAmount;
    }

    public void setBankDisbursedAmount(Double bankDisbursedAmount) {
        this.bankDisbursedAmount = bankDisbursedAmount;
    }

    public String getBankTransactionNo() {
        return bankTransactionNo;
    }

    public void setBankTransactionNo(String bankTransactionNo) {
        this.bankTransactionNo = bankTransactionNo;
    }

    public String getBankRemark() {
        return bankRemark;
    }

    public void setBankRemark(String bankRemark) {
        this.bankRemark = bankRemark;
    }

    public Double getNbfcDisbursedAmount() {
        return nbfcDisbursedAmount;
    }

    public void setNbfcDisbursedAmount(Double nbfcDisbursedAmount) {
        this.nbfcDisbursedAmount = nbfcDisbursedAmount;
    }

    public String getNbfcTransactionNo() {
        return nbfcTransactionNo;
    }

    public void setNbfcTransactionNo(String nbfcTransactionNo) {
        this.nbfcTransactionNo = nbfcTransactionNo;
    }

    public String getNbfcRemark() {
        return nbfcRemark;
    }

    public void setNbfcRemark(String nbfcRemark) {
        this.nbfcRemark = nbfcRemark;
    }
}
