
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for PERINPUT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PERINPUT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FirstGivenName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MiddleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OtherMiddleNames" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiaMiddleName3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FamilyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BirthYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BirthMonth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BirthDay" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Gender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MaritalStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Relationship" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PERINPADDR" type="{http://webservice.commcons.experian.com}PERINPADDR" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PERINPPHN" type="{http://webservice.commcons.experian.com}PERINPPHN" maxOccurs="3" minOccurs="0"/>
 *         &lt;element name="PERINPIDC" type="{http://webservice.commcons.experian.com}PERINPIDC" maxOccurs="6" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PERINPUT", propOrder = {
    "segmentCode",
    "firstGivenName",
    "middleName",
    "otherMiddleNames",
    "indiaMiddleName3",
    "familyName",
    "birthYear",
    "birthMonth",
    "birthDay",
    "gender",
    "maritalStatus",
    "relationship",
    "perinpaddr",
    "perinpphn",
    "perinpidc"
})
public class PERINPUT {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "FirstGivenName")
    protected String firstGivenName;
    @XmlElement(name = "MiddleName")
    protected String middleName;
    @XmlElement(name = "OtherMiddleNames")
    protected String otherMiddleNames;
    @XmlElement(name = "IndiaMiddleName3")
    protected String indiaMiddleName3;
    @XmlElement(name = "FamilyName")
    protected String familyName;
    @XmlElement(name = "BirthYear")
    protected String birthYear;
    @XmlElement(name = "BirthMonth")
    protected String birthMonth;
    @XmlElement(name = "BirthDay")
    protected String birthDay;
    @XmlElement(name = "Gender")
    protected String gender;
    @XmlElement(name = "MaritalStatus")
    protected String maritalStatus;
    @XmlElement(name = "Relationship")
    protected String relationship;
    @XmlElement(name = "PERINPADDR")
    protected List<PERINPADDR> perinpaddr;
    @XmlElement(name = "PERINPPHN")
    protected List<PERINPPHN> perinpphn;
    @XmlElement(name = "PERINPIDC")
    protected List<PERINPIDC> perinpidc;

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
     * Gets the value of the birthYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthYear() {
        return birthYear;
    }

    /**
     * Sets the value of the birthYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthYear(String value) {
        this.birthYear = value;
    }

    /**
     * Gets the value of the birthMonth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthMonth() {
        return birthMonth;
    }

    /**
     * Sets the value of the birthMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthMonth(String value) {
        this.birthMonth = value;
    }

    /**
     * Gets the value of the birthDay property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBirthDay() {
        return birthDay;
    }

    /**
     * Sets the value of the birthDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthDay(String value) {
        this.birthDay = value;
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
     * Gets the value of the maritalStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * Sets the value of the maritalStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaritalStatus(String value) {
        this.maritalStatus = value;
    }

    /**
     * Gets the value of the relationship property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * Sets the value of the relationship property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelationship(String value) {
        this.relationship = value;
    }

    /**
     * Gets the value of the perinpaddr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the perinpaddr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPERINPADDR().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PERINPADDR }
     * 
     * 
     */
    public List<PERINPADDR> getPERINPADDR() {
        if (perinpaddr == null) {
            perinpaddr = new ArrayList<PERINPADDR>();
        }
        return this.perinpaddr;
    }

    /**
     * Gets the value of the perinpphn property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the perinpphn property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPERINPPHN().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PERINPPHN }
     * 
     * 
     */
    public List<PERINPPHN> getPERINPPHN() {
        if (perinpphn == null) {
            perinpphn = new ArrayList<PERINPPHN>();
        }
        return this.perinpphn;
    }

    /**
     * Gets the value of the perinpidc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the perinpidc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPERINPIDC().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PERINPIDC }
     * 
     * 
     */
    public List<PERINPIDC> getPERINPIDC() {
        if (perinpidc == null) {
            perinpidc = new ArrayList<PERINPIDC>();
        }
        return this.perinpidc;
    }

}
