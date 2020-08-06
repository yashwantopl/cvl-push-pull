
package com.opl.mudra.api.cibil.model.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for CreditScoreType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreditScoreType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}CreditScoreFactor" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="CreditScoreModel" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="NoScoreReason" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Source"/>
 *       &lt;/sequence>
 *       &lt;attribute name="riskScore" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="scoreName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="populationRank" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="qualitativeRank" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="inquiriesAffectedScore" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="new" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditScoreType", propOrder = {
    "creditScoreFactor",
    "creditScoreModel",
    "noScoreReason",
    "source"
})
public class CreditScoreType {

    @XmlElement(name = "CreditScoreFactor")
    protected List<CreditScoreFactor> creditScoreFactor;
    @XmlElement(name = "CreditScoreModel", required = true)
    protected CodeRef creditScoreModel;
    @XmlElement(name = "NoScoreReason")
    protected CodeRef noScoreReason;
    @XmlElement(name = "Source", required = true)
    protected Source source;
    @XmlAttribute(name = "riskScore")
    protected BigDecimal riskScore;
    @XmlAttribute(name = "scoreName")
    protected String scoreName;
    @XmlAttribute(name = "populationRank")
    protected String populationRank;
    @XmlAttribute(name = "qualitativeRank")
    protected String qualitativeRank;
    @XmlAttribute(name = "inquiriesAffectedScore")
    protected Boolean inquiriesAffectedScore;
    @XmlAttribute(name = "new")
    protected Boolean _new;

    /**
     * Gets the value of the creditScoreFactor property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the creditScoreFactor property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCreditScoreFactor().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CreditScoreFactor }
     * 
     * 
     */
    public List<CreditScoreFactor> getCreditScoreFactor() {
        if (creditScoreFactor == null) {
            creditScoreFactor = new ArrayList<CreditScoreFactor>();
        }
        return this.creditScoreFactor;
    }

    /**
     * Gets the value of the creditScoreModel property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getCreditScoreModel() {
        return creditScoreModel;
    }

    /**
     * Sets the value of the creditScoreModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setCreditScoreModel(CodeRef value) {
        this.creditScoreModel = value;
    }

    /**
     * Gets the value of the noScoreReason property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getNoScoreReason() {
        return noScoreReason;
    }

    /**
     * Sets the value of the noScoreReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setNoScoreReason(CodeRef value) {
        this.noScoreReason = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link Source }
     *     
     */
    public Source getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link Source }
     *     
     */
    public void setSource(Source value) {
        this.source = value;
    }

    /**
     * Gets the value of the riskScore property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRiskScore() {
        return riskScore;
    }

    /**
     * Sets the value of the riskScore property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRiskScore(BigDecimal value) {
        this.riskScore = value;
    }

    /**
     * Gets the value of the scoreName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScoreName() {
        return scoreName;
    }

    /**
     * Sets the value of the scoreName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScoreName(String value) {
        this.scoreName = value;
    }

    /**
     * Gets the value of the populationRank property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPopulationRank() {
        return populationRank;
    }

    /**
     * Sets the value of the populationRank property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPopulationRank(String value) {
        this.populationRank = value;
    }

    /**
     * Gets the value of the qualitativeRank property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQualitativeRank() {
        return qualitativeRank;
    }

    /**
     * Sets the value of the qualitativeRank property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQualitativeRank(String value) {
        this.qualitativeRank = value;
    }

    /**
     * Gets the value of the inquiriesAffectedScore property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInquiriesAffectedScore() {
        return inquiriesAffectedScore;
    }

    /**
     * Sets the value of the inquiriesAffectedScore property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInquiriesAffectedScore(Boolean value) {
        this.inquiriesAffectedScore = value;
    }

    /**
     * Gets the value of the new property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNew() {
        return _new;
    }

    /**
     * Sets the value of the new property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNew(Boolean value) {
        this._new = value;
    }

}
