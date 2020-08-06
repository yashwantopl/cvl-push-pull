
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SCOREPERSON complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SCOREPERSON">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ModelNameCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BureauScorePerson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SCOREPERSON", propOrder = {
    "segmentCode",
    "modelNameCd",
    "bureauScorePerson"
})
public class SCOREPERSON {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "ModelNameCd")
    protected String modelNameCd;
    @XmlElement(name = "BureauScorePerson")
    protected String bureauScorePerson;

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
     * Gets the value of the modelNameCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelNameCd() {
        return modelNameCd;
    }

    /**
     * Sets the value of the modelNameCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModelNameCd(String value) {
        this.modelNameCd = value;
    }

    /**
     * Gets the value of the bureauScorePerson property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBureauScorePerson() {
        return bureauScorePerson;
    }

    /**
     * Sets the value of the bureauScorePerson property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBureauScorePerson(String value) {
        this.bureauScorePerson = value;
    }

}
