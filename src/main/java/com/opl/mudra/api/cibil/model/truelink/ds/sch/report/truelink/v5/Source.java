
package com.opl.mudra.api.cibil.model.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="BorrowerKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Bureau" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="InquiryDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Reference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Locale" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "borrowerKey",
    "bureau",
    "inquiryDate",
    "reference",
    "locale"
})
@XmlRootElement(name = "Source")
public class Source {

    @XmlElement(name = "BorrowerKey", required = true)
    protected String borrowerKey;
    @XmlElement(name = "Bureau", required = true)
    protected CodeRef bureau;
    @XmlElement(name = "InquiryDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar inquiryDate;
    @XmlElement(name = "Reference", required = true)
    protected String reference;
    @XmlElement(name = "Locale")
    protected String locale;

    /**
     * Gets the value of the borrowerKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerKey() {
        return borrowerKey;
    }

    /**
     * Sets the value of the borrowerKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerKey(String value) {
        this.borrowerKey = value;
    }

    /**
     * Gets the value of the bureau property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getBureau() {
        return bureau;
    }

    /**
     * Sets the value of the bureau property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setBureau(CodeRef value) {
        this.bureau = value;
    }

    /**
     * Gets the value of the inquiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInquiryDate() {
        return inquiryDate;
    }

    /**
     * Sets the value of the inquiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInquiryDate(XMLGregorianCalendar value) {
        this.inquiryDate = value;
    }

    /**
     * Gets the value of the reference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReference() {
        return reference;
    }

    /**
     * Sets the value of the reference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReference(String value) {
        this.reference = value;
    }

    /**
     * Gets the value of the locale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Sets the value of the locale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocale(String value) {
        this.locale = value;
    }

}
