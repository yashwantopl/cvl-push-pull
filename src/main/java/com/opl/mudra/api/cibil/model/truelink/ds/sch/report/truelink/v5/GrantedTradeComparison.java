
package com.opl.mudra.api.cibil.model.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.*;
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
 *         &lt;element name="AccountType" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="CreditType" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PaymentFrequency" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="TermType" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="WorstPayStatus" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}PayStatusHistory" minOccurs="0"/>
 *         &lt;element name="CreditLimit" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *       &lt;attribute name="amountPastDue" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="collateral" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="dateLastPayment" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="datePastDue" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="dateWorstPayStatus" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="late30Count" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="late60Count" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="late90Count" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="monthlyPayment" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="monthsReviewed" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="termMonths" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="worstPayStatusCount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "accountType",
    "creditType",
    "paymentFrequency",
    "termType",
    "worstPayStatus",
    "payStatusHistory",
    "creditLimit"
})
@XmlRootElement(name = "GrantedTradeComparison")
public class GrantedTradeComparison {

    @XmlElement(name = "AccountType")
    protected boolean accountType;
    @XmlElement(name = "CreditType")
    protected boolean creditType;
    @XmlElement(name = "PaymentFrequency")
    protected boolean paymentFrequency;
    @XmlElement(name = "TermType")
    protected boolean termType;
    @XmlElement(name = "WorstPayStatus")
    protected boolean worstPayStatus;
    @XmlElement(name = "PayStatusHistory")
    protected PayStatusHistory payStatusHistory;
    @XmlElement(name = "CreditLimit", required = true)
    protected BigDecimal creditLimit;
    @XmlAttribute(name = "amountPastDue", required = true)
    protected BigDecimal amountPastDue;
    @XmlAttribute(name = "collateral", required = true)
    protected boolean collateral;
    @XmlAttribute(name = "dateLastPayment")
    protected Boolean dateLastPayment;
    @XmlAttribute(name = "datePastDue")
    protected Boolean datePastDue;
    @XmlAttribute(name = "dateWorstPayStatus")
    protected Boolean dateWorstPayStatus;
    @XmlAttribute(name = "late30Count")
    protected BigDecimal late30Count;
    @XmlAttribute(name = "late60Count")
    protected BigDecimal late60Count;
    @XmlAttribute(name = "late90Count")
    protected BigDecimal late90Count;
    @XmlAttribute(name = "monthlyPayment")
    protected BigDecimal monthlyPayment;
    @XmlAttribute(name = "monthsReviewed")
    protected BigDecimal monthsReviewed;
    @XmlAttribute(name = "termMonths")
    protected BigDecimal termMonths;
    @XmlAttribute(name = "worstPayStatusCount")
    protected BigDecimal worstPayStatusCount;

    /**
     * Gets the value of the accountType property.
     * 
     */
    public boolean isAccountType() {
        return accountType;
    }

    /**
     * Sets the value of the accountType property.
     * 
     */
    public void setAccountType(boolean value) {
        this.accountType = value;
    }

    /**
     * Gets the value of the creditType property.
     * 
     */
    public boolean isCreditType() {
        return creditType;
    }

    /**
     * Sets the value of the creditType property.
     * 
     */
    public void setCreditType(boolean value) {
        this.creditType = value;
    }

    /**
     * Gets the value of the paymentFrequency property.
     * 
     */
    public boolean isPaymentFrequency() {
        return paymentFrequency;
    }

    /**
     * Sets the value of the paymentFrequency property.
     * 
     */
    public void setPaymentFrequency(boolean value) {
        this.paymentFrequency = value;
    }

    /**
     * Gets the value of the termType property.
     * 
     */
    public boolean isTermType() {
        return termType;
    }

    /**
     * Sets the value of the termType property.
     * 
     */
    public void setTermType(boolean value) {
        this.termType = value;
    }

    /**
     * Gets the value of the worstPayStatus property.
     * 
     */
    public boolean isWorstPayStatus() {
        return worstPayStatus;
    }

    /**
     * Sets the value of the worstPayStatus property.
     * 
     */
    public void setWorstPayStatus(boolean value) {
        this.worstPayStatus = value;
    }

    /**
     * Gets the value of the payStatusHistory property.
     * 
     * @return
     *     possible object is
     *     {@link PayStatusHistory }
     *     
     */
    public PayStatusHistory getPayStatusHistory() {
        return payStatusHistory;
    }

    /**
     * Sets the value of the payStatusHistory property.
     * 
     * @param value
     *     allowed object is
     *     {@link PayStatusHistory }
     *     
     */
    public void setPayStatusHistory(PayStatusHistory value) {
        this.payStatusHistory = value;
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
     * Gets the value of the amountPastDue property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmountPastDue() {
        return amountPastDue;
    }

    /**
     * Sets the value of the amountPastDue property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmountPastDue(BigDecimal value) {
        this.amountPastDue = value;
    }

    /**
     * Gets the value of the collateral property.
     * 
     */
    public boolean isCollateral() {
        return collateral;
    }

    /**
     * Sets the value of the collateral property.
     * 
     */
    public void setCollateral(boolean value) {
        this.collateral = value;
    }

    /**
     * Gets the value of the dateLastPayment property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDateLastPayment() {
        return dateLastPayment;
    }

    /**
     * Sets the value of the dateLastPayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDateLastPayment(Boolean value) {
        this.dateLastPayment = value;
    }

    /**
     * Gets the value of the datePastDue property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDatePastDue() {
        return datePastDue;
    }

    /**
     * Sets the value of the datePastDue property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDatePastDue(Boolean value) {
        this.datePastDue = value;
    }

    /**
     * Gets the value of the dateWorstPayStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDateWorstPayStatus() {
        return dateWorstPayStatus;
    }

    /**
     * Sets the value of the dateWorstPayStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDateWorstPayStatus(Boolean value) {
        this.dateWorstPayStatus = value;
    }

    /**
     * Gets the value of the late30Count property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLate30Count() {
        return late30Count;
    }

    /**
     * Sets the value of the late30Count property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLate30Count(BigDecimal value) {
        this.late30Count = value;
    }

    /**
     * Gets the value of the late60Count property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLate60Count() {
        return late60Count;
    }

    /**
     * Sets the value of the late60Count property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLate60Count(BigDecimal value) {
        this.late60Count = value;
    }

    /**
     * Gets the value of the late90Count property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLate90Count() {
        return late90Count;
    }

    /**
     * Sets the value of the late90Count property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLate90Count(BigDecimal value) {
        this.late90Count = value;
    }

    /**
     * Gets the value of the monthlyPayment property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    /**
     * Sets the value of the monthlyPayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMonthlyPayment(BigDecimal value) {
        this.monthlyPayment = value;
    }

    /**
     * Gets the value of the monthsReviewed property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMonthsReviewed() {
        return monthsReviewed;
    }

    /**
     * Sets the value of the monthsReviewed property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMonthsReviewed(BigDecimal value) {
        this.monthsReviewed = value;
    }

    /**
     * Gets the value of the termMonths property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTermMonths() {
        return termMonths;
    }

    /**
     * Sets the value of the termMonths property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTermMonths(BigDecimal value) {
        this.termMonths = value;
    }

    /**
     * Gets the value of the worstPayStatusCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWorstPayStatusCount() {
        return worstPayStatusCount;
    }

    /**
     * Sets the value of the worstPayStatusCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWorstPayStatusCount(BigDecimal value) {
        this.worstPayStatusCount = value;
    }

}
