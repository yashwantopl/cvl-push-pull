
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for COMMBRPT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="COMMBRPT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BurProductSearchResultCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BurProductHitNoHitInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BurProductValueAddInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BurRptNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BurRptTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BurRptDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BurRptTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BurRptLanguageCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "COMMBRPT", propOrder = {
    "segmentCode",
    "burProductSearchResultCd",
    "burProductHitNoHitInd",
    "burProductValueAddInd",
    "burRptNum",
    "burRptTitle",
    "burRptDate",
    "burRptTime",
    "burRptLanguageCd"
})
public class COMMBRPT {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "BurProductSearchResultCd")
    protected String burProductSearchResultCd;
    @XmlElement(name = "BurProductHitNoHitInd")
    protected String burProductHitNoHitInd;
    @XmlElement(name = "BurProductValueAddInd")
    protected String burProductValueAddInd;
    @XmlElement(name = "BurRptNum")
    protected String burRptNum;
    @XmlElement(name = "BurRptTitle")
    protected String burRptTitle;
    @XmlElement(name = "BurRptDate")
    protected String burRptDate;
    @XmlElement(name = "BurRptTime")
    protected String burRptTime;
    @XmlElement(name = "BurRptLanguageCd")
    protected String burRptLanguageCd;

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
     * Gets the value of the burProductSearchResultCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBurProductSearchResultCd() {
        return burProductSearchResultCd;
    }

    /**
     * Sets the value of the burProductSearchResultCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBurProductSearchResultCd(String value) {
        this.burProductSearchResultCd = value;
    }

    /**
     * Gets the value of the burProductHitNoHitInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBurProductHitNoHitInd() {
        return burProductHitNoHitInd;
    }

    /**
     * Sets the value of the burProductHitNoHitInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBurProductHitNoHitInd(String value) {
        this.burProductHitNoHitInd = value;
    }

    /**
     * Gets the value of the burProductValueAddInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBurProductValueAddInd() {
        return burProductValueAddInd;
    }

    /**
     * Sets the value of the burProductValueAddInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBurProductValueAddInd(String value) {
        this.burProductValueAddInd = value;
    }

    /**
     * Gets the value of the burRptNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBurRptNum() {
        return burRptNum;
    }

    /**
     * Sets the value of the burRptNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBurRptNum(String value) {
        this.burRptNum = value;
    }

    /**
     * Gets the value of the burRptTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBurRptTitle() {
        return burRptTitle;
    }

    /**
     * Sets the value of the burRptTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBurRptTitle(String value) {
        this.burRptTitle = value;
    }

    /**
     * Gets the value of the burRptDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBurRptDate() {
        return burRptDate;
    }

    /**
     * Sets the value of the burRptDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBurRptDate(String value) {
        this.burRptDate = value;
    }

    /**
     * Gets the value of the burRptTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBurRptTime() {
        return burRptTime;
    }

    /**
     * Sets the value of the burRptTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBurRptTime(String value) {
        this.burRptTime = value;
    }

    /**
     * Gets the value of the burRptLanguageCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBurRptLanguageCd() {
        return burRptLanguageCd;
    }

    /**
     * Sets the value of the burRptLanguageCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBurRptLanguageCd(String value) {
        this.burRptLanguageCd = value;
    }

}
