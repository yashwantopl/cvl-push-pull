
package com.opl.mudra.api.cibil.model.truelink.ds.sch.report.truelink.v5;

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
 *         &lt;element name="ContactMethod" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="CreditType" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PreviousAccountCondition" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *       &lt;attribute name="amountPastDue" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="previousAmountPastDue" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "contactMethod",
    "creditType",
    "previousAccountCondition"
})
@XmlRootElement(name = "WatchTradeComparison")
public class WatchTradeComparison {

    @XmlElement(name = "ContactMethod")
    protected boolean contactMethod;
    @XmlElement(name = "CreditType")
    protected boolean creditType;
    @XmlElement(name = "PreviousAccountCondition")
    protected boolean previousAccountCondition;
    @XmlAttribute(name = "amountPastDue")
    protected BigDecimal amountPastDue;
    @XmlAttribute(name = "previousAmountPastDue")
    protected BigDecimal previousAmountPastDue;

    /**
     * Gets the value of the contactMethod property.
     * 
     */
    public boolean isContactMethod() {
        return contactMethod;
    }

    /**
     * Sets the value of the contactMethod property.
     * 
     */
    public void setContactMethod(boolean value) {
        this.contactMethod = value;
    }

    /**
     * Gets the value of the creditType property.
     * 
     */
    public boolean isCreditType() {
        return creditType;
    }

    /**
     * Sets the value of the creditType property.
     * 
     */
    public void setCreditType(boolean value) {
        this.creditType = value;
    }

    /**
     * Gets the value of the previousAccountCondition property.
     * 
     */
    public boolean isPreviousAccountCondition() {
        return previousAccountCondition;
    }

    /**
     * Sets the value of the previousAccountCondition property.
     * 
     */
    public void setPreviousAccountCondition(boolean value) {
        this.previousAccountCondition = value;
    }

    /**
     * Gets the value of the amountPastDue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmountPastDue() {
        return amountPastDue;
    }

    /**
     * Sets the value of the amountPastDue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmountPastDue(BigDecimal value) {
        this.amountPastDue = value;
    }

    /**
     * Gets the value of the previousAmountPastDue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPreviousAmountPastDue() {
        return previousAmountPastDue;
    }

    /**
     * Sets the value of the previousAmountPastDue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPreviousAmountPastDue(BigDecimal value) {
        this.previousAmountPastDue = value;
    }

}
