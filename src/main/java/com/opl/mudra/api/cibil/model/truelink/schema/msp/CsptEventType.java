
package com.opl.mudra.api.cibil.model.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.CommEventMediumType;
import com.opl.mudra.api.cibil.model.truelink.schema.database.tcps.enumerations.CsptIssueType;


/**
 * <p>Java class for CsptEventType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CsptEventType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CsrId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CallType" type="{com/truelink/schema/database/tcps/enumerations}CsptIssueType"/>
 *         &lt;element name="Reason" type="{com/truelink/schema/database/tcps/enumerations}CsptReasonType" minOccurs="0"/>
 *         &lt;element name="Action" type="{com/truelink/schema/database/tcps/enumerations}CsptActionType"/>
 *         &lt;element name="Contact" type="{com/truelink/schema/database/tcps/enumerations}CommEventMediumType"/>
 *         &lt;element name="SiteName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="CustomerId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Product" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Remarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Details" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SubjectCsrId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="CsrName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EventDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CsptEventType", propOrder = {
    "csrId",
    "callType",
    "reason",
    "action",
    "contact",
    "siteName",
    "customerId",
    "product",
    "remarks",
    "details",
    "subjectCsrId",
    "csrName",
    "eventDate"
})
public class CsptEventType {

    @XmlElement(name = "CsrId")
    protected long csrId;
    @XmlElement(name = "CallType", required = true)
    @XmlSchemaType(name = "string")
    protected CsptIssueType callType;
    @XmlElement(name = "Reason")
    @XmlSchemaType(name = "string")
    protected CsptReasonType reason;
    @XmlElement(name = "Action", required = true)
    protected String action;
    @XmlElement(name = "Contact", required = true)
    @XmlSchemaType(name = "string")
    protected CommEventMediumType contact;
    @XmlElement(name = "SiteName", required = true)
    protected String siteName;
    @XmlElement(name = "CustomerId")
    protected Long customerId;
    @XmlElement(name = "Product")
    protected String product;
    @XmlElement(name = "Remarks")
    protected String remarks;
    @XmlElement(name = "Details")
    protected String details;
    @XmlElement(name = "SubjectCsrId")
    protected Long subjectCsrId;
    @XmlElement(name = "CsrName")
    protected String csrName;
    @XmlElement(name = "EventDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar eventDate;

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
     * Gets the value of the callType property.
     * 
     * @return
     *     possible object is
     *     {@link CsptIssueType }
     *     
     */
    public CsptIssueType getCallType() {
        return callType;
    }

    /**
     * Sets the value of the callType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CsptIssueType }
     *     
     */
    public void setCallType(CsptIssueType value) {
        this.callType = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link CsptReasonType }
     *     
     */
    public CsptReasonType getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link CsptReasonType }
     *     
     */
    public void setReason(CsptReasonType value) {
        this.reason = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * Gets the value of the contact property.
     * 
     * @return
     *     possible object is
     *     {@link CommEventMediumType }
     *     
     */
    public CommEventMediumType getContact() {
        return contact;
    }

    /**
     * Sets the value of the contact property.
     * 
     * @param value
     *     allowed object is
     *     {@link CommEventMediumType }
     *     
     */
    public void setContact(CommEventMediumType value) {
        this.contact = value;
    }

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
     * Gets the value of the customerId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the value of the customerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCustomerId(Long value) {
        this.customerId = value;
    }

    /**
     * Gets the value of the product property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProduct() {
        return product;
    }

    /**
     * Sets the value of the product property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProduct(String value) {
        this.product = value;
    }

    /**
     * Gets the value of the remarks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * Sets the value of the remarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemarks(String value) {
        this.remarks = value;
    }

    /**
     * Gets the value of the details property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets the value of the details property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetails(String value) {
        this.details = value;
    }

    /**
     * Gets the value of the subjectCsrId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSubjectCsrId() {
        return subjectCsrId;
    }

    /**
     * Sets the value of the subjectCsrId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSubjectCsrId(Long value) {
        this.subjectCsrId = value;
    }

    /**
     * Gets the value of the csrName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCsrName() {
        return csrName;
    }

    /**
     * Sets the value of the csrName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCsrName(String value) {
        this.csrName = value;
    }

    /**
     * Gets the value of the eventDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEventDate() {
        return eventDate;
    }

    /**
     * Sets the value of the eventDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEventDate(XMLGregorianCalendar value) {
        this.eventDate = value;
    }

}
