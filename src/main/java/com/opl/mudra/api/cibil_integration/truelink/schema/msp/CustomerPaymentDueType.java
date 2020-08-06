
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;


/**
 * <p>Java class for CustomerPaymentDueType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerPaymentDueType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CustomerId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="PaymentScheduleId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="PartnerCustomerId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ExpirationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="AmountDue" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerPaymentDueType", propOrder = {
    "customerId",
    "paymentScheduleId",
    "partnerCustomerId",
    "expirationDate",
    "amountDue"
})
public class CustomerPaymentDueType {

    @XmlElement(name = "CustomerId")
    protected long customerId;
    @XmlElement(name = "PaymentScheduleId")
    protected long paymentScheduleId;
    @XmlElement(name = "PartnerCustomerId", required = true)
    protected String partnerCustomerId;
    @XmlElement(name = "ExpirationDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expirationDate;
    @XmlElement(name = "AmountDue", required = true)
    protected BigDecimal amountDue;

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
     * Gets the value of the partnerCustomerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerCustomerId() {
        return partnerCustomerId;
    }

    /**
     * Sets the value of the partnerCustomerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerCustomerId(String value) {
        this.partnerCustomerId = value;
    }

    /**
     * Gets the value of the expirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the value of the expirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpirationDate(XMLGregorianCalendar value) {
        this.expirationDate = value;
    }

    /**
     * Gets the value of the amountDue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmountDue() {
        return amountDue;
    }

    /**
     * Sets the value of the amountDue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmountDue(BigDecimal value) {
        this.amountDue = value;
    }

}
