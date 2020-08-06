
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BUSHDETAILS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BUSHDETAILS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BalanceAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaymentTerms" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiaSanctionedAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiaAmountPastDue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiaCreditLimitAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiaActualPayAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastPaymentAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreditExtendedAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SettlementAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WilfulDefaultStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WilfulDefaultAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountStatusDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SuitFiledStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SuitFiledDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SuitFiledAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InstalmentAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DrawingPower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LimitUtilize" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NAORC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RepaymentFreq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LastRepaidAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AmountOverdue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BUSHDETAILS", propOrder = {
    "segmentCode",
    "balanceAmt",
    "paymentTerms",
    "indiaSanctionedAmt",
    "indiaAmountPastDue",
    "indiaCreditLimitAmt",
    "indiaActualPayAmt",
    "lastPaymentAmt",
    "creditExtendedAmt",
    "settlementAmt",
    "wilfulDefaultStatus",
    "wilfulDefaultAmount",
    "accountStatus",
    "accountStatusDetail",
    "suitFiledStatus",
    "suitFiledDate",
    "suitFiledAmount",
    "instalmentAmount",
    "drawingPower",
    "limitUtilize",
    "naorc",
    "repaymentFreq",
    "lastRepaidAmount",
    "amountOverdue"
})
public class BUSHDETAILS {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "BalanceAmt")
    protected String balanceAmt;
    @XmlElement(name = "PaymentTerms")
    protected String paymentTerms;
    @XmlElement(name = "IndiaSanctionedAmt")
    protected String indiaSanctionedAmt;
    @XmlElement(name = "IndiaAmountPastDue")
    protected String indiaAmountPastDue;
    @XmlElement(name = "IndiaCreditLimitAmt")
    protected String indiaCreditLimitAmt;
    @XmlElement(name = "IndiaActualPayAmt")
    protected String indiaActualPayAmt;
    @XmlElement(name = "LastPaymentAmt")
    protected String lastPaymentAmt;
    @XmlElement(name = "CreditExtendedAmt")
    protected String creditExtendedAmt;
    @XmlElement(name = "SettlementAmt")
    protected String settlementAmt;
    @XmlElement(name = "WilfulDefaultStatus")
    protected String wilfulDefaultStatus;
    @XmlElement(name = "WilfulDefaultAmount")
    protected String wilfulDefaultAmount;
    @XmlElement(name = "AccountStatus")
    protected String accountStatus;
    @XmlElement(name = "AccountStatusDetail")
    protected String accountStatusDetail;
    @XmlElement(name = "SuitFiledStatus")
    protected String suitFiledStatus;
    @XmlElement(name = "SuitFiledDate")
    protected String suitFiledDate;
    @XmlElement(name = "SuitFiledAmount")
    protected String suitFiledAmount;
    @XmlElement(name = "InstalmentAmount")
    protected String instalmentAmount;
    @XmlElement(name = "DrawingPower")
    protected String drawingPower;
    @XmlElement(name = "LimitUtilize")
    protected String limitUtilize;
    @XmlElement(name = "NAORC")
    protected String naorc;
    @XmlElement(name = "RepaymentFreq")
    protected String repaymentFreq;
    @XmlElement(name = "LastRepaidAmount")
    protected String lastRepaidAmount;
    @XmlElement(name = "AmountOverdue")
    protected String amountOverdue;

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
     * Gets the value of the balanceAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBalanceAmt() {
        return balanceAmt;
    }

    /**
     * Sets the value of the balanceAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBalanceAmt(String value) {
        this.balanceAmt = value;
    }

    /**
     * Gets the value of the paymentTerms property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentTerms() {
        return paymentTerms;
    }

    /**
     * Sets the value of the paymentTerms property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentTerms(String value) {
        this.paymentTerms = value;
    }

    /**
     * Gets the value of the indiaSanctionedAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndiaSanctionedAmt() {
        return indiaSanctionedAmt;
    }

    /**
     * Sets the value of the indiaSanctionedAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndiaSanctionedAmt(String value) {
        this.indiaSanctionedAmt = value;
    }

    /**
     * Gets the value of the indiaAmountPastDue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndiaAmountPastDue() {
        return indiaAmountPastDue;
    }

    /**
     * Sets the value of the indiaAmountPastDue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndiaAmountPastDue(String value) {
        this.indiaAmountPastDue = value;
    }

    /**
     * Gets the value of the indiaCreditLimitAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndiaCreditLimitAmt() {
        return indiaCreditLimitAmt;
    }

    /**
     * Sets the value of the indiaCreditLimitAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndiaCreditLimitAmt(String value) {
        this.indiaCreditLimitAmt = value;
    }

    /**
     * Gets the value of the indiaActualPayAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndiaActualPayAmt() {
        return indiaActualPayAmt;
    }

    /**
     * Sets the value of the indiaActualPayAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndiaActualPayAmt(String value) {
        this.indiaActualPayAmt = value;
    }

    /**
     * Gets the value of the lastPaymentAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastPaymentAmt() {
        return lastPaymentAmt;
    }

    /**
     * Sets the value of the lastPaymentAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastPaymentAmt(String value) {
        this.lastPaymentAmt = value;
    }

    /**
     * Gets the value of the creditExtendedAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditExtendedAmt() {
        return creditExtendedAmt;
    }

    /**
     * Sets the value of the creditExtendedAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditExtendedAmt(String value) {
        this.creditExtendedAmt = value;
    }

    /**
     * Gets the value of the settlementAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSettlementAmt() {
        return settlementAmt;
    }

    /**
     * Sets the value of the settlementAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSettlementAmt(String value) {
        this.settlementAmt = value;
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
     * Gets the value of the instalmentAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstalmentAmount() {
        return instalmentAmount;
    }

    /**
     * Sets the value of the instalmentAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstalmentAmount(String value) {
        this.instalmentAmount = value;
    }

    /**
     * Gets the value of the drawingPower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrawingPower() {
        return drawingPower;
    }

    /**
     * Sets the value of the drawingPower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrawingPower(String value) {
        this.drawingPower = value;
    }

    /**
     * Gets the value of the limitUtilize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLimitUtilize() {
        return limitUtilize;
    }

    /**
     * Sets the value of the limitUtilize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLimitUtilize(String value) {
        this.limitUtilize = value;
    }

    /**
     * Gets the value of the naorc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNAORC() {
        return naorc;
    }

    /**
     * Sets the value of the naorc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNAORC(String value) {
        this.naorc = value;
    }

    /**
     * Gets the value of the repaymentFreq property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepaymentFreq() {
        return repaymentFreq;
    }

    /**
     * Sets the value of the repaymentFreq property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepaymentFreq(String value) {
        this.repaymentFreq = value;
    }

    /**
     * Gets the value of the lastRepaidAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastRepaidAmount() {
        return lastRepaidAmount;
    }

    /**
     * Sets the value of the lastRepaidAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastRepaidAmount(String value) {
        this.lastRepaidAmount = value;
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

}
