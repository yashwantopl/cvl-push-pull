
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for CREDTYPE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CREDTYPE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CurrencyCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalCurrentBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PctTotalOwnCurrBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PctTotalPVTCurrBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PctTotalPUBCurrBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PctTotalMNCCurrBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PctTotalNBFC_OthCurrBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TotalCreditTypeNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PctTotalStandardCreditType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PctTotalSubStandardCreditType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PctTotalDoubtfulCreditType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PctTotalLossCreditType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PctTotalSpecMentionCreditType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ACCTYPINFO" type="{http://webservice.commcons.experian.com}ACCTYPINFO" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CREDTYPE", propOrder = {
    "segmentCode",
    "currencyCd",
    "totalCurrentBalance",
    "pctTotalOwnCurrBalance",
    "pctTotalPVTCurrBalance",
    "pctTotalPUBCurrBalance",
    "pctTotalMNCCurrBalance",
    "pctTotalNBFCOthCurrBalance",
    "totalCreditTypeNo",
    "pctTotalStandardCreditType",
    "pctTotalSubStandardCreditType",
    "pctTotalDoubtfulCreditType",
    "pctTotalLossCreditType",
    "pctTotalSpecMentionCreditType",
    "acctypinfo"
})
public class CREDTYPE {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "CurrencyCd")
    protected String currencyCd;
    @XmlElement(name = "TotalCurrentBalance")
    protected String totalCurrentBalance;
    @XmlElement(name = "PctTotalOwnCurrBalance")
    protected String pctTotalOwnCurrBalance;
    @XmlElement(name = "PctTotalPVTCurrBalance")
    protected String pctTotalPVTCurrBalance;
    @XmlElement(name = "PctTotalPUBCurrBalance")
    protected String pctTotalPUBCurrBalance;
    @XmlElement(name = "PctTotalMNCCurrBalance")
    protected String pctTotalMNCCurrBalance;
    @XmlElement(name = "PctTotalNBFC_OthCurrBalance")
    protected String pctTotalNBFCOthCurrBalance;
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
    @XmlElement(name = "ACCTYPINFO")
    protected List<ACCTYPINFO> acctypinfo;

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
     * Gets the value of the pctTotalOwnCurrBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPctTotalOwnCurrBalance() {
        return pctTotalOwnCurrBalance;
    }

    /**
     * Sets the value of the pctTotalOwnCurrBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPctTotalOwnCurrBalance(String value) {
        this.pctTotalOwnCurrBalance = value;
    }

    /**
     * Gets the value of the pctTotalPVTCurrBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPctTotalPVTCurrBalance() {
        return pctTotalPVTCurrBalance;
    }

    /**
     * Sets the value of the pctTotalPVTCurrBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPctTotalPVTCurrBalance(String value) {
        this.pctTotalPVTCurrBalance = value;
    }

    /**
     * Gets the value of the pctTotalPUBCurrBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPctTotalPUBCurrBalance() {
        return pctTotalPUBCurrBalance;
    }

    /**
     * Sets the value of the pctTotalPUBCurrBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPctTotalPUBCurrBalance(String value) {
        this.pctTotalPUBCurrBalance = value;
    }

    /**
     * Gets the value of the pctTotalMNCCurrBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPctTotalMNCCurrBalance() {
        return pctTotalMNCCurrBalance;
    }

    /**
     * Sets the value of the pctTotalMNCCurrBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPctTotalMNCCurrBalance(String value) {
        this.pctTotalMNCCurrBalance = value;
    }

    /**
     * Gets the value of the pctTotalNBFCOthCurrBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPctTotalNBFCOthCurrBalance() {
        return pctTotalNBFCOthCurrBalance;
    }

    /**
     * Sets the value of the pctTotalNBFCOthCurrBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPctTotalNBFCOthCurrBalance(String value) {
        this.pctTotalNBFCOthCurrBalance = value;
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

    /**
     * Gets the value of the acctypinfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the acctypinfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getACCTYPINFO().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ACCTYPINFO }
     * 
     * 
     */
    public List<ACCTYPINFO> getACCTYPINFO() {
        if (acctypinfo == null) {
            acctypinfo = new ArrayList<ACCTYPINFO>();
        }
        return this.acctypinfo;
    }

}
