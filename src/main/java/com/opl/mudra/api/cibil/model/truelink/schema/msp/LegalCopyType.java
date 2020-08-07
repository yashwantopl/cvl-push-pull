
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.LocaleType;


/**
 * <p>Java class for LegalCopyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LegalCopyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LegalCopyId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Copy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{com/truelink/schema/database/tcps/enumerations}LegalCopy"/>
 *         &lt;element ref="{com/truelink/schema/database/tcps/enumerations}Locale"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LegalCopyType", propOrder = {
    "legalCopyId",
    "copy",
    "legalCopy",
    "locale"
})
public class LegalCopyType {

    @XmlElement(name = "LegalCopyId")
    protected long legalCopyId;
    @XmlElement(name = "Copy", required = true)
    protected String copy;
    @XmlElement(name = "LegalCopy", namespace = "com/truelink/schema/database/tcps/enumerations", required = true)
    @XmlSchemaType(name = "string")
    protected com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.LegalCopyType legalCopy;
    @XmlElement(name = "Locale", namespace = "com/truelink/schema/database/tcps/enumerations", required = true)
    @XmlSchemaType(name = "string")
    protected LocaleType locale;

    /**
     * Gets the value of the legalCopyId property.
     * 
     */
    public long getLegalCopyId() {
        return legalCopyId;
    }

    /**
     * Sets the value of the legalCopyId property.
     * 
     */
    public void setLegalCopyId(long value) {
        this.legalCopyId = value;
    }

    /**
     * Gets the value of the copy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCopy() {
        return copy;
    }

    /**
     * Sets the value of the copy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCopy(String value) {
        this.copy = value;
    }

    /**
     * Gets the value of the legalCopy property.
     * 
     * @return
     *     possible object is
     *     {@link com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.LegalCopyType }
     *     
     */
    public com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.LegalCopyType getLegalCopy() {
        return legalCopy;
    }

    /**
     * Sets the value of the legalCopy property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.LegalCopyType }
     *     
     */
    public void setLegalCopy(com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.LegalCopyType value) {
        this.legalCopy = value;
    }

    /**
     * Gets the value of the locale property.
     * 
     * @return
     *     possible object is
     *     {@link LocaleType }
     *     
     */
    public LocaleType getLocale() {
        return locale;
    }

    /**
     * Sets the value of the locale property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocaleType }
     *     
     */
    public void setLocale(LocaleType value) {
        this.locale = value;
    }

}
