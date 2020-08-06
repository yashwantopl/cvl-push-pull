
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PERSUMMARY complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PERSUMMARY">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CCATOTACCS" type="{http://webservice.commcons.experian.com}CCATOTACCS" minOccurs="0"/>
 *         &lt;element name="CCAACCTBAL" type="{http://webservice.commcons.experian.com}CCAACCTBAL" minOccurs="0"/>
 *         &lt;element name="CCAACCTHIS" type="{http://webservice.commcons.experian.com}CCAACCTHIS" minOccurs="0"/>
 *         &lt;element name="ENQATTR" type="{http://webservice.commcons.experian.com}ENQATTR" minOccurs="0"/>
 *         &lt;element name="ENQATTRALL" type="{http://webservice.commcons.experian.com}ENQATTRALL" minOccurs="0"/>
 *         &lt;element name="ENQATTRNCR" type="{http://webservice.commcons.experian.com}ENQATTRNCR" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PERSUMMARY", propOrder = {
    "segmentCode",
    "ccatotaccs",
    "ccaacctbal",
    "ccaaccthis",
    "enqattr",
    "enqattrall",
    "enqattrncr"
})
public class PERSUMMARY {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "CCATOTACCS")
    protected CCATOTACCS ccatotaccs;
    @XmlElement(name = "CCAACCTBAL")
    protected CCAACCTBAL ccaacctbal;
    @XmlElement(name = "CCAACCTHIS")
    protected CCAACCTHIS ccaaccthis;
    @XmlElement(name = "ENQATTR")
    protected ENQATTR enqattr;
    @XmlElement(name = "ENQATTRALL")
    protected ENQATTRALL enqattrall;
    @XmlElement(name = "ENQATTRNCR")
    protected ENQATTRNCR enqattrncr;

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
     * Gets the value of the ccatotaccs property.
     * 
     * @return
     *     possible object is
     *     {@link CCATOTACCS }
     *     
     */
    public CCATOTACCS getCCATOTACCS() {
        return ccatotaccs;
    }

    /**
     * Sets the value of the ccatotaccs property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCATOTACCS }
     *     
     */
    public void setCCATOTACCS(CCATOTACCS value) {
        this.ccatotaccs = value;
    }

    /**
     * Gets the value of the ccaacctbal property.
     * 
     * @return
     *     possible object is
     *     {@link CCAACCTBAL }
     *     
     */
    public CCAACCTBAL getCCAACCTBAL() {
        return ccaacctbal;
    }

    /**
     * Sets the value of the ccaacctbal property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCAACCTBAL }
     *     
     */
    public void setCCAACCTBAL(CCAACCTBAL value) {
        this.ccaacctbal = value;
    }

    /**
     * Gets the value of the ccaaccthis property.
     * 
     * @return
     *     possible object is
     *     {@link CCAACCTHIS }
     *     
     */
    public CCAACCTHIS getCCAACCTHIS() {
        return ccaaccthis;
    }

    /**
     * Sets the value of the ccaaccthis property.
     * 
     * @param value
     *     allowed object is
     *     {@link CCAACCTHIS }
     *     
     */
    public void setCCAACCTHIS(CCAACCTHIS value) {
        this.ccaaccthis = value;
    }

    /**
     * Gets the value of the enqattr property.
     * 
     * @return
     *     possible object is
     *     {@link ENQATTR }
     *     
     */
    public ENQATTR getENQATTR() {
        return enqattr;
    }

    /**
     * Sets the value of the enqattr property.
     * 
     * @param value
     *     allowed object is
     *     {@link ENQATTR }
     *     
     */
    public void setENQATTR(ENQATTR value) {
        this.enqattr = value;
    }

    /**
     * Gets the value of the enqattrall property.
     * 
     * @return
     *     possible object is
     *     {@link ENQATTRALL }
     *     
     */
    public ENQATTRALL getENQATTRALL() {
        return enqattrall;
    }

    /**
     * Sets the value of the enqattrall property.
     * 
     * @param value
     *     allowed object is
     *     {@link ENQATTRALL }
     *     
     */
    public void setENQATTRALL(ENQATTRALL value) {
        this.enqattrall = value;
    }

    /**
     * Gets the value of the enqattrncr property.
     * 
     * @return
     *     possible object is
     *     {@link ENQATTRNCR }
     *     
     */
    public ENQATTRNCR getENQATTRNCR() {
        return enqattrncr;
    }

    /**
     * Sets the value of the enqattrncr property.
     * 
     * @param value
     *     allowed object is
     *     {@link ENQATTRNCR }
     *     
     */
    public void setENQATTRNCR(ENQATTRNCR value) {
        this.enqattrncr = value;
    }

}
