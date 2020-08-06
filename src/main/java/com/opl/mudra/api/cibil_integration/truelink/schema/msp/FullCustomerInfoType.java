
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.SecretQuestionType;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for FullCustomerInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FullCustomerInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MinimalCustomerInfo" type="{com/truelink/schema/msp}MinimalCustomerInfoType"/>
 *         &lt;element name="LockStatus">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="IsLocked" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="Locks" type="{com/truelink/schema/msp}LockInfoType" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="IdentityVerifcationStatus">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="IsIdentityVerified" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="HasAttemptedIdentityConfirmation" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Credential" type="{com/truelink/schema/msp}CredentialType" minOccurs="0"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Addresses" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Address" type="{com/truelink/schema/msp}AddressType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="CreditCards" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CreditCard" type="{com/truelink/schema/msp}CreditCardType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="SecretQuestionAndAnswer" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Question" type="{com/truelink/schema/database/tcps/enumerations}SecretQuestionType"/>
 *                   &lt;element name="Answer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Offers" type="{com/truelink/schema/msp}OffersType" minOccurs="0"/>
 *         &lt;element name="Assets" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Asset" type="{com/truelink/schema/msp}AssetType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="LastLoginDate" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FullCustomerInfoType", propOrder = {
    "minimalCustomerInfo",
    "lockStatus",
    "identityVerifcationStatus",
    "credential",
    "email",
    "addresses",
    "creditCards",
    "secretQuestionAndAnswer",
    "offers",
    "assets",
    "lastLoginDate"
})
public class FullCustomerInfoType {

    @XmlElement(name = "MinimalCustomerInfo", required = true)
    protected MinimalCustomerInfoType minimalCustomerInfo;
    @XmlElement(name = "LockStatus", required = true)
    protected FullCustomerInfoType.LockStatus lockStatus;
    @XmlElement(name = "IdentityVerifcationStatus", required = true)
    protected FullCustomerInfoType.IdentityVerifcationStatus identityVerifcationStatus;
    @XmlElement(name = "Credential")
    protected CredentialType credential;
    @XmlElement(name = "Email")
    protected String email;
    @XmlElement(name = "Addresses")
    protected FullCustomerInfoType.Addresses addresses;
    @XmlElement(name = "CreditCards")
    protected FullCustomerInfoType.CreditCards creditCards;
    @XmlElement(name = "SecretQuestionAndAnswer")
    protected FullCustomerInfoType.SecretQuestionAndAnswer secretQuestionAndAnswer;
    @XmlElement(name = "Offers")
    protected OffersType offers;
    @XmlElement(name = "Assets")
    protected FullCustomerInfoType.Assets assets;
    @XmlElement(name = "LastLoginDate")
    protected Object lastLoginDate;

    /**
     * Gets the value of the minimalCustomerInfo property.
     *
     * @return
     *     possible object is
     *     {@link MinimalCustomerInfoType }
     *
     */
    public MinimalCustomerInfoType getMinimalCustomerInfo() {
        return minimalCustomerInfo;
    }

    /**
     * Sets the value of the minimalCustomerInfo property.
     *
     * @param value
     *     allowed object is
     *     {@link MinimalCustomerInfoType }
     *
     */
    public void setMinimalCustomerInfo(MinimalCustomerInfoType value) {
        this.minimalCustomerInfo = value;
    }

    /**
     * Gets the value of the lockStatus property.
     *
     * @return
     *     possible object is
     *     {@link FullCustomerInfoType.LockStatus }
     *
     */
    public FullCustomerInfoType.LockStatus getLockStatus() {
        return lockStatus;
    }

    /**
     * Sets the value of the lockStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link FullCustomerInfoType.LockStatus }
     *
     */
    public void setLockStatus(FullCustomerInfoType.LockStatus value) {
        this.lockStatus = value;
    }

    /**
     * Gets the value of the identityVerifcationStatus property.
     *
     * @return
     *     possible object is
     *     {@link FullCustomerInfoType.IdentityVerifcationStatus }
     *
     */
    public FullCustomerInfoType.IdentityVerifcationStatus getIdentityVerifcationStatus() {
        return identityVerifcationStatus;
    }

    /**
     * Sets the value of the identityVerifcationStatus property.
     *
     * @param value
     *     allowed object is
     *     {@link FullCustomerInfoType.IdentityVerifcationStatus }
     *
     */
    public void setIdentityVerifcationStatus(FullCustomerInfoType.IdentityVerifcationStatus value) {
        this.identityVerifcationStatus = value;
    }

    /**
     * Gets the value of the credential property.
     *
     * @return
     *     possible object is
     *     {@link CredentialType }
     *
     */
    public CredentialType getCredential() {
        return credential;
    }

    /**
     * Sets the value of the credential property.
     *
     * @param value
     *     allowed object is
     *     {@link CredentialType }
     *
     */
    public void setCredential(CredentialType value) {
        this.credential = value;
    }

    /**
     * Gets the value of the email property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the addresses property.
     *
     * @return
     *     possible object is
     *     {@link FullCustomerInfoType.Addresses }
     *
     */
    public FullCustomerInfoType.Addresses getAddresses() {
        return addresses;
    }

    /**
     * Sets the value of the addresses property.
     *
     * @param value
     *     allowed object is
     *     {@link FullCustomerInfoType.Addresses }
     *
     */
    public void setAddresses(FullCustomerInfoType.Addresses value) {
        this.addresses = value;
    }

    /**
     * Gets the value of the creditCards property.
     *
     * @return
     *     possible object is
     *     {@link FullCustomerInfoType.CreditCards }
     *
     */
    public FullCustomerInfoType.CreditCards getCreditCards() {
        return creditCards;
    }

    /**
     * Sets the value of the creditCards property.
     *
     * @param value
     *     allowed object is
     *     {@link FullCustomerInfoType.CreditCards }
     *
     */
    public void setCreditCards(FullCustomerInfoType.CreditCards value) {
        this.creditCards = value;
    }

    /**
     * Gets the value of the secretQuestionAndAnswer property.
     *
     * @return
     *     possible object is
     *     {@link FullCustomerInfoType.SecretQuestionAndAnswer }
     *
     */
    public FullCustomerInfoType.SecretQuestionAndAnswer getSecretQuestionAndAnswer() {
        return secretQuestionAndAnswer;
    }

    /**
     * Sets the value of the secretQuestionAndAnswer property.
     *
     * @param value
     *     allowed object is
     *     {@link FullCustomerInfoType.SecretQuestionAndAnswer }
     *
     */
    public void setSecretQuestionAndAnswer(FullCustomerInfoType.SecretQuestionAndAnswer value) {
        this.secretQuestionAndAnswer = value;
    }

    /**
     * Gets the value of the offers property.
     *
     * @return
     *     possible object is
     *     {@link OffersType }
     *
     */
    public OffersType getOffers() {
        return offers;
    }

    /**
     * Sets the value of the offers property.
     *
     * @param value
     *     allowed object is
     *     {@link OffersType }
     *
     */
    public void setOffers(OffersType value) {
        this.offers = value;
    }

    /**
     * Gets the value of the assets property.
     *
     * @return
     *     possible object is
     *     {@link FullCustomerInfoType.Assets }
     *
     */
    public FullCustomerInfoType.Assets getAssets() {
        return assets;
    }

    /**
     * Sets the value of the assets property.
     *
     * @param value
     *     allowed object is
     *     {@link FullCustomerInfoType.Assets }
     *
     */
    public void setAssets(FullCustomerInfoType.Assets value) {
        this.assets = value;
    }

    /**
     * Gets the value of the lastLoginDate property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * Sets the value of the lastLoginDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setLastLoginDate(Object value) {
        this.lastLoginDate = value;
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
     *         &lt;element name="Address" type="{com/truelink/schema/msp}AddressType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "address"
    })
    public static class Addresses {

        @XmlElement(name = "Address", required = true)
        protected List<AddressType> address;

        /**
         * Gets the value of the address property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the address property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAddress().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AddressType }
         * 
         * 
         */
        public List<AddressType> getAddress() {
            if (address == null) {
                address = new ArrayList<AddressType>();
            }
            return this.address;
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
     *         &lt;element name="Asset" type="{com/truelink/schema/msp}AssetType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "asset"
    })
    public static class Assets {

        @XmlElement(name = "Asset", required = true)
        protected List<AssetType> asset;

        /**
         * Gets the value of the asset property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the asset property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAsset().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AssetType }
         * 
         * 
         */
        public List<AssetType> getAsset() {
            if (asset == null) {
                asset = new ArrayList<AssetType>();
            }
            return this.asset;
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
     *         &lt;element name="CreditCard" type="{com/truelink/schema/msp}CreditCardType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "creditCard"
    })
    public static class CreditCards {

        @XmlElement(name = "CreditCard", required = true)
        protected List<CreditCardType> creditCard;

        /**
         * Gets the value of the creditCard property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the creditCard property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCreditCard().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CreditCardType }
         * 
         * 
         */
        public List<CreditCardType> getCreditCard() {
            if (creditCard == null) {
                creditCard = new ArrayList<CreditCardType>();
            }
            return this.creditCard;
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
     *         &lt;element name="IsIdentityVerified" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="HasAttemptedIdentityConfirmation" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "isIdentityVerified",
        "hasAttemptedIdentityConfirmation"
    })
    public static class IdentityVerifcationStatus {

        @XmlElement(name = "IsIdentityVerified")
        protected boolean isIdentityVerified;
        @XmlElement(name = "HasAttemptedIdentityConfirmation")
        protected boolean hasAttemptedIdentityConfirmation;

        /**
         * Gets the value of the isIdentityVerified property.
         * 
         */
        public boolean isIsIdentityVerified() {
            return isIdentityVerified;
        }

        /**
         * Sets the value of the isIdentityVerified property.
         * 
         */
        public void setIsIdentityVerified(boolean value) {
            this.isIdentityVerified = value;
        }

        /**
         * Gets the value of the hasAttemptedIdentityConfirmation property.
         * 
         */
        public boolean isHasAttemptedIdentityConfirmation() {
            return hasAttemptedIdentityConfirmation;
        }

        /**
         * Sets the value of the hasAttemptedIdentityConfirmation property.
         * 
         */
        public void setHasAttemptedIdentityConfirmation(boolean value) {
            this.hasAttemptedIdentityConfirmation = value;
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
     *         &lt;element name="IsLocked" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="Locks" type="{com/truelink/schema/msp}LockInfoType" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "isLocked",
        "locks"
    })
    public static class LockStatus {

        @XmlElement(name = "IsLocked")
        protected boolean isLocked;
        @XmlElement(name = "Locks")
        protected List<LockInfoType> locks;

        /**
         * Gets the value of the isLocked property.
         * 
         */
        public boolean isIsLocked() {
            return isLocked;
        }

        /**
         * Sets the value of the isLocked property.
         * 
         */
        public void setIsLocked(boolean value) {
            this.isLocked = value;
        }

        /**
         * Gets the value of the locks property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the locks property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getLocks().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link LockInfoType }
         * 
         * 
         */
        public List<LockInfoType> getLocks() {
            if (locks == null) {
                locks = new ArrayList<LockInfoType>();
            }
            return this.locks;
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
     *         &lt;element name="Question" type="{com/truelink/schema/database/tcps/enumerations}SecretQuestionType"/>
     *         &lt;element name="Answer" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "question",
        "answer"
    })
    public static class SecretQuestionAndAnswer {

        @XmlElement(name = "Question", required = true)
        @XmlSchemaType(name = "string")
        protected SecretQuestionType question;
        @XmlElement(name = "Answer", required = true)
        protected String answer;

        /**
         * Gets the value of the question property.
         * 
         * @return
         *     possible object is
         *     {@link SecretQuestionType }
         *     
         */
        public SecretQuestionType getQuestion() {
            return question;
        }

        /**
         * Sets the value of the question property.
         * 
         * @param value
         *     allowed object is
         *     {@link SecretQuestionType }
         *     
         */
        public void setQuestion(SecretQuestionType value) {
            this.question = value;
        }

        /**
         * Gets the value of the answer property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAnswer() {
            return answer;
        }

        /**
         * Sets the value of the answer property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAnswer(String value) {
            this.answer = value;
        }

    }

}
