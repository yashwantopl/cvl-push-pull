
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DISPUTE complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DISPUTE">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DisputeRegisteredDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DisputeUpdatedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DisputeFieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DisputeText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DisputeReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DisputeResCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DISPUTE", propOrder = {
    "segmentCode",
    "disputeRegisteredDate",
    "disputeUpdatedDate",
    "disputeFieldName",
    "disputeText",
    "disputeReasonCode",
    "disputeResCode"
})
public class DISPUTE {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "DisputeRegisteredDate")
    protected String disputeRegisteredDate;
    @XmlElement(name = "DisputeUpdatedDate")
    protected String disputeUpdatedDate;
    @XmlElement(name = "DisputeFieldName")
    protected String disputeFieldName;
    @XmlElement(name = "DisputeText")
    protected String disputeText;
    @XmlElement(name = "DisputeReasonCode")
    protected String disputeReasonCode;
    @XmlElement(name = "DisputeResCode")
    protected String disputeResCode;

    /**
     * Gets the value of the segmentCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSegmentCode() {
        return segmentCode;
    }

    /**
     * Sets the value of the segmentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSegmentCode(String value) {
        this.segmentCode = value;
    }

    /**
     * Gets the value of the disputeRegisteredDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisputeRegisteredDate() {
        return disputeRegisteredDate;
    }

    /**
     * Sets the value of the disputeRegisteredDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisputeRegisteredDate(String value) {
        this.disputeRegisteredDate = value;
    }

    /**
     * Gets the value of the disputeUpdatedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisputeUpdatedDate() {
        return disputeUpdatedDate;
    }

    /**
     * Sets the value of the disputeUpdatedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisputeUpdatedDate(String value) {
        this.disputeUpdatedDate = value;
    }

    /**
     * Gets the value of the disputeFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisputeFieldName() {
        return disputeFieldName;
    }

    /**
     * Sets the value of the disputeFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisputeFieldName(String value) {
        this.disputeFieldName = value;
    }

    /**
     * Gets the value of the disputeText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisputeText() {
        return disputeText;
    }

    /**
     * Sets the value of the disputeText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisputeText(String value) {
        this.disputeText = value;
    }

    /**
     * Gets the value of the disputeReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisputeReasonCode() {
        return disputeReasonCode;
    }

    /**
     * Sets the value of the disputeReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisputeReasonCode(String value) {
        this.disputeReasonCode = value;
    }

    /**
     * Gets the value of the disputeResCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisputeResCode() {
        return disputeResCode;
    }

    /**
     * Sets the value of the disputeResCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisputeResCode(String value) {
        this.disputeResCode = value;
    }

}
