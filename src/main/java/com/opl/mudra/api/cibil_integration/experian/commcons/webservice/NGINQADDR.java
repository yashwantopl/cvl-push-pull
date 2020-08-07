
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NGINQADDR complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NGINQADDR">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AddrType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InqCompanyAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InqCompanyCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InqCompanyPINCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CountryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NGINQADDR", propOrder = {
    "segmentCode",
    "addrType",
    "inqCompanyAddress",
    "inqCompanyCity",
    "inqCompanyPINCode",
    "countryCode"
})
public class NGINQADDR {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "AddrType")
    protected String addrType;
    @XmlElement(name = "InqCompanyAddress")
    protected String inqCompanyAddress;
    @XmlElement(name = "InqCompanyCity")
    protected String inqCompanyCity;
    @XmlElement(name = "InqCompanyPINCode")
    protected String inqCompanyPINCode;
    @XmlElement(name = "CountryCode")
    protected String countryCode;

    /**
     * Gets the value of the segmentCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentCode() {
        return segmentCode;
    }

    /**
     * Sets the value of the segmentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentCode(String value) {
        this.segmentCode = value;
    }

    /**
     * Gets the value of the addrType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddrType() {
        return addrType;
    }

    /**
     * Sets the value of the addrType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddrType(String value) {
        this.addrType = value;
    }

    /**
     * Gets the value of the inqCompanyAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInqCompanyAddress() {
        return inqCompanyAddress;
    }

    /**
     * Sets the value of the inqCompanyAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInqCompanyAddress(String value) {
        this.inqCompanyAddress = value;
    }

    /**
     * Gets the value of the inqCompanyCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInqCompanyCity() {
        return inqCompanyCity;
    }

    /**
     * Sets the value of the inqCompanyCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInqCompanyCity(String value) {
        this.inqCompanyCity = value;
    }

    /**
     * Gets the value of the inqCompanyPINCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInqCompanyPINCode() {
        return inqCompanyPINCode;
    }

    /**
     * Sets the value of the inqCompanyPINCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInqCompanyPINCode(String value) {
        this.inqCompanyPINCode = value;
    }

    /**
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
    }

}
