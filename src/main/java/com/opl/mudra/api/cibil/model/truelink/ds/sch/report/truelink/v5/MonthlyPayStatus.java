
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
 *         &lt;element name="GenericRemark" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef" minOccurs="0"/>
 *         &lt;element name="RatingRemark" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef" minOccurs="0"/>
 *         &lt;element name="ComplianceRemark" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="date" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="status" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="changed" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="currentBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="highCredit" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="CreditLimit" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="PaymentDue" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="PastDue" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="ActualPayment" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "genericRemark",
    "ratingRemark",
    "complianceRemark"
})
@XmlRootElement(name = "MonthlyPayStatus")
public class MonthlyPayStatus {

    @XmlElement(name = "GenericRemark")
    protected CodeRef genericRemark;
    @XmlElement(name = "RatingRemark")
    protected CodeRef ratingRemark;
    @XmlElement(name = "ComplianceRemark")
    protected CodeRef complianceRemark;
    @XmlAttribute(name = "date")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlAttribute(name = "status")
    protected String status;
    @XmlAttribute(name = "changed")
    protected Boolean changed;
    @XmlAttribute(name = "currentBalance")
    protected BigDecimal currentBalance;
    @XmlAttribute(name = "highCredit")
    protected BigDecimal highCredit;
    @XmlAttribute(name = "CreditLimit")
    protected BigDecimal creditLimit;
    @XmlAttribute(name = "PaymentDue")
    protected BigDecimal paymentDue;
    @XmlAttribute(name = "PastDue")
    protected BigDecimal pastDue;
    @XmlAttribute(name = "ActualPayment")
    protected BigDecimal actualPayment;

    /**
     * Gets the value of the genericRemark property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getGenericRemark() {
        return genericRemark;
    }

    /**
     * Sets the value of the genericRemark property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setGenericRemark(CodeRef value) {
        this.genericRemark = value;
    }

    /**
     * Gets the value of the ratingRemark property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getRatingRemark() {
        return ratingRemark;
    }

    /**
     * Sets the value of the ratingRemark property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setRatingRemark(CodeRef value) {
        this.ratingRemark = value;
    }

    /**
     * Gets the value of the complianceRemark property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getComplianceRemark() {
        return complianceRemark;
    }

    /**
     * Sets the value of the complianceRemark property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setComplianceRemark(CodeRef value) {
        this.complianceRemark = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the changed property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isChanged() {
        return changed;
    }

    /**
     * Sets the value of the changed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setChanged(Boolean value) {
        this.changed = value;
    }

    /**
     * Gets the value of the currentBalance property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    /**
     * Sets the value of the currentBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCurrentBalance(BigDecimal value) {
        this.currentBalance = value;
    }

    /**
     * Gets the value of the highCredit property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getHighCredit() {
        return highCredit;
    }

    /**
     * Sets the value of the highCredit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setHighCredit(BigDecimal value) {
        this.highCredit = value;
    }

    /**
     * Gets the value of the creditLimit property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    /**
     * Sets the value of the creditLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCreditLimit(BigDecimal value) {
        this.creditLimit = value;
    }

    /**
     * Gets the value of the paymentDue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPaymentDue() {
        return paymentDue;
    }

    /**
     * Sets the value of the paymentDue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPaymentDue(BigDecimal value) {
        this.paymentDue = value;
    }

    /**
     * Gets the value of the pastDue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPastDue() {
        return pastDue;
    }

    /**
     * Sets the value of the pastDue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPastDue(BigDecimal value) {
        this.pastDue = value;
    }

    /**
     * Gets the value of the actualPayment property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getActualPayment() {
        return actualPayment;
    }

    /**
     * Sets the value of the actualPayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setActualPayment(BigDecimal value) {
        this.actualPayment = value;
    }

}
