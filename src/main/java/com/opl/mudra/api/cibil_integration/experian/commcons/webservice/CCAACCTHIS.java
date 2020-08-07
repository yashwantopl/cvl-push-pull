
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CCAACCTHIS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CCAACCTHIS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OldestWOAccountMonth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OldestWOAccountYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MostRecentWOAccountMonth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MostRecentWOAccountYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FirstCreditAccountMonth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FirstCreditAccountYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MostRecentCreditAccountMonth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MostRecentCreditAccountYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CCAACCTHIS", propOrder = {
    "segmentCode",
    "oldestWOAccountMonth",
    "oldestWOAccountYear",
    "mostRecentWOAccountMonth",
    "mostRecentWOAccountYear",
    "firstCreditAccountMonth",
    "firstCreditAccountYear",
    "mostRecentCreditAccountMonth",
    "mostRecentCreditAccountYear"
})
public class CCAACCTHIS {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "OldestWOAccountMonth")
    protected String oldestWOAccountMonth;
    @XmlElement(name = "OldestWOAccountYear")
    protected String oldestWOAccountYear;
    @XmlElement(name = "MostRecentWOAccountMonth")
    protected String mostRecentWOAccountMonth;
    @XmlElement(name = "MostRecentWOAccountYear")
    protected String mostRecentWOAccountYear;
    @XmlElement(name = "FirstCreditAccountMonth")
    protected String firstCreditAccountMonth;
    @XmlElement(name = "FirstCreditAccountYear")
    protected String firstCreditAccountYear;
    @XmlElement(name = "MostRecentCreditAccountMonth")
    protected String mostRecentCreditAccountMonth;
    @XmlElement(name = "MostRecentCreditAccountYear")
    protected String mostRecentCreditAccountYear;

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
     * Gets the value of the oldestWOAccountMonth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldestWOAccountMonth() {
        return oldestWOAccountMonth;
    }

    /**
     * Sets the value of the oldestWOAccountMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldestWOAccountMonth(String value) {
        this.oldestWOAccountMonth = value;
    }

    /**
     * Gets the value of the oldestWOAccountYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldestWOAccountYear() {
        return oldestWOAccountYear;
    }

    /**
     * Sets the value of the oldestWOAccountYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldestWOAccountYear(String value) {
        this.oldestWOAccountYear = value;
    }

    /**
     * Gets the value of the mostRecentWOAccountMonth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMostRecentWOAccountMonth() {
        return mostRecentWOAccountMonth;
    }

    /**
     * Sets the value of the mostRecentWOAccountMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMostRecentWOAccountMonth(String value) {
        this.mostRecentWOAccountMonth = value;
    }

    /**
     * Gets the value of the mostRecentWOAccountYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMostRecentWOAccountYear() {
        return mostRecentWOAccountYear;
    }

    /**
     * Sets the value of the mostRecentWOAccountYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMostRecentWOAccountYear(String value) {
        this.mostRecentWOAccountYear = value;
    }

    /**
     * Gets the value of the firstCreditAccountMonth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstCreditAccountMonth() {
        return firstCreditAccountMonth;
    }

    /**
     * Sets the value of the firstCreditAccountMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstCreditAccountMonth(String value) {
        this.firstCreditAccountMonth = value;
    }

    /**
     * Gets the value of the firstCreditAccountYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstCreditAccountYear() {
        return firstCreditAccountYear;
    }

    /**
     * Sets the value of the firstCreditAccountYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstCreditAccountYear(String value) {
        this.firstCreditAccountYear = value;
    }

    /**
     * Gets the value of the mostRecentCreditAccountMonth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMostRecentCreditAccountMonth() {
        return mostRecentCreditAccountMonth;
    }

    /**
     * Sets the value of the mostRecentCreditAccountMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMostRecentCreditAccountMonth(String value) {
        this.mostRecentCreditAccountMonth = value;
    }

    /**
     * Gets the value of the mostRecentCreditAccountYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMostRecentCreditAccountYear() {
        return mostRecentCreditAccountYear;
    }

    /**
     * Sets the value of the mostRecentCreditAccountYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMostRecentCreditAccountYear(String value) {
        this.mostRecentCreditAccountYear = value;
    }

}
