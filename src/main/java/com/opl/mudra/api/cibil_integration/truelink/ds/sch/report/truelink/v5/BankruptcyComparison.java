
package com.opl.mudra.api.cibil_integration.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="assetAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="dateResolved" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="exemptAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="liabilityAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "BankruptcyComparison")
public class BankruptcyComparison {

    @XmlAttribute(name = "assetAmount")
    protected BigDecimal assetAmount;
    @XmlAttribute(name = "dateResolved")
    protected Boolean dateResolved;
    @XmlAttribute(name = "exemptAmount")
    protected BigDecimal exemptAmount;
    @XmlAttribute(name = "liabilityAmount")
    protected BigDecimal liabilityAmount;

    /**
     * Gets the value of the assetAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAssetAmount() {
        return assetAmount;
    }

    /**
     * Sets the value of the assetAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAssetAmount(BigDecimal value) {
        this.assetAmount = value;
    }

    /**
     * Gets the value of the dateResolved property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDateResolved() {
        return dateResolved;
    }

    /**
     * Sets the value of the dateResolved property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDateResolved(Boolean value) {
        this.dateResolved = value;
    }

    /**
     * Gets the value of the exemptAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getExemptAmount() {
        return exemptAmount;
    }

    /**
     * Sets the value of the exemptAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setExemptAmount(BigDecimal value) {
        this.exemptAmount = value;
    }

    /**
     * Gets the value of the liabilityAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLiabilityAmount() {
        return liabilityAmount;
    }

    /**
     * Sets the value of the liabilityAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLiabilityAmount(BigDecimal value) {
        this.liabilityAmount = value;
    }

}
