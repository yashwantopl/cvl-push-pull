
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
 *         &lt;element name="ContactMethod" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="CreditType" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="PreviousAccountCondition" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
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
@XmlRootElement(name = "WatchTrade")
public class WatchTrade {

    @XmlElement(name = "ContactMethod", required = true)
    protected CodeRef contactMethod;
    @XmlElement(name = "CreditType", required = true)
    protected CodeRef creditType;
    @XmlElement(name = "PreviousAccountCondition", required = true)
    protected CodeRef previousAccountCondition;
    @XmlAttribute(name = "amountPastDue")
    protected BigDecimal amountPastDue;
    @XmlAttribute(name = "previousAmountPastDue")
    protected BigDecimal previousAmountPastDue;

    /**
     * Gets the value of the contactMethod property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getContactMethod() {
        return contactMethod;
    }

    /**
     * Sets the value of the contactMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setContactMethod(CodeRef value) {
        this.contactMethod = value;
    }

    /**
     * Gets the value of the creditType property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getCreditType() {
        return creditType;
    }

    /**
     * Sets the value of the creditType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setCreditType(CodeRef value) {
        this.creditType = value;
    }

    /**
     * Gets the value of the previousAccountCondition property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getPreviousAccountCondition() {
        return previousAccountCondition;
    }

    /**
     * Sets the value of the previousAccountCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setPreviousAccountCondition(CodeRef value) {
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
