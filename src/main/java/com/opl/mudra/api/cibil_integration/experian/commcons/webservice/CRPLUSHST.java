
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CRPLUSHST complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CRPLUSHST">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaymentStatusValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CashLimitAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreditLimitAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ActualPaymentAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CurrentBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AmountPastDue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EMIAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CRPLUSHST", propOrder = {
    "segmentCode",
    "accountDate",
    "paymentStatusValue",
    "cashLimitAmt",
    "creditLimitAmt",
    "actualPaymentAmt",
    "currentBalance",
    "amountPastDue",
    "emiAmount"
})
public class CRPLUSHST {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "AccountDate")
    protected String accountDate;
    @XmlElement(name = "PaymentStatusValue")
    protected String paymentStatusValue;
    @XmlElement(name = "CashLimitAmt")
    protected String cashLimitAmt;
    @XmlElement(name = "CreditLimitAmt")
    protected String creditLimitAmt;
    @XmlElement(name = "ActualPaymentAmt")
    protected String actualPaymentAmt;
    @XmlElement(name = "CurrentBalance")
    protected String currentBalance;
    @XmlElement(name = "AmountPastDue")
    protected String amountPastDue;
    @XmlElement(name = "EMIAmount")
    protected String emiAmount;

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
     * Gets the value of the accountDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountDate() {
        return accountDate;
    }

    /**
     * Sets the value of the accountDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountDate(String value) {
        this.accountDate = value;
    }

    /**
     * Gets the value of the paymentStatusValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentStatusValue() {
        return paymentStatusValue;
    }

    /**
     * Sets the value of the paymentStatusValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentStatusValue(String value) {
        this.paymentStatusValue = value;
    }

    /**
     * Gets the value of the cashLimitAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCashLimitAmt() {
        return cashLimitAmt;
    }

    /**
     * Sets the value of the cashLimitAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCashLimitAmt(String value) {
        this.cashLimitAmt = value;
    }

    /**
     * Gets the value of the creditLimitAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditLimitAmt() {
        return creditLimitAmt;
    }

    /**
     * Sets the value of the creditLimitAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditLimitAmt(String value) {
        this.creditLimitAmt = value;
    }

    /**
     * Gets the value of the actualPaymentAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActualPaymentAmt() {
        return actualPaymentAmt;
    }

    /**
     * Sets the value of the actualPaymentAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActualPaymentAmt(String value) {
        this.actualPaymentAmt = value;
    }

    /**
     * Gets the value of the currentBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentBalance() {
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
    public void setCurrentBalance(String value) {
        this.currentBalance = value;
    }

    /**
     * Gets the value of the amountPastDue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmountPastDue() {
        return amountPastDue;
    }

    /**
     * Sets the value of the amountPastDue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmountPastDue(String value) {
        this.amountPastDue = value;
    }

    /**
     * Gets the value of the emiAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEMIAmount() {
        return emiAmount;
    }

    /**
     * Sets the value of the emiAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMIAmount(String value) {
        this.emiAmount = value;
    }

}
