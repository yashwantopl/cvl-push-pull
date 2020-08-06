
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DelOutstandingBalance complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DelOutstandingBalance">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotBalBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotBalGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotBalSelfBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotBalSelfGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotBalPSUBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotBalPSUGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotBalPVTBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotBalPVTGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotBalNBFCBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotBalNBFCGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotBalNonSelfBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotBalNonSelfGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DelOutstandingBalance", propOrder = {
    "segmentCode",
    "delTotBalBorrower",
    "delTotBalGuarantor",
    "delTotBalSelfBorrower",
    "delTotBalSelfGuarantor",
    "delTotBalPSUBorrower",
    "delTotBalPSUGuarantor",
    "delTotBalPVTBorrower",
    "delTotBalPVTGuarantor",
    "delTotBalNBFCBorrower",
    "delTotBalNBFCGuarantor",
    "delTotBalNonSelfBorrower",
    "delTotBalNonSelfGuarantor"
})
public class DelOutstandingBalance {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "DelTotBalBorrower")
    protected String delTotBalBorrower;
    @XmlElement(name = "DelTotBalGuarantor")
    protected String delTotBalGuarantor;
    @XmlElement(name = "DelTotBalSelfBorrower")
    protected String delTotBalSelfBorrower;
    @XmlElement(name = "DelTotBalSelfGuarantor")
    protected String delTotBalSelfGuarantor;
    @XmlElement(name = "DelTotBalPSUBorrower")
    protected String delTotBalPSUBorrower;
    @XmlElement(name = "DelTotBalPSUGuarantor")
    protected String delTotBalPSUGuarantor;
    @XmlElement(name = "DelTotBalPVTBorrower")
    protected String delTotBalPVTBorrower;
    @XmlElement(name = "DelTotBalPVTGuarantor")
    protected String delTotBalPVTGuarantor;
    @XmlElement(name = "DelTotBalNBFCBorrower")
    protected String delTotBalNBFCBorrower;
    @XmlElement(name = "DelTotBalNBFCGuarantor")
    protected String delTotBalNBFCGuarantor;
    @XmlElement(name = "DelTotBalNonSelfBorrower")
    protected String delTotBalNonSelfBorrower;
    @XmlElement(name = "DelTotBalNonSelfGuarantor")
    protected String delTotBalNonSelfGuarantor;

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
     * Gets the value of the delTotBalBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotBalBorrower() {
        return delTotBalBorrower;
    }

    /**
     * Sets the value of the delTotBalBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotBalBorrower(String value) {
        this.delTotBalBorrower = value;
    }

    /**
     * Gets the value of the delTotBalGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotBalGuarantor() {
        return delTotBalGuarantor;
    }

    /**
     * Sets the value of the delTotBalGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotBalGuarantor(String value) {
        this.delTotBalGuarantor = value;
    }

    /**
     * Gets the value of the delTotBalSelfBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotBalSelfBorrower() {
        return delTotBalSelfBorrower;
    }

    /**
     * Sets the value of the delTotBalSelfBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotBalSelfBorrower(String value) {
        this.delTotBalSelfBorrower = value;
    }

    /**
     * Gets the value of the delTotBalSelfGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotBalSelfGuarantor() {
        return delTotBalSelfGuarantor;
    }

    /**
     * Sets the value of the delTotBalSelfGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotBalSelfGuarantor(String value) {
        this.delTotBalSelfGuarantor = value;
    }

    /**
     * Gets the value of the delTotBalPSUBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotBalPSUBorrower() {
        return delTotBalPSUBorrower;
    }

    /**
     * Sets the value of the delTotBalPSUBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotBalPSUBorrower(String value) {
        this.delTotBalPSUBorrower = value;
    }

    /**
     * Gets the value of the delTotBalPSUGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotBalPSUGuarantor() {
        return delTotBalPSUGuarantor;
    }

    /**
     * Sets the value of the delTotBalPSUGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotBalPSUGuarantor(String value) {
        this.delTotBalPSUGuarantor = value;
    }

    /**
     * Gets the value of the delTotBalPVTBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotBalPVTBorrower() {
        return delTotBalPVTBorrower;
    }

    /**
     * Sets the value of the delTotBalPVTBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotBalPVTBorrower(String value) {
        this.delTotBalPVTBorrower = value;
    }

    /**
     * Gets the value of the delTotBalPVTGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotBalPVTGuarantor() {
        return delTotBalPVTGuarantor;
    }

    /**
     * Sets the value of the delTotBalPVTGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotBalPVTGuarantor(String value) {
        this.delTotBalPVTGuarantor = value;
    }

    /**
     * Gets the value of the delTotBalNBFCBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotBalNBFCBorrower() {
        return delTotBalNBFCBorrower;
    }

    /**
     * Sets the value of the delTotBalNBFCBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotBalNBFCBorrower(String value) {
        this.delTotBalNBFCBorrower = value;
    }

    /**
     * Gets the value of the delTotBalNBFCGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotBalNBFCGuarantor() {
        return delTotBalNBFCGuarantor;
    }

    /**
     * Sets the value of the delTotBalNBFCGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotBalNBFCGuarantor(String value) {
        this.delTotBalNBFCGuarantor = value;
    }

    /**
     * Gets the value of the delTotBalNonSelfBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotBalNonSelfBorrower() {
        return delTotBalNonSelfBorrower;
    }

    /**
     * Sets the value of the delTotBalNonSelfBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotBalNonSelfBorrower(String value) {
        this.delTotBalNonSelfBorrower = value;
    }

    /**
     * Gets the value of the delTotBalNonSelfGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotBalNonSelfGuarantor() {
        return delTotBalNonSelfGuarantor;
    }

    /**
     * Sets the value of the delTotBalNonSelfGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotBalNonSelfGuarantor(String value) {
        this.delTotBalNonSelfGuarantor = value;
    }

}
