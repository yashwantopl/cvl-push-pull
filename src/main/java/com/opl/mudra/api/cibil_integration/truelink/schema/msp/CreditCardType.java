
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CreditCardType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreditCardType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CreditCardNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EncryptionIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="CardHolderName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ExpirationMonth" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ExpirationYear" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="BillingAddress" type="{com/truelink/schema/msp}AddressType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditCardType", propOrder = {
    "creditCardNumber",
    "encryptionIndicator",
    "cardHolderName",
    "expirationMonth",
    "expirationYear",
    "billingAddress"
})
public class CreditCardType {

    @XmlElement(name = "CreditCardNumber", required = true)
    protected String creditCardNumber;
    @XmlElement(name = "EncryptionIndicator")
    protected boolean encryptionIndicator;
    @XmlElement(name = "CardHolderName", required = true)
    protected String cardHolderName;
    @XmlElement(name = "ExpirationMonth")
    protected int expirationMonth;
    @XmlElement(name = "ExpirationYear")
    protected int expirationYear;
    @XmlElement(name = "BillingAddress", required = true)
    protected AddressType billingAddress;

    /**
     * Gets the value of the creditCardNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    /**
     * Sets the value of the creditCardNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditCardNumber(String value) {
        this.creditCardNumber = value;
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

    /**
     * Gets the value of the cardHolderName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCardHolderName() {
        return cardHolderName;
    }

    /**
     * Sets the value of the cardHolderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCardHolderName(String value) {
        this.cardHolderName = value;
    }

    /**
     * Gets the value of the expirationMonth property.
     * 
     */
    public int getExpirationMonth() {
        return expirationMonth;
    }

    /**
     * Sets the value of the expirationMonth property.
     * 
     */
    public void setExpirationMonth(int value) {
        this.expirationMonth = value;
    }

    /**
     * Gets the value of the expirationYear property.
     * 
     */
    public int getExpirationYear() {
        return expirationYear;
    }

    /**
     * Sets the value of the expirationYear property.
     * 
     */
    public void setExpirationYear(int value) {
        this.expirationYear = value;
    }

    /**
     * Gets the value of the billingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getBillingAddress() {
        return billingAddress;
    }

    /**
     * Sets the value of the billingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     */
    public void setBillingAddress(AddressType value) {
        this.billingAddress = value;
    }

}
