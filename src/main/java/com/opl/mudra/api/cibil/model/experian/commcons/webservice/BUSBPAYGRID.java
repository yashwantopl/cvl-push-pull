
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BUSBPAYGRID complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BUSBPAYGRID">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TimePeriodInd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Year" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WeekNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Monthvalue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PaymentStatusValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DaysPastDue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AssetClassification" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BUSHDETAILS" type="{http://webservice.commcons.experian.com}BUSHDETAILS" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BUSBPAYGRID", propOrder = {
    "segmentCode",
    "timePeriodInd",
    "year",
    "weekNumber",
    "monthvalue",
    "accountDate",
    "paymentStatusValue",
    "daysPastDue",
    "assetClassification",
    "bushdetails"
})
public class BUSBPAYGRID {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "TimePeriodInd")
    protected String timePeriodInd;
    @XmlElement(name = "Year")
    protected int year;
    @XmlElement(name = "WeekNumber")
    protected String weekNumber;
    @XmlElement(name = "Monthvalue")
    protected String monthvalue;
    @XmlElement(name = "AccountDate")
    protected String accountDate;
    @XmlElement(name = "PaymentStatusValue")
    protected String paymentStatusValue;
    @XmlElement(name = "DaysPastDue")
    protected int daysPastDue;
    @XmlElement(name = "AssetClassification")
    protected String assetClassification;
    @XmlElement(name = "BUSHDETAILS")
    protected BUSHDETAILS bushdetails;

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
     * Gets the value of the timePeriodInd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimePeriodInd() {
        return timePeriodInd;
    }

    /**
     * Sets the value of the timePeriodInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimePeriodInd(String value) {
        this.timePeriodInd = value;
    }

    /**
     * Gets the value of the year property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the value of the year property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYear(int value) {
        this.year = value;
    }

    /**
     * Gets the value of the weekNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWeekNumber() {
        return weekNumber;
    }

    /**
     * Sets the value of the weekNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWeekNumber(String value) {
        this.weekNumber = value;
    }

    /**
     * Gets the value of the monthvalue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonthvalue() {
        return monthvalue;
    }

    /**
     * Sets the value of the monthvalue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonthvalue(String value) {
        this.monthvalue = value;
    }

    /**
     * Gets the value of the accountDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountDate() {
        return accountDate;
    }

    /**
     * Sets the value of the accountDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountDate(String value) {
        this.accountDate = value;
    }

    /**
     * Gets the value of the paymentStatusValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentStatusValue() {
        return paymentStatusValue;
    }

    /**
     * Sets the value of the paymentStatusValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentStatusValue(String value) {
        this.paymentStatusValue = value;
    }

    /**
     * Gets the value of the daysPastDue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public int getDaysPastDue() {
        return daysPastDue;
    }

    /**
     * Sets the value of the daysPastDue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDaysPastDue(int value) {
        this.daysPastDue = value;
    }

    /**
     * Gets the value of the assetClassification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssetClassification() {
        return assetClassification;
    }

    /**
     * Sets the value of the assetClassification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssetClassification(String value) {
        this.assetClassification = value;
    }

    /**
     * Gets the value of the bushdetails property.
     * 
     * @return
     *     possible object is
     *     {@link BUSHDETAILS }
     *     
     */
    public BUSHDETAILS getBUSHDETAILS() {
        return bushdetails;
    }

    /**
     * Sets the value of the bushdetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link BUSHDETAILS }
     *     
     */
    public void setBUSHDETAILS(BUSHDETAILS value) {
        this.bushdetails = value;
    }

}
