
package com.opl.mudra.api.cibil.model.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TradelineSummaryInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TradelineSummaryInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="TotalAccounts" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="OpenAccounts" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="CloseAccounts" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="DelinquentAccounts" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="DerogatoryAccounts" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="TotalBalances" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="TotalMonthlyPayments" type="{http://www.w3.org/2001/XMLSchema}float" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TradelineSummaryInfo")
public class TradelineSummaryInfo {

    @XmlAttribute(name = "TotalAccounts")
    protected Integer totalAccounts;
    @XmlAttribute(name = "OpenAccounts")
    protected Integer openAccounts;
    @XmlAttribute(name = "CloseAccounts")
    protected Integer closeAccounts;
    @XmlAttribute(name = "DelinquentAccounts")
    protected Integer delinquentAccounts;
    @XmlAttribute(name = "DerogatoryAccounts")
    protected Integer derogatoryAccounts;
    @XmlAttribute(name = "TotalBalances")
    protected Float totalBalances;
    @XmlAttribute(name = "TotalMonthlyPayments")
    protected Float totalMonthlyPayments;

    /**
     * Gets the value of the totalAccounts property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalAccounts() {
        return totalAccounts;
    }

    /**
     * Sets the value of the totalAccounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalAccounts(Integer value) {
        this.totalAccounts = value;
    }

    /**
     * Gets the value of the openAccounts property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOpenAccounts() {
        return openAccounts;
    }

    /**
     * Sets the value of the openAccounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOpenAccounts(Integer value) {
        this.openAccounts = value;
    }

    /**
     * Gets the value of the closeAccounts property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCloseAccounts() {
        return closeAccounts;
    }

    /**
     * Sets the value of the closeAccounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCloseAccounts(Integer value) {
        this.closeAccounts = value;
    }

    /**
     * Gets the value of the delinquentAccounts property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDelinquentAccounts() {
        return delinquentAccounts;
    }

    /**
     * Sets the value of the delinquentAccounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDelinquentAccounts(Integer value) {
        this.delinquentAccounts = value;
    }

    /**
     * Gets the value of the derogatoryAccounts property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getDerogatoryAccounts() {
        return derogatoryAccounts;
    }

    /**
     * Sets the value of the derogatoryAccounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setDerogatoryAccounts(Integer value) {
        this.derogatoryAccounts = value;
    }

    /**
     * Gets the value of the totalBalances property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getTotalBalances() {
        return totalBalances;
    }

    /**
     * Sets the value of the totalBalances property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setTotalBalances(Float value) {
        this.totalBalances = value;
    }

    /**
     * Gets the value of the totalMonthlyPayments property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getTotalMonthlyPayments() {
        return totalMonthlyPayments;
    }

    /**
     * Sets the value of the totalMonthlyPayments property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setTotalMonthlyPayments(Float value) {
        this.totalMonthlyPayments = value;
    }

}
