//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.02.28 at 03:32:08 PM IST 
//


package com.opl.mudra.api.cibil.model.highmark.individual.request.acknow;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.opl.mudra.api.cibil.utils.CDATAAdapter;



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
 *         &lt;element name="ADDRESS" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="TYPE">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;enumeration value="D01"/>
 *                         &lt;enumeration value="D02"/>
 *                         &lt;enumeration value="D03"/>
 *                         &lt;enumeration value="D04"/>
 *                         &lt;enumeration value="D05"/>
 *                         &lt;enumeration value="D06"/>
 *                         &lt;enumeration value="D07"/>
 *                         &lt;enumeration value="D08"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="ADDRESS-1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="CITY" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="STATE">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;enumeration value="AP"/>
 *                         &lt;enumeration value="AR"/>
 *                         &lt;enumeration value="AS"/>
 *                         &lt;enumeration value="BR"/>
 *                         &lt;enumeration value="CG"/>
 *                         &lt;enumeration value="GA"/>
 *                         &lt;enumeration value="GJ"/>
 *                         &lt;enumeration value="HR"/>
 *                         &lt;enumeration value="HP"/>
 *                         &lt;enumeration value="JK"/>
 *                         &lt;enumeration value="JH"/>
 *                         &lt;enumeration value="KA"/>
 *                         &lt;enumeration value="KL"/>
 *                         &lt;enumeration value="MP"/>
 *                         &lt;enumeration value="MH"/>
 *                         &lt;enumeration value="MN"/>
 *                         &lt;enumeration value="ML"/>
 *                         &lt;enumeration value="MZ"/>
 *                         &lt;enumeration value="NL"/>
 *                         &lt;enumeration value="OR"/>
 *                         &lt;enumeration value="PB"/>
 *                         &lt;enumeration value="RJ"/>
 *                         &lt;enumeration value="SK"/>
 *                         &lt;enumeration value="TN"/>
 *                         &lt;enumeration value="TR"/>
 *                         &lt;enumeration value="UK"/>
 *                         &lt;enumeration value="UP"/>
 *                         &lt;enumeration value="WB"/>
 *                         &lt;enumeration value="AN"/>
 *                         &lt;enumeration value="CH"/>
 *                         &lt;enumeration value="DN"/>
 *                         &lt;enumeration value="DD"/>
 *                         &lt;enumeration value="DL"/>
 *                         &lt;enumeration value="LD"/>
 *                         &lt;enumeration value="PY"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="PIN" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "address"
})
@XmlRootElement(name = "ADDRESS-SEGMENT")
public class ADDRESSSEGMENT {

    @XmlElement(name = "ADDRESS", required = true)
    protected List<ADDRESS> address;

    /**
     * Gets the value of the address property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the address property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getADDRESS().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ADDRESS }
     * 
     * 
     */
    public List<ADDRESS> getADDRESS() {
        if (address == null) {
            address = new ArrayList<ADDRESS>();
        }
        return this.address;
    }
    
    public void setADDRESS(List<ADDRESS> addList) {
        this.address = addList;
    }


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
     *         &lt;element name="TYPE">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;enumeration value="D01"/>
     *               &lt;enumeration value="D02"/>
     *               &lt;enumeration value="D03"/>
     *               &lt;enumeration value="D04"/>
     *               &lt;enumeration value="D05"/>
     *               &lt;enumeration value="D06"/>
     *               &lt;enumeration value="D07"/>
     *               &lt;enumeration value="D08"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="ADDRESS-1" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="CITY" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="STATE">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;enumeration value="AP"/>
     *               &lt;enumeration value="AR"/>
     *               &lt;enumeration value="AS"/>
     *               &lt;enumeration value="BR"/>
     *               &lt;enumeration value="CG"/>
     *               &lt;enumeration value="GA"/>
     *               &lt;enumeration value="GJ"/>
     *               &lt;enumeration value="HR"/>
     *               &lt;enumeration value="HP"/>
     *               &lt;enumeration value="JK"/>
     *               &lt;enumeration value="JH"/>
     *               &lt;enumeration value="KA"/>
     *               &lt;enumeration value="KL"/>
     *               &lt;enumeration value="MP"/>
     *               &lt;enumeration value="MH"/>
     *               &lt;enumeration value="MN"/>
     *               &lt;enumeration value="ML"/>
     *               &lt;enumeration value="MZ"/>
     *               &lt;enumeration value="NL"/>
     *               &lt;enumeration value="OR"/>
     *               &lt;enumeration value="PB"/>
     *               &lt;enumeration value="RJ"/>
     *               &lt;enumeration value="SK"/>
     *               &lt;enumeration value="TN"/>
     *               &lt;enumeration value="TR"/>
     *               &lt;enumeration value="UK"/>
     *               &lt;enumeration value="UP"/>
     *               &lt;enumeration value="WB"/>
     *               &lt;enumeration value="AN"/>
     *               &lt;enumeration value="CH"/>
     *               &lt;enumeration value="DN"/>
     *               &lt;enumeration value="DD"/>
     *               &lt;enumeration value="DL"/>
     *               &lt;enumeration value="LD"/>
     *               &lt;enumeration value="PY"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="PIN" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/>
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
        "type",
        "address1",
        "city",
        "state",
        "pin"
    })
    public static class ADDRESS {

        @XmlElement(name = "TYPE", required = true)
        protected String type;
        @XmlJavaTypeAdapter(value = CDATAAdapter.class)
        @XmlElement(name = "ADDRESS-1", required = true)
        protected String address1;
        @XmlElement(name = "CITY", required = true)
        protected String city;
        @XmlElement(name = "STATE", required = true)
        protected String state;
        @XmlElement(name = "PIN")
        @XmlSchemaType(name = "unsignedInt")
        protected String pin;

        /**
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTYPE() {
            return type;
        }

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTYPE(String value) {
            this.type = value;
        }

        /**
         * Gets the value of the address1 property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getADDRESS1() {
            return address1;
        }

        /**
         * Sets the value of the address1 property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setADDRESS1(String value) {
            this.address1 = value;
        }

        /**
         * Gets the value of the city property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCITY() {
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
        public void setCITY(String value) {
            this.city = value;
        }

        /**
         * Gets the value of the state property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSTATE() {
            return state;
        }

        /**
         * Sets the value of the state property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSTATE(String value) {
            this.state = value;
        }

        /**
         * Gets the value of the pin property.
         * 
         */
        public String getPIN() {
            return pin;
        }

        /**
         * Sets the value of the pin property.
         * 
         */
        public void setPIN(String value) {
            this.pin = value;
        }

    }

}