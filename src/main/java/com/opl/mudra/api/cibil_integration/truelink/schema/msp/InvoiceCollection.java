
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InvoiceFull" type="{com/truelink/schema/msp}InvoiceFullType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "invoiceFull"
})
@XmlRootElement(name = "InvoiceCollection")
public class InvoiceCollection {

    @XmlElement(name = "InvoiceFull", required = true)
    protected InvoiceFullType invoiceFull;

    /**
     * Gets the value of the invoiceFull property.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceFullType }
     *     
     */
    public InvoiceFullType getInvoiceFull() {
        return invoiceFull;
    }

    /**
     * Sets the value of the invoiceFull property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceFullType }
     *     
     */
    public void setInvoiceFull(InvoiceFullType value) {
        this.invoiceFull = value;
    }

}
