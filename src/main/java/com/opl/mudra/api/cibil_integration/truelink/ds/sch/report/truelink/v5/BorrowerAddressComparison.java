
package com.opl.mudra.api.cibil_integration.truelink.ds.sch.report.truelink.v5;

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
 *         &lt;element name="CreditAddress" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Dwelling" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Origin" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Ownership" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *       &lt;attribute name="dateReported" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="dateUpdated" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "creditAddress",
    "dwelling",
    "origin",
    "ownership"
})
@XmlRootElement(name = "BorrowerAddressComparison")
public class BorrowerAddressComparison {

    @XmlElement(name = "CreditAddress")
    protected boolean creditAddress;
    @XmlElement(name = "Dwelling")
    protected boolean dwelling;
    @XmlElement(name = "Origin")
    protected boolean origin;
    @XmlElement(name = "Ownership")
    protected boolean ownership;
    @XmlAttribute(name = "dateReported")
    protected Boolean dateReported;
    @XmlAttribute(name = "dateUpdated")
    protected Boolean dateUpdated;

    /**
     * Gets the value of the creditAddress property.
     * 
     */
    public boolean isCreditAddress() {
        return creditAddress;
    }

    /**
     * Sets the value of the creditAddress property.
     * 
     */
    public void setCreditAddress(boolean value) {
        this.creditAddress = value;
    }

    /**
     * Gets the value of the dwelling property.
     * 
     */
    public boolean isDwelling() {
        return dwelling;
    }

    /**
     * Sets the value of the dwelling property.
     * 
     */
    public void setDwelling(boolean value) {
        this.dwelling = value;
    }

    /**
     * Gets the value of the origin property.
     * 
     */
    public boolean isOrigin() {
        return origin;
    }

    /**
     * Sets the value of the origin property.
     * 
     */
    public void setOrigin(boolean value) {
        this.origin = value;
    }

    /**
     * Gets the value of the ownership property.
     * 
     */
    public boolean isOwnership() {
        return ownership;
    }

    /**
     * Sets the value of the ownership property.
     * 
     */
    public void setOwnership(boolean value) {
        this.ownership = value;
    }

    /**
     * Gets the value of the dateReported property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDateReported() {
        return dateReported;
    }

    /**
     * Sets the value of the dateReported property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDateReported(Boolean value) {
        this.dateReported = value;
    }

    /**
     * Gets the value of the dateUpdated property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDateUpdated() {
        return dateUpdated;
    }

    /**
     * Sets the value of the dateUpdated property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDateUpdated(Boolean value) {
        this.dateUpdated = value;
    }

}
