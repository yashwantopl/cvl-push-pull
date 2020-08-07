
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.BureauType;


/**
 * <p>Java class for VantageScoreType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VantageScoreType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VantageScoreXML" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="SourceBureau" use="required" type="{com/truelink/schema/database/tcps/enumerations}BureauType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VantageScoreType", propOrder = {
    "vantageScoreXML"
})
public class VantageScoreType {

    @XmlElement(name = "VantageScoreXML", required = true)
    protected String vantageScoreXML;
    @XmlAttribute(name = "SourceBureau", required = true)
    protected BureauType sourceBureau;

    /**
     * Gets the value of the vantageScoreXML property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVantageScoreXML() {
        return vantageScoreXML;
    }

    /**
     * Sets the value of the vantageScoreXML property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVantageScoreXML(String value) {
        this.vantageScoreXML = value;
    }

    /**
     * Gets the value of the sourceBureau property.
     * 
     * @return
     *     possible object is
     *     {@link BureauType }
     *     
     */
    public BureauType getSourceBureau() {
        return sourceBureau;
    }

    /**
     * Sets the value of the sourceBureau property.
     * 
     * @param value
     *     allowed object is
     *     {@link BureauType }
     *     
     */
    public void setSourceBureau(BureauType value) {
        this.sourceBureau = value;
    }

}
