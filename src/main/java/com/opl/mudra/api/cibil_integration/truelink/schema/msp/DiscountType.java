
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.DiscountAdjustmentType;
import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.DiscountTargetType;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;


/**
 * <p>Java class for DiscountType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DiscountType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DiscountId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="DiscountAdjustment" type="{com/truelink/schema/database/tcps/enumerations}DiscountAdjustmentType"/>
 *         &lt;element name="DiscountTarget" type="{com/truelink/schema/database/tcps/enumerations}DiscountTargetType"/>
 *         &lt;element name="RedemptionLimit" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="RedemptionCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="IssuanceCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="PriceReduction" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="FixedPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="PercentReduction" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiscountType", propOrder = {
    "discountId",
    "discountAdjustment",
    "discountTarget",
    "redemptionLimit",
    "redemptionCount",
    "issuanceCount",
    "priceReduction",
    "fixedPrice",
    "percentReduction"
})
public class DiscountType {

    @XmlElement(name = "DiscountId")
    protected long discountId;
    @XmlElement(name = "DiscountAdjustment", required = true)
    @XmlSchemaType(name = "string")
    protected DiscountAdjustmentType discountAdjustment;
    @XmlElement(name = "DiscountTarget", required = true)
    @XmlSchemaType(name = "string")
    protected DiscountTargetType discountTarget;
    @XmlElement(name = "RedemptionLimit")
    protected long redemptionLimit;
    @XmlElement(name = "RedemptionCount")
    protected long redemptionCount;
    @XmlElement(name = "IssuanceCount")
    protected long issuanceCount;
    @XmlElement(name = "PriceReduction")
    protected BigDecimal priceReduction;
    @XmlElement(name = "FixedPrice")
    protected BigDecimal fixedPrice;
    @XmlElement(name = "PercentReduction")
    protected BigDecimal percentReduction;

    /**
     * Gets the value of the discountId property.
     * 
     */
    public long getDiscountId() {
        return discountId;
    }

    /**
     * Sets the value of the discountId property.
     * 
     */
    public void setDiscountId(long value) {
        this.discountId = value;
    }

    /**
     * Gets the value of the discountAdjustment property.
     * 
     * @return
     *     possible object is
     *     {@link DiscountAdjustmentType }
     *     
     */
    public DiscountAdjustmentType getDiscountAdjustment() {
        return discountAdjustment;
    }

    /**
     * Sets the value of the discountAdjustment property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscountAdjustmentType }
     *     
     */
    public void setDiscountAdjustment(DiscountAdjustmentType value) {
        this.discountAdjustment = value;
    }

    /**
     * Gets the value of the discountTarget property.
     * 
     * @return
     *     possible object is
     *     {@link DiscountTargetType }
     *     
     */
    public DiscountTargetType getDiscountTarget() {
        return discountTarget;
    }

    /**
     * Sets the value of the discountTarget property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscountTargetType }
     *     
     */
    public void setDiscountTarget(DiscountTargetType value) {
        this.discountTarget = value;
    }

    /**
     * Gets the value of the redemptionLimit property.
     * 
     */
    public long getRedemptionLimit() {
        return redemptionLimit;
    }

    /**
     * Sets the value of the redemptionLimit property.
     * 
     */
    public void setRedemptionLimit(long value) {
        this.redemptionLimit = value;
    }

    /**
     * Gets the value of the redemptionCount property.
     * 
     */
    public long getRedemptionCount() {
        return redemptionCount;
    }

    /**
     * Sets the value of the redemptionCount property.
     * 
     */
    public void setRedemptionCount(long value) {
        this.redemptionCount = value;
    }

    /**
     * Gets the value of the issuanceCount property.
     * 
     */
    public long getIssuanceCount() {
        return issuanceCount;
    }

    /**
     * Sets the value of the issuanceCount property.
     * 
     */
    public void setIssuanceCount(long value) {
        this.issuanceCount = value;
    }

    /**
     * Gets the value of the priceReduction property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPriceReduction() {
        return priceReduction;
    }

    /**
     * Sets the value of the priceReduction property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPriceReduction(BigDecimal value) {
        this.priceReduction = value;
    }

    /**
     * Gets the value of the fixedPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getFixedPrice() {
        return fixedPrice;
    }

    /**
     * Sets the value of the fixedPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setFixedPrice(BigDecimal value) {
        this.fixedPrice = value;
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

}
