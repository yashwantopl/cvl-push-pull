
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PermissionConfirmationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PermissionConfirmationType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PermissionConfirmationId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="PermissionConfirmation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PermissionConfirmationType", propOrder = {
    "permissionConfirmationId",
    "permissionConfirmation"
})
public class PermissionConfirmationType {

    @XmlElement(name = "PermissionConfirmationId")
    protected long permissionConfirmationId;
    @XmlElement(name = "PermissionConfirmation", required = true)
    protected String permissionConfirmation;

    /**
     * Gets the value of the permissionConfirmationId property.
     * 
     */
    public long getPermissionConfirmationId() {
        return permissionConfirmationId;
    }

    /**
     * Sets the value of the permissionConfirmationId property.
     * 
     */
    public void setPermissionConfirmationId(long value) {
        this.permissionConfirmationId = value;
    }

    /**
     * Gets the value of the permissionConfirmation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPermissionConfirmation() {
        return permissionConfirmation;
    }

    /**
     * Sets the value of the permissionConfirmation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPermissionConfirmation(String value) {
        this.permissionConfirmation = value;
    }

}
