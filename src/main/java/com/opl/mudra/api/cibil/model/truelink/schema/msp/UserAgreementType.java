
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserAgreementType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserAgreementType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="UserAgreementId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="UserAgreement" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserAgreementType", propOrder = {
    "userAgreementId",
    "userAgreement"
})
public class UserAgreementType {

    @XmlElement(name = "UserAgreementId")
    protected long userAgreementId;
    @XmlElement(name = "UserAgreement", required = true)
    protected String userAgreement;

    /**
     * Gets the value of the userAgreementId property.
     * 
     */
    public long getUserAgreementId() {
        return userAgreementId;
    }

    /**
     * Sets the value of the userAgreementId property.
     * 
     */
    public void setUserAgreementId(long value) {
        this.userAgreementId = value;
    }

    /**
     * Gets the value of the userAgreement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAgreement() {
        return userAgreement;
    }

    /**
     * Sets the value of the userAgreement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAgreement(String value) {
        this.userAgreement = value;
    }

}
