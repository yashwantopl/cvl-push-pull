
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
 *         &lt;element name="creditType" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *       &lt;/sequence>
 *       &lt;attribute name="originalCreditor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="actualPaymentAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "creditType"
})
@XmlRootElement(name = "CollectionTrade")
public class CollectionTrade {

    @XmlElement(required = true)
    protected CodeRef creditType;
    @XmlAttribute(name = "originalCreditor")
    protected String originalCreditor;
    @XmlAttribute(name = "actualPaymentAmount")
    protected BigDecimal actualPaymentAmount;

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
     * Gets the value of the originalCreditor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginalCreditor() {
        return originalCreditor;
    }

    /**
     * Sets the value of the originalCreditor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginalCreditor(String value) {
        this.originalCreditor = value;
    }

    /**
     * Gets the value of the actualPaymentAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getActualPaymentAmount() {
        return actualPaymentAmount;
    }

    /**
     * Sets the value of the actualPaymentAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setActualPaymentAmount(BigDecimal value) {
        this.actualPaymentAmount = value;
    }

}
