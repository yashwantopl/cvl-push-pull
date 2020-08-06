
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for EXPNGRPT complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EXPNGRPT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BureauReport" type="{http://webservice.commcons.experian.com}BureauReport"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EXPNGRPT", propOrder = {
    "bureauReport"
})
@XmlRootElement
public class EXPNGRPT {

    @XmlElement(name = "BureauReport", required = true)
    protected BureauReport bureauReport;

    /**
     * Gets the value of the bureauReport property.
     * 
     * @return
     *     possible object is
     *     {@link BureauReport }
     *     
     */
    public BureauReport getBureauReport() {
        return bureauReport;
    }

    /**
     * Sets the value of the bureauReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link BureauReport }
     *     
     */
    public void setBureauReport(BureauReport value) {
        this.bureauReport = value;
    }

}
