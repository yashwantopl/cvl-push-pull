
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
 *       &lt;attribute name="dateSettled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="liability" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "ForeclosureComparison")
public class ForeclosureComparison {

    @XmlAttribute(name = "dateSettled")
    protected Boolean dateSettled;
    @XmlAttribute(name = "liability")
    protected BigDecimal liability;

    /**
     * Gets the value of the dateSettled property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDateSettled() {
        return dateSettled;
    }

    /**
     * Sets the value of the dateSettled property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDateSettled(Boolean value) {
        this.dateSettled = value;
    }

    /**
     * Gets the value of the liability property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLiability() {
        return liability;
    }

    /**
     * Sets the value of the liability property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLiability(BigDecimal value) {
        this.liability = value;
    }

}
