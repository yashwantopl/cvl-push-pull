
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DPDDerivativesSelf complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DPDDerivativesSelf">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoAccts_ACCtype" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoAccts_0DPD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoAccts_1_30DPD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoAccts_31_60DPD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoAccts_61_90DPD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoAccts_91_365DPD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoAccts_366_730DPD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoAccts_731_900DPD" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNNoAccts_STD_Asset" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoAccts_SUB_Asset" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoAccts_DBT_Asset" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoAccts_SMA_Asset" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotNoAccts_LSS_Asset" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DPDDerivativesSelf", propOrder = {
    "segmentCode",
    "totNoAcctsACCtype",
    "totNoAccts0DPD",
    "totNoAccts130DPD",
    "totNoAccts3160DPD",
    "totNoAccts6190DPD",
    "totNoAccts91365DPD",
    "totNoAccts366730DPD",
    "totNoAccts731900DPD",
    "totNNoAcctsSTDAsset",
    "totNoAcctsSUBAsset",
    "totNoAcctsDBTAsset",
    "totNoAcctsSMAAsset",
    "totNoAcctsLSSAsset"
})
public class DPDDerivativesSelf {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "TotNoAccts_ACCtype")
    protected String totNoAcctsACCtype;
    @XmlElement(name = "TotNoAccts_0DPD")
    protected String totNoAccts0DPD;
    @XmlElement(name = "TotNoAccts_1_30DPD")
    protected String totNoAccts130DPD;
    @XmlElement(name = "TotNoAccts_31_60DPD")
    protected String totNoAccts3160DPD;
    @XmlElement(name = "TotNoAccts_61_90DPD")
    protected String totNoAccts6190DPD;
    @XmlElement(name = "TotNoAccts_91_365DPD")
    protected String totNoAccts91365DPD;
    @XmlElement(name = "TotNoAccts_366_730DPD")
    protected String totNoAccts366730DPD;
    @XmlElement(name = "TotNoAccts_731_900DPD")
    protected String totNoAccts731900DPD;
    @XmlElement(name = "TotNNoAccts_STD_Asset")
    protected String totNNoAcctsSTDAsset;
    @XmlElement(name = "TotNoAccts_SUB_Asset")
    protected String totNoAcctsSUBAsset;
    @XmlElement(name = "TotNoAccts_DBT_Asset")
    protected String totNoAcctsDBTAsset;
    @XmlElement(name = "TotNoAccts_SMA_Asset")
    protected String totNoAcctsSMAAsset;
    @XmlElement(name = "TotNoAccts_LSS_Asset")
    protected String totNoAcctsLSSAsset;

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
     * Gets the value of the totNoAcctsACCtype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoAcctsACCtype() {
        return totNoAcctsACCtype;
    }

    /**
     * Sets the value of the totNoAcctsACCtype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoAcctsACCtype(String value) {
        this.totNoAcctsACCtype = value;
    }

    /**
     * Gets the value of the totNoAccts0DPD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoAccts0DPD() {
        return totNoAccts0DPD;
    }

    /**
     * Sets the value of the totNoAccts0DPD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoAccts0DPD(String value) {
        this.totNoAccts0DPD = value;
    }

    /**
     * Gets the value of the totNoAccts130DPD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoAccts130DPD() {
        return totNoAccts130DPD;
    }

    /**
     * Sets the value of the totNoAccts130DPD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoAccts130DPD(String value) {
        this.totNoAccts130DPD = value;
    }

    /**
     * Gets the value of the totNoAccts3160DPD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoAccts3160DPD() {
        return totNoAccts3160DPD;
    }

    /**
     * Sets the value of the totNoAccts3160DPD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoAccts3160DPD(String value) {
        this.totNoAccts3160DPD = value;
    }

    /**
     * Gets the value of the totNoAccts6190DPD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoAccts6190DPD() {
        return totNoAccts6190DPD;
    }

    /**
     * Sets the value of the totNoAccts6190DPD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoAccts6190DPD(String value) {
        this.totNoAccts6190DPD = value;
    }

    /**
     * Gets the value of the totNoAccts91365DPD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoAccts91365DPD() {
        return totNoAccts91365DPD;
    }

    /**
     * Sets the value of the totNoAccts91365DPD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoAccts91365DPD(String value) {
        this.totNoAccts91365DPD = value;
    }

    /**
     * Gets the value of the totNoAccts366730DPD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoAccts366730DPD() {
        return totNoAccts366730DPD;
    }

    /**
     * Sets the value of the totNoAccts366730DPD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoAccts366730DPD(String value) {
        this.totNoAccts366730DPD = value;
    }

    /**
     * Gets the value of the totNoAccts731900DPD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoAccts731900DPD() {
        return totNoAccts731900DPD;
    }

    /**
     * Sets the value of the totNoAccts731900DPD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoAccts731900DPD(String value) {
        this.totNoAccts731900DPD = value;
    }

    /**
     * Gets the value of the totNNoAcctsSTDAsset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNNoAcctsSTDAsset() {
        return totNNoAcctsSTDAsset;
    }

    /**
     * Sets the value of the totNNoAcctsSTDAsset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNNoAcctsSTDAsset(String value) {
        this.totNNoAcctsSTDAsset = value;
    }

    /**
     * Gets the value of the totNoAcctsSUBAsset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoAcctsSUBAsset() {
        return totNoAcctsSUBAsset;
    }

    /**
     * Sets the value of the totNoAcctsSUBAsset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoAcctsSUBAsset(String value) {
        this.totNoAcctsSUBAsset = value;
    }

    /**
     * Gets the value of the totNoAcctsDBTAsset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoAcctsDBTAsset() {
        return totNoAcctsDBTAsset;
    }

    /**
     * Sets the value of the totNoAcctsDBTAsset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoAcctsDBTAsset(String value) {
        this.totNoAcctsDBTAsset = value;
    }

    /**
     * Gets the value of the totNoAcctsSMAAsset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoAcctsSMAAsset() {
        return totNoAcctsSMAAsset;
    }

    /**
     * Sets the value of the totNoAcctsSMAAsset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoAcctsSMAAsset(String value) {
        this.totNoAcctsSMAAsset = value;
    }

    /**
     * Gets the value of the totNoAcctsLSSAsset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotNoAcctsLSSAsset() {
        return totNoAcctsLSSAsset;
    }

    /**
     * Sets the value of the totNoAcctsLSSAsset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotNoAcctsLSSAsset(String value) {
        this.totNoAcctsLSSAsset = value;
    }

}
