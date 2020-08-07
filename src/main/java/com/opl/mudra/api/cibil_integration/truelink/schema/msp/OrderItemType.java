
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;


import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.OrderItemStatusType;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;


/**
 * <p>Java class for OrderItemType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderItemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CatalogItem" type="{com/truelink/schema/msp}CatalogItemType"/>
 *         &lt;element name="Price" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Quantity" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element ref="{com/truelink/schema/database/tcps/enumerations}OrderItemStatus"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderItemType", propOrder = {
    "catalogItem",
    "price",
    "quantity",
    "orderItemStatus"
})
public class OrderItemType {

    @XmlElement(name = "CatalogItem", required = true)
    protected CatalogItemType catalogItem;
    @XmlElement(name = "Price", required = true)
    protected BigDecimal price;
    @XmlElement(name = "Quantity")
    protected long quantity;
    @XmlElement(name = "OrderItemStatus", namespace = "com/truelink/schema/database/tcps/enumerations", required = true)
    @XmlSchemaType(name = "string")
    protected OrderItemStatusType orderItemStatus;

    /**
     * Gets the value of the catalogItem property.
     * 
     * @return
     *     possible object is
     *     {@link CatalogItemType }
     *     
     */
    public CatalogItemType getCatalogItem() {
        return catalogItem;
    }

    /**
     * Sets the value of the catalogItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogItemType }
     *     
     */
    public void setCatalogItem(CatalogItemType value) {
        this.catalogItem = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     */
    public long getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     */
    public void setQuantity(long value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the orderItemStatus property.
     * 
     * @return
     *     possible object is
     *     {@link OrderItemStatusType }
     *     
     */
    public OrderItemStatusType getOrderItemStatus() {
        return orderItemStatus;
    }

    /**
     * Sets the value of the orderItemStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderItemStatusType }
     *     
     */
    public void setOrderItemStatus(OrderItemStatusType value) {
        this.orderItemStatus = value;
    }

}
