
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;


import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.LegalResponseType;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for LegalCopyForMobileType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LegalCopyForMobileType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LegalCopyId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="AcceptLegalCopy" type="{com/truelink/schema/database/tcps/enumerations}LegalResponseType"/>
 *         &lt;element name="LegalCopyAcceptTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LegalCopyForMobileType", propOrder = {
    "legalCopyId",
    "acceptLegalCopy",
    "legalCopyAcceptTime"
})
public class LegalCopyForMobileType {

    @XmlElement(name = "LegalCopyId")
    protected long legalCopyId;
    @XmlElement(name = "AcceptLegalCopy", required = true)
    @XmlSchemaType(name = "string")
    protected LegalResponseType acceptLegalCopy;
    @XmlElement(name = "LegalCopyAcceptTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar legalCopyAcceptTime;

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
     * Gets the value of the acceptLegalCopy property.
     * 
     * @return
     *     possible object is
     *     {@link LegalResponseType }
     *     
     */
    public LegalResponseType getAcceptLegalCopy() {
        return acceptLegalCopy;
    }

    /**
     * Sets the value of the acceptLegalCopy property.
     * 
     * @param value
     *     allowed object is
     *     {@link LegalResponseType }
     *     
     */
    public void setAcceptLegalCopy(LegalResponseType value) {
        this.acceptLegalCopy = value;
    }

    /**
     * Gets the value of the legalCopyAcceptTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLegalCopyAcceptTime() {
        return legalCopyAcceptTime;
    }

    /**
     * Sets the value of the legalCopyAcceptTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLegalCopyAcceptTime(XMLGregorianCalendar value) {
        this.legalCopyAcceptTime = value;
    }

}
