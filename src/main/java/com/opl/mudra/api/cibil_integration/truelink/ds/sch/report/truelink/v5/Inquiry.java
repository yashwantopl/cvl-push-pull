
package com.opl.mudra.api.cibil_integration.truelink.ds.sch.report.truelink.v5;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;


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
 *         &lt;element name="IndustryCode" type="{com/truelink/ds/sch/report/truelink/v5}CodeRef"/>
 *         &lt;element ref="{com/truelink/ds/sch/report/truelink/v5}Source"/>
 *       &lt;/sequence>
 *       &lt;attribute name="inquiryDate" use="required" type="{http://www.w3.org/2001/XMLSchema}date" />
 *       &lt;attribute name="subscriberName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="subscriberNumber" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="bureau" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="inquiryType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="amount" type="{http://www.w3.org/2001/XMLSchema}decimal" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "industryCode",
    "source"
})
@XmlRootElement(name = "Inquiry")
public class Inquiry {

    @XmlElement(name = "IndustryCode", required = true)
    protected CodeRef industryCode;
    @XmlElement(name = "Source", required = true)
    protected Source source;
    @XmlAttribute(name = "inquiryDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar inquiryDate;
    @XmlAttribute(name = "subscriberName", required = true)
    protected String subscriberName;
    @XmlAttribute(name = "subscriberNumber", required = true)
    protected String subscriberNumber;
    @XmlAttribute(name = "bureau", required = true)
    protected String bureau;
    @XmlAttribute(name = "inquiryType")
    protected String inquiryType;
    @XmlAttribute(name = "amount")
    protected BigDecimal amount;

    /**
     * Gets the value of the industryCode property.
     * 
     * @return
     *     possible object is
     *     {@link CodeRef }
     *     
     */
    public CodeRef getIndustryCode() {
        return industryCode;
    }

    /**
     * Sets the value of the industryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CodeRef }
     *     
     */
    public void setIndustryCode(CodeRef value) {
        this.industryCode = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link Source }
     *     
     */
    public Source getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link Source }
     *     
     */
    public void setSource(Source value) {
        this.source = value;
    }

    /**
     * Gets the value of the inquiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInquiryDate() {
        return inquiryDate;
    }

    /**
     * Sets the value of the inquiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInquiryDate(XMLGregorianCalendar value) {
        this.inquiryDate = value;
    }

    /**
     * Gets the value of the subscriberName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriberName() {
        return subscriberName;
    }

    /**
     * Sets the value of the subscriberName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriberName(String value) {
        this.subscriberName = value;
    }

    /**
     * Gets the value of the subscriberNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriberNumber() {
        return subscriberNumber;
    }

    /**
     * Sets the value of the subscriberNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriberNumber(String value) {
        this.subscriberNumber = value;
    }

    /**
     * Gets the value of the bureau property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBureau() {
        return bureau;
    }

    /**
     * Sets the value of the bureau property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBureau(String value) {
        this.bureau = value;
    }

    /**
     * Gets the value of the inquiryType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInquiryType() {
        return inquiryType;
    }

    /**
     * Sets the value of the inquiryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInquiryType(String value) {
        this.inquiryType = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmount(BigDecimal value) {
        this.amount = value;
    }

}
