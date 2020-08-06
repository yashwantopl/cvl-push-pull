
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RecentOpenDate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RecentOpenDate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecentOpenDtSelf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecentOpenDtSelfActive" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecentOpenDtPSU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecentOpenDtPVT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecentOpenDtNBFC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecentOpenDtNonSelf" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecentOpenDt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecentOpenDate", propOrder = {
    "segmentCode",
    "recentOpenDtSelf",
    "recentOpenDtSelfActive",
    "recentOpenDtPSU",
    "recentOpenDtPVT",
    "recentOpenDtNBFC",
    "recentOpenDtNonSelf",
    "recentOpenDt"
})
public class RecentOpenDate {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "RecentOpenDtSelf")
    protected String recentOpenDtSelf;
    @XmlElement(name = "RecentOpenDtSelfActive")
    protected String recentOpenDtSelfActive;
    @XmlElement(name = "RecentOpenDtPSU")
    protected String recentOpenDtPSU;
    @XmlElement(name = "RecentOpenDtPVT")
    protected String recentOpenDtPVT;
    @XmlElement(name = "RecentOpenDtNBFC")
    protected String recentOpenDtNBFC;
    @XmlElement(name = "RecentOpenDtNonSelf")
    protected String recentOpenDtNonSelf;
    @XmlElement(name = "RecentOpenDt")
    protected String recentOpenDt;

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
     * Gets the value of the recentOpenDtSelf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecentOpenDtSelf() {
        return recentOpenDtSelf;
    }

    /**
     * Sets the value of the recentOpenDtSelf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecentOpenDtSelf(String value) {
        this.recentOpenDtSelf = value;
    }

    /**
     * Gets the value of the recentOpenDtSelfActive property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecentOpenDtSelfActive() {
        return recentOpenDtSelfActive;
    }

    /**
     * Sets the value of the recentOpenDtSelfActive property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecentOpenDtSelfActive(String value) {
        this.recentOpenDtSelfActive = value;
    }

    /**
     * Gets the value of the recentOpenDtPSU property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecentOpenDtPSU() {
        return recentOpenDtPSU;
    }

    /**
     * Sets the value of the recentOpenDtPSU property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecentOpenDtPSU(String value) {
        this.recentOpenDtPSU = value;
    }

    /**
     * Gets the value of the recentOpenDtPVT property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecentOpenDtPVT() {
        return recentOpenDtPVT;
    }

    /**
     * Sets the value of the recentOpenDtPVT property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecentOpenDtPVT(String value) {
        this.recentOpenDtPVT = value;
    }

    /**
     * Gets the value of the recentOpenDtNBFC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecentOpenDtNBFC() {
        return recentOpenDtNBFC;
    }

    /**
     * Sets the value of the recentOpenDtNBFC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecentOpenDtNBFC(String value) {
        this.recentOpenDtNBFC = value;
    }

    /**
     * Gets the value of the recentOpenDtNonSelf property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecentOpenDtNonSelf() {
        return recentOpenDtNonSelf;
    }

    /**
     * Sets the value of the recentOpenDtNonSelf property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecentOpenDtNonSelf(String value) {
        this.recentOpenDtNonSelf = value;
    }

    /**
     * Gets the value of the recentOpenDt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecentOpenDt() {
        return recentOpenDt;
    }

    /**
     * Sets the value of the recentOpenDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecentOpenDt(String value) {
        this.recentOpenDt = value;
    }

}
