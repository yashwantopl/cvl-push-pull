
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for PRSNSRCH complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PRSNSRCH">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FirstGivenName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MiddleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OtherMiddleNames" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiaMiddleName3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FamilyName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Relationship" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DateOfBirth" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Gender" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MaritalStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PERSONID" type="{http://webservice.commcons.experian.com}PERSONID" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PERSADDR" type="{http://webservice.commcons.experian.com}PERSADDR" maxOccurs="3" minOccurs="0"/>
 *         &lt;element name="PERSPHONE" type="{http://webservice.commcons.experian.com}PERSPHONE" maxOccurs="3" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PRSNSRCH", propOrder = {
    "firstGivenName",
    "middleName",
    "otherMiddleNames",
    "indiaMiddleName3",
    "familyName",
    "relationship",
    "dateOfBirth",
    "gender",
    "maritalStatus",
    "personid",
    "persaddr",
    "persphone"
})
public class PRSNSRCH {

    @XmlElement(name = "FirstGivenName", required = true)
    protected String firstGivenName;
    @XmlElement(name = "MiddleName")
    protected String middleName;
    @XmlElement(name = "OtherMiddleNames")
    protected String otherMiddleNames;
    @XmlElement(name = "IndiaMiddleName3")
    protected String indiaMiddleName3;
    @XmlElement(name = "FamilyName", required = true)
    protected String familyName;
    @XmlElement(name = "Relationship")
    protected String relationship;
    @XmlElement(name = "DateOfBirth", required = true)
    protected String dateOfBirth;
    @XmlElement(name = "Gender", required = true)
    protected String gender;
    @XmlElement(name = "MaritalStatus")
    protected String maritalStatus;
    @XmlElement(name = "PERSONID")
    protected List<PERSONID> personid;
    @XmlElement(name = "PERSADDR")
    protected List<PERSADDR> persaddr;
    @XmlElement(name = "PERSPHONE")
    protected List<PERSPHONE> persphone;

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
     * Gets the value of the personid property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the personid property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPERSONID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PERSONID }
     * 
     * 
     */
    public List<PERSONID> getPERSONID() {
        if (personid == null) {
            personid = new ArrayList<PERSONID>();
        }
        return this.personid;
    }

    /**
     * Gets the value of the persaddr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the persaddr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPERSADDR().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PERSADDR }
     * 
     * 
     */
    public List<PERSADDR> getPERSADDR() {
        if (persaddr == null) {
            persaddr = new ArrayList<PERSADDR>();
        }
        return this.persaddr;
    }

    /**
     * Gets the value of the persphone property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the persphone property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPERSPHONE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PERSPHONE }
     * 
     * 
     */
    public List<PERSPHONE> getPERSPHONE() {
        if (persphone == null) {
            persphone = new ArrayList<PERSPHONE>();
        }
        return this.persphone;
    }

}
