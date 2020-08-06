
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ENQPRSNSRCH complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ENQPRSNSRCH">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FirstGivenName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MiddleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OtherMiddleNames" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FamilyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Suffix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ApplicationRole" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DateOfBirth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Gender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiaMiddleName3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiaNameTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ENQPRSNSRCH", propOrder = {
    "segmentCode",
    "firstGivenName",
    "middleName",
    "otherMiddleNames",
    "familyName",
    "suffix",
    "applicationRole",
    "dateOfBirth",
    "gender",
    "indiaMiddleName3",
    "indiaNameTitle"
})
public class ENQPRSNSRCH {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "FirstGivenName")
    protected String firstGivenName;
    @XmlElement(name = "MiddleName")
    protected String middleName;
    @XmlElement(name = "OtherMiddleNames")
    protected String otherMiddleNames;
    @XmlElement(name = "FamilyName")
    protected String familyName;
    @XmlElement(name = "Suffix")
    protected String suffix;
    @XmlElement(name = "ApplicationRole")
    protected String applicationRole;
    @XmlElement(name = "DateOfBirth")
    protected String dateOfBirth;
    @XmlElement(name = "Gender")
    protected String gender;
    @XmlElement(name = "IndiaMiddleName3")
    protected String indiaMiddleName3;
    @XmlElement(name = "IndiaNameTitle")
    protected String indiaNameTitle;

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
     * Gets the value of the firstGivenName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstGivenName() {
        return firstGivenName;
    }

    /**
     * Sets the value of the firstGivenName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstGivenName(String value) {
        this.firstGivenName = value;
    }

    /**
     * Gets the value of the middleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets the value of the middleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiddleName(String value) {
        this.middleName = value;
    }

    /**
     * Gets the value of the otherMiddleNames property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherMiddleNames() {
        return otherMiddleNames;
    }

    /**
     * Sets the value of the otherMiddleNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherMiddleNames(String value) {
        this.otherMiddleNames = value;
    }

    /**
     * Gets the value of the familyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Sets the value of the familyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFamilyName(String value) {
        this.familyName = value;
    }

    /**
     * Gets the value of the suffix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * Sets the value of the suffix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuffix(String value) {
        this.suffix = value;
    }

    /**
     * Gets the value of the applicationRole property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationRole() {
        return applicationRole;
    }

    /**
     * Sets the value of the applicationRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationRole(String value) {
        this.applicationRole = value;
    }

    /**
     * Gets the value of the dateOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the value of the dateOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateOfBirth(String value) {
        this.dateOfBirth = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGender(String value) {
        this.gender = value;
    }

    /**
     * Gets the value of the indiaMiddleName3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndiaMiddleName3() {
        return indiaMiddleName3;
    }

    /**
     * Sets the value of the indiaMiddleName3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndiaMiddleName3(String value) {
        this.indiaMiddleName3 = value;
    }

    /**
     * Gets the value of the indiaNameTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndiaNameTitle() {
        return indiaNameTitle;
    }

    /**
     * Sets the value of the indiaNameTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndiaNameTitle(String value) {
        this.indiaNameTitle = value;
    }

}
