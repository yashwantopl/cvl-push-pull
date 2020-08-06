
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CCAACCTBAL complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CCAACCTBAL">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotDefaultBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotOutstandingBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotSecuredBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PercSecuredBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotUnSecuredBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PercUnSecuredBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PercSFWDWOSTLAccnts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CCAACCTBAL", propOrder = {
    "segmentCode",
    "totDefaultBalance",
    "totOutstandingBalance",
    "totSecuredBalance",
    "percSecuredBalance",
    "totUnSecuredBalance",
    "percUnSecuredBalance",
    "percSFWDWOSTLAccnts"
})
public class CCAACCTBAL {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "TotDefaultBalance")
    protected String totDefaultBalance;
    @XmlElement(name = "TotOutstandingBalance")
    protected String totOutstandingBalance;
    @XmlElement(name = "TotSecuredBalance")
    protected String totSecuredBalance;
    @XmlElement(name = "PercSecuredBalance")
    protected String percSecuredBalance;
    @XmlElement(name = "TotUnSecuredBalance")
    protected String totUnSecuredBalance;
    @XmlElement(name = "PercUnSecuredBalance")
    protected String percUnSecuredBalance;
    @XmlElement(name = "PercSFWDWOSTLAccnts")
    protected String percSFWDWOSTLAccnts;

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
     * Gets the value of the totDefaultBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotDefaultBalance() {
        return totDefaultBalance;
    }

    /**
     * Sets the value of the totDefaultBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotDefaultBalance(String value) {
        this.totDefaultBalance = value;
    }

    /**
     * Gets the value of the totOutstandingBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotOutstandingBalance() {
        return totOutstandingBalance;
    }

    /**
     * Sets the value of the totOutstandingBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotOutstandingBalance(String value) {
        this.totOutstandingBalance = value;
    }

    /**
     * Gets the value of the totSecuredBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotSecuredBalance() {
        return totSecuredBalance;
    }

    /**
     * Sets the value of the totSecuredBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotSecuredBalance(String value) {
        this.totSecuredBalance = value;
    }

    /**
     * Gets the value of the percSecuredBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPercSecuredBalance() {
        return percSecuredBalance;
    }

    /**
     * Sets the value of the percSecuredBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPercSecuredBalance(String value) {
        this.percSecuredBalance = value;
    }

    /**
     * Gets the value of the totUnSecuredBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotUnSecuredBalance() {
        return totUnSecuredBalance;
    }

    /**
     * Sets the value of the totUnSecuredBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotUnSecuredBalance(String value) {
        this.totUnSecuredBalance = value;
    }

    /**
     * Gets the value of the percUnSecuredBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPercUnSecuredBalance() {
        return percUnSecuredBalance;
    }

    /**
     * Sets the value of the percUnSecuredBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPercUnSecuredBalance(String value) {
        this.percUnSecuredBalance = value;
    }

    /**
     * Gets the value of the percSFWDWOSTLAccnts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPercSFWDWOSTLAccnts() {
        return percSFWDWOSTLAccnts;
    }

    /**
     * Sets the value of the percSFWDWOSTLAccnts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPercSFWDWOSTLAccnts(String value) {
        this.percSFWDWOSTLAccnts = value;
    }

}
