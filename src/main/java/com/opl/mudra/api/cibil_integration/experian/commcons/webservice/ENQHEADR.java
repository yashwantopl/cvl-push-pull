
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ENQHEADR complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ENQHEADR">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ClientEnquiryRefNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BureauMemberId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Purpose" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Product" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SearchType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnquiryApplicationType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnquiryAccountType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EnquiryAmtMonetaryType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnquiryAmount" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Frequency" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DurationofAgreement" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ENQHEADR", propOrder = {
    "clientEnquiryRefNumber",
    "bureauMemberId",
    "purpose",
    "product",
    "searchType",
    "enquiryApplicationType",
    "enquiryAccountType",
    "enquiryAmtMonetaryType",
    "enquiryAmount",
    "frequency",
    "durationofAgreement"
})
public class ENQHEADR {

    @XmlElement(name = "ClientEnquiryRefNumber")
    protected String clientEnquiryRefNumber;
    @XmlElement(name = "BureauMemberId")
    protected String bureauMemberId;
    @XmlElement(name = "Purpose")
    protected String purpose;
    @XmlElement(name = "Product")
    protected String product;
    @XmlElement(name = "SearchType")
    protected String searchType;
    @XmlElement(name = "EnquiryApplicationType")
    protected String enquiryApplicationType;
    @XmlElement(name = "EnquiryAccountType", required = true)
    protected String enquiryAccountType;
    @XmlElement(name = "EnquiryAmtMonetaryType")
    protected String enquiryAmtMonetaryType;
    @XmlElement(name = "EnquiryAmount", required = true)
    protected String enquiryAmount;
    @XmlElement(name = "Frequency", required = true)
    protected String frequency;
    @XmlElement(name = "DurationofAgreement", required = true)
    protected String durationofAgreement;

    /**
     * Gets the value of the clientEnquiryRefNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientEnquiryRefNumber() {
        return clientEnquiryRefNumber;
    }

    /**
     * Sets the value of the clientEnquiryRefNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientEnquiryRefNumber(String value) {
        this.clientEnquiryRefNumber = value;
    }

    /**
     * Gets the value of the bureauMemberId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBureauMemberId() {
        return bureauMemberId;
    }

    /**
     * Sets the value of the bureauMemberId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBureauMemberId(String value) {
        this.bureauMemberId = value;
    }

    /**
     * Gets the value of the purpose property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurpose() {
        return purpose;
    }

    /**
     * Sets the value of the purpose property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurpose(String value) {
        this.purpose = value;
    }

    /**
     * Gets the value of the product property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProduct() {
        return product;
    }

    /**
     * Sets the value of the product property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProduct(String value) {
        this.product = value;
    }

    /**
     * Gets the value of the searchType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSearchType() {
        return searchType;
    }

    /**
     * Sets the value of the searchType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSearchType(String value) {
        this.searchType = value;
    }

    /**
     * Gets the value of the enquiryApplicationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnquiryApplicationType() {
        return enquiryApplicationType;
    }

    /**
     * Sets the value of the enquiryApplicationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnquiryApplicationType(String value) {
        this.enquiryApplicationType = value;
    }

    /**
     * Gets the value of the enquiryAccountType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnquiryAccountType() {
        return enquiryAccountType;
    }

    /**
     * Sets the value of the enquiryAccountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnquiryAccountType(String value) {
        this.enquiryAccountType = value;
    }

    /**
     * Gets the value of the enquiryAmtMonetaryType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnquiryAmtMonetaryType() {
        return enquiryAmtMonetaryType;
    }

    /**
     * Sets the value of the enquiryAmtMonetaryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnquiryAmtMonetaryType(String value) {
        this.enquiryAmtMonetaryType = value;
    }

    /**
     * Gets the value of the enquiryAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnquiryAmount() {
        return enquiryAmount;
    }

    /**
     * Sets the value of the enquiryAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnquiryAmount(String value) {
        this.enquiryAmount = value;
    }

    /**
     * Gets the value of the frequency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * Sets the value of the frequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrequency(String value) {
        this.frequency = value;
    }

    /**
     * Gets the value of the durationofAgreement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDurationofAgreement() {
        return durationofAgreement;
    }

    /**
     * Sets the value of the durationofAgreement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDurationofAgreement(String value) {
        this.durationofAgreement = value;
    }

}
