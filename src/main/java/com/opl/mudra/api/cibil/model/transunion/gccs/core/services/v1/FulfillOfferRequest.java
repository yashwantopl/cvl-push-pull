
package com.opl.mudra.api.cibil.model.transunion.gccs.core.services.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.LegalResponseType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{com/transunion/gccs/core/services/v1}BaseRequestType">
 *       &lt;sequence>
 *         &lt;element name="PartnerCustomerId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CustomerInfo">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{com/transunion/gccs/core/services/v1}CustomerInfoType">
 *                 &lt;sequence>
 *                 &lt;/sequence>
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="LegalCopyStatus" type="{com/truelink/schema/database/tcps/enumerations}LegalResponseType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ProductConfigurationId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "partnerCustomerId",
    "customerInfo",
    "legalCopyStatus"
})
@XmlRootElement(name = "FulfillOfferRequest")
public class FulfillOfferRequest
    extends BaseRequestType
{

    @XmlElement(name = "PartnerCustomerId", required = true)
    protected String partnerCustomerId;
    @XmlElement(name = "CustomerInfo", required = true)
    protected FulfillOfferRequest.CustomerInfo customerInfo;
    @XmlElement(name = "LegalCopyStatus", required = true)
    @XmlSchemaType(name = "string")
    protected LegalResponseType legalCopyStatus;
    @XmlAttribute(name = "ProductConfigurationId", required = true)
    protected String productConfigurationId;

    /**
     * Gets the value of the partnerCustomerId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPartnerCustomerId() {
        return partnerCustomerId;
    }

    /**
     * Sets the value of the partnerCustomerId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPartnerCustomerId(String value) {
        this.partnerCustomerId = value;
    }

    /**
     * Gets the value of the customerInfo property.
     *
     * @return
     *     possible object is
     *     {@link FulfillOfferRequest.CustomerInfo }
     *
     */
    public FulfillOfferRequest.CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    /**
     * Sets the value of the customerInfo property.
     *
     * @param value
     *     allowed object is
     *     {@link FulfillOfferRequest.CustomerInfo }
     *
     */
    public void setCustomerInfo(FulfillOfferRequest.CustomerInfo value) {
        this.customerInfo = value;
    }

    /**
     * Gets the value of the legalCopyStatus property.
     * 
     * @return
     *     possible object is
     *     {@link LegalResponseType }
     *     
     */
    public LegalResponseType getLegalCopyStatus() {
        return legalCopyStatus;
    }

    /**
     * Sets the value of the legalCopyStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link LegalResponseType }
     *     
     */
    public void setLegalCopyStatus(LegalResponseType value) {
        this.legalCopyStatus = value;
    }

    /**
     * Gets the value of the productConfigurationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductConfigurationId() {
        return productConfigurationId;
    }

    /**
     * Sets the value of the productConfigurationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductConfigurationId(String value) {
        this.productConfigurationId = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{com/transunion/gccs/core/services/v1}CustomerInfoType">
     *       &lt;sequence>
     *       &lt;/sequence>
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class CustomerInfo
        extends CustomerInfoType
    {


    }


	@Override
	public String toString() {
		return "FulfillOfferRequest [partnerCustomerId=" + partnerCustomerId + ", customerInfo=" + customerInfo
				+ ", legalCopyStatus=" + legalCopyStatus + ", productConfigurationId=" + productConfigurationId
				+ ", siteName=" + siteName + ", accountName=" + accountName + ", accountCode=" + accountCode
				+ ", clientKey=" + clientKey + ", requestKey=" + requestKey + "]";
	}

}
