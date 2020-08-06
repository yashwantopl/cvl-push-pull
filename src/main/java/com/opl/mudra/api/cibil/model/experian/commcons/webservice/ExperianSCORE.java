
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExperianSCORE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ExperianSCORE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ModelNameCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ScoreValueCommercial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ScoreConfLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExperianSCORE", propOrder = {
    "segmentCode",
    "modelNameCd",
    "scoreValueCommercial",
    "scoreConfLevel"
})
public class ExperianSCORE {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "ModelNameCd")
    protected String modelNameCd;
    @XmlElement(name = "ScoreValueCommercial")
    protected String scoreValueCommercial;
    @XmlElement(name = "ScoreConfLevel")
    protected String scoreConfLevel;

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
     * Gets the value of the scoreValueCommercial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScoreValueCommercial() {
        return scoreValueCommercial;
    }

    /**
     * Sets the value of the scoreValueCommercial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScoreValueCommercial(String value) {
        this.scoreValueCommercial = value;
    }

    /**
     * Gets the value of the scoreConfLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScoreConfLevel() {
        return scoreConfLevel;
    }

    /**
     * Sets the value of the scoreConfLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScoreConfLevel(String value) {
        this.scoreConfLevel = value;
    }

}
