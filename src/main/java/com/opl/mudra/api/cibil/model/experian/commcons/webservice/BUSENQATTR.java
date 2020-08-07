
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BUSENQATTR complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BUSENQATTR">
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
 *         &lt;element name="TotEnq1month" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotEnq2-3month" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotEnq4-6month" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotEnq7-12month" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotEnq12-24month" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotEnqAbove24month" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OthEnq1month" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OthEnq2-3month" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OthEnq4-6month" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OthEnq7-12month" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OthEnq12-24month" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OthEnqAbove24month" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OthMostRecentEnqDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OwnEnq1month" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OwnEnq2-3month" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OwnEnq4-6month" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OwnEnq7-12month" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OwnEnq12-24month" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OwnEnqAbove24month" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OwnMostRecentEnqDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BUSENQATTR", propOrder = {
    "segmentCode",
    "mostRecentEnqDate",
    "totEnq",
    "totEnq7Days",
    "totEnq30Days",
    "totEnq90Days",
    "totEnq180Days",
    "totEnq1Month",
    "totEnq23Month",
    "totEnq46Month",
    "totEnq712Month",
    "totEnq1224Month",
    "totEnqAbove24Month",
    "othEnq1Month",
    "othEnq23Month",
    "othEnq46Month",
    "othEnq712Month",
    "othEnq1224Month",
    "othEnqAbove24Month",
    "othMostRecentEnqDate",
    "ownEnq1Month",
    "ownEnq23Month",
    "ownEnq46Month",
    "ownEnq712Month",
    "ownEnq1224Month",
    "ownEnqAbove24Month",
    "ownMostRecentEnqDate"
})
public class BUSENQATTR {

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
    @XmlElement(name = "TotEnq1month")
    protected String totEnq1Month;
    @XmlElement(name = "TotEnq2-3month")
    protected String totEnq23Month;
    @XmlElement(name = "TotEnq4-6month")
    protected String totEnq46Month;
    @XmlElement(name = "TotEnq7-12month")
    protected String totEnq712Month;
    @XmlElement(name = "TotEnq12-24month")
    protected String totEnq1224Month;
    @XmlElement(name = "TotEnqAbove24month")
    protected String totEnqAbove24Month;
    @XmlElement(name = "OthEnq1month")
    protected String othEnq1Month;
    @XmlElement(name = "OthEnq2-3month")
    protected String othEnq23Month;
    @XmlElement(name = "OthEnq4-6month")
    protected String othEnq46Month;
    @XmlElement(name = "OthEnq7-12month")
    protected String othEnq712Month;
    @XmlElement(name = "OthEnq12-24month")
    protected String othEnq1224Month;
    @XmlElement(name = "OthEnqAbove24month")
    protected String othEnqAbove24Month;
    @XmlElement(name = "OthMostRecentEnqDate")
    protected String othMostRecentEnqDate;
    @XmlElement(name = "OwnEnq1month")
    protected String ownEnq1Month;
    @XmlElement(name = "OwnEnq2-3month")
    protected String ownEnq23Month;
    @XmlElement(name = "OwnEnq4-6month")
    protected String ownEnq46Month;
    @XmlElement(name = "OwnEnq7-12month")
    protected String ownEnq712Month;
    @XmlElement(name = "OwnEnq12-24month")
    protected String ownEnq1224Month;
    @XmlElement(name = "OwnEnqAbove24month")
    protected String ownEnqAbove24Month;
    @XmlElement(name = "OwnMostRecentEnqDate")
    protected String ownMostRecentEnqDate;

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

    /**
     * Gets the value of the totEnq1Month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotEnq1Month() {
        return totEnq1Month;
    }

    /**
     * Sets the value of the totEnq1Month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotEnq1Month(String value) {
        this.totEnq1Month = value;
    }

    /**
     * Gets the value of the totEnq23Month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotEnq23Month() {
        return totEnq23Month;
    }

    /**
     * Sets the value of the totEnq23Month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotEnq23Month(String value) {
        this.totEnq23Month = value;
    }

    /**
     * Gets the value of the totEnq46Month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotEnq46Month() {
        return totEnq46Month;
    }

    /**
     * Sets the value of the totEnq46Month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotEnq46Month(String value) {
        this.totEnq46Month = value;
    }

    /**
     * Gets the value of the totEnq712Month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotEnq712Month() {
        return totEnq712Month;
    }

    /**
     * Sets the value of the totEnq712Month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotEnq712Month(String value) {
        this.totEnq712Month = value;
    }

    /**
     * Gets the value of the totEnq1224Month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotEnq1224Month() {
        return totEnq1224Month;
    }

    /**
     * Sets the value of the totEnq1224Month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotEnq1224Month(String value) {
        this.totEnq1224Month = value;
    }

    /**
     * Gets the value of the totEnqAbove24Month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotEnqAbove24Month() {
        return totEnqAbove24Month;
    }

    /**
     * Sets the value of the totEnqAbove24Month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotEnqAbove24Month(String value) {
        this.totEnqAbove24Month = value;
    }

    /**
     * Gets the value of the othEnq1Month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOthEnq1Month() {
        return othEnq1Month;
    }

    /**
     * Sets the value of the othEnq1Month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOthEnq1Month(String value) {
        this.othEnq1Month = value;
    }

    /**
     * Gets the value of the othEnq23Month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOthEnq23Month() {
        return othEnq23Month;
    }

    /**
     * Sets the value of the othEnq23Month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOthEnq23Month(String value) {
        this.othEnq23Month = value;
    }

    /**
     * Gets the value of the othEnq46Month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOthEnq46Month() {
        return othEnq46Month;
    }

    /**
     * Sets the value of the othEnq46Month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOthEnq46Month(String value) {
        this.othEnq46Month = value;
    }

    /**
     * Gets the value of the othEnq712Month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOthEnq712Month() {
        return othEnq712Month;
    }

    /**
     * Sets the value of the othEnq712Month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOthEnq712Month(String value) {
        this.othEnq712Month = value;
    }

    /**
     * Gets the value of the othEnq1224Month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOthEnq1224Month() {
        return othEnq1224Month;
    }

    /**
     * Sets the value of the othEnq1224Month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOthEnq1224Month(String value) {
        this.othEnq1224Month = value;
    }

    /**
     * Gets the value of the othEnqAbove24Month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOthEnqAbove24Month() {
        return othEnqAbove24Month;
    }

    /**
     * Sets the value of the othEnqAbove24Month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOthEnqAbove24Month(String value) {
        this.othEnqAbove24Month = value;
    }

    /**
     * Gets the value of the othMostRecentEnqDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOthMostRecentEnqDate() {
        return othMostRecentEnqDate;
    }

    /**
     * Sets the value of the othMostRecentEnqDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOthMostRecentEnqDate(String value) {
        this.othMostRecentEnqDate = value;
    }

    /**
     * Gets the value of the ownEnq1Month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnEnq1Month() {
        return ownEnq1Month;
    }

    /**
     * Sets the value of the ownEnq1Month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnEnq1Month(String value) {
        this.ownEnq1Month = value;
    }

    /**
     * Gets the value of the ownEnq23Month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnEnq23Month() {
        return ownEnq23Month;
    }

    /**
     * Sets the value of the ownEnq23Month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnEnq23Month(String value) {
        this.ownEnq23Month = value;
    }

    /**
     * Gets the value of the ownEnq46Month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnEnq46Month() {
        return ownEnq46Month;
    }

    /**
     * Sets the value of the ownEnq46Month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnEnq46Month(String value) {
        this.ownEnq46Month = value;
    }

    /**
     * Gets the value of the ownEnq712Month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnEnq712Month() {
        return ownEnq712Month;
    }

    /**
     * Sets the value of the ownEnq712Month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnEnq712Month(String value) {
        this.ownEnq712Month = value;
    }

    /**
     * Gets the value of the ownEnq1224Month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnEnq1224Month() {
        return ownEnq1224Month;
    }

    /**
     * Sets the value of the ownEnq1224Month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnEnq1224Month(String value) {
        this.ownEnq1224Month = value;
    }

    /**
     * Gets the value of the ownEnqAbove24Month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnEnqAbove24Month() {
        return ownEnqAbove24Month;
    }

    /**
     * Sets the value of the ownEnqAbove24Month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnEnqAbove24Month(String value) {
        this.ownEnqAbove24Month = value;
    }

    /**
     * Gets the value of the ownMostRecentEnqDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnMostRecentEnqDate() {
        return ownMostRecentEnqDate;
    }

    /**
     * Sets the value of the ownMostRecentEnqDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnMostRecentEnqDate(String value) {
        this.ownMostRecentEnqDate = value;
    }

}
