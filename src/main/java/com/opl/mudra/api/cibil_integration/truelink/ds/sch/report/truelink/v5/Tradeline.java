
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
 *         &lt;element name="AccountCondition" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef" maxOccurs="unbounded"/>
 *         &lt;element name="AccountDesignator" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="DisputeFlag" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef" minOccurs="0"/>
 *         &lt;element name="IndustryCode" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="OpenClosed" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="PayStatus" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element name="VerificationIndicator" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Remark" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}CollectionTrade" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}GrantedTrade" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}WatchTrade" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Source"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}DisputeRemarks" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="accountNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="creditorName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="currentBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="dateAccountStatus" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="dateClosed" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="dateOpened" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="dateReported" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="dateVerified" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="writtenOffAmtTotal" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="writtenOffPrincipal" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="settlementAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="highBalance" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="subscriberCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="position" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
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
    "accountCondition",
    "accountDesignator",
    "disputeFlag",
    "industryCode",
    "openClosed",
    "payStatus",
    "verificationIndicator",
    "remark",
    "collectionTrade",
    "grantedTrade",
    "watchTrade",
    "source",
    "disputeRemarks"
})
@XmlRootElement(name = "Tradeline")
public class Tradeline {

    @XmlElement(name = "AccountCondition", required = true)
    protected List<CodeRef> accountCondition;
    @XmlElement(name = "AccountDesignator", required = true)
    protected CodeRef accountDesignator;
    @XmlElement(name = "DisputeFlag")
    protected CodeRef disputeFlag;
    @XmlElement(name = "IndustryCode", required = true)
    protected CodeRef industryCode;
    @XmlElement(name = "OpenClosed", required = true)
    protected CodeRef openClosed;
    @XmlElement(name = "PayStatus", required = true)
    protected CodeRef payStatus;
    @XmlElement(name = "VerificationIndicator", required = true)
    protected CodeRef verificationIndicator;
    @XmlElement(name = "Remark")
    protected List<Remark> remark;
    @XmlElement(name = "CollectionTrade")
    protected CollectionTrade collectionTrade;
    @XmlElement(name = "GrantedTrade")
    protected GrantedTrade grantedTrade;
    @XmlElement(name = "WatchTrade")
    protected WatchTrade watchTrade;
    @XmlElement(name = "Source", required = true)
    protected Source source;
    @XmlElement(name = "DisputeRemarks")
    protected DisputeRemarks disputeRemarks;
    @XmlAttribute(name = "accountNumber", required = true)
    protected String accountNumber;
    @XmlAttribute(name = "creditorName", required = true)
    protected String creditorName;
    @XmlAttribute(name = "currentBalance")
    protected BigDecimal currentBalance;
    @XmlAttribute(name = "dateAccountStatus")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateAccountStatus;
    @XmlAttribute(name = "dateClosed")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateClosed;
    @XmlAttribute(name = "dateOpened")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateOpened;
    @XmlAttribute(name = "dateReported")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateReported;
    @XmlAttribute(name = "dateVerified")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dateVerified;
    @XmlAttribute(name = "writtenOffAmtTotal")
    protected BigDecimal writtenOffAmtTotal;
    @XmlAttribute(name = "writtenOffPrincipal")
    protected BigDecimal writtenOffPrincipal;
    @XmlAttribute(name = "settlementAmount")
    protected BigDecimal settlementAmount;
    @XmlAttribute(name = "highBalance", required = true)
    protected BigDecimal highBalance;
    @XmlAttribute(name = "subscriberCode", required = true)
    protected String subscriberCode;
    @XmlAttribute(name = "position", required = true)
    protected Integer position;
    @XmlAttribute(name = "bureau", required = true)
    protected String bureau;

    /**
     * Gets the value of the accountCondition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accountCondition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccountCondition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CodeRef }
     * 
     * 
     */
    public List<CodeRef> getAccountCondition() {
        if (accountCondition == null) {
            accountCondition = new ArrayList<CodeRef>();
        }
        return this.accountCondition;
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
     * Gets the value of the disputeFlag property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getDisputeFlag() {
        return disputeFlag;
    }

    /**
     * Sets the value of the disputeFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setDisputeFlag(CodeRef value) {
        this.disputeFlag = value;
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
     * Gets the value of the openClosed property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getOpenClosed() {
        return openClosed;
    }

    /**
     * Sets the value of the openClosed property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setOpenClosed(CodeRef value) {
        this.openClosed = value;
    }

    /**
     * Gets the value of the payStatus property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getPayStatus() {
        return payStatus;
    }

    /**
     * Sets the value of the payStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setPayStatus(CodeRef value) {
        this.payStatus = value;
    }

    /**
     * Gets the value of the verificationIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getVerificationIndicator() {
        return verificationIndicator;
    }

    /**
     * Sets the value of the verificationIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setVerificationIndicator(CodeRef value) {
        this.verificationIndicator = value;
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
     * Gets the value of the collectionTrade property.
     * 
     * @return
     *     possible object is
     *     {@link CollectionTrade }
     *     
     */
    public CollectionTrade getCollectionTrade() {
        return collectionTrade;
    }

    /**
     * Sets the value of the collectionTrade property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectionTrade }
     *     
     */
    public void setCollectionTrade(CollectionTrade value) {
        this.collectionTrade = value;
    }

    /**
     * Gets the value of the grantedTrade property.
     * 
     * @return
     *     possible object is
     *     {@link GrantedTrade }
     *     
     */
    public GrantedTrade getGrantedTrade() {
        return grantedTrade;
    }

    /**
     * Sets the value of the grantedTrade property.
     * 
     * @param value
     *     allowed object is
     *     {@link GrantedTrade }
     *     
     */
    public void setGrantedTrade(GrantedTrade value) {
        this.grantedTrade = value;
    }

    /**
     * Gets the value of the watchTrade property.
     * 
     * @return
     *     possible object is
     *     {@link WatchTrade }
     *     
     */
    public WatchTrade getWatchTrade() {
        return watchTrade;
    }

    /**
     * Sets the value of the watchTrade property.
     * 
     * @param value
     *     allowed object is
     *     {@link WatchTrade }
     *     
     */
    public void setWatchTrade(WatchTrade value) {
        this.watchTrade = value;
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
     * Gets the value of the creditorName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditorName() {
        return creditorName;
    }

    /**
     * Sets the value of the creditorName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditorName(String value) {
        this.creditorName = value;
    }

    /**
     * Gets the value of the currentBalance property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    /**
     * Sets the value of the currentBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCurrentBalance(BigDecimal value) {
        this.currentBalance = value;
    }

    /**
     * Gets the value of the dateAccountStatus property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateAccountStatus() {
        return dateAccountStatus;
    }

    /**
     * Sets the value of the dateAccountStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateAccountStatus(XMLGregorianCalendar value) {
        this.dateAccountStatus = value;
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
     * Gets the value of the writtenOffAmtTotal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWrittenOffAmtTotal() {
        return writtenOffAmtTotal;
    }

    /**
     * Sets the value of the writtenOffAmtTotal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWrittenOffAmtTotal(BigDecimal value) {
        this.writtenOffAmtTotal = value;
    }

    /**
     * Gets the value of the writtenOffPrincipal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWrittenOffPrincipal() {
        return writtenOffPrincipal;
    }

    /**
     * Sets the value of the writtenOffPrincipal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWrittenOffPrincipal(BigDecimal value) {
        this.writtenOffPrincipal = value;
    }

    /**
     * Gets the value of the settlementAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    /**
     * Sets the value of the settlementAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSettlementAmount(BigDecimal value) {
        this.settlementAmount = value;
    }

    /**
     * Gets the value of the highBalance property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getHighBalance() {
        return highBalance;
    }

    /**
     * Sets the value of the highBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setHighBalance(BigDecimal value) {
        this.highBalance = value;
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
     * Gets the value of the position property.
     * 
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     */
    public void setPosition(Integer value) {
        this.position = value;
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
