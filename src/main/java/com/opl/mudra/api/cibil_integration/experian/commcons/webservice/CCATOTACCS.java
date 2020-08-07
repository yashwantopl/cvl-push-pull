
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CCATOTACCS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CCATOTACCS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotAccnts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotActiveAccnts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PercActiveAccnts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotDefaultAccnts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PercDefaultAccnts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotClosedAccnts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PercClosedAccnts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotSFWDWOSTLAccnts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CCATOTACCS", propOrder = {
    "segmentCode",
    "totAccnts",
    "totActiveAccnts",
    "percActiveAccnts",
    "totDefaultAccnts",
    "percDefaultAccnts",
    "totClosedAccnts",
    "percClosedAccnts",
    "totSFWDWOSTLAccnts"
})
public class CCATOTACCS {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "TotAccnts")
    protected String totAccnts;
    @XmlElement(name = "TotActiveAccnts")
    protected String totActiveAccnts;
    @XmlElement(name = "PercActiveAccnts")
    protected String percActiveAccnts;
    @XmlElement(name = "TotDefaultAccnts")
    protected String totDefaultAccnts;
    @XmlElement(name = "PercDefaultAccnts")
    protected String percDefaultAccnts;
    @XmlElement(name = "TotClosedAccnts")
    protected String totClosedAccnts;
    @XmlElement(name = "PercClosedAccnts")
    protected String percClosedAccnts;
    @XmlElement(name = "TotSFWDWOSTLAccnts")
    protected String totSFWDWOSTLAccnts;

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
     * Gets the value of the totAccnts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotAccnts() {
        return totAccnts;
    }

    /**
     * Sets the value of the totAccnts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotAccnts(String value) {
        this.totAccnts = value;
    }

    /**
     * Gets the value of the totActiveAccnts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotActiveAccnts() {
        return totActiveAccnts;
    }

    /**
     * Sets the value of the totActiveAccnts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotActiveAccnts(String value) {
        this.totActiveAccnts = value;
    }

    /**
     * Gets the value of the percActiveAccnts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPercActiveAccnts() {
        return percActiveAccnts;
    }

    /**
     * Sets the value of the percActiveAccnts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPercActiveAccnts(String value) {
        this.percActiveAccnts = value;
    }

    /**
     * Gets the value of the totDefaultAccnts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotDefaultAccnts() {
        return totDefaultAccnts;
    }

    /**
     * Sets the value of the totDefaultAccnts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotDefaultAccnts(String value) {
        this.totDefaultAccnts = value;
    }

    /**
     * Gets the value of the percDefaultAccnts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPercDefaultAccnts() {
        return percDefaultAccnts;
    }

    /**
     * Sets the value of the percDefaultAccnts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPercDefaultAccnts(String value) {
        this.percDefaultAccnts = value;
    }

    /**
     * Gets the value of the totClosedAccnts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotClosedAccnts() {
        return totClosedAccnts;
    }

    /**
     * Sets the value of the totClosedAccnts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotClosedAccnts(String value) {
        this.totClosedAccnts = value;
    }

    /**
     * Gets the value of the percClosedAccnts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPercClosedAccnts() {
        return percClosedAccnts;
    }

    /**
     * Sets the value of the percClosedAccnts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPercClosedAccnts(String value) {
        this.percClosedAccnts = value;
    }

    /**
     * Gets the value of the totSFWDWOSTLAccnts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotSFWDWOSTLAccnts() {
        return totSFWDWOSTLAccnts;
    }

    /**
     * Sets the value of the totSFWDWOSTLAccnts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotSFWDWOSTLAccnts(String value) {
        this.totSFWDWOSTLAccnts = value;
    }

}
