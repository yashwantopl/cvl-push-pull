
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ENQATTR complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ENQATTR">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MostRecentEnqDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotEnq" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotEnq7days" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotEnq30days" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotEnq90days" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotEnq180days" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ENQATTR", propOrder = {
    "segmentCode",
    "mostRecentEnqDate",
    "totEnq",
    "totEnq7Days",
    "totEnq30Days",
    "totEnq90Days",
    "totEnq180Days"
})
public class ENQATTR {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "MostRecentEnqDate")
    protected String mostRecentEnqDate;
    @XmlElement(name = "TotEnq")
    protected String totEnq;
    @XmlElement(name = "TotEnq7days")
    protected String totEnq7Days;
    @XmlElement(name = "TotEnq30days")
    protected String totEnq30Days;
    @XmlElement(name = "TotEnq90days")
    protected String totEnq90Days;
    @XmlElement(name = "TotEnq180days")
    protected String totEnq180Days;

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
     * Gets the value of the mostRecentEnqDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMostRecentEnqDate() {
        return mostRecentEnqDate;
    }

    /**
     * Sets the value of the mostRecentEnqDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMostRecentEnqDate(String value) {
        this.mostRecentEnqDate = value;
    }

    /**
     * Gets the value of the totEnq property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotEnq() {
        return totEnq;
    }

    /**
     * Sets the value of the totEnq property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotEnq(String value) {
        this.totEnq = value;
    }

    /**
     * Gets the value of the totEnq7Days property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotEnq7Days() {
        return totEnq7Days;
    }

    /**
     * Sets the value of the totEnq7Days property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotEnq7Days(String value) {
        this.totEnq7Days = value;
    }

    /**
     * Gets the value of the totEnq30Days property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotEnq30Days() {
        return totEnq30Days;
    }

    /**
     * Sets the value of the totEnq30Days property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotEnq30Days(String value) {
        this.totEnq30Days = value;
    }

    /**
     * Gets the value of the totEnq90Days property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotEnq90Days() {
        return totEnq90Days;
    }

    /**
     * Sets the value of the totEnq90Days property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotEnq90Days(String value) {
        this.totEnq90Days = value;
    }

    /**
     * Gets the value of the totEnq180Days property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotEnq180Days() {
        return totEnq180Days;
    }

    /**
     * Sets the value of the totEnq180Days property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotEnq180Days(String value) {
        this.totEnq180Days = value;
    }

}
