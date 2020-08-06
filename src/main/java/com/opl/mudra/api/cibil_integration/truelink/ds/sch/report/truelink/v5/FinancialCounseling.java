
package com.opl.mudra.api.cibil_integration.truelink.ds.sch.report.truelink.v5;

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
 *       &lt;attribute name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="dateChecked" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="dateSettled" type="{http://www.w3.org/2001/XMLSchema}date" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "FinancialCounseling")
public class FinancialCounseling {

    @XmlAttribute(name = "amount")
    protected BigDecimal amount;
    @XmlAttribute(name = "dateChecked")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateChecked;
    @XmlAttribute(name = "dateSettled")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateSettled;

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

    /**
     * Gets the value of the dateChecked property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateChecked() {
        return dateChecked;
    }

    /**
     * Sets the value of the dateChecked property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateChecked(XMLGregorianCalendar value) {
        this.dateChecked = value;
    }

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

}
