
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomerToUpdatePaymentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerToUpdatePaymentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CustomerId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="PaymentScheduleId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="PaymentStatus" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerToUpdatePaymentType", propOrder = {
    "customerId",
    "paymentScheduleId",
    "paymentStatus"
})
public class CustomerToUpdatePaymentType {

    @XmlElement(name = "CustomerId")
    protected long customerId;
    @XmlElement(name = "PaymentScheduleId")
    protected long paymentScheduleId;
    @XmlElement(name = "PaymentStatus", required = true)
    protected String paymentStatus;

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
     * Gets the value of the paymentScheduleId property.
     * 
     */
    public long getPaymentScheduleId() {
        return paymentScheduleId;
    }

    /**
     * Sets the value of the paymentScheduleId property.
     * 
     */
    public void setPaymentScheduleId(long value) {
        this.paymentScheduleId = value;
    }

    /**
     * Gets the value of the paymentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Sets the value of the paymentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentStatus(String value) {
        this.paymentStatus = value;
    }

}
