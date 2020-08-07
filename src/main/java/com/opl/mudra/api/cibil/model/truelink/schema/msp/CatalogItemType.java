
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CatalogItemType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CatalogItemType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BundleId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="BillingPeriodId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="BillingDelayOnline" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="BillingDelayOffline" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="UpgradeCatalogItemId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OfferPrice" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="PartnerOfferCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PartnerOfferDesc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PartnerGroupId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="PartnerGroupType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PartialCustomer" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="BillingBundleId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="PartnerProductCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PartnerChannelCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ComponentDescription" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BenefitComponentDescription" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CatalogItemType", propOrder = {
    "bundleId",
    "billingPeriodId",
    "billingDelayOnline",
    "billingDelayOffline",
    "upgradeCatalogItemId",
    "name",
    "description",
    "offerPrice",
    "partnerOfferCode",
    "partnerOfferDesc",
    "partnerGroupId",
    "partnerGroupType",
    "partialCustomer",
    "billingBundleId",
    "partnerProductCode",
    "partnerChannelCode",
    "componentDescription",
    "benefitComponentDescription"
})
public class CatalogItemType {

    @XmlElement(name = "BundleId")
    protected long bundleId;
    @XmlElement(name = "BillingPeriodId")
    protected long billingPeriodId;
    @XmlElement(name = "BillingDelayOnline")
    protected long billingDelayOnline;
    @XmlElement(name = "BillingDelayOffline")
    protected long billingDelayOffline;
    @XmlElement(name = "UpgradeCatalogItemId")
    protected long upgradeCatalogItemId;
    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "OfferPrice", required = true)
    protected BigDecimal offerPrice;
    @XmlElement(name = "PartnerOfferCode", required = true)
    protected String partnerOfferCode;
    @XmlElement(name = "PartnerOfferDesc", required = true)
    protected String partnerOfferDesc;
    @XmlElement(name = "PartnerGroupId")
    protected long partnerGroupId;
    @XmlElement(name = "PartnerGroupType", required = true)
    protected String partnerGroupType;
    @XmlElement(name = "PartialCustomer")
    protected boolean partialCustomer;
    @XmlElement(name = "BillingBundleId")
    protected Long billingBundleId;
    @XmlElement(name = "PartnerProductCode")
    protected String partnerProductCode;
    @XmlElement(name = "PartnerChannelCode")
    protected String partnerChannelCode;
    @XmlElement(name = "ComponentDescription")
    protected List<String> componentDescription;
    @XmlElement(name = "BenefitComponentDescription")
    protected List<String> benefitComponentDescription;

    /**
     * Gets the value of the bundleId property.
     * 
     */
    public long getBundleId() {
        return bundleId;
    }

    /**
     * Sets the value of the bundleId property.
     * 
     */
    public void setBundleId(long value) {
        this.bundleId = value;
    }

    /**
     * Gets the value of the billingPeriodId property.
     * 
     */
    public long getBillingPeriodId() {
        return billingPeriodId;
    }

    /**
     * Sets the value of the billingPeriodId property.
     * 
     */
    public void setBillingPeriodId(long value) {
        this.billingPeriodId = value;
    }

    /**
     * Gets the value of the billingDelayOnline property.
     * 
     */
    public long getBillingDelayOnline() {
        return billingDelayOnline;
    }

    /**
     * Sets the value of the billingDelayOnline property.
     * 
     */
    public void setBillingDelayOnline(long value) {
        this.billingDelayOnline = value;
    }

    /**
     * Gets the value of the billingDelayOffline property.
     * 
     */
    public long getBillingDelayOffline() {
        return billingDelayOffline;
    }

    /**
     * Sets the value of the billingDelayOffline property.
     * 
     */
    public void setBillingDelayOffline(long value) {
        this.billingDelayOffline = value;
    }

    /**
     * Gets the value of the upgradeCatalogItemId property.
     * 
     */
    public long getUpgradeCatalogItemId() {
        return upgradeCatalogItemId;
    }

    /**
     * Sets the value of the upgradeCatalogItemId property.
     * 
     */
    public void setUpgradeCatalogItemId(long value) {
        this.upgradeCatalogItemId = value;
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
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the offerPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getOfferPrice() {
        return offerPrice;
    }

    /**
     * Sets the value of the offerPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setOfferPrice(BigDecimal value) {
        this.offerPrice = value;
    }

    /**
     * Gets the value of the partnerOfferCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerOfferCode() {
        return partnerOfferCode;
    }

    /**
     * Sets the value of the partnerOfferCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerOfferCode(String value) {
        this.partnerOfferCode = value;
    }

    /**
     * Gets the value of the partnerOfferDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerOfferDesc() {
        return partnerOfferDesc;
    }

    /**
     * Sets the value of the partnerOfferDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerOfferDesc(String value) {
        this.partnerOfferDesc = value;
    }

    /**
     * Gets the value of the partnerGroupId property.
     * 
     */
    public long getPartnerGroupId() {
        return partnerGroupId;
    }

    /**
     * Sets the value of the partnerGroupId property.
     * 
     */
    public void setPartnerGroupId(long value) {
        this.partnerGroupId = value;
    }

    /**
     * Gets the value of the partnerGroupType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerGroupType() {
        return partnerGroupType;
    }

    /**
     * Sets the value of the partnerGroupType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerGroupType(String value) {
        this.partnerGroupType = value;
    }

    /**
     * Gets the value of the partialCustomer property.
     * 
     */
    public boolean isPartialCustomer() {
        return partialCustomer;
    }

    /**
     * Sets the value of the partialCustomer property.
     * 
     */
    public void setPartialCustomer(boolean value) {
        this.partialCustomer = value;
    }

    /**
     * Gets the value of the billingBundleId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBillingBundleId() {
        return billingBundleId;
    }

    /**
     * Sets the value of the billingBundleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBillingBundleId(Long value) {
        this.billingBundleId = value;
    }

    /**
     * Gets the value of the partnerProductCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerProductCode() {
        return partnerProductCode;
    }

    /**
     * Sets the value of the partnerProductCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerProductCode(String value) {
        this.partnerProductCode = value;
    }

    /**
     * Gets the value of the partnerChannelCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerChannelCode() {
        return partnerChannelCode;
    }

    /**
     * Sets the value of the partnerChannelCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerChannelCode(String value) {
        this.partnerChannelCode = value;
    }

    /**
     * Gets the value of the componentDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the componentDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComponentDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getComponentDescription() {
        if (componentDescription == null) {
            componentDescription = new ArrayList<String>();
        }
        return this.componentDescription;
    }

    /**
     * Gets the value of the benefitComponentDescription property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the benefitComponentDescription property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBenefitComponentDescription().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getBenefitComponentDescription() {
        if (benefitComponentDescription == null) {
            benefitComponentDescription = new ArrayList<String>();
        }
        return this.benefitComponentDescription;
    }

}
