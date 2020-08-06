
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BUSBORROWER complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BUSBORROWER">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerShortName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerLastReportedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerPAN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerCIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerTIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerServiceTaxNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerOtherID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerLegalConst" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerCat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerIndType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerClassActivity1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerClassActivity2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerClassActivity3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerSicCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerSalesFigure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerEmpNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerCreditRating" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerAssAuthority" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerCreditRatingIssueDt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerCreditRatingExpDt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerPINCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerLocationType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerAccountStatusDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerMobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerTelephoneNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerTelephoneAreaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerFaxNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerFaxAreaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BUSBORROWER", propOrder = {
    "segmentCode",
    "borrowerName",
    "borrowerShortName",
    "borrowerLastReportedDate",
    "borrowerPAN",
    "borrowerCIN",
    "borrowerTIN",
    "borrowerServiceTaxNo",
    "borrowerOtherID",
    "borrowerLegalConst",
    "borrowerCat",
    "borrowerIndType",
    "borrowerClassActivity1",
    "borrowerClassActivity2",
    "borrowerClassActivity3",
    "borrowerSicCode",
    "borrowerSalesFigure",
    "borrowerYear",
    "borrowerEmpNo",
    "borrowerCreditRating",
    "borrowerAssAuthority",
    "borrowerCreditRatingIssueDt",
    "borrowerCreditRatingExpDt",
    "borrowerAddress",
    "borrowerCity",
    "borrowerPINCode",
    "borrowerLocationType",
    "borrowerCountry",
    "borrowerAccountStatusDate",
    "startDate",
    "borrowerMobile",
    "borrowerTelephoneNo",
    "borrowerTelephoneAreaCode",
    "borrowerFaxNo",
    "borrowerFaxAreaCode"
})
public class BUSBORROWER {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "BorrowerName")
    protected String borrowerName;
    @XmlElement(name = "BorrowerShortName")
    protected String borrowerShortName;
    @XmlElement(name = "BorrowerLastReportedDate")
    protected String borrowerLastReportedDate;
    @XmlElement(name = "BorrowerPAN")
    protected String borrowerPAN;
    @XmlElement(name = "BorrowerCIN")
    protected String borrowerCIN;
    @XmlElement(name = "BorrowerTIN")
    protected String borrowerTIN;
    @XmlElement(name = "BorrowerServiceTaxNo")
    protected String borrowerServiceTaxNo;
    @XmlElement(name = "BorrowerOtherID")
    protected String borrowerOtherID;
    @XmlElement(name = "BorrowerLegalConst")
    protected String borrowerLegalConst;
    @XmlElement(name = "BorrowerCat")
    protected String borrowerCat;
    @XmlElement(name = "BorrowerIndType")
    protected String borrowerIndType;
    @XmlElement(name = "BorrowerClassActivity1")
    protected String borrowerClassActivity1;
    @XmlElement(name = "BorrowerClassActivity2")
    protected String borrowerClassActivity2;
    @XmlElement(name = "BorrowerClassActivity3")
    protected String borrowerClassActivity3;
    @XmlElement(name = "BorrowerSicCode")
    protected String borrowerSicCode;
    @XmlElement(name = "BorrowerSalesFigure")
    protected String borrowerSalesFigure;
    @XmlElement(name = "BorrowerYear")
    protected String borrowerYear;
    @XmlElement(name = "BorrowerEmpNo")
    protected String borrowerEmpNo;
    @XmlElement(name = "BorrowerCreditRating")
    protected String borrowerCreditRating;
    @XmlElement(name = "BorrowerAssAuthority")
    protected String borrowerAssAuthority;
    @XmlElement(name = "BorrowerCreditRatingIssueDt")
    protected String borrowerCreditRatingIssueDt;
    @XmlElement(name = "BorrowerCreditRatingExpDt")
    protected String borrowerCreditRatingExpDt;
    @XmlElement(name = "BorrowerAddress")
    protected String borrowerAddress;
    @XmlElement(name = "BorrowerCity")
    protected String borrowerCity;
    @XmlElement(name = "BorrowerPINCode")
    protected String borrowerPINCode;
    @XmlElement(name = "BorrowerLocationType")
    protected String borrowerLocationType;
    @XmlElement(name = "BorrowerCountry")
    protected String borrowerCountry;
    @XmlElement(name = "BorrowerAccountStatusDate")
    protected String borrowerAccountStatusDate;
    @XmlElement(name = "StartDate")
    protected String startDate;
    @XmlElement(name = "BorrowerMobile")
    protected String borrowerMobile;
    @XmlElement(name = "BorrowerTelephoneNo")
    protected String borrowerTelephoneNo;
    @XmlElement(name = "BorrowerTelephoneAreaCode")
    protected String borrowerTelephoneAreaCode;
    @XmlElement(name = "BorrowerFaxNo")
    protected String borrowerFaxNo;
    @XmlElement(name = "BorrowerFaxAreaCode")
    protected String borrowerFaxAreaCode;

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
     * Gets the value of the borrowerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerName() {
        return borrowerName;
    }

    /**
     * Sets the value of the borrowerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerName(String value) {
        this.borrowerName = value;
    }

    /**
     * Gets the value of the borrowerShortName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerShortName() {
        return borrowerShortName;
    }

    /**
     * Sets the value of the borrowerShortName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerShortName(String value) {
        this.borrowerShortName = value;
    }

    /**
     * Gets the value of the borrowerLastReportedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerLastReportedDate() {
        return borrowerLastReportedDate;
    }

    /**
     * Sets the value of the borrowerLastReportedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerLastReportedDate(String value) {
        this.borrowerLastReportedDate = value;
    }

    /**
     * Gets the value of the borrowerPAN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerPAN() {
        return borrowerPAN;
    }

    /**
     * Sets the value of the borrowerPAN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerPAN(String value) {
        this.borrowerPAN = value;
    }

    /**
     * Gets the value of the borrowerCIN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerCIN() {
        return borrowerCIN;
    }

    /**
     * Sets the value of the borrowerCIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerCIN(String value) {
        this.borrowerCIN = value;
    }

    /**
     * Gets the value of the borrowerTIN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerTIN() {
        return borrowerTIN;
    }

    /**
     * Sets the value of the borrowerTIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerTIN(String value) {
        this.borrowerTIN = value;
    }

    /**
     * Gets the value of the borrowerServiceTaxNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerServiceTaxNo() {
        return borrowerServiceTaxNo;
    }

    /**
     * Sets the value of the borrowerServiceTaxNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerServiceTaxNo(String value) {
        this.borrowerServiceTaxNo = value;
    }

    /**
     * Gets the value of the borrowerOtherID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerOtherID() {
        return borrowerOtherID;
    }

    /**
     * Sets the value of the borrowerOtherID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerOtherID(String value) {
        this.borrowerOtherID = value;
    }

    /**
     * Gets the value of the borrowerLegalConst property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerLegalConst() {
        return borrowerLegalConst;
    }

    /**
     * Sets the value of the borrowerLegalConst property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerLegalConst(String value) {
        this.borrowerLegalConst = value;
    }

    /**
     * Gets the value of the borrowerCat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerCat() {
        return borrowerCat;
    }

    /**
     * Sets the value of the borrowerCat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerCat(String value) {
        this.borrowerCat = value;
    }

    /**
     * Gets the value of the borrowerIndType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerIndType() {
        return borrowerIndType;
    }

    /**
     * Sets the value of the borrowerIndType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerIndType(String value) {
        this.borrowerIndType = value;
    }

    /**
     * Gets the value of the borrowerClassActivity1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerClassActivity1() {
        return borrowerClassActivity1;
    }

    /**
     * Sets the value of the borrowerClassActivity1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerClassActivity1(String value) {
        this.borrowerClassActivity1 = value;
    }

    /**
     * Gets the value of the borrowerClassActivity2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerClassActivity2() {
        return borrowerClassActivity2;
    }

    /**
     * Sets the value of the borrowerClassActivity2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerClassActivity2(String value) {
        this.borrowerClassActivity2 = value;
    }

    /**
     * Gets the value of the borrowerClassActivity3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerClassActivity3() {
        return borrowerClassActivity3;
    }

    /**
     * Sets the value of the borrowerClassActivity3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerClassActivity3(String value) {
        this.borrowerClassActivity3 = value;
    }

    /**
     * Gets the value of the borrowerSicCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerSicCode() {
        return borrowerSicCode;
    }

    /**
     * Sets the value of the borrowerSicCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerSicCode(String value) {
        this.borrowerSicCode = value;
    }

    /**
     * Gets the value of the borrowerSalesFigure property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerSalesFigure() {
        return borrowerSalesFigure;
    }

    /**
     * Sets the value of the borrowerSalesFigure property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerSalesFigure(String value) {
        this.borrowerSalesFigure = value;
    }

    /**
     * Gets the value of the borrowerYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerYear() {
        return borrowerYear;
    }

    /**
     * Sets the value of the borrowerYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerYear(String value) {
        this.borrowerYear = value;
    }

    /**
     * Gets the value of the borrowerEmpNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerEmpNo() {
        return borrowerEmpNo;
    }

    /**
     * Sets the value of the borrowerEmpNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerEmpNo(String value) {
        this.borrowerEmpNo = value;
    }

    /**
     * Gets the value of the borrowerCreditRating property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerCreditRating() {
        return borrowerCreditRating;
    }

    /**
     * Sets the value of the borrowerCreditRating property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerCreditRating(String value) {
        this.borrowerCreditRating = value;
    }

    /**
     * Gets the value of the borrowerAssAuthority property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerAssAuthority() {
        return borrowerAssAuthority;
    }

    /**
     * Sets the value of the borrowerAssAuthority property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerAssAuthority(String value) {
        this.borrowerAssAuthority = value;
    }

    /**
     * Gets the value of the borrowerCreditRatingIssueDt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerCreditRatingIssueDt() {
        return borrowerCreditRatingIssueDt;
    }

    /**
     * Sets the value of the borrowerCreditRatingIssueDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerCreditRatingIssueDt(String value) {
        this.borrowerCreditRatingIssueDt = value;
    }

    /**
     * Gets the value of the borrowerCreditRatingExpDt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerCreditRatingExpDt() {
        return borrowerCreditRatingExpDt;
    }

    /**
     * Sets the value of the borrowerCreditRatingExpDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerCreditRatingExpDt(String value) {
        this.borrowerCreditRatingExpDt = value;
    }

    /**
     * Gets the value of the borrowerAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerAddress() {
        return borrowerAddress;
    }

    /**
     * Sets the value of the borrowerAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerAddress(String value) {
        this.borrowerAddress = value;
    }

    /**
     * Gets the value of the borrowerCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerCity() {
        return borrowerCity;
    }

    /**
     * Sets the value of the borrowerCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerCity(String value) {
        this.borrowerCity = value;
    }

    /**
     * Gets the value of the borrowerPINCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerPINCode() {
        return borrowerPINCode;
    }

    /**
     * Sets the value of the borrowerPINCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerPINCode(String value) {
        this.borrowerPINCode = value;
    }

    /**
     * Gets the value of the borrowerLocationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerLocationType() {
        return borrowerLocationType;
    }

    /**
     * Sets the value of the borrowerLocationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerLocationType(String value) {
        this.borrowerLocationType = value;
    }

    /**
     * Gets the value of the borrowerCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerCountry() {
        return borrowerCountry;
    }

    /**
     * Sets the value of the borrowerCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerCountry(String value) {
        this.borrowerCountry = value;
    }

    /**
     * Gets the value of the borrowerAccountStatusDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerAccountStatusDate() {
        return borrowerAccountStatusDate;
    }

    /**
     * Sets the value of the borrowerAccountStatusDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerAccountStatusDate(String value) {
        this.borrowerAccountStatusDate = value;
    }

    /**
     * Gets the value of the startDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Sets the value of the startDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartDate(String value) {
        this.startDate = value;
    }

    /**
     * Gets the value of the borrowerMobile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerMobile() {
        return borrowerMobile;
    }

    /**
     * Sets the value of the borrowerMobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerMobile(String value) {
        this.borrowerMobile = value;
    }

    /**
     * Gets the value of the borrowerTelephoneNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerTelephoneNo() {
        return borrowerTelephoneNo;
    }

    /**
     * Sets the value of the borrowerTelephoneNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerTelephoneNo(String value) {
        this.borrowerTelephoneNo = value;
    }

    /**
     * Gets the value of the borrowerTelephoneAreaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerTelephoneAreaCode() {
        return borrowerTelephoneAreaCode;
    }

    /**
     * Sets the value of the borrowerTelephoneAreaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerTelephoneAreaCode(String value) {
        this.borrowerTelephoneAreaCode = value;
    }

    /**
     * Gets the value of the borrowerFaxNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerFaxNo() {
        return borrowerFaxNo;
    }

    /**
     * Sets the value of the borrowerFaxNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerFaxNo(String value) {
        this.borrowerFaxNo = value;
    }

    /**
     * Gets the value of the borrowerFaxAreaCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerFaxAreaCode() {
        return borrowerFaxAreaCode;
    }

    /**
     * Sets the value of the borrowerFaxAreaCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerFaxAreaCode(String value) {
        this.borrowerFaxAreaCode = value;
    }

}
