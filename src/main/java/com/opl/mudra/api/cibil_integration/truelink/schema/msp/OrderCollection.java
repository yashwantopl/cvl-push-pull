
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
 *         &lt;element name="OrderFull" type="{com/truelink/schema/msp}OrderFullType"/>
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
    "orderFull"
})
@XmlRootElement(name = "OrderCollection")
public class OrderCollection {

    @XmlElement(name = "OrderFull", required = true)
    protected OrderFullType orderFull;

    /**
     * Gets the value of the orderFull property.
     * 
     * @return
     *     possible object is
     *     {@link OrderFullType }
     *     
     */
    public OrderFullType getOrderFull() {
        return orderFull;
    }

    /**
     * Sets the value of the orderFull property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderFullType }
     *     
     */
    public void setOrderFull(OrderFullType value) {
        this.orderFull = value;
    }

}
