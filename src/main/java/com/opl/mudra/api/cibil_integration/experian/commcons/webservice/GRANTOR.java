
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GRANTOR complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GRANTOR">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalCreditProviders" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CurrentCreditProviders" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalSameCreditProviders" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalOtherCreditProviders" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GRANTOR", propOrder = {
    "segmentCode",
    "totalCreditProviders",
    "currentCreditProviders",
    "totalSameCreditProviders",
    "totalOtherCreditProviders"
})
public class GRANTOR {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "TotalCreditProviders")
    protected String totalCreditProviders;
    @XmlElement(name = "CurrentCreditProviders")
    protected String currentCreditProviders;
    @XmlElement(name = "TotalSameCreditProviders")
    protected String totalSameCreditProviders;
    @XmlElement(name = "TotalOtherCreditProviders")
    protected String totalOtherCreditProviders;

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
     * Gets the value of the totalCreditProviders property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalCreditProviders() {
        return totalCreditProviders;
    }

    /**
     * Sets the value of the totalCreditProviders property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalCreditProviders(String value) {
        this.totalCreditProviders = value;
    }

    /**
     * Gets the value of the currentCreditProviders property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentCreditProviders() {
        return currentCreditProviders;
    }

    /**
     * Sets the value of the currentCreditProviders property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentCreditProviders(String value) {
        this.currentCreditProviders = value;
    }

    /**
     * Gets the value of the totalSameCreditProviders property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalSameCreditProviders() {
        return totalSameCreditProviders;
    }

    /**
     * Sets the value of the totalSameCreditProviders property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalSameCreditProviders(String value) {
        this.totalSameCreditProviders = value;
    }

    /**
     * Gets the value of the totalOtherCreditProviders property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalOtherCreditProviders() {
        return totalOtherCreditProviders;
    }

    /**
     * Sets the value of the totalOtherCreditProviders property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalOtherCreditProviders(String value) {
        this.totalOtherCreditProviders = value;
    }

}
