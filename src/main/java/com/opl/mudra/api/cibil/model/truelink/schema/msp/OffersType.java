
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OffersType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OffersType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OptInToTU" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="OptInTO3rdParty" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OffersType", propOrder = {
    "optInToTU",
    "optInTO3RdParty"
})
public class OffersType {

    @XmlElement(name = "OptInToTU")
    protected boolean optInToTU;
    @XmlElement(name = "OptInTO3rdParty")
    protected boolean optInTO3RdParty;

    /**
     * Gets the value of the optInToTU property.
     * 
     */
    public boolean isOptInToTU() {
        return optInToTU;
    }

    /**
     * Sets the value of the optInToTU property.
     * 
     */
    public void setOptInToTU(boolean value) {
        this.optInToTU = value;
    }

    /**
     * Gets the value of the optInTO3RdParty property.
     * 
     */
    public boolean isOptInTO3RdParty() {
        return optInTO3RdParty;
    }

    /**
     * Sets the value of the optInTO3RdParty property.
     * 
     */
    public void setOptInTO3RdParty(boolean value) {
        this.optInTO3RdParty = value;
    }

}
