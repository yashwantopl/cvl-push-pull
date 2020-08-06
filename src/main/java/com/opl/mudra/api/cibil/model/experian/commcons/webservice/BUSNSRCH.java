
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BUSNSRCH complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BUSNSRCH">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BusName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BUSNSRCH", propOrder = {
    "busName"
})
public class BUSNSRCH {

    @XmlElement(name = "BusName", required = true)
    protected String busName;

    /**
     * Gets the value of the busName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusName() {
        return busName;
    }

    /**
     * Sets the value of the busName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusName(String value) {
        this.busName = value;
    }

}
