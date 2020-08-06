
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

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
 *         &lt;element name="EXPNGRPT" type="{http://webservice.commcons.experian.com}EXPNGRPT"/>
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
    "expngrpt"
})
@XmlRootElement(name = "getExperianRequestResponse")
public class GetExperianRequestResponse {

    @XmlElement(name = "EXPNGRPT", required = true)
    protected EXPNGRPT expngrpt;

    /**
     * Gets the value of the expngrpt property.
     * 
     * @return
     *     possible object is
     *     {@link EXPNGRPT }
     *     
     */
    public EXPNGRPT getEXPNGRPT() {
        return expngrpt;
    }

    /**
     * Sets the value of the expngrpt property.
     * 
     * @param value
     *     allowed object is
     *     {@link EXPNGRPT }
     *     
     */
    public void setEXPNGRPT(EXPNGRPT value) {
        this.expngrpt = value;
    }

}
