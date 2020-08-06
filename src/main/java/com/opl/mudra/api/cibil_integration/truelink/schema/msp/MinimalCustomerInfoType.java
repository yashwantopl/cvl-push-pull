
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;


import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.SiteType;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for MinimalCustomerInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MinimalCustomerInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FullName" type="{com/truelink/schema/msp}FullNameInfoType"/>
 *         &lt;element name="SocialSecurityNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Site" type="{com/truelink/schema/database/tcps/enumerations}SiteType"/>
 *         &lt;element name="TestUser" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="MemberSince" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MinimalCustomerInfoType", propOrder = {
    "fullName",
    "socialSecurityNumber",
    "site",
    "testUser",
    "memberSince"
})
public class MinimalCustomerInfoType {

    @XmlElement(name = "FullName", required = true)
    protected FullNameInfoType fullName;
    @XmlElement(name = "SocialSecurityNumber", required = true)
    protected String socialSecurityNumber;
    @XmlElement(name = "Site", required = true)
    @XmlSchemaType(name = "string")
    protected SiteType site;
    @XmlElement(name = "TestUser")
    protected Long testUser;
    @XmlElement(name = "MemberSince")
    protected Object memberSince;

    /**
     * Gets the value of the fullName property.
     * 
     * @return
     *     possible object is
     *     {@link FullNameInfoType }
     *     
     */
    public FullNameInfoType getFullName() {
        return fullName;
    }

    /**
     * Sets the value of the fullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link FullNameInfoType }
     *     
     */
    public void setFullName(FullNameInfoType value) {
        this.fullName = value;
    }

    /**
     * Gets the value of the socialSecurityNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    /**
     * Sets the value of the socialSecurityNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSocialSecurityNumber(String value) {
        this.socialSecurityNumber = value;
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

    /**
     * Gets the value of the testUser property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTestUser() {
        return testUser;
    }

    /**
     * Sets the value of the testUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTestUser(Long value) {
        this.testUser = value;
    }

    /**
     * Gets the value of the memberSince property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getMemberSince() {
        return memberSince;
    }

    /**
     * Sets the value of the memberSince property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setMemberSince(Object value) {
        this.memberSince = value;
    }

}
