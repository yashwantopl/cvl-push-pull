
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.DeliveryType;
import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.NotifyStatusType;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for NotificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NotificationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NotificationId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CustomerId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CustomerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NotificationLoggedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="NotificationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element ref="{com/truelink/schema/database/tcps/enumerations}NotifyStatus"/>
 *         &lt;element name="DisplayNotifyStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{com/truelink/schema/database/tcps/enumerations}Delivery"/>
 *         &lt;element ref="{com/truelink/schema/database/tcps/enumerations}Notification"/>
 *         &lt;element name="NotificaionData" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LastSentDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="AllowResend" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NotificationType", propOrder = {
    "notificationId",
    "customerId",
    "customerName",
    "notificationLoggedDate",
    "notificationDate",
    "notifyStatus",
    "displayNotifyStatus",
    "delivery",
    "notification",
    "notificaionData",
    "lastSentDate",
    "allowResend"
})
public class NotificationType {

    @XmlElement(name = "NotificationId")
    protected long notificationId;
    @XmlElement(name = "CustomerId")
    protected long customerId;
    @XmlElement(name = "CustomerName", required = true)
    protected String customerName;
    @XmlElement(name = "NotificationLoggedDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar notificationLoggedDate;
    @XmlElement(name = "NotificationDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar notificationDate;
    @XmlElement(name = "NotifyStatus", namespace = "com/truelink/schema/database/tcps/enumerations", required = true)
    @XmlSchemaType(name = "string")
    protected NotifyStatusType notifyStatus;
    @XmlElement(name = "DisplayNotifyStatus", required = true)
    protected String displayNotifyStatus;
    @XmlElement(name = "Delivery", namespace = "com/truelink/schema/database/tcps/enumerations", required = true)
    @XmlSchemaType(name = "string")
    protected DeliveryType delivery;
    @XmlElement(name = "Notification", namespace = "com/truelink/schema/database/tcps/enumerations", required = true)
    protected String notification;
    @XmlElement(name = "NotificaionData", required = true)
    protected String notificaionData;
    @XmlElement(name = "LastSentDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastSentDate;
    @XmlElement(name = "AllowResend")
    protected Boolean allowResend;

    /**
     * Gets the value of the notificationId property.
     * 
     */
    public long getNotificationId() {
        return notificationId;
    }

    /**
     * Sets the value of the notificationId property.
     * 
     */
    public void setNotificationId(long value) {
        this.notificationId = value;
    }

    /**
     * Gets the value of the customerId property.
     * 
     */
    public long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the value of the customerId property.
     * 
     */
    public void setCustomerId(long value) {
        this.customerId = value;
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
     * Gets the value of the notificationLoggedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNotificationLoggedDate() {
        return notificationLoggedDate;
    }

    /**
     * Sets the value of the notificationLoggedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNotificationLoggedDate(XMLGregorianCalendar value) {
        this.notificationLoggedDate = value;
    }

    /**
     * Gets the value of the notificationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNotificationDate() {
        return notificationDate;
    }

    /**
     * Sets the value of the notificationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNotificationDate(XMLGregorianCalendar value) {
        this.notificationDate = value;
    }

    /**
     * Gets the value of the notifyStatus property.
     * 
     * @return
     *     possible object is
     *     {@link NotifyStatusType }
     *     
     */
    public NotifyStatusType getNotifyStatus() {
        return notifyStatus;
    }

    /**
     * Sets the value of the notifyStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link NotifyStatusType }
     *     
     */
    public void setNotifyStatus(NotifyStatusType value) {
        this.notifyStatus = value;
    }

    /**
     * Gets the value of the displayNotifyStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayNotifyStatus() {
        return displayNotifyStatus;
    }

    /**
     * Sets the value of the displayNotifyStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayNotifyStatus(String value) {
        this.displayNotifyStatus = value;
    }

    /**
     * Gets the value of the delivery property.
     * 
     * @return
     *     possible object is
     *     {@link DeliveryType }
     *     
     */
    public DeliveryType getDelivery() {
        return delivery;
    }

    /**
     * Sets the value of the delivery property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeliveryType }
     *     
     */
    public void setDelivery(DeliveryType value) {
        this.delivery = value;
    }

    /**
     * Gets the value of the notification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotification() {
        return notification;
    }

    /**
     * Sets the value of the notification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotification(String value) {
        this.notification = value;
    }

    /**
     * Gets the value of the notificaionData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotificaionData() {
        return notificaionData;
    }

    /**
     * Sets the value of the notificaionData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificaionData(String value) {
        this.notificaionData = value;
    }

    /**
     * Gets the value of the lastSentDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastSentDate() {
        return lastSentDate;
    }

    /**
     * Sets the value of the lastSentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastSentDate(XMLGregorianCalendar value) {
        this.lastSentDate = value;
    }

    /**
     * Gets the value of the allowResend property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowResend() {
        return allowResend;
    }

    /**
     * Sets the value of the allowResend property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowResend(Boolean value) {
        this.allowResend = value;
    }

}
