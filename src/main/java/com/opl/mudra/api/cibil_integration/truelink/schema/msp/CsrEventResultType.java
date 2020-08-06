
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for CsrEventResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CsrEventResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Contacts">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Contact" type="{com/truelink/schema/msp}CsptContactType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Reasons">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Reason" type="{com/truelink/schema/msp}CsptReasonType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Actions">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Action" type="{com/truelink/schema/msp}CsptActionType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Products">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Product" type="{com/truelink/schema/msp}CsptProductType" maxOccurs="unbounded"/>
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
@XmlType(name = "CsrEventResultType", propOrder = {
    "contacts",
    "reasons",
    "actions",
    "products"
})
public class CsrEventResultType {

    @XmlElement(name = "Contacts", required = true)
    protected CsrEventResultType.Contacts contacts;
    @XmlElement(name = "Reasons", required = true)
    protected CsrEventResultType.Reasons reasons;
    @XmlElement(name = "Actions", required = true)
    protected CsrEventResultType.Actions actions;
    @XmlElement(name = "Products", required = true)
    protected CsrEventResultType.Products products;

    /**
     * Gets the value of the contacts property.
     *
     * @return
     *     possible object is
     *     {@link CsrEventResultType.Contacts }
     *
     */
    public CsrEventResultType.Contacts getContacts() {
        return contacts;
    }

    /**
     * Sets the value of the contacts property.
     *
     * @param value
     *     allowed object is
     *     {@link CsrEventResultType.Contacts }
     *
     */
    public void setContacts(CsrEventResultType.Contacts value) {
        this.contacts = value;
    }

    /**
     * Gets the value of the reasons property.
     *
     * @return
     *     possible object is
     *     {@link CsrEventResultType.Reasons }
     *
     */
    public CsrEventResultType.Reasons getReasons() {
        return reasons;
    }

    /**
     * Sets the value of the reasons property.
     *
     * @param value
     *     allowed object is
     *     {@link CsrEventResultType.Reasons }
     *
     */
    public void setReasons(CsrEventResultType.Reasons value) {
        this.reasons = value;
    }

    /**
     * Gets the value of the actions property.
     *
     * @return
     *     possible object is
     *     {@link CsrEventResultType.Actions }
     *
     */
    public CsrEventResultType.Actions getActions() {
        return actions;
    }

    /**
     * Sets the value of the actions property.
     *
     * @param value
     *     allowed object is
     *     {@link CsrEventResultType.Actions }
     *
     */
    public void setActions(CsrEventResultType.Actions value) {
        this.actions = value;
    }

    /**
     * Gets the value of the products property.
     *
     * @return
     *     possible object is
     *     {@link CsrEventResultType.Products }
     *
     */
    public CsrEventResultType.Products getProducts() {
        return products;
    }

    /**
     * Sets the value of the products property.
     *
     * @param value
     *     allowed object is
     *     {@link CsrEventResultType.Products }
     *
     */
    public void setProducts(CsrEventResultType.Products value) {
        this.products = value;
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
     *         &lt;element name="Action" type="{com/truelink/schema/msp}CsptActionType" maxOccurs="unbounded"/>
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
        "action"
    })
    public static class Actions {

        @XmlElement(name = "Action", required = true)
        protected List<CsptActionType> action;

        /**
         * Gets the value of the action property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the action property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAction().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CsptActionType }
         * 
         * 
         */
        public List<CsptActionType> getAction() {
            if (action == null) {
                action = new ArrayList<CsptActionType>();
            }
            return this.action;
        }

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
     *         &lt;element name="Contact" type="{com/truelink/schema/msp}CsptContactType" maxOccurs="unbounded"/>
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
        "contact"
    })
    public static class Contacts {

        @XmlElement(name = "Contact", required = true)
        protected List<CsptContactType> contact;

        /**
         * Gets the value of the contact property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the contact property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContact().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CsptContactType }
         * 
         * 
         */
        public List<CsptContactType> getContact() {
            if (contact == null) {
                contact = new ArrayList<CsptContactType>();
            }
            return this.contact;
        }

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
     *         &lt;element name="Product" type="{com/truelink/schema/msp}CsptProductType" maxOccurs="unbounded"/>
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
        "product"
    })
    public static class Products {

        @XmlElement(name = "Product", required = true)
        protected List<CsptProductType> product;

        /**
         * Gets the value of the product property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the product property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProduct().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CsptProductType }
         * 
         * 
         */
        public List<CsptProductType> getProduct() {
            if (product == null) {
                product = new ArrayList<CsptProductType>();
            }
            return this.product;
        }

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
     *         &lt;element name="Reason" type="{com/truelink/schema/msp}CsptReasonType" maxOccurs="unbounded"/>
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
        "reason"
    })
    public static class Reasons {

        @XmlElement(name = "Reason", required = true)
        protected List<CsptReasonType> reason;

        /**
         * Gets the value of the reason property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the reason property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getReason().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CsptReasonType }
         * 
         * 
         */
        public List<CsptReasonType> getReason() {
            if (reason == null) {
                reason = new ArrayList<CsptReasonType>();
            }
            return this.reason;
        }

    }

}
