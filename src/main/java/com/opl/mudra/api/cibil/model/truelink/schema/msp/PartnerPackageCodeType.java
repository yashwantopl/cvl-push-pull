
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PartnerPackageCodeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PartnerPackageCodeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CatalogItemId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="LocaleTypeId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="EnrollmentPackageCode" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="OnToOfflinePackageCode" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartnerPackageCodeType", propOrder = {
    "catalogItemId",
    "localeTypeId",
    "enrollmentPackageCode",
    "onToOfflinePackageCode"
})
public class PartnerPackageCodeType {

    @XmlElement(name = "CatalogItemId")
    protected long catalogItemId;
    @XmlElement(name = "LocaleTypeId")
    protected long localeTypeId;
    @XmlElement(name = "EnrollmentPackageCode")
    protected long enrollmentPackageCode;
    @XmlElement(name = "OnToOfflinePackageCode")
    protected long onToOfflinePackageCode;

    /**
     * Gets the value of the catalogItemId property.
     * 
     */
    public long getCatalogItemId() {
        return catalogItemId;
    }

    /**
     * Sets the value of the catalogItemId property.
     * 
     */
    public void setCatalogItemId(long value) {
        this.catalogItemId = value;
    }

    /**
     * Gets the value of the localeTypeId property.
     * 
     */
    public long getLocaleTypeId() {
        return localeTypeId;
    }

    /**
     * Sets the value of the localeTypeId property.
     * 
     */
    public void setLocaleTypeId(long value) {
        this.localeTypeId = value;
    }

    /**
     * Gets the value of the enrollmentPackageCode property.
     * 
     */
    public long getEnrollmentPackageCode() {
        return enrollmentPackageCode;
    }

    /**
     * Sets the value of the enrollmentPackageCode property.
     * 
     */
    public void setEnrollmentPackageCode(long value) {
        this.enrollmentPackageCode = value;
    }

    /**
     * Gets the value of the onToOfflinePackageCode property.
     * 
     */
    public long getOnToOfflinePackageCode() {
        return onToOfflinePackageCode;
    }

    /**
     * Sets the value of the onToOfflinePackageCode property.
     * 
     */
    public void setOnToOfflinePackageCode(long value) {
        this.onToOfflinePackageCode = value;
    }

}
