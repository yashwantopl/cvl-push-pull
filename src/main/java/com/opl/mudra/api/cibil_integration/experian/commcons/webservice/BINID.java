
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BINID complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BINID">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExperianEncryptedBIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BINID", propOrder = {
    "experianEncryptedBIN"
})
public class BINID {

    @XmlElement(name = "ExperianEncryptedBIN")
    protected String experianEncryptedBIN;

    /**
     * Gets the value of the experianEncryptedBIN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExperianEncryptedBIN() {
        return experianEncryptedBIN;
    }

    /**
     * Sets the value of the experianEncryptedBIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExperianEncryptedBIN(String value) {
        this.experianEncryptedBIN = value;
    }

}
