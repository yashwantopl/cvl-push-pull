
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
 *         &lt;element name="creditType" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *       &lt;attribute name="originalCreditor" type="{http://www.w3.org/2001/XMLSchema}boolean" />
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
@XmlRootElement(name = "CollectionTradeComparison")
public class CollectionTradeComparison {

    protected boolean creditType;
    @XmlAttribute(name = "originalCreditor")
    protected Boolean originalCreditor;

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
     * Gets the value of the originalCreditor property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOriginalCreditor() {
        return originalCreditor;
    }

    /**
     * Sets the value of the originalCreditor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOriginalCreditor(Boolean value) {
        this.originalCreditor = value;
    }

}
