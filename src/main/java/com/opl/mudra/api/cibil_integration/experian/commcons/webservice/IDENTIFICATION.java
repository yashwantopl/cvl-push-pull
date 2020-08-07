
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IDENTIFICATION complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IDENTIFICATION">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="XMLUser" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="XMLPassword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ProductFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IDENTIFICATION", propOrder = {
    "xmlUser",
    "xmlPassword",
    "productFlag"
})
public class IDENTIFICATION {

    @XmlElement(name = "XMLUser", required = true)
    protected String xmlUser;
    @XmlElement(name = "XMLPassword", required = true)
    protected String xmlPassword;
    @XmlElement(name = "ProductFlag")
    protected String productFlag;

    /**
     * Gets the value of the xmlUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXMLUser() {
        return xmlUser;
    }

    /**
     * Sets the value of the xmlUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXMLUser(String value) {
        this.xmlUser = value;
    }

    /**
     * Gets the value of the xmlPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXMLPassword() {
        return xmlPassword;
    }

    /**
     * Sets the value of the xmlPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXMLPassword(String value) {
        this.xmlPassword = value;
    }

    /**
     * Gets the value of the productFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductFlag() {
        return productFlag;
    }

    /**
     * Sets the value of the productFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductFlag(String value) {
        this.productFlag = value;
    }

}
