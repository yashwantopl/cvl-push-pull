
package com.opl.mudra.api.cibil.model.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;


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
 *         &lt;element name="AccountCondition" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="AccountDesignator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DisputeFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="IndustryCode" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="OpenClosed" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="PayStatus" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="VerificationIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Remark" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}CollectionTradeComparison" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}GrantedTradeComparison" minOccurs="0"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}WatchTradeComparison" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="accountNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="creditorName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="currentBalance" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="dateAccountStatus" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="dateClosed" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="dateOpened" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="dateReported" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="dateVerified" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="highBalance" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *       &lt;attribute name="subscriberCode" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="position" type="{http://www.w3.org/2001/XMLSchema}int" />
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
    "collectionTradeComparison",
    "grantedTradeComparison",
    "watchTradeComparison"
})
@XmlRootElement(name = "TradelineComparison")
public class TradelineComparison {

    @XmlElement(name = "AccountCondition")
    protected boolean accountCondition;
    @XmlElement(name = "AccountDesignator")
    protected boolean accountDesignator;
    @XmlElement(name = "DisputeFlag")
    protected boolean disputeFlag;
    @XmlElement(name = "IndustryCode")
    protected boolean industryCode;
    @XmlElement(name = "OpenClosed")
    protected boolean openClosed;
    @XmlElement(name = "PayStatus")
    protected boolean payStatus;
    @XmlElement(name = "VerificationIndicator")
    protected boolean verificationIndicator;
    @XmlElement(name = "Remark")
    protected Boolean remark;
    @XmlElement(name = "CollectionTradeComparison")
    protected CollectionTradeComparison collectionTradeComparison;
    @XmlElement(name = "GrantedTradeComparison")
    protected GrantedTradeComparison grantedTradeComparison;
    @XmlElement(name = "WatchTradeComparison")
    protected WatchTradeComparison watchTradeComparison;
    @XmlAttribute(name = "accountNumber", required = true)
    protected boolean accountNumber;
    @XmlAttribute(name = "creditorName", required = true)
    protected String creditorName;
    @XmlAttribute(name = "currentBalance", required = true)
    protected BigDecimal currentBalance;
    @XmlAttribute(name = "dateAccountStatus")
    protected Boolean dateAccountStatus;
    @XmlAttribute(name = "dateClosed")
    protected Boolean dateClosed;
    @XmlAttribute(name = "dateOpened")
    protected Boolean dateOpened;
    @XmlAttribute(name = "dateReported")
    protected Boolean dateReported;
    @XmlAttribute(name = "dateVerified")
    protected Boolean dateVerified;
    @XmlAttribute(name = "highBalance", required = true)
    protected BigDecimal highBalance;
    @XmlAttribute(name = "subscriberCode", required = true)
    protected boolean subscriberCode;
    @XmlAttribute(name = "position")
    protected Integer position;

    /**
     * Gets the value of the accountCondition property.
     * 
     */
    public boolean isAccountCondition() {
        return accountCondition;
    }

    /**
     * Sets the value of the accountCondition property.
     * 
     */
    public void setAccountCondition(boolean value) {
        this.accountCondition = value;
    }

    /**
     * Gets the value of the accountDesignator property.
     * 
     */
    public boolean isAccountDesignator() {
        return accountDesignator;
    }

    /**
     * Sets the value of the accountDesignator property.
     * 
     */
    public void setAccountDesignator(boolean value) {
        this.accountDesignator = value;
    }

    /**
     * Gets the value of the disputeFlag property.
     * 
     */
    public boolean isDisputeFlag() {
        return disputeFlag;
    }

    /**
     * Sets the value of the disputeFlag property.
     * 
     */
    public void setDisputeFlag(boolean value) {
        this.disputeFlag = value;
    }

    /**
     * Gets the value of the industryCode property.
     * 
     */
    public boolean isIndustryCode() {
        return industryCode;
    }

    /**
     * Sets the value of the industryCode property.
     * 
     */
    public void setIndustryCode(boolean value) {
        this.industryCode = value;
    }

    /**
     * Gets the value of the openClosed property.
     * 
     */
    public boolean isOpenClosed() {
        return openClosed;
    }

    /**
     * Sets the value of the openClosed property.
     * 
     */
    public void setOpenClosed(boolean value) {
        this.openClosed = value;
    }

    /**
     * Gets the value of the payStatus property.
     * 
     */
    public boolean isPayStatus() {
        return payStatus;
    }

    /**
     * Sets the value of the payStatus property.
     * 
     */
    public void setPayStatus(boolean value) {
        this.payStatus = value;
    }

    /**
     * Gets the value of the verificationIndicator property.
     * 
     */
    public boolean isVerificationIndicator() {
        return verificationIndicator;
    }

    /**
     * Sets the value of the verificationIndicator property.
     * 
     */
    public void setVerificationIndicator(boolean value) {
        this.verificationIndicator = value;
    }

    /**
     * Gets the value of the remark property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRemark() {
        return remark;
    }

    /**
     * Sets the value of the remark property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRemark(Boolean value) {
        this.remark = value;
    }

    /**
     * Gets the value of the collectionTradeComparison property.
     * 
     * @return
     *     possible object is
     *     {@link CollectionTradeComparison }
     *     
     */
    public CollectionTradeComparison getCollectionTradeComparison() {
        return collectionTradeComparison;
    }

    /**
     * Sets the value of the collectionTradeComparison property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectionTradeComparison }
     *     
     */
    public void setCollectionTradeComparison(CollectionTradeComparison value) {
        this.collectionTradeComparison = value;
    }

    /**
     * Gets the value of the grantedTradeComparison property.
     * 
     * @return
     *     possible object is
     *     {@link GrantedTradeComparison }
     *     
     */
    public GrantedTradeComparison getGrantedTradeComparison() {
        return grantedTradeComparison;
    }

    /**
     * Sets the value of the grantedTradeComparison property.
     * 
     * @param value
     *     allowed object is
     *     {@link GrantedTradeComparison }
     *     
     */
    public void setGrantedTradeComparison(GrantedTradeComparison value) {
        this.grantedTradeComparison = value;
    }

    /**
     * Gets the value of the watchTradeComparison property.
     * 
     * @return
     *     possible object is
     *     {@link WatchTradeComparison }
     *     
     */
    public WatchTradeComparison getWatchTradeComparison() {
        return watchTradeComparison;
    }

    /**
     * Sets the value of the watchTradeComparison property.
     * 
     * @param value
     *     allowed object is
     *     {@link WatchTradeComparison }
     *     
     */
    public void setWatchTradeComparison(WatchTradeComparison value) {
        this.watchTradeComparison = value;
    }

    /**
     * Gets the value of the accountNumber property.
     * 
     */
    public boolean isAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the accountNumber property.
     * 
     */
    public void setAccountNumber(boolean value) {
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
     *     {@link Boolean }
     *     
     */
    public Boolean isDateAccountStatus() {
        return dateAccountStatus;
    }

    /**
     * Sets the value of the dateAccountStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDateAccountStatus(Boolean value) {
        this.dateAccountStatus = value;
    }

    /**
     * Gets the value of the dateClosed property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDateClosed() {
        return dateClosed;
    }

    /**
     * Sets the value of the dateClosed property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDateClosed(Boolean value) {
        this.dateClosed = value;
    }

    /**
     * Gets the value of the dateOpened property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDateOpened() {
        return dateOpened;
    }

    /**
     * Sets the value of the dateOpened property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDateOpened(Boolean value) {
        this.dateOpened = value;
    }

    /**
     * Gets the value of the dateReported property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDateReported() {
        return dateReported;
    }

    /**
     * Sets the value of the dateReported property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDateReported(Boolean value) {
        this.dateReported = value;
    }

    /**
     * Gets the value of the dateVerified property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDateVerified() {
        return dateVerified;
    }

    /**
     * Sets the value of the dateVerified property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDateVerified(Boolean value) {
        this.dateVerified = value;
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
     */
    public boolean isSubscriberCode() {
        return subscriberCode;
    }

    /**
     * Sets the value of the subscriberCode property.
     * 
     */
    public void setSubscriberCode(boolean value) {
        this.subscriberCode = value;
    }

    /**
     * Gets the value of the position property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * Sets the value of the position property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPosition(Integer value) {
        this.position = value;
    }

}
