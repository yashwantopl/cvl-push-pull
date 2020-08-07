
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for CsrInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CsrInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CsrId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="EmployeeInfo" type="{com/truelink/schema/msp}EmployeeInfoType"/>
 *         &lt;element name="LastLoginDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CsrInfoType", propOrder = {
    "csrId",
    "employeeInfo",
    "lastLoginDate"
})
public class CsrInfoType {

    @XmlElement(name = "CsrId")
    protected long csrId;
    @XmlElement(name = "EmployeeInfo", required = true)
    protected EmployeeInfoType employeeInfo;
    @XmlElement(name = "LastLoginDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastLoginDate;

    /**
     * Gets the value of the csrId property.
     * 
     */
    public long getCsrId() {
        return csrId;
    }

    /**
     * Sets the value of the csrId property.
     * 
     */
    public void setCsrId(long value) {
        this.csrId = value;
    }

    /**
     * Gets the value of the employeeInfo property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeInfoType }
     *     
     */
    public EmployeeInfoType getEmployeeInfo() {
        return employeeInfo;
    }

    /**
     * Sets the value of the employeeInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeInfoType }
     *     
     */
    public void setEmployeeInfo(EmployeeInfoType value) {
        this.employeeInfo = value;
    }

    /**
     * Gets the value of the lastLoginDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * Sets the value of the lastLoginDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastLoginDate(XMLGregorianCalendar value) {
        this.lastLoginDate = value;
    }

}
