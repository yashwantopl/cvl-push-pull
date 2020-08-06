
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ACTTYPEDPDBALDerivativesNonSelf complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ACTTYPEDPDBALDerivativesNonSelf">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountTypeNonSelf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DPDDerivativesNonSelf" type="{http://webservice.commcons.experian.com}DPDDerivativesNonSelf" minOccurs="0"/>
 *         &lt;element name="BALDerivativesNonSelf" type="{http://webservice.commcons.experian.com}BALDerivativesNonSelf" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ACTTYPEDPDBALDerivativesNonSelf", propOrder = {
    "segmentCode",
    "accountTypeNonSelf",
    "dpdDerivativesNonSelf",
    "balDerivativesNonSelf"
})
public class ACTTYPEDPDBALDerivativesNonSelf {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "AccountTypeNonSelf")
    protected String accountTypeNonSelf;
    @XmlElement(name = "DPDDerivativesNonSelf")
    protected DPDDerivativesNonSelf dpdDerivativesNonSelf;
    @XmlElement(name = "BALDerivativesNonSelf")
    protected BALDerivativesNonSelf balDerivativesNonSelf;

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
     * Gets the value of the accountTypeNonSelf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountTypeNonSelf() {
        return accountTypeNonSelf;
    }

    /**
     * Sets the value of the accountTypeNonSelf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountTypeNonSelf(String value) {
        this.accountTypeNonSelf = value;
    }

    /**
     * Gets the value of the dpdDerivativesNonSelf property.
     * 
     * @return
     *     possible object is
     *     {@link DPDDerivativesNonSelf }
     *     
     */
    public DPDDerivativesNonSelf getDPDDerivativesNonSelf() {
        return dpdDerivativesNonSelf;
    }

    /**
     * Sets the value of the dpdDerivativesNonSelf property.
     * 
     * @param value
     *     allowed object is
     *     {@link DPDDerivativesNonSelf }
     *     
     */
    public void setDPDDerivativesNonSelf(DPDDerivativesNonSelf value) {
        this.dpdDerivativesNonSelf = value;
    }

    /**
     * Gets the value of the balDerivativesNonSelf property.
     * 
     * @return
     *     possible object is
     *     {@link BALDerivativesNonSelf }
     *     
     */
    public BALDerivativesNonSelf getBALDerivativesNonSelf() {
        return balDerivativesNonSelf;
    }

    /**
     * Sets the value of the balDerivativesNonSelf property.
     * 
     * @param value
     *     allowed object is
     *     {@link BALDerivativesNonSelf }
     *     
     */
    public void setBALDerivativesNonSelf(BALDerivativesNonSelf value) {
        this.balDerivativesNonSelf = value;
    }

}
