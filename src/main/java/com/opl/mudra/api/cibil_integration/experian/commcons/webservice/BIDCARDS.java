
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BIDCARDS complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BIDCARDS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdNumberType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IndiaIdNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdIssueDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IdExpirationDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BIDCARDS", propOrder = {
    "segmentCode",
    "idNumberType",
    "indiaIdNumber",
    "idIssueDate",
    "idExpirationDate"
})
public class BIDCARDS {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "IdNumberType")
    protected String idNumberType;
    @XmlElement(name = "IndiaIdNumber")
    protected String indiaIdNumber;
    @XmlElement(name = "IdIssueDate")
    protected String idIssueDate;
    @XmlElement(name = "IdExpirationDate")
    protected String idExpirationDate;

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
     * Gets the value of the idNumberType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdNumberType() {
        return idNumberType;
    }

    /**
     * Sets the value of the idNumberType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdNumberType(String value) {
        this.idNumberType = value;
    }

    /**
     * Gets the value of the indiaIdNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndiaIdNumber() {
        return indiaIdNumber;
    }

    /**
     * Sets the value of the indiaIdNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndiaIdNumber(String value) {
        this.indiaIdNumber = value;
    }

    /**
     * Gets the value of the idIssueDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdIssueDate() {
        return idIssueDate;
    }

    /**
     * Sets the value of the idIssueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdIssueDate(String value) {
        this.idIssueDate = value;
    }

    /**
     * Gets the value of the idExpirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdExpirationDate() {
        return idExpirationDate;
    }

    /**
     * Sets the value of the idExpirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdExpirationDate(String value) {
        this.idExpirationDate = value;
    }

}
