
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.FailureEnum;


/**
 * <p>Java class for BTXFailureResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BTXFailureResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="BTXReferenceId" type="{http://www.w3.org/2001/XMLSchema}long" />
 *       &lt;attribute name="Message" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="FailureEnum" type="{com/truelink/schema/database/tcps/enumerations}FailureEnum" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BTXFailureResponseType")
public class BTXFailureResponseType {

    @XmlAttribute(name = "BTXReferenceId")
    protected Long btxReferenceId;
    @XmlAttribute(name = "Message")
    protected String message;
    @XmlAttribute(name = "FailureEnum")
    protected FailureEnum failureEnum;

    /**
     * Gets the value of the btxReferenceId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBTXReferenceId() {
        return btxReferenceId;
    }

    /**
     * Sets the value of the btxReferenceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBTXReferenceId(Long value) {
        this.btxReferenceId = value;
    }

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

}
