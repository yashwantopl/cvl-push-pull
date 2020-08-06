
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.CsptIssueType;


/**
 * <p>Java class for CsptReasonType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CsptReasonType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Count" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="Reason" type="{com/truelink/schema/database/tcps/enumerations}CsptIssueType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CsptReasonType", propOrder = {
    "count",
    "reason"
})
public class CsptReasonType {

    @XmlElement(name = "Count", required = true)
    protected BigInteger count;
    @XmlElement(name = "Reason", required = true)
    @XmlSchemaType(name = "string")
    protected CsptIssueType reason;

    /**
     * Gets the value of the count property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCount() {
        return count;
    }

    /**
     * Sets the value of the count property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCount(BigInteger value) {
        this.count = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link CsptIssueType }
     *     
     */
    public CsptIssueType getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link CsptIssueType }
     *     
     */
    public void setReason(CsptIssueType value) {
        this.reason = value;
    }

}
