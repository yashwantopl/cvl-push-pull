
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for NGSYSMSG complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NGSYSMSG">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="systemMessageCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="systemMessageText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="systemOutputFormatVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="systemMessageDescription" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NGSYSMSG", propOrder = {
    "segmentCode",
    "systemMessageCode",
    "systemMessageText",
    "systemOutputFormatVersion",
    "systemMessageDescription"
})
public class NGSYSMSG {

    @XmlElement(name = "SegmentCode", required = true)
    protected String segmentCode;
    @XmlElement(required = true)
    protected String systemMessageCode;
    @XmlElement(required = true)
    protected String systemMessageText;
    @XmlElement(required = true)
    protected String systemOutputFormatVersion;
    @XmlElement(required = true)
    protected String systemMessageDescription;

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
     * Gets the value of the systemMessageCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemMessageCode() {
        return systemMessageCode;
    }

    /**
     * Sets the value of the systemMessageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemMessageCode(String value) {
        this.systemMessageCode = value;
    }

    /**
     * Gets the value of the systemMessageText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemMessageText() {
        return systemMessageText;
    }

    /**
     * Sets the value of the systemMessageText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemMessageText(String value) {
        this.systemMessageText = value;
    }

    /**
     * Gets the value of the systemOutputFormatVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemOutputFormatVersion() {
        return systemOutputFormatVersion;
    }

    /**
     * Sets the value of the systemOutputFormatVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemOutputFormatVersion(String value) {
        this.systemOutputFormatVersion = value;
    }

    /**
     * Gets the value of the systemMessageDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemMessageDescription() {
        return systemMessageDescription;
    }

    /**
     * Sets the value of the systemMessageDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemMessageDescription(String value) {
        this.systemMessageDescription = value;
    }

}
