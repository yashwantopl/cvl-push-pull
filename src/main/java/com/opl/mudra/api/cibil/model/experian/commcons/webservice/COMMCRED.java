
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for COMMCRED complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="COMMCRED">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountNumberSfx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountPortfolioType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountCurrency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountTypeDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountFinRespTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountStatusDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountOpenDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountClosedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SanctionedAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AssetClassification" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CurrentBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WilfulDefaultStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WilfulDefaultDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WilfulDefaultAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SuitFiledStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SuitFiledDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SuitFiledAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WrittenOffAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AmountOverdue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastReportedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SanctionDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaymentStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaymentStatusDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LoanExpiryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LoanRenewDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RestructoringReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RestructoringReasonDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SecurityCoverage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuranteeCoverage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BankRemark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BUSCREDITOR" type="{http://webservice.commcons.experian.com}BUSCREDITOR" minOccurs="0"/>
 *         &lt;element name="BUSBPAYGRID" type="{http://webservice.commcons.experian.com}BUSBPAYGRID" maxOccurs="36" minOccurs="0"/>
 *         &lt;element name="BUSBORROWER" type="{http://webservice.commcons.experian.com}BUSBORROWER" minOccurs="0"/>
 *         &lt;element name="GUARANTOR" type="{http://webservice.commcons.experian.com}GUARANTOR" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="SECURITYDTLS" type="{http://webservice.commcons.experian.com}SECURITYDTLS" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="CHQDIS" type="{http://webservice.commcons.experian.com}CHQDIS" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="COMSTMTALRT" type="{http://webservice.commcons.experian.com}COMSTMTALRT" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BUSDISPUTE" type="{http://webservice.commcons.experian.com}BUSDISPUTE" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "COMMCRED", propOrder = {
    "segmentCode",
    "accountNumber",
    "accountNumberSfx",
    "accountPortfolioType",
    "accountType",
    "accountCurrency",
    "accountTypeDetail",
    "accountFinRespTypeCd",
    "accountStatus",
    "accountStatusDetail",
    "accountOpenDate",
    "accountClosedDate",
    "sanctionedAmount",
    "assetClassification",
    "currentBalance",
    "wilfulDefaultStatus",
    "wilfulDefaultDate",
    "wilfulDefaultAmount",
    "suitFiledStatus",
    "suitFiledDate",
    "suitFiledAmount",
    "writtenOffAmount",
    "amountOverdue",
    "lastReportedDate",
    "sanctionDate",
    "paymentStatus",
    "paymentStatusDetail",
    "loanExpiryDate",
    "loanRenewDate",
    "restructoringReason",
    "restructoringReasonDetail",
    "securityCoverage",
    "guranteeCoverage",
    "bankRemark",
    "buscreditor",
    "busbpaygrid",
    "busborrower",
    "guarantor",
    "securitydtls",
    "chqdis",
    "comstmtalrt",
    "busdispute"
})
public class COMMCRED {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "AccountNumber")
    protected String accountNumber;
    @XmlElement(name = "AccountNumberSfx")
    protected String accountNumberSfx;
    @XmlElement(name = "AccountPortfolioType")
    protected String accountPortfolioType;
    @XmlElement(name = "AccountType")
    protected String accountType;
    @XmlElement(name = "AccountCurrency")
    protected String accountCurrency;
    @XmlElement(name = "AccountTypeDetail")
    protected String accountTypeDetail;
    @XmlElement(name = "AccountFinRespTypeCd")
    protected String accountFinRespTypeCd;
    @XmlElement(name = "AccountStatus")
    protected String accountStatus;
    @XmlElement(name = "AccountStatusDetail")
    protected String accountStatusDetail;
    @XmlElement(name = "AccountOpenDate")
    protected String accountOpenDate;
    @XmlElement(name = "AccountClosedDate")
    protected String accountClosedDate;
    @XmlElement(name = "SanctionedAmount")
    protected Double sanctionedAmount;
    @XmlElement(name = "AssetClassification")
    protected String assetClassification;
    @XmlElement(name = "CurrentBalance")
    protected Double currentBalance;
    @XmlElement(name = "WilfulDefaultStatus")
    protected String wilfulDefaultStatus;
    @XmlElement(name = "WilfulDefaultDate")
    protected String wilfulDefaultDate;
    @XmlElement(name = "WilfulDefaultAmount")
    protected String wilfulDefaultAmount;
    @XmlElement(name = "SuitFiledStatus")
    protected String suitFiledStatus;
    @XmlElement(name = "SuitFiledDate")
    protected String suitFiledDate;
    @XmlElement(name = "SuitFiledAmount")
    protected String suitFiledAmount;
    @XmlElement(name = "WrittenOffAmount")
    protected String writtenOffAmount;
    @XmlElement(name = "AmountOverdue")
    protected String amountOverdue;
    @XmlElement(name = "LastReportedDate")
    protected String lastReportedDate;
    @XmlElement(name = "SanctionDate")
    protected String sanctionDate;
    @XmlElement(name = "PaymentStatus")
    protected String paymentStatus;
    @XmlElement(name = "PaymentStatusDetail")
    protected String paymentStatusDetail;
    @XmlElement(name = "LoanExpiryDate")
    protected String loanExpiryDate;
    @XmlElement(name = "LoanRenewDate")
    protected String loanRenewDate;
    @XmlElement(name = "RestructoringReason")
    protected String restructoringReason;
    @XmlElement(name = "RestructoringReasonDetail")
    protected String restructoringReasonDetail;
    @XmlElement(name = "SecurityCoverage")
    protected String securityCoverage;
    @XmlElement(name = "GuranteeCoverage")
    protected String guranteeCoverage;
    @XmlElement(name = "BankRemark")
    protected String bankRemark;
    @XmlElement(name = "BUSCREDITOR")
    protected BUSCREDITOR buscreditor;
    @XmlElement(name = "BUSBPAYGRID")
    protected List<BUSBPAYGRID> busbpaygrid;
    @XmlElement(name = "BUSBORROWER")
    protected BUSBORROWER busborrower;
    @XmlElement(name = "GUARANTOR")
    protected List<GUARANTOR> guarantor;
    @XmlElement(name = "SECURITYDTLS")
    protected List<SECURITYDTLS> securitydtls;
    @XmlElement(name = "CHQDIS")
    protected List<CHQDIS> chqdis;
    @XmlElement(name = "COMSTMTALRT")
    protected List<COMSTMTALRT> comstmtalrt;
    @XmlElement(name = "BUSDISPUTE")
    protected List<BUSDISPUTE> busdispute;

    /**
     * Gets the value of the segmentCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentCode() {
        return segmentCode;
    }

    /**
     * Sets the value of the segmentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentCode(String value) {
        this.segmentCode = value;
    }

    /**
     * Gets the value of the accountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the accountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNumber(String value) {
        this.accountNumber = value;
    }

    /**
     * Gets the value of the accountNumberSfx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNumberSfx() {
        return accountNumberSfx;
    }

    /**
     * Sets the value of the accountNumberSfx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNumberSfx(String value) {
        this.accountNumberSfx = value;
    }

    /**
     * Gets the value of the accountPortfolioType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountPortfolioType() {
        return accountPortfolioType;
    }

    /**
     * Sets the value of the accountPortfolioType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountPortfolioType(String value) {
        this.accountPortfolioType = value;
    }

    /**
     * Gets the value of the accountType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the value of the accountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountType(String value) {
        this.accountType = value;
    }

    /**
     * Gets the value of the accountCurrency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountCurrency() {
        return accountCurrency;
    }

    /**
     * Sets the value of the accountCurrency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountCurrency(String value) {
        this.accountCurrency = value;
    }

    /**
     * Gets the value of the accountTypeDetail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountTypeDetail() {
        return accountTypeDetail;
    }

    /**
     * Sets the value of the accountTypeDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountTypeDetail(String value) {
        this.accountTypeDetail = value;
    }

    /**
     * Gets the value of the accountFinRespTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountFinRespTypeCd() {
        return accountFinRespTypeCd;
    }

    /**
     * Sets the value of the accountFinRespTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountFinRespTypeCd(String value) {
        this.accountFinRespTypeCd = value;
    }

    /**
     * Gets the value of the accountStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountStatus() {
        return accountStatus;
    }

    /**
     * Sets the value of the accountStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountStatus(String value) {
        this.accountStatus = value;
    }

    /**
     * Gets the value of the accountStatusDetail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountStatusDetail() {
        return accountStatusDetail;
    }

    /**
     * Sets the value of the accountStatusDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountStatusDetail(String value) {
        this.accountStatusDetail = value;
    }

    /**
     * Gets the value of the accountOpenDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountOpenDate() {
        return accountOpenDate;
    }

    /**
     * Sets the value of the accountOpenDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountOpenDate(String value) {
        this.accountOpenDate = value;
    }

    /**
     * Gets the value of the accountClosedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountClosedDate() {
        return accountClosedDate;
    }

    /**
     * Sets the value of the accountClosedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountClosedDate(String value) {
        this.accountClosedDate = value;
    }

    /**
     * Gets the value of the sanctionedAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Double getSanctionedAmount() {
        return sanctionedAmount;
    }

    /**
     * Sets the value of the sanctionedAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSanctionedAmount(Double value) {
        this.sanctionedAmount = value;
    }

    /**
     * Gets the value of the assetClassification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssetClassification() {
        return assetClassification;
    }

    /**
     * Sets the value of the assetClassification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssetClassification(String value) {
        this.assetClassification = value;
    }

    /**
     * Gets the value of the currentBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Double getCurrentBalance() {
        return currentBalance;
    }

    /**
     * Sets the value of the currentBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentBalance(Double value) {
        this.currentBalance = value;
    }

    /**
     * Gets the value of the wilfulDefaultStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWilfulDefaultStatus() {
        return wilfulDefaultStatus;
    }

    /**
     * Sets the value of the wilfulDefaultStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWilfulDefaultStatus(String value) {
        this.wilfulDefaultStatus = value;
    }

    /**
     * Gets the value of the wilfulDefaultDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWilfulDefaultDate() {
        return wilfulDefaultDate;
    }

    /**
     * Sets the value of the wilfulDefaultDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWilfulDefaultDate(String value) {
        this.wilfulDefaultDate = value;
    }

    /**
     * Gets the value of the wilfulDefaultAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWilfulDefaultAmount() {
        return wilfulDefaultAmount;
    }

    /**
     * Sets the value of the wilfulDefaultAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWilfulDefaultAmount(String value) {
        this.wilfulDefaultAmount = value;
    }

    /**
     * Gets the value of the suitFiledStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuitFiledStatus() {
        return suitFiledStatus;
    }

    /**
     * Sets the value of the suitFiledStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuitFiledStatus(String value) {
        this.suitFiledStatus = value;
    }

    /**
     * Gets the value of the suitFiledDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuitFiledDate() {
        return suitFiledDate;
    }

    /**
     * Sets the value of the suitFiledDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuitFiledDate(String value) {
        this.suitFiledDate = value;
    }

    /**
     * Gets the value of the suitFiledAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuitFiledAmount() {
        return suitFiledAmount;
    }

    /**
     * Sets the value of the suitFiledAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuitFiledAmount(String value) {
        this.suitFiledAmount = value;
    }

    /**
     * Gets the value of the writtenOffAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWrittenOffAmount() {
        return writtenOffAmount;
    }

    /**
     * Sets the value of the writtenOffAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWrittenOffAmount(String value) {
        this.writtenOffAmount = value;
    }

    /**
     * Gets the value of the amountOverdue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmountOverdue() {
        return amountOverdue;
    }

    /**
     * Sets the value of the amountOverdue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmountOverdue(String value) {
        this.amountOverdue = value;
    }

    /**
     * Gets the value of the lastReportedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastReportedDate() {
        return lastReportedDate;
    }

    /**
     * Sets the value of the lastReportedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastReportedDate(String value) {
        this.lastReportedDate = value;
    }

    /**
     * Gets the value of the sanctionDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSanctionDate() {
        return sanctionDate;
    }

    /**
     * Sets the value of the sanctionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSanctionDate(String value) {
        this.sanctionDate = value;
    }

    /**
     * Gets the value of the paymentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Sets the value of the paymentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentStatus(String value) {
        this.paymentStatus = value;
    }

    /**
     * Gets the value of the paymentStatusDetail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentStatusDetail() {
        return paymentStatusDetail;
    }

    /**
     * Sets the value of the paymentStatusDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentStatusDetail(String value) {
        this.paymentStatusDetail = value;
    }

    /**
     * Gets the value of the loanExpiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanExpiryDate() {
        return loanExpiryDate;
    }

    /**
     * Sets the value of the loanExpiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanExpiryDate(String value) {
        this.loanExpiryDate = value;
    }

    /**
     * Gets the value of the loanRenewDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoanRenewDate() {
        return loanRenewDate;
    }

    /**
     * Sets the value of the loanRenewDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoanRenewDate(String value) {
        this.loanRenewDate = value;
    }

    /**
     * Gets the value of the restructoringReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRestructoringReason() {
        return restructoringReason;
    }

    /**
     * Sets the value of the restructoringReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRestructoringReason(String value) {
        this.restructoringReason = value;
    }

    /**
     * Gets the value of the restructoringReasonDetail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRestructoringReasonDetail() {
        return restructoringReasonDetail;
    }

    /**
     * Sets the value of the restructoringReasonDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRestructoringReasonDetail(String value) {
        this.restructoringReasonDetail = value;
    }

    /**
     * Gets the value of the securityCoverage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecurityCoverage() {
        return securityCoverage;
    }

    /**
     * Sets the value of the securityCoverage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecurityCoverage(String value) {
        this.securityCoverage = value;
    }

    /**
     * Gets the value of the guranteeCoverage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuranteeCoverage() {
        return guranteeCoverage;
    }

    /**
     * Sets the value of the guranteeCoverage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuranteeCoverage(String value) {
        this.guranteeCoverage = value;
    }

    /**
     * Gets the value of the bankRemark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankRemark() {
        return bankRemark;
    }

    /**
     * Sets the value of the bankRemark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankRemark(String value) {
        this.bankRemark = value;
    }

    /**
     * Gets the value of the buscreditor property.
     * 
     * @return
     *     possible object is
     *     {@link BUSCREDITOR }
     *     
     */
    public BUSCREDITOR getBUSCREDITOR() {
        return buscreditor;
    }

    /**
     * Sets the value of the buscreditor property.
     * 
     * @param value
     *     allowed object is
     *     {@link BUSCREDITOR }
     *     
     */
    public void setBUSCREDITOR(BUSCREDITOR value) {
        this.buscreditor = value;
    }

    /**
     * Gets the value of the busbpaygrid property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the busbpaygrid property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBUSBPAYGRID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BUSBPAYGRID }
     * 
     * 
     */
    public List<BUSBPAYGRID> getBUSBPAYGRID() {
        if (busbpaygrid == null) {
            busbpaygrid = new ArrayList<BUSBPAYGRID>();
        }
        return this.busbpaygrid;
    }

    /**
     * Gets the value of the busborrower property.
     * 
     * @return
     *     possible object is
     *     {@link BUSBORROWER }
     *     
     */
    public BUSBORROWER getBUSBORROWER() {
        return busborrower;
    }

    /**
     * Sets the value of the busborrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link BUSBORROWER }
     *     
     */
    public void setBUSBORROWER(BUSBORROWER value) {
        this.busborrower = value;
    }

    /**
     * Gets the value of the guarantor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the guarantor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGUARANTOR().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GUARANTOR }
     * 
     * 
     */
    public List<GUARANTOR> getGUARANTOR() {
        if (guarantor == null) {
            guarantor = new ArrayList<GUARANTOR>();
        }
        return this.guarantor;
    }

    /**
     * Gets the value of the securitydtls property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the securitydtls property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSECURITYDTLS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SECURITYDTLS }
     * 
     * 
     */
    public List<SECURITYDTLS> getSECURITYDTLS() {
        if (securitydtls == null) {
            securitydtls = new ArrayList<SECURITYDTLS>();
        }
        return this.securitydtls;
    }

    /**
     * Gets the value of the chqdis property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the chqdis property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCHQDIS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CHQDIS }
     * 
     * 
     */
    public List<CHQDIS> getCHQDIS() {
        if (chqdis == null) {
            chqdis = new ArrayList<CHQDIS>();
        }
        return this.chqdis;
    }

    /**
     * Gets the value of the comstmtalrt property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comstmtalrt property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCOMSTMTALRT().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link COMSTMTALRT }
     * 
     * 
     */
    public List<COMSTMTALRT> getCOMSTMTALRT() {
        if (comstmtalrt == null) {
            comstmtalrt = new ArrayList<COMSTMTALRT>();
        }
        return this.comstmtalrt;
    }

    /**
     * Gets the value of the busdispute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the busdispute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBUSDISPUTE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BUSDISPUTE }
     * 
     * 
     */
    public List<BUSDISPUTE> getBUSDISPUTE() {
        if (busdispute == null) {
            busdispute = new ArrayList<BUSDISPUTE>();
        }
        return this.busdispute;
    }

}
