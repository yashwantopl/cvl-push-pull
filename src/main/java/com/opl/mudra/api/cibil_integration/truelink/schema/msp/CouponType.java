
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CouponType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CouponType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CouponId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Descriptor" type="{com/truelink/schema/msp}CouponDescriptorType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CouponType", propOrder = {
    "couponId",
    "descriptor"
})
public class CouponType {

    @XmlElement(name = "CouponId")
    protected long couponId;
    @XmlElement(name = "Descriptor", required = true)
    protected CouponDescriptorType descriptor;

    /**
     * Gets the value of the couponId property.
     * 
     */
    public long getCouponId() {
        return couponId;
    }

    /**
     * Sets the value of the couponId property.
     * 
     */
    public void setCouponId(long value) {
        this.couponId = value;
    }

    /**
     * Gets the value of the descriptor property.
     * 
     * @return
     *     possible object is
     *     {@link CouponDescriptorType }
     *     
     */
    public CouponDescriptorType getDescriptor() {
        return descriptor;
    }

    /**
     * Sets the value of the descriptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link CouponDescriptorType }
     *     
     */
    public void setDescriptor(CouponDescriptorType value) {
        this.descriptor = value;
    }

}
