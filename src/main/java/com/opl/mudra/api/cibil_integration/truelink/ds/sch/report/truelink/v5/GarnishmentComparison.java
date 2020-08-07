
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
 *       &lt;attribute name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="dateSatisfied" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="garnishee" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="plaintiff" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "GarnishmentComparison")
public class GarnishmentComparison {

    @XmlAttribute(name = "amount")
    protected BigDecimal amount;
    @XmlAttribute(name = "dateSatisfied")
    protected Boolean dateSatisfied;
    @XmlAttribute(name = "garnishee")
    protected Boolean garnishee;
    @XmlAttribute(name = "plaintiff")
    protected Boolean plaintiff;

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the value of the dateSatisfied property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDateSatisfied() {
        return dateSatisfied;
    }

    /**
     * Sets the value of the dateSatisfied property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDateSatisfied(Boolean value) {
        this.dateSatisfied = value;
    }

    /**
     * Gets the value of the garnishee property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGarnishee() {
        return garnishee;
    }

    /**
     * Sets the value of the garnishee property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGarnishee(Boolean value) {
        this.garnishee = value;
    }

    /**
     * Gets the value of the plaintiff property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPlaintiff() {
        return plaintiff;
    }

    /**
     * Sets the value of the plaintiff property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPlaintiff(Boolean value) {
        this.plaintiff = value;
    }

}
