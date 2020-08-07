
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for NGINQUIRY complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NGINQUIRY">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InqUserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InqBureauMemberId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InqBureauMemberIndustry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InqBureauMemberName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InqRefNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InqPurposeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InqAcctTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InqAmt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InqAmtMonetaryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InqProductName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InqProductCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InqProductSearchTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Frequency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InqCompanyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnquiryApplicationType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DurationofAgreement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InqLegalEntity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InqCompanyBankAccNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InqPAN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NGINQADDR" type="{http://webservice.commcons.experian.com}NGINQADDR" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="NGINQTEL" type="{http://webservice.commcons.experian.com}NGINQTEL" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NGINQUIRY", propOrder = {
    "segmentCode",
    "inqUserId",
    "inqBureauMemberId",
    "inqBureauMemberIndustry",
    "inqBureauMemberName",
    "inqRefNum",
    "inqPurposeCd",
    "inqAcctTypeCd",
    "inqAmt",
    "inqAmtMonetaryCd",
    "inqProductName",
    "inqProductCd",
    "inqProductSearchTypeCd",
    "frequency",
    "inqCompanyName",
    "enquiryApplicationType",
    "durationofAgreement",
    "inqLegalEntity",
    "inqCompanyBankAccNumber",
    "inqPAN",
    "nginqaddr",
    "nginqtel"
})
public class NGINQUIRY {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "InqUserId")
    protected String inqUserId;
    @XmlElement(name = "InqBureauMemberId")
    protected String inqBureauMemberId;
    @XmlElement(name = "InqBureauMemberIndustry")
    protected String inqBureauMemberIndustry;
    @XmlElement(name = "InqBureauMemberName")
    protected String inqBureauMemberName;
    @XmlElement(name = "InqRefNum")
    protected String inqRefNum;
    @XmlElement(name = "InqPurposeCd")
    protected String inqPurposeCd;
    @XmlElement(name = "InqAcctTypeCd")
    protected String inqAcctTypeCd;
    @XmlElement(name = "InqAmt")
    protected String inqAmt;
    @XmlElement(name = "InqAmtMonetaryCd")
    protected String inqAmtMonetaryCd;
    @XmlElement(name = "InqProductName")
    protected String inqProductName;
    @XmlElement(name = "InqProductCd")
    protected String inqProductCd;
    @XmlElement(name = "InqProductSearchTypeCd")
    protected String inqProductSearchTypeCd;
    @XmlElement(name = "Frequency")
    protected String frequency;
    @XmlElement(name = "InqCompanyName")
    protected String inqCompanyName;
    @XmlElement(name = "EnquiryApplicationType")
    protected String enquiryApplicationType;
    @XmlElement(name = "DurationofAgreement")
    protected String durationofAgreement;
    @XmlElement(name = "InqLegalEntity")
    protected String inqLegalEntity;
    @XmlElement(name = "InqCompanyBankAccNumber")
    protected String inqCompanyBankAccNumber;
    @XmlElement(name = "InqPAN")
    protected String inqPAN;
    @XmlElement(name = "NGINQADDR")
    protected List<NGINQADDR> nginqaddr;
    @XmlElement(name = "NGINQTEL")
    protected List<NGINQTEL> nginqtel;

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
     * Gets the value of the inqUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInqUserId() {
        return inqUserId;
    }

    /**
     * Sets the value of the inqUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInqUserId(String value) {
        this.inqUserId = value;
    }

    /**
     * Gets the value of the inqBureauMemberId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInqBureauMemberId() {
        return inqBureauMemberId;
    }

    /**
     * Sets the value of the inqBureauMemberId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInqBureauMemberId(String value) {
        this.inqBureauMemberId = value;
    }

    /**
     * Gets the value of the inqBureauMemberIndustry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInqBureauMemberIndustry() {
        return inqBureauMemberIndustry;
    }

    /**
     * Sets the value of the inqBureauMemberIndustry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInqBureauMemberIndustry(String value) {
        this.inqBureauMemberIndustry = value;
    }

    /**
     * Gets the value of the inqBureauMemberName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInqBureauMemberName() {
        return inqBureauMemberName;
    }

    /**
     * Sets the value of the inqBureauMemberName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInqBureauMemberName(String value) {
        this.inqBureauMemberName = value;
    }

    /**
     * Gets the value of the inqRefNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInqRefNum() {
        return inqRefNum;
    }

    /**
     * Sets the value of the inqRefNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInqRefNum(String value) {
        this.inqRefNum = value;
    }

    /**
     * Gets the value of the inqPurposeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInqPurposeCd() {
        return inqPurposeCd;
    }

    /**
     * Sets the value of the inqPurposeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInqPurposeCd(String value) {
        this.inqPurposeCd = value;
    }

    /**
     * Gets the value of the inqAcctTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInqAcctTypeCd() {
        return inqAcctTypeCd;
    }

    /**
     * Sets the value of the inqAcctTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInqAcctTypeCd(String value) {
        this.inqAcctTypeCd = value;
    }

    /**
     * Gets the value of the inqAmt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInqAmt() {
        return inqAmt;
    }

    /**
     * Sets the value of the inqAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInqAmt(String value) {
        this.inqAmt = value;
    }

    /**
     * Gets the value of the inqAmtMonetaryCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInqAmtMonetaryCd() {
        return inqAmtMonetaryCd;
    }

    /**
     * Sets the value of the inqAmtMonetaryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInqAmtMonetaryCd(String value) {
        this.inqAmtMonetaryCd = value;
    }

    /**
     * Gets the value of the inqProductName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInqProductName() {
        return inqProductName;
    }

    /**
     * Sets the value of the inqProductName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInqProductName(String value) {
        this.inqProductName = value;
    }

    /**
     * Gets the value of the inqProductCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInqProductCd() {
        return inqProductCd;
    }

    /**
     * Sets the value of the inqProductCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInqProductCd(String value) {
        this.inqProductCd = value;
    }

    /**
     * Gets the value of the inqProductSearchTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInqProductSearchTypeCd() {
        return inqProductSearchTypeCd;
    }

    /**
     * Sets the value of the inqProductSearchTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInqProductSearchTypeCd(String value) {
        this.inqProductSearchTypeCd = value;
    }

    /**
     * Gets the value of the frequency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * Sets the value of the frequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrequency(String value) {
        this.frequency = value;
    }

    /**
     * Gets the value of the inqCompanyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInqCompanyName() {
        return inqCompanyName;
    }

    /**
     * Sets the value of the inqCompanyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInqCompanyName(String value) {
        this.inqCompanyName = value;
    }

    /**
     * Gets the value of the enquiryApplicationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnquiryApplicationType() {
        return enquiryApplicationType;
    }

    /**
     * Sets the value of the enquiryApplicationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnquiryApplicationType(String value) {
        this.enquiryApplicationType = value;
    }

    /**
     * Gets the value of the durationofAgreement property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDurationofAgreement() {
        return durationofAgreement;
    }

    /**
     * Sets the value of the durationofAgreement property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDurationofAgreement(String value) {
        this.durationofAgreement = value;
    }

    /**
     * Gets the value of the inqLegalEntity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInqLegalEntity() {
        return inqLegalEntity;
    }

    /**
     * Sets the value of the inqLegalEntity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInqLegalEntity(String value) {
        this.inqLegalEntity = value;
    }

    /**
     * Gets the value of the inqCompanyBankAccNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInqCompanyBankAccNumber() {
        return inqCompanyBankAccNumber;
    }

    /**
     * Sets the value of the inqCompanyBankAccNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInqCompanyBankAccNumber(String value) {
        this.inqCompanyBankAccNumber = value;
    }

    /**
     * Gets the value of the inqPAN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInqPAN() {
        return inqPAN;
    }

    /**
     * Sets the value of the inqPAN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInqPAN(String value) {
        this.inqPAN = value;
    }

    /**
     * Gets the value of the nginqaddr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nginqaddr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNGINQADDR().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NGINQADDR }
     * 
     * 
     */
    public List<NGINQADDR> getNGINQADDR() {
        if (nginqaddr == null) {
            nginqaddr = new ArrayList<NGINQADDR>();
        }
        return this.nginqaddr;
    }

    /**
     * Gets the value of the nginqtel property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nginqtel property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNGINQTEL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NGINQTEL }
     * 
     * 
     */
    public List<NGINQTEL> getNGINQTEL() {
        if (nginqtel == null) {
            nginqtel = new ArrayList<NGINQTEL>();
        }
        return this.nginqtel;
    }

}
