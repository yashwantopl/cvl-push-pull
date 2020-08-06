
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.BankAcctType;


/**
 * <p>Java class for BillingMethodType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BillingMethodType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AccountHolderName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BillingAddress" type="{com/truelink/schema/msp}AddressType"/>
 *         &lt;element name="EncryptionType" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="MemberId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CreditCard" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                 &lt;/sequence>
 *                 &lt;attribute name="CreditCardNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="CreditCardType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="CreditCardExpirationDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="BankAccount" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                 &lt;/sequence>
 *                 &lt;attribute name="AccountNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="RoutingNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                 &lt;attribute name="AccountType" use="required" type="{com/truelink/schema/database/tcps/enumerations}BankAcctType" />
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillingMethodType", propOrder = {
    "accountHolderName",
    "billingAddress",
    "encryptionType",
    "memberId",
    "creditCard",
    "bankAccount"
})
public class BillingMethodType {

    @XmlElement(name = "AccountHolderName", required = true)
    protected String accountHolderName;
    @XmlElement(name = "BillingAddress", required = true)
    protected AddressType billingAddress;
    @XmlElement(name = "EncryptionType")
    protected long encryptionType;
    @XmlElement(name = "MemberId")
    protected long memberId;
    @XmlElement(name = "CreditCard")
    protected BillingMethodType.CreditCard creditCard;
    @XmlElement(name = "BankAccount")
    protected BillingMethodType.BankAccount bankAccount;

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

    /**
     * Gets the value of the encryptionType property.
     *
     */
    public long getEncryptionType() {
        return encryptionType;
    }

    /**
     * Sets the value of the encryptionType property.
     *
     */
    public void setEncryptionType(long value) {
        this.encryptionType = value;
    }

    /**
     * Gets the value of the memberId property.
     *
     */
    public long getMemberId() {
        return memberId;
    }

    /**
     * Sets the value of the memberId property.
     *
     */
    public void setMemberId(long value) {
        this.memberId = value;
    }

    /**
     * Gets the value of the creditCard property.
     *
     * @return
     *     possible object is
     *     {@link BillingMethodType.CreditCard }
     *
     */
    public BillingMethodType.CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * Sets the value of the creditCard property.
     *
     * @param value
     *     allowed object is
     *     {@link BillingMethodType.CreditCard }
     *
     */
    public void setCreditCard(BillingMethodType.CreditCard value) {
        this.creditCard = value;
    }

    /**
     * Gets the value of the bankAccount property.
     *
     * @return
     *     possible object is
     *     {@link BillingMethodType.BankAccount }
     *
     */
    public BillingMethodType.BankAccount getBankAccount() {
        return bankAccount;
    }

    /**
     * Sets the value of the bankAccount property.
     *
     * @param value
     *     allowed object is
     *     {@link BillingMethodType.BankAccount }
     *
     */
    public void setBankAccount(BillingMethodType.BankAccount value) {
        this.bankAccount = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *       &lt;/sequence>
     *       &lt;attribute name="AccountNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="RoutingNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="AccountType" use="required" type="{com/truelink/schema/database/tcps/enumerations}BankAcctType" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class BankAccount {

        @XmlAttribute(name = "AccountNumber", required = true)
        protected String accountNumber;
        @XmlAttribute(name = "RoutingNumber", required = true)
        protected String routingNumber;
        @XmlAttribute(name = "AccountType", required = true)
        protected BankAcctType accountType;

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
         * Gets the value of the accountType property.
         * 
         * @return
         *     possible object is
         *     {@link BankAcctType }
         *     
         */
        public BankAcctType getAccountType() {
            return accountType;
        }

        /**
         * Sets the value of the accountType property.
         * 
         * @param value
         *     allowed object is
         *     {@link BankAcctType }
         *     
         */
        public void setAccountType(BankAcctType value) {
            this.accountType = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *       &lt;/sequence>
     *       &lt;attribute name="CreditCardNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="CreditCardType" type="{http://www.w3.org/2001/XMLSchema}string" />
     *       &lt;attribute name="CreditCardExpirationDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class CreditCard {

        @XmlAttribute(name = "CreditCardNumber", required = true)
        protected String creditCardNumber;
        @XmlAttribute(name = "CreditCardType")
        protected String creditCardType;
        @XmlAttribute(name = "CreditCardExpirationDate", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar creditCardExpirationDate;

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
         * Gets the value of the creditCardType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCreditCardType() {
            return creditCardType;
        }

        /**
         * Sets the value of the creditCardType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCreditCardType(String value) {
            this.creditCardType = value;
        }

        /**
         * Gets the value of the creditCardExpirationDate property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getCreditCardExpirationDate() {
            return creditCardExpirationDate;
        }

        /**
         * Sets the value of the creditCardExpirationDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setCreditCardExpirationDate(XMLGregorianCalendar value) {
            this.creditCardExpirationDate = value;
        }

    }

}
