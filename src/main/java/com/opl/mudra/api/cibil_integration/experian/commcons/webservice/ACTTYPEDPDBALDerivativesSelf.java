
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ACTTYPEDPDBALDerivativesSelf complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ACTTYPEDPDBALDerivativesSelf">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountTypeSelf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DPDDerivativesSelf" type="{http://webservice.commcons.experian.com}DPDDerivativesSelf" minOccurs="0"/>
 *         &lt;element name="BALDerivativesSelf" type="{http://webservice.commcons.experian.com}BALDerivativesSelf" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ACTTYPEDPDBALDerivativesSelf", propOrder = {
    "segmentCode",
    "accountTypeSelf",
    "dpdDerivativesSelf",
    "balDerivativesSelf"
})
public class ACTTYPEDPDBALDerivativesSelf {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "AccountTypeSelf")
    protected String accountTypeSelf;
    @XmlElement(name = "DPDDerivativesSelf")
    protected DPDDerivativesSelf dpdDerivativesSelf;
    @XmlElement(name = "BALDerivativesSelf")
    protected BALDerivativesSelf balDerivativesSelf;

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
     * Gets the value of the accountTypeSelf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountTypeSelf() {
        return accountTypeSelf;
    }

    /**
     * Sets the value of the accountTypeSelf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountTypeSelf(String value) {
        this.accountTypeSelf = value;
    }

    /**
     * Gets the value of the dpdDerivativesSelf property.
     * 
     * @return
     *     possible object is
     *     {@link DPDDerivativesSelf }
     *     
     */
    public DPDDerivativesSelf getDPDDerivativesSelf() {
        return dpdDerivativesSelf;
    }

    /**
     * Sets the value of the dpdDerivativesSelf property.
     * 
     * @param value
     *     allowed object is
     *     {@link DPDDerivativesSelf }
     *     
     */
    public void setDPDDerivativesSelf(DPDDerivativesSelf value) {
        this.dpdDerivativesSelf = value;
    }

    /**
     * Gets the value of the balDerivativesSelf property.
     * 
     * @return
     *     possible object is
     *     {@link BALDerivativesSelf }
     *     
     */
    public BALDerivativesSelf getBALDerivativesSelf() {
        return balDerivativesSelf;
    }

    /**
     * Sets the value of the balDerivativesSelf property.
     * 
     * @param value
     *     allowed object is
     *     {@link BALDerivativesSelf }
     *     
     */
    public void setBALDerivativesSelf(BALDerivativesSelf value) {
        this.balDerivativesSelf = value;
    }

}
