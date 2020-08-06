
package com.opl.mudra.api.cibil.model.experian.commcons.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for PERENQRHIST complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PERENQRHIST">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnquiryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnquiryPurposeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnquiryConsAmtCurrencyCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BureauMemberIndustryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BureauMemberId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BureauMemberName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnquiryAmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnquiryRefNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DurationofAgreement" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Frequency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnquiryAccountType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InqAccountPurposeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InqCreditPurpose" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnquiryFinRespCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnquiryBankAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EnquiryTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ENQPRSNSRCH" type="{http://webservice.commcons.experian.com}ENQPRSNSRCH" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ENQPERSALIAS" type="{http://webservice.commcons.experian.com}ENQPERSALIAS" minOccurs="0"/>
 *         &lt;element name="ENQPERSONID" type="{http://webservice.commcons.experian.com}ENQPERSONID" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ENQPERSADDR" type="{http://webservice.commcons.experian.com}ENQPERSADDR" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ENQPERSPHONE" type="{http://webservice.commcons.experian.com}ENQPERSPHONE" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ENQPERSEMAIL" type="{http://webservice.commcons.experian.com}ENQPERSEMAIL" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="EMPLOYER" type="{http://webservice.commcons.experian.com}EMPLOYER" minOccurs="0"/>
 *         &lt;element name="ENQPERSDETAIL" type="{http://webservice.commcons.experian.com}ENQPERSDETAIL" minOccurs="0"/>
 *         &lt;element name="ENQDISPUTE" type="{http://webservice.commcons.experian.com}ENQDISPUTE" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PERENQRHIST", propOrder = {
    "segmentCode",
    "enquiryDate",
    "enquiryPurposeCd",
    "enquiryConsAmtCurrencyCd",
    "bureauMemberIndustryCd",
    "bureauMemberId",
    "bureauMemberName",
    "enquiryAmount",
    "enquiryRefNum",
    "durationofAgreement",
    "frequency",
    "enquiryAccountType",
    "inqAccountPurposeCd",
    "inqCreditPurpose",
    "enquiryFinRespCd",
    "enquiryBankAccountNumber",
    "enquiryTime",
    "enqprsnsrch",
    "enqpersalias",
    "enqpersonid",
    "enqpersaddr",
    "enqpersphone",
    "enqpersemail",
    "employer",
    "enqpersdetail",
    "enqdispute"
})
public class PERENQRHIST {

    @XmlElement(name = "SegmentCode")
    protected String segmentCode;
    @XmlElement(name = "EnquiryDate")
    protected String enquiryDate;
    @XmlElement(name = "EnquiryPurposeCd")
    protected String enquiryPurposeCd;
    @XmlElement(name = "EnquiryConsAmtCurrencyCd")
    protected String enquiryConsAmtCurrencyCd;
    @XmlElement(name = "BureauMemberIndustryCd")
    protected String bureauMemberIndustryCd;
    @XmlElement(name = "BureauMemberId")
    protected String bureauMemberId;
    @XmlElement(name = "BureauMemberName")
    protected String bureauMemberName;
    @XmlElement(name = "EnquiryAmount")
    protected String enquiryAmount;
    @XmlElement(name = "EnquiryRefNum")
    protected String enquiryRefNum;
    @XmlElement(name = "DurationofAgreement")
    protected String durationofAgreement;
    @XmlElement(name = "Frequency")
    protected String frequency;
    @XmlElement(name = "EnquiryAccountType")
    protected String enquiryAccountType;
    @XmlElement(name = "InqAccountPurposeCd")
    protected String inqAccountPurposeCd;
    @XmlElement(name = "InqCreditPurpose")
    protected String inqCreditPurpose;
    @XmlElement(name = "EnquiryFinRespCd")
    protected String enquiryFinRespCd;
    @XmlElement(name = "EnquiryBankAccountNumber")
    protected String enquiryBankAccountNumber;
    @XmlElement(name = "EnquiryTime")
    protected String enquiryTime;
    @XmlElement(name = "ENQPRSNSRCH")
    protected List<ENQPRSNSRCH> enqprsnsrch;
    @XmlElement(name = "ENQPERSALIAS")
    protected ENQPERSALIAS enqpersalias;
    @XmlElement(name = "ENQPERSONID")
    protected List<ENQPERSONID> enqpersonid;
    @XmlElement(name = "ENQPERSADDR")
    protected List<ENQPERSADDR> enqpersaddr;
    @XmlElement(name = "ENQPERSPHONE")
    protected List<ENQPERSPHONE> enqpersphone;
    @XmlElement(name = "ENQPERSEMAIL")
    protected List<ENQPERSEMAIL> enqpersemail;
    @XmlElement(name = "EMPLOYER")
    protected EMPLOYER employer;
    @XmlElement(name = "ENQPERSDETAIL")
    protected ENQPERSDETAIL enqpersdetail;
    @XmlElement(name = "ENQDISPUTE")
    protected List<ENQDISPUTE> enqdispute;

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
     * Gets the value of the bureauMemberIndustryCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBureauMemberIndustryCd() {
        return bureauMemberIndustryCd;
    }

    /**
     * Sets the value of the bureauMemberIndustryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBureauMemberIndustryCd(String value) {
        this.bureauMemberIndustryCd = value;
    }

    /**
     * Gets the value of the bureauMemberId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBureauMemberId() {
        return bureauMemberId;
    }

    /**
     * Sets the value of the bureauMemberId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBureauMemberId(String value) {
        this.bureauMemberId = value;
    }

    /**
     * Gets the value of the bureauMemberName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBureauMemberName() {
        return bureauMemberName;
    }

    /**
     * Sets the value of the bureauMemberName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBureauMemberName(String value) {
        this.bureauMemberName = value;
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
     * Gets the value of the enquiryRefNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnquiryRefNum() {
        return enquiryRefNum;
    }

    /**
     * Sets the value of the enquiryRefNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnquiryRefNum(String value) {
        this.enquiryRefNum = value;
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
     * Gets the value of the enquiryAccountType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnquiryAccountType() {
        return enquiryAccountType;
    }

    /**
     * Sets the value of the enquiryAccountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnquiryAccountType(String value) {
        this.enquiryAccountType = value;
    }

    /**
     * Gets the value of the inqAccountPurposeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInqAccountPurposeCd() {
        return inqAccountPurposeCd;
    }

    /**
     * Sets the value of the inqAccountPurposeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInqAccountPurposeCd(String value) {
        this.inqAccountPurposeCd = value;
    }

    /**
     * Gets the value of the inqCreditPurpose property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInqCreditPurpose() {
        return inqCreditPurpose;
    }

    /**
     * Sets the value of the inqCreditPurpose property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInqCreditPurpose(String value) {
        this.inqCreditPurpose = value;
    }

    /**
     * Gets the value of the enquiryFinRespCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnquiryFinRespCd() {
        return enquiryFinRespCd;
    }

    /**
     * Sets the value of the enquiryFinRespCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnquiryFinRespCd(String value) {
        this.enquiryFinRespCd = value;
    }

    /**
     * Gets the value of the enquiryBankAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnquiryBankAccountNumber() {
        return enquiryBankAccountNumber;
    }

    /**
     * Sets the value of the enquiryBankAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnquiryBankAccountNumber(String value) {
        this.enquiryBankAccountNumber = value;
    }

    /**
     * Gets the value of the enquiryTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnquiryTime() {
        return enquiryTime;
    }

    /**
     * Sets the value of the enquiryTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnquiryTime(String value) {
        this.enquiryTime = value;
    }

    /**
     * Gets the value of the enqprsnsrch property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the enqprsnsrch property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getENQPRSNSRCH().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ENQPRSNSRCH }
     * 
     * 
     */
    public List<ENQPRSNSRCH> getENQPRSNSRCH() {
        if (enqprsnsrch == null) {
            enqprsnsrch = new ArrayList<ENQPRSNSRCH>();
        }
        return this.enqprsnsrch;
    }

    /**
     * Gets the value of the enqpersalias property.
     * 
     * @return
     *     possible object is
     *     {@link ENQPERSALIAS }
     *     
     */
    public ENQPERSALIAS getENQPERSALIAS() {
        return enqpersalias;
    }

    /**
     * Sets the value of the enqpersalias property.
     * 
     * @param value
     *     allowed object is
     *     {@link ENQPERSALIAS }
     *     
     */
    public void setENQPERSALIAS(ENQPERSALIAS value) {
        this.enqpersalias = value;
    }

    /**
     * Gets the value of the enqpersonid property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the enqpersonid property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getENQPERSONID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ENQPERSONID }
     * 
     * 
     */
    public List<ENQPERSONID> getENQPERSONID() {
        if (enqpersonid == null) {
            enqpersonid = new ArrayList<ENQPERSONID>();
        }
        return this.enqpersonid;
    }

    /**
     * Gets the value of the enqpersaddr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the enqpersaddr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getENQPERSADDR().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ENQPERSADDR }
     * 
     * 
     */
    public List<ENQPERSADDR> getENQPERSADDR() {
        if (enqpersaddr == null) {
            enqpersaddr = new ArrayList<ENQPERSADDR>();
        }
        return this.enqpersaddr;
    }

    /**
     * Gets the value of the enqpersphone property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the enqpersphone property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getENQPERSPHONE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ENQPERSPHONE }
     * 
     * 
     */
    public List<ENQPERSPHONE> getENQPERSPHONE() {
        if (enqpersphone == null) {
            enqpersphone = new ArrayList<ENQPERSPHONE>();
        }
        return this.enqpersphone;
    }

    /**
     * Gets the value of the enqpersemail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the enqpersemail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getENQPERSEMAIL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ENQPERSEMAIL }
     * 
     * 
     */
    public List<ENQPERSEMAIL> getENQPERSEMAIL() {
        if (enqpersemail == null) {
            enqpersemail = new ArrayList<ENQPERSEMAIL>();
        }
        return this.enqpersemail;
    }

    /**
     * Gets the value of the employer property.
     * 
     * @return
     *     possible object is
     *     {@link EMPLOYER }
     *     
     */
    public EMPLOYER getEMPLOYER() {
        return employer;
    }

    /**
     * Sets the value of the employer property.
     * 
     * @param value
     *     allowed object is
     *     {@link EMPLOYER }
     *     
     */
    public void setEMPLOYER(EMPLOYER value) {
        this.employer = value;
    }

    /**
     * Gets the value of the enqpersdetail property.
     * 
     * @return
     *     possible object is
     *     {@link ENQPERSDETAIL }
     *     
     */
    public ENQPERSDETAIL getENQPERSDETAIL() {
        return enqpersdetail;
    }

    /**
     * Sets the value of the enqpersdetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link ENQPERSDETAIL }
     *     
     */
    public void setENQPERSDETAIL(ENQPERSDETAIL value) {
        this.enqpersdetail = value;
    }

    /**
     * Gets the value of the enqdispute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the enqdispute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getENQDISPUTE().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ENQDISPUTE }
     * 
     * 
     */
    public List<ENQDISPUTE> getENQDISPUTE() {
        if (enqdispute == null) {
            enqdispute = new ArrayList<ENQDISPUTE>();
        }
        return this.enqdispute;
    }

}
