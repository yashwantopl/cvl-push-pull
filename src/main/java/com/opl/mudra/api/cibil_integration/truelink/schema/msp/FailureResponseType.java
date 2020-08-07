
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.FailureEnum;
import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.TimePeriodUnitType;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FailureResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FailureResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="Message" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FailureEnum" type="{com/truelink/schema/database/tcps/enumerations}FailureEnum" />
 *       &lt;attribute name="FailureSchId" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="SecondFailureSchId" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="CustomerId" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="BtxRefKey" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="Severity" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="TimePeriod" type="{com/truelink/schema/database/tcps/enumerations}TimePeriodUnitType" />
 *       &lt;attribute name="ClientUserKey" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ClientRefreshURL" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="ServiceUserId" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="IETransactionId" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FailureResponseType")
public class FailureResponseType {

    @XmlAttribute(name = "Message")
    protected String message;
    @XmlAttribute(name = "FailureEnum")
    protected FailureEnum failureEnum;
    @XmlAttribute(name = "FailureSchId")
    protected Long failureSchId;
    @XmlAttribute(name = "SecondFailureSchId")
    protected Long secondFailureSchId;
    @XmlAttribute(name = "CustomerId")
    protected Long customerId;
    @XmlAttribute(name = "BtxRefKey")
    protected Long btxRefKey;
    @XmlAttribute(name = "Severity")
    protected String severity;
    @XmlAttribute(name = "TimePeriod")
    protected TimePeriodUnitType timePeriod;
    @XmlAttribute(name = "ClientUserKey")
    protected String clientUserKey;
    @XmlAttribute(name = "ClientRefreshURL")
    protected String clientRefreshURL;
    @XmlAttribute(name = "ServiceUserId")
    protected Long serviceUserId;
    @XmlAttribute(name = "IETransactionId")
    protected Long ieTransactionId;

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * Gets the value of the failureEnum property.
     * 
     * @return
     *     possible object is
     *     {@link FailureEnum }
     *     
     */
    public FailureEnum getFailureEnum() {
        return failureEnum;
    }

    /**
     * Sets the value of the failureEnum property.
     * 
     * @param value
     *     allowed object is
     *     {@link FailureEnum }
     *     
     */
    public void setFailureEnum(FailureEnum value) {
        this.failureEnum = value;
    }

    /**
     * Gets the value of the failureSchId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getFailureSchId() {
        return failureSchId;
    }

    /**
     * Sets the value of the failureSchId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setFailureSchId(Long value) {
        this.failureSchId = value;
    }

    /**
     * Gets the value of the secondFailureSchId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSecondFailureSchId() {
        return secondFailureSchId;
    }

    /**
     * Sets the value of the secondFailureSchId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSecondFailureSchId(Long value) {
        this.secondFailureSchId = value;
    }

    /**
     * Gets the value of the customerId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the value of the customerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCustomerId(Long value) {
        this.customerId = value;
    }

    /**
     * Gets the value of the btxRefKey property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBtxRefKey() {
        return btxRefKey;
    }

    /**
     * Sets the value of the btxRefKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBtxRefKey(Long value) {
        this.btxRefKey = value;
    }

    /**
     * Gets the value of the severity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeverity() {
        return severity;
    }

    /**
     * Sets the value of the severity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeverity(String value) {
        this.severity = value;
    }

    /**
     * Gets the value of the timePeriod property.
     * 
     * @return
     *     possible object is
     *     {@link TimePeriodUnitType }
     *     
     */
    public TimePeriodUnitType getTimePeriod() {
        return timePeriod;
    }

    /**
     * Sets the value of the timePeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link TimePeriodUnitType }
     *     
     */
    public void setTimePeriod(TimePeriodUnitType value) {
        this.timePeriod = value;
    }

    /**
     * Gets the value of the clientUserKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientUserKey() {
        return clientUserKey;
    }

    /**
     * Sets the value of the clientUserKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientUserKey(String value) {
        this.clientUserKey = value;
    }

    /**
     * Gets the value of the clientRefreshURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientRefreshURL() {
        return clientRefreshURL;
    }

    /**
     * Sets the value of the clientRefreshURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientRefreshURL(String value) {
        this.clientRefreshURL = value;
    }

    /**
     * Gets the value of the serviceUserId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getServiceUserId() {
        return serviceUserId;
    }

    /**
     * Sets the value of the serviceUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setServiceUserId(Long value) {
        this.serviceUserId = value;
    }

    /**
     * Gets the value of the ieTransactionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIETransactionId() {
        return ieTransactionId;
    }

    /**
     * Sets the value of the ieTransactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIETransactionId(Long value) {
        this.ieTransactionId = value;
    }

	@Override
	public String toString() {
		return "FailureResponseType [message=" + message + ", failureEnum=" + failureEnum + ", failureSchId="
				+ failureSchId + ", secondFailureSchId=" + secondFailureSchId + ", customerId=" + customerId
				+ ", btxRefKey=" + btxRefKey + ", severity=" + severity + ", timePeriod=" + timePeriod
				+ ", clientUserKey=" + clientUserKey + ", clientRefreshURL=" + clientRefreshURL + ", serviceUserId="
				+ serviceUserId + ", ieTransactionId=" + ieTransactionId + "]";
	}

}
