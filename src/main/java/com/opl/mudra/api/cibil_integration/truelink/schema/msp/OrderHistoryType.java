
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for OrderHistoryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderHistoryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Order" type="{com/truelink/schema/msp}OrderFullType"/>
 *         &lt;element name="Invoice" type="{com/truelink/schema/msp}OrderHistoryInvoiceType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderHistoryType", propOrder = {
    "order",
    "invoice"
})
public class OrderHistoryType {

    @XmlElement(name = "Order", required = true)
    protected OrderFullType order;
    @XmlElement(name = "Invoice", required = true)
    protected List<OrderHistoryInvoiceType> invoice;

    /**
     * Gets the value of the order property.
     * 
     * @return
     *     possible object is
     *     {@link OrderFullType }
     *     
     */
    public OrderFullType getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderFullType }
     *     
     */
    public void setOrder(OrderFullType value) {
        this.order = value;
    }

    /**
     * Gets the value of the invoice property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invoice property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvoice().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderHistoryInvoiceType }
     * 
     * 
     */
    public List<OrderHistoryInvoiceType> getInvoice() {
        if (invoice == null) {
            invoice = new ArrayList<OrderHistoryInvoiceType>();
        }
        return this.invoice;
    }

}
