
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.ScoreModelType;


/**
 * <p>Java class for CreditScoreReportType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreditScoreReportType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{com/truelink/schema/database/tcps/enumerations}ScoreModel"/>
 *         &lt;element name="TransriskScore" type="{com/truelink/schema/msp}TransriskScoreType" minOccurs="0"/>
 *         &lt;element name="VantageScore" type="{com/truelink/schema/msp}VantageScoreType" minOccurs="0"/>
 *         &lt;element name="CVAccountManagementScore" type="{com/truelink/schema/msp}CVAccountManagementScoreType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditScoreReportType", propOrder = {
    "scoreModel",
    "transriskScore",
    "vantageScore",
    "cvAccountManagementScore"
})
public class CreditScoreReportType {

    @XmlElement(name = "ScoreModel", namespace = "com/truelink/schema/database/tcps/enumerations", required = true)
    @XmlSchemaType(name = "string")
    protected ScoreModelType scoreModel;
    @XmlElement(name = "TransriskScore")
    protected TransriskScoreType transriskScore;
    @XmlElement(name = "VantageScore")
    protected VantageScoreType vantageScore;
    @XmlElement(name = "CVAccountManagementScore")
    protected CVAccountManagementScoreType cvAccountManagementScore;

    /**
     * Gets the value of the scoreModel property.
     * 
     * @return
     *     possible object is
     *     {@link ScoreModelType }
     *     
     */
    public ScoreModelType getScoreModel() {
        return scoreModel;
    }

    /**
     * Sets the value of the scoreModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScoreModelType }
     *     
     */
    public void setScoreModel(ScoreModelType value) {
        this.scoreModel = value;
    }

    /**
     * Gets the value of the transriskScore property.
     * 
     * @return
     *     possible object is
     *     {@link TransriskScoreType }
     *     
     */
    public TransriskScoreType getTransriskScore() {
        return transriskScore;
    }

    /**
     * Sets the value of the transriskScore property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransriskScoreType }
     *     
     */
    public void setTransriskScore(TransriskScoreType value) {
        this.transriskScore = value;
    }

    /**
     * Gets the value of the vantageScore property.
     * 
     * @return
     *     possible object is
     *     {@link VantageScoreType }
     *     
     */
    public VantageScoreType getVantageScore() {
        return vantageScore;
    }

    /**
     * Sets the value of the vantageScore property.
     * 
     * @param value
     *     allowed object is
     *     {@link VantageScoreType }
     *     
     */
    public void setVantageScore(VantageScoreType value) {
        this.vantageScore = value;
    }

    /**
     * Gets the value of the cvAccountManagementScore property.
     * 
     * @return
     *     possible object is
     *     {@link CVAccountManagementScoreType }
     *     
     */
    public CVAccountManagementScoreType getCVAccountManagementScore() {
        return cvAccountManagementScore;
    }

    /**
     * Sets the value of the cvAccountManagementScore property.
     * 
     * @param value
     *     allowed object is
     *     {@link CVAccountManagementScoreType }
     *     
     */
    public void setCVAccountManagementScore(CVAccountManagementScoreType value) {
        this.cvAccountManagementScore = value;
    }

}
