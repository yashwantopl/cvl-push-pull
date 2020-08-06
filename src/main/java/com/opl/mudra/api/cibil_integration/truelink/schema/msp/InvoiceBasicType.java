
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;


import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.InvoiceStatusType;
import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.StateType;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for InvoiceBasicType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InvoiceBasicType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CustomerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DiscountAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DbTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InvoiceStatus" type="{com/truelink/schema/database/tcps/enumerations}InvoiceStatusType" minOccurs="0"/>
 *         &lt;element name="TotalAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PayableDate" type="{http://www.w3.org/2001/XMLSchema}anySimpleType"/>
 *         &lt;element name="PastDueDate" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" minOccurs="0"/>
 *         &lt;element name="RefundInvoiceId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="SalesTaxAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SalesTaxRate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="State" type="{com/truelink/schema/database/tcps/enumerations}StateType"/>
 *         &lt;element name="StreetAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SubTotal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvoiceBasicType", propOrder = {
    "city",
    "customerName",
    "discountAmount",
    "dbTotal",
    "invoiceStatus",
    "totalAmount",
    "payableDate",
    "pastDueDate",
    "refundInvoiceId",
    "salesTaxAmount",
    "salesTaxRate",
    "state",
    "streetAddress",
    "subTotal"
})
public class InvoiceBasicType {

    @XmlElement(name = "City", required = true)
    protected String city;
    @XmlElement(name = "CustomerName", required = true)
    protected String customerName;
    @XmlElement(name = "DiscountAmount")
    protected String discountAmount;
    @XmlElement(name = "DbTotal")
    protected String dbTotal;
    @XmlElement(name = "InvoiceStatus")
    @XmlSchemaType(name = "string")
    protected InvoiceStatusType invoiceStatus;
    @XmlElement(name = "TotalAmount", required = true)
    protected String totalAmount;
    @XmlElement(name = "PayableDate", required = true)
    protected Object payableDate;
    @XmlElement(name = "PastDueDate")
    protected Object pastDueDate;
    @XmlElement(name = "RefundInvoiceId")
    protected Long refundInvoiceId;
    @XmlElement(name = "SalesTaxAmount")
    protected String salesTaxAmount;
    @XmlElement(name = "SalesTaxRate")
    protected String salesTaxRate;
    @XmlElement(name = "State", required = true)
    @XmlSchemaType(name = "string")
    protected StateType state;
    @XmlElement(name = "StreetAddress", required = true)
    protected String streetAddress;
    @XmlElement(name = "SubTotal")
    protected String subTotal;

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the customerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the value of the customerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerName(String value) {
        this.customerName = value;
    }

    /**
     * Gets the value of the discountAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Sets the value of the discountAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountAmount(String value) {
        this.discountAmount = value;
    }

    /**
     * Gets the value of the dbTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDbTotal() {
        return dbTotal;
    }

    /**
     * Sets the value of the dbTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDbTotal(String value) {
        this.dbTotal = value;
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
     * Gets the value of the totalAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalAmount() {
        return totalAmount;
    }

    /**
     * Sets the value of the totalAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalAmount(String value) {
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
     * Gets the value of the refundInvoiceId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRefundInvoiceId() {
        return refundInvoiceId;
    }

    /**
     * Sets the value of the refundInvoiceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRefundInvoiceId(Long value) {
        this.refundInvoiceId = value;
    }

    /**
     * Gets the value of the salesTaxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesTaxAmount() {
        return salesTaxAmount;
    }

    /**
     * Sets the value of the salesTaxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesTaxAmount(String value) {
        this.salesTaxAmount = value;
    }

    /**
     * Gets the value of the salesTaxRate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesTaxRate() {
        return salesTaxRate;
    }

    /**
     * Sets the value of the salesTaxRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesTaxRate(String value) {
        this.salesTaxRate = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link StateType }
     *     
     */
    public StateType getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link StateType }
     *     
     */
    public void setState(StateType value) {
        this.state = value;
    }

    /**
     * Gets the value of the streetAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Sets the value of the streetAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetAddress(String value) {
        this.streetAddress = value;
    }

    /**
     * Gets the value of the subTotal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubTotal() {
        return subTotal;
    }

    /**
     * Sets the value of the subTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubTotal(String value) {
        this.subTotal = value;
    }

}
