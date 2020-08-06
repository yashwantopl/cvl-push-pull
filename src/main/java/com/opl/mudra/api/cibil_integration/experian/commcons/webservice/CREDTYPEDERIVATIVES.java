
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CREDTYPEDERIVATIVES complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CREDTYPEDERIVATIVES">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreditFacilities" type="{http://webservice.commcons.experian.com}CreditFacilities" minOccurs="0"/>
 *         &lt;element name="TotalOutstandingBalance" type="{http://webservice.commcons.experian.com}TotalOutstandingBalance" minOccurs="0"/>
 *         &lt;element name="RecentOpenDate" type="{http://webservice.commcons.experian.com}RecentOpenDate" minOccurs="0"/>
 *         &lt;element name="DelCreditFacilities" type="{http://webservice.commcons.experian.com}DelCreditFacilities" minOccurs="0"/>
 *         &lt;element name="DelOutstandingBalance" type="{http://webservice.commcons.experian.com}DelOutstandingBalance" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CREDTYPEDERIVATIVES", propOrder = {
    "segmentCode",
    "creditFacilities",
    "totalOutstandingBalance",
    "recentOpenDate",
    "delCreditFacilities",
    "delOutstandingBalance"
})
public class CREDTYPEDERIVATIVES {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "CreditFacilities")
    protected CreditFacilities creditFacilities;
    @XmlElement(name = "TotalOutstandingBalance")
    protected TotalOutstandingBalance totalOutstandingBalance;
    @XmlElement(name = "RecentOpenDate")
    protected RecentOpenDate recentOpenDate;
    @XmlElement(name = "DelCreditFacilities")
    protected DelCreditFacilities delCreditFacilities;
    @XmlElement(name = "DelOutstandingBalance")
    protected DelOutstandingBalance delOutstandingBalance;

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
     * Gets the value of the creditFacilities property.
     * 
     * @return
     *     possible object is
     *     {@link CreditFacilities }
     *     
     */
    public CreditFacilities getCreditFacilities() {
        return creditFacilities;
    }

    /**
     * Sets the value of the creditFacilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditFacilities }
     *     
     */
    public void setCreditFacilities(CreditFacilities value) {
        this.creditFacilities = value;
    }

    /**
     * Gets the value of the totalOutstandingBalance property.
     * 
     * @return
     *     possible object is
     *     {@link TotalOutstandingBalance }
     *     
     */
    public TotalOutstandingBalance getTotalOutstandingBalance() {
        return totalOutstandingBalance;
    }

    /**
     * Sets the value of the totalOutstandingBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link TotalOutstandingBalance }
     *     
     */
    public void setTotalOutstandingBalance(TotalOutstandingBalance value) {
        this.totalOutstandingBalance = value;
    }

    /**
     * Gets the value of the recentOpenDate property.
     * 
     * @return
     *     possible object is
     *     {@link RecentOpenDate }
     *     
     */
    public RecentOpenDate getRecentOpenDate() {
        return recentOpenDate;
    }

    /**
     * Sets the value of the recentOpenDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecentOpenDate }
     *     
     */
    public void setRecentOpenDate(RecentOpenDate value) {
        this.recentOpenDate = value;
    }

    /**
     * Gets the value of the delCreditFacilities property.
     * 
     * @return
     *     possible object is
     *     {@link DelCreditFacilities }
     *     
     */
    public DelCreditFacilities getDelCreditFacilities() {
        return delCreditFacilities;
    }

    /**
     * Sets the value of the delCreditFacilities property.
     * 
     * @param value
     *     allowed object is
     *     {@link DelCreditFacilities }
     *     
     */
    public void setDelCreditFacilities(DelCreditFacilities value) {
        this.delCreditFacilities = value;
    }

    /**
     * Gets the value of the delOutstandingBalance property.
     * 
     * @return
     *     possible object is
     *     {@link DelOutstandingBalance }
     *     
     */
    public DelOutstandingBalance getDelOutstandingBalance() {
        return delOutstandingBalance;
    }

    /**
     * Sets the value of the delOutstandingBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link DelOutstandingBalance }
     *     
     */
    public void setDelOutstandingBalance(DelOutstandingBalance value) {
        this.delOutstandingBalance = value;
    }

}
