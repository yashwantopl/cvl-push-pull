
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InvoiceItemFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InvoiceItemFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InvoiceItemId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="InvoiceItemInfo" type="{com/truelink/schema/msp}InvoiceItemType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InvoiceItemFullType", propOrder = {
    "invoiceItemId",
    "invoiceItemInfo"
})
public class InvoiceItemFullType {

    @XmlElement(name = "InvoiceItemId")
    protected long invoiceItemId;
    @XmlElement(name = "InvoiceItemInfo", required = true)
    protected InvoiceItemType invoiceItemInfo;

    /**
     * Gets the value of the invoiceItemId property.
     * 
     */
    public long getInvoiceItemId() {
        return invoiceItemId;
    }

    /**
     * Sets the value of the invoiceItemId property.
     * 
     */
    public void setInvoiceItemId(long value) {
        this.invoiceItemId = value;
    }

    /**
     * Gets the value of the invoiceItemInfo property.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceItemType }
     *     
     */
    public InvoiceItemType getInvoiceItemInfo() {
        return invoiceItemInfo;
    }

    /**
     * Sets the value of the invoiceItemInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceItemType }
     *     
     */
    public void setInvoiceItemInfo(InvoiceItemType value) {
        this.invoiceItemInfo = value;
    }

}
