
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CURRCYSTAT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CURRCYSTAT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FundCurrentBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NonFundCurrentBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ShortTermsCurrentBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LongTermsCurrentBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WilfullDefaultCurrentBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SuitFiledCurrentBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CURRCYSTAT", propOrder = {
    "segmentCode",
    "fundCurrentBalance",
    "nonFundCurrentBalance",
    "shortTermsCurrentBalance",
    "longTermsCurrentBalance",
    "wilfullDefaultCurrentBalance",
    "suitFiledCurrentBalance"
})
public class CURRCYSTAT {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "FundCurrentBalance")
    protected String fundCurrentBalance;
    @XmlElement(name = "NonFundCurrentBalance")
    protected String nonFundCurrentBalance;
    @XmlElement(name = "ShortTermsCurrentBalance")
    protected String shortTermsCurrentBalance;
    @XmlElement(name = "LongTermsCurrentBalance")
    protected String longTermsCurrentBalance;
    @XmlElement(name = "WilfullDefaultCurrentBalance")
    protected String wilfullDefaultCurrentBalance;
    @XmlElement(name = "SuitFiledCurrentBalance")
    protected String suitFiledCurrentBalance;

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
     * Gets the value of the fundCurrentBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFundCurrentBalance() {
        return fundCurrentBalance;
    }

    /**
     * Sets the value of the fundCurrentBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFundCurrentBalance(String value) {
        this.fundCurrentBalance = value;
    }

    /**
     * Gets the value of the nonFundCurrentBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNonFundCurrentBalance() {
        return nonFundCurrentBalance;
    }

    /**
     * Sets the value of the nonFundCurrentBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNonFundCurrentBalance(String value) {
        this.nonFundCurrentBalance = value;
    }

    /**
     * Gets the value of the shortTermsCurrentBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortTermsCurrentBalance() {
        return shortTermsCurrentBalance;
    }

    /**
     * Sets the value of the shortTermsCurrentBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortTermsCurrentBalance(String value) {
        this.shortTermsCurrentBalance = value;
    }

    /**
     * Gets the value of the longTermsCurrentBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongTermsCurrentBalance() {
        return longTermsCurrentBalance;
    }

    /**
     * Sets the value of the longTermsCurrentBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongTermsCurrentBalance(String value) {
        this.longTermsCurrentBalance = value;
    }

    /**
     * Gets the value of the wilfullDefaultCurrentBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWilfullDefaultCurrentBalance() {
        return wilfullDefaultCurrentBalance;
    }

    /**
     * Sets the value of the wilfullDefaultCurrentBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWilfullDefaultCurrentBalance(String value) {
        this.wilfullDefaultCurrentBalance = value;
    }

    /**
     * Gets the value of the suitFiledCurrentBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuitFiledCurrentBalance() {
        return suitFiledCurrentBalance;
    }

    /**
     * Sets the value of the suitFiledCurrentBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuitFiledCurrentBalance(String value) {
        this.suitFiledCurrentBalance = value;
    }

}
