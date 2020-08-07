
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.AssetStatusType;


/**
 * <p>Java class for AssetType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssetType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AssetId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Type" type="{com/truelink/schema/database/tcps/enumerations}AssetType"/>
 *         &lt;element name="Status" type="{com/truelink/schema/database/tcps/enumerations}AssetStatusType"/>
 *         &lt;element name="FulfillmentId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CreationDate" type="{http://www.w3.org/2001/XMLSchema}anySimpleType"/>
 *         &lt;element name="ExpirationDate" type="{http://www.w3.org/2001/XMLSchema}anySimpleType"/>
 *         &lt;element name="VendorArtifactId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="AlertWatchId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="CreditFileId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssetType", propOrder = {
    "assetId",
    "type",
    "status",
    "fulfillmentId",
    "creationDate",
    "expirationDate",
    "vendorArtifactId",
    "alertWatchId",
    "creditFileId"
})
public class AssetType {

    @XmlElement(name = "AssetId")
    protected long assetId;
    @XmlElement(name = "Type", required = true)
    @XmlSchemaType(name = "string")
    protected com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.AssetType type;
    @XmlElement(name = "Status", required = true)
    @XmlSchemaType(name = "string")
    protected AssetStatusType status;
    @XmlElement(name = "FulfillmentId")
    protected long fulfillmentId;
    @XmlElement(name = "CreationDate", required = true)
    protected Object creationDate;
    @XmlElement(name = "ExpirationDate", required = true)
    protected Object expirationDate;
    @XmlElement(name = "VendorArtifactId")
    protected Long vendorArtifactId;
    @XmlElement(name = "AlertWatchId")
    protected Long alertWatchId;
    @XmlElement(name = "CreditFileId")
    protected Long creditFileId;

    /**
     * Gets the value of the assetId property.
     * 
     */
    public long getAssetId() {
        return assetId;
    }

    /**
     * Sets the value of the assetId property.
     * 
     */
    public void setAssetId(long value) {
        this.assetId = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.AssetType }
     *     
     */
    public com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.AssetType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.AssetType }
     *     
     */
    public void setType(com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.AssetType value) {
        this.type = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link AssetStatusType }
     *     
     */
    public AssetStatusType getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssetStatusType }
     *     
     */
    public void setStatus(AssetStatusType value) {
        this.status = value;
    }

    /**
     * Gets the value of the fulfillmentId property.
     * 
     */
    public long getFulfillmentId() {
        return fulfillmentId;
    }

    /**
     * Sets the value of the fulfillmentId property.
     * 
     */
    public void setFulfillmentId(long value) {
        this.fulfillmentId = value;
    }

    /**
     * Gets the value of the creationDate property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setCreationDate(Object value) {
        this.creationDate = value;
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
     * Gets the value of the vendorArtifactId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getVendorArtifactId() {
        return vendorArtifactId;
    }

    /**
     * Sets the value of the vendorArtifactId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setVendorArtifactId(Long value) {
        this.vendorArtifactId = value;
    }

    /**
     * Gets the value of the alertWatchId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAlertWatchId() {
        return alertWatchId;
    }

    /**
     * Sets the value of the alertWatchId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAlertWatchId(Long value) {
        this.alertWatchId = value;
    }

    /**
     * Gets the value of the creditFileId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCreditFileId() {
        return creditFileId;
    }

    /**
     * Sets the value of the creditFileId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCreditFileId(Long value) {
        this.creditFileId = value;
    }

}
