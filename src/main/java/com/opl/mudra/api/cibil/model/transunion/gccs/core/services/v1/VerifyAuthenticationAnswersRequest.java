
package com.opl.mudra.api.cibil.model.transunion.gccs.core.services.v1;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.LocaleType;


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
 *         &lt;element name="LocaleType" type="{com/truelink/schema/database/tcps/enumerations}LocaleType" minOccurs="0"/>
 *         &lt;element name="IVAnswer" type="{com/transunion/gccs/core/services/v1}IVAnswer" maxOccurs="unbounded"/>
 *         &lt;element name="ChallengeConfigGUID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
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
    "localeType",
    "ivAnswer",
    "challengeConfigGUID"
})
@XmlRootElement(name = "VerifyAuthenticationAnswersRequest")
public class VerifyAuthenticationAnswersRequest
    extends BaseRequestType
{

    @XmlElement(name = "PartnerCustomerId", required = true)
    protected String partnerCustomerId;
    @XmlElement(name = "LocaleType")
    @XmlSchemaType(name = "string")
    protected LocaleType localeType;
    @XmlElement(name = "IVAnswer", required = true)
    protected List<IVAnswer> ivAnswer;
    @XmlElement(name = "ChallengeConfigGUID", required = true)
    protected String challengeConfigGUID;

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
     * Gets the value of the localeType property.
     * 
     * @return
     *     possible object is
     *     {@link LocaleType }
     *     
     */
    public LocaleType getLocaleType() {
        return localeType;
    }

    /**
     * Sets the value of the localeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocaleType }
     *     
     */
    public void setLocaleType(LocaleType value) {
        this.localeType = value;
    }

    /**
     * Gets the value of the ivAnswer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ivAnswer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIVAnswer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IVAnswer }
     * 
     * 
     */
    public List<IVAnswer> getIVAnswer() {
        if (ivAnswer == null) {
            ivAnswer = new ArrayList<IVAnswer>();
        }
        return this.ivAnswer;
    }

    /**
     * Gets the value of the challengeConfigGUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChallengeConfigGUID() {
        return challengeConfigGUID;
    }

    /**
     * Sets the value of the challengeConfigGUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChallengeConfigGUID(String value) {
        this.challengeConfigGUID = value;
    }

	@Override
	public String toString() {
		return "VerifyAuthenticationAnswersRequest [partnerCustomerId=" + partnerCustomerId + ", localeType="
				+ localeType + ", ivAnswer=" + ivAnswer + ", challengeConfigGUID=" + challengeConfigGUID + ", siteName="
				+ siteName + ", accountName=" + accountName + ", accountCode=" + accountCode + ", clientKey="
				+ clientKey + ", requestKey=" + requestKey + "]";
	}

}
