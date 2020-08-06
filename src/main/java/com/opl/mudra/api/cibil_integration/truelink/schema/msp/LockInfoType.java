
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.LockReasonType;
import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.LockStatusType;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for LockInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LockInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LockReason" type="{com/truelink/schema/database/tcps/enumerations}LockReasonType"/>
 *         &lt;element name="LockStatus" type="{com/truelink/schema/database/tcps/enumerations}LockStatusType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LockInfoType", propOrder = {
    "lockReason",
    "lockStatus"
})
public class LockInfoType {

    @XmlElement(name = "LockReason", required = true)
    @XmlSchemaType(name = "string")
    protected LockReasonType lockReason;
    @XmlElement(name = "LockStatus", required = true)
    @XmlSchemaType(name = "string")
    protected LockStatusType lockStatus;

    /**
     * Gets the value of the lockReason property.
     * 
     * @return
     *     possible object is
     *     {@link LockReasonType }
     *     
     */
    public LockReasonType getLockReason() {
        return lockReason;
    }

    /**
     * Sets the value of the lockReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link LockReasonType }
     *     
     */
    public void setLockReason(LockReasonType value) {
        this.lockReason = value;
    }

    /**
     * Gets the value of the lockStatus property.
     * 
     * @return
     *     possible object is
     *     {@link LockStatusType }
     *     
     */
    public LockStatusType getLockStatus() {
        return lockStatus;
    }

    /**
     * Sets the value of the lockStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link LockStatusType }
     *     
     */
    public void setLockStatus(LockStatusType value) {
        this.lockStatus = value;
    }

}
