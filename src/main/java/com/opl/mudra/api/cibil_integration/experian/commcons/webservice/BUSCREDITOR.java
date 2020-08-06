
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BUSCREDITOR complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BUSCREDITOR">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreditorIndustryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreditorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BUSCREDITOR", propOrder = {
    "segmentCode",
    "creditorIndustryCd",
    "creditorName"
})
public class BUSCREDITOR {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "CreditorIndustryCd")
    protected String creditorIndustryCd;
    @XmlElement(name = "CreditorName")
    protected String creditorName;

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
     * Gets the value of the creditorIndustryCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditorIndustryCd() {
        return creditorIndustryCd;
    }

    /**
     * Sets the value of the creditorIndustryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditorIndustryCd(String value) {
        this.creditorIndustryCd = value;
    }

    /**
     * Gets the value of the creditorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditorName() {
        return creditorName;
    }

    /**
     * Sets the value of the creditorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditorName(String value) {
        this.creditorName = value;
    }

}
