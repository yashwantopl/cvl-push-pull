
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BUSBIDCARDS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BUSBIDCARDS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdNumberType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IDSourceCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BUSBIDCARDS", propOrder = {
    "segmentCode",
    "idNumberType",
    "idNumber",
    "idSourceCd",
    "idCount",
    "idDate"
})
public class BUSBIDCARDS {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "IdNumberType")
    protected String idNumberType;
    @XmlElement(name = "IdNumber")
    protected String idNumber;
    @XmlElement(name = "IDSourceCd")
    protected String idSourceCd;
    @XmlElement(name = "IdCount")
    protected String idCount;
    @XmlElement(name = "IdDate")
    protected String idDate;

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
     * Gets the value of the idNumberType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdNumberType() {
        return idNumberType;
    }

    /**
     * Sets the value of the idNumberType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdNumberType(String value) {
        this.idNumberType = value;
    }

    /**
     * Gets the value of the idNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * Sets the value of the idNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdNumber(String value) {
        this.idNumber = value;
    }

    /**
     * Gets the value of the idSourceCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIDSourceCd() {
        return idSourceCd;
    }

    /**
     * Sets the value of the idSourceCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIDSourceCd(String value) {
        this.idSourceCd = value;
    }

    /**
     * Gets the value of the idCount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCount() {
        return idCount;
    }

    /**
     * Sets the value of the idCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCount(String value) {
        this.idCount = value;
    }

    /**
     * Gets the value of the idDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdDate() {
        return idDate;
    }

    /**
     * Sets the value of the idDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdDate(String value) {
        this.idDate = value;
    }

}
