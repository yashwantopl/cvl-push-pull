
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.OrderStatusType;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for OrderType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrderItem" type="{com/truelink/schema/msp}OrderItemFullType" maxOccurs="unbounded"/>
 *         &lt;element name="CreationDate" type="{http://www.w3.org/2001/XMLSchema}anySimpleType"/>
 *         &lt;element name="PendExpirationDate" type="{http://www.w3.org/2001/XMLSchema}anySimpleType"/>
 *         &lt;element name="ReferenceKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{com/truelink/schema/database/tcps/enumerations}OrderStatus"/>
 *         &lt;element name="OrderStatusDate" type="{http://www.w3.org/2001/XMLSchema}anySimpleType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderType", propOrder = {
    "orderItem",
    "creationDate",
    "pendExpirationDate",
    "referenceKey",
    "orderStatus",
    "orderStatusDate"
})
public class OrderType {

    @XmlElement(name = "OrderItem", required = true)
    protected List<OrderItemFullType> orderItem;
    @XmlElement(name = "CreationDate", required = true)
    protected Object creationDate;
    @XmlElement(name = "PendExpirationDate", required = true)
    protected Object pendExpirationDate;
    @XmlElement(name = "ReferenceKey", required = true)
    protected String referenceKey;
    @XmlElement(name = "OrderStatus", namespace = "com/truelink/schema/database/tcps/enumerations", required = true)
    @XmlSchemaType(name = "string")
    protected OrderStatusType orderStatus;
    @XmlElement(name = "OrderStatusDate", required = true)
    protected Object orderStatusDate;

    /**
     * Gets the value of the orderItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderItemFullType }
     * 
     * 
     */
    public List<OrderItemFullType> getOrderItem() {
        if (orderItem == null) {
            orderItem = new ArrayList<OrderItemFullType>();
        }
        return this.orderItem;
    }

    /**
     * Gets the value of the creationDate property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setCreationDate(Object value) {
        this.creationDate = value;
    }

    /**
     * Gets the value of the pendExpirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getPendExpirationDate() {
        return pendExpirationDate;
    }

    /**
     * Sets the value of the pendExpirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setPendExpirationDate(Object value) {
        this.pendExpirationDate = value;
    }

    /**
     * Gets the value of the referenceKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceKey() {
        return referenceKey;
    }

    /**
     * Sets the value of the referenceKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceKey(String value) {
        this.referenceKey = value;
    }

    /**
     * Gets the value of the orderStatus property.
     * 
     * @return
     *     possible object is
     *     {@link OrderStatusType }
     *     
     */
    public OrderStatusType getOrderStatus() {
        return orderStatus;
    }

    /**
     * Sets the value of the orderStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderStatusType }
     *     
     */
    public void setOrderStatus(OrderStatusType value) {
        this.orderStatus = value;
    }

    /**
     * Gets the value of the orderStatusDate property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getOrderStatusDate() {
        return orderStatusDate;
    }

    /**
     * Sets the value of the orderStatusDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setOrderStatusDate(Object value) {
        this.orderStatusDate = value;
    }

}
