
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;


import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.InvoiceStatusType;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for InvoiceType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InvoiceType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InvoiceStatus" type="{com/truelink/schema/database/tcps/enumerations}InvoiceStatusType"/>
 *         &lt;element name="ContactName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BillingAddress" type="{com/truelink/schema/msp}AddressType"/>
 *         &lt;element name="Date" type="{http://www.w3.org/2001/XMLSchema}anySimpleType"/>
 *         &lt;element name="TotalAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="PayableDate" type="{http://www.w3.org/2001/XMLSchema}anySimpleType"/>
 *         &lt;element name="InvoiceItemFull" type="{com/truelink/schema/msp}InvoiceItemFullType" maxOccurs="unbounded"/>
 *         &lt;element name="ReferenceCode" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="PastDueDate" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" minOccurs="0"/>
 *         &lt;element name="SalesTaxRate" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="SaleTaxAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="DbTotal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="DiscountAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="RefundedInvoiceId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="SubTotal" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="BtxRefKey" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvoiceType", propOrder = {
    "invoiceStatus",
    "contactName",
    "billingAddress",
    "date",
    "totalAmount",
    "payableDate",
    "invoiceItemFull",
    "referenceCode",
    "pastDueDate",
    "salesTaxRate",
    "saleTaxAmount",
    "dbTotal",
    "discountAmount",
    "refundedInvoiceId",
    "subTotal",
    "btxRefKey"
})
public class InvoiceType {

    @XmlElement(name = "InvoiceStatus", required = true)
    @XmlSchemaType(name = "string")
    protected InvoiceStatusType invoiceStatus;
    @XmlElement(name = "ContactName", required = true)
    protected String contactName;
    @XmlElement(name = "BillingAddress", required = true)
    protected AddressType billingAddress;
    @XmlElement(name = "Date", required = true)
    protected Object date;
    @XmlElement(name = "TotalAmount", required = true)
    protected BigDecimal totalAmount;
    @XmlElement(name = "PayableDate", required = true)
    protected Object payableDate;
    @XmlElement(name = "InvoiceItemFull", required = true)
    protected List<InvoiceItemFullType> invoiceItemFull;
    @XmlElement(name = "ReferenceCode")
    protected Long referenceCode;
    @XmlElement(name = "PastDueDate")
    protected Object pastDueDate;
    @XmlElement(name = "SalesTaxRate")
    protected BigDecimal salesTaxRate;
    @XmlElement(name = "SaleTaxAmount")
    protected BigDecimal saleTaxAmount;
    @XmlElement(name = "DbTotal")
    protected BigDecimal dbTotal;
    @XmlElement(name = "DiscountAmount")
    protected BigDecimal discountAmount;
    @XmlElement(name = "RefundedInvoiceId")
    protected Long refundedInvoiceId;
    @XmlElement(name = "SubTotal")
    protected BigDecimal subTotal;
    @XmlElement(name = "BtxRefKey")
    protected BigDecimal btxRefKey;

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
     * Gets the value of the contactName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Sets the value of the contactName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactName(String value) {
        this.contactName = value;
    }

    /**
     * Gets the value of the billingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getBillingAddress() {
        return billingAddress;
    }

    /**
     * Sets the value of the billingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     */
    public void setBillingAddress(AddressType value) {
        this.billingAddress = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setDate(Object value) {
        this.date = value;
    }

    /**
     * Gets the value of the totalAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * Sets the value of the totalAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalAmount(BigDecimal value) {
        this.totalAmount = value;
    }

    /**
     * Gets the value of the payableDate property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getPayableDate() {
        return payableDate;
    }

    /**
     * Sets the value of the payableDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setPayableDate(Object value) {
        this.payableDate = value;
    }

    /**
     * Gets the value of the invoiceItemFull property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invoiceItemFull property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvoiceItemFull().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InvoiceItemFullType }
     * 
     * 
     */
    public List<InvoiceItemFullType> getInvoiceItemFull() {
        if (invoiceItemFull == null) {
            invoiceItemFull = new ArrayList<InvoiceItemFullType>();
        }
        return this.invoiceItemFull;
    }

    /**
     * Gets the value of the referenceCode property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getReferenceCode() {
        return referenceCode;
    }

    /**
     * Sets the value of the referenceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setReferenceCode(Long value) {
        this.referenceCode = value;
    }

    /**
     * Gets the value of the pastDueDate property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getPastDueDate() {
        return pastDueDate;
    }

    /**
     * Sets the value of the pastDueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setPastDueDate(Object value) {
        this.pastDueDate = value;
    }

    /**
     * Gets the value of the salesTaxRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSalesTaxRate() {
        return salesTaxRate;
    }

    /**
     * Sets the value of the salesTaxRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSalesTaxRate(BigDecimal value) {
        this.salesTaxRate = value;
    }

    /**
     * Gets the value of the saleTaxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSaleTaxAmount() {
        return saleTaxAmount;
    }

    /**
     * Sets the value of the saleTaxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSaleTaxAmount(BigDecimal value) {
        this.saleTaxAmount = value;
    }

    /**
     * Gets the value of the dbTotal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDbTotal() {
        return dbTotal;
    }

    /**
     * Sets the value of the dbTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDbTotal(BigDecimal value) {
        this.dbTotal = value;
    }

    /**
     * Gets the value of the discountAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Sets the value of the discountAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDiscountAmount(BigDecimal value) {
        this.discountAmount = value;
    }

    /**
     * Gets the value of the refundedInvoiceId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRefundedInvoiceId() {
        return refundedInvoiceId;
    }

    /**
     * Sets the value of the refundedInvoiceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRefundedInvoiceId(Long value) {
        this.refundedInvoiceId = value;
    }

    /**
     * Gets the value of the subTotal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSubTotal() {
        return subTotal;
    }

    /**
     * Sets the value of the subTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSubTotal(BigDecimal value) {
        this.subTotal = value;
    }

    /**
     * Gets the value of the btxRefKey property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBtxRefKey() {
        return btxRefKey;
    }

    /**
     * Sets the value of the btxRefKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBtxRefKey(BigDecimal value) {
        this.btxRefKey = value;
    }

}
