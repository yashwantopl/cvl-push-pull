
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PERDETAILS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PERDETAILS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PERINPUT" type="{http://webservice.commcons.experian.com}PERINPUT" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PERDETAILS", propOrder = {
    "perinput"
})
public class PERDETAILS {

    @XmlElement(name = "PERINPUT")
    protected PERINPUT perinput;

    /**
     * Gets the value of the perinput property.
     * 
     * @return
     *     possible object is
     *     {@link PERINPUT }
     *     
     */
    public PERINPUT getPERINPUT() {
        return perinput;
    }

    /**
     * Sets the value of the perinput property.
     * 
     * @param value
     *     allowed object is
     *     {@link PERINPUT }
     *     
     */
    public void setPERINPUT(PERINPUT value) {
        this.perinput = value;
    }

}
