
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.SiteType;


/**
 * <p>Java class for CouponDescriptorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CouponDescriptorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CouponNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="BeginDate" type="{http://www.w3.org/2001/XMLSchema}anySimpleType"/>
 *         &lt;element name="ExpirationDate" type="{http://www.w3.org/2001/XMLSchema}anySimpleType"/>
 *         &lt;element name="NumberOfUses" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="FixedPrice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AmountReduction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PercentReduction" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="UseFilter">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Bundle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="Site" type="{com/truelink/schema/database/tcps/enumerations}SiteType" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CouponDescriptorType", propOrder = {
    "name",
    "description",
    "couponNumber",
    "beginDate",
    "expirationDate",
    "numberOfUses",
    "fixedPrice",
    "amountReduction",
    "percentReduction",
    "useFilter"
})
public class CouponDescriptorType {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "CouponNumber", required = true)
    protected String couponNumber;
    @XmlElement(name = "BeginDate", required = true)
    protected Object beginDate;
    @XmlElement(name = "ExpirationDate", required = true)
    protected Object expirationDate;
    @XmlElement(name = "NumberOfUses")
    protected long numberOfUses;
    @XmlElement(name = "FixedPrice")
    protected String fixedPrice;
    @XmlElement(name = "AmountReduction")
    protected String amountReduction;
    @XmlElement(name = "PercentReduction")
    protected BigDecimal percentReduction;
    @XmlElement(name = "UseFilter", required = true)
    protected CouponDescriptorType.UseFilter useFilter;

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
     * Gets the value of the couponNumber property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCouponNumber() {
        return couponNumber;
    }

    /**
     * Sets the value of the couponNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCouponNumber(String value) {
        this.couponNumber = value;
    }

    /**
     * Gets the value of the beginDate property.
     *
     * @return
     *     possible object is
     *     {@link Object }
     *
     */
    public Object getBeginDate() {
        return beginDate;
    }

    /**
     * Sets the value of the beginDate property.
     *
     * @param value
     *     allowed object is
     *     {@link Object }
     *
     */
    public void setBeginDate(Object value) {
        this.beginDate = value;
    }

    /**
     * Gets the value of the expirationDate property.
     *
     * @return
     *     possible object is
     *     {@link Object }
     *
     */
    public Object getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets the value of the expirationDate property.
     *
     * @param value
     *     allowed object is
     *     {@link Object }
     *
     */
    public void setExpirationDate(Object value) {
        this.expirationDate = value;
    }

    /**
     * Gets the value of the numberOfUses property.
     *
     */
    public long getNumberOfUses() {
        return numberOfUses;
    }

    /**
     * Sets the value of the numberOfUses property.
     *
     */
    public void setNumberOfUses(long value) {
        this.numberOfUses = value;
    }

    /**
     * Gets the value of the fixedPrice property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFixedPrice() {
        return fixedPrice;
    }

    /**
     * Sets the value of the fixedPrice property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFixedPrice(String value) {
        this.fixedPrice = value;
    }

    /**
     * Gets the value of the amountReduction property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAmountReduction() {
        return amountReduction;
    }

    /**
     * Sets the value of the amountReduction property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAmountReduction(String value) {
        this.amountReduction = value;
    }

    /**
     * Gets the value of the percentReduction property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    public BigDecimal getPercentReduction() {
        return percentReduction;
    }

    /**
     * Sets the value of the percentReduction property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setPercentReduction(BigDecimal value) {
        this.percentReduction = value;
    }

    /**
     * Gets the value of the useFilter property.
     *
     * @return
     *     possible object is
     *     {@link CouponDescriptorType.UseFilter }
     *
     */
    public CouponDescriptorType.UseFilter getUseFilter() {
        return useFilter;
    }

    /**
     * Sets the value of the useFilter property.
     *
     * @param value
     *     allowed object is
     *     {@link CouponDescriptorType.UseFilter }
     *
     */
    public void setUseFilter(CouponDescriptorType.UseFilter value) {
        this.useFilter = value;
    }


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
     *         &lt;element name="Bundle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="Site" type="{com/truelink/schema/database/tcps/enumerations}SiteType" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "bundle",
        "site"
    })
    public static class UseFilter {

        @XmlElement(name = "Bundle")
        protected String bundle;
        @XmlElement(name = "Site")
        @XmlSchemaType(name = "string")
        protected SiteType site;

        /**
         * Gets the value of the bundle property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBundle() {
            return bundle;
        }

        /**
         * Sets the value of the bundle property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBundle(String value) {
            this.bundle = value;
        }

        /**
         * Gets the value of the site property.
         * 
         * @return
         *     possible object is
         *     {@link SiteType }
         *     
         */
        public SiteType getSite() {
            return site;
        }

        /**
         * Sets the value of the site property.
         * 
         * @param value
         *     allowed object is
         *     {@link SiteType }
         *     
         */
        public void setSite(SiteType value) {
            this.site = value;
        }

    }

}
