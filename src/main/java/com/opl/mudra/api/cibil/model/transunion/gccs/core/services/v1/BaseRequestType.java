
package com.opl.mudra.api.cibil.model.transunion.gccs.core.services.v1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BaseRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BaseRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SiteName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AccountName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="AccountCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ClientKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RequestKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseRequestType", propOrder = {
    "siteName",
    "accountName",
    "accountCode",
    "clientKey",
    "requestKey"
})
@XmlSeeAlso({
    GetAuthenticationQuestionsRequest.class,
    VerifyAuthenticationAnswersRequest.class,
    GetCustomerAssetsRequest.class,
    FulfillOfferRequest.class,
    UpdateCustomerIVStatusRequest.class,
    PingRequest.class,
    GetCreditScoreRequest.class
})
public class BaseRequestType {

    @XmlElement(name = "SiteName", required = true)
    protected String siteName;
    @XmlElement(name = "AccountName", required = true)
    protected String accountName;
    @XmlElement(name = "AccountCode", required = true)
    protected String accountCode;
    @XmlElement(name = "ClientKey", required = true)
    protected String clientKey;
    @XmlElement(name = "RequestKey", required = true)
    protected String requestKey;

    /**
     * Gets the value of the siteName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiteName() {
        return siteName;
    }

    /**
     * Sets the value of the siteName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiteName(String value) {
        this.siteName = value;
    }

    /**
     * Gets the value of the accountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the value of the accountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountName(String value) {
        this.accountName = value;
    }

    /**
     * Gets the value of the accountCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountCode() {
        return accountCode;
    }

    /**
     * Sets the value of the accountCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountCode(String value) {
        this.accountCode = value;
    }

    /**
     * Gets the value of the clientKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientKey() {
        return clientKey;
    }

    /**
     * Sets the value of the clientKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientKey(String value) {
        this.clientKey = value;
    }

    /**
     * Gets the value of the requestKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestKey() {
        return requestKey;
    }

    /**
     * Sets the value of the requestKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestKey(String value) {
        this.requestKey = value;
    }

}
