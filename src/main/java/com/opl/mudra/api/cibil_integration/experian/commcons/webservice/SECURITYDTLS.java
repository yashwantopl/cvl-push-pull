
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SECURITYDTLS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SECURITYDTLS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SECURED_AM" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CurrencyCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SEC_TYPE_CD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SEC_CLASS_CD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VALUATION_DT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SECURITYDTLS", propOrder = {
    "segmentCode",
    "securedam",
    "currencyCd",
    "sectypecd",
    "secclasscd",
    "valuationdt"
})
public class SECURITYDTLS {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "SECURED_AM")
    protected String securedam;
    @XmlElement(name = "CurrencyCd")
    protected String currencyCd;
    @XmlElement(name = "SEC_TYPE_CD")
    protected String sectypecd;
    @XmlElement(name = "SEC_CLASS_CD")
    protected String secclasscd;
    @XmlElement(name = "VALUATION_DT")
    protected String valuationdt;

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
     * Gets the value of the securedam property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSECUREDAM() {
        return securedam;
    }

    /**
     * Sets the value of the securedam property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSECUREDAM(String value) {
        this.securedam = value;
    }

    /**
     * Gets the value of the currencyCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCd() {
        return currencyCd;
    }

    /**
     * Sets the value of the currencyCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCd(String value) {
        this.currencyCd = value;
    }

    /**
     * Gets the value of the sectypecd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSECTYPECD() {
        return sectypecd;
    }

    /**
     * Sets the value of the sectypecd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSECTYPECD(String value) {
        this.sectypecd = value;
    }

    /**
     * Gets the value of the secclasscd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSECCLASSCD() {
        return secclasscd;
    }

    /**
     * Sets the value of the secclasscd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSECCLASSCD(String value) {
        this.secclasscd = value;
    }

    /**
     * Gets the value of the valuationdt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVALUATIONDT() {
        return valuationdt;
    }

    /**
     * Sets the value of the valuationdt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVALUATIONDT(String value) {
        this.valuationdt = value;
    }

}
