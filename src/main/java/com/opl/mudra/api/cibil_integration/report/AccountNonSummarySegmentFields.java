
package com.opl.mudra.api.cibil_integration.report;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "PaymentHistory2FieldLength",
    "DateOpenedOrDisbursed",
    "CurrentBalance",
    "PaymentHistoryEndDate",
    "ReportingMemberShortName",
    "PaymentHistory2",
    "ReportingMemberShortNameFieldLength",
    "AccountType",
    "HighCreditOrSanctionedAmountFieldLength",
    "PaymentHistory1",
    "DateReportedAndCertified",
    "CurrentBalanceFieldLength",
    "DateOfLastPayment",
    "DateClosed",
    "OwenershipIndicator",
    "PaymentHistory1FieldLength",
    "PaymentHistoryStartDate",
    "HighCreditOrSanctionedAmount",
    "WrittenOffAmountPrincipal",
    "SettlementAmountFieldLength",
    "WrittenOffAmountTotal",
    "WrittenOffAmountPrincipalFieldLength",
    "SettlementAmount",
    "CreditLimit",
    "PaymentFrequency",
    "CreditLimitFieldLength",
    "WrittenOffAndSettled",
    "WrittenOffAmountTotalFieldLength",
    "EmiAmount",
    "RateOfInterest",
    "RepaymentTenure",
    "SuitFiledOrWilfulDefault",
    "AmountOverdue",
    "RepaymentTenureFieldLength"
})
public class AccountNonSummarySegmentFields implements Serializable
{

    @JsonProperty("PaymentHistory2FieldLength")
    private Integer paymentHistory2FieldLength;
    @JsonProperty("DateOpenedOrDisbursed")
    private String dateOpenedOrDisbursed;
    @JsonProperty("CurrentBalance")
    private Double currentBalance;
    @JsonProperty("PaymentHistoryEndDate")
    private String paymentHistoryEndDate;
    @JsonProperty("ReportingMemberShortName")
    private String reportingMemberShortName;
    @JsonProperty("PaymentHistory2")
    private String paymentHistory2;
    @JsonProperty("ReportingMemberShortNameFieldLength")
    private Integer reportingMemberShortNameFieldLength;
    @JsonProperty("AccountType")
    private String accountType;
    @JsonProperty("HighCreditOrSanctionedAmountFieldLength")
    private String highCreditOrSanctionedAmountFieldLength;
    @JsonProperty("PaymentHistory1")
    private String paymentHistory1;
    @JsonProperty("DateReportedAndCertified")
    private String dateReportedAndCertified;
    @JsonProperty("CurrentBalanceFieldLength")
    private String currentBalanceFieldLength;
    @JsonProperty("DateOfLastPayment")
    private String dateOfLastPayment;
    @JsonProperty("DateClosed")
    private String dateClosed;
    @JsonProperty("OwenershipIndicator")
    private Integer owenershipIndicator;
    @JsonProperty("PaymentHistory1FieldLength")
    private String paymentHistory1FieldLength;
    @JsonProperty("PaymentHistoryStartDate")
    private String paymentHistoryStartDate;
    @JsonProperty("HighCreditOrSanctionedAmount")
    private Double highCreditOrSanctionedAmount;
    @JsonProperty("WrittenOffAmountPrincipal")
    private Integer writtenOffAmountPrincipal;
    @JsonProperty("SettlementAmountFieldLength")
    private String settlementAmountFieldLength;
    @JsonProperty("WrittenOffAmountTotal")
    private Double writtenOffAmountTotal;
    @JsonProperty("WrittenOffAmountPrincipalFieldLength")
    private String writtenOffAmountPrincipalFieldLength;
    @JsonProperty("SettlementAmount")
    private Double settlementAmount;
    @JsonProperty("CreditLimit")
    private Double creditLimit;
    @JsonProperty("PaymentFrequency")
    private String paymentFrequency;
    @JsonProperty("CreditLimitFieldLength")
    private String creditLimitFieldLength;
    @JsonProperty("WrittenOffAndSettled")
    private String writtenOffAndSettled;
    @JsonProperty("WrittenOffAmountTotalFieldLength")
    private String writtenOffAmountTotalFieldLength;
    @JsonProperty("EmiAmount")
    private Double emiAmount;
    @JsonProperty("RateOfInterest")
    private Double rateOfInterest;
    @JsonProperty("RepaymentTenure")
    private Integer repaymentTenure;
    @JsonProperty("RepaymentTenureFieldLength")
    private Integer repaymentTenureFieldLength;
    @JsonProperty("AmountOverdue")
    private Double amountOverdue;
    @JsonProperty("SuitFiledOrWilfulDefault")
    private String suitFiledOrWilfulDefault;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 8282207029371274443L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AccountNonSummarySegmentFields() {
    }

    /**
     * 
     * @param writtenOffAmountTotalFieldLength
     * @param paymentHistory2FieldLength
     * @param highCreditOrSanctionedAmount
     * @param settlementAmountFieldLength
     * @param creditLimit
     * @param currentBalance
     * @param dateOfLastPayment
     * @param owenershipIndicator
     * @param dateClosed
     * @param reportingMemberShortNameFieldLength
     * @param reportingMemberShortName
     * @param paymentHistoryEndDate
     * @param paymentHistory2
     * @param paymentHistory1
     * @param dateOpenedOrDisbursed
     * @param writtenOffAmountTotal
     * @param writtenOffAndSettled
     * @param dateReportedAndCertified
     * @param paymentFrequency
     * @param settlementAmount
     * @param highCreditOrSanctionedAmountFieldLength
     * @param writtenOffAmountPrincipalFieldLength
     * @param writtenOffAmountPrincipal
     * @param accountType
     * @param creditLimitFieldLength
     * @param paymentHistoryStartDate
     * @param currentBalanceFieldLength
     * @param paymentHistory1FieldLength
     * @param repaymentTenure
     * @param repaymentTenureFieldLength
     * @param emiAmount
     * @param rateOfInterest
     * @param amountOverdue
     */
    public AccountNonSummarySegmentFields(Integer paymentHistory2FieldLength, String dateOpenedOrDisbursed, Double currentBalance, String paymentHistoryEndDate, String reportingMemberShortName, String paymentHistory2, Integer reportingMemberShortNameFieldLength, String accountType, String highCreditOrSanctionedAmountFieldLength, String paymentHistory1, String dateReportedAndCertified, String currentBalanceFieldLength, String dateOfLastPayment, String dateClosed, Integer owenershipIndicator, String paymentHistory1FieldLength, String paymentHistoryStartDate, Double highCreditOrSanctionedAmount, Integer writtenOffAmountPrincipal, String settlementAmountFieldLength, Double writtenOffAmountTotal, String writtenOffAmountPrincipalFieldLength, Double settlementAmount, Double creditLimit, String paymentFrequency, String creditLimitFieldLength, String writtenOffAndSettled, String writtenOffAmountTotalFieldLength,Double emiAmount,Double rateOfInterest,Integer repaymentTenure,Integer repaymentTenureFieldLength,Double amountOverdue,String suitFiledOrWilfulDefault) {
        super();
        this.paymentHistory2FieldLength = paymentHistory2FieldLength;
        this.dateOpenedOrDisbursed = dateOpenedOrDisbursed;
        this.currentBalance = currentBalance;
        this.paymentHistoryEndDate = paymentHistoryEndDate;
        this.reportingMemberShortName = reportingMemberShortName;
        this.paymentHistory2 = paymentHistory2;
        this.reportingMemberShortNameFieldLength = reportingMemberShortNameFieldLength;
        this.accountType = accountType;
        this.highCreditOrSanctionedAmountFieldLength = highCreditOrSanctionedAmountFieldLength;
        this.paymentHistory1 = paymentHistory1;
        this.dateReportedAndCertified = dateReportedAndCertified;
        this.currentBalanceFieldLength = currentBalanceFieldLength;
        this.dateOfLastPayment = dateOfLastPayment;
        this.dateClosed = dateClosed;
        this.owenershipIndicator = owenershipIndicator;
        this.paymentHistory1FieldLength = paymentHistory1FieldLength;
        this.paymentHistoryStartDate = paymentHistoryStartDate;
        this.highCreditOrSanctionedAmount = highCreditOrSanctionedAmount;
        this.writtenOffAmountPrincipal = writtenOffAmountPrincipal;
        this.settlementAmountFieldLength = settlementAmountFieldLength;
        this.writtenOffAmountTotal = writtenOffAmountTotal;
        this.writtenOffAmountPrincipalFieldLength = writtenOffAmountPrincipalFieldLength;
        this.settlementAmount = settlementAmount;
        this.creditLimit = creditLimit;
        this.paymentFrequency = paymentFrequency;
        this.creditLimitFieldLength = creditLimitFieldLength;
        this.writtenOffAndSettled = writtenOffAndSettled;
        this.writtenOffAmountTotalFieldLength = writtenOffAmountTotalFieldLength;
        this.emiAmount = emiAmount;
        this.rateOfInterest = rateOfInterest;
        this.repaymentTenure = repaymentTenure;
        this.repaymentTenureFieldLength = repaymentTenureFieldLength;
        this.amountOverdue = amountOverdue;
        this.suitFiledOrWilfulDefault = suitFiledOrWilfulDefault;
    }

    @JsonProperty("PaymentHistory2FieldLength")
    public Integer getPaymentHistory2FieldLength() {
        return paymentHistory2FieldLength;
    }

    @JsonProperty("PaymentHistory2FieldLength")
    public void setPaymentHistory2FieldLength(Integer paymentHistory2FieldLength) {
        this.paymentHistory2FieldLength = paymentHistory2FieldLength;
    }

    @JsonProperty("DateOpenedOrDisbursed")
    public String getDateOpenedOrDisbursed() {
        return dateOpenedOrDisbursed;
    }

    @JsonProperty("DateOpenedOrDisbursed")
    public void setDateOpenedOrDisbursed(String dateOpenedOrDisbursed) {
        this.dateOpenedOrDisbursed = dateOpenedOrDisbursed;
    }

    @JsonProperty("CurrentBalance")
    public Double getCurrentBalance() {
        return currentBalance;
    }

    @JsonProperty("CurrentBalance")
    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    @JsonProperty("PaymentHistoryEndDate")
    public String getPaymentHistoryEndDate() {
        return paymentHistoryEndDate;
    }

    @JsonProperty("PaymentHistoryEndDate")
    public void setPaymentHistoryEndDate(String paymentHistoryEndDate) {
        this.paymentHistoryEndDate = paymentHistoryEndDate;
    }

    @JsonProperty("ReportingMemberShortName")
    public String getReportingMemberShortName() {
        return reportingMemberShortName;
    }

    @JsonProperty("ReportingMemberShortName")
    public void setReportingMemberShortName(String reportingMemberShortName) {
        this.reportingMemberShortName = reportingMemberShortName;
    }

    @JsonProperty("PaymentHistory2")
    public String getPaymentHistory2() {
        return paymentHistory2;
    }

    @JsonProperty("PaymentHistory2")
    public void setPaymentHistory2(String paymentHistory2) {
        this.paymentHistory2 = paymentHistory2;
    }

    @JsonProperty("ReportingMemberShortNameFieldLength")
    public Integer getReportingMemberShortNameFieldLength() {
        return reportingMemberShortNameFieldLength;
    }

    @JsonProperty("ReportingMemberShortNameFieldLength")
    public void setReportingMemberShortNameFieldLength(Integer reportingMemberShortNameFieldLength) {
        this.reportingMemberShortNameFieldLength = reportingMemberShortNameFieldLength;
    }

    @JsonProperty("AccountType")
    public String getAccountType() {
        return accountType;
    }

    @JsonProperty("AccountType")
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @JsonProperty("HighCreditOrSanctionedAmountFieldLength")
    public String getHighCreditOrSanctionedAmountFieldLength() {
        return highCreditOrSanctionedAmountFieldLength;
    }

    @JsonProperty("HighCreditOrSanctionedAmountFieldLength")
    public void setHighCreditOrSanctionedAmountFieldLength(String highCreditOrSanctionedAmountFieldLength) {
        this.highCreditOrSanctionedAmountFieldLength = highCreditOrSanctionedAmountFieldLength;
    }

    @JsonProperty("PaymentHistory1")
    public String getPaymentHistory1() {
        return paymentHistory1;
    }

    @JsonProperty("PaymentHistory1")
    public void setPaymentHistory1(String paymentHistory1) {
        this.paymentHistory1 = paymentHistory1;
    }

    @JsonProperty("DateReportedAndCertified")
    public String getDateReportedAndCertified() {
        return dateReportedAndCertified;
    }

    @JsonProperty("DateReportedAndCertified")
    public void setDateReportedAndCertified(String dateReportedAndCertified) {
        this.dateReportedAndCertified = dateReportedAndCertified;
    }

    @JsonProperty("CurrentBalanceFieldLength")
    public String getCurrentBalanceFieldLength() {
        return currentBalanceFieldLength;
    }

    @JsonProperty("CurrentBalanceFieldLength")
    public void setCurrentBalanceFieldLength(String currentBalanceFieldLength) {
        this.currentBalanceFieldLength = currentBalanceFieldLength;
    }

    @JsonProperty("DateOfLastPayment")
    public String getDateOfLastPayment() {
        return dateOfLastPayment;
    }

    @JsonProperty("DateOfLastPayment")
    public void setDateOfLastPayment(String dateOfLastPayment) {
        this.dateOfLastPayment = dateOfLastPayment;
    }

    @JsonProperty("DateClosed")
    public String getDateClosed() {
        return dateClosed;
    }

    @JsonProperty("DateClosed")
    public void setDateClosed(String dateClosed) {
        this.dateClosed = dateClosed;
    }

    @JsonProperty("OwenershipIndicator")
    public Integer getOwenershipIndicator() {
        return owenershipIndicator;
    }

    @JsonProperty("OwenershipIndicator")
    public void setOwenershipIndicator(Integer owenershipIndicator) {
        this.owenershipIndicator = owenershipIndicator;
    }

    @JsonProperty("PaymentHistory1FieldLength")
    public String getPaymentHistory1FieldLength() {
        return paymentHistory1FieldLength;
    }

    @JsonProperty("PaymentHistory1FieldLength")
    public void setPaymentHistory1FieldLength(String paymentHistory1FieldLength) {
        this.paymentHistory1FieldLength = paymentHistory1FieldLength;
    }

    @JsonProperty("PaymentHistoryStartDate")
    public String getPaymentHistoryStartDate() {
        return paymentHistoryStartDate;
    }

    @JsonProperty("PaymentHistoryStartDate")
    public void setPaymentHistoryStartDate(String paymentHistoryStartDate) {
        this.paymentHistoryStartDate = paymentHistoryStartDate;
    }

    @JsonProperty("HighCreditOrSanctionedAmount")
    public Double getHighCreditOrSanctionedAmount() {
        return highCreditOrSanctionedAmount;
    }

    @JsonProperty("HighCreditOrSanctionedAmount")
    public void setHighCreditOrSanctionedAmount(Double highCreditOrSanctionedAmount) {
        this.highCreditOrSanctionedAmount = highCreditOrSanctionedAmount;
    }

    @JsonProperty("WrittenOffAmountPrincipal")
    public Integer getWrittenOffAmountPrincipal() {
        return writtenOffAmountPrincipal;
    }

    @JsonProperty("WrittenOffAmountPrincipal")
    public void setWrittenOffAmountPrincipal(Integer writtenOffAmountPrincipal) {
        this.writtenOffAmountPrincipal = writtenOffAmountPrincipal;
    }

    @JsonProperty("SettlementAmountFieldLength")
    public String getSettlementAmountFieldLength() {
        return settlementAmountFieldLength;
    }

    @JsonProperty("SettlementAmountFieldLength")
    public void setSettlementAmountFieldLength(String settlementAmountFieldLength) {
        this.settlementAmountFieldLength = settlementAmountFieldLength;
    }

    @JsonProperty("WrittenOffAmountTotal")
    public Double getWrittenOffAmountTotal() {
        return writtenOffAmountTotal;
    }

    @JsonProperty("WrittenOffAmountTotal")
    public void setWrittenOffAmountTotal(Double writtenOffAmountTotal) {
        this.writtenOffAmountTotal = writtenOffAmountTotal;
    }

    @JsonProperty("WrittenOffAmountPrincipalFieldLength")
    public String getWrittenOffAmountPrincipalFieldLength() {
        return writtenOffAmountPrincipalFieldLength;
    }

    @JsonProperty("WrittenOffAmountPrincipalFieldLength")
    public void setWrittenOffAmountPrincipalFieldLength(String writtenOffAmountPrincipalFieldLength) {
        this.writtenOffAmountPrincipalFieldLength = writtenOffAmountPrincipalFieldLength;
    }

    @JsonProperty("SettlementAmount")
    public Double getSettlementAmount() {
        return settlementAmount;
    }

    @JsonProperty("SettlementAmount")
    public void setSettlementAmount(Double settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    @JsonProperty("CreditLimit")
    public Double getCreditLimit() {
        return creditLimit;
    }

    @JsonProperty("CreditLimit")
    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    @JsonProperty("PaymentFrequency")
    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    @JsonProperty("PaymentFrequency")
    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    @JsonProperty("CreditLimitFieldLength")
    public String getCreditLimitFieldLength() {
        return creditLimitFieldLength;
    }

    @JsonProperty("CreditLimitFieldLength")
    public void setCreditLimitFieldLength(String creditLimitFieldLength) {
        this.creditLimitFieldLength = creditLimitFieldLength;
    }

    @JsonProperty("WrittenOffAndSettled")
    public String getWrittenOffAndSettled() {
        return writtenOffAndSettled;
    }

    @JsonProperty("WrittenOffAndSettled")
    public void setWrittenOffAndSettled(String writtenOffAndSettled) {
        this.writtenOffAndSettled = writtenOffAndSettled;
    }

    @JsonProperty("WrittenOffAmountTotalFieldLength")
    public String getWrittenOffAmountTotalFieldLength() {
        return writtenOffAmountTotalFieldLength;
    }

    @JsonProperty("WrittenOffAmountTotalFieldLength")
    public void setWrittenOffAmountTotalFieldLength(String writtenOffAmountTotalFieldLength) {
        this.writtenOffAmountTotalFieldLength = writtenOffAmountTotalFieldLength;
    }
    
    @JsonProperty("EmiAmount")
    public Double getEmiAmount() {
		return emiAmount;
	}

    @JsonProperty("EmiAmount")
	public void setEmiAmount(Double emiAmount) {
		this.emiAmount = emiAmount;
	}

    @JsonProperty("RateOfInterest")
	public Double getRateOfInterest() {
		return rateOfInterest;
	}

    @JsonProperty("RateOfInterest")
	public void setRateOfInterest(Double rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
    
    @JsonProperty("RepaymentTenure")
	public Integer getRepaymentTenure() {
		return repaymentTenure;
	}
    @JsonProperty("RepaymentTenure")
	public void setRepaymentTenure(Integer repaymentTenure) {
		this.repaymentTenure = repaymentTenure;
	}

    @JsonProperty("RepaymentTenureFieldLength")
	public Integer getRepaymentTenureFieldLength() {
		return repaymentTenureFieldLength;
	}
    @JsonProperty("RepaymentTenureFieldLength")
	public void setRepaymentTenureFieldLength(Integer repaymentTenureFieldLength) {
		this.repaymentTenureFieldLength = repaymentTenureFieldLength;
	}
    
    @JsonProperty("AmountOverdue")
	public Double getAmountOverdue() {
		return amountOverdue;
	}

    @JsonProperty("AmountOverdue")
	public void setAmountOverdue(Double amountOverdue) {
		this.amountOverdue = amountOverdue;
	}
    
    @JsonProperty("SuitFiledOrWilfulDefault")
	public String getSuitFiledOrWilfulDefault() {
		return suitFiledOrWilfulDefault;
	}

    @JsonProperty("SuitFiledOrWilfulDefault")
	public void setSuitFiledOrWilfulDefault(String suitFiledOrWilfulDefault) {
		this.suitFiledOrWilfulDefault = suitFiledOrWilfulDefault;
	}

	@JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
