
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
 *         &lt;element name="CreditorType" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *       &lt;/sequence>
 *       &lt;attribute name="dateMaturity" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "creditorType"
})
@XmlRootElement(name = "FinancingStatement")
public class FinancingStatement {

    @XmlElement(name = "CreditorType", required = true)
    protected CodeRef creditorType;
    @XmlAttribute(name = "dateMaturity")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateMaturity;

    /**
     * Gets the value of the creditorType property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getCreditorType() {
        return creditorType;
    }

    /**
     * Sets the value of the creditorType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setCreditorType(CodeRef value) {
        this.creditorType = value;
    }

    /**
     * Gets the value of the dateMaturity property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateMaturity() {
        return dateMaturity;
    }

    /**
     * Sets the value of the dateMaturity property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateMaturity(XMLGregorianCalendar value) {
        this.dateMaturity = value;
    }

}
