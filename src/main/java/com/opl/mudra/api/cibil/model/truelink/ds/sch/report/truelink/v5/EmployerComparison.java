
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
 *         &lt;element name="CreditAddressComparison" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *       &lt;attribute name="dateReported" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="dateUpdated" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "creditAddressComparison"
})
@XmlRootElement(name = "EmployerComparison")
public class EmployerComparison {

    @XmlElement(name = "CreditAddressComparison")
    protected boolean creditAddressComparison;
    @XmlAttribute(name = "dateReported")
    protected Boolean dateReported;
    @XmlAttribute(name = "dateUpdated")
    protected Boolean dateUpdated;
    @XmlAttribute(name = "name", required = true)
    protected boolean name;

    /**
     * Gets the value of the creditAddressComparison property.
     * 
     */
    public boolean isCreditAddressComparison() {
        return creditAddressComparison;
    }

    /**
     * Sets the value of the creditAddressComparison property.
     * 
     */
    public void setCreditAddressComparison(boolean value) {
        this.creditAddressComparison = value;
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

    /**
     * Gets the value of the name property.
     * 
     */
    public boolean isName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     */
    public void setName(boolean value) {
        this.name = value;
    }

}
