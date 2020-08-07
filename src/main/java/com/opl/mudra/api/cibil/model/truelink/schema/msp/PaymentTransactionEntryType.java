
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.TransEntryResultType;
import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.TransEntryStatusType;
import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.TransEntryType;


/**
 * <p>Java class for PaymentTransactionEntryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentTransactionEntryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PaymentTransactionEntryId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Status" type="{com/truelink/schema/database/tcps/enumerations}TransEntryStatusType"/>
 *         &lt;element name="Type" type="{com/truelink/schema/database/tcps/enumerations}TransEntryType"/>
 *         &lt;element name="ResultType" type="{com/truelink/schema/database/tcps/enumerations}TransEntryResultType"/>
 *         &lt;element name="CurrencyAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="ServiceRefCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TransactionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentTransactionEntryType", propOrder = {
    "paymentTransactionEntryId",
    "status",
    "type",
    "resultType",
    "currencyAmount",
    "serviceRefCode",
    "transactionDate"
})
public class PaymentTransactionEntryType {

    @XmlElement(name = "PaymentTransactionEntryId")
    protected long paymentTransactionEntryId;
    @XmlElement(name = "Status", required = true)
    @XmlSchemaType(name = "string")
    protected TransEntryStatusType status;
    @XmlElement(name = "Type", required = true)
    @XmlSchemaType(name = "string")
    protected TransEntryType type;
    @XmlElement(name = "ResultType", required = true)
    @XmlSchemaType(name = "string")
    protected TransEntryResultType resultType;
    @XmlElement(name = "CurrencyAmount", required = true)
    protected BigDecimal currencyAmount;
    @XmlElement(name = "ServiceRefCode", required = true)
    protected String serviceRefCode;
    @XmlElement(name = "TransactionDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar transactionDate;

    /**
     * Gets the value of the paymentTransactionEntryId property.
     * 
     */
    public long getPaymentTransactionEntryId() {
        return paymentTransactionEntryId;
    }

    /**
     * Sets the value of the paymentTransactionEntryId property.
     * 
     */
    public void setPaymentTransactionEntryId(long value) {
        this.paymentTransactionEntryId = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link TransEntryStatusType }
     *     
     */
    public TransEntryStatusType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransEntryStatusType }
     *     
     */
    public void setStatus(TransEntryStatusType value) {
        this.status = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link TransEntryType }
     *     
     */
    public TransEntryType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransEntryType }
     *     
     */
    public void setType(TransEntryType value) {
        this.type = value;
    }

    /**
     * Gets the value of the resultType property.
     * 
     * @return
     *     possible object is
     *     {@link TransEntryResultType }
     *     
     */
    public TransEntryResultType getResultType() {
        return resultType;
    }

    /**
     * Sets the value of the resultType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransEntryResultType }
     *     
     */
    public void setResultType(TransEntryResultType value) {
        this.resultType = value;
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
     * Gets the value of the serviceRefCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceRefCode() {
        return serviceRefCode;
    }

    /**
     * Sets the value of the serviceRefCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceRefCode(String value) {
        this.serviceRefCode = value;
    }

    /**
     * Gets the value of the transactionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTransactionDate() {
        return transactionDate;
    }

    /**
     * Sets the value of the transactionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTransactionDate(XMLGregorianCalendar value) {
        this.transactionDate = value;
    }

}
