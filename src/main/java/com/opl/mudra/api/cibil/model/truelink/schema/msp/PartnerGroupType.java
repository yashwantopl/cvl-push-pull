
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerGroupType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerGroupType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{com/truelink/schema/database/tcps/enumerations}PartnerGroup"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AcctValidationRequired" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="AcceptCreditCard" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="AcceptBankAccount" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="AllowWebEnrollment" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerGroupType", propOrder = {
    "partnerGroup",
    "name",
    "acctValidationRequired",
    "acceptCreditCard",
    "acceptBankAccount",
    "allowWebEnrollment"
})
public class PartnerGroupType {

    @XmlElement(name = "PartnerGroup", namespace = "com/truelink/schema/database/tcps/enumerations", required = true)
    protected String partnerGroup;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "AcctValidationRequired")
    protected boolean acctValidationRequired;
    @XmlElement(name = "AcceptCreditCard")
    protected boolean acceptCreditCard;
    @XmlElement(name = "AcceptBankAccount")
    protected boolean acceptBankAccount;
    @XmlElement(name = "AllowWebEnrollment")
    protected boolean allowWebEnrollment;

    /**
     * Gets the value of the partnerGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerGroup() {
        return partnerGroup;
    }

    /**
     * Sets the value of the partnerGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerGroup(String value) {
        this.partnerGroup = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the acctValidationRequired property.
     * 
     */
    public boolean isAcctValidationRequired() {
        return acctValidationRequired;
    }

    /**
     * Sets the value of the acctValidationRequired property.
     * 
     */
    public void setAcctValidationRequired(boolean value) {
        this.acctValidationRequired = value;
    }

    /**
     * Gets the value of the acceptCreditCard property.
     * 
     */
    public boolean isAcceptCreditCard() {
        return acceptCreditCard;
    }

    /**
     * Sets the value of the acceptCreditCard property.
     * 
     */
    public void setAcceptCreditCard(boolean value) {
        this.acceptCreditCard = value;
    }

    /**
     * Gets the value of the acceptBankAccount property.
     * 
     */
    public boolean isAcceptBankAccount() {
        return acceptBankAccount;
    }

    /**
     * Sets the value of the acceptBankAccount property.
     * 
     */
    public void setAcceptBankAccount(boolean value) {
        this.acceptBankAccount = value;
    }

    /**
     * Gets the value of the allowWebEnrollment property.
     * 
     */
    public boolean isAllowWebEnrollment() {
        return allowWebEnrollment;
    }

    /**
     * Sets the value of the allowWebEnrollment property.
     * 
     */
    public void setAllowWebEnrollment(boolean value) {
        this.allowWebEnrollment = value;
    }

}
