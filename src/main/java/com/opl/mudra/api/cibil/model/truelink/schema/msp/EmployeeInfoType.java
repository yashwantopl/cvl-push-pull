
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.EmployeeStatusType;
import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.RoleType;


/**
 * <p>Java class for EmployeeInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EmployeeInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FullName" type="{com/truelink/schema/msp}FullNameInfoType"/>
 *         &lt;element name="Credential" type="{com/truelink/schema/msp}CredentialType"/>
 *         &lt;element name="EmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EmployeeStatus" type="{com/truelink/schema/database/tcps/enumerations}EmployeeStatusType"/>
 *         &lt;element name="Role" type="{com/truelink/schema/database/tcps/enumerations}RoleType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EmployeeInfoType", propOrder = {
    "fullName",
    "credential",
    "emailAddress",
    "employeeStatus",
    "role"
})
public class EmployeeInfoType {

    @XmlElement(name = "FullName", required = true)
    protected FullNameInfoType fullName;
    @XmlElement(name = "Credential", required = true)
    protected CredentialType credential;
    @XmlElement(name = "EmailAddress", required = true)
    protected String emailAddress;
    @XmlElement(name = "EmployeeStatus", required = true)
    @XmlSchemaType(name = "string")
    protected EmployeeStatusType employeeStatus;
    @XmlElement(name = "Role", required = true)
    @XmlSchemaType(name = "string")
    protected List<RoleType> role;

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
     * Gets the value of the credential property.
     * 
     * @return
     *     possible object is
     *     {@link CredentialType }
     *     
     */
    public CredentialType getCredential() {
        return credential;
    }

    /**
     * Sets the value of the credential property.
     * 
     * @param value
     *     allowed object is
     *     {@link CredentialType }
     *     
     */
    public void setCredential(CredentialType value) {
        this.credential = value;
    }

    /**
     * Gets the value of the emailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the value of the emailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailAddress(String value) {
        this.emailAddress = value;
    }

    /**
     * Gets the value of the employeeStatus property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeStatusType }
     *     
     */
    public EmployeeStatusType getEmployeeStatus() {
        return employeeStatus;
    }

    /**
     * Sets the value of the employeeStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeStatusType }
     *     
     */
    public void setEmployeeStatus(EmployeeStatusType value) {
        this.employeeStatus = value;
    }

    /**
     * Gets the value of the role property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the role property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRole().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RoleType }
     * 
     * 
     */
    public List<RoleType> getRole() {
        if (role == null) {
            role = new ArrayList<RoleType>();
        }
        return this.role;
    }

}
