
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.StateType;


/**
 * <p>Java class for AddressParsedType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddressParsedType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HouseNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PreDirection" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StreetName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PostDirection" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="StreetType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="City" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="State" type="{com/truelink/schema/database/tcps/enumerations}StateType"/>
 *         &lt;element name="PostalCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddressParsedType", propOrder = {
    "houseNumber",
    "preDirection",
    "streetName",
    "postDirection",
    "streetType",
    "city",
    "state",
    "postalCode"
})
public class AddressParsedType {

    @XmlElement(name = "HouseNumber", required = true)
    protected String houseNumber;
    @XmlElement(name = "PreDirection", required = true)
    protected String preDirection;
    @XmlElement(name = "StreetName", required = true)
    protected String streetName;
    @XmlElement(name = "PostDirection", required = true)
    protected String postDirection;
    @XmlElement(name = "StreetType", required = true)
    protected String streetType;
    @XmlElement(name = "City", required = true)
    protected String city;
    @XmlElement(name = "State", required = true)
    @XmlSchemaType(name = "string")
    protected StateType state;
    @XmlElement(name = "PostalCode", required = true)
    protected String postalCode;

    /**
     * Gets the value of the houseNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * Sets the value of the houseNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseNumber(String value) {
        this.houseNumber = value;
    }

    /**
     * Gets the value of the preDirection property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreDirection() {
        return preDirection;
    }

    /**
     * Sets the value of the preDirection property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreDirection(String value) {
        this.preDirection = value;
    }

    /**
     * Gets the value of the streetName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Sets the value of the streetName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetName(String value) {
        this.streetName = value;
    }

    /**
     * Gets the value of the postDirection property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostDirection() {
        return postDirection;
    }

    /**
     * Sets the value of the postDirection property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostDirection(String value) {
        this.postDirection = value;
    }

    /**
     * Gets the value of the streetType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetType() {
        return streetType;
    }

    /**
     * Sets the value of the streetType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetType(String value) {
        this.streetType = value;
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

    /**
     * Gets the value of the postalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

}
