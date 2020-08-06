
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.BureauType;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for CVAccountManagementScoreType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CVAccountManagementScoreType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CVAccountManagementScoreXML" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "CVAccountManagementScoreType", propOrder = {
    "cvAccountManagementScoreXML"
})
public class CVAccountManagementScoreType {

    @XmlElement(name = "CVAccountManagementScoreXML", required = true)
    protected String cvAccountManagementScoreXML;
    @XmlAttribute(name = "SourceBureau", required = true)
    protected BureauType sourceBureau;

    /**
     * Gets the value of the cvAccountManagementScoreXML property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCVAccountManagementScoreXML() {
        return cvAccountManagementScoreXML;
    }

    /**
     * Sets the value of the cvAccountManagementScoreXML property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCVAccountManagementScoreXML(String value) {
        this.cvAccountManagementScoreXML = value;
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
