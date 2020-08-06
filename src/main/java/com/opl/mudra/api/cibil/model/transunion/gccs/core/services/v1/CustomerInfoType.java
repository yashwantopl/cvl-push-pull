
package com.opl.mudra.api.cibil.model.transunion.gccs.core.services.v1;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.opl.mudra.api.cibil.model.truelink.ds.sch.pii.v1.AddressType;
import com.opl.mudra.api.cibil.model.truelink.ds.sch.pii.v1.FullNameType;
import com.opl.mudra.api.cibil.model.truelink.ds.sch.pii.v1.GenderEnum;
import com.opl.mudra.api.cibil.model.truelink.ds.sch.pii.v1.IdentifierType;
import com.opl.mudra.api.cibil.model.truelink.ds.sch.pii.v1.PhoneNumberType;


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
 *         &lt;element name="Name" type="{com/truelink/ds/sch/pii/v1}FullNameType"/>
 *         &lt;element name="IdentificationNumber" type="{com/truelink/ds/sch/pii/v1}IdentifierType" maxOccurs="unbounded"/>
 *         &lt;element name="Address" type="{com/truelink/ds/sch/pii/v1}AddressType"/>
 *         &lt;element name="PreviousAddress" type="{com/truelink/ds/sch/pii/v1}AddressType" minOccurs="0"/>
 *         &lt;element name="DateOfBirth" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="PhoneNumber" type="{com/truelink/ds/sch/pii/v1}PhoneNumberType" minOccurs="0"/>
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Gender" type="{com/truelink/ds/sch/pii/v1}GenderEnum"/>
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
    "name",
    "identificationNumber",
    "address",
    "previousAddress",
    "dateOfBirth",
    "phoneNumber",
    "email",
    "gender"
})
@XmlSeeAlso({
    com.opl.mudra.api.cibil.model.transunion.gccs.core.services.v1.FulfillOfferRequest.CustomerInfo.class
})
public class CustomerInfoType {

    @XmlElement(name = "Name", required = true)
    protected FullNameType name;
    @XmlElement(name = "IdentificationNumber", required = true)
    protected List<IdentifierType> identificationNumber;
    @XmlElement(name = "Address", required = true)
    protected AddressType address;
    @XmlElement(name = "PreviousAddress")
    protected AddressType previousAddress;
    @XmlElement(name = "DateOfBirth")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateOfBirth;
    @XmlElement(name = "PhoneNumber")
    protected PhoneNumberType phoneNumber;
    @XmlElement(name = "Email")
    protected String email;
    @XmlElement(name = "Gender", required = true)
    @XmlSchemaType(name = "string")
    protected GenderEnum gender;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link FullNameType }
     *     
     */
    public FullNameType getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link FullNameType }
     *     
     */
    public void setName(FullNameType value) {
        this.name = value;
    }

    /**
     * Gets the value of the identificationNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the identificationNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIdentificationNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IdentifierType }
     * 
     * 
     */
    public List<IdentifierType> getIdentificationNumber() {
        if (identificationNumber == null) {
            identificationNumber = new ArrayList<IdentifierType>();
        }
        return this.identificationNumber;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     */
    public void setAddress(AddressType value) {
        this.address = value;
    }

    /**
     * Gets the value of the previousAddress property.
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getPreviousAddress() {
        return previousAddress;
    }

    /**
     * Sets the value of the previousAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     */
    public void setPreviousAddress(AddressType value) {
        this.previousAddress = value;
    }

    /**
     * Gets the value of the dateOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the value of the dateOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateOfBirth(XMLGregorianCalendar value) {
        this.dateOfBirth = value;
    }

    /**
     * Gets the value of the phoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link PhoneNumberType }
     *     
     */
    public PhoneNumberType getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link PhoneNumberType }
     *     
     */
    public void setPhoneNumber(PhoneNumberType value) {
        this.phoneNumber = value;
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
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link GenderEnum }
     *     
     */
    public GenderEnum getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenderEnum }
     *     
     */
    public void setGender(GenderEnum value) {
        this.gender = value;
    }

}
