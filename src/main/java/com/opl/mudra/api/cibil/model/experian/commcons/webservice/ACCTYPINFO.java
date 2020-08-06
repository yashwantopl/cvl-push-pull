
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ACCTYPINFO complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ACCTYPINFO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CurrencyCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalCurrentBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalCreditTypeNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PctTotalStandardCreditType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PctTotalSubStandardCreditType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PctTotalDoubtfulCreditType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PctTotalLossCreditType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PctTotalSpecMentionCreditType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ACCTYPINFO", propOrder = {
    "segmentCode",
    "accountType",
    "currencyCd",
    "totalCurrentBalance",
    "totalCreditTypeNo",
    "pctTotalStandardCreditType",
    "pctTotalSubStandardCreditType",
    "pctTotalDoubtfulCreditType",
    "pctTotalLossCreditType",
    "pctTotalSpecMentionCreditType"
})
public class ACCTYPINFO {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "AccountType")
    protected String accountType;
    @XmlElement(name = "CurrencyCd")
    protected String currencyCd;
    @XmlElement(name = "TotalCurrentBalance")
    protected String totalCurrentBalance;
    @XmlElement(name = "TotalCreditTypeNo")
    protected String totalCreditTypeNo;
    @XmlElement(name = "PctTotalStandardCreditType")
    protected String pctTotalStandardCreditType;
    @XmlElement(name = "PctTotalSubStandardCreditType")
    protected String pctTotalSubStandardCreditType;
    @XmlElement(name = "PctTotalDoubtfulCreditType")
    protected String pctTotalDoubtfulCreditType;
    @XmlElement(name = "PctTotalLossCreditType")
    protected String pctTotalLossCreditType;
    @XmlElement(name = "PctTotalSpecMentionCreditType")
    protected String pctTotalSpecMentionCreditType;

    /**
     * Gets the value of the segmentCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentCode() {
        return segmentCode;
    }

    /**
     * Sets the value of the segmentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentCode(String value) {
        this.segmentCode = value;
    }

    /**
     * Gets the value of the accountType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the value of the accountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountType(String value) {
        this.accountType = value;
    }

    /**
     * Gets the value of the currencyCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCd() {
        return currencyCd;
    }

    /**
     * Sets the value of the currencyCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCd(String value) {
        this.currencyCd = value;
    }

    /**
     * Gets the value of the totalCurrentBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalCurrentBalance() {
        return totalCurrentBalance;
    }

    /**
     * Sets the value of the totalCurrentBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalCurrentBalance(String value) {
        this.totalCurrentBalance = value;
    }

    /**
     * Gets the value of the totalCreditTypeNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalCreditTypeNo() {
        return totalCreditTypeNo;
    }

    /**
     * Sets the value of the totalCreditTypeNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalCreditTypeNo(String value) {
        this.totalCreditTypeNo = value;
    }

    /**
     * Gets the value of the pctTotalStandardCreditType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPctTotalStandardCreditType() {
        return pctTotalStandardCreditType;
    }

    /**
     * Sets the value of the pctTotalStandardCreditType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPctTotalStandardCreditType(String value) {
        this.pctTotalStandardCreditType = value;
    }

    /**
     * Gets the value of the pctTotalSubStandardCreditType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPctTotalSubStandardCreditType() {
        return pctTotalSubStandardCreditType;
    }

    /**
     * Sets the value of the pctTotalSubStandardCreditType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPctTotalSubStandardCreditType(String value) {
        this.pctTotalSubStandardCreditType = value;
    }

    /**
     * Gets the value of the pctTotalDoubtfulCreditType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPctTotalDoubtfulCreditType() {
        return pctTotalDoubtfulCreditType;
    }

    /**
     * Sets the value of the pctTotalDoubtfulCreditType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPctTotalDoubtfulCreditType(String value) {
        this.pctTotalDoubtfulCreditType = value;
    }

    /**
     * Gets the value of the pctTotalLossCreditType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPctTotalLossCreditType() {
        return pctTotalLossCreditType;
    }

    /**
     * Sets the value of the pctTotalLossCreditType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPctTotalLossCreditType(String value) {
        this.pctTotalLossCreditType = value;
    }

    /**
     * Gets the value of the pctTotalSpecMentionCreditType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPctTotalSpecMentionCreditType() {
        return pctTotalSpecMentionCreditType;
    }

    /**
     * Sets the value of the pctTotalSpecMentionCreditType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPctTotalSpecMentionCreditType(String value) {
        this.pctTotalSpecMentionCreditType = value;
    }

}
