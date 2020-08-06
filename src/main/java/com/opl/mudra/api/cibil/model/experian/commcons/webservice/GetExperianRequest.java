
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
 *         &lt;element name="BusinessProductRequest" type="{http://webservice.commcons.experian.com}BusinessProductRequest"/>
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
    "businessProductRequest"
})
@XmlRootElement(name = "getExperianRequest")
public class GetExperianRequest {

    @XmlElement(name = "BusinessProductRequest", required = true)
    protected BusinessProductRequest businessProductRequest;

    /**
     * Gets the value of the businessProductRequest property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessProductRequest }
     *     
     */
    public BusinessProductRequest getBusinessProductRequest() {
        return businessProductRequest;
    }

    /**
     * Sets the value of the businessProductRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessProductRequest }
     *     
     */
    public void setBusinessProductRequest(BusinessProductRequest value) {
        this.businessProductRequest = value;
    }

}
