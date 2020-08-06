
package com.opl.mudra.api.cibil.model.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.*;


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
 *         &lt;element name="CreditorType" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *       &lt;attribute name="dateMaturity" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "creditorType"
})
@XmlRootElement(name = "FinancingStatementComparsion")
public class FinancingStatementComparsion {

    @XmlElement(name = "CreditorType")
    protected boolean creditorType;
    @XmlAttribute(name = "dateMaturity")
    protected Boolean dateMaturity;

    /**
     * Gets the value of the creditorType property.
     * 
     */
    public boolean isCreditorType() {
        return creditorType;
    }

    /**
     * Sets the value of the creditorType property.
     * 
     */
    public void setCreditorType(boolean value) {
        this.creditorType = value;
    }

    /**
     * Gets the value of the dateMaturity property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDateMaturity() {
        return dateMaturity;
    }

    /**
     * Sets the value of the dateMaturity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDateMaturity(Boolean value) {
        this.dateMaturity = value;
    }

}
