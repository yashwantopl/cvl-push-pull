
package com.opl.mudra.api.cibil.model.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;


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
 *       &lt;/sequence>
 *       &lt;attribute name="dateSettled" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="liability" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "Foreclosure")
public class Foreclosure {

    @XmlAttribute(name = "dateSettled")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateSettled;
    @XmlAttribute(name = "liability")
    protected BigDecimal liability;

    /**
     * Gets the value of the dateSettled property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateSettled() {
        return dateSettled;
    }

    /**
     * Sets the value of the dateSettled property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateSettled(XMLGregorianCalendar value) {
        this.dateSettled = value;
    }

    /**
     * Gets the value of the liability property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLiability() {
        return liability;
    }

    /**
     * Sets the value of the liability property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLiability(BigDecimal value) {
        this.liability = value;
    }

}
