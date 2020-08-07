
package com.opl.mudra.api.cibil_integration.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element name="BankingType" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="AccountDesignator" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="IndustryCode" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="Status" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Remark" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Source"/>
 *       &lt;/sequence>
 *       &lt;attribute name="bankName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="subscriberCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="accountNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="balance" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="dateClosed" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="dateOpened" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="dateVerified" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="bureau" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "bankingType",
    "accountDesignator",
    "industryCode",
    "status",
    "remark",
    "source"
})
@XmlRootElement(name = "BankingRecord")
public class BankingRecord {

    @XmlElement(name = "BankingType", required = true)
    protected CodeRef bankingType;
    @XmlElement(name = "AccountDesignator", required = true)
    protected CodeRef accountDesignator;
    @XmlElement(name = "IndustryCode", required = true)
    protected CodeRef industryCode;
    @XmlElement(name = "Status", required = true)
    protected CodeRef status;
    @XmlElement(name = "Remark")
    protected List<Remark> remark;
    @XmlElement(name = "Source", required = true)
    protected Source source;
    @XmlAttribute(name = "bankName", required = true)
    protected String bankName;
    @XmlAttribute(name = "subscriberCode", required = true)
    protected String subscriberCode;
    @XmlAttribute(name = "accountNumber", required = true)
    protected String accountNumber;
    @XmlAttribute(name = "balance", required = true)
    protected BigDecimal balance;
    @XmlAttribute(name = "dateClosed")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateClosed;
    @XmlAttribute(name = "dateOpened")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateOpened;
    @XmlAttribute(name = "dateVerified")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateVerified;
    @XmlAttribute(name = "bureau", required = true)
    protected String bureau;

    /**
     * Gets the value of the bankingType property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getBankingType() {
        return bankingType;
    }

    /**
     * Sets the value of the bankingType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setBankingType(CodeRef value) {
        this.bankingType = value;
    }

    /**
     * Gets the value of the accountDesignator property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getAccountDesignator() {
        return accountDesignator;
    }

    /**
     * Sets the value of the accountDesignator property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setAccountDesignator(CodeRef value) {
        this.accountDesignator = value;
    }

    /**
     * Gets the value of the industryCode property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getIndustryCode() {
        return industryCode;
    }

    /**
     * Sets the value of the industryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setIndustryCode(CodeRef value) {
        this.industryCode = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setStatus(CodeRef value) {
        this.status = value;
    }

    /**
     * Gets the value of the remark property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the remark property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRemark().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Remark }
     * 
     * 
     */
    public List<Remark> getRemark() {
        if (remark == null) {
            remark = new ArrayList<Remark>();
        }
        return this.remark;
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
     * Gets the value of the bankName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Sets the value of the bankName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankName(String value) {
        this.bankName = value;
    }

    /**
     * Gets the value of the subscriberCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriberCode() {
        return subscriberCode;
    }

    /**
     * Sets the value of the subscriberCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriberCode(String value) {
        this.subscriberCode = value;
    }

    /**
     * Gets the value of the accountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the accountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNumber(String value) {
        this.accountNumber = value;
    }

    /**
     * Gets the value of the balance property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * Sets the value of the balance property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBalance(BigDecimal value) {
        this.balance = value;
    }

    /**
     * Gets the value of the dateClosed property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateClosed() {
        return dateClosed;
    }

    /**
     * Sets the value of the dateClosed property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateClosed(XMLGregorianCalendar value) {
        this.dateClosed = value;
    }

    /**
     * Gets the value of the dateOpened property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateOpened() {
        return dateOpened;
    }

    /**
     * Sets the value of the dateOpened property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateOpened(XMLGregorianCalendar value) {
        this.dateOpened = value;
    }

    /**
     * Gets the value of the dateVerified property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateVerified() {
        return dateVerified;
    }

    /**
     * Sets the value of the dateVerified property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateVerified(XMLGregorianCalendar value) {
        this.dateVerified = value;
    }

    /**
     * Gets the value of the bureau property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBureau() {
        return bureau;
    }

    /**
     * Sets the value of the bureau property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBureau(String value) {
        this.bureau = value;
    }

}
