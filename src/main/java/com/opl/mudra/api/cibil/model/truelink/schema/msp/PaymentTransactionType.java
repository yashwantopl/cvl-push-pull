
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.PaymentProcessorType;
import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.PaymentTransStatusType;


/**
 * <p>Java class for PaymentTransactionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentTransactionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PaymentTransactionId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Status" type="{com/truelink/schema/database/tcps/enumerations}PaymentTransStatusType"/>
 *         &lt;element name="PaymentProcessor" type="{com/truelink/schema/database/tcps/enumerations}PaymentProcessorType"/>
 *         &lt;element name="RefCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CurrencyAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="CapturedAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="RefundedAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="CreditCardType" type="{com/truelink/schema/database/tcps/enumerations}CreditCardType"/>
 *         &lt;element name="CreditCard" type="{com/truelink/schema/msp}CreditCardType"/>
 *         &lt;element name="PaymentTransactionEntry" type="{com/truelink/schema/msp}PaymentTransactionEntryType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentTransactionType", propOrder = {
    "paymentTransactionId",
    "status",
    "paymentProcessor",
    "refCode",
    "currencyAmount",
    "capturedAmount",
    "refundedAmount",
    "creditCardType",
    "creditCard",
    "paymentTransactionEntry"
})
public class PaymentTransactionType {

    @XmlElement(name = "PaymentTransactionId")
    protected long paymentTransactionId;
    @XmlElement(name = "Status", required = true)
    @XmlSchemaType(name = "string")
    protected PaymentTransStatusType status;
    @XmlElement(name = "PaymentProcessor", required = true)
    @XmlSchemaType(name = "string")
    protected PaymentProcessorType paymentProcessor;
    @XmlElement(name = "RefCode", required = true)
    protected String refCode;
    @XmlElement(name = "CurrencyAmount", required = true)
    protected BigDecimal currencyAmount;
    @XmlElement(name = "CapturedAmount")
    protected BigDecimal capturedAmount;
    @XmlElement(name = "RefundedAmount")
    protected BigDecimal refundedAmount;
    @XmlElement(name = "CreditCardType", required = true)
    @XmlSchemaType(name = "string")
    protected com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.CreditCardType creditCardType;
    @XmlElement(name = "CreditCard", required = true)
    protected com.opl.mudra.api.cibil.model.truelink.schema.msp.CreditCardType creditCard;
    @XmlElement(name = "PaymentTransactionEntry", required = true)
    protected List<PaymentTransactionEntryType> paymentTransactionEntry;

    /**
     * Gets the value of the paymentTransactionId property.
     * 
     */
    public long getPaymentTransactionId() {
        return paymentTransactionId;
    }

    /**
     * Sets the value of the paymentTransactionId property.
     * 
     */
    public void setPaymentTransactionId(long value) {
        this.paymentTransactionId = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentTransStatusType }
     *     
     */
    public PaymentTransStatusType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentTransStatusType }
     *     
     */
    public void setStatus(PaymentTransStatusType value) {
        this.status = value;
    }

    /**
     * Gets the value of the paymentProcessor property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentProcessorType }
     *     
     */
    public PaymentProcessorType getPaymentProcessor() {
        return paymentProcessor;
    }

    /**
     * Sets the value of the paymentProcessor property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentProcessorType }
     *     
     */
    public void setPaymentProcessor(PaymentProcessorType value) {
        this.paymentProcessor = value;
    }

    /**
     * Gets the value of the refCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRefCode() {
        return refCode;
    }

    /**
     * Sets the value of the refCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRefCode(String value) {
        this.refCode = value;
    }

    /**
     * Gets the value of the currencyAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCurrencyAmount() {
        return currencyAmount;
    }

    /**
     * Sets the value of the currencyAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCurrencyAmount(BigDecimal value) {
        this.currencyAmount = value;
    }

    /**
     * Gets the value of the capturedAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCapturedAmount() {
        return capturedAmount;
    }

    /**
     * Sets the value of the capturedAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCapturedAmount(BigDecimal value) {
        this.capturedAmount = value;
    }

    /**
     * Gets the value of the refundedAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRefundedAmount() {
        return refundedAmount;
    }

    /**
     * Sets the value of the refundedAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRefundedAmount(BigDecimal value) {
        this.refundedAmount = value;
    }

    /**
     * Gets the value of the creditCardType property.
     * 
     * @return
     *     possible object is
     *     {@link com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.CreditCardType }
     *     
     */
    public com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.CreditCardType getCreditCardType() {
        return creditCardType;
    }

    /**
     * Sets the value of the creditCardType property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.CreditCardType }
     *     
     */
    public void setCreditCardType(com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.CreditCardType value) {
        this.creditCardType = value;
    }

    /**
     * Gets the value of the creditCard property.
     * 
     * @return
     *     possible object is
     *     {@link com.opl.mudra.api.cibil.model.truelink.schema.msp.CreditCardType }
     *     
     */
    public com.opl.mudra.api.cibil.model.truelink.schema.msp.CreditCardType getCreditCard() {
        return creditCard;
    }

    /**
     * Sets the value of the creditCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.opl.mudra.api.cibil.model.truelink.schema.msp.CreditCardType }
     *     
     */
    public void setCreditCard(com.opl.mudra.api.cibil.model.truelink.schema.msp.CreditCardType value) {
        this.creditCard = value;
    }

    /**
     * Gets the value of the paymentTransactionEntry property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentTransactionEntry property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentTransactionEntry().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PaymentTransactionEntryType }
     * 
     * 
     */
    public List<PaymentTransactionEntryType> getPaymentTransactionEntry() {
        if (paymentTransactionEntry == null) {
            paymentTransactionEntry = new ArrayList<PaymentTransactionEntryType>();
        }
        return this.paymentTransactionEntry;
    }

}
