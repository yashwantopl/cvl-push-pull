
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BUSOTH complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BUSOTH">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IncorporationDt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BusinessCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ActivityClass" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalSales" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FinancialYear" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ServiceTax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RecentCRRating" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CRAgency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BorrowerSicCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BUSOTH", propOrder = {
    "segmentCode",
    "incorporationDt",
    "businessCategory",
    "activityClass",
    "totalSales",
    "financialYear",
    "serviceTax",
    "recentCRRating",
    "crAgency",
    "borrowerSicCode"
})
public class BUSOTH {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "IncorporationDt")
    protected String incorporationDt;
    @XmlElement(name = "BusinessCategory")
    protected String businessCategory;
    @XmlElement(name = "ActivityClass")
    protected String activityClass;
    @XmlElement(name = "TotalSales")
    protected String totalSales;
    @XmlElement(name = "FinancialYear")
    protected String financialYear;
    @XmlElement(name = "ServiceTax")
    protected String serviceTax;
    @XmlElement(name = "RecentCRRating")
    protected String recentCRRating;
    @XmlElement(name = "CRAgency")
    protected String crAgency;
    @XmlElement(name = "BorrowerSicCode")
    protected String borrowerSicCode;

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
     * Gets the value of the incorporationDt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncorporationDt() {
        return incorporationDt;
    }

    /**
     * Sets the value of the incorporationDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncorporationDt(String value) {
        this.incorporationDt = value;
    }

    /**
     * Gets the value of the businessCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessCategory() {
        return businessCategory;
    }

    /**
     * Sets the value of the businessCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessCategory(String value) {
        this.businessCategory = value;
    }

    /**
     * Gets the value of the activityClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityClass() {
        return activityClass;
    }

    /**
     * Sets the value of the activityClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityClass(String value) {
        this.activityClass = value;
    }

    /**
     * Gets the value of the totalSales property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalSales() {
        return totalSales;
    }

    /**
     * Sets the value of the totalSales property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalSales(String value) {
        this.totalSales = value;
    }

    /**
     * Gets the value of the financialYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFinancialYear() {
        return financialYear;
    }

    /**
     * Sets the value of the financialYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinancialYear(String value) {
        this.financialYear = value;
    }

    /**
     * Gets the value of the serviceTax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceTax() {
        return serviceTax;
    }

    /**
     * Sets the value of the serviceTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceTax(String value) {
        this.serviceTax = value;
    }

    /**
     * Gets the value of the recentCRRating property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecentCRRating() {
        return recentCRRating;
    }

    /**
     * Sets the value of the recentCRRating property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecentCRRating(String value) {
        this.recentCRRating = value;
    }

    /**
     * Gets the value of the crAgency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCRAgency() {
        return crAgency;
    }

    /**
     * Sets the value of the crAgency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCRAgency(String value) {
        this.crAgency = value;
    }

    /**
     * Gets the value of the borrowerSicCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBorrowerSicCode() {
        return borrowerSicCode;
    }

    /**
     * Sets the value of the borrowerSicCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBorrowerSicCode(String value) {
        this.borrowerSicCode = value;
    }

}
