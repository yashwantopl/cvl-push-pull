
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DelCreditFacilities complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DelCreditFacilities">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotNoBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotNoGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotNoSelfBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotNoSelfGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotNoPSUBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotNoPSUGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotNoPVTBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotNoPVTGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotNoNBFCBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotNoNBFCGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotNoNonSelfBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DelTotNoNonSelfGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DelCreditFacilities", propOrder = {
    "segmentCode",
    "delTotNoBorrower",
    "delTotNoGuarantor",
    "delTotNoSelfBorrower",
    "delTotNoSelfGuarantor",
    "delTotNoPSUBorrower",
    "delTotNoPSUGuarantor",
    "delTotNoPVTBorrower",
    "delTotNoPVTGuarantor",
    "delTotNoNBFCBorrower",
    "delTotNoNBFCGuarantor",
    "delTotNoNonSelfBorrower",
    "delTotNoNonSelfGuarantor"
})
public class DelCreditFacilities {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "DelTotNoBorrower")
    protected String delTotNoBorrower;
    @XmlElement(name = "DelTotNoGuarantor")
    protected String delTotNoGuarantor;
    @XmlElement(name = "DelTotNoSelfBorrower")
    protected String delTotNoSelfBorrower;
    @XmlElement(name = "DelTotNoSelfGuarantor")
    protected String delTotNoSelfGuarantor;
    @XmlElement(name = "DelTotNoPSUBorrower")
    protected String delTotNoPSUBorrower;
    @XmlElement(name = "DelTotNoPSUGuarantor")
    protected String delTotNoPSUGuarantor;
    @XmlElement(name = "DelTotNoPVTBorrower")
    protected String delTotNoPVTBorrower;
    @XmlElement(name = "DelTotNoPVTGuarantor")
    protected String delTotNoPVTGuarantor;
    @XmlElement(name = "DelTotNoNBFCBorrower")
    protected String delTotNoNBFCBorrower;
    @XmlElement(name = "DelTotNoNBFCGuarantor")
    protected String delTotNoNBFCGuarantor;
    @XmlElement(name = "DelTotNoNonSelfBorrower")
    protected String delTotNoNonSelfBorrower;
    @XmlElement(name = "DelTotNoNonSelfGuarantor")
    protected String delTotNoNonSelfGuarantor;

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
     * Gets the value of the delTotNoBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotNoBorrower() {
        return delTotNoBorrower;
    }

    /**
     * Sets the value of the delTotNoBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotNoBorrower(String value) {
        this.delTotNoBorrower = value;
    }

    /**
     * Gets the value of the delTotNoGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotNoGuarantor() {
        return delTotNoGuarantor;
    }

    /**
     * Sets the value of the delTotNoGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotNoGuarantor(String value) {
        this.delTotNoGuarantor = value;
    }

    /**
     * Gets the value of the delTotNoSelfBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotNoSelfBorrower() {
        return delTotNoSelfBorrower;
    }

    /**
     * Sets the value of the delTotNoSelfBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotNoSelfBorrower(String value) {
        this.delTotNoSelfBorrower = value;
    }

    /**
     * Gets the value of the delTotNoSelfGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotNoSelfGuarantor() {
        return delTotNoSelfGuarantor;
    }

    /**
     * Sets the value of the delTotNoSelfGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotNoSelfGuarantor(String value) {
        this.delTotNoSelfGuarantor = value;
    }

    /**
     * Gets the value of the delTotNoPSUBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotNoPSUBorrower() {
        return delTotNoPSUBorrower;
    }

    /**
     * Sets the value of the delTotNoPSUBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotNoPSUBorrower(String value) {
        this.delTotNoPSUBorrower = value;
    }

    /**
     * Gets the value of the delTotNoPSUGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotNoPSUGuarantor() {
        return delTotNoPSUGuarantor;
    }

    /**
     * Sets the value of the delTotNoPSUGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotNoPSUGuarantor(String value) {
        this.delTotNoPSUGuarantor = value;
    }

    /**
     * Gets the value of the delTotNoPVTBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotNoPVTBorrower() {
        return delTotNoPVTBorrower;
    }

    /**
     * Sets the value of the delTotNoPVTBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotNoPVTBorrower(String value) {
        this.delTotNoPVTBorrower = value;
    }

    /**
     * Gets the value of the delTotNoPVTGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotNoPVTGuarantor() {
        return delTotNoPVTGuarantor;
    }

    /**
     * Sets the value of the delTotNoPVTGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotNoPVTGuarantor(String value) {
        this.delTotNoPVTGuarantor = value;
    }

    /**
     * Gets the value of the delTotNoNBFCBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotNoNBFCBorrower() {
        return delTotNoNBFCBorrower;
    }

    /**
     * Sets the value of the delTotNoNBFCBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotNoNBFCBorrower(String value) {
        this.delTotNoNBFCBorrower = value;
    }

    /**
     * Gets the value of the delTotNoNBFCGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotNoNBFCGuarantor() {
        return delTotNoNBFCGuarantor;
    }

    /**
     * Sets the value of the delTotNoNBFCGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotNoNBFCGuarantor(String value) {
        this.delTotNoNBFCGuarantor = value;
    }

    /**
     * Gets the value of the delTotNoNonSelfBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotNoNonSelfBorrower() {
        return delTotNoNonSelfBorrower;
    }

    /**
     * Sets the value of the delTotNoNonSelfBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotNoNonSelfBorrower(String value) {
        this.delTotNoNonSelfBorrower = value;
    }

    /**
     * Gets the value of the delTotNoNonSelfGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDelTotNoNonSelfGuarantor() {
        return delTotNoNonSelfGuarantor;
    }

    /**
     * Sets the value of the delTotNoNonSelfGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDelTotNoNonSelfGuarantor(String value) {
        this.delTotNoNonSelfGuarantor = value;
    }

}
