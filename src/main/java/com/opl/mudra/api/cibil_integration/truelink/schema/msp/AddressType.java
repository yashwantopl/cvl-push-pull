
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.StateType;


/**
 * <p>Java class for AddressType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddressType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StreetAddressLineOne" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StreetAddressLineTwo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Zip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="State" type="{com/truelink/schema/database/tcps/enumerations}StateType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressType", propOrder = {
    "streetAddressLineOne",
    "streetAddressLineTwo",
    "city",
    "zip",
    "state"
})
public class AddressType {

    @XmlElement(name = "StreetAddressLineOne", required = true)
    protected String streetAddressLineOne;
    @XmlElement(name = "StreetAddressLineTwo")
    protected String streetAddressLineTwo;
    @XmlElement(name = "City", required = true)
    protected String city;
    @XmlElement(name = "Zip", required = true)
    protected String zip;
    @XmlElement(name = "State", required = true)
    @XmlSchemaType(name = "string")
    protected StateType state;

    /**
     * Gets the value of the streetAddressLineOne property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetAddressLineOne() {
        return streetAddressLineOne;
    }

    /**
     * Sets the value of the streetAddressLineOne property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetAddressLineOne(String value) {
        this.streetAddressLineOne = value;
    }

    /**
     * Gets the value of the streetAddressLineTwo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetAddressLineTwo() {
        return streetAddressLineTwo;
    }

    /**
     * Sets the value of the streetAddressLineTwo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetAddressLineTwo(String value) {
        this.streetAddressLineTwo = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the zip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets the value of the zip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZip(String value) {
        this.zip = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link StateType }
     *     
     */
    public StateType getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link StateType }
     *     
     */
    public void setState(StateType value) {
        this.state = value;
    }

}
