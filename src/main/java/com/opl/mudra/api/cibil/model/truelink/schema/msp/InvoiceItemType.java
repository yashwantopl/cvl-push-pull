
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.DiscountAdjustmentType;


/**
 * <p>Java class for InvoiceItemType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InvoiceItemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Amount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Quantity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="InvoiceItemType" type="{com/truelink/schema/database/tcps/enumerations}InvoiceItemType"/>
 *         &lt;element name="BundleId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Taxable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AdjustedInvoiceItemId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="DiscountAdjustmentTypeId" type="{com/truelink/schema/database/tcps/enumerations}DiscountAdjustmentType" minOccurs="0"/>
 *         &lt;element name="PercentReduction" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="FixedAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="AmountReduction" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvoiceItemType", propOrder = {
    "amount",
    "quantity",
    "invoiceItemType",
    "bundleId",
    "taxable",
    "description",
    "adjustedInvoiceItemId",
    "discountAdjustmentTypeId",
    "percentReduction",
    "fixedAmount",
    "amountReduction"
})
public class InvoiceItemType {

    @XmlElement(name = "Amount", required = true)
    protected BigDecimal amount;
    @XmlElement(name = "Quantity")
    protected int quantity;
    @XmlElement(name = "InvoiceItemType", required = true)
    @XmlSchemaType(name = "string")
    protected com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.InvoiceItemType invoiceItemType;
    @XmlElement(name = "BundleId")
    protected long bundleId;
    @XmlElement(name = "Taxable")
    protected boolean taxable;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "AdjustedInvoiceItemId")
    protected Long adjustedInvoiceItemId;
    @XmlElement(name = "DiscountAdjustmentTypeId")
    @XmlSchemaType(name = "string")
    protected DiscountAdjustmentType discountAdjustmentTypeId;
    @XmlElement(name = "PercentReduction")
    protected BigDecimal percentReduction;
    @XmlElement(name = "FixedAmount")
    protected BigDecimal fixedAmount;
    @XmlElement(name = "AmountReduction")
    protected BigDecimal amountReduction;

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     */
    public void setQuantity(int value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the invoiceItemType property.
     * 
     * @return
     *     possible object is
     *     {@link com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.InvoiceItemType }
     *     
     */
    public com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.InvoiceItemType getInvoiceItemType() {
        return invoiceItemType;
    }

    /**
     * Sets the value of the invoiceItemType property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.InvoiceItemType }
     *     
     */
    public void setInvoiceItemType(com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.InvoiceItemType value) {
        this.invoiceItemType = value;
    }

    /**
     * Gets the value of the bundleId property.
     * 
     */
    public long getBundleId() {
        return bundleId;
    }

    /**
     * Sets the value of the bundleId property.
     * 
     */
    public void setBundleId(long value) {
        this.bundleId = value;
    }

    /**
     * Gets the value of the taxable property.
     * 
     */
    public boolean isTaxable() {
        return taxable;
    }

    /**
     * Sets the value of the taxable property.
     * 
     */
    public void setTaxable(boolean value) {
        this.taxable = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the adjustedInvoiceItemId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAdjustedInvoiceItemId() {
        return adjustedInvoiceItemId;
    }

    /**
     * Sets the value of the adjustedInvoiceItemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAdjustedInvoiceItemId(Long value) {
        this.adjustedInvoiceItemId = value;
    }

    /**
     * Gets the value of the discountAdjustmentTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link DiscountAdjustmentType }
     *     
     */
    public DiscountAdjustmentType getDiscountAdjustmentTypeId() {
        return discountAdjustmentTypeId;
    }

    /**
     * Sets the value of the discountAdjustmentTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscountAdjustmentType }
     *     
     */
    public void setDiscountAdjustmentTypeId(DiscountAdjustmentType value) {
        this.discountAdjustmentTypeId = value;
    }

    /**
     * Gets the value of the percentReduction property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPercentReduction() {
        return percentReduction;
    }

    /**
     * Sets the value of the percentReduction property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPercentReduction(BigDecimal value) {
        this.percentReduction = value;
    }

    /**
     * Gets the value of the fixedAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFixedAmount() {
        return fixedAmount;
    }

    /**
     * Sets the value of the fixedAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFixedAmount(BigDecimal value) {
        this.fixedAmount = value;
    }

    /**
     * Gets the value of the amountReduction property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmountReduction() {
        return amountReduction;
    }

    /**
     * Sets the value of the amountReduction property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmountReduction(BigDecimal value) {
        this.amountReduction = value;
    }

}
