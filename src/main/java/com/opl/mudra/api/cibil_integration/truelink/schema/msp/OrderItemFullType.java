
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrderItemFullType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderItemFullType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrderItemId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="OrderItemInfo" type="{com/truelink/schema/msp}OrderItemType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderItemFullType", propOrder = {
    "orderItemId",
    "orderItemInfo"
})
public class OrderItemFullType {

    @XmlElement(name = "OrderItemId")
    protected long orderItemId;
    @XmlElement(name = "OrderItemInfo", required = true)
    protected OrderItemType orderItemInfo;

    /**
     * Gets the value of the orderItemId property.
     * 
     */
    public long getOrderItemId() {
        return orderItemId;
    }

    /**
     * Sets the value of the orderItemId property.
     * 
     */
    public void setOrderItemId(long value) {
        this.orderItemId = value;
    }

    /**
     * Gets the value of the orderItemInfo property.
     * 
     * @return
     *     possible object is
     *     {@link OrderItemType }
     *     
     */
    public OrderItemType getOrderItemInfo() {
        return orderItemInfo;
    }

    /**
     * Sets the value of the orderItemInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderItemType }
     *     
     */
    public void setOrderItemInfo(OrderItemType value) {
        this.orderItemInfo = value;
    }

}
