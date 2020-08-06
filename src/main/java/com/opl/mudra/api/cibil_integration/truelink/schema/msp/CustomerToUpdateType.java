
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for CustomerToUpdateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerToUpdateType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CustomerId" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="PartnerCustomerId" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="FullNameInfo" type="{com/truelink/schema/msp}FullNameInfoType" minOccurs="0"/>
 *         &lt;element name="Address" type="{com/truelink/schema/msp}AddressType" minOccurs="0"/>
 *         &lt;element name="PhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExpirationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="NewPartnerCustomerId" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/schema/database/tcps/enumerations}CustomerStatus" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerToUpdateType", propOrder = {
    "customerId",
    "partnerCustomerId",
    "fullNameInfo",
    "address",
    "phoneNumber",
    "expirationDate",
    "newPartnerCustomerId",
    "customerStatus"
})
public class CustomerToUpdateType {

    @XmlElement(name = "CustomerId")
    protected Object customerId;
    @XmlElement(name = "PartnerCustomerId")
    protected Object partnerCustomerId;
    @XmlElement(name = "FullNameInfo")
    protected FullNameInfoType fullNameInfo;
    @XmlElement(name = "Address")
    protected AddressType address;
    @XmlElement(name = "PhoneNumber")
    protected String phoneNumber;
    @XmlElement(name = "ExpirationDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expirationDate;
    @XmlElement(name = "NewPartnerCustomerId")
    protected Object newPartnerCustomerId;
    @XmlElement(name = "CustomerStatus", namespace = "com/truelink/schema/database/tcps/enumerations")
    @XmlSchemaType(name = "string")
    protected CustomerStatusType customerStatus;

    /**
     * Gets the value of the customerId property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getCustomerId() {
        return customerId;
    }

    /**
     * Sets the value of the customerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setCustomerId(Object value) {
        this.customerId = value;
    }

    /**
     * Gets the value of the partnerCustomerId property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getPartnerCustomerId() {
        return partnerCustomerId;
    }

    /**
     * Sets the value of the partnerCustomerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setPartnerCustomerId(Object value) {
        this.partnerCustomerId = value;
    }

    /**
     * Gets the value of the fullNameInfo property.
     * 
     * @return
     *     possible object is
     *     {@link FullNameInfoType }
     *     
     */
    public FullNameInfoType getFullNameInfo() {
        return fullNameInfo;
    }

    /**
     * Sets the value of the fullNameInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link FullNameInfoType }
     *     
     */
    public void setFullNameInfo(FullNameInfoType value) {
        this.fullNameInfo = value;
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
     * Gets the value of the phoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * Gets the value of the expirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the value of the expirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpirationDate(XMLGregorianCalendar value) {
        this.expirationDate = value;
    }

    /**
     * Gets the value of the newPartnerCustomerId property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getNewPartnerCustomerId() {
        return newPartnerCustomerId;
    }

    /**
     * Sets the value of the newPartnerCustomerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setNewPartnerCustomerId(Object value) {
        this.newPartnerCustomerId = value;
    }

    /**
     * Gets the value of the customerStatus property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerStatusType }
     *     
     */
    public CustomerStatusType getCustomerStatus() {
        return customerStatus;
    }

    /**
     * Sets the value of the customerStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerStatusType }
     *     
     */
    public void setCustomerStatus(CustomerStatusType value) {
        this.customerStatus = value;
    }

}
