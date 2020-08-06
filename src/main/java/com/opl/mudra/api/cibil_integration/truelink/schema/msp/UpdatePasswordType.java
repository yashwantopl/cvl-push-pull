
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.PasswordStatusType;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for UpdatePasswordType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UpdatePasswordType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{com/truelink/schema/database/tcps/enumerations}PasswordStatus"/>
 *         &lt;element name="NewPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ResetPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UpdatePasswordType", propOrder = {
    "passwordStatus",
    "newPassword",
    "resetPassword"
})
public class UpdatePasswordType {

    @XmlElement(name = "PasswordStatus", namespace = "com/truelink/schema/database/tcps/enumerations", required = true)
    @XmlSchemaType(name = "string")
    protected PasswordStatusType passwordStatus;
    @XmlElement(name = "NewPassword")
    protected String newPassword;
    @XmlElement(name = "ResetPassword")
    protected String resetPassword;

    /**
     * Gets the value of the passwordStatus property.
     * 
     * @return
     *     possible object is
     *     {@link PasswordStatusType }
     *     
     */
    public PasswordStatusType getPasswordStatus() {
        return passwordStatus;
    }

    /**
     * Sets the value of the passwordStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link PasswordStatusType }
     *     
     */
    public void setPasswordStatus(PasswordStatusType value) {
        this.passwordStatus = value;
    }

    /**
     * Gets the value of the newPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Sets the value of the newPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewPassword(String value) {
        this.newPassword = value;
    }

    /**
     * Gets the value of the resetPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResetPassword() {
        return resetPassword;
    }

    /**
     * Sets the value of the resetPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResetPassword(String value) {
        this.resetPassword = value;
    }

}
