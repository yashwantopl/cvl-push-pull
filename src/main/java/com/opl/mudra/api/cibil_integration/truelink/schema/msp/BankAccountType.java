
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.BankAcctType;


/**
 * <p>Java class for BankAccountType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BankAccountType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="AccountNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="RoutingNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="AccoutType" use="required" type="{com/truelink/schema/database/tcps/enumerations}BankAcctType" />
 *       &lt;attribute name="AccountHolderName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="EncryptionIndicator" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BankAccountType")
public class BankAccountType {

    @XmlAttribute(name = "AccountNumber", required = true)
    protected String accountNumber;
    @XmlAttribute(name = "RoutingNumber", required = true)
    protected String routingNumber;
    @XmlAttribute(name = "AccoutType", required = true)
    protected BankAcctType accoutType;
    @XmlAttribute(name = "AccountHolderName")
    protected String accountHolderName;
    @XmlAttribute(name = "EncryptionIndicator", required = true)
    protected boolean encryptionIndicator;

    /**
     * Gets the value of the accountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the accountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNumber(String value) {
        this.accountNumber = value;
    }

    /**
     * Gets the value of the routingNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoutingNumber() {
        return routingNumber;
    }

    /**
     * Sets the value of the routingNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoutingNumber(String value) {
        this.routingNumber = value;
    }

    /**
     * Gets the value of the accoutType property.
     * 
     * @return
     *     possible object is
     *     {@link BankAcctType }
     *     
     */
    public BankAcctType getAccoutType() {
        return accoutType;
    }

    /**
     * Sets the value of the accoutType property.
     * 
     * @param value
     *     allowed object is
     *     {@link BankAcctType }
     *     
     */
    public void setAccoutType(BankAcctType value) {
        this.accoutType = value;
    }

    /**
     * Gets the value of the accountHolderName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountHolderName() {
        return accountHolderName;
    }

    /**
     * Sets the value of the accountHolderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountHolderName(String value) {
        this.accountHolderName = value;
    }

    /**
     * Gets the value of the encryptionIndicator property.
     * 
     */
    public boolean isEncryptionIndicator() {
        return encryptionIndicator;
    }

    /**
     * Sets the value of the encryptionIndicator property.
     * 
     */
    public void setEncryptionIndicator(boolean value) {
        this.encryptionIndicator = value;
    }

}
