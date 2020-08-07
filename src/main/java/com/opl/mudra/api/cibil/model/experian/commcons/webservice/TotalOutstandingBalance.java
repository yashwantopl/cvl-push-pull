
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TotalOutstandingBalance complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TotalOutstandingBalance">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBalBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBalGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBalSelfBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBalSelfGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBalPSUBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBalPSUGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBalPVTBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBalPVTGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBalNBFCBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBalNBFCGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBalNonSelfBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBalNonSelfGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TotalOutstandingBalance", propOrder = {
    "segmentCode",
    "totBalBorrower",
    "totBalGuarantor",
    "totBalSelfBorrower",
    "totBalSelfGuarantor",
    "totBalPSUBorrower",
    "totBalPSUGuarantor",
    "totBalPVTBorrower",
    "totBalPVTGuarantor",
    "totBalNBFCBorrower",
    "totBalNBFCGuarantor",
    "totBalNonSelfBorrower",
    "totBalNonSelfGuarantor"
})
public class TotalOutstandingBalance {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "TotBalBorrower")
    protected String totBalBorrower;
    @XmlElement(name = "TotBalGuarantor")
    protected String totBalGuarantor;
    @XmlElement(name = "TotBalSelfBorrower")
    protected String totBalSelfBorrower;
    @XmlElement(name = "TotBalSelfGuarantor")
    protected String totBalSelfGuarantor;
    @XmlElement(name = "TotBalPSUBorrower")
    protected String totBalPSUBorrower;
    @XmlElement(name = "TotBalPSUGuarantor")
    protected String totBalPSUGuarantor;
    @XmlElement(name = "TotBalPVTBorrower")
    protected String totBalPVTBorrower;
    @XmlElement(name = "TotBalPVTGuarantor")
    protected String totBalPVTGuarantor;
    @XmlElement(name = "TotBalNBFCBorrower")
    protected String totBalNBFCBorrower;
    @XmlElement(name = "TotBalNBFCGuarantor")
    protected String totBalNBFCGuarantor;
    @XmlElement(name = "TotBalNonSelfBorrower")
    protected String totBalNonSelfBorrower;
    @XmlElement(name = "TotBalNonSelfGuarantor")
    protected String totBalNonSelfGuarantor;

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
     * Gets the value of the totBalBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBalBorrower() {
        return totBalBorrower;
    }

    /**
     * Sets the value of the totBalBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBalBorrower(String value) {
        this.totBalBorrower = value;
    }

    /**
     * Gets the value of the totBalGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBalGuarantor() {
        return totBalGuarantor;
    }

    /**
     * Sets the value of the totBalGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBalGuarantor(String value) {
        this.totBalGuarantor = value;
    }

    /**
     * Gets the value of the totBalSelfBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBalSelfBorrower() {
        return totBalSelfBorrower;
    }

    /**
     * Sets the value of the totBalSelfBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBalSelfBorrower(String value) {
        this.totBalSelfBorrower = value;
    }

    /**
     * Gets the value of the totBalSelfGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBalSelfGuarantor() {
        return totBalSelfGuarantor;
    }

    /**
     * Sets the value of the totBalSelfGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBalSelfGuarantor(String value) {
        this.totBalSelfGuarantor = value;
    }

    /**
     * Gets the value of the totBalPSUBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBalPSUBorrower() {
        return totBalPSUBorrower;
    }

    /**
     * Sets the value of the totBalPSUBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBalPSUBorrower(String value) {
        this.totBalPSUBorrower = value;
    }

    /**
     * Gets the value of the totBalPSUGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBalPSUGuarantor() {
        return totBalPSUGuarantor;
    }

    /**
     * Sets the value of the totBalPSUGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBalPSUGuarantor(String value) {
        this.totBalPSUGuarantor = value;
    }

    /**
     * Gets the value of the totBalPVTBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBalPVTBorrower() {
        return totBalPVTBorrower;
    }

    /**
     * Sets the value of the totBalPVTBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBalPVTBorrower(String value) {
        this.totBalPVTBorrower = value;
    }

    /**
     * Gets the value of the totBalPVTGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBalPVTGuarantor() {
        return totBalPVTGuarantor;
    }

    /**
     * Sets the value of the totBalPVTGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBalPVTGuarantor(String value) {
        this.totBalPVTGuarantor = value;
    }

    /**
     * Gets the value of the totBalNBFCBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBalNBFCBorrower() {
        return totBalNBFCBorrower;
    }

    /**
     * Sets the value of the totBalNBFCBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBalNBFCBorrower(String value) {
        this.totBalNBFCBorrower = value;
    }

    /**
     * Gets the value of the totBalNBFCGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBalNBFCGuarantor() {
        return totBalNBFCGuarantor;
    }

    /**
     * Sets the value of the totBalNBFCGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBalNBFCGuarantor(String value) {
        this.totBalNBFCGuarantor = value;
    }

    /**
     * Gets the value of the totBalNonSelfBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBalNonSelfBorrower() {
        return totBalNonSelfBorrower;
    }

    /**
     * Sets the value of the totBalNonSelfBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBalNonSelfBorrower(String value) {
        this.totBalNonSelfBorrower = value;
    }

    /**
     * Gets the value of the totBalNonSelfGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBalNonSelfGuarantor() {
        return totBalNonSelfGuarantor;
    }

    /**
     * Sets the value of the totBalNonSelfGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBalNonSelfGuarantor(String value) {
        this.totBalNonSelfGuarantor = value;
    }

}
