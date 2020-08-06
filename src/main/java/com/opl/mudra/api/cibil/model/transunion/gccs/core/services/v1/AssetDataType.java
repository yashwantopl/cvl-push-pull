
package com.opl.mudra.api.cibil.model.transunion.gccs.core.services.v1;


import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil.model.truelink.ds.sch.report.truelink.v5.TrueLinkCreditReportType;
import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.AssetStatusType;
import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.AssetType;


/**
 * <p>Java class for AssetDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssetDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AssetId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Type" type="{com/truelink/schema/database/tcps/enumerations}AssetType"/>
 *         &lt;element name="Status" type="{com/truelink/schema/database/tcps/enumerations}AssetStatusType"/>
 *         &lt;element name="CreationDate" type="{http://www.w3.org/2001/XMLSchema}anySimpleType"/>
 *         &lt;element name="ExpirationDate" type="{http://www.w3.org/2001/XMLSchema}anySimpleType"/>
 *         &lt;element name="SafetyCheckFailure" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="TrueLinkCreditReport" type="{com/truelink/ds/sch/report/truelink/v5}TrueLinkCreditReportType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssetDataType", propOrder = {
    "assetId",
    "type",
    "status",
    "creationDate",
    "expirationDate",
    "safetyCheckFailure",
    "trueLinkCreditReport"
})
public class AssetDataType {

    @XmlElement(name = "AssetId")
    protected long assetId;
    @XmlElement(name = "Type", required = true)
    @XmlSchemaType(name = "string")
    protected AssetType type;
    @XmlElement(name = "Status", required = true)
    @XmlSchemaType(name = "string")
    protected AssetStatusType status;
    @XmlElement(name = "CreationDate", required = true)
    protected Object creationDate;
    @XmlElement(name = "ExpirationDate", required = true)
    protected Object expirationDate;
    @XmlElement(name = "SafetyCheckFailure")
    protected Boolean safetyCheckFailure;
    // Commented for v2
    // @XmlElementRef(name = "TrueLinkCreditReport", namespace = "com/truelink/ds/sch/report/truelink/v5", type = JAXBElement.class, required = false)
    @XmlElement(name = "TrueLinkCreditReport")
    protected TrueLinkCreditReportType trueLinkCreditReport;

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
     *     {@link AssetType }
     *     
     */
    public AssetType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssetType }
     *     
     */
    public void setType(AssetType value) {
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
     * Gets the value of the safetyCheckFailure property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSafetyCheckFailure() {
        return safetyCheckFailure;
    }

    /**
     * Sets the value of the safetyCheckFailure property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSafetyCheckFailure(Boolean value) {
        this.safetyCheckFailure = value;
    }

    /**
     * Gets the value of the trueLinkCreditReport property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link TrueLinkCreditReportType }{@code >}
     *     
     */
    public TrueLinkCreditReportType getTrueLinkCreditReport() {
        return trueLinkCreditReport;
    }

    /**
     * Sets the value of the trueLinkCreditReport property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link TrueLinkCreditReportType }{@code >}
     *     
     */
    public void setTrueLinkCreditReport(TrueLinkCreditReportType value) {
        this.trueLinkCreditReport = value;
    }
    
	@Override
	public String toString() {
		return "AssetDataType [assetId=" + assetId + ", type=" + type + ", status=" + status + ", creationDate="
				+ creationDate + ", expirationDate=" + expirationDate + ", safetyCheckFailure=" + safetyCheckFailure
				+ ", trueLinkCreditReport=" + (trueLinkCreditReport!=null ? trueLinkCreditReport.toString(): null) + "]";
	}
    

}
