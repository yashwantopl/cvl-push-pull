
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomerInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Address" type="{com/truelink/schema/msp}AddressType" maxOccurs="unbounded"/>
 *         &lt;element name="FullName" type="{com/truelink/schema/msp}FullNameInfoType"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SocialSecurityNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Credential" type="{com/truelink/schema/msp}CredentialType"/>
 *         &lt;element name="BIllingMethod" type="{com/truelink/schema/msp}BillingMethodType"/>
 *         &lt;element name="CustomerEnrollmentInfo" type="{com/truelink/schema/msp}CustomerEnrollmentInfoType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerInfoType", propOrder = {
    "address",
    "fullName",
    "email",
    "socialSecurityNumber",
    "credential",
    "bIllingMethod",
    "customerEnrollmentInfo"
})
public class CustomerInfoType {

    @XmlElement(name = "Address", required = true)
    protected List<AddressType> address;
    @XmlElement(name = "FullName", required = true)
    protected FullNameInfoType fullName;
    @XmlElement(name = "Email", required = true)
    protected String email;
    @XmlElement(name = "SocialSecurityNumber", required = true)
    protected String socialSecurityNumber;
    @XmlElement(name = "Credential", required = true)
    protected CredentialType credential;
    @XmlElement(name = "BIllingMethod", required = true)
    protected BillingMethodType bIllingMethod;
    @XmlElement(name = "CustomerEnrollmentInfo", required = true)
    protected CustomerEnrollmentInfoType customerEnrollmentInfo;

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

    /**
     * Gets the value of the fullName property.
     * 
     * @return
     *     possible object is
     *     {@link FullNameInfoType }
     *     
     */
    public FullNameInfoType getFullName() {
        return fullName;
    }

    /**
     * Sets the value of the fullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link FullNameInfoType }
     *     
     */
    public void setFullName(FullNameInfoType value) {
        this.fullName = value;
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
     * Gets the value of the socialSecurityNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    /**
     * Sets the value of the socialSecurityNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSocialSecurityNumber(String value) {
        this.socialSecurityNumber = value;
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
     * Gets the value of the bIllingMethod property.
     * 
     * @return
     *     possible object is
     *     {@link BillingMethodType }
     *     
     */
    public BillingMethodType getBIllingMethod() {
        return bIllingMethod;
    }

    /**
     * Sets the value of the bIllingMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link BillingMethodType }
     *     
     */
    public void setBIllingMethod(BillingMethodType value) {
        this.bIllingMethod = value;
    }

    /**
     * Gets the value of the customerEnrollmentInfo property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerEnrollmentInfoType }
     *     
     */
    public CustomerEnrollmentInfoType getCustomerEnrollmentInfo() {
        return customerEnrollmentInfo;
    }

    /**
     * Sets the value of the customerEnrollmentInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerEnrollmentInfoType }
     *     
     */
    public void setCustomerEnrollmentInfo(CustomerEnrollmentInfoType value) {
        this.customerEnrollmentInfo = value;
    }

}
