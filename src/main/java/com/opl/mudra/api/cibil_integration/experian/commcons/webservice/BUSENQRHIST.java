
package com.opl.mudra.api.cibil_integration.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for BUSENQRHIST complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BUSENQRHIST">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnquiryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnquiryPurposeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AccountType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnquiryConsAmtCurrencyCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnquiryBureauMemberId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnquiryAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnquiryIndustryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BUSPRSNSRCH" type="{http://webservice.commcons.experian.com}BUSPRSNSRCH" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BUSPERSONID" type="{http://webservice.commcons.experian.com}BUSPERSONID" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BUSPERSADDR" type="{http://webservice.commcons.experian.com}BUSPERSADDR" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BUSPERSPHONE" type="{http://webservice.commcons.experian.com}BUSPERSPHONE" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BUSPERDISPUTE" type="{http://webservice.commcons.experian.com}BUSPERDISPUTE" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BUSENQRHIST", propOrder = {
    "segmentCode",
    "enquiryDate",
    "enquiryPurposeCd",
    "accountType",
    "enquiryConsAmtCurrencyCd",
    "enquiryBureauMemberId",
    "enquiryAmount",
    "enquiryIndustryCd",
    "busprsnsrch",
    "buspersonid",
    "buspersaddr",
    "buspersphone",
    "busperdispute"
})
public class BUSENQRHIST {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "EnquiryDate")
    protected String enquiryDate;
    @XmlElement(name = "EnquiryPurposeCd")
    protected String enquiryPurposeCd;
    @XmlElement(name = "AccountType")
    protected String accountType;
    @XmlElement(name = "EnquiryConsAmtCurrencyCd")
    protected String enquiryConsAmtCurrencyCd;
    @XmlElement(name = "EnquiryBureauMemberId")
    protected String enquiryBureauMemberId;
    @XmlElement(name = "EnquiryAmount")
    protected String enquiryAmount;
    @XmlElement(name = "EnquiryIndustryCd")
    protected String enquiryIndustryCd;
    @XmlElement(name = "BUSPRSNSRCH")
    protected List<BUSPRSNSRCH> busprsnsrch;
    @XmlElement(name = "BUSPERSONID")
    protected List<BUSPERSONID> buspersonid;
    @XmlElement(name = "BUSPERSADDR")
    protected List<BUSPERSADDR> buspersaddr;
    @XmlElement(name = "BUSPERSPHONE")
    protected List<BUSPERSPHONE> buspersphone;
    @XmlElement(name = "BUSPERDISPUTE")
    protected List<BUSPERDISPUTE> busperdispute;

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
     * Gets the value of the enquiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnquiryDate() {
        return enquiryDate;
    }

    /**
     * Sets the value of the enquiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnquiryDate(String value) {
        this.enquiryDate = value;
    }

    /**
     * Gets the value of the enquiryPurposeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnquiryPurposeCd() {
        return enquiryPurposeCd;
    }

    /**
     * Sets the value of the enquiryPurposeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnquiryPurposeCd(String value) {
        this.enquiryPurposeCd = value;
    }

    /**
     * Gets the value of the accountType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * Sets the value of the accountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountType(String value) {
        this.accountType = value;
    }

    /**
     * Gets the value of the enquiryConsAmtCurrencyCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnquiryConsAmtCurrencyCd() {
        return enquiryConsAmtCurrencyCd;
    }

    /**
     * Sets the value of the enquiryConsAmtCurrencyCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnquiryConsAmtCurrencyCd(String value) {
        this.enquiryConsAmtCurrencyCd = value;
    }

    /**
     * Gets the value of the enquiryBureauMemberId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnquiryBureauMemberId() {
        return enquiryBureauMemberId;
    }

    /**
     * Sets the value of the enquiryBureauMemberId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnquiryBureauMemberId(String value) {
        this.enquiryBureauMemberId = value;
    }

    /**
     * Gets the value of the enquiryAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnquiryAmount() {
        return enquiryAmount;
    }

    /**
     * Sets the value of the enquiryAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnquiryAmount(String value) {
        this.enquiryAmount = value;
    }

    /**
     * Gets the value of the enquiryIndustryCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnquiryIndustryCd() {
        return enquiryIndustryCd;
    }

    /**
     * Sets the value of the enquiryIndustryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnquiryIndustryCd(String value) {
        this.enquiryIndustryCd = value;
    }

    /**
     * Gets the value of the busprsnsrch property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the busprsnsrch property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBUSPRSNSRCH().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BUSPRSNSRCH }
     * 
     * 
     */
    public List<BUSPRSNSRCH> getBUSPRSNSRCH() {
        if (busprsnsrch == null) {
            busprsnsrch = new ArrayList<BUSPRSNSRCH>();
        }
        return this.busprsnsrch;
    }

    /**
     * Gets the value of the buspersonid property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the buspersonid property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBUSPERSONID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BUSPERSONID }
     * 
     * 
     */
    public List<BUSPERSONID> getBUSPERSONID() {
        if (buspersonid == null) {
            buspersonid = new ArrayList<BUSPERSONID>();
        }
        return this.buspersonid;
    }

    /**
     * Gets the value of the buspersaddr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the buspersaddr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBUSPERSADDR().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BUSPERSADDR }
     * 
     * 
     */
    public List<BUSPERSADDR> getBUSPERSADDR() {
        if (buspersaddr == null) {
            buspersaddr = new ArrayList<BUSPERSADDR>();
        }
        return this.buspersaddr;
    }

    /**
     * Gets the value of the buspersphone property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the buspersphone property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBUSPERSPHONE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BUSPERSPHONE }
     * 
     * 
     */
    public List<BUSPERSPHONE> getBUSPERSPHONE() {
        if (buspersphone == null) {
            buspersphone = new ArrayList<BUSPERSPHONE>();
        }
        return this.buspersphone;
    }

    /**
     * Gets the value of the busperdispute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the busperdispute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBUSPERDISPUTE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BUSPERDISPUTE }
     * 
     * 
     */
    public List<BUSPERDISPUTE> getBUSPERDISPUTE() {
        if (busperdispute == null) {
            busperdispute = new ArrayList<BUSPERDISPUTE>();
        }
        return this.busperdispute;
    }

}
