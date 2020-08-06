
package com.opl.mudra.api.cibil_integration.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IndustryCode" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *       &lt;attribute name="inquiryDate" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="subscriberName" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="subscriberNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="bureau" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="inquiryType" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "industryCode"
})
@XmlRootElement(name = "InquiryComparison")
public class InquiryComparison {

    @XmlElement(name = "IndustryCode")
    protected boolean industryCode;
    @XmlAttribute(name = "inquiryDate", required = true)
    protected boolean inquiryDate;
    @XmlAttribute(name = "subscriberName", required = true)
    protected boolean subscriberName;
    @XmlAttribute(name = "subscriberNumber", required = true)
    protected boolean subscriberNumber;
    @XmlAttribute(name = "bureau", required = true)
    protected boolean bureau;
    @XmlAttribute(name = "inquiryType")
    protected Boolean inquiryType;

    /**
     * Gets the value of the industryCode property.
     * 
     */
    public boolean isIndustryCode() {
        return industryCode;
    }

    /**
     * Sets the value of the industryCode property.
     * 
     */
    public void setIndustryCode(boolean value) {
        this.industryCode = value;
    }

    /**
     * Gets the value of the inquiryDate property.
     * 
     */
    public boolean isInquiryDate() {
        return inquiryDate;
    }

    /**
     * Sets the value of the inquiryDate property.
     * 
     */
    public void setInquiryDate(boolean value) {
        this.inquiryDate = value;
    }

    /**
     * Gets the value of the subscriberName property.
     * 
     */
    public boolean isSubscriberName() {
        return subscriberName;
    }

    /**
     * Sets the value of the subscriberName property.
     * 
     */
    public void setSubscriberName(boolean value) {
        this.subscriberName = value;
    }

    /**
     * Gets the value of the subscriberNumber property.
     * 
     */
    public boolean isSubscriberNumber() {
        return subscriberNumber;
    }

    /**
     * Sets the value of the subscriberNumber property.
     * 
     */
    public void setSubscriberNumber(boolean value) {
        this.subscriberNumber = value;
    }

    /**
     * Gets the value of the bureau property.
     * 
     */
    public boolean isBureau() {
        return bureau;
    }

    /**
     * Sets the value of the bureau property.
     * 
     */
    public void setBureau(boolean value) {
        this.bureau = value;
    }

    /**
     * Gets the value of the inquiryType property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInquiryType() {
        return inquiryType;
    }

    /**
     * Sets the value of the inquiryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInquiryType(Boolean value) {
        this.inquiryType = value;
    }

}
