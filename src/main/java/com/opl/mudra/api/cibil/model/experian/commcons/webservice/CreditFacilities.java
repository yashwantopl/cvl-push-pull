
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CreditFacilities complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreditFacilities">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoActiveBorr_Guran" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoSelfBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoSelfGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoSelfActiveBorr_Guran" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoPSUBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoPSUGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoPSUActiveBorr_Guran" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoPVTBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoPVTGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoPVTActiveBorr_Guran" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoNBFCBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoNBFCGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoNBFCActiveBorr_Guran" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoNonSelfBorrower" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoNonSelfGuarantor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoNonSelfActiveBorr_Guran" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditFacilities", propOrder = {
    "segmentCode",
    "totNoBorrower",
    "totNoGuarantor",
    "totNoActiveBorrGuran",
    "totNoSelfBorrower",
    "totNoSelfGuarantor",
    "totNoSelfActiveBorrGuran",
    "totNoPSUBorrower",
    "totNoPSUGuarantor",
    "totNoPSUActiveBorrGuran",
    "totNoPVTBorrower",
    "totNoPVTGuarantor",
    "totNoPVTActiveBorrGuran",
    "totNoNBFCBorrower",
    "totNoNBFCGuarantor",
    "totNoNBFCActiveBorrGuran",
    "totNoNonSelfBorrower",
    "totNoNonSelfGuarantor",
    "totNoNonSelfActiveBorrGuran"
})
public class CreditFacilities {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "TotNoBorrower")
    protected String totNoBorrower;
    @XmlElement(name = "TotNoGuarantor")
    protected String totNoGuarantor;
    @XmlElement(name = "TotNoActiveBorr_Guran")
    protected String totNoActiveBorrGuran;
    @XmlElement(name = "TotNoSelfBorrower")
    protected String totNoSelfBorrower;
    @XmlElement(name = "TotNoSelfGuarantor")
    protected String totNoSelfGuarantor;
    @XmlElement(name = "TotNoSelfActiveBorr_Guran")
    protected String totNoSelfActiveBorrGuran;
    @XmlElement(name = "TotNoPSUBorrower")
    protected String totNoPSUBorrower;
    @XmlElement(name = "TotNoPSUGuarantor")
    protected String totNoPSUGuarantor;
    @XmlElement(name = "TotNoPSUActiveBorr_Guran")
    protected String totNoPSUActiveBorrGuran;
    @XmlElement(name = "TotNoPVTBorrower")
    protected String totNoPVTBorrower;
    @XmlElement(name = "TotNoPVTGuarantor")
    protected String totNoPVTGuarantor;
    @XmlElement(name = "TotNoPVTActiveBorr_Guran")
    protected String totNoPVTActiveBorrGuran;
    @XmlElement(name = "TotNoNBFCBorrower")
    protected String totNoNBFCBorrower;
    @XmlElement(name = "TotNoNBFCGuarantor")
    protected String totNoNBFCGuarantor;
    @XmlElement(name = "TotNoNBFCActiveBorr_Guran")
    protected String totNoNBFCActiveBorrGuran;
    @XmlElement(name = "TotNoNonSelfBorrower")
    protected String totNoNonSelfBorrower;
    @XmlElement(name = "TotNoNonSelfGuarantor")
    protected String totNoNonSelfGuarantor;
    @XmlElement(name = "TotNoNonSelfActiveBorr_Guran")
    protected String totNoNonSelfActiveBorrGuran;

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
     * Gets the value of the totNoBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoBorrower() {
        return totNoBorrower;
    }

    /**
     * Sets the value of the totNoBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoBorrower(String value) {
        this.totNoBorrower = value;
    }

    /**
     * Gets the value of the totNoGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoGuarantor() {
        return totNoGuarantor;
    }

    /**
     * Sets the value of the totNoGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoGuarantor(String value) {
        this.totNoGuarantor = value;
    }

    /**
     * Gets the value of the totNoActiveBorrGuran property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoActiveBorrGuran() {
        return totNoActiveBorrGuran;
    }

    /**
     * Sets the value of the totNoActiveBorrGuran property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoActiveBorrGuran(String value) {
        this.totNoActiveBorrGuran = value;
    }

    /**
     * Gets the value of the totNoSelfBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoSelfBorrower() {
        return totNoSelfBorrower;
    }

    /**
     * Sets the value of the totNoSelfBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoSelfBorrower(String value) {
        this.totNoSelfBorrower = value;
    }

    /**
     * Gets the value of the totNoSelfGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoSelfGuarantor() {
        return totNoSelfGuarantor;
    }

    /**
     * Sets the value of the totNoSelfGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoSelfGuarantor(String value) {
        this.totNoSelfGuarantor = value;
    }

    /**
     * Gets the value of the totNoSelfActiveBorrGuran property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoSelfActiveBorrGuran() {
        return totNoSelfActiveBorrGuran;
    }

    /**
     * Sets the value of the totNoSelfActiveBorrGuran property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoSelfActiveBorrGuran(String value) {
        this.totNoSelfActiveBorrGuran = value;
    }

    /**
     * Gets the value of the totNoPSUBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoPSUBorrower() {
        return totNoPSUBorrower;
    }

    /**
     * Sets the value of the totNoPSUBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoPSUBorrower(String value) {
        this.totNoPSUBorrower = value;
    }

    /**
     * Gets the value of the totNoPSUGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoPSUGuarantor() {
        return totNoPSUGuarantor;
    }

    /**
     * Sets the value of the totNoPSUGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoPSUGuarantor(String value) {
        this.totNoPSUGuarantor = value;
    }

    /**
     * Gets the value of the totNoPSUActiveBorrGuran property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoPSUActiveBorrGuran() {
        return totNoPSUActiveBorrGuran;
    }

    /**
     * Sets the value of the totNoPSUActiveBorrGuran property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoPSUActiveBorrGuran(String value) {
        this.totNoPSUActiveBorrGuran = value;
    }

    /**
     * Gets the value of the totNoPVTBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoPVTBorrower() {
        return totNoPVTBorrower;
    }

    /**
     * Sets the value of the totNoPVTBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoPVTBorrower(String value) {
        this.totNoPVTBorrower = value;
    }

    /**
     * Gets the value of the totNoPVTGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoPVTGuarantor() {
        return totNoPVTGuarantor;
    }

    /**
     * Sets the value of the totNoPVTGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoPVTGuarantor(String value) {
        this.totNoPVTGuarantor = value;
    }

    /**
     * Gets the value of the totNoPVTActiveBorrGuran property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoPVTActiveBorrGuran() {
        return totNoPVTActiveBorrGuran;
    }

    /**
     * Sets the value of the totNoPVTActiveBorrGuran property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoPVTActiveBorrGuran(String value) {
        this.totNoPVTActiveBorrGuran = value;
    }

    /**
     * Gets the value of the totNoNBFCBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoNBFCBorrower() {
        return totNoNBFCBorrower;
    }

    /**
     * Sets the value of the totNoNBFCBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoNBFCBorrower(String value) {
        this.totNoNBFCBorrower = value;
    }

    /**
     * Gets the value of the totNoNBFCGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoNBFCGuarantor() {
        return totNoNBFCGuarantor;
    }

    /**
     * Sets the value of the totNoNBFCGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoNBFCGuarantor(String value) {
        this.totNoNBFCGuarantor = value;
    }

    /**
     * Gets the value of the totNoNBFCActiveBorrGuran property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoNBFCActiveBorrGuran() {
        return totNoNBFCActiveBorrGuran;
    }

    /**
     * Sets the value of the totNoNBFCActiveBorrGuran property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoNBFCActiveBorrGuran(String value) {
        this.totNoNBFCActiveBorrGuran = value;
    }

    /**
     * Gets the value of the totNoNonSelfBorrower property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoNonSelfBorrower() {
        return totNoNonSelfBorrower;
    }

    /**
     * Sets the value of the totNoNonSelfBorrower property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoNonSelfBorrower(String value) {
        this.totNoNonSelfBorrower = value;
    }

    /**
     * Gets the value of the totNoNonSelfGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoNonSelfGuarantor() {
        return totNoNonSelfGuarantor;
    }

    /**
     * Sets the value of the totNoNonSelfGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoNonSelfGuarantor(String value) {
        this.totNoNonSelfGuarantor = value;
    }

    /**
     * Gets the value of the totNoNonSelfActiveBorrGuran property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoNonSelfActiveBorrGuran() {
        return totNoNonSelfActiveBorrGuran;
    }

    /**
     * Sets the value of the totNoNonSelfActiveBorrGuran property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoNonSelfActiveBorrGuran(String value) {
        this.totNoNonSelfActiveBorrGuran = value;
    }

}
