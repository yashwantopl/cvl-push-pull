
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BALDerivativesNonSelf complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BALDerivativesNonSelf">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBALAccts_ACCtype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBALAccts_0DPD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBALAccts_1_30DPD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBALAccts_31_60DPD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBALAccts_61_90DPD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBALAccts_91_365DPD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBALAccts_366_730DPD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBALoAccts_731_900DPD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBALAccts_STD_Asset" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBALAccts_SUB_Asset" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBALAccts_DBT_Asset" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBALAccts_SMA_Asset" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotBALAccts_LSS_Asset" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BALDerivativesNonSelf", propOrder = {
    "segmentCode",
    "totBALAcctsACCtype",
    "totBALAccts0DPD",
    "totBALAccts130DPD",
    "totBALAccts3160DPD",
    "totBALAccts6190DPD",
    "totBALAccts91365DPD",
    "totBALAccts366730DPD",
    "totBALoAccts731900DPD",
    "totBALAcctsSTDAsset",
    "totBALAcctsSUBAsset",
    "totBALAcctsDBTAsset",
    "totBALAcctsSMAAsset",
    "totBALAcctsLSSAsset"
})
public class BALDerivativesNonSelf {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "TotBALAccts_ACCtype")
    protected String totBALAcctsACCtype;
    @XmlElement(name = "TotBALAccts_0DPD")
    protected String totBALAccts0DPD;
    @XmlElement(name = "TotBALAccts_1_30DPD")
    protected String totBALAccts130DPD;
    @XmlElement(name = "TotBALAccts_31_60DPD")
    protected String totBALAccts3160DPD;
    @XmlElement(name = "TotBALAccts_61_90DPD")
    protected String totBALAccts6190DPD;
    @XmlElement(name = "TotBALAccts_91_365DPD")
    protected String totBALAccts91365DPD;
    @XmlElement(name = "TotBALAccts_366_730DPD")
    protected String totBALAccts366730DPD;
    @XmlElement(name = "TotBALoAccts_731_900DPD")
    protected String totBALoAccts731900DPD;
    @XmlElement(name = "TotBALAccts_STD_Asset")
    protected String totBALAcctsSTDAsset;
    @XmlElement(name = "TotBALAccts_SUB_Asset")
    protected String totBALAcctsSUBAsset;
    @XmlElement(name = "TotBALAccts_DBT_Asset")
    protected String totBALAcctsDBTAsset;
    @XmlElement(name = "TotBALAccts_SMA_Asset")
    protected String totBALAcctsSMAAsset;
    @XmlElement(name = "TotBALAccts_LSS_Asset")
    protected String totBALAcctsLSSAsset;

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
     * Gets the value of the totBALAcctsACCtype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBALAcctsACCtype() {
        return totBALAcctsACCtype;
    }

    /**
     * Sets the value of the totBALAcctsACCtype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBALAcctsACCtype(String value) {
        this.totBALAcctsACCtype = value;
    }

    /**
     * Gets the value of the totBALAccts0DPD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBALAccts0DPD() {
        return totBALAccts0DPD;
    }

    /**
     * Sets the value of the totBALAccts0DPD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBALAccts0DPD(String value) {
        this.totBALAccts0DPD = value;
    }

    /**
     * Gets the value of the totBALAccts130DPD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBALAccts130DPD() {
        return totBALAccts130DPD;
    }

    /**
     * Sets the value of the totBALAccts130DPD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBALAccts130DPD(String value) {
        this.totBALAccts130DPD = value;
    }

    /**
     * Gets the value of the totBALAccts3160DPD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBALAccts3160DPD() {
        return totBALAccts3160DPD;
    }

    /**
     * Sets the value of the totBALAccts3160DPD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBALAccts3160DPD(String value) {
        this.totBALAccts3160DPD = value;
    }

    /**
     * Gets the value of the totBALAccts6190DPD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBALAccts6190DPD() {
        return totBALAccts6190DPD;
    }

    /**
     * Sets the value of the totBALAccts6190DPD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBALAccts6190DPD(String value) {
        this.totBALAccts6190DPD = value;
    }

    /**
     * Gets the value of the totBALAccts91365DPD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBALAccts91365DPD() {
        return totBALAccts91365DPD;
    }

    /**
     * Sets the value of the totBALAccts91365DPD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBALAccts91365DPD(String value) {
        this.totBALAccts91365DPD = value;
    }

    /**
     * Gets the value of the totBALAccts366730DPD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBALAccts366730DPD() {
        return totBALAccts366730DPD;
    }

    /**
     * Sets the value of the totBALAccts366730DPD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBALAccts366730DPD(String value) {
        this.totBALAccts366730DPD = value;
    }

    /**
     * Gets the value of the totBALoAccts731900DPD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBALoAccts731900DPD() {
        return totBALoAccts731900DPD;
    }

    /**
     * Sets the value of the totBALoAccts731900DPD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBALoAccts731900DPD(String value) {
        this.totBALoAccts731900DPD = value;
    }

    /**
     * Gets the value of the totBALAcctsSTDAsset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBALAcctsSTDAsset() {
        return totBALAcctsSTDAsset;
    }

    /**
     * Sets the value of the totBALAcctsSTDAsset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBALAcctsSTDAsset(String value) {
        this.totBALAcctsSTDAsset = value;
    }

    /**
     * Gets the value of the totBALAcctsSUBAsset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBALAcctsSUBAsset() {
        return totBALAcctsSUBAsset;
    }

    /**
     * Sets the value of the totBALAcctsSUBAsset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBALAcctsSUBAsset(String value) {
        this.totBALAcctsSUBAsset = value;
    }

    /**
     * Gets the value of the totBALAcctsDBTAsset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBALAcctsDBTAsset() {
        return totBALAcctsDBTAsset;
    }

    /**
     * Sets the value of the totBALAcctsDBTAsset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBALAcctsDBTAsset(String value) {
        this.totBALAcctsDBTAsset = value;
    }

    /**
     * Gets the value of the totBALAcctsSMAAsset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBALAcctsSMAAsset() {
        return totBALAcctsSMAAsset;
    }

    /**
     * Sets the value of the totBALAcctsSMAAsset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBALAcctsSMAAsset(String value) {
        this.totBALAcctsSMAAsset = value;
    }

    /**
     * Gets the value of the totBALAcctsLSSAsset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotBALAcctsLSSAsset() {
        return totBALAcctsLSSAsset;
    }

    /**
     * Sets the value of the totBALAcctsLSSAsset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotBALAcctsLSSAsset(String value) {
        this.totBALAcctsLSSAsset = value;
    }

}
