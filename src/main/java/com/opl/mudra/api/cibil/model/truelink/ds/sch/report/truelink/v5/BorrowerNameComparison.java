
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
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="NameType" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "nameType"
})
@XmlRootElement(name = "BorrowerNameComparison")
public class BorrowerNameComparison {

    @XmlElement(name = "Name")
    protected boolean name;
    @XmlElement(name = "NameType")
    protected boolean nameType;

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
     * Gets the value of the nameType property.
     * 
     */
    public boolean isNameType() {
        return nameType;
    }

    /**
     * Sets the value of the nameType property.
     * 
     */
    public void setNameType(boolean value) {
        this.nameType = value;
    }

}
