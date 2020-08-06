
package com.opl.mudra.api.cibil_integration.truelink.schema.msp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.EnrollmentStatusType;
import com.opl.mudra.api.cibil_integration.truelink.schema.database.tcps.enumerations.InteractionType;


/**
 * <p>Java class for CustomerEnrollmentInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerEnrollmentInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EnrollmentId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="CustomerId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="OrigCatalogItemId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element ref="{com/truelink/schema/database/tcps/enumerations}EnrollmentStatus"/>
 *         &lt;element name="Fulfillment" type="{com/truelink/schema/database/tcps/enumerations}InteractionType"/>
 *         &lt;element name="Enrollment" type="{com/truelink/schema/database/tcps/enumerations}InteractionType"/>
 *         &lt;element name="PartnerCustomerCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="PartnerTransactionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="EnrollmentSource" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerEnrollmentInfoType", propOrder = {
    "enrollmentId",
    "customerId",
    "origCatalogItemId",
    "enrollmentStatus",
    "fulfillment",
    "enrollment",
    "partnerCustomerCode",
    "partnerTransactionCode",
    "enrollmentSource"
})
public class CustomerEnrollmentInfoType {

    @XmlElement(name = "EnrollmentId")
    protected long enrollmentId;
    @XmlElement(name = "CustomerId")
    protected long customerId;
    @XmlElement(name = "OrigCatalogItemId")
    protected long origCatalogItemId;
    @XmlElement(name = "EnrollmentStatus", namespace = "com/truelink/schema/database/tcps/enumerations", required = true)
    @XmlSchemaType(name = "string")
    protected EnrollmentStatusType enrollmentStatus;
    @XmlElement(name = "Fulfillment", required = true)
    @XmlSchemaType(name = "string")
    protected InteractionType fulfillment;
    @XmlElement(name = "Enrollment", required = true)
    @XmlSchemaType(name = "string")
    protected InteractionType enrollment;
    @XmlElement(name = "PartnerCustomerCode", required = true)
    protected String partnerCustomerCode;
    @XmlElement(name = "PartnerTransactionCode", required = true)
    protected String partnerTransactionCode;
    @XmlElement(name = "EnrollmentSource", required = true)
    protected String enrollmentSource;

    /**
     * Gets the value of the enrollmentId property.
     * 
     */
    public long getEnrollmentId() {
        return enrollmentId;
    }

    /**
     * Sets the value of the enrollmentId property.
     * 
     */
    public void setEnrollmentId(long value) {
        this.enrollmentId = value;
    }

    /**
     * Gets the value of the customerId property.
     * 
     */
    public long getCustomerId() {
        return customerId;
    }

    /**
     * Sets the value of the customerId property.
     * 
     */
    public void setCustomerId(long value) {
        this.customerId = value;
    }

    /**
     * Gets the value of the origCatalogItemId property.
     * 
     */
    public long getOrigCatalogItemId() {
        return origCatalogItemId;
    }

    /**
     * Sets the value of the origCatalogItemId property.
     * 
     */
    public void setOrigCatalogItemId(long value) {
        this.origCatalogItemId = value;
    }

    /**
     * Gets the value of the enrollmentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link EnrollmentStatusType }
     *     
     */
    public EnrollmentStatusType getEnrollmentStatus() {
        return enrollmentStatus;
    }

    /**
     * Sets the value of the enrollmentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnrollmentStatusType }
     *     
     */
    public void setEnrollmentStatus(EnrollmentStatusType value) {
        this.enrollmentStatus = value;
    }

    /**
     * Gets the value of the fulfillment property.
     * 
     * @return
     *     possible object is
     *     {@link InteractionType }
     *     
     */
    public InteractionType getFulfillment() {
        return fulfillment;
    }

    /**
     * Sets the value of the fulfillment property.
     * 
     * @param value
     *     allowed object is
     *     {@link InteractionType }
     *     
     */
    public void setFulfillment(InteractionType value) {
        this.fulfillment = value;
    }

    /**
     * Gets the value of the enrollment property.
     * 
     * @return
     *     possible object is
     *     {@link InteractionType }
     *     
     */
    public InteractionType getEnrollment() {
        return enrollment;
    }

    /**
     * Sets the value of the enrollment property.
     * 
     * @param value
     *     allowed object is
     *     {@link InteractionType }
     *     
     */
    public void setEnrollment(InteractionType value) {
        this.enrollment = value;
    }

    /**
     * Gets the value of the partnerCustomerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerCustomerCode() {
        return partnerCustomerCode;
    }

    /**
     * Sets the value of the partnerCustomerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerCustomerCode(String value) {
        this.partnerCustomerCode = value;
    }

    /**
     * Gets the value of the partnerTransactionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerTransactionCode() {
        return partnerTransactionCode;
    }

    /**
     * Sets the value of the partnerTransactionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerTransactionCode(String value) {
        this.partnerTransactionCode = value;
    }

    /**
     * Gets the value of the enrollmentSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnrollmentSource() {
        return enrollmentSource;
    }

    /**
     * Sets the value of the enrollmentSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnrollmentSource(String value) {
        this.enrollmentSource = value;
    }

}
