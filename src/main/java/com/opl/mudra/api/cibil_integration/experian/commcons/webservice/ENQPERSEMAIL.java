
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ENQPERSEMAIL complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ENQPERSEMAIL">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WebAddrType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WebAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ENQPERSEMAIL", propOrder = {
    "segmentCode",
    "webAddrType",
    "webAddr"
})
public class ENQPERSEMAIL {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "WebAddrType")
    protected String webAddrType;
    @XmlElement(name = "WebAddr")
    protected String webAddr;

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
     * Gets the value of the webAddrType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebAddrType() {
        return webAddrType;
    }

    /**
     * Sets the value of the webAddrType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebAddrType(String value) {
        this.webAddrType = value;
    }

    /**
     * Gets the value of the webAddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebAddr() {
        return webAddr;
    }

    /**
     * Sets the value of the webAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebAddr(String value) {
        this.webAddr = value;
    }

}
