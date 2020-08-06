
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GUARANTOR complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GUARANTOR">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorPAN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorLocationType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorPINCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorTelephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorLastReportedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorStartDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorDOB" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorVoterID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorPassport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorDLNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorUID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorRationCardNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorDIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorCIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorTIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorBUSSCat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorBUSSIndType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorIncorpDt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorRegNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GuarantorServiceTaxNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GUARANTOR", propOrder = {
    "segmentCode",
    "guarantorName",
    "guarantorType",
    "guarantorPAN",
    "guarantorLocationType",
    "guarantorAddress",
    "guarantorCity",
    "guarantorPINCode",
    "guarantorTelephone",
    "guarantorLastReportedDate",
    "guarantorStartDate",
    "guarantorDOB",
    "guarantorVoterID",
    "guarantorPassport",
    "guarantorDLNo",
    "guarantorUID",
    "guarantorRationCardNo",
    "guarantorDIN",
    "guarantorCIN",
    "guarantorTIN",
    "guarantorBUSSCat",
    "guarantorBUSSIndType",
    "guarantorIncorpDt",
    "guarantorRegNo",
    "guarantorServiceTaxNo"
})
public class GUARANTOR {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "GuarantorName")
    protected String guarantorName;
    @XmlElement(name = "GuarantorType")
    protected String guarantorType;
    @XmlElement(name = "GuarantorPAN")
    protected String guarantorPAN;
    @XmlElement(name = "GuarantorLocationType")
    protected String guarantorLocationType;
    @XmlElement(name = "GuarantorAddress")
    protected String guarantorAddress;
    @XmlElement(name = "GuarantorCity")
    protected String guarantorCity;
    @XmlElement(name = "GuarantorPINCode")
    protected String guarantorPINCode;
    @XmlElement(name = "GuarantorTelephone")
    protected String guarantorTelephone;
    @XmlElement(name = "GuarantorLastReportedDate")
    protected String guarantorLastReportedDate;
    @XmlElement(name = "GuarantorStartDate")
    protected String guarantorStartDate;
    @XmlElement(name = "GuarantorDOB")
    protected String guarantorDOB;
    @XmlElement(name = "GuarantorVoterID")
    protected String guarantorVoterID;
    @XmlElement(name = "GuarantorPassport")
    protected String guarantorPassport;
    @XmlElement(name = "GuarantorDLNo")
    protected String guarantorDLNo;
    @XmlElement(name = "GuarantorUID")
    protected String guarantorUID;
    @XmlElement(name = "GuarantorRationCardNo")
    protected String guarantorRationCardNo;
    @XmlElement(name = "GuarantorDIN")
    protected String guarantorDIN;
    @XmlElement(name = "GuarantorCIN")
    protected String guarantorCIN;
    @XmlElement(name = "GuarantorTIN")
    protected String guarantorTIN;
    @XmlElement(name = "GuarantorBUSSCat")
    protected String guarantorBUSSCat;
    @XmlElement(name = "GuarantorBUSSIndType")
    protected String guarantorBUSSIndType;
    @XmlElement(name = "GuarantorIncorpDt")
    protected String guarantorIncorpDt;
    @XmlElement(name = "GuarantorRegNo")
    protected String guarantorRegNo;
    @XmlElement(name = "GuarantorServiceTaxNo")
    protected String guarantorServiceTaxNo;

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
     * Gets the value of the guarantorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorName() {
        return guarantorName;
    }

    /**
     * Sets the value of the guarantorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorName(String value) {
        this.guarantorName = value;
    }

    /**
     * Gets the value of the guarantorType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorType() {
        return guarantorType;
    }

    /**
     * Sets the value of the guarantorType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorType(String value) {
        this.guarantorType = value;
    }

    /**
     * Gets the value of the guarantorPAN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorPAN() {
        return guarantorPAN;
    }

    /**
     * Sets the value of the guarantorPAN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorPAN(String value) {
        this.guarantorPAN = value;
    }

    /**
     * Gets the value of the guarantorLocationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorLocationType() {
        return guarantorLocationType;
    }

    /**
     * Sets the value of the guarantorLocationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorLocationType(String value) {
        this.guarantorLocationType = value;
    }

    /**
     * Gets the value of the guarantorAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorAddress() {
        return guarantorAddress;
    }

    /**
     * Sets the value of the guarantorAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorAddress(String value) {
        this.guarantorAddress = value;
    }

    /**
     * Gets the value of the guarantorCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorCity() {
        return guarantorCity;
    }

    /**
     * Sets the value of the guarantorCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorCity(String value) {
        this.guarantorCity = value;
    }

    /**
     * Gets the value of the guarantorPINCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorPINCode() {
        return guarantorPINCode;
    }

    /**
     * Sets the value of the guarantorPINCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorPINCode(String value) {
        this.guarantorPINCode = value;
    }

    /**
     * Gets the value of the guarantorTelephone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorTelephone() {
        return guarantorTelephone;
    }

    /**
     * Sets the value of the guarantorTelephone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorTelephone(String value) {
        this.guarantorTelephone = value;
    }

    /**
     * Gets the value of the guarantorLastReportedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorLastReportedDate() {
        return guarantorLastReportedDate;
    }

    /**
     * Sets the value of the guarantorLastReportedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorLastReportedDate(String value) {
        this.guarantorLastReportedDate = value;
    }

    /**
     * Gets the value of the guarantorStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorStartDate() {
        return guarantorStartDate;
    }

    /**
     * Sets the value of the guarantorStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorStartDate(String value) {
        this.guarantorStartDate = value;
    }

    /**
     * Gets the value of the guarantorDOB property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorDOB() {
        return guarantorDOB;
    }

    /**
     * Sets the value of the guarantorDOB property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorDOB(String value) {
        this.guarantorDOB = value;
    }

    /**
     * Gets the value of the guarantorVoterID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorVoterID() {
        return guarantorVoterID;
    }

    /**
     * Sets the value of the guarantorVoterID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorVoterID(String value) {
        this.guarantorVoterID = value;
    }

    /**
     * Gets the value of the guarantorPassport property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorPassport() {
        return guarantorPassport;
    }

    /**
     * Sets the value of the guarantorPassport property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorPassport(String value) {
        this.guarantorPassport = value;
    }

    /**
     * Gets the value of the guarantorDLNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorDLNo() {
        return guarantorDLNo;
    }

    /**
     * Sets the value of the guarantorDLNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorDLNo(String value) {
        this.guarantorDLNo = value;
    }

    /**
     * Gets the value of the guarantorUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorUID() {
        return guarantorUID;
    }

    /**
     * Sets the value of the guarantorUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorUID(String value) {
        this.guarantorUID = value;
    }

    /**
     * Gets the value of the guarantorRationCardNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorRationCardNo() {
        return guarantorRationCardNo;
    }

    /**
     * Sets the value of the guarantorRationCardNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorRationCardNo(String value) {
        this.guarantorRationCardNo = value;
    }

    /**
     * Gets the value of the guarantorDIN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorDIN() {
        return guarantorDIN;
    }

    /**
     * Sets the value of the guarantorDIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorDIN(String value) {
        this.guarantorDIN = value;
    }

    /**
     * Gets the value of the guarantorCIN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorCIN() {
        return guarantorCIN;
    }

    /**
     * Sets the value of the guarantorCIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorCIN(String value) {
        this.guarantorCIN = value;
    }

    /**
     * Gets the value of the guarantorTIN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorTIN() {
        return guarantorTIN;
    }

    /**
     * Sets the value of the guarantorTIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorTIN(String value) {
        this.guarantorTIN = value;
    }

    /**
     * Gets the value of the guarantorBUSSCat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorBUSSCat() {
        return guarantorBUSSCat;
    }

    /**
     * Sets the value of the guarantorBUSSCat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorBUSSCat(String value) {
        this.guarantorBUSSCat = value;
    }

    /**
     * Gets the value of the guarantorBUSSIndType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorBUSSIndType() {
        return guarantorBUSSIndType;
    }

    /**
     * Sets the value of the guarantorBUSSIndType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorBUSSIndType(String value) {
        this.guarantorBUSSIndType = value;
    }

    /**
     * Gets the value of the guarantorIncorpDt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorIncorpDt() {
        return guarantorIncorpDt;
    }

    /**
     * Sets the value of the guarantorIncorpDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorIncorpDt(String value) {
        this.guarantorIncorpDt = value;
    }

    /**
     * Gets the value of the guarantorRegNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorRegNo() {
        return guarantorRegNo;
    }

    /**
     * Sets the value of the guarantorRegNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorRegNo(String value) {
        this.guarantorRegNo = value;
    }

    /**
     * Gets the value of the guarantorServiceTaxNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuarantorServiceTaxNo() {
        return guarantorServiceTaxNo;
    }

    /**
     * Sets the value of the guarantorServiceTaxNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuarantorServiceTaxNo(String value) {
        this.guarantorServiceTaxNo = value;
    }

}
