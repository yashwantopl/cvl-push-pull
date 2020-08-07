
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CURRCYCNT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CURRCYCNT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CurrencyCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalNoFundedAccountType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalNoNonFundedAccountType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalNoShortTermAccountType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalNoLongTermAccountType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalNoWDAccountType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalNoSFAccountType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalNoDISHNR_CHQ" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CURRCYCNT", propOrder = {
    "segmentCode",
    "currencyCd",
    "totalNoFundedAccountType",
    "totalNoNonFundedAccountType",
    "totalNoShortTermAccountType",
    "totalNoLongTermAccountType",
    "totalNoWDAccountType",
    "totalNoSFAccountType",
    "totalNoDISHNRCHQ"
})
public class CURRCYCNT {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "CurrencyCd")
    protected String currencyCd;
    @XmlElement(name = "TotalNoFundedAccountType")
    protected String totalNoFundedAccountType;
    @XmlElement(name = "TotalNoNonFundedAccountType")
    protected String totalNoNonFundedAccountType;
    @XmlElement(name = "TotalNoShortTermAccountType")
    protected String totalNoShortTermAccountType;
    @XmlElement(name = "TotalNoLongTermAccountType")
    protected String totalNoLongTermAccountType;
    @XmlElement(name = "TotalNoWDAccountType")
    protected String totalNoWDAccountType;
    @XmlElement(name = "TotalNoSFAccountType")
    protected String totalNoSFAccountType;
    @XmlElement(name = "TotalNoDISHNR_CHQ")
    protected String totalNoDISHNRCHQ;

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
     * Gets the value of the currencyCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCd() {
        return currencyCd;
    }

    /**
     * Sets the value of the currencyCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCd(String value) {
        this.currencyCd = value;
    }

    /**
     * Gets the value of the totalNoFundedAccountType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalNoFundedAccountType() {
        return totalNoFundedAccountType;
    }

    /**
     * Sets the value of the totalNoFundedAccountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalNoFundedAccountType(String value) {
        this.totalNoFundedAccountType = value;
    }

    /**
     * Gets the value of the totalNoNonFundedAccountType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalNoNonFundedAccountType() {
        return totalNoNonFundedAccountType;
    }

    /**
     * Sets the value of the totalNoNonFundedAccountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalNoNonFundedAccountType(String value) {
        this.totalNoNonFundedAccountType = value;
    }

    /**
     * Gets the value of the totalNoShortTermAccountType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalNoShortTermAccountType() {
        return totalNoShortTermAccountType;
    }

    /**
     * Sets the value of the totalNoShortTermAccountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalNoShortTermAccountType(String value) {
        this.totalNoShortTermAccountType = value;
    }

    /**
     * Gets the value of the totalNoLongTermAccountType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalNoLongTermAccountType() {
        return totalNoLongTermAccountType;
    }

    /**
     * Sets the value of the totalNoLongTermAccountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalNoLongTermAccountType(String value) {
        this.totalNoLongTermAccountType = value;
    }

    /**
     * Gets the value of the totalNoWDAccountType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalNoWDAccountType() {
        return totalNoWDAccountType;
    }

    /**
     * Sets the value of the totalNoWDAccountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalNoWDAccountType(String value) {
        this.totalNoWDAccountType = value;
    }

    /**
     * Gets the value of the totalNoSFAccountType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalNoSFAccountType() {
        return totalNoSFAccountType;
    }

    /**
     * Sets the value of the totalNoSFAccountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalNoSFAccountType(String value) {
        this.totalNoSFAccountType = value;
    }

    /**
     * Gets the value of the totalNoDISHNRCHQ property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalNoDISHNRCHQ() {
        return totalNoDISHNRCHQ;
    }

    /**
     * Sets the value of the totalNoDISHNRCHQ property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalNoDISHNRCHQ(String value) {
        this.totalNoDISHNRCHQ = value;
    }

}
