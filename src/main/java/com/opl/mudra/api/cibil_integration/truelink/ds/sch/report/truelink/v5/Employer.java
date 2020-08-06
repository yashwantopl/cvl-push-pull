
package com.opl.mudra.api.cibil_integration.truelink.ds.sch.report.truelink.v5;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.opl.mudra.api.cibil_integration.truelink.ds.sch.pii.v1.AddressType;


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
 *         &lt;element name="CreditAddress" type="{com/truelink/ds/sch/pii/v1}AddressType" minOccurs="0"/>
 *         &lt;element name="OccupationCode" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef" minOccurs="0"/>
 *         &lt;element name="NetGrossIndicator" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef" minOccurs="0"/>
 *         &lt;element name="IncomeFreqIndicator" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Source"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}DisputeRemarks" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="dateReported" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="dateUpdated" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="income" type="{http://www.w3.org/2001/XMLSchema}double" />
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="partitionSet" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="account" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "creditAddress",
    "occupationCode",
    "netGrossIndicator",
    "incomeFreqIndicator",
    "source",
    "disputeRemarks"
})
@XmlRootElement(name = "Employer")
public class Employer {

    @XmlElement(name = "CreditAddress")
    protected AddressType creditAddress;
    @XmlElement(name = "OccupationCode")
    protected CodeRef occupationCode;
    @XmlElement(name = "NetGrossIndicator")
    protected CodeRef netGrossIndicator;
    @XmlElement(name = "IncomeFreqIndicator")
    protected CodeRef incomeFreqIndicator;
    @XmlElement(name = "Source", required = true)
    protected Source source;
    @XmlElement(name = "DisputeRemarks")
    protected DisputeRemarks disputeRemarks;
    @XmlAttribute(name = "dateReported")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateReported;
    @XmlAttribute(name = "dateUpdated")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateUpdated;
    @XmlAttribute(name = "income")
    protected Double income;
    @XmlAttribute(name = "name")
    protected String name;
    @XmlAttribute(name = "partitionSet")
    protected Integer partitionSet;
    @XmlAttribute(name = "account")
    protected String account;

    /**
     * Gets the value of the creditAddress property.
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getCreditAddress() {
        return creditAddress;
    }

    /**
     * Sets the value of the creditAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     */
    public void setCreditAddress(AddressType value) {
        this.creditAddress = value;
    }

    /**
     * Gets the value of the occupationCode property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getOccupationCode() {
        return occupationCode;
    }

    /**
     * Sets the value of the occupationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setOccupationCode(CodeRef value) {
        this.occupationCode = value;
    }

    /**
     * Gets the value of the netGrossIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getNetGrossIndicator() {
        return netGrossIndicator;
    }

    /**
     * Sets the value of the netGrossIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setNetGrossIndicator(CodeRef value) {
        this.netGrossIndicator = value;
    }

    /**
     * Gets the value of the incomeFreqIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getIncomeFreqIndicator() {
        return incomeFreqIndicator;
    }

    /**
     * Sets the value of the incomeFreqIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setIncomeFreqIndicator(CodeRef value) {
        this.incomeFreqIndicator = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link Source }
     *     
     */
    public Source getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link Source }
     *     
     */
    public void setSource(Source value) {
        this.source = value;
    }

    /**
     * Gets the value of the disputeRemarks property.
     * 
     * @return
     *     possible object is
     *     {@link DisputeRemarks }
     *     
     */
    public DisputeRemarks getDisputeRemarks() {
        return disputeRemarks;
    }

    /**
     * Sets the value of the disputeRemarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link DisputeRemarks }
     *     
     */
    public void setDisputeRemarks(DisputeRemarks value) {
        this.disputeRemarks = value;
    }

    /**
     * Gets the value of the dateReported property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateReported() {
        return dateReported;
    }

    /**
     * Sets the value of the dateReported property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateReported(XMLGregorianCalendar value) {
        this.dateReported = value;
    }

    /**
     * Gets the value of the dateUpdated property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateUpdated() {
        return dateUpdated;
    }

    /**
     * Sets the value of the dateUpdated property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateUpdated(XMLGregorianCalendar value) {
        this.dateUpdated = value;
    }

    /**
     * Gets the value of the income property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getIncome() {
        return income;
    }

    /**
     * Sets the value of the income property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setIncome(Double value) {
        this.income = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the partitionSet property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPartitionSet() {
        return partitionSet;
    }

    /**
     * Sets the value of the partitionSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPartitionSet(Integer value) {
        this.partitionSet = value;
    }

    /**
     * Gets the value of the account property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccount() {
        return account;
    }

    /**
     * Sets the value of the account property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccount(String value) {
        this.account = value;
    }

}
