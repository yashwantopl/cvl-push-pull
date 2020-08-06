
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
 *         &lt;element name="CreditAddress" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IndustryCode" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="telephone" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
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
    "industryCode"
})
@XmlRootElement(name = "MessageComparison")
public class MessageComparison {

    @XmlElement(name = "CreditAddress")
    protected boolean creditAddress;
    @XmlElement(name = "IndustryCode")
    protected boolean industryCode;
    @XmlAttribute(name = "name", required = true)
    protected boolean name;
    @XmlAttribute(name = "telephone", required = true)
    protected boolean telephone;

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
     * Gets the value of the industryCode property.
     * 
     */
    public boolean isIndustryCode() {
        return industryCode;
    }

    /**
     * Sets the value of the industryCode property.
     * 
     */
    public void setIndustryCode(boolean value) {
        this.industryCode = value;
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

    /**
     * Gets the value of the telephone property.
     * 
     */
    public boolean isTelephone() {
        return telephone;
    }

    /**
     * Sets the value of the telephone property.
     * 
     */
    public void setTelephone(boolean value) {
        this.telephone = value;
    }

}
