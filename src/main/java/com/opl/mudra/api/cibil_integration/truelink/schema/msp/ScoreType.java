
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for ScoreType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ScoreType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ScoreXML" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Bureau" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ScoreType", propOrder = {
    "scoreXML"
})
public class ScoreType {

    @XmlElement(name = "ScoreXML", required = true)
    protected String scoreXML;
    @XmlAttribute(name = "Bureau")
    protected String bureau;

    /**
     * Gets the value of the scoreXML property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScoreXML() {
        return scoreXML;
    }

    /**
     * Sets the value of the scoreXML property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScoreXML(String value) {
        this.scoreXML = value;
    }

    /**
     * Gets the value of the bureau property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBureau() {
        return bureau;
    }

    /**
     * Sets the value of the bureau property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBureau(String value) {
        this.bureau = value;
    }

}
