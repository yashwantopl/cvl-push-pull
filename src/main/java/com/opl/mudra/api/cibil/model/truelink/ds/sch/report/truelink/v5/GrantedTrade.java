
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
 *         &lt;element name="AccountType" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="CreditType" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="PaymentFrequency" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="TermType" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="WorstPayStatus" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}PayStatusHistory" minOccurs="0"/>
 *         &lt;element name="CreditLimit" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="CashLimit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="EMIAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="CollateralType" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="amountPastDue" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="collateral" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="dateLastPayment" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="datePastDue" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="dateWorstPayStatus" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="late30Count" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="late60Count" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="late90Count" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="monthlyPayment" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="monthsReviewed" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="termMonths" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="worstPastStatus" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="actualPaymentAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="interestRate" type="{http://www.w3.org/2001/XMLSchema}decimal" />
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
    "creditLimit",
    "cashLimit",
    "emiAmount",
    "collateralType"
})
@XmlRootElement(name = "GrantedTrade")
public class GrantedTrade {

    @XmlElement(name = "AccountType", required = true)
    protected CodeRef accountType;
    @XmlElement(name = "CreditType", required = true)
    protected CodeRef creditType;
    @XmlElement(name = "PaymentFrequency", required = true)
    protected CodeRef paymentFrequency;
    @XmlElement(name = "TermType", required = true)
    protected CodeRef termType;
    @XmlElement(name = "WorstPayStatus", required = true)
    protected CodeRef worstPayStatus;
    @XmlElement(name = "PayStatusHistory")
    protected PayStatusHistory payStatusHistory;
    @XmlElement(name = "CreditLimit", required = true)
    protected BigDecimal creditLimit;
    @XmlElement(name = "CashLimit")
    protected BigDecimal cashLimit;
    @XmlElement(name = "EMIAmount")
    protected BigDecimal emiAmount;
    @XmlElement(name = "CollateralType")
    protected CodeRef collateralType;
    @XmlAttribute(name = "amountPastDue")
    protected BigDecimal amountPastDue;
    @XmlAttribute(name = "collateral", required = true)
    protected String collateral;
    @XmlAttribute(name = "dateLastPayment")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateLastPayment;
    @XmlAttribute(name = "datePastDue")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datePastDue;
    @XmlAttribute(name = "dateWorstPayStatus")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateWorstPayStatus;
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
    @XmlAttribute(name = "worstPastStatus")
    protected BigDecimal worstPastStatus;
    @XmlAttribute(name = "actualPaymentAmount")
    protected BigDecimal actualPaymentAmount;
    @XmlAttribute(name = "interestRate")
    protected BigDecimal interestRate;

    /**
     * Gets the value of the accountType property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getAccountType() {
        return accountType;
    }

    /**
     * Sets the value of the accountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setAccountType(CodeRef value) {
        this.accountType = value;
    }

    /**
     * Gets the value of the creditType property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getCreditType() {
        return creditType;
    }

    /**
     * Sets the value of the creditType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setCreditType(CodeRef value) {
        this.creditType = value;
    }

    /**
     * Gets the value of the paymentFrequency property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getPaymentFrequency() {
        return paymentFrequency;
    }

    /**
     * Sets the value of the paymentFrequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setPaymentFrequency(CodeRef value) {
        this.paymentFrequency = value;
    }

    /**
     * Gets the value of the termType property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getTermType() {
        return termType;
    }

    /**
     * Sets the value of the termType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setTermType(CodeRef value) {
        this.termType = value;
    }

    /**
     * Gets the value of the worstPayStatus property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getWorstPayStatus() {
        return worstPayStatus;
    }

    /**
     * Sets the value of the worstPayStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setWorstPayStatus(CodeRef value) {
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
     * Gets the value of the cashLimit property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCashLimit() {
        return cashLimit;
    }

    /**
     * Sets the value of the cashLimit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCashLimit(BigDecimal value) {
        this.cashLimit = value;
    }

    /**
     * Gets the value of the emiAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEMIAmount() {
        return emiAmount;
    }

    /**
     * Sets the value of the emiAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEMIAmount(BigDecimal value) {
        this.emiAmount = value;
    }

    /**
     * Gets the value of the collateralType property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getCollateralType() {
        return collateralType;
    }

    /**
     * Sets the value of the collateralType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setCollateralType(CodeRef value) {
        this.collateralType = value;
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
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCollateral() {
        return collateral;
    }

    /**
     * Sets the value of the collateral property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCollateral(String value) {
        this.collateral = value;
    }

    /**
     * Gets the value of the dateLastPayment property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateLastPayment() {
        return dateLastPayment;
    }

    /**
     * Sets the value of the dateLastPayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateLastPayment(XMLGregorianCalendar value) {
        this.dateLastPayment = value;
    }

    /**
     * Gets the value of the datePastDue property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatePastDue() {
        return datePastDue;
    }

    /**
     * Sets the value of the datePastDue property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatePastDue(XMLGregorianCalendar value) {
        this.datePastDue = value;
    }

    /**
     * Gets the value of the dateWorstPayStatus property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateWorstPayStatus() {
        return dateWorstPayStatus;
    }

    /**
     * Sets the value of the dateWorstPayStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateWorstPayStatus(XMLGregorianCalendar value) {
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
     * Gets the value of the worstPastStatus property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWorstPastStatus() {
        return worstPastStatus;
    }

    /**
     * Sets the value of the worstPastStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWorstPastStatus(BigDecimal value) {
        this.worstPastStatus = value;
    }

    /**
     * Gets the value of the actualPaymentAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getActualPaymentAmount() {
        return actualPaymentAmount;
    }

    /**
     * Sets the value of the actualPaymentAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setActualPaymentAmount(BigDecimal value) {
        this.actualPaymentAmount = value;
    }

    /**
     * Gets the value of the interestRate property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInterestRate() {
        return interestRate;
    }

    /**
     * Sets the value of the interestRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInterestRate(BigDecimal value) {
        this.interestRate = value;
    }

}
