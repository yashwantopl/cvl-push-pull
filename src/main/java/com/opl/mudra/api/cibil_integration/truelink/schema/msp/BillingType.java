
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.InvoiceStatusType;


/**
 * <p>Java class for BillingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BillingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PaymentOptionType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AccountNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="AmountBilled" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="SalesTax" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="BillingDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/schema/database/tcps/enumerations}Invoice" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/schema/database/tcps/enumerations}InvoiceStatus" minOccurs="0"/>
 *         &lt;element name="DisplayStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AccountHoldersName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="InvoiceId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="AllowRefund" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillingType", propOrder = {
    "paymentOptionType",
    "accountNumber",
    "startDate",
    "amountBilled",
    "salesTax",
    "billingDate",
    "invoice",
    "invoiceStatus",
    "displayStatus",
    "accountHoldersName",
    "invoiceId",
    "allowRefund"
})
public class BillingType {

    @XmlElement(name = "PaymentOptionType", required = true)
    protected String paymentOptionType;
    @XmlElement(name = "AccountNumber", required = true)
    protected String accountNumber;
    @XmlElement(name = "StartDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlElement(name = "AmountBilled")
    protected BigDecimal amountBilled;
    @XmlElement(name = "SalesTax")
    protected BigDecimal salesTax;
    @XmlElement(name = "BillingDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar billingDate;
    @XmlElement(name = "Invoice", namespace = "com/truelink/schema/database/tcps/enumerations")
    @XmlSchemaType(name = "string")
    protected InvoiceType invoice;
    @XmlElement(name = "InvoiceStatus", namespace = "com/truelink/schema/database/tcps/enumerations")
    @XmlSchemaType(name = "string")
    protected InvoiceStatusType invoiceStatus;
    @XmlElement(name = "DisplayStatus", required = true)
    protected String displayStatus;
    @XmlElement(name = "AccountHoldersName", required = true)
    protected String accountHoldersName;
    @XmlElement(name = "InvoiceId")
    protected Long invoiceId;
    @XmlElement(name = "AllowRefund")
    protected Boolean allowRefund;

    /**
     * Gets the value of the paymentOptionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentOptionType() {
        return paymentOptionType;
    }

    /**
     * Sets the value of the paymentOptionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentOptionType(String value) {
        this.paymentOptionType = value;
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
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the amountBilled property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmountBilled() {
        return amountBilled;
    }

    /**
     * Sets the value of the amountBilled property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmountBilled(BigDecimal value) {
        this.amountBilled = value;
    }

    /**
     * Gets the value of the salesTax property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSalesTax() {
        return salesTax;
    }

    /**
     * Sets the value of the salesTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSalesTax(BigDecimal value) {
        this.salesTax = value;
    }

    /**
     * Gets the value of the billingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBillingDate() {
        return billingDate;
    }

    /**
     * Sets the value of the billingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBillingDate(XMLGregorianCalendar value) {
        this.billingDate = value;
    }

    /**
     * Gets the value of the invoice property.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceType }
     *     
     */
    public InvoiceType getInvoice() {
        return invoice;
    }

    /**
     * Sets the value of the invoice property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceType }
     *     
     */
    public void setInvoice(InvoiceType value) {
        this.invoice = value;
    }

    /**
     * Gets the value of the invoiceStatus property.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceStatusType }
     *     
     */
    public InvoiceStatusType getInvoiceStatus() {
        return invoiceStatus;
    }

    /**
     * Sets the value of the invoiceStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceStatusType }
     *     
     */
    public void setInvoiceStatus(InvoiceStatusType value) {
        this.invoiceStatus = value;
    }

    /**
     * Gets the value of the displayStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayStatus() {
        return displayStatus;
    }

    /**
     * Sets the value of the displayStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayStatus(String value) {
        this.displayStatus = value;
    }

    /**
     * Gets the value of the accountHoldersName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountHoldersName() {
        return accountHoldersName;
    }

    /**
     * Sets the value of the accountHoldersName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountHoldersName(String value) {
        this.accountHoldersName = value;
    }

    /**
     * Gets the value of the invoiceId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getInvoiceId() {
        return invoiceId;
    }

    /**
     * Sets the value of the invoiceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setInvoiceId(Long value) {
        this.invoiceId = value;
    }

    /**
     * Gets the value of the allowRefund property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowRefund() {
        return allowRefund;
    }

    /**
     * Sets the value of the allowRefund property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowRefund(Boolean value) {
        this.allowRefund = value;
    }

}
