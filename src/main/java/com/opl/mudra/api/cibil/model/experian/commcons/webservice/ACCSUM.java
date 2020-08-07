
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ACCSUM complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ACCSUM">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalCreditAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalClosedAccounts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MonthFirstToCredit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="YearFirstToCredit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalActiveAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalActiveSTDAccoun" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalActiveSUBAccoun" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalActiveSMAAccoun" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalActiveDBTAccoun" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalActiveLSSAccoun" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ACCSUM", propOrder = {
    "segmentCode",
    "totalCreditAccount",
    "totalClosedAccounts",
    "monthFirstToCredit",
    "yearFirstToCredit",
    "totalActiveAccount",
    "totalActiveSTDAccoun",
    "totalActiveSUBAccoun",
    "totalActiveSMAAccoun",
    "totalActiveDBTAccoun",
    "totalActiveLSSAccoun"
})
public class ACCSUM {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "TotalCreditAccount")
    protected String totalCreditAccount;
    @XmlElement(name = "TotalClosedAccounts")
    protected String totalClosedAccounts;
    @XmlElement(name = "MonthFirstToCredit")
    protected String monthFirstToCredit;
    @XmlElement(name = "YearFirstToCredit")
    protected String yearFirstToCredit;
    @XmlElement(name = "TotalActiveAccount")
    protected String totalActiveAccount;
    @XmlElement(name = "TotalActiveSTDAccoun")
    protected String totalActiveSTDAccoun;
    @XmlElement(name = "TotalActiveSUBAccoun")
    protected String totalActiveSUBAccoun;
    @XmlElement(name = "TotalActiveSMAAccoun")
    protected String totalActiveSMAAccoun;
    @XmlElement(name = "TotalActiveDBTAccoun")
    protected String totalActiveDBTAccoun;
    @XmlElement(name = "TotalActiveLSSAccoun")
    protected String totalActiveLSSAccoun;

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
     * Gets the value of the totalCreditAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalCreditAccount() {
        return totalCreditAccount;
    }

    /**
     * Sets the value of the totalCreditAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalCreditAccount(String value) {
        this.totalCreditAccount = value;
    }

    /**
     * Gets the value of the totalClosedAccounts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalClosedAccounts() {
        return totalClosedAccounts;
    }

    /**
     * Sets the value of the totalClosedAccounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalClosedAccounts(String value) {
        this.totalClosedAccounts = value;
    }

    /**
     * Gets the value of the monthFirstToCredit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonthFirstToCredit() {
        return monthFirstToCredit;
    }

    /**
     * Sets the value of the monthFirstToCredit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonthFirstToCredit(String value) {
        this.monthFirstToCredit = value;
    }

    /**
     * Gets the value of the yearFirstToCredit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYearFirstToCredit() {
        return yearFirstToCredit;
    }

    /**
     * Sets the value of the yearFirstToCredit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYearFirstToCredit(String value) {
        this.yearFirstToCredit = value;
    }

    /**
     * Gets the value of the totalActiveAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalActiveAccount() {
        return totalActiveAccount;
    }

    /**
     * Sets the value of the totalActiveAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalActiveAccount(String value) {
        this.totalActiveAccount = value;
    }

    /**
     * Gets the value of the totalActiveSTDAccoun property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalActiveSTDAccoun() {
        return totalActiveSTDAccoun;
    }

    /**
     * Sets the value of the totalActiveSTDAccoun property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalActiveSTDAccoun(String value) {
        this.totalActiveSTDAccoun = value;
    }

    /**
     * Gets the value of the totalActiveSUBAccoun property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalActiveSUBAccoun() {
        return totalActiveSUBAccoun;
    }

    /**
     * Sets the value of the totalActiveSUBAccoun property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalActiveSUBAccoun(String value) {
        this.totalActiveSUBAccoun = value;
    }

    /**
     * Gets the value of the totalActiveSMAAccoun property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalActiveSMAAccoun() {
        return totalActiveSMAAccoun;
    }

    /**
     * Sets the value of the totalActiveSMAAccoun property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalActiveSMAAccoun(String value) {
        this.totalActiveSMAAccoun = value;
    }

    /**
     * Gets the value of the totalActiveDBTAccoun property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalActiveDBTAccoun() {
        return totalActiveDBTAccoun;
    }

    /**
     * Sets the value of the totalActiveDBTAccoun property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalActiveDBTAccoun(String value) {
        this.totalActiveDBTAccoun = value;
    }

    /**
     * Gets the value of the totalActiveLSSAccoun property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalActiveLSSAccoun() {
        return totalActiveLSSAccoun;
    }

    /**
     * Sets the value of the totalActiveLSSAccoun property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalActiveLSSAccoun(String value) {
        this.totalActiveLSSAccoun = value;
    }

}
